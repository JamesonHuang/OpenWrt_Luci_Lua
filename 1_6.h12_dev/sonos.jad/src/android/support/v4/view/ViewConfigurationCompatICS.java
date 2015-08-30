// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.view.ViewConfiguration;

class ViewConfigurationCompatICS
{

    ViewConfigurationCompatICS()
    {
    }

    static boolean hasPermanentMenuKey(ViewConfiguration viewconfiguration)
    {
        return viewconfiguration.hasPermanentMenuKey();
    }
}
