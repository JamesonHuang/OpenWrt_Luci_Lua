// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.html;

import ch.qos.logback.core.*;
import ch.qos.logback.core.pattern.Converter;
import ch.qos.logback.core.pattern.ConverterUtil;
import ch.qos.logback.core.pattern.parser.Parser;
import ch.qos.logback.core.spi.ScanException;
import java.util.*;

// Referenced classes of package ch.qos.logback.core.html:
//            CssBuilder

public abstract class HTMLLayoutBase extends LayoutBase
{

    public HTMLLayoutBase()
    {
        title = "Logback Log Messages";
        counter = 0L;
    }

    private void buildHeaderRowForTable(StringBuilder stringbuilder)
    {
        Converter converter = head;
        stringbuilder.append("<tr class=\"header\">");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        while(converter != null) 
            if(computeConverterName(converter) == null)
            {
                converter = converter.getNext();
            } else
            {
                stringbuilder.append("<td class=\"");
                stringbuilder.append(computeConverterName(converter));
                stringbuilder.append("\">");
                stringbuilder.append(computeConverterName(converter));
                stringbuilder.append("</td>");
                stringbuilder.append(CoreConstants.LINE_SEPARATOR);
                converter = converter.getNext();
            }
        stringbuilder.append("</tr>");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
    }

    protected String computeConverterName(Converter converter)
    {
        String s = converter.getClass().getSimpleName();
        int i = s.indexOf("Converter");
        if(i != -1)
            s = s.substring(0, i);
        return s;
    }

    public String getContentType()
    {
        return "text/html";
    }

    public CssBuilder getCssBuilder()
    {
        return cssBuilder;
    }

    protected abstract Map getDefaultConverterMap();

    public Map getEffectiveConverterMap()
    {
        HashMap hashmap = new HashMap();
        Map map = getDefaultConverterMap();
        if(map != null)
            hashmap.putAll(map);
        Context context = getContext();
        if(context != null)
        {
            Map map1 = (Map)context.getObject("PATTERN_RULE_REGISTRY");
            if(map1 != null)
                hashmap.putAll(map1);
        }
        return hashmap;
    }

    public String getFileFooter()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        stringbuilder.append("</body></html>");
        return stringbuilder.toString();
    }

    public String getFileHeader()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"");
        stringbuilder.append(" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        stringbuilder.append("<html>");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        stringbuilder.append("  <head>");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        stringbuilder.append("    <title>");
        stringbuilder.append(title);
        stringbuilder.append("</title>");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        cssBuilder.addCss(stringbuilder);
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        stringbuilder.append("  </head>");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        stringbuilder.append("<body>");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        return stringbuilder.toString();
    }

    public String getPattern()
    {
        return pattern;
    }

    public String getPresentationFooter()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("</table>");
        return stringbuilder.toString();
    }

    public String getPresentationHeader()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("<hr/>");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        stringbuilder.append("<p>Log session start time ");
        stringbuilder.append(new Date());
        stringbuilder.append("</p><p></p>");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        stringbuilder.append("<table cellspacing=\"0\">");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        buildHeaderRowForTable(stringbuilder);
        return stringbuilder.toString();
    }

    public String getTitle()
    {
        return title;
    }

    public void setCssBuilder(CssBuilder cssbuilder)
    {
        cssBuilder = cssbuilder;
    }

    public void setPattern(String s)
    {
        pattern = s;
    }

    public void setTitle(String s)
    {
        title = s;
    }

    public void start()
    {
        boolean flag = false;
        try
        {
            Parser parser = new Parser(pattern);
            parser.setContext(getContext());
            head = parser.compile(parser.parse(), getEffectiveConverterMap());
            ConverterUtil.startConverters(head);
        }
        catch(ScanException scanexception)
        {
            addError("Incorrect pattern found", scanexception);
            flag = true;
        }
        if(!flag)
            super.started = true;
    }

    protected void startNewTableIfLimitReached(StringBuilder stringbuilder)
    {
        if(counter >= 10000L)
        {
            counter = 0L;
            stringbuilder.append("</table>");
            stringbuilder.append(CoreConstants.LINE_SEPARATOR);
            stringbuilder.append("<p></p>");
            stringbuilder.append("<table cellspacing=\"0\">");
            stringbuilder.append(CoreConstants.LINE_SEPARATOR);
            buildHeaderRowForTable(stringbuilder);
        }
    }

    protected long counter;
    protected CssBuilder cssBuilder;
    protected Converter head;
    protected String pattern;
    protected String title;
}
