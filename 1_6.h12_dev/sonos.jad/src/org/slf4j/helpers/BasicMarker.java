// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.slf4j.helpers;

import java.util.*;
import org.slf4j.Marker;

public class BasicMarker
    implements Marker
{

    BasicMarker(String s)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("A marker name cannot be null");
        } else
        {
            name = s;
            return;
        }
    }

    /**
     * @deprecated Method add is deprecated
     */

    public void add(Marker marker)
    {
        this;
        JVM INSTR monitorenter ;
        if(marker != null)
            break MISSING_BLOCK_LABEL_23;
        throw new IllegalArgumentException("A null value cannot be added to a Marker as reference.");
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        boolean flag = contains(marker);
        if(!flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(marker.contains(this)) goto _L1; else goto _L3
_L3:
        if(refereceList == null)
            refereceList = new Vector();
        refereceList.add(marker);
          goto _L1
    }

    public boolean contains(String s)
    {
        boolean flag;
        flag = true;
        if(s == null)
            throw new IllegalArgumentException("Other cannot be null");
        if(!name.equals(s)) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(hasReferences())
        {
            for(int i = 0; i < refereceList.size(); i++)
                if(((Marker)refereceList.get(i)).contains(s))
                    continue; /* Loop/switch isn't completed */

        }
        flag = false;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean contains(Marker marker)
    {
        boolean flag;
        flag = true;
        if(marker == null)
            throw new IllegalArgumentException("Other cannot be null");
        if(!equals(marker)) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(hasReferences())
        {
            for(int i = 0; i < refereceList.size(); i++)
                if(((Marker)refereceList.get(i)).contains(marker))
                    continue; /* Loop/switch isn't completed */

        }
        flag = false;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(this != obj) goto _L2; else goto _L1
_L1:
        flag = true;
_L4:
        return flag;
_L2:
        if(obj != null && (obj instanceof Marker))
        {
            Marker marker = (Marker)obj;
            flag = name.equals(marker.getName());
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getName()
    {
        return name;
    }

    public boolean hasChildren()
    {
        return hasReferences();
    }

    /**
     * @deprecated Method hasReferences is deprecated
     */

    public boolean hasReferences()
    {
        this;
        JVM INSTR monitorenter ;
        if(refereceList == null) goto _L2; else goto _L1
_L1:
        int i = refereceList.size();
        if(i <= 0) goto _L2; else goto _L3
_L3:
        boolean flag = true;
_L5:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
        Exception exception;
        exception;
        throw exception;
    }

    public int hashCode()
    {
        return name.hashCode();
    }

    /**
     * @deprecated Method iterator is deprecated
     */

    public Iterator iterator()
    {
        this;
        JVM INSTR monitorenter ;
        if(refereceList == null) goto _L2; else goto _L1
_L1:
        Iterator iterator3 = refereceList.iterator();
        Iterator iterator2 = iterator3;
_L4:
        this;
        JVM INSTR monitorexit ;
        return iterator2;
_L2:
        Iterator iterator1 = Collections.EMPTY_LIST.iterator();
        iterator2 = iterator1;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method remove is deprecated
     */

    public boolean remove(Marker marker)
    {
        boolean flag = false;
        this;
        JVM INSTR monitorenter ;
        List list = refereceList;
        if(list != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        int i;
        int j;
        i = refereceList.size();
        j = 0;
_L3:
        if(j >= i)
            continue; /* Loop/switch isn't completed */
        if(!marker.equals((Marker)refereceList.get(j)))
            break MISSING_BLOCK_LABEL_80;
        refereceList.remove(j);
        flag = true;
        continue; /* Loop/switch isn't completed */
        j++;
          goto _L3
        Exception exception;
        exception;
        throw exception;
        if(true) goto _L1; else goto _L4
_L4:
    }

    public String toString()
    {
        String s;
        if(!hasReferences())
        {
            s = getName();
        } else
        {
            Iterator iterator1 = iterator();
            StringBuffer stringbuffer = new StringBuffer(getName());
            stringbuffer.append(' ').append(OPEN);
            do
            {
                if(!iterator1.hasNext())
                    break;
                stringbuffer.append(((Marker)iterator1.next()).getName());
                if(iterator1.hasNext())
                    stringbuffer.append(SEP);
            } while(true);
            stringbuffer.append(CLOSE);
            s = stringbuffer.toString();
        }
        return s;
    }

    private static String CLOSE = " ]";
    private static String OPEN = "[ ";
    private static String SEP = ", ";
    private static final long serialVersionUID = 0x1908ed5291b507e7L;
    private final String name;
    private List refereceList;

}
