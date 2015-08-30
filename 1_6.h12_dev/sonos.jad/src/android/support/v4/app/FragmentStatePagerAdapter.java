// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package android.support.v4.app:
//            Fragment, FragmentManager, FragmentTransaction

public abstract class FragmentStatePagerAdapter extends PagerAdapter
{

    public FragmentStatePagerAdapter(FragmentManager fragmentmanager)
    {
        mCurTransaction = null;
        mSavedState = new ArrayList();
        mFragments = new ArrayList();
        mCurrentPrimaryItem = null;
        mFragmentManager = fragmentmanager;
    }

    public void destroyItem(ViewGroup viewgroup, int i, Object obj)
    {
        Fragment fragment = (Fragment)obj;
        if(mCurTransaction == null)
            mCurTransaction = mFragmentManager.beginTransaction();
        for(; mSavedState.size() <= i; mSavedState.add(null));
        mSavedState.set(i, mFragmentManager.saveFragmentInstanceState(fragment));
        mFragments.set(i, null);
        mCurTransaction.remove(fragment);
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

    public Object instantiateItem(ViewGroup viewgroup, int i)
    {
        if(mFragments.size() <= i) goto _L2; else goto _L1
_L1:
        Fragment fragment1 = (Fragment)mFragments.get(i);
        if(fragment1 == null) goto _L2; else goto _L3
_L3:
        return fragment1;
_L2:
        if(mCurTransaction == null)
            mCurTransaction = mFragmentManager.beginTransaction();
        Fragment fragment = getItem(i);
        if(mSavedState.size() > i)
        {
            Fragment.SavedState savedstate = (Fragment.SavedState)mSavedState.get(i);
            if(savedstate != null)
                fragment.setInitialSavedState(savedstate);
        }
        for(; mFragments.size() <= i; mFragments.add(null));
        fragment.setMenuVisibility(false);
        fragment.setUserVisibleHint(false);
        mFragments.set(i, fragment);
        mCurTransaction.add(viewgroup.getId(), fragment);
        fragment1 = fragment;
        if(true) goto _L3; else goto _L4
_L4:
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
        if(parcelable != null)
        {
            Bundle bundle = (Bundle)parcelable;
            bundle.setClassLoader(classloader);
            Parcelable aparcelable[] = bundle.getParcelableArray("states");
            mSavedState.clear();
            mFragments.clear();
            if(aparcelable != null)
            {
                for(int j = 0; j < aparcelable.length; j++)
                    mSavedState.add((Fragment.SavedState)aparcelable[j]);

            }
            Iterator iterator = bundle.keySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                String s = (String)iterator.next();
                if(s.startsWith("f"))
                {
                    int i = Integer.parseInt(s.substring(1));
                    Fragment fragment = mFragmentManager.getFragment(bundle, s);
                    if(fragment != null)
                    {
                        for(; mFragments.size() <= i; mFragments.add(null));
                        fragment.setMenuVisibility(false);
                        mFragments.set(i, fragment);
                    } else
                    {
                        Log.w("FragmentStatePagerAdapter", (new StringBuilder()).append("Bad fragment at key ").append(s).toString());
                    }
                }
            } while(true);
        }
    }

    public Parcelable saveState()
    {
        Bundle bundle = null;
        if(mSavedState.size() > 0)
        {
            bundle = new Bundle();
            Fragment.SavedState asavedstate[] = new Fragment.SavedState[mSavedState.size()];
            mSavedState.toArray(asavedstate);
            bundle.putParcelableArray("states", asavedstate);
        }
        for(int i = 0; i < mFragments.size(); i++)
        {
            Fragment fragment = (Fragment)mFragments.get(i);
            if(fragment == null)
                continue;
            if(bundle == null)
                bundle = new Bundle();
            String s = (new StringBuilder()).append("f").append(i).toString();
            mFragmentManager.putFragment(bundle, s, fragment);
        }

        return bundle;
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
    private static final String TAG = "FragmentStatePagerAdapter";
    private FragmentTransaction mCurTransaction;
    private Fragment mCurrentPrimaryItem;
    private final FragmentManager mFragmentManager;
    private ArrayList mFragments;
    private ArrayList mSavedState;
}
