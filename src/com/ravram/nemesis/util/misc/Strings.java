package com.ravram.nemesis.util.misc;

public class Strings {

    public static String escape (final String s) {
        return String.format("\"%s\"", s);
    }

}
