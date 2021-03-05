package json.data;

import io.lacuna.bifurcan.IEntry;
import io.lacuna.bifurcan.List;
import io.lacuna.bifurcan.Map;
import json.coerce.Convert;
import json.coerce.General;
import util.error.Either;

import java.util.Arrays;
import java.util.Optional;

import static json.coerce.General.coerceLong;
import static json.coerce.General.coerceString;
import static json.data.JType.JsonArray;
import static json.data.JType.JsonObject;
import static util.function.Functions.Function1;
import static util.function.Functions.Function3;
import static util.misc.Strings.escape;
import static util.misc.Strings.unescape;

public class JsonT {

    public final static class In {
        final Object[] path;

        public In(final Object[] path) {
            this.path = path;
        }
    }

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
        this.json = node;
    }

    private JObj jobj () {
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

    private Optional<Json> lookupKey(final String key) {
        return jobj().value.get(escape(key));
    }

    private Optional<Json> lookupIndex(final long index) {
        return Optional.ofNullable(jarr().value.nth(index, null));
    }

    private Map<String, Json> assoc (final String key, final Json value) {
        return jobj().value.put(escape(key), value);
    }

    private List<Json> replace (final long index, final Json value) {
        return (List<Json>) jarr().value.update(index, x -> value);
    }

    private <A> JsonT consume (final Either<String, A> comp, final Function1<A, JsonT> f) {
        return comp.fold(f, this::fail);
    }

    private Either<String, Json> assocInObj(final JsonT json, final Json toAssoc, final int depth, final Object... keys) {
        final Either<String, String> skey = coerceString(keys[depth]);
        if (skey.isRight()) {
            final String key   = skey.value();
            final Json value   = json.lookupKey(key).orElse(node);
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

    public final <A> Either<String, A> getAs(final Convert<Json, A> f, final In in) {
        return get(in).as(f);
    }

    public final JsonT get (final In in) {
        int depth = 0;
        Object[] path = in.path;
        JsonT tree = this;
        if (depth == path.length) return this;
        else {
            while(depth < path.length && tree.success) {
                final Object k = path[depth];
                final JsonT tempTree = tree;
                if (tree.json.type == JsonObject) {
                    tree = tree.consume(coerceString(k),
                      key -> tempTree
                        .lookupKey(key)
                        .map(this::succeed)
                        .orElse(fail("Key `%s` not found", key)));
                    depth ++;
                }
                else if (tree.json.type == JsonArray) {
                    tree = tree.consume(coerceLong(k),
                      index -> tempTree
                        .lookupIndex(index)
                        .map(this::succeed)
                        .orElse(fail("Index if `%n` does not exist", index)));
                    depth ++;
                }
                else return fail("Cannot get `%s` in structure `%s`", k, json.toString());
            }
            return tree;
        }
    }

    // TODO: remove (final In in) ?

    public final JsonT remove(final String... keys) {
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
        else {
            return fail("Cannot remove keys `%s` from `%s`.", Arrays.toString(keys), json);
        }
    }

    public final JsonT insert(final Json value, final In in) {
        if (in.path.length == 0) return this;
        else return assocInRec(json, value, 0, in.path).fold(this::succeed, this::fail);
    }

    public final JsonT insert(final JsonT value, final In in) {
        final Either<String, Json> affixed = value.affix();
        if (affixed.isRight()) return insert(affixed, in);
        else return fail("Cannot insert invalid json: ", affixed.error());
    }

    public final <A> JsonT insert(final A value, final Convert<A, Json> to, final In in) {
        if (in.path.length == 0) return this;
        else return to.convert(value).map(x -> insert(x, in)).fold(x -> x, this::fail);
    }

    public final <A> JsonT insert(final A value, final In in) {
        return insert(value, General::coerceJson, in);
    }

    public final JsonT update(final Function1<JsonT, JsonT> f, final In in) {
        return consume(f.apply(get(in)).affix(), v -> insert(v, in));
    }

    public final <A, B> JsonT update(final Convert<Json, A> to, final Function1<A, B> f, final In in) {
        return consume(get(in).as(to).map(f), v -> insert(v, in));
    }

    public final <A, B> JsonT update(final Convert<Json, A> to,
                                     final Function1<A, B> f,
                                     final Convert<B, Json> from,
                                     final In in) {
        final Convert<Json, Json> g = to.compose(f).compose(from);
        return consume(get(in).as(g), v -> insert(v, in));
    }

    public final JsonT merge (final JsonT that) {
        if (json.type == JsonObject) {
            if (that.json.type == JsonObject) {
                return succeed(new JObj(jobj().value.merge(that.jobj().value, (a, b) -> b)));
            }
            else return that.affix().fold(j -> fail("%s is not a JSON object", j), this::fail);
        }
        else if (json.type == JsonArray) {
            if (that.json.type == JsonArray) {
                return succeed(new JArr((List<Json>) jarr().value.concat(that.jarr().value)));
            }
            else return that.affix().fold(j -> fail("%s is not a JSON array", j), this::fail);
        }
        else return fail("%s is neither a JSON object nor a JSON array", json);
    }

    public final JsonT merge (final Json that) {
        return merge(that.transform());
    }

    public final <A> Either<String, A> as(final Convert<Json, A> f) {
        return f.convert(json);
    }

    public final <A> Either<String, A> reduceObj(final A init,
                                                 final Function3<A, String, JsonT, Either<String, A>> f) {
        A start = init;
        if (json.type == JsonObject) {
            for (IEntry<String, Json> e : jobj().value.entries()) {
                final Either<String, A> res = f.apply(start, unescape(e.key()), e.value().transform());
                if (res.isRight()) start = res.value();
                else return Either.left(res.error());
            }
            return Either.right(start);
        } else return Either.left(String.format("Cannot reduce over json type `%s`.", json.type));
    }

    public final <A> Either<String, A> reduceArr(final A init,
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
        } else return Either.left(String.format("Cannot reduce over json type `%s`.", json.type));
    }

    public final Either<String, Json> affix() {
        if (success) return Either.right(json);
        else return Either.left(error);
    }

    public static In in(final Object... path) {
        return new In(path);
    }
}