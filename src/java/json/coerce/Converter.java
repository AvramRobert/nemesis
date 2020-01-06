package json.coerce;

import json.data.Json;
import json.data.JsonTree;
import json.ext.Function2;
import util.Either;

import java.util.function.Function;

public class Converter {

    public static <A, B, C> Convert<Json, C> combine(final Function<JsonTree, Either<String, A>> f1,
                                                     final Function<JsonTree, Either<String, B>> f2,
                                                     final Function2<A, B, C> f) {
        return json -> {
            var tree = json.transform();
            return f1.apply(tree).flatMap(a -> f2.apply(tree).map(b -> f.apply(a, b)));
        };
    }
}
