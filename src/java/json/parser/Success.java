package json.parser;

public final class Success<A> extends Consumption<A> {
    public final Seeker seeker;
    public final A result;

    public Success(final Seeker seeker, final A result) {
        this.seeker = seeker;
        this.result = result;
    }

    @Override
    A value() {
        return result;
    }

    @Override
    <B> Consumption<B> coerce() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("Success: %s", result);
    }
}
