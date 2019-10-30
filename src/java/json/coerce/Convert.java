package json.coerce;

import json.data.Json;
import util.Either;

import java.util.function.Function;

@FunctionalInterface
public interface Convert <A, B> {
    default Function<A, Either<String, B>> function() {
     return this::convert;
    }

    Either<String, B> convert(final A value);
}
