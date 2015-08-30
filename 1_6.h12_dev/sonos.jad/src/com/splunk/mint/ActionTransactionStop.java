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
//            ActionTransaction, InterfaceDataType, EnumActionType, EnumTransactionStatus, 
//            Properties, TransactionsDatabase, DataSaver, NetSender

class ActionTransactionStop extends ActionTransaction
    implements InterfaceDataType
{

    private ActionTransactionStop(String s, EnumTransactionStatus enumtransactionstatus, String s1, HashMap hashmap)
    {
        super(s, EnumActionType.trstop, hashmap);
        duration = 0L;
        status = EnumTransactionStatus.FAIL;
        reason = "";
        status = enumtransactionstatus;
        reason = s1;
        if(s1 == null || s1.length() == 0)
            reason = "NA";
        TransactionsDatabase.Container container = Properties.transactionsDatabase.getStartedTransactionContainer(s);
        if(container != null)
        {
            transaction_id = container.transid;
            long l = container.timestamp.longValue();
            if(l != -1L)
                duration = timestampMilis.longValue() - l;
        } else
        {
            transaction_id = null;
        }
        Properties.transactionsDatabase.closeStartedTransaction(s);
    }

    protected static final ActionTransactionStop createTransactionCancel(String s, String s1, HashMap hashmap)
    {
        return new ActionTransactionStop(s, EnumTransactionStatus.CANCEL, s1, hashmap);
    }

    protected static final ActionTransactionStop createTransactionFail(String s, String s1, HashMap hashmap)
    {
        return new ActionTransactionStop(s, EnumTransactionStatus.FAIL, s1, hashmap);
    }

    protected static final ActionTransactionStop createTransactionStop(String s, HashMap hashmap)
    {
        return new ActionTransactionStop(s, EnumTransactionStatus.SUCCESS, null, hashmap);
    }

    public void save(DataSaver datasaver)
    {
        String s = toJsonLine();
        if(s != null)
            (new DataSaver()).save(s);
    }

    public void send(Context context, NetSender netsender, boolean flag)
    {
        String s = toJsonLine();
        if(s != null)
            netsender.send(s, flag);
    }

    public void send(NetSender netsender, boolean flag)
    {
        String s = toJsonLine();
        if(s != null)
            netsender.send(s, flag);
    }

    public String toJsonLine()
    {
        String s;
        if(transaction_id == null)
        {
            s = null;
        } else
        {
            JSONObject jsonobject = getBasicDataFixtureJson();
            try
            {
                jsonobject.put("tr_name", name);
                jsonobject.put("status", status.toString());
                jsonobject.put("reason", reason);
                jsonobject.put("transaction_id", transaction_id);
                jsonobject.put("tr_duration", String.valueOf(duration));
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
            if(Properties.transactions.contains(transaction_id))
                Properties.transactions.remove(transaction_id);
            s = (new StringBuilder()).append(jsonobject.toString()).append(Properties.getSeparator(EnumActionType.trstop)).toString();
        }
        return s;
    }

    protected long duration;
    protected String reason;
    protected EnumTransactionStatus status;
}
