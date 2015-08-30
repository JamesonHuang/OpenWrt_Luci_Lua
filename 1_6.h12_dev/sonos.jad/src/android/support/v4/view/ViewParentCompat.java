// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.content.Context;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;

// Referenced classes of package android.support.v4.view:
//            ViewParentCompatICS

public class ViewParentCompat
{
    static class ViewParentCompatICSImpl extends ViewParentCompatStubImpl
    {

        public boolean requestSendAccessibilityEvent(ViewParent viewparent, View view, AccessibilityEvent accessibilityevent)
        {
            return ViewParentCompatICS.requestSendAccessibilityEvent(viewparent, view, accessibilityevent);
        }

        ViewParentCompatICSImpl()
        {
        }
    }

    static class ViewParentCompatStubImpl
        implements ViewParentCompatImpl
    {

        public boolean requestSendAccessibilityEvent(ViewParent viewparent, View view, AccessibilityEvent accessibilityevent)
        {
            boolean flag;
            if(view == null)
            {
                flag = false;
            } else
            {
                ((AccessibilityManager)view.getContext().getSystemService("accessibility")).sendAccessibilityEvent(accessibilityevent);
                flag = true;
            }
            return flag;
        }

        ViewParentCompatStubImpl()
        {
        }
    }

    static interface ViewParentCompatImpl
    {

        public abstract boolean requestSendAccessibilityEvent(ViewParent viewparent, View view, AccessibilityEvent accessibilityevent);
    }


    private ViewParentCompat()
    {
    }

    public static boolean requestSendAccessibilityEvent(ViewParent viewparent, View view, AccessibilityEvent accessibilityevent)
    {
        return IMPL.requestSendAccessibilityEvent(viewparent, view, accessibilityevent);
    }

    static final ViewParentCompatImpl IMPL;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 14)
            IMPL = new ViewParentCompatICSImpl();
        else
            IMPL = new ViewParentCompatStubImpl();
    }
}
