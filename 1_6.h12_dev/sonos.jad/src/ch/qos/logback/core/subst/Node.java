// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.subst;

import java.io.PrintStream;

public class Node
{
    static final class Type extends Enum
    {

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(ch/qos/logback/core/subst/Node$Type, s);
        }

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        private static final Type $VALUES[];
        public static final Type LITERAL;
        public static final Type VARIABLE;

        static 
        {
            LITERAL = new Type("LITERAL", 0);
            VARIABLE = new Type("VARIABLE", 1);
            Type atype[] = new Type[2];
            atype[0] = LITERAL;
            atype[1] = VARIABLE;
            $VALUES = atype;
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }


    public Node(Type type1, Object obj)
    {
        type = type1;
        payload = obj;
    }

    public Node(Type type1, Object obj, Object obj1)
    {
        type = type1;
        payload = obj;
        defaultPart = obj1;
    }

    void append(Node node)
    {
        if(node != null)
        {
            for(; next != null; this = next);
            next = node;
        }
    }

    public void dump()
    {
        System.out.print(toString());
        System.out.print(" -> ");
        if(next != null)
            next.dump();
        else
            System.out.print(" null");
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this != obj) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(obj == null || getClass() != obj.getClass())
        {
            flag = false;
        } else
        {
            Node node = (Node)obj;
            if(type != node.type)
                flag = false;
            else
            if(payload == null ? node.payload != null : !payload.equals(node.payload))
                flag = false;
            else
            if(defaultPart == null ? node.defaultPart != null : !defaultPart.equals(node.defaultPart))
                flag = false;
            else
            if(next == null ? node.next != null : !next.equals(node.next))
                flag = false;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public int hashCode()
    {
        int i = 0;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        if(type != null)
            j = type.hashCode();
        else
            j = 0;
        k = j * 31;
        if(payload != null)
            l = payload.hashCode();
        else
            l = 0;
        i1 = 31 * (l + k);
        if(defaultPart != null)
            j1 = defaultPart.hashCode();
        else
            j1 = 0;
        k1 = 31 * (j1 + i1);
        if(next != null)
            i = next.hashCode();
        return k1 + i;
    }

    void recursive(Node node, StringBuilder stringbuilder)
    {
        for(; node != null; node = node.next)
            stringbuilder.append(node.toString()).append(" --> ");

        stringbuilder.append("null ");
    }

    public void setNext(Node node)
    {
        next = node;
    }

    public String toString()
    {
        class _cls1
        {

            static final int $SwitchMap$ch$qos$logback$core$subst$Node$Type[];

            static 
            {
                $SwitchMap$ch$qos$logback$core$subst$Node$Type = new int[Type.values().length];
                NoSuchFieldError nosuchfielderror1;
                try
                {
                    $SwitchMap$ch$qos$logback$core$subst$Node$Type[Type.LITERAL.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                $SwitchMap$ch$qos$logback$core$subst$Node$Type[Type.VARIABLE.ordinal()] = 2;
_L2:
                return;
                nosuchfielderror1;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.ch.qos.logback.core.subst.Node.Type[type.ordinal()];
        JVM INSTR tableswitch 1 2: default 32
    //                   1 38
    //                   2 82;
           goto _L1 _L2 _L3
_L1:
        String s1 = null;
_L5:
        return s1;
_L2:
        s1 = (new StringBuilder()).append("Node{type=").append(type).append(", payload='").append(payload).append("'}").toString();
        continue; /* Loop/switch isn't completed */
_L3:
        StringBuilder stringbuilder = new StringBuilder();
        StringBuilder stringbuilder1 = new StringBuilder();
        if(defaultPart != null)
            recursive((Node)defaultPart, stringbuilder1);
        recursive((Node)payload, stringbuilder);
        String s = (new StringBuilder()).append("Node{type=").append(type).append(", payload='").append(stringbuilder.toString()).append("'").toString();
        if(defaultPart != null)
            s = (new StringBuilder()).append(s).append(", defaultPart=").append(stringbuilder1.toString()).toString();
        s1 = (new StringBuilder()).append(s).append('}').toString();
        if(true) goto _L5; else goto _L4
_L4:
    }

    Object defaultPart;
    Node next;
    Object payload;
    Type type;
}
