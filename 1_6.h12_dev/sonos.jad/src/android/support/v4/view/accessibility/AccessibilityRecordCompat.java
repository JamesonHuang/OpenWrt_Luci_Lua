// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view.accessibility;

import android.os.Parcelable;
import android.view.View;
import java.util.Collections;
import java.util.List;

// Referenced classes of package android.support.v4.view.accessibility:
//            AccessibilityNodeInfoCompat, AccessibilityRecordCompatJellyBean, AccessibilityRecordCompatIcsMr1, AccessibilityRecordCompatIcs

public class AccessibilityRecordCompat
{
    static class AccessibilityRecordJellyBeanImpl extends AccessibilityRecordIcsMr1Impl
    {

        public void setSource(Object obj, View view, int i)
        {
            AccessibilityRecordCompatJellyBean.setSource(obj, view, i);
        }

        AccessibilityRecordJellyBeanImpl()
        {
        }
    }

    static class AccessibilityRecordIcsMr1Impl extends AccessibilityRecordIcsImpl
    {

        public int getMaxScrollX(Object obj)
        {
            return AccessibilityRecordCompatIcsMr1.getMaxScrollX(obj);
        }

        public int getMaxScrollY(Object obj)
        {
            return AccessibilityRecordCompatIcsMr1.getMaxScrollY(obj);
        }

        public void setMaxScrollX(Object obj, int i)
        {
            AccessibilityRecordCompatIcsMr1.setMaxScrollX(obj, i);
        }

        public void setMaxScrollY(Object obj, int i)
        {
            AccessibilityRecordCompatIcsMr1.setMaxScrollY(obj, i);
        }

        AccessibilityRecordIcsMr1Impl()
        {
        }
    }

    static class AccessibilityRecordIcsImpl extends AccessibilityRecordStubImpl
    {

        public int getAddedCount(Object obj)
        {
            return AccessibilityRecordCompatIcs.getAddedCount(obj);
        }

        public CharSequence getBeforeText(Object obj)
        {
            return AccessibilityRecordCompatIcs.getBeforeText(obj);
        }

        public CharSequence getClassName(Object obj)
        {
            return AccessibilityRecordCompatIcs.getClassName(obj);
        }

        public CharSequence getContentDescription(Object obj)
        {
            return AccessibilityRecordCompatIcs.getContentDescription(obj);
        }

        public int getCurrentItemIndex(Object obj)
        {
            return AccessibilityRecordCompatIcs.getCurrentItemIndex(obj);
        }

        public int getFromIndex(Object obj)
        {
            return AccessibilityRecordCompatIcs.getFromIndex(obj);
        }

        public int getItemCount(Object obj)
        {
            return AccessibilityRecordCompatIcs.getItemCount(obj);
        }

        public Parcelable getParcelableData(Object obj)
        {
            return AccessibilityRecordCompatIcs.getParcelableData(obj);
        }

        public int getRemovedCount(Object obj)
        {
            return AccessibilityRecordCompatIcs.getRemovedCount(obj);
        }

        public int getScrollX(Object obj)
        {
            return AccessibilityRecordCompatIcs.getScrollX(obj);
        }

        public int getScrollY(Object obj)
        {
            return AccessibilityRecordCompatIcs.getScrollY(obj);
        }

        public AccessibilityNodeInfoCompat getSource(Object obj)
        {
            return AccessibilityNodeInfoCompat.wrapNonNullInstance(AccessibilityRecordCompatIcs.getSource(obj));
        }

        public List getText(Object obj)
        {
            return AccessibilityRecordCompatIcs.getText(obj);
        }

        public int getToIndex(Object obj)
        {
            return AccessibilityRecordCompatIcs.getToIndex(obj);
        }

        public int getWindowId(Object obj)
        {
            return AccessibilityRecordCompatIcs.getWindowId(obj);
        }

        public boolean isChecked(Object obj)
        {
            return AccessibilityRecordCompatIcs.isChecked(obj);
        }

        public boolean isEnabled(Object obj)
        {
            return AccessibilityRecordCompatIcs.isEnabled(obj);
        }

        public boolean isFullScreen(Object obj)
        {
            return AccessibilityRecordCompatIcs.isFullScreen(obj);
        }

        public boolean isPassword(Object obj)
        {
            return AccessibilityRecordCompatIcs.isPassword(obj);
        }

        public boolean isScrollable(Object obj)
        {
            return AccessibilityRecordCompatIcs.isScrollable(obj);
        }

        public Object obtain()
        {
            return AccessibilityRecordCompatIcs.obtain();
        }

        public Object obtain(Object obj)
        {
            return AccessibilityRecordCompatIcs.obtain(obj);
        }

        public void recycle(Object obj)
        {
            AccessibilityRecordCompatIcs.recycle(obj);
        }

        public void setAddedCount(Object obj, int i)
        {
            AccessibilityRecordCompatIcs.setAddedCount(obj, i);
        }

        public void setBeforeText(Object obj, CharSequence charsequence)
        {
            AccessibilityRecordCompatIcs.setBeforeText(obj, charsequence);
        }

        public void setChecked(Object obj, boolean flag)
        {
            AccessibilityRecordCompatIcs.setChecked(obj, flag);
        }

        public void setClassName(Object obj, CharSequence charsequence)
        {
            AccessibilityRecordCompatIcs.setClassName(obj, charsequence);
        }

        public void setContentDescription(Object obj, CharSequence charsequence)
        {
            AccessibilityRecordCompatIcs.setContentDescription(obj, charsequence);
        }

        public void setCurrentItemIndex(Object obj, int i)
        {
            AccessibilityRecordCompatIcs.setCurrentItemIndex(obj, i);
        }

        public void setEnabled(Object obj, boolean flag)
        {
            AccessibilityRecordCompatIcs.setEnabled(obj, flag);
        }

        public void setFromIndex(Object obj, int i)
        {
            AccessibilityRecordCompatIcs.setFromIndex(obj, i);
        }

        public void setFullScreen(Object obj, boolean flag)
        {
            AccessibilityRecordCompatIcs.setFullScreen(obj, flag);
        }

        public void setItemCount(Object obj, int i)
        {
            AccessibilityRecordCompatIcs.setItemCount(obj, i);
        }

        public void setParcelableData(Object obj, Parcelable parcelable)
        {
            AccessibilityRecordCompatIcs.setParcelableData(obj, parcelable);
        }

        public void setPassword(Object obj, boolean flag)
        {
            AccessibilityRecordCompatIcs.setPassword(obj, flag);
        }

        public void setRemovedCount(Object obj, int i)
        {
            AccessibilityRecordCompatIcs.setRemovedCount(obj, i);
        }

        public void setScrollX(Object obj, int i)
        {
            AccessibilityRecordCompatIcs.setScrollX(obj, i);
        }

        public void setScrollY(Object obj, int i)
        {
            AccessibilityRecordCompatIcs.setScrollY(obj, i);
        }

        public void setScrollable(Object obj, boolean flag)
        {
            AccessibilityRecordCompatIcs.setScrollable(obj, flag);
        }

        public void setSource(Object obj, View view)
        {
            AccessibilityRecordCompatIcs.setSource(obj, view);
        }

        public void setToIndex(Object obj, int i)
        {
            AccessibilityRecordCompatIcs.setToIndex(obj, i);
        }

        AccessibilityRecordIcsImpl()
        {
        }
    }

    static class AccessibilityRecordStubImpl
        implements AccessibilityRecordImpl
    {

        public int getAddedCount(Object obj)
        {
            return 0;
        }

        public CharSequence getBeforeText(Object obj)
        {
            return null;
        }

        public CharSequence getClassName(Object obj)
        {
            return null;
        }

        public CharSequence getContentDescription(Object obj)
        {
            return null;
        }

        public int getCurrentItemIndex(Object obj)
        {
            return 0;
        }

        public int getFromIndex(Object obj)
        {
            return 0;
        }

        public int getItemCount(Object obj)
        {
            return 0;
        }

        public int getMaxScrollX(Object obj)
        {
            return 0;
        }

        public int getMaxScrollY(Object obj)
        {
            return 0;
        }

        public Parcelable getParcelableData(Object obj)
        {
            return null;
        }

        public int getRemovedCount(Object obj)
        {
            return 0;
        }

        public int getScrollX(Object obj)
        {
            return 0;
        }

        public int getScrollY(Object obj)
        {
            return 0;
        }

        public AccessibilityNodeInfoCompat getSource(Object obj)
        {
            return null;
        }

        public List getText(Object obj)
        {
            return Collections.emptyList();
        }

        public int getToIndex(Object obj)
        {
            return 0;
        }

        public int getWindowId(Object obj)
        {
            return 0;
        }

        public boolean isChecked(Object obj)
        {
            return false;
        }

        public boolean isEnabled(Object obj)
        {
            return false;
        }

        public boolean isFullScreen(Object obj)
        {
            return false;
        }

        public boolean isPassword(Object obj)
        {
            return false;
        }

        public boolean isScrollable(Object obj)
        {
            return false;
        }

        public Object obtain()
        {
            return null;
        }

        public Object obtain(Object obj)
        {
            return null;
        }

        public void recycle(Object obj)
        {
        }

        public void setAddedCount(Object obj, int i)
        {
        }

        public void setBeforeText(Object obj, CharSequence charsequence)
        {
        }

        public void setChecked(Object obj, boolean flag)
        {
        }

        public void setClassName(Object obj, CharSequence charsequence)
        {
        }

        public void setContentDescription(Object obj, CharSequence charsequence)
        {
        }

        public void setCurrentItemIndex(Object obj, int i)
        {
        }

        public void setEnabled(Object obj, boolean flag)
        {
        }

        public void setFromIndex(Object obj, int i)
        {
        }

        public void setFullScreen(Object obj, boolean flag)
        {
        }

        public void setItemCount(Object obj, int i)
        {
        }

        public void setMaxScrollX(Object obj, int i)
        {
        }

        public void setMaxScrollY(Object obj, int i)
        {
        }

        public void setParcelableData(Object obj, Parcelable parcelable)
        {
        }

        public void setPassword(Object obj, boolean flag)
        {
        }

        public void setRemovedCount(Object obj, int i)
        {
        }

        public void setScrollX(Object obj, int i)
        {
        }

        public void setScrollY(Object obj, int i)
        {
        }

        public void setScrollable(Object obj, boolean flag)
        {
        }

        public void setSource(Object obj, View view)
        {
        }

        public void setSource(Object obj, View view, int i)
        {
        }

        public void setToIndex(Object obj, int i)
        {
        }

        AccessibilityRecordStubImpl()
        {
        }
    }

    static interface AccessibilityRecordImpl
    {

        public abstract int getAddedCount(Object obj);

        public abstract CharSequence getBeforeText(Object obj);

        public abstract CharSequence getClassName(Object obj);

        public abstract CharSequence getContentDescription(Object obj);

        public abstract int getCurrentItemIndex(Object obj);

        public abstract int getFromIndex(Object obj);

        public abstract int getItemCount(Object obj);

        public abstract int getMaxScrollX(Object obj);

        public abstract int getMaxScrollY(Object obj);

        public abstract Parcelable getParcelableData(Object obj);

        public abstract int getRemovedCount(Object obj);

        public abstract int getScrollX(Object obj);

        public abstract int getScrollY(Object obj);

        public abstract AccessibilityNodeInfoCompat getSource(Object obj);

        public abstract List getText(Object obj);

        public abstract int getToIndex(Object obj);

        public abstract int getWindowId(Object obj);

        public abstract boolean isChecked(Object obj);

        public abstract boolean isEnabled(Object obj);

        public abstract boolean isFullScreen(Object obj);

        public abstract boolean isPassword(Object obj);

        public abstract boolean isScrollable(Object obj);

        public abstract Object obtain();

        public abstract Object obtain(Object obj);

        public abstract void recycle(Object obj);

        public abstract void setAddedCount(Object obj, int i);

        public abstract void setBeforeText(Object obj, CharSequence charsequence);

        public abstract void setChecked(Object obj, boolean flag);

        public abstract void setClassName(Object obj, CharSequence charsequence);

        public abstract void setContentDescription(Object obj, CharSequence charsequence);

        public abstract void setCurrentItemIndex(Object obj, int i);

        public abstract void setEnabled(Object obj, boolean flag);

        public abstract void setFromIndex(Object obj, int i);

        public abstract void setFullScreen(Object obj, boolean flag);

        public abstract void setItemCount(Object obj, int i);

        public abstract void setMaxScrollX(Object obj, int i);

        public abstract void setMaxScrollY(Object obj, int i);

        public abstract void setParcelableData(Object obj, Parcelable parcelable);

        public abstract void setPassword(Object obj, boolean flag);

        public abstract void setRemovedCount(Object obj, int i);

        public abstract void setScrollX(Object obj, int i);

        public abstract void setScrollY(Object obj, int i);

        public abstract void setScrollable(Object obj, boolean flag);

        public abstract void setSource(Object obj, View view);

        public abstract void setSource(Object obj, View view, int i);

        public abstract void setToIndex(Object obj, int i);
    }


    public AccessibilityRecordCompat(Object obj)
    {
        mRecord = obj;
    }

    public static AccessibilityRecordCompat obtain()
    {
        return new AccessibilityRecordCompat(IMPL.obtain());
    }

    public static AccessibilityRecordCompat obtain(AccessibilityRecordCompat accessibilityrecordcompat)
    {
        return new AccessibilityRecordCompat(IMPL.obtain(accessibilityrecordcompat.mRecord));
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this != obj) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(obj == null)
            flag = false;
        else
        if(getClass() != obj.getClass())
        {
            flag = false;
        } else
        {
            AccessibilityRecordCompat accessibilityrecordcompat = (AccessibilityRecordCompat)obj;
            if(mRecord == null)
            {
                if(accessibilityrecordcompat.mRecord != null)
                    flag = false;
            } else
            if(!mRecord.equals(accessibilityrecordcompat.mRecord))
                flag = false;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public int getAddedCount()
    {
        return IMPL.getAddedCount(mRecord);
    }

    public CharSequence getBeforeText()
    {
        return IMPL.getBeforeText(mRecord);
    }

    public CharSequence getClassName()
    {
        return IMPL.getClassName(mRecord);
    }

    public CharSequence getContentDescription()
    {
        return IMPL.getContentDescription(mRecord);
    }

    public int getCurrentItemIndex()
    {
        return IMPL.getCurrentItemIndex(mRecord);
    }

    public int getFromIndex()
    {
        return IMPL.getFromIndex(mRecord);
    }

    public Object getImpl()
    {
        return mRecord;
    }

    public int getItemCount()
    {
        return IMPL.getItemCount(mRecord);
    }

    public int getMaxScrollX()
    {
        return IMPL.getMaxScrollX(mRecord);
    }

    public int getMaxScrollY()
    {
        return IMPL.getMaxScrollY(mRecord);
    }

    public Parcelable getParcelableData()
    {
        return IMPL.getParcelableData(mRecord);
    }

    public int getRemovedCount()
    {
        return IMPL.getRemovedCount(mRecord);
    }

    public int getScrollX()
    {
        return IMPL.getScrollX(mRecord);
    }

    public int getScrollY()
    {
        return IMPL.getScrollY(mRecord);
    }

    public AccessibilityNodeInfoCompat getSource()
    {
        return IMPL.getSource(mRecord);
    }

    public List getText()
    {
        return IMPL.getText(mRecord);
    }

    public int getToIndex()
    {
        return IMPL.getToIndex(mRecord);
    }

    public int getWindowId()
    {
        return IMPL.getWindowId(mRecord);
    }

    public int hashCode()
    {
        int i;
        if(mRecord == null)
            i = 0;
        else
            i = mRecord.hashCode();
        return i;
    }

    public boolean isChecked()
    {
        return IMPL.isChecked(mRecord);
    }

    public boolean isEnabled()
    {
        return IMPL.isEnabled(mRecord);
    }

    public boolean isFullScreen()
    {
        return IMPL.isFullScreen(mRecord);
    }

    public boolean isPassword()
    {
        return IMPL.isPassword(mRecord);
    }

    public boolean isScrollable()
    {
        return IMPL.isScrollable(mRecord);
    }

    public void recycle()
    {
        IMPL.recycle(mRecord);
    }

    public void setAddedCount(int i)
    {
        IMPL.setAddedCount(mRecord, i);
    }

    public void setBeforeText(CharSequence charsequence)
    {
        IMPL.setBeforeText(mRecord, charsequence);
    }

    public void setChecked(boolean flag)
    {
        IMPL.setChecked(mRecord, flag);
    }

    public void setClassName(CharSequence charsequence)
    {
        IMPL.setClassName(mRecord, charsequence);
    }

    public void setContentDescription(CharSequence charsequence)
    {
        IMPL.setContentDescription(mRecord, charsequence);
    }

    public void setCurrentItemIndex(int i)
    {
        IMPL.setCurrentItemIndex(mRecord, i);
    }

    public void setEnabled(boolean flag)
    {
        IMPL.setEnabled(mRecord, flag);
    }

    public void setFromIndex(int i)
    {
        IMPL.setFromIndex(mRecord, i);
    }

    public void setFullScreen(boolean flag)
    {
        IMPL.setFullScreen(mRecord, flag);
    }

    public void setItemCount(int i)
    {
        IMPL.setItemCount(mRecord, i);
    }

    public void setMaxScrollX(int i)
    {
        IMPL.setMaxScrollX(mRecord, i);
    }

    public void setMaxScrollY(int i)
    {
        IMPL.setMaxScrollY(mRecord, i);
    }

    public void setParcelableData(Parcelable parcelable)
    {
        IMPL.setParcelableData(mRecord, parcelable);
    }

    public void setPassword(boolean flag)
    {
        IMPL.setPassword(mRecord, flag);
    }

    public void setRemovedCount(int i)
    {
        IMPL.setRemovedCount(mRecord, i);
    }

    public void setScrollX(int i)
    {
        IMPL.setScrollX(mRecord, i);
    }

    public void setScrollY(int i)
    {
        IMPL.setScrollY(mRecord, i);
    }

    public void setScrollable(boolean flag)
    {
        IMPL.setScrollable(mRecord, flag);
    }

    public void setSource(View view)
    {
        IMPL.setSource(mRecord, view);
    }

    public void setSource(View view, int i)
    {
        IMPL.setSource(mRecord, view, i);
    }

    public void setToIndex(int i)
    {
        IMPL.setToIndex(mRecord, i);
    }

    private static final AccessibilityRecordImpl IMPL;
    private final Object mRecord;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 16)
            IMPL = new AccessibilityRecordJellyBeanImpl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 15)
            IMPL = new AccessibilityRecordIcsMr1Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 14)
            IMPL = new AccessibilityRecordIcsImpl();
        else
            IMPL = new AccessibilityRecordStubImpl();
    }
}
