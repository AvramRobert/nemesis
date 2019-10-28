package json.data;

import io.lacuna.bifurcan.List;
import io.lacuna.bifurcan.Map;

import java.util.Optional;
import java.util.stream.Collectors;

import static json.data.JType.*;

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

    private Json rassocObj(final Json value, final int at, final String... keys) {
        if (at >= keys.length) return value;
        else {
            final String key = keys[0];
            final Map<String, Json> map = new Map<String, Json>().put(key, rassocObj(value, at + 1, keys));
            return new JObj(map);
        }
    }

    private Json rassocArr(final Json value, final int at, final int... indexes) {
        if (at >= indexes.length) return value;
        else {
            final List<Json> list = new List<Json>().addLast(rassocArr(value, at + 1, indexes));
            return new JArr(list);
        }
    }

    private Json nestedAssocObj(final Json value, final int at, final String... keys) {
        if (type == JsonObject) {
            final String key = keys[0];
            final Map<String, Json> obj = jobj().value;
            final Json thing = obj.get(key, null);
            if (thing != null) {
                return new JObj(obj.put(key, thing.nestedAssocObj(value, at + 1, keys)));
            }
            return rassocObj(value, at, keys);
        } else return this;
    }

    private Json nestedAssocArr(final Json value, final int at, final int... indexes) {
        if (type == JsonArray) {
            final int idx = indexes[at];
            final List<Json> arr = jarr().value;
            final Json thing = arr.nth(idx, null);
            if (thing != null) {
                final List<Json> updated = (List<Json>) arr.update(idx, x -> x.nestedAssocArr(value, at + 1, indexes));
                return new JArr(updated);
            }
            else return rassocArr(value, at, indexes);
        }
        else return this;
    }

    public final Json assoc (final Json value, final int... indexes) {
        if (type == JsonArray) {
            if (indexes.length == 0) return this;
            else return nestedAssocArr(value, 0, indexes);
        }
        else return this;
    }

    public final Json assoc (final Json value, final String... keys) {
        if (type == JsonObject) {
            if (keys.length == 0) return this;
            else return nestedAssocObj(value, 0, keys);
        } else return this;
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

    private <A> Optional<Json> coerce (final A value) {
        if (value instanceof Number) {
            return Optional.of(new JNum((Number) value));
        }
        else if (value instanceof String) {
            return Optional.of(new JString((String) value));
        }
        else if (value instanceof Boolean) {
            final boolean bool = (Boolean) value;
            return Optional.of(bool ? JBool.jtrue : JBool.jfalse);
        }
        else if (value == null) {
            return Optional.of(JNull.instance);
        }
        else if (value instanceof Json) {
            return Optional.of((Json) value);
        }
        else return Optional.empty(); // should I throw?
    }

    public final <A> Json assoc (final String key, final A value) {
        return assoc(key, coerce(value).get());
    }

    public final <A> Json assoc (final String key, final java.util.List<A> vals) {
        final java.util.List<Json> list = vals
                        .stream()
                        .map(this::coerce)
                        .map(Optional::get)
                        .collect(Collectors.toList());
        return assoc(key, new JArr(List.from(list)));
    }
}
