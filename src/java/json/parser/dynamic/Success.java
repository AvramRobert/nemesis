package json.parser.dynamic;

import json.data.dynamic.Json;

public final class Success implements Result {
    public final Json value;

    public Success(final Json result) {
        this.value = result;
    }

    @Override
    public Json json() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("Success: %s", value.toString());
    }

}
