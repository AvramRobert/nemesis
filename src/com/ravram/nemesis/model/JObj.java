package com.ravram.nemesis.model;

import com.ravram.nemesis.util.misc.Strings;
import io.lacuna.bifurcan.IEntry;
import io.lacuna.bifurcan.Map;

import java.util.HashMap;

public final class JObj extends Json {
    public final Map<String, Json> value;
    public JObj(final Map<String, Json> i) {
        this.value = i;
        this.type = JType.JsonObject;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public String encode() {
        final StringBuilder buff = new StringBuilder();

        buff.append("{");

        for (IEntry<String, Json> entry : value) {
            buff.append(",")
                .append(Strings.escape(entry.key()))
                .append(":")
                .append(entry.value().encode());
        }
        if (value.size() == 0) {
            return buff.append("}").toString();
        } else {
            return buff.delete(1, 2).append("}").toString();
        }
    }

    public static final JObj empty = new JObj(Map.from(new HashMap<>()));
}
