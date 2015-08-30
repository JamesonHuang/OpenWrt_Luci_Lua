// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.graphics.drawable.*;

// Referenced classes of package com.sonos.acr.util:
//            DebugBackground

class DebugBackgroundDrawable extends LayerDrawable
    implements DebugBackground
{

    public DebugBackgroundDrawable(int i, Drawable drawable)
    {
        Drawable adrawable[] = new Drawable[2];
        adrawable[0] = drawable;
        adrawable[1] = new ColorDrawable(0xffff0000);
        super(adrawable);
        oldDrawable = drawable;
    }

    public Drawable getOldDrawable()
    {
        oldDrawable.invalidateSelf();
        return oldDrawable;
    }

    protected Drawable oldDrawable;
}
