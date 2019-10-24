package util;

import clojure.lang.IFn;

import java.util.function.Supplier;

public class Debug {

    public static <A> void println(final A arg) {
        System.out.println(arg);
    }

    public static <A> Tuple<A, Double> time (final Supplier<A> f) {
        final long t0 = System.nanoTime();
        final A res = f.get();
        final long t1 = System.nanoTime();
        final double delta = (t1 - t0) * 0.000001;
        return new Tuple<>(res, delta);
    }

    public static Supplier<Object> supply(IFn f) {
        return f::invoke;
    }
}
