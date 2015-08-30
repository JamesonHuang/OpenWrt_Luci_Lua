// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.spi;

import ch.qos.logback.core.Appender;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package ch.qos.logback.core.spi:
//            AppenderAttachable

public class AppenderAttachableImpl
    implements AppenderAttachable
{

    public AppenderAttachableImpl()
    {
    }

    public void addAppender(Appender appender)
    {
        if(appender == null)
        {
            throw new IllegalArgumentException("Null argument disallowed");
        } else
        {
            appenderList.addIfAbsent(appender);
            return;
        }
    }

    public int appendLoopOnAppenders(Object obj)
    {
        Iterator iterator = appenderList.iterator();
        int i;
        for(i = 0; iterator.hasNext(); i++)
            ((Appender)iterator.next()).doAppend(obj);

        return i;
    }

    public void detachAndStopAllAppenders()
    {
        for(Iterator iterator = appenderList.iterator(); iterator.hasNext(); ((Appender)iterator.next()).stop());
        appenderList.clear();
    }

    public boolean detachAppender(Appender appender)
    {
        boolean flag;
        if(appender == null)
            flag = false;
        else
            flag = appenderList.remove(appender);
        return flag;
    }

    public boolean detachAppender(String s)
    {
        boolean flag = false;
        if(s != null) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        boolean flag1;
        Iterator iterator = appenderList.iterator();
        Appender appender;
        do
        {
            if(!iterator.hasNext())
                break; /* Loop/switch isn't completed */
            appender = (Appender)iterator.next();
        } while(!s.equals(appender.getName()));
        flag1 = appenderList.remove(appender);
_L4:
        flag = flag1;
        if(true) goto _L1; else goto _L3
_L3:
        flag1 = false;
          goto _L4
        if(true) goto _L1; else goto _L5
_L5:
    }

    public Appender getAppender(String s)
    {
        if(s != null) goto _L2; else goto _L1
_L1:
        Appender appender = null;
_L4:
        return appender;
_L2:
        for(Iterator iterator = appenderList.iterator(); iterator.hasNext();)
        {
            appender = (Appender)iterator.next();
            if(s.equals(appender.getName()))
                continue; /* Loop/switch isn't completed */
        }

        appender = null;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean isAttached(Appender appender)
    {
        if(appender != null) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        for(Iterator iterator = appenderList.iterator(); iterator.hasNext();)
            if((Appender)iterator.next() == appender)
            {
                flag = true;
                continue; /* Loop/switch isn't completed */
            }

        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public Iterator iteratorForAppenders()
    {
        return appenderList.iterator();
    }

    static final long START = System.currentTimeMillis();
    private final CopyOnWriteArrayList appenderList = new CopyOnWriteArrayList();

}
