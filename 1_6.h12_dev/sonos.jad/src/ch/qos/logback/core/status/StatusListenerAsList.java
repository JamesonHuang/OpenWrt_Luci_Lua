// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.status;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package ch.qos.logback.core.status:
//            StatusListener, Status

public class StatusListenerAsList
    implements StatusListener
{

    public StatusListenerAsList()
    {
        statusList = new ArrayList();
    }

    public void addStatusEvent(Status status)
    {
        statusList.add(status);
    }

    public List getStatusList()
    {
        return statusList;
    }

    List statusList;
}
