// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import java.util.HashMap;

// Referenced classes of package com.splunk.mint:
//            ActionTransactionStart

class TransactionsDatabase extends HashMap
{
    public class Container
    {

        final TransactionsDatabase this$0;
        public Long timestamp;
        public String transid;

        public Container(Long long1, String s)
        {
            this$0 = TransactionsDatabase.this;
            super();
            timestamp = long1;
            transid = s;
        }
    }


    public TransactionsDatabase()
    {
    }

    /**
     * @deprecated Method addStartedTransaction is deprecated
     */

    public boolean addStartedTransaction(ActionTransactionStart actiontransactionstart)
    {
        boolean flag = false;
        this;
        JVM INSTR monitorenter ;
        if(actiontransactionstart != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        put((new StringBuilder()).append("TStart:name:").append(actiontransactionstart.name).toString(), new Container(actiontransactionstart.timestampMilis, actiontransactionstart.transaction_id));
        flag = true;
        continue; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        exception1.printStackTrace();
        if(true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method closeStartedTransaction is deprecated
     */

    public boolean closeStartedTransaction(String s)
    {
        boolean flag = false;
        this;
        JVM INSTR monitorenter ;
        if(s != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        if(!containsKey((new StringBuilder()).append("TStart:name:").append(s).toString()))
            continue; /* Loop/switch isn't completed */
        put((new StringBuilder()).append("TStart:name:").append(s).toString(), new Container(Long.valueOf(-1L), null));
        flag = true;
        if(true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method getStartedTransactionContainer is deprecated
     */

    public Container getStartedTransactionContainer(String s)
    {
        Container container = null;
        this;
        JVM INSTR monitorenter ;
        if(s != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return container;
_L2:
        if(containsKey((new StringBuilder()).append("TStart:name:").append(s).toString()))
            container = (Container)get((new StringBuilder()).append("TStart:name:").append(s).toString());
        if(true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    protected static final String TransName = "TStart:name:";
    private static final long serialVersionUID = 0xcf3443817e97b27fL;
}
