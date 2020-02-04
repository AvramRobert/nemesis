package util;

import util.Functions.Function1;

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
    public <B> Either<E, B> map(Function1<R, B> f) {
        return (Left<E, B>) this;
    }

    @Override
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
        return null;
    }

    @Override
    public boolean isLeft() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("Left: %s", error);
    }
}
