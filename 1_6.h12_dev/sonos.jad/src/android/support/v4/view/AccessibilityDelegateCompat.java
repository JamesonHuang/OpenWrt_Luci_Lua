// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

// Referenced classes of package android.support.v4.view:
//            AccessibilityDelegateCompatJellyBean, AccessibilityDelegateCompatIcs

public class AccessibilityDelegateCompat
{
    static class AccessibilityDelegateJellyBeanImpl extends AccessibilityDelegateIcsImpl
    {

        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(Object obj, View view)
        {
            Object obj1 = AccessibilityDelegateCompatJellyBean.getAccessibilityNodeProvider(obj, view);
            AccessibilityNodeProviderCompat accessibilitynodeprovidercompat;
            if(obj1 != null)
                accessibilitynodeprovidercompat = new AccessibilityNodeProviderCompat(obj1);
            else
                accessibilitynodeprovidercompat = null;
            return accessibilitynodeprovidercompat;
        }

        public Object newAccessiblityDelegateBridge(AccessibilityDelegateCompat accessibilitydelegatecompat)
        {
            return AccessibilityDelegateCompatJellyBean.newAccessibilityDelegateBridge(accessibilitydelegatecompat. new AccessibilityDelegateCompatJellyBean.AccessibilityDelegateBridgeJellyBean() {

                public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
                {
                    return compat.dispatchPopulateAccessibilityEvent(view, accessibilityevent);
                }

                public Object getAccessibilityNodeProvider(View view)
                {
                    AccessibilityNodeProviderCompat accessibilitynodeprovidercompat = compat.getAccessibilityNodeProvider(view);
                    Object obj;
                    if(accessibilitynodeprovidercompat != null)
                        obj = accessibilitynodeprovidercompat.getProvider();
                    else
                        obj = null;
                    return obj;
                }

                public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
                {
                    compat.onInitializeAccessibilityEvent(view, accessibilityevent);
                }

                public void onInitializeAccessibilityNodeInfo(View view, Object obj)
                {
                    compat.onInitializeAccessibilityNodeInfo(view, new AccessibilityNodeInfoCompat(obj));
                }

                public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
                {
                    compat.onPopulateAccessibilityEvent(view, accessibilityevent);
                }

                public boolean onRequestSendAccessibilityEvent(ViewGroup viewgroup, View view, AccessibilityEvent accessibilityevent)
                {
                    return compat.onRequestSendAccessibilityEvent(viewgroup, view, accessibilityevent);
                }

                public boolean performAccessibilityAction(View view, int i, Bundle bundle)
                {
                    return compat.performAccessibilityAction(view, i, bundle);
                }

                public void sendAccessibilityEvent(View view, int i)
                {
                    compat.sendAccessibilityEvent(view, i);
                }

                public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityevent)
                {
                    compat.sendAccessibilityEventUnchecked(view, accessibilityevent);
                }

                final AccessibilityDelegateJellyBeanImpl this$0;
                final AccessibilityDelegateCompat val$compat;


// JavaClassFileOutputException: Invalid index accessing method local variables table of <init>
            }
);
        }

        public boolean performAccessibilityAction(Object obj, View view, int i, Bundle bundle)
        {
            return AccessibilityDelegateCompatJellyBean.performAccessibilityAction(obj, view, i, bundle);
        }

        AccessibilityDelegateJellyBeanImpl()
        {
        }
    }

    static class AccessibilityDelegateIcsImpl extends AccessibilityDelegateStubImpl
    {

        public boolean dispatchPopulateAccessibilityEvent(Object obj, View view, AccessibilityEvent accessibilityevent)
        {
            return AccessibilityDelegateCompatIcs.dispatchPopulateAccessibilityEvent(obj, view, accessibilityevent);
        }

        public Object newAccessiblityDelegateBridge(AccessibilityDelegateCompat accessibilitydelegatecompat)
        {
            return AccessibilityDelegateCompatIcs.newAccessibilityDelegateBridge(accessibilitydelegatecompat. new AccessibilityDelegateCompatIcs.AccessibilityDelegateBridge() {

                public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
                {
                    return compat.dispatchPopulateAccessibilityEvent(view, accessibilityevent);
                }

                public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
                {
                    compat.onInitializeAccessibilityEvent(view, accessibilityevent);
                }

                public void onInitializeAccessibilityNodeInfo(View view, Object obj)
                {
                    compat.onInitializeAccessibilityNodeInfo(view, new AccessibilityNodeInfoCompat(obj));
                }

                public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
                {
                    compat.onPopulateAccessibilityEvent(view, accessibilityevent);
                }

                public boolean onRequestSendAccessibilityEvent(ViewGroup viewgroup, View view, AccessibilityEvent accessibilityevent)
                {
                    return compat.onRequestSendAccessibilityEvent(viewgroup, view, accessibilityevent);
                }

                public void sendAccessibilityEvent(View view, int i)
                {
                    compat.sendAccessibilityEvent(view, i);
                }

                public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityevent)
                {
                    compat.sendAccessibilityEventUnchecked(view, accessibilityevent);
                }

                final AccessibilityDelegateIcsImpl this$0;
                final AccessibilityDelegateCompat val$compat;


// JavaClassFileOutputException: Invalid index accessing method local variables table of <init>
            }
);
        }

        public Object newAccessiblityDelegateDefaultImpl()
        {
            return AccessibilityDelegateCompatIcs.newAccessibilityDelegateDefaultImpl();
        }

        public void onInitializeAccessibilityEvent(Object obj, View view, AccessibilityEvent accessibilityevent)
        {
            AccessibilityDelegateCompatIcs.onInitializeAccessibilityEvent(obj, view, accessibilityevent);
        }

        public void onInitializeAccessibilityNodeInfo(Object obj, View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            AccessibilityDelegateCompatIcs.onInitializeAccessibilityNodeInfo(obj, view, accessibilitynodeinfocompat.getInfo());
        }

        public void onPopulateAccessibilityEvent(Object obj, View view, AccessibilityEvent accessibilityevent)
        {
            AccessibilityDelegateCompatIcs.onPopulateAccessibilityEvent(obj, view, accessibilityevent);
        }

        public boolean onRequestSendAccessibilityEvent(Object obj, ViewGroup viewgroup, View view, AccessibilityEvent accessibilityevent)
        {
            return AccessibilityDelegateCompatIcs.onRequestSendAccessibilityEvent(obj, viewgroup, view, accessibilityevent);
        }

        public void sendAccessibilityEvent(Object obj, View view, int i)
        {
            AccessibilityDelegateCompatIcs.sendAccessibilityEvent(obj, view, i);
        }

        public void sendAccessibilityEventUnchecked(Object obj, View view, AccessibilityEvent accessibilityevent)
        {
            AccessibilityDelegateCompatIcs.sendAccessibilityEventUnchecked(obj, view, accessibilityevent);
        }

        AccessibilityDelegateIcsImpl()
        {
        }
    }

    static class AccessibilityDelegateStubImpl
        implements AccessibilityDelegateImpl
    {

        public boolean dispatchPopulateAccessibilityEvent(Object obj, View view, AccessibilityEvent accessibilityevent)
        {
            return false;
        }

        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(Object obj, View view)
        {
            return null;
        }

        public Object newAccessiblityDelegateBridge(AccessibilityDelegateCompat accessibilitydelegatecompat)
        {
            return null;
        }

        public Object newAccessiblityDelegateDefaultImpl()
        {
            return null;
        }

        public void onInitializeAccessibilityEvent(Object obj, View view, AccessibilityEvent accessibilityevent)
        {
        }

        public void onInitializeAccessibilityNodeInfo(Object obj, View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
        }

        public void onPopulateAccessibilityEvent(Object obj, View view, AccessibilityEvent accessibilityevent)
        {
        }

        public boolean onRequestSendAccessibilityEvent(Object obj, ViewGroup viewgroup, View view, AccessibilityEvent accessibilityevent)
        {
            return true;
        }

        public boolean performAccessibilityAction(Object obj, View view, int i, Bundle bundle)
        {
            return false;
        }

        public void sendAccessibilityEvent(Object obj, View view, int i)
        {
        }

        public void sendAccessibilityEventUnchecked(Object obj, View view, AccessibilityEvent accessibilityevent)
        {
        }

        AccessibilityDelegateStubImpl()
        {
        }
    }

    static interface AccessibilityDelegateImpl
    {

        public abstract boolean dispatchPopulateAccessibilityEvent(Object obj, View view, AccessibilityEvent accessibilityevent);

        public abstract AccessibilityNodeProviderCompat getAccessibilityNodeProvider(Object obj, View view);

        public abstract Object newAccessiblityDelegateBridge(AccessibilityDelegateCompat accessibilitydelegatecompat);

        public abstract Object newAccessiblityDelegateDefaultImpl();

        public abstract void onInitializeAccessibilityEvent(Object obj, View view, AccessibilityEvent accessibilityevent);

        public abstract void onInitializeAccessibilityNodeInfo(Object obj, View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat);

        public abstract void onPopulateAccessibilityEvent(Object obj, View view, AccessibilityEvent accessibilityevent);

        public abstract boolean onRequestSendAccessibilityEvent(Object obj, ViewGroup viewgroup, View view, AccessibilityEvent accessibilityevent);

        public abstract boolean performAccessibilityAction(Object obj, View view, int i, Bundle bundle);

        public abstract void sendAccessibilityEvent(Object obj, View view, int i);

        public abstract void sendAccessibilityEventUnchecked(Object obj, View view, AccessibilityEvent accessibilityevent);
    }


    public AccessibilityDelegateCompat()
    {
        mBridge = IMPL.newAccessiblityDelegateBridge(this);
    }

    public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        return IMPL.dispatchPopulateAccessibilityEvent(DEFAULT_DELEGATE, view, accessibilityevent);
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view)
    {
        return IMPL.getAccessibilityNodeProvider(DEFAULT_DELEGATE, view);
    }

    Object getBridge()
    {
        return mBridge;
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        IMPL.onInitializeAccessibilityEvent(DEFAULT_DELEGATE, view, accessibilityevent);
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        IMPL.onInitializeAccessibilityNodeInfo(DEFAULT_DELEGATE, view, accessibilitynodeinfocompat);
    }

    public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        IMPL.onPopulateAccessibilityEvent(DEFAULT_DELEGATE, view, accessibilityevent);
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup viewgroup, View view, AccessibilityEvent accessibilityevent)
    {
        return IMPL.onRequestSendAccessibilityEvent(DEFAULT_DELEGATE, viewgroup, view, accessibilityevent);
    }

    public boolean performAccessibilityAction(View view, int i, Bundle bundle)
    {
        return IMPL.performAccessibilityAction(DEFAULT_DELEGATE, view, i, bundle);
    }

    public void sendAccessibilityEvent(View view, int i)
    {
        IMPL.sendAccessibilityEvent(DEFAULT_DELEGATE, view, i);
    }

    public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityevent)
    {
        IMPL.sendAccessibilityEventUnchecked(DEFAULT_DELEGATE, view, accessibilityevent);
    }

    private static final Object DEFAULT_DELEGATE;
    private static final AccessibilityDelegateImpl IMPL;
    final Object mBridge;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 16)
            IMPL = new AccessibilityDelegateJellyBeanImpl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 14)
            IMPL = new AccessibilityDelegateIcsImpl();
        else
            IMPL = new AccessibilityDelegateStubImpl();
        DEFAULT_DELEGATE = IMPL.newAccessiblityDelegateDefaultImpl();
    }
}
