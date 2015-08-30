// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityNodeInfo;
import java.util.List;

class AccessibilityNodeInfoCompatApi21
{
    static class CollectionItemInfo
    {

        public static boolean isSelected(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo)obj).isSelected();
        }

        CollectionItemInfo()
        {
        }
    }


    AccessibilityNodeInfoCompatApi21()
    {
    }

    static void addAction(Object obj, Object obj1)
    {
        ((AccessibilityNodeInfo)obj).addAction((android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction)obj1);
    }

    static int getAccessibilityActionId(Object obj)
    {
        return ((android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction)obj).getId();
    }

    static CharSequence getAccessibilityActionLabel(Object obj)
    {
        return ((android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction)obj).getLabel();
    }

    static List getActionList(Object obj)
    {
        return (List)((AccessibilityNodeInfo)obj).getActionList();
    }

    static Object newAccessibilityAction(int i, CharSequence charsequence)
    {
        return new android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction(i, charsequence);
    }

    public static Object obtainCollectionInfo(int i, int j, boolean flag, int k)
    {
        return android.view.accessibility.AccessibilityNodeInfo.CollectionInfo.obtain(i, j, flag, k);
    }

    public static Object obtainCollectionItemInfo(int i, int j, int k, int l, boolean flag, boolean flag1)
    {
        return android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo.obtain(i, j, k, l, flag, flag1);
    }
}
