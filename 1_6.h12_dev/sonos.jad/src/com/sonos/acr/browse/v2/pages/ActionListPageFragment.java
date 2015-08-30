// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import android.content.Context;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.sonos.acr.browse.v2.PageFragment;
import com.sonos.acr.browse.v2.actions.ActionItem;
import com.sonos.acr.browse.v2.actions.ActionSet;
import com.sonos.acr.browse.v2.actions.sclib.SCLibSelectableActionItem;
import com.sonos.acr.util.SLog;
import java.util.ArrayList;

public class ActionListPageFragment extends PageFragment
{
    private class ActionAdapter extends BaseAdapter
    {

        public int getCount()
        {
            return actionList.size();
        }

        public ActionItem getItem(int i)
        {
            return (ActionItem)actionList.get(i);
        }

        public volatile Object getItem(int i)
        {
            return getItem(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public View getView(final int finalPosition, View view, ViewGroup viewgroup)
        {
            ActionItem actionitem = getItem(finalPosition);
            TextView textview;
            if(view instanceof TextView)
                textview = (TextView)view;
            else
                textview = (TextView)LayoutInflater.from(
// JavaClassFileOutputException: get_constant: invalid tag

        ArrayList actionList;
        final ActionListPageFragment this$0;

        private ActionAdapter(ActionSet actionset)
        {
            this$0 = ActionListPageFragment.this;
            super();
            actionList = actionset.getActions();
        }


        // Unreferenced inner class com/sonos/acr/browse/v2/pages/ActionListPageFragment$ActionAdapter$1

/* anonymous class */
        class _cls1
            implements android.view.View.OnClickListener
        {

            public void onClick(View view1)
            {
                onItemClick(getItem(finalPosition));
            }

            final ActionAdapter this$1;
            final int val$finalPosition;

                
                {
                    this$1 = ActionAdapter.this;
                    finalPosition = i;
                    super();
                }
        }

    }

    public static interface OnActionClickListener
    {

        public abstract void onActionClick(ActionItem actionitem);
    }


    public ActionListPageFragment(int i, ActionSet actionset)
    {
        PageFragment(i);
        actionList = actionset;
    }

    public ActionListPageFragment(ActionSet actionset)
    {
        actionList = actionset;
    }

    protected int getCellLayoutId()
    {
        return 0x7f030003;
    }

    public int getLayoutId()
    {
        return 0x7f030002;
    }

    public boolean isGone()
    {
        boolean flag;
        if(actionList != null && !actionList.isEmpty())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public View onCreateThemedView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        View view = layoutinflater.inflate(getLayoutId(), viewgroup, false);
        SLog.i(LOG_TAG, "onCreateThemeViewCalled");
        adapterView = (LinearLayout)view.findViewById(0x7f0a0031);
        actionAdapter = new ActionAdapter(actionList);
        for(int i = 0; i < actionAdapter.getCount(); i++)
            adapterView.addView(actionAdapter.getView(i, null, adapterView));

        return view;
    }

    public void onItemClick(ActionItem actionitem)
    {
        if(clickListener != null)
            clickListener.onActionClick(actionitem);
    }

    public void setClickListener(OnActionClickListener onactionclicklistener)
    {
        clickListener = onactionclicklistener;
    }

    private ActionAdapter actionAdapter;
    ActionSet actionList;
    private LinearLayout adapterView;
    OnActionClickListener clickListener;

}
