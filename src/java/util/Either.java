package util;

import json.ext.Function2;
import json.ext.Function3;
import json.ext.Function4;
import json.ext.Function5;

import java.util.function.Function;

public interface Either<E, R> {

    static <ER, C> Either<ER, C> right(final C r) {
        return new Right<>(r);
    }

    static <ER, C> Either<ER, C> left(final ER e) {
        return new Left<>(e);
    }

    <B> B fold(final Function<R, B> f, final Function<E, B> g);

    <B> Either<E, B> map(final Function<R, B> f);

    <B> Either<E, B> flatMap(final Function<R, Either<E, B>> f);

    R value();

    E error();

    boolean isRight();

    boolean isLeft();
}
