package json.data;

import io.lacuna.bifurcan.List;
import io.lacuna.bifurcan.Map;
import json.coerce.Convert;
import util.Either;

import java.util.HashMap;

import static json.data.JType.*;
import static util.Functions.*;

public abstract class Json {
    JType type;

    private JEmpty jEmpty () {
        return (JEmpty) this;
    }

    private JObj jobj () {
        return (JObj) this;
    }

    private JArr jarr() {
        return (JArr) this;
    }

    public final Convert<Object, String> defaultStringConvert = this::coerceString;

    public final Convert<Object, Json> defaultJsonConvert = this::coerceJson;

    private <A> Either<String, Integer> coerceInteger (final A value) {
        if (value instanceof Integer) return Either.right((Integer) value);
        else return Either.left(String.format("Value `%s` is not of type Integer", value.toString()));
    }

    private <A> Either<String, String> coerceString (final A value) {
        if (value instanceof String) return Either.right((String) value);
        else return Either.left(String.format("Value `%s` is not of type String", value.toString()));
    }

    private <A> Either<String, Json> coerceJson (final A value) {
        if (value instanceof Number) {
            return Either.right(new JNum((Number) value));
        }
        else if (value instanceof String) {
            return Either.right(new JString((String) value));
        }
        else if (value instanceof Boolean) {
            final boolean bool = (Boolean) value;
            return Either.right(bool ? JBool.jtrue : JBool.jfalse);
        }
        else if (value == null) {
            return Either.right(JNull.instance);
        }
        else if (value instanceof java.util.HashMap) {
            final HashMap<Object, Object> map = (HashMap<Object, Object>) value;
            return traversem(map, this::coerceString, this::coerceJson).map(JObj::new); // this is mutually recursive -- may not be that good
        }
        else if (value instanceof java.util.List) {
            final java.util.List<Object> list = (java.util.List<Object>) value;
            return traversel(list, this::coerceJson).map(JArr::new); // this is mutually recursive -- may not be that good
        }
        else return Either.left(String.format("Class type of `%s` for value `%s` is not supported", value.getClass().toString(), value));
    }

    private final Either<String, Json> assocInObj(final Json toAssoc, final int depth, final Object... keys) {
        final Either<String, String> skey = coerceString(keys[depth]);
        final Map<String, Json> map = jobj().value;
        if (skey.isRight()) {
            final String k = skey.value();
            final Json  v  = map.get(k, null);
            if (v != null) {
                final Either<String, Json> nv = v.assocInR(toAssoc, depth + 1, keys);
                if (nv.isRight()) return Either.right(new JObj(map.put(k, nv.value())));
                else return nv;
            }
            else {
                final Either<String, Json> nj = new JObj(new Map<>()).assocInR(toAssoc, depth + 1, keys);
                return nj.map(json -> new JObj(map.put(k, json)));
            }
        } else return Either.left(String.format("Key for json was expected to be a string: %s", skey.error()));
    }

    private final Either<String, Json> assocInArr(final Json toAssoc, final int depth, final Object... keys) {
        final Either<String, Integer> sidx = coerceInteger(keys[depth]);
        final List<Json> list = jarr().value;
        if (sidx.isRight()) {
            final int  i = sidx.value();
            final Json v = list.nth(i, null);
            if (v != null) {
                final Either<String, Json> newJson = v.assocInR(toAssoc, depth + 1, keys);
                if (newJson.isRight()) return Either.right(new JArr((List<Json>) list.update(i, a -> newJson.value())));
                else return newJson;
            }
            else return Either.left(String.format("Index `%s` is not in the `%s` array", i, list));
        } else return Either.left(String.format("Index for array was expected to be an integer: %s", sidx.error()));
    }

    public final Either<String, Json> assocInR(final Json value, final int depth, final Object... keys) {
        if (depth >= keys.length) return Either.right(value);
        else {
            if (type == JsonObject) {
                return assocInObj(value, depth, keys);
            }
            else if (type == JsonArray) {
                return assocInArr(value, depth, keys);
            }
            else return Either.left("Cannot associate into `%s`. It is not a structure.");
        }
    }

    public final Json at (final String name) {
        switch (type) {
            case JsonObject:
                return jobj().value.get(name, JEmpty.empty);
            default:
                return this;
        }
    }

    public final Json at (final long index) {
        switch (type) {
            case JsonArray:
                return jarr().value.nth(index, JEmpty.empty);
            default:
                return this;
        }
    }

    public final Json assocIn(final Json value, final Object... keys) {
        return assocInR(value, 0, keys).fold(x -> x, e -> this);
    }

    public final Json dissoc (final String key) {
        if (type == JsonObject) {
            return new JObj(jobj().value.remove(key));
        }
        else return this;
    }

    public final Json dissoc (final int index) {
        if (type == JsonArray) {
            final JArr arr = jarr();
            final List<Json> left = arr.value.slice(0, index - 1);
            final List<Json> right = arr.value.slice(index + 1, arr.value.size());
            return new JArr((List<Json>) left.concat(right));
        }
        else return this;
    }

    public final Json assoc (final String key, final Json value) {
        if (type == JsonObject) {
            return new JObj(jobj().value.put(key, value));
        }
        else if (type == JsonEmpty) {
            return new JObj(new Map<String, Json>().put(key, value));
        }
        else return this;
    }

    public final Json assoc (final int index, final Json value) {
        if (type == JsonArray) {
            return new JArr((List<Json>) jarr().value.update(index, x -> value));
        }
        else return this;
    }

    public final <A> Json assoc (final String key, final A value) {
        return assoc(key, value, defaultJsonConvert);
    }

    public final <A> Json assoc (final int index, final A value) {
        return assoc(index, value, defaultJsonConvert);
    }

    public final <A> Json assoc (final String key, final A value, final Convert<A, Json> w) {
        return w.convert(value).fold(x -> assoc(key, x), e -> this);
    }

    public final <A> Json assoc (final int index, final A value, final Convert<A, Json> w) {
        return w.convert(value).fold(x -> assoc(index, x), e -> this);
    }
}