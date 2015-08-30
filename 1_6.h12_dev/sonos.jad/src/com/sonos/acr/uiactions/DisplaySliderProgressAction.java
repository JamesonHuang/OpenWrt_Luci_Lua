// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import com.sonos.acr.SonosActivity;
import com.sonos.sclib.*;

public class DisplaySliderProgressAction extends SCIActionSwigBase
    implements android.content.DialogInterface.OnClickListener, android.content.DialogInterface.OnCancelListener
{

    public DisplaySliderProgressAction(SonosActivity sonosactivity, String s, String s1, String s2, int i, int j, int k)
    {
        title = s1;
        instructions = s2;
        rangeMax = k;
        rangeMin = j;
        initialValue = i;
        currentContext = sonosactivity;
    }

    public DisplaySliderProgressAction(String s, String s1, int i, int j)
    {
        title = s;
        instructions = s1;
        rangeMax = i;
        rangeMin = 0;
        initialValue = j;
    }

    public void onCancel(DialogInterface dialoginterface)
    {
        context.getPropertyBag().setBoolProp(sclibConstants.SCACTN_BOOLPROP_MENU_CANCEL, true);
        context.actionHasCompleted(this);
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        if(i != -1) goto _L2; else goto _L1
_L1:
        context.getPropertyBag().setIntProp(sclibConstants.SCACTN_INTPROP_INTEGER_INPUT, seekBar.getProgress() + rangeMin);
        context.actionHasCompleted(this);
_L4:
        return;
_L2:
        if(i == -2)
        {
            context.getPropertyBag().setBoolProp(sclibConstants.SCACTN_BOOLPROP_MENU_CANCEL, true);
            context.actionHasCompleted(this);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
    {
        context = sciactioncontext;
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(currentContext);
        View view = LayoutInflater.from(currentContext).inflate(0x7f0300a1, null);
        seekBar = (SeekBar)view.findViewById(0x7f0a01d9);
        seekBar.setMax(rangeMax - rangeMin);
        seekBar.setProgress(initialValue - rangeMin);
        builder.setView(view);
        builder.setTitle(title);
        if(instructions != null && instructions.trim().length() > 0)
            builder.setMessage(instructions);
        builder.setPositiveButton(0x104000a, this);
        builder.setNegativeButton(0x1040000, this);
        builder.setOnCancelListener(this);
        m_alertDialog = builder.show();
        return SCActionCompletionStatus.WAIT_FOR_CALLBACK;
    }

    protected SCIActionContext context;
    protected SonosActivity currentContext;
    protected int initialValue;
    protected String instructions;
    protected AlertDialog m_alertDialog;
    protected int rangeMax;
    protected int rangeMin;
    protected SeekBar seekBar;
    protected String title;
}
