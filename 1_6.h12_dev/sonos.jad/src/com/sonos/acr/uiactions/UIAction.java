// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions;

import com.sonos.acr.SonosActivity;
import com.sonos.sclib.*;

public abstract class UIAction extends SCIActionSwigBase
{

    protected UIAction(SonosActivity sonosactivity)
    {
        currentContext = sonosactivity;
    }

    public abstract SCActionCompletionStatus perform(SCIActionContext sciactioncontext);

    public final SonosActivity currentContext;
}
