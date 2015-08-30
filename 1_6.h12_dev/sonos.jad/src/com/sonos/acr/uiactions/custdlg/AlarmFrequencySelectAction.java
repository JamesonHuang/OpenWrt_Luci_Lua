// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions.custdlg;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.SparseBooleanArray;
import android.widget.ListView;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.uiactions.DisplayCustomControlAction;
import com.sonos.acr.util.SLog;
import com.sonos.sclib.*;

public class AlarmFrequencySelectAction extends DisplayCustomControlAction
    implements android.content.DialogInterface.OnClickListener, android.content.DialogInterface.OnMultiChoiceClickListener
{

    public AlarmFrequencySelectAction(SonosActivity sonosactivity)
    {
        DisplayCustomControlAction(sonosactivity);
    }

    private String logCheckBoxStates(ListView listview)
    {
        String s = "";
        int i = 0;
        while(i < listview.getCount()) 
        {
            StringBuilder stringbuilder = (new StringBuilder()).append(s).append(" ").append(i).append(":");
            String s1;
            if(listview.isItemChecked(i))
                s1 = "1";
            else
                s1 = "0";
            s = stringbuilder.append(s1).toString();
            i++;
        }
        return s;
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        SLog.e(LOG_TAG, "onClickSingle called");
        if(i == -1)
        {
            ListView listview = m_alertDialog.getListView();
            SCIRecurrence scirecurrence = alarm.getRecurrence();
            if(listview.isItemChecked(0))
            {
                scirecurrence.setRecurrenceType(SCRecurrenceType.SCRECURRENCE_ONCE);
            } else
            {
                scirecurrence.setRecurrenceType(SCRecurrenceType.SCRECURRENCE_DAYS_OF_WEEK);
                scirecurrence.enableForDayOfWeek(SCDayOfWeek.SCDAY_MONDAY, listview.isItemChecked(1));
                scirecurrence.enableForDayOfWeek(SCDayOfWeek.SCDAY_TUESDAY, listview.isItemChecked(2));
                scirecurrence.enableForDayOfWeek(SCDayOfWeek.SCDAY_WEDNESDAY, listview.isItemChecked(3));
                scirecurrence.enableForDayOfWeek(SCDayOfWeek.SCDAY_THURSDAY, listview.isItemChecked(4));
                scirecurrence.enableForDayOfWeek(SCDayOfWeek.SCDAY_FRIDAY, listview.isItemChecked(5));
                scirecurrence.enableForDayOfWeek(SCDayOfWeek.SCDAY_SATURDAY, listview.isItemChecked(6));
                scirecurrence.enableForDayOfWeek(SCDayOfWeek.SCDAY_SUNDAY, listview.isItemChecked(7));
            }
            alarm.setRecurrence(scirecurrence);
            actionContext.actionHasCompleted(this);
        }
    }

    public void onClick(DialogInterface dialoginterface, int i, boolean flag)
    {
        ListView listview;
        int j;
        listview = m_alertDialog.getListView();
        j = 0;
        for(int k = 0; k < listview.getCheckedItemPositions().size(); k++)
        {
            int j1 = listview.getCheckedItemPositions().keyAt(k);
            if(listview.getCheckedItemPositions().get(j1))
                j++;
        }

        SLog.e(LOG_TAG, (new StringBuilder()).append("Check Box States Before: ").append(logCheckBoxStates(listview)).toString());
        if(i != 0) goto _L2; else goto _L1
_L1:
        int l = listview.getCount();
        int i1 = 1;
        while(i1 < l) 
        {
            boolean aflag[] = enabledDays;
            boolean flag1;
            boolean flag2;
            if(!flag)
                flag1 = true;
            else
                flag1 = false;
            aflag[i1] = flag1;
            if(!flag)
                flag2 = true;
            else
                flag2 = false;
            listview.setItemChecked(i1, flag2);
            i1++;
        }
          goto _L3
_L2:
        if(i <= 0) goto _L3; else goto _L4
_L4:
        if(!flag) goto _L6; else goto _L5
_L5:
        enabledDays[0] = false;
        listview.setItemChecked(0, false);
_L3:
        return;
_L6:
        if(j == 0)
        {
            enabledDays[0] = true;
            listview.setItemChecked(0, true);
        }
        if(true) goto _L3; else goto _L7
_L7:
    }

    public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
    {
        actionContext = sciactioncontext;
        int i = actionContext.getPropertyBag().getIntProp(sclibConstants.SCACTN_INTPROP_ALARMID);
        alarm = currentContext.getHousehold().getAlarmManager().getEditableAlarm(i);
        CharSequence acharsequence[] = new CharSequence[8];
        acharsequence[0] = currentContext.getString(0x7f0c0022);
        acharsequence[1] = currentContext.getString(0x7f0c0021);
        acharsequence[2] = currentContext.getString(0x7f0c0026);
        acharsequence[3] = currentContext.getString(0x7f0c0027);
        acharsequence[4] = currentContext.getString(0x7f0c0025);
        acharsequence[5] = currentContext.getString(0x7f0c0020);
        acharsequence[6] = currentContext.getString(0x7f0c0023);
        acharsequence[7] = currentContext.getString(0x7f0c0024);
        SCIRecurrence scirecurrence = alarm.getRecurrence();
        boolean aflag[] = new boolean[8];
        boolean flag;
        if(scirecurrence.getRecurrenceType() == SCRecurrenceType.SCRECURRENCE_ONCE)
            flag = true;
        else
            flag = false;
        aflag[0] = flag;
        aflag[1] = scirecurrence.isEnabledForDayOfWeek(SCDayOfWeek.SCDAY_MONDAY);
        aflag[2] = scirecurrence.isEnabledForDayOfWeek(SCDayOfWeek.SCDAY_TUESDAY);
        aflag[3] = scirecurrence.isEnabledForDayOfWeek(SCDayOfWeek.SCDAY_WEDNESDAY);
        aflag[4] = scirecurrence.isEnabledForDayOfWeek(SCDayOfWeek.SCDAY_THURSDAY);
        aflag[5] = scirecurrence.isEnabledForDayOfWeek(SCDayOfWeek.SCDAY_FRIDAY);
        aflag[6] = scirecurrence.isEnabledForDayOfWeek(SCDayOfWeek.SCDAY_SATURDAY);
        aflag[7] = scirecurrence.isEnabledForDayOfWeek(SCDayOfWeek.SCDAY_SUNDAY);
        enabledDays = aflag;
        m_alertDialogBuilder.setMultiChoiceItems(acharsequence, enabledDays, this);
        m_alertDialogBuilder.setPositiveButton(0x104000a, this);
        m_alertDialogBuilder.setNegativeButton(0x1040000, null);
        m_alertDialogBuilder.setTitle(0x7f0c001f);
        m_alertDialog = m_alertDialogBuilder.create();
        m_alertDialog.show();
        return SCActionCompletionStatus.WAIT_FOR_CALLBACK;
    }

    SCIActionContext actionContext;
    private SCIAlarm alarm;
    boolean enabledDays[];
}
