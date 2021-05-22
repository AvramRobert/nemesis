package com.ravram.nemesis.coerce;

import static com.ravram.nemesis.util.function.Functions.*;

import com.ravram.nemesis.Read;
import com.ravram.nemesis.util.error.Either;
import com.ravram.nemesis.Json;

public final class JsonReader {
    private final Json json;

    public JsonReader(final Json json) {
        this.json = json;
    }

    public final <T0, T1> Either<String, T1> using(
            Read<T0> f0,
            Function1<T0, T1> comb) {
        return f0.apply(json)
                .map(t0 -> comb.apply(t0));
    }

    public final <T0, T1, T2> Either<String, T2> using(
            Read<T0> f0,
            Read<T1> f1,
            Function2<T0, T1, T2> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .map(t1 -> comb.apply(t0, t1)));
    }

    public final <T0, T1, T2, T3> Either<String, T3> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Function3<T0, T1, T2, T3> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .map(t2 -> comb.apply(t0, t1, t2))));
    }

    public final <T0, T1, T2, T3, T4> Either<String, T4> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Function4<T0, T1, T2, T3, T4> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .map(t3 -> comb.apply(t0, t1, t2, t3)))));
    }

    public final <T0, T1, T2, T3, T4, T5> Either<String, T5> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Function5<T0, T1, T2, T3, T4, T5> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .map(t4 -> comb.apply(t0, t1, t2, t3, t4))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6> Either<String, T6> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Function6<T0, T1, T2, T3, T4, T5, T6> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .map(t5 -> comb.apply(t0, t1, t2, t3, t4, t5)))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7> Either<String, T7> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Function7<T0, T1, T2, T3, T4, T5, T6, T7> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .map(t6 -> comb.apply(t0, t1, t2, t3, t4, t5, t6))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8> Either<String, T8> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Function8<T0, T1, T2, T3, T4, T5, T6, T7, T8> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .map(t7 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7)))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9> Either<String, T9> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Function9<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .map(t8 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> Either<String, T10> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Function10<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .map(t9 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9)))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> Either<String, T11> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Function11<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .map(t10 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10))))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Either<String, T12> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Read<T11> f11,
            Function12<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .flatMap(t10 -> f11.apply(json)
                                                                                                        .map(t11 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11)))))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> Either<String, T13> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Read<T11> f11,
            Read<T12> f12,
            Function13<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .flatMap(t10 -> f11.apply(json)
                                                                                                        .flatMap(t11 -> f12.apply(json)
                                                                                                                .map(t12 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12))))))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> Either<String, T14> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Read<T11> f11,
            Read<T12> f12,
            Read<T13> f13,
            Function14<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .flatMap(t10 -> f11.apply(json)
                                                                                                        .flatMap(t11 -> f12.apply(json)
                                                                                                                .flatMap(t12 -> f13.apply(json)
                                                                                                                        .map(t13 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13)))))))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> Either<String, T15> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Read<T11> f11,
            Read<T12> f12,
            Read<T13> f13,
            Read<T14> f14,
            Function15<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .flatMap(t10 -> f11.apply(json)
                                                                                                        .flatMap(t11 -> f12.apply(json)
                                                                                                                .flatMap(t12 -> f13.apply(json)
                                                                                                                        .flatMap(t13 -> f14.apply(json)
                                                                                                                                .map(t14 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14))))))))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> Either<String, T16> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Read<T11> f11,
            Read<T12> f12,
            Read<T13> f13,
            Read<T14> f14,
            Read<T15> f15,
            Function16<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .flatMap(t10 -> f11.apply(json)
                                                                                                        .flatMap(t11 -> f12.apply(json)
                                                                                                                .flatMap(t12 -> f13.apply(json)
                                                                                                                        .flatMap(t13 -> f14.apply(json)
                                                                                                                                .flatMap(t14 -> f15.apply(json)
                                                                                                                                        .map(t15 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15)))))))))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> Either<String, T17> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Read<T11> f11,
            Read<T12> f12,
            Read<T13> f13,
            Read<T14> f14,
            Read<T15> f15,
            Read<T16> f16,
            Function17<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .flatMap(t10 -> f11.apply(json)
                                                                                                        .flatMap(t11 -> f12.apply(json)
                                                                                                                .flatMap(t12 -> f13.apply(json)
                                                                                                                        .flatMap(t13 -> f14.apply(json)
                                                                                                                                .flatMap(t14 -> f15.apply(json)
                                                                                                                                        .flatMap(t15 -> f16.apply(json)
                                                                                                                                                .map(t16 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16))))))))))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> Either<String, T18> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Read<T11> f11,
            Read<T12> f12,
            Read<T13> f13,
            Read<T14> f14,
            Read<T15> f15,
            Read<T16> f16,
            Read<T17> f17,
            Function18<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .flatMap(t10 -> f11.apply(json)
                                                                                                        .flatMap(t11 -> f12.apply(json)
                                                                                                                .flatMap(t12 -> f13.apply(json)
                                                                                                                        .flatMap(t13 -> f14.apply(json)
                                                                                                                                .flatMap(t14 -> f15.apply(json)
                                                                                                                                        .flatMap(t15 -> f16.apply(json)
                                                                                                                                                .flatMap(t16 -> f17.apply(json)
                                                                                                                                                        .map(t17 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17)))))))))))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> Either<String, T19> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Read<T11> f11,
            Read<T12> f12,
            Read<T13> f13,
            Read<T14> f14,
            Read<T15> f15,
            Read<T16> f16,
            Read<T17> f17,
            Read<T18> f18,
            Function19<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .flatMap(t10 -> f11.apply(json)
                                                                                                        .flatMap(t11 -> f12.apply(json)
                                                                                                                .flatMap(t12 -> f13.apply(json)
                                                                                                                        .flatMap(t13 -> f14.apply(json)
                                                                                                                                .flatMap(t14 -> f15.apply(json)
                                                                                                                                        .flatMap(t15 -> f16.apply(json)
                                                                                                                                                .flatMap(t16 -> f17.apply(json)
                                                                                                                                                        .flatMap(t17 -> f18.apply(json)
                                                                                                                                                                .map(t18 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18))))))))))))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> Either<String, T20> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Read<T11> f11,
            Read<T12> f12,
            Read<T13> f13,
            Read<T14> f14,
            Read<T15> f15,
            Read<T16> f16,
            Read<T17> f17,
            Read<T18> f18,
            Read<T19> f19,
            Function20<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .flatMap(t10 -> f11.apply(json)
                                                                                                        .flatMap(t11 -> f12.apply(json)
                                                                                                                .flatMap(t12 -> f13.apply(json)
                                                                                                                        .flatMap(t13 -> f14.apply(json)
                                                                                                                                .flatMap(t14 -> f15.apply(json)
                                                                                                                                        .flatMap(t15 -> f16.apply(json)
                                                                                                                                                .flatMap(t16 -> f17.apply(json)
                                                                                                                                                        .flatMap(t17 -> f18.apply(json)
                                                                                                                                                                .flatMap(t18 -> f19.apply(json)
                                                                                                                                                                        .map(t19 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19)))))))))))))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> Either<String, T21> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Read<T11> f11,
            Read<T12> f12,
            Read<T13> f13,
            Read<T14> f14,
            Read<T15> f15,
            Read<T16> f16,
            Read<T17> f17,
            Read<T18> f18,
            Read<T19> f19,
            Read<T20> f20,
            Function21<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .flatMap(t10 -> f11.apply(json)
                                                                                                        .flatMap(t11 -> f12.apply(json)
                                                                                                                .flatMap(t12 -> f13.apply(json)
                                                                                                                        .flatMap(t13 -> f14.apply(json)
                                                                                                                                .flatMap(t14 -> f15.apply(json)
                                                                                                                                        .flatMap(t15 -> f16.apply(json)
                                                                                                                                                .flatMap(t16 -> f17.apply(json)
                                                                                                                                                        .flatMap(t17 -> f18.apply(json)
                                                                                                                                                                .flatMap(t18 -> f19.apply(json)
                                                                                                                                                                        .flatMap(t19 -> f20.apply(json)
                                                                                                                                                                                .map(t20 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20))))))))))))))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> Either<String, T22> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Read<T11> f11,
            Read<T12> f12,
            Read<T13> f13,
            Read<T14> f14,
            Read<T15> f15,
            Read<T16> f16,
            Read<T17> f17,
            Read<T18> f18,
            Read<T19> f19,
            Read<T20> f20,
            Read<T21> f21,
            Function22<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .flatMap(t10 -> f11.apply(json)
                                                                                                        .flatMap(t11 -> f12.apply(json)
                                                                                                                .flatMap(t12 -> f13.apply(json)
                                                                                                                        .flatMap(t13 -> f14.apply(json)
                                                                                                                                .flatMap(t14 -> f15.apply(json)
                                                                                                                                        .flatMap(t15 -> f16.apply(json)
                                                                                                                                                .flatMap(t16 -> f17.apply(json)
                                                                                                                                                        .flatMap(t17 -> f18.apply(json)
                                                                                                                                                                .flatMap(t18 -> f19.apply(json)
                                                                                                                                                                        .flatMap(t19 -> f20.apply(json)
                                                                                                                                                                                .flatMap(t20 -> f21.apply(json)
                                                                                                                                                                                        .map(t21 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21)))))))))))))))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23> Either<String, T23> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Read<T11> f11,
            Read<T12> f12,
            Read<T13> f13,
            Read<T14> f14,
            Read<T15> f15,
            Read<T16> f16,
            Read<T17> f17,
            Read<T18> f18,
            Read<T19> f19,
            Read<T20> f20,
            Read<T21> f21,
            Read<T22> f22,
            Function23<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .flatMap(t10 -> f11.apply(json)
                                                                                                        .flatMap(t11 -> f12.apply(json)
                                                                                                                .flatMap(t12 -> f13.apply(json)
                                                                                                                        .flatMap(t13 -> f14.apply(json)
                                                                                                                                .flatMap(t14 -> f15.apply(json)
                                                                                                                                        .flatMap(t15 -> f16.apply(json)
                                                                                                                                                .flatMap(t16 -> f17.apply(json)
                                                                                                                                                        .flatMap(t17 -> f18.apply(json)
                                                                                                                                                                .flatMap(t18 -> f19.apply(json)
                                                                                                                                                                        .flatMap(t19 -> f20.apply(json)
                                                                                                                                                                                .flatMap(t20 -> f21.apply(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.apply(json)
                                                                                                                                                                                                .map(t22 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22))))))))))))))))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24> Either<String, T24> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Read<T11> f11,
            Read<T12> f12,
            Read<T13> f13,
            Read<T14> f14,
            Read<T15> f15,
            Read<T16> f16,
            Read<T17> f17,
            Read<T18> f18,
            Read<T19> f19,
            Read<T20> f20,
            Read<T21> f21,
            Read<T22> f22,
            Read<T23> f23,
            Function24<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .flatMap(t10 -> f11.apply(json)
                                                                                                        .flatMap(t11 -> f12.apply(json)
                                                                                                                .flatMap(t12 -> f13.apply(json)
                                                                                                                        .flatMap(t13 -> f14.apply(json)
                                                                                                                                .flatMap(t14 -> f15.apply(json)
                                                                                                                                        .flatMap(t15 -> f16.apply(json)
                                                                                                                                                .flatMap(t16 -> f17.apply(json)
                                                                                                                                                        .flatMap(t17 -> f18.apply(json)
                                                                                                                                                                .flatMap(t18 -> f19.apply(json)
                                                                                                                                                                        .flatMap(t19 -> f20.apply(json)
                                                                                                                                                                                .flatMap(t20 -> f21.apply(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.apply(json)
                                                                                                                                                                                                .flatMap(t22 -> f23.apply(json)
                                                                                                                                                                                                        .map(t23 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23)))))))))))))))))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25> Either<String, T25> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Read<T11> f11,
            Read<T12> f12,
            Read<T13> f13,
            Read<T14> f14,
            Read<T15> f15,
            Read<T16> f16,
            Read<T17> f17,
            Read<T18> f18,
            Read<T19> f19,
            Read<T20> f20,
            Read<T21> f21,
            Read<T22> f22,
            Read<T23> f23,
            Read<T24> f24,
            Function25<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .flatMap(t10 -> f11.apply(json)
                                                                                                        .flatMap(t11 -> f12.apply(json)
                                                                                                                .flatMap(t12 -> f13.apply(json)
                                                                                                                        .flatMap(t13 -> f14.apply(json)
                                                                                                                                .flatMap(t14 -> f15.apply(json)
                                                                                                                                        .flatMap(t15 -> f16.apply(json)
                                                                                                                                                .flatMap(t16 -> f17.apply(json)
                                                                                                                                                        .flatMap(t17 -> f18.apply(json)
                                                                                                                                                                .flatMap(t18 -> f19.apply(json)
                                                                                                                                                                        .flatMap(t19 -> f20.apply(json)
                                                                                                                                                                                .flatMap(t20 -> f21.apply(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.apply(json)
                                                                                                                                                                                                .flatMap(t22 -> f23.apply(json)
                                                                                                                                                                                                        .flatMap(t23 -> f24.apply(json)
                                                                                                                                                                                                                .map(t24 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24))))))))))))))))))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26> Either<String, T26> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Read<T11> f11,
            Read<T12> f12,
            Read<T13> f13,
            Read<T14> f14,
            Read<T15> f15,
            Read<T16> f16,
            Read<T17> f17,
            Read<T18> f18,
            Read<T19> f19,
            Read<T20> f20,
            Read<T21> f21,
            Read<T22> f22,
            Read<T23> f23,
            Read<T24> f24,
            Read<T25> f25,
            Function26<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .flatMap(t10 -> f11.apply(json)
                                                                                                        .flatMap(t11 -> f12.apply(json)
                                                                                                                .flatMap(t12 -> f13.apply(json)
                                                                                                                        .flatMap(t13 -> f14.apply(json)
                                                                                                                                .flatMap(t14 -> f15.apply(json)
                                                                                                                                        .flatMap(t15 -> f16.apply(json)
                                                                                                                                                .flatMap(t16 -> f17.apply(json)
                                                                                                                                                        .flatMap(t17 -> f18.apply(json)
                                                                                                                                                                .flatMap(t18 -> f19.apply(json)
                                                                                                                                                                        .flatMap(t19 -> f20.apply(json)
                                                                                                                                                                                .flatMap(t20 -> f21.apply(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.apply(json)
                                                                                                                                                                                                .flatMap(t22 -> f23.apply(json)
                                                                                                                                                                                                        .flatMap(t23 -> f24.apply(json)
                                                                                                                                                                                                                .flatMap(t24 -> f25.apply(json)
                                                                                                                                                                                                                        .map(t25 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25)))))))))))))))))))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27> Either<String, T27> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Read<T11> f11,
            Read<T12> f12,
            Read<T13> f13,
            Read<T14> f14,
            Read<T15> f15,
            Read<T16> f16,
            Read<T17> f17,
            Read<T18> f18,
            Read<T19> f19,
            Read<T20> f20,
            Read<T21> f21,
            Read<T22> f22,
            Read<T23> f23,
            Read<T24> f24,
            Read<T25> f25,
            Read<T26> f26,
            Function27<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .flatMap(t10 -> f11.apply(json)
                                                                                                        .flatMap(t11 -> f12.apply(json)
                                                                                                                .flatMap(t12 -> f13.apply(json)
                                                                                                                        .flatMap(t13 -> f14.apply(json)
                                                                                                                                .flatMap(t14 -> f15.apply(json)
                                                                                                                                        .flatMap(t15 -> f16.apply(json)
                                                                                                                                                .flatMap(t16 -> f17.apply(json)
                                                                                                                                                        .flatMap(t17 -> f18.apply(json)
                                                                                                                                                                .flatMap(t18 -> f19.apply(json)
                                                                                                                                                                        .flatMap(t19 -> f20.apply(json)
                                                                                                                                                                                .flatMap(t20 -> f21.apply(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.apply(json)
                                                                                                                                                                                                .flatMap(t22 -> f23.apply(json)
                                                                                                                                                                                                        .flatMap(t23 -> f24.apply(json)
                                                                                                                                                                                                                .flatMap(t24 -> f25.apply(json)
                                                                                                                                                                                                                        .flatMap(t25 -> f26.apply(json)
                                                                                                                                                                                                                                .map(t26 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26))))))))))))))))))))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28> Either<String, T28> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Read<T11> f11,
            Read<T12> f12,
            Read<T13> f13,
            Read<T14> f14,
            Read<T15> f15,
            Read<T16> f16,
            Read<T17> f17,
            Read<T18> f18,
            Read<T19> f19,
            Read<T20> f20,
            Read<T21> f21,
            Read<T22> f22,
            Read<T23> f23,
            Read<T24> f24,
            Read<T25> f25,
            Read<T26> f26,
            Read<T27> f27,
            Function28<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .flatMap(t10 -> f11.apply(json)
                                                                                                        .flatMap(t11 -> f12.apply(json)
                                                                                                                .flatMap(t12 -> f13.apply(json)
                                                                                                                        .flatMap(t13 -> f14.apply(json)
                                                                                                                                .flatMap(t14 -> f15.apply(json)
                                                                                                                                        .flatMap(t15 -> f16.apply(json)
                                                                                                                                                .flatMap(t16 -> f17.apply(json)
                                                                                                                                                        .flatMap(t17 -> f18.apply(json)
                                                                                                                                                                .flatMap(t18 -> f19.apply(json)
                                                                                                                                                                        .flatMap(t19 -> f20.apply(json)
                                                                                                                                                                                .flatMap(t20 -> f21.apply(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.apply(json)
                                                                                                                                                                                                .flatMap(t22 -> f23.apply(json)
                                                                                                                                                                                                        .flatMap(t23 -> f24.apply(json)
                                                                                                                                                                                                                .flatMap(t24 -> f25.apply(json)
                                                                                                                                                                                                                        .flatMap(t25 -> f26.apply(json)
                                                                                                                                                                                                                                .flatMap(t26 -> f27.apply(json)
                                                                                                                                                                                                                                        .map(t27 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27)))))))))))))))))))))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29> Either<String, T29> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Read<T11> f11,
            Read<T12> f12,
            Read<T13> f13,
            Read<T14> f14,
            Read<T15> f15,
            Read<T16> f16,
            Read<T17> f17,
            Read<T18> f18,
            Read<T19> f19,
            Read<T20> f20,
            Read<T21> f21,
            Read<T22> f22,
            Read<T23> f23,
            Read<T24> f24,
            Read<T25> f25,
            Read<T26> f26,
            Read<T27> f27,
            Read<T28> f28,
            Function29<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .flatMap(t10 -> f11.apply(json)
                                                                                                        .flatMap(t11 -> f12.apply(json)
                                                                                                                .flatMap(t12 -> f13.apply(json)
                                                                                                                        .flatMap(t13 -> f14.apply(json)
                                                                                                                                .flatMap(t14 -> f15.apply(json)
                                                                                                                                        .flatMap(t15 -> f16.apply(json)
                                                                                                                                                .flatMap(t16 -> f17.apply(json)
                                                                                                                                                        .flatMap(t17 -> f18.apply(json)
                                                                                                                                                                .flatMap(t18 -> f19.apply(json)
                                                                                                                                                                        .flatMap(t19 -> f20.apply(json)
                                                                                                                                                                                .flatMap(t20 -> f21.apply(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.apply(json)
                                                                                                                                                                                                .flatMap(t22 -> f23.apply(json)
                                                                                                                                                                                                        .flatMap(t23 -> f24.apply(json)
                                                                                                                                                                                                                .flatMap(t24 -> f25.apply(json)
                                                                                                                                                                                                                        .flatMap(t25 -> f26.apply(json)
                                                                                                                                                                                                                                .flatMap(t26 -> f27.apply(json)
                                                                                                                                                                                                                                        .flatMap(t27 -> f28.apply(json)
                                                                                                                                                                                                                                                .map(t28 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28))))))))))))))))))))))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30> Either<String, T30> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Read<T11> f11,
            Read<T12> f12,
            Read<T13> f13,
            Read<T14> f14,
            Read<T15> f15,
            Read<T16> f16,
            Read<T17> f17,
            Read<T18> f18,
            Read<T19> f19,
            Read<T20> f20,
            Read<T21> f21,
            Read<T22> f22,
            Read<T23> f23,
            Read<T24> f24,
            Read<T25> f25,
            Read<T26> f26,
            Read<T27> f27,
            Read<T28> f28,
            Read<T29> f29,
            Function30<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .flatMap(t10 -> f11.apply(json)
                                                                                                        .flatMap(t11 -> f12.apply(json)
                                                                                                                .flatMap(t12 -> f13.apply(json)
                                                                                                                        .flatMap(t13 -> f14.apply(json)
                                                                                                                                .flatMap(t14 -> f15.apply(json)
                                                                                                                                        .flatMap(t15 -> f16.apply(json)
                                                                                                                                                .flatMap(t16 -> f17.apply(json)
                                                                                                                                                        .flatMap(t17 -> f18.apply(json)
                                                                                                                                                                .flatMap(t18 -> f19.apply(json)
                                                                                                                                                                        .flatMap(t19 -> f20.apply(json)
                                                                                                                                                                                .flatMap(t20 -> f21.apply(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.apply(json)
                                                                                                                                                                                                .flatMap(t22 -> f23.apply(json)
                                                                                                                                                                                                        .flatMap(t23 -> f24.apply(json)
                                                                                                                                                                                                                .flatMap(t24 -> f25.apply(json)
                                                                                                                                                                                                                        .flatMap(t25 -> f26.apply(json)
                                                                                                                                                                                                                                .flatMap(t26 -> f27.apply(json)
                                                                                                                                                                                                                                        .flatMap(t27 -> f28.apply(json)
                                                                                                                                                                                                                                                .flatMap(t28 -> f29.apply(json)
                                                                                                                                                                                                                                                        .map(t29 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29)))))))))))))))))))))))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31> Either<String, T31> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Read<T11> f11,
            Read<T12> f12,
            Read<T13> f13,
            Read<T14> f14,
            Read<T15> f15,
            Read<T16> f16,
            Read<T17> f17,
            Read<T18> f18,
            Read<T19> f19,
            Read<T20> f20,
            Read<T21> f21,
            Read<T22> f22,
            Read<T23> f23,
            Read<T24> f24,
            Read<T25> f25,
            Read<T26> f26,
            Read<T27> f27,
            Read<T28> f28,
            Read<T29> f29,
            Read<T30> f30,
            Function31<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .flatMap(t10 -> f11.apply(json)
                                                                                                        .flatMap(t11 -> f12.apply(json)
                                                                                                                .flatMap(t12 -> f13.apply(json)
                                                                                                                        .flatMap(t13 -> f14.apply(json)
                                                                                                                                .flatMap(t14 -> f15.apply(json)
                                                                                                                                        .flatMap(t15 -> f16.apply(json)
                                                                                                                                                .flatMap(t16 -> f17.apply(json)
                                                                                                                                                        .flatMap(t17 -> f18.apply(json)
                                                                                                                                                                .flatMap(t18 -> f19.apply(json)
                                                                                                                                                                        .flatMap(t19 -> f20.apply(json)
                                                                                                                                                                                .flatMap(t20 -> f21.apply(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.apply(json)
                                                                                                                                                                                                .flatMap(t22 -> f23.apply(json)
                                                                                                                                                                                                        .flatMap(t23 -> f24.apply(json)
                                                                                                                                                                                                                .flatMap(t24 -> f25.apply(json)
                                                                                                                                                                                                                        .flatMap(t25 -> f26.apply(json)
                                                                                                                                                                                                                                .flatMap(t26 -> f27.apply(json)
                                                                                                                                                                                                                                        .flatMap(t27 -> f28.apply(json)
                                                                                                                                                                                                                                                .flatMap(t28 -> f29.apply(json)
                                                                                                                                                                                                                                                        .flatMap(t29 -> f30.apply(json)
                                                                                                                                                                                                                                                                .map(t30 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30))))))))))))))))))))))))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32> Either<String, T32> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Read<T11> f11,
            Read<T12> f12,
            Read<T13> f13,
            Read<T14> f14,
            Read<T15> f15,
            Read<T16> f16,
            Read<T17> f17,
            Read<T18> f18,
            Read<T19> f19,
            Read<T20> f20,
            Read<T21> f21,
            Read<T22> f22,
            Read<T23> f23,
            Read<T24> f24,
            Read<T25> f25,
            Read<T26> f26,
            Read<T27> f27,
            Read<T28> f28,
            Read<T29> f29,
            Read<T30> f30,
            Read<T31> f31,
            Function32<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .flatMap(t10 -> f11.apply(json)
                                                                                                        .flatMap(t11 -> f12.apply(json)
                                                                                                                .flatMap(t12 -> f13.apply(json)
                                                                                                                        .flatMap(t13 -> f14.apply(json)
                                                                                                                                .flatMap(t14 -> f15.apply(json)
                                                                                                                                        .flatMap(t15 -> f16.apply(json)
                                                                                                                                                .flatMap(t16 -> f17.apply(json)
                                                                                                                                                        .flatMap(t17 -> f18.apply(json)
                                                                                                                                                                .flatMap(t18 -> f19.apply(json)
                                                                                                                                                                        .flatMap(t19 -> f20.apply(json)
                                                                                                                                                                                .flatMap(t20 -> f21.apply(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.apply(json)
                                                                                                                                                                                                .flatMap(t22 -> f23.apply(json)
                                                                                                                                                                                                        .flatMap(t23 -> f24.apply(json)
                                                                                                                                                                                                                .flatMap(t24 -> f25.apply(json)
                                                                                                                                                                                                                        .flatMap(t25 -> f26.apply(json)
                                                                                                                                                                                                                                .flatMap(t26 -> f27.apply(json)
                                                                                                                                                                                                                                        .flatMap(t27 -> f28.apply(json)
                                                                                                                                                                                                                                                .flatMap(t28 -> f29.apply(json)
                                                                                                                                                                                                                                                        .flatMap(t29 -> f30.apply(json)
                                                                                                                                                                                                                                                                .flatMap(t30 -> f31.apply(json)
                                                                                                                                                                                                                                                                        .map(t31 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31)))))))))))))))))))))))))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33> Either<String, T33> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Read<T11> f11,
            Read<T12> f12,
            Read<T13> f13,
            Read<T14> f14,
            Read<T15> f15,
            Read<T16> f16,
            Read<T17> f17,
            Read<T18> f18,
            Read<T19> f19,
            Read<T20> f20,
            Read<T21> f21,
            Read<T22> f22,
            Read<T23> f23,
            Read<T24> f24,
            Read<T25> f25,
            Read<T26> f26,
            Read<T27> f27,
            Read<T28> f28,
            Read<T29> f29,
            Read<T30> f30,
            Read<T31> f31,
            Read<T32> f32,
            Function33<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .flatMap(t10 -> f11.apply(json)
                                                                                                        .flatMap(t11 -> f12.apply(json)
                                                                                                                .flatMap(t12 -> f13.apply(json)
                                                                                                                        .flatMap(t13 -> f14.apply(json)
                                                                                                                                .flatMap(t14 -> f15.apply(json)
                                                                                                                                        .flatMap(t15 -> f16.apply(json)
                                                                                                                                                .flatMap(t16 -> f17.apply(json)
                                                                                                                                                        .flatMap(t17 -> f18.apply(json)
                                                                                                                                                                .flatMap(t18 -> f19.apply(json)
                                                                                                                                                                        .flatMap(t19 -> f20.apply(json)
                                                                                                                                                                                .flatMap(t20 -> f21.apply(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.apply(json)
                                                                                                                                                                                                .flatMap(t22 -> f23.apply(json)
                                                                                                                                                                                                        .flatMap(t23 -> f24.apply(json)
                                                                                                                                                                                                                .flatMap(t24 -> f25.apply(json)
                                                                                                                                                                                                                        .flatMap(t25 -> f26.apply(json)
                                                                                                                                                                                                                                .flatMap(t26 -> f27.apply(json)
                                                                                                                                                                                                                                        .flatMap(t27 -> f28.apply(json)
                                                                                                                                                                                                                                                .flatMap(t28 -> f29.apply(json)
                                                                                                                                                                                                                                                        .flatMap(t29 -> f30.apply(json)
                                                                                                                                                                                                                                                                .flatMap(t30 -> f31.apply(json)
                                                                                                                                                                                                                                                                        .flatMap(t31 -> f32.apply(json)
                                                                                                                                                                                                                                                                                .map(t32 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32))))))))))))))))))))))))))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34> Either<String, T34> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Read<T11> f11,
            Read<T12> f12,
            Read<T13> f13,
            Read<T14> f14,
            Read<T15> f15,
            Read<T16> f16,
            Read<T17> f17,
            Read<T18> f18,
            Read<T19> f19,
            Read<T20> f20,
            Read<T21> f21,
            Read<T22> f22,
            Read<T23> f23,
            Read<T24> f24,
            Read<T25> f25,
            Read<T26> f26,
            Read<T27> f27,
            Read<T28> f28,
            Read<T29> f29,
            Read<T30> f30,
            Read<T31> f31,
            Read<T32> f32,
            Read<T33> f33,
            Function34<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .flatMap(t10 -> f11.apply(json)
                                                                                                        .flatMap(t11 -> f12.apply(json)
                                                                                                                .flatMap(t12 -> f13.apply(json)
                                                                                                                        .flatMap(t13 -> f14.apply(json)
                                                                                                                                .flatMap(t14 -> f15.apply(json)
                                                                                                                                        .flatMap(t15 -> f16.apply(json)
                                                                                                                                                .flatMap(t16 -> f17.apply(json)
                                                                                                                                                        .flatMap(t17 -> f18.apply(json)
                                                                                                                                                                .flatMap(t18 -> f19.apply(json)
                                                                                                                                                                        .flatMap(t19 -> f20.apply(json)
                                                                                                                                                                                .flatMap(t20 -> f21.apply(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.apply(json)
                                                                                                                                                                                                .flatMap(t22 -> f23.apply(json)
                                                                                                                                                                                                        .flatMap(t23 -> f24.apply(json)
                                                                                                                                                                                                                .flatMap(t24 -> f25.apply(json)
                                                                                                                                                                                                                        .flatMap(t25 -> f26.apply(json)
                                                                                                                                                                                                                                .flatMap(t26 -> f27.apply(json)
                                                                                                                                                                                                                                        .flatMap(t27 -> f28.apply(json)
                                                                                                                                                                                                                                                .flatMap(t28 -> f29.apply(json)
                                                                                                                                                                                                                                                        .flatMap(t29 -> f30.apply(json)
                                                                                                                                                                                                                                                                .flatMap(t30 -> f31.apply(json)
                                                                                                                                                                                                                                                                        .flatMap(t31 -> f32.apply(json)
                                                                                                                                                                                                                                                                                .flatMap(t32 -> f33.apply(json)
                                                                                                                                                                                                                                                                                        .map(t33 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33)))))))))))))))))))))))))))))))))));
    }

    public final <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35> Either<String, T35> using(
            Read<T0> f0,
            Read<T1> f1,
            Read<T2> f2,
            Read<T3> f3,
            Read<T4> f4,
            Read<T5> f5,
            Read<T6> f6,
            Read<T7> f7,
            Read<T8> f8,
            Read<T9> f9,
            Read<T10> f10,
            Read<T11> f11,
            Read<T12> f12,
            Read<T13> f13,
            Read<T14> f14,
            Read<T15> f15,
            Read<T16> f16,
            Read<T17> f17,
            Read<T18> f18,
            Read<T19> f19,
            Read<T20> f20,
            Read<T21> f21,
            Read<T22> f22,
            Read<T23> f23,
            Read<T24> f24,
            Read<T25> f25,
            Read<T26> f26,
            Read<T27> f27,
            Read<T28> f28,
            Read<T29> f29,
            Read<T30> f30,
            Read<T31> f31,
            Read<T32> f32,
            Read<T33> f33,
            Read<T34> f34,
            Function35<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35> comb) {
        return f0.apply(json)
                .flatMap(t0 -> f1.apply(json)
                        .flatMap(t1 -> f2.apply(json)
                                .flatMap(t2 -> f3.apply(json)
                                        .flatMap(t3 -> f4.apply(json)
                                                .flatMap(t4 -> f5.apply(json)
                                                        .flatMap(t5 -> f6.apply(json)
                                                                .flatMap(t6 -> f7.apply(json)
                                                                        .flatMap(t7 -> f8.apply(json)
                                                                                .flatMap(t8 -> f9.apply(json)
                                                                                        .flatMap(t9 -> f10.apply(json)
                                                                                                .flatMap(t10 -> f11.apply(json)
                                                                                                        .flatMap(t11 -> f12.apply(json)
                                                                                                                .flatMap(t12 -> f13.apply(json)
                                                                                                                        .flatMap(t13 -> f14.apply(json)
                                                                                                                                .flatMap(t14 -> f15.apply(json)
                                                                                                                                        .flatMap(t15 -> f16.apply(json)
                                                                                                                                                .flatMap(t16 -> f17.apply(json)
                                                                                                                                                        .flatMap(t17 -> f18.apply(json)
                                                                                                                                                                .flatMap(t18 -> f19.apply(json)
                                                                                                                                                                        .flatMap(t19 -> f20.apply(json)
                                                                                                                                                                                .flatMap(t20 -> f21.apply(json)
                                                                                                                                                                                        .flatMap(t21 -> f22.apply(json)
                                                                                                                                                                                                .flatMap(t22 -> f23.apply(json)
                                                                                                                                                                                                        .flatMap(t23 -> f24.apply(json)
                                                                                                                                                                                                                .flatMap(t24 -> f25.apply(json)
                                                                                                                                                                                                                        .flatMap(t25 -> f26.apply(json)
                                                                                                                                                                                                                                .flatMap(t26 -> f27.apply(json)
                                                                                                                                                                                                                                        .flatMap(t27 -> f28.apply(json)
                                                                                                                                                                                                                                                .flatMap(t28 -> f29.apply(json)
                                                                                                                                                                                                                                                        .flatMap(t29 -> f30.apply(json)
                                                                                                                                                                                                                                                                .flatMap(t30 -> f31.apply(json)
                                                                                                                                                                                                                                                                        .flatMap(t31 -> f32.apply(json)
                                                                                                                                                                                                                                                                                .flatMap(t32 -> f33.apply(json)
                                                                                                                                                                                                                                                                                        .flatMap(t33 -> f34.apply(json)
                                                                                                                                                                                                                                                                                                .map(t34 -> comb.apply(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33, t34))))))))))))))))))))))))))))))))))));
    }
}