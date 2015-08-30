// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uibusymanager;

import android.app.Activity;

// Referenced classes of package com.sonos.acr.uibusymanager:
//            AbsUiBusyManager

public class SimpleUiBusyManager extends AbsUiBusyManager
{

    public SimpleUiBusyManager(Activity activity)
    {
        super(activity);
    }

    public void start()
    {
        lockUI();
    }

    public void stop()
    {
        unlockUI();
    }
}
