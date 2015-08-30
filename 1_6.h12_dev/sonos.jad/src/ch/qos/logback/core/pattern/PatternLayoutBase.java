// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.pattern;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.LayoutBase;
import ch.qos.logback.core.pattern.parser.Parser;
import ch.qos.logback.core.spi.ScanException;
import ch.qos.logback.core.status.ErrorStatus;
import ch.qos.logback.core.status.StatusManager;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package ch.qos.logback.core.pattern:
//            ConverterUtil, PostCompileProcessor, Converter

public abstract class PatternLayoutBase extends LayoutBase
{

    public PatternLayoutBase()
    {
        instanceConverterMap = new HashMap();
        outputPatternAsHeader = false;
    }

    public abstract Map getDefaultConverterMap();

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
        hashmap.putAll(instanceConverterMap);
        return hashmap;
    }

    public Map getInstanceConverterMap()
    {
        return instanceConverterMap;
    }

    public String getPattern()
    {
        return pattern;
    }

    public String getPresentationHeader()
    {
        String s;
        if(outputPatternAsHeader)
            s = (new StringBuilder()).append(getPresentationHeaderPrefix()).append(pattern).toString();
        else
            s = super.getPresentationHeader();
        return s;
    }

    protected String getPresentationHeaderPrefix()
    {
        return "";
    }

    public boolean isOutputPatternAsHeader()
    {
        return outputPatternAsHeader;
    }

    protected void setContextForConverters(Converter converter)
    {
        ConverterUtil.setContextForConverters(getContext(), converter);
    }

    public void setOutputPatternAsHeader(boolean flag)
    {
        outputPatternAsHeader = flag;
    }

    public void setPattern(String s)
    {
        pattern = s;
    }

    public void setPostCompileProcessor(PostCompileProcessor postcompileprocessor)
    {
        postCompileProcessor = postcompileprocessor;
    }

    public void start()
    {
        if(pattern == null || pattern.length() == 0)
            addError("Empty or null pattern.");
        else
            try
            {
                Parser parser = new Parser(pattern);
                if(getContext() != null)
                    parser.setContext(getContext());
                head = parser.compile(parser.parse(), getEffectiveConverterMap());
                if(postCompileProcessor != null)
                    postCompileProcessor.process(head);
                ConverterUtil.setContextForConverters(getContext(), head);
                ConverterUtil.startConverters(head);
                super.start();
            }
            catch(ScanException scanexception)
            {
                getContext().getStatusManager().add(new ErrorStatus((new StringBuilder()).append("Failed to parse pattern \"").append(getPattern()).append("\".").toString(), this, scanexception));
            }
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getName()).append("(\"").append(getPattern()).append("\")").toString();
    }

    protected String writeLoopOnConverters(Object obj)
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        for(Converter converter = head; converter != null; converter = converter.getNext())
            converter.write(stringbuilder, obj);

        return stringbuilder.toString();
    }

    Converter head;
    Map instanceConverterMap;
    protected boolean outputPatternAsHeader;
    String pattern;
    protected PostCompileProcessor postCompileProcessor;
}
