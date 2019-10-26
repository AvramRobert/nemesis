package json.data.typed;

import json.data.JsonType;

public class JNum extends Json {
    final JsonType type;
    final Number value;
    public JNum(final Number value) {
        this.value = value;
        this.type = JsonType.JsonNumber;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
