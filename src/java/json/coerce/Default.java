package json.coerce;

import json.data.*;
import util.Either;

import java.util.List;
import java.util.Optional;

public class Default {

    public final <A> Convert<Json, Optional<A>> optional(final Convert<Json, A> f) {
        return json -> f.convert(json).fold(a -> Either.right(Optional.of(a)), x -> Either.right(Optional.empty()));
    }

    public final static Convert<Long, Json> LONG_TO_JSON = value -> Either.right(new JNum(value));
    public final static Convert<String, Json> STRING_TO_JSON = value -> Either.right(new JString(value));
    public final static Convert<Void, Json> NULL_TO_JSON = x -> Either.right(JNull.instance);

    public final static Convert<Json, Long> JSON_TO_LONG = json -> {
        if (json instanceof JNum) {
            final Number num = ((JNum) json).value;
            if (num instanceof Long) return Either.right((Long) num);
            else if (num instanceof Integer) return Either.right(Integer.toUnsignedLong((Integer) num));
            else return Either.left(String.format("`%s` is not a Long", num));
        } else return Either.left(String.format("`%s` is not a valid JSON number", json));
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

    public final static Convert<Boolean, Json> BOOLEAN_TO_JSON = value -> Either.right(new JBool(value));

    public final static Convert<Json, List<Json>> JSON_TO_LIST = json -> {
        if (json instanceof JArr) {
            return Either.right(((JArr) json).value.toList());
        } else {
            return Either.left(String.format("`%s` is not a valid JSON array", json));
        }
    };

    public final static Convert<Json, Void> JSON_TO_NULL = json -> {
        if (json instanceof JNull) {
            return Either.right(null);
        } else return Either.left(String.format("`%s` is not a valid JSON null", json));
    };
}
