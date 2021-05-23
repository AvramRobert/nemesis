package com.ravram.nemesis;

import com.ravram.nemesis.coerce.Convert;
import com.ravram.nemesis.model.*;
import io.lacuna.bifurcan.IEntry;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public final class Readers {
    public final static Read<Integer> READ_INT = json -> {
        if (json.type() == JType.JsonNumber) {
            final Number num = ((JNum) json).value;
            if (num instanceof Integer) return Attempt.success(num.intValue());
            else return Attempt.failure("`%s` is not an Integer", num);
        }
        else return Attempt.failure("`%s` is not a valid JSON number", json);
    };

    public final static Read<Long> READ_LONG = json -> {
        if (json.type() == JType.JsonNumber) {
            final Number num = ((JNum) json).value;
            if (num instanceof Long) return Attempt.success(num.longValue());
            else if (num instanceof Integer) return Attempt.success(num.longValue());
            else return Attempt.failure("`%s` is not a Long", num);
        } else return Attempt.failure("`%s` is not a valid JSON number", json);
    };

    public final static Read<Double> READ_DOUBLE = json -> {
        if (json.type() == JType.JsonNumber) {
            final Number num = ((JNum) json).value;
            if (num instanceof Double) return Attempt.success(num.doubleValue());
            else return Attempt.failure("`%s` is not a Double", num);
        }
        else return Attempt.failure("`%s` is not a valid JSON number", json);
    };

    public final static Read<Float> READ_FLOAT = json -> {
        if (json.type() == JType.JsonNumber) {
            final Number num = ((JNum) json).value;
            if (num instanceof Float) return Attempt.success(num.floatValue());
            else return Attempt.failure("`%s` is not a Float", num);
        }
        else return Attempt.failure("`%s` is not a valid JSON number", json);
    };

    public final static Read<String> READ_STRING = json -> {
        if (json.type() == JType.JsonString) {
            return Attempt.success(((JString) json).value);
        } else {
            return Attempt.failure("`%s` is not a valid JSON string", json);
        }
    };

    public final static Read<Boolean> READ_BOOLEAN = json -> {
        if (json.type() == JType.JsonBool) {
            return Attempt.success(((JBool) json).value);
        } else {
            return Attempt.failure("`%s` is not a valid JSON boolean", json);
        }
    };

    public final static Read<Void> READ_NULL = json -> {
        if (json.type() == JType.JsonNull) {
            return Attempt.success(null);
        } else return Attempt.failure("`%s` is not a valid JSON null", json);
    };

    public final static Read<ZonedDateTime> READ_ZONED_DATE_TIME = json -> {
        if (json.type() == JType.JsonString) {
            try {
                final ZonedDateTime zdt = ZonedDateTime.parse(((JString) json).value, DateTimeFormatter.ISO_ZONED_DATE_TIME);
                return Attempt.success(zdt);
            } catch (Exception e) {
                return Attempt.failure("`%s` is not a valid ZonedDateTime.", json);
            }
        } else return Attempt.failure("`%s` is not a valid JSON string", json);
    };

    public final static Read<UUID> READ_UUID = json -> {
        if (json.type() == JType.JsonString) {
            try {
                final UUID uuid = UUID.fromString(((JString) json).value);
                return Attempt.success(uuid);
            } catch (Exception e) {
                return Attempt.failure("`%s` is not a valid UUID.", json);
            }
        } else return Attempt.failure("`%s` is not a valid JSON string", json);
    };


    public final static Read<List<Json>> READ_LIST = json -> {
        if (json.type() == JType.JsonArray) {
            return Attempt.success(((JArr) json).value.toList());
        } else {
            return Attempt.failure("`%s` is not a valid JSON array", json);
        }
    };

    public final static Read<Set<Json>> READ_SET = json -> {
        if (json.type() == JType.JsonArray) {
            List<Json> list = ((JArr) json).value.toList();
            return Attempt.success(new HashSet<>(list));
        } else {
            return Attempt.failure("`%s` is not a valid JSON array", json);
        }
    };

    public static <A> Read<List<A>> readListOf(final Read<A> f) {
        return json -> {
            if (json.type() == JType.JsonArray) {
                final JArr arr = (JArr) json;
                List<A> list = new ArrayList<>();
                for (Json j : arr.value) {
                    final Attempt<A> a = f.apply(j);
                    if (a.isSuccess()) list.add(a.value());
                    else return Attempt.failure("Could not coerce `%s` into expected type()", json);
                }
                return Attempt.success(list);
            }
            else return Attempt.failure("`%s` is not a valid JSON array", json);
        };
    }

    public static <A> Read<Set<A>> readSetOf(final Read<A> f) {
        return json -> {
            if (json.type() == JType.JsonArray) {
                final JArr arr = (JArr) json;
                Set<A> list = new HashSet<>();
                for (Json j : arr.value) {
                    final Attempt<A> a = f.apply(j);
                    if (a.isSuccess()) list.add(a.value());
                    else return Attempt.failure("Could not coerce `%s` into expected type", j);
                }
                return Attempt.success(list);
            }
            else return Attempt.failure("`%s` is not a valid JSON array", json);
        };
    }

    public static <K, A> Read<Map<K, A>> readMapOf(final Convert<String, K> kf, final Read<A> vf) {
        return json -> {
            if (json.type() == JType.JsonString) {
                final JObj obj = (JObj) json;
                Map<K, A> map = new HashMap<>();
                for (IEntry<String, Json> entry : obj.value) {
                    final Attempt<K> key = kf.apply(entry.key());
                    if (key.isSuccess()) {
                        final Attempt<A> value = vf.apply(entry.value());
                        if (value.isSuccess()) map.put(key.value(), value.value());
                        else return Attempt.failure("Could not coerce `%s` to expected value type", entry.value());
                    } else return Attempt.failure("Could not coerce `%s` to expected key type", entry.key());
                }
                return Attempt.success(map);
            }
            else return Attempt.failure("`%s` is not a valid JSON object", json);
        };
    }

    public static <A> Read<Map<String, A>> readMapOf(final Read<A> vf) {
        return readMapOf(Attempt::success, vf);
    }
}
