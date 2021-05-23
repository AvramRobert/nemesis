package com.ravram.nemesis.coerce;

import com.ravram.nemesis.Attempt;
import com.ravram.nemesis.Json;
import com.ravram.nemesis.model.*;
import io.lacuna.bifurcan.List;

import static com.ravram.nemesis.util.misc.Collections.*;

public class DynamicConversions {

    public static <A> Attempt<Long> coerceIndex(final A value) {
        if (value instanceof Long) return Attempt.success((Long) value);
        else if (value instanceof Integer) return Attempt.success(Integer.toUnsignedLong((Integer) value));
        else return Attempt.failure("Value `%s` is not of type Long", value.toString());
    }

    public static <A> Attempt<String> coerceKey(final A key) {
        if (key instanceof String) return Attempt.success((String) key);
        return Attempt.failure("Key `%s` is not of type String", key.toString());
    }

    @SuppressWarnings("unchecked")
    public static <A> Attempt<Json> coerceJson(final A value) {
        if (value instanceof Number) {
            return Attempt.success(new JNum((Number) value));

        } else if (value instanceof String) {
            return Attempt.success(new JString((String) value));

        } else if (value instanceof Boolean) {
            final boolean bool = (Boolean) value;
            return Attempt.success(bool ? JBool.jtrue : JBool.jfalse);

        } else if (value == null) {
            return Attempt.success(JNull.instance);

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
            return traverseMap(map, DynamicConversions::coerceKey, DynamicConversions::coerceJson).map(JObj::new);

        } else if (value instanceof java.util.List) {
            final java.util.List<Object> list = (java.util.List<Object>) value;
            return traverseList(list, DynamicConversions::coerceJson).map(l -> new JArr(List.from(l)));

        } else if (value instanceof java.util.Set) {
            final java.util.Set<Object> set = (java.util.Set<Object>) value;
            return traverseSet(set, DynamicConversions::coerceJson).map(l -> new JArr(List.from(l)));
        }
        else
            return Attempt.failure("Class type of `%s` for value `%s` is not supported", value.getClass().toString(), value);
    }
}
