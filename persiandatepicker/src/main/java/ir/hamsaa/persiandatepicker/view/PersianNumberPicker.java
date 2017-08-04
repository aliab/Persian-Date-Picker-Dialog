package ir.hamsaa.persiandatepicker.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;

/**
 * Created by aliabdolahi on 1/23/17.
 */

public class PersianNumberPicker extends NumberPicker {

    private Typeface typeFace;

    public PersianNumberPicker(Context context) {
        super(context);
    }

    public PersianNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PersianNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        updateView(child);
    }

    @Override
    public void addView(View child, int index,
                        android.view.ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        updateView(child);
    }

    @Override
    public void addView(View child, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, params);
        updateView(child);
    }

    public void setTypeFace(Typeface typeFace) {
        this.typeFace = typeFace;
        super.invalidate();
    }

    private void updateView(View view) {
        if (view instanceof TextView) {
            if (PersianDatePickerDialog.typeFace != null)
                ((TextView) view).setTypeface(PersianDatePickerDialog.typeFace);
        }
    }


}
