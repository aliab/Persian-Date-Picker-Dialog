package ir.hamsaa.persiandatepicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Date;

import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import ir.hamsaa.persiandatepicker.util.PersianCalendarConstants;
import ir.hamsaa.persiandatepicker.util.PersianCalendarUtils;


class PersianDatePicker extends LinearLayout {

    private OnDateChangedListener mListener;
    private NumberPicker yearNumberPicker;
    private NumberPicker monthNumberPicker;
    private NumberPicker dayNumberPicker;

    private int minYear;
    private int maxYear;
    private int yearRange;

    private boolean displayDescription;
    private TextView descriptionTextView;

    public PersianDatePicker(Context context) {
        this(context, null, -1);
    }

    public PersianDatePicker(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public PersianDatePicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.sl_persian_date_picker, this);
        yearNumberPicker = (NumberPicker) view.findViewById(R.id.yearNumberPicker);
        monthNumberPicker = (NumberPicker) view.findViewById(R.id.monthNumberPicker);
        dayNumberPicker = (NumberPicker) view.findViewById(R.id.dayNumberPicker);
        descriptionTextView = (TextView) view.findViewById(R.id.descriptionTextView);

        PersianCalendar pCalendar = new PersianCalendar();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PersianDatePicker, 0, 0);

        yearRange = a.getInteger(R.styleable.PersianDatePicker_yearRange, 10);

		/*
         * Initializing yearNumberPicker min and max values If minYear and
		 * maxYear attributes are not set, use (current year - 10) as min and
		 * (current year + 10) as max.
		 */
        minYear = a.getInt(R.styleable.PersianDatePicker_minYear, pCalendar.getPersianYear() - yearRange);
        maxYear = a.getInt(R.styleable.PersianDatePicker_maxYear, pCalendar.getPersianYear() + yearRange);
        yearNumberPicker.setMinValue(minYear);
        yearNumberPicker.setMaxValue(maxYear);

        int selectedYear = a.getInt(R.styleable.PersianDatePicker_selectedYear, pCalendar.getPersianYear());
        if (selectedYear > maxYear || selectedYear < minYear) {
            throw new IllegalArgumentException(String.format("Selected year (%d) must be between minYear(%d) and maxYear(%d)", selectedYear, minYear, maxYear));
        }
        yearNumberPicker.setValue(selectedYear);
        yearNumberPicker.setOnValueChangedListener(dateChangeListener);

		/*
         * initializng monthNumberPicker
		 */
        boolean displayMonthNames = a.getBoolean(R.styleable.PersianDatePicker_displayMonthNames, false);
        monthNumberPicker.setMinValue(1);
        monthNumberPicker.setMaxValue(12);
        if (displayMonthNames) {
            monthNumberPicker.setDisplayedValues(PersianCalendarConstants.persianMonthNames);
        }
        int selectedMonth = a.getInteger(R.styleable.PersianDatePicker_selectedMonth, pCalendar.getPersianMonth());
        if (selectedMonth < 1 || selectedMonth > 12) {
            throw new IllegalArgumentException(String.format("Selected month (%d) must be between 1 and 12", selectedMonth));
        }
        monthNumberPicker.setValue(selectedMonth);
        monthNumberPicker.setOnValueChangedListener(dateChangeListener);

		/*
		 * initializiing dayNumberPicker
		 */
        dayNumberPicker.setMinValue(1);
        dayNumberPicker.setMaxValue(31);
        int selectedDay = a.getInteger(R.styleable.PersianDatePicker_selectedDay, pCalendar.getPersianDay());
        if (selectedDay > 31 || selectedDay < 1) {
            throw new IllegalArgumentException(String.format("Selected day (%d) must be between 1 and 31", selectedDay));
        }
        if (selectedMonth > 6 && selectedMonth < 12 && selectedDay == 31) {
            selectedDay = 30;
        } else {
            boolean isLeapYear = PersianCalendarUtils.isPersianLeapYear(selectedYear);
            if (isLeapYear && selectedDay == 31) {
                selectedDay = 30;
            } else if (selectedDay > 29) {
                selectedDay = 29;
            }
        }
        dayNumberPicker.setValue(selectedDay);
        dayNumberPicker.setOnValueChangedListener(dateChangeListener);
		
		/*
		 * displayDescription
		 */
        displayDescription = a.getBoolean(R.styleable.PersianDatePicker_displayDescription, false);
        if (displayDescription) {
            descriptionTextView.setVisibility(View.VISIBLE);
            descriptionTextView.setText(getDisplayPersianDate().getPersianLongDate());
        }

        a.recycle();
    }

    NumberPicker.OnValueChangeListener dateChangeListener = new NumberPicker.OnValueChangeListener() {

        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            int year = yearNumberPicker.getValue();
            boolean isLeapYear = PersianCalendarUtils.isPersianLeapYear(year);

            int month = monthNumberPicker.getValue();
            int day = dayNumberPicker.getValue();

            if (month < 7) {
                dayNumberPicker.setMinValue(1);
                dayNumberPicker.setMaxValue(31);
            } else if (month > 6 && month < 12) {
                if (day == 31) {
                    dayNumberPicker.setValue(30);
                }
                dayNumberPicker.setMinValue(1);
                dayNumberPicker.setMaxValue(30);
            } else if (month == 12) {
                if (isLeapYear) {
                    if (day == 31) {
                        dayNumberPicker.setValue(30);
                    }
                    dayNumberPicker.setMinValue(1);
                    dayNumberPicker.setMaxValue(30);
                } else {
                    if (day > 29) {
                        dayNumberPicker.setValue(29);
                    }
                    dayNumberPicker.setMinValue(1);
                    dayNumberPicker.setMaxValue(29);
                }
            }

            // Set description
            if (displayDescription) {
                descriptionTextView.setText(getDisplayPersianDate().getPersianLongDate());
            }

            if (mListener != null) {
                mListener.onDateChanged(yearNumberPicker.getValue(), monthNumberPicker.getValue(),
                        dayNumberPicker.getValue());
            }

        }

    };

    public void setOnDateChangedListener(OnDateChangedListener onDateChangedListener) {
        mListener = onDateChangedListener;
    }

    /**
     * The callback used to indicate the user changed the date.
     * A class that wants to be notified when the date of PersianDatePicker
     * changes should implement this interface and register itself as the
     * listener of date change events using the PersianDataPicker's
     * setOnDateChangedListener method.
     */
    public interface OnDateChangedListener {

        /**
         * Called upon a date change.
         *
         * @param newYear  The year that was set.
         * @param newMonth The month that was set (1-12)
         * @param newDay   The day of the month that was set.
         */
        void onDateChanged(int newYear, int newMonth, int newDay);
    }

    public Date getDisplayDate() {
        PersianCalendar displayPersianDate = new PersianCalendar();
        displayPersianDate.setPersianDate(yearNumberPicker.getValue(), monthNumberPicker.getValue(), dayNumberPicker.getValue());
        return displayPersianDate.getTime();
    }

    public void setDisplayDate(Date displayDate) {
        setDisplayPersianDate(new PersianCalendar(displayDate.getTime()));
    }

    public PersianCalendar getDisplayPersianDate() {
        PersianCalendar displayPersianDate = new PersianCalendar();
        displayPersianDate.setPersianDate(yearNumberPicker.getValue(), monthNumberPicker.getValue(), dayNumberPicker.getValue());
        return displayPersianDate;
    }

    public void setDisplayPersianDate(PersianCalendar displayPersianDate) {
        int year = displayPersianDate.getPersianYear();
        int month = displayPersianDate.getPersianMonth();
        int day = displayPersianDate.getPersianDay();
        if (month > 6 && month < 12 && day == 31) {
            day = 30;
        } else {
            boolean isLeapYear = PersianCalendarUtils.isPersianLeapYear(year);
            if (isLeapYear && day == 31) {
                day = 30;
            } else if (day > 29) {
                day = 29;
            }
        }
        dayNumberPicker.setValue(day);

        minYear = year - yearRange;
        maxYear = year + yearRange;
        yearNumberPicker.setMinValue(minYear);
        yearNumberPicker.setMaxValue(maxYear);

        yearNumberPicker.setValue(year);
        monthNumberPicker.setValue(month);
        dayNumberPicker.setValue(day);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        // begin boilerplate code that allows parent classes to save state
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        // end

        ss.datetime = this.getDisplayDate().getTime();
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        // begin boilerplate code so parent classes can restore state
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        // end

        setDisplayDate(new Date(ss.datetime));
    }

    static class SavedState extends BaseSavedState {
        long datetime;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.datetime = in.readLong();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeLong(this.datetime);
        }

        // required field that makes Parcelables from a Parcel
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

}
