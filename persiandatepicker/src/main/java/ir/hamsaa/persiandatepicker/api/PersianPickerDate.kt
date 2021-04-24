package ir.hamsaa.persiandatepicker.api

import java.util.*

interface PersianPickerDate {

    fun setDate(timestamp: Long)

    fun setDate(date: Date)

    fun setDate(persianYear: Int, persianMonth: Int, persianDay: Int)

    fun getPersianYear(): Int

    fun getPersianMonth(): Int

    fun getPersianDay(): Int

    fun getGregorianYear(): Int

    fun getGregorianMonth(): Int

    fun getGregorianDay(): Int

    fun getPersianWeekDay(): Int

    fun getGregorianWeekDay(): Int

    fun getGregorianDate(): Date
}