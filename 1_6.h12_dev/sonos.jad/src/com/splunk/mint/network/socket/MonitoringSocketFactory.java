// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint.network.socket;

import com.splunk.mint.Logger;
import com.splunk.mint.network.MonitorRegistry;
import java.net.SocketImpl;
import java.net.SocketImplFactory;

// Referenced classes of package com.splunk.mint.network.socket:
//            MonitoringSocketImpl

public class MonitoringSocketFactory
    implements SocketImplFactory
{

    public MonitoringSocketFactory(MonitorRegistry monitorregistry)
    {
        registry = monitorregistry;
    }

    public SocketImpl createSocketImpl()
    {
        MonitoringSocketImpl monitoringsocketimpl;
        try
        {
            monitoringsocketimpl = new MonitoringSocketImpl(registry);
        }
        catch(Exception exception)
        {
            Logger.logError("Could not create the Network Monitoring implementation, Network monitoring will be disabled.");
            throw new RuntimeException("Could not create the Network Monitoring implementation, Network monitoring will be disabled.");
        }
        return monitoringsocketimpl;
    }

    private final MonitorRegistry registry;
}
