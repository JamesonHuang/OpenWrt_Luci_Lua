// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.*;

class AccessibilityDelegateCompatJellyBean
{
    public static interface AccessibilityDelegateBridgeJellyBean
    {

        public abstract boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent);

        public abstract Object getAccessibilityNodeProvider(View view);

        public abstract void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent);

        public abstract void onInitializeAccessibilityNodeInfo(View view, Object obj);

        public abstract void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent);

        public abstract boolean onRequestSendAccessibilityEvent(ViewGroup viewgroup, View view, AccessibilityEvent accessibilityevent);

        public abstract boolean performAccessibilityAction(View view, int i, Bundle bundle);

        public abstract void sendAccessibilityEvent(View view, int i);

        public abstract void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityevent);
    }


    AccessibilityDelegateCompatJellyBean()
    {
    }

    public static Object getAccessibilityNodeProvider(Object obj, View view)
    {
        return ((android.view.View.AccessibilityDelegate)obj).getAccessibilityNodeProvider(view);
    }

    public static Object newAccessibilityDelegateBridge(final AccessibilityDelegateBridgeJellyBean bridge)
    {
        return new android.view.View.AccessibilityDelegate() {

            public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
            {
                return bridge.dispatchPopulateAccessibilityEvent(view, accessibilityevent);
            }

            public AccessibilityNodeProvider getAccessibilityNodeProvider(View view)
            {
                return (AccessibilityNodeProvider)bridge.getAccessibilityNodeProvider(view);
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

            public boolean performAccessibilityAction(View view, int i, Bundle bundle)
            {
                return bridge.performAccessibilityAction(view, i, bundle);
            }

            public void sendAccessibilityEvent(View view, int i)
            {
                bridge.sendAccessibilityEvent(view, i);
            }

            public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityevent)
            {
                bridge.sendAccessibilityEventUnchecked(view, accessibilityevent);
            }

            final AccessibilityDelegateBridgeJellyBean val$bridge;

            
            {
                bridge = accessibilitydelegatebridgejellybean;
                super();
            }
        }
;
    }

    public static boolean performAccessibilityAction(Object obj, View view, int i, Bundle bundle)
    {
        return ((android.view.View.AccessibilityDelegate)obj).performAccessibilityAction(view, i, bundle);
    }
}
