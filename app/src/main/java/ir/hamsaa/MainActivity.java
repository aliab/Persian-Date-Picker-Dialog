package ir.hamsaa;


import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

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

    /* To fix androidx 1.1.0 bug on localization and multi language app:
     *first set your locale
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        Locale locale = new Locale(LanguageManager.ENGLISH); // read/set your app locale here
        Context context = LanguageManager.ApContextWrapper.contextWrapper(newBase, locale);
        super.attachBaseContext(context);
    }

    /* To fix androidx 1.1.0 bug on localization ,multi language  & LTR/RTL  on some android versions from 21..25:
     *  applyOverrideConfiguration must be call because of androidx ver 1.1.0 bug that override resource configuration that applies to
     * the context by working on  night mode, it changes fonts size bigger & override locale on some android versions from 21..25
     * for fix must  override  configuration  to context on applyOverrideConfiguration again!
     * {@link  https://android-review.googlesource.com/c/platform/frameworks/support/+/1126021}
     * Full Bug explanation {@link https://android.googlesource.com/platform/frameworks/support/+/refs/heads/androidx-master-dev/appcompat/src/main/java/androidx/appcompat/app/AppCompatDelegateImpl.java}
     * AppCompat selectively uses applyOverrideConfiguration() for DayNight functionality.
     * Unfortunately the framework has a few bugs around Resources instances on SDKs 21-25,
     * resulting in the root Resources instance (i.e. Application) being modified when it
     * shouldn't be. We can work around it by always calling applyOverrideConfiguration()
     * where available.
     */
    @Override
    public void applyOverrideConfiguration(Configuration overrideConfiguration) {
        super.applyOverrideConfiguration(getResources().getConfiguration());
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
                .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
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
                .setPickerBackgroundDrawable(R.drawable.darkmode_bg)
                .setTitleType(PersianDatePickerDialog.DAY_MONTH_YEAR)
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
