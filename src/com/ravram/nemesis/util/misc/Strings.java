package com.ravram.nemesis.util.misc;

import java.util.regex.Matcher;

public class Strings {

    public static String escape (final String string) {
        final String escaped =
                Matcher.quoteReplacement(string)
                .replace("\"", "\\\"")
                .replace("\f", "\\f")
                .replace("\r", "\\r")
                .replace("\b", "\\b")
                .replace("\t", "\\t")
                .replace("\n", "\\n");

        return String.format("\"%s\"", escaped);
    }

}
