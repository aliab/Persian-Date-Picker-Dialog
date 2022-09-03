package ir.hamsaa;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.api.PersianPickerDate;
import ir.hamsaa.persiandatepicker.api.PersianPickerListener;
import ir.hamsaa.persiandatepicker.date.PersianDateImpl;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private PersianDatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void showCalendar(View v) {

//        Typeface typeface = Typeface.createFromAsset(getAssets(), "Shabnam-Light-FD.ttf");

        picker = new PersianDatePickerDialog(this)
                .setPositiveButtonString("انتخاب")
                .setNegativeButton("بیخیال")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMinYear(1300)
//                .setAllButtonsTextSize(12)
                .setMaxYear(1500)
                .setInitDate(1370, 3, 13)
//                .setActionTextColor(Color.GRAY)
//                .setTypeFace(typeface)
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


}
