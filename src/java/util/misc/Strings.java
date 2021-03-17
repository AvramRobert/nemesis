package util.misc;

public class Strings {

    public static String escape (final String s) {
        return String.format("\"%s\"", s);
    }

    public static String unescape (final String s) { return s.replace("\"", ""); }

}
