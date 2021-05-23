package com.ravram.nemesis.util.error;

import com.ravram.nemesis.Attempt;
import com.ravram.nemesis.util.function.Consumers.Consumer1;
import com.ravram.nemesis.util.function.Functions.Function1;

public interface AttemptTrait<A> {
    default <B> Attempt<B> map(final Function1<A, B> f) {
        return flatMap(a -> Attempt.success(f.apply(a)));
    };

    default <B> Attempt<B> flatMap(final Function1<A, Attempt<B>> f) {
        if (isSuccess()) return f.apply(value());
        else return Attempt.failure(error());
    }

    default <B> B fold(final Function1<A, B> f, final Function1<String, B> e){
        if (isSuccess()) return f.apply(value());
        else return e.apply(error());
    }

    default void consume(final Consumer1<A> f, final Consumer1<String> e) {
        if (isSuccess()) f.apply(value());
        else e.apply(error());
    }

    boolean isSuccess();

    boolean isFailure();

    A value();

    String error();
}
