package json.coerce;

import json.data.Json;
import json.data.JsonTree;
import json.ext.Function2;
import json.ext.Function3;
import util.Either;

import java.util.function.Function;

public class JsonMapper {

    public final JsonTree tree;

    public JsonMapper(final JsonTree tree) {
        this.tree = tree;
    }

    public <A, B, C> Function<Function2<A, B, C>, Either<String, C>>
    combine(final Function<JsonTree, Either<String, A>> f1,
            final Function<JsonTree, Either<String, B>> f2) {
        return f -> f1.apply(tree).flatMap(a -> f2.apply(tree).map(b -> f.apply(a, b)));
    }

    public <A, B, C, D> Either<String, D> combine(final Function<JsonTree, Either<String, A>> f1,
                                                  final Function<JsonTree, Either<String, B>> f2,
                                                  final Function<JsonTree, Either<String, C>> f3,
                                                  final Function3<A, B, C, D> comp) {
        return f1.apply(tree)
         .flatMap(a ->
          f2.apply(tree)
           .flatMap(b ->
            f3.apply(tree).map(c -> comp.apply(a, b, c))));
    }
}
