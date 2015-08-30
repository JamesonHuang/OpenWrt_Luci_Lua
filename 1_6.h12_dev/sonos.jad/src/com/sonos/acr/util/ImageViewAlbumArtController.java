// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.graphics.Bitmap;
import android.view.animation.Animation;
import android.widget.ImageView;

// Referenced classes of package com.sonos.acr.util:
//            AlbumArtController, AlbumArtSize

public class ImageViewAlbumArtController extends AlbumArtController
{

    public ImageViewAlbumArtController(AlbumArtSize albumartsize, ImageView imageview)
    {
        super(albumartsize);
        imageView = imageview;
    }

    protected void clearImage()
    {
        imageView.setImageDrawable(null);
    }

    public void setAnimation(Animation animation1)
    {
        animation = animation1;
    }

    protected void setImageBitmap(Bitmap bitmap, boolean flag)
    {
        imageView.setImageBitmap(bitmap);
        imageView.clearAnimation();
        if(!flag && animation != null)
            imageView.startAnimation(animation);
    }

    protected void setImageResource(int i)
    {
        imageView.setImageResource(i);
    }

    private Animation animation;
    private ImageView imageView;
}
