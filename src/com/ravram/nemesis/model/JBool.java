package com.ravram.nemesis.model;

import com.ravram.nemesis.Json;
import com.ravram.nemesis.JsonT;

public class JBool implements Json {
    public final boolean value;

    public JBool(final boolean value) {
        this.value = value;
    }

    @Override
    public JType type() {
        return JType.JsonBool;
    }

    @Override
    public JsonT transform() {
        return new JsonT(this);
    }

    @Override
    public String toString() {
        return encode();
    }

    @Override
    public String encode() {
        return Boolean.toString(value);
    }

    public static Json jtrue = new JBool(true);
    public static Json jfalse = new JBool(false);
}
