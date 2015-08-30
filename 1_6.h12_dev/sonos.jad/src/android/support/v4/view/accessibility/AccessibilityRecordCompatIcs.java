// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view.accessibility;

import android.os.Parcelable;
import android.view.View;
import android.view.accessibility.AccessibilityRecord;
import java.util.List;

class AccessibilityRecordCompatIcs
{

    AccessibilityRecordCompatIcs()
    {
    }

    public static int getAddedCount(Object obj)
    {
        return ((AccessibilityRecord)obj).getAddedCount();
    }

    public static CharSequence getBeforeText(Object obj)
    {
        return ((AccessibilityRecord)obj).getBeforeText();
    }

    public static CharSequence getClassName(Object obj)
    {
        return ((AccessibilityRecord)obj).getClassName();
    }

    public static CharSequence getContentDescription(Object obj)
    {
        return ((AccessibilityRecord)obj).getContentDescription();
    }

    public static int getCurrentItemIndex(Object obj)
    {
        return ((AccessibilityRecord)obj).getCurrentItemIndex();
    }

    public static int getFromIndex(Object obj)
    {
        return ((AccessibilityRecord)obj).getFromIndex();
    }

    public static int getItemCount(Object obj)
    {
        return ((AccessibilityRecord)obj).getItemCount();
    }

    public static Parcelable getParcelableData(Object obj)
    {
        return ((AccessibilityRecord)obj).getParcelableData();
    }

    public static int getRemovedCount(Object obj)
    {
        return ((AccessibilityRecord)obj).getRemovedCount();
    }

    public static int getScrollX(Object obj)
    {
        return ((AccessibilityRecord)obj).getScrollX();
    }

    public static int getScrollY(Object obj)
    {
        return ((AccessibilityRecord)obj).getScrollY();
    }

    public static Object getSource(Object obj)
    {
        return ((AccessibilityRecord)obj).getSource();
    }

    public static List getText(Object obj)
    {
        return ((AccessibilityRecord)obj).getText();
    }

    public static int getToIndex(Object obj)
    {
        return ((AccessibilityRecord)obj).getToIndex();
    }

    public static int getWindowId(Object obj)
    {
        return ((AccessibilityRecord)obj).getWindowId();
    }

    public static boolean isChecked(Object obj)
    {
        return ((AccessibilityRecord)obj).isChecked();
    }

    public static boolean isEnabled(Object obj)
    {
        return ((AccessibilityRecord)obj).isEnabled();
    }

    public static boolean isFullScreen(Object obj)
    {
        return ((AccessibilityRecord)obj).isFullScreen();
    }

    public static boolean isPassword(Object obj)
    {
        return ((AccessibilityRecord)obj).isPassword();
    }

    public static boolean isScrollable(Object obj)
    {
        return ((AccessibilityRecord)obj).isScrollable();
    }

    public static Object obtain()
    {
        return AccessibilityRecord.obtain();
    }

    public static Object obtain(Object obj)
    {
        return AccessibilityRecord.obtain((AccessibilityRecord)obj);
    }

    public static void recycle(Object obj)
    {
        ((AccessibilityRecord)obj).recycle();
    }

    public static void setAddedCount(Object obj, int i)
    {
        ((AccessibilityRecord)obj).setAddedCount(i);
    }

    public static void setBeforeText(Object obj, CharSequence charsequence)
    {
        ((AccessibilityRecord)obj).setBeforeText(charsequence);
    }

    public static void setChecked(Object obj, boolean flag)
    {
        ((AccessibilityRecord)obj).setChecked(flag);
    }

    public static void setClassName(Object obj, CharSequence charsequence)
    {
        ((AccessibilityRecord)obj).setClassName(charsequence);
    }

    public static void setContentDescription(Object obj, CharSequence charsequence)
    {
        ((AccessibilityRecord)obj).setContentDescription(charsequence);
    }

    public static void setCurrentItemIndex(Object obj, int i)
    {
        ((AccessibilityRecord)obj).setCurrentItemIndex(i);
    }

    public static void setEnabled(Object obj, boolean flag)
    {
        ((AccessibilityRecord)obj).setEnabled(flag);
    }

    public static void setFromIndex(Object obj, int i)
    {
        ((AccessibilityRecord)obj).setFromIndex(i);
    }

    public static void setFullScreen(Object obj, boolean flag)
    {
        ((AccessibilityRecord)obj).setFullScreen(flag);
    }

    public static void setItemCount(Object obj, int i)
    {
        ((AccessibilityRecord)obj).setItemCount(i);
    }

    public static void setParcelableData(Object obj, Parcelable parcelable)
    {
        ((AccessibilityRecord)obj).setParcelableData(parcelable);
    }

    public static void setPassword(Object obj, boolean flag)
    {
        ((AccessibilityRecord)obj).setPassword(flag);
    }

    public static void setRemovedCount(Object obj, int i)
    {
        ((AccessibilityRecord)obj).setRemovedCount(i);
    }

    public static void setScrollX(Object obj, int i)
    {
        ((AccessibilityRecord)obj).setScrollX(i);
    }

    public static void setScrollY(Object obj, int i)
    {
        ((AccessibilityRecord)obj).setScrollY(i);
    }

    public static void setScrollable(Object obj, boolean flag)
    {
        ((AccessibilityRecord)obj).setScrollable(flag);
    }

    public static void setSource(Object obj, View view)
    {
        ((AccessibilityRecord)obj).setSource(view);
    }

    public static void setToIndex(Object obj, int i)
    {
        ((AccessibilityRecord)obj).setToIndex(i);
    }
}
