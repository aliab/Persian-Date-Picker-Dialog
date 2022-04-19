package ir.hamsaa.persiandatepicker.util;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class PersianDateHelper {

    public boolean isValidDate(String date) {
        try {
            return !parseAndFormatDate(date).isEmpty();
        } catch (Throwable ignore) {

        }
        return false;
    }

    public String parseAndFormatDate(String date) {
        try {
            return PersianDateFormat.format(new PersianDateFormat("yyyy/MM/dd").parse(date), "Y/m/d");
        } catch (Throwable ignore) {

        }
        return "";
    }

    public String formatDate(PersianDate date) {
        try {
            return PersianDateFormat.format(date, "Y/m/d");
        } catch (Throwable ignore) {

        }
        return "";
    }

    public String formatTime(PersianDate time) {
        try {
            return PersianDateFormat.format(time, "H:i:s");
        } catch (Throwable ignore) {

        }
        return "";
    }

}
