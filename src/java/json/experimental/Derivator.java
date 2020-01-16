package json.experimental;

import json.coerce.Convert;
import json.data.Json;
import json.data.JsonT;
import util.Colls;
import util.Debug;
import util.Either;

import static json.coerce.DefaultConverters.*;
import java.lang.reflect.Field;
import java.util.Arrays;

public class Derivator {

    private static Either<String, ?> coerce(final Class<?> clazz, final JsonT jsont) {
        final String type = clazz.getTypeName().toLowerCase();
        if (type.equals("long")) {
            return jsont.as(JSON_TO_LONG);
        }
        else if (type.equals("int")) {
            return jsont.as(JSON_TO_INT);
        }
        else if (type.equals("float")) {
            return jsont.as(JSON_TO_FLOAT);
        }
        else if (type.equals("double")) {
            return jsont.as(JSON_TO_DOUBLE);
        }
        else if (type.equals("boolean")) {
            return jsont.as(JSON_TO_BOOLEAN);
        }
        else if (type.equals("java.lang.string")) {
            return jsont.as(JSON_TO_STRING);
        }
        else if (type.contains("java.util.list")) { // this is idiotic;
            return jsont.as(JSON_TO_LIST).flatMap(list -> Colls.traversel(list, a -> reader(Object.class).convert(a)));
        }
        else return jsont.affix().flatMap(j -> reader(clazz).convert(j));
    }

    // The order of the fields in the class is important. The constructor's parameters have to correspond to that order.
    // If the constructor doesn't accept the fields in the same order, then I can't instantiate it, because it can't find one that matches.
    // I could theoretically get all constructors, get their types and then try to hash-mapishly rearrange the values so that they match,
    // but if the constructor accepts multiple values of the same type, that only differ semantically, then re-arranging can't guarantee that
    // they'll also be put in the right place...

    public static <A> Convert<Json, A> reader (final Class<A> clazz) {
        return json -> {
            try {
                final JsonT jsonT      = json.transform();
                final Field[] fields   = clazz.getDeclaredFields();
                final Class<?>[] types = new Class[fields.length];
                final Object[] values  = new Object[fields.length];

                for (int i = 0; i < fields.length; i++) {
                    final Class<?> attClazz = fields[i].getType();
                    final Either<String, ?> result = coerce(attClazz, jsonT.get(fields[i].getName()));
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

    public static <A> Convert<A, Json> writer (final Class<A> clazz) {
        return null;
    }
}