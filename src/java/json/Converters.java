package json;
import json.coerce.Convert;
import json.data.*;
import util.misc.Collections;
import util.error.Either;

import java.util.*;

public class Converters {

    public final <A> Convert<Json, Optional<A>> optional(final Convert<Json, A> f) {
        return json -> f.convert(json).fold(a -> Either.right(Optional.of(a)), x -> Either.right(Optional.empty()));
    }

    public final static Convert<Long, Json> LONG_TO_JSON = value -> Either.right(new JNum(value));
    public final static Convert<Integer, Json> INT_TO_JSON = value -> Either.right(new JNum(value));
    public final static Convert<Float, Json> FLOAT_TO_JSON = value -> Either.right(new JNum(value));
    public final static Convert<Double, Json> DOUBLE_TO_JSON = value -> Either.right(new JNum(value));
    public final static Convert<String, Json> STRING_TO_JSON = value -> Either.right(new JString(value));
    public final static Convert<Void, Json> NULL_TO_JSON = x -> Either.right(JNull.instance);
    public final static Convert<Boolean, Json> BOOLEAN_TO_JSON = value -> Either.right(new JBool(value));

    public final static Convert<Json, Integer> JSON_TO_INT = json -> {
        if (json instanceof JNum) {
            final Number num = ((JNum) json).value;
            if (num instanceof Integer) return Either.right((Integer) num);
            else return Either.left(String.format("`%s` is not an Integer", num));
        }
        else return Either.left(String.format("`%s` is not a valid JSON number", json));
    };

    public final static Convert<Json, Long> JSON_TO_LONG = json -> {
        if (json instanceof JNum) {
            final Number num = ((JNum) json).value;
            if (num instanceof Long) return Either.right((Long) num);
            else if (num instanceof Integer) return Either.right(Integer.toUnsignedLong((Integer) num));
            else return Either.left(String.format("`%s` is not a Long", num));
        } else return Either.left(String.format("`%s` is not a valid JSON number", json));
    };

    public final static Convert<Json, Double> JSON_TO_DOUBLE = json -> {
        if (json instanceof JNum) {
            final Number num = ((JNum) json).value;
            if (num instanceof Double) return Either.right((Double) num);
            else return Either.left(String.format("`%s` is not a Double", num));
        }
        else return Either.left(String.format("`%s` is not a valid JSON number", json));
    };

    public final static Convert<Json, Float> JSON_TO_FLOAT = json -> {
        if (json instanceof JNum) {
            final Number num = ((JNum) json).value;
            if (num instanceof Float) return Either.right((Float) num);
            else return Either.left(String.format("`%s` is not a Float", num));
        }
        else return Either.left(String.format("`%s` is not a valid JSON number", json));
    };

    public final static Convert<Json, String> JSON_TO_STRING = json -> {
        if (json instanceof JString) {
            return Either.right(((JString) json).value);
        } else {
            return Either.right(String.format("`%s` is not a valid JSON string", json));
        }
    };

    public final static Convert<Json, Boolean> JSON_TO_BOOLEAN = json -> {
        if (json instanceof JBool) {
            return Either.right(((JBool) json).value);
        } else {
            return Either.left(String.format("`%s` is not a valid JSON boolean", json));
        }
    };

    public final static Convert<Json, List<Json>> JSON_TO_LIST = json -> {
        if (json instanceof JArr) {
            return Either.right(((JArr) json).value.toList());
        } else {
            return Either.left(String.format("`%s` is not a valid JSON array", json));
        }
    };

    public final <A> Convert<Json, List<A>> partialListOf(final Convert<Json, A> f) {
        return json -> {
            if (json instanceof  JArr) {
                final JArr arr = (JArr) json;
                List<A> list = new ArrayList<>();
                for (Json j : arr.value) {
                    f.convert(j).fold(list::add, x -> null);
                }
                return Either.right(list);
            }
            else return Either.left(String.format("`%s` is not a valid JSON array", json));
        };
    }

    public static <A> Convert<Json, List<A>> listOf(final Convert<Json, A> f) {
        return json -> {
            if (json instanceof JArr) {
                final JArr arr = (JArr) json;
                List<A> list = new ArrayList<>();
                for (Json j : arr.value) {
                    final Either<String, A> a = f.convert(j);
                    if (a.isRight()) list.add(a.value());
                    else return Either.left(String.format("Could not coerce `%s` into proper type", j));
                }
                return Either.right(list);
            }
            else return Either.left(String.format("`%s` is not a valid JSON array", json));
        };
    }

    public final static Convert<Json, Void> JSON_TO_NULL = json -> {
        if (json instanceof JNull) {
            return Either.right(null);
        } else return Either.left(String.format("`%s` is not a valid JSON null", json));
    };

    public static <A> Convert <List<A>, Json> listFrom(final Convert<A, Json> f) {
        return list -> Collections.traversel_(list, f::convert).map(JArr::new);
    }
}