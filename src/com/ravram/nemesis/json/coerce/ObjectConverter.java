package com.ravram.nemesis.json.coerce;

import com.ravram.nemesis.json.model.JObj;
import com.ravram.nemesis.json.model.Json;
import com.ravram.nemesis.util.error.Either;
import io.lacuna.bifurcan.Map;
import io.lacuna.bifurcan.Maps.Entry;

import java.util.Arrays;

public final class ObjectConverter<A> {
    private final A obj;

    private Entry<String, Json> entry(final String key, final Json value) {
        return new Entry<>("\"" + key + "\"", value);
    }

    public ObjectConverter(final A obj) {
        this.obj = obj;
    }

    @SafeVarargs
    private final JObj mapFrom(final Entry<String, Json>... entries) {
        return new JObj(Map.from(Arrays.asList(entries)));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0) {
        return f0.convert(obj).map(t0 -> mapFrom(entry(key0, t0)));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).map(t1 -> mapFrom(entry(key0, t0), entry(key1, t1))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).map(t2 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2)))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).map(t3 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).map(t4 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4)))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).map(t5 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).map(t6 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6)))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).map(t7 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).map(t8 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8)))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).map(t9 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).map(t10 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10)))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10,
                                           final String key11, final Convert<A, Json> f11) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).flatMap(t10 -> f11.convert(obj).map(t11 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11))))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10,
                                           final String key11, final Convert<A, Json> f11,
                                           final String key12, final Convert<A, Json> f12) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).flatMap(t10 -> f11.convert(obj).flatMap(t11 -> f12.convert(obj).map(t12 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12)))))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10,
                                           final String key11, final Convert<A, Json> f11,
                                           final String key12, final Convert<A, Json> f12,
                                           final String key13, final Convert<A, Json> f13) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).flatMap(t10 -> f11.convert(obj).flatMap(t11 -> f12.convert(obj).flatMap(t12 -> f13.convert(obj).map(t13 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13))))))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10,
                                           final String key11, final Convert<A, Json> f11,
                                           final String key12, final Convert<A, Json> f12,
                                           final String key13, final Convert<A, Json> f13,
                                           final String key14, final Convert<A, Json> f14) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).flatMap(t10 -> f11.convert(obj).flatMap(t11 -> f12.convert(obj).flatMap(t12 -> f13.convert(obj).flatMap(t13 -> f14.convert(obj).map(t14 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14)))))))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10,
                                           final String key11, final Convert<A, Json> f11,
                                           final String key12, final Convert<A, Json> f12,
                                           final String key13, final Convert<A, Json> f13,
                                           final String key14, final Convert<A, Json> f14,
                                           final String key15, final Convert<A, Json> f15) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).flatMap(t10 -> f11.convert(obj).flatMap(t11 -> f12.convert(obj).flatMap(t12 -> f13.convert(obj).flatMap(t13 -> f14.convert(obj).flatMap(t14 -> f15.convert(obj).map(t15 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15))))))))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10,
                                           final String key11, final Convert<A, Json> f11,
                                           final String key12, final Convert<A, Json> f12,
                                           final String key13, final Convert<A, Json> f13,
                                           final String key14, final Convert<A, Json> f14,
                                           final String key15, final Convert<A, Json> f15,
                                           final String key16, final Convert<A, Json> f16) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).flatMap(t10 -> f11.convert(obj).flatMap(t11 -> f12.convert(obj).flatMap(t12 -> f13.convert(obj).flatMap(t13 -> f14.convert(obj).flatMap(t14 -> f15.convert(obj).flatMap(t15 -> f16.convert(obj).map(t16 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16)))))))))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10,
                                           final String key11, final Convert<A, Json> f11,
                                           final String key12, final Convert<A, Json> f12,
                                           final String key13, final Convert<A, Json> f13,
                                           final String key14, final Convert<A, Json> f14,
                                           final String key15, final Convert<A, Json> f15,
                                           final String key16, final Convert<A, Json> f16,
                                           final String key17, final Convert<A, Json> f17) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).flatMap(t10 -> f11.convert(obj).flatMap(t11 -> f12.convert(obj).flatMap(t12 -> f13.convert(obj).flatMap(t13 -> f14.convert(obj).flatMap(t14 -> f15.convert(obj).flatMap(t15 -> f16.convert(obj).flatMap(t16 -> f17.convert(obj).map(t17 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17))))))))))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10,
                                           final String key11, final Convert<A, Json> f11,
                                           final String key12, final Convert<A, Json> f12,
                                           final String key13, final Convert<A, Json> f13,
                                           final String key14, final Convert<A, Json> f14,
                                           final String key15, final Convert<A, Json> f15,
                                           final String key16, final Convert<A, Json> f16,
                                           final String key17, final Convert<A, Json> f17,
                                           final String key18, final Convert<A, Json> f18) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).flatMap(t10 -> f11.convert(obj).flatMap(t11 -> f12.convert(obj).flatMap(t12 -> f13.convert(obj).flatMap(t13 -> f14.convert(obj).flatMap(t14 -> f15.convert(obj).flatMap(t15 -> f16.convert(obj).flatMap(t16 -> f17.convert(obj).flatMap(t17 -> f18.convert(obj).map(t18 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18)))))))))))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10,
                                           final String key11, final Convert<A, Json> f11,
                                           final String key12, final Convert<A, Json> f12,
                                           final String key13, final Convert<A, Json> f13,
                                           final String key14, final Convert<A, Json> f14,
                                           final String key15, final Convert<A, Json> f15,
                                           final String key16, final Convert<A, Json> f16,
                                           final String key17, final Convert<A, Json> f17,
                                           final String key18, final Convert<A, Json> f18,
                                           final String key19, final Convert<A, Json> f19) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).flatMap(t10 -> f11.convert(obj).flatMap(t11 -> f12.convert(obj).flatMap(t12 -> f13.convert(obj).flatMap(t13 -> f14.convert(obj).flatMap(t14 -> f15.convert(obj).flatMap(t15 -> f16.convert(obj).flatMap(t16 -> f17.convert(obj).flatMap(t17 -> f18.convert(obj).flatMap(t18 -> f19.convert(obj).map(t19 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19))))))))))))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10,
                                           final String key11, final Convert<A, Json> f11,
                                           final String key12, final Convert<A, Json> f12,
                                           final String key13, final Convert<A, Json> f13,
                                           final String key14, final Convert<A, Json> f14,
                                           final String key15, final Convert<A, Json> f15,
                                           final String key16, final Convert<A, Json> f16,
                                           final String key17, final Convert<A, Json> f17,
                                           final String key18, final Convert<A, Json> f18,
                                           final String key19, final Convert<A, Json> f19,
                                           final String key20, final Convert<A, Json> f20) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).flatMap(t10 -> f11.convert(obj).flatMap(t11 -> f12.convert(obj).flatMap(t12 -> f13.convert(obj).flatMap(t13 -> f14.convert(obj).flatMap(t14 -> f15.convert(obj).flatMap(t15 -> f16.convert(obj).flatMap(t16 -> f17.convert(obj).flatMap(t17 -> f18.convert(obj).flatMap(t18 -> f19.convert(obj).flatMap(t19 -> f20.convert(obj).map(t20 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20)))))))))))))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10,
                                           final String key11, final Convert<A, Json> f11,
                                           final String key12, final Convert<A, Json> f12,
                                           final String key13, final Convert<A, Json> f13,
                                           final String key14, final Convert<A, Json> f14,
                                           final String key15, final Convert<A, Json> f15,
                                           final String key16, final Convert<A, Json> f16,
                                           final String key17, final Convert<A, Json> f17,
                                           final String key18, final Convert<A, Json> f18,
                                           final String key19, final Convert<A, Json> f19,
                                           final String key20, final Convert<A, Json> f20,
                                           final String key21, final Convert<A, Json> f21) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).flatMap(t10 -> f11.convert(obj).flatMap(t11 -> f12.convert(obj).flatMap(t12 -> f13.convert(obj).flatMap(t13 -> f14.convert(obj).flatMap(t14 -> f15.convert(obj).flatMap(t15 -> f16.convert(obj).flatMap(t16 -> f17.convert(obj).flatMap(t17 -> f18.convert(obj).flatMap(t18 -> f19.convert(obj).flatMap(t19 -> f20.convert(obj).flatMap(t20 -> f21.convert(obj).map(t21 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21))))))))))))))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10,
                                           final String key11, final Convert<A, Json> f11,
                                           final String key12, final Convert<A, Json> f12,
                                           final String key13, final Convert<A, Json> f13,
                                           final String key14, final Convert<A, Json> f14,
                                           final String key15, final Convert<A, Json> f15,
                                           final String key16, final Convert<A, Json> f16,
                                           final String key17, final Convert<A, Json> f17,
                                           final String key18, final Convert<A, Json> f18,
                                           final String key19, final Convert<A, Json> f19,
                                           final String key20, final Convert<A, Json> f20,
                                           final String key21, final Convert<A, Json> f21,
                                           final String key22, final Convert<A, Json> f22) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).flatMap(t10 -> f11.convert(obj).flatMap(t11 -> f12.convert(obj).flatMap(t12 -> f13.convert(obj).flatMap(t13 -> f14.convert(obj).flatMap(t14 -> f15.convert(obj).flatMap(t15 -> f16.convert(obj).flatMap(t16 -> f17.convert(obj).flatMap(t17 -> f18.convert(obj).flatMap(t18 -> f19.convert(obj).flatMap(t19 -> f20.convert(obj).flatMap(t20 -> f21.convert(obj).flatMap(t21 -> f22.convert(obj).map(t22 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22)))))))))))))))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10,
                                           final String key11, final Convert<A, Json> f11,
                                           final String key12, final Convert<A, Json> f12,
                                           final String key13, final Convert<A, Json> f13,
                                           final String key14, final Convert<A, Json> f14,
                                           final String key15, final Convert<A, Json> f15,
                                           final String key16, final Convert<A, Json> f16,
                                           final String key17, final Convert<A, Json> f17,
                                           final String key18, final Convert<A, Json> f18,
                                           final String key19, final Convert<A, Json> f19,
                                           final String key20, final Convert<A, Json> f20,
                                           final String key21, final Convert<A, Json> f21,
                                           final String key22, final Convert<A, Json> f22,
                                           final String key23, final Convert<A, Json> f23) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).flatMap(t10 -> f11.convert(obj).flatMap(t11 -> f12.convert(obj).flatMap(t12 -> f13.convert(obj).flatMap(t13 -> f14.convert(obj).flatMap(t14 -> f15.convert(obj).flatMap(t15 -> f16.convert(obj).flatMap(t16 -> f17.convert(obj).flatMap(t17 -> f18.convert(obj).flatMap(t18 -> f19.convert(obj).flatMap(t19 -> f20.convert(obj).flatMap(t20 -> f21.convert(obj).flatMap(t21 -> f22.convert(obj).flatMap(t22 -> f23.convert(obj).map(t23 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23))))))))))))))))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10,
                                           final String key11, final Convert<A, Json> f11,
                                           final String key12, final Convert<A, Json> f12,
                                           final String key13, final Convert<A, Json> f13,
                                           final String key14, final Convert<A, Json> f14,
                                           final String key15, final Convert<A, Json> f15,
                                           final String key16, final Convert<A, Json> f16,
                                           final String key17, final Convert<A, Json> f17,
                                           final String key18, final Convert<A, Json> f18,
                                           final String key19, final Convert<A, Json> f19,
                                           final String key20, final Convert<A, Json> f20,
                                           final String key21, final Convert<A, Json> f21,
                                           final String key22, final Convert<A, Json> f22,
                                           final String key23, final Convert<A, Json> f23,
                                           final String key24, final Convert<A, Json> f24) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).flatMap(t10 -> f11.convert(obj).flatMap(t11 -> f12.convert(obj).flatMap(t12 -> f13.convert(obj).flatMap(t13 -> f14.convert(obj).flatMap(t14 -> f15.convert(obj).flatMap(t15 -> f16.convert(obj).flatMap(t16 -> f17.convert(obj).flatMap(t17 -> f18.convert(obj).flatMap(t18 -> f19.convert(obj).flatMap(t19 -> f20.convert(obj).flatMap(t20 -> f21.convert(obj).flatMap(t21 -> f22.convert(obj).flatMap(t22 -> f23.convert(obj).flatMap(t23 -> f24.convert(obj).map(t24 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24)))))))))))))))))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10,
                                           final String key11, final Convert<A, Json> f11,
                                           final String key12, final Convert<A, Json> f12,
                                           final String key13, final Convert<A, Json> f13,
                                           final String key14, final Convert<A, Json> f14,
                                           final String key15, final Convert<A, Json> f15,
                                           final String key16, final Convert<A, Json> f16,
                                           final String key17, final Convert<A, Json> f17,
                                           final String key18, final Convert<A, Json> f18,
                                           final String key19, final Convert<A, Json> f19,
                                           final String key20, final Convert<A, Json> f20,
                                           final String key21, final Convert<A, Json> f21,
                                           final String key22, final Convert<A, Json> f22,
                                           final String key23, final Convert<A, Json> f23,
                                           final String key24, final Convert<A, Json> f24,
                                           final String key25, final Convert<A, Json> f25) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).flatMap(t10 -> f11.convert(obj).flatMap(t11 -> f12.convert(obj).flatMap(t12 -> f13.convert(obj).flatMap(t13 -> f14.convert(obj).flatMap(t14 -> f15.convert(obj).flatMap(t15 -> f16.convert(obj).flatMap(t16 -> f17.convert(obj).flatMap(t17 -> f18.convert(obj).flatMap(t18 -> f19.convert(obj).flatMap(t19 -> f20.convert(obj).flatMap(t20 -> f21.convert(obj).flatMap(t21 -> f22.convert(obj).flatMap(t22 -> f23.convert(obj).flatMap(t23 -> f24.convert(obj).flatMap(t24 -> f25.convert(obj).map(t25 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25))))))))))))))))))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10,
                                           final String key11, final Convert<A, Json> f11,
                                           final String key12, final Convert<A, Json> f12,
                                           final String key13, final Convert<A, Json> f13,
                                           final String key14, final Convert<A, Json> f14,
                                           final String key15, final Convert<A, Json> f15,
                                           final String key16, final Convert<A, Json> f16,
                                           final String key17, final Convert<A, Json> f17,
                                           final String key18, final Convert<A, Json> f18,
                                           final String key19, final Convert<A, Json> f19,
                                           final String key20, final Convert<A, Json> f20,
                                           final String key21, final Convert<A, Json> f21,
                                           final String key22, final Convert<A, Json> f22,
                                           final String key23, final Convert<A, Json> f23,
                                           final String key24, final Convert<A, Json> f24,
                                           final String key25, final Convert<A, Json> f25,
                                           final String key26, final Convert<A, Json> f26) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).flatMap(t10 -> f11.convert(obj).flatMap(t11 -> f12.convert(obj).flatMap(t12 -> f13.convert(obj).flatMap(t13 -> f14.convert(obj).flatMap(t14 -> f15.convert(obj).flatMap(t15 -> f16.convert(obj).flatMap(t16 -> f17.convert(obj).flatMap(t17 -> f18.convert(obj).flatMap(t18 -> f19.convert(obj).flatMap(t19 -> f20.convert(obj).flatMap(t20 -> f21.convert(obj).flatMap(t21 -> f22.convert(obj).flatMap(t22 -> f23.convert(obj).flatMap(t23 -> f24.convert(obj).flatMap(t24 -> f25.convert(obj).flatMap(t25 -> f26.convert(obj).map(t26 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25), entry(key26, t26)))))))))))))))))))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10,
                                           final String key11, final Convert<A, Json> f11,
                                           final String key12, final Convert<A, Json> f12,
                                           final String key13, final Convert<A, Json> f13,
                                           final String key14, final Convert<A, Json> f14,
                                           final String key15, final Convert<A, Json> f15,
                                           final String key16, final Convert<A, Json> f16,
                                           final String key17, final Convert<A, Json> f17,
                                           final String key18, final Convert<A, Json> f18,
                                           final String key19, final Convert<A, Json> f19,
                                           final String key20, final Convert<A, Json> f20,
                                           final String key21, final Convert<A, Json> f21,
                                           final String key22, final Convert<A, Json> f22,
                                           final String key23, final Convert<A, Json> f23,
                                           final String key24, final Convert<A, Json> f24,
                                           final String key25, final Convert<A, Json> f25,
                                           final String key26, final Convert<A, Json> f26,
                                           final String key27, final Convert<A, Json> f27) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).flatMap(t10 -> f11.convert(obj).flatMap(t11 -> f12.convert(obj).flatMap(t12 -> f13.convert(obj).flatMap(t13 -> f14.convert(obj).flatMap(t14 -> f15.convert(obj).flatMap(t15 -> f16.convert(obj).flatMap(t16 -> f17.convert(obj).flatMap(t17 -> f18.convert(obj).flatMap(t18 -> f19.convert(obj).flatMap(t19 -> f20.convert(obj).flatMap(t20 -> f21.convert(obj).flatMap(t21 -> f22.convert(obj).flatMap(t22 -> f23.convert(obj).flatMap(t23 -> f24.convert(obj).flatMap(t24 -> f25.convert(obj).flatMap(t25 -> f26.convert(obj).flatMap(t26 -> f27.convert(obj).map(t27 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25), entry(key26, t26), entry(key27, t27))))))))))))))))))))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10,
                                           final String key11, final Convert<A, Json> f11,
                                           final String key12, final Convert<A, Json> f12,
                                           final String key13, final Convert<A, Json> f13,
                                           final String key14, final Convert<A, Json> f14,
                                           final String key15, final Convert<A, Json> f15,
                                           final String key16, final Convert<A, Json> f16,
                                           final String key17, final Convert<A, Json> f17,
                                           final String key18, final Convert<A, Json> f18,
                                           final String key19, final Convert<A, Json> f19,
                                           final String key20, final Convert<A, Json> f20,
                                           final String key21, final Convert<A, Json> f21,
                                           final String key22, final Convert<A, Json> f22,
                                           final String key23, final Convert<A, Json> f23,
                                           final String key24, final Convert<A, Json> f24,
                                           final String key25, final Convert<A, Json> f25,
                                           final String key26, final Convert<A, Json> f26,
                                           final String key27, final Convert<A, Json> f27,
                                           final String key28, final Convert<A, Json> f28) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).flatMap(t10 -> f11.convert(obj).flatMap(t11 -> f12.convert(obj).flatMap(t12 -> f13.convert(obj).flatMap(t13 -> f14.convert(obj).flatMap(t14 -> f15.convert(obj).flatMap(t15 -> f16.convert(obj).flatMap(t16 -> f17.convert(obj).flatMap(t17 -> f18.convert(obj).flatMap(t18 -> f19.convert(obj).flatMap(t19 -> f20.convert(obj).flatMap(t20 -> f21.convert(obj).flatMap(t21 -> f22.convert(obj).flatMap(t22 -> f23.convert(obj).flatMap(t23 -> f24.convert(obj).flatMap(t24 -> f25.convert(obj).flatMap(t25 -> f26.convert(obj).flatMap(t26 -> f27.convert(obj).flatMap(t27 -> f28.convert(obj).map(t28 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25), entry(key26, t26), entry(key27, t27), entry(key28, t28)))))))))))))))))))))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10,
                                           final String key11, final Convert<A, Json> f11,
                                           final String key12, final Convert<A, Json> f12,
                                           final String key13, final Convert<A, Json> f13,
                                           final String key14, final Convert<A, Json> f14,
                                           final String key15, final Convert<A, Json> f15,
                                           final String key16, final Convert<A, Json> f16,
                                           final String key17, final Convert<A, Json> f17,
                                           final String key18, final Convert<A, Json> f18,
                                           final String key19, final Convert<A, Json> f19,
                                           final String key20, final Convert<A, Json> f20,
                                           final String key21, final Convert<A, Json> f21,
                                           final String key22, final Convert<A, Json> f22,
                                           final String key23, final Convert<A, Json> f23,
                                           final String key24, final Convert<A, Json> f24,
                                           final String key25, final Convert<A, Json> f25,
                                           final String key26, final Convert<A, Json> f26,
                                           final String key27, final Convert<A, Json> f27,
                                           final String key28, final Convert<A, Json> f28,
                                           final String key29, final Convert<A, Json> f29) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).flatMap(t10 -> f11.convert(obj).flatMap(t11 -> f12.convert(obj).flatMap(t12 -> f13.convert(obj).flatMap(t13 -> f14.convert(obj).flatMap(t14 -> f15.convert(obj).flatMap(t15 -> f16.convert(obj).flatMap(t16 -> f17.convert(obj).flatMap(t17 -> f18.convert(obj).flatMap(t18 -> f19.convert(obj).flatMap(t19 -> f20.convert(obj).flatMap(t20 -> f21.convert(obj).flatMap(t21 -> f22.convert(obj).flatMap(t22 -> f23.convert(obj).flatMap(t23 -> f24.convert(obj).flatMap(t24 -> f25.convert(obj).flatMap(t25 -> f26.convert(obj).flatMap(t26 -> f27.convert(obj).flatMap(t27 -> f28.convert(obj).flatMap(t28 -> f29.convert(obj).map(t29 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25), entry(key26, t26), entry(key27, t27), entry(key28, t28), entry(key29, t29))))))))))))))))))))))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10,
                                           final String key11, final Convert<A, Json> f11,
                                           final String key12, final Convert<A, Json> f12,
                                           final String key13, final Convert<A, Json> f13,
                                           final String key14, final Convert<A, Json> f14,
                                           final String key15, final Convert<A, Json> f15,
                                           final String key16, final Convert<A, Json> f16,
                                           final String key17, final Convert<A, Json> f17,
                                           final String key18, final Convert<A, Json> f18,
                                           final String key19, final Convert<A, Json> f19,
                                           final String key20, final Convert<A, Json> f20,
                                           final String key21, final Convert<A, Json> f21,
                                           final String key22, final Convert<A, Json> f22,
                                           final String key23, final Convert<A, Json> f23,
                                           final String key24, final Convert<A, Json> f24,
                                           final String key25, final Convert<A, Json> f25,
                                           final String key26, final Convert<A, Json> f26,
                                           final String key27, final Convert<A, Json> f27,
                                           final String key28, final Convert<A, Json> f28,
                                           final String key29, final Convert<A, Json> f29,
                                           final String key30, final Convert<A, Json> f30) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).flatMap(t10 -> f11.convert(obj).flatMap(t11 -> f12.convert(obj).flatMap(t12 -> f13.convert(obj).flatMap(t13 -> f14.convert(obj).flatMap(t14 -> f15.convert(obj).flatMap(t15 -> f16.convert(obj).flatMap(t16 -> f17.convert(obj).flatMap(t17 -> f18.convert(obj).flatMap(t18 -> f19.convert(obj).flatMap(t19 -> f20.convert(obj).flatMap(t20 -> f21.convert(obj).flatMap(t21 -> f22.convert(obj).flatMap(t22 -> f23.convert(obj).flatMap(t23 -> f24.convert(obj).flatMap(t24 -> f25.convert(obj).flatMap(t25 -> f26.convert(obj).flatMap(t26 -> f27.convert(obj).flatMap(t27 -> f28.convert(obj).flatMap(t28 -> f29.convert(obj).flatMap(t29 -> f30.convert(obj).map(t30 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25), entry(key26, t26), entry(key27, t27), entry(key28, t28), entry(key29, t29), entry(key30, t30)))))))))))))))))))))))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10,
                                           final String key11, final Convert<A, Json> f11,
                                           final String key12, final Convert<A, Json> f12,
                                           final String key13, final Convert<A, Json> f13,
                                           final String key14, final Convert<A, Json> f14,
                                           final String key15, final Convert<A, Json> f15,
                                           final String key16, final Convert<A, Json> f16,
                                           final String key17, final Convert<A, Json> f17,
                                           final String key18, final Convert<A, Json> f18,
                                           final String key19, final Convert<A, Json> f19,
                                           final String key20, final Convert<A, Json> f20,
                                           final String key21, final Convert<A, Json> f21,
                                           final String key22, final Convert<A, Json> f22,
                                           final String key23, final Convert<A, Json> f23,
                                           final String key24, final Convert<A, Json> f24,
                                           final String key25, final Convert<A, Json> f25,
                                           final String key26, final Convert<A, Json> f26,
                                           final String key27, final Convert<A, Json> f27,
                                           final String key28, final Convert<A, Json> f28,
                                           final String key29, final Convert<A, Json> f29,
                                           final String key30, final Convert<A, Json> f30,
                                           final String key31, final Convert<A, Json> f31) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).flatMap(t10 -> f11.convert(obj).flatMap(t11 -> f12.convert(obj).flatMap(t12 -> f13.convert(obj).flatMap(t13 -> f14.convert(obj).flatMap(t14 -> f15.convert(obj).flatMap(t15 -> f16.convert(obj).flatMap(t16 -> f17.convert(obj).flatMap(t17 -> f18.convert(obj).flatMap(t18 -> f19.convert(obj).flatMap(t19 -> f20.convert(obj).flatMap(t20 -> f21.convert(obj).flatMap(t21 -> f22.convert(obj).flatMap(t22 -> f23.convert(obj).flatMap(t23 -> f24.convert(obj).flatMap(t24 -> f25.convert(obj).flatMap(t25 -> f26.convert(obj).flatMap(t26 -> f27.convert(obj).flatMap(t27 -> f28.convert(obj).flatMap(t28 -> f29.convert(obj).flatMap(t29 -> f30.convert(obj).flatMap(t30 -> f31.convert(obj).map(t31 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25), entry(key26, t26), entry(key27, t27), entry(key28, t28), entry(key29, t29), entry(key30, t30), entry(key31, t31))))))))))))))))))))))))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10,
                                           final String key11, final Convert<A, Json> f11,
                                           final String key12, final Convert<A, Json> f12,
                                           final String key13, final Convert<A, Json> f13,
                                           final String key14, final Convert<A, Json> f14,
                                           final String key15, final Convert<A, Json> f15,
                                           final String key16, final Convert<A, Json> f16,
                                           final String key17, final Convert<A, Json> f17,
                                           final String key18, final Convert<A, Json> f18,
                                           final String key19, final Convert<A, Json> f19,
                                           final String key20, final Convert<A, Json> f20,
                                           final String key21, final Convert<A, Json> f21,
                                           final String key22, final Convert<A, Json> f22,
                                           final String key23, final Convert<A, Json> f23,
                                           final String key24, final Convert<A, Json> f24,
                                           final String key25, final Convert<A, Json> f25,
                                           final String key26, final Convert<A, Json> f26,
                                           final String key27, final Convert<A, Json> f27,
                                           final String key28, final Convert<A, Json> f28,
                                           final String key29, final Convert<A, Json> f29,
                                           final String key30, final Convert<A, Json> f30,
                                           final String key31, final Convert<A, Json> f31,
                                           final String key32, final Convert<A, Json> f32) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).flatMap(t10 -> f11.convert(obj).flatMap(t11 -> f12.convert(obj).flatMap(t12 -> f13.convert(obj).flatMap(t13 -> f14.convert(obj).flatMap(t14 -> f15.convert(obj).flatMap(t15 -> f16.convert(obj).flatMap(t16 -> f17.convert(obj).flatMap(t17 -> f18.convert(obj).flatMap(t18 -> f19.convert(obj).flatMap(t19 -> f20.convert(obj).flatMap(t20 -> f21.convert(obj).flatMap(t21 -> f22.convert(obj).flatMap(t22 -> f23.convert(obj).flatMap(t23 -> f24.convert(obj).flatMap(t24 -> f25.convert(obj).flatMap(t25 -> f26.convert(obj).flatMap(t26 -> f27.convert(obj).flatMap(t27 -> f28.convert(obj).flatMap(t28 -> f29.convert(obj).flatMap(t29 -> f30.convert(obj).flatMap(t30 -> f31.convert(obj).flatMap(t31 -> f32.convert(obj).map(t32 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25), entry(key26, t26), entry(key27, t27), entry(key28, t28), entry(key29, t29), entry(key30, t30), entry(key31, t31), entry(key32, t32)))))))))))))))))))))))))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10,
                                           final String key11, final Convert<A, Json> f11,
                                           final String key12, final Convert<A, Json> f12,
                                           final String key13, final Convert<A, Json> f13,
                                           final String key14, final Convert<A, Json> f14,
                                           final String key15, final Convert<A, Json> f15,
                                           final String key16, final Convert<A, Json> f16,
                                           final String key17, final Convert<A, Json> f17,
                                           final String key18, final Convert<A, Json> f18,
                                           final String key19, final Convert<A, Json> f19,
                                           final String key20, final Convert<A, Json> f20,
                                           final String key21, final Convert<A, Json> f21,
                                           final String key22, final Convert<A, Json> f22,
                                           final String key23, final Convert<A, Json> f23,
                                           final String key24, final Convert<A, Json> f24,
                                           final String key25, final Convert<A, Json> f25,
                                           final String key26, final Convert<A, Json> f26,
                                           final String key27, final Convert<A, Json> f27,
                                           final String key28, final Convert<A, Json> f28,
                                           final String key29, final Convert<A, Json> f29,
                                           final String key30, final Convert<A, Json> f30,
                                           final String key31, final Convert<A, Json> f31,
                                           final String key32, final Convert<A, Json> f32,
                                           final String key33, final Convert<A, Json> f33) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).flatMap(t10 -> f11.convert(obj).flatMap(t11 -> f12.convert(obj).flatMap(t12 -> f13.convert(obj).flatMap(t13 -> f14.convert(obj).flatMap(t14 -> f15.convert(obj).flatMap(t15 -> f16.convert(obj).flatMap(t16 -> f17.convert(obj).flatMap(t17 -> f18.convert(obj).flatMap(t18 -> f19.convert(obj).flatMap(t19 -> f20.convert(obj).flatMap(t20 -> f21.convert(obj).flatMap(t21 -> f22.convert(obj).flatMap(t22 -> f23.convert(obj).flatMap(t23 -> f24.convert(obj).flatMap(t24 -> f25.convert(obj).flatMap(t25 -> f26.convert(obj).flatMap(t26 -> f27.convert(obj).flatMap(t27 -> f28.convert(obj).flatMap(t28 -> f29.convert(obj).flatMap(t29 -> f30.convert(obj).flatMap(t30 -> f31.convert(obj).flatMap(t31 -> f32.convert(obj).flatMap(t32 -> f33.convert(obj).map(t33 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25), entry(key26, t26), entry(key27, t27), entry(key28, t28), entry(key29, t29), entry(key30, t30), entry(key31, t31), entry(key32, t32), entry(key33, t33))))))))))))))))))))))))))))))))))));
    }

    public final Either<String, Json> with(final String key0, final Convert<A, Json> f0,
                                           final String key1, final Convert<A, Json> f1,
                                           final String key2, final Convert<A, Json> f2,
                                           final String key3, final Convert<A, Json> f3,
                                           final String key4, final Convert<A, Json> f4,
                                           final String key5, final Convert<A, Json> f5,
                                           final String key6, final Convert<A, Json> f6,
                                           final String key7, final Convert<A, Json> f7,
                                           final String key8, final Convert<A, Json> f8,
                                           final String key9, final Convert<A, Json> f9,
                                           final String key10, final Convert<A, Json> f10,
                                           final String key11, final Convert<A, Json> f11,
                                           final String key12, final Convert<A, Json> f12,
                                           final String key13, final Convert<A, Json> f13,
                                           final String key14, final Convert<A, Json> f14,
                                           final String key15, final Convert<A, Json> f15,
                                           final String key16, final Convert<A, Json> f16,
                                           final String key17, final Convert<A, Json> f17,
                                           final String key18, final Convert<A, Json> f18,
                                           final String key19, final Convert<A, Json> f19,
                                           final String key20, final Convert<A, Json> f20,
                                           final String key21, final Convert<A, Json> f21,
                                           final String key22, final Convert<A, Json> f22,
                                           final String key23, final Convert<A, Json> f23,
                                           final String key24, final Convert<A, Json> f24,
                                           final String key25, final Convert<A, Json> f25,
                                           final String key26, final Convert<A, Json> f26,
                                           final String key27, final Convert<A, Json> f27,
                                           final String key28, final Convert<A, Json> f28,
                                           final String key29, final Convert<A, Json> f29,
                                           final String key30, final Convert<A, Json> f30,
                                           final String key31, final Convert<A, Json> f31,
                                           final String key32, final Convert<A, Json> f32,
                                           final String key33, final Convert<A, Json> f33,
                                           final String key34, final Convert<A, Json> f34) {
        return f0.convert(obj).flatMap(t0 -> f1.convert(obj).flatMap(t1 -> f2.convert(obj).flatMap(t2 -> f3.convert(obj).flatMap(t3 -> f4.convert(obj).flatMap(t4 -> f5.convert(obj).flatMap(t5 -> f6.convert(obj).flatMap(t6 -> f7.convert(obj).flatMap(t7 -> f8.convert(obj).flatMap(t8 -> f9.convert(obj).flatMap(t9 -> f10.convert(obj).flatMap(t10 -> f11.convert(obj).flatMap(t11 -> f12.convert(obj).flatMap(t12 -> f13.convert(obj).flatMap(t13 -> f14.convert(obj).flatMap(t14 -> f15.convert(obj).flatMap(t15 -> f16.convert(obj).flatMap(t16 -> f17.convert(obj).flatMap(t17 -> f18.convert(obj).flatMap(t18 -> f19.convert(obj).flatMap(t19 -> f20.convert(obj).flatMap(t20 -> f21.convert(obj).flatMap(t21 -> f22.convert(obj).flatMap(t22 -> f23.convert(obj).flatMap(t23 -> f24.convert(obj).flatMap(t24 -> f25.convert(obj).flatMap(t25 -> f26.convert(obj).flatMap(t26 -> f27.convert(obj).flatMap(t27 -> f28.convert(obj).flatMap(t28 -> f29.convert(obj).flatMap(t29 -> f30.convert(obj).flatMap(t30 -> f31.convert(obj).flatMap(t31 -> f32.convert(obj).flatMap(t32 -> f33.convert(obj).flatMap(t33 -> f34.convert(obj).map(t34 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25), entry(key26, t26), entry(key27, t27), entry(key28, t28), entry(key29, t29), entry(key30, t30), entry(key31, t31), entry(key32, t32), entry(key33, t33), entry(key34, t34)))))))))))))))))))))))))))))))))))));
    }
}