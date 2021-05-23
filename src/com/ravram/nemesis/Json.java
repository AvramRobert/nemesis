package com.ravram.nemesis;

import com.ravram.nemesis.coerce.JsonReader;
import com.ravram.nemesis.coerce.JsonWriter;
import com.ravram.nemesis.model.JObj;
import com.ravram.nemesis.model.JTrait;
import com.ravram.nemesis.parser.Parser;

public interface Json extends JTrait {

    Json empty = JObj.empty;

    static Attempt<Json> forceParse(final String text) {
        return Parser.parse(text);
    }

    static JsonT parse (final String text) {
        return forceParse(text).fold(JsonT::new, JsonT::new);
    }

    static <A> Attempt<A> parseAs(final Read<A> f, final String text) {
        return parse(text).as(f);
    }

    static <A> JsonWriter<A> write(final A a) {
        return new JsonWriter<>(a);
    }

    static JsonReader read(final Json json) { return new JsonReader(json); }

    static String asString(final Json json) {
        return json.encode();
    }

    static <A> Attempt<Json> asJson (final A value, final Write<A> f) {
        return f.apply(value);
    }
}