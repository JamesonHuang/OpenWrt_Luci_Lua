// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import java.util.LinkedList;

// Referenced classes of package com.sonos.acr.util:
//            SLog

public class WorkQueue
{
    private class PoolWorker extends Thread
    {

        public void run()
        {
_L4:
            LinkedList linkedlist = queue;
            linkedlist;
            JVM INSTR monitorenter ;
_L2:
            boolean flag = queue.isEmpty();
            if(!flag)
                break; /* Loop/switch isn't completed */
            try
            {
                queue.wait();
            }
            catch(InterruptedException interruptedexception) { }
            if(true) goto _L2; else goto _L1
_L1:
            r = (Runnable)queue.removeFirst();
            linkedlist;
            JVM INSTR monitorexit ;
            Exception exception;
            try
            {
                r.run();
            }
            catch(RuntimeException runtimeexception)
            {
                SLog.e(WorkQueue.LOG_TAG, "An error occurred during execution of thread", runtimeexception);
            }
            r = null;
            if(true) goto _L4; else goto _L3
_L3:
            exception;
            linkedlist;
            JVM INSTR monitorexit ;
            throw exception;
        }

        Runnable r;
        final WorkQueue this$0;

        private PoolWorker()
        {
            this$0 = WorkQueue.this;
            super();
        }

    }


    public WorkQueue(int i)
    {
        nThreads = i;
        threads = new PoolWorker[i];
        for(int j = 0; j < i; j++)
        {
            threads[j] = new PoolWorker();
            threads[j].start();
        }

    }

    public void cancel(Runnable runnable)
    {
        LinkedList linkedlist = queue;
        linkedlist;
        JVM INSTR monitorenter ;
        if(queue.remove(runnable)) goto _L2; else goto _L1
_L1:
        PoolWorker apoolworker[];
        int i;
        int j;
        apoolworker = threads;
        i = apoolworker.length;
        j = 0;
_L4:
        if(j < i)
        {
            PoolWorker poolworker = apoolworker[j];
            if(runnable == poolworker.r)
                poolworker.interrupt();
            j++;
            continue; /* Loop/switch isn't completed */
        }
_L2:
        linkedlist;
        JVM INSTR monitorexit ;
        return;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void execute(Runnable runnable)
    {
        if(runnable == null)
            break MISSING_BLOCK_LABEL_36;
        LinkedList linkedlist = queue;
        linkedlist;
        JVM INSTR monitorenter ;
        queue.addLast(runnable);
        queue.notify();
    }

    private static final String LOG_TAG = com/sonos/acr/util/WorkQueue.getSimpleName();
    private final int nThreads;
    private final LinkedList queue = new LinkedList();
    private final PoolWorker threads[];



}
