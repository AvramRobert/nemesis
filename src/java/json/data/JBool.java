package json.data;

public class JBool extends Json {
    final JType type;
    public final boolean value;

    public JBool(final boolean value) {
        this.value = value;
        this.type = JType.JsonBool;
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }

    public static Json jtrue = new JBool(true);
    public static Json jfalse = new JBool(false);
}
