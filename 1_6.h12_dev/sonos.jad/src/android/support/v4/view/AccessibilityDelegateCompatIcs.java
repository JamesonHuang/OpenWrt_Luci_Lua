// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

class AccessibilityDelegateCompatIcs
{
    public static interface AccessibilityDelegateBridge
    {

        public abstract boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent);

        public abstract void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent);

        public abstract void onInitializeAccessibilityNodeInfo(View view, Object obj);

        public abstract void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent);

        public abstract boolean onRequestSendAccessibilityEvent(ViewGroup viewgroup, View view, AccessibilityEvent accessibilityevent);

        public abstract void sendAccessibilityEvent(View view, int i);

        public abstract void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityevent);
    }


    AccessibilityDelegateCompatIcs()
    {
    }

    public static boolean dispatchPopulateAccessibilityEvent(Object obj, View view, AccessibilityEvent accessibilityevent)
    {
        return ((android.view.View.AccessibilityDelegate)obj).dispatchPopulateAccessibilityEvent(view, accessibilityevent);
    }

    public static Object newAccessibilityDelegateBridge(final AccessibilityDelegateBridge bridge)
    {
        return new android.view.View.AccessibilityDelegate() {

            public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
            {
                return bridge.dispatchPopulateAccessibilityEvent(view, accessibilityevent);
            }

            public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
            {
                bridge.onInitializeAccessibilityEvent(view, accessibilityevent);
            }

            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilitynodeinfo)
            {
                bridge.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfo);
            }

            public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
            {
                bridge.onPopulateAccessibilityEvent(view, accessibilityevent);
            }

            public boolean onRequestSendAccessibilityEvent(ViewGroup viewgroup, View view, AccessibilityEvent accessibilityevent)
            {
                return bridge.onRequestSendAccessibilityEvent(viewgroup, view, accessibilityevent);
            }

            public void sendAccessibilityEvent(View view, int i)
            {
                bridge.sendAccessibilityEvent(view, i);
            }

            public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityevent)
            {
                bridge.sendAccessibilityEventUnchecked(view, accessibilityevent);
            }

            final AccessibilityDelegateBridge val$bridge;

            
            {
                bridge = accessibilitydelegatebridge;
                super();
            }
        }
;
    }

    public static Object newAccessibilityDelegateDefaultImpl()
    {
        return new android.view.View.AccessibilityDelegate();
    }

    public static void onInitializeAccessibilityEvent(Object obj, View view, AccessibilityEvent accessibilityevent)
    {
        ((android.view.View.AccessibilityDelegate)obj).onInitializeAccessibilityEvent(view, accessibilityevent);
    }

    public static void onInitializeAccessibilityNodeInfo(Object obj, View view, Object obj1)
    {
        ((android.view.View.AccessibilityDelegate)obj).onInitializeAccessibilityNodeInfo(view, (AccessibilityNodeInfo)obj1);
    }

    public static void onPopulateAccessibilityEvent(Object obj, View view, AccessibilityEvent accessibilityevent)
    {
        ((android.view.View.AccessibilityDelegate)obj).onPopulateAccessibilityEvent(view, accessibilityevent);
    }

    public static boolean onRequestSendAccessibilityEvent(Object obj, ViewGroup viewgroup, View view, AccessibilityEvent accessibilityevent)
    {
        return ((android.view.View.AccessibilityDelegate)obj).onRequestSendAccessibilityEvent(viewgroup, view, accessibilityevent);
    }

    public static void sendAccessibilityEvent(Object obj, View view, int i)
    {
        ((android.view.View.AccessibilityDelegate)obj).sendAccessibilityEvent(view, i);
    }

    public static void sendAccessibilityEventUnchecked(Object obj, View view, AccessibilityEvent accessibilityevent)
    {
        ((android.view.View.AccessibilityDelegate)obj).sendAccessibilityEventUnchecked(view, accessibilityevent);
    }
}
