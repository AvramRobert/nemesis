package json.coerce;

import json.data.Json;
import util.Either;

@FunctionalInterface
public interface Write <A> {
    Either<String, Json> write(final A value);
}
