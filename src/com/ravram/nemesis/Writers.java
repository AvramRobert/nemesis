package com.ravram.nemesis;

import com.ravram.nemesis.coerce.Convert;
import com.ravram.nemesis.coerce.DynamicConversions;
import com.ravram.nemesis.model.*;
import com.ravram.nemesis.util.error.Either;
import com.ravram.nemesis.util.misc.Collections;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public final class Writers {
    public final static Write<Integer> WRITE_INT = value -> Either.right(new JNum(value));
    public final static Write<Long> WRITE_LONG = value -> Either.right(new JNum(value));
    public final static Write<Float> WRITE_FLOAT = value -> Either.right(new JNum(value));
    public final static Write<Double> WRITE_DOUBLE = value -> Either.right(new JNum(value));
    public final static Write<String> WRITE_STRING = value -> Either.right(new JString(value));
    public final static Write<Void> WRITE_NULL = x -> Either.right(JNull.instance);
    public final static Write<Boolean> WRITE_BOOLEAN = value -> Either.right(new JBool(value));
    public final static Write<List<Json>> WRITE_LIST = value -> Either.right(new JArr(io.lacuna.bifurcan.List.from(value)));
    public final static Write<Set<Json>> WRITE_SET = value -> Either.right(new JArr(io.lacuna.bifurcan.List.from(value)));
    public final static Write<Optional<Json>> WRITE_OPTIONAL = value -> value.isPresent() ? Either.right(value.get()) : Either.right(JNull.instance);
    public final static Write<ZonedDateTime> WRITE_ZONED_DATE_TIME = value -> Either.right(new JString(value.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)));
    public final static Write<UUID> WRITE_UUID = value -> Either.right(new JString(value.toString()));

    public static <K, A> Write<Map<K, A>> mapFrom(final Convert<String, K, String> kf, final Write<A> vf) {
        return map -> Collections.traverseMap(map, kf::apply, vf::apply).map(JObj::new);
    }

    public static <K, A> Write<Map<String, A>> mapFrom(final Write<A> vf) {
        return map -> Collections.traverseMap(map, DynamicConversions::coerceKey, vf::apply).map(JObj::new);
    }

    public static <A> Write<List<A>> listFrom(final Write<A> f) {
        return list -> Collections.bTraverseList(list, f::apply).map(JArr::new);
    }

    public static <A> Write<Set<A>> setFrom(final Write<A> f) {
        return set -> Collections.bTraverseSet(set, f::apply).map(JArr::new);
    }
}