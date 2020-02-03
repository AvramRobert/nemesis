package util;

import io.lacuna.bifurcan.List;
import io.lacuna.bifurcan.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

public class Colls {

    public static <E, K1, K2, A, B> Either<E, Map<K2, B>> traversem(final java.util.HashMap<K1, A> kvs, final Function<K1, Either<E, K2>> kf, final Function<A, Either<E, B>> vf) {
        final HashMap<K2, B> map = new HashMap<>();
        for (java.util.Map.Entry<K1, A> entry : kvs.entrySet()) {
            final Either<E, B> res = kf
              .apply(entry.getKey())
              .flatMap(k -> {
                  return vf.apply(entry.getValue()).map(v -> {
                      map.put(k, v);
                      return v;
                  });
              });
            if (res.isLeft()) return Either.left(res.error());
        }
        return Either.right(Map.from(map));
    }

    public static <E, A, B> Either<E, java.util.List<B>> traversel(final java.util.List<A> as, final Function<A, Either<E, B>> f) {
        final ArrayList<B> bs = new ArrayList<>();
        for (A a : as) {
            final Either<E, B> b = f.apply(a);
            if (b.isRight()) bs.add(b.value());
            else return Either.left(b.error());
        }
        return Either.right(bs);
    }

    public static <E, A, B> Either<E, List<B>> traversel_(final java.util.List<A> as, final Function<A, Either<E, B>> f) {
        return traversel(as, f).map(List::from);
    }

    public static <E, A> Either<E, java.util.List<A>> sequencel (final java.util.List<Either<E, A>> as) {
        return traversel(as, x -> x);
    }

    public static <E, A> Either<E, List<A>> sequencel_(final java.util.List<Either<E, A>> as) {
        return sequencel(as).map(List::from);
    }
}
