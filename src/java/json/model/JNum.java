package json.model;

public class JNum extends Json {
    public final Number value;
    public JNum(final Number value) {
        this.value = value;
        this.type = JType.JsonNumber;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
