// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.splunk.mint:
//            BaseDTO, InterfaceDataType, EnumActionType, DataSaver, 
//            NetSender, Properties

class ActionView extends BaseDTO
    implements InterfaceDataType
{

    public ActionView(EnumActionType enumactiontype, String s)
    {
        super(enumactiontype, null);
        viewName = "";
        viewName = s;
    }

    public static final ActionView logView(String s)
    {
        return new ActionView(EnumActionType.view, s);
    }

    public void save(DataSaver datasaver)
    {
        datasaver.save(toJsonLine());
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
        JSONObject jsonobject = getBasicDataFixtureJson();
        try
        {
            if(viewName != null)
                jsonobject.put("view_name", viewName);
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        return (new StringBuilder()).append(jsonobject.toString()).append(Properties.getSeparator(EnumActionType.view)).toString();
    }

    public String viewName;
}
