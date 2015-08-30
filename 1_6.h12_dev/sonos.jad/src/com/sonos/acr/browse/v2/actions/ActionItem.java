// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.actions;

import android.view.View;

public abstract class ActionItem
    implements android.view.View.OnClickListener
{

    public ActionItem()
    {
    }

    protected void finalize()
        throws Throwable
    {
        finalize();
    }

    public abstract String getActionID();

    public abstract String getCategory();

    public abstract String getLabel();

    public abstract boolean isEnabled();

    public void onClick(View view)
    {
        perform();
    }

    public abstract void perform();

    public String toString()
    {
        return (new StringBuilder()).append("[").append(getLabel()).append(":").append(getCategory()).append("]").toString();
    }
}
