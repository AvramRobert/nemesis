package json.parser.dynamic;

import json.data.dynamic.Json;

public final class Failure implements Result {
    public final String msg;

    public Failure(final String msg) {
        this.msg = msg;
    }

    @Override
    public Json json() { return null; }

    @Override
    public String toString() {
        return String.format("Failure: %s", msg);
    }
}
