package json.data;

public class JNum extends Json {
    final JType type;
    final Number value;
    public JNum(final Number value) {
        this.value = value;
        this.type = JType.JsonNumber;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
