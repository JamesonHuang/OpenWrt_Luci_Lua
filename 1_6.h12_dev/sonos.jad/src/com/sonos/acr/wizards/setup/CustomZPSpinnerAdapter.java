// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.setup;

import android.content.Context;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.sonos.acr.util.SLog;
import java.util.ArrayList;

public class CustomZPSpinnerAdapter extends BaseAdapter
{
    class ViewHolder
    {

        TextView text;
        final CustomZPSpinnerAdapter this$0;

        ViewHolder()
        {
            this$0 = CustomZPSpinnerAdapter.this;
            super();
        }
    }


    public CustomZPSpinnerAdapter(Context context)
    {
        mNames = new ArrayList();
        mUrls = new ArrayList();
        mIds = new ArrayList();
        mFieldId = 0;
        spinner_item_id = 0x7f0300c0;
        spinner_dropdown_item_id = 0x1090009;
        mContext = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void addItem(String s, String s1, String s2)
    {
        mNames.add(s);
        mUrls.add(s1);
        mIds.add(s2);
    }

    public boolean areAllItemsSelectable()
    {
        return false;
    }

    public View createResource(int i, View view, ViewGroup viewgroup, int j)
    {
        View view1 = view;
        ViewHolder viewholder;
        if(view1 == null)
        {
            SLog.w("Inside Inflate:", "Creating Inflater");
            view1 = layoutInflater.inflate(j, viewgroup, false);
            viewholder = new ViewHolder();
            viewholder.text = (TextView)view1.findViewById(0x1020014);
            if(j != 0x1090008)
                if(j == 0x1090009);
            view1.setTag(viewholder);
        } else
        {
            viewholder = (ViewHolder)view1.getTag();
        }
        viewholder.text.setText(getItem(i).toString());
        return view1;
    }

    public int getCount()
    {
        return mNames.size();
    }

    public View getDropDownView(int i, View view, ViewGroup viewgroup)
    {
        if(!$assertionsDisabled && spinner_dropdown_item_id == 0)
            throw new AssertionError();
        else
            return createResource(i, view, viewgroup, spinner_dropdown_item_id);
    }

    public Object getItem(int i)
    {
        return mNames.get(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public String getItemURL(int i)
    {
        return (String)mUrls.get(i);
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        if(!$assertionsDisabled && spinner_item_id == 0)
            throw new AssertionError();
        else
            return createResource(i, view, viewgroup, spinner_item_id);
    }

    public String getZonePlayerId(int i)
    {
        return (String)mIds.get(i);
    }

    public void setResourceIds(int i, int j)
    {
        spinner_item_id = i;
        spinner_dropdown_item_id = j;
    }

    static final boolean $assertionsDisabled;
    LayoutInflater layoutInflater;
    Context mContext;
    int mDropDownResource;
    int mFieldId;
    ArrayList mIds;
    ArrayList mNames;
    ArrayList mUrls;
    int spinner_dropdown_item_id;
    int spinner_item_id;

    static 
    {
        boolean flag;
        if(!com/sonos/acr/wizards/setup/CustomZPSpinnerAdapter.desiredAssertionStatus())
            flag = true;
        else
            flag = false;
        $assertionsDisabled = flag;
    }
}
