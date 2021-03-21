package com.ravram.nemesis.json.model;

public final class In {
    public final boolean isEmpty;
    public final Object[] path;
    public In(final Object[] path) {
        this.path = path;
        isEmpty = this.path.length == 0;
    }

    public final Object[] getPath() {
        return path;
    }
}