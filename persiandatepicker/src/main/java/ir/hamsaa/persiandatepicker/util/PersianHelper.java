package ir.hamsaa.persiandatepicker.util;

public class PersianHelper {
    private static final char[] persianNumbers = new char[]{'۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹'};
    private static final char[] englishNumbers = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static String toPersianNumber(String text) {
        if (text.isEmpty())
            return "";
        StringBuilder out = new StringBuilder();
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            if ('0' <= c && c <= '9') {
                int number = Integer.parseInt(String.valueOf(c));
                out.append(persianNumbers[number]);
            } else if (c == '٫') {
                out.append('،');
            } else {
                out.append(c);
            }

        }
        return out.toString();
    }

    public static String toEnglishNumber(String text) {
        if (text.isEmpty())
            return "";
        StringBuilder out = new StringBuilder();
        int length = text.length();

        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);

            int charPos;
            if ((charPos = hasCharachter(c)) != -1) {
                out.append(englishNumbers[charPos]);
            } else if (c == '،') {
                out.append('٫');
            } else {
                out.append(c);
            }

        }

        return out.toString();
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