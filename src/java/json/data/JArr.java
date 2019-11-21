package json.data;

import io.lacuna.bifurcan.List;
import util.Debug;

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
            //Debug.println(json);
            buff.append(",").append(json.toString());
        }
        if (value.size() == 0) {
            return buff.append("]").toString();
        } else {
            return buff.delete(1, 2).append("]").toString();
        }
    }
}
