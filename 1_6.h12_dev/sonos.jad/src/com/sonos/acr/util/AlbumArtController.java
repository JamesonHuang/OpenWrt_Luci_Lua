// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.graphics.Bitmap;
import com.sonos.acr.downloadmanager.BitmapDownloadManager;
import com.sonos.acr.downloadmanager.DownloadBitmapCacheListener;
import com.sonos.sclib.SCIBrowseItem;
import com.sonos.sclib.sclibConstants;

// Referenced classes of package com.sonos.acr.util:
//            AlbumArtSize, SLog

public abstract class AlbumArtController
    implements DownloadBitmapCacheListener
{

    public AlbumArtController(AlbumArtSize albumartsize)
    {
        LOG_TAG = (new StringBuilder()).append(getClass().getSimpleName()).append(":").append(getIdInfo()).toString();
        lastDownloadSerial = -2L;
        viewState = 0;
        int ai[] = new int[3];
        ai[0] = 0;
        ai[1] = 0;
        ai[2] = 0;
        stateDrawables = ai;
        albumArtSize = albumartsize;
    }

    public AlbumArtController(AlbumArtSize albumartsize, int i, int j, int k)
    {
        LOG_TAG = (new StringBuilder()).append(getClass().getSimpleName()).append(":").append(getIdInfo()).toString();
        lastDownloadSerial = -2L;
        viewState = 0;
        int ai[] = new int[3];
        ai[0] = 0;
        ai[1] = 0;
        ai[2] = 0;
        stateDrawables = ai;
        albumArtSize = albumartsize;
        stateDrawables[0] = i;
        stateDrawables[1] = j;
        stateDrawables[2] = k;
    }

    private void showDefault()
    {
        if(viewState != 3)
        {
            int i = stateDrawables[viewState];
            if(i != -1)
                if(i == 0)
                    clearImage();
                else
                    setImageResource(i);
        }
    }

    public boolean cancelDownload()
    {
        long l = lastDownloadSerial;
        if(viewState == 1)
        {
            albumArtURL = null;
            lastDownloadSerial = -2L;
        }
        return albumArtSize.getManager().cancelDownload(this, l);
    }

    protected abstract void clearImage();

    public AlbumArtSize getAlbumArtSize()
    {
        return albumArtSize;
    }

    public String getIdInfo()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("");
        int i = logid;
        logid = i + 1;
        return stringbuilder.append(i).toString();
    }

    public final void onBitmapDownloadFailed(long l, String s, int i)
    {
        if(s != null && s.equals(albumArtURL))
        {
            lastDownloadSerial = sclibConstants.SCOP_INVALID_SERIALNUM;
            viewState = 2;
            showDefault();
        }
    }

    public final void onBitmapDownloaded(long l, String s, Bitmap bitmap, int i, boolean flag)
    {
        if(s != null && s.equals(albumArtURL))
        {
            viewState = 3;
            if(bitmap != null)
                setImageBitmap(bitmap, flag);
            else
                setImageResource(i);
        }
    }

    public void reset()
    {
        albumArtURL = null;
        viewState = 0;
        showDefault();
    }

    public void setDefaultDrawables(int i, int j, int k)
    {
        stateDrawables[0] = i;
        stateDrawables[1] = j;
        stateDrawables[2] = k;
        showDefault();
    }

    public void setDefaultResourceId(int i)
    {
        setDefaultDrawables(i, i, i);
    }

    protected abstract void setImageBitmap(Bitmap bitmap, boolean flag);

    public void setImageFromBrowseItem(SCIBrowseItem scibrowseitem)
    {
        setImageURI(scibrowseitem.getAlbumArtURL(albumArtSize.getPixelWidth()), scibrowseitem.getAlbumArtType());
    }

    protected abstract void setImageResource(int i);

    public void setImageURI(String s)
    {
        setImageURI(s, com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_URL);
    }

    public void setImageURI(String s, com.sonos.sclib.SCIBrowseItem.SCAlbumArtType scalbumarttype)
    {
        if(s == null || s.equals(albumArtURL)) goto _L2; else goto _L1
_L1:
        cancelDownload();
        albumArtURL = s;
        viewState = 1;
        lastDownloadSerial = albumArtSize.getManager().queueDownload(s, scalbumarttype, this, 0);
        (int)lastDownloadSerial;
        JVM INSTR tableswitch -2 0: default 80
    //                   -2 102
    //                   -1 85
    //                   0 80;
           goto _L3 _L4 _L5 _L3
_L3:
        showDefault();
_L2:
        return;
_L5:
        viewState = 2;
        SLog.d(LOG_TAG, "Not Downloading previous failure");
        continue; /* Loop/switch isn't completed */
_L4:
        SLog.e(LOG_TAG, "Not Downloading Invalid serial? SHOULD NOT SEE");
        if(true) goto _L3; else goto _L6
_L6:
    }

    public void setNextImageURI(String s)
    {
        if(s != null && !"".equals(s) && !s.equals(albumArtURL))
            albumArtSize.getManager().queueDownload(s, com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_URL, null, 0);
    }

    private static final int STATE_DOWNLOADING = 1;
    private static final int STATE_DOWNLOAD_FAILED = 2;
    private static final int STATE_DOWNLOAD_SUCCESS = 3;
    private static final int STATE_INITIALIZED = 0;
    private static final int USE_CURRENT = -1;
    private static final int USE_EMPTY;
    static int logid = 0;
    protected final String LOG_TAG;
    protected AlbumArtSize albumArtSize;
    protected String albumArtURL;
    private long lastDownloadSerial;
    int stateDrawables[];
    private int viewState;

}
