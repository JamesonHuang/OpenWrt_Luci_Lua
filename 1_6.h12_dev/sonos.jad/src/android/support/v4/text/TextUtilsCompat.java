// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.text;

import java.util.Locale;

// Referenced classes of package android.support.v4.text:
//            ICUCompat

public class TextUtilsCompat
{

    public TextUtilsCompat()
    {
    }

    private static int getLayoutDirectionFromFirstChar(Locale locale)
    {
        int i = 0;
        Character.getDirectionality(locale.getDisplayName(locale).charAt(0));
        JVM INSTR tableswitch 1 2: default 36
    //                   1 38
    //                   2 38;
           goto _L1 _L2 _L2
_L1:
        return i;
_L2:
        i = 1;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public static int getLayoutDirectionFromLocale(Locale locale)
    {
        if(locale == null || locale.equals(ROOT)) goto _L2; else goto _L1
_L1:
        String s = ICUCompat.getScript(ICUCompat.addLikelySubtags(locale.toString()));
        if(s != null) goto _L4; else goto _L3
_L3:
        int i = getLayoutDirectionFromFirstChar(locale);
_L6:
        return i;
_L4:
        if(s.equalsIgnoreCase(ARAB_SCRIPT_SUBTAG) || s.equalsIgnoreCase(HEBR_SCRIPT_SUBTAG))
        {
            i = 1;
            continue; /* Loop/switch isn't completed */
        }
_L2:
        i = 0;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static String htmlEncode(String s)
    {
        StringBuilder stringbuilder;
        int i;
        stringbuilder = new StringBuilder();
        i = 0;
_L8:
        char c;
        if(i >= s.length())
            break MISSING_BLOCK_LABEL_138;
        c = s.charAt(i);
        c;
        JVM INSTR lookupswitch 5: default 76
    //                   34: 128
    //                   38: 108
    //                   39: 118
    //                   60: 88
    //                   62: 98;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L2:
        break MISSING_BLOCK_LABEL_128;
_L5:
        break; /* Loop/switch isn't completed */
_L1:
        stringbuilder.append(c);
_L9:
        i++;
        if(true) goto _L8; else goto _L7
_L7:
        stringbuilder.append("&lt;");
          goto _L9
_L6:
        stringbuilder.append("&gt;");
          goto _L9
_L3:
        stringbuilder.append("&amp;");
          goto _L9
_L4:
        stringbuilder.append("&#39;");
          goto _L9
        stringbuilder.append("&quot;");
          goto _L9
        return stringbuilder.toString();
    }

    private static String ARAB_SCRIPT_SUBTAG = "Arab";
    private static String HEBR_SCRIPT_SUBTAG = "Hebr";
    public static final Locale ROOT = new Locale("", "");

}
