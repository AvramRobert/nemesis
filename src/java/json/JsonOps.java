package json;

import json.data.*;
import util.Either;
import json.parser.Parser;

public class JsonOps {

    public static Json empty() {
        return JEmpty.instance;
    }

    public static Either<String, Json> parse (final String text) {
        return Parser.parse(text);
    }

    public static String asString(final Json json) {
        return json.toString();
    }
}