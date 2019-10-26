package json.parser;

import json.data.Json;

public abstract class Result {
    abstract Json value ();

    public boolean succeeded() {
        return this instanceof Success;
    }

    public static Success succeed(final Json result) {
        return new Success(result);
    }

    public static Failure failed(final String msg) { return new Failure(msg); }
}
