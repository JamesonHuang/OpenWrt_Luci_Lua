// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCollectionUtil
{

    public StringCollectionUtil()
    {
    }

    public static void removeMatching(Collection collection, Collection collection1)
    {
        ArrayList arraylist = new ArrayList(collection.size());
        for(Iterator iterator = collection1.iterator(); iterator.hasNext();)
        {
            Pattern pattern = Pattern.compile((String)iterator.next());
            Iterator iterator1 = collection.iterator();
            while(iterator1.hasNext()) 
            {
                String s = (String)iterator1.next();
                if(pattern.matcher(s).matches())
                    arraylist.add(s);
            }
        }

        collection.removeAll(arraylist);
    }

    public static transient void removeMatching(Collection collection, String as[])
    {
        removeMatching(collection, ((Collection) (Arrays.asList(as))));
    }

    public static void retainMatching(Collection collection, Collection collection1)
    {
        if(!collection1.isEmpty()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        ArrayList arraylist = new ArrayList(collection.size());
        for(Iterator iterator = collection1.iterator(); iterator.hasNext();)
        {
            Pattern pattern = Pattern.compile((String)iterator.next());
            Iterator iterator1 = collection.iterator();
            while(iterator1.hasNext()) 
            {
                String s = (String)iterator1.next();
                if(pattern.matcher(s).matches())
                    arraylist.add(s);
            }
        }

        collection.retainAll(arraylist);
        if(true) goto _L1; else goto _L3
_L3:
    }

    public static transient void retainMatching(Collection collection, String as[])
    {
        retainMatching(collection, ((Collection) (Arrays.asList(as))));
    }
}
