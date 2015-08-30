// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.app.ActivityManager;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.downloadmanager.BitmapDownloadManager;
import com.sonos.acr.localaudiolibrary.LocalMediaUtils;

// Referenced classes of package com.sonos.acr.util:
//            ImageUtils

public final class AlbumArtSize extends Enum
{

    private AlbumArtSize(String s, int i, int j, int k)
    {
        super(s, i);
        updatePixelWidth(SonosApplication.getInstance().getResources().getDimensionPixelSize(j));
        manager = new BitmapDownloadManager(this, determineCacheSize(k));
        if(j == 0x7f09005a)
            logoArtSize = com.sonos.sclib.SCILogoArtworkCache.SCLogoArtSize.LOGO_SIZE_DEFAULT;
    }

    private static int determineCacheSize(int i)
    {
        if(((ActivityManager)SonosApplication.getInstance().getSystemService("activity")).getMemoryClass() <= 128 && SonosApplication.getInstance().getResources().getDisplayMetrics().densityDpi > 320)
            i = (int)Math.ceil((double)i / 2D);
        return i;
    }

    private static com.sonos.sclib.SCILogoArtworkCache.SCLogoArtSize determineCircleLogoArtSize(int i)
    {
        com.sonos.sclib.SCILogoArtworkCache.SCLogoArtSize sclogoartsize;
        if(i <= 18)
            sclogoartsize = com.sonos.sclib.SCILogoArtworkCache.SCLogoArtSize.LOGO_SIZE_SMALL;
        else
        if(i <= 24)
            sclogoartsize = com.sonos.sclib.SCILogoArtworkCache.SCLogoArtSize.LOGO_SIZE_MEDIUM;
        else
        if(i <= 48)
            sclogoartsize = com.sonos.sclib.SCILogoArtworkCache.SCLogoArtSize.LOGO_SIZE_LARGE;
        else
        if(i <= 72)
            sclogoartsize = com.sonos.sclib.SCILogoArtworkCache.SCLogoArtSize.LOGO_SIZE_XLARGE;
        else
            sclogoartsize = com.sonos.sclib.SCILogoArtworkCache.SCLogoArtSize.LOGO_SIZE_DEFAULT;
        return sclogoartsize;
    }

    private int determineScaleFactor(android.graphics.BitmapFactory.Options options)
    {
        int i = 1;
        if(options.outHeight > pixelWidth || options.outWidth > pixelWidth)
        {
            i = (int)Math.pow(2D, Math.max(1, (int)Math.floor(Math.log((double)pixelWidth / (double)Math.max(options.outHeight, options.outWidth)) / Math.log(0.5D))));
            if((1.0D * Math.max(options.outHeight, options.outWidth)) / (double)i <= (double)pixelWidth)
                i /= 2;
        }
        return i;
    }

    private static com.sonos.sclib.SCILogoArtworkCache.SCLogoArtSize determineSquareLogoArtSize(int i)
    {
        com.sonos.sclib.SCILogoArtworkCache.SCLogoArtSize sclogoartsize;
        if(i <= 20)
            sclogoartsize = com.sonos.sclib.SCILogoArtworkCache.SCLogoArtSize.LOGO_SIZE_XSMALL;
        else
        if(i <= 40)
            sclogoartsize = com.sonos.sclib.SCILogoArtworkCache.SCLogoArtSize.LOGO_SIZE_SMALL;
        else
        if(i <= 80)
            sclogoartsize = com.sonos.sclib.SCILogoArtworkCache.SCLogoArtSize.LOGO_SIZE_MEDIUM;
        else
        if(i <= 200)
            sclogoartsize = com.sonos.sclib.SCILogoArtworkCache.SCLogoArtSize.LOGO_SIZE_LARGE;
        else
            sclogoartsize = com.sonos.sclib.SCILogoArtworkCache.SCLogoArtSize.LOGO_SIZE_XLARGE;
        return sclogoartsize;
    }

    public static void onLowMemory()
    {
        AlbumArtSize aalbumartsize[] = values();
        int i = aalbumartsize.length;
        for(int j = 0; j < i; j++)
            aalbumartsize[j].getManager().onLowMemory();

    }

    public static AlbumArtSize valueOf(String s)
    {
        return (AlbumArtSize)Enum.valueOf(com/sonos/acr/util/AlbumArtSize, s);
    }

    public static AlbumArtSize[] values()
    {
        return (AlbumArtSize[])$VALUES.clone();
    }

    public com.sonos.sclib.SCILogoArtworkCache.SCLogoArtSize getLogoArtSize()
    {
        return logoArtSize;
    }

    public BitmapDownloadManager getManager()
    {
        return manager;
    }

    public int getPixelWidth()
    {
        return pixelWidth;
    }

    public int getScaleFactor(String s)
    {
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        LocalMediaUtils.decodeBitmapUri(s, options);
        return determineScaleFactor(options);
    }

    public int getScaleFactor(byte abyte0[], int i)
    {
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        ImageUtils.decodeByteArray(abyte0, 0, i, options);
        return determineScaleFactor(options);
    }

    public String toString()
    {
        return (new StringBuilder()).append("AlbumArtSize{name=").append(name()).append(", logoArtSize=").append(logoArtSize).append(", pixelWidth=").append(pixelWidth).append('}').toString();
    }

    public void updatePixelWidth(int i)
    {
        pixelWidth = i;
        logoArtSize = determineSquareLogoArtSize(pixelWidth);
    }

    private static final AlbumArtSize $VALUES[];
    public static final String LOG_TAG = com/sonos/acr/util/AlbumArtSize.getSimpleName();
    public static final AlbumArtSize SIZE_BROWSE;
    public static final AlbumArtSize SIZE_LARGE_BROWSE;
    public static final AlbumArtSize SIZE_NOW_PLAYING;
    public static final AlbumArtSize SIZE_RATINGS;
    public static final AlbumArtSize SIZE_SEARCH;
    com.sonos.sclib.SCILogoArtworkCache.SCLogoArtSize logoArtSize;
    private BitmapDownloadManager manager;
    private int pixelWidth;

    static 
    {
        SIZE_SEARCH = new AlbumArtSize("SIZE_SEARCH", 0, 0x7f090009, 50);
        SIZE_RATINGS = new AlbumArtSize("SIZE_RATINGS", 1, 0x7f09005a, 10);
        SIZE_BROWSE = new AlbumArtSize("SIZE_BROWSE", 2, 0x7f090004, 100);
        SIZE_LARGE_BROWSE = new AlbumArtSize("SIZE_LARGE_BROWSE", 3, 0x7f090020, 50);
        SIZE_NOW_PLAYING = new AlbumArtSize("SIZE_NOW_PLAYING", 4, 0x7f090007, 5);
        AlbumArtSize aalbumartsize[] = new AlbumArtSize[5];
        aalbumartsize[0] = SIZE_SEARCH;
        aalbumartsize[1] = SIZE_RATINGS;
        aalbumartsize[2] = SIZE_BROWSE;
        aalbumartsize[3] = SIZE_LARGE_BROWSE;
        aalbumartsize[4] = SIZE_NOW_PLAYING;
        $VALUES = aalbumartsize;
    }
}
