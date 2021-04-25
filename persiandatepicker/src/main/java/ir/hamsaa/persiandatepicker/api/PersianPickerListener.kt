package ir.hamsaa.persiandatepicker.api

public interface PersianPickerListener {

    fun onDateSelected(persianPickerDate: PersianPickerDate)

    fun onDismissed()
}