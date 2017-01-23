package ir.hamsaa.persiandatepicker;

import ir.hamsaa.persiandatepicker.util.PersianCalendar;

/**
 * Created by aliabdolahi on 1/23/17.
 */

public interface Listener {
    void onDateSelected(PersianCalendar persianCalendar);

    void onDisimised();
}
