package edu.challenge.beat.util;

public final class StringUtil {

    private StringUtil(){}

    public static boolean checkTrimEmpty( final String str) {
        for(int index = 0; index < str.length(); index++) {
            if(!Character.isWhitespace(str.charAt(index))) {
                return false;
            }
        }
        return true;
    }
}
