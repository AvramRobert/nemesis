package json.data;

import io.lacuna.bifurcan.IEntry;
import io.lacuna.bifurcan.List;
import io.lacuna.bifurcan.Map;
import json.coerce.Convert;
import util.Either;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import static util.Colls.*;
import static util.Functions.*;
import static json.data.JType.*;

public class JsonT {

    private final Json json;
    private final boolean success;
    private final String error;

    private final Map<String, Json> map = new Map<>();
    private final JObj node = new JObj(map);

    public JsonT(final Json json) {
        this.json = json;
        this.success = true;
        this.error = "";
    }

    public JsonT(final String error) {
        this.error = error;
        this.success = false;
        this.json = JEmpty.instance;
    }

    public JObj jobj () {
        return (JObj) json;
    }

    private JArr jarr() {
        return (JArr) json;
    }


    private JsonT succeed(final Json json) {
        return new JsonT(json);
    }

    private JsonT fail(final String msg, Object... params) {
        return new JsonT(String.format(msg, params));
    }

    private <A> Either<String, A> left (final String msg, Object... params) {
        return Either.left(String.format(msg, params));
    }

    private Optional<Json> lookup (final String key) {
        return jobj().value.get(escape(key));
    }

    private Map<String, Json> assoc (final String key, final Json value) {
        return jobj().value.put(escape(key), value);
    }

    private List<Json> replace (final long index, final Json value) {
        return (List<Json>) jarr().value.update(index, x -> value);
    }

    private Convert<Object, String> defaultStringConvert = this::coerceString;

    private Convert<Object, Json> defaultJsonConvert = this::coerceJson;

    private String escape (final String s) {
        return String.format("\"%s\"", s);
    }

    private String unescape (final String s) { return s.replace("\"", ""); }

    private <A> JsonT consume (final Either<String, A> comp, final Function1<A, JsonT> f) {
        return comp.fold(f, this::fail);
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
            return traversel(list, this::coerceJson).map(l -> new JArr(List.from(l))); // this is mutually recursive -- may not be that good
        } else
            return Either.left(String.format("Class type of `%s` for value `%s` is not supported", value.getClass().toString(), value));
    }

    private Either<String, Json> assocInObj(final JsonT json, final Json toAssoc, final int depth, final Object... keys) {
        final Either<String, String> skey = coerceString(keys[depth]);
        if (skey.isRight()) {
            final String key   = skey.value();
            final Json value   = json.lookup(key).orElse(node);
            return assocInRec(value, toAssoc, depth + 1, keys).map(x -> new JObj(json.assoc(key, x)));
        } else {
            return left("Key for json was expected to be a string: %s", skey.error());
        }
    }

    private Either<String, Json> assocInArr(final JsonT json, final Json toAssoc, final int depth, final Object... keys) {
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
        else return left("Cannot insert into `%s`. It is not a structure.", toAssoc);
    }

    private Either<String, Json> subwalk (final IEntry<String, Json> entry,
                                          final Function2<String, JsonT, Either<String, JsonT>> f) {
        final String key  = unescape(entry.key());
        final JsonT value = entry.value().transform();
        if (value.json.type == JsonObject) {
            return value.traverse(f);
        }
        else return f.apply(key, value).flatMap(JsonT::affix);
    }

    public final JsonT get (final String key) {
        if (json.type == JsonObject) {
            return lookup(key).map(this::succeed).orElse(fail(String.format("Key `%s` not found", key)));
        }
        else return fail("Cannot lookup key `%s` in `%s`.", key, json);
    }

    public final JsonT get (final long index) {
        if (json.type == JsonArray) {
            final Json r = jarr().value.nth(index, null);
            return (r != null) ? succeed(r) : fail("Index if `%n` does not exist", index);
        }
        else return fail("Cannot lookup index `%d` in `%s`.", index, json);
    }

    public final <A> Either<String, A> getAs (final Convert<Json, A> f, final long index) {
        return get(index).as(f);
    }

    public final <A> Either<String, A> getAs(final Convert<Json, A> f, final String key) {
        return get(key).as(f);
    }

    public final <A> Either<String, A> getInAs(final Convert<Json, A> f, Object... keys) {
        return getIn(keys).as(f);
    }

    public final JsonT getIn (final Object... keys) {
        int depth = 0;
        JsonT tree = this;
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

    public final JsonT dissoc(final String... keys) {
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

    public final JsonT insert(final String key, final Json value) {
        if (json.type == JsonObject || json.type == JsonEmpty) {
            return succeed(new JObj(assoc(key, value)));
        } else {
            return fail("Cannot insert key `%s` into `%s`.", key, json);
        }
    }

    public final JsonT insert(final int index, final Json value) {
        if (json.type == JsonArray) {
            return succeed(new JArr((List<Json>) jarr().value.update(index, x -> value)));
        } else {
            return fail("Cannot insert index `%d` into `%s`.", index, json);
        }
    }

    public final JsonT insertIn(final Json value, final Object... keys) {
        if (keys.length == 0) return this;
        else return assocInRec(json, value, 0, keys).fold(this::succeed, this::fail);
    }

    public final <A> JsonT insertIn(final A value, final Object... keys) {
        return insertIn(value, defaultJsonConvert, keys);
    }

    public final <A> JsonT insertIn(final A value, final Convert<A, Json> to, final Object... keys) {
        if (keys.length == 0) return this;
        else return to.convert(value).map(x -> insertIn(x, keys)).fold(x -> x, this::fail);
    }

    public final <A> JsonT insert(final String key, final A value) {
        return insert(key, value, defaultJsonConvert);
    }

    public final <A> JsonT insert(final int index, final A value) {
        return insert(index, value, defaultJsonConvert);
    }

    public final <A> JsonT insert(final String key, final A value, final Convert<A, Json> w) {
        return consume(w.convert(value), v -> insert(key, v));
    }

    public final <A> JsonT insert(final int index, final A value, final Convert<A, Json> w) {
        return consume(w.convert(value), v -> insert(index, v));
    }

    public final JsonT update(final String key, final Function1<JsonT, JsonT> f) {
        return f.apply(get(key)).affix().fold(x -> insert(key, x), this::fail);
    }

    public final JsonT update(final int index, final Function1<JsonT, JsonT> f) {
        return f.apply(get(index)).affix().fold(x -> insert(index, x), this::fail);
    }

    public final <A, B> JsonT update(final String key,
                                     final Function1<A, B> f,
                                     final Convert<Json, A> to,
                                     final Convert<B, Json> from) {
        final Convert<Json, Json> g = to.compose(f).compose(from);
        return consume(get(key).as(g), v -> insert(key, v));
    }

    public final <A, B> JsonT update(final int index,
                                     final Function1<A, B> f,
                                     final Convert<Json, A> to,
                                     final Convert<B, Json> from) {
        final Convert<Json, Json> g = to.compose(f).compose(from);
        return consume(get(index).as(g), v -> insert(index, v));
    }

    public final JsonT updateIn(final Function1<JsonT, JsonT> f, Object... keys) {
        return f.apply(getIn(keys)).affix().fold(x -> insertIn(x, keys), this::fail);
    }

    public final <A, B> JsonT updateIn (final Function1<A, B> f, final Convert<Json, A> from, final Convert<B, Json> to, Object... keys) {
        return getIn(keys)
                .affix()
                .flatMap(from::convert)
                .map(f)
                .flatMap(to::convert)
                .fold(j -> insertIn(j, keys), this::fail);
    }

    public final JsonT merge (final JsonT that) {
        if (json.type == JsonObject) {
            if (that.json.type == JsonObject) {
                return succeed(new JObj(jobj().value.merge(that.jobj().value, (a, b) -> b)));
            }
            else return that.affix().fold(j -> fail("%s is not a JSON object", j), this::fail);
        }
        else return fail("%s is not a JSON object", json);
    }

    public final JsonT merge (final Json that) {
        return merge(that.transform());
    }

    public final Either<String, Json> affix() {
        if (success) return Either.right(json);
        else return Either.left(error);
    }

    public final <A> Either<String, A> as(final Convert<Json, A> f) {
        return f.convert(json);
    }

    public final <A> Either<String, A> reduceObj (final A init,
                                                  final Function3<A, String, JsonT, Either<String, A>> f) {
        A start = init;
        if (json.type == JsonObject) {
            for (IEntry<String, Json> e: jobj ().value.entries()) {
                final Either<String, A> res = f.apply(start, unescape(e.key()), e.value().transform());
                if (res.isRight()) start     = res.value();
                else return Either.left(res.error());
            }
            return Either.right(start);
        }
        else return Either.left(String.format("Cannot reduce over json type `%s`.", json.type));
    }

    public final <A> Either<String, A> reduceArr (final A init,
                                                  final Function3<A, Integer, JsonT, Either<String, A>> f) {
        A start = init;
        if (json.type == JsonArray) {
            int i = 0;
            final List<Json> js = jarr().value;
            for (Json j : js) {
                final Either<String, A> res = f.apply(start, i, j.transform());
                if (res.isRight()) start = res.value();
                else return Either.left(res.error());
            }
            return Either.right(start);
        }
        else return Either.left(String.format("Cannot reduce over json type `%s`.", json.type));
    }

    public final Either<String, Json> traverse (final Function2<String, JsonT, Either<String, JsonT>> f) {
        final HashMap<String, Json> hm = new HashMap<>();
        if (json.type == JsonObject) {
            for (IEntry<String, Json> e : jobj().value) {
                final Either<String, Json> result = subwalk(e, f);
                if (result.isRight()) result.consume(json -> hm.put(e.key(), json), x -> {});
                else return Either.left(result.error());
            }
        }
        else return Either.left(String.format("Cannot traverse over json type `%s`.", json.type));
        return Either.right(new JObj(Map.from(hm)));
    }
}