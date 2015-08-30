// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.util;


// Referenced classes of package ch.qos.logback.core.util:
//            CharSequenceState

class CharSequenceToRegexMapper
{

    CharSequenceToRegexMapper()
    {
    }

    private String number(int i)
    {
        return (new StringBuilder()).append("\\d{").append(i).append("}").toString();
    }

    String toRegex(CharSequenceState charsequencestate)
    {
        int i;
        char c;
        i = charsequencestate.occurrences;
        c = charsequencestate.c;
        charsequencestate.c;
        JVM INSTR lookupswitch 22: default 200
    //                   39: 306
    //                   46: 289
    //                   68: 258
    //                   69: 268
    //                   70: 258
    //                   71: 229
    //                   72: 258
    //                   75: 258
    //                   77: 236
    //                   83: 258
    //                   87: 258
    //                   90: 282
    //                   92: 296
    //                   97: 275
    //                   100: 258
    //                   104: 258
    //                   107: 258
    //                   109: 258
    //                   115: 258
    //                   119: 258
    //                   121: 258
    //                   122: 229;
           goto _L1 _L2 _L3 _L4 _L5 _L4 _L6 _L4 _L4 _L7 _L4 _L4 _L8 _L9 _L10 _L4 _L4 _L4 _L4 _L4 _L4 _L4 _L6
_L1:
        String s;
        if(i == 1)
            s = (new StringBuilder()).append("").append(c).toString();
        else
            s = (new StringBuilder()).append(c).append("{").append(i).append("}").toString();
_L12:
        return s;
_L6:
        s = ".*";
        continue; /* Loop/switch isn't completed */
_L7:
        if(i >= 3)
            s = ".{3,12}";
        else
            s = number(i);
        continue; /* Loop/switch isn't completed */
_L4:
        s = number(i);
        continue; /* Loop/switch isn't completed */
_L5:
        s = ".{2,12}";
        continue; /* Loop/switch isn't completed */
_L10:
        s = ".{2}";
        continue; /* Loop/switch isn't completed */
_L8:
        s = "(\\+|-)\\d{4}";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "\\.";
        continue; /* Loop/switch isn't completed */
_L9:
        throw new IllegalStateException("Forward slashes are not allowed");
_L2:
        if(i == 1)
            s = "";
        else
            throw new IllegalStateException("Too many single quotes");
        if(true) goto _L12; else goto _L11
_L11:
    }
}
