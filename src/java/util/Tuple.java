package util;

public class Tuple<A, B> {
    public final A left;
    public final B right;

    public Tuple(final A left, final B right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", left, right);
    }
}
