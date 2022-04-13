package ir.hamsaa.persiandatepicker.date;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import saman.zamani.persiandate.PersianDate;

public class PersianDateFixedLeapYear extends PersianDate {

    public PersianDateFixedLeapYear() {
    }
    public PersianDateFixedLeapYear(Long timeInMilliSecond) {
        super(timeInMilliSecond);
    }

    public PersianDateFixedLeapYear(Date date) {
        super(date);
    }

    @Override
    public boolean isLeap(int year) {
        int[] matches = { 1, 5, 9, 13, 17, 22, 26, 30 };
        int modulus = year % 33;
        boolean isLeapYear = false;
        for (int match : matches){
            if (match == modulus) {
                isLeapYear = true;
                break;
            }
        }
        return isLeapYear;
    }

}
