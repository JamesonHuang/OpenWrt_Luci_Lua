// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.downloadmanager;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import com.caverock.androidsvg.SVG;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.sclib.sinks.ArtworkDataEventSink;
import com.sonos.acr.util.*;
import com.sonos.sclib.SCIArtworkData;
import com.sonos.sclib.SCILogoArtworkCache;
import java.io.ByteArrayInputStream;

// Referenced classes of package com.sonos.acr.downloadmanager:
//            BitmapDownloadOperation

public class BitmapLogoDownloadOperation extends BitmapDownloadOperation
    implements com.sonos.acr.sclib.sinks.ArtworkDataEventSink.ArtworkDataListener
{

    public BitmapLogoDownloadOperation(Context context, String s, AlbumArtSize albumartsize)
    {
        super(context, s, albumartsize);
        artworkData = null;
        cacheHit = false;
        SCILogoArtworkCache scilogoartworkcache = LibraryUtils.getLogoCache();
        if(scilogoartworkcache != null)
            artworkData = scilogoartworkcache.requestArtwork(getUri(), albumartsize.getLogoArtSize());
    }

    private boolean updateLogoBitmap(byte abyte0[], com.sonos.sclib.SCIArtworkData.ArtworkDataFormat artworkdataformat)
    {
        boolean flag = false;
        if(abyte0 != null && abyte0.length > 0)
            if(artworkdataformat == com.sonos.sclib.SCIArtworkData.ArtworkDataFormat.ART_DATA_VECTOR)
                try
                {
                    SVG svg = SVG.getFromInputStream(new ByteArrayInputStream(abyte0));
                    int i = getAlbumArtSize().getPixelWidth();
                    Bitmap bitmap1 = ImageUtils.getBitmap(svg, new RectF(0.0F, 0.0F, i, i));
                    if(bitmap1 != null)
                    {
                        onBitmapDownloadSucceeded(bitmap1, 0);
                        flag = true;
                    } else
                    {
                        SLog.e(LOG_TAG, "Logo load failed");
                    }
                }
                catch(Exception exception1)
                {
                    SLog.e(LOG_TAG, "Logo load failed");
                }
            else
                try
                {
                    Bitmap bitmap = ImageUtils.decodeByteArray(abyte0, 0, abyte0.length, new android.graphics.BitmapFactory.Options());
                    if(bitmap != null)
                    {
                        bitmap.setDensity(SonosApplication.getInstance().getResources().getDisplayMetrics().densityDpi);
                        onBitmapDownloadSucceeded(bitmap, 0);
                        flag = true;
                    } else
                    {
                        SLog.e(LOG_TAG, "Logo load failed");
                    }
                }
                catch(Exception exception)
                {
                    SLog.e(LOG_TAG, "Album art Bitmap load failed");
                }
        return flag;
    }

    public void cancel()
    {
        if(artworkEventSink != null)
            artworkEventSink.removeListener(this);
        super.cancel();
    }

    protected void cleanupDownload(boolean flag)
    {
        if(artworkEventSink != null)
            artworkEventSink.removeListener(this);
        super.cleanupDownload(flag);
    }

    public com.sonos.sclib.SCIBrowseItem.SCAlbumArtType getDownloadType()
    {
        return com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOGO;
    }

    public void onArtworkLoadedEvent(SCIArtworkData sciartworkdata, boolean flag)
    {
        cacheHit = flag;
        if(!sciartworkdata.hasFailed()) goto _L2; else goto _L1
_L1:
        onBitmapDownloadSucceeded(null, 0x7f06002b);
_L4:
        return;
_L2:
        if(sciartworkdata.isReady())
        {
            int i = (int)sciartworkdata.getSizeInBytes();
            byte abyte0[] = new byte[i];
            sciartworkdata.getData(abyte0, i);
            updateLogoBitmap(abyte0, sciartworkdata.getDataFormat());
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void performDownload()
    {
        if(artworkData != null)
        {
            artworkEventSink = new ArtworkDataEventSink(artworkData);
            artworkEventSink.addListener(this);
        }
    }

    public boolean wasCacheHit()
    {
        return cacheHit;
    }

    private SCIArtworkData artworkData;
    private ArtworkDataEventSink artworkEventSink;
    boolean cacheHit;
}
