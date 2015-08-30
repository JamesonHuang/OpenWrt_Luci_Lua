// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.spi;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package ch.qos.logback.core.joran.spi:
//            ElementPath

public class ElementSelector extends ElementPath
{

    public ElementSelector()
    {
    }

    public ElementSelector(String s)
    {
        super(s);
    }

    public ElementSelector(List list)
    {
        super(list);
    }

    private boolean equalityCheck(String s, String s1)
    {
        return s.equalsIgnoreCase(s1);
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj != null && (obj instanceof ElementSelector)) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        ElementSelector elementselector = (ElementSelector)obj;
        if(elementselector.size() != size())
            continue; /* Loop/switch isn't completed */
        int i = size();
        for(int j = 0; j < i; j++)
            if(!equalityCheck(get(j), elementselector.get(j)))
                continue; /* Loop/switch isn't completed */

        flag = true;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean fullPathMatch(ElementPath elementpath)
    {
        boolean flag = false;
        if(elementpath.size() == size()) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        int i = size();
        for(int j = 0; j < i; j++)
            if(!equalityCheck(get(j), elementpath.get(j)))
                continue; /* Loop/switch isn't completed */

        flag = true;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public int getPrefixMatchLength(ElementPath elementpath)
    {
        int i = 0;
        int l;
        if(elementpath == null)
        {
            l = 0;
        } else
        {
            int j = partList.size();
            int k = elementpath.partList.size();
            if(j == 0 || k == 0)
            {
                l = 0;
            } else
            {
                int i1;
                int j1;
                if(j <= k)
                    i1 = j;
                else
                    i1 = k;
                for(j1 = 0; j1 < i1 && equalityCheck((String)partList.get(j1), (String)elementpath.partList.get(j1));)
                {
                    int k1 = i + 1;
                    j1++;
                    i = k1;
                }

                l = i;
            }
        }
        return l;
    }

    public int getTailMatchLength(ElementPath elementpath)
    {
        int i = 0;
        if(elementpath != null) goto _L2; else goto _L1
_L1:
        return i;
_L2:
        int j = partList.size();
        int k = elementpath.partList.size();
        if(j != 0 && k != 0)
        {
            int l;
            int i1;
            int j1;
            if(j <= k)
                l = j;
            else
                l = k;
            i1 = 1;
            j1 = 0;
            for(; i1 <= l && equalityCheck((String)partList.get(j - i1), (String)elementpath.partList.get(k - i1)); i1++)
                j1++;

            i = j1;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public int hashCode()
    {
        int i = 0;
        int j = size();
        int k = 0;
        for(; i < j; i++)
            k ^= get(i).toLowerCase().hashCode();

        return k;
    }

    public boolean isContainedIn(ElementPath elementpath)
    {
        boolean flag;
        if(elementpath == null)
            flag = false;
        else
            flag = elementpath.toStableString().contains(toStableString());
        return flag;
    }
}
