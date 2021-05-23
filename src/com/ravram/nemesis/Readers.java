package com.ravram.nemesis;

import com.ravram.nemesis.coerce.Convert;
import com.ravram.nemesis.model.*;
import com.ravram.nemesis.util.error.Either;
import io.lacuna.bifurcan.IEntry;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public final class Readers {
    public final static Read<Integer> READ_INT = json -> {
        if (json.type() == JType.JsonNumber) {
            final Number num = ((JNum) json).value;
            if (num instanceof Integer) return Either.right(num.intValue());
            else return Either.left("`%s` is not an Integer", num);
        }
        else return Either.left("`%s` is not a valid JSON number", json);
    };

    public final static Read<Long> READ_LONG = json -> {
        if (json.type() == JType.JsonNumber) {
            final Number num = ((JNum) json).value;
            if (num instanceof Long) return Either.right(num.longValue());
            else if (num instanceof Integer) return Either.right(num.longValue());
            else return Either.left("`%s` is not a Long", num);
        } else return Either.left("`%s` is not a valid JSON number", json);
    };

    public final static Read<Double> READ_DOUBLE = json -> {
        if (json.type() == JType.JsonNumber) {
            final Number num = ((JNum) json).value;
            if (num instanceof Double) return Either.right(num.doubleValue());
            else return Either.left("`%s` is not a Double", num);
        }
        else return Either.left("`%s` is not a valid JSON number", json);
    };

    public final static Read<Float> READ_FLOAT = json -> {
        if (json.type() == JType.JsonNumber) {
            final Number num = ((JNum) json).value;
            if (num instanceof Float) return Either.right(num.floatValue());
            else return Either.left("`%s` is not a Float", num);
        }
        else return Either.left("`%s` is not a valid JSON number", json);
    };

    public final static Read<String> READ_STRING = json -> {
        if (json.type() == JType.JsonString) {
            return Either.right(((JString) json).value);
        } else {
            return Either.left("`%s` is not a valid JSON string", json);
        }
    };

    public final static Read<Boolean> READ_BOOLEAN = json -> {
        if (json.type() == JType.JsonBool) {
            return Either.right(((JBool) json).value);
        } else {
            return Either.left("`%s` is not a valid JSON boolean", json);
        }
    };

    public final static Read<Void> READ_NULL = json -> {
        if (json.type() == JType.JsonNull) {
            return Either.right(null);
        } else return Either.left("`%s` is not a valid JSON null", json);
    };

    public final static Read<ZonedDateTime> READ_ZONED_DATE_TIME = json -> {
        if (json.type() == JType.JsonString) {
            try {
                final ZonedDateTime zdt = ZonedDateTime.parse(((JString) json).value, DateTimeFormatter.ISO_ZONED_DATE_TIME);
                return Either.right(zdt);
            } catch (Exception e) {
                return Either.left("`%s` is not a valid ZonedDateTime.", json);
            }
        } else return Either.left("`%s` is not a valid JSON string", json);
    };

    public final static Read<UUID> READ_UUID = json -> {
        if (json.type() == JType.JsonString) {
            try {
                final UUID uuid = UUID.fromString(((JString) json).value);
                return Either.right(uuid);
            } catch (Exception e) {
                return Either.left("`%s` is not a valid UUID.", json);
            }
        } else return Either.left("`%s` is not a valid JSON string", json);
    };


    public final static Read<List<Json>> READ_LIST = json -> {
        if (json.type() == JType.JsonArray) {
            return Either.right(((JArr) json).value.toList());
        } else {
            return Either.left("`%s` is not a valid JSON array", json);
        }
    };

    public final static Read<Set<Json>> READ_SET = json -> {
        if (json.type() == JType.JsonArray) {
            List<Json> list = ((JArr) json).value.toList();
            return Either.right(new HashSet<>(list));
        } else {
            return Either.left("`%s` is not a valid JSON array", json);
        }
    };

    public static <A> Read<List<A>> listOf(final Read<A> f) {
        return json -> {
            if (json.type() == JType.JsonArray) {
                final JArr arr = (JArr) json;
                List<A> list = new ArrayList<>();
                for (Json j : arr.value) {
                    final Either<String, A> a = f.apply(j);
                    if (a.isRight()) list.add(a.value());
                    else return Either.left("Could not coerce `%s` into expected type()", json);
                }
                return Either.right(list);
            }
            else return Either.left("`%s` is not a valid JSON array", json);
        };
    }

    public static <A> Read<Set<A>> setOf(final Read<A> f) {
        return json -> {
            if (json.type() == JType.JsonArray) {
                final JArr arr = (JArr) json;
                Set<A> list = new HashSet<>();
                for (Json j : arr.value) {
                    final Either<String, A> a = f.apply(j);
                    if (a.isRight()) list.add(a.value());
                    else return Either.left("Could not coerce `%s` into expected type", j);
                }
                return Either.right(list);
            }
            else return Either.left("`%s` is not a valid JSON array", json);
        };
    }

    public static <K, A> Read<Map<K, A>> mapOf(final Convert<String, String, K> kf, final Read<A> vf) {
        return json -> {
            if (json.type() == JType.JsonString) {
                final JObj obj = (JObj) json;
                Map<K, A> map = new HashMap<>();
                for (IEntry<String, Json> entry : obj.value) {
                    final Either<String, K> key = kf.apply(entry.key());
                    if (key.isRight()) {
                        final Either<String, A> value = vf.apply(entry.value());
                        if (value.isRight()) map.put(key.value(), value.value());
                        else return Either.left("Could not coerce `%s` to expected value type", entry.value());
                    } else return Either.left("Could not coerce `%s` to expected key type", entry.key());
                }
                return Either.right(map);
            }
            else return Either.left("`%s` is not a valid JSON object", json);
        };
    }

    public static <A> Read<Map<String, A>> mapOf(final Read<A> vf) {
        return mapOf(Either::right, vf);
    }
}