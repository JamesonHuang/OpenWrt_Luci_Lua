// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.moremusic.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

// Referenced classes of package com.sonos.acr.moremusic.view:
//            WebNavigationView

public class ProgressWebNavigationView extends ProgressBar
    implements WebNavigationView
{

    public ProgressWebNavigationView(Context context)
    {
        super(context);
    }

    public ProgressWebNavigationView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public ProgressWebNavigationView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    public void resetButtons()
    {
    }

    public void setNavigationListener(WebNavigationView.WebNavigationListener webnavigationlistener)
    {
    }

    public void updateNavigationButtons(boolean flag, boolean flag1)
    {
    }

    public void updatePageLoaded(boolean flag)
    {
        byte byte0;
        if(flag)
            byte0 = 8;
        else
            byte0 = 0;
        setVisibility(byte0);
    }

    public void updateVisibility(boolean flag)
    {
    }
}
