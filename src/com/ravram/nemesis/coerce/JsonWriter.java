package com.ravram.nemesis.coerce;

import com.ravram.nemesis.model.JObj;
import com.ravram.nemesis.Write;
import com.ravram.nemesis.Json;
import com.ravram.nemesis.util.error.Either;
import io.lacuna.bifurcan.Map;
import io.lacuna.bifurcan.Maps.Entry;

import java.util.Arrays;

public final class JsonWriter<A> {
    private final A obj;

    private Entry<String, Json> entry(final String key, final Json value) {
        return new Entry<>("\"" + key + "\"", value);
    }

    public JsonWriter(final A obj) {
        this.obj = obj;
    }

    @SafeVarargs
    private final JObj mapFrom(final Entry<String, Json>... entries) {
        return new JObj(Map.from(Arrays.asList(entries)));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0) {
        return f0.apply(obj).map(t0 -> mapFrom(entry(key0, t0)));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).map(t1 -> mapFrom(entry(key0, t0), entry(key1, t1))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).map(t2 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2)))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).map(t3 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).map(t4 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4)))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).map(t5 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).map(t6 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6)))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).map(t7 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).map(t8 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8)))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).map(t9 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).map(t10 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10)))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10,
                                            final String key11, final Write<A> f11) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).flatMap(t10 -> f11.apply(obj).map(t11 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11))))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10,
                                            final String key11, final Write<A> f11,
                                            final String key12, final Write<A> f12) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).flatMap(t10 -> f11.apply(obj).flatMap(t11 -> f12.apply(obj).map(t12 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12)))))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10,
                                            final String key11, final Write<A> f11,
                                            final String key12, final Write<A> f12,
                                            final String key13, final Write<A> f13) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).flatMap(t10 -> f11.apply(obj).flatMap(t11 -> f12.apply(obj).flatMap(t12 -> f13.apply(obj).map(t13 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13))))))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10,
                                            final String key11, final Write<A> f11,
                                            final String key12, final Write<A> f12,
                                            final String key13, final Write<A> f13,
                                            final String key14, final Write<A> f14) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).flatMap(t10 -> f11.apply(obj).flatMap(t11 -> f12.apply(obj).flatMap(t12 -> f13.apply(obj).flatMap(t13 -> f14.apply(obj).map(t14 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14)))))))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10,
                                            final String key11, final Write<A> f11,
                                            final String key12, final Write<A> f12,
                                            final String key13, final Write<A> f13,
                                            final String key14, final Write<A> f14,
                                            final String key15, final Write<A> f15) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).flatMap(t10 -> f11.apply(obj).flatMap(t11 -> f12.apply(obj).flatMap(t12 -> f13.apply(obj).flatMap(t13 -> f14.apply(obj).flatMap(t14 -> f15.apply(obj).map(t15 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15))))))))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10,
                                            final String key11, final Write<A> f11,
                                            final String key12, final Write<A> f12,
                                            final String key13, final Write<A> f13,
                                            final String key14, final Write<A> f14,
                                            final String key15, final Write<A> f15,
                                            final String key16, final Write<A> f16) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).flatMap(t10 -> f11.apply(obj).flatMap(t11 -> f12.apply(obj).flatMap(t12 -> f13.apply(obj).flatMap(t13 -> f14.apply(obj).flatMap(t14 -> f15.apply(obj).flatMap(t15 -> f16.apply(obj).map(t16 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16)))))))))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10,
                                            final String key11, final Write<A> f11,
                                            final String key12, final Write<A> f12,
                                            final String key13, final Write<A> f13,
                                            final String key14, final Write<A> f14,
                                            final String key15, final Write<A> f15,
                                            final String key16, final Write<A> f16,
                                            final String key17, final Write<A> f17) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).flatMap(t10 -> f11.apply(obj).flatMap(t11 -> f12.apply(obj).flatMap(t12 -> f13.apply(obj).flatMap(t13 -> f14.apply(obj).flatMap(t14 -> f15.apply(obj).flatMap(t15 -> f16.apply(obj).flatMap(t16 -> f17.apply(obj).map(t17 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17))))))))))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10,
                                            final String key11, final Write<A> f11,
                                            final String key12, final Write<A> f12,
                                            final String key13, final Write<A> f13,
                                            final String key14, final Write<A> f14,
                                            final String key15, final Write<A> f15,
                                            final String key16, final Write<A> f16,
                                            final String key17, final Write<A> f17,
                                            final String key18, final Write<A> f18) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).flatMap(t10 -> f11.apply(obj).flatMap(t11 -> f12.apply(obj).flatMap(t12 -> f13.apply(obj).flatMap(t13 -> f14.apply(obj).flatMap(t14 -> f15.apply(obj).flatMap(t15 -> f16.apply(obj).flatMap(t16 -> f17.apply(obj).flatMap(t17 -> f18.apply(obj).map(t18 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18)))))))))))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10,
                                            final String key11, final Write<A> f11,
                                            final String key12, final Write<A> f12,
                                            final String key13, final Write<A> f13,
                                            final String key14, final Write<A> f14,
                                            final String key15, final Write<A> f15,
                                            final String key16, final Write<A> f16,
                                            final String key17, final Write<A> f17,
                                            final String key18, final Write<A> f18,
                                            final String key19, final Write<A> f19) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).flatMap(t10 -> f11.apply(obj).flatMap(t11 -> f12.apply(obj).flatMap(t12 -> f13.apply(obj).flatMap(t13 -> f14.apply(obj).flatMap(t14 -> f15.apply(obj).flatMap(t15 -> f16.apply(obj).flatMap(t16 -> f17.apply(obj).flatMap(t17 -> f18.apply(obj).flatMap(t18 -> f19.apply(obj).map(t19 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19))))))))))))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10,
                                            final String key11, final Write<A> f11,
                                            final String key12, final Write<A> f12,
                                            final String key13, final Write<A> f13,
                                            final String key14, final Write<A> f14,
                                            final String key15, final Write<A> f15,
                                            final String key16, final Write<A> f16,
                                            final String key17, final Write<A> f17,
                                            final String key18, final Write<A> f18,
                                            final String key19, final Write<A> f19,
                                            final String key20, final Write<A> f20) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).flatMap(t10 -> f11.apply(obj).flatMap(t11 -> f12.apply(obj).flatMap(t12 -> f13.apply(obj).flatMap(t13 -> f14.apply(obj).flatMap(t14 -> f15.apply(obj).flatMap(t15 -> f16.apply(obj).flatMap(t16 -> f17.apply(obj).flatMap(t17 -> f18.apply(obj).flatMap(t18 -> f19.apply(obj).flatMap(t19 -> f20.apply(obj).map(t20 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20)))))))))))))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10,
                                            final String key11, final Write<A> f11,
                                            final String key12, final Write<A> f12,
                                            final String key13, final Write<A> f13,
                                            final String key14, final Write<A> f14,
                                            final String key15, final Write<A> f15,
                                            final String key16, final Write<A> f16,
                                            final String key17, final Write<A> f17,
                                            final String key18, final Write<A> f18,
                                            final String key19, final Write<A> f19,
                                            final String key20, final Write<A> f20,
                                            final String key21, final Write<A> f21) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).flatMap(t10 -> f11.apply(obj).flatMap(t11 -> f12.apply(obj).flatMap(t12 -> f13.apply(obj).flatMap(t13 -> f14.apply(obj).flatMap(t14 -> f15.apply(obj).flatMap(t15 -> f16.apply(obj).flatMap(t16 -> f17.apply(obj).flatMap(t17 -> f18.apply(obj).flatMap(t18 -> f19.apply(obj).flatMap(t19 -> f20.apply(obj).flatMap(t20 -> f21.apply(obj).map(t21 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21))))))))))))))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10,
                                            final String key11, final Write<A> f11,
                                            final String key12, final Write<A> f12,
                                            final String key13, final Write<A> f13,
                                            final String key14, final Write<A> f14,
                                            final String key15, final Write<A> f15,
                                            final String key16, final Write<A> f16,
                                            final String key17, final Write<A> f17,
                                            final String key18, final Write<A> f18,
                                            final String key19, final Write<A> f19,
                                            final String key20, final Write<A> f20,
                                            final String key21, final Write<A> f21,
                                            final String key22, final Write<A> f22) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).flatMap(t10 -> f11.apply(obj).flatMap(t11 -> f12.apply(obj).flatMap(t12 -> f13.apply(obj).flatMap(t13 -> f14.apply(obj).flatMap(t14 -> f15.apply(obj).flatMap(t15 -> f16.apply(obj).flatMap(t16 -> f17.apply(obj).flatMap(t17 -> f18.apply(obj).flatMap(t18 -> f19.apply(obj).flatMap(t19 -> f20.apply(obj).flatMap(t20 -> f21.apply(obj).flatMap(t21 -> f22.apply(obj).map(t22 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22)))))))))))))))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10,
                                            final String key11, final Write<A> f11,
                                            final String key12, final Write<A> f12,
                                            final String key13, final Write<A> f13,
                                            final String key14, final Write<A> f14,
                                            final String key15, final Write<A> f15,
                                            final String key16, final Write<A> f16,
                                            final String key17, final Write<A> f17,
                                            final String key18, final Write<A> f18,
                                            final String key19, final Write<A> f19,
                                            final String key20, final Write<A> f20,
                                            final String key21, final Write<A> f21,
                                            final String key22, final Write<A> f22,
                                            final String key23, final Write<A> f23) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).flatMap(t10 -> f11.apply(obj).flatMap(t11 -> f12.apply(obj).flatMap(t12 -> f13.apply(obj).flatMap(t13 -> f14.apply(obj).flatMap(t14 -> f15.apply(obj).flatMap(t15 -> f16.apply(obj).flatMap(t16 -> f17.apply(obj).flatMap(t17 -> f18.apply(obj).flatMap(t18 -> f19.apply(obj).flatMap(t19 -> f20.apply(obj).flatMap(t20 -> f21.apply(obj).flatMap(t21 -> f22.apply(obj).flatMap(t22 -> f23.apply(obj).map(t23 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23))))))))))))))))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10,
                                            final String key11, final Write<A> f11,
                                            final String key12, final Write<A> f12,
                                            final String key13, final Write<A> f13,
                                            final String key14, final Write<A> f14,
                                            final String key15, final Write<A> f15,
                                            final String key16, final Write<A> f16,
                                            final String key17, final Write<A> f17,
                                            final String key18, final Write<A> f18,
                                            final String key19, final Write<A> f19,
                                            final String key20, final Write<A> f20,
                                            final String key21, final Write<A> f21,
                                            final String key22, final Write<A> f22,
                                            final String key23, final Write<A> f23,
                                            final String key24, final Write<A> f24) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).flatMap(t10 -> f11.apply(obj).flatMap(t11 -> f12.apply(obj).flatMap(t12 -> f13.apply(obj).flatMap(t13 -> f14.apply(obj).flatMap(t14 -> f15.apply(obj).flatMap(t15 -> f16.apply(obj).flatMap(t16 -> f17.apply(obj).flatMap(t17 -> f18.apply(obj).flatMap(t18 -> f19.apply(obj).flatMap(t19 -> f20.apply(obj).flatMap(t20 -> f21.apply(obj).flatMap(t21 -> f22.apply(obj).flatMap(t22 -> f23.apply(obj).flatMap(t23 -> f24.apply(obj).map(t24 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24)))))))))))))))))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10,
                                            final String key11, final Write<A> f11,
                                            final String key12, final Write<A> f12,
                                            final String key13, final Write<A> f13,
                                            final String key14, final Write<A> f14,
                                            final String key15, final Write<A> f15,
                                            final String key16, final Write<A> f16,
                                            final String key17, final Write<A> f17,
                                            final String key18, final Write<A> f18,
                                            final String key19, final Write<A> f19,
                                            final String key20, final Write<A> f20,
                                            final String key21, final Write<A> f21,
                                            final String key22, final Write<A> f22,
                                            final String key23, final Write<A> f23,
                                            final String key24, final Write<A> f24,
                                            final String key25, final Write<A> f25) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).flatMap(t10 -> f11.apply(obj).flatMap(t11 -> f12.apply(obj).flatMap(t12 -> f13.apply(obj).flatMap(t13 -> f14.apply(obj).flatMap(t14 -> f15.apply(obj).flatMap(t15 -> f16.apply(obj).flatMap(t16 -> f17.apply(obj).flatMap(t17 -> f18.apply(obj).flatMap(t18 -> f19.apply(obj).flatMap(t19 -> f20.apply(obj).flatMap(t20 -> f21.apply(obj).flatMap(t21 -> f22.apply(obj).flatMap(t22 -> f23.apply(obj).flatMap(t23 -> f24.apply(obj).flatMap(t24 -> f25.apply(obj).map(t25 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25))))))))))))))))))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10,
                                            final String key11, final Write<A> f11,
                                            final String key12, final Write<A> f12,
                                            final String key13, final Write<A> f13,
                                            final String key14, final Write<A> f14,
                                            final String key15, final Write<A> f15,
                                            final String key16, final Write<A> f16,
                                            final String key17, final Write<A> f17,
                                            final String key18, final Write<A> f18,
                                            final String key19, final Write<A> f19,
                                            final String key20, final Write<A> f20,
                                            final String key21, final Write<A> f21,
                                            final String key22, final Write<A> f22,
                                            final String key23, final Write<A> f23,
                                            final String key24, final Write<A> f24,
                                            final String key25, final Write<A> f25,
                                            final String key26, final Write<A> f26) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).flatMap(t10 -> f11.apply(obj).flatMap(t11 -> f12.apply(obj).flatMap(t12 -> f13.apply(obj).flatMap(t13 -> f14.apply(obj).flatMap(t14 -> f15.apply(obj).flatMap(t15 -> f16.apply(obj).flatMap(t16 -> f17.apply(obj).flatMap(t17 -> f18.apply(obj).flatMap(t18 -> f19.apply(obj).flatMap(t19 -> f20.apply(obj).flatMap(t20 -> f21.apply(obj).flatMap(t21 -> f22.apply(obj).flatMap(t22 -> f23.apply(obj).flatMap(t23 -> f24.apply(obj).flatMap(t24 -> f25.apply(obj).flatMap(t25 -> f26.apply(obj).map(t26 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25), entry(key26, t26)))))))))))))))))))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10,
                                            final String key11, final Write<A> f11,
                                            final String key12, final Write<A> f12,
                                            final String key13, final Write<A> f13,
                                            final String key14, final Write<A> f14,
                                            final String key15, final Write<A> f15,
                                            final String key16, final Write<A> f16,
                                            final String key17, final Write<A> f17,
                                            final String key18, final Write<A> f18,
                                            final String key19, final Write<A> f19,
                                            final String key20, final Write<A> f20,
                                            final String key21, final Write<A> f21,
                                            final String key22, final Write<A> f22,
                                            final String key23, final Write<A> f23,
                                            final String key24, final Write<A> f24,
                                            final String key25, final Write<A> f25,
                                            final String key26, final Write<A> f26,
                                            final String key27, final Write<A> f27) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).flatMap(t10 -> f11.apply(obj).flatMap(t11 -> f12.apply(obj).flatMap(t12 -> f13.apply(obj).flatMap(t13 -> f14.apply(obj).flatMap(t14 -> f15.apply(obj).flatMap(t15 -> f16.apply(obj).flatMap(t16 -> f17.apply(obj).flatMap(t17 -> f18.apply(obj).flatMap(t18 -> f19.apply(obj).flatMap(t19 -> f20.apply(obj).flatMap(t20 -> f21.apply(obj).flatMap(t21 -> f22.apply(obj).flatMap(t22 -> f23.apply(obj).flatMap(t23 -> f24.apply(obj).flatMap(t24 -> f25.apply(obj).flatMap(t25 -> f26.apply(obj).flatMap(t26 -> f27.apply(obj).map(t27 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25), entry(key26, t26), entry(key27, t27))))))))))))))))))))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10,
                                            final String key11, final Write<A> f11,
                                            final String key12, final Write<A> f12,
                                            final String key13, final Write<A> f13,
                                            final String key14, final Write<A> f14,
                                            final String key15, final Write<A> f15,
                                            final String key16, final Write<A> f16,
                                            final String key17, final Write<A> f17,
                                            final String key18, final Write<A> f18,
                                            final String key19, final Write<A> f19,
                                            final String key20, final Write<A> f20,
                                            final String key21, final Write<A> f21,
                                            final String key22, final Write<A> f22,
                                            final String key23, final Write<A> f23,
                                            final String key24, final Write<A> f24,
                                            final String key25, final Write<A> f25,
                                            final String key26, final Write<A> f26,
                                            final String key27, final Write<A> f27,
                                            final String key28, final Write<A> f28) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).flatMap(t10 -> f11.apply(obj).flatMap(t11 -> f12.apply(obj).flatMap(t12 -> f13.apply(obj).flatMap(t13 -> f14.apply(obj).flatMap(t14 -> f15.apply(obj).flatMap(t15 -> f16.apply(obj).flatMap(t16 -> f17.apply(obj).flatMap(t17 -> f18.apply(obj).flatMap(t18 -> f19.apply(obj).flatMap(t19 -> f20.apply(obj).flatMap(t20 -> f21.apply(obj).flatMap(t21 -> f22.apply(obj).flatMap(t22 -> f23.apply(obj).flatMap(t23 -> f24.apply(obj).flatMap(t24 -> f25.apply(obj).flatMap(t25 -> f26.apply(obj).flatMap(t26 -> f27.apply(obj).flatMap(t27 -> f28.apply(obj).map(t28 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25), entry(key26, t26), entry(key27, t27), entry(key28, t28)))))))))))))))))))))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10,
                                            final String key11, final Write<A> f11,
                                            final String key12, final Write<A> f12,
                                            final String key13, final Write<A> f13,
                                            final String key14, final Write<A> f14,
                                            final String key15, final Write<A> f15,
                                            final String key16, final Write<A> f16,
                                            final String key17, final Write<A> f17,
                                            final String key18, final Write<A> f18,
                                            final String key19, final Write<A> f19,
                                            final String key20, final Write<A> f20,
                                            final String key21, final Write<A> f21,
                                            final String key22, final Write<A> f22,
                                            final String key23, final Write<A> f23,
                                            final String key24, final Write<A> f24,
                                            final String key25, final Write<A> f25,
                                            final String key26, final Write<A> f26,
                                            final String key27, final Write<A> f27,
                                            final String key28, final Write<A> f28,
                                            final String key29, final Write<A> f29) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).flatMap(t10 -> f11.apply(obj).flatMap(t11 -> f12.apply(obj).flatMap(t12 -> f13.apply(obj).flatMap(t13 -> f14.apply(obj).flatMap(t14 -> f15.apply(obj).flatMap(t15 -> f16.apply(obj).flatMap(t16 -> f17.apply(obj).flatMap(t17 -> f18.apply(obj).flatMap(t18 -> f19.apply(obj).flatMap(t19 -> f20.apply(obj).flatMap(t20 -> f21.apply(obj).flatMap(t21 -> f22.apply(obj).flatMap(t22 -> f23.apply(obj).flatMap(t23 -> f24.apply(obj).flatMap(t24 -> f25.apply(obj).flatMap(t25 -> f26.apply(obj).flatMap(t26 -> f27.apply(obj).flatMap(t27 -> f28.apply(obj).flatMap(t28 -> f29.apply(obj).map(t29 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25), entry(key26, t26), entry(key27, t27), entry(key28, t28), entry(key29, t29))))))))))))))))))))))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10,
                                            final String key11, final Write<A> f11,
                                            final String key12, final Write<A> f12,
                                            final String key13, final Write<A> f13,
                                            final String key14, final Write<A> f14,
                                            final String key15, final Write<A> f15,
                                            final String key16, final Write<A> f16,
                                            final String key17, final Write<A> f17,
                                            final String key18, final Write<A> f18,
                                            final String key19, final Write<A> f19,
                                            final String key20, final Write<A> f20,
                                            final String key21, final Write<A> f21,
                                            final String key22, final Write<A> f22,
                                            final String key23, final Write<A> f23,
                                            final String key24, final Write<A> f24,
                                            final String key25, final Write<A> f25,
                                            final String key26, final Write<A> f26,
                                            final String key27, final Write<A> f27,
                                            final String key28, final Write<A> f28,
                                            final String key29, final Write<A> f29,
                                            final String key30, final Write<A> f30) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).flatMap(t10 -> f11.apply(obj).flatMap(t11 -> f12.apply(obj).flatMap(t12 -> f13.apply(obj).flatMap(t13 -> f14.apply(obj).flatMap(t14 -> f15.apply(obj).flatMap(t15 -> f16.apply(obj).flatMap(t16 -> f17.apply(obj).flatMap(t17 -> f18.apply(obj).flatMap(t18 -> f19.apply(obj).flatMap(t19 -> f20.apply(obj).flatMap(t20 -> f21.apply(obj).flatMap(t21 -> f22.apply(obj).flatMap(t22 -> f23.apply(obj).flatMap(t23 -> f24.apply(obj).flatMap(t24 -> f25.apply(obj).flatMap(t25 -> f26.apply(obj).flatMap(t26 -> f27.apply(obj).flatMap(t27 -> f28.apply(obj).flatMap(t28 -> f29.apply(obj).flatMap(t29 -> f30.apply(obj).map(t30 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25), entry(key26, t26), entry(key27, t27), entry(key28, t28), entry(key29, t29), entry(key30, t30)))))))))))))))))))))))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10,
                                            final String key11, final Write<A> f11,
                                            final String key12, final Write<A> f12,
                                            final String key13, final Write<A> f13,
                                            final String key14, final Write<A> f14,
                                            final String key15, final Write<A> f15,
                                            final String key16, final Write<A> f16,
                                            final String key17, final Write<A> f17,
                                            final String key18, final Write<A> f18,
                                            final String key19, final Write<A> f19,
                                            final String key20, final Write<A> f20,
                                            final String key21, final Write<A> f21,
                                            final String key22, final Write<A> f22,
                                            final String key23, final Write<A> f23,
                                            final String key24, final Write<A> f24,
                                            final String key25, final Write<A> f25,
                                            final String key26, final Write<A> f26,
                                            final String key27, final Write<A> f27,
                                            final String key28, final Write<A> f28,
                                            final String key29, final Write<A> f29,
                                            final String key30, final Write<A> f30,
                                            final String key31, final Write<A> f31) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).flatMap(t10 -> f11.apply(obj).flatMap(t11 -> f12.apply(obj).flatMap(t12 -> f13.apply(obj).flatMap(t13 -> f14.apply(obj).flatMap(t14 -> f15.apply(obj).flatMap(t15 -> f16.apply(obj).flatMap(t16 -> f17.apply(obj).flatMap(t17 -> f18.apply(obj).flatMap(t18 -> f19.apply(obj).flatMap(t19 -> f20.apply(obj).flatMap(t20 -> f21.apply(obj).flatMap(t21 -> f22.apply(obj).flatMap(t22 -> f23.apply(obj).flatMap(t23 -> f24.apply(obj).flatMap(t24 -> f25.apply(obj).flatMap(t25 -> f26.apply(obj).flatMap(t26 -> f27.apply(obj).flatMap(t27 -> f28.apply(obj).flatMap(t28 -> f29.apply(obj).flatMap(t29 -> f30.apply(obj).flatMap(t30 -> f31.apply(obj).map(t31 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25), entry(key26, t26), entry(key27, t27), entry(key28, t28), entry(key29, t29), entry(key30, t30), entry(key31, t31))))))))))))))))))))))))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10,
                                            final String key11, final Write<A> f11,
                                            final String key12, final Write<A> f12,
                                            final String key13, final Write<A> f13,
                                            final String key14, final Write<A> f14,
                                            final String key15, final Write<A> f15,
                                            final String key16, final Write<A> f16,
                                            final String key17, final Write<A> f17,
                                            final String key18, final Write<A> f18,
                                            final String key19, final Write<A> f19,
                                            final String key20, final Write<A> f20,
                                            final String key21, final Write<A> f21,
                                            final String key22, final Write<A> f22,
                                            final String key23, final Write<A> f23,
                                            final String key24, final Write<A> f24,
                                            final String key25, final Write<A> f25,
                                            final String key26, final Write<A> f26,
                                            final String key27, final Write<A> f27,
                                            final String key28, final Write<A> f28,
                                            final String key29, final Write<A> f29,
                                            final String key30, final Write<A> f30,
                                            final String key31, final Write<A> f31,
                                            final String key32, final Write<A> f32) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).flatMap(t10 -> f11.apply(obj).flatMap(t11 -> f12.apply(obj).flatMap(t12 -> f13.apply(obj).flatMap(t13 -> f14.apply(obj).flatMap(t14 -> f15.apply(obj).flatMap(t15 -> f16.apply(obj).flatMap(t16 -> f17.apply(obj).flatMap(t17 -> f18.apply(obj).flatMap(t18 -> f19.apply(obj).flatMap(t19 -> f20.apply(obj).flatMap(t20 -> f21.apply(obj).flatMap(t21 -> f22.apply(obj).flatMap(t22 -> f23.apply(obj).flatMap(t23 -> f24.apply(obj).flatMap(t24 -> f25.apply(obj).flatMap(t25 -> f26.apply(obj).flatMap(t26 -> f27.apply(obj).flatMap(t27 -> f28.apply(obj).flatMap(t28 -> f29.apply(obj).flatMap(t29 -> f30.apply(obj).flatMap(t30 -> f31.apply(obj).flatMap(t31 -> f32.apply(obj).map(t32 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25), entry(key26, t26), entry(key27, t27), entry(key28, t28), entry(key29, t29), entry(key30, t30), entry(key31, t31), entry(key32, t32)))))))))))))))))))))))))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10,
                                            final String key11, final Write<A> f11,
                                            final String key12, final Write<A> f12,
                                            final String key13, final Write<A> f13,
                                            final String key14, final Write<A> f14,
                                            final String key15, final Write<A> f15,
                                            final String key16, final Write<A> f16,
                                            final String key17, final Write<A> f17,
                                            final String key18, final Write<A> f18,
                                            final String key19, final Write<A> f19,
                                            final String key20, final Write<A> f20,
                                            final String key21, final Write<A> f21,
                                            final String key22, final Write<A> f22,
                                            final String key23, final Write<A> f23,
                                            final String key24, final Write<A> f24,
                                            final String key25, final Write<A> f25,
                                            final String key26, final Write<A> f26,
                                            final String key27, final Write<A> f27,
                                            final String key28, final Write<A> f28,
                                            final String key29, final Write<A> f29,
                                            final String key30, final Write<A> f30,
                                            final String key31, final Write<A> f31,
                                            final String key32, final Write<A> f32,
                                            final String key33, final Write<A> f33) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).flatMap(t10 -> f11.apply(obj).flatMap(t11 -> f12.apply(obj).flatMap(t12 -> f13.apply(obj).flatMap(t13 -> f14.apply(obj).flatMap(t14 -> f15.apply(obj).flatMap(t15 -> f16.apply(obj).flatMap(t16 -> f17.apply(obj).flatMap(t17 -> f18.apply(obj).flatMap(t18 -> f19.apply(obj).flatMap(t19 -> f20.apply(obj).flatMap(t20 -> f21.apply(obj).flatMap(t21 -> f22.apply(obj).flatMap(t22 -> f23.apply(obj).flatMap(t23 -> f24.apply(obj).flatMap(t24 -> f25.apply(obj).flatMap(t25 -> f26.apply(obj).flatMap(t26 -> f27.apply(obj).flatMap(t27 -> f28.apply(obj).flatMap(t28 -> f29.apply(obj).flatMap(t29 -> f30.apply(obj).flatMap(t30 -> f31.apply(obj).flatMap(t31 -> f32.apply(obj).flatMap(t32 -> f33.apply(obj).map(t33 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25), entry(key26, t26), entry(key27, t27), entry(key28, t28), entry(key29, t29), entry(key30, t30), entry(key31, t31), entry(key32, t32), entry(key33, t33))))))))))))))))))))))))))))))))))));
    }

    public final Either<String, Json> using(final String key0, final Write<A> f0,
                                            final String key1, final Write<A> f1,
                                            final String key2, final Write<A> f2,
                                            final String key3, final Write<A> f3,
                                            final String key4, final Write<A> f4,
                                            final String key5, final Write<A> f5,
                                            final String key6, final Write<A> f6,
                                            final String key7, final Write<A> f7,
                                            final String key8, final Write<A> f8,
                                            final String key9, final Write<A> f9,
                                            final String key10, final Write<A> f10,
                                            final String key11, final Write<A> f11,
                                            final String key12, final Write<A> f12,
                                            final String key13, final Write<A> f13,
                                            final String key14, final Write<A> f14,
                                            final String key15, final Write<A> f15,
                                            final String key16, final Write<A> f16,
                                            final String key17, final Write<A> f17,
                                            final String key18, final Write<A> f18,
                                            final String key19, final Write<A> f19,
                                            final String key20, final Write<A> f20,
                                            final String key21, final Write<A> f21,
                                            final String key22, final Write<A> f22,
                                            final String key23, final Write<A> f23,
                                            final String key24, final Write<A> f24,
                                            final String key25, final Write<A> f25,
                                            final String key26, final Write<A> f26,
                                            final String key27, final Write<A> f27,
                                            final String key28, final Write<A> f28,
                                            final String key29, final Write<A> f29,
                                            final String key30, final Write<A> f30,
                                            final String key31, final Write<A> f31,
                                            final String key32, final Write<A> f32,
                                            final String key33, final Write<A> f33,
                                            final String key34, final Write<A> f34) {
        return f0.apply(obj).flatMap(t0 -> f1.apply(obj).flatMap(t1 -> f2.apply(obj).flatMap(t2 -> f3.apply(obj).flatMap(t3 -> f4.apply(obj).flatMap(t4 -> f5.apply(obj).flatMap(t5 -> f6.apply(obj).flatMap(t6 -> f7.apply(obj).flatMap(t7 -> f8.apply(obj).flatMap(t8 -> f9.apply(obj).flatMap(t9 -> f10.apply(obj).flatMap(t10 -> f11.apply(obj).flatMap(t11 -> f12.apply(obj).flatMap(t12 -> f13.apply(obj).flatMap(t13 -> f14.apply(obj).flatMap(t14 -> f15.apply(obj).flatMap(t15 -> f16.apply(obj).flatMap(t16 -> f17.apply(obj).flatMap(t17 -> f18.apply(obj).flatMap(t18 -> f19.apply(obj).flatMap(t19 -> f20.apply(obj).flatMap(t20 -> f21.apply(obj).flatMap(t21 -> f22.apply(obj).flatMap(t22 -> f23.apply(obj).flatMap(t23 -> f24.apply(obj).flatMap(t24 -> f25.apply(obj).flatMap(t25 -> f26.apply(obj).flatMap(t26 -> f27.apply(obj).flatMap(t27 -> f28.apply(obj).flatMap(t28 -> f29.apply(obj).flatMap(t29 -> f30.apply(obj).flatMap(t30 -> f31.apply(obj).flatMap(t31 -> f32.apply(obj).flatMap(t32 -> f33.apply(obj).flatMap(t33 -> f34.apply(obj).map(t34 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25), entry(key26, t26), entry(key27, t27), entry(key28, t28), entry(key29, t29), entry(key30, t30), entry(key31, t31), entry(key32, t32), entry(key33, t33), entry(key34, t34)))))))))))))))))))))))))))))))))))));
    }
}