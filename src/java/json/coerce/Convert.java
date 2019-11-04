package json.coerce;

import util.Either;
import util.Left;
import util.Right;

import java.util.function.Function;

@FunctionalInterface
public interface Convert <A, B> {
    default Function<A, Either<String, B>> function() {
     return this::convert;
    }

    default <C> Convert<A, C> compose (final Convert<B, C> f) {
        return a -> convert(a).fold(f::convert, Left::new);
    }

    default <C> Convert<A, C> compose (final Function<B, C> f) {
        return a -> convert(a).fold(b -> new Right<>(f.apply(b)), Left::new);
    }

    Either<String, B> convert(final A value);
}
