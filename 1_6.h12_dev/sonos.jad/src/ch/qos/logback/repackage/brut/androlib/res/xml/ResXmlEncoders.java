// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.repackage.brut.androlib.res.xml;

import java.util.*;

public final class ResXmlEncoders
{

    public ResXmlEncoders()
    {
    }

    public static String encodeAsResXmlAttr(String s)
    {
        if(!s.isEmpty()) goto _L2; else goto _L1
_L1:
        return s;
_L2:
        char ac[];
        StringBuilder stringbuilder;
        ac = s.toCharArray();
        stringbuilder = new StringBuilder(10 + s.length());
        ac[0];
        JVM INSTR lookupswitch 3: default 68
    //                   35: 165
    //                   63: 165
    //                   64: 165;
           goto _L3 _L4 _L4 _L4
_L3:
        int i;
        int j;
        i = ac.length;
        j = 0;
_L13:
        if(j >= i) goto _L6; else goto _L5
_L5:
        char c = ac[j];
        c;
        JVM INSTR lookupswitch 3: default 124
    //                   10: 202
    //                   34: 192
    //                   92: 175;
           goto _L7 _L8 _L9 _L10
_L7:
        if(isPrintableChar(c)) goto _L12; else goto _L11
_L11:
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(c);
        stringbuilder.append(String.format("\\u%04x", aobj));
_L14:
        j++;
          goto _L13
_L4:
        stringbuilder.append('\\');
          goto _L3
_L10:
        stringbuilder.append('\\');
_L12:
        stringbuilder.append(c);
          goto _L14
_L9:
        stringbuilder.append("&quot;");
          goto _L14
_L8:
        stringbuilder.append("\\n");
          goto _L14
_L6:
        s = stringbuilder.toString();
        if(true) goto _L1; else goto _L15
_L15:
    }

    public static String encodeAsXmlValue(String s)
    {
        if(!s.isEmpty()) goto _L2; else goto _L1
_L1:
        return s;
_L2:
        char ac[];
        StringBuilder stringbuilder;
        ac = s.toCharArray();
        stringbuilder = new StringBuilder(10 + s.length());
        ac[0];
        JVM INSTR lookupswitch 3: default 68
    //                   35: 155
    //                   63: 155
    //                   64: 155;
           goto _L3 _L4 _L4 _L4
_L3:
        int i;
        int j;
        boolean flag;
        boolean flag1;
        int k;
        boolean flag2;
        i = ac.length;
        j = 0;
        flag = true;
        flag1 = false;
        k = 0;
        flag2 = false;
_L9:
        if(j >= i) goto _L6; else goto _L5
_L5:
        char c = ac[j];
        if(!flag2) goto _L8; else goto _L7
_L7:
        boolean flag3;
        boolean flag4;
        if(c == '>')
        {
            k = 1 + stringbuilder.length();
            flag2 = false;
            flag4 = flag;
            flag3 = false;
        } else
        {
            boolean flag6 = flag;
            flag3 = flag1;
            flag4 = flag6;
        }
        stringbuilder.append(c);
_L10:
        j++;
        boolean flag5 = flag4;
        flag1 = flag3;
        flag = flag5;
          goto _L9
_L4:
        stringbuilder.append('\\');
          goto _L3
_L8:
        if(c == ' ')
        {
            if(flag)
                flag1 = true;
            flag3 = flag1;
            flag4 = true;
            break MISSING_BLOCK_LABEL_130;
        }
        switch(c)
        {
        default:
            if(!isPrintableChar(c))
            {
                Object aobj[] = new Object[1];
                aobj[0] = Integer.valueOf(c);
                stringbuilder.append(String.format("\\u%04x", aobj));
                flag3 = flag1;
                flag4 = false;
            } else
            {
                flag3 = flag1;
                flag4 = false;
                break MISSING_BLOCK_LABEL_130;
            }
            break;

        case 92: // '\\'
            stringbuilder.append('\\');
            flag3 = flag1;
            flag4 = false;
            break MISSING_BLOCK_LABEL_130;

        case 10: // '\n'
        case 39: // '\''
            flag4 = false;
            flag3 = true;
            break MISSING_BLOCK_LABEL_130;

        case 34: // '"'
            stringbuilder.append('\\');
            flag3 = flag1;
            flag4 = false;
            break MISSING_BLOCK_LABEL_130;

        case 60: // '<'
            if(flag1)
            {
                stringbuilder.insert(k, '"').append('"');
                flag3 = flag1;
                flag2 = true;
                flag4 = false;
            } else
            {
                flag3 = flag1;
                flag2 = true;
                flag4 = false;
            }
            break MISSING_BLOCK_LABEL_130;
        }
          goto _L10
_L6:
        if(flag1 || flag)
            stringbuilder.insert(k, '"').append('"');
        s = stringbuilder.toString();
        if(true) goto _L1; else goto _L11
_L11:
    }

    public static String enumerateNonPositionalSubstitutions(String s)
    {
        List list = findNonPositionalSubstitutions(s, -1);
        if(list.size() >= 2)
        {
            StringBuilder stringbuilder = new StringBuilder();
            Iterator iterator = list.iterator();
            int i = 0;
            int j = 0;
            while(iterator.hasNext()) 
            {
                Integer integer = Integer.valueOf(1 + ((Integer)iterator.next()).intValue());
                StringBuilder stringbuilder1 = stringbuilder.append(s.substring(j, integer.intValue()));
                int k = i + 1;
                stringbuilder1.append(k).append('$');
                j = integer.intValue();
                i = k;
            }
            stringbuilder.append(s.substring(j));
            s = stringbuilder.toString();
        }
        return s;
    }

    public static String escapeXmlChars(String s)
    {
        return s.replace("&", "&amp;").replace("<", "&lt;");
    }

    private static List findNonPositionalSubstitutions(String s, int i)
    {
        int j;
        int k;
        ArrayList arraylist;
        int l;
        j = 0;
        k = s.length();
        arraylist = new ArrayList();
        l = 0;
_L5:
        int i1;
        int j1;
        i1 = s.indexOf('%', l);
        j1 = i1 + 1;
        if(j1 != 0 && j1 != k) goto _L2; else goto _L1
_L1:
        return arraylist;
_L2:
        char c1;
        l = j1 + 1;
        char c = s.charAt(j1);
        if(c == '%')
            break; /* Loop/switch isn't completed */
        if(c < '0' || c > '9' || l >= k)
            break; /* Loop/switch isn't completed */
        do
        {
            int k1 = l;
            l = k1 + 1;
            c1 = s.charAt(k1);
        } while(c1 >= '0' && c1 <= '9' && l < k);
        if(c1 == '$') goto _L4; else goto _L3
_L4:
        break; /* Loop/switch isn't completed */
_L3:
        arraylist.add(Integer.valueOf(i1));
        if(i == -1 || ++j < i) goto _L5; else goto _L1
    }

    public static boolean hasMultipleNonPositionalSubstitutions(String s)
    {
        boolean flag = true;
        if(findNonPositionalSubstitutions(s, 2).size() <= flag)
            flag = false;
        return flag;
    }

    private static boolean isPrintableChar(char c)
    {
        Character.UnicodeBlock unicodeblock = Character.UnicodeBlock.of(c);
        boolean flag;
        if(!Character.isISOControl(c) && c != '\uFFFF' && unicodeblock != null && unicodeblock != Character.UnicodeBlock.SPECIALS)
            flag = true;
        else
            flag = false;
        return flag;
    }
}
