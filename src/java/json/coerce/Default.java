package json.coerce;

import json.data.JNum;
import json.data.Json;
import util.Either;

public class Default {

    public final static Convert<Json, Long> jsonToLong = json -> {
            if (json instanceof JNum) {
                final Number num = ((JNum) json).value;
                if (num instanceof Long) return Either.right((Long) num);
                else if (num instanceof Integer) return Either.right(Integer.toUnsignedLong((Integer) num));
                else return Either.left(String.format("`%s` is not a Long", num));
            }
            else return Either.left(String.format("`%s` is not a JSON number", json));
        };

    public static Convert<Long, Json> longToJson = value -> Either.right(new JNum(value));
}
