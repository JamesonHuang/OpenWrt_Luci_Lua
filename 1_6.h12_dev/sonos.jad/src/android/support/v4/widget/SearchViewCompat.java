// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.content.ComponentName;
import android.content.Context;
import android.view.View;

// Referenced classes of package android.support.v4.widget:
//            SearchViewCompatIcs, SearchViewCompatHoneycomb

public class SearchViewCompat
{
    public static abstract class OnCloseListenerCompat
    {

        public boolean onClose()
        {
            return false;
        }

        final Object mListener = SearchViewCompat.IMPL.newOnCloseListener(this);

        public OnCloseListenerCompat()
        {
        }
    }

    public static abstract class OnQueryTextListenerCompat
    {

        public boolean onQueryTextChange(String s)
        {
            return false;
        }

        public boolean onQueryTextSubmit(String s)
        {
            return false;
        }

        final Object mListener = SearchViewCompat.IMPL.newOnQueryTextListener(this);

        public OnQueryTextListenerCompat()
        {
        }
    }

    static class SearchViewCompatIcsImpl extends SearchViewCompatHoneycombImpl
    {

        public View newSearchView(Context context)
        {
            return SearchViewCompatIcs.newSearchView(context);
        }

        public void setImeOptions(View view, int i)
        {
            SearchViewCompatIcs.setImeOptions(view, i);
        }

        public void setInputType(View view, int i)
        {
            SearchViewCompatIcs.setInputType(view, i);
        }

        SearchViewCompatIcsImpl()
        {
        }
    }

    static class SearchViewCompatHoneycombImpl extends SearchViewCompatStubImpl
    {

        public CharSequence getQuery(View view)
        {
            return SearchViewCompatHoneycomb.getQuery(view);
        }

        public boolean isIconified(View view)
        {
            return SearchViewCompatHoneycomb.isIconified(view);
        }

        public boolean isQueryRefinementEnabled(View view)
        {
            return SearchViewCompatHoneycomb.isQueryRefinementEnabled(view);
        }

        public boolean isSubmitButtonEnabled(View view)
        {
            return SearchViewCompatHoneycomb.isSubmitButtonEnabled(view);
        }

        public Object newOnCloseListener(final OnCloseListenerCompat listener)
        {
            return SearchViewCompatHoneycomb.newOnCloseListener(new SearchViewCompatHoneycomb.OnCloseListenerCompatBridge() {

                public boolean onClose()
                {
                    return listener.onClose();
                }

                final SearchViewCompatHoneycombImpl this$0;
                final OnCloseListenerCompat val$listener;

                
                {
                    this$0 = SearchViewCompatHoneycombImpl.this;
                    listener = oncloselistenercompat;
                    super();
                }
            }
);
        }

        public Object newOnQueryTextListener(final OnQueryTextListenerCompat listener)
        {
            return SearchViewCompatHoneycomb.newOnQueryTextListener(new SearchViewCompatHoneycomb.OnQueryTextListenerCompatBridge() {

                public boolean onQueryTextChange(String s)
                {
                    return listener.onQueryTextChange(s);
                }

                public boolean onQueryTextSubmit(String s)
                {
                    return listener.onQueryTextSubmit(s);
                }

                final SearchViewCompatHoneycombImpl this$0;
                final OnQueryTextListenerCompat val$listener;

                
                {
                    this$0 = SearchViewCompatHoneycombImpl.this;
                    listener = onquerytextlistenercompat;
                    super();
                }
            }
);
        }

        public View newSearchView(Context context)
        {
            return SearchViewCompatHoneycomb.newSearchView(context);
        }

        public void setIconified(View view, boolean flag)
        {
            SearchViewCompatHoneycomb.setIconified(view, flag);
        }

        public void setMaxWidth(View view, int i)
        {
            SearchViewCompatHoneycomb.setMaxWidth(view, i);
        }

        public void setOnCloseListener(Object obj, Object obj1)
        {
            SearchViewCompatHoneycomb.setOnCloseListener(obj, obj1);
        }

        public void setOnQueryTextListener(Object obj, Object obj1)
        {
            SearchViewCompatHoneycomb.setOnQueryTextListener(obj, obj1);
        }

        public void setQuery(View view, CharSequence charsequence, boolean flag)
        {
            SearchViewCompatHoneycomb.setQuery(view, charsequence, flag);
        }

        public void setQueryHint(View view, CharSequence charsequence)
        {
            SearchViewCompatHoneycomb.setQueryHint(view, charsequence);
        }

        public void setQueryRefinementEnabled(View view, boolean flag)
        {
            SearchViewCompatHoneycomb.setQueryRefinementEnabled(view, flag);
        }

        public void setSearchableInfo(View view, ComponentName componentname)
        {
            SearchViewCompatHoneycomb.setSearchableInfo(view, componentname);
        }

        public void setSubmitButtonEnabled(View view, boolean flag)
        {
            SearchViewCompatHoneycomb.setSubmitButtonEnabled(view, flag);
        }

        SearchViewCompatHoneycombImpl()
        {
        }
    }

    static class SearchViewCompatStubImpl
        implements SearchViewCompatImpl
    {

        public CharSequence getQuery(View view)
        {
            return null;
        }

        public boolean isIconified(View view)
        {
            return true;
        }

        public boolean isQueryRefinementEnabled(View view)
        {
            return false;
        }

        public boolean isSubmitButtonEnabled(View view)
        {
            return false;
        }

        public Object newOnCloseListener(OnCloseListenerCompat oncloselistenercompat)
        {
            return null;
        }

        public Object newOnQueryTextListener(OnQueryTextListenerCompat onquerytextlistenercompat)
        {
            return null;
        }

        public View newSearchView(Context context)
        {
            return null;
        }

        public void setIconified(View view, boolean flag)
        {
        }

        public void setImeOptions(View view, int i)
        {
        }

        public void setInputType(View view, int i)
        {
        }

        public void setMaxWidth(View view, int i)
        {
        }

        public void setOnCloseListener(Object obj, Object obj1)
        {
        }

        public void setOnQueryTextListener(Object obj, Object obj1)
        {
        }

        public void setQuery(View view, CharSequence charsequence, boolean flag)
        {
        }

        public void setQueryHint(View view, CharSequence charsequence)
        {
        }

        public void setQueryRefinementEnabled(View view, boolean flag)
        {
        }

        public void setSearchableInfo(View view, ComponentName componentname)
        {
        }

        public void setSubmitButtonEnabled(View view, boolean flag)
        {
        }

        SearchViewCompatStubImpl()
        {
        }
    }

    static interface SearchViewCompatImpl
    {

        public abstract CharSequence getQuery(View view);

        public abstract boolean isIconified(View view);

        public abstract boolean isQueryRefinementEnabled(View view);

        public abstract boolean isSubmitButtonEnabled(View view);

        public abstract Object newOnCloseListener(OnCloseListenerCompat oncloselistenercompat);

        public abstract Object newOnQueryTextListener(OnQueryTextListenerCompat onquerytextlistenercompat);

        public abstract View newSearchView(Context context);

        public abstract void setIconified(View view, boolean flag);

        public abstract void setImeOptions(View view, int i);

        public abstract void setInputType(View view, int i);

        public abstract void setMaxWidth(View view, int i);

        public abstract void setOnCloseListener(Object obj, Object obj1);

        public abstract void setOnQueryTextListener(Object obj, Object obj1);

        public abstract void setQuery(View view, CharSequence charsequence, boolean flag);

        public abstract void setQueryHint(View view, CharSequence charsequence);

        public abstract void setQueryRefinementEnabled(View view, boolean flag);

        public abstract void setSearchableInfo(View view, ComponentName componentname);

        public abstract void setSubmitButtonEnabled(View view, boolean flag);
    }


    private SearchViewCompat(Context context)
    {
    }

    public static CharSequence getQuery(View view)
    {
        return IMPL.getQuery(view);
    }

    public static boolean isIconified(View view)
    {
        return IMPL.isIconified(view);
    }

    public static boolean isQueryRefinementEnabled(View view)
    {
        return IMPL.isQueryRefinementEnabled(view);
    }

    public static boolean isSubmitButtonEnabled(View view)
    {
        return IMPL.isSubmitButtonEnabled(view);
    }

    public static View newSearchView(Context context)
    {
        return IMPL.newSearchView(context);
    }

    public static void setIconified(View view, boolean flag)
    {
        IMPL.setIconified(view, flag);
    }

    public static void setImeOptions(View view, int i)
    {
        IMPL.setImeOptions(view, i);
    }

    public static void setInputType(View view, int i)
    {
        IMPL.setInputType(view, i);
    }

    public static void setMaxWidth(View view, int i)
    {
        IMPL.setMaxWidth(view, i);
    }

    public static void setOnCloseListener(View view, OnCloseListenerCompat oncloselistenercompat)
    {
        IMPL.setOnCloseListener(view, oncloselistenercompat.mListener);
    }

    public static void setOnQueryTextListener(View view, OnQueryTextListenerCompat onquerytextlistenercompat)
    {
        IMPL.setOnQueryTextListener(view, onquerytextlistenercompat.mListener);
    }

    public static void setQuery(View view, CharSequence charsequence, boolean flag)
    {
        IMPL.setQuery(view, charsequence, flag);
    }

    public static void setQueryHint(View view, CharSequence charsequence)
    {
        IMPL.setQueryHint(view, charsequence);
    }

    public static void setQueryRefinementEnabled(View view, boolean flag)
    {
        IMPL.setQueryRefinementEnabled(view, flag);
    }

    public static void setSearchableInfo(View view, ComponentName componentname)
    {
        IMPL.setSearchableInfo(view, componentname);
    }

    public static void setSubmitButtonEnabled(View view, boolean flag)
    {
        IMPL.setSubmitButtonEnabled(view, flag);
    }

    private static final SearchViewCompatImpl IMPL;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 14)
            IMPL = new SearchViewCompatIcsImpl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 11)
            IMPL = new SearchViewCompatHoneycombImpl();
        else
            IMPL = new SearchViewCompatStubImpl();
    }

}
