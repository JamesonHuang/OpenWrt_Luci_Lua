// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package dslv;

import android.graphics.Point;
import android.view.View;

// Referenced classes of package dslv:
//            DragSortController, DragSortListView, Draggable

public class AllNodeController extends DragSortController
{

    public AllNodeController(DragSortListView dragsortlistview)
    {
        super(dragsortlistview);
    }

    public AllNodeController(DragSortListView dragsortlistview, int i, int j, int k)
    {
        super(dragsortlistview, i, j, k);
    }

    public AllNodeController(DragSortListView dragsortlistview, int i, int j, int k, int l)
    {
        super(dragsortlistview, i, j, k, l);
        dragSortListView = dragsortlistview;
    }

    public View onCreateFloatView(int i)
    {
        return super.onCreateFloatView(i);
    }

    public void onDragFloatView(View view, Point point, Point point1)
    {
        int i = dragSortListView.getFirstVisiblePosition();
        int j = dragSortListView.getDividerHeight();
        if(i == 0)
        {
            View view1 = dragSortListView.getChildAt(0);
            if((view1 instanceof Draggable) && !((Draggable)view1).canReorder())
            {
                int k = j + view1.getBottom();
                if(point.y < k)
                    point.y = k;
            }
        }
    }

    DragSortListView dragSortListView;
}
