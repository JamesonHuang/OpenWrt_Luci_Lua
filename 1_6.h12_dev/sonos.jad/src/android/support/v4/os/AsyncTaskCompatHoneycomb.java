// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.os;

import android.os.AsyncTask;

class AsyncTaskCompatHoneycomb
{

    AsyncTaskCompatHoneycomb()
    {
    }

    static transient void executeParallel(AsyncTask asynctask, Object aobj[])
    {
        asynctask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, aobj);
    }
}
