package com.ravram.nemesis.model;

public class JNum extends Json {
    public final Number value;
    public JNum(final Number value) {
        this.value = value;
        this.type = JType.JsonNumber;
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
