package com.ravram.nemesis.model;

public class JNull extends Json {
    private JNull() {
        this.type = JType.JsonNull;
    }

    public final static Json instance = new JNull();

    @Override
    public String toString() {
        return encode();
    }

    @Override
    public String encode() {
        return "null";
    }
}
