// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import java.util.List;
import java.util.Map;

public abstract class SharedElementCallback
{

    public SharedElementCallback()
    {
    }

    private static Bitmap createDrawableBitmap(Drawable drawable)
    {
        int i = drawable.getIntrinsicWidth();
        int j = drawable.getIntrinsicHeight();
        Bitmap bitmap;
        if(i <= 0 || j <= 0)
        {
            bitmap = null;
        } else
        {
            float f = Math.min(1.0F, (float)MAX_IMAGE_SIZE / (float)(i * j));
            if((drawable instanceof BitmapDrawable) && f == 1.0F)
            {
                bitmap = ((BitmapDrawable)drawable).getBitmap();
            } else
            {
                int k = (int)(f * (float)i);
                int l = (int)(f * (float)j);
                bitmap = Bitmap.createBitmap(k, l, android.graphics.Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                Rect rect = drawable.getBounds();
                int i1 = rect.left;
                int j1 = rect.top;
                int k1 = rect.right;
                int l1 = rect.bottom;
                drawable.setBounds(0, 0, k, l);
                drawable.draw(canvas);
                drawable.setBounds(i1, j1, k1, l1);
            }
        }
        return bitmap;
    }

    public Parcelable onCaptureSharedElementSnapshot(View view, Matrix matrix, RectF rectf)
    {
        if(!(view instanceof ImageView)) goto _L2; else goto _L1
_L1:
        ImageView imageview;
        Drawable drawable;
        Drawable drawable1;
        imageview = (ImageView)view;
        drawable = imageview.getDrawable();
        drawable1 = imageview.getBackground();
        if(drawable == null || drawable1 != null) goto _L2; else goto _L3
_L3:
        Bitmap bitmap1 = createDrawableBitmap(drawable);
        if(bitmap1 == null) goto _L2; else goto _L4
_L4:
        Object obj;
        obj = new Bundle();
        ((Bundle) (obj)).putParcelable("sharedElement:snapshot:bitmap", bitmap1);
        ((Bundle) (obj)).putString("sharedElement:snapshot:imageScaleType", imageview.getScaleType().toString());
        if(imageview.getScaleType() == android.widget.ImageView.ScaleType.MATRIX)
        {
            Matrix matrix1 = imageview.getImageMatrix();
            float af[] = new float[9];
            matrix1.getValues(af);
            ((Bundle) (obj)).putFloatArray("sharedElement:snapshot:imageMatrix", af);
        }
_L6:
        return ((Parcelable) (obj));
_L2:
        int i = Math.round(rectf.width());
        int j = Math.round(rectf.height());
        Bitmap bitmap = null;
        if(i > 0 && j > 0)
        {
            float f = Math.min(1.0F, (float)MAX_IMAGE_SIZE / (float)(i * j));
            int k = (int)(f * (float)i);
            int l = (int)(f * (float)j);
            if(mTempMatrix == null)
                mTempMatrix = new Matrix();
            mTempMatrix.set(matrix);
            mTempMatrix.postTranslate(-rectf.left, -rectf.top);
            mTempMatrix.postScale(f, f);
            bitmap = Bitmap.createBitmap(k, l, android.graphics.Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            canvas.concat(mTempMatrix);
            view.draw(canvas);
        }
        obj = bitmap;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public View onCreateSnapshotView(Context context, Parcelable parcelable)
    {
        ImageView imageview = null;
        if(!(parcelable instanceof Bundle)) goto _L2; else goto _L1
_L1:
        Bundle bundle;
        Bitmap bitmap1;
        bundle = (Bundle)parcelable;
        bitmap1 = (Bitmap)bundle.getParcelable("sharedElement:snapshot:bitmap");
        if(bitmap1 != null) goto _L4; else goto _L3
_L3:
        Object obj = null;
_L5:
        return ((View) (obj));
_L4:
        ImageView imageview1 = new ImageView(context);
        imageview = imageview1;
        imageview1.setImageBitmap(bitmap1);
        imageview1.setScaleType(android.widget.ImageView.ScaleType.valueOf(bundle.getString("sharedElement:snapshot:imageScaleType")));
        if(imageview1.getScaleType() == android.widget.ImageView.ScaleType.MATRIX)
        {
            float af[] = bundle.getFloatArray("sharedElement:snapshot:imageMatrix");
            Matrix matrix = new Matrix();
            matrix.setValues(af);
            imageview1.setImageMatrix(matrix);
        }
_L6:
        obj = imageview;
        if(true) goto _L5; else goto _L2
_L2:
        if(parcelable instanceof Bitmap)
        {
            Bitmap bitmap = (Bitmap)parcelable;
            imageview = new ImageView(context);
            imageview.setImageBitmap(bitmap);
        }
          goto _L6
    }

    public void onMapSharedElements(List list, Map map)
    {
    }

    public void onRejectSharedElements(List list)
    {
    }

    public void onSharedElementEnd(List list, List list1, List list2)
    {
    }

    public void onSharedElementStart(List list, List list1, List list2)
    {
    }

    private static final String BUNDLE_SNAPSHOT_BITMAP = "sharedElement:snapshot:bitmap";
    private static final String BUNDLE_SNAPSHOT_IMAGE_MATRIX = "sharedElement:snapshot:imageMatrix";
    private static final String BUNDLE_SNAPSHOT_IMAGE_SCALETYPE = "sharedElement:snapshot:imageScaleType";
    private static int MAX_IMAGE_SIZE = 0x100000;
    private Matrix mTempMatrix;

}
