// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import android.content.Context;
import java.util.HashMap;

// Referenced classes of package com.splunk.mint:
//            BaseDTO, InterfaceDataType, DataSaver, NetSender, 
//            EnumActionType, Properties

abstract class ActionTransaction extends BaseDTO
    implements InterfaceDataType
{

    protected ActionTransaction(String s, EnumActionType enumactiontype, HashMap hashmap)
    {
        super(enumactiontype, hashmap);
        name = "";
        transaction_id = "";
        name = s;
    }

    public void save(DataSaver datasaver)
    {
        (new DataSaver()).save(toJsonLine());
    }

    public void send(Context context, NetSender netsender, boolean flag)
    {
        netsender.send(toJsonLine(), flag);
    }

    public void send(NetSender netsender, boolean flag)
    {
        netsender.send(toJsonLine(), flag);
    }

    public String toJsonLine()
    {
        return (new StringBuilder()).append("").append(Properties.getSeparator(EnumActionType.trstart)).toString();
    }

    protected String name;
    protected String transaction_id;
}
