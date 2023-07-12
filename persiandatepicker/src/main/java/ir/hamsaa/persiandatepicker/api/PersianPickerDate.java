package ir.hamsaa.persiandatepicker.api;

import java.util.Date;

public interface PersianPickerDate {

    void setDate(Long timestamp);

    //The input function takes a unixtime
    void setDateByUnix(Long UnixTimestamp);

    void setDate(Date date);

    void setDate(int persianYear, int persianMonth, int persianDay);

    int getPersianYear();

    int getPersianMonth();

    int getPersianDay();

    int getGregorianYear();

    int getGregorianMonth();

    int getGregorianDay();

    int getDayOfWeek();

    String getPersianMonthName();

    String getPersianDayOfWeekName();

    /**
     * @return String of Persian Date ex: دوشنبه ۱۳ خرداد ۱۳۷۰
     */
    String getPersianLongDate();

    Date getGregorianDate();

    //The function returns a unixtime
    long getTimestamp();



    long getTimeUnix();

    boolean isLeapYear();

}
