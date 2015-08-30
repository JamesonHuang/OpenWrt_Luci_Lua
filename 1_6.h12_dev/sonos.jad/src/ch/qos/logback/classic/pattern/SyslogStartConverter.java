// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.util.LevelToSyslogSeverity;
import ch.qos.logback.core.net.SyslogAppenderBase;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// Referenced classes of package ch.qos.logback.classic.pattern:
//            ClassicConverter

public class SyslogStartConverter extends ClassicConverter
{

    public SyslogStartConverter()
    {
        lastTimestamp = -1L;
        timesmapStr = null;
    }

    String computeTimeStampString(long l)
    {
        this;
        JVM INSTR monitorenter ;
        if(l != lastTimestamp)
        {
            lastTimestamp = l;
            timesmapStr = simpleFormat.format(new Date(l));
        }
        String s = timesmapStr;
        return s;
    }

    public String convert(ILoggingEvent iloggingevent)
    {
        StringBuilder stringbuilder = new StringBuilder();
        int i = facility + LevelToSyslogSeverity.convert(iloggingevent);
        stringbuilder.append("<");
        stringbuilder.append(i);
        stringbuilder.append(">");
        stringbuilder.append(computeTimeStampString(iloggingevent.getTimeStamp()));
        stringbuilder.append(' ');
        stringbuilder.append("localhost");
        stringbuilder.append(' ');
        return stringbuilder.toString();
    }

    public volatile String convert(Object obj)
    {
        return convert((ILoggingEvent)obj);
    }

    public void start()
    {
        boolean flag;
        String s;
        flag = false;
        s = getFirstOption();
        if(s != null) goto _L2; else goto _L1
_L1:
        addError("was expecting a facility string as an option");
_L4:
        return;
_L2:
        facility = SyslogAppenderBase.facilityStringToint(s);
        try
        {
            simpleFormat = new SimpleDateFormat("MMM dd HH:mm:ss", new DateFormatSymbols(Locale.US));
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            addError("Could not instantiate SimpleDateFormat", illegalargumentexception);
            flag = true;
        }
        if(!flag)
            super.start();
        if(true) goto _L4; else goto _L3
_L3:
    }

    int facility;
    long lastTimestamp;
    final String localHostName = "localhost";
    SimpleDateFormat simpleFormat;
    String timesmapStr;
}
