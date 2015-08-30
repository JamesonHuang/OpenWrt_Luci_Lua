// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling.helper;

import ch.qos.logback.core.pattern.DynamicConverter;
import ch.qos.logback.core.util.CachingDateFormatter;
import ch.qos.logback.core.util.DatePatternToRegexUtil;
import java.util.Date;
import java.util.List;

// Referenced classes of package ch.qos.logback.core.rolling.helper:
//            MonoTypedConverter

public class DateTokenConverter extends DynamicConverter
    implements MonoTypedConverter
{

    public DateTokenConverter()
    {
        primary = true;
    }

    public String convert(Object obj)
    {
        if(obj == null)
            throw new IllegalArgumentException("Null argument forbidden");
        if(obj instanceof Date)
            return convert((Date)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Cannot convert ").append(obj).append(" of type").append(obj.getClass().getName()).toString());
    }

    public String convert(Date date)
    {
        return cdf.format(date.getTime());
    }

    public String getDatePattern()
    {
        return datePattern;
    }

    public boolean isApplicable(Object obj)
    {
        return obj instanceof Date;
    }

    public boolean isPrimary()
    {
        return primary;
    }

    public void start()
    {
        datePattern = getFirstOption();
        if(datePattern == null)
            datePattern = "yyyy-MM-dd";
        List list = getOptionList();
        if(list != null && list.size() > 1 && "AUX".equalsIgnoreCase((String)list.get(1)))
            primary = false;
        cdf = new CachingDateFormatter(datePattern);
    }

    public String toRegex()
    {
        return (new DatePatternToRegexUtil(datePattern)).toRegex();
    }

    public static final String AUXILIARY_TOKEN = "AUX";
    public static final String CONVERTER_KEY = "d";
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private CachingDateFormatter cdf;
    private String datePattern;
    private boolean primary;
}
