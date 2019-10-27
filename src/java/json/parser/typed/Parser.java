package json.parser.typed;

import io.lacuna.bifurcan.List;
import io.lacuna.bifurcan.Map;
import json.data.typed.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Parser {

    // == PARSING STATE INFO == //
    private final boolean SUCCESSFUL = true;
    private final boolean FAILED = false;
    private String failure = "";
    private Json result = JEmpty.empty;

    // == PARSING TEXT INFO == //
    private final String text;
    private final int length;
    private int cursor = 0;
    private int lines = 0;

    // == GENERAL CONSTANTS == //
    private final String NULL = "`null`";
    private final String BOOLS = "`true`, `false`";
    private final String NUMS  = "`0-9`";
    private final String JSON  = "`{`, `[`, `\"`, `0-9`, `true`, `false` or `null`";
    private final String COLON = "`:`";
    private final String COMMA = "`,`";
    private final String QUOTE = "`\"`";
    private final char CBRAKET = ']';
    private final char CPAREN  = '}';


    public Parser(final String input) {
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

    private boolean unexpectedEnd (final String expected, final char received) {
        final String msg = String.format("Unexpected end of input. Expected any of %s but received %c.", expected, received);
        this.failure = failureMessage(msg);
        return FAILED;
    }

    private boolean abruptEnd (final String expected) {
        final String msg = String.format("Unexpected end of input. Expected any of %s but received nothing.", expected);
        this.failure = failureMessage(msg);
        return FAILED;
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

    private boolean succeed(final Json json) {
        this.result = json;
        return SUCCESSFUL;
    }

    private void skipFiller() {
        while (true) {
            if (space(CURRENT())) proceed(1);
            else if (newline(CURRENT())) jump();
            else break;
        }
    }

    private boolean numeralConsumption(final StringBuilder buffer) {
        char current;
        while (true) {
            current = CURRENT();
            if (number(current)) {
                buffer.append(current);
                proceed(1);
            }
            else return succeed(new JNum(Double.parseDouble(buffer.toString())));
        }
    }

    private boolean consumeDecimal(final StringBuilder buffer) {
        proceed(1);
        final char current = CURRENT();
        if (number(current)) {
            buffer.append(current);
            proceed(1);
            return numeralConsumption(buffer);
        } else return unexpectedEnd(NUMS, current);
    }

    private boolean consumeExponent(final StringBuilder buffer) {
        proceed(1);
        final char current = CURRENT();
        if (number(current) || sign(current)) {
            buffer.append(current);
            proceed(1);
            return numeralConsumption(buffer);
        }
        else return unexpectedEnd(NUMS, current);
    }

    private boolean consumeNumber() {
        final StringBuilder buffer  = new StringBuilder();
        char current;
        while (true) {
            current = CURRENT();
            if (number(current)) {
                buffer.append(current);
                proceed(1);
            }
            else if (decimal(current)) {
                return consumeDecimal(buffer);
            }
            else if (exponent(current)) {
                return consumeExponent(buffer);
            }
            else return succeed(new JNum(Integer.parseInt(buffer.toString())));
        }
    }

    private boolean consumeSignedNumber() {
        proceed(1);
        final char current = CURRENT();
        if (number(current)) return consumeNumber();
        else return unexpectedEnd(NUMS, current);
    }

    private boolean stringConsumption(final StringBuilder buffer) {
        char current;
        while (true) {
            current = CURRENT();
            if (string(current)) {
                proceed(1);
                return succeed(new JString(buffer.toString()));
            }
            else {
                buffer.append(current);
                proceed(1);
            }
        }
    }

    private boolean consumeString() {
        final StringBuilder buffer = new StringBuilder();
        final char current = CURRENT();
        if (string(current)) {
            buffer.append(current);
            proceed(1);
            return stringConsumption(buffer);
        }
        else return unexpectedEnd(QUOTE, current);
    }

    private boolean consumeComma(final char until) {
        skipFiller();
        final char current = CURRENT();
        if (current == until) { return SUCCESSFUL; }
        else if (comma(current)) {
            proceed(1);
            skipFiller();
            if (CURRENT() == until) {
                return unexpectedEnd(JSON, until);
            }
            else return SUCCESSFUL;
        }
        else return unexpectedEnd(COMMA, current);
    }

    private boolean consumePair() {
        skipFiller();
        final char current = CURRENT();
        if (pair(current)) {
            proceed(1);
            skipFiller();
            return SUCCESSFUL;
        }
        else return unexpectedEnd(COLON, current);
    }

    private boolean consumeTrue() {
        if (hasNext(3)) {
            final char rchar = atNext(1);
            final char uchar = atNext(2);
            final char echar = atNext(3);
            if (truth(CURRENT(), rchar, uchar, echar)) {
                proceed(4);
                return succeed(JBool.jtrue);
            }
            else return abruptEnd(BOOLS);
        } else return abruptEnd(BOOLS);
    }

    private boolean consumeFalse() {
        if (hasNext(4)) {
            final char achar = atNext(1);
            final char lchar = atNext(2);
            final char schar = atNext(3);
            final char echar = atNext(4);
            if (falsity(CURRENT(), achar, lchar, schar, echar)) {
                proceed(5);
                return succeed(JBool.jfalse);
            }
            else return abruptEnd(BOOLS);
        } else return abruptEnd(BOOLS);
    }

    private boolean consumeNull() {
        if (hasNext(3)) {
            final char uchar  = atNext(1);
            final char lchar1 = atNext(2);
            final char lchar2 = atNext(3);
            if (nullity(CURRENT(), uchar, lchar1, lchar2)) {
                proceed(4);
                return succeed(JNull.instance);
            }
            else return abruptEnd(NULL);
        } else return abruptEnd(NULL);
    }

    private boolean consumeObj() {
        proceed(1);
        final HashMap<String, Json> fields = new HashMap<>();
        char current;
        while (true) {
            current = CURRENT();
            skipFiller();
            if (objClose(current)) {
                proceed(1);
                return succeed(new JObj(Map.from(fields)));
            }
            else if (consumeString()) {
                final String key = result.toString();
                if (consumePair()) {
                    if (consumeAny()) {
                        final Json value = result;
                        if (consumeComma(CPAREN)) fields.put(key, value);
                        else return FAILED;
                    } else return FAILED;
                } else return FAILED;
            }
            else return unexpectedEnd(JSON, current);
        }
    }

    private boolean consumeArr() {
        proceed(1);
        final ArrayList<Json> list = new ArrayList<>();
        char current;
        while(true) {
            current = CURRENT();
            skipFiller();
            if (arrClose(current)) {
                proceed(1);
                return succeed(new JArr(List.from(list)));
            }
            else if (consumeAny()) {
                final Json value = result;
                if (consumeComma(CBRAKET)) list.add(value);
                else return FAILED;
            }
            else return unexpectedEnd(JSON, current);
        }
    }

    private boolean consumeAny() {
        final char current = CURRENT();
        skipFiller();
        if (number(current)) return consumeNumber();
        else if (sign(current)) return consumeSignedNumber();
        else if (arrOpen(current)) return consumeArr();
        else if (objOpen(current)) return consumeObj();
        else if (string(current)) return consumeString();
        else if (n(current)) return consumeNull();
        else if (t(current)) return consumeTrue();
        else if (f(current)) return consumeFalse();
        else return unexpectedEnd(JSON, current);
    }

    public static Result parse (final String input) {
        final Parser p = new Parser(input);
        if (input.isEmpty()) return Result.succeed(p.result);
        else {
            try {
                if (p.consumeAny()) return Result.succeed(p.result);
                else return Result.failed(p.failure);
            } catch (Exception e) {
                return Result.failed(e.getMessage()); // do better
            }
        }
    }
}