// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.anonymous.components;

import android.graphics.Bitmap;
import android.view.*;
import com.sonos.acr.downloadmanager.BitmapDownloadManager;
import com.sonos.acr.downloadmanager.DownloadBitmapCacheListener;
import com.sonos.acr.util.AlbumArtSize;
import com.sonos.acr.view.SonosImageView;

// Referenced classes of package com.sonos.acr.wizards.anonymous.components:
//            WizardComponent

public class WizardImageComponent extends WizardComponent
    implements DownloadBitmapCacheListener
{

    public WizardImageComponent()
    {
        imageBmp = null;
        imageResId = -1;
    }

    protected void fetchImage()
    {
        AlbumArtSize.SIZE_NOW_PLAYING.getManager().queueDownload(imageURL, imageType, this, 0);
    }

    public View getComponentView(ViewGroup viewgroup)
    {
        SonosImageView sonosimageview;
        sonosimageview = (SonosImageView)LayoutInflater.from(viewgroup.getContext()).inflate(0x7f0300b7, viewgroup, false);
        sonosimageview.setVisibility(0);
        if(imageBmp == null) goto _L2; else goto _L1
_L1:
        sonosimageview.setImageBitmap(imageBmp);
_L4:
        return sonosimageview;
_L2:
        if(imageResId > 0)
            sonosimageview.setImageResource(imageResId);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public com.sonos.sclib.SCIBrowseItem.SCAlbumArtType getImageType()
    {
        return imageType;
    }

    public String getImageURL()
    {
        return imageURL;
    }

    public String logString()
    {
        return (new StringBuilder()).append("Image Component - Type: ").append(imageType.name()).append(" URL: ").append(imageURL).toString();
    }

    public void onBitmapDownloadFailed(long l, String s, int i)
    {
    }

    public void onBitmapDownloaded(long l, String s, Bitmap bitmap, int i, boolean flag)
    {
        if(bitmap != null)
            imageBmp = bitmap;
        else
            imageResId = i;
    }

    protected void setImageType(com.sonos.sclib.SCIBrowseItem.SCAlbumArtType scalbumarttype)
    {
        imageType = scalbumarttype;
    }

    protected void setImageURL(String s)
    {
        imageURL = s;
    }

    private Bitmap imageBmp;
    private int imageResId;
    private com.sonos.sclib.SCIBrowseItem.SCAlbumArtType imageType;
    private String imageURL;
}
