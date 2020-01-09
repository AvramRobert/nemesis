package json.coerce;

import io.lacuna.bifurcan.Map;
import io.lacuna.bifurcan.Maps.Entry;
import json.data.JObj;
import json.data.Json;

import java.util.Arrays;

public class ObjectConverter<A> {
    private final Entry<String, Json> entry(final String key, final Json value) {
        return new Entry<>(key, value);
    }

    @SafeVarargs
    private final JObj mapFrom(final Entry<String, Json>... entries) {
        return new JObj(Map.from(Arrays.asList(entries)));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0) {
        return value -> f0.convert(value).map(t0 -> mapFrom(entry(key0, t0)));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
                                         final String key1, final Convert<A, Json> f1) {
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).map(t1 -> mapFrom(entry(key0, t0), entry(key1, t1))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
                                         final String key1, final Convert<A, Json> f1,
                                         final String key2, final Convert<A, Json> f2) {
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).map(t2 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2)))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
                                         final String key1, final Convert<A, Json> f1,
                                         final String key2, final Convert<A, Json> f2,
                                         final String key3, final Convert<A, Json> f3) {
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).map(t3 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
                                         final String key1, final Convert<A, Json> f1,
                                         final String key2, final Convert<A, Json> f2,
                                         final String key3, final Convert<A, Json> f3,
                                         final String key4, final Convert<A, Json> f4) {
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).map(t4 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4)))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
                                         final String key1, final Convert<A, Json> f1,
                                         final String key2, final Convert<A, Json> f2,
                                         final String key3, final Convert<A, Json> f3,
                                         final String key4, final Convert<A, Json> f4,
                                         final String key5, final Convert<A, Json> f5) {
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).map(t5 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5))))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
                                         final String key1, final Convert<A, Json> f1,
                                         final String key2, final Convert<A, Json> f2,
                                         final String key3, final Convert<A, Json> f3,
                                         final String key4, final Convert<A, Json> f4,
                                         final String key5, final Convert<A, Json> f5,
                                         final String key6, final Convert<A, Json> f6) {
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).flatMap(t5 -> f6.convert(value).map(t6 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6)))))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
                                         final String key1, final Convert<A, Json> f1,
                                         final String key2, final Convert<A, Json> f2,
                                         final String key3, final Convert<A, Json> f3,
                                         final String key4, final Convert<A, Json> f4,
                                         final String key5, final Convert<A, Json> f5,
                                         final String key6, final Convert<A, Json> f6,
                                         final String key7, final Convert<A, Json> f7) {
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).flatMap(t5 -> f6.convert(value).flatMap(t6 -> f7.convert(value).map(t7 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7))))))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
                                         final String key1, final Convert<A, Json> f1,
                                         final String key2, final Convert<A, Json> f2,
                                         final String key3, final Convert<A, Json> f3,
                                         final String key4, final Convert<A, Json> f4,
                                         final String key5, final Convert<A, Json> f5,
                                         final String key6, final Convert<A, Json> f6,
                                         final String key7, final Convert<A, Json> f7,
                                         final String key8, final Convert<A, Json> f8) {
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).flatMap(t5 -> f6.convert(value).flatMap(t6 -> f7.convert(value).flatMap(t7 -> f8.convert(value).map(t8 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8)))))))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
                                         final String key1, final Convert<A, Json> f1,
                                         final String key2, final Convert<A, Json> f2,
                                         final String key3, final Convert<A, Json> f3,
                                         final String key4, final Convert<A, Json> f4,
                                         final String key5, final Convert<A, Json> f5,
                                         final String key6, final Convert<A, Json> f6,
                                         final String key7, final Convert<A, Json> f7,
                                         final String key8, final Convert<A, Json> f8,
                                         final String key9, final Convert<A, Json> f9) {
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).flatMap(t5 -> f6.convert(value).flatMap(t6 -> f7.convert(value).flatMap(t7 -> f8.convert(value).flatMap(t8 -> f9.convert(value).map(t9 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9))))))))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
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
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).flatMap(t5 -> f6.convert(value).flatMap(t6 -> f7.convert(value).flatMap(t7 -> f8.convert(value).flatMap(t8 -> f9.convert(value).flatMap(t9 -> f10.convert(value).map(t10 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10)))))))))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
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
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).flatMap(t5 -> f6.convert(value).flatMap(t6 -> f7.convert(value).flatMap(t7 -> f8.convert(value).flatMap(t8 -> f9.convert(value).flatMap(t9 -> f10.convert(value).flatMap(t10 -> f11.convert(value).map(t11 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11))))))))))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
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
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).flatMap(t5 -> f6.convert(value).flatMap(t6 -> f7.convert(value).flatMap(t7 -> f8.convert(value).flatMap(t8 -> f9.convert(value).flatMap(t9 -> f10.convert(value).flatMap(t10 -> f11.convert(value).flatMap(t11 -> f12.convert(value).map(t12 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12)))))))))))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
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
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).flatMap(t5 -> f6.convert(value).flatMap(t6 -> f7.convert(value).flatMap(t7 -> f8.convert(value).flatMap(t8 -> f9.convert(value).flatMap(t9 -> f10.convert(value).flatMap(t10 -> f11.convert(value).flatMap(t11 -> f12.convert(value).flatMap(t12 -> f13.convert(value).map(t13 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13))))))))))))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
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
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).flatMap(t5 -> f6.convert(value).flatMap(t6 -> f7.convert(value).flatMap(t7 -> f8.convert(value).flatMap(t8 -> f9.convert(value).flatMap(t9 -> f10.convert(value).flatMap(t10 -> f11.convert(value).flatMap(t11 -> f12.convert(value).flatMap(t12 -> f13.convert(value).flatMap(t13 -> f14.convert(value).map(t14 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14)))))))))))))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
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
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).flatMap(t5 -> f6.convert(value).flatMap(t6 -> f7.convert(value).flatMap(t7 -> f8.convert(value).flatMap(t8 -> f9.convert(value).flatMap(t9 -> f10.convert(value).flatMap(t10 -> f11.convert(value).flatMap(t11 -> f12.convert(value).flatMap(t12 -> f13.convert(value).flatMap(t13 -> f14.convert(value).flatMap(t14 -> f15.convert(value).map(t15 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15))))))))))))))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
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
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).flatMap(t5 -> f6.convert(value).flatMap(t6 -> f7.convert(value).flatMap(t7 -> f8.convert(value).flatMap(t8 -> f9.convert(value).flatMap(t9 -> f10.convert(value).flatMap(t10 -> f11.convert(value).flatMap(t11 -> f12.convert(value).flatMap(t12 -> f13.convert(value).flatMap(t13 -> f14.convert(value).flatMap(t14 -> f15.convert(value).flatMap(t15 -> f16.convert(value).map(t16 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16)))))))))))))))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
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
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).flatMap(t5 -> f6.convert(value).flatMap(t6 -> f7.convert(value).flatMap(t7 -> f8.convert(value).flatMap(t8 -> f9.convert(value).flatMap(t9 -> f10.convert(value).flatMap(t10 -> f11.convert(value).flatMap(t11 -> f12.convert(value).flatMap(t12 -> f13.convert(value).flatMap(t13 -> f14.convert(value).flatMap(t14 -> f15.convert(value).flatMap(t15 -> f16.convert(value).flatMap(t16 -> f17.convert(value).map(t17 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17))))))))))))))))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
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
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).flatMap(t5 -> f6.convert(value).flatMap(t6 -> f7.convert(value).flatMap(t7 -> f8.convert(value).flatMap(t8 -> f9.convert(value).flatMap(t9 -> f10.convert(value).flatMap(t10 -> f11.convert(value).flatMap(t11 -> f12.convert(value).flatMap(t12 -> f13.convert(value).flatMap(t13 -> f14.convert(value).flatMap(t14 -> f15.convert(value).flatMap(t15 -> f16.convert(value).flatMap(t16 -> f17.convert(value).flatMap(t17 -> f18.convert(value).map(t18 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18)))))))))))))))))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
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
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).flatMap(t5 -> f6.convert(value).flatMap(t6 -> f7.convert(value).flatMap(t7 -> f8.convert(value).flatMap(t8 -> f9.convert(value).flatMap(t9 -> f10.convert(value).flatMap(t10 -> f11.convert(value).flatMap(t11 -> f12.convert(value).flatMap(t12 -> f13.convert(value).flatMap(t13 -> f14.convert(value).flatMap(t14 -> f15.convert(value).flatMap(t15 -> f16.convert(value).flatMap(t16 -> f17.convert(value).flatMap(t17 -> f18.convert(value).flatMap(t18 -> f19.convert(value).map(t19 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19))))))))))))))))))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
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
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).flatMap(t5 -> f6.convert(value).flatMap(t6 -> f7.convert(value).flatMap(t7 -> f8.convert(value).flatMap(t8 -> f9.convert(value).flatMap(t9 -> f10.convert(value).flatMap(t10 -> f11.convert(value).flatMap(t11 -> f12.convert(value).flatMap(t12 -> f13.convert(value).flatMap(t13 -> f14.convert(value).flatMap(t14 -> f15.convert(value).flatMap(t15 -> f16.convert(value).flatMap(t16 -> f17.convert(value).flatMap(t17 -> f18.convert(value).flatMap(t18 -> f19.convert(value).flatMap(t19 -> f20.convert(value).map(t20 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20)))))))))))))))))))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
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
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).flatMap(t5 -> f6.convert(value).flatMap(t6 -> f7.convert(value).flatMap(t7 -> f8.convert(value).flatMap(t8 -> f9.convert(value).flatMap(t9 -> f10.convert(value).flatMap(t10 -> f11.convert(value).flatMap(t11 -> f12.convert(value).flatMap(t12 -> f13.convert(value).flatMap(t13 -> f14.convert(value).flatMap(t14 -> f15.convert(value).flatMap(t15 -> f16.convert(value).flatMap(t16 -> f17.convert(value).flatMap(t17 -> f18.convert(value).flatMap(t18 -> f19.convert(value).flatMap(t19 -> f20.convert(value).flatMap(t20 -> f21.convert(value).map(t21 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21))))))))))))))))))))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
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
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).flatMap(t5 -> f6.convert(value).flatMap(t6 -> f7.convert(value).flatMap(t7 -> f8.convert(value).flatMap(t8 -> f9.convert(value).flatMap(t9 -> f10.convert(value).flatMap(t10 -> f11.convert(value).flatMap(t11 -> f12.convert(value).flatMap(t12 -> f13.convert(value).flatMap(t13 -> f14.convert(value).flatMap(t14 -> f15.convert(value).flatMap(t15 -> f16.convert(value).flatMap(t16 -> f17.convert(value).flatMap(t17 -> f18.convert(value).flatMap(t18 -> f19.convert(value).flatMap(t19 -> f20.convert(value).flatMap(t20 -> f21.convert(value).flatMap(t21 -> f22.convert(value).map(t22 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22)))))))))))))))))))))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
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
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).flatMap(t5 -> f6.convert(value).flatMap(t6 -> f7.convert(value).flatMap(t7 -> f8.convert(value).flatMap(t8 -> f9.convert(value).flatMap(t9 -> f10.convert(value).flatMap(t10 -> f11.convert(value).flatMap(t11 -> f12.convert(value).flatMap(t12 -> f13.convert(value).flatMap(t13 -> f14.convert(value).flatMap(t14 -> f15.convert(value).flatMap(t15 -> f16.convert(value).flatMap(t16 -> f17.convert(value).flatMap(t17 -> f18.convert(value).flatMap(t18 -> f19.convert(value).flatMap(t19 -> f20.convert(value).flatMap(t20 -> f21.convert(value).flatMap(t21 -> f22.convert(value).flatMap(t22 -> f23.convert(value).map(t23 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23))))))))))))))))))))))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
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
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).flatMap(t5 -> f6.convert(value).flatMap(t6 -> f7.convert(value).flatMap(t7 -> f8.convert(value).flatMap(t8 -> f9.convert(value).flatMap(t9 -> f10.convert(value).flatMap(t10 -> f11.convert(value).flatMap(t11 -> f12.convert(value).flatMap(t12 -> f13.convert(value).flatMap(t13 -> f14.convert(value).flatMap(t14 -> f15.convert(value).flatMap(t15 -> f16.convert(value).flatMap(t16 -> f17.convert(value).flatMap(t17 -> f18.convert(value).flatMap(t18 -> f19.convert(value).flatMap(t19 -> f20.convert(value).flatMap(t20 -> f21.convert(value).flatMap(t21 -> f22.convert(value).flatMap(t22 -> f23.convert(value).flatMap(t23 -> f24.convert(value).map(t24 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24)))))))))))))))))))))))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
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
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).flatMap(t5 -> f6.convert(value).flatMap(t6 -> f7.convert(value).flatMap(t7 -> f8.convert(value).flatMap(t8 -> f9.convert(value).flatMap(t9 -> f10.convert(value).flatMap(t10 -> f11.convert(value).flatMap(t11 -> f12.convert(value).flatMap(t12 -> f13.convert(value).flatMap(t13 -> f14.convert(value).flatMap(t14 -> f15.convert(value).flatMap(t15 -> f16.convert(value).flatMap(t16 -> f17.convert(value).flatMap(t17 -> f18.convert(value).flatMap(t18 -> f19.convert(value).flatMap(t19 -> f20.convert(value).flatMap(t20 -> f21.convert(value).flatMap(t21 -> f22.convert(value).flatMap(t22 -> f23.convert(value).flatMap(t23 -> f24.convert(value).flatMap(t24 -> f25.convert(value).map(t25 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25))))))))))))))))))))))))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
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
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).flatMap(t5 -> f6.convert(value).flatMap(t6 -> f7.convert(value).flatMap(t7 -> f8.convert(value).flatMap(t8 -> f9.convert(value).flatMap(t9 -> f10.convert(value).flatMap(t10 -> f11.convert(value).flatMap(t11 -> f12.convert(value).flatMap(t12 -> f13.convert(value).flatMap(t13 -> f14.convert(value).flatMap(t14 -> f15.convert(value).flatMap(t15 -> f16.convert(value).flatMap(t16 -> f17.convert(value).flatMap(t17 -> f18.convert(value).flatMap(t18 -> f19.convert(value).flatMap(t19 -> f20.convert(value).flatMap(t20 -> f21.convert(value).flatMap(t21 -> f22.convert(value).flatMap(t22 -> f23.convert(value).flatMap(t23 -> f24.convert(value).flatMap(t24 -> f25.convert(value).flatMap(t25 -> f26.convert(value).map(t26 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25), entry(key26, t26)))))))))))))))))))))))))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
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
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).flatMap(t5 -> f6.convert(value).flatMap(t6 -> f7.convert(value).flatMap(t7 -> f8.convert(value).flatMap(t8 -> f9.convert(value).flatMap(t9 -> f10.convert(value).flatMap(t10 -> f11.convert(value).flatMap(t11 -> f12.convert(value).flatMap(t12 -> f13.convert(value).flatMap(t13 -> f14.convert(value).flatMap(t14 -> f15.convert(value).flatMap(t15 -> f16.convert(value).flatMap(t16 -> f17.convert(value).flatMap(t17 -> f18.convert(value).flatMap(t18 -> f19.convert(value).flatMap(t19 -> f20.convert(value).flatMap(t20 -> f21.convert(value).flatMap(t21 -> f22.convert(value).flatMap(t22 -> f23.convert(value).flatMap(t23 -> f24.convert(value).flatMap(t24 -> f25.convert(value).flatMap(t25 -> f26.convert(value).flatMap(t26 -> f27.convert(value).map(t27 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25), entry(key26, t26), entry(key27, t27))))))))))))))))))))))))))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
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
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).flatMap(t5 -> f6.convert(value).flatMap(t6 -> f7.convert(value).flatMap(t7 -> f8.convert(value).flatMap(t8 -> f9.convert(value).flatMap(t9 -> f10.convert(value).flatMap(t10 -> f11.convert(value).flatMap(t11 -> f12.convert(value).flatMap(t12 -> f13.convert(value).flatMap(t13 -> f14.convert(value).flatMap(t14 -> f15.convert(value).flatMap(t15 -> f16.convert(value).flatMap(t16 -> f17.convert(value).flatMap(t17 -> f18.convert(value).flatMap(t18 -> f19.convert(value).flatMap(t19 -> f20.convert(value).flatMap(t20 -> f21.convert(value).flatMap(t21 -> f22.convert(value).flatMap(t22 -> f23.convert(value).flatMap(t23 -> f24.convert(value).flatMap(t24 -> f25.convert(value).flatMap(t25 -> f26.convert(value).flatMap(t26 -> f27.convert(value).flatMap(t27 -> f28.convert(value).map(t28 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25), entry(key26, t26), entry(key27, t27), entry(key28, t28)))))))))))))))))))))))))))))));
    }

    public final Convert<A, Json> object(final String key0, final Convert<A, Json> f0,
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
        return value -> f0.convert(value).flatMap(t0 -> f1.convert(value).flatMap(t1 -> f2.convert(value).flatMap(t2 -> f3.convert(value).flatMap(t3 -> f4.convert(value).flatMap(t4 -> f5.convert(value).flatMap(t5 -> f6.convert(value).flatMap(t6 -> f7.convert(value).flatMap(t7 -> f8.convert(value).flatMap(t8 -> f9.convert(value).flatMap(t9 -> f10.convert(value).flatMap(t10 -> f11.convert(value).flatMap(t11 -> f12.convert(value).flatMap(t12 -> f13.convert(value).flatMap(t13 -> f14.convert(value).flatMap(t14 -> f15.convert(value).flatMap(t15 -> f16.convert(value).flatMap(t16 -> f17.convert(value).flatMap(t17 -> f18.convert(value).flatMap(t18 -> f19.convert(value).flatMap(t19 -> f20.convert(value).flatMap(t20 -> f21.convert(value).flatMap(t21 -> f22.convert(value).flatMap(t22 -> f23.convert(value).flatMap(t23 -> f24.convert(value).flatMap(t24 -> f25.convert(value).flatMap(t25 -> f26.convert(value).flatMap(t26 -> f27.convert(value).flatMap(t27 -> f28.convert(value).flatMap(t28 -> f29.convert(value).map(t29 -> mapFrom(entry(key0, t0), entry(key1, t1), entry(key2, t2), entry(key3, t3), entry(key4, t4), entry(key5, t5), entry(key6, t6), entry(key7, t7), entry(key8, t8), entry(key9, t9), entry(key10, t10), entry(key11, t11), entry(key12, t12), entry(key13, t13), entry(key14, t14), entry(key15, t15), entry(key16, t16), entry(key17, t17), entry(key18, t18), entry(key19, t19), entry(key20, t20), entry(key21, t21), entry(key22, t22), entry(key23, t23), entry(key24, t24), entry(key25, t25), entry(key26, t26), entry(key27, t27), entry(key28, t28), entry(key29, t29))))))))))))))))))))))))))))))));
    }
}