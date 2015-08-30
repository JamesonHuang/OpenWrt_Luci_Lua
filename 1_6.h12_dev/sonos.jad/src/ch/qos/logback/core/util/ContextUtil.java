// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.util;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAwareBase;
import java.util.*;

public class ContextUtil extends ContextAwareBase
{

    public ContextUtil(Context context)
    {
        setContext(context);
    }

    public void addHostNameAsProperty()
    {
        context.putProperty("HOSTNAME", "localhost");
    }

    public void addProperties(Properties properties)
    {
        if(properties != null)
        {
            Iterator iterator = properties.keySet().iterator();
            while(iterator.hasNext()) 
            {
                String s = (String)iterator.next();
                context.putProperty(s, properties.getProperty(s));
            }
        }
    }
}
