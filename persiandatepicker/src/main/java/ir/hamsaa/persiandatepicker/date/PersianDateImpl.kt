package ir.hamsaa.persiandatepicker.date

import ir.hamsaa.persiandatepicker.api.PersianPickerDate
import saman.zamani.persiandate.PersianDate
import java.util.*

class PersianDateImpl : PersianPickerDate {

    private var persianDate: PersianDate

    init {
        persianDate = PersianDate()
    }

    override fun setDate(timestamp: Long) {
        persianDate = PersianDate(timestamp)
    }

    override fun setDate(date: Date) {
        persianDate = PersianDate(date)
    }

    override fun setDate(persianYear: Int, persianMonth: Int, persianDay: Int) {
        persianDate.shYear = persianYear;
        persianDate.shMonth = persianMonth;
        persianDate.shDay = persianDay;
    }

    override fun getPersianYear(): Int {
        return persianDate.shYear
    }

    override fun getPersianMonth(): Int {
        return persianDate.shMonth
    }

    override fun getPersianDay(): Int {
        return persianDate.shDay
    }

    override fun getGregorianYear(): Int {
        return persianDate.grgYear
    }

    override fun getGregorianMonth(): Int {
        return persianDate.grgMonth
    }

    override fun getGregorianDay(): Int {
        return persianDate.grgDay
    }

    override fun getDayOfWeek(): Int {
        return persianDate.dayOfWeek()
    }

    override fun getPersianMonthName(): String {
        return persianDate.monthName()
    }

    override fun getPersianDayOfWeekName(): String {
        return persianDate.dayName()
    }

    override fun getPersianLongDate(): String {
        return getPersianDayOfWeekName() + "  " + getPersianDay() + "  " + getPersianMonthName() + "  " + getPersianYear()
    }

    override fun getGregorianDate(): Date {
        return persianDate.toDate()
    }

    override fun getTimestamp(): Long {
        return persianDate.time
    }

}