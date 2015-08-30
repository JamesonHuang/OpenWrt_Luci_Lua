// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.pattern.parser;


class Token
{

    public Token(int i)
    {
        this(i, null);
    }

    public Token(int i, Object obj)
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
        if(!(obj instanceof Token))
        {
            flag = false;
        } else
        {
            Token token = (Token)obj;
            if(type != token.type)
                flag = false;
            else
            if(value == null ? token.value != null : !value.equals(token.value))
                flag = false;
        }
        if(true) goto _L1; else goto _L3
_L3:
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
        int i = 29 * type;
        int j;
        if(value != null)
            j = value.hashCode();
        else
            j = 0;
        return j + i;
    }

    public String toString()
    {
        type;
        JVM INSTR lookupswitch 7: default 72
    //                   37: 109
    //                   41: 145
    //                   1000: 121
    //                   1002: 115
    //                   1004: 133
    //                   1005: 139
    //                   1006: 127;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L3:
        break MISSING_BLOCK_LABEL_145;
_L1:
        String s = "UNKNOWN";
_L9:
        String s1;
        if(value == null)
            s1 = (new StringBuilder()).append("Token(").append(s).append(")").toString();
        else
            s1 = (new StringBuilder()).append("Token(").append(s).append(", \"").append(value).append("\")").toString();
        return s1;
_L2:
        s = "%";
          goto _L9
_L5:
        s = "FormatModifier";
          goto _L9
_L4:
        s = "LITERAL";
          goto _L9
_L8:
        s = "OPTION";
          goto _L9
_L6:
        s = "SIMPLE_KEYWORD";
          goto _L9
_L7:
        s = "COMPOSITE_KEYWORD";
          goto _L9
        s = "RIGHT_PARENTHESIS";
          goto _L9
    }

    static Token BARE_COMPOSITE_KEYWORD_TOKEN = new Token(1005, "BARE");
    static final int COMPOSITE_KEYWORD = 1005;
    static final int CURLY_LEFT = 123;
    static final int CURLY_RIGHT = 125;
    static final int DOT = 46;
    static final int EOF = 0x7fffffff;
    static Token EOF_TOKEN = new Token(0x7fffffff, "EOF");
    static final int FORMAT_MODIFIER = 1002;
    static final int LITERAL = 1000;
    static final int MINUS = 45;
    static final int OPTION = 1006;
    static final int PERCENT = 37;
    static Token PERCENT_TOKEN = new Token(37);
    static final int RIGHT_PARENTHESIS = 41;
    static Token RIGHT_PARENTHESIS_TOKEN = new Token(41);
    static final int SIMPLE_KEYWORD = 1004;
    private final int type;
    private final Object value;

}
