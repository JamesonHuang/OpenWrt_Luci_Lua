// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.sonos.acr.browse.v2.actions.ActionItem;
import com.sonos.acr.browse.v2.actions.ActionSet;
import com.sonos.acr.view.DividerLinearLayout;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.browse.v2.view:
//            ActionMenu

public abstract class AbsActionMenu extends DividerLinearLayout
    implements ActionMenu
{
    public class ClickActionHandler extends ActionItem
    {

        public String getActionID()
        {
            return wrappedItem.getActionID();
        }

        public String getCategory()
        {
            return wrappedItem.getCategory();
        }

        public String getLabel()
        {
            return wrappedItem.getLabel();
        }

        public boolean isEnabled()
        {
            return wrappedItem.isEnabled();
        }

        public void onClick(View view)
        {
            perform();
        }

        public void perform()
        {
            wrappedItem.perform();
        }

        final AbsActionMenu this$0;
        ActionItem wrappedItem;

        public ClickActionHandler(ActionItem actionitem)
        {
            this$0 = AbsActionMenu.this;
            super();
            wrappedItem = actionitem;
        }
    }

    public static interface ActionListener
    {

        public abstract void onPerformAction();
    }


    protected AbsActionMenu(Context context)
    {
        super(context);
    }

    protected AbsActionMenu(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected AbsActionMenu(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    public View addActionItem(ActionItem actionitem)
    {
        View view;
        if(actionitem instanceof ClickActionHandler)
            view = addActionItem((ClickActionHandler)actionitem);
        else
            view = addActionItem(new ClickActionHandler(actionitem));
        return view;
    }

    public abstract View addActionItem(ClickActionHandler clickactionhandler);

    public void setActions(ActionSet actionset)
    {
        clearActionItems();
        for(Iterator iterator = actionset.getActions().iterator(); iterator.hasNext(); addActionItem((ActionItem)iterator.next()));
    }
}
