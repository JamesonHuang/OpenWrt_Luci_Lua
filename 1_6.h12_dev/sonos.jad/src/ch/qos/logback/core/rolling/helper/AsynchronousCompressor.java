// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling.helper;

import java.util.concurrent.*;

// Referenced classes of package ch.qos.logback.core.rolling.helper:
//            CompressionRunnable, Compressor

public class AsynchronousCompressor
{

    public AsynchronousCompressor(Compressor compressor1)
    {
        compressor = compressor1;
    }

    public Future compressAsynchronously(String s, String s1, String s2)
    {
        java.util.concurrent.ScheduledExecutorService scheduledexecutorservice = Executors.newScheduledThreadPool(1);
        Future future = scheduledexecutorservice.submit(new CompressionRunnable(compressor, s, s1, s2));
        scheduledexecutorservice.shutdown();
        return future;
    }

    Compressor compressor;
}
