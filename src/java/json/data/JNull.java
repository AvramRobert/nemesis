package json.data;

public class JNull extends Json {
    final JsonType type;

    private JNull() {
        this.type = JsonType.JNull;
    }

    public final static Json instance = new JNull();

    @Override
    public String toString() {
        return "null";
    }
}
