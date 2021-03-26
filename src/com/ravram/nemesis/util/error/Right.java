package com.ravram.nemesis.util.error;

import com.ravram.nemesis.util.function.Consumers;
import com.ravram.nemesis.util.function.Functions.Function1;

public class Right<E, R> implements Either<E, R> {
    public final R value;

    public Right(final R value) {
        this.value = value;
    }

    @Override
    public void consume(Consumers.Consumer1<R> f, Consumers.Consumer1<E> g) {
        f.apply(value);
    }

    @Override
    public <B> B fold(Function1<R, B> f, Function1<E, B> g) {
        return f.apply(value);
    }

    @Override
    public <B> Either<E, B> map(Function1<R, B> f) {
        return new Right<>(f.apply(value));
    }

    @Override
    public <B> Either<E, B> flatMap(Function1<R, Either<E, B>> f) {
        return f.apply(value);
    }

    @Override
    public E error() {
        throw new UnsupportedOperationException("Either is `Right` case. There is no error here");
    }

    @Override
    public R value() {
        return value;
    }

    @Override
    public boolean isRight() {
        return true;
    }

    @Override
    public boolean isLeft() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("Right: %s", value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Right) {
            return value == ((Right<?, ?>) obj).value;
        } else {
            return false;
        }
    }
}
