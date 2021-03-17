package util.error;

import util.function.Consumers.Consumer1;
import util.function.Functions.Function1;

public class Right<E, R> implements Either<E, R> {
    public final R value;

    public Right(final R value) {
        this.value = value;
    }

    @Override
    public void consume(Consumer1<R> f, Consumer1<E> g) {
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
        return null;
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
}
