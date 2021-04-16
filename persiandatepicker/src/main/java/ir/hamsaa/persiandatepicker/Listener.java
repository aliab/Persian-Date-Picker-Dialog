package ir.hamsaa.persiandatepicker;

import ir.hamsaa.persiandatepicker.util.PersianDate;

/**
 * Created by aliabdolahi on 1/23/17.
 */

public interface Listener {
    void onDateSelected(PersianDate PersianDate);

    void onDismissed();
}
