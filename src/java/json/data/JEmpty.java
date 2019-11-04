package json.data;

public final class JEmpty extends Json {

    public final static JEmpty instance = new JEmpty();

    public JEmpty() {
        this.type = JType.JsonEmpty;
    }

    @Override
    public String toString() {
        return "{}";
    }
}
