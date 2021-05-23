package com.ravram.nemesis;

import com.ravram.nemesis.util.error.AttemptTrait;
import com.ravram.nemesis.util.error.Failure;
import com.ravram.nemesis.util.error.Success;

public interface Attempt<A> extends AttemptTrait<A> {
    static <B> Attempt<B> success(final B value) { return new Success<>(value); }
    static <B> Attempt<B> failure(final String err, final Object... args) { return new Failure<>(String.format(err, args)); }
}
