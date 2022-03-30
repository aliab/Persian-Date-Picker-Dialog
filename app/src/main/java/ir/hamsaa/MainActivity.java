package ir.hamsaa;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.api.PersianPickerDate;
import ir.hamsaa.persiandatepicker.api.PersianPickerListener;
import ir.hamsaa.persiandatepicker.date.PersianDateFixedLeapYear;
import ir.hamsaa.persiandatepicker.date.PersianDateImpl;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import saman.zamani.persiandate.PersianDateFormat;


public class MainActivity extends AppCompatActivity {

    private PersianDatePickerDialog picker;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        PersianDateFixedLeapYear persianDate = new PersianDateFixedLeapYear();
        Log.d(TAG, "today: " + persianDate.getShDay());
        persianDate.addDay(1);
        Log.d(TAG, "tomorrow: " + persianDate.getShDay());


        // format `persianDate`
        Log.d(TAG, PersianDateFormat.format(persianDate, "Y/m/d"));

        // format string persian date
        try {
            Log.d(TAG, PersianDateFormat.format(new PersianDateFormat("yyyy/MM/dd").parse("1401/03/16"), "y-m-d"));
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }


    public void showCalendar(View v) {

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Shabnam-Light-FD.ttf");

        picker = new PersianDatePickerDialog(this)
                .setPositiveButtonString("باشه")
                .setNegativeButton("بیخیال")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMinYear(1300)
                .setAllButtonsTextSize(12)
                .setMaxYear(1500)
                .setInitDate(1370, 3, 13)
                .setActionTextColor(Color.GRAY)
                .setTypeFace(typeface)
//                .setShowDayPicker(false)
                .setTitleType(PersianDatePickerDialog.DAY_MONTH_YEAR)
                .setShowInBottomSheet(true)
                .setListener(new PersianPickerListener() {
                    @Override
                    public void onDateSelected(PersianPickerDate persianPickerDate) {
                        Log.d(TAG, "onDateSelected: " + persianPickerDate.getTimestamp());//675930448000
                        Log.d(TAG, "onDateSelected: " + persianPickerDate.getGregorianDate());//Mon Jun 03 10:57:28 GMT+04:30 1991
                        Log.d(TAG, "onDateSelected: " + persianPickerDate.getPersianLongDate());// دوشنبه  13  خرداد  1370
                        Log.d(TAG, "onDateSelected: " + persianPickerDate.getPersianMonthName());//خرداد
                        Log.d(TAG, "onDateSelected: " + PersianDateImpl.isLeapYear(persianPickerDate.getPersianYear()));//true
                        Toast.makeText(MainActivity.this, persianPickerDate.getPersianYear() + "/" + persianPickerDate.getPersianMonth() + "/" + persianPickerDate.getPersianDay(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDismissed() {
                        Toast.makeText(MainActivity.this, "Dismissed", Toast.LENGTH_SHORT).show();
                    }
                });

        picker.show();
    }

    public void showCalendarInDarkMode(View v) {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Shabnam-Light-FD.ttf");

        PersianCalendar initDate = new PersianCalendar();
        initDate.setPersianDate(1370, 3, 13);

        picker = new PersianDatePickerDialog(this)
                .setPositiveButtonString("باشه")
                .setNegativeButton("بیخیال")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMinYear(1300)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setInitDate(initDate)
                .setTypeFace(typeface)
                .setBackgroundColor(Color.BLACK)
                .setTitleColor(Color.WHITE)
                .setActionTextColor(Color.RED)
                .setPickerBackgroundDrawable(R.drawable.darkmode_bg)
                .setTitleType(PersianDatePickerDialog.MONTH_YEAR)
                .setShowDayPicker(false)

                .setCancelable(false)
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {
                        Toast.makeText(MainActivity.this, persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDismissed() {

                    }
                });
        picker.show();
    }

}
