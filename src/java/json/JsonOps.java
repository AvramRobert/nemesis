package json;

import json.coerce.Convert;
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

    public static <A> Either<String, A> parseAs(final String text, final Convert<Json, A> f) {
        return parse(text).flatMap(x -> x.transform().as(f));
    }

    public static String asString(final Json json) {
        return json.toString();
    }
}