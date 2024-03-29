package com.ravram.nemesis;

import com.ravram.nemesis.coerce.Convert;
import com.ravram.nemesis.coerce.DynamicConversions;
import com.ravram.nemesis.model.JArr;
import com.ravram.nemesis.model.JObj;
import com.ravram.nemesis.model.JType;
import com.ravram.nemesis.util.function.Functions;
import io.lacuna.bifurcan.IEntry;
import io.lacuna.bifurcan.List;
import io.lacuna.bifurcan.Map;

import java.util.Arrays;

import static com.ravram.nemesis.util.function.Functions.Function1;
import static com.ravram.nemesis.util.function.Functions.Function2;
import static com.ravram.nemesis.util.function.Functions.Function3;

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

    private JObj jobj() {
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

    private Attempt<Json> associateInObject(final JObj json, final Json toAssoc, final int depth, final Object... keys) {
        final Attempt<String> skey = DynamicConversions.coerceKey(keys[depth]);
        if (skey.isSuccess()) {
            final String key = skey.value();
            final Json value = json.value.get(key, node);
            return associate(value, toAssoc, depth + 1, keys).map(x -> new JObj(json.value.put(key, x)));
        } else {
            return Attempt.failure("Key for json was expected to be a string: %s", skey.error());
        }
    }

    private Attempt<Json> associateInArray(final JArr json, final Json toAssoc, final int depth, final Object... keys) {
        final Attempt<Long> sidx = DynamicConversions.coerceIndex(keys[depth]);
        if (sidx.isSuccess()) {
            final List<Json> list = json.value;
            final long idx = sidx.value();
            if (list.size() > idx) {
                final Json value = list.nth(idx, node);
                return associate(value, toAssoc, depth + 1, keys).map(x -> new JArr((List<Json>) json.value.update(idx, __ -> x)));
            } else return Attempt.failure("Index of `%d` does not exist.", idx);
        } else {
            return Attempt.failure("Index for array was expected to be a long: %s", sidx.error());
        }
    }

    private Attempt<Json> associate(final Json value, final Json toAssoc, final int depth, final Object... keys) {
        if (depth >= keys.length) return Attempt.success(toAssoc);
        else if (value.type() == JType.JsonObject) return associateInObject((JObj) value, toAssoc, depth, keys);
        else if (value.type() == JType.JsonArray) return associateInArray((JArr) value, toAssoc, depth, keys);
        else return Attempt.failure("Cannot insert into `%s`. It is not a structure.", toAssoc);
    }

    private JsonT blindGet(final Object... path) {
        int depth = 0;
        Json tree = json;
        if (depth == path.length) return this;
        else {
            while (depth < path.length) {
                final Object k = path[depth];
                if (tree.type() == JType.JsonObject) {
                    final JObj obj = (JObj) tree;
                    final Attempt<String> ekey = DynamicConversions.coerceKey(k);
                    if (ekey.isFailure()) return fail(ekey.error());
                    else {
                        final String key = ekey.value();
                        final Json value = obj.value.get(key, null);
                        if (value == null) return fail("Key `%s` not found", key);
                        else {
                            tree = value;
                            depth++;
                        }
                    }
                } else if (tree.type() == JType.JsonArray) {
                    final JArr arr = (JArr) tree;
                    final Attempt<Long> eindex = DynamicConversions.coerceIndex(k);
                    if (eindex.isFailure()) return fail(eindex.error());
                    else {
                        final long index = eindex.value();
                        final Json value = arr.value.nth(index, null);
                        if (value == null) return fail("Index `%n` does not exist", index);
                        else {
                            tree = value;
                            depth++;
                        }
                    }
                } else return fail("Cannot get `%s` in structure `%s`", k, json.toString());
            }
            return succeed(tree);
        }
    }

    private JsonT blindInsert(final Json value, final Object... path) {
        final Attempt<Json> result = associate(json, value, 0, path);
        if (result.isFailure()) return fail(result.error());
        else return succeed(result.value());
    }

    private <A> JsonT blindInsertConverted(final A value, final Write<A> to, final Object... path) {
        final Attempt<Json> converted = to.apply(value);
        if (converted.isSuccess()) return blindInsert(converted.value(), path);
        else return fail(converted.error());
    }

    private JsonT blindMerge(final Json that) {
        if (json.type() == JType.JsonObject) {
            if (that.type() == JType.JsonObject) {
                final JObj obj = (JObj) that;
                return succeed(new JObj(jobj().value.merge(obj.value, (a, b) -> b)));
            } else return fail("%s is not a JSON object", that);
        } else if (json.type() == JType.JsonArray) {
            if (that.type() == JType.JsonArray) {
                final JArr arr = (JArr) that;
                return succeed(new JArr((List<Json>) jarr().value.concat(arr.value)));
            } else return fail("%s is not a JSON array", that);
        } else return fail("%s is neither a JSON object nor a JSON array", json);
    }

    private JsonT blindRemove(final String... keys) {
        if (json.type() == JType.JsonObject) {
            Map<String, Json> map = jobj().value;
            for (String key : keys) {
                if (map.contains(key)) {
                    map = map.remove(key);
                }
            }
            return succeed(new JObj(map));
        } else {
            return fail("Cannot remove keys `%s` from `%s`.", Arrays.toString(keys), json);
        }
    }

    private <A> Attempt<A> blindReduceObj(final A init,
                                          final Function3<A, String, JsonT, Attempt<A>> f) {
        A start = init;
        if (json.type() == JType.JsonObject) {
            for (IEntry<String, Json> e : jobj().value.entries()) {
                final Attempt<A> res = f.apply(start, e.key(), e.value().transform());
                if (res.isSuccess()) start = res.value();
                else return Attempt.failure(res.error());
            }
            return Attempt.success(start);
        } else return Attempt.failure("Cannot reduce over json type `%s`.", json.type());
    }

    private <A> Attempt<A> blindReduceArr(final A init,
                                          final Function3<A, Integer, JsonT, Attempt<A>> f) {
        A start = init;
        if (json.type() == JType.JsonArray) {
            int i = 0;
            final List<Json> js = jarr().value;
            for (Json j : js) {
                final Attempt<A> res = f.apply(start, i, j.transform());
                if (res.isSuccess()) start = res.value();
                else return Attempt.failure(res.error());
            }
            return Attempt.success(start);
        } else return Attempt.failure("Cannot reduce over json type `%s`", json.type());
    }

    private boolean empty(final Object... path) {
        return path.length == 0;
    }

    public final JsonT getJson(final Object... path) {
        if (failed || empty(path)) return this;
        else return blindGet(path);
    }

    public final <A> Attempt<A> getValue(final Read<A> f, final Object... path) {
        return getJson(path).as(f);
    }

    public final JsonT remove(final String... keys) {
        if (failed || keys.length == 0) return this;
        else return blindRemove(keys);
    }

    public final JsonT insertJson(final Json value, final Object... path) {
        if (failed || empty(path)) return this;
        else return blindInsert(value, path);
    }

    public final JsonT insertJson(final JsonT value, final Object... path) {
        if (failed || empty(path)) return this;
        else if (value.failed) return fail("Cannot insert; Invalid JSON: %s", value.error);
        else return blindInsert(value.json, path);
    }

    public final <A> JsonT insertValue(final A value, final Write<A> to, final Object... path) {
        if (failed || empty(path)) return this;
        else return blindInsertConverted(value, to, path);
    }

    public final <A> JsonT insertValue(final A value, final Object... path) {
        return insertValue(value, DynamicConversions::coerceJson, path);
    }

    public final JsonT updateJson(final Function1<JsonT, JsonT> f, final Object... path) {
        if (failed || empty(path)) return this;
        else {
            final JsonT result = f.apply(blindGet(path));
            if (result.failed) return fail("Could not apply update. Error: %s", result.error);
            else return blindInsert(result.json, path);
        }
    }

    public final <A, B> JsonT updateValue(final Read<A> from,
                                          final Function1<A, B> f,
                                          final Write<B> to,
                                          final Object... path) {
        if (failed || empty(path)) return this;
        else {
            final Convert<Json, Json> g = to.compose(f).compose(from);
            final Attempt<Json> result = blindGet(path).as(g::apply);
            if (result.isFailure()) return fail("Could not apply update. Error: %s", result.error());
            else return blindInsert(result.value(), path);
        }
    }

    public final <A, B> JsonT updateValue(final Read<A> to, final Function1<A, B> f, final Object... path) {
        return updateValue(to, f, DynamicConversions::coerceJson, path);
    }

    // TODO: Add mergeValue(final A a) {} -> where a is coerced using some separate coerce collection function?

    public final JsonT mergeJson(final JsonT that) {
        if (failed) return this;
        else if (that.failed) return fail("Cannot merge: %s", that.error);
        else return blindMerge(that.json);
    }

    public final JsonT mergeJson(final Json that) {
        if (failed) return this;
        else return blindMerge(that);
    }

    public final <A> Attempt<A> reduceObj(final A init,
                                          final Function3<A, String, JsonT, Attempt<A>> f) {
        if (failed) return Attempt.failure(error);
        else return blindReduceObj(init, f);
    }

    public final <A> Attempt<A> reduceArr(final A init,
                                          final Function3<A, Integer, JsonT, Attempt<A>> f) {
        if (failed) return Attempt.failure(error);
        else return blindReduceArr(init, f);
    }

    public final <A> Attempt<A> as(final Read<A> f) {
        if (failed) return Attempt.failure(error);
        else return f.apply(json);
    }

    public final Attempt<Json> affix() {
        if (failed) return Attempt.failure(error);
        else return Attempt.success(json);
    }
}