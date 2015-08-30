// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

class ContextCompatJellybean
{

    ContextCompatJellybean()
    {
    }

    public static void startActivities(Context context, Intent aintent[], Bundle bundle)
    {
        context.startActivities(aintent, bundle);
    }
}
