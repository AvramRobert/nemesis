package json.data;

public final class JEmpty extends Json {

    public static JEmpty empty = new JEmpty();

    public JEmpty() {
        this.type = JsonType.JsonEmpty;
    }

    @Override
    public String toString() {
        return "{}";
    }
}
