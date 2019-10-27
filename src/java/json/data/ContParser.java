package json.data;

import json.data.typed.JEmpty;
import json.data.typed.Json;

public class ContParser {

    final static int NUMBER   = 0;
    final static int DECIMAL  = 1;
    final static int EXPONENT = 2;
    final static int TRUE     = 3;
    final static int FALSE    = 4;
    final static int NULL     = 5;
    final static int STRING   = 6;
    final static int ARRAY    = 7;
    final static int OBJECT   = 8;

    private int detect(final char c) {
        return 0;
    }

    // If I want to remove recursion and still do nesting, I need a stack of some kind.
    // If the stack has some element on top, the primitive currently being parsed needs to be added to that element.
    // This may work ok for lists, but how do you do objects with key value pairs?
    // Probably with a separate state?
    public final Json parse(final String input) {
        int state;
        final StringBuilder buff = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            switch (detect(current)) {
                case NUMBER:
                    state = NUMBER;
                    buff.append(current);
                    ;
                case DECIMAL: ;
                case EXPONENT: ;
                case TRUE: ;
                case FALSE: ;
                case NULL: ;
                case STRING: ;
                case ARRAY: ;
                case OBJECT: ;
            }
        }
        return JEmpty.empty;
    }
}
