# Persian Date Picker Dialog
[![](https://jitpack.io/v/aliab/Persian-Date-Picker-Dialog.svg)](https://jitpack.io/#aliab/Persian-Date-Picker-Dialog)

![Hero Image](https://raw.githubusercontent.com/aliab/Persian-Date-Picker-Dialog/master/screenshot/heroimage.jpg)


Persian Date Picker Dialog

This library provides you a beautiful Persian Date picker dialog with easy API.

## Usages

Use this dependency in your build.gradle file to reference this library in your project

Step 1. Add the JitPack repository to your build file. Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io" }
        }
    }
```

Step 2. Add the dependency
```groovy
dependencies {
    implementation 'com.github.aliab:Persian-Date-Picker-Dialog:1.6.1'
}
```


Then in your Java Code, you use it like below.

```java
 picker = new PersianDatePickerDialog(this)
                .setPositiveButtonString("باشه")
                .setNegativeButton("بیخیال")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMinYear(1300)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setInitDate(1370, 3, 13)
                .setActionTextColor(Color.GRAY)
                .setTypeFace(typeface)
                .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
                .setShowInBottomSheet(true)
                .setListener(new PersianPickerListener() {
                    @Override
                    public void onDateSelected(@NotNull PersianPickerDate persianPickerDate) {
                        Log.d(TAG, "onDateSelected: " + persianPickerDate.getTimestamp());//675930448000
                        Log.d(TAG, "onDateSelected: " + persianPickerDate.getGregorianDate());//Mon Jun 03 10:57:28 GMT+04:30 1991
                        Log.d(TAG, "onDateSelected: " + persianPickerDate.getPersianLongDate());// دوشنبه  13  خرداد  1370
                        Log.d(TAG, "onDateSelected: " + persianPickerDate.getPersianMonthName());//خرداد
                        Log.d(TAG, "onDateSelected: " + PersianCalendarUtils.isPersianLeapYear(persianPickerDate.getPersianYear()));//true
                        Toast.makeText(context, persianPickerDate.getPersianYear() + "/" + persianPickerDate.getPersianMonth() + "/" + persianPickerDate.getPersianDay(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDismissed() {

                    }
                });

        picker.show();
```

## Public Methods

| Name | Description |
|:----:|:----:|
|setMaxYear(int)| set maximum year can be selected|
|setMinYear(int)| set minimum year can be selected|
|setTypeFace(TypeFace)| set dialog typeface|
|setInitDate(PersianCalendar)| set date that dialog will launch on that|
|setInitDate(PersianCalendar,boolean)| set date that dialog will launch on that and force min/max year to be compatible with it|
|setPositiveButtonString(String)| set positive button text|
|setPositiveButtonResource(@StringRes int)| set positive button text from strings.xml|
|setNegativeButton(String)| set negative button text|
|setNegativeButtonResource(@StringRes int)| set negative button text from strings.xml|
|setTodayButton(String)| set today button text|
|setTodayButtonResource(@StringRes int)| set today button text from strings.xml|
|setTodayButtonVisible(boolean)| set today button visible/invisible|
|setActionTextColor(@ColorInt int)| set dialog buttons texts color|
|setActionTextColorResource(@ColorRes int)| set dialog buttons texts color form colors.xml|
|setCancelable(boolean)| set dialog cancelable or not|
|setBackgroundColor(@ColorInt int)| set dialog background color|
|setPickerBackgroundColor(@ColorInt int)| set date pickers background color|
|setTitleType(NO_TITLE/DAY_MONTH_YEAR/WEEKDAY_DAY_MONTH_YEAR)|It will handle title show scenarios|
|setPickerBackgroundDrawable(@DrawableRes int)| set date pickers background drawable from res/drawable folder|
|setAllButtonsTextSize(int)| set Action button text size|
|setShowInBottomSheet(bool)|switch between dialog and bottomsheet|
|setListener(Listener)| set dialog callback listener|

## STYLING
If you want to change picker text color or divider color you can set it via an easy style
in your base application Theme, add 
```xml
 <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
         
         <!-- Refrence your picker theme here -->
        <item name="numberPickerTheme">@style/customNumberPicker</item>
 </style>

 <!-- Here is your custom date picker theme -->
 <style name="customNumberPicker">
        <!-- use this for text color -->
        <item name="android:textColorPrimary">@android:color/holo_green_dark</item>
         
        <!-- use this for divider color -->
        <item name="colorControlNormal">@android:color/holo_purple</item>
 </style>
```

## CREDITS
* Special Thanks to [PersianDatePicker](https://github.com/alibehzadian/PersianDatePicker).

## License
```
   
The MIT License (MIT)

Copyright (c) 2021 Ali Abdolahi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```


