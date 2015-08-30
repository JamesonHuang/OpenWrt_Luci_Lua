// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityNodeInfo;

class AccessibilityNodeInfoCompatKitKat
{
    static class RangeInfo
    {

        static float getCurrent(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.RangeInfo)obj).getCurrent();
        }

        static float getMax(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.RangeInfo)obj).getMax();
        }

        static float getMin(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.RangeInfo)obj).getMin();
        }

        static int getType(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.RangeInfo)obj).getType();
        }

        RangeInfo()
        {
        }
    }

    static class CollectionItemInfo
    {

        static int getColumnIndex(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo)obj).getColumnIndex();
        }

        static int getColumnSpan(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo)obj).getColumnSpan();
        }

        static int getRowIndex(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo)obj).getRowIndex();
        }

        static int getRowSpan(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo)obj).getRowSpan();
        }

        static boolean isHeading(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo)obj).isHeading();
        }

        CollectionItemInfo()
        {
        }
    }

    static class CollectionInfo
    {

        static int getColumnCount(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionInfo)obj).getColumnCount();
        }

        static int getRowCount(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionInfo)obj).getRowCount();
        }

        static boolean isHierarchical(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionInfo)obj).isHierarchical();
        }

        CollectionInfo()
        {
        }
    }


    AccessibilityNodeInfoCompatKitKat()
    {
    }

    static Object getCollectionInfo(Object obj)
    {
        return ((AccessibilityNodeInfo)obj).getCollectionInfo();
    }

    static Object getCollectionItemInfo(Object obj)
    {
        return ((AccessibilityNodeInfo)obj).getCollectionItemInfo();
    }

    static int getLiveRegion(Object obj)
    {
        return ((AccessibilityNodeInfo)obj).getLiveRegion();
    }

    static Object getRangeInfo(Object obj)
    {
        return ((AccessibilityNodeInfo)obj).getRangeInfo();
    }

    public static Object obtainCollectionInfo(int i, int j, boolean flag, int k)
    {
        return android.view.accessibility.AccessibilityNodeInfo.CollectionInfo.obtain(i, j, flag);
    }

    public static Object obtainCollectionItemInfo(int i, int j, int k, int l, boolean flag)
    {
        return android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo.obtain(i, j, k, l, flag);
    }

    public static void setCollectionInfo(Object obj, Object obj1)
    {
        ((AccessibilityNodeInfo)obj).setCollectionInfo((android.view.accessibility.AccessibilityNodeInfo.CollectionInfo)obj1);
    }

    public static void setCollectionItemInfo(Object obj, Object obj1)
    {
        ((AccessibilityNodeInfo)obj).setCollectionItemInfo((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo)obj1);
    }

    static void setLiveRegion(Object obj, int i)
    {
        ((AccessibilityNodeInfo)obj).setLiveRegion(i);
    }
}
