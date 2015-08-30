// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.view.View;
import android.widget.SearchView;

class SearchViewCompatHoneycomb
{
    static interface OnCloseListenerCompatBridge
    {

        public abstract boolean onClose();
    }

    static interface OnQueryTextListenerCompatBridge
    {

        public abstract boolean onQueryTextChange(String s);

        public abstract boolean onQueryTextSubmit(String s);
    }


    SearchViewCompatHoneycomb()
    {
    }

    public static CharSequence getQuery(View view)
    {
        return ((SearchView)view).getQuery();
    }

    public static boolean isIconified(View view)
    {
        return ((SearchView)view).isIconified();
    }

    public static boolean isQueryRefinementEnabled(View view)
    {
        return ((SearchView)view).isQueryRefinementEnabled();
    }

    public static boolean isSubmitButtonEnabled(View view)
    {
        return ((SearchView)view).isSubmitButtonEnabled();
    }

    public static Object newOnCloseListener(final OnCloseListenerCompatBridge listener)
    {
        return new android.widget.SearchView.OnCloseListener() {

            public boolean onClose()
            {
                return listener.onClose();
            }

            final OnCloseListenerCompatBridge val$listener;

            
            {
                listener = oncloselistenercompatbridge;
                super();
            }
        }
;
    }

    public static Object newOnQueryTextListener(final OnQueryTextListenerCompatBridge listener)
    {
        return new android.widget.SearchView.OnQueryTextListener() {

            public boolean onQueryTextChange(String s)
            {
                return listener.onQueryTextChange(s);
            }

            public boolean onQueryTextSubmit(String s)
            {
                return listener.onQueryTextSubmit(s);
            }

            final OnQueryTextListenerCompatBridge val$listener;

            
            {
                listener = onquerytextlistenercompatbridge;
                super();
            }
        }
;
    }

    public static View newSearchView(Context context)
    {
        return new SearchView(context);
    }

    public static void setIconified(View view, boolean flag)
    {
        ((SearchView)view).setIconified(flag);
    }

    public static void setMaxWidth(View view, int i)
    {
        ((SearchView)view).setMaxWidth(i);
    }

    public static void setOnCloseListener(Object obj, Object obj1)
    {
        ((SearchView)obj).setOnCloseListener((android.widget.SearchView.OnCloseListener)obj1);
    }

    public static void setOnQueryTextListener(Object obj, Object obj1)
    {
        ((SearchView)obj).setOnQueryTextListener((android.widget.SearchView.OnQueryTextListener)obj1);
    }

    public static void setQuery(View view, CharSequence charsequence, boolean flag)
    {
        ((SearchView)view).setQuery(charsequence, flag);
    }

    public static void setQueryHint(View view, CharSequence charsequence)
    {
        ((SearchView)view).setQueryHint(charsequence);
    }

    public static void setQueryRefinementEnabled(View view, boolean flag)
    {
        ((SearchView)view).setQueryRefinementEnabled(flag);
    }

    public static void setSearchableInfo(View view, ComponentName componentname)
    {
        SearchView searchview = (SearchView)view;
        searchview.setSearchableInfo(((SearchManager)searchview.getContext().getSystemService("search")).getSearchableInfo(componentname));
    }

    public static void setSubmitButtonEnabled(View view, boolean flag)
    {
        ((SearchView)view).setSubmitButtonEnabled(flag);
    }
}
