// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.pattern;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAware;

// Referenced classes of package ch.qos.logback.core.pattern:
//            Converter, CompositeConverter, DynamicConverter

public class ConverterUtil
{

    public ConverterUtil()
    {
    }

    public static Converter findTail(Converter converter)
    {
        do
        {
            Converter converter1;
label0:
            {
                if(converter != null)
                {
                    converter1 = converter.getNext();
                    if(converter1 != null)
                        break label0;
                }
                return converter;
            }
            converter = converter1;
        } while(true);
    }

    public static void setContextForConverters(Context context, Converter converter)
    {
        for(Converter converter1 = converter; converter1 != null; converter1 = converter1.getNext())
            if(converter1 instanceof ContextAware)
                ((ContextAware)converter1).setContext(context);

    }

    public static void startConverters(Converter converter)
    {
        Converter converter1 = converter;
        while(converter1 != null) 
        {
            if(converter1 instanceof CompositeConverter)
            {
                CompositeConverter compositeconverter = (CompositeConverter)converter1;
                startConverters(compositeconverter.childConverter);
                compositeconverter.start();
            } else
            if(converter1 instanceof DynamicConverter)
                ((DynamicConverter)converter1).start();
            converter1 = converter1.getNext();
        }
    }
}
