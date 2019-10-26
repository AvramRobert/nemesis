package json.data.dynamic;

import io.lacuna.bifurcan.List;
import io.lacuna.bifurcan.Map;
import json.data.JsonType;

public class Json {
    final JsonType type;
    final Object value;
    public Json(final JsonType type, final Object value) {
        this.value = value;
        this.type = type;
    }

    public static final Json jnull = new Json(JsonType.JsonNull, null);
    public static final Json empty = new Json(JsonType.JsonEmpty, null);
    public static final Json jtrue = new Json(JsonType.JsonBool, true);
    public static final Json jfalse = new Json(JsonType.JsonBool, false);

    final Map<String, Json> jobj() {
        return (Map<String, Json>) value;
    }

    final List<Json> jarr() {
        return (List<Json>) value;
    }

    final Integer jint() {
        return (Integer) value;
    }

    final Float jfloat() {
        return (Float) value;
    }

    final Number jnum() { return (Number) value; }

    final Boolean jbool() { return (Boolean) value; }

    final String jstring() { return (String) value; }

    public Json at (final String name) {
        switch (type) {
            case JsonObj:
                return jobj().get(name, empty);
            default:
                return this;
        }
    }

    public Json at (final Long index) {
        switch (type) {
            case JsonArr:
                return jarr().nth(index);
            default:
                return this;
        }
    }

    @Override
    public String toString() {
        switch (type) {
            case JsonNull: return "null";
            case JsonEmpty: return "{}";
            case JsonBool: return jbool().toString();
            case JsonNumber: return jnum().toString();
            case JsonString: return jstring();
            case JsonArr: return jarr().stream().map(Object::toString).toString();
            case JsonObj: return jobj().mapValues((x, y) -> y.toString()).toString();
            default: return "{}";
        }
    }
}