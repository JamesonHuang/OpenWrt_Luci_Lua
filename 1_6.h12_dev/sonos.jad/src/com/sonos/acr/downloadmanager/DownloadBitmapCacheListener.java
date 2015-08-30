// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.downloadmanager;

import android.graphics.Bitmap;

public interface DownloadBitmapCacheListener
{

    public abstract void onBitmapDownloadFailed(long l, String s, int i);

    public abstract void onBitmapDownloaded(long l, String s, Bitmap bitmap, int i, boolean flag);
}
