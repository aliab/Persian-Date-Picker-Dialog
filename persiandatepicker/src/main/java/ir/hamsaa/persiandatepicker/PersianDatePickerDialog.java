package ir.hamsaa.persiandatepicker;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import ir.hamsaa.persiandatepicker.util.PersianHelper;

/**
 * Created by aliabdolahi on 1/23/17.
 */

public class PersianDatePickerDialog {

    public static final int THIS_YEAR = -1;

    private Context context;
    private String positiveButtonString = "تایید";
    private String negativeButtonString = "انصراف";
    private Listener listener;
    private int maxYear = 0;
    private int minYear = 0;
    private PersianCalendar initDate;
    private PersianCalendar pCalendar;
    public static Typeface typeFace;
    private String todayButtonString = "امروز";
    private boolean todayButtonVisibility = false;
    private int actionColor = Color.GRAY;
    private boolean cancelable = true;

    public PersianDatePickerDialog(Context context) {
        this.context = context;
    }

    public PersianDatePickerDialog setListener(Listener listener) {
        this.listener = listener;
        return this;
    }


    public PersianDatePickerDialog setMaxYear(int maxYear) {
        this.maxYear = maxYear;
        return this;
    }

    public PersianDatePickerDialog setTypeFace(Typeface typeFace) {
        this.typeFace = typeFace;
        return this;
    }

    public PersianDatePickerDialog setMinYear(int minYear) {
        this.minYear = minYear;
        return this;
    }

    public PersianDatePickerDialog setInitDate(PersianCalendar initDate) {
        this.initDate = initDate;
        return this;
    }

    public PersianDatePickerDialog setPositiveButtonString(String positiveButtonString) {
        this.positiveButtonString = positiveButtonString;
        return this;
    }

    public PersianDatePickerDialog setPositiveButtonResource(@StringRes int positiveButton) {
        this.positiveButtonString = context.getString(positiveButton);
        return this;
    }

    public PersianDatePickerDialog setTodayButtonVisible(boolean todayButtonVisiblity) {
        this.todayButtonVisibility = todayButtonVisiblity;
        return this;
    }

    public PersianDatePickerDialog setTodayButton(String todayButton) {
        this.todayButtonString = todayButton;
        return this;
    }

    public PersianDatePickerDialog setTodayButtonResource(@StringRes int todayButton) {
        this.todayButtonString = context.getString(todayButton);
        return this;
    }


    public PersianDatePickerDialog setNegativeButton(String negativeButton) {
        this.negativeButtonString = negativeButton;
        return this;
    }

    public PersianDatePickerDialog setNegativeButtonResource(@StringRes int negativeButton) {
        this.negativeButtonString = context.getString(negativeButton);
        return this;
    }

    public PersianDatePickerDialog setActionTextColor(@ColorInt int colorInt) {
        this.actionColor = colorInt;
        return this;
    }

    public PersianDatePickerDialog setActionTextColorResource(@ColorRes int colorInt) {
        this.actionColor = ContextCompat.getColor(context, colorInt);
        return this;
    }

    public PersianDatePickerDialog setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }


    public void show() {

        pCalendar = new PersianCalendar();

        View v = View.inflate(context, R.layout.dialog_picker, null);
        final PersianDatePicker datePicker = v.findViewById(R.id.datePicker);
        final TextView dateText = v.findViewById(R.id.dateText);
        final AppCompatButton positiveButton = v.findViewById(R.id.positive_button);
        final AppCompatButton negativeButton = v.findViewById(R.id.negative_button);
        final AppCompatButton todayButton = v.findViewById(R.id.today_button);


        if (maxYear > 0) {
            datePicker.setMaxYear(maxYear);
        } else if (maxYear == THIS_YEAR) {
            datePicker.setMaxYear(pCalendar.getPersianYear());
        }

        if (minYear > 0) {
            datePicker.setMinYear(minYear);
        }

        if (initDate != null) {
            datePicker.setDisplayPersianDate(initDate);
        }

        if (typeFace != null) {
            dateText.setTypeface(typeFace);
            positiveButton.setTypeface(typeFace);
            negativeButton.setTypeface(typeFace);
            todayButton.setTypeface(typeFace);
            datePicker.setTypeFace(typeFace);
        }

        positiveButton.setTextColor(actionColor);
        negativeButton.setTextColor(actionColor);
        todayButton.setTextColor(actionColor);

        positiveButton.setText(positiveButtonString);
        negativeButton.setText(negativeButtonString);
        todayButton.setText(todayButtonString);

        if (todayButtonVisibility) {
            todayButton.setVisibility(View.VISIBLE);
        }

        pCalendar = datePicker.getDisplayPersianDate();
        updateView(dateText);

        datePicker.setOnDateChangedListener(new PersianDatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(int newYear, int newMonth, int newDay) {
                pCalendar.setPersianDate(newYear, newMonth, newDay);
                updateView(dateText);
            }
        });

        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(v)
                .setCancelable(cancelable)
                .create();

        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onDismissed();
                }
                dialog.dismiss();
            }
        });

        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onDateSelected(datePicker.getDisplayPersianDate());
                }
                dialog.dismiss();
            }
        });

        todayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datePicker.setDisplayDate(new Date());

                if (maxYear > 0) {
                    datePicker.setMaxYear(maxYear);
                }

                if (minYear > 0) {
                    datePicker.setMinYear(minYear);
                }

                pCalendar = datePicker.getDisplayPersianDate();
                updateView(dateText);
            }
        });

        dialog.show();
    }

    private void updateView(TextView dateText) {
        String date =
                pCalendar.getPersianWeekDayName() + " " +
                        pCalendar.getPersianDay() + " " +
                        pCalendar.getPersianMonthName() + " " +
                        pCalendar.getPersianYear();
        dateText.setText(PersianHelper.toPersianNumber(date));
    }

}
