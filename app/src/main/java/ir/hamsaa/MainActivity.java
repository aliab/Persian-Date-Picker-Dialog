package ir.hamsaa;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;


public class MainActivity extends AppCompatActivity {

    private PersianDatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void showCalendar(View v) {
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
                .setActionTextColor(Color.GRAY)
                .setTypeFace(typeface)
                .setTextColor(getResources().getColor(R.color.colorPrimary))
                .setDividerColor(getResources().getColor(R.color.colorAccent))
                .setDividerHeight(10)
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
                .setActionTextColor(Color.GRAY)
                .setTypeFace(typeface)
                .setBackgroundColor(Color.BLACK)
                .setTitleColor(Color.WHITE)
                .setActionTextColor(Color.WHITE)
                //.setPickerBackgroundDrawable(R.drawable.darkmode_bg)
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
