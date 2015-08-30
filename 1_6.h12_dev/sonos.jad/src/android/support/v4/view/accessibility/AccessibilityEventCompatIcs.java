// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityRecord;

class AccessibilityEventCompatIcs
{

    AccessibilityEventCompatIcs()
    {
    }

    public static void appendRecord(AccessibilityEvent accessibilityevent, Object obj)
    {
        accessibilityevent.appendRecord((AccessibilityRecord)obj);
    }

    public static Object getRecord(AccessibilityEvent accessibilityevent, int i)
    {
        return accessibilityevent.getRecord(i);
    }

    public static int getRecordCount(AccessibilityEvent accessibilityevent)
    {
        return accessibilityevent.getRecordCount();
    }

    public static void setScrollable(AccessibilityEvent accessibilityevent, boolean flag)
    {
        accessibilityevent.setScrollable(flag);
    }
}
