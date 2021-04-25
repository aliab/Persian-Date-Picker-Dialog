package ir.hamsaa.persiandatepicker.api

import java.util.*

public interface PersianPickerDate {

    fun setDate(timestamp: Long)

    fun setDate(date: Date)

    fun setDate(persianYear: Int, persianMonth: Int, persianDay: Int)

    fun getPersianYear(): Int

    fun getPersianMonth(): Int

    fun getPersianDay(): Int

    fun getGregorianYear(): Int

    fun getGregorianMonth(): Int

    fun getGregorianDay(): Int

    fun getDayOfWeek(): Int

    fun getPersianMonthName(): String

    fun getPersianDayOfWeekName(): String

    /**
     * @return String of Persian Date ex: دوشنبه ۱۳ خرداد ۱۳۷۰
     */
    fun getPersianLongDate(): String

    fun getGregorianDate(): Date

    fun getTimestamp(): Long
}