package util;

import io.lacuna.bifurcan.List;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

public class Functions {
    public static <A> Optional<List<A>> sequence (final java.util.List<Optional<A>> vals) {
        final ArrayList<A> coerced = new ArrayList<>();
        boolean broken = false;
        for (Optional<A> v : vals) {
            if (v.isPresent()) coerced.add(v.get());
            else broken = true;
            if (broken) break;
        }
        if (broken) return Optional.empty();
        else return Optional.of(List.from(coerced));
    }

    public static <A, B> java.util.List<B> map (final Function<A, B> f, final java.util.List<A> as) {
        final ArrayList<B> bs = new ArrayList<>();
        for (A a : as) {
            bs.add(f.apply(a));
        }
        return bs;
    }
}
