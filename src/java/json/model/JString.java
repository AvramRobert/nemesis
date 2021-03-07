package json.model;

public final class JString extends Json {
    public final String value;
    public JString(final String i) {
        this.value = i;
        this.type = JType.JsonString;
    }

    @Override
    public String toString() {
        return value;
    }
}
