// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse;

import com.sonos.sclib.SCIBrowseDataSource;
import com.sonos.sclib.SCIBrowseStackManager;

public interface Browseable
{

    public abstract void displayBrowseStack(SCIBrowseStackManager scibrowsestackmanager);

    public abstract void popPages(int i);

    public abstract void pushURI(String s, String s1, boolean flag);

    public abstract void showPicker(SCIBrowseDataSource scibrowsedatasource, String s);
}
