// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.splunk.mint:
//            ActionTransaction, InterfaceDataType, EnumActionType, Utils, 
//            Properties, TransactionsDatabase, DataSaver, NetSender

class ActionTransactionStart extends ActionTransaction
    implements InterfaceDataType
{

    private ActionTransactionStart(String s, HashMap hashmap)
    {
        super(s, EnumActionType.trstart, hashmap);
        transaction_id = Utils.getRandomSessionNumber();
        if(!Properties.transactions.contains(transaction_id))
            Properties.transactions.add(transaction_id);
    }

    public static ActionTransactionStart createTransactionStart(String s, HashMap hashmap)
    {
        ActionTransactionStart actiontransactionstart = new ActionTransactionStart(s, hashmap);
        Properties.transactionsDatabase.addStartedTransaction(actiontransactionstart);
        return actiontransactionstart;
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
        JSONObject jsonobject = getBasicDataFixtureJson();
        try
        {
            jsonobject.put("tr_name", name);
            jsonobject.put("transaction_id", transaction_id);
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        return (new StringBuilder()).append(jsonobject.toString()).append(Properties.getSeparator(EnumActionType.trstart)).toString();
    }
}
