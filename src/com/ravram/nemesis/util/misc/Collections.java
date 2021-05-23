package com.ravram.nemesis.util.misc;

import com.ravram.nemesis.Attempt;
import com.ravram.nemesis.coerce.Convert;
import io.lacuna.bifurcan.List;
import io.lacuna.bifurcan.Map;

import java.util.ArrayList;
import java.util.HashMap;

public class Collections {

    public static <E, K1, K2, A, B> Attempt<Map<K2, B>> traverseMap(final java.util.Map<K1, A> kvs, final Convert<K1, K2> kf, final Convert<A, B> vf) {
        final HashMap<K2, B> map = new HashMap<>();
        for (java.util.Map.Entry<K1, A> entry : kvs.entrySet()) {
            final Attempt<B> res = kf
              .apply(entry.getKey())
              .flatMap(k -> vf.apply(entry.getValue()).map(v -> {
                  map.put(k, v);
                  return v;
              }));
            if (res.isFailure()) return Attempt.failure(res.error());
        }
        return Attempt.success(Map.from(map));
    }

    public static <E, A, B> Attempt<java.util.List<B>> traverseIterator(final Iterable<A> as, final Convert<A, B> f) {
        final ArrayList<B> bs = new ArrayList<>();
        for (A a : as) {
            final Attempt<B> b = f.apply(a);
            if (b.isSuccess()) bs.add(b.value());
            else return Attempt.failure(b.error());
        }
        return Attempt.success(bs);
    }

    public static <A, B> Attempt<java.util.List<B>> traverseArray(final A[] as, final Convert<A, B> f) {
        final ArrayList<B> bs = new ArrayList<>();
        for (A a : as) {
            final Attempt<B> b = f.apply(a);
            if (b.isSuccess()) bs.add(b.value());
            else return Attempt.failure(b.error());
        }
        return Attempt.success(bs);
    }

    public static <A, B> Attempt<java.util.List<B>> traverseSet(final java.util.Set<A> as, final Convert<A, B> f) {
        return traverseIterator(as, f);
    }

    public static <A, B> Attempt<java.util.List<B>> traverseList(final java.util.List<A> as, final Convert<A, B> f) {
        return traverseIterator(as, f);
    }

    public static <A, B> Attempt<List<B>> bTraverseList(final java.util.List<A> as, final Convert<A, B> f) {
        return traverseIterator(as, f).map(List::from);
    }

    public static <A, B> Attempt<List<B>> bTraverseSet(final java.util.Set<A> as, final Convert<A, B> f) {
        return traverseIterator(as, f).map(List::from);
    }
}