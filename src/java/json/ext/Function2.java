package json.ext;

@FunctionalInterface
public interface Function2<A, B, C> {
    C apply(final A a, final B b);
}
