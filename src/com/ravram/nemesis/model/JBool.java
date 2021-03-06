package com.ravram.nemesis.model;

public class JBool extends Json {
    public final boolean value;

    public JBool(final boolean value) {
        this.value = value;
        this.type = JType.JsonBool;
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
