package json.coerce;

import static util.Functions.*;

import json.data.Json;
import json.data.JsonTransform;
import util.Either;

public class Converter {
    public static <A, B> Convert<Json, B> combine(
      Function1<JsonTransform, Either<String, A>> f0,
      Function1<A, B> comb) {
        return json -> {
            JsonTransform blob = json.transform();
            return f0.apply(blob)
              .map(a -> comb.apply(a));
        };
    }

    public static <A, B, C> Convert<Json, C> combine(
      Function1<JsonTransform, Either<String, A>> f0,
      Function1<JsonTransform, Either<String, B>> f1,
      Function2<A, B, C> comb) {
        return json -> {
            JsonTransform blob = json.transform();
            return f0.apply(blob)
              .flatMap(a -> f1.apply(blob)
                .map(b -> comb.apply(a, b)));
        };
    }

    public static <A, B, C, D> Convert<Json, D> combine(
      Function1<JsonTransform, Either<String, A>> f0,
      Function1<JsonTransform, Either<String, B>> f1,
      Function1<JsonTransform, Either<String, C>> f2,
      Function3<A, B, C, D> comb) {
        return json -> {
            JsonTransform blob = json.transform();
            return f0.apply(blob)
              .flatMap(a -> f1.apply(blob)
                .flatMap(b -> f2.apply(blob)
                  .map(c -> comb.apply(a, b, c))));
        };
    }

    public static <A, B, C, D, E> Convert<Json, E> combine(
      Function1<JsonTransform, Either<String, A>> f0,
      Function1<JsonTransform, Either<String, B>> f1,
      Function1<JsonTransform, Either<String, C>> f2,
      Function1<JsonTransform, Either<String, D>> f3,
      Function4<A, B, C, D, E> comb) {
        return json -> {
            JsonTransform blob = json.transform();
            return f0.apply(blob)
              .flatMap(a -> f1.apply(blob)
                .flatMap(b -> f2.apply(blob)
                  .flatMap(c -> f3.apply(blob)
                    .map(d -> comb.apply(a, b, c, d)))));
        };
    }

    public static <A, B, C, D, E, F> Convert<Json, F> combine(
      Function1<JsonTransform, Either<String, A>> f0,
      Function1<JsonTransform, Either<String, B>> f1,
      Function1<JsonTransform, Either<String, C>> f2,
      Function1<JsonTransform, Either<String, D>> f3,
      Function1<JsonTransform, Either<String, E>> f4,
      Function5<A, B, C, D, E, F> comb) {
        return json -> {
            JsonTransform blob = json.transform();
            return f0.apply(blob)
              .flatMap(a -> f1.apply(blob)
                .flatMap(b -> f2.apply(blob)
                  .flatMap(c -> f3.apply(blob)
                    .flatMap(d -> f4.apply(blob)
                      .map(e -> comb.apply(a, b, c, d, e))))));
        };
    }

    public static <A, B, C, D, E, F, G> Convert<Json, G> combine(
      Function1<JsonTransform, Either<String, A>> f0,
      Function1<JsonTransform, Either<String, B>> f1,
      Function1<JsonTransform, Either<String, C>> f2,
      Function1<JsonTransform, Either<String, D>> f3,
      Function1<JsonTransform, Either<String, E>> f4,
      Function1<JsonTransform, Either<String, F>> f5,
      Function6<A, B, C, D, E, F, G> comb) {
        return json -> {
            JsonTransform blob = json.transform();
            return f0.apply(blob)
              .flatMap(a -> f1.apply(blob)
                .flatMap(b -> f2.apply(blob)
                  .flatMap(c -> f3.apply(blob)
                    .flatMap(d -> f4.apply(blob)
                      .flatMap(e -> f5.apply(blob)
                        .map(f -> comb.apply(a, b, c, d, e, f)))))));
        };
    }

    public static <A, B, C, D, E, F, G, H> Convert<Json, H> combine(
      Function1<JsonTransform, Either<String, A>> f0,
      Function1<JsonTransform, Either<String, B>> f1,
      Function1<JsonTransform, Either<String, C>> f2,
      Function1<JsonTransform, Either<String, D>> f3,
      Function1<JsonTransform, Either<String, E>> f4,
      Function1<JsonTransform, Either<String, F>> f5,
      Function1<JsonTransform, Either<String, G>> f6,
      Function7<A, B, C, D, E, F, G, H> comb) {
        return json -> {
            JsonTransform blob = json.transform();
            return f0.apply(blob)
              .flatMap(a -> f1.apply(blob)
                .flatMap(b -> f2.apply(blob)
                  .flatMap(c -> f3.apply(blob)
                    .flatMap(d -> f4.apply(blob)
                      .flatMap(e -> f5.apply(blob)
                        .flatMap(f -> f6.apply(blob)
                          .map(g -> comb.apply(a, b, c, d, e, f, g))))))));
        };
    }

    public static <A, B, C, D, E, F, G, H, I> Convert<Json, I> combine(
      Function1<JsonTransform, Either<String, A>> f0,
      Function1<JsonTransform, Either<String, B>> f1,
      Function1<JsonTransform, Either<String, C>> f2,
      Function1<JsonTransform, Either<String, D>> f3,
      Function1<JsonTransform, Either<String, E>> f4,
      Function1<JsonTransform, Either<String, F>> f5,
      Function1<JsonTransform, Either<String, G>> f6,
      Function1<JsonTransform, Either<String, H>> f7,
      Function8<A, B, C, D, E, F, G, H, I> comb) {
        return json -> {
            JsonTransform blob = json.transform();
            return f0.apply(blob)
              .flatMap(a -> f1.apply(blob)
                .flatMap(b -> f2.apply(blob)
                  .flatMap(c -> f3.apply(blob)
                    .flatMap(d -> f4.apply(blob)
                      .flatMap(e -> f5.apply(blob)
                        .flatMap(f -> f6.apply(blob)
                          .flatMap(g -> f7.apply(blob)
                            .map(h -> comb.apply(a, b, c, d, e, f, g, h)))))))));
        };
    }

    public static <A, B, C, D, E, F, G, H, I, J> Convert<Json, J> combine(
      Function1<JsonTransform, Either<String, A>> f0,
      Function1<JsonTransform, Either<String, B>> f1,
      Function1<JsonTransform, Either<String, C>> f2,
      Function1<JsonTransform, Either<String, D>> f3,
      Function1<JsonTransform, Either<String, E>> f4,
      Function1<JsonTransform, Either<String, F>> f5,
      Function1<JsonTransform, Either<String, G>> f6,
      Function1<JsonTransform, Either<String, H>> f7,
      Function1<JsonTransform, Either<String, I>> f8,
      Function9<A, B, C, D, E, F, G, H, I, J> comb) {
        return json -> {
            JsonTransform blob = json.transform();
            return f0.apply(blob)
              .flatMap(a -> f1.apply(blob)
                .flatMap(b -> f2.apply(blob)
                  .flatMap(c -> f3.apply(blob)
                    .flatMap(d -> f4.apply(blob)
                      .flatMap(e -> f5.apply(blob)
                        .flatMap(f -> f6.apply(blob)
                          .flatMap(g -> f7.apply(blob)
                            .flatMap(h -> f8.apply(blob)
                              .map(i -> comb.apply(a, b, c, d, e, f, g, h, i))))))))));
        };
    }

    public static <A, B, C, D, E, F, G, H, I, J, K> Convert<Json, K> combine(
      Function1<JsonTransform, Either<String, A>> f0,
      Function1<JsonTransform, Either<String, B>> f1,
      Function1<JsonTransform, Either<String, C>> f2,
      Function1<JsonTransform, Either<String, D>> f3,
      Function1<JsonTransform, Either<String, E>> f4,
      Function1<JsonTransform, Either<String, F>> f5,
      Function1<JsonTransform, Either<String, G>> f6,
      Function1<JsonTransform, Either<String, H>> f7,
      Function1<JsonTransform, Either<String, I>> f8,
      Function1<JsonTransform, Either<String, J>> f9,
      Function10<A, B, C, D, E, F, G, H, I, J, K> comb) {
        return json -> {
            JsonTransform blob = json.transform();
            return f0.apply(blob)
              .flatMap(a -> f1.apply(blob)
                .flatMap(b -> f2.apply(blob)
                  .flatMap(c -> f3.apply(blob)
                    .flatMap(d -> f4.apply(blob)
                      .flatMap(e -> f5.apply(blob)
                        .flatMap(f -> f6.apply(blob)
                          .flatMap(g -> f7.apply(blob)
                            .flatMap(h -> f8.apply(blob)
                              .flatMap(i -> f9.apply(blob)
                                .map(j -> comb.apply(a, b, c, d, e, f, g, h, i, j)))))))))));
        };
    }

    public static <A, B, C, D, E, F, G, H, I, J, K, L> Convert<Json, L> combine(
      Function1<JsonTransform, Either<String, A>> f0,
      Function1<JsonTransform, Either<String, B>> f1,
      Function1<JsonTransform, Either<String, C>> f2,
      Function1<JsonTransform, Either<String, D>> f3,
      Function1<JsonTransform, Either<String, E>> f4,
      Function1<JsonTransform, Either<String, F>> f5,
      Function1<JsonTransform, Either<String, G>> f6,
      Function1<JsonTransform, Either<String, H>> f7,
      Function1<JsonTransform, Either<String, I>> f8,
      Function1<JsonTransform, Either<String, J>> f9,
      Function1<JsonTransform, Either<String, K>> f10,
      Function11<A, B, C, D, E, F, G, H, I, J, K, L> comb) {
        return json -> {
            JsonTransform blob = json.transform();
            return f0.apply(blob)
              .flatMap(a -> f1.apply(blob)
                .flatMap(b -> f2.apply(blob)
                  .flatMap(c -> f3.apply(blob)
                    .flatMap(d -> f4.apply(blob)
                      .flatMap(e -> f5.apply(blob)
                        .flatMap(f -> f6.apply(blob)
                          .flatMap(g -> f7.apply(blob)
                            .flatMap(h -> f8.apply(blob)
                              .flatMap(i -> f9.apply(blob)
                                .flatMap(j -> f10.apply(blob)
                                  .map(k -> comb.apply(a, b, c, d, e, f, g, h, i, j, k))))))))))));
        };
    }

    public static <A, B, C, D, E, F, G, H, I, J, K, L, M> Convert<Json, M> combine(
      Function1<JsonTransform, Either<String, A>> f0,
      Function1<JsonTransform, Either<String, B>> f1,
      Function1<JsonTransform, Either<String, C>> f2,
      Function1<JsonTransform, Either<String, D>> f3,
      Function1<JsonTransform, Either<String, E>> f4,
      Function1<JsonTransform, Either<String, F>> f5,
      Function1<JsonTransform, Either<String, G>> f6,
      Function1<JsonTransform, Either<String, H>> f7,
      Function1<JsonTransform, Either<String, I>> f8,
      Function1<JsonTransform, Either<String, J>> f9,
      Function1<JsonTransform, Either<String, K>> f10,
      Function1<JsonTransform, Either<String, L>> f11,
      Function12<A, B, C, D, E, F, G, H, I, J, K, L, M> comb) {
        return json -> {
            JsonTransform blob = json.transform();
            return f0.apply(blob)
              .flatMap(a -> f1.apply(blob)
                .flatMap(b -> f2.apply(blob)
                  .flatMap(c -> f3.apply(blob)
                    .flatMap(d -> f4.apply(blob)
                      .flatMap(e -> f5.apply(blob)
                        .flatMap(f -> f6.apply(blob)
                          .flatMap(g -> f7.apply(blob)
                            .flatMap(h -> f8.apply(blob)
                              .flatMap(i -> f9.apply(blob)
                                .flatMap(j -> f10.apply(blob)
                                  .flatMap(k -> f11.apply(blob)
                                    .map(l -> comb.apply(a, b, c, d, e, f, g, h, i, j, k, l)))))))))))));
        };
    }

    public static <A, B, C, D, E, F, G, H, I, J, K, L, M, N> Convert<Json, N> combine(
      Function1<JsonTransform, Either<String, A>> f0,
      Function1<JsonTransform, Either<String, B>> f1,
      Function1<JsonTransform, Either<String, C>> f2,
      Function1<JsonTransform, Either<String, D>> f3,
      Function1<JsonTransform, Either<String, E>> f4,
      Function1<JsonTransform, Either<String, F>> f5,
      Function1<JsonTransform, Either<String, G>> f6,
      Function1<JsonTransform, Either<String, H>> f7,
      Function1<JsonTransform, Either<String, I>> f8,
      Function1<JsonTransform, Either<String, J>> f9,
      Function1<JsonTransform, Either<String, K>> f10,
      Function1<JsonTransform, Either<String, L>> f11,
      Function1<JsonTransform, Either<String, M>> f12,
      Function13<A, B, C, D, E, F, G, H, I, J, K, L, M, N> comb) {
        return json -> {
            JsonTransform blob = json.transform();
            return f0.apply(blob)
              .flatMap(a -> f1.apply(blob)
                .flatMap(b -> f2.apply(blob)
                  .flatMap(c -> f3.apply(blob)
                    .flatMap(d -> f4.apply(blob)
                      .flatMap(e -> f5.apply(blob)
                        .flatMap(f -> f6.apply(blob)
                          .flatMap(g -> f7.apply(blob)
                            .flatMap(h -> f8.apply(blob)
                              .flatMap(i -> f9.apply(blob)
                                .flatMap(j -> f10.apply(blob)
                                  .flatMap(k -> f11.apply(blob)
                                    .flatMap(l -> f12.apply(blob)
                                      .map(m -> comb.apply(a, b, c, d, e, f, g, h, i, j, k, l, m))))))))))))));
        };
    }

    public static <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O> Convert<Json, O> combine(
      Function1<JsonTransform, Either<String, A>> f0,
      Function1<JsonTransform, Either<String, B>> f1,
      Function1<JsonTransform, Either<String, C>> f2,
      Function1<JsonTransform, Either<String, D>> f3,
      Function1<JsonTransform, Either<String, E>> f4,
      Function1<JsonTransform, Either<String, F>> f5,
      Function1<JsonTransform, Either<String, G>> f6,
      Function1<JsonTransform, Either<String, H>> f7,
      Function1<JsonTransform, Either<String, I>> f8,
      Function1<JsonTransform, Either<String, J>> f9,
      Function1<JsonTransform, Either<String, K>> f10,
      Function1<JsonTransform, Either<String, L>> f11,
      Function1<JsonTransform, Either<String, M>> f12,
      Function1<JsonTransform, Either<String, N>> f13,
      Function14<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O> comb) {
        return json -> {
            JsonTransform blob = json.transform();
            return f0.apply(blob)
              .flatMap(a -> f1.apply(blob)
                .flatMap(b -> f2.apply(blob)
                  .flatMap(c -> f3.apply(blob)
                    .flatMap(d -> f4.apply(blob)
                      .flatMap(e -> f5.apply(blob)
                        .flatMap(f -> f6.apply(blob)
                          .flatMap(g -> f7.apply(blob)
                            .flatMap(h -> f8.apply(blob)
                              .flatMap(i -> f9.apply(blob)
                                .flatMap(j -> f10.apply(blob)
                                  .flatMap(k -> f11.apply(blob)
                                    .flatMap(l -> f12.apply(blob)
                                      .flatMap(m -> f13.apply(blob)
                                        .map(n -> comb.apply(a, b, c, d, e, f, g, h, i, j, k, l, m, n)))))))))))))));
        };
    }

    public static <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P> Convert<Json, P> combine(
      Function1<JsonTransform, Either<String, A>> f0,
      Function1<JsonTransform, Either<String, B>> f1,
      Function1<JsonTransform, Either<String, C>> f2,
      Function1<JsonTransform, Either<String, D>> f3,
      Function1<JsonTransform, Either<String, E>> f4,
      Function1<JsonTransform, Either<String, F>> f5,
      Function1<JsonTransform, Either<String, G>> f6,
      Function1<JsonTransform, Either<String, H>> f7,
      Function1<JsonTransform, Either<String, I>> f8,
      Function1<JsonTransform, Either<String, J>> f9,
      Function1<JsonTransform, Either<String, K>> f10,
      Function1<JsonTransform, Either<String, L>> f11,
      Function1<JsonTransform, Either<String, M>> f12,
      Function1<JsonTransform, Either<String, N>> f13,
      Function1<JsonTransform, Either<String, O>> f14,
      Function15<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P> comb) {
        return json -> {
            JsonTransform blob = json.transform();
            return f0.apply(blob)
              .flatMap(a -> f1.apply(blob)
                .flatMap(b -> f2.apply(blob)
                  .flatMap(c -> f3.apply(blob)
                    .flatMap(d -> f4.apply(blob)
                      .flatMap(e -> f5.apply(blob)
                        .flatMap(f -> f6.apply(blob)
                          .flatMap(g -> f7.apply(blob)
                            .flatMap(h -> f8.apply(blob)
                              .flatMap(i -> f9.apply(blob)
                                .flatMap(j -> f10.apply(blob)
                                  .flatMap(k -> f11.apply(blob)
                                    .flatMap(l -> f12.apply(blob)
                                      .flatMap(m -> f13.apply(blob)
                                        .flatMap(n -> f14.apply(blob)
                                          .map(o -> comb.apply(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o))))))))))))))));
        };
    }

    public static <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q> Convert<Json, Q> combine(
      Function1<JsonTransform, Either<String, A>> f0,
      Function1<JsonTransform, Either<String, B>> f1,
      Function1<JsonTransform, Either<String, C>> f2,
      Function1<JsonTransform, Either<String, D>> f3,
      Function1<JsonTransform, Either<String, E>> f4,
      Function1<JsonTransform, Either<String, F>> f5,
      Function1<JsonTransform, Either<String, G>> f6,
      Function1<JsonTransform, Either<String, H>> f7,
      Function1<JsonTransform, Either<String, I>> f8,
      Function1<JsonTransform, Either<String, J>> f9,
      Function1<JsonTransform, Either<String, K>> f10,
      Function1<JsonTransform, Either<String, L>> f11,
      Function1<JsonTransform, Either<String, M>> f12,
      Function1<JsonTransform, Either<String, N>> f13,
      Function1<JsonTransform, Either<String, O>> f14,
      Function1<JsonTransform, Either<String, P>> f15,
      Function16<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q> comb) {
        return json -> {
            JsonTransform blob = json.transform();
            return f0.apply(blob)
              .flatMap(a -> f1.apply(blob)
                .flatMap(b -> f2.apply(blob)
                  .flatMap(c -> f3.apply(blob)
                    .flatMap(d -> f4.apply(blob)
                      .flatMap(e -> f5.apply(blob)
                        .flatMap(f -> f6.apply(blob)
                          .flatMap(g -> f7.apply(blob)
                            .flatMap(h -> f8.apply(blob)
                              .flatMap(i -> f9.apply(blob)
                                .flatMap(j -> f10.apply(blob)
                                  .flatMap(k -> f11.apply(blob)
                                    .flatMap(l -> f12.apply(blob)
                                      .flatMap(m -> f13.apply(blob)
                                        .flatMap(n -> f14.apply(blob)
                                          .flatMap(o -> f15.apply(blob)
                                            .map(p -> comb.apply(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p)))))))))))))))));
        };
    }

    public static <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R> Convert<Json, R> combine(
      Function1<JsonTransform, Either<String, A>> f0,
      Function1<JsonTransform, Either<String, B>> f1,
      Function1<JsonTransform, Either<String, C>> f2,
      Function1<JsonTransform, Either<String, D>> f3,
      Function1<JsonTransform, Either<String, E>> f4,
      Function1<JsonTransform, Either<String, F>> f5,
      Function1<JsonTransform, Either<String, G>> f6,
      Function1<JsonTransform, Either<String, H>> f7,
      Function1<JsonTransform, Either<String, I>> f8,
      Function1<JsonTransform, Either<String, J>> f9,
      Function1<JsonTransform, Either<String, K>> f10,
      Function1<JsonTransform, Either<String, L>> f11,
      Function1<JsonTransform, Either<String, M>> f12,
      Function1<JsonTransform, Either<String, N>> f13,
      Function1<JsonTransform, Either<String, O>> f14,
      Function1<JsonTransform, Either<String, P>> f15,
      Function1<JsonTransform, Either<String, Q>> f16,
      Function17<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R> comb) {
        return json -> {
            JsonTransform blob = json.transform();
            return f0.apply(blob)
              .flatMap(a -> f1.apply(blob)
                .flatMap(b -> f2.apply(blob)
                  .flatMap(c -> f3.apply(blob)
                    .flatMap(d -> f4.apply(blob)
                      .flatMap(e -> f5.apply(blob)
                        .flatMap(f -> f6.apply(blob)
                          .flatMap(g -> f7.apply(blob)
                            .flatMap(h -> f8.apply(blob)
                              .flatMap(i -> f9.apply(blob)
                                .flatMap(j -> f10.apply(blob)
                                  .flatMap(k -> f11.apply(blob)
                                    .flatMap(l -> f12.apply(blob)
                                      .flatMap(m -> f13.apply(blob)
                                        .flatMap(n -> f14.apply(blob)
                                          .flatMap(o -> f15.apply(blob)
                                            .flatMap(p -> f16.apply(blob)
                                              .map(q -> comb.apply(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q))))))))))))))))));
        };
    }

    public static <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S> Convert<Json, S> combine(
      Function1<JsonTransform, Either<String, A>> f0,
      Function1<JsonTransform, Either<String, B>> f1,
      Function1<JsonTransform, Either<String, C>> f2,
      Function1<JsonTransform, Either<String, D>> f3,
      Function1<JsonTransform, Either<String, E>> f4,
      Function1<JsonTransform, Either<String, F>> f5,
      Function1<JsonTransform, Either<String, G>> f6,
      Function1<JsonTransform, Either<String, H>> f7,
      Function1<JsonTransform, Either<String, I>> f8,
      Function1<JsonTransform, Either<String, J>> f9,
      Function1<JsonTransform, Either<String, K>> f10,
      Function1<JsonTransform, Either<String, L>> f11,
      Function1<JsonTransform, Either<String, M>> f12,
      Function1<JsonTransform, Either<String, N>> f13,
      Function1<JsonTransform, Either<String, O>> f14,
      Function1<JsonTransform, Either<String, P>> f15,
      Function1<JsonTransform, Either<String, Q>> f16,
      Function1<JsonTransform, Either<String, R>> f17,
      Function18<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S> comb) {
        return json -> {
            JsonTransform blob = json.transform();
            return f0.apply(blob)
              .flatMap(a -> f1.apply(blob)
                .flatMap(b -> f2.apply(blob)
                  .flatMap(c -> f3.apply(blob)
                    .flatMap(d -> f4.apply(blob)
                      .flatMap(e -> f5.apply(blob)
                        .flatMap(f -> f6.apply(blob)
                          .flatMap(g -> f7.apply(blob)
                            .flatMap(h -> f8.apply(blob)
                              .flatMap(i -> f9.apply(blob)
                                .flatMap(j -> f10.apply(blob)
                                  .flatMap(k -> f11.apply(blob)
                                    .flatMap(l -> f12.apply(blob)
                                      .flatMap(m -> f13.apply(blob)
                                        .flatMap(n -> f14.apply(blob)
                                          .flatMap(o -> f15.apply(blob)
                                            .flatMap(p -> f16.apply(blob)
                                              .flatMap(q -> f17.apply(blob)
                                                .map(r -> comb.apply(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r)))))))))))))))))));
        };
    }

    public static <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T> Convert<Json, T> combine(
      Function1<JsonTransform, Either<String, A>> f0,
      Function1<JsonTransform, Either<String, B>> f1,
      Function1<JsonTransform, Either<String, C>> f2,
      Function1<JsonTransform, Either<String, D>> f3,
      Function1<JsonTransform, Either<String, E>> f4,
      Function1<JsonTransform, Either<String, F>> f5,
      Function1<JsonTransform, Either<String, G>> f6,
      Function1<JsonTransform, Either<String, H>> f7,
      Function1<JsonTransform, Either<String, I>> f8,
      Function1<JsonTransform, Either<String, J>> f9,
      Function1<JsonTransform, Either<String, K>> f10,
      Function1<JsonTransform, Either<String, L>> f11,
      Function1<JsonTransform, Either<String, M>> f12,
      Function1<JsonTransform, Either<String, N>> f13,
      Function1<JsonTransform, Either<String, O>> f14,
      Function1<JsonTransform, Either<String, P>> f15,
      Function1<JsonTransform, Either<String, Q>> f16,
      Function1<JsonTransform, Either<String, R>> f17,
      Function1<JsonTransform, Either<String, S>> f18,
      Function19<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T> comb) {
        return json -> {
            JsonTransform blob = json.transform();
            return f0.apply(blob)
              .flatMap(a -> f1.apply(blob)
                .flatMap(b -> f2.apply(blob)
                  .flatMap(c -> f3.apply(blob)
                    .flatMap(d -> f4.apply(blob)
                      .flatMap(e -> f5.apply(blob)
                        .flatMap(f -> f6.apply(blob)
                          .flatMap(g -> f7.apply(blob)
                            .flatMap(h -> f8.apply(blob)
                              .flatMap(i -> f9.apply(blob)
                                .flatMap(j -> f10.apply(blob)
                                  .flatMap(k -> f11.apply(blob)
                                    .flatMap(l -> f12.apply(blob)
                                      .flatMap(m -> f13.apply(blob)
                                        .flatMap(n -> f14.apply(blob)
                                          .flatMap(o -> f15.apply(blob)
                                            .flatMap(p -> f16.apply(blob)
                                              .flatMap(q -> f17.apply(blob)
                                                .flatMap(r -> f18.apply(blob)
                                                  .map(s -> comb.apply(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s))))))))))))))))))));
        };
    }

    public static <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U> Convert<Json, U> combine(
      Function1<JsonTransform, Either<String, A>> f0,
      Function1<JsonTransform, Either<String, B>> f1,
      Function1<JsonTransform, Either<String, C>> f2,
      Function1<JsonTransform, Either<String, D>> f3,
      Function1<JsonTransform, Either<String, E>> f4,
      Function1<JsonTransform, Either<String, F>> f5,
      Function1<JsonTransform, Either<String, G>> f6,
      Function1<JsonTransform, Either<String, H>> f7,
      Function1<JsonTransform, Either<String, I>> f8,
      Function1<JsonTransform, Either<String, J>> f9,
      Function1<JsonTransform, Either<String, K>> f10,
      Function1<JsonTransform, Either<String, L>> f11,
      Function1<JsonTransform, Either<String, M>> f12,
      Function1<JsonTransform, Either<String, N>> f13,
      Function1<JsonTransform, Either<String, O>> f14,
      Function1<JsonTransform, Either<String, P>> f15,
      Function1<JsonTransform, Either<String, Q>> f16,
      Function1<JsonTransform, Either<String, R>> f17,
      Function1<JsonTransform, Either<String, S>> f18,
      Function1<JsonTransform, Either<String, T>> f19,
      Function20<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U> comb) {
        return json -> {
            JsonTransform blob = json.transform();
            return f0.apply(blob)
              .flatMap(a -> f1.apply(blob)
                .flatMap(b -> f2.apply(blob)
                  .flatMap(c -> f3.apply(blob)
                    .flatMap(d -> f4.apply(blob)
                      .flatMap(e -> f5.apply(blob)
                        .flatMap(f -> f6.apply(blob)
                          .flatMap(g -> f7.apply(blob)
                            .flatMap(h -> f8.apply(blob)
                              .flatMap(i -> f9.apply(blob)
                                .flatMap(j -> f10.apply(blob)
                                  .flatMap(k -> f11.apply(blob)
                                    .flatMap(l -> f12.apply(blob)
                                      .flatMap(m -> f13.apply(blob)
                                        .flatMap(n -> f14.apply(blob)
                                          .flatMap(o -> f15.apply(blob)
                                            .flatMap(p -> f16.apply(blob)
                                              .flatMap(q -> f17.apply(blob)
                                                .flatMap(r -> f18.apply(blob)
                                                  .flatMap(s -> f19.apply(blob)
                                                    .map(t -> comb.apply(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t)))))))))))))))))))));
        };
    }

    public static <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V> Convert<Json, V> combine(
      Function1<JsonTransform, Either<String, A>> f0,
      Function1<JsonTransform, Either<String, B>> f1,
      Function1<JsonTransform, Either<String, C>> f2,
      Function1<JsonTransform, Either<String, D>> f3,
      Function1<JsonTransform, Either<String, E>> f4,
      Function1<JsonTransform, Either<String, F>> f5,
      Function1<JsonTransform, Either<String, G>> f6,
      Function1<JsonTransform, Either<String, H>> f7,
      Function1<JsonTransform, Either<String, I>> f8,
      Function1<JsonTransform, Either<String, J>> f9,
      Function1<JsonTransform, Either<String, K>> f10,
      Function1<JsonTransform, Either<String, L>> f11,
      Function1<JsonTransform, Either<String, M>> f12,
      Function1<JsonTransform, Either<String, N>> f13,
      Function1<JsonTransform, Either<String, O>> f14,
      Function1<JsonTransform, Either<String, P>> f15,
      Function1<JsonTransform, Either<String, Q>> f16,
      Function1<JsonTransform, Either<String, R>> f17,
      Function1<JsonTransform, Either<String, S>> f18,
      Function1<JsonTransform, Either<String, T>> f19,
      Function1<JsonTransform, Either<String, U>> f20,
      Function21<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V> comb) {
        return json -> {
            JsonTransform blob = json.transform();
            return f0.apply(blob)
              .flatMap(a -> f1.apply(blob)
                .flatMap(b -> f2.apply(blob)
                  .flatMap(c -> f3.apply(blob)
                    .flatMap(d -> f4.apply(blob)
                      .flatMap(e -> f5.apply(blob)
                        .flatMap(f -> f6.apply(blob)
                          .flatMap(g -> f7.apply(blob)
                            .flatMap(h -> f8.apply(blob)
                              .flatMap(i -> f9.apply(blob)
                                .flatMap(j -> f10.apply(blob)
                                  .flatMap(k -> f11.apply(blob)
                                    .flatMap(l -> f12.apply(blob)
                                      .flatMap(m -> f13.apply(blob)
                                        .flatMap(n -> f14.apply(blob)
                                          .flatMap(o -> f15.apply(blob)
                                            .flatMap(p -> f16.apply(blob)
                                              .flatMap(q -> f17.apply(blob)
                                                .flatMap(r -> f18.apply(blob)
                                                  .flatMap(s -> f19.apply(blob)
                                                    .flatMap(t -> f20.apply(blob)
                                                      .map(u -> comb.apply(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u))))))))))))))))))))));
        };
    }
}