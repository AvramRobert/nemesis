package json.parser;

import io.lacuna.bifurcan.List;
import io.lacuna.bifurcan.Map;
import json.data.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Parser2 {

    // == PARSING STATE INFO == //
    private boolean SUCCESSFUL = true;
    private String failure = "";
    private Json result = JEmpty.empty;

    // == PARSING TEXT INFO == //
    private final String text;
    private final int length;
    private int cursor = 0;
    private int lines = 0;

    // == GENERAL CONSTANTS == //
    private final int READY = 0;
    private final int STARTED = 1;
    private final String NULL = "NULL";
    private final String BOOLS = "true,false";
    private final String NUMS  = "0-9";
    private final String JSON  = "{, [, \", 0-9, true, false or null";
    private final String COLON = ":";
    private final String COMMA = ",";
    private final String QUOTE = "\"";
    private final char CBRAKET = ']';
    private final char CPAREN  = '}';


    public Parser2(final String input) {
        this.text = input;
        this.length = text.length();
    }

    private String pointedSample(final int subtextSize) {
        int div = subtextSize / 2;
        int left = cursor - div;
        int right = cursor + div;
        if (left >= 0 && right < length) {
            return text.substring(left, cursor) + " |-> " + text.substring(cursor, right);
        }
        else if (right < length) {
            return text.substring(0, cursor) + " |-> " + text.substring(cursor, right);
        }
        else if (left >= 0) {
            return text.substring(left, cursor) + " |-> " + text.substring(cursor, length - 1);
        }
        else return text + " <-| ";
    }

    private char CURRENT() { return text.charAt(cursor); }

    private char atNext(final int i) {
        return text.charAt(cursor + i);
    }

    private boolean hasNext(final int i) { return i < length; }

    private void proceed(final int i) {
        cursor += i;
    }

    private void jump() {
        lines ++;
        cursor ++;
    }

    private String failureMessage(final String prelude) {
        return String.format("%s\nFailed at line: %d\n%s", prelude, lines, pointedSample(30));
    }

    private void unexpectedEnd (final String expected, final char received) {
        SUCCESSFUL = !SUCCESSFUL;
        final String msg = String.format("Unexpected end of input. Expected `%s` but received `%c`.", expected, received);
        this.failure = failureMessage(msg);
    }

    private void abruptEnd (final String expected) {
        SUCCESSFUL = !SUCCESSFUL;
        final String msg = String.format("Unexpected end of input. Expected `%s but received nothing.", expected);
        this.failure = failureMessage(msg);
    }

    private boolean number(final char c) {
        return  c == '0' ||
                c == '1' ||
                c == '2' ||
                c == '3' ||
                c == '4' ||
                c == '5' ||
                c == '6' ||
                c == '7' ||
                c == '8' ||
                c == '9';
    }

    private boolean objOpen(final char c) {
        return c == '{';
    }

    private boolean objClose(final char c) {
        return c == '}';
    }

    private boolean arrOpen (final char c) {
        return c == '[';
    }

    private boolean arrClose (final char c) { return c == ']'; }

    private boolean string (final char c) {
        return c == '"';
    }

    private boolean sign (final char c) { return (c == '+') || (c == '-'); }

    private boolean exponent(final char e) {
        return e == 'e' || e == 'E';
    }

    private boolean space(final char c) {
        return c == ' ';
    }

    private boolean newline (final char c) {
        return c == '\n';
    }

    private boolean decimal(final char c) { return c == '.'; }

    private boolean pair(final char c) { return c == ':'; }

    private boolean comma(final char c) {
        return c == ',';
    }

    private boolean ready(final int state) {
        return state == READY;
    }

    private boolean started(final int state) { return state == STARTED; }

    private boolean n(final char c) { return c == 'n'; }

    private boolean t(final char c) { return c == 't'; }

    private boolean f(final char c) { return c == 'f'; }

    private boolean nullity(final char n, final char u, final char l1, final char l2) {
        return n == 'n' && u == 'u' && l1 == 'l' && l2 == 'l';
    }

    private boolean truth(final char t, final char r, final char u, final char e) {
        return t == 't' && r == 'r' && u == 'u' && e == 'e';
    }

    private boolean falsity(final char f, final char a, final char l, final char s, final char e) {
        return f == 'f' && a == 'a' && l == 'l' && s == 's' && e == 'e';
    }

    private void succeed(final Json json) {
        SUCCESSFUL = true;
        this.result = json;
    }

    private void skipFiller() {
        while (true) {
            if (space(CURRENT())) proceed(1);
            else if (newline(CURRENT())) jump();
            else break;
        }
    }

    private void consumeDecimal(final StringBuilder buffer) {
        int state = READY;
        char current;
        while (true) {
            current = CURRENT();
            if (number(current)) {
                buffer.append(current);
                state = STARTED;
                proceed(1);
            }
            else if (ready(state)) {
                unexpectedEnd(NUMS, current);
                break;
            }
            else {
                succeed(new JNum(Double.parseDouble(buffer.toString())));
                break;
            }
        }
    }

    private void consumeExponent(final StringBuilder buffer) {
        int state = READY;
        char current;
        while (true) {
            current = CURRENT();
            if (number(current)) {
                buffer.append(current);
                state = STARTED;
                proceed(1);
            }
            else if (ready(state) && sign(current)) {
                buffer.append(current);
                state = STARTED;
                proceed(1);
            }
            else if (ready(state)) {
                unexpectedEnd(NUMS, current);
                break;
            }
            else {
                succeed(new JNum(Float.parseFloat(buffer.toString())));
                break;
            }
        }
    }

    private void consumeNumber() {
        final StringBuilder buffer  = new StringBuilder();
        int state = READY;
        char current;
        while (true) {
            current = CURRENT();
            if (number(current)) {
                buffer.append(current);
                state = STARTED;
                proceed(1);
            }
            else if (started(state)) {
                if (decimal(current)) {
                    proceed(1);
                    consumeDecimal(buffer.append(current));
                    break;
                }
                else if (exponent(current)) {
                    proceed(1);
                    consumeExponent(buffer.append(current));
                    break;
                }
                else {
                    succeed(new JNum(Integer.parseInt(buffer.toString())));
                    break;
                }
            }
            else if (ready(state)) {
                unexpectedEnd(NUMS, current);
                break;
            }
            else {
                succeed(new JNum(Integer.parseInt(buffer.toString())));
                break;
            }
        }
    }

    private void consumeString() {
        final StringBuilder buffer = new StringBuilder();
        int state = READY;
        char current;
        while(true) {
            current = CURRENT();
            if (ready(state)) {
                state = STARTED;
                proceed(1);
            }
            else if (string(current) && started(state)) {
                proceed(1);
                break;
            }
            else if (started(state)) {
                buffer.append(current);
                proceed(1);
            }
            else {
                unexpectedEnd(QUOTE, current);
                break;
            }
        }
        succeed(new JString(buffer.toString()));
    }

    private void consumeComma(final char until) {
        skipFiller();
        final char current = CURRENT();
        if (current == until) {}
        else if (comma(current)) {
            proceed(1);
            skipFiller();
        }
        else unexpectedEnd(COMMA, current);
    }

    private void consumePair() {
        skipFiller();
        final char current = CURRENT();
        if (pair(current)) {
            proceed(1);
            skipFiller();
        }
        else unexpectedEnd(COLON, current);
    }

    private void consumeTrue() {
        if (hasNext(3)) {
            final char rchar = atNext(1);
            final char uchar = atNext(2);
            final char echar = atNext(3);
            if (truth(CURRENT(), rchar, uchar, echar)) {
                proceed(4);
                succeed(JBool.jtrue);
            }
            else abruptEnd(BOOLS);
        } else abruptEnd(BOOLS);
    }

    private void consumeFalse() {
        if (hasNext(4)) {
            final char achar = atNext(1);
            final char lchar = atNext(2);
            final char schar = atNext(3);
            final char echar = atNext(4);
            if (falsity(CURRENT(), achar, lchar, schar, echar)) {
                proceed(5);
                succeed(JBool.jfalse);
            }
            else abruptEnd(BOOLS);
        } else abruptEnd(BOOLS);
    }

    private void consumeNull() {
        if (hasNext(3)) {
            final char uchar  = atNext(1);
            final char lchar1 = atNext(2);
            final char lchar2 = atNext(3);
            if (nullity(CURRENT(), uchar, lchar1, lchar2)) {
                proceed(4);
                succeed(JNull.instance);
            }
            else abruptEnd(NULL);
        } else abruptEnd(NULL);
    }

    private void consumeObj() {
        final HashMap<String, Json> fields = new HashMap<>();
        int state = READY;
        char current;
        while (true) {
            current = CURRENT();
            skipFiller();
            if (ready(state)) {
                state = STARTED;
                proceed(1);
            }
            else if (started(state) && objClose(current)) {
                proceed(1);
                break;
            }
            else if (started(state)) {
                consumeString();
                if (SUCCESSFUL) {
                    final String key = result.toString();
                    consumePair();
                    if (SUCCESSFUL) {
                        consumeAny();
                        if (SUCCESSFUL) {
                            final Json value = result; // this isn't a copy. be careful
                            consumeComma(CPAREN);
                            if (SUCCESSFUL) fields.put(key, value);
                        }
                    }
                }
            }
            else unexpectedEnd(JSON, current);
        }
        succeed(new JObj(Map.from(fields)));
    }

    private void consumeArr() {
        final ArrayList<Json> list = new ArrayList<>();
        int state = READY;
        char current;
        while(true) {
            current = CURRENT();
            skipFiller();
            if (ready(state)) {
                state = STARTED;
                proceed(1);
            }
            else if (started(state) && arrClose(current)) {
                proceed(1);
                break;
            }
            else if (started(state)) {
                consumeAny();
                if (SUCCESSFUL) {
                    final Json value = result;
                    consumeComma(CBRAKET);
                    if (SUCCESSFUL) list.add(value);
                }
            }
            else unexpectedEnd(JSON, current);
        }
        succeed(new JArr(List.from(list)));
    }

    private void consumeAny() {
        final char current = CURRENT();
        skipFiller();
        if (number(current)) consumeNumber();
        else if (arrOpen(current)) consumeArr();
        else if (objOpen(current)) consumeObj();
        else if (string(current)) consumeString();
        else if (n(current)) consumeNull();
        else if (t(current)) consumeTrue();
        else if (f(current)) consumeFalse();
        else unexpectedEnd(JSON, current);
    }

    public static Consumption parse (final String input) {
        final Parser2 p = new Parser2(input);
        if (input.isEmpty()) return Consumption.succeed(p.result);
        else {
            try {
                p.consumeAny();
                if (p.SUCCESSFUL) return Consumption.succeed(p.result);
                else return Consumption.failed(p.failure);
            } catch (Exception e) {
                return Consumption.failed(e.getMessage()); // do better
            }
        }
    }
}
