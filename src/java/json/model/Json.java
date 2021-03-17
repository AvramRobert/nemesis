package json.model;

public abstract class Json {
    public JType type;

    public JsonT transform() {
        return new JsonT(this);
    }

    public String stringify() {
        return this.toString();
    }
}