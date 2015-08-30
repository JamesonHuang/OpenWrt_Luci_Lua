// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.downloadmanager;

import android.content.Context;
import android.graphics.Bitmap;
import com.sonos.acr.util.AlbumArtSize;
import com.sonos.acr.util.SLog;

// Referenced classes of package com.sonos.acr.downloadmanager:
//            ImageDescription

public abstract class BitmapDownloadOperation
{
    public static interface BitmapDownloadOperationListener
    {

        public abstract void onBitmapDownloadCanceled(BitmapDownloadOperation bitmapdownloadoperation);

        public abstract void onBitmapDownloadFailed(BitmapDownloadOperation bitmapdownloadoperation);

        public abstract void onBitmapDownloadSucceeded(BitmapDownloadOperation bitmapdownloadoperation);
    }


    protected BitmapDownloadOperation(Context context, String s, AlbumArtSize albumartsize)
    {
        startTime = 0L;
        mContext = context;
        mUri = s;
        mSize = albumartsize;
    }

    public void cancel()
    {
        if(!mCanceled)
        {
            mCanceled = true;
            if(mStarted && mListener != null)
                mListener.onBitmapDownloadCanceled(this);
            cleanupDownload(false);
        }
    }

    protected void cleanupDownload(boolean flag)
    {
        SLog.d(LOG_TAG, (new StringBuilder()).append("DownloadOperation took: ").append(System.currentTimeMillis() - startTime).append("ms").toString());
    }

    public AlbumArtSize getAlbumArtSize()
    {
        return mSize;
    }

    public Bitmap getBitmap()
    {
        Bitmap bitmap;
        if(mImageDescription == null)
            bitmap = null;
        else
            bitmap = mImageDescription.getBitmap();
        return bitmap;
    }

    public Context getContext()
    {
        return mContext;
    }

    public abstract com.sonos.sclib.SCIBrowseItem.SCAlbumArtType getDownloadType();

    public ImageDescription getImageDescription()
    {
        return mImageDescription;
    }

    protected String getInternalUri()
    {
        return mUri;
    }

    public int getResId()
    {
        int i;
        if(mImageDescription == null)
            i = 0;
        else
            i = mImageDescription.getResId();
        return i;
    }

    public String getUri()
    {
        return mUri;
    }

    protected void onBitmapDownloadFailed()
    {
        mImageDescription = null;
        mListener.onBitmapDownloadFailed(this);
        cleanupDownload(false);
    }

    protected void onBitmapDownloadSucceeded(Bitmap bitmap, int i)
    {
        mImageDescription = new ImageDescription(bitmap, i);
        if(mListener != null)
            mListener.onBitmapDownloadSucceeded(this);
        cleanupDownload(true);
    }

    protected abstract void performDownload();

    public final void start(BitmapDownloadOperationListener bitmapdownloadoperationlistener)
    {
        mListener = bitmapdownloadoperationlistener;
        if(!mStarted && !mCanceled)
        {
            mStarted = true;
            startTime = System.currentTimeMillis();
            performDownload();
        }
    }

    public abstract boolean wasCacheHit();

    protected final String LOG_TAG = (new StringBuilder()).append(com/sonos/acr/downloadmanager/BitmapDownloadOperation.getSimpleName()).append(":").append(getClass().getSimpleName()).toString();
    protected boolean mCanceled;
    private Context mContext;
    protected ImageDescription mImageDescription;
    protected BitmapDownloadOperationListener mListener;
    protected AlbumArtSize mSize;
    protected boolean mStarted;
    private String mUri;
    private long startTime;
}
