// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.ILoggingEvent;

// Referenced classes of package ch.qos.logback.classic.pattern:
//            ClassicConverter, Abbreviator, ClassNameOnlyAbbreviator, TargetLengthBasedClassNameAbbreviator

public abstract class NamedConverter extends ClassicConverter
{

    public NamedConverter()
    {
        abbreviator = null;
    }

    public String convert(ILoggingEvent iloggingevent)
    {
        String s = getFullyQualifiedName(iloggingevent);
        if(abbreviator != null)
            s = abbreviator.abbreviate(s);
        return s;
    }

    public volatile String convert(Object obj)
    {
        return convert((ILoggingEvent)obj);
    }

    protected abstract String getFullyQualifiedName(ILoggingEvent iloggingevent);

    public void start()
    {
        String s = getFirstOption();
        if(s != null)
            try
            {
                int i = Integer.parseInt(s);
                if(i == 0)
                    abbreviator = new ClassNameOnlyAbbreviator();
                else
                if(i > 0)
                    abbreviator = new TargetLengthBasedClassNameAbbreviator(i);
            }
            catch(NumberFormatException numberformatexception) { }
    }

    Abbreviator abbreviator;
}
