package json.parser.dynamic;

import json.data.dynamic.Json;

interface Result {
    Json json();

    static Result succeed(final Json json) { return new Success(json); }
    static Result failed(final String msg) { return new Failure(msg); }
}
