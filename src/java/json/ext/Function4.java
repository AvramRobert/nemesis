package json.ext;

public interface Function4<A, B, C, D> {
    <E> E apply (final A a, final B b, final C c, final D d);
}
