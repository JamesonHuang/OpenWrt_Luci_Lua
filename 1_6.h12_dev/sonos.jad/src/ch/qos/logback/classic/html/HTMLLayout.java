// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.html;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.pattern.MDCConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.html.HTMLLayoutBase;
import ch.qos.logback.core.html.IThrowableRenderer;
import ch.qos.logback.core.pattern.Converter;
import java.util.Map;

// Referenced classes of package ch.qos.logback.classic.html:
//            DefaultThrowableRenderer, DefaultCssBuilder

public class HTMLLayout extends HTMLLayoutBase
{

    public HTMLLayout()
    {
        pattern = "%date%thread%level%logger%mdc%msg";
        throwableRenderer = new DefaultThrowableRenderer();
        cssBuilder = new DefaultCssBuilder();
    }

    private void appendEventToBuffer(StringBuilder stringbuilder, Converter converter, ILoggingEvent iloggingevent)
    {
        stringbuilder.append("<td class=\"");
        stringbuilder.append(computeConverterName(converter));
        stringbuilder.append("\">");
        converter.write(stringbuilder, iloggingevent);
        stringbuilder.append("</td>");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
    }

    protected String computeConverterName(Converter converter)
    {
        String s;
        if(converter instanceof MDCConverter)
        {
            s = ((MDCConverter)converter).getFirstOption();
            if(s == null)
                s = "MDC";
        } else
        {
            s = super.computeConverterName(converter);
        }
        return s;
    }

    public String doLayout(ILoggingEvent iloggingevent)
    {
        StringBuilder stringbuilder = new StringBuilder();
        startNewTableIfLimitReached(stringbuilder);
        boolean flag = true;
        long l = counter;
        counter = l + 1L;
        if((l & 1L) == 0L)
            flag = false;
        String s = iloggingevent.getLevel().toString().toLowerCase();
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        stringbuilder.append("<tr class=\"");
        stringbuilder.append(s);
        if(flag)
            stringbuilder.append(" odd\">");
        else
            stringbuilder.append(" even\">");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        for(Converter converter = head; converter != null; converter = converter.getNext())
            appendEventToBuffer(stringbuilder, converter, iloggingevent);

        stringbuilder.append("</tr>");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        if(iloggingevent.getThrowableProxy() != null)
            throwableRenderer.render(stringbuilder, iloggingevent);
        return stringbuilder.toString();
    }

    public volatile String doLayout(Object obj)
    {
        return doLayout((ILoggingEvent)obj);
    }

    protected Map getDefaultConverterMap()
    {
        return PatternLayout.defaultConverterMap;
    }

    public IThrowableRenderer getThrowableRenderer()
    {
        return throwableRenderer;
    }

    public void setThrowableRenderer(IThrowableRenderer ithrowablerenderer)
    {
        throwableRenderer = ithrowablerenderer;
    }

    public void start()
    {
        boolean flag = false;
        if(throwableRenderer == null)
        {
            addError("ThrowableRender cannot be null.");
            flag = true;
        }
        if(!flag)
            super.start();
    }

    static final String DEFAULT_CONVERSION_PATTERN = "%date%thread%level%logger%mdc%msg";
    IThrowableRenderer throwableRenderer;
}
