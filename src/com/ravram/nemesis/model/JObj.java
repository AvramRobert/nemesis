package com.ravram.nemesis.model;

import com.ravram.nemesis.Json;
import com.ravram.nemesis.JsonT;
import com.ravram.nemesis.util.misc.Strings;
import io.lacuna.bifurcan.IEntry;
import io.lacuna.bifurcan.Map;

import java.util.HashMap;

public final class JObj implements Json {
    public final Map<String, Json> value;
    public JObj(final Map<String, Json> i) {
        this.value = i;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public JType type() {
        return JType.JsonObject;
    }

    @Override
    public JsonT transform() {
        return new JsonT(this);
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
