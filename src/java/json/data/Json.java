package json.data;

public abstract class Json {
    JType type;

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
            case JsonObject:
                return jobj().value.get(name, JEmpty.empty);
            default:
                return this;
        }
    }

    public final Json at (final long index) {
        switch (type) {
            case JsonArray:
                return jseq().value.nth(index, JEmpty.empty);
            default:
                return this;
        }
    }
}
