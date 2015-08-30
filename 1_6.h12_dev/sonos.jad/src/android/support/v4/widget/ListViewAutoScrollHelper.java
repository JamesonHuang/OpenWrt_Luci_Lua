// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.view.View;
import android.widget.ListView;

// Referenced classes of package android.support.v4.widget:
//            AutoScrollHelper

public class ListViewAutoScrollHelper extends AutoScrollHelper
{

    public ListViewAutoScrollHelper(ListView listview)
    {
        super(listview);
        mTarget = listview;
    }

    public boolean canTargetScrollHorizontally(int i)
    {
        return false;
    }

    public boolean canTargetScrollVertically(int i)
    {
        boolean flag;
        ListView listview;
        int j;
        flag = false;
        listview = mTarget;
        j = listview.getCount();
        if(j != 0) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        int k = listview.getChildCount();
        int l = listview.getFirstVisiblePosition();
        int i1 = l + k;
        if(i <= 0 ? i < 0 && (l > 0 || listview.getChildAt(0).getTop() < 0) : i1 < j || listview.getChildAt(k - 1).getBottom() > listview.getHeight())
            flag = true;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void scrollTargetBy(int i, int j)
    {
        ListView listview;
        int k;
        listview = mTarget;
        k = listview.getFirstVisiblePosition();
        if(k != -1) goto _L2; else goto _L1
_L1:
        return;
_L2:
        View view = listview.getChildAt(0);
        if(view != null)
            listview.setSelectionFromTop(k, view.getTop() - j);
        if(true) goto _L1; else goto _L3
_L3:
    }

    private final ListView mTarget;
}
