package com.ravram.nemesis;

import com.ravram.nemesis.coerce.Convert;
import com.ravram.nemesis.coerce.DynamicConversions;
import com.ravram.nemesis.model.*;
import com.ravram.nemesis.util.error.Either;
import com.ravram.nemesis.util.misc.Collections;
import io.lacuna.bifurcan.IEntry;

import java.util.*;

public class Converters {

    // JSON TO OBJECT
    public final static Convert<Json, Integer> JSON_TO_INT = json -> {
        if (json.type == JType.JsonNumber) {
            final Number num = ((JNum) json).value;
            if (num instanceof Integer) return Either.right((Integer) num);
            else return Either.left(String.format("`%s` is not an Integer", num));
        }
        else return Either.left(String.format("`%s` is not a valid JSON number", json));
    };

    public final static Convert<Json, Long> JSON_TO_LONG = json -> {
        if (json.type == JType.JsonNumber) {
            final Number num = ((JNum) json).value;
            if (num instanceof Long) return Either.right((Long) num);
            else if (num instanceof Integer) return Either.right(Integer.toUnsignedLong((Integer) num));
            else return Either.left(String.format("`%s` is not a Long", num));
        } else return Either.left(String.format("`%s` is not a valid JSON number", json));
    };

    public final static Convert<Json, Double> JSON_TO_DOUBLE = json -> {
        if (json.type == JType.JsonNumber) {
            final Number num = ((JNum) json).value;
            if (num instanceof Double) return Either.right((Double) num);
            else return Either.left(String.format("`%s` is not a Double", num));
        }
        else return Either.left(String.format("`%s` is not a valid JSON number", json));
    };

    public final static Convert<Json, Float> JSON_TO_FLOAT = json -> {
        if (json.type == JType.JsonNumber) {
            final Number num = ((JNum) json).value;
            if (num instanceof Float) return Either.right((Float) num);
            else return Either.left(String.format("`%s` is not a Float", num));
        }
        else return Either.left(String.format("`%s` is not a valid JSON number", json));
    };

    public final static Convert<Json, String> JSON_TO_STRING = json -> {
        if (json.type == JType.JsonString) {
            return Either.right(((JString) json).value);
        } else {
            return Either.right(String.format("`%s` is not a valid JSON string", json));
        }
    };

    public final static Convert<Json, Boolean> JSON_TO_BOOLEAN = json -> {
        if (json.type == JType.JsonBool) {
            return Either.right(((JBool) json).value);
        } else {
            return Either.left(String.format("`%s` is not a valid JSON boolean", json));
        }
    };

    public final static Convert<Json, Void> JSON_TO_NULL = json -> {
        if (json.type == JType.JsonNull) {
            return Either.right(null);
        } else return Either.left(String.format("`%s` is not a valid JSON null", json));
    };

    public final static Convert<Json, List<Json>> JSON_TO_LIST = json -> {
        if (json.type == JType.JsonArray) {
            return Either.right(((JArr) json).value.toList());
        } else {
            return Either.left(String.format("`%s` is not a valid JSON array", json));
        }
    };

    public final static Convert<Json, Set<Json>> JSON_TO_SET = json -> {
        if (json.type == JType.JsonArray) {
            List<Json> list = ((JArr) json).value.toList();
            return Either.right(new HashSet<>(list));
        } else {
            return Either.left(String.format("`%s` is not a valid JSON array", json));
        }
    };

    public static <A> Convert<Json, List<A>> listOf(final Convert<Json, A> f) {
        return json -> {
            if (json.type == JType.JsonArray) {
                final JArr arr = (JArr) json;
                List<A> list = new ArrayList<>();
                for (Json j : arr.value) {
                    final Either<String, A> a = f.convert(j);
                    if (a.isRight()) list.add(a.value());
                    else return Either.left(String.format("Could not coerce `%s` into expected type", j));
                }
                return Either.right(list);
            }
            else return Either.left(String.format("`%s` is not a valid JSON array", json));
        };
    }

    public static <A> Convert<Json, Set<A>> setOf(final Convert<Json, A> f) {
        return json -> {
            if (json.type == JType.JsonArray) {
                final JArr arr = (JArr) json;
                Set<A> list = new HashSet<>();
                for (Json j : arr.value) {
                    final Either<String, A> a = f.convert(j);
                    if (a.isRight()) list.add(a.value());
                    else return Either.left(String.format("Could not coerce `%s` into expected type", j));
                }
                return Either.right(list);
            }
            else return Either.left(String.format("`%s` is not a valid JSON array", json));
        };
    }

    public static <A> Convert<Json, Map<String, A>> mapOf(final Convert<Json, A> f) {
        return json -> {
            if (json.type == JType.JsonString) {
                final JObj obj = (JObj) json;
                Map<String, A> map = new HashMap<>();
                for (IEntry<String, Json> entry : obj.value) {
                    final Either<String, A> a = f.convert(entry.value());
                    if (a.isRight()) map.put(entry.key(), a.value());
                    else return Either.left(String.format("Could not coerce `%s` expected type", entry.value()));
                }
                return Either.right(map);
            }
            else return Either.left(String.format("`%s` is not a valid JSON object", json));
        };
    }

    public final <A> Convert<Json, Optional<A>> optionalOf(final Convert<Json, A> f) {
        return json -> f.convert(json).fold(a -> Either.right(Optional.of(a)), x -> Either.right(Optional.empty()));
    }

    // OBJECT TO JSON

    public final static Convert<Long, Json> LONG_TO_JSON = value -> Either.right(new JNum(value));
    public final static Convert<Integer, Json> INT_TO_JSON = value -> Either.right(new JNum(value));
    public final static Convert<Float, Json> FLOAT_TO_JSON = value -> Either.right(new JNum(value));
    public final static Convert<Double, Json> DOUBLE_TO_JSON = value -> Either.right(new JNum(value));
    public final static Convert<String, Json> STRING_TO_JSON = value -> Either.right(new JString(value));
    public final static Convert<Void, Json> NULL_TO_JSON = x -> Either.right(JNull.instance);
    public final static Convert<Boolean, Json> BOOLEAN_TO_JSON = value -> Either.right(new JBool(value));
    public final static Convert<List<Json>, Json> LIST_TO_JSON = value -> Either.right(new JArr(io.lacuna.bifurcan.List.from(value)));
    public final static Convert<Set<Json>, Json> SET_TO_JSON = value -> Either.right(new JArr(io.lacuna.bifurcan.List.from(value)));
    
    public static <A> Convert<Map<String, A>, Json> mapFrom(final Convert<A, Json> f) {
        return map -> Collections.traverseMap(
                map,
                DynamicConversions::coerceKey, f::convert)
                .map(JObj::new);
    }

    public static <A> Convert <List<A>, Json> listFrom(final Convert<A, Json> f) {
        return list -> Collections.bTraverseList(list, f::convert).map(JArr::new);
    }

    public static <A> Convert <Set<A>, Json> setFrom(final Convert<A, Json> f) {
        return set -> Collections.bTraverseSet(set, f::convert).map(JArr::new);
    }

    public static <A> Convert<Optional<A>, Json> optionalFrom(final Convert<A, Json> f) {
        return opt -> {
            if (opt.isPresent()) return f.convert(opt.get());
            else return Either.right(JNull.instance);
        };
    }
}