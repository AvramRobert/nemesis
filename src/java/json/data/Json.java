package json.data;

import json.coerce.JsonMapper;

public abstract class Json {
    JType type;

    public JsonTree transform() {
        return new JsonTree(this);
    }

    public String stringify() {
        return this.toString();
    }

    public JsonMapper mapper() {
        return new JsonMapper(transform());
    }
}