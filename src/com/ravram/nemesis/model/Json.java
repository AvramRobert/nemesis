package com.ravram.nemesis.model;

public abstract class Json {
    public JType type;

    public JsonT transform() {
        return new JsonT(this);
    }

    public abstract String encode();
}