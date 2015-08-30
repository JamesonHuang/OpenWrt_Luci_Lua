// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content;

import android.content.ComponentName;
import android.content.Intent;

class IntentCompatHoneycomb
{

    IntentCompatHoneycomb()
    {
    }

    public static Intent makeMainActivity(ComponentName componentname)
    {
        return Intent.makeMainActivity(componentname);
    }

    public static Intent makeRestartActivityTask(ComponentName componentname)
    {
        return Intent.makeRestartActivityTask(componentname);
    }
}
