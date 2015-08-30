// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import android.content.Context;

// Referenced classes of package com.splunk.mint:
//            DataSaver, NetSender

interface InterfaceDataType
{

    public abstract void save(DataSaver datasaver);

    public abstract void send(Context context, NetSender netsender, boolean flag);

    public abstract void send(NetSender netsender, boolean flag);

    public abstract String toJsonLine();
}
