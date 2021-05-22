package com.ravram.nemesis.model;

import com.ravram.nemesis.Json;
import com.ravram.nemesis.JsonT;
import com.ravram.nemesis.util.misc.Strings;

public final class JString implements Json {
    public final String value;
    public JString(final String i) {
        this.value = i;
    }

    @Override
    public JType type() {
        return JType.JsonString;
    }

    @Override
    public JsonT transform() {
        return new JsonT(this);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public String encode() {
        return Strings.escape(value);
    }
}
