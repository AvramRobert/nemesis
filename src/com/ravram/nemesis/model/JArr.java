package com.ravram.nemesis.model;

import io.lacuna.bifurcan.List;

public final class JArr extends Json {
    public final List<Json> value;

    public JArr(final List<Json> i) {
        this.value = i;
        this.type = JType.JsonArray;
    }

    public final static Json empty = new JArr(List.of());

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public String encode() {
        final StringBuilder buff = new StringBuilder();
        buff.append("[");

        for (Json json : value) {
            buff.append(",").append(json.encode());
        }
        if (value.size() == 0) {
            return buff.append("]").toString();
        } else {
            return buff.delete(1, 2).append("]").toString();
        }
    }
}
