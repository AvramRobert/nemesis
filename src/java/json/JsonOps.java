package json;

import json.coerce.Convert;
import json.data.*;
import util.Either;
import json.parser.Parser;

public class JsonOps {

    public static Json empty() {
        return JEmpty.instance;
    }

    public static Either<String, Json> eparse (final String text) {
        return Parser.parse(text);
    }

    public static JsonT parse (final String text) {
        return eparse(text).fold(JsonT::new, JsonT::new);
    }

    public static <A> Either<String, A> parseAs(final Convert<Json, A> f, final String text) {
        return parse(text).as(f);
    }

    public static String asString(final Json json) {
        return json.toString();
    }
}