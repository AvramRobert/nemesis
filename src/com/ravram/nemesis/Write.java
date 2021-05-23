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

public interface Write<A> extends Convert<A, Json> {
    Write<Integer> INT = value -> Attempt.success(new JNum(value));
    Write<Long> LONG = value -> Attempt.success(new JNum(value));
    Write<Float> FLOAT = value -> Attempt.success(new JNum(value));
    Write<Double> DOUBLE = value -> Attempt.success(new JNum(value));
    Write<String> STRING = value -> Attempt.success(new JString(value));
    Write<Void> NULL = x -> Attempt.success(JNull.instance);
    Write<Boolean> BOOLEAN = value -> Attempt.success(new JBool(value));
    Write<List<Json>> LIST = value -> Attempt.success(new JArr(io.lacuna.bifurcan.List.from(value)));
    Write<Set<Json>> SET = value -> Attempt.success(new JArr(io.lacuna.bifurcan.List.from(value)));
    Write<ZonedDateTime> ZONED_DATE_TIME = value -> Attempt.success(new JString(value.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)));
    Write<UUID> UUID = value -> Attempt.success(new JString(value.toString()));

    static <A> Write<List<A>> list(final Write<A> f) {
        return list -> Collections.bTraverseList(list, f).map(JArr::new);
    }

    static <A> Write<Set<A>> set(final Write<A> f) {
        return set -> Collections.bTraverseSet(set, f).map(JArr::new);
    }

    static <K, A> Write<Map<K, A>> map(final Convert<K, String> km, final Write<A> vf) {
        return map -> Collections.traverseMap(map, km, vf).map(JObj::new);
    }

    static <K, A> Write<Map<String, A>> map(final Write<A> vf) {
        return map -> Collections.traverseMap(map, DynamicConversions::coerceKey, vf).map(JObj::new);
    }
}
