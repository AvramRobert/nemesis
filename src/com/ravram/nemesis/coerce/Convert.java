package com.ravram.nemesis.coerce;

import com.ravram.nemesis.Attempt;
import com.ravram.nemesis.util.error.Failure;
import com.ravram.nemesis.util.function.Functions.Function1;

@FunctionalInterface
public interface Convert <I, O> {

    default <P> Convert<P, O> compose (final Convert<P, I> g) {
        return p -> g.apply(p).fold(this::apply, Failure::new);
    }

    default <P> Convert<P, O> compose (final Function1<P, I> g) {
        return p -> apply(g.apply(p));
    }

    default <B> Convert<I, B> map(final Function1<O, B> f) {
        return i -> apply(i).map(f);
    }

    default <B> Convert<I, B> flatMap(final Function1<O, Convert<I, B>> f) {
        return i -> apply(i).flatMap(o -> f.apply(o).apply(i));
    }

    Attempt<O> apply(final I value);
}
