// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr;

import android.content.Intent;
import android.os.Bundle;

// Referenced classes of package com.sonos.acr:
//            SonosActivity, SonosLaunchActivity

public class SonosUrlHandlerActivity extends SonosActivity
{

    public SonosUrlHandlerActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        Intent intent = new Intent(this, com/sonos/acr/SonosLaunchActivity);
        intent.addFlags(0x10000000);
        startActivity(intent);
    }
}
