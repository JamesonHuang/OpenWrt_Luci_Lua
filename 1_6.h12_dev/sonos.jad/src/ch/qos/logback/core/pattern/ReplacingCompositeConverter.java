// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.pattern;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package ch.qos.logback.core.pattern:
//            CompositeConverter

public class ReplacingCompositeConverter extends CompositeConverter
{

    public ReplacingCompositeConverter()
    {
    }

    public void start()
    {
        List list = getOptionList();
        if(list == null)
        {
            addError("at least two options are expected whereas you have declared none");
        } else
        {
            int i = list.size();
            if(i < 2)
            {
                addError((new StringBuilder()).append("at least two options are expected whereas you have declared only ").append(i).append("as [").append(list).append("]").toString());
            } else
            {
                regex = (String)list.get(0);
                pattern = Pattern.compile(regex);
                replacement = (String)list.get(1);
                super.start();
            }
        }
    }

    protected String transform(Object obj, String s)
    {
        if(started)
            s = pattern.matcher(s).replaceAll(replacement);
        return s;
    }

    Pattern pattern;
    String regex;
    String replacement;
}
