package json.parser;

import json.data.Json;

public final class Success implements Result {
    public final Json result;

    public Success(final Json result) {
        this.result = result;
    }

    @Override
    public Json json() { return result; }

    @Override
    public String toString() {
        return String.format("Success: %s", result);
    }

    @Override
    public boolean succeeded() {
        return true;
    }
}
