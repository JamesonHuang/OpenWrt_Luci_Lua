// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.sonos.acr.browse.v2.actions.ActionItem;
import com.sonos.acr.browse.v2.actions.ActionSet;
import com.sonos.acr.util.ActionMenuUtils;
import com.sonos.sclib.SCIBrowseItem;
import com.sonos.sclib.sclibConstants;
import dslv.Draggable;

// Referenced classes of package com.sonos.acr.browse.v2.view:
//            BrowseItemListViewCell

public class BrowseEditItemListViewCell extends BrowseItemListViewCell
    implements Draggable
{

    public BrowseEditItemListViewCell(Context context)
    {
        super(context);
        editable = false;
        int ai[] = new int[2];
        ai[0] = 0;
        ai[1] = 0;
        locationInWindow = ai;
    }

    public BrowseEditItemListViewCell(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        editable = false;
        int ai[] = new int[2];
        ai[0] = 0;
        ai[1] = 0;
        locationInWindow = ai;
    }

    public BrowseEditItemListViewCell(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        editable = false;
        int ai[] = new int[2];
        ai[0] = 0;
        ai[1] = 0;
        locationInWindow = ai;
    }

    public boolean canDelete()
    {
        boolean flag;
        if(browseItem != null && canDeleteItem(browseItem))
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected boolean canDeleteItem(SCIBrowseItem scibrowseitem)
    {
        boolean flag;
        if(isInEditMode() && scibrowseitem != null && ActionMenuUtils.getDeleteAction(scibrowseitem) != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean canReorder()
    {
        boolean flag;
        if(browseItem != null && canReorderItem(browseItem))
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected boolean canReorderItem(SCIBrowseItem scibrowseitem)
    {
        boolean flag;
        if(isInEditMode() && scibrowseitem != null && ActionMenuUtils.getReorderAction(scibrowseitem) != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected int getLayoutId()
    {
        return 0x7f030008;
    }

    protected void init()
    {
        super.init();
        deleteTrackButton = (ImageView)findViewById(0x7f0a003c);
        deleteButtonContainer = (FrameLayout)findViewById(0x7f0a003b);
        itemDragger = (ImageView)findViewById(0x7f0a003d);
        deleteTrackButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                onDeleteClicked();
            }

            final BrowseEditItemListViewCell this$0;

            
            {
                this$0 = BrowseEditItemListViewCell.this;
                super();
            }
        }
);
        setClipToPadding(false);
    }

    public boolean isInEditMode()
    {
        return editable;
    }

    protected void onDeleteClicked()
    {
        if(browseItem != null)
        {
            ActionItem actionitem = (new ActionSet(browseItem.getActions())).findActionById(sclibConstants.SC_ACTIONID_DELETE_ITEM);
            if(actionitem != null)
                actionitem.perform();
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        boolean flag = false;
        if(itemDragger == null || !isInEditMode() || itemDragger.getVisibility() != 0 || motionevent.getAction() != 0) goto _L2; else goto _L1
_L1:
        float f;
        float f1;
        itemDragger.getLocationOnScreen(locationInWindow);
        f = motionevent.getRawX();
        f1 = motionevent.getRawY();
        if(f <= (float)locationInWindow[0] || f >= (float)(locationInWindow[0] + itemDragger.getWidth()) || f1 <= (float)locationInWindow[1] || f1 >= (float)(locationInWindow[1] + itemDragger.getHeight())) goto _L2; else goto _L3
_L3:
        return flag;
_L2:
        flag = super.onTouchEvent(motionevent);
        if(true) goto _L3; else goto _L4
_L4:
    }

    public final void setEditState(boolean flag)
    {
        if(editable != flag)
        {
            editable = flag;
            onEditModeChanged();
        }
    }

    protected void updateEditButtons(SCIBrowseItem scibrowseitem)
    {
        byte byte0 = 4;
        if(canDeleteItem(scibrowseitem))
        {
            deleteTrackButton.setVisibility(0);
            deleteButtonContainer.setVisibility(0);
        } else
        {
            ImageView imageview = deleteTrackButton;
            byte byte1;
            FrameLayout framelayout;
            if(isInEditMode())
                byte1 = byte0;
            else
                byte1 = 8;
            imageview.setVisibility(byte1);
            framelayout = deleteButtonContainer;
            if(!isInEditMode())
                byte0 = 8;
            framelayout.setVisibility(byte0);
        }
        if(itemDragger != null)
            if(canReorderItem(scibrowseitem))
                itemDragger.setVisibility(0);
            else
                itemDragger.setVisibility(8);
    }

    protected void updateViews(SCIBrowseItem scibrowseitem)
    {
        super.updateViews(scibrowseitem);
        updateEditButtons(scibrowseitem);
    }

    protected FrameLayout deleteButtonContainer;
    protected ImageView deleteTrackButton;
    protected boolean editable;
    protected ImageView itemDragger;
    private final int locationInWindow[];
}
