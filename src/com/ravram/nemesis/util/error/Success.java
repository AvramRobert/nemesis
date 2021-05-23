package com.ravram.nemesis.util.error;

import com.ravram.nemesis.Attempt;

public final class Success<A> implements Attempt<A> {
    public final A value;

    public Success(final A value) {
        this.value = value;
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public boolean isFailure() {
        return false;
    }

    @Override
    public A value() {
        return value;
    }

    @Override
    public String error() {
        throw new UnsupportedOperationException(String.format("There is no error. Operation succeeded with %s.", value));
    }

    @Override
    public String toString() {
        return String.format("Right: %s", value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Success) {
            return value.equals(((Success<?>) obj).value);
        } else {
            return false;
        }
    }
}
