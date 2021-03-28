package com.ravram.nemesis;

import com.ravram.nemesis.model.In;
import com.ravram.nemesis.util.error.Either;
import com.ravram.nemesis.coerce.Convert;
import com.ravram.nemesis.coerce.JsonConverter;
import com.ravram.nemesis.coerce.ObjectConverter;
import com.ravram.nemesis.parser.Parser;
import com.ravram.nemesis.model.JObj;
import com.ravram.nemesis.model.Json;
import com.ravram.nemesis.model.JsonT;

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