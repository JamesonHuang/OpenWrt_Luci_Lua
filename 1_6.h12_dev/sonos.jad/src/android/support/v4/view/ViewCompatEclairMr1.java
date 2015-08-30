// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ViewCompatEclairMr1
{

    ViewCompatEclairMr1()
    {
    }

    public static boolean isOpaque(View view)
    {
        return view.isOpaque();
    }

    public static void setChildrenDrawingOrderEnabled(ViewGroup viewgroup, boolean flag)
    {
        if(sChildrenDrawingOrderMethod == null)
        {
            Method method;
            Object aobj[];
            try
            {
                Class aclass[] = new Class[1];
                aclass[0] = Boolean.TYPE;
                sChildrenDrawingOrderMethod = android/view/ViewGroup.getDeclaredMethod("setChildrenDrawingOrderEnabled", aclass);
            }
            catch(NoSuchMethodException nosuchmethodexception)
            {
                Log.e("ViewCompat", "Unable to find childrenDrawingOrderEnabled", nosuchmethodexception);
            }
            sChildrenDrawingOrderMethod.setAccessible(true);
        }
        method = sChildrenDrawingOrderMethod;
        aobj = new Object[1];
        aobj[0] = Boolean.valueOf(flag);
        method.invoke(viewgroup, aobj);
_L1:
        return;
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        Log.e("ViewCompat", "Unable to invoke childrenDrawingOrderEnabled", illegalaccessexception);
          goto _L1
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        Log.e("ViewCompat", "Unable to invoke childrenDrawingOrderEnabled", illegalargumentexception);
          goto _L1
        InvocationTargetException invocationtargetexception;
        invocationtargetexception;
        Log.e("ViewCompat", "Unable to invoke childrenDrawingOrderEnabled", invocationtargetexception);
          goto _L1
    }

    public static final String TAG = "ViewCompat";
    private static Method sChildrenDrawingOrderMethod;
}
