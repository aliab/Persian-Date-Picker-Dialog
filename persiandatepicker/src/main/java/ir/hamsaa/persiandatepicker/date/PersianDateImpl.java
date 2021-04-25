package ir.hamsaa.persiandatepicker.date;

import java.util.Date;

import ir.hamsaa.persiandatepicker.api.PersianPickerDate;
import saman.zamani.persiandate.PersianDate;

public class PersianDateImpl implements PersianPickerDate {

    private PersianDate persianDate;

    public PersianDateImpl() {
        persianDate = new PersianDate();
    }

    @Override
    public void setDate(Long timestamp) {
        persianDate = new PersianDate(timestamp);
    }

    @Override
    public void setDate(Date date) {
        persianDate = new PersianDate(date);
    }

    @Override
    public void setDate(int persianYear, int persianMonth, int persianDay) {
        persianDate.setShYear(persianYear);
        persianDate.setShMonth(persianMonth);
        persianDate.setShDay(persianDay);
    }

    @Override
    public int getPersianYear() {
        return persianDate.getShYear();
    }

    @Override
    public int getPersianMonth() {
        return persianDate.getShMonth();
    }

    @Override
    public int getPersianDay() {
        return persianDate.getShDay();
    }

    @Override
    public int getGregorianYear() {
        return persianDate.getGrgYear();
    }

    @Override
    public int getGregorianMonth() {
        return persianDate.getGrgMonth();
    }

    @Override
    public int getGregorianDay() {
        return persianDate.getGrgDay();
    }

    @Override
    public int getDayOfWeek() {
        return persianDate.dayOfWeek();
    }

    @Override
    public String getPersianMonthName() {
        return persianDate.monthName();
    }

    @Override
    public String getPersianDayOfWeekName() {
        return persianDate.dayName();
    }

    @Override
    public String getPersianLongDate() {
        return getPersianDayOfWeekName() + "  " + getPersianDay() + "  " + getPersianMonthName() + "  " + getPersianYear();
    }

    @Override
    public Date getGregorianDate() {
        return persianDate.toDate();
    }

    @Override
    public long getTimestamp() {
        return persianDate.getTime();
    }
}
