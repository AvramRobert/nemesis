package json.parser;

public class Seeker {
    private final String text;
    private int cursor;
    private int lines;

    public Seeker(final String text) {
        this.text = text;
        cursor = 0;
        lines = 0;
    }

    public final String pointedSample(final int chars) {
        int length = text.length();
        int div = chars / 2;
        int left = cursor - div;
        int right = cursor + div;
        if (left >= 0 && right < length) return text.substring(left, cursor) + "| ->" + text.substring(cursor, right);
        else if (right < length) return "| ->" + text.substring(0, right);
        else if (left >= 0) return text.substring(left, length - 1) + "<- |";
        else return "";
    }

    public final int cursor() { return cursor; }

    public final int length() { return text.length(); }

    public final char current() { return text.charAt(cursor); }

    public final char atNext(final int i) {
        return text.charAt(cursor + i);
    }

    public final boolean hasNext(final int i) { return i < text.length(); }

    public final int line() { return lines; }

    public final Seeker proceed(final int i) {
        cursor += i;
        return this;
    }

    public final Seeker jump() {
        lines ++;
        cursor ++;
        return this;
    }
}