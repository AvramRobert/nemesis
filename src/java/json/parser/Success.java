package json.parser;

import json.data.Json;

public final class Success extends Consumption {
    public final Json result;

    public Success(final Json result) {
        this.result = result;
    }

    @Override
    Json value() { return result; }

    @Override
    public String toString() {
        return String.format("Success: %s", result);
    }
}
