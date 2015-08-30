// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.view.View;
import android.view.ViewGroup;

public final class HierarchyTreeChangeListener
    implements android.view.ViewGroup.OnHierarchyChangeListener
{

    private HierarchyTreeChangeListener(android.view.ViewGroup.OnHierarchyChangeListener onhierarchychangelistener)
    {
        if(onhierarchychangelistener == null)
        {
            throw new NullPointerException("Delegate must not be null.");
        } else
        {
            _flddelegate = onhierarchychangelistener;
            return;
        }
    }

    public static HierarchyTreeChangeListener wrap(android.view.ViewGroup.OnHierarchyChangeListener onhierarchychangelistener)
    {
        return new HierarchyTreeChangeListener(onhierarchychangelistener);
    }

    public void onChildViewAdded(View view, View view1)
    {
        _flddelegate.onChildViewAdded(view, view1);
        if(view1 instanceof ViewGroup)
        {
            ViewGroup viewgroup = (ViewGroup)view1;
            viewgroup.setOnHierarchyChangeListener(this);
            for(int i = 0; i < viewgroup.getChildCount(); i++)
                onChildViewAdded(((View) (viewgroup)), viewgroup.getChildAt(i));

        }
    }

    public void onChildViewRemoved(View view, View view1)
    {
        if(view1 instanceof ViewGroup)
        {
            ViewGroup viewgroup = (ViewGroup)view1;
            for(int i = 0; i < viewgroup.getChildCount(); i++)
                onChildViewRemoved(((View) (viewgroup)), viewgroup.getChildAt(i));

            viewgroup.setOnHierarchyChangeListener(null);
        }
        _flddelegate.onChildViewRemoved(view, view1);
    }

    private final android.view.ViewGroup.OnHierarchyChangeListener _flddelegate;
}
