package json.data;

import io.lacuna.bifurcan.List;

public final class JArr extends Json {
    final List<Json> value;
    public JArr(final List<Json> i) {
        this.value = i;
        this.type = JType.JsonArray;
    }

    @Override
    public String toString() {
        final StringBuilder buff = new StringBuilder();
        buff.append("[");

        for (Json json : value) {
            buff.append(", ").append(json.toString()).append("\n");
        }
        if (value.size() == 0) {
            return buff.append("]").toString();
        } else {
            return buff.delete(1, 3).append("]").toString();
        }
    }
}
