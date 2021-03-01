package json;

import json.coerce.Convert;
import json.coerce.ObjectConverter;
import json.data.*;
import json.parser.Parser;
import util.Either;

import static json.data.JsonT.*;

public class JsonOps {

    public  static In in (final Object... path) { return JsonT.in(path); }

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
    public static <A> ObjectConverter<A> objectConverter() {
        return new ObjectConverter<>();
    }

    public static String asString(final Json json) {
        return json.toString();
    }

    public static <A> Either<String, Json> asJson (final Convert<A, Json> f, final A value) {
        return f.convert(value);
    }
}