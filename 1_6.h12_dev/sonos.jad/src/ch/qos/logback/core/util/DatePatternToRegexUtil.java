// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.util;

import java.util.*;

// Referenced classes of package ch.qos.logback.core.util:
//            CharSequenceToRegexMapper, CharSequenceState

public class DatePatternToRegexUtil
{

    public DatePatternToRegexUtil(String s)
    {
        datePattern = s;
        datePatternLength = s.length();
    }

    private List tokenize()
    {
        ArrayList arraylist = new ArrayList();
        CharSequenceState charsequencestate = null;
        int i = 0;
        while(i < datePatternLength) 
        {
            char c = datePattern.charAt(i);
            if(charsequencestate == null || charsequencestate.c != c)
            {
                charsequencestate = new CharSequenceState(c);
                arraylist.add(charsequencestate);
            } else
            {
                charsequencestate.incrementOccurrences();
            }
            i++;
        }
        return arraylist;
    }

    public String toRegex()
    {
        List list = tokenize();
        StringBuilder stringbuilder = new StringBuilder();
        CharSequenceState charsequencestate;
        for(Iterator iterator = list.iterator(); iterator.hasNext(); stringbuilder.append(regexMapper.toRegex(charsequencestate)))
            charsequencestate = (CharSequenceState)iterator.next();

        return stringbuilder.toString();
    }

    final String datePattern;
    final int datePatternLength;
    final CharSequenceToRegexMapper regexMapper = new CharSequenceToRegexMapper();
}
