package json.parser;

import json.data.Json;

public abstract class Consumption {
    Seeker seeker;
    abstract Json value ();

    public boolean succeeded() {
        return this instanceof Success;
    }

    public static Success succeed(final Seeker seeker, final Json result) {
        return new Success(seeker, result);
    }

    public static Failure failed(final Seeker seeker, final String msg) {
        return new Failure(seeker, msg);
    }
}
