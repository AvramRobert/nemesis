package com.ravram.nemesis.coerce;

import io.lacuna.bifurcan.List;
import com.ravram.nemesis.model.*;
import com.ravram.nemesis.util.error.Either;
import com.ravram.nemesis.util.misc.Strings;

import static com.ravram.nemesis.util.misc.Collections.*;

public class DynamicConversions {

    public static <A> Either<String, Long> coerceLong(final A value) {
        if (value instanceof Long) return Either.right((Long) value);
        else if (value instanceof Integer) return Either.right(Integer.toUnsignedLong((Integer) value));
        else return Either.left("Value `%s` is not of type Long", value.toString());
    }

    public static <A> Either<String, String> coerceString(final A value) {
        if (value instanceof String) return Either.right((String) value);
        else return Either.left("Value `%s` is not of type String", value.toString());
    }

    @SuppressWarnings("unchecked")
    public static <A> Either<String, Json> coerceJson(final A value) {
        if (value instanceof Number) {
            return Either.right(new JNum((Number) value));

        } else if (value instanceof String) {
            return Either.right(new JString((String) value));

        } else if (value instanceof Boolean) {
            final boolean bool = (Boolean) value;
            return Either.right(bool ? JBool.jtrue : JBool.jfalse);

        } else if (value == null) {
            return Either.right(JNull.instance);

        } else if (value instanceof Integer[]) {
            final Integer[] array = (Integer[]) value;
            return traverseArray(array, DynamicConversions::coerceJson).map(l -> new JArr(List.from(l)));

        } else if (value instanceof Long[]) {
            final Long[] array = (Long[]) value;
            return traverseArray(array, DynamicConversions::coerceJson).map(l -> new JArr(List.from(l)));

        } else if (value instanceof Double[]) {
            final Double[] array = (Double[]) value;
            return traverseArray(array, DynamicConversions::coerceJson).map(l -> new JArr(List.from(l)));

        } else if (value instanceof Float[]) {
            final Float[] array = (Float[]) value;
            return traverseArray(array, DynamicConversions::coerceJson).map(l -> new JArr(List.from(l)));

        } else if (value instanceof Object[]) {
            final Object[] array = (Object[]) value;
            return traverseArray(array, DynamicConversions::coerceJson).map(l -> new JArr(List.from(l)));

        } else if (value instanceof java.util.Map) {
            final java.util.Map<Object, Object> map = (java.util.Map<Object, Object>) value;
            return traverseMap(map, s -> coerceString(s).map(Strings::escape), DynamicConversions::coerceJson).map(JObj::new);

        } else if (value instanceof java.util.List) {
            final java.util.List<Object> list = (java.util.List<Object>) value;
            return traverseList(list, DynamicConversions::coerceJson).map(l -> new JArr(List.from(l)));

        } else if (value instanceof java.util.Set) {
            final java.util.Set<Object> set = (java.util.Set<Object>) value;
            return traverseSet(set, DynamicConversions::coerceJson).map(l -> new JArr(List.from(l)));
        }
        else
            return Either.left("Class type of `%s` for value `%s` is not supported", value.getClass().toString(), value);
    }
}
