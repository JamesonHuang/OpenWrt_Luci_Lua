// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view.accessibility;

import android.view.View;
import android.view.accessibility.AccessibilityRecord;

class AccessibilityRecordCompatJellyBean
{

    AccessibilityRecordCompatJellyBean()
    {
    }

    public static void setSource(Object obj, View view, int i)
    {
        ((AccessibilityRecord)obj).setSource(view, i);
    }
}
