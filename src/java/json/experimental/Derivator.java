package json.experimental;

import json.coerce.Convert;
import json.data.JArr;
import json.data.JObj;
import json.data.Json;
import json.data.JsonT;
import util.Colls;
import util.Debug;
import util.Either;

import static json.coerce.DefaultConverters.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Derivator {

    // You could try the same as Jackson and force the Class<A> type by annotation.
    private static Either<String, ?> coerceFromJson(final Class<?> clazz, final JsonT jsont) {
        final String type = clazz.getTypeName().toLowerCase();
        if (type.equals("long")) {
            return jsont.as(JSON_TO_LONG);
        } else if (type.equals("int")) {
            return jsont.as(JSON_TO_INT);
        } else if (type.equals("float")) {
            return jsont.as(JSON_TO_FLOAT);
        } else if (type.equals("double")) {
            return jsont.as(JSON_TO_DOUBLE);
        } else if (type.equals("boolean")) {
            return jsont.as(JSON_TO_BOOLEAN);
        } else if (type.equals("java.lang.string")) {
            return jsont.as(JSON_TO_STRING);
        } else if (type.contains("java.util.list")) { // this is idiotic;
            // this also doesn't work properly. At least not directly. I can derive the `Object` contents, but I can't derive its proper class due to erasure.
            // so instantiation is not concrete
            return jsont.as(JSON_TO_LIST).flatMap(list -> Colls.traversel(list, a -> reader(Object.class).convert(a)));
        } else return jsont.affix().flatMap(j -> reader(clazz).convert(j));
    }

    private static <A> Either<String, Json> coerceToJson(final Field field, final A instance) {
        final String type = field.getType().getTypeName().toLowerCase();
        try {
            if (type.equals("long")) {
                return LONG_TO_JSON.convert(field.getLong(instance));
            } else if (type.equals("float")) {
                return FLOAT_TO_JSON.convert(field.getFloat(instance));
            } else if (type.equals("int")) {
                return INT_TO_JSON.convert(field.getInt(instance));
            } else if (type.equals("boolean")) {
                return BOOLEAN_TO_JSON.convert(field.getBoolean(instance));
            } else if (type.equals("string")) {
                return STRING_TO_JSON.convert((String) field.get(instance));
            } else {
                return writer((Class<Object>) field.getType()).convert(field.get(instance));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Either.left(e.getMessage());
        }
    }

    private static <A> Either<String, Json> objectWriter(final A value) {
        final Field[] fields = value.getClass().getDeclaredFields();
        JsonT obj = JObj.empty().transform();
        for (int i = 0; i < fields.length; i++) {
            final Field field = fields[i];
            final Either<String, Json> result = coerceToJson(field, value);
            if (result.isRight()) {
                obj = obj.insert(result.value(), field.getName());
            } else return Either.left(result.error());
        }
        return obj.affix();
    }

    private static <A> Either<String, Json> listWriter(final List<?> values) {
        final List<Json> js = new ArrayList<>();
        HashMap<String, Convert<Object, Json>> writers = new HashMap<>();
        for (Object elm: values) {
            final Class<Object> clazz = (Class<Object>) elm.getClass();
            final String type         = clazz.getTypeName();
            if (writers.containsKey(type)) {
                final Either<String, Json> result = writers.get(type).convert(elm);
                if (result.isRight()) js.add(result.value());
                else return Either.left(result.error());
            }
            else {
                final Convert<Object, Json> f = writer(clazz);
                writers.put(clazz.getTypeName(), f);
                final Either<String, Json> result = f.convert(elm);
                if (result.isRight()) js.add(result.value());
                else return Either.left(result.error());
            }
        }
        return Either.right(new JArr(io.lacuna.bifurcan.List.from(js)));
    }

    // The order of the fields in the class is important. The constructor's parameters have to correspond to that order.
    // If the constructor doesn't accept the fields in the same order, then I can't instantiate it, because it can't find one that matches.
    // I could theoretically get all constructors, get their types and then try to hash-mapishly rearrange the values so that they match,
    // but if the constructor accepts multiple values of the same type, that only differ semantically, then re-arranging can't guarantee that
    // they'll also be put in the right place...
    public static <A> Convert<Json, A> reader(final Class<A> clazz) {
        return json -> {
            try {
                final JsonT jsonT = json.transform();
                final Field[] fields = clazz.getDeclaredFields();
                final Class<?>[] types = new Class[fields.length];
                final Object[] values = new Object[fields.length];

                for (int i = 0; i < fields.length; i++) {
                    final Class<?> attClazz = fields[i].getType();
                    final Either<String, ?> result = coerceFromJson(attClazz, jsonT.get(fields[i].getName()));
                    types[i] = attClazz;
                    if (result.isRight()) values[i] = result.value();
                    else return Either.left(result.error());
                }
                return Either.right(clazz.getDeclaredConstructor(types).newInstance(values));
            } catch (Exception e) {
                e.printStackTrace();
                return Either.left(e.getMessage());
            }
        };
    }

    public static <A> Convert<A, Json> writer(final Class<A> clazz) {
        return instance -> {
            try {
                if (instance instanceof Long) {
                    return LONG_TO_JSON.convert((Long) instance);
                }
                else if (instance instanceof Float) {
                    return FLOAT_TO_JSON.convert((Float) instance);
                }
                else if (instance instanceof Integer) {
                    return INT_TO_JSON.convert((Integer) instance);
                }
                else if (instance instanceof Boolean) {
                    return BOOLEAN_TO_JSON.convert((Boolean) instance);
                }
                else if (instance instanceof String) {
                    return STRING_TO_JSON.convert((String) instance);
                }
                else if (instance instanceof List) {
                    return listWriter((List<?>) instance);
                }
                else {
                    return objectWriter(instance);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return Either.left(e.getMessage());
            }
        };
    }
}