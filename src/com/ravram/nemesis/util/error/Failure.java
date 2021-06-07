package com.ravram.nemesis.util.error;

import com.ravram.nemesis.Attempt;

public final class Failure<A> implements Attempt<A> {
    public final String error;

    public Failure(final String error) {
        this.error = error;
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public boolean isFailure() {
        return true;
    }

    @Override
    public A value() {
        throw new UnsupportedOperationException(String.format("There is no value. Computation failed with:\n%s.", error));
    }

    @Override
    public String error() {
        return error;
    }

    @Override
    public String toString() {
        return String.format("Left: %s", error);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Failure) {
            return error.equals(((Failure<?>) obj).error);
        } else {
            return false;
        }
    }
}
