package com.ravram.nemesis;

import com.ravram.nemesis.coerce.Convert;
import com.ravram.nemesis.coerce.DynamicConversions;
import com.ravram.nemesis.model.*;
import com.ravram.nemesis.util.misc.Collections;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public final class Writers {
    public final static Write<Integer> WRITE_INT = value -> Attempt.success(new JNum(value));
    public final static Write<Long> WRITE_LONG = value -> Attempt.success(new JNum(value));
    public final static Write<Float> WRITE_FLOAT = value -> Attempt.success(new JNum(value));
    public final static Write<Double> WRITE_DOUBLE = value -> Attempt.success(new JNum(value));
    public final static Write<String> WRITE_STRING = value -> Attempt.success(new JString(value));
    public final static Write<Void> WRITE_NULL = x -> Attempt.success(JNull.instance);
    public final static Write<Boolean> WRITE_BOOLEAN = value -> Attempt.success(new JBool(value));
    public final static Write<List<Json>> WRITE_LIST = value -> Attempt.success(new JArr(io.lacuna.bifurcan.List.from(value)));
    public final static Write<Set<Json>> WRITE_SET = value -> Attempt.success(new JArr(io.lacuna.bifurcan.List.from(value)));
    public final static Write<ZonedDateTime> WRITE_ZONED_DATE_TIME = value -> Attempt.success(new JString(value.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)));
    public final static Write<UUID> WRITE_UUID = value -> Attempt.success(new JString(value.toString()));

    public static <K, A> Write<Map<K, A>> writeMapOf(final Convert<K, String> km, final Write<A> vf) {
        return map -> Collections.traverseMap(map, km, vf).map(JObj::new);
    }

    public static <K, A> Write<Map<K, A>> writeMapOf(final Write<A> vf) {
        return map -> Collections.traverseMap(map, DynamicConversions::coerceKey, vf).map(JObj::new);
    }

    public static <A> Write<List<A>> writeListOf(final Write<A> f) {
        return list -> Collections.bTraverseList(list, f).map(JArr::new);
    }

    public static <A> Write<Set<A>> writeSetOf(final Write<A> f) {
        return set -> Collections.bTraverseSet(set, f).map(JArr::new);
    }
}