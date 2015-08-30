// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.DatePicker;
import com.sonos.acr.SonosActivity;
import com.sonos.sclib.*;
import net.davidcesarino.android.atlantis.ui.dialog.DatePickerDialogFragment;

// Referenced classes of package com.sonos.acr.uiactions:
//            UIAction

public class DisplayDatePickerAction extends UIAction
{

    DisplayDatePickerAction(SonosActivity sonosactivity, String s, SCISystemTime scisystemtime)
    {
        super(sonosactivity);
        mTitle = s;
        mSystemTime = scisystemtime;
    }

    public SCActionCompletionStatus perform(final SCIActionContext pActionCtxt)
    {
        final SCIPropertyBag bag = pActionCtxt.getPropertyBag();
        SCActionCompletionStatus scactioncompletionstatus;
        if(mSystemTime != null && mSystemTime.isValid())
        {
            android.app.DatePickerDialog.OnDateSetListener ondatesetlistener = new android.app.DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker datepicker, int i, int j, int k)
                {
                    bag.setIntProp(sclibConstants.SCACTN_INTPROP_DATE_YEAR, i);
                    bag.setIntProp(sclibConstants.SCACTN_INTPROP_DATE_MONTH, j + 1);
                    bag.setIntProp(sclibConstants.SCACTN_INTPROP_DATE_DAY, k);
                    pActionCtxt.actionHasCompleted(DisplayDatePickerAction.this);
                }

                final DisplayDatePickerAction this$0;
                final SCIPropertyBag val$bag;
                final SCIActionContext val$pActionCtxt;

            
            {
                this$0 = DisplayDatePickerAction.this;
                bag = scipropertybag;
                pActionCtxt = sciactioncontext;
                super();
            }
            }
;
            android.content.DialogInterface.OnCancelListener oncancellistener = new android.content.DialogInterface.OnCancelListener() {

                public void onCancel(DialogInterface dialoginterface)
                {
                    bag.setBoolProp(sclibConstants.SCACTN_BOOLPROP_MENU_CANCEL, true);
                    pActionCtxt.actionHasCompleted(DisplayDatePickerAction.this);
                }

                final DisplayDatePickerAction this$0;
                final SCIPropertyBag val$bag;
                final SCIActionContext val$pActionCtxt;

            
            {
                this$0 = DisplayDatePickerAction.this;
                bag = scipropertybag;
                pActionCtxt = sciactioncontext;
                super();
            }
            }
;
            Bundle bundle = new Bundle();
            bundle.putInt("Year", mSystemTime.getYear());
            bundle.putInt("Month", -1 + mSystemTime.getMonth());
            bundle.putInt("Day", mSystemTime.getDay());
            DatePickerDialogFragment datepickerdialogfragment = new DatePickerDialogFragment(currentContext, ondatesetlistener, oncancellistener);
            datepickerdialogfragment.setArguments(bundle);
            datepickerdialogfragment.show(currentContext.getSupportFragmentManager(), "fragment_date_picker");
            scactioncompletionstatus = SCActionCompletionStatus.WAIT_FOR_CALLBACK;
        } else
        {
            bag.setBoolProp(sclibConstants.SCACTN_BOOLPROP_MENU_CANCEL, true);
            scactioncompletionstatus = SCActionCompletionStatus.DONE_WITH_ACTION;
        }
        return scactioncompletionstatus;
    }

    private static final String LOG_TAG = com/sonos/acr/uiactions/DisplayDatePickerAction.getSimpleName();
    SCISystemTime mSystemTime;
    String mTitle;

}
