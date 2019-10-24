package json.parser;

public final class Failure<A> extends Consumption<A> {
    public final Seeker seeker;
    public final String msg;

    public Failure(final Seeker seeker, final String msg) {
        this.seeker = seeker;
        this.msg = msg;
    }

    @Override
    A value() {
        return null;
    }

    @Override
    <B> Consumption<B> coerce() {
        return new Failure<>(seeker, msg);
    }

    @Override
    public String toString() {
        final String sample  = seeker.pointedSample(20);
        return String.format("%s\nFailure: %s\nFailed at line %d", sample, msg, seeker.line());
    }
}
