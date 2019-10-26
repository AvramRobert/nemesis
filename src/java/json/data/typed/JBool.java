package json.data.typed;

import json.data.JsonType;

public class JBool extends Json {
    final JsonType type;
    final boolean value;

    private JBool(final boolean value) {
        this.value = value;
        this.type = JsonType.JsonBool;
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }

    public static Json jtrue = new JBool(true);
    public static Json jfalse = new JBool(false);
}
