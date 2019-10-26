package json.parser.typed;

import json.data.typed.Json;

interface Result {
    Json json ();
    static Success succeed(final Json result) { return new Success(result); }
    static Failure failed(final String msg) { return new Failure(msg); }
}
