// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions.custdlg;

import com.sonos.acr.SonosActivity;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.uiactions.DisplayCustomControlAction;
import com.sonos.sclib.SCActionCompletionStatus;
import com.sonos.sclib.SCIActionContext;

public class ResetDialogAction extends DisplayCustomControlAction
{

    public ResetDialogAction(SonosActivity sonosactivity)
    {
        super(sonosactivity);
    }

    public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
    {
        if(!SonosApplication.isUserAMonkey())
            SonosApplication.getInstance().reset();
        return SCActionCompletionStatus.DONE_WITH_ACTION;
    }
}
