// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.status;

import ch.qos.logback.core.Context;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package ch.qos.logback.core.status:
//            StatusManager, Status, ErrorStatus, InfoStatus, 
//            WarnStatus

public class StatusUtil
{

    public StatusUtil(Context context)
    {
        sm = context.getStatusManager();
    }

    public StatusUtil(StatusManager statusmanager)
    {
        sm = statusmanager;
    }

    public static boolean contextHasStatusListener(Context context)
    {
        boolean flag;
        StatusManager statusmanager;
        flag = false;
        statusmanager = context.getStatusManager();
        if(statusmanager != null) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        List list = statusmanager.getCopyOfStatusListenerList();
        if(list != null && list.size() != 0)
            flag = true;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public static List filterStatusListByTimeThreshold(List list, long l)
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Status status = (Status)iterator.next();
            if(status.getDate().longValue() >= l)
                arraylist.add(status);
        } while(true);
        return arraylist;
    }

    public void addError(Object obj, String s, Throwable throwable)
    {
        addStatus(new ErrorStatus(s, obj, throwable));
    }

    public void addInfo(Object obj, String s)
    {
        addStatus(new InfoStatus(s, obj));
    }

    public void addStatus(Status status)
    {
        if(sm != null)
            sm.add(status);
    }

    public void addWarn(Object obj, String s)
    {
        addStatus(new WarnStatus(s, obj));
    }

    public boolean containsException(Class class1)
    {
        Iterator iterator = sm.getCopyOfStatusList().iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        Throwable throwable = ((Status)iterator.next()).getThrowable();
        if(throwable == null || !throwable.getClass().getName().equals(class1.getName())) goto _L4; else goto _L3
_L3:
        boolean flag = true;
_L6:
        return flag;
_L2:
        flag = false;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public boolean containsMatch(int i, String s)
    {
        return containsMatch(0L, i, s);
    }

    public boolean containsMatch(long l, int i, String s)
    {
        Pattern pattern;
        Iterator iterator;
        List list = filterStatusListByTimeThreshold(sm.getCopyOfStatusList(), l);
        pattern = Pattern.compile(s);
        iterator = list.iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        Status status = (Status)iterator.next();
        if(i != status.getLevel() || !pattern.matcher(status.getMessage()).lookingAt()) goto _L4; else goto _L3
_L3:
        boolean flag = true;
_L6:
        return flag;
_L2:
        flag = false;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public boolean containsMatch(String s)
    {
        Pattern pattern;
        Iterator iterator;
        pattern = Pattern.compile(s);
        iterator = sm.getCopyOfStatusList().iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        if(!pattern.matcher(((Status)iterator.next()).getMessage()).lookingAt()) goto _L4; else goto _L3
_L3:
        boolean flag = true;
_L6:
        return flag;
_L2:
        flag = false;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public int getHighestLevel(long l)
    {
        Iterator iterator = filterStatusListByTimeThreshold(sm.getCopyOfStatusList(), l).iterator();
        int i = 0;
        while(iterator.hasNext()) 
        {
            Status status = (Status)iterator.next();
            int j;
            if(status.getLevel() > i)
                j = status.getLevel();
            else
                j = i;
            i = j;
        }
        return i;
    }

    public boolean hasXMLParsingErrors(long l)
    {
        return containsMatch(l, 2, "XML_PARSING");
    }

    public boolean isErrorFree(long l)
    {
        boolean flag;
        if(2 > getHighestLevel(l))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public int matchCount(String s)
    {
        Pattern pattern = Pattern.compile(s);
        Iterator iterator = sm.getCopyOfStatusList().iterator();
        int i = 0;
        while(iterator.hasNext()) 
        {
            int j;
            if(pattern.matcher(((Status)iterator.next()).getMessage()).lookingAt())
                j = i + 1;
            else
                j = i;
            i = j;
        }
        return i;
    }

    public boolean noXMLParsingErrorsOccurred(long l)
    {
        boolean flag;
        if(!hasXMLParsingErrors(l))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public long timeOfLastReset()
    {
        List list = sm.getCopyOfStatusList();
        if(list != null) goto _L2; else goto _L1
_L1:
        long l = -1L;
_L4:
        return l;
_L2:
        int i = -1 + list.size();
        do
        {
            if(i < 0)
                break;
            Status status = (Status)list.get(i);
            if("Will reset and reconfigure context ".equals(status.getMessage()))
            {
                l = status.getDate().longValue();
                continue; /* Loop/switch isn't completed */
            }
            i--;
        } while(true);
        l = -1L;
        if(true) goto _L4; else goto _L3
_L3:
    }

    StatusManager sm;
}
