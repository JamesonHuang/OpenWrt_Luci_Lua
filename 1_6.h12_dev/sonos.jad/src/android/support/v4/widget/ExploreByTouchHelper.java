// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.*;
import android.support.v4.view.accessibility.*;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import java.util.*;

public abstract class ExploreByTouchHelper extends AccessibilityDelegateCompat
{
    private class ExploreByTouchNodeProvider extends AccessibilityNodeProviderCompat
    {

        public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int i)
        {
            return createNode(i);
        }

        public boolean performAction(int i, int j, Bundle bundle)
        {
            return ExploreByTouchHelper.this.performAction(i, j, bundle);
        }

        final ExploreByTouchHelper this$0;

        private ExploreByTouchNodeProvider()
        {
            this$0 = ExploreByTouchHelper.this;
            super();
        }

    }


    public ExploreByTouchHelper(View view)
    {
        mFocusedVirtualViewId = 0x80000000;
        mHoveredVirtualViewId = 0x80000000;
        if(view == null)
        {
            throw new IllegalArgumentException("View may not be null");
        } else
        {
            mView = view;
            mManager = (AccessibilityManager)view.getContext().getSystemService("accessibility");
            return;
        }
    }

    private boolean clearAccessibilityFocus(int i)
    {
        boolean flag;
        if(isAccessibilityFocused(i))
        {
            mFocusedVirtualViewId = 0x80000000;
            mView.invalidate();
            sendEventForVirtualView(i, 0x10000);
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    private AccessibilityEvent createEvent(int i, int j)
    {
        i;
        JVM INSTR tableswitch -1 -1: default 20
    //                   -1 29;
           goto _L1 _L2
_L1:
        AccessibilityEvent accessibilityevent = createEventForChild(i, j);
_L4:
        return accessibilityevent;
_L2:
        accessibilityevent = createEventForHost(j);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private AccessibilityEvent createEventForChild(int i, int j)
    {
        AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain(j);
        accessibilityevent.setEnabled(true);
        accessibilityevent.setClassName(DEFAULT_CLASS_NAME);
        onPopulateEventForVirtualView(i, accessibilityevent);
        if(accessibilityevent.getText().isEmpty() && accessibilityevent.getContentDescription() == null)
        {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        } else
        {
            accessibilityevent.setPackageName(mView.getContext().getPackageName());
            AccessibilityEventCompat.asRecord(accessibilityevent).setSource(mView, i);
            return accessibilityevent;
        }
    }

    private AccessibilityEvent createEventForHost(int i)
    {
        AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain(i);
        ViewCompat.onInitializeAccessibilityEvent(mView, accessibilityevent);
        return accessibilityevent;
    }

    private AccessibilityNodeInfoCompat createNode(int i)
    {
        i;
        JVM INSTR tableswitch -1 -1: default 20
    //                   -1 28;
           goto _L1 _L2
_L1:
        AccessibilityNodeInfoCompat accessibilitynodeinfocompat = createNodeForChild(i);
_L4:
        return accessibilitynodeinfocompat;
_L2:
        accessibilitynodeinfocompat = createNodeForHost();
        if(true) goto _L4; else goto _L3
_L3:
    }

    private AccessibilityNodeInfoCompat createNodeForChild(int i)
    {
        AccessibilityNodeInfoCompat accessibilitynodeinfocompat = AccessibilityNodeInfoCompat.obtain();
        accessibilitynodeinfocompat.setEnabled(true);
        accessibilitynodeinfocompat.setClassName(DEFAULT_CLASS_NAME);
        onPopulateNodeForVirtualView(i, accessibilitynodeinfocompat);
        if(accessibilitynodeinfocompat.getText() == null && accessibilitynodeinfocompat.getContentDescription() == null)
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        accessibilitynodeinfocompat.getBoundsInParent(mTempParentRect);
        if(mTempParentRect.isEmpty())
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        int j = accessibilitynodeinfocompat.getActions();
        if((j & 0x40) != 0)
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        if((j & 0x80) != 0)
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        accessibilitynodeinfocompat.setPackageName(mView.getContext().getPackageName());
        accessibilitynodeinfocompat.setSource(mView, i);
        accessibilitynodeinfocompat.setParent(mView);
        int k;
        int l;
        if(mFocusedVirtualViewId == i)
        {
            accessibilitynodeinfocompat.setAccessibilityFocused(true);
            accessibilitynodeinfocompat.addAction(128);
        } else
        {
            accessibilitynodeinfocompat.setAccessibilityFocused(false);
            accessibilitynodeinfocompat.addAction(64);
        }
        if(intersectVisibleToUser(mTempParentRect))
        {
            accessibilitynodeinfocompat.setVisibleToUser(true);
            accessibilitynodeinfocompat.setBoundsInParent(mTempParentRect);
        }
        mView.getLocationOnScreen(mTempGlobalRect);
        k = mTempGlobalRect[0];
        l = mTempGlobalRect[1];
        mTempScreenRect.set(mTempParentRect);
        mTempScreenRect.offset(k, l);
        accessibilitynodeinfocompat.setBoundsInScreen(mTempScreenRect);
        return accessibilitynodeinfocompat;
    }

    private AccessibilityNodeInfoCompat createNodeForHost()
    {
        AccessibilityNodeInfoCompat accessibilitynodeinfocompat = AccessibilityNodeInfoCompat.obtain(mView);
        ViewCompat.onInitializeAccessibilityNodeInfo(mView, accessibilitynodeinfocompat);
        LinkedList linkedlist = new LinkedList();
        getVisibleVirtualViews(linkedlist);
        Integer integer;
        for(Iterator iterator = linkedlist.iterator(); iterator.hasNext(); accessibilitynodeinfocompat.addChild(mView, integer.intValue()))
            integer = (Integer)iterator.next();

        return accessibilitynodeinfocompat;
    }

    private boolean intersectVisibleToUser(Rect rect)
    {
        boolean flag;
        flag = false;
        break MISSING_BLOCK_LABEL_2;
label0:
        while(true) 
        {
            do
                return flag;
            while(rect == null || rect.isEmpty() || mView.getWindowVisibility() != 0);
            android.view.ViewParent viewparent;
            View view;
            for(viewparent = mView.getParent(); viewparent instanceof View; viewparent = view.getParent())
            {
                view = (View)viewparent;
                if(ViewCompat.getAlpha(view) <= 0.0F || view.getVisibility() != 0)
                    continue label0;
            }

            if(viewparent != null && mView.getLocalVisibleRect(mTempVisibleRect))
                flag = rect.intersect(mTempVisibleRect);
        }
    }

    private boolean isAccessibilityFocused(int i)
    {
        boolean flag;
        if(mFocusedVirtualViewId == i)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private boolean manageFocusForChild(int i, int j, Bundle bundle)
    {
        j;
        JVM INSTR lookupswitch 2: default 28
    //                   64: 34
    //                   128: 44;
           goto _L1 _L2 _L3
_L1:
        boolean flag = false;
_L5:
        return flag;
_L2:
        flag = requestAccessibilityFocus(i);
        continue; /* Loop/switch isn't completed */
_L3:
        flag = clearAccessibilityFocus(i);
        if(true) goto _L5; else goto _L4
_L4:
    }

    private boolean performAction(int i, int j, Bundle bundle)
    {
        i;
        JVM INSTR tableswitch -1 -1: default 20
    //                   -1 32;
           goto _L1 _L2
_L1:
        boolean flag = performActionForChild(i, j, bundle);
_L4:
        return flag;
_L2:
        flag = performActionForHost(j, bundle);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private boolean performActionForChild(int i, int j, Bundle bundle)
    {
        j;
        JVM INSTR lookupswitch 2: default 28
    //                   64: 40
    //                   128: 40;
           goto _L1 _L2 _L2
_L1:
        boolean flag = onPerformActionForVirtualView(i, j, bundle);
_L4:
        return flag;
_L2:
        flag = manageFocusForChild(i, j, bundle);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private boolean performActionForHost(int i, Bundle bundle)
    {
        return ViewCompat.performAccessibilityAction(mView, i, bundle);
    }

    private boolean requestAccessibilityFocus(int i)
    {
        boolean flag;
        flag = false;
        break MISSING_BLOCK_LABEL_2;
        if(mManager.isEnabled() && AccessibilityManagerCompat.isTouchExplorationEnabled(mManager) && !isAccessibilityFocused(i))
        {
            mFocusedVirtualViewId = i;
            mView.invalidate();
            sendEventForVirtualView(i, 32768);
            flag = true;
        }
        return flag;
    }

    private void updateHoveredVirtualView(int i)
    {
        if(mHoveredVirtualViewId != i)
        {
            int j = mHoveredVirtualViewId;
            mHoveredVirtualViewId = i;
            sendEventForVirtualView(i, 128);
            sendEventForVirtualView(j, 256);
        }
    }

    public boolean dispatchHoverEvent(MotionEvent motionevent)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = false;
        if(mManager.isEnabled() && AccessibilityManagerCompat.isTouchExplorationEnabled(mManager)) goto _L2; else goto _L1
_L1:
        return flag1;
_L2:
        switch(motionevent.getAction())
        {
        case 7: // '\007'
        case 9: // '\t'
            int i = getVirtualViewAt(motionevent.getX(), motionevent.getY());
            updateHoveredVirtualView(i);
            if(i == 0x80000000)
                flag = false;
            flag1 = flag;
            break;

        case 10: // '\n'
            if(mFocusedVirtualViewId != 0x80000000)
            {
                updateHoveredVirtualView(0x80000000);
                flag1 = flag;
            }
            break;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view)
    {
        if(mNodeProvider == null)
            mNodeProvider = new ExploreByTouchNodeProvider();
        return mNodeProvider;
    }

    public int getFocusedVirtualView()
    {
        return mFocusedVirtualViewId;
    }

    protected abstract int getVirtualViewAt(float f, float f1);

    protected abstract void getVisibleVirtualViews(List list);

    public void invalidateRoot()
    {
        invalidateVirtualView(-1);
    }

    public void invalidateVirtualView(int i)
    {
        sendEventForVirtualView(i, 2048);
    }

    protected abstract boolean onPerformActionForVirtualView(int i, int j, Bundle bundle);

    protected abstract void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityevent);

    protected abstract void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilitynodeinfocompat);

    public boolean sendEventForVirtualView(int i, int j)
    {
        boolean flag = false;
        if(i != 0x80000000 && mManager.isEnabled()) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        android.view.ViewParent viewparent = mView.getParent();
        if(viewparent != null)
        {
            AccessibilityEvent accessibilityevent = createEvent(i, j);
            flag = ViewParentCompat.requestSendAccessibilityEvent(viewparent, mView, accessibilityevent);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    private static final String DEFAULT_CLASS_NAME = android/view/View.getName();
    public static final int INVALID_ID = 0x80000000;
    private int mFocusedVirtualViewId;
    private int mHoveredVirtualViewId;
    private final AccessibilityManager mManager;
    private ExploreByTouchNodeProvider mNodeProvider;
    private final int mTempGlobalRect[] = new int[2];
    private final Rect mTempParentRect = new Rect();
    private final Rect mTempScreenRect = new Rect();
    private final Rect mTempVisibleRect = new Rect();
    private final View mView;



}
