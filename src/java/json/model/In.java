package json.model;

public final class In {
    final boolean isEmpty;
    final Object[] path;
    public In(final Object[] path) {
        this.path = path;
        isEmpty = this.path.length == 0;
    }
}