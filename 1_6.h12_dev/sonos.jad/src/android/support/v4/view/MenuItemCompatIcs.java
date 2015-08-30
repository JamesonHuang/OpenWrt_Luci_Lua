// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.view.MenuItem;

class MenuItemCompatIcs
{
    static class OnActionExpandListenerWrapper
        implements android.view.MenuItem.OnActionExpandListener
    {

        public boolean onMenuItemActionCollapse(MenuItem menuitem)
        {
            return mWrapped.onMenuItemActionCollapse(menuitem);
        }

        public boolean onMenuItemActionExpand(MenuItem menuitem)
        {
            return mWrapped.onMenuItemActionExpand(menuitem);
        }

        private SupportActionExpandProxy mWrapped;

        public OnActionExpandListenerWrapper(SupportActionExpandProxy supportactionexpandproxy)
        {
            mWrapped = supportactionexpandproxy;
        }
    }

    static interface SupportActionExpandProxy
    {

        public abstract boolean onMenuItemActionCollapse(MenuItem menuitem);

        public abstract boolean onMenuItemActionExpand(MenuItem menuitem);
    }


    MenuItemCompatIcs()
    {
    }

    public static boolean collapseActionView(MenuItem menuitem)
    {
        return menuitem.collapseActionView();
    }

    public static boolean expandActionView(MenuItem menuitem)
    {
        return menuitem.expandActionView();
    }

    public static boolean isActionViewExpanded(MenuItem menuitem)
    {
        return menuitem.isActionViewExpanded();
    }

    public static MenuItem setOnActionExpandListener(MenuItem menuitem, SupportActionExpandProxy supportactionexpandproxy)
    {
        return menuitem.setOnActionExpandListener(new OnActionExpandListenerWrapper(supportactionexpandproxy));
    }
}
