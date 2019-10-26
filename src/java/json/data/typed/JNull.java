package json.data.typed;

import json.data.JsonType;

public class JNull extends Json {
    final JsonType type;

    private JNull() {
        this.type = JsonType.JsonNull;
    }

    public final static Json instance = new JNull();

    @Override
    public String toString() {
        return "null";
    }
}
