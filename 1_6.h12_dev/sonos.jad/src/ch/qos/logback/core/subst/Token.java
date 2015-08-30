// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.subst;


public class Token
{
    public static final class Type extends Enum
    {

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(ch/qos/logback/core/subst/Token$Type, s);
        }

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        private static final Type $VALUES[];
        public static final Type CURLY_LEFT;
        public static final Type CURLY_RIGHT;
        public static final Type DEFAULT;
        public static final Type LITERAL;
        public static final Type START;

        static 
        {
            LITERAL = new Type("LITERAL", 0);
            START = new Type("START", 1);
            CURLY_LEFT = new Type("CURLY_LEFT", 2);
            CURLY_RIGHT = new Type("CURLY_RIGHT", 3);
            DEFAULT = new Type("DEFAULT", 4);
            Type atype[] = new Type[5];
            atype[0] = LITERAL;
            atype[1] = START;
            atype[2] = CURLY_LEFT;
            atype[3] = CURLY_RIGHT;
            atype[4] = DEFAULT;
            $VALUES = atype;
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }


    public Token(Type type1, String s)
    {
        type = type1;
        payload = s;
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
            Token token = (Token)obj;
            if(type != token.type)
                flag = false;
            else
            if(payload == null ? token.payload != null : !payload.equals(token.payload))
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
        if(type != null)
            j = type.hashCode();
        else
            j = 0;
        k = j * 31;
        if(payload != null)
            i = payload.hashCode();
        return k + i;
    }

    public String toString()
    {
        String s = (new StringBuilder()).append("Token{type=").append(type).toString();
        if(payload != null)
            s = (new StringBuilder()).append(s).append(", payload='").append(payload).append('\'').toString();
        return (new StringBuilder()).append(s).append('}').toString();
    }

    public static final Token CURLY_LEFT_TOKEN;
    public static final Token CURLY_RIGHT_TOKEN;
    public static final Token DEFAULT_SEP_TOKEN;
    public static final Token START_TOKEN;
    String payload;
    Type type;

    static 
    {
        START_TOKEN = new Token(Type.START, null);
        CURLY_LEFT_TOKEN = new Token(Type.CURLY_LEFT, null);
        CURLY_RIGHT_TOKEN = new Token(Type.CURLY_RIGHT, null);
        DEFAULT_SEP_TOKEN = new Token(Type.DEFAULT, null);
    }
}
