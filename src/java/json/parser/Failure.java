package json.parser;

import json.data.Json;

public final class Failure extends Result {
    public final String msg;

    public Failure(final String msg) {
        this.msg = msg;
    }

    @Override
    Json value() { return null; }

    @Override
    public String toString() {
        return String.format("Failure: %s", msg);
    }
}
