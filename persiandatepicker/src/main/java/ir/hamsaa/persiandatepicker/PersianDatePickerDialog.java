package ir.hamsaa.persiandatepicker;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

/**
 * Created by aliabdolahi on 1/23/17.
 */

public class PersianDatePickerDialog {

    private Context context;
    private String positiveButton;
    private String negativeButton;

    public PersianDatePickerDialog with(Context context) {
        this.context = context;
        return this;
    }

    public PersianDatePickerDialog setListener() {
        return this;
    }

    public PersianDatePickerDialog setPositiveButton(String positiveButton) {
        this.positiveButton = positiveButton;
        return this;
    }

    public PersianDatePickerDialog setPositiveButtonResource(@StringRes int positiveButton) {
        this.positiveButton = context.getString(positiveButton);
        return this;
    }

    public PersianDatePickerDialog setNegativeButton(String negativeButton) {
        this.negativeButton = negativeButton;
        return this;
    }

    public PersianDatePickerDialog setNegativeButtonResource(@StringRes int negativeButton) {
        this.negativeButton = context.getString(negativeButton);
        return this;
    }


    public PersianDatePickerDialog build() {
        return this;
    }

    protected void show() {

        new AlertDialog.Builder(context)
                .setNegativeButton(positiveButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(negativeButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setCancelable(true)
                .show();

    }

}
