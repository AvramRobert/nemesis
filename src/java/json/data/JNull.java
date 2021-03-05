package json.data;

public class JNull extends Json {
    private JNull() {
        this.type = JType.JsonNull;
    }

    public final static Json instance = new JNull();

    @Override
    public String toString() {
        return "null";
    }
}
