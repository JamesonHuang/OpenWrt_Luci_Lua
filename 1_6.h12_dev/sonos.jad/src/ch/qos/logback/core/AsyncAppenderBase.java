// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core;

import ch.qos.logback.core.spi.AppenderAttachable;
import ch.qos.logback.core.spi.AppenderAttachableImpl;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// Referenced classes of package ch.qos.logback.core:
//            UnsynchronizedAppenderBase, Appender

public class AsyncAppenderBase extends UnsynchronizedAppenderBase
    implements AppenderAttachable
{
    class Worker extends Thread
    {

        public void run()
        {
            AsyncAppenderBase asyncappenderbase = AsyncAppenderBase.this;
            AppenderAttachableImpl appenderattachableimpl = asyncappenderbase.aai;
            do
            {
                if(!asyncappenderbase.isStarted())
                    break;
                try
                {
                    appenderattachableimpl.appendLoopOnAppenders(asyncappenderbase.blockingQueue.take());
                    continue;
                }
                catch(InterruptedException interruptedexception) { }
                break;
            } while(true);
            addInfo("Worker thread will flush remaining events before exiting.");
            for(Iterator iterator = asyncappenderbase.blockingQueue.iterator(); iterator.hasNext(); appenderattachableimpl.appendLoopOnAppenders(iterator.next()));
            appenderattachableimpl.detachAndStopAllAppenders();
        }

        final AsyncAppenderBase this$0;

        Worker()
        {
            this$0 = AsyncAppenderBase.this;
            super();
        }
    }


    public AsyncAppenderBase()
    {
        aai = new AppenderAttachableImpl();
        queueSize = 256;
        appenderCount = 0;
        discardingThreshold = -1;
        worker = new Worker();
    }

    private boolean isQueueBelowDiscardingThreshold()
    {
        boolean flag;
        if(blockingQueue.remainingCapacity() < discardingThreshold)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void put(Object obj)
    {
        blockingQueue.put(obj);
_L2:
        return;
        InterruptedException interruptedexception;
        interruptedexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void addAppender(Appender appender)
    {
        if(appenderCount == 0)
        {
            appenderCount = 1 + appenderCount;
            addInfo((new StringBuilder()).append("Attaching appender named [").append(appender.getName()).append("] to AsyncAppender.").toString());
            aai.addAppender(appender);
        } else
        {
            addWarn("One and only one appender may be attached to AsyncAppender.");
            addWarn((new StringBuilder()).append("Ignoring additional appender named [").append(appender.getName()).append("]").toString());
        }
    }

    protected void append(Object obj)
    {
        if(!isQueueBelowDiscardingThreshold() || !isDiscardable(obj))
        {
            preprocess(obj);
            put(obj);
        }
    }

    public void detachAndStopAllAppenders()
    {
        aai.detachAndStopAllAppenders();
    }

    public boolean detachAppender(Appender appender)
    {
        return aai.detachAppender(appender);
    }

    public boolean detachAppender(String s)
    {
        return aai.detachAppender(s);
    }

    public Appender getAppender(String s)
    {
        return aai.getAppender(s);
    }

    public int getDiscardingThreshold()
    {
        return discardingThreshold;
    }

    public int getNumberOfElementsInQueue()
    {
        return blockingQueue.size();
    }

    public int getQueueSize()
    {
        return queueSize;
    }

    public int getRemainingCapacity()
    {
        return blockingQueue.remainingCapacity();
    }

    public boolean isAttached(Appender appender)
    {
        return aai.isAttached(appender);
    }

    protected boolean isDiscardable(Object obj)
    {
        return false;
    }

    public Iterator iteratorForAppenders()
    {
        return aai.iteratorForAppenders();
    }

    protected void preprocess(Object obj)
    {
    }

    public void setDiscardingThreshold(int i)
    {
        discardingThreshold = i;
    }

    public void setQueueSize(int i)
    {
        queueSize = i;
    }

    public void start()
    {
        if(appenderCount == 0)
            addError("No attached appenders found.");
        else
        if(queueSize < 1)
        {
            addError((new StringBuilder()).append("Invalid queue size [").append(queueSize).append("]").toString());
        } else
        {
            blockingQueue = new ArrayBlockingQueue(queueSize);
            if(discardingThreshold == -1)
                discardingThreshold = queueSize / 5;
            addInfo((new StringBuilder()).append("Setting discardingThreshold to ").append(discardingThreshold).toString());
            worker.setDaemon(true);
            worker.setName((new StringBuilder()).append("AsyncAppender-Worker-").append(worker.getName()).toString());
            super.start();
            worker.start();
        }
    }

    public void stop()
    {
        if(isStarted())
        {
            super.stop();
            worker.interrupt();
            try
            {
                worker.join(1000L);
            }
            catch(InterruptedException interruptedexception)
            {
                addError("Failed to join worker thread", interruptedexception);
            }
        }
    }

    public static final int DEFAULT_QUEUE_SIZE = 256;
    static final int UNDEFINED = -1;
    AppenderAttachableImpl aai;
    int appenderCount;
    BlockingQueue blockingQueue;
    int discardingThreshold;
    int queueSize;
    Worker worker;
}
