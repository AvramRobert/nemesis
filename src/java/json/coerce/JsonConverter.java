package json.coerce;

import static util.Functions.*;

import json.data.Json;
import json.data.JsonT;

public class JsonConverter {
    public static <T0, T1> Convert<Json, T1> combine(
      Convert<JsonT, T0> f0,
      Function1<T0, T1> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .map(t0 -> comb.apply(t0));
        };
    }

    public static <T0, T1, T2> Convert<Json, T2> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Function2<T0, T1, T2> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .map(t1 -> comb.apply(t0, t1)));
        };
    }

    public static <T0, T1, T2, T3> Convert<Json, T3> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Function3<T0, T1, T2, T3> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .map(t2 -> comb.apply(t0, t1, t2))));
        };
    }

    public static <T0, T1, T2, T3, T4> Convert<Json, T4> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Function4<T0, T1, T2, T3, T4> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .map(t3 -> comb.apply(t0, t1, t2, t3)))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5> Convert<Json, T5> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Function5<T0, T1, T2, T3, T4, T5> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .map(t4 -> comb.apply(t0, t1, t2, t3, t4))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6> Convert<Json, T6> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Function6<T0, T1, T2, T3, T4, T5, T6> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .map(t5 -> comb.apply(t0, t1, t2, t3, t4, t5)))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7> Convert<Json, T7> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Function7<T0, T1, T2, T3, T4, T5, T6, T7> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .map(t6 -> comb.apply(t0, t1, t2, t3, t4, t5, t6))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8> Convert<Json, T8> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Function8<T0, T1, T2, T3, T4, T5, T6, T7, T8> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .map(t7 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7)))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9> Convert<Json, T9> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Function9<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .map(t8 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> Convert<Json, T10> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Function10<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .map(t9 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9)))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> Convert<Json, T11> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Function11<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .map(t10 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10))))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Convert<Json, T12> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Convert<JsonT, T11> f11,
      Function12<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .flatMap(t10 -> f11.convert(blob)
                                    .map(t11 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11)))))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> Convert<Json, T13> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Convert<JsonT, T11> f11,
      Convert<JsonT, T12> f12,
      Function13<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .flatMap(t10 -> f11.convert(blob)
                                    .flatMap(t11 -> f12.convert(blob)
                                      .map(t12 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12))))))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> Convert<Json, T14> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Convert<JsonT, T11> f11,
      Convert<JsonT, T12> f12,
      Convert<JsonT, T13> f13,
      Function14<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .flatMap(t10 -> f11.convert(blob)
                                    .flatMap(t11 -> f12.convert(blob)
                                      .flatMap(t12 -> f13.convert(blob)
                                        .map(t13 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13)))))))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> Convert<Json, T15> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Convert<JsonT, T11> f11,
      Convert<JsonT, T12> f12,
      Convert<JsonT, T13> f13,
      Convert<JsonT, T14> f14,
      Function15<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .flatMap(t10 -> f11.convert(blob)
                                    .flatMap(t11 -> f12.convert(blob)
                                      .flatMap(t12 -> f13.convert(blob)
                                        .flatMap(t13 -> f14.convert(blob)
                                          .map(t14 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14))))))))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> Convert<Json, T16> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Convert<JsonT, T11> f11,
      Convert<JsonT, T12> f12,
      Convert<JsonT, T13> f13,
      Convert<JsonT, T14> f14,
      Convert<JsonT, T15> f15,
      Function16<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .flatMap(t10 -> f11.convert(blob)
                                    .flatMap(t11 -> f12.convert(blob)
                                      .flatMap(t12 -> f13.convert(blob)
                                        .flatMap(t13 -> f14.convert(blob)
                                          .flatMap(t14 -> f15.convert(blob)
                                            .map(t15 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15)))))))))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> Convert<Json, T17> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Convert<JsonT, T11> f11,
      Convert<JsonT, T12> f12,
      Convert<JsonT, T13> f13,
      Convert<JsonT, T14> f14,
      Convert<JsonT, T15> f15,
      Convert<JsonT, T16> f16,
      Function17<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .flatMap(t10 -> f11.convert(blob)
                                    .flatMap(t11 -> f12.convert(blob)
                                      .flatMap(t12 -> f13.convert(blob)
                                        .flatMap(t13 -> f14.convert(blob)
                                          .flatMap(t14 -> f15.convert(blob)
                                            .flatMap(t15 -> f16.convert(blob)
                                              .map(t16 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16))))))))))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> Convert<Json, T18> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Convert<JsonT, T11> f11,
      Convert<JsonT, T12> f12,
      Convert<JsonT, T13> f13,
      Convert<JsonT, T14> f14,
      Convert<JsonT, T15> f15,
      Convert<JsonT, T16> f16,
      Convert<JsonT, T17> f17,
      Function18<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .flatMap(t10 -> f11.convert(blob)
                                    .flatMap(t11 -> f12.convert(blob)
                                      .flatMap(t12 -> f13.convert(blob)
                                        .flatMap(t13 -> f14.convert(blob)
                                          .flatMap(t14 -> f15.convert(blob)
                                            .flatMap(t15 -> f16.convert(blob)
                                              .flatMap(t16 -> f17.convert(blob)
                                                .map(t17 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17)))))))))))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> Convert<Json, T19> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Convert<JsonT, T11> f11,
      Convert<JsonT, T12> f12,
      Convert<JsonT, T13> f13,
      Convert<JsonT, T14> f14,
      Convert<JsonT, T15> f15,
      Convert<JsonT, T16> f16,
      Convert<JsonT, T17> f17,
      Convert<JsonT, T18> f18,
      Function19<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .flatMap(t10 -> f11.convert(blob)
                                    .flatMap(t11 -> f12.convert(blob)
                                      .flatMap(t12 -> f13.convert(blob)
                                        .flatMap(t13 -> f14.convert(blob)
                                          .flatMap(t14 -> f15.convert(blob)
                                            .flatMap(t15 -> f16.convert(blob)
                                              .flatMap(t16 -> f17.convert(blob)
                                                .flatMap(t17 -> f18.convert(blob)
                                                  .map(t18 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18))))))))))))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> Convert<Json, T20> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Convert<JsonT, T11> f11,
      Convert<JsonT, T12> f12,
      Convert<JsonT, T13> f13,
      Convert<JsonT, T14> f14,
      Convert<JsonT, T15> f15,
      Convert<JsonT, T16> f16,
      Convert<JsonT, T17> f17,
      Convert<JsonT, T18> f18,
      Convert<JsonT, T19> f19,
      Function20<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .flatMap(t10 -> f11.convert(blob)
                                    .flatMap(t11 -> f12.convert(blob)
                                      .flatMap(t12 -> f13.convert(blob)
                                        .flatMap(t13 -> f14.convert(blob)
                                          .flatMap(t14 -> f15.convert(blob)
                                            .flatMap(t15 -> f16.convert(blob)
                                              .flatMap(t16 -> f17.convert(blob)
                                                .flatMap(t17 -> f18.convert(blob)
                                                  .flatMap(t18 -> f19.convert(blob)
                                                    .map(t19 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19)))))))))))))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> Convert<Json, T21> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Convert<JsonT, T11> f11,
      Convert<JsonT, T12> f12,
      Convert<JsonT, T13> f13,
      Convert<JsonT, T14> f14,
      Convert<JsonT, T15> f15,
      Convert<JsonT, T16> f16,
      Convert<JsonT, T17> f17,
      Convert<JsonT, T18> f18,
      Convert<JsonT, T19> f19,
      Convert<JsonT, T20> f20,
      Function21<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .flatMap(t10 -> f11.convert(blob)
                                    .flatMap(t11 -> f12.convert(blob)
                                      .flatMap(t12 -> f13.convert(blob)
                                        .flatMap(t13 -> f14.convert(blob)
                                          .flatMap(t14 -> f15.convert(blob)
                                            .flatMap(t15 -> f16.convert(blob)
                                              .flatMap(t16 -> f17.convert(blob)
                                                .flatMap(t17 -> f18.convert(blob)
                                                  .flatMap(t18 -> f19.convert(blob)
                                                    .flatMap(t19 -> f20.convert(blob)
                                                      .map(t20 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20))))))))))))))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> Convert<Json, T22> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Convert<JsonT, T11> f11,
      Convert<JsonT, T12> f12,
      Convert<JsonT, T13> f13,
      Convert<JsonT, T14> f14,
      Convert<JsonT, T15> f15,
      Convert<JsonT, T16> f16,
      Convert<JsonT, T17> f17,
      Convert<JsonT, T18> f18,
      Convert<JsonT, T19> f19,
      Convert<JsonT, T20> f20,
      Convert<JsonT, T21> f21,
      Function22<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .flatMap(t10 -> f11.convert(blob)
                                    .flatMap(t11 -> f12.convert(blob)
                                      .flatMap(t12 -> f13.convert(blob)
                                        .flatMap(t13 -> f14.convert(blob)
                                          .flatMap(t14 -> f15.convert(blob)
                                            .flatMap(t15 -> f16.convert(blob)
                                              .flatMap(t16 -> f17.convert(blob)
                                                .flatMap(t17 -> f18.convert(blob)
                                                  .flatMap(t18 -> f19.convert(blob)
                                                    .flatMap(t19 -> f20.convert(blob)
                                                      .flatMap(t20 -> f21.convert(blob)
                                                        .map(t21 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21)))))))))))))))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23> Convert<Json, T23> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Convert<JsonT, T11> f11,
      Convert<JsonT, T12> f12,
      Convert<JsonT, T13> f13,
      Convert<JsonT, T14> f14,
      Convert<JsonT, T15> f15,
      Convert<JsonT, T16> f16,
      Convert<JsonT, T17> f17,
      Convert<JsonT, T18> f18,
      Convert<JsonT, T19> f19,
      Convert<JsonT, T20> f20,
      Convert<JsonT, T21> f21,
      Convert<JsonT, T22> f22,
      Function23<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .flatMap(t10 -> f11.convert(blob)
                                    .flatMap(t11 -> f12.convert(blob)
                                      .flatMap(t12 -> f13.convert(blob)
                                        .flatMap(t13 -> f14.convert(blob)
                                          .flatMap(t14 -> f15.convert(blob)
                                            .flatMap(t15 -> f16.convert(blob)
                                              .flatMap(t16 -> f17.convert(blob)
                                                .flatMap(t17 -> f18.convert(blob)
                                                  .flatMap(t18 -> f19.convert(blob)
                                                    .flatMap(t19 -> f20.convert(blob)
                                                      .flatMap(t20 -> f21.convert(blob)
                                                        .flatMap(t21 -> f22.convert(blob)
                                                          .map(t22 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22))))))))))))))))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24> Convert<Json, T24> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Convert<JsonT, T11> f11,
      Convert<JsonT, T12> f12,
      Convert<JsonT, T13> f13,
      Convert<JsonT, T14> f14,
      Convert<JsonT, T15> f15,
      Convert<JsonT, T16> f16,
      Convert<JsonT, T17> f17,
      Convert<JsonT, T18> f18,
      Convert<JsonT, T19> f19,
      Convert<JsonT, T20> f20,
      Convert<JsonT, T21> f21,
      Convert<JsonT, T22> f22,
      Convert<JsonT, T23> f23,
      Function24<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .flatMap(t10 -> f11.convert(blob)
                                    .flatMap(t11 -> f12.convert(blob)
                                      .flatMap(t12 -> f13.convert(blob)
                                        .flatMap(t13 -> f14.convert(blob)
                                          .flatMap(t14 -> f15.convert(blob)
                                            .flatMap(t15 -> f16.convert(blob)
                                              .flatMap(t16 -> f17.convert(blob)
                                                .flatMap(t17 -> f18.convert(blob)
                                                  .flatMap(t18 -> f19.convert(blob)
                                                    .flatMap(t19 -> f20.convert(blob)
                                                      .flatMap(t20 -> f21.convert(blob)
                                                        .flatMap(t21 -> f22.convert(blob)
                                                          .flatMap(t22 -> f23.convert(blob)
                                                            .map(t23 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23)))))))))))))))))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25> Convert<Json, T25> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Convert<JsonT, T11> f11,
      Convert<JsonT, T12> f12,
      Convert<JsonT, T13> f13,
      Convert<JsonT, T14> f14,
      Convert<JsonT, T15> f15,
      Convert<JsonT, T16> f16,
      Convert<JsonT, T17> f17,
      Convert<JsonT, T18> f18,
      Convert<JsonT, T19> f19,
      Convert<JsonT, T20> f20,
      Convert<JsonT, T21> f21,
      Convert<JsonT, T22> f22,
      Convert<JsonT, T23> f23,
      Convert<JsonT, T24> f24,
      Function25<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .flatMap(t10 -> f11.convert(blob)
                                    .flatMap(t11 -> f12.convert(blob)
                                      .flatMap(t12 -> f13.convert(blob)
                                        .flatMap(t13 -> f14.convert(blob)
                                          .flatMap(t14 -> f15.convert(blob)
                                            .flatMap(t15 -> f16.convert(blob)
                                              .flatMap(t16 -> f17.convert(blob)
                                                .flatMap(t17 -> f18.convert(blob)
                                                  .flatMap(t18 -> f19.convert(blob)
                                                    .flatMap(t19 -> f20.convert(blob)
                                                      .flatMap(t20 -> f21.convert(blob)
                                                        .flatMap(t21 -> f22.convert(blob)
                                                          .flatMap(t22 -> f23.convert(blob)
                                                            .flatMap(t23 -> f24.convert(blob)
                                                              .map(t24 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24))))))))))))))))))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26> Convert<Json, T26> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Convert<JsonT, T11> f11,
      Convert<JsonT, T12> f12,
      Convert<JsonT, T13> f13,
      Convert<JsonT, T14> f14,
      Convert<JsonT, T15> f15,
      Convert<JsonT, T16> f16,
      Convert<JsonT, T17> f17,
      Convert<JsonT, T18> f18,
      Convert<JsonT, T19> f19,
      Convert<JsonT, T20> f20,
      Convert<JsonT, T21> f21,
      Convert<JsonT, T22> f22,
      Convert<JsonT, T23> f23,
      Convert<JsonT, T24> f24,
      Convert<JsonT, T25> f25,
      Function26<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .flatMap(t10 -> f11.convert(blob)
                                    .flatMap(t11 -> f12.convert(blob)
                                      .flatMap(t12 -> f13.convert(blob)
                                        .flatMap(t13 -> f14.convert(blob)
                                          .flatMap(t14 -> f15.convert(blob)
                                            .flatMap(t15 -> f16.convert(blob)
                                              .flatMap(t16 -> f17.convert(blob)
                                                .flatMap(t17 -> f18.convert(blob)
                                                  .flatMap(t18 -> f19.convert(blob)
                                                    .flatMap(t19 -> f20.convert(blob)
                                                      .flatMap(t20 -> f21.convert(blob)
                                                        .flatMap(t21 -> f22.convert(blob)
                                                          .flatMap(t22 -> f23.convert(blob)
                                                            .flatMap(t23 -> f24.convert(blob)
                                                              .flatMap(t24 -> f25.convert(blob)
                                                                .map(t25 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25)))))))))))))))))))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27> Convert<Json, T27> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Convert<JsonT, T11> f11,
      Convert<JsonT, T12> f12,
      Convert<JsonT, T13> f13,
      Convert<JsonT, T14> f14,
      Convert<JsonT, T15> f15,
      Convert<JsonT, T16> f16,
      Convert<JsonT, T17> f17,
      Convert<JsonT, T18> f18,
      Convert<JsonT, T19> f19,
      Convert<JsonT, T20> f20,
      Convert<JsonT, T21> f21,
      Convert<JsonT, T22> f22,
      Convert<JsonT, T23> f23,
      Convert<JsonT, T24> f24,
      Convert<JsonT, T25> f25,
      Convert<JsonT, T26> f26,
      Function27<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .flatMap(t10 -> f11.convert(blob)
                                    .flatMap(t11 -> f12.convert(blob)
                                      .flatMap(t12 -> f13.convert(blob)
                                        .flatMap(t13 -> f14.convert(blob)
                                          .flatMap(t14 -> f15.convert(blob)
                                            .flatMap(t15 -> f16.convert(blob)
                                              .flatMap(t16 -> f17.convert(blob)
                                                .flatMap(t17 -> f18.convert(blob)
                                                  .flatMap(t18 -> f19.convert(blob)
                                                    .flatMap(t19 -> f20.convert(blob)
                                                      .flatMap(t20 -> f21.convert(blob)
                                                        .flatMap(t21 -> f22.convert(blob)
                                                          .flatMap(t22 -> f23.convert(blob)
                                                            .flatMap(t23 -> f24.convert(blob)
                                                              .flatMap(t24 -> f25.convert(blob)
                                                                .flatMap(t25 -> f26.convert(blob)
                                                                  .map(t26 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26))))))))))))))))))))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28> Convert<Json, T28> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Convert<JsonT, T11> f11,
      Convert<JsonT, T12> f12,
      Convert<JsonT, T13> f13,
      Convert<JsonT, T14> f14,
      Convert<JsonT, T15> f15,
      Convert<JsonT, T16> f16,
      Convert<JsonT, T17> f17,
      Convert<JsonT, T18> f18,
      Convert<JsonT, T19> f19,
      Convert<JsonT, T20> f20,
      Convert<JsonT, T21> f21,
      Convert<JsonT, T22> f22,
      Convert<JsonT, T23> f23,
      Convert<JsonT, T24> f24,
      Convert<JsonT, T25> f25,
      Convert<JsonT, T26> f26,
      Convert<JsonT, T27> f27,
      Function28<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .flatMap(t10 -> f11.convert(blob)
                                    .flatMap(t11 -> f12.convert(blob)
                                      .flatMap(t12 -> f13.convert(blob)
                                        .flatMap(t13 -> f14.convert(blob)
                                          .flatMap(t14 -> f15.convert(blob)
                                            .flatMap(t15 -> f16.convert(blob)
                                              .flatMap(t16 -> f17.convert(blob)
                                                .flatMap(t17 -> f18.convert(blob)
                                                  .flatMap(t18 -> f19.convert(blob)
                                                    .flatMap(t19 -> f20.convert(blob)
                                                      .flatMap(t20 -> f21.convert(blob)
                                                        .flatMap(t21 -> f22.convert(blob)
                                                          .flatMap(t22 -> f23.convert(blob)
                                                            .flatMap(t23 -> f24.convert(blob)
                                                              .flatMap(t24 -> f25.convert(blob)
                                                                .flatMap(t25 -> f26.convert(blob)
                                                                  .flatMap(t26 -> f27.convert(blob)
                                                                    .map(t27 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27)))))))))))))))))))))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29> Convert<Json, T29> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Convert<JsonT, T11> f11,
      Convert<JsonT, T12> f12,
      Convert<JsonT, T13> f13,
      Convert<JsonT, T14> f14,
      Convert<JsonT, T15> f15,
      Convert<JsonT, T16> f16,
      Convert<JsonT, T17> f17,
      Convert<JsonT, T18> f18,
      Convert<JsonT, T19> f19,
      Convert<JsonT, T20> f20,
      Convert<JsonT, T21> f21,
      Convert<JsonT, T22> f22,
      Convert<JsonT, T23> f23,
      Convert<JsonT, T24> f24,
      Convert<JsonT, T25> f25,
      Convert<JsonT, T26> f26,
      Convert<JsonT, T27> f27,
      Convert<JsonT, T28> f28,
      Function29<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .flatMap(t10 -> f11.convert(blob)
                                    .flatMap(t11 -> f12.convert(blob)
                                      .flatMap(t12 -> f13.convert(blob)
                                        .flatMap(t13 -> f14.convert(blob)
                                          .flatMap(t14 -> f15.convert(blob)
                                            .flatMap(t15 -> f16.convert(blob)
                                              .flatMap(t16 -> f17.convert(blob)
                                                .flatMap(t17 -> f18.convert(blob)
                                                  .flatMap(t18 -> f19.convert(blob)
                                                    .flatMap(t19 -> f20.convert(blob)
                                                      .flatMap(t20 -> f21.convert(blob)
                                                        .flatMap(t21 -> f22.convert(blob)
                                                          .flatMap(t22 -> f23.convert(blob)
                                                            .flatMap(t23 -> f24.convert(blob)
                                                              .flatMap(t24 -> f25.convert(blob)
                                                                .flatMap(t25 -> f26.convert(blob)
                                                                  .flatMap(t26 -> f27.convert(blob)
                                                                    .flatMap(t27 -> f28.convert(blob)
                                                                      .map(t28 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28))))))))))))))))))))))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30> Convert<Json, T30> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Convert<JsonT, T11> f11,
      Convert<JsonT, T12> f12,
      Convert<JsonT, T13> f13,
      Convert<JsonT, T14> f14,
      Convert<JsonT, T15> f15,
      Convert<JsonT, T16> f16,
      Convert<JsonT, T17> f17,
      Convert<JsonT, T18> f18,
      Convert<JsonT, T19> f19,
      Convert<JsonT, T20> f20,
      Convert<JsonT, T21> f21,
      Convert<JsonT, T22> f22,
      Convert<JsonT, T23> f23,
      Convert<JsonT, T24> f24,
      Convert<JsonT, T25> f25,
      Convert<JsonT, T26> f26,
      Convert<JsonT, T27> f27,
      Convert<JsonT, T28> f28,
      Convert<JsonT, T29> f29,
      Function30<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .flatMap(t10 -> f11.convert(blob)
                                    .flatMap(t11 -> f12.convert(blob)
                                      .flatMap(t12 -> f13.convert(blob)
                                        .flatMap(t13 -> f14.convert(blob)
                                          .flatMap(t14 -> f15.convert(blob)
                                            .flatMap(t15 -> f16.convert(blob)
                                              .flatMap(t16 -> f17.convert(blob)
                                                .flatMap(t17 -> f18.convert(blob)
                                                  .flatMap(t18 -> f19.convert(blob)
                                                    .flatMap(t19 -> f20.convert(blob)
                                                      .flatMap(t20 -> f21.convert(blob)
                                                        .flatMap(t21 -> f22.convert(blob)
                                                          .flatMap(t22 -> f23.convert(blob)
                                                            .flatMap(t23 -> f24.convert(blob)
                                                              .flatMap(t24 -> f25.convert(blob)
                                                                .flatMap(t25 -> f26.convert(blob)
                                                                  .flatMap(t26 -> f27.convert(blob)
                                                                    .flatMap(t27 -> f28.convert(blob)
                                                                      .flatMap(t28 -> f29.convert(blob)
                                                                        .map(t29 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29)))))))))))))))))))))))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31> Convert<Json, T31> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Convert<JsonT, T11> f11,
      Convert<JsonT, T12> f12,
      Convert<JsonT, T13> f13,
      Convert<JsonT, T14> f14,
      Convert<JsonT, T15> f15,
      Convert<JsonT, T16> f16,
      Convert<JsonT, T17> f17,
      Convert<JsonT, T18> f18,
      Convert<JsonT, T19> f19,
      Convert<JsonT, T20> f20,
      Convert<JsonT, T21> f21,
      Convert<JsonT, T22> f22,
      Convert<JsonT, T23> f23,
      Convert<JsonT, T24> f24,
      Convert<JsonT, T25> f25,
      Convert<JsonT, T26> f26,
      Convert<JsonT, T27> f27,
      Convert<JsonT, T28> f28,
      Convert<JsonT, T29> f29,
      Convert<JsonT, T30> f30,
      Function31<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .flatMap(t10 -> f11.convert(blob)
                                    .flatMap(t11 -> f12.convert(blob)
                                      .flatMap(t12 -> f13.convert(blob)
                                        .flatMap(t13 -> f14.convert(blob)
                                          .flatMap(t14 -> f15.convert(blob)
                                            .flatMap(t15 -> f16.convert(blob)
                                              .flatMap(t16 -> f17.convert(blob)
                                                .flatMap(t17 -> f18.convert(blob)
                                                  .flatMap(t18 -> f19.convert(blob)
                                                    .flatMap(t19 -> f20.convert(blob)
                                                      .flatMap(t20 -> f21.convert(blob)
                                                        .flatMap(t21 -> f22.convert(blob)
                                                          .flatMap(t22 -> f23.convert(blob)
                                                            .flatMap(t23 -> f24.convert(blob)
                                                              .flatMap(t24 -> f25.convert(blob)
                                                                .flatMap(t25 -> f26.convert(blob)
                                                                  .flatMap(t26 -> f27.convert(blob)
                                                                    .flatMap(t27 -> f28.convert(blob)
                                                                      .flatMap(t28 -> f29.convert(blob)
                                                                        .flatMap(t29 -> f30.convert(blob)
                                                                          .map(t30 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30))))))))))))))))))))))))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32> Convert<Json, T32> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Convert<JsonT, T11> f11,
      Convert<JsonT, T12> f12,
      Convert<JsonT, T13> f13,
      Convert<JsonT, T14> f14,
      Convert<JsonT, T15> f15,
      Convert<JsonT, T16> f16,
      Convert<JsonT, T17> f17,
      Convert<JsonT, T18> f18,
      Convert<JsonT, T19> f19,
      Convert<JsonT, T20> f20,
      Convert<JsonT, T21> f21,
      Convert<JsonT, T22> f22,
      Convert<JsonT, T23> f23,
      Convert<JsonT, T24> f24,
      Convert<JsonT, T25> f25,
      Convert<JsonT, T26> f26,
      Convert<JsonT, T27> f27,
      Convert<JsonT, T28> f28,
      Convert<JsonT, T29> f29,
      Convert<JsonT, T30> f30,
      Convert<JsonT, T31> f31,
      Function32<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .flatMap(t10 -> f11.convert(blob)
                                    .flatMap(t11 -> f12.convert(blob)
                                      .flatMap(t12 -> f13.convert(blob)
                                        .flatMap(t13 -> f14.convert(blob)
                                          .flatMap(t14 -> f15.convert(blob)
                                            .flatMap(t15 -> f16.convert(blob)
                                              .flatMap(t16 -> f17.convert(blob)
                                                .flatMap(t17 -> f18.convert(blob)
                                                  .flatMap(t18 -> f19.convert(blob)
                                                    .flatMap(t19 -> f20.convert(blob)
                                                      .flatMap(t20 -> f21.convert(blob)
                                                        .flatMap(t21 -> f22.convert(blob)
                                                          .flatMap(t22 -> f23.convert(blob)
                                                            .flatMap(t23 -> f24.convert(blob)
                                                              .flatMap(t24 -> f25.convert(blob)
                                                                .flatMap(t25 -> f26.convert(blob)
                                                                  .flatMap(t26 -> f27.convert(blob)
                                                                    .flatMap(t27 -> f28.convert(blob)
                                                                      .flatMap(t28 -> f29.convert(blob)
                                                                        .flatMap(t29 -> f30.convert(blob)
                                                                          .flatMap(t30 -> f31.convert(blob)
                                                                            .map(t31 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31)))))))))))))))))))))))))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33> Convert<Json, T33> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Convert<JsonT, T11> f11,
      Convert<JsonT, T12> f12,
      Convert<JsonT, T13> f13,
      Convert<JsonT, T14> f14,
      Convert<JsonT, T15> f15,
      Convert<JsonT, T16> f16,
      Convert<JsonT, T17> f17,
      Convert<JsonT, T18> f18,
      Convert<JsonT, T19> f19,
      Convert<JsonT, T20> f20,
      Convert<JsonT, T21> f21,
      Convert<JsonT, T22> f22,
      Convert<JsonT, T23> f23,
      Convert<JsonT, T24> f24,
      Convert<JsonT, T25> f25,
      Convert<JsonT, T26> f26,
      Convert<JsonT, T27> f27,
      Convert<JsonT, T28> f28,
      Convert<JsonT, T29> f29,
      Convert<JsonT, T30> f30,
      Convert<JsonT, T31> f31,
      Convert<JsonT, T32> f32,
      Function33<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .flatMap(t10 -> f11.convert(blob)
                                    .flatMap(t11 -> f12.convert(blob)
                                      .flatMap(t12 -> f13.convert(blob)
                                        .flatMap(t13 -> f14.convert(blob)
                                          .flatMap(t14 -> f15.convert(blob)
                                            .flatMap(t15 -> f16.convert(blob)
                                              .flatMap(t16 -> f17.convert(blob)
                                                .flatMap(t17 -> f18.convert(blob)
                                                  .flatMap(t18 -> f19.convert(blob)
                                                    .flatMap(t19 -> f20.convert(blob)
                                                      .flatMap(t20 -> f21.convert(blob)
                                                        .flatMap(t21 -> f22.convert(blob)
                                                          .flatMap(t22 -> f23.convert(blob)
                                                            .flatMap(t23 -> f24.convert(blob)
                                                              .flatMap(t24 -> f25.convert(blob)
                                                                .flatMap(t25 -> f26.convert(blob)
                                                                  .flatMap(t26 -> f27.convert(blob)
                                                                    .flatMap(t27 -> f28.convert(blob)
                                                                      .flatMap(t28 -> f29.convert(blob)
                                                                        .flatMap(t29 -> f30.convert(blob)
                                                                          .flatMap(t30 -> f31.convert(blob)
                                                                            .flatMap(t31 -> f32.convert(blob)
                                                                              .map(t32 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32))))))))))))))))))))))))))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34> Convert<Json, T34> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Convert<JsonT, T11> f11,
      Convert<JsonT, T12> f12,
      Convert<JsonT, T13> f13,
      Convert<JsonT, T14> f14,
      Convert<JsonT, T15> f15,
      Convert<JsonT, T16> f16,
      Convert<JsonT, T17> f17,
      Convert<JsonT, T18> f18,
      Convert<JsonT, T19> f19,
      Convert<JsonT, T20> f20,
      Convert<JsonT, T21> f21,
      Convert<JsonT, T22> f22,
      Convert<JsonT, T23> f23,
      Convert<JsonT, T24> f24,
      Convert<JsonT, T25> f25,
      Convert<JsonT, T26> f26,
      Convert<JsonT, T27> f27,
      Convert<JsonT, T28> f28,
      Convert<JsonT, T29> f29,
      Convert<JsonT, T30> f30,
      Convert<JsonT, T31> f31,
      Convert<JsonT, T32> f32,
      Convert<JsonT, T33> f33,
      Function34<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .flatMap(t10 -> f11.convert(blob)
                                    .flatMap(t11 -> f12.convert(blob)
                                      .flatMap(t12 -> f13.convert(blob)
                                        .flatMap(t13 -> f14.convert(blob)
                                          .flatMap(t14 -> f15.convert(blob)
                                            .flatMap(t15 -> f16.convert(blob)
                                              .flatMap(t16 -> f17.convert(blob)
                                                .flatMap(t17 -> f18.convert(blob)
                                                  .flatMap(t18 -> f19.convert(blob)
                                                    .flatMap(t19 -> f20.convert(blob)
                                                      .flatMap(t20 -> f21.convert(blob)
                                                        .flatMap(t21 -> f22.convert(blob)
                                                          .flatMap(t22 -> f23.convert(blob)
                                                            .flatMap(t23 -> f24.convert(blob)
                                                              .flatMap(t24 -> f25.convert(blob)
                                                                .flatMap(t25 -> f26.convert(blob)
                                                                  .flatMap(t26 -> f27.convert(blob)
                                                                    .flatMap(t27 -> f28.convert(blob)
                                                                      .flatMap(t28 -> f29.convert(blob)
                                                                        .flatMap(t29 -> f30.convert(blob)
                                                                          .flatMap(t30 -> f31.convert(blob)
                                                                            .flatMap(t31 -> f32.convert(blob)
                                                                              .flatMap(t32 -> f33.convert(blob)
                                                                                .map(t33 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33)))))))))))))))))))))))))))))))))));
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35> Convert<Json, T35> combine(
      Convert<JsonT, T0> f0,
      Convert<JsonT, T1> f1,
      Convert<JsonT, T2> f2,
      Convert<JsonT, T3> f3,
      Convert<JsonT, T4> f4,
      Convert<JsonT, T5> f5,
      Convert<JsonT, T6> f6,
      Convert<JsonT, T7> f7,
      Convert<JsonT, T8> f8,
      Convert<JsonT, T9> f9,
      Convert<JsonT, T10> f10,
      Convert<JsonT, T11> f11,
      Convert<JsonT, T12> f12,
      Convert<JsonT, T13> f13,
      Convert<JsonT, T14> f14,
      Convert<JsonT, T15> f15,
      Convert<JsonT, T16> f16,
      Convert<JsonT, T17> f17,
      Convert<JsonT, T18> f18,
      Convert<JsonT, T19> f19,
      Convert<JsonT, T20> f20,
      Convert<JsonT, T21> f21,
      Convert<JsonT, T22> f22,
      Convert<JsonT, T23> f23,
      Convert<JsonT, T24> f24,
      Convert<JsonT, T25> f25,
      Convert<JsonT, T26> f26,
      Convert<JsonT, T27> f27,
      Convert<JsonT, T28> f28,
      Convert<JsonT, T29> f29,
      Convert<JsonT, T30> f30,
      Convert<JsonT, T31> f31,
      Convert<JsonT, T32> f32,
      Convert<JsonT, T33> f33,
      Convert<JsonT, T34> f34,
      Function35<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35> comb) {
        return json -> {
            JsonT blob = json.transform();
            return f0.convert(blob)
              .flatMap(t0 -> f1.convert(blob)
                .flatMap(t1 -> f2.convert(blob)
                  .flatMap(t2 -> f3.convert(blob)
                    .flatMap(t3 -> f4.convert(blob)
                      .flatMap(t4 -> f5.convert(blob)
                        .flatMap(t5 -> f6.convert(blob)
                          .flatMap(t6 -> f7.convert(blob)
                            .flatMap(t7 -> f8.convert(blob)
                              .flatMap(t8 -> f9.convert(blob)
                                .flatMap(t9 -> f10.convert(blob)
                                  .flatMap(t10 -> f11.convert(blob)
                                    .flatMap(t11 -> f12.convert(blob)
                                      .flatMap(t12 -> f13.convert(blob)
                                        .flatMap(t13 -> f14.convert(blob)
                                          .flatMap(t14 -> f15.convert(blob)
                                            .flatMap(t15 -> f16.convert(blob)
                                              .flatMap(t16 -> f17.convert(blob)
                                                .flatMap(t17 -> f18.convert(blob)
                                                  .flatMap(t18 -> f19.convert(blob)
                                                    .flatMap(t19 -> f20.convert(blob)
                                                      .flatMap(t20 -> f21.convert(blob)
                                                        .flatMap(t21 -> f22.convert(blob)
                                                          .flatMap(t22 -> f23.convert(blob)
                                                            .flatMap(t23 -> f24.convert(blob)
                                                              .flatMap(t24 -> f25.convert(blob)
                                                                .flatMap(t25 -> f26.convert(blob)
                                                                  .flatMap(t26 -> f27.convert(blob)
                                                                    .flatMap(t27 -> f28.convert(blob)
                                                                      .flatMap(t28 -> f29.convert(blob)
                                                                        .flatMap(t29 -> f30.convert(blob)
                                                                          .flatMap(t30 -> f31.convert(blob)
                                                                            .flatMap(t31 -> f32.convert(blob)
                                                                              .flatMap(t32 -> f33.convert(blob)
                                                                                .flatMap(t33 -> f34.convert(blob)
                                                                                  .map(t34 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33, t34))))))))))))))))))))))))))))))))))));
        };
    }
}