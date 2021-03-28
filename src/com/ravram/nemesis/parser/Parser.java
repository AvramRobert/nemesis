package com.ravram.nemesis.parser;

import com.ravram.nemesis.model.*;
import com.ravram.nemesis.util.error.Either;
import io.lacuna.bifurcan.List;
import io.lacuna.bifurcan.Map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Parser {

    // == GENERAL CONSTANTS == //
    private final String NULL = "null";
    private final String BOOLS = "true | false";
    private final String NUMS  = "0-9";
    private final String JSON  = "{ | [ | \" | 0-9 | true | false | null";
    private final String OBJC = "\" | }";
    private final String OBJN = ", | }";
    private final String ARRN = ", | ]";
    private final String ARRC = ", | ]";

    private final char COMMA = ',';
    private final char COLON = ':';
    private final char QUOTE = '\"';
    private final char O_CURLY = '{';
    private final char C_CURLY = '}';
    private final char O_BRACKET = '[';
    private final char C_BRACKET = ']';
    private final char N = 'n';
    private final char U = 'u';
    private final char L = 'l';
    private final char F = 'f';
    private final char A = 'a';
    private final char S = 's';
    private final char E = 'e';
    private final char T = 't';
    private final char R = 'r';
    private final char MINUS = '-';
    private final char PLUS  = '+';
    private final char DECIMAL = '.';
    private final char EXP_S = 'e';
    private final char EXP_L = 'E';
    private final char SPACE = ' ';
    private final char NEWLINE = '\n';
    private final char ZERO = '0';
    private final char ONE = '1';
    private final char TWO = '2';
    private final char THREE = '3';
    private final char FOUR = '4';
    private final char FIVE = '5';
    private final char SIX = '6';
    private final char SEVEN = '7';
    private final char EIGHT = '8';
    private final char NINE = '9';

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

    Parser(final int cursor, final String input) {
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

    private String failureMessage(final String prelude) {
        return String.format("%s\nFailed at line: %d\n%s", prelude, lines, pointedSample(30));
    }

    private boolean unexpectedEnd (final String expected, final char received) {
        final String msg = String.format("Unexpected end of input. Expected `%s` but received `%c`.", expected, received);
        this.failure = failureMessage(msg);
        return FAILED;
    }

    private boolean unexpectedEnd (final char expected, final char received) {
        final String msg = String.format("Unexpected end of input. Expected `%c` but received `%c`.", expected, received);
        this.failure = failureMessage(msg);
        return FAILED;
    }

    private boolean abruptEnd (final String expected) {
        final String msg = String.format("Unexpected end of input. Expected `%s` but received nothing.", expected);
        this.failure = failureMessage(msg);
        return FAILED;
    }

    private boolean abruptEnd (final char... expected) {
        if (expected.length > 1) {
            return abruptEnd(Arrays.toString(expected));
        } else {
            return abruptEnd(String.valueOf(expected[0]));
        }
    }

    private boolean succeed(final Json json) {
        this.result = json;
        return SUCCESSFUL;
    }

    private boolean consumeNumeral(final int start) {
        cursor++; // consume first `0-9`
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
                    return unexpectedEnd(NUMS, current);
            }
        }
        return abruptEnd(NUMS);
    }

    private boolean consumeExponent(final int start) {
        cursor ++; // consume the `E`, `e`
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
                    return unexpectedEnd(NUMS, current);
            }
        }
        else return abruptEnd(NUMS);
    }

    private boolean consumeDecimal(final int start) {
        cursor ++; // consume `.`
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
                    cursor++;
                    continue;
                case EXP_S:
                case EXP_L:
                    if (decimalStart < cursor) return consumeExponent(start);
                    else return unexpectedEnd(NUMS, current);
                default:
                    // a character is consumed that isn't related to numbers
                    // decimalStart dictates when I start counting after the decimal point
                    if (decimalStart < cursor) {
                        final double number = Double.parseDouble(text.substring(start, cursor));
                        return succeed(new JNum(number));
                    }
                    else unexpectedEnd(NUMS, current);
            }
        }
        // the input is exhausted, but there's enough digits to yield a number
        if (decimalStart < cursor) {
            final double number = Double.parseDouble(text.substring(start, cursor));
            return succeed(new JNum(number));
        }
        else return abruptEnd(NUMS);
    }

    private boolean consumeNumber(final int start) {
        cursor++; // consume first `0-9`
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
        // the entry point guarantees that I always have enough digits for a number
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
                    return unexpectedEnd(NUMS, current);
            }
        } else return abruptEnd(NUMS);
    }

    private boolean consumeTrue() {
        cursor++; // consume `t`
        if (cursor + 2 < length) {
            if (text.charAt(cursor) == R) {
                cursor++; // consume `r`
                if (text.charAt(cursor) == U) {
                    cursor++; // consume `u`
                    if (text.charAt(cursor) == E) {
                        cursor++; // consume `e`
                        return succeed(JBool.jtrue);
                    } else return unexpectedEnd(E, text.charAt(cursor));
                } else return unexpectedEnd(U, text.charAt(cursor));
            } else return unexpectedEnd(R, text.charAt(cursor));
        } else return abruptEnd(BOOLS);
    }

    private boolean consumeFalse() {
        cursor++; // consume `f`
        if (cursor + 3 < length) {
            if (text.charAt(cursor) == A) {
                cursor++; // consume `a`
                if (text.charAt(cursor) == L) {
                    cursor++; // consume `l`
                    if (text.charAt(cursor) == S) {
                        cursor++; // consume `s`
                        if (text.charAt(cursor) == E) {
                            cursor++; // consume `e`
                            return succeed(JBool.jfalse);
                        } else return unexpectedEnd(E, text.charAt(cursor));
                    } else return unexpectedEnd(S, text.charAt(cursor));
                } else return unexpectedEnd(L, text.charAt(cursor));
            } else return unexpectedEnd(A, text.charAt(cursor));
        } else return abruptEnd(BOOLS);
    }

    private boolean consumeNull() {
        cursor++; // consume 'n';
        if (cursor + 2 < length) {
            if (text.charAt(cursor) == U) {
                cursor++; // consume 'u'
                if (text.charAt(cursor) == L) {
                    cursor++; // consume 'l'
                    if (text.charAt(cursor) == L) {
                        cursor++; // consume 'l'
                        return succeed(JNull.instance);
                    } else return unexpectedEnd(L, text.charAt(cursor));
                } else return unexpectedEnd(L, text.charAt(cursor));
            } else return unexpectedEnd(U, text.charAt(cursor));
        } else return abruptEnd(NULL);
    }

    private boolean consumeString() {
        cursor++; // consume the `"`
        int start = cursor;
        while (cursor < length) {
            switch (text.charAt(cursor)) {
                case QUOTE:
                    final Json value = new JString(text.substring(start, cursor));
                    cursor++; // consume the `"`
                    return succeed(value);
                default:
                    cursor++;
            }
        }
        return abruptEnd(QUOTE);
    }

    private boolean consumeColon() {
        while (cursor < length) {
            final char current = text.charAt(cursor);
            switch (current) {
                case COLON:
                    cursor++; // consume the `:`
                    return SUCCESSFUL;
                case NEWLINE:
                    lines++;
                case SPACE:
                    cursor++;
                    continue;
                default:
                    return unexpectedEnd(COLON, current);
            }
        }
        return abruptEnd(COLON);
    }

    private boolean consumeObjEnd() {
        while (cursor < length) {
            final char current = text.charAt(cursor);
            switch (current) {
                case C_CURLY:
                case COMMA: return SUCCESSFUL;
                case NEWLINE:
                    lines++;
                case SPACE:
                    cursor++;
                    continue;
                default:
                    return unexpectedEnd(OBJN, current);
            }
        }
        return abruptEnd(OBJN);
    }

    private boolean consumeObj() {
        cursor++; // consume the `{`
        final HashMap<String, Json> map = new HashMap<>();
        while (cursor < length) {
            char current = text.charAt(cursor);
            switch (current) {
                case NEWLINE:
                    lines++;
                case SPACE:
                    cursor++;
                    continue;
                case C_CURLY:
                    cursor++; // consume the `}`;
                    return succeed(JObj.empty);
                case QUOTE:
                    if (consumeString()) {
                        final String key = result.toString();
                        if (consumeColon()) {
                            if (consumeAny()) {
                                final Json value = result;
                                if (consumeObjEnd()) {
                                    map.put(key, value);
                                    switch (text.charAt(cursor)) {
                                        case C_CURLY:
                                            cursor++; // consume the `}`
                                            return succeed(new JObj(Map.from(map)));
                                        default:
                                            cursor++; // consume the `,`
                                            continue;
                                    }
                                } else return FAILED;
                            } else return FAILED;
                        } else return FAILED;
                    } else return FAILED;
                default:
                    return unexpectedEnd(OBJC, current);
            }
        }
        return abruptEnd(QUOTE, C_CURLY);
    }

    private boolean consumeArrEnd() {
        while (cursor < length) {
            final char current = text.charAt(cursor);
            switch (current) {
                case C_BRACKET:
                case COMMA: return SUCCESSFUL;
                case NEWLINE:
                    lines++;
                case SPACE:
                    cursor++;
                    continue;
                default:
                    return unexpectedEnd(ARRC, current);
            }
        }
        return abruptEnd(ARRN);
    }

    private boolean consumeArr() {
        cursor++; // consume the `[`
        final ArrayList<Json> list = new ArrayList<>();
        while (cursor < length) {
            switch (text.charAt(cursor)) {
                case C_BRACKET:
                    cursor++; // consume the `]`
                    return succeed(JArr.empty);
                case COMMA:
                    return unexpectedEnd(JSON, COMMA);
                default:
                    if (consumeAny()) {
                        final Json value = result;
                        if (consumeArrEnd()) {
                            list.add(value);
                            switch (text.charAt(cursor)) {
                                case C_BRACKET:
                                    cursor++; // consume the `]`
                                    return succeed(new JArr(List.from(list)));
                                case COMMA:
                                    cursor++; // consume the `,`;
                            }
                        } else return FAILED;
                    } else return FAILED;
            }
        }
        return abruptEnd(JSON);
    }

    private boolean consumeAny() {
        while (cursor < length) {
            final char current = text.charAt(cursor);
            switch (current) {
                case O_CURLY: return consumeObj();
                case O_BRACKET: return consumeArr();
                case N: return consumeNull();
                case T: return consumeTrue();
                case F: return consumeFalse();
                case QUOTE: return consumeString();
                case MINUS:
                case PLUS: return consumeSignedNumber();
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
                default: return unexpectedEnd(JSON, current);
            }
        }
        return abruptEnd(JSON);
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