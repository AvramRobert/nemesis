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

    static <A, B, C, Er> Either<Er, C> combine2(final Either<Er, A> e1,
                                                final Either<Er, B> e2,
                                                final Function2<A, B, C> f) {
        return e1.flatMap(a -> e2.map(b -> f.apply(a, b)));
    }

    static <A, B, C, D, Er> Either<Er, D> combine3(final Either<Er, A> e1,
                                                   final Either<Er, B> e2,
                                                   final Either<Er, C> e3,
                                                   final Function3<A, B, C, D> f) {
        return e1.flatMap(a ->
                e2.flatMap(b ->
                        e3.map(c -> f.apply(a, b, c))));
    }

    // FIXME: These have to be fixed
    static <A, B, C, D, E, Er> Either<Er, E> combine4(final Either<Er, A> e1,
                                                      final Either<Er, B> e2,
                                                      final Either<Er, C> e3,
                                                      final Either<Er, D> e4,
                                                      final Function4<A, B, C, D> f) {
        return e1.flatMap(a ->
                e2.flatMap(b ->
                        e3.flatMap(c ->
                                e4.map(d -> f.apply(a, b, c, d)))));
    }

    static <A, B, C, D, E, F, Er> Either<Er, F> combine5(final Either<Er, A> e1,
                                                         final Either<Er, B> e2,
                                                         final Either<Er, C> e3,
                                                         final Either<Er, D> e4,
                                                         final Either<Er, E> e5,
                                                         final Function5<A, B, C, D, E> f) {
        return e1.flatMap(a ->
                e2.flatMap(b ->
                        e3.flatMap(c ->
                                e4.flatMap(d ->
                                        e5.map(e -> f.apply(a, b, c, d, e))))));
    }
}
