package com.ravram.nemesis.model;

import com.ravram.nemesis.util.misc.Strings;

public final class JString extends Json {
    public final String value;
    public JString(final String i) {
        this.value = i;
        this.type = JType.JsonString;
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
