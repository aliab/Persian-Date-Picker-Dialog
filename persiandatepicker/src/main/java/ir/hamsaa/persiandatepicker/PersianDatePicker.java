package ir.hamsaa.persiandatepicker;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shawnlin.numberpicker.NumberPicker;

import java.util.Date;

import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import ir.hamsaa.persiandatepicker.util.PersianCalendarConstants;
import ir.hamsaa.persiandatepicker.util.PersianCalendarUtils;
import ir.hamsaa.persiandatepicker.util.PersianHelper;


class PersianDatePicker extends LinearLayout {

  private final PersianCalendar pCalendar;
  private int selectedMonth;
  private int selectedYear;
  private int selectedDay;
  private boolean displayMonthNames;
  private OnDateChangedListener mListener;
  private NumberPicker yearNumberPicker;
  private NumberPicker monthNumberPicker;
  private NumberPicker dayNumberPicker;

  private int minYear;
  private int maxYear;

  private boolean displayDescription;
  private TextView descriptionTextView;
  private Typeface typeFace;
  private int dividerColor;
  private int yearRange;
  private @ColorInt
  Integer dateTextColor;
  private @ColorInt
  Integer selectedDateTextColor;
  private Float textSize;

  public PersianDatePicker(Context context) {
    this(context, null, -1);
  }

  public PersianDatePicker(Context context, AttributeSet attrs) {
    this(context, attrs, -1);
  }

  private void updateVariablesFromXml(Context context, AttributeSet attrs) {

    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PersianDatePicker, 0, 0);
    yearRange = a.getInteger(R.styleable.PersianDatePicker_yearRange, 10);
    /*
     * Initializing yearNumberPicker min and max values If minYear and
     * maxYear attributes are not set, use (current year - 10) as min and
     * (current year + 10) as max.
     */
    minYear = a.getInt(R.styleable.PersianDatePicker_minYear, pCalendar.getPersianYear() - yearRange);
    maxYear = a.getInt(R.styleable.PersianDatePicker_maxYear, pCalendar.getPersianYear() + yearRange);
    displayMonthNames = a.getBoolean(R.styleable.PersianDatePicker_displayMonthNames, false);
    /*
     * displayDescription
     */
    displayDescription = a.getBoolean(R.styleable.PersianDatePicker_displayDescription, false);
    selectedDay = a.getInteger(R.styleable.PersianDatePicker_selectedDay, pCalendar.getPersianDay());
    selectedYear = a.getInt(R.styleable.PersianDatePicker_selectedYear, pCalendar.getPersianYear());
    selectedMonth = a.getInteger(R.styleable.PersianDatePicker_selectedMonth, pCalendar.getPersianMonth());

    // if you pass selected year before min year, then we need to push min year to before that
    if (minYear > selectedYear) {
      minYear = selectedYear - yearRange;
    }

    if (maxYear < selectedYear) {
      maxYear = selectedYear + yearRange;
    }

    a.recycle();
  }

  public PersianDatePicker(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);

    // get layout inflater
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    // inflate views
    View view = inflater.inflate(R.layout.sl_persian_date_picker, this);

    // get views
    yearNumberPicker = view.findViewById(R.id.yearNumberPicker);
    monthNumberPicker = view.findViewById(R.id.monthNumberPicker);
    dayNumberPicker = view.findViewById(R.id.dayNumberPicker);
    descriptionTextView = view.findViewById(R.id.descriptionTextView);


    yearNumberPicker.setFormatter(new NumberPicker.Formatter() {
      @Override
      public String format(int i) {
        return PersianHelper.toPersianNumber(i + "");
      }
    });

    monthNumberPicker.setFormatter(new NumberPicker.Formatter() {
      @Override
      public String format(int i) {
        return PersianHelper.toPersianNumber(i + "");
      }
    });

    dayNumberPicker.setFormatter(new NumberPicker.Formatter() {
      @Override
      public String format(int i) {
        return PersianHelper.toPersianNumber(i + "");
      }
    });

    // init calendar
    pCalendar = new PersianCalendar();

    // update variables from xml
    updateVariablesFromXml(context, attrs);

    // update view
    updateViewData();
  }

  public void setMaxYear(int maxYear) {
    this.maxYear = maxYear;
    updateViewData();
  }

  public void setMinYear(int minYear) {
    this.minYear = minYear;
    updateViewData();
  }

  public void setTypeFace(Typeface typeFace) {
    this.typeFace = typeFace;
    updateViewData();
  }

  public void setDividerColor(@ColorInt int color) {
    this.dividerColor = color;
    updateViewData();
  }

  private void setDividerColor(NumberPicker picker, int color) {

    java.lang.reflect.Field[] pickerFields = NumberPicker.class.getDeclaredFields();
    for (java.lang.reflect.Field pf : pickerFields) {
      if (pf.getName().equals("mSelectionDivider")) {
        pf.setAccessible(true);
        try {
          ColorDrawable colorDrawable = new ColorDrawable(color);
          pf.set(picker, colorDrawable);
        } catch (IllegalArgumentException e) {
          e.printStackTrace();
        } catch (Resources.NotFoundException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
        break;
      }
    }
  }


  private void updateViewData() {

    if (typeFace != null) {
      yearNumberPicker.setTypeface(typeFace);
      monthNumberPicker.setTypeface(typeFace);
      dayNumberPicker.setTypeface(typeFace);
    }
    if (dateTextColor != null) {
      yearNumberPicker.setTextColor(dateTextColor);
      monthNumberPicker.setTextColor(dateTextColor);
      dayNumberPicker.setTextColor(dateTextColor);
    }
    if (selectedDateTextColor != null) {
      yearNumberPicker.setSelectedTextColor(selectedDateTextColor);
      monthNumberPicker.setSelectedTextColor(selectedDateTextColor);
      dayNumberPicker.setSelectedTextColor(selectedDateTextColor);
    }
    if (textSize != null) {
      yearNumberPicker.setTextSize(textSize);
      monthNumberPicker.setTextSize(textSize);
      dayNumberPicker.setTextSize(textSize);
      yearNumberPicker.setSelectedTextSize(textSize);
      monthNumberPicker.setSelectedTextSize(textSize);
      dayNumberPicker.setSelectedTextSize(textSize);
    }

    if (dividerColor > 0) {
      setDividerColor(yearNumberPicker, dividerColor);
      setDividerColor(monthNumberPicker, dividerColor);
      setDividerColor(dayNumberPicker, dividerColor);
    }

    yearNumberPicker.setMinValue(minYear);
    yearNumberPicker.setMaxValue(maxYear);


    if (selectedYear > maxYear) {
      selectedYear = maxYear;
    }
    if (selectedYear < minYear) {
      selectedYear = minYear;
    }

    yearNumberPicker.setValue(selectedYear);
    yearNumberPicker.setOnValueChangedListener(dateChangeListener);

    /*
     * initialing monthNumberPicker
     */

    monthNumberPicker.setMinValue(1);
    monthNumberPicker.setMaxValue(12);
    if (displayMonthNames) {
      monthNumberPicker.setDisplayedValues(PersianCalendarConstants.persianMonthNames);
    }

    if (selectedMonth < 1 || selectedMonth > 12) {
      throw new IllegalArgumentException(String.format("Selected month (%d) must be between 1 and 12", selectedMonth));
    }
    monthNumberPicker.setValue(selectedMonth);
    monthNumberPicker.setOnValueChangedListener(dateChangeListener);

    /*
     * initializing dayNumberPicker
     */
    dayNumberPicker.setMinValue(1);
    dayNumberPicker.setMaxValue(31);
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


    if (displayDescription) {
      descriptionTextView.setVisibility(View.VISIBLE);
      descriptionTextView.setText(getDisplayPersianDate().getPersianLongDate());
    }
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

  public void setDateTextColor(int dateTextColor) {
    this.dateTextColor = dateTextColor;
  }

  public void setSelectedDateTextColor(int selectedDateTextColor) {
    this.selectedDateTextColor = selectedDateTextColor;
  }

  public void setTextSize(float textSize) {
    this.textSize = textSize;
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


    selectedYear = year;
    selectedMonth = month;
    selectedDay = day;

    // if you pass selected year before min year, then we need to push min year to before that
    if (minYear > selectedYear) {
      minYear = selectedYear - yearRange;
      yearNumberPicker.setMinValue(minYear);
    }

    // if you pass selected year after max year, then we need to push max year to after that
    if (maxYear < selectedYear) {
      maxYear = selectedYear + yearRange;
      yearNumberPicker.setMaxValue(maxYear);
    }

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
