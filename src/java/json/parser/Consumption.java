package json.parser;

public abstract class Consumption<A> {
    Seeker seeker;

    public boolean succeeded() {
        return this instanceof Success;
    }
    abstract A value ();
    abstract <B> Consumption<B> coerce();

    public static <C> Success<C> succeed(final Seeker seeker, final C result) {
        return new Success<>(seeker, result);
    }

    public static <C> Failure<C> failed(final Seeker seeker, final String msg) {
        return new Failure<>(seeker, msg);
    }
}
