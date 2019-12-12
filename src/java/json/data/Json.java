package json.data;

public abstract class Json {
    JType type;

    public JsonTree transform() { return new JsonTree(this); }
    public String stringify () { return this.toString(); }
}