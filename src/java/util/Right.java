package util;

import java.util.function.Function;

public class Right<E, R> implements Either<E, R> {
    public final R value;

    public Right(final R value) {
        this.value = value;
    }

    @Override
    public <B> B fold(Function<R, B> f, Function<E, B> g) {
        return f.apply(value);
    }

    @Override
    public <B> Either<E, B> map(Function<R, B> f) {
        return new Right<>(f.apply(value));
    }

    @Override
    public <B> Either<E, B> flatMap(Function<R, Either<E, B>> f) {
        return f.apply(value);
    }
}
