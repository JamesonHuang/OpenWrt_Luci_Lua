// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package dslv;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import com.sonos.acr.util.ImageUtils;

public class SimpleFloatViewManager
    implements DragSortListView.FloatViewManager
{

    public SimpleFloatViewManager(ListView listview)
    {
        mFloatBGDrawableRes = 0;
        mListView = listview;
    }

    public View onCreateFloatView(int i)
    {
        View view = mListView.getChildAt((i + mListView.getHeaderViewsCount()) - mListView.getFirstVisiblePosition());
        Object obj;
        if(view == null)
        {
            obj = null;
        } else
        {
            view.setPressed(false);
            view.setDrawingCacheEnabled(true);
            mFloatBitmap = ImageUtils.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);
            if(mImageView == null)
                mImageView = new ImageView(mListView.getContext());
            mImageView.setBackgroundResource(mFloatBGDrawableRes);
            mImageView.setPadding(0, 0, 0, 0);
            mImageView.setImageBitmap(mFloatBitmap);
            obj = mImageView;
        }
        return ((View) (obj));
    }

    public void onDestroyFloatView(View view)
    {
        ((ImageView)view).setImageDrawable(null);
        mFloatBitmap.recycle();
        mFloatBitmap = null;
    }

    public void onDragFloatView(View view, Point point, Point point1)
    {
    }

    public void setFloatBGDrawableRes(int i)
    {
        mFloatBGDrawableRes = i;
    }

    private int mFloatBGDrawableRes;
    private Bitmap mFloatBitmap;
    private ImageView mImageView;
    private ListView mListView;
}
