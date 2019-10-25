package json.parser;

import io.lacuna.bifurcan.List;
import io.lacuna.bifurcan.Map;
import json.data.*;
import java.util.ArrayList;
import java.util.HashMap;
import static json.parser.Consumption.*;

public class JsonParser {
    private static final int READY = 0;
    private static final int STARTED = 1;
    private static final String NULL = "NULL";
    private static final String BOOLS = "true,false";
    private static final String NUMS  = "0-9";
    private static final String JSON  = "{, [, \", 0-9, true, false or null";
    private static final String COLON = ":";
    private static final String COMMA = ",";
    private static final String QUOTE = "\"";
    private static final char CBRAKET = ']';
    private static final char CPAREN  = '}';

    private static String failureMessage(final Seeker seeker, final String msg) {
        final String sample = seeker.pointedSample(20);
        return String.format("%s\nFailed at line: %d\n%s", msg, seeker.line(), sample);
    }

    private static String unexpectedEnd (final Seeker seeker, final String expected, final char received) {
        final String msg = String.format("Unexpected end of input. Expected `%s`, received `%c`", expected, received);
        return failureMessage(seeker, msg);
    }

    private static String abruptEnd (final Seeker seeker, final String expected) {
        final String msg = String.format("Unexpected end of input. Expected `%s`, received nothing", expected);
        return failureMessage(seeker, msg);
    }

    private static boolean number(final char c) {
        return c == '0' ||
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

    private static boolean objOpen(final char c) {
        return c == '{';
    }

    private static boolean objClose(final char c) {
        return c == '}';
    }

    private static boolean arrOpen (final char c) {
        return c == '[';
    }

    private static boolean arrClose (final char c) { return c == ']'; }

    private static boolean string (final char c) {
        return c == '"';
    }

    private static boolean sign (final char c) { return (c == '+') || (c == '-'); }

    private static boolean exponent(final char e) {
        return e == 'e' || e == 'E';
    }

    private static boolean space(final char c) {
        return c == ' ';
    }

    private static boolean newline (final char c) {
        return c == '\n';
    }

    private static boolean decimal(final char c) { return c == '.'; }

    private static boolean pair(final char c) { return c == ':'; }

    private static boolean comma(final char c) {
        return c == ',';
    }

    private static boolean ready(final int state) {
        return state == READY;
    }

    private static boolean started(final int state) { return state == STARTED; }

    private static boolean n(final char c) { return c == 'n'; }

    private static boolean t(final char c) { return c == 't'; }

    private static boolean f(final char c) { return c == 'f'; }

    private static boolean nullity(final char n, final char u, final char l1, final char l2) {
        return n == 'n' && u == 'u' && l1 == 'l' && l2 == 'l';
    }

    private static boolean truth(final char t, final char r, final char u, final char e) {
        return t == 't' && r == 'r' && u == 'u' && e == 'e';
    }

    private static boolean falsity(final char f, final char a, final char l, final char s, final char e) {
        return f == 'f' && a == 'a' && l == 'l' && s == 's' && e == 'e';
    }

    private static void skipFiller(final Seeker seeker) {
        while (true) {
            char current = seeker.current();
            if (space(current)) seeker.proceed(1);
            else if (newline(current)) seeker.jump();
            else break;
        }
    }

    private static Consumption consumeDecimal(final StringBuilder buffer, final Seeker seeker) {
        int state = READY;
        char current;
        while (true) {
            current = seeker.current();
            if (number(current)) {
                buffer.append(current);
                state = STARTED;
                seeker.proceed(1);
            }
            else if (ready(state)) return failed(unexpectedEnd(seeker, NUMS, current));
            else return succeed(new JNum(Double.parseDouble(buffer.toString())));
        }
    }

    private static Consumption consumeExponent(final StringBuilder buffer, final Seeker seeker) {
        int state = READY;
        char current;
        while (true) {
            current = seeker.current();
            if (number(current)) {
                buffer.append(current);
                state = STARTED;
                seeker.proceed(1);
            }
            else if (ready(state) && sign(current)) {
                buffer.append(current);
                state = STARTED;
                seeker.proceed(1);
            }
            else if (ready(state)) return failed(unexpectedEnd(seeker, NUMS, current));
            else return succeed(new JNum(Float.parseFloat(buffer.toString())));
        }
    }

    private static Consumption consumeNumber(final Seeker seeker) {
        final StringBuilder buffer  = new StringBuilder();
        int state = READY;
        char current;
        while (true) {
            current = seeker.current();
            if (number(current)) {
                buffer.append(current);
                state = STARTED;
                seeker.proceed(1);
            }
            else if (started(state)) {
                if (decimal(current)) {
                    seeker.proceed(1);
                    return consumeDecimal(buffer.append(current), seeker);
                }
                else if (exponent(current)) {
                    seeker.proceed(1);
                    return consumeExponent(buffer.append(current), seeker);
                }
                else return succeed(new JNum(Integer.parseInt(buffer.toString())));
            }
            else if (ready(state)) return failed(unexpectedEnd(seeker, NUMS, current));
            else return succeed(new JNum(Integer.parseInt(buffer.toString())));
        }
    }

    private static Consumption consumeString(final Seeker seeker) {
        final StringBuilder buffer = new StringBuilder();
        int state = READY;
        char current;
        while(true) {
            current = seeker.current();
            if (ready(state)) {
                state = STARTED;
                seeker.proceed(1);
            }
            else if (string(current) && started(state)) {
                seeker.proceed(1);
                break;
            }
            else if (started(state)) {
                buffer.append(current);
                seeker.proceed(1);
            }
            else {
                return failed(unexpectedEnd(seeker, QUOTE, current));
            }
        }
        return succeed(new JString(buffer.toString()));
    }

    private static Consumption consumeKey(final Seeker seeker) {
        final Consumption result = consumeString(seeker);
        if (result.succeeded()) return succeed(result.value());
        else return result;
    }

    private static Consumption consumeComma(final Seeker seeker, final char stop) {
        skipFiller(seeker);
        final char current = seeker.current();
        if (current == stop) return succeed(null);
        else if (comma(current)) {
            seeker.proceed(1);
            skipFiller(seeker);
            return succeed(null);
        }
        else return failed(unexpectedEnd(seeker, COMMA, current));
    }

    private static Consumption consumePair(final Seeker seeker) {
        skipFiller(seeker);
        final char current = seeker.current();
        if (pair(current)) {
            seeker.proceed(1);
            skipFiller(seeker);
            return succeed(null);
        }
        else return failed(unexpectedEnd(seeker, COLON, current));
    }

    private static Consumption consumeTrue(final Seeker seeker) {
        final char tchar = seeker.current();
        if (seeker.hasNext(3)) {
            final char rchar = seeker.atNext(1);
            final char uchar = seeker.atNext(2);
            final char echar = seeker.atNext(3);
            if (truth(tchar, rchar, uchar, echar)) {
                seeker.proceed(4);
                return succeed(JBool.jtrue);
            }
            else return failed(abruptEnd(seeker, BOOLS));
        } else return failed(abruptEnd(seeker, BOOLS));
    }

    private static Consumption consumeFalse(final Seeker seeker) {
        final char fchar = seeker.current();
        if (seeker.hasNext(4)) {
            final char achar = seeker.atNext(1);
            final char lchar = seeker.atNext(2);
            final char schar = seeker.atNext(3);
            final char echar = seeker.atNext(4);
            if (falsity(fchar, achar, lchar, schar, echar)) {
                seeker.proceed(5);
                return succeed(JBool.jfalse);
            }
            else return failed(abruptEnd(seeker, BOOLS));
        } else return failed(abruptEnd(seeker, BOOLS));
    }

    private static Consumption consumeNull(final Seeker seeker) {
        final char nchar = seeker.current();
        if (seeker.hasNext(3)) {
            final char uchar = seeker.atNext(1);
            final char lchar1 = seeker.atNext(2);
            final char lchar2 = seeker.atNext(3);
            if (nullity(nchar, uchar, lchar1, lchar2)) {
                seeker.proceed(4);
                return succeed(JNull.instance);
            }
            else return failed(abruptEnd(seeker, NULL));
        } else return failed(abruptEnd(seeker, NULL));
    }

    private static Consumption consumeObj(final Seeker seeker) {
        final HashMap<String, Json> fields = new HashMap<>();
        int state = READY;
        char current;
        while (true) {
            skipFiller(seeker);
            current = seeker.current();
            if (ready(state)) {
                state = STARTED;
                seeker.proceed(1);
            }
            else if (objClose(current) && started(state)) {
                seeker.proceed(1);
                break;
            }
            else if (started(state)) {
                final Consumption key = consumeKey(seeker);
                if (key.succeeded()) {
                    final Consumption separation = consumePair(seeker);
                    if (separation.succeeded()) {
                        final Consumption value = consumeAny(seeker);
                        if (value.succeeded()) {
                            final Consumption delimitation = consumeComma(seeker, CPAREN);
                            if (delimitation.succeeded()) {
                                fields.put(key.value().toString(), value.value());
                            }
                            else return delimitation;
                        }
                        else return value;
                    } else return separation;
                } else return key;
            }
            else failed(unexpectedEnd(seeker, JSON, current));
        }
        return succeed(new JObj(Map.from(fields)));
    }

    private static Consumption consumeArr(final Seeker seeker) {
        final ArrayList<Json> list = new ArrayList<>();
        int state = READY;
        char current;
        while(true) {
            skipFiller(seeker);
            current = seeker.current();
            if (ready(state)) {
                state = STARTED;
                seeker.proceed(1);
            }
            else if (started(state) && arrClose(current)) {
                seeker.proceed(1);
                break;
            }
            else if (started(state)) {
                final Consumption result = consumeAny(seeker);
                if (result.succeeded()) {
                    final Consumption delimitation = consumeComma(seeker, CBRAKET);
                    if (delimitation.succeeded()) list.add(result.value());
                    else return delimitation;
                }
                else return result;
            }
            else return failed (unexpectedEnd(seeker, JSON, current));
        }
        return succeed(new JArr(List.from(list)));
    }

    private static Consumption consumeAny(final Seeker seeker) {
        skipFiller(seeker);
        final char current = seeker.current();
        if (number(current)) {
            return consumeNumber(seeker);
        }
        else if (arrOpen(current)) {
            return consumeArr(seeker);
        }
        else if (objOpen(current)) {
            return consumeObj(seeker);
        }
        else if (string(current)) {
            return consumeString(seeker);
        }
        else if (n(current)) {
            return consumeNull(seeker);
        }
        else if (t(current)) {
            return consumeTrue(seeker);
        }
        else if (f(current)) {
            return consumeFalse(seeker);
        }
        else return failed(unexpectedEnd(seeker, JSON, current));
    }

    public static Consumption parse (final String input) {
        final Seeker seeker = new Seeker(input);
        if (input.isEmpty()) return succeed(JEmpty.empty);
        else {
            try {
                return consumeAny(seeker);
            } catch (Exception e) {
                return failed("Exception: " + e.getMessage()); // do better
            }
        }
    }
}