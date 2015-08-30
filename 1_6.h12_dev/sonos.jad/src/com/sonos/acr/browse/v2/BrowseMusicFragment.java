// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2;

import android.content.res.Configuration;
import com.sonos.acr.browse.v2.pages.BrowseFlipperPageFragment;
import com.sonos.acr.browse.v2.pages.DataSourcePageFragment;
import java.util.Stack;

// Referenced classes of package com.sonos.acr.browse.v2:
//            PageFragment

public class BrowseMusicFragment extends BrowseFlipperPageFragment
{

    public BrowseMusicFragment()
    {
        this(0x7f010055);
        setPaddingBasedOnConfiguration();
    }

    public BrowseMusicFragment(int i)
    {
        this(i, 0x7f030011);
        setPaddingBasedOnConfiguration();
    }

    public BrowseMusicFragment(int i, int j)
    {
        super(i, j);
        setPaddingBasedOnConfiguration();
    }

    public String getActionFilter(boolean flag)
    {
        return ((PageFragment)pages.peek()).getActionFilter(flag);
    }

    public String getTopSCUri()
    {
        String s;
        if(!pages.empty() && (pages.peek() instanceof DataSourcePageFragment))
            s = ((DataSourcePageFragment)pages.peek()).getSCUri();
        else
            s = null;
        return s;
    }

    public boolean hasFooterMenu()
    {
        boolean flag;
        if(!pages.empty() && ((PageFragment)pages.peek()).hasFooterMenu())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        setPaddingBasedOnConfiguration();
    }

    public void onPageInvalidated(PageFragment pagefragment)
    {
        super.onPageInvalidated(pagefragment);
        setPaddingBasedOnConfiguration();
    }

    public void onPageUpdated(PageFragment pagefragment)
    {
        super.onPageUpdated(pagefragment);
        setPaddingBasedOnConfiguration();
    }

    public void setPaddingBasedOnConfiguration()
    {
    }
}
