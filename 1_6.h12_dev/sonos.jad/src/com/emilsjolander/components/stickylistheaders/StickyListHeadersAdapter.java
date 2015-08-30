// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.emilsjolander.components.stickylistheaders;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

public interface StickyListHeadersAdapter
    extends ListAdapter
{

    public abstract long getHeaderId(int i);

    public abstract View getHeaderView(int i, View view, ViewGroup viewgroup);
}
