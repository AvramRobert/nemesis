package json.parser;

import json.data.Json;

interface Result {
    Json json ();
    boolean succeeded();

    static Success succeed(final Json result) { return new Success(result); }
    static Failure failed(final String msg) { return new Failure(msg); }
}
