package com.ravram.nemesis.json.model;

import com.ravram.nemesis.json.coerce.Convert;
import com.ravram.nemesis.json.coerce.DynamicConversions;
import com.ravram.nemesis.util.error.Either;
import com.ravram.nemesis.util.function.Functions;
import com.ravram.nemesis.util.misc.Strings;
import io.lacuna.bifurcan.IEntry;
import io.lacuna.bifurcan.List;
import io.lacuna.bifurcan.Map;

import java.util.Arrays;

import static com.ravram.nemesis.util.error.Either.left;

public class JsonT {

    private final Json json;
    private final boolean failed;
    private final String error;

    private final JObj node = new JObj(new Map<>());

    public JsonT(final Json json) {
        this.json = json;
        this.failed = false;
        this.error = "";
    }

    public JsonT(final String error) {
        this.error = error;
        this.failed = true;
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

    private Either<String, Json> associateInObject(final JObj json, final Json toAssoc, final int depth, final Object... keys) {
        final Either<String, String> skey = DynamicConversions.coerceString(keys[depth]);
        if (skey.isRight()) {
            final String key   = Strings.escape(skey.value());
            final Json value   = json.value.get(key, node);
            return associate(value, toAssoc, depth + 1, keys).map(x -> new JObj(json.value.put(key, x)));
        } else {
            return Either.left("Key for json was expected to be a string: %s", skey.error());
        }
    }

    private Either<String, Json> associateInArray(final JArr json, final Json toAssoc, final int depth, final Object... keys) {
        final Either<String, Long> sidx = DynamicConversions.coerceLong(keys[depth]);
        if (sidx.isRight()) {
            final List<Json> list = json.value;
            final long idx        = sidx.value();
            if (list.size() > idx) {
                final Json value = list.nth(idx, node);
                return associate(value, toAssoc, depth + 1, keys).map(x -> new JArr((List<Json>) json.value.update(idx, __ -> x)));
            }
            else return Either.left("Index of `%d` does not exist.", idx);
        } else {
            return Either.left("Index for array was expected to be a long: %s", sidx.error());
        }
    }

    private Either<String, Json> associate(final Json value, final Json toAssoc, final int depth, final Object... keys) {
        if      (depth >= keys.length)     return Either.right(toAssoc);
        else if (value.type == JType.JsonObject) return associateInObject((JObj) value, toAssoc, depth, keys);
        else if (value.type == JType.JsonArray)  return associateInArray((JArr) value, toAssoc, depth, keys);
        else return left("Cannot insert into `%s`. It is not a structure.", toAssoc);
    }

    private JsonT blindGet (final In in) {
        int depth = 0;
        Object[] path = in.path;
        Json tree = json;
        if (depth == path.length) return this;
        else {
            while(depth < path.length) {
                final Object k = path[depth];
                if (tree.type == JType.JsonObject) {
                    final JObj obj = (JObj) tree;
                    final Either<String, String> ekey = DynamicConversions.coerceString(k);
                    if (ekey.isLeft()) return fail(ekey.error());
                    else {
                        final String key = ekey.value();
                        final Json value = obj.value.get(Strings.escape(key), null);
                        if (value == null) return fail("Key `%s` not found", key);
                        else {
                            tree = value;
                            depth ++;
                        }
                    }
                }
                else if (tree.type == JType.JsonArray) {
                    final JArr arr = (JArr) tree;
                    final Either<String, Long> eindex = DynamicConversions.coerceLong(k);
                    if (eindex.isLeft()) return fail(eindex.error());
                    else {
                        final long index = eindex.value();
                        final Json value = arr.value.nth(index, null);
                        if (value == null) return fail("Index `%n` does not exist", index);
                        else {
                            tree = value;
                            depth ++;
                        }
                    }
                }
                else return fail("Cannot get `%s` in structure `%s`", k, json.toString());
            }
            return succeed(tree);
        }
    }

    private JsonT blindInsert(final Json value, final In in) {
        final Either<String, Json> result = associate(json, value, 0, in.path);
        if (result.isLeft()) return fail(result.error());
        else return succeed(result.value());
    }

    private <A> JsonT blindInsertConverted(final A value, final Convert<A, Json> to, final In in) {
        final Either<String, Json> converted = to.convert(value);
        if (converted.isRight()) return blindInsert(converted.value(), in);
        else return fail(converted.error());
    }

    private JsonT blindMerge(final Json that) {
        if (json.type == JType.JsonObject) {
            if (that.type == JType.JsonObject) {
                final JObj obj = (JObj) that;
                return succeed(new JObj(jobj().value.merge(obj.value, (a, b) -> b)));
            }
            else return fail("%s is not a JSON object", that);
        }
        else if (json.type == JType.JsonArray) {
            if (that.type == JType.JsonArray) {
                final JArr arr = (JArr) that;
                return succeed(new JArr((List<Json>) jarr().value.concat(arr.value)));
            }
            else return fail("%s is not a JSON array", that);
        }
        else return fail("%s is neither a JSON object nor a JSON array", json);
    }

    private JsonT blindRemove(final String... keys) {
        if (json.type == JType.JsonObject) {
            Map<String, Json> map = jobj().value;
            for (String key: keys) {
                final String k = Strings.escape(key);
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

    private <A> Either<String, A> reduceObjBlind(final A init,
                                                 final Functions.Function3<A, String, JsonT, Either<String, A>> f) {
        A start = init;
        if (json.type == JType.JsonObject) {
            for (IEntry<String, Json> e : jobj().value.entries()) {
                final Either<String, A> res = f.apply(start, Strings.unescape(e.key()), e.value().transform());
                if (res.isRight()) start = res.value();
                else return Either.left(res.error());
            }
            return Either.right(start);
        } else return left(String.format("Cannot reduce over json type `%s`.", json.type));
    }

    private <A> Either<String, A> reduceArrBlind(final A init,
                                                 final Functions.Function3<A, Integer, JsonT, Either<String, A>> f) {
        A start = init;
        if (json.type == JType.JsonArray) {
            int i = 0;
            final List<Json> js = jarr().value;
            for (Json j : js) {
                final Either<String, A> res = f.apply(start, i, j.transform());
                if (res.isRight()) start = res.value();
                else return Either.left(res.error());
            }
            return Either.right(start);
        } else return left(String.format("Cannot reduce over json type `%s`.", json.type));
    }

    public final JsonT getJson(final In in) {
        if (failed || in.isEmpty) return this;
        else return blindGet(in);
    }

    public final <A> Either<String, A> getValue(final Convert<Json, A> f, final In in) {
        return getJson(in).as(f);
    }

    // TODO: remove (final In in) ?
    // removing from array should:
    // A. Replace the previous value with null?
    // B. Shrink the array?

    public final JsonT remove(final String... keys) {
        if (failed || keys.length == 0) return this;
        else return blindRemove(keys);
    }

    public final JsonT insertJson(final Json value, final In in) {
        if (failed || in.isEmpty) return this;
        else return blindInsert(value, in);
    }

    public final JsonT insertJson(final JsonT value, final In in) {
        if (failed || in.isEmpty) return this;
        else if (value.failed) return fail("Cannot insert; Invalid JSON: %s", value.error);
        else return blindInsert(value.json, in);
    }

    public final <A> JsonT insertValue(final A value, final Convert<A, Json> to, final In in) {
        if (failed || in.isEmpty) return this;
        else return blindInsertConverted(value, to, in);
        }

    public final <A> JsonT insertValue(final A value, final In in) {
        return insertValue(value, DynamicConversions::coerceJson, in);
    }

    public final JsonT updateJson(final Functions.Function1<JsonT, JsonT> f, final In in) {
        if (failed || in.isEmpty) return this;
        else {
            final JsonT result = f.apply(blindGet(in));
            if (result.failed) return fail("Could not apply update. Error: %s", result.error);
            else return blindInsert(result.json, in);
        }
    }

    public final <A, B> JsonT updateValue(final Convert<Json, A> from,
                                          final Functions.Function1<A, B> f,
                                          final Convert<B, Json> to,
                                          final In in) {
        if (failed || in.isEmpty) return this;
        else {
            final Convert<Json, Json> g = from.compose(f).compose(to);
            final Either<String, Json> result = blindGet(in).as(g);
            if (result.isLeft()) return fail("Could not apply update. Error: %s", result.error());
            else return blindInsert(result.value(), in);
        }
    }

    public final <A, B> JsonT updateValue(final Convert<Json, A> to, final Functions.Function1<A, B> f, final In in) {
        return updateValue(to, f, DynamicConversions::coerceJson, in);
    }

    // TODO: Add mergeValue(final A a) {} -> where a is coerced using some separate coerce collection function?

    public final JsonT mergeJson (final JsonT that) {
        if (failed) return this;
        else if (that.failed) return fail("Cannot merge: %s", that.error);
        else return blindMerge(that.json);
    }

    public final JsonT mergeJson (final Json that) {
        if (failed) return this;
        else return blindMerge(that);
    }

    public final <A> Either<String, A> reduceObj(final A init,
                                                 final Functions.Function3<A, String, JsonT, Either<String, A>> f) {
        if (failed) return Either.left(error);
        else return reduceObjBlind(init, f);
    }

    public final <A> Either<String, A> reduceArr(final A init,
                                                 final Functions.Function3<A, Integer, JsonT, Either<String, A>> f) {
        if (failed) return Either.left(error);
        else return reduceArrBlind(init, f);
    }

    public final <A> Either<String, A> as(final Convert<Json, A> f) {
        return f.convert(json);
    }

    public final Either<String, Json> affix() {
        if (failed) return Either.left(error);
        else return Either.right(json);
    }
}