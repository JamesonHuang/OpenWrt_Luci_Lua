// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.imaging;

import android.graphics.Bitmap;

public interface BlurProcessor
{

    public abstract void performBlur(Bitmap bitmap, Bitmap bitmap1, float f);
}
