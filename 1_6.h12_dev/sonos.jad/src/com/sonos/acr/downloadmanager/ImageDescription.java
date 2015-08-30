// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.downloadmanager;

import android.graphics.Bitmap;

public class ImageDescription
{

    public ImageDescription(Bitmap bitmap1, int i)
    {
        bitmap = bitmap1;
        resId = i;
    }

    public Bitmap getBitmap()
    {
        return bitmap;
    }

    public int getResId()
    {
        return resId;
    }

    private Bitmap bitmap;
    private int resId;
}
