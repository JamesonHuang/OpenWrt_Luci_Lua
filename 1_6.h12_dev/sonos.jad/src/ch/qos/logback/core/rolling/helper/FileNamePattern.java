// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling.helper;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.pattern.*;
import ch.qos.logback.core.pattern.parser.Parser;
import ch.qos.logback.core.pattern.util.AlmostAsIsEscapeUtil;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.ScanException;
import java.util.*;

// Referenced classes of package ch.qos.logback.core.rolling.helper:
//            IntegerTokenConverter, DateTokenConverter, FileFilterUtil, MonoTypedConverter

public class FileNamePattern extends ContextAwareBase
{

    public FileNamePattern(String s, Context context)
    {
        setPattern(FileFilterUtil.slashify(s));
        setContext(context);
        parse();
        ConverterUtil.startConverters(headTokenConverter);
    }

    public String convert(Object obj)
    {
        StringBuilder stringbuilder = new StringBuilder();
        for(Converter converter = headTokenConverter; converter != null; converter = converter.getNext())
            stringbuilder.append(converter.convert(obj));

        return stringbuilder.toString();
    }

    public String convertInt(int i)
    {
        return convert(Integer.valueOf(i));
    }

    public transient String convertMultipleArguments(Object aobj[])
    {
        StringBuilder stringbuilder = new StringBuilder();
label0:
        for(Converter converter = headTokenConverter; converter != null; converter = converter.getNext())
        {
            if(converter instanceof MonoTypedConverter)
            {
                MonoTypedConverter monotypedconverter = (MonoTypedConverter)converter;
                int i = aobj.length;
                int j = 0;
                do
                {
                    if(j >= i)
                        continue label0;
                    Object obj = aobj[j];
                    if(monotypedconverter.isApplicable(obj))
                        stringbuilder.append(converter.convert(obj));
                    j++;
                } while(true);
            }
            stringbuilder.append(converter.convert(((Object) (aobj))));
        }

        return stringbuilder.toString();
    }

    String escapeRightParantesis(String s)
    {
        return pattern.replace(")", "\\)");
    }

    public IntegerTokenConverter getIntegerTokenConverter()
    {
        Converter converter = headTokenConverter;
_L3:
        if(converter == null)
            break MISSING_BLOCK_LABEL_31;
        if(!(converter instanceof IntegerTokenConverter)) goto _L2; else goto _L1
_L1:
        IntegerTokenConverter integertokenconverter = (IntegerTokenConverter)converter;
_L4:
        return integertokenconverter;
_L2:
        converter = converter.getNext();
          goto _L3
        integertokenconverter = null;
          goto _L4
    }

    public String getPattern()
    {
        return pattern;
    }

    public DateTokenConverter getPrimaryDateTokenConverter()
    {
        Converter converter = headTokenConverter;
_L3:
        if(converter == null) goto _L2; else goto _L1
_L1:
        DateTokenConverter datetokenconverter;
        if(!(converter instanceof DateTokenConverter))
            continue; /* Loop/switch isn't completed */
        datetokenconverter = (DateTokenConverter)converter;
        if(!datetokenconverter.isPrimary())
            continue; /* Loop/switch isn't completed */
_L4:
        return datetokenconverter;
        converter = converter.getNext();
          goto _L3
_L2:
        datetokenconverter = null;
          goto _L4
    }

    void parse()
    {
        Parser parser = new Parser(escapeRightParantesis(pattern), new AlmostAsIsEscapeUtil());
        parser.setContext(context);
        headTokenConverter = parser.compile(parser.parse(), CONVERTER_MAP);
_L1:
        return;
        ScanException scanexception;
        scanexception;
        addError((new StringBuilder()).append("Failed to parse pattern \"").append(pattern).append("\".").toString(), scanexception);
          goto _L1
    }

    public void setPattern(String s)
    {
        if(s != null)
            pattern = s.trim();
    }

    public String toRegex()
    {
        StringBuilder stringbuilder = new StringBuilder();
        Converter converter = headTokenConverter;
        while(converter != null) 
        {
            if(converter instanceof LiteralConverter)
                stringbuilder.append(converter.convert(null));
            else
            if(converter instanceof IntegerTokenConverter)
                stringbuilder.append("\\d{1,2}");
            else
            if(converter instanceof DateTokenConverter)
                stringbuilder.append(((DateTokenConverter)converter).toRegex());
            converter = converter.getNext();
        }
        return stringbuilder.toString();
    }

    public String toRegexForFixedDate(Date date)
    {
        StringBuilder stringbuilder = new StringBuilder();
        Converter converter = headTokenConverter;
        while(converter != null) 
        {
            if(converter instanceof LiteralConverter)
                stringbuilder.append(converter.convert(null));
            else
            if(converter instanceof IntegerTokenConverter)
                stringbuilder.append("(\\d{1,3})");
            else
            if(converter instanceof DateTokenConverter)
                stringbuilder.append(converter.convert(date));
            converter = converter.getNext();
        }
        return stringbuilder.toString();
    }

    public String toString()
    {
        return pattern;
    }

    static final Map CONVERTER_MAP;
    Converter headTokenConverter;
    String pattern;

    static 
    {
        CONVERTER_MAP = new HashMap();
        CONVERTER_MAP.put("i", ch/qos/logback/core/rolling/helper/IntegerTokenConverter.getName());
        CONVERTER_MAP.put("d", ch/qos/logback/core/rolling/helper/DateTokenConverter.getName());
    }
}
