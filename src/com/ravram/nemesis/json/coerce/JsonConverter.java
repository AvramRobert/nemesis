package com.ravram.nemesis.json.coerce;

import static com.ravram.nemesis.util.function.Functions.*;

import com.ravram.nemesis.util.error.Either;
import com.ravram.nemesis.json.model.JsonT;

public class JsonConverter {
    private final JsonT json;

    public JsonConverter(final JsonT json) {
        this.json = json;
    }

    public <T0, T1> Either<String, T1> with(
            Convert<JsonT, T0> f0,
            Function1<T0, T1> comb) {
        return f0.convert(json)
                .map(t0 -> comb.apply(t0));
    }

    public <T0, T1, T2> Either<String, T2> with(
            Convert<JsonT, T0> f0,
            Convert<JsonT, T1> f1,
            Function2<T0, T1, T2> comb) {
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .map(t1 -> comb.apply(t0, t1)));
    }

    public <T0, T1, T2, T3> Either<String, T3> with(
            Convert<JsonT, T0> f0,
            Convert<JsonT, T1> f1,
            Convert<JsonT, T2> f2,
            Function3<T0, T1, T2, T3> comb) {
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .map(t2 -> comb.apply(t0, t1, t2))));
    }

    public <T0, T1, T2, T3, T4> Either<String, T4> with(
            Convert<JsonT, T0> f0,
            Convert<JsonT, T1> f1,
            Convert<JsonT, T2> f2,
            Convert<JsonT, T3> f3,
            Function4<T0, T1, T2, T3, T4> comb) {
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .map(t3 -> comb.apply(t0, t1, t2, t3)))));
    }

    public <T0, T1, T2, T3, T4, T5> Either<String, T5> with(
            Convert<JsonT, T0> f0,
            Convert<JsonT, T1> f1,
            Convert<JsonT, T2> f2,
            Convert<JsonT, T3> f3,
            Convert<JsonT, T4> f4,
            Function5<T0, T1, T2, T3, T4, T5> comb) {
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .map(t4 -> comb.apply(t0, t1, t2, t3, t4))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6> Either<String, T6> with(
            Convert<JsonT, T0> f0,
            Convert<JsonT, T1> f1,
            Convert<JsonT, T2> f2,
            Convert<JsonT, T3> f3,
            Convert<JsonT, T4> f4,
            Convert<JsonT, T5> f5,
            Function6<T0, T1, T2, T3, T4, T5, T6> comb) {
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .map(t5 -> comb.apply(t0, t1, t2, t3, t4, t5)))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7> Either<String, T7> with(
            Convert<JsonT, T0> f0,
            Convert<JsonT, T1> f1,
            Convert<JsonT, T2> f2,
            Convert<JsonT, T3> f3,
            Convert<JsonT, T4> f4,
            Convert<JsonT, T5> f5,
            Convert<JsonT, T6> f6,
            Function7<T0, T1, T2, T3, T4, T5, T6, T7> comb) {
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .map(t6 -> comb.apply(t0, t1, t2, t3, t4, t5, t6))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8> Either<String, T8> with(
            Convert<JsonT, T0> f0,
            Convert<JsonT, T1> f1,
            Convert<JsonT, T2> f2,
            Convert<JsonT, T3> f3,
            Convert<JsonT, T4> f4,
            Convert<JsonT, T5> f5,
            Convert<JsonT, T6> f6,
            Convert<JsonT, T7> f7,
            Function8<T0, T1, T2, T3, T4, T5, T6, T7, T8> comb) {
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .map(t7 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7)))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9> Either<String, T9> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .map(t8 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> Either<String, T10> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .map(t9 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9)))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> Either<String, T11> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .map(t10 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10))))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Either<String, T12> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .flatMap(t10 -> f11.convert(json)
                                                                                                        .map(t11 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11)))))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> Either<String, T13> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .flatMap(t10 -> f11.convert(json)
                                                                                                        .flatMap(t11 -> f12.convert(json)
                                                                                                                .map(t12 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12))))))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> Either<String, T14> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .flatMap(t10 -> f11.convert(json)
                                                                                                        .flatMap(t11 -> f12.convert(json)
                                                                                                                .flatMap(t12 -> f13.convert(json)
                                                                                                                        .map(t13 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13)))))))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> Either<String, T15> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .flatMap(t10 -> f11.convert(json)
                                                                                                        .flatMap(t11 -> f12.convert(json)
                                                                                                                .flatMap(t12 -> f13.convert(json)
                                                                                                                        .flatMap(t13 -> f14.convert(json)
                                                                                                                                .map(t14 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14))))))))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> Either<String, T16> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .flatMap(t10 -> f11.convert(json)
                                                                                                        .flatMap(t11 -> f12.convert(json)
                                                                                                                .flatMap(t12 -> f13.convert(json)
                                                                                                                        .flatMap(t13 -> f14.convert(json)
                                                                                                                                .flatMap(t14 -> f15.convert(json)
                                                                                                                                        .map(t15 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15)))))))))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> Either<String, T17> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .flatMap(t10 -> f11.convert(json)
                                                                                                        .flatMap(t11 -> f12.convert(json)
                                                                                                                .flatMap(t12 -> f13.convert(json)
                                                                                                                        .flatMap(t13 -> f14.convert(json)
                                                                                                                                .flatMap(t14 -> f15.convert(json)
                                                                                                                                        .flatMap(t15 -> f16.convert(json)
                                                                                                                                                .map(t16 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16))))))))))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> Either<String, T18> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .flatMap(t10 -> f11.convert(json)
                                                                                                        .flatMap(t11 -> f12.convert(json)
                                                                                                                .flatMap(t12 -> f13.convert(json)
                                                                                                                        .flatMap(t13 -> f14.convert(json)
                                                                                                                                .flatMap(t14 -> f15.convert(json)
                                                                                                                                        .flatMap(t15 -> f16.convert(json)
                                                                                                                                                .flatMap(t16 -> f17.convert(json)
                                                                                                                                                        .map(t17 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17)))))))))))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> Either<String, T19> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .flatMap(t10 -> f11.convert(json)
                                                                                                        .flatMap(t11 -> f12.convert(json)
                                                                                                                .flatMap(t12 -> f13.convert(json)
                                                                                                                        .flatMap(t13 -> f14.convert(json)
                                                                                                                                .flatMap(t14 -> f15.convert(json)
                                                                                                                                        .flatMap(t15 -> f16.convert(json)
                                                                                                                                                .flatMap(t16 -> f17.convert(json)
                                                                                                                                                        .flatMap(t17 -> f18.convert(json)
                                                                                                                                                                .map(t18 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18))))))))))))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> Either<String, T20> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .flatMap(t10 -> f11.convert(json)
                                                                                                        .flatMap(t11 -> f12.convert(json)
                                                                                                                .flatMap(t12 -> f13.convert(json)
                                                                                                                        .flatMap(t13 -> f14.convert(json)
                                                                                                                                .flatMap(t14 -> f15.convert(json)
                                                                                                                                        .flatMap(t15 -> f16.convert(json)
                                                                                                                                                .flatMap(t16 -> f17.convert(json)
                                                                                                                                                        .flatMap(t17 -> f18.convert(json)
                                                                                                                                                                .flatMap(t18 -> f19.convert(json)
                                                                                                                                                                        .map(t19 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19)))))))))))))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> Either<String, T21> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .flatMap(t10 -> f11.convert(json)
                                                                                                        .flatMap(t11 -> f12.convert(json)
                                                                                                                .flatMap(t12 -> f13.convert(json)
                                                                                                                        .flatMap(t13 -> f14.convert(json)
                                                                                                                                .flatMap(t14 -> f15.convert(json)
                                                                                                                                        .flatMap(t15 -> f16.convert(json)
                                                                                                                                                .flatMap(t16 -> f17.convert(json)
                                                                                                                                                        .flatMap(t17 -> f18.convert(json)
                                                                                                                                                                .flatMap(t18 -> f19.convert(json)
                                                                                                                                                                        .flatMap(t19 -> f20.convert(json)
                                                                                                                                                                                .map(t20 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20))))))))))))))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> Either<String, T22> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .flatMap(t10 -> f11.convert(json)
                                                                                                        .flatMap(t11 -> f12.convert(json)
                                                                                                                .flatMap(t12 -> f13.convert(json)
                                                                                                                        .flatMap(t13 -> f14.convert(json)
                                                                                                                                .flatMap(t14 -> f15.convert(json)
                                                                                                                                        .flatMap(t15 -> f16.convert(json)
                                                                                                                                                .flatMap(t16 -> f17.convert(json)
                                                                                                                                                        .flatMap(t17 -> f18.convert(json)
                                                                                                                                                                .flatMap(t18 -> f19.convert(json)
                                                                                                                                                                        .flatMap(t19 -> f20.convert(json)
                                                                                                                                                                                .flatMap(t20 -> f21.convert(json)
                                                                                                                                                                                        .map(t21 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21)))))))))))))))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23> Either<String, T23> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .flatMap(t10 -> f11.convert(json)
                                                                                                        .flatMap(t11 -> f12.convert(json)
                                                                                                                .flatMap(t12 -> f13.convert(json)
                                                                                                                        .flatMap(t13 -> f14.convert(json)
                                                                                                                                .flatMap(t14 -> f15.convert(json)
                                                                                                                                        .flatMap(t15 -> f16.convert(json)
                                                                                                                                                .flatMap(t16 -> f17.convert(json)
                                                                                                                                                        .flatMap(t17 -> f18.convert(json)
                                                                                                                                                                .flatMap(t18 -> f19.convert(json)
                                                                                                                                                                        .flatMap(t19 -> f20.convert(json)
                                                                                                                                                                                .flatMap(t20 -> f21.convert(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.convert(json)
                                                                                                                                                                                                .map(t22 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22))))))))))))))))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24> Either<String, T24> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .flatMap(t10 -> f11.convert(json)
                                                                                                        .flatMap(t11 -> f12.convert(json)
                                                                                                                .flatMap(t12 -> f13.convert(json)
                                                                                                                        .flatMap(t13 -> f14.convert(json)
                                                                                                                                .flatMap(t14 -> f15.convert(json)
                                                                                                                                        .flatMap(t15 -> f16.convert(json)
                                                                                                                                                .flatMap(t16 -> f17.convert(json)
                                                                                                                                                        .flatMap(t17 -> f18.convert(json)
                                                                                                                                                                .flatMap(t18 -> f19.convert(json)
                                                                                                                                                                        .flatMap(t19 -> f20.convert(json)
                                                                                                                                                                                .flatMap(t20 -> f21.convert(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.convert(json)
                                                                                                                                                                                                .flatMap(t22 -> f23.convert(json)
                                                                                                                                                                                                        .map(t23 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23)))))))))))))))))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25> Either<String, T25> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .flatMap(t10 -> f11.convert(json)
                                                                                                        .flatMap(t11 -> f12.convert(json)
                                                                                                                .flatMap(t12 -> f13.convert(json)
                                                                                                                        .flatMap(t13 -> f14.convert(json)
                                                                                                                                .flatMap(t14 -> f15.convert(json)
                                                                                                                                        .flatMap(t15 -> f16.convert(json)
                                                                                                                                                .flatMap(t16 -> f17.convert(json)
                                                                                                                                                        .flatMap(t17 -> f18.convert(json)
                                                                                                                                                                .flatMap(t18 -> f19.convert(json)
                                                                                                                                                                        .flatMap(t19 -> f20.convert(json)
                                                                                                                                                                                .flatMap(t20 -> f21.convert(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.convert(json)
                                                                                                                                                                                                .flatMap(t22 -> f23.convert(json)
                                                                                                                                                                                                        .flatMap(t23 -> f24.convert(json)
                                                                                                                                                                                                                .map(t24 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24))))))))))))))))))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26> Either<String, T26> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .flatMap(t10 -> f11.convert(json)
                                                                                                        .flatMap(t11 -> f12.convert(json)
                                                                                                                .flatMap(t12 -> f13.convert(json)
                                                                                                                        .flatMap(t13 -> f14.convert(json)
                                                                                                                                .flatMap(t14 -> f15.convert(json)
                                                                                                                                        .flatMap(t15 -> f16.convert(json)
                                                                                                                                                .flatMap(t16 -> f17.convert(json)
                                                                                                                                                        .flatMap(t17 -> f18.convert(json)
                                                                                                                                                                .flatMap(t18 -> f19.convert(json)
                                                                                                                                                                        .flatMap(t19 -> f20.convert(json)
                                                                                                                                                                                .flatMap(t20 -> f21.convert(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.convert(json)
                                                                                                                                                                                                .flatMap(t22 -> f23.convert(json)
                                                                                                                                                                                                        .flatMap(t23 -> f24.convert(json)
                                                                                                                                                                                                                .flatMap(t24 -> f25.convert(json)
                                                                                                                                                                                                                        .map(t25 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25)))))))))))))))))))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27> Either<String, T27> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .flatMap(t10 -> f11.convert(json)
                                                                                                        .flatMap(t11 -> f12.convert(json)
                                                                                                                .flatMap(t12 -> f13.convert(json)
                                                                                                                        .flatMap(t13 -> f14.convert(json)
                                                                                                                                .flatMap(t14 -> f15.convert(json)
                                                                                                                                        .flatMap(t15 -> f16.convert(json)
                                                                                                                                                .flatMap(t16 -> f17.convert(json)
                                                                                                                                                        .flatMap(t17 -> f18.convert(json)
                                                                                                                                                                .flatMap(t18 -> f19.convert(json)
                                                                                                                                                                        .flatMap(t19 -> f20.convert(json)
                                                                                                                                                                                .flatMap(t20 -> f21.convert(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.convert(json)
                                                                                                                                                                                                .flatMap(t22 -> f23.convert(json)
                                                                                                                                                                                                        .flatMap(t23 -> f24.convert(json)
                                                                                                                                                                                                                .flatMap(t24 -> f25.convert(json)
                                                                                                                                                                                                                        .flatMap(t25 -> f26.convert(json)
                                                                                                                                                                                                                                .map(t26 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26))))))))))))))))))))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28> Either<String, T28> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .flatMap(t10 -> f11.convert(json)
                                                                                                        .flatMap(t11 -> f12.convert(json)
                                                                                                                .flatMap(t12 -> f13.convert(json)
                                                                                                                        .flatMap(t13 -> f14.convert(json)
                                                                                                                                .flatMap(t14 -> f15.convert(json)
                                                                                                                                        .flatMap(t15 -> f16.convert(json)
                                                                                                                                                .flatMap(t16 -> f17.convert(json)
                                                                                                                                                        .flatMap(t17 -> f18.convert(json)
                                                                                                                                                                .flatMap(t18 -> f19.convert(json)
                                                                                                                                                                        .flatMap(t19 -> f20.convert(json)
                                                                                                                                                                                .flatMap(t20 -> f21.convert(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.convert(json)
                                                                                                                                                                                                .flatMap(t22 -> f23.convert(json)
                                                                                                                                                                                                        .flatMap(t23 -> f24.convert(json)
                                                                                                                                                                                                                .flatMap(t24 -> f25.convert(json)
                                                                                                                                                                                                                        .flatMap(t25 -> f26.convert(json)
                                                                                                                                                                                                                                .flatMap(t26 -> f27.convert(json)
                                                                                                                                                                                                                                        .map(t27 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27)))))))))))))))))))))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29> Either<String, T29> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .flatMap(t10 -> f11.convert(json)
                                                                                                        .flatMap(t11 -> f12.convert(json)
                                                                                                                .flatMap(t12 -> f13.convert(json)
                                                                                                                        .flatMap(t13 -> f14.convert(json)
                                                                                                                                .flatMap(t14 -> f15.convert(json)
                                                                                                                                        .flatMap(t15 -> f16.convert(json)
                                                                                                                                                .flatMap(t16 -> f17.convert(json)
                                                                                                                                                        .flatMap(t17 -> f18.convert(json)
                                                                                                                                                                .flatMap(t18 -> f19.convert(json)
                                                                                                                                                                        .flatMap(t19 -> f20.convert(json)
                                                                                                                                                                                .flatMap(t20 -> f21.convert(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.convert(json)
                                                                                                                                                                                                .flatMap(t22 -> f23.convert(json)
                                                                                                                                                                                                        .flatMap(t23 -> f24.convert(json)
                                                                                                                                                                                                                .flatMap(t24 -> f25.convert(json)
                                                                                                                                                                                                                        .flatMap(t25 -> f26.convert(json)
                                                                                                                                                                                                                                .flatMap(t26 -> f27.convert(json)
                                                                                                                                                                                                                                        .flatMap(t27 -> f28.convert(json)
                                                                                                                                                                                                                                                .map(t28 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28))))))))))))))))))))))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30> Either<String, T30> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .flatMap(t10 -> f11.convert(json)
                                                                                                        .flatMap(t11 -> f12.convert(json)
                                                                                                                .flatMap(t12 -> f13.convert(json)
                                                                                                                        .flatMap(t13 -> f14.convert(json)
                                                                                                                                .flatMap(t14 -> f15.convert(json)
                                                                                                                                        .flatMap(t15 -> f16.convert(json)
                                                                                                                                                .flatMap(t16 -> f17.convert(json)
                                                                                                                                                        .flatMap(t17 -> f18.convert(json)
                                                                                                                                                                .flatMap(t18 -> f19.convert(json)
                                                                                                                                                                        .flatMap(t19 -> f20.convert(json)
                                                                                                                                                                                .flatMap(t20 -> f21.convert(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.convert(json)
                                                                                                                                                                                                .flatMap(t22 -> f23.convert(json)
                                                                                                                                                                                                        .flatMap(t23 -> f24.convert(json)
                                                                                                                                                                                                                .flatMap(t24 -> f25.convert(json)
                                                                                                                                                                                                                        .flatMap(t25 -> f26.convert(json)
                                                                                                                                                                                                                                .flatMap(t26 -> f27.convert(json)
                                                                                                                                                                                                                                        .flatMap(t27 -> f28.convert(json)
                                                                                                                                                                                                                                                .flatMap(t28 -> f29.convert(json)
                                                                                                                                                                                                                                                        .map(t29 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29)))))))))))))))))))))))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31> Either<String, T31> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .flatMap(t10 -> f11.convert(json)
                                                                                                        .flatMap(t11 -> f12.convert(json)
                                                                                                                .flatMap(t12 -> f13.convert(json)
                                                                                                                        .flatMap(t13 -> f14.convert(json)
                                                                                                                                .flatMap(t14 -> f15.convert(json)
                                                                                                                                        .flatMap(t15 -> f16.convert(json)
                                                                                                                                                .flatMap(t16 -> f17.convert(json)
                                                                                                                                                        .flatMap(t17 -> f18.convert(json)
                                                                                                                                                                .flatMap(t18 -> f19.convert(json)
                                                                                                                                                                        .flatMap(t19 -> f20.convert(json)
                                                                                                                                                                                .flatMap(t20 -> f21.convert(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.convert(json)
                                                                                                                                                                                                .flatMap(t22 -> f23.convert(json)
                                                                                                                                                                                                        .flatMap(t23 -> f24.convert(json)
                                                                                                                                                                                                                .flatMap(t24 -> f25.convert(json)
                                                                                                                                                                                                                        .flatMap(t25 -> f26.convert(json)
                                                                                                                                                                                                                                .flatMap(t26 -> f27.convert(json)
                                                                                                                                                                                                                                        .flatMap(t27 -> f28.convert(json)
                                                                                                                                                                                                                                                .flatMap(t28 -> f29.convert(json)
                                                                                                                                                                                                                                                        .flatMap(t29 -> f30.convert(json)
                                                                                                                                                                                                                                                                .map(t30 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30))))))))))))))))))))))))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32> Either<String, T32> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .flatMap(t10 -> f11.convert(json)
                                                                                                        .flatMap(t11 -> f12.convert(json)
                                                                                                                .flatMap(t12 -> f13.convert(json)
                                                                                                                        .flatMap(t13 -> f14.convert(json)
                                                                                                                                .flatMap(t14 -> f15.convert(json)
                                                                                                                                        .flatMap(t15 -> f16.convert(json)
                                                                                                                                                .flatMap(t16 -> f17.convert(json)
                                                                                                                                                        .flatMap(t17 -> f18.convert(json)
                                                                                                                                                                .flatMap(t18 -> f19.convert(json)
                                                                                                                                                                        .flatMap(t19 -> f20.convert(json)
                                                                                                                                                                                .flatMap(t20 -> f21.convert(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.convert(json)
                                                                                                                                                                                                .flatMap(t22 -> f23.convert(json)
                                                                                                                                                                                                        .flatMap(t23 -> f24.convert(json)
                                                                                                                                                                                                                .flatMap(t24 -> f25.convert(json)
                                                                                                                                                                                                                        .flatMap(t25 -> f26.convert(json)
                                                                                                                                                                                                                                .flatMap(t26 -> f27.convert(json)
                                                                                                                                                                                                                                        .flatMap(t27 -> f28.convert(json)
                                                                                                                                                                                                                                                .flatMap(t28 -> f29.convert(json)
                                                                                                                                                                                                                                                        .flatMap(t29 -> f30.convert(json)
                                                                                                                                                                                                                                                                .flatMap(t30 -> f31.convert(json)
                                                                                                                                                                                                                                                                        .map(t31 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31)))))))))))))))))))))))))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33> Either<String, T33> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .flatMap(t10 -> f11.convert(json)
                                                                                                        .flatMap(t11 -> f12.convert(json)
                                                                                                                .flatMap(t12 -> f13.convert(json)
                                                                                                                        .flatMap(t13 -> f14.convert(json)
                                                                                                                                .flatMap(t14 -> f15.convert(json)
                                                                                                                                        .flatMap(t15 -> f16.convert(json)
                                                                                                                                                .flatMap(t16 -> f17.convert(json)
                                                                                                                                                        .flatMap(t17 -> f18.convert(json)
                                                                                                                                                                .flatMap(t18 -> f19.convert(json)
                                                                                                                                                                        .flatMap(t19 -> f20.convert(json)
                                                                                                                                                                                .flatMap(t20 -> f21.convert(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.convert(json)
                                                                                                                                                                                                .flatMap(t22 -> f23.convert(json)
                                                                                                                                                                                                        .flatMap(t23 -> f24.convert(json)
                                                                                                                                                                                                                .flatMap(t24 -> f25.convert(json)
                                                                                                                                                                                                                        .flatMap(t25 -> f26.convert(json)
                                                                                                                                                                                                                                .flatMap(t26 -> f27.convert(json)
                                                                                                                                                                                                                                        .flatMap(t27 -> f28.convert(json)
                                                                                                                                                                                                                                                .flatMap(t28 -> f29.convert(json)
                                                                                                                                                                                                                                                        .flatMap(t29 -> f30.convert(json)
                                                                                                                                                                                                                                                                .flatMap(t30 -> f31.convert(json)
                                                                                                                                                                                                                                                                        .flatMap(t31 -> f32.convert(json)
                                                                                                                                                                                                                                                                                .map(t32 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32))))))))))))))))))))))))))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34> Either<String, T34> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .flatMap(t10 -> f11.convert(json)
                                                                                                        .flatMap(t11 -> f12.convert(json)
                                                                                                                .flatMap(t12 -> f13.convert(json)
                                                                                                                        .flatMap(t13 -> f14.convert(json)
                                                                                                                                .flatMap(t14 -> f15.convert(json)
                                                                                                                                        .flatMap(t15 -> f16.convert(json)
                                                                                                                                                .flatMap(t16 -> f17.convert(json)
                                                                                                                                                        .flatMap(t17 -> f18.convert(json)
                                                                                                                                                                .flatMap(t18 -> f19.convert(json)
                                                                                                                                                                        .flatMap(t19 -> f20.convert(json)
                                                                                                                                                                                .flatMap(t20 -> f21.convert(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.convert(json)
                                                                                                                                                                                                .flatMap(t22 -> f23.convert(json)
                                                                                                                                                                                                        .flatMap(t23 -> f24.convert(json)
                                                                                                                                                                                                                .flatMap(t24 -> f25.convert(json)
                                                                                                                                                                                                                        .flatMap(t25 -> f26.convert(json)
                                                                                                                                                                                                                                .flatMap(t26 -> f27.convert(json)
                                                                                                                                                                                                                                        .flatMap(t27 -> f28.convert(json)
                                                                                                                                                                                                                                                .flatMap(t28 -> f29.convert(json)
                                                                                                                                                                                                                                                        .flatMap(t29 -> f30.convert(json)
                                                                                                                                                                                                                                                                .flatMap(t30 -> f31.convert(json)
                                                                                                                                                                                                                                                                        .flatMap(t31 -> f32.convert(json)
                                                                                                                                                                                                                                                                                .flatMap(t32 -> f33.convert(json)
                                                                                                                                                                                                                                                                                        .map(t33 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33)))))))))))))))))))))))))))))))))));
    }

    public <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35> Either<String, T35> with(
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
        return f0.convert(json)
                .flatMap(t0 -> f1.convert(json)
                        .flatMap(t1 -> f2.convert(json)
                                .flatMap(t2 -> f3.convert(json)
                                        .flatMap(t3 -> f4.convert(json)
                                                .flatMap(t4 -> f5.convert(json)
                                                        .flatMap(t5 -> f6.convert(json)
                                                                .flatMap(t6 -> f7.convert(json)
                                                                        .flatMap(t7 -> f8.convert(json)
                                                                                .flatMap(t8 -> f9.convert(json)
                                                                                        .flatMap(t9 -> f10.convert(json)
                                                                                                .flatMap(t10 -> f11.convert(json)
                                                                                                        .flatMap(t11 -> f12.convert(json)
                                                                                                                .flatMap(t12 -> f13.convert(json)
                                                                                                                        .flatMap(t13 -> f14.convert(json)
                                                                                                                                .flatMap(t14 -> f15.convert(json)
                                                                                                                                        .flatMap(t15 -> f16.convert(json)
                                                                                                                                                .flatMap(t16 -> f17.convert(json)
                                                                                                                                                        .flatMap(t17 -> f18.convert(json)
                                                                                                                                                                .flatMap(t18 -> f19.convert(json)
                                                                                                                                                                        .flatMap(t19 -> f20.convert(json)
                                                                                                                                                                                .flatMap(t20 -> f21.convert(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.convert(json)
                                                                                                                                                                                                .flatMap(t22 -> f23.convert(json)
                                                                                                                                                                                                        .flatMap(t23 -> f24.convert(json)
                                                                                                                                                                                                                .flatMap(t24 -> f25.convert(json)
                                                                                                                                                                                                                        .flatMap(t25 -> f26.convert(json)
                                                                                                                                                                                                                                .flatMap(t26 -> f27.convert(json)
                                                                                                                                                                                                                                        .flatMap(t27 -> f28.convert(json)
                                                                                                                                                                                                                                                .flatMap(t28 -> f29.convert(json)
                                                                                                                                                                                                                                                        .flatMap(t29 -> f30.convert(json)
                                                                                                                                                                                                                                                                .flatMap(t30 -> f31.convert(json)
                                                                                                                                                                                                                                                                        .flatMap(t31 -> f32.convert(json)
                                                                                                                                                                                                                                                                                .flatMap(t32 -> f33.convert(json)
                                                                                                                                                                                                                                                                                        .flatMap(t33 -> f34.convert(json)
                                                                                                                                                                                                                                                                                                .map(t34 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33, t34))))))))))))))))))))))))))))))))))));
    }
}