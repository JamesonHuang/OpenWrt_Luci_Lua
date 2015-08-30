// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityEvent;

// Referenced classes of package android.support.v4.view.accessibility:
//            AccessibilityRecordCompat, AccessibilityEventCompatIcs

public class AccessibilityEventCompat
{
    static class AccessibilityEventIcsImpl extends AccessibilityEventStubImpl
    {

        public void appendRecord(AccessibilityEvent accessibilityevent, Object obj)
        {
            AccessibilityEventCompatIcs.appendRecord(accessibilityevent, obj);
        }

        public Object getRecord(AccessibilityEvent accessibilityevent, int i)
        {
            return AccessibilityEventCompatIcs.getRecord(accessibilityevent, i);
        }

        public int getRecordCount(AccessibilityEvent accessibilityevent)
        {
            return AccessibilityEventCompatIcs.getRecordCount(accessibilityevent);
        }

        AccessibilityEventIcsImpl()
        {
        }
    }

    static class AccessibilityEventStubImpl
        implements AccessibilityEventVersionImpl
    {

        public void appendRecord(AccessibilityEvent accessibilityevent, Object obj)
        {
        }

        public Object getRecord(AccessibilityEvent accessibilityevent, int i)
        {
            return null;
        }

        public int getRecordCount(AccessibilityEvent accessibilityevent)
        {
            return 0;
        }

        AccessibilityEventStubImpl()
        {
        }
    }

    static interface AccessibilityEventVersionImpl
    {

        public abstract void appendRecord(AccessibilityEvent accessibilityevent, Object obj);

        public abstract Object getRecord(AccessibilityEvent accessibilityevent, int i);

        public abstract int getRecordCount(AccessibilityEvent accessibilityevent);
    }


    private AccessibilityEventCompat()
    {
    }

    public static void appendRecord(AccessibilityEvent accessibilityevent, AccessibilityRecordCompat accessibilityrecordcompat)
    {
        IMPL.appendRecord(accessibilityevent, accessibilityrecordcompat.getImpl());
    }

    public static AccessibilityRecordCompat asRecord(AccessibilityEvent accessibilityevent)
    {
        return new AccessibilityRecordCompat(accessibilityevent);
    }

    public static AccessibilityRecordCompat getRecord(AccessibilityEvent accessibilityevent, int i)
    {
        return new AccessibilityRecordCompat(IMPL.getRecord(accessibilityevent, i));
    }

    public static int getRecordCount(AccessibilityEvent accessibilityevent)
    {
        return IMPL.getRecordCount(accessibilityevent);
    }

    private static final AccessibilityEventVersionImpl IMPL;
    public static final int TYPES_ALL_MASK = -1;
    public static final int TYPE_ANNOUNCEMENT = 16384;
    public static final int TYPE_GESTURE_DETECTION_END = 0x80000;
    public static final int TYPE_GESTURE_DETECTION_START = 0x40000;
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_END = 1024;
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_START = 512;
    public static final int TYPE_TOUCH_INTERACTION_END = 0x200000;
    public static final int TYPE_TOUCH_INTERACTION_START = 0x100000;
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUSED = 32768;
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED = 0x10000;
    public static final int TYPE_VIEW_HOVER_ENTER = 128;
    public static final int TYPE_VIEW_HOVER_EXIT = 256;
    public static final int TYPE_VIEW_SCROLLED = 4096;
    public static final int TYPE_VIEW_TEXT_SELECTION_CHANGED = 8192;
    public static final int TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY = 0x20000;
    public static final int TYPE_WINDOW_CONTENT_CHANGED = 2048;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 14)
            IMPL = new AccessibilityEventIcsImpl();
        else
            IMPL = new AccessibilityEventStubImpl();
    }
}
