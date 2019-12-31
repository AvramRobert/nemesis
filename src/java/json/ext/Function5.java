package json.ext;

public interface Function5<A, B, C, D, E> {
    <F> F apply(final A a, final B b, final C c, final D d, final E e);
}
