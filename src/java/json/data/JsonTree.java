package json.data;

import io.lacuna.bifurcan.List;
import io.lacuna.bifurcan.Map;
import json.coerce.Convert;
import util.Either;

import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

import static json.data.JType.*;
import static json.data.JType.JsonArray;
import static util.Functions.traversel;
import static util.Functions.traversem;

public class JsonTree {
    private Json json;

    private String error = "";
    private final boolean SUCCESS = true;
    private final boolean FAIL    = false;
    private boolean success = SUCCESS;

    private final Map<String, Json> map = new Map<>();
    private final JObj node = new JObj(map);

    public JsonTree(final Json json) {
        this.json = json;

    }

    private JEmpty jEmpty () {
        return (JEmpty) json;
    }

    private JObj jobj () {
        return (JObj) json;
    }

    private JArr jarr() {
        return (JArr) json;
    }


    private JsonTree succeed(final Json json) {
        this.json = json;
        return this;
    }

    private JsonTree fail(final String error) {
        this.error = error;
        success = FAIL;
        return this;
    }

    private final Convert<Object, String> defaultStringConvert = this::coerceString;

    private final Convert<Object, Json> defaultJsonConvert = this::coerceJson;

    private final String escape (final String s) {
        return "\"" + s + "\"";
    }

    private <A> JsonTree consume (final Either<String, A> comp, final Function<A, JsonTree> f) {
        return comp.fold(f, this::fail);
    }

    private <A, B> JsonTree biconsume(final Either<String, A> ea, final Either<String, B> eb, final BiFunction<A, B, JsonTree> f) {
        return ea.flatMap(a -> eb.map(b -> f.apply(a, b))).fold(x -> x, this::fail);
    }

    private <A> Either<String, Long> coerceLong(final A value) {
        if (value instanceof Long) return Either.right((Long) value);
        else return Either.left(String.format("Value `%s` is not of type Integer", value.toString()));
    }

    private <A> Either<String, String> coerceString(final A value) {
        if (value instanceof String) return Either.right((String) value);
        else return Either.left(String.format("Value `%s` is not of type String", value.toString()));
    }

    private <A> Either<String, Json> coerceJson(final A value) {
        if (value instanceof Number) {
            return Either.right(new JNum((Number) value));
        } else if (value instanceof String) {
            return Either.right(new JString((String) value));
        } else if (value instanceof Boolean) {
            final boolean bool = (Boolean) value;
            return Either.right(bool ? JBool.jtrue : JBool.jfalse);
        } else if (value == null) {
            return Either.right(JNull.instance);
        } else if (value instanceof java.util.HashMap) {
            final HashMap<Object, Object> map = (HashMap<Object, Object>) value;
            return traversem(map, this::coerceString, this::coerceJson).map(JObj::new); // this is mutually recursive -- may not be that good
        } else if (value instanceof java.util.List) {
            final java.util.List<Object> list = (java.util.List<Object>) value;
            return traversel(list, this::coerceJson).map(JArr::new); // this is mutually recursive -- may not be that good
        } else
            return Either.left(String.format("Class type of `%s` for value `%s` is not supported", value.getClass().toString(), value));
    }

    private Either<String, Json> assocInObj(final Json json, final Json toAssoc, final int depth, final Object... keys) {
        final Map<String, Json> map = jobj().value;
        final Either<String, String> skey = coerceString(keys[depth]);
        if (skey.isRight()) {
            final String key   = skey.value();
            final Json value   = map.get(key).orElse(node);
            return assocInRec(value, toAssoc, depth + 1, keys);
        } else {
            return Either.left(String.format("Key for json was expected to be a string: %s", skey.error()));
        }
    }

    private Either<String, Json> assocInArr(final Json json, final Json toAssoc, final int depth, final Object... keys) {
        final Either<String, Long> sidx = coerceLong(keys[depth]);
        final List<Json> list = jarr().value;
        if (sidx.isRight()) {
            final long idx = sidx.value();
            final Json value = list.nth(idx, node);
            return assocInRec(value, toAssoc, depth + 1, keys);
        } else {
            return Either.left(String.format("Index for array was expected to be an integer: %s", sidx.error()));
        }
    }

    private Either<String, Json> assocInRec(final Json value, final Json toAssoc, final int depth, final Object... keys) {
        if (depth >= keys.length)          return Either.right(toAssoc);
        else if (value.type == JsonObject) return assocInObj(value, toAssoc, depth, keys);
        else if (value.type == JsonArray)  return assocInArr(value, toAssoc, depth, keys);
        else return Either.left("Cannot associate into `%s`. It is not a structure.");
    }

    public final JsonTree get (final String key) {
        if (json.type == JsonObject) {
            return jobj().value.get(key).map(this::succeed).orElse(fail("Key not found"));
        }
        else return fail(String.format("Cannot lookup key `%s` in `%s`.", key, json));
    }

    public final JsonTree get (final long index) {
        if (json.type == JsonArray) {
            final Json r = jarr().value.nth(index, null);
            return (r != null) ? succeed(r) : fail("Index not found");
        }
        else return fail(String.format("Cannot lookup index `%d` in `%s`.", index, json));
    }

    public final <K> JsonTree get (final K key) {
        if (json.type == JsonObject)
            return consume(coerceString(key), k -> {
                return jobj().value.get(k).map(this::succeed).orElse(fail(String.format("Key `%s` does not exist", k)));
            });
        else if (json.type == JsonArray) {
            return consume(coerceLong(key), i -> {
                final Json r = jarr().value.nth(i, null);
                return r != null ? succeed(r) : fail(String.format("Index `%d` does not exist", i));
            });
        }
        else return fail(String.format("Cannot get in structure `%s`", this.toString()));
    }

    public final JsonTree getIn (final Object... keys) {
        int depth = 0;
        JsonTree tree = this;
        if (depth == keys.length) return this;
        else {
            while(depth < keys.length && tree.success) {
                tree = get(keys[depth]);
                depth ++;
            }
            return tree;
        }
    }

    public final JsonTree assocIn(final Json value, final Object... keys) {
        return assocInRec(json, value, 0, keys).fold(this::succeed, this::fail);
    }

    public final JsonTree dissoc(final String key) {
        if (json.type == JsonObject) {
            return succeed(new JObj(jobj().value.remove(key)));
        }
        else if (json.type == JsonEmpty) {
            return this;
        }
        else {
            return fail(String.format("Cannot dissociate key `%s` from `%s`.", key, json));
        }
    }

    public final JsonTree dissoc(final int index) {
        if (json.type == JsonArray) {
            final JArr arr         = jarr();
            final List<Json> left  = arr.value.slice(0, index - 1);
            final List<Json> right = arr.value.slice(index + 1, arr.value.size());
            return succeed(new JArr((List<Json>) left.concat(right)));
        } else {
            return fail(String.format("Cannot dissociate index `%d` from `%s`.", index, json));
        }
    }

    public final <K, V> JsonTree assoc (final K key, final V value, final Convert<V, Json> f) {
        if (json.type == JsonObject) {
            return biconsume(coerceString(key), f.convert(value), this::assoc);
        }
        else if (json.type == JsonArray) {
            return biconsume(coerceString(key), f.convert(value), this::assoc);
        }
        else {
            return fail(String.format("Cannot associate key `%s` into `%s`.", key, json));
        }
    }

    public final <K> JsonTree assoc(final K key, final Json value) {
        if (json.type == JsonObject) {
            return consume(coerceString(key), k -> assoc(k, value));
        }
        else if (json.type == JsonArray) {
            return consume(coerceLong(key), i -> assoc(i, value));
        }
        else {
            return fail(String.format("Cannot associate key `%s` into `%s`.", key, json));
        }
    }

    public final JsonTree assoc(final String key, final Json value) {
        if (json.type == JsonObject) {
            return succeed(new JObj(jobj().value.put(escape(key), value)));
        } else if (json.type == JsonEmpty) {
            return succeed(new JObj(map.put(escape(key), value)));
        } else {
            return fail(String.format("Cannot associate key `%s` into `%s`.", key, json));
        }
    }

    public final JsonTree assoc(final int index, final Json value) {
        if (json.type == JsonArray) {
            return succeed(new JArr((List<Json>) jarr().value.update(index, x -> value)));
        } else {
            return fail(String.format("Cannot associate index `%d` into `%s`.", index, json));
        }
    }

    public final <A> JsonTree assoc(final String key, final A value) {
        return assoc(key, value, defaultJsonConvert);
    }

    public final <A> JsonTree assoc(final int index, final A value) {
        return assoc(index, value, defaultJsonConvert);
    }

    public final <A> JsonTree assoc(final String key, final A value, final Convert<A, Json> w) {
        return consume(w.convert(value), v -> assoc(key, v));
    }

    public final <A> JsonTree assoc(final int index, final A value, final Convert<A, Json> w) {
        return consume(w.convert(value), v -> assoc(index, v));
    }

    public final JsonTree update(final String key, final Function<Json, Json> f) {
        return succeed(new JObj(jobj().value.update(key, f :: apply)));
    }

    public final JsonTree update(final int index, final Function<Json, Json> f) {
        return succeed(new JArr((List<Json>) jarr().value.update(index, f)));
    }

    public final <A, B> JsonTree update(final String key, final Function<A, B> f, final Convert<Json, A> to, final Convert<B, Json> from) {
        final Convert<Json, Json> g = to.compose(f).compose(from);
        return consume(get(key).convert(g), v -> assoc(key, v));
    }

    public final <A, B> JsonTree update(final int index, final Function<A, B> f, final Convert<Json, A> to, final Convert<B, Json> from) {
        final Convert<Json, Json> g = to.compose(f).compose(from);
        return consume(get(index).convert(g), v -> assoc(index, v));
    }

    public final Either<String, Json> affix() {
        if (success) return Either.right(json);
        else return Either.left(error);
    }

    public final <A> Either<String, A> convert (final Convert<Json, A> f) {
        return f.convert(json);
    }
}