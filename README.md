# Persian Date Picker Dialog
[![](https://jitpack.io/v/hamsaadev/Persian-Date-Picker-Dialog.svg)](https://jitpack.io/#hamsaadev/Persian-Date-Picker-Dialog)

![Hero Image](https://raw.githubusercontent.com/hamsaadev/Persian-Date-Picker-Dialog/master/screenshot/heroimage.jpg)


Persian Date Picker Dialog

This library provides you a beautiful Persian Date picker dialog with easy API.


## برای دوستان فارسی زبان
ما در همسا همیشه سعی میکنیم که حداقل سهم کوچکی در توسعه نرم افزار های فارسی داشته باشیم. به همین دلیل خوشحال میشویم که هر آنچه که ساخته ایم را با دیگران به اشتراک بگذاریم تا بتوانیم آینده ای بهتر را همه در کنار هم بسازیم. اگر ایده ای دارید یا این کد ها را بهبود داده اید٬ بسیار خوشحال میشویم که درخواست بهبود کد ها از شما را بپذیریم.

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
    compile 'com.github.hamsaadev:Persian-Date-Picker-Dialog:V1.0'
}
```


Then in your Java Code, you use it like below.

```java
 picker = new PersianDatePickerDialog(this)
                .setPositiveButtonString("باشه")
                .setNegativeButton("بیخیال")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setInitDate(initDate)
                .setMaxYear(1395)
                .setMinYear(1300)
                .setActionTextColor(Color.GRAY)
                .setTypeFace(typeface)
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {
                        Toast.makeText(context, persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDisimised() {

                    }
                });

        picker.show();
```

If you need to set an initial date, just simply set it like below.
```java

 PersianCalendar initDate = new PersianCalendar();
 initDate.setPersianDate(1370, 3, 13);

 persianDatePickerDialog.setInitDate(initDate)
```

## CREDITS
* Special Thanks to [PersianDatePicker](https://github.com/alibehzadian/PersianDatePicker).


## License
```
   
The MIT License (MIT)

Copyright (c) 2017 Hamsaa.ir

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


