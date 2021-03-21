package com.ravram.nemesis.util.error;
import com.ravram.nemesis.util.function.Consumers;
import com.ravram.nemesis.util.function.Functions;

public interface Either<E, R> {

    static <ER, C> Either<ER, C> right(final C r) {
        return new Right<>(r);
    }

    static <ER, C> Either<ER, C> left(final ER e) {
        return new Left<>(e);
    }

    static <A> Either<String, A> left (final String msg, Object... params) {
        return Either.left(String.format(msg, params));
    }

    void consume (final Consumers.Consumer1<R> f, final Consumers.Consumer1<E> g);

    <B> B fold(final Functions.Function1<R, B> f, final Functions.Function1<E, B> g);

    <B> Either<E, B> map(final Functions.Function1<R, B> f);

    <B> Either<E, B> flatMap(final Functions.Function1<R, Either<E, B>> f);

    R value();

    E error();

    boolean isRight();

    boolean isLeft();
}
