package json.coerce;

import json.data.Json;
import json.data.JsonT;
import util.Either;

import static json.coerce.DefaultConverters.*;
import java.lang.reflect.Field;

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
        else if (type.equals("string")) {
            return jsont.as(JSON_TO_STRING);
        }
        else if (type.equals("boolean")) {
            return jsont.as(JSON_TO_BOOLEAN);
        }
        return null; // this is the point when this either becomes an object or a collection -> but this is getting complicated
    }

    public static <A> Convert<Json, A> reader (final Class<A> clazz) {
        return json -> {
            try {
                final JsonT jsonT = json.transform();
                final Field[] fields = clazz.getDeclaredFields();
                final Class<?>[] types = new Class[fields.length];
                final Object[] values = new Object[fields.length];

                for (int i = 0; i < fields.length; i++) {
                    final Class<?> attClazz = fields[i].getDeclaringClass();
                    final Either<String, ?> type = coerce(attClazz, jsonT.get(fields[i].getName()));
                    types[i] = fields[i].getDeclaringClass();
                }

                return Either.right(clazz.getConstructor(types).newInstance(values));
            } catch (Exception e) {
                return Either.left(e.getMessage());
            }
        };
    }

    public static <A> Convert<A, Json> writer (final Class<A> clazz) {
        return null;
    }
}
