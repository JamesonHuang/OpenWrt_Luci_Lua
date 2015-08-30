// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.support.v4.internal.view.SupportMenuItem;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

// Referenced classes of package android.support.v4.view:
//            ActionProvider, MenuItemCompatIcs, MenuItemCompatHoneycomb

public class MenuItemCompat
{
    static class IcsMenuVersionImpl extends HoneycombMenuVersionImpl
    {

        public boolean collapseActionView(MenuItem menuitem)
        {
            return MenuItemCompatIcs.collapseActionView(menuitem);
        }

        public boolean expandActionView(MenuItem menuitem)
        {
            return MenuItemCompatIcs.expandActionView(menuitem);
        }

        public boolean isActionViewExpanded(MenuItem menuitem)
        {
            return MenuItemCompatIcs.isActionViewExpanded(menuitem);
        }

        public MenuItem setOnActionExpandListener(MenuItem menuitem, final OnActionExpandListener listener)
        {
            MenuItem menuitem1;
            if(listener == null)
                menuitem1 = MenuItemCompatIcs.setOnActionExpandListener(menuitem, null);
            else
                menuitem1 = MenuItemCompatIcs.setOnActionExpandListener(menuitem, new MenuItemCompatIcs.SupportActionExpandProxy() {

                    public boolean onMenuItemActionCollapse(MenuItem menuitem2)
                    {
                        return listener.onMenuItemActionCollapse(menuitem2);
                    }

                    public boolean onMenuItemActionExpand(MenuItem menuitem2)
                    {
                        return listener.onMenuItemActionExpand(menuitem2);
                    }

                    final IcsMenuVersionImpl this$0;
                    final OnActionExpandListener val$listener;

                
                {
                    this$0 = IcsMenuVersionImpl.this;
                    listener = onactionexpandlistener;
                    super();
                }
                }
);
            return menuitem1;
        }

        IcsMenuVersionImpl()
        {
        }
    }

    static class HoneycombMenuVersionImpl
        implements MenuVersionImpl
    {

        public boolean collapseActionView(MenuItem menuitem)
        {
            return false;
        }

        public boolean expandActionView(MenuItem menuitem)
        {
            return false;
        }

        public View getActionView(MenuItem menuitem)
        {
            return MenuItemCompatHoneycomb.getActionView(menuitem);
        }

        public boolean isActionViewExpanded(MenuItem menuitem)
        {
            return false;
        }

        public MenuItem setActionView(MenuItem menuitem, int i)
        {
            return MenuItemCompatHoneycomb.setActionView(menuitem, i);
        }

        public MenuItem setActionView(MenuItem menuitem, View view)
        {
            return MenuItemCompatHoneycomb.setActionView(menuitem, view);
        }

        public MenuItem setOnActionExpandListener(MenuItem menuitem, OnActionExpandListener onactionexpandlistener)
        {
            return menuitem;
        }

        public void setShowAsAction(MenuItem menuitem, int i)
        {
            MenuItemCompatHoneycomb.setShowAsAction(menuitem, i);
        }

        HoneycombMenuVersionImpl()
        {
        }
    }

    static class BaseMenuVersionImpl
        implements MenuVersionImpl
    {

        public boolean collapseActionView(MenuItem menuitem)
        {
            return false;
        }

        public boolean expandActionView(MenuItem menuitem)
        {
            return false;
        }

        public View getActionView(MenuItem menuitem)
        {
            return null;
        }

        public boolean isActionViewExpanded(MenuItem menuitem)
        {
            return false;
        }

        public MenuItem setActionView(MenuItem menuitem, int i)
        {
            return menuitem;
        }

        public MenuItem setActionView(MenuItem menuitem, View view)
        {
            return menuitem;
        }

        public MenuItem setOnActionExpandListener(MenuItem menuitem, OnActionExpandListener onactionexpandlistener)
        {
            return menuitem;
        }

        public void setShowAsAction(MenuItem menuitem, int i)
        {
        }

        BaseMenuVersionImpl()
        {
        }
    }

    public static interface OnActionExpandListener
    {

        public abstract boolean onMenuItemActionCollapse(MenuItem menuitem);

        public abstract boolean onMenuItemActionExpand(MenuItem menuitem);
    }

    static interface MenuVersionImpl
    {

        public abstract boolean collapseActionView(MenuItem menuitem);

        public abstract boolean expandActionView(MenuItem menuitem);

        public abstract View getActionView(MenuItem menuitem);

        public abstract boolean isActionViewExpanded(MenuItem menuitem);

        public abstract MenuItem setActionView(MenuItem menuitem, int i);

        public abstract MenuItem setActionView(MenuItem menuitem, View view);

        public abstract MenuItem setOnActionExpandListener(MenuItem menuitem, OnActionExpandListener onactionexpandlistener);

        public abstract void setShowAsAction(MenuItem menuitem, int i);
    }


    public MenuItemCompat()
    {
    }

    public static boolean collapseActionView(MenuItem menuitem)
    {
        boolean flag;
        if(menuitem instanceof SupportMenuItem)
            flag = ((SupportMenuItem)menuitem).collapseActionView();
        else
            flag = IMPL.collapseActionView(menuitem);
        return flag;
    }

    public static boolean expandActionView(MenuItem menuitem)
    {
        boolean flag;
        if(menuitem instanceof SupportMenuItem)
            flag = ((SupportMenuItem)menuitem).expandActionView();
        else
            flag = IMPL.expandActionView(menuitem);
        return flag;
    }

    public static ActionProvider getActionProvider(MenuItem menuitem)
    {
        ActionProvider actionprovider;
        if(menuitem instanceof SupportMenuItem)
        {
            actionprovider = ((SupportMenuItem)menuitem).getSupportActionProvider();
        } else
        {
            Log.w("MenuItemCompat", "getActionProvider: item does not implement SupportMenuItem; returning null");
            actionprovider = null;
        }
        return actionprovider;
    }

    public static View getActionView(MenuItem menuitem)
    {
        View view;
        if(menuitem instanceof SupportMenuItem)
            view = ((SupportMenuItem)menuitem).getActionView();
        else
            view = IMPL.getActionView(menuitem);
        return view;
    }

    public static boolean isActionViewExpanded(MenuItem menuitem)
    {
        boolean flag;
        if(menuitem instanceof SupportMenuItem)
            flag = ((SupportMenuItem)menuitem).isActionViewExpanded();
        else
            flag = IMPL.isActionViewExpanded(menuitem);
        return flag;
    }

    public static MenuItem setActionProvider(MenuItem menuitem, ActionProvider actionprovider)
    {
        if(menuitem instanceof SupportMenuItem)
            menuitem = ((SupportMenuItem)menuitem).setSupportActionProvider(actionprovider);
        else
            Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return menuitem;
    }

    public static MenuItem setActionView(MenuItem menuitem, int i)
    {
        MenuItem menuitem1;
        if(menuitem instanceof SupportMenuItem)
            menuitem1 = ((SupportMenuItem)menuitem).setActionView(i);
        else
            menuitem1 = IMPL.setActionView(menuitem, i);
        return menuitem1;
    }

    public static MenuItem setActionView(MenuItem menuitem, View view)
    {
        MenuItem menuitem1;
        if(menuitem instanceof SupportMenuItem)
            menuitem1 = ((SupportMenuItem)menuitem).setActionView(view);
        else
            menuitem1 = IMPL.setActionView(menuitem, view);
        return menuitem1;
    }

    public static MenuItem setOnActionExpandListener(MenuItem menuitem, OnActionExpandListener onactionexpandlistener)
    {
        Object obj;
        if(menuitem instanceof SupportMenuItem)
            obj = ((SupportMenuItem)menuitem).setSupportOnActionExpandListener(onactionexpandlistener);
        else
            obj = IMPL.setOnActionExpandListener(menuitem, onactionexpandlistener);
        return ((MenuItem) (obj));
    }

    public static void setShowAsAction(MenuItem menuitem, int i)
    {
        if(menuitem instanceof SupportMenuItem)
            ((SupportMenuItem)menuitem).setShowAsAction(i);
        else
            IMPL.setShowAsAction(menuitem, i);
    }

    static final MenuVersionImpl IMPL;
    public static final int SHOW_AS_ACTION_ALWAYS = 2;
    public static final int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;
    public static final int SHOW_AS_ACTION_IF_ROOM = 1;
    public static final int SHOW_AS_ACTION_NEVER = 0;
    public static final int SHOW_AS_ACTION_WITH_TEXT = 4;
    private static final String TAG = "MenuItemCompat";

    static 
    {
        int i = android.os.Build.VERSION.SDK_INT;
        if(i >= 14)
            IMPL = new IcsMenuVersionImpl();
        else
        if(i >= 11)
            IMPL = new HoneycombMenuVersionImpl();
        else
            IMPL = new BaseMenuVersionImpl();
    }
}
