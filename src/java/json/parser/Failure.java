package json.parser;

import json.data.Json;

public final class Failure extends Consumption {
    public final Seeker seeker;
    public final String msg;

    public Failure(final Seeker seeker, final String msg) {
        this.seeker = seeker;
        this.msg = msg;
    }

    @Override
    Json value() { return null; }

    @Override
    public String toString() {
        final String sample  = seeker.pointedSample(20);
        return String.format("%s\nFailure: %s\nFailed at line %d", sample, msg, seeker.line());
    }
}
