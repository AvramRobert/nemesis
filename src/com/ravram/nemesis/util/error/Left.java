package com.ravram.nemesis.util.error;

import com.ravram.nemesis.util.function.Consumers;
import static com.ravram.nemesis.util.function.Functions.Function1;

public class Left<E, R> implements Either<E, R> {
    public final E error;

    public Left(final E error) {
        this.error = error;
    }

    @Override
    public <B> B fold(Function1<R, B> f, Function1<E, B> g) {
        return g.apply(error);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <B> Either<E, B> map(Function1<R, B> f) {
        return (Left<E, B>) this;
    }

    @Override
    public void consume(Consumers.Consumer1<R> f, Consumers.Consumer1<E> g) {
        g.apply(error);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <B> Either<E, B> flatMap(Function1<R, Either<E, B>> f) {
        return (Left<E, B>) this;
    }

    @Override
    public boolean isRight() {
        return false;
    }

    @Override
    public E error() {
        return error;
    }

    @Override
    public R value() {
        throw new UnsupportedOperationException("Either is `Left` case. There is no value here.");
    }

    @Override
    public boolean isLeft() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("Left: %s", error);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Left) {
            return error == ((Left<?, ?>) obj).error;
        } else {
            return false;
        }
    }
}
