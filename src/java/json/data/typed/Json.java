package json.data.typed;

import json.data.JsonType;

public abstract class Json {
    JsonType type;

    private JEmpty jEmpty () {
        return (JEmpty) this;
    }

    private JObj jobj () {
        return (JObj) this;
    }

    private JArr jseq () {
        return (JArr) this;
    }

    public final Json at (final String name) {
        switch (type) {
            case JsonObj:
                return jobj().value.get(name, JEmpty.empty);
            default:
                return this;
        }
    }

    public final Json at (final long index) {
        switch (type) {
            case JsonArr:
                return jseq().value.nth(index, JEmpty.empty);
            default:
                return this;
        }
    }
}
