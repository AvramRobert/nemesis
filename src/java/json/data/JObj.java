package json.data;

import io.lacuna.bifurcan.IEntry;
import io.lacuna.bifurcan.Map;

public final class JObj extends Json {
    final Map<String, Json> value;
    public JObj(final Map<String, Json> i) {
        this.value = i;
        this.type = JsonType.JsonObj;
    }

    @Override
    public String toString() {
        final StringBuilder buff = new StringBuilder();

        buff.append("{");

        for (IEntry<String, Json> entry : value) {
            buff.append(", ")
                .append(entry.key())
                .append(":")
                .append(entry.value().toString())
                .append("\n");
        }
        if (value.size() == 0) {
            return buff.append("}").toString();
        } else {
            return buff.delete(1, 3).append("}").toString();
        }
    }
}
