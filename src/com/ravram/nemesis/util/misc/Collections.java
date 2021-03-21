package com.ravram.nemesis.util.misc;

import com.ravram.nemesis.util.error.Either;
import io.lacuna.bifurcan.List;
import io.lacuna.bifurcan.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

public class Collections {

    public static <E, K1, K2, A, B> Either<E, Map<K2, B>> traverseMap(final java.util.Map<K1, A> kvs, final Function<K1, Either<E, K2>> kf, final Function<A, Either<E, B>> vf) {
        final HashMap<K2, B> map = new HashMap<>();
        for (java.util.Map.Entry<K1, A> entry : kvs.entrySet()) {
            final Either<E, B> res = kf
              .apply(entry.getKey())
              .flatMap(k -> vf.apply(entry.getValue()).map(v -> {
                  map.put(k, v);
                  return v;
              }));
            if (res.isLeft()) return Either.left(res.error());
        }
        return Either.right(Map.from(map));
    }

    public static <E, A, B> Either<E, java.util.List<B>> traverseIterator(final Iterable<A> as, final Function<A, Either<E, B>> f) {
        final ArrayList<B> bs = new ArrayList<>();
        for (A a : as) {
            final Either<E, B> b = f.apply(a);
            if (b.isRight()) bs.add(b.value());
            else return Either.left(b.error());
        }
        return Either.right(bs);
    }

    public static <E, A, B> Either<E, java.util.List<B>> traverseArray(final A[] as, final Function<A, Either<E, B>> f) {
        final ArrayList<B> bs = new ArrayList<>();
        for (A a : as) {
            final Either<E, B> b = f.apply(a);
            if (b.isRight()) bs.add(b.value());
            else return Either.left(b.error());
        }
        return Either.right(bs);
    }

    public static <E, A, B> Either<E, java.util.List<B>> traverseSet(final java.util.Set<A> as, final Function<A, Either<E, B>> f) {
        return traverseIterator(as, f);
    }

    public static <E, A, B> Either<E, java.util.List<B>> traverseList(final java.util.List<A> as, final Function<A, Either<E, B>> f) {
        return traverseIterator(as, f);
    }

    public static <E, A, B> Either<E, List<B>> bTraverseList(final java.util.List<A> as, final Function<A, Either<E, B>> f) {
        return traverseIterator(as, f).map(List::from);
    }

    public static <E, A, B> Either<E, List<B>> bTraverseSet(final java.util.Set<A> as, final Function<A, Either<E, B>> f) {
        return traverseIterator(as, f).map(List::from);
    }
}