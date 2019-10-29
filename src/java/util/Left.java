package util;

import java.util.function.Function;

public class Left<E, R> implements Either<E, R> {
    public final E error;

    public Left(final E error) {
        this.error = error;
    }

    @Override
    public <B> B fold(Function<R, B> f, Function<E, B> g) {
        return g.apply(error);
    }

    @Override
    public <B> Either<E, B> map(Function<R, B> f) {
        return (Left<E, B>) this;
    }

    @Override
    public <B> Either<E, B> flatMap(Function<R, Either<E, B>> f) {
        return (Left<E, B>) this;
    }
}
