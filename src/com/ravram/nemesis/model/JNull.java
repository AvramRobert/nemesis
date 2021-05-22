package com.ravram.nemesis.model;

import com.ravram.nemesis.Json;
import com.ravram.nemesis.JsonT;

public class JNull implements Json {
    private JNull() { }

    @Override
    public JType type() {
        return JType.JsonNull;
    }

    @Override
    public JsonT transform() {
        return new JsonT(this);
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
