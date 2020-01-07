package json.data;

public abstract class Json {
    JType type;

    public JsonTransform transform() {
        return new JsonTransform(this);
    }

    public String stringify() {
        return this.toString();
    }
}