// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package dslv;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package dslv:
//            Draggable

public class DragSortItemView extends ViewGroup
    implements Draggable
{

    public DragSortItemView(Context context)
    {
        super(context);
        mGravity = 48;
        setLayoutParams(new android.widget.AbsListView.LayoutParams(-1, -2));
        setClipChildren(true);
    }

    public boolean canDelete()
    {
        boolean flag = false;
        View view = getChildAt(0);
        if((view instanceof Draggable) && ((Draggable)view).canDelete())
            flag = true;
        return flag;
    }

    public boolean canReorder()
    {
        boolean flag = false;
        View view = getChildAt(0);
        if((view instanceof Draggable) && ((Draggable)view).canReorder())
            flag = true;
        return flag;
    }

    public int getGravity()
    {
        return mGravity;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        View view = getChildAt(0);
        if(view != null)
            if(mGravity == 48)
                view.layout(0, 0, getMeasuredWidth(), view.getMeasuredHeight());
            else
                view.layout(0, getMeasuredHeight() - view.getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight());
    }

    protected void onMeasure(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getSize(j);
        int l = android.view.View.MeasureSpec.getSize(i);
        int i1 = android.view.View.MeasureSpec.getMode(j);
        View view = getChildAt(0);
        if(view == null)
        {
            setMeasuredDimension(0, l);
        } else
        {
            if(view.isLayoutRequested())
                measureChild(view, i, android.view.View.MeasureSpec.makeMeasureSpec(0, 0));
            if(i1 == 0)
            {
                android.view.ViewGroup.LayoutParams layoutparams = getLayoutParams();
                if(layoutparams.height > 0)
                    k = layoutparams.height;
                else
                    k = view.getMeasuredHeight();
            }
            setMeasuredDimension(l, k);
        }
    }

    public void setGravity(int i)
    {
        mGravity = i;
    }

    private int mGravity;
}
