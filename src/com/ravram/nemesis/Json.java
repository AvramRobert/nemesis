package com.ravram.nemesis;

import com.ravram.nemesis.coerce.JsonReader;
import com.ravram.nemesis.coerce.JsonWriter;
import com.ravram.nemesis.model.*;
import com.ravram.nemesis.parser.Parser;
import com.ravram.nemesis.util.error.Either;

public interface Json extends JTrait {

    Json empty = JObj.empty;

    static In in (final Object... path) { return new In(path); }

    static Either<String, Json> forceParse(final String text) {
        return Parser.parse(text);
    }

    static JsonT parse (final String text) {
        return forceParse(text).fold(JsonT::new, JsonT::new);
    }

    static <A> Either<String, A> parseAs(final Read<A> f, final String text) {
        return parse(text).as(f);
    }

    static <A> JsonWriter<A> write(final A a) {
        return new JsonWriter<>(a);
    }

    static JsonReader read(final Json json) { return new JsonReader(json); }

    static String asString(final Json json) {
        return json.encode();
    }

    static <A> Either<String, Json> asJson (final A value, final Write<A> f) {
        return f.apply(value);
    }
}