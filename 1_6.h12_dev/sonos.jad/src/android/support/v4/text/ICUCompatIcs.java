// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.text;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ICUCompatIcs
{

    ICUCompatIcs()
    {
    }

    public static String addLikelySubtags(String s)
    {
        if(sAddLikelySubtagsMethod == null) goto _L2; else goto _L1
_L1:
        String s1;
        Object aobj[] = new Object[1];
        aobj[0] = s;
        s1 = (String)sAddLikelySubtagsMethod.invoke(null, aobj);
_L4:
        return s1;
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        Log.w("ICUCompatIcs", illegalaccessexception);
_L2:
        s1 = s;
        if(true) goto _L4; else goto _L3
_L3:
        InvocationTargetException invocationtargetexception;
        invocationtargetexception;
        Log.w("ICUCompatIcs", invocationtargetexception);
          goto _L2
    }

    public static String getScript(String s)
    {
        if(sGetScriptMethod == null) goto _L2; else goto _L1
_L1:
        String s1;
        Object aobj[] = new Object[1];
        aobj[0] = s;
        s1 = (String)sGetScriptMethod.invoke(null, aobj);
_L4:
        return s1;
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        Log.w("ICUCompatIcs", illegalaccessexception);
_L2:
        s1 = null;
        if(true) goto _L4; else goto _L3
_L3:
        InvocationTargetException invocationtargetexception;
        invocationtargetexception;
        Log.w("ICUCompatIcs", invocationtargetexception);
          goto _L2
    }

    private static final String TAG = "ICUCompatIcs";
    private static Method sAddLikelySubtagsMethod;
    private static Method sGetScriptMethod;

    static 
    {
        Class class1 = Class.forName("libcore.icu.ICU");
        if(class1 != null)
        {
            Class aclass[] = new Class[1];
            aclass[0] = java/lang/String;
            sGetScriptMethod = class1.getMethod("getScript", aclass);
            Class aclass1[] = new Class[1];
            aclass1[0] = java/lang/String;
            sAddLikelySubtagsMethod = class1.getMethod("addLikelySubtags", aclass1);
        }
_L1:
        return;
        Exception exception;
        exception;
        Log.w("ICUCompatIcs", exception);
          goto _L1
    }
}
