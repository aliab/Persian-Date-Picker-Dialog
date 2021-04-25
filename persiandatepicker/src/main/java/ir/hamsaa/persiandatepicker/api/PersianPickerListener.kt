package ir.hamsaa.persiandatepicker.api

interface PersianPickerListener {

    fun onDateSelected(persianPickerDate: PersianPickerDate)

    fun onDismissed()
}