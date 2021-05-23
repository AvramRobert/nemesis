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

    Attempt<O> apply(final I value);
}
