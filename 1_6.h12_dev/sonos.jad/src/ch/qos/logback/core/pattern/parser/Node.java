// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.pattern.parser;


public class Node
{

    Node(int i)
    {
        this(i, null);
    }

    Node(int i, Object obj)
    {
        type = i;
        value = obj;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this != obj) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(!(obj instanceof Node))
        {
            flag = false;
        } else
        {
            Node node = (Node)obj;
            if(type != node.type || (value == null ? node.value != null : !value.equals(node.value)) || (next == null ? node.next != null : !next.equals(node.next)))
                flag = false;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public Node getNext()
    {
        return next;
    }

    public int getType()
    {
        return type;
    }

    public Object getValue()
    {
        return value;
    }

    public int hashCode()
    {
        int i = 31 * type;
        int j;
        if(value != null)
            j = value.hashCode();
        else
            j = 0;
        return j + i;
    }

    String printNext()
    {
        String s;
        if(next != null)
            s = (new StringBuilder()).append(" -> ").append(next).toString();
        else
            s = "";
        return s;
    }

    public void setNext(Node node)
    {
        next = node;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        type;
        JVM INSTR tableswitch 0 0: default 32
    //                   0 55;
           goto _L1 _L2
_L1:
        stringbuffer.append(super.toString());
_L4:
        stringbuffer.append(printNext());
        return stringbuffer.toString();
_L2:
        stringbuffer.append((new StringBuilder()).append("LITERAL(").append(value).append(")").toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    static final int COMPOSITE_KEYWORD = 2;
    static final int LITERAL = 0;
    static final int SIMPLE_KEYWORD = 1;
    Node next;
    final int type;
    final Object value;
}
