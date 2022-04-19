package ir.hamsaa.persiandatepicker.util;

import java.text.ParseException;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class PersianDateHelper {

    static public boolean isValidDate(String date) {
        try {
            return !parseAndFormatDate(date).isEmpty();
        } catch (Throwable ignore) {

        }
        return false;
    }

    static public PersianDate parseDate(String date) {
        return parseDate(date, "yyyy/MM/dd");
    }

    static public PersianDate parseDate(String date, String pattern) {
        try {
            return new PersianDateFormat(pattern).parse(date);
        } catch (ParseException ignore) {
        }
        return null;
    }

    static public String parseAndFormatDate(String date) {
        try {
            return PersianDateFormat.format(parseDate(date), "Y/m/d");
        } catch (Throwable ignore) {

        }
        return "";
    }

    static public String formatDate(PersianDate date) {
        try {
            return PersianDateFormat.format(date, "Y/m/d");
        } catch (Throwable ignore) {

        }
        return "";
    }

    static public String formatTime(PersianDate time) {
        try {
            return PersianDateFormat.format(time, "H:i:s");
        } catch (Throwable ignore) {

        }
        return "";
    }

}
