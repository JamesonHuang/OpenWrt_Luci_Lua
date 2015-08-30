// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.widget.TimePicker;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.uiactions:
//            UIAction

public class DisplayTimePickerAction extends UIAction
{

    DisplayTimePickerAction(SonosActivity sonosactivity, String s, SCITime scitime)
    {
        super(sonosactivity);
        mTitle = s;
        mTime = scitime;
    }

    public SCActionCompletionStatus perform(final SCIActionContext pActionCtxt)
    {
        final SCIPropertyBag bag = pActionCtxt.getPropertyBag();
        android.app.TimePickerDialog.OnTimeSetListener ontimesetlistener = new android.app.TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(TimePicker timepicker, int k, int l)
            {
                bag.setIntProp(sclibConstants.SCACTN_INTPROP_TIME_HOURS, k);
                bag.setIntProp(sclibConstants.SCACTN_INTPROP_TIME_MINUTES, l);
                bag.setIntProp(sclibConstants.SCACTN_INTPROP_TIME_SECONDS, 0);
                pActionCtxt.actionHasCompleted(DisplayTimePickerAction.this);
            }

            final DisplayTimePickerAction this$0;
            final SCIPropertyBag val$bag;
            final SCIActionContext val$pActionCtxt;

            
            {
                this$0 = DisplayTimePickerAction.this;
                bag = scipropertybag;
                pActionCtxt = sciactioncontext;
                super();
            }
        }
;
        SCIDateTimeManager scidatetimemanager = currentContext.getHousehold().getDateTimeManager();
        SonosActivity sonosactivity = currentContext;
        int i = mTime.getHour();
        int j = mTime.getMinute();
        boolean flag;
        TimePickerDialog timepickerdialog;
        if(scidatetimemanager.getTimeFormat() == SCTimeFormat.SCTIMEFORMAT_24HOUR)
            flag = true;
        else
            flag = false;
        timepickerdialog = new TimePickerDialog(sonosactivity, ontimesetlistener, i, j, flag);
        timepickerdialog.setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

            public void onCancel(DialogInterface dialoginterface)
            {
                bag.setBoolProp(sclibConstants.SCACTN_BOOLPROP_MENU_CANCEL, true);
                pActionCtxt.actionHasCompleted(DisplayTimePickerAction.this);
            }

            final DisplayTimePickerAction this$0;
            final SCIPropertyBag val$bag;
            final SCIActionContext val$pActionCtxt;

            
            {
                this$0 = DisplayTimePickerAction.this;
                bag = scipropertybag;
                pActionCtxt = sciactioncontext;
                super();
            }
        }
);
        timepickerdialog.show();
        return SCActionCompletionStatus.WAIT_FOR_CALLBACK;
    }

    private static final String LOG_TAG = com/sonos/acr/uiactions/DisplayTimePickerAction.getSimpleName();
    SCITime mTime;
    String mTitle;

}
