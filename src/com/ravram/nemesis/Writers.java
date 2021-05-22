package com.ravram.nemesis;

import com.ravram.nemesis.coerce.DynamicConversions;
import com.ravram.nemesis.model.*;
import com.ravram.nemesis.util.error.Either;
import com.ravram.nemesis.util.misc.Collections;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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
    
    public static <A> Write<Map<String, A>> mapFrom(final Write<A> f) {
        return map -> Collections.traverseMap(
                map,
                DynamicConversions::coerceKey, f::apply)
                .map(JObj::new);
    }

    public static <A> Write<List<A>> listFrom(final Write<A> f) {
        return list -> Collections.bTraverseList(list, f::apply).map(JArr::new);
    }

    public static <A> Write<Set<A>> setFrom(final Write<A> f) {
        return set -> Collections.bTraverseSet(set, f::apply).map(JArr::new);
    }

    public static <A> Write<Optional<A>> optionalFrom(final Write<A> f) {
        return opt -> {
            if (opt.isPresent()) return f.apply(opt.get());
            else return Either.right(JNull.instance);
        };
    }
}