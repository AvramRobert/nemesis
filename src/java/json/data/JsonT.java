package json.data;

import io.lacuna.bifurcan.List;
import io.lacuna.bifurcan.Map;

public class JsonT {
    final JsonType type;
    final Object value;
    public JsonT(final JsonType type, final Object value) {
        this.value = value;
        this.type = type;
    }

    final JsonT empty = new JsonT(JsonType.JsonEmpty, null);

    final Map<String, JsonT> jobj() {
        return (Map<String, JsonT>) value;
    }

    final List<JsonT> jseq() {
        return (List<JsonT>) value;
    }

    public JsonT at (final String name) {
        switch (type) {
            case JsonObj:
                return jobj().get(name, empty);
            default:
                return this;
        }
    }

    public JsonT at (final Long index) {
        switch (type) {
            case JsonSeq:
                return jseq().nth(index);
            default:
                return this;
        }
    }
}