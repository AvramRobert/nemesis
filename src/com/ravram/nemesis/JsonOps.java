package com.ravram.nemesis;

import com.ravram.nemesis.coerce.JsonReader;
import com.ravram.nemesis.coerce.JsonWriter;
import com.ravram.nemesis.model.In;
import com.ravram.nemesis.model.JObj;
import com.ravram.nemesis.model.Json;
import com.ravram.nemesis.model.JsonT;
import com.ravram.nemesis.parser.Parser;
import com.ravram.nemesis.util.error.Either;

public class JsonOps {

    public  static In in (final Object... path) { return new In(path); }

    public static final Json empty = JObj.empty;

    public static Either<String, Json> forceParse(final String text) {
        return Parser.parse(text);
    }

    public static JsonT parse (final String text) {
        return forceParse(text).fold(JsonT::new, JsonT::new);
    }

    public static <A> Either<String, A> parseAs(final Read<A> f, final String text) {
        return parse(text).as(f);
    }

    public static <A> JsonWriter<A> write(final A a) {
        return new JsonWriter<>(a);
    }

    public static JsonReader read(final Json json) { return new JsonReader(json); }

    public static String asString(final Json json) {
        return json.encode();
    }

    public static <A> Either<String, Json> asJson (final A value, final Write<A> f) {
        return f.apply(value);
    }
}