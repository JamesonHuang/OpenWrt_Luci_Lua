// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ProgressBar;

public class WaitingTransparentProgressBar extends ProgressDialog
{

    public WaitingTransparentProgressBar(Context context)
    {
        super(context, 0x7f0d00a6);
    }

    public static WaitingTransparentProgressBar show(Context context, CharSequence charsequence, CharSequence charsequence1)
    {
        return show(context, charsequence, charsequence1, false);
    }

    public static WaitingTransparentProgressBar show(Context context, CharSequence charsequence, CharSequence charsequence1, boolean flag)
    {
        return show(context, charsequence, charsequence1, flag, false, null);
    }

    public static WaitingTransparentProgressBar show(Context context, CharSequence charsequence, CharSequence charsequence1, boolean flag, boolean flag1)
    {
        return show(context, charsequence, charsequence1, flag, flag1, null);
    }

    public static WaitingTransparentProgressBar show(Context context, CharSequence charsequence, CharSequence charsequence1, boolean flag, boolean flag1, android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        WaitingTransparentProgressBar waitingtransparentprogressbar = new WaitingTransparentProgressBar(context);
        waitingtransparentprogressbar.setTitle(charsequence);
        waitingtransparentprogressbar.setCancelable(flag1);
        waitingtransparentprogressbar.setOnCancelListener(oncancellistener);
        waitingtransparentprogressbar.addContentView(new ProgressBar(context), new android.view.ViewGroup.LayoutParams(-2, -2));
        waitingtransparentprogressbar.show();
        return waitingtransparentprogressbar;
    }
}
