// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

class ViewCompatICS
{

    ViewCompatICS()
    {
    }

    public static boolean canScrollHorizontally(View view, int i)
    {
        return view.canScrollHorizontally(i);
    }

    public static boolean canScrollVertically(View view, int i)
    {
        return view.canScrollVertically(i);
    }

    public static void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        view.onInitializeAccessibilityEvent(accessibilityevent);
    }

    public static void onInitializeAccessibilityNodeInfo(View view, Object obj)
    {
        view.onInitializeAccessibilityNodeInfo((AccessibilityNodeInfo)obj);
    }

    public static void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        view.onPopulateAccessibilityEvent(accessibilityevent);
    }

    public static void setAccessibilityDelegate(View view, Object obj)
    {
        view.setAccessibilityDelegate((android.view.View.AccessibilityDelegate)obj);
    }

    public static void setFitsSystemWindows(View view, boolean flag)
    {
        view.setFitsSystemWindows(flag);
    }
}
