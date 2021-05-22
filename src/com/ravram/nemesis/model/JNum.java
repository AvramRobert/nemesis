package com.ravram.nemesis.model;

import com.ravram.nemesis.Json;
import com.ravram.nemesis.JsonT;

public class JNum implements Json {
    public final Number value;
    public JNum(final Number value) {
        this.value = value;
    }

    @Override
    public JType type() {
        return JType.JsonNumber;
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
        return value.toString();
    }
}
