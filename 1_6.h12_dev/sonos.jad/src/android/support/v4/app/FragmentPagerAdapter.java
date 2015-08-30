// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package android.support.v4.app:
//            FragmentManager, Fragment, FragmentTransaction

public abstract class FragmentPagerAdapter extends PagerAdapter
{

    public FragmentPagerAdapter(FragmentManager fragmentmanager)
    {
        mCurTransaction = null;
        mCurrentPrimaryItem = null;
        mFragmentManager = fragmentmanager;
    }

    private static String makeFragmentName(int i, long l)
    {
        return (new StringBuilder()).append("android:switcher:").append(i).append(":").append(l).toString();
    }

    public void destroyItem(ViewGroup viewgroup, int i, Object obj)
    {
        if(mCurTransaction == null)
            mCurTransaction = mFragmentManager.beginTransaction();
        mCurTransaction.detach((Fragment)obj);
    }

    public void finishUpdate(ViewGroup viewgroup)
    {
        if(mCurTransaction != null)
        {
            mCurTransaction.commitAllowingStateLoss();
            mCurTransaction = null;
            mFragmentManager.executePendingTransactions();
        }
    }

    public abstract Fragment getItem(int i);

    public long getItemId(int i)
    {
        return (long)i;
    }

    public Object instantiateItem(ViewGroup viewgroup, int i)
    {
        if(mCurTransaction == null)
            mCurTransaction = mFragmentManager.beginTransaction();
        long l = getItemId(i);
        String s = makeFragmentName(viewgroup.getId(), l);
        Fragment fragment = mFragmentManager.findFragmentByTag(s);
        if(fragment != null)
        {
            mCurTransaction.attach(fragment);
        } else
        {
            fragment = getItem(i);
            mCurTransaction.add(viewgroup.getId(), fragment, makeFragmentName(viewgroup.getId(), l));
        }
        if(fragment != mCurrentPrimaryItem)
        {
            fragment.setMenuVisibility(false);
            fragment.setUserVisibleHint(false);
        }
        return fragment;
    }

    public boolean isViewFromObject(View view, Object obj)
    {
        boolean flag;
        if(((Fragment)obj).getView() == view)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void restoreState(Parcelable parcelable, ClassLoader classloader)
    {
    }

    public Parcelable saveState()
    {
        return null;
    }

    public void setPrimaryItem(ViewGroup viewgroup, int i, Object obj)
    {
        Fragment fragment = (Fragment)obj;
        if(fragment != mCurrentPrimaryItem)
        {
            if(mCurrentPrimaryItem != null)
            {
                mCurrentPrimaryItem.setMenuVisibility(false);
                mCurrentPrimaryItem.setUserVisibleHint(false);
            }
            if(fragment != null)
            {
                fragment.setMenuVisibility(true);
                fragment.setUserVisibleHint(true);
            }
            mCurrentPrimaryItem = fragment;
        }
    }

    public void startUpdate(ViewGroup viewgroup)
    {
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "FragmentPagerAdapter";
    private FragmentTransaction mCurTransaction;
    private Fragment mCurrentPrimaryItem;
    private final FragmentManager mFragmentManager;
}
