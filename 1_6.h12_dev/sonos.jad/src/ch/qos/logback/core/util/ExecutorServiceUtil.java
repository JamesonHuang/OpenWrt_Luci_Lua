// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.util;

import ch.qos.logback.core.CoreConstants;
import java.util.concurrent.*;

public class ExecutorServiceUtil
{

    public ExecutorServiceUtil()
    {
    }

    public static ExecutorService newExecutorService()
    {
        return new ThreadPoolExecutor(CoreConstants.CORE_POOL_SIZE, 32, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue());
    }

    public static void shutdown(ExecutorService executorservice)
    {
        executorservice.shutdownNow();
    }
}
