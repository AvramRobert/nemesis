package com.ravram.nemesis.parser;

import com.ravram.nemesis.Attempt;
import com.ravram.nemesis.Json;
import com.ravram.nemesis.model.*;
import io.lacuna.bifurcan.List;
import io.lacuna.bifurcan.Map;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Parser {

    // == GENERAL CONSTANTS == //
    private static final int SUBTEXT_SIZE = 30;
    private static final char COMMA = ',';
    private static final char COLON = ':';
    private static final char QUOTE = '\"';
    private static final char BACKSLASH = '\\';
    private static final char NEWLINE = '\n';

    private static final char O_CURLY = '{';
    private static final char C_CURLY = '}';
    private static final char O_BRACKET = '[';
    private static final char C_BRACKET = ']';
    private static final char N = 'n';
    private static final char U = 'u';
    private static final char L = 'l';
    private static final char F = 'f';
    private static final char A = 'a';
    private static final char S = 's';
    private static final char E = 'e';
    private static final char T = 't';
    private static final char R = 'r';
    private static final char B = 'b';
    private static final char MINUS = '-';
    private static final char PLUS = '+';
    private static final char DECIMAL = '.';
    private static final char EXP_S = 'e';
    private static final char EXP_L = 'E';
    private static final char SPACE = ' ';
    private static final char ZERO = '0';
    private static final char ONE = '1';
    private static final char TWO = '2';
    private static final char THREE = '3';
    private static final char FOUR = '4';
    private static final char FIVE = '5';
    private static final char SIX = '6';
    private static final char SEVEN = '7';
    private static final char EIGHT = '8';
    private static final char NINE = '9';

    private static final String TEXT_PATTERN = "text";

    public static final String ESCAPE_PATTERN =
            join(quote(QUOTE),
                    quote(BACKSLASH),
                    quote(R),
                    quote(N),
                    quote(F),
                    quote(T),
                    quote(B));

    private static final String A_PATTERN = quote(A);
    private static final String L_PATTERN = quote(L);
    private static final String S_PATTERN = quote(S);
    private static final String E_PATTERN = quote(E);
    private static final String U_PATTERN = quote(U);
    private static final String R_PATTERN = quote(R);
    private static final String COLON_PATTERN = quote(COLON);

    private static final String NUM_PATTERN =
            join(quote(ONE),
                    quote(TWO),
                    quote(THREE),
                    quote(FOUR),
                    quote(FIVE),
                    quote(SIX),
                    quote(SEVEN),
                    quote(EIGHT),
                    quote(NINE));

    private static final String BOOL_PATTERN =
            join(quote("true"), quote("false"));

    private static final String NULL_PATTERN =
            quote("null");

    private static final String JSON_PATTERN =
            join(join(
                    quote(O_CURLY),
                    quote(O_BRACKET),
                    quote(QUOTE)),
                    BOOL_PATTERN,
                    NULL_PATTERN,
                    NUM_PATTERN);

    private static final String OBJ_KEY_PATTERN =
            join(quote(QUOTE), quote(C_CURLY));

    private static final String OBJ_VAL_PATTERN =
            join(quote(COMMA), quote(C_CURLY));

    private static final String ARR_VAL_PATTERN =
            join(quote(COMMA), quote(C_BRACKET));

    private static final boolean SUCCESSFUL = true;
    private static final boolean FAILED = false;

    // == PARSING STATE INFO == //
    private String failure = "";
    private Json result = JObj.empty;

    // == PARSING TEXT INFO == //
    private final String text;
    private final int length;
    private int cursor = 0;
    private int lines = 0;

    Parser(final String input) {
        this.text = input;
        this.length = text.length();
    }

    private static String quote(final char c) {
        return String.format("`%c`", c);
    }

    private static String quote(final String s) {
        return String.format("`%s`", s);
    }

    private static String join(final String... strings) {
        return String.join(" | ", strings);
    }

    private int left(final int subtextSize) {
        final int bound = cursor - subtextSize;
        return Math.max(bound, 0);
    }

    private int right(final int subtextSize) {
        final int bound = cursor + subtextSize;
        return Math.min(bound, length);
    }

    private String pointedSample(final int subtextSize) {
        final int middle = subtextSize / 2;
        final String left = text.substring(left(middle), cursor);
        final String right = text.substring(cursor, right(middle));
        if (right.length() > 0) return left + " <- here -> " + right;
        else return left + " <- here";
    }

    private String failureMessage(final String prelude) {
        return String.format("%s\nFailed at line: %d\n%s", prelude, lines, pointedSample(SUBTEXT_SIZE));
    }

    private boolean unexpected(final char received, final String expected) {
        final String msg = String.format("Unexpected input. Expected %s but received `%c`.", expected, received);
        this.failure = failureMessage(msg);
        return FAILED;
    }

    private boolean abruptEnd(final String expected) {
        final String msg = String.format("Unexpected end of input. Expected %s but received nothing.", expected);
        this.failure = failureMessage(msg);
        return FAILED;
    }

    private boolean succeed(final Json json) {
        this.result = json;
        return SUCCESSFUL;
    }

    private boolean consumeNumeral(final int start) {
        cursor++; // consume first number
        while (cursor < length) {
            char current = text.charAt(cursor);
            switch (current) {
                case ZERO:
                case ONE:
                case TWO:
                case THREE:
                case FOUR:
                case FIVE:
                case SIX:
                case SEVEN:
                case EIGHT:
                case NINE:
                    cursor++;
                    continue;
                default:
                    final double number = Double.parseDouble(text.substring(start, cursor));
                    return succeed(new JNum(number));
            }
        }
        // the entry point guarantees that I always have a valid number
        final double number = Double.parseDouble(text.substring(start, cursor));
        return succeed(new JNum(number));
    }

    private boolean consumeSignedExponent(final int start) {
        cursor++; // consume `+`, `-`
        if (cursor < length) {
            char current = text.charAt(cursor);
            switch (current) {
                case ZERO:
                case ONE:
                case TWO:
                case THREE:
                case FOUR:
                case FIVE:
                case SIX:
                case SEVEN:
                case EIGHT:
                case NINE:
                    return consumeNumeral(start);
                default:
                    return unexpected(current, NUM_PATTERN);
            }
        }
        return abruptEnd(NUM_PATTERN);
    }

    private boolean consumeExponent(final int start) {
        cursor++; // consume `E`, `e`
        if (cursor < length) {
            char current = text.charAt(cursor);
            switch (current) {
                case ZERO:
                case ONE:
                case TWO:
                case THREE:
                case FOUR:
                case FIVE:
                case SIX:
                case SEVEN:
                case EIGHT:
                case NINE:
                    return consumeNumeral(start);
                case MINUS:
                case PLUS:
                    return consumeSignedExponent(start);
                default:
                    return unexpected(current, NUM_PATTERN);
            }
        } else return abruptEnd(NUM_PATTERN);
    }

    private boolean consumeDecimal(final int start) {
        cursor++; // consume `.`
        int decimalStart = cursor;
        while (cursor < length) {
            char current = text.charAt(cursor);
            switch (current) {
                case ZERO:
                case ONE:
                case TWO:
                case THREE:
                case FOUR:
                case FIVE:
                case SIX:
                case SEVEN:
                case EIGHT:
                case NINE:
                    cursor++; // consume number
                    continue;
                case EXP_S:
                case EXP_L:
                    if (decimalStart < cursor) return consumeExponent(start);
                    else return unexpected(current, NUM_PATTERN);
                default:
                    // a character is consumed that isn't related to numbers
                    // decimalStart dictates when I start counting after the decimal point
                    if (decimalStart < cursor) {
                        final double number = Double.parseDouble(text.substring(start, cursor));
                        return succeed(new JNum(number));
                    } else unexpected(current, NUM_PATTERN);
            }
        }
        // the input is exhausted, but there's enough digits to yield a number
        if (decimalStart < cursor) {
            final double number = Double.parseDouble(text.substring(start, cursor));
            return succeed(new JNum(number));
        } else return abruptEnd(NUM_PATTERN);
    }

    private boolean consumeNumber(final int start) {
        cursor++; // consume first number
        while (cursor < length) {
            char current = text.charAt(cursor);
            switch (current) {
                case ZERO:
                case ONE:
                case TWO:
                case THREE:
                case FOUR:
                case FIVE:
                case SIX:
                case SEVEN:
                case EIGHT:
                case NINE:
                    cursor++;
                    continue;
                case DECIMAL:
                    return consumeDecimal(start);
                case EXP_S:
                case EXP_L:
                    return consumeExponent(start);
                default:
                    final int number = Integer.parseInt(text.substring(start, cursor));
                    return succeed(new JNum(number));
            }
        }
        // the entry point guarantees enough digits for a number
        final int number = Integer.parseInt(text.substring(start, cursor));
        return succeed(new JNum(number));
    }

    private boolean consumeSignedNumber() {
        final int start = cursor;
        cursor++; // consume `+`, `-`
        if (cursor < length) {
            char current = text.charAt(cursor);
            switch (current) {
                case ZERO:
                case ONE:
                case TWO:
                case THREE:
                case FOUR:
                case FIVE:
                case SIX:
                case SEVEN:
                case EIGHT:
                case NINE:
                    return consumeNumber(start);
                default:
                    return unexpected(current, NUM_PATTERN);
            }
        } else return abruptEnd(NUM_PATTERN);
    }

    private boolean consumeTrue() {
        cursor++; // consume `t`
        char current;
        if (cursor < length) {
            current = text.charAt(cursor);
            if (current == R) {
                cursor++; // consume `r`
                if (cursor < length) {
                    current = text.charAt(cursor);
                    if (current == U) {
                        cursor++; // consume `u`
                        if (cursor < length) {
                            current = text.charAt(cursor);
                            if (current == E) {
                                cursor++; // consume `e`
                                return succeed(JBool.jtrue);
                            } else return unexpected(current, E_PATTERN);
                        } else return abruptEnd(E_PATTERN);
                    } else return unexpected(current, U_PATTERN);
                } else return abruptEnd(U_PATTERN);
            } else return unexpected(current, R_PATTERN);
        } else return abruptEnd(R_PATTERN);
    }

    private boolean consumeFalse() {
        cursor++; // consume `f`
        char current;
        if (cursor < length) {
            current = text.charAt(cursor);
            if (current == A) {
                cursor++; // consume `a`
                if (cursor < length) {
                    current = text.charAt(cursor);
                    if (current == L) {
                        cursor++; // consume `l`
                        if (cursor < length) {
                            current = text.charAt(cursor);
                            if (current == S) {
                                cursor++; // consume `s`
                                if (cursor < length) {
                                    current = text.charAt(cursor);
                                    if (current == E) {
                                        cursor++; // consume `e`
                                        return succeed(JBool.jfalse);
                                    } else return unexpected(current, E_PATTERN);
                                } else return abruptEnd(E_PATTERN);
                            } else return unexpected(current, S_PATTERN);
                        } else return abruptEnd(S_PATTERN);
                    } else return unexpected(current, L_PATTERN);
                } else return abruptEnd(L_PATTERN);
            } else return unexpected(current, A_PATTERN);
        } else return abruptEnd(A_PATTERN);
    }

    private boolean consumeNull() {
        cursor++; // consume 'n';
        char current;
        if (cursor < length) {
            current = text.charAt(cursor);
            if (current == U) {
                cursor++; // consume `u`
                if (cursor < length) {
                    current = text.charAt(cursor);
                    if (current == L) {
                        cursor++; // consume `l`
                        if (cursor < length) {
                            current = text.charAt(cursor);
                            if (current == L) {
                                cursor++; // consume 'l`
                                return succeed(JNull.instance);
                            } else return unexpected(current, L_PATTERN);
                        } else return abruptEnd(L_PATTERN);
                    } else return unexpected(current, L_PATTERN);
                } else return abruptEnd(L_PATTERN);
            } else return unexpected(current, U_PATTERN);
        } else return abruptEnd(U_PATTERN);
    }

    private boolean consumeEscape(final StringBuilder bld) {
        cursor++; // consume '\'
        if (cursor < length) {
            final char current = text.charAt(cursor);
            switch (current) {
                case QUOTE:
                case BACKSLASH:
                    cursor++;
                    bld.append(current);
                    return SUCCESSFUL;
                case N:
                    cursor++;
                    bld.append("\n");
                    return SUCCESSFUL;
                case F:
                    cursor++;
                    bld.append("\f");
                    return SUCCESSFUL;
                case T:
                    cursor++;
                    bld.append("\t");
                    return SUCCESSFUL;
                case B:
                    cursor++;
                    bld.append("\b");
                    return SUCCESSFUL;
                case R:
                    cursor++;
                    bld.append("\r");
                    return SUCCESSFUL;
                default:
                    return unexpected(current, ESCAPE_PATTERN);
            }
        }
        return abruptEnd(ESCAPE_PATTERN);
    }

    private boolean consumeStringEscaping(final int start) {
        final StringBuilder bld = new StringBuilder(text.substring(start, cursor));
        while (cursor < length) {
            final char current = text.charAt(cursor);
            if (current == BACKSLASH) {
                if (!consumeEscape(bld)) {
                    return FAILED;
                }
            } else if (current == QUOTE) {
                cursor++; // consume `"`
                final Json value = new JString(bld.toString());
                return succeed(value);
            } else {
                cursor++;
                bld.append(current);
            }
        }
        return abruptEnd(TEXT_PATTERN);
    }

    private boolean consumeString() {
        cursor++; // consume `"`
        int start = cursor;
        while (cursor < length) {
            final char current = text.charAt(cursor);
            if (current == BACKSLASH) {
                return consumeStringEscaping(start);
            }
            else if (current == QUOTE) {
                final Json value = new JString(text.substring(start, cursor));
                cursor++; // consume `"`
                return succeed(value);
            }
            else {
                cursor++;
            }
        }
        return abruptEnd(TEXT_PATTERN);
    }

    private boolean consumeColon() {
        while (cursor < length) {
            final char current = text.charAt(cursor);
            switch (current) {
                case COLON:
                    cursor++; // consume `:`
                    return SUCCESSFUL;
                case NEWLINE:
                    lines++;
                case SPACE:
                    cursor++;
                    continue;
                default:
                    return unexpected(current, COLON_PATTERN);
            }
        }
        return abruptEnd(COLON_PATTERN);
    }

    private boolean consumeObjEnd() {
        while (cursor < length) {
            final char current = text.charAt(cursor);
            switch (current) {
                case C_CURLY:
                case COMMA:
                    return SUCCESSFUL;
                case NEWLINE:
                    lines++;
                case SPACE:
                    cursor++;
                    continue;
                default:
                    return unexpected(current, OBJ_VAL_PATTERN);
            }
        }
        return abruptEnd(OBJ_VAL_PATTERN);
    }

    private boolean consumeObj() {
        cursor++; // consume `{`
        final HashMap<String, Json> map = new HashMap<>();
        boolean beginning = true;
        while (cursor < length) {
            char current = text.charAt(cursor);
            switch (current) {
                case NEWLINE:
                    lines++;
                case SPACE:
                    cursor++;
                    continue;
                case C_CURLY:
                    if (beginning) {
                        cursor++; // consume `}`;
                        return succeed(JObj.empty);
                    } else return unexpected(C_CURLY, JSON_PATTERN);
                case QUOTE:
                    beginning = false;
                    if (consumeString()) {
                        final String key = result.toString();
                        if (consumeColon()) {
                            if (consumeAny()) {
                                final Json value = result;
                                if (consumeObjEnd()) {
                                    map.put(key, value);
                                    if (text.charAt(cursor) == C_CURLY) {
                                        cursor++; // consume `}`
                                        return succeed(new JObj(Map.from(map)));
                                    }
                                    cursor++; // consume `,`
                                    continue;
                                } else return FAILED;
                            } else return FAILED;
                        } else return FAILED;
                    } else return FAILED;
                default:
                    return unexpected(current, OBJ_KEY_PATTERN);
            }
        }
        return abruptEnd(OBJ_KEY_PATTERN);
    }

    private boolean consumeArrEnd() {
        while (cursor < length) {
            final char current = text.charAt(cursor);
            switch (current) {
                case C_BRACKET:
                case COMMA:
                    return SUCCESSFUL;
                case NEWLINE:
                    lines++;
                case SPACE:
                    cursor++;
                    continue;
                default:
                    return unexpected(current, ARR_VAL_PATTERN);
            }
        }
        return abruptEnd(ARR_VAL_PATTERN);
    }

    private boolean consumeArr() {
        cursor++; // consume `[`
        final ArrayList<Json> list = new ArrayList<>();
        boolean beginning = true;
        while (cursor < length) {
            switch (text.charAt(cursor)) {
                case C_BRACKET:
                    if (beginning) {
                        cursor++; // consume `]`
                        return succeed(JArr.empty);
                    } else return unexpected(C_BRACKET, JSON_PATTERN);
                case COMMA:
                    return unexpected(COMMA, JSON_PATTERN);
                default:
                    beginning = false;
                    if (consumeAny()) {
                        final Json value = result;
                        if (consumeArrEnd()) {
                            list.add(value);
                            switch (text.charAt(cursor)) {
                                case C_BRACKET:
                                    cursor++; // consume `]`
                                    return succeed(new JArr(List.from(list)));
                                case COMMA:
                                    cursor++; // consume `,`
                            }
                        } else return FAILED;
                    } else return FAILED;
            }
        }
        return abruptEnd(JSON_PATTERN);
    }

    private boolean consumeAny() {
        while (cursor < length) {
            final char current = text.charAt(cursor);
            switch (current) {
                case O_CURLY:
                    return consumeObj();
                case O_BRACKET:
                    return consumeArr();
                case N:
                    return consumeNull();
                case T:
                    return consumeTrue();
                case F:
                    return consumeFalse();
                case QUOTE:
                    return consumeString();
                case MINUS:
                case PLUS:
                    return consumeSignedNumber();
                case ZERO:
                case ONE:
                case TWO:
                case THREE:
                case FOUR:
                case FIVE:
                case SIX:
                case SEVEN:
                case EIGHT:
                case NINE:
                    return consumeNumber(cursor);
                case NEWLINE:
                    lines++;
                case SPACE:
                    cursor++;
                    continue;
                default:
                    return unexpected(current, JSON_PATTERN);
            }
        }
        return abruptEnd(JSON_PATTERN);
    }

    public static Attempt<Json> parse(final String input) {
        final Parser p = new Parser(input);
        if (input.isEmpty()) return Attempt.failure("No input to parse.");
        else {
            try {
                if (p.consumeAny()) {
                    if (p.cursor < p.length) {
                        return Attempt.failure("Unexpected 'EOF'. Input ended abruptly.\nInput was unparsable after: %s", p.pointedSample(10));
                    }
                    else return Attempt.success(p.result);
                }
                else return Attempt.failure(p.failure);
            } catch (Exception e) {
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                e.printStackTrace(new PrintStream(baos));
                return Attempt.failure(baos.toString());
            }
        }
    }
}