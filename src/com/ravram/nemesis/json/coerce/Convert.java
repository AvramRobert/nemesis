package com.ravram.nemesis.json.coerce;

import com.ravram.nemesis.util.error.Either;
import com.ravram.nemesis.util.error.Left;
import com.ravram.nemesis.util.error.Right;
import com.ravram.nemesis.util.function.Functions;

@FunctionalInterface
public interface Convert <A, B> {
    default Functions.Function1<A, Either<String, B>> function() {
     return this::convert;
    }

    default <C> Convert<A, C> compose (final Convert<B, C> f) {
        return a -> convert(a).fold(f::convert, Left::new);
    }

    default <C> Convert<A, C> compose (final Functions.Function1<B, C> f) {
        return a -> convert(a).fold(b -> new Right<>(f.apply(b)), Left::new);
    }

    Either<String, B> convert(final A value);
}
