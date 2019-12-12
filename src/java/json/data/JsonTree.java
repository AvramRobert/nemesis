package json.data;

import io.lacuna.bifurcan.List;
import io.lacuna.bifurcan.Map;
import json.coerce.Convert;
import util.Either;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import static json.data.JType.*;
import static json.data.JType.JsonArray;
import static util.Functions.traversel;
import static util.Functions.traversem;

public class JsonTree {
    private final Json json;
    private final boolean success;
    private final String error;

    private final Map<String, Json> map = new Map<>();
    private final JObj node = new JObj(map);

    public JsonTree(final Json json) {
        this.json = json;
        this.success = true;
        this.error = "";
    }

    public JsonTree(final String error) {
        this.error = error;
        this.success = false;
        this.json = JEmpty.instance;
    }

    private JEmpty jEmpty () {
        return (JEmpty) json;
    }

    public JObj jobj () {
        return (JObj) json;
    }

    private JArr jarr() {
        return (JArr) json;
    }


    private JsonTree succeed(final Json json) {
        return new JsonTree(json);
    }

    private JsonTree fail(final String msg, Object... params) {
        return new JsonTree(String.format(msg, params));
    }

    private <A> Either<String, A> left (final String msg, Object... params) {
        return Either.left(String.format(msg, params));
    }

    private final Optional<Json> lookup (final String key) {
        return jobj().value.get(escape(key));
    }

    private final Map<String, Json> remove (final String key) {
        return jobj().value.remove(escape(key));
    }

    private final boolean contains (final String key) {
        return jobj().value.contains(escape(key));
    }

    private final boolean within (final long index) {
        return jarr().value.size() < index;
    }

    private final Map<String, Json> insert (final String key, final Json value) {
        return jobj().value.put(escape(key), value);
    }

    private final List<Json> replace (final long index, final Json value) {
        return (List<Json>) jarr().value.update(index, x -> value);
    }

    private final Convert<Object, String> defaultStringConvert = this::coerceString;

    private final Convert<Object, Json> defaultJsonConvert = this::coerceJson;

    private final String escape (final String s) {
        return String.format("\"%s\"", s);
    }

    private <A> JsonTree consume (final Either<String, A> comp, final Function<A, JsonTree> f) {
        return comp.fold(f, this::fail);
    }

    private <A, B> JsonTree biconsume(final Either<String, A> ea, final Either<String, B> eb, final BiFunction<A, B, JsonTree> f) {
        return ea.flatMap(a -> eb.map(b -> f.apply(a, b))).fold(x -> x, this::fail);
    }

    private <A> Either<String, Long> coerceLong(final A value) {
        if (value instanceof Long) return Either.right((Long) value);
        else if (value instanceof Integer) return Either.right(Integer.toUnsignedLong((Integer) value));
        else return Either.left(String.format("Value `%s` is not of type Long", value.toString()));
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

    private Either<String, Json> assocInObj(final JsonTree json, final Json toAssoc, final int depth, final Object... keys) {
        final Either<String, String> skey = coerceString(keys[depth]);
        if (skey.isRight()) {
            final String key   = skey.value();
            final Json value   = json.lookup(key).orElse(node);
            return assocInRec(value, toAssoc, depth + 1, keys).map(x -> new JObj(json.insert(key, x)));
        } else {
            return left("Key for json was expected to be a string: %s", skey.error());
        }
    }

    private Either<String, Json> assocInArr(final JsonTree json, final Json toAssoc, final int depth, final Object... keys) {
        final Either<String, Long> sidx = coerceLong(keys[depth]);
        if (sidx.isRight()) {
            final List<Json> list = json.jarr().value;
            final long idx        = sidx.value();
            if (list.size() > idx) {
                final Json value = list.nth(idx, node);
                return assocInRec(value, toAssoc, depth + 1, keys).map(x -> new JArr(json.replace(idx, x)));
            }
            else return left("Index of `%d` does not exist.", idx);
        } else {
            return left("Index for array was expected to be a long: %s", sidx.error());
        }
    }

    private Either<String, Json> assocInRec(final Json value, final Json toAssoc, final int depth, final Object... keys) {
        if      (depth >= keys.length)     return Either.right(toAssoc);
        else if (value.type == JsonObject) return assocInObj(value.transform(), toAssoc, depth, keys);
        else if (value.type == JsonArray)  return assocInArr(value.transform(), toAssoc, depth, keys);
        else return left("Cannot associate into `%s`. It is not a structure.", toAssoc);
    }

    public final JsonTree get (final String key) {
        if (json.type == JsonObject) {
            return lookup(key).map(this::succeed).orElse(fail(String.format("Key `%s` not found", key)));
        }
        else return fail("Cannot lookup key `%s` in `%s`.", key, json);
    }

    public final JsonTree get (final long index) {
        if (json.type == JsonArray) {
            final Json r = jarr().value.nth(index, null);
            return (r != null) ? succeed(r) : fail("Index if `%n` does not exist", index);
        }
        else return fail("Cannot lookup index `%d` in `%s`.", index, json);
    }

    public final JsonTree getIn (final Object... keys) {
        int depth = 0;
        JsonTree tree = this;
        if (depth == keys.length) return this;
        else {
            while(depth < keys.length && tree.success) {
                final Object k = keys[depth];
                if (tree.json.type == JsonObject) {
                    tree = tree.consume(coerceString(k), tree::get);
                    depth ++;
                }
                else if (tree.json.type == JsonArray) {
                    tree = tree.consume(coerceLong(k), tree::get);
                    depth ++;
                }
                else return fail("Cannot get `%s` in structure `%s`", k, json.toString());
            }
            return tree;
        }
    }

    public final JsonTree dissoc(final String... keys) {
        if (json.type == JsonObject) {
            Map<String, Json> map = jobj().value;
            for (String key: keys) {
                final String k = escape(key);
                if (map.contains(k)) {
                    map = map.remove(k);
                }
            }
            return succeed(new JObj(map));
        }
        else if (json.type == JsonEmpty) {
            return this;
        }
        else {
            return fail("Cannot dissociate keys `%s` from `%s`.", Arrays.toString(keys), json);
        }
    }

    public final JsonTree assoc(final String key, final Json value) {
        if (json.type == JsonObject || json.type == JsonEmpty) {
            return succeed(new JObj(insert(key, value)));
        } else {
            return fail("Cannot associate key `%s` into `%s`.", key, json);
        }
    }

    public final JsonTree assoc(final int index, final Json value) {
        if (json.type == JsonArray) {
            return succeed(new JArr((List<Json>) jarr().value.update(index, x -> value)));
        } else {
            return fail("Cannot associate index `%d` into `%s`.", index, json);
        }
    }

    public final JsonTree assocIn(final Json value, final Object... keys) {
        if (keys.length == 0) return this;
        else return assocInRec(json, value, 0, keys).fold(this::succeed, this::fail);
    }

    public final <A> JsonTree assocIn(final A value, final Object... keys) {
        return assocIn(value, defaultJsonConvert, keys);
    }

    public final <A> JsonTree assocIn(final A value, final Convert<A, Json> to, final Object... keys) {
        if (keys.length == 0) return this;
        else return to.convert(value).map(x -> assocIn(x, keys)).fold(x -> x, this::fail);
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

    public final JsonTree update(final String key, final Function<JsonTree, JsonTree> f) {
        return f.apply(get(key)).affix().fold(x -> assoc(key, x), this::fail);
    }

    public final JsonTree update(final int index, final Function<JsonTree, JsonTree> f) {
        return f.apply(get(index)).affix().fold(x -> assoc(index, x), this::fail);
    }

    public final JsonTree updateIn(final Function<JsonTree, JsonTree> f, Object... keys) {
        return f.apply(getIn(keys)).affix().fold(x -> assocIn(x, keys), this::fail);
    }

    public final <A, B> JsonTree updateIn (final Function<A, B> f, final Convert<Json, A> from, final Convert<B, Json> to, Object... keys) {
        return getIn(keys)
                .affix()
                .flatMap(from::convert)
                .map(f)
                .flatMap(to::convert)
                .fold(j -> assocIn(j, keys), this::fail);
    }

    public final JsonTree merge (final JsonTree that) {
        if (json.type == JsonObject) {
            if (that.json.type == JsonObject) {
                return succeed(new JObj(jobj().value.merge(that.jobj().value, (a, b) -> b)));
            }
            else return that.affix().fold(j -> fail("%s is not a JSON object", j), this::fail);
        }
        else return fail("%s is not a JSON object", json);
    }

    public final JsonTree merge (final Json that) {
        return merge(that.transform());
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