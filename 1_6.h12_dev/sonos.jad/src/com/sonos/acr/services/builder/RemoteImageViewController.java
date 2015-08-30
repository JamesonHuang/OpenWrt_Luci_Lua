// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.services.builder;

import android.graphics.*;
import android.widget.RemoteViews;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.downloadmanager.BitmapDownloadManager;
import com.sonos.acr.util.*;

public class RemoteImageViewController extends AlbumArtController
{
    public static interface RemoteImageViewListener
    {

        public abstract void onImageUpdated();
    }


    public RemoteImageViewController(AlbumArtSize albumartsize, int i, RemoteImageViewListener remoteimageviewlistener, boolean flag)
    {
        this(albumartsize, i, remoteimageviewlistener, flag, 160);
    }

    public RemoteImageViewController(AlbumArtSize albumartsize, int i, RemoteImageViewListener remoteimageviewlistener, boolean flag, int j)
    {
        super(albumartsize);
        currentImage = null;
        currentImageId = 0;
        isSquare = false;
        imageViewId = i;
        listener = remoteimageviewlistener;
        isSquare = flag;
        density = j;
    }

    private Bitmap createSquareBitmap(Bitmap bitmap)
    {
        int i = Math.max(bitmap.getWidth(), bitmap.getHeight());
        float f = Math.max(i, albumArtSize.getPixelWidth());
        Bitmap bitmap1 = ImageUtils.createBitmap((int)f, (int)f, android.graphics.Bitmap.Config.ARGB_8888);
        Matrix matrix = new Matrix();
        matrix.setRectToRect(new RectF(0.0F, 0.0F, i, i), new RectF(0.0F, 0.0F, f, f), android.graphics.Matrix.ScaleToFit.CENTER);
        Canvas canvas = new Canvas(bitmap1);
        canvas.setMatrix(matrix);
        canvas.drawBitmap(bitmap, (i - bitmap.getWidth()) / 2, (i - bitmap.getHeight()) / 2, null);
        return bitmap1;
    }

    public void cancel()
    {
        cancelDownload();
        currentImage = null;
        currentImageId = 0;
    }

    protected void clearImage()
    {
        currentImage = null;
        currentImageId = 0;
        if(listener != null)
            listener.onImageUpdated();
    }

    public int getImageViewId()
    {
        return imageViewId;
    }

    protected void setImageBitmap(Bitmap bitmap, boolean flag)
    {
        if(isSquare)
            bitmap = createSquareBitmap(bitmap);
        currentImage = bitmap;
        currentImage.setDensity(density);
        currentImageId = 0;
        if(listener != null)
            listener.onImageUpdated();
    }

    protected void setImageResource(int i)
    {
        currentImageId = i;
        if(ImageUtils.isRawImage(SonosApplication.getInstance().getResources(), i))
        {
            currentImage = ImageUtils.getSvgFromResource(i, albumArtSize.getPixelWidth(), albumArtSize.getPixelWidth());
            if(currentImage != null)
                currentImage.setDensity(density);
        } else
        {
            currentImage = null;
        }
        if(listener != null)
            listener.onImageUpdated();
    }

    public void setListener(RemoteImageViewListener remoteimageviewlistener)
    {
        listener = remoteimageviewlistener;
    }

    public void updateImage(RemoteViews remoteviews, int i, String s, com.sonos.sclib.SCIBrowseItem.SCAlbumArtType scalbumarttype)
    {
        RemoteImageViewListener remoteimageviewlistener = listener;
        listener = null;
        setDefaultResourceId(i);
        if(s != null)
            setImageURI(s, scalbumarttype);
        else
            reset();
        if(remoteviews == null) goto _L2; else goto _L1
_L1:
        if(currentImage == null) goto _L4; else goto _L3
_L3:
        remoteviews.setImageViewBitmap(imageViewId, currentImage);
_L2:
        listener = remoteimageviewlistener;
        return;
_L4:
        if(currentImageId != 0)
            remoteviews.setImageViewResource(imageViewId, currentImageId);
        if(true) goto _L2; else goto _L5
_L5:
    }

    public void updateNextAlbumArtUri(String s)
    {
        if(s != null)
            albumArtSize.getManager().queueDownload(s, com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_URL, null, 0);
    }

    Bitmap currentImage;
    int currentImageId;
    private final int density;
    final int imageViewId;
    private boolean isSquare;
    RemoteImageViewListener listener;
}
