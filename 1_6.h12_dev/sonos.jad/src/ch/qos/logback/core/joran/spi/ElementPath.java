// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.spi;

import java.util.*;

public class ElementPath
{

    public ElementPath()
    {
        partList = new ArrayList();
    }

    public ElementPath(String s)
    {
        partList = new ArrayList();
        if(s != null) goto _L2; else goto _L1
_L1:
        String as[];
        return;
_L2:
        if((as = s.split("/")) != null)
        {
            int i = as.length;
            int j = 0;
            while(j < i) 
            {
                String s1 = as[j];
                if(s1.length() > 0)
                    partList.add(s1);
                j++;
            }
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public ElementPath(List list)
    {
        partList = new ArrayList();
        partList.addAll(list);
    }

    private boolean equalityCheck(String s, String s1)
    {
        return s.equalsIgnoreCase(s1);
    }

    public ElementPath duplicate()
    {
        ElementPath elementpath = new ElementPath();
        elementpath.partList.addAll(partList);
        return elementpath;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj != null && (obj instanceof ElementPath)) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        ElementPath elementpath = (ElementPath)obj;
        if(elementpath.size() != size())
            continue; /* Loop/switch isn't completed */
        int i = size();
        for(int j = 0; j < i; j++)
            if(!equalityCheck(get(j), elementpath.get(j)))
                continue; /* Loop/switch isn't completed */

        flag = true;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public String get(int i)
    {
        return (String)partList.get(i);
    }

    public List getCopyOfPartList()
    {
        return new ArrayList(partList);
    }

    public String peekLast()
    {
        String s;
        if(!partList.isEmpty())
        {
            int i = partList.size();
            s = (String)partList.get(i - 1);
        } else
        {
            s = null;
        }
        return s;
    }

    public void pop()
    {
        if(!partList.isEmpty())
            partList.remove(-1 + partList.size());
    }

    public void push(String s)
    {
        partList.add(s);
    }

    public int size()
    {
        return partList.size();
    }

    protected String toStableString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        String s;
        for(Iterator iterator = partList.iterator(); iterator.hasNext(); stringbuilder.append("[").append(s).append("]"))
            s = (String)iterator.next();

        return stringbuilder.toString();
    }

    public String toString()
    {
        return toStableString();
    }

    ArrayList partList;
}
