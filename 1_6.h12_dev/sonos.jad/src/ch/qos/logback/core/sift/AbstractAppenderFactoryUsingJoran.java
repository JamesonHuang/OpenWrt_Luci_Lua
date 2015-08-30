// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.sift;

import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.spi.JoranException;
import java.util.List;
import java.util.Map;

// Referenced classes of package ch.qos.logback.core.sift:
//            AppenderFactory, SiftingJoranConfiguratorBase

public abstract class AbstractAppenderFactoryUsingJoran
    implements AppenderFactory
{

    protected AbstractAppenderFactoryUsingJoran(List list, String s, Map map)
    {
        eventList = removeSiftElement(list);
        key = s;
        parentPropertyMap = map;
    }

    public Appender buildAppender(Context context, String s)
        throws JoranException
    {
        SiftingJoranConfiguratorBase siftingjoranconfiguratorbase = getSiftingJoranConfigurator(s);
        siftingjoranconfiguratorbase.setContext(context);
        siftingjoranconfiguratorbase.doConfigure(eventList);
        return siftingjoranconfiguratorbase.getAppender();
    }

    public List getEventList()
    {
        return eventList;
    }

    public abstract SiftingJoranConfiguratorBase getSiftingJoranConfigurator(String s);

    List removeSiftElement(List list)
    {
        return list.subList(1, -1 + list.size());
    }

    final List eventList;
    protected String key;
    protected Map parentPropertyMap;
}
