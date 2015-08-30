// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.sift;

import ch.qos.logback.core.sift.AbstractAppenderFactoryUsingJoran;
import ch.qos.logback.core.sift.SiftingJoranConfiguratorBase;
import java.util.List;
import java.util.Map;

// Referenced classes of package ch.qos.logback.classic.sift:
//            SiftingJoranConfigurator

public class AppenderFactoryUsingJoran extends AbstractAppenderFactoryUsingJoran
{

    AppenderFactoryUsingJoran(List list, String s, Map map)
    {
        super(list, s, map);
    }

    public SiftingJoranConfiguratorBase getSiftingJoranConfigurator(String s)
    {
        return new SiftingJoranConfigurator(key, s, parentPropertyMap);
    }
}
