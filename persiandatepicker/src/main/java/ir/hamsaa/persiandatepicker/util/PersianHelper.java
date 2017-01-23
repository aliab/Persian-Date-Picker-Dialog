package ir.hamsaa.persiandatepicker.util;

/**
 * Created by sajjad on 01/18/2016.
 */
public class PersianHelper {
    private static char[] persianNumbers = new char[]{'۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹'};
    private static char[] englishNumbers = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static String toPersianNumber(String text) {
        if (text.isEmpty())
            return "";
        String out = "";
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            if ('0' <= c && c <= '9') {
                int number = Integer.parseInt(String.valueOf(c));
                out += persianNumbers[number];
            } else if (c == '٫') {
                out += '،';
            } else {
                out += c;
            }

        }
        return out;
    }

    public static String toEnglishNumber(String text) {
        if (text.isEmpty())
            return "";
        String out = "";
        int length = text.length();

        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);

            int charPos;
            if ((charPos = hasCharachter(c)) != -1) {
                out += englishNumbers[charPos];
            } else if (c == '،') {
                out += '٫';
            } else {
                out += c;
            }

        }

        return out;
    }

    private static int hasCharachter(char c) {
        for (int i = 0; i < persianNumbers.length; i++) {
            if (c == persianNumbers[i]) {
                return i;
            }
        }
        return -1;
    }
}