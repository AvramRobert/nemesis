package util;

import java.util.function.Function;

public interface Either<E, R> {
    <B> B fold (final Function<R, B> f, final Function<E, B> g);
    <B> Either<E, B> map (final Function<R, B> f);
    <B> Either<E, B> flatMap(final Function<R, Either<E, B>> f);
}
