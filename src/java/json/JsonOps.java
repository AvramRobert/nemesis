package json;

import json.coerce.Convert;
import json.coerce.JsonConverter;
import json.coerce.ObjectConverter;
import json.model.In;
import json.model.JObj;
import json.model.Json;
import json.model.JsonT;
import json.parser.Parser;
import util.error.Either;

public class JsonOps {

    public  static In in (final Object... path) { return new In(path); }

    public static final Json empty = JObj.empty;

    public static Either<String, Json> forceParse(final String text) {
        return Parser.parse(text);
    }

    public static JsonT parse (final String text) {
        return forceParse(text).fold(JsonT::new, JsonT::new);
    }

    public static <A> Either<String, A> parseAs(final Convert<Json, A> f, final String text) {
        return parse(text).as(f);
    }

    public static <A> ObjectConverter<A> convert(final A a) {
        return new ObjectConverter<>(a);
    }

    public static JsonConverter convert(final JsonT json) { return new JsonConverter(json); }

    public static JsonConverter convert(final Json json) { return new JsonConverter(json.transform()); }

    public static String asString(final Json json) {
        return json.toString();
    }

    public static <A> Either<String, Json> asJson (final A value, final Convert<A, Json> f) {
        return f.convert(value);
    }
}