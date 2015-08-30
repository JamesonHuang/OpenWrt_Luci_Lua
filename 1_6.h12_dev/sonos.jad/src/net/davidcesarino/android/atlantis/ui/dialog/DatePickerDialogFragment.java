// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.davidcesarino.android.atlantis.ui.dialog;

import android.app.*;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

public class DatePickerDialogFragment extends DialogFragment
{

    public DatePickerDialogFragment(Activity activity, android.app.DatePickerDialog.OnDateSetListener ondatesetlistener, android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        mListener = ondatesetlistener;
        mCancelListener = oncancellistener;
        mCurrentContext = activity;
    }

    private android.app.DatePickerDialog.OnDateSetListener getConstructorListener()
    {
        android.app.DatePickerDialog.OnDateSetListener ondatesetlistener;
        if(hasJellyBeanAndAbove())
            ondatesetlistener = null;
        else
            ondatesetlistener = mListener;
        return ondatesetlistener;
    }

    private static boolean hasJellyBeanAndAbove()
    {
        boolean flag;
        if(android.os.Build.VERSION.SDK_INT >= 16)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
    }

    public void onCancel(DialogInterface dialoginterface)
    {
        super.onCancel(dialoginterface);
        mCancelListener.onCancel(dialoginterface);
    }

    public Dialog onCreateDialog(Bundle bundle)
    {
        Bundle bundle1 = getArguments();
        int i = bundle1.getInt("Year");
        int j = bundle1.getInt("Month");
        int k = bundle1.getInt("Day");
        final DatePickerDialog picker = new DatePickerDialog(mCurrentContext, getConstructorListener(), i, j, k);
        if(hasJellyBeanAndAbove())
            picker.setButton(-1, mCurrentContext.getString(0x104000a), new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int l)
                {
                    DatePicker datepicker = picker.getDatePicker();
                    mListener.onDateSet(datepicker, datepicker.getYear(), datepicker.getMonth(), datepicker.getDayOfMonth());
                }

                final DatePickerDialogFragment this$0;
                final DatePickerDialog val$picker;

            
            {
                this$0 = DatePickerDialogFragment.this;
                picker = datepickerdialog;
                super();
            }
            }
);
        picker.setButton(-2, mCurrentContext.getString(0x1040000), new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int l)
            {
                mCancelListener.onCancel(dialoginterface);
            }

            final DatePickerDialogFragment this$0;

            
            {
                this$0 = DatePickerDialogFragment.this;
                super();
            }
        }
);
        picker.setOnCancelListener(mCancelListener);
        return picker;
    }

    public void onDetach()
    {
        mListener = null;
        mCancelListener = null;
        super.onDetach();
    }

    public static final String DATE = "Day";
    public static final String MONTH = "Month";
    public static final String YEAR = "Year";
    private android.content.DialogInterface.OnCancelListener mCancelListener;
    private Activity mCurrentContext;
    private android.app.DatePickerDialog.OnDateSetListener mListener;


}
