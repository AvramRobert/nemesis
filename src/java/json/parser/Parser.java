package json.parser;

import io.lacuna.bifurcan.List;
import io.lacuna.bifurcan.Map;
import json.model.*;
import util.error.Either;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Parser {

    // == PARSING STATE INFO == //
    private final boolean SUCCESSFUL = true;
    private final boolean FAILED = false;
    private String failure = "";
    private Json result = JObj.empty;

    // == PARSING TEXT INFO == //
    private final String text;
    private final int length;
    private int cursor = 0;
    private int lines = 0;

    // == GENERAL CONSTANTS == //
    private final String NULL = "null";
    private final String BOOLS = "true, false";
    private final String NUMS  = "0-9";
    private final String JSON  = "{, [, \", 0-9, true, false or null";
    private final char S_COLON = ':';
    private final char S_COMMA = ',';
    private final char S_QUOTE = '\"';
    private final char COMMA = ',';
    private final char COLON = ':';
    private final char QUOTE = '\"';
    private final char O_CURLY = '{';
    private final char C_CURLY = '}';
    private final char O_BRACKET = '[';
    private final char C_BRACKET = ']';
    private final char N = 'n';
    private final char F = 'f';
    private final char T = 't';
    private final char MINUS = '-';
    private final char PLUS  = '+';
    private final char DECIMAL = '.';
    private final char EXP_S = 'e';
    private final char EXP_L = 'E';
    private final char SPACE = ' ';
    private final char NEWLINE = '\n';

    public Parser(final int cursor, final String input) {
        this.text = input;
        this.cursor = cursor;
        this.length = text.length();
    }

    private int left (final int subtextSize) {
        final int bound = cursor - subtextSize;
        return Math.max(bound, 0);
    }

    private int right (final int subtextSize) {
        final int bound = cursor + subtextSize;
        return Math.min(bound, length);
    }

    private String pointedSample(final int subtextSize) {
        int middle  = subtextSize / 2;
        return text.substring(left(middle), cursor) + " <-| " + text.substring(cursor, right(middle));
    }

    private boolean hasNext(final int i) { return (cursor + i) < length; }

    private String failureMessage(final String prelude) {
        return String.format("%s\nFailed at line: %d\n%s", prelude, lines, pointedSample(30));
    }

    private boolean unexpectedEnd (final String expected, final char received) {
        final String msg = String.format("Unexpected end of input. Expected any of `%s` but received `%c`.", expected, received);
        this.failure = failureMessage(msg);
        return FAILED;
    }

    private boolean unexpectedEnd (final char expected, final char received) {
        final String msg = String.format("Unexpected end of input. Expected `%c` but received `%c`.", expected, received);
        this.failure = failureMessage(msg);
        return FAILED;
    }

    private boolean abruptEnd (final String expected) {
        final String msg = String.format("Unexpected end of input. Expected any of %s but received nothing.", expected);
        this.failure = failureMessage(msg);
        return FAILED;
    }

    private boolean abruptEnd (final Character... expected) {
        String msg = "";
        if (expected.length > 1) {
            msg = String.format("Unexpected end of input. Expected `%c`, but received nothing", expected[0]);
        } else {
            msg = String.format("Unexpected end of input. Expected any of `%s`, but received nothing.", Arrays.toString(expected));
        }

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

    private void skip() {
        char current;
        while (cursor < length) {
            current = text.charAt(cursor);
            if (current == SPACE) {
                cursor ++;
            }
            else if (current == NEWLINE) {
                cursor ++;
                lines ++;
            }
            else break;
        }
    }

    private boolean consumeNumeral(final int start) {
        char current;
        while (cursor < length) {
            current = text.charAt(cursor);
            if (number(current)) {
                cursor ++;
            }
            else {
                final double number = Double.parseDouble(text.substring(start, cursor));
                return succeed(new JNum(number));
            }
        }

        if (start < cursor) {
            final double number = Double.parseDouble(text.substring(start, cursor));
            return succeed(new JNum(number));
        }
        else return abruptEnd(NUMS);
    }

    private boolean consumeDecimal(final int start) {
        cursor ++;
        boolean atLeastOneNumber = FAILED;
        while (cursor < length) {
            final char current = text.charAt(cursor);
            if (number(current)) {
                atLeastOneNumber = SUCCESSFUL;
                cursor ++;
            }
            else if (atLeastOneNumber) {
                if (current == EXP_S || current == EXP_L) {
                    return consumeExponent(start);
                } else {
                    final double number = Double.parseDouble(text.substring(start, cursor));
                    return succeed(new JNum(number));
                }
            }
            else {
                return unexpectedEnd(NUMS, current);
            }
        }
        if (atLeastOneNumber) {
            final double number = Double.parseDouble(text.substring(start, cursor));
            return succeed(new JNum(number));
        }
        else return abruptEnd(NUMS);
    }

    private boolean consumeExponent(final int start) {
        cursor ++;
        if (cursor < length) {
            final char current = text.charAt(cursor);
            if (number(current)) {
                return consumeNumeral(start);
            }
            else if (current == MINUS || current == PLUS) {
                cursor ++; // consume the sign
                return consumeNumeral(start);
            }
            else {
                return unexpectedEnd(NUMS, current);
            }
        } else return abruptEnd(NUMS);
    }

    private boolean consumeNumber(final int start) {
        char current;
        while (cursor < length) {
            current = text.charAt(cursor);
            if (number(current)) {
                cursor ++;
            }
            else if (current == DECIMAL) {
                return consumeDecimal(start);
            }
            else if (current == EXP_S || current == EXP_L) {
                return consumeExponent(start);
            }
            else {
                final int number = Integer.parseInt(text.substring(start, cursor));
                return succeed(new JNum(number));
            }
        }

        if (start < cursor) {
            final int number = Integer.parseInt(text.substring(start, cursor));
            return succeed(new JNum(number));
        }
        else return abruptEnd(NUMS);
    }

    private boolean consumeSignedNumber() {
        final int start = cursor;
        cursor ++;
        if (cursor < length) {
            final char current = text.charAt(cursor);
            if (number(current)) return consumeNumber(start);
            else return unexpectedEnd(NUMS, current);
        } else return abruptEnd(NUMS);
    }

    private boolean consumeStringContent() {
        char current;
        while (cursor < length) {
            current = text.charAt(cursor);
            if (current == QUOTE) {
                cursor ++; // also consume the last ' " '
                return SUCCESSFUL;
            }
            else cursor++;
        }
        return abruptEnd(JSON);
    }

    private boolean consumeString() {
        final char current = text.charAt(cursor);
        if (current == QUOTE) {
            final int start = cursor;
            cursor ++;
            if (consumeStringContent()) {
                final String sub = text.substring(start, cursor);
                return succeed(new JString(sub));
            }
            else return FAILED;
        }
        else return unexpectedEnd(S_QUOTE, current);
    }

    private boolean consumeComma(final char until) {
        skip();
        if (cursor < length) {
            final char current = text.charAt(cursor);
            if (current == COMMA) {
                cursor ++;
                skip();
                if (hasNext(0) && text.charAt(cursor) == until) {
                    return unexpectedEnd(JSON, until);
                }
                else return SUCCESSFUL;
            }
            else if (current == until) { return SUCCESSFUL; }
            else return unexpectedEnd(S_COMMA, current);
        }
        else return abruptEnd(S_COMMA, until);
    }

    private boolean consumePair() {
        skip();
        if (cursor < length) {
            final char current = text.charAt(cursor);
            if (current == COLON) {
                cursor ++;
                skip();
                return SUCCESSFUL;
            }
            else return unexpectedEnd(S_COLON, current);
        }
        else return abruptEnd(COLON);
    }

    private boolean consumeTrue() {
        if (hasNext(3)) {
            final char t = text.charAt(cursor);
            final char r = text.charAt(cursor + 1);
            final char u = text.charAt(cursor + 2);
            final char e = text.charAt(cursor + 3);
            if (truth(t, r, u, e)) {
                cursor += 4;
                return succeed(JBool.jtrue);
            }
            else return abruptEnd(BOOLS);
        } else return abruptEnd(BOOLS);
    }

    private boolean consumeFalse() {
        if (hasNext(4)) {
            final char f = text.charAt(cursor);
            final char a = text.charAt(cursor + 1);
            final char l = text.charAt(cursor + 2);
            final char s = text.charAt(cursor + 3);
            final char e = text.charAt(cursor+ 4);
            if (falsity(f, a, l, s, e)) {
                cursor += 5;
                return succeed(JBool.jfalse);
            }
            else return abruptEnd(BOOLS);
        } else return abruptEnd(BOOLS);
    }

    private boolean consumeNull() {
        if (hasNext(3)) {
            final char n  = text.charAt(cursor);
            final char u  = text.charAt(cursor + 1);
            final char l1 = text.charAt(cursor + 2);
            final char l2 = text.charAt(cursor + 3);
            if (nullity(n, u, l1, l2)) {
                cursor += 4;
                return succeed(JNull.instance);
            }
            else return abruptEnd(NULL);
        } else return abruptEnd(NULL);
    }

    private boolean consumeObj() {
        cursor ++;
        final HashMap<String, Json> fields = new HashMap<>();
        char current;
        while (true) {
            current = text.charAt(cursor);
            skip();
            if (current == C_CURLY) {
                cursor ++;
                return succeed(new JObj(Map.from(fields)));
            }
            else if (consumeString()) {
                final String key = result.toString();
                if (consumePair()) {
                    if (consumeAny()) {
                        final Json value = result;
                        if (consumeComma(C_CURLY)) fields.put(key, value);
                        else return FAILED;
                    } else return FAILED;
                } else return FAILED;
            }
            else return unexpectedEnd(JSON, current);
        }
    }

    private boolean consumeArr() {
        cursor ++;
        final ArrayList<Json> list = new ArrayList<>();
        char current;
        while(true) {
            current = text.charAt(cursor);
            skip();
            if (current == C_BRACKET) {
                cursor ++;
                return succeed(new JArr(List.from(list)));
            }
            else {
                if (consumeAny()) {
                    final Json value = result;
                    if (consumeComma(C_BRACKET)) list.add(value);
                    else return FAILED;
                }
                else return FAILED;
            }
        }
    }

    private boolean consumeAny() {
        skip();
        if (cursor < length) {
            final char current = text.charAt(cursor);
            if (number(current))           return consumeNumber(cursor);
            else if (current == O_BRACKET) return consumeArr();
            else if (current == O_CURLY)   return consumeObj();
            else if (current == QUOTE)     return consumeString();
            else if (current == N)         return consumeNull();
            else if (current == T)         return consumeTrue();
            else if (current == F)         return consumeFalse();
            else if (current == MINUS)     return consumeSignedNumber();
            else if (current == PLUS)      return consumeSignedNumber();
            else                           return unexpectedEnd(JSON, current);
        }
        else return abruptEnd(JSON);
    }

    public static Either<String, Json> parse (final String input) {
        final Parser p = new Parser(0, input);
        if (input.isEmpty()) return Either.left("No input to parse.");
        else {
            try {
                if (p.consumeAny()) return Either.right(p.result);
                else return Either.left(p.failure);
            } catch (Exception e) {
                e.printStackTrace();
                return Either.left(e.getMessage()); // do better
            }
        }
    }
}