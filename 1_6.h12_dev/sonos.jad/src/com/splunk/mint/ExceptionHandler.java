// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.splunk.mint:
//            ActionError, EnumActionType, EnumExceptionType, Properties, 
//            TransactionsDatabase, ActionTransactionStop, DataSaver, NetSender, 
//            CrashInfo, Utils, Mint, MintCallback

public class ExceptionHandler
    implements Thread.UncaughtExceptionHandler
{

    public ExceptionHandler(Thread.UncaughtExceptionHandler uncaughtexceptionhandler)
    {
        defaultExceptionHandler = uncaughtexceptionhandler;
    }

    public void uncaughtException(Thread thread, Throwable throwable)
    {
        StringWriter stringwriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringwriter));
        ActionError actionerror = new ActionError(EnumActionType.error, stringwriter.toString(), EnumExceptionType.UNHANDLED, null);
        for(Iterator iterator = Properties.transactionsDatabase.entrySet().iterator(); iterator.hasNext(); iterator.remove())
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            if(entry.getValue() != null)
                ActionTransactionStop.createTransactionFail(((String)entry.getKey()).replace("TStart:name:", ""), actionerror.getErrorHash(), null).save(new DataSaver());
        }

        actionerror.send(new NetSender(), true);
        (new CrashInfo()).saveLastCrashID(String.valueOf(actionerror.getErrorHash()));
        (new CrashInfo()).saveCrashCounter();
        Utils.setForceSendPingOnNextStart();
        if(Mint.mintCallback != null)
            Mint.mintCallback.lastBreath(new Exception(throwable));
        try
        {
            Thread.sleep(3000L);
        }
        catch(InterruptedException interruptedexception)
        {
            interruptedexception.printStackTrace();
        }
        defaultExceptionHandler.uncaughtException(thread, throwable);
    }

    private Thread.UncaughtExceptionHandler defaultExceptionHandler;
}
