// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.status;

import java.util.*;

// Referenced classes of package ch.qos.logback.core.status:
//            Status

public abstract class StatusBase
    implements Status
{

    StatusBase(int i, String s, Object obj)
    {
        this(i, s, obj, null);
    }

    StatusBase(int i, String s, Object obj, Throwable throwable1)
    {
        level = i;
        message = s;
        origin = obj;
        throwable = throwable1;
        date = System.currentTimeMillis();
    }

    /**
     * @deprecated Method add is deprecated
     */

    public void add(Status status)
    {
        this;
        JVM INSTR monitorenter ;
        if(status != null)
            break MISSING_BLOCK_LABEL_21;
        throw new NullPointerException("Null values are not valid Status.");
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if(childrenList == null)
            childrenList = new ArrayList();
        childrenList.add(status);
        this;
        JVM INSTR monitorexit ;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this != obj) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(obj == null)
            flag = false;
        else
        if(getClass() != obj.getClass())
        {
            flag = false;
        } else
        {
            StatusBase statusbase = (StatusBase)obj;
            if(level != statusbase.level)
                flag = false;
            else
            if(message == null)
            {
                if(statusbase.message != null)
                    flag = false;
            } else
            if(!message.equals(statusbase.message))
                flag = false;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public Long getDate()
    {
        return Long.valueOf(date);
    }

    /**
     * @deprecated Method getEffectiveLevel is deprecated
     */

    public int getEffectiveLevel()
    {
        this;
        JVM INSTR monitorenter ;
        Iterator iterator1;
        int j;
        int i = level;
        iterator1 = iterator();
        j = i;
_L1:
        int k;
        if(!iterator1.hasNext())
            break MISSING_BLOCK_LABEL_54;
        k = ((Status)iterator1.next()).getEffectiveLevel();
        if(k > j)
            j = k;
          goto _L1
        this;
        JVM INSTR monitorexit ;
        return j;
        Exception exception;
        exception;
        throw exception;
    }

    public int getLevel()
    {
        return level;
    }

    public String getMessage()
    {
        return message;
    }

    public Object getOrigin()
    {
        return origin;
    }

    public Throwable getThrowable()
    {
        return throwable;
    }

    /**
     * @deprecated Method hasChildren is deprecated
     */

    public boolean hasChildren()
    {
        this;
        JVM INSTR monitorenter ;
        if(childrenList == null) goto _L2; else goto _L1
_L1:
        int i = childrenList.size();
        if(i <= 0) goto _L2; else goto _L3
_L3:
        boolean flag = true;
_L5:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
        Exception exception;
        exception;
        throw exception;
    }

    public int hashCode()
    {
        int i = 31 * (31 + level);
        int j;
        if(message == null)
            j = 0;
        else
            j = message.hashCode();
        return j + i;
    }

    /**
     * @deprecated Method iterator is deprecated
     */

    public Iterator iterator()
    {
        this;
        JVM INSTR monitorenter ;
        if(childrenList == null) goto _L2; else goto _L1
_L1:
        Iterator iterator3 = childrenList.iterator();
        Iterator iterator2 = iterator3;
_L4:
        this;
        JVM INSTR monitorexit ;
        return iterator2;
_L2:
        Iterator iterator1 = EMPTY_LIST.iterator();
        iterator2 = iterator1;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method remove is deprecated
     */

    public boolean remove(Status status)
    {
        this;
        JVM INSTR monitorenter ;
        List list = childrenList;
        if(list != null) goto _L2; else goto _L1
_L1:
        boolean flag1 = false;
_L4:
        this;
        JVM INSTR monitorexit ;
        return flag1;
_L2:
        boolean flag = childrenList.remove(status);
        flag1 = flag;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        getEffectiveLevel();
        JVM INSTR tableswitch 0 2: default 40
    //                   0 114
    //                   1 124
    //                   2 134;
           goto _L1 _L2 _L3 _L4
_L1:
        if(origin != null)
        {
            stringbuffer.append(" in ");
            stringbuffer.append(origin);
            stringbuffer.append(" -");
        }
        stringbuffer.append(" ");
        stringbuffer.append(message);
        if(throwable != null)
        {
            stringbuffer.append(" ");
            stringbuffer.append(throwable);
        }
        return stringbuffer.toString();
_L2:
        stringbuffer.append("INFO");
        continue; /* Loop/switch isn't completed */
_L3:
        stringbuffer.append("WARN");
        continue; /* Loop/switch isn't completed */
_L4:
        stringbuffer.append("ERROR");
        if(true) goto _L1; else goto _L5
_L5:
    }

    private static final List EMPTY_LIST = new ArrayList(0);
    List childrenList;
    long date;
    int level;
    final String message;
    final Object origin;
    Throwable throwable;

}
