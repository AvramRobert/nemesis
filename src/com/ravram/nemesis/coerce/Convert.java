package com.ravram.nemesis.coerce;

import com.ravram.nemesis.util.error.Either;
import com.ravram.nemesis.util.error.Left;
import com.ravram.nemesis.util.error.Right;
import com.ravram.nemesis.util.function.Functions.Function1;

@FunctionalInterface
public interface Convert <E, A, B> {
    default <C> Convert<E, A, C> compose (final Convert<E, B, C> f) {
        return a -> apply(a).fold(f::apply, Left::new);
    }

    default <C> Convert<E, A, C> compose (final Function1<B, C> f) {
        return a -> apply(a).fold(b -> new Right<>(f.apply(b)), Left::new);
    }

    Either<E, B> apply(final A value);
}
