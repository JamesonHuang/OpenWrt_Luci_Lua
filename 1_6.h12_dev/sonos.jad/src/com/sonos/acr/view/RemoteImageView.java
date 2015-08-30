// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import com.sonos.acr.sclib.wrappers.NowPlaying;
import com.sonos.acr.util.AlbumArtController;
import com.sonos.acr.util.AlbumArtSize;

// Referenced classes of package com.sonos.acr.view:
//            BlurImageView, SonosImageView

public class RemoteImageView extends ImageSwitcher
    implements android.widget.ViewSwitcher.ViewFactory
{
    private class RemoteAlbumArtController extends AlbumArtController
    {

        protected void clearImage()
        {
            setImageDrawable(null);
        }

        public String getIdInfo()
        {
            RemoteImageView _tmp = RemoteImageView.this;
            return RemoteImageView.LOG_TAG;
        }

        protected void setImageBitmap(Bitmap bitmap, boolean flag)
        {
            if(!flag)
            {
                setInAnimation(fadeIn);
                setOutAnimation(fadeOut);
            } else
            {
                setInAnimation(null);
                setOutAnimation(null);
            }
            setImageDrawable(new BitmapDrawable(bitmap));
        }

        protected void setImageResource(int i)
        {
            RemoteImageView.this.setImageResource(i);
        }

        Animation fadeIn;
        Animation fadeOut;
        final RemoteImageView this$0;

        private RemoteAlbumArtController(AlbumArtSize albumartsize, int i, int j, int k)
        {
            this$0 = RemoteImageView.this;
            super(albumartsize, i, j, k);
            fadeIn = AnimationUtils.loadAnimation(getContext(), 0x7f040001);
            fadeOut = AnimationUtils.loadAnimation(getContext(), 0x7f040002);
        }

    }


    public RemoteImageView(Context context, AttributeSet attributeset)
    {
        TypedArray typedarray;
        super(context, attributeset);
        fixedAlong = 0;
        typedarray = context.obtainStyledAttributes(attributeset, com.sonos.acr.R.styleable.RemoteImageView);
        alpha = typedarray.getInt(5, 255);
        blurRadius = typedarray.getDimension(1, 0.0F);
        scaleType = typedarray.getInt(0, 3);
        fixedAlong = typedarray.getInt(2, 0);
        initDrawable = getResourceType(typedarray, 7);
        loadingDrawable = getResourceType(typedarray, 3);
        failedDrawable = getResourceType(typedarray, 6);
        if(isInEditMode()) goto _L2; else goto _L1
_L1:
        typedarray.getInt(4, 1);
        JVM INSTR tableswitch 2 5: default 136
    //                   2 192
    //                   3 200
    //                   4 208
    //                   5 216;
           goto _L3 _L4 _L5 _L6 _L7
_L3:
        AlbumArtSize albumartsize = AlbumArtSize.SIZE_BROWSE;
_L9:
        controller = new RemoteAlbumArtController(albumartsize, initDrawable, loadingDrawable, failedDrawable);
_L2:
        typedarray.recycle();
        setFactory(this);
        deviceDensity = context.getResources().getDisplayMetrics().densityDpi;
        return;
_L4:
        albumartsize = AlbumArtSize.SIZE_LARGE_BROWSE;
        continue; /* Loop/switch isn't completed */
_L5:
        albumartsize = AlbumArtSize.SIZE_NOW_PLAYING;
        continue; /* Loop/switch isn't completed */
_L6:
        albumartsize = AlbumArtSize.SIZE_SEARCH;
        continue; /* Loop/switch isn't completed */
_L7:
        albumartsize = AlbumArtSize.SIZE_RATINGS;
        if(true) goto _L9; else goto _L8
_L8:
    }

    private static int getResourceType(TypedArray typedarray, int i)
    {
        int j = typedarray.getResourceId(i, 0);
        if(j == 0)
            j = typedarray.getInt(i, 0);
        return j;
    }

    public AlbumArtSize getAlbumArtSize()
    {
        return controller.getAlbumArtSize();
    }

    public int getFixedAlong()
    {
        return fixedAlong;
    }

    public View makeView()
    {
        Object obj;
        android.widget.FrameLayout.LayoutParams layoutparams;
        if(blurRadius > 1.0F)
        {
            BlurImageView blurimageview = new BlurImageView(getContext());
            blurimageview.setBlurRadius(blurRadius);
            obj = blurimageview;
        } else
        {
            obj = new SonosImageView(getContext());
        }
        ((SonosImageView) (obj)).setFixedDimention(getFixedAlong());
        ((SonosImageView) (obj)).setDuplicateParentStateEnabled(true);
        layoutparams = new android.widget.FrameLayout.LayoutParams(-1, -1);
        layoutparams.gravity = 17;
        ((SonosImageView) (obj)).setLayoutParams(layoutparams);
        ((SonosImageView) (obj)).setAlpha(alpha);
        ((SonosImageView) (obj)).setAdjustViewBounds(true);
        ((SonosImageView) (obj)).setScaleType(scaleTypeArray[scaleType]);
        return ((View) (obj));
    }

    public void reset()
    {
        super.reset();
        setInAnimation(null);
        setOutAnimation(null);
        controller.reset();
    }

    public void setDefaultResourceId(int i)
    {
        controller.setDefaultResourceId(i);
    }

    public void setImageFromNowPlaying(NowPlaying nowplaying)
    {
        int i = nowplaying.getDefaultAlbumArtResourceId();
        if(failedDrawable > 0)
            i = nowplaying.getDefaultAlbumArtResourceId(failedDrawable);
        setImageURI(nowplaying.getAlbumArtURI(getAlbumArtSize()));
        setNextImageURI(nowplaying.getNextTrackAlbumArtURI(getAlbumArtSize()));
        RemoteAlbumArtController remotealbumartcontroller = controller;
        int j;
        int k;
        if(initDrawable > 0)
            j = i;
        else
            j = initDrawable;
        if(loadingDrawable > 0)
            k = i;
        else
            k = loadingDrawable;
        if(failedDrawable <= 0)
            i = failedDrawable;
        remotealbumartcontroller.setDefaultDrawables(j, k, i);
    }

    public void setImageResource(int i)
    {
        if(i == 0)
            setImageDrawable(null);
        else
            super.setImageResource(i);
    }

    public void setImageURI(String s)
    {
        controller.setImageURI(s);
    }

    public void setImageURI(String s, com.sonos.sclib.SCIBrowseItem.SCAlbumArtType scalbumarttype)
    {
        controller.setImageURI(s, scalbumarttype);
    }

    public void setNextImageURI(String s)
    {
        controller.setNextImageURI(s);
    }

    public void setRawImageResourceLockState(boolean flag)
    {
        for(int i = 0; i < getChildCount(); i++)
        {
            View view = getChildAt(i);
            if(view != null && (view instanceof SonosImageView))
                ((SonosImageView)view).setRawImageResourceLockState(flag);
        }

    }

    public void setSmallImageFromNowPlaying(NowPlaying nowplaying)
    {
        int i = nowplaying.getDefaultSmallAlbumArtResourceId();
        if(failedDrawable > 0)
            i = nowplaying.getDefaultAlbumArtResourceId(failedDrawable);
        RemoteAlbumArtController remotealbumartcontroller = controller;
        int j;
        int k;
        if(initDrawable > 0)
            j = i;
        else
            j = initDrawable;
        if(loadingDrawable > 0)
            k = i;
        else
            k = loadingDrawable;
        if(failedDrawable <= 0)
            i = failedDrawable;
        remotealbumartcontroller.setDefaultDrawables(j, k, i);
        setImageURI(nowplaying.getAlbumArtURI(getAlbumArtSize()));
        setNextImageURI(nowplaying.getNextTrackAlbumArtURI(getAlbumArtSize()));
    }

    public static final String LOG_TAG = com/sonos/acr/view/RemoteImageView.getSimpleName();
    public static final int SCALE_FIT_CENTER = 3;
    private static final android.widget.ImageView.ScaleType scaleTypeArray[];
    private int alpha;
    private float blurRadius;
    private RemoteAlbumArtController controller;
    int deviceDensity;
    private final int failedDrawable;
    private int fixedAlong;
    private final int initDrawable;
    private final int loadingDrawable;
    private int scaleType;

    static 
    {
        android.widget.ImageView.ScaleType ascaletype[] = new android.widget.ImageView.ScaleType[8];
        ascaletype[0] = android.widget.ImageView.ScaleType.MATRIX;
        ascaletype[1] = android.widget.ImageView.ScaleType.FIT_XY;
        ascaletype[2] = android.widget.ImageView.ScaleType.FIT_START;
        ascaletype[3] = android.widget.ImageView.ScaleType.FIT_CENTER;
        ascaletype[4] = android.widget.ImageView.ScaleType.FIT_END;
        ascaletype[5] = android.widget.ImageView.ScaleType.CENTER;
        ascaletype[6] = android.widget.ImageView.ScaleType.CENTER_CROP;
        ascaletype[7] = android.widget.ImageView.ScaleType.CENTER_INSIDE;
        scaleTypeArray = ascaletype;
    }
}
