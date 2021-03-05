package json;

import json.coerce.Convert;
import json.coerce.ObjectConverter;
import json.data.*;
import json.parser.Parser;
import util.Either;

import static json.data.JsonT.*;

public class JsonOps {

    public  static In in (final Object... path) { return JsonT.in(path); }

    public static final Json empty = JObj.empty;

    public static Either<String, Json> eparse (final String text) {
        return Parser.parse(text);
    }

    public static JsonT parse (final String text) {
        return eparse(text).fold(JsonT::new, JsonT::new);
    }

    public static <A> Either<String, A> parseAs(final Convert<Json, A> f, final String text) {
        return parse(text).as(f);
    }

    public static <A> ObjectConverter<A> convert(final A a) {
        return new ObjectConverter<>(a);
    }

    // FIXME:
    // Make both the object and json converters keepers of the `A` and `Json` types
    // rename the primitives `with`
    // make them all return their respective conversion types and not the `Convert` instance
    // derive the current form of the objectConverter based on the new one
    // Define `convert` functions here that
    // Let the users specify the lambda.
    // eg: Convert<Json, String> = json -> convert(json).with(...);

    public static String asString(final Json json) {
        return json.toString();
    }

    public static <A> Either<String, Json> asJson (final Convert<A, Json> f, final A value) {
        return f.convert(value);
    }
}