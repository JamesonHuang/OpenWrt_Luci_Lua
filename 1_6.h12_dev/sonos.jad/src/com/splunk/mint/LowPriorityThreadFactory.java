// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import java.util.concurrent.ThreadFactory;

class LowPriorityThreadFactory
    implements ThreadFactory
{

    LowPriorityThreadFactory()
    {
    }

    public Thread newThread(Runnable runnable)
    {
        Thread thread = new Thread(runnable);
        thread.setPriority(1);
        return thread;
    }
}
