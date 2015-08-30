// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Activity;
import android.content.*;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.*;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.SimpleArrayMap;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.*;
import android.view.animation.Animation;
import java.io.FileDescriptor;
import java.io.PrintWriter;

// Referenced classes of package android.support.v4.app:
//            LoaderManagerImpl, FragmentManagerImpl, FragmentActivity, SuperNotCalledException, 
//            SharedElementCallback, FragmentManager, LoaderManager, FragmentContainer

public class Fragment
    implements ComponentCallbacks, android.view.View.OnCreateContextMenuListener
{
    public static class InstantiationException extends RuntimeException
    {

        public InstantiationException(String s, Exception exception)
        {
            super(s, exception);
        }
    }

    public static class SavedState
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeBundle(mState);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        final Bundle mState;


        SavedState(Bundle bundle)
        {
            mState = bundle;
        }

        SavedState(Parcel parcel, ClassLoader classloader)
        {
            mState = parcel.readBundle();
            if(classloader != null && mState != null)
                mState.setClassLoader(classloader);
        }
    }


    public Fragment()
    {
        mState = 0;
        mIndex = -1;
        mTargetIndex = -1;
        mMenuVisible = true;
        mUserVisibleHint = true;
        mEnterTransition = null;
        mReturnTransition = USE_DEFAULT_TRANSITION;
        mExitTransition = null;
        mReenterTransition = USE_DEFAULT_TRANSITION;
        mSharedElementEnterTransition = null;
        mSharedElementReturnTransition = USE_DEFAULT_TRANSITION;
        mEnterTransitionCallback = null;
        mExitTransitionCallback = null;
    }

    public static Fragment instantiate(Context context, String s)
    {
        return instantiate(context, s, null);
    }

    public static Fragment instantiate(Context context, String s, Bundle bundle)
    {
        Fragment fragment;
        try
        {
            Class class1 = (Class)sClassMap.get(s);
            if(class1 == null)
            {
                class1 = context.getClassLoader().loadClass(s);
                sClassMap.put(s, class1);
            }
            fragment = (Fragment)class1.newInstance();
            if(bundle != null)
            {
                bundle.setClassLoader(fragment.getClass().getClassLoader());
                fragment.mArguments = bundle;
            }
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new InstantiationException((new StringBuilder()).append("Unable to instantiate fragment ").append(s).append(": make sure class name exists, is public, and has an").append(" empty constructor that is public").toString(), classnotfoundexception);
        }
        catch(java.lang.InstantiationException instantiationexception)
        {
            throw new InstantiationException((new StringBuilder()).append("Unable to instantiate fragment ").append(s).append(": make sure class name exists, is public, and has an").append(" empty constructor that is public").toString(), instantiationexception);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new InstantiationException((new StringBuilder()).append("Unable to instantiate fragment ").append(s).append(": make sure class name exists, is public, and has an").append(" empty constructor that is public").toString(), illegalaccessexception);
        }
        return fragment;
    }

    static boolean isSupportFragmentClass(Context context, String s)
    {
        boolean flag1;
        Class class1 = (Class)sClassMap.get(s);
        if(class1 == null)
        {
            class1 = context.getClassLoader().loadClass(s);
            sClassMap.put(s, class1);
        }
        flag1 = android/support/v4/app/Fragment.isAssignableFrom(class1);
        boolean flag = flag1;
_L2:
        return flag;
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        flag = false;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        printwriter.print(s);
        printwriter.print("mFragmentId=#");
        printwriter.print(Integer.toHexString(mFragmentId));
        printwriter.print(" mContainerId=#");
        printwriter.print(Integer.toHexString(mContainerId));
        printwriter.print(" mTag=");
        printwriter.println(mTag);
        printwriter.print(s);
        printwriter.print("mState=");
        printwriter.print(mState);
        printwriter.print(" mIndex=");
        printwriter.print(mIndex);
        printwriter.print(" mWho=");
        printwriter.print(mWho);
        printwriter.print(" mBackStackNesting=");
        printwriter.println(mBackStackNesting);
        printwriter.print(s);
        printwriter.print("mAdded=");
        printwriter.print(mAdded);
        printwriter.print(" mRemoving=");
        printwriter.print(mRemoving);
        printwriter.print(" mResumed=");
        printwriter.print(mResumed);
        printwriter.print(" mFromLayout=");
        printwriter.print(mFromLayout);
        printwriter.print(" mInLayout=");
        printwriter.println(mInLayout);
        printwriter.print(s);
        printwriter.print("mHidden=");
        printwriter.print(mHidden);
        printwriter.print(" mDetached=");
        printwriter.print(mDetached);
        printwriter.print(" mMenuVisible=");
        printwriter.print(mMenuVisible);
        printwriter.print(" mHasMenu=");
        printwriter.println(mHasMenu);
        printwriter.print(s);
        printwriter.print("mRetainInstance=");
        printwriter.print(mRetainInstance);
        printwriter.print(" mRetaining=");
        printwriter.print(mRetaining);
        printwriter.print(" mUserVisibleHint=");
        printwriter.println(mUserVisibleHint);
        if(mFragmentManager != null)
        {
            printwriter.print(s);
            printwriter.print("mFragmentManager=");
            printwriter.println(mFragmentManager);
        }
        if(mActivity != null)
        {
            printwriter.print(s);
            printwriter.print("mActivity=");
            printwriter.println(mActivity);
        }
        if(mParentFragment != null)
        {
            printwriter.print(s);
            printwriter.print("mParentFragment=");
            printwriter.println(mParentFragment);
        }
        if(mArguments != null)
        {
            printwriter.print(s);
            printwriter.print("mArguments=");
            printwriter.println(mArguments);
        }
        if(mSavedFragmentState != null)
        {
            printwriter.print(s);
            printwriter.print("mSavedFragmentState=");
            printwriter.println(mSavedFragmentState);
        }
        if(mSavedViewState != null)
        {
            printwriter.print(s);
            printwriter.print("mSavedViewState=");
            printwriter.println(mSavedViewState);
        }
        if(mTarget != null)
        {
            printwriter.print(s);
            printwriter.print("mTarget=");
            printwriter.print(mTarget);
            printwriter.print(" mTargetRequestCode=");
            printwriter.println(mTargetRequestCode);
        }
        if(mNextAnim != 0)
        {
            printwriter.print(s);
            printwriter.print("mNextAnim=");
            printwriter.println(mNextAnim);
        }
        if(mContainer != null)
        {
            printwriter.print(s);
            printwriter.print("mContainer=");
            printwriter.println(mContainer);
        }
        if(mView != null)
        {
            printwriter.print(s);
            printwriter.print("mView=");
            printwriter.println(mView);
        }
        if(mInnerView != null)
        {
            printwriter.print(s);
            printwriter.print("mInnerView=");
            printwriter.println(mView);
        }
        if(mAnimatingAway != null)
        {
            printwriter.print(s);
            printwriter.print("mAnimatingAway=");
            printwriter.println(mAnimatingAway);
            printwriter.print(s);
            printwriter.print("mStateAfterAnimating=");
            printwriter.println(mStateAfterAnimating);
        }
        if(mLoaderManager != null)
        {
            printwriter.print(s);
            printwriter.println("Loader Manager:");
            mLoaderManager.dump((new StringBuilder()).append(s).append("  ").toString(), filedescriptor, printwriter, as);
        }
        if(mChildFragmentManager != null)
        {
            printwriter.print(s);
            printwriter.println((new StringBuilder()).append("Child ").append(mChildFragmentManager).append(":").toString());
            mChildFragmentManager.dump((new StringBuilder()).append(s).append("  ").toString(), filedescriptor, printwriter, as);
        }
    }

    public final boolean equals(Object obj)
    {
        return super.equals(obj);
    }

    Fragment findFragmentByWho(String s)
    {
        if(!s.equals(mWho))
            if(mChildFragmentManager != null)
                this = mChildFragmentManager.findFragmentByWho(s);
            else
                this = null;
        return this;
    }

    public final FragmentActivity getActivity()
    {
        return mActivity;
    }

    public boolean getAllowEnterTransitionOverlap()
    {
        boolean flag;
        if(mAllowEnterTransitionOverlap == null)
            flag = true;
        else
            flag = mAllowEnterTransitionOverlap.booleanValue();
        return flag;
    }

    public boolean getAllowReturnTransitionOverlap()
    {
        boolean flag;
        if(mAllowReturnTransitionOverlap == null)
            flag = true;
        else
            flag = mAllowReturnTransitionOverlap.booleanValue();
        return flag;
    }

    public final Bundle getArguments()
    {
        return mArguments;
    }

    public final FragmentManager getChildFragmentManager()
    {
        if(mChildFragmentManager != null) goto _L2; else goto _L1
_L1:
        instantiateChildFragmentManager();
        if(mState < 5) goto _L4; else goto _L3
_L3:
        mChildFragmentManager.dispatchResume();
_L2:
        return mChildFragmentManager;
_L4:
        if(mState >= 4)
            mChildFragmentManager.dispatchStart();
        else
        if(mState >= 2)
            mChildFragmentManager.dispatchActivityCreated();
        else
        if(mState >= 1)
            mChildFragmentManager.dispatchCreate();
        if(true) goto _L2; else goto _L5
_L5:
    }

    public Object getEnterTransition()
    {
        return mEnterTransition;
    }

    public Object getExitTransition()
    {
        return mExitTransition;
    }

    public final FragmentManager getFragmentManager()
    {
        return mFragmentManager;
    }

    public final int getId()
    {
        return mFragmentId;
    }

    public LayoutInflater getLayoutInflater(Bundle bundle)
    {
        LayoutInflater layoutinflater = mActivity.getLayoutInflater().cloneInContext(mActivity);
        getChildFragmentManager();
        layoutinflater.setFactory(mChildFragmentManager.getLayoutInflaterFactory());
        return layoutinflater;
    }

    public LoaderManager getLoaderManager()
    {
        LoaderManagerImpl loadermanagerimpl;
        if(mLoaderManager != null)
        {
            loadermanagerimpl = mLoaderManager;
        } else
        {
            if(mActivity == null)
                throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(this).append(" not attached to Activity").toString());
            mCheckedForLoaderManager = true;
            mLoaderManager = mActivity.getLoaderManager(mWho, mLoadersStarted, true);
            loadermanagerimpl = mLoaderManager;
        }
        return loadermanagerimpl;
    }

    public final Fragment getParentFragment()
    {
        return mParentFragment;
    }

    public Object getReenterTransition()
    {
        Object obj;
        if(mReenterTransition == USE_DEFAULT_TRANSITION)
            obj = getExitTransition();
        else
            obj = mReenterTransition;
        return obj;
    }

    public final Resources getResources()
    {
        if(mActivity == null)
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(this).append(" not attached to Activity").toString());
        else
            return mActivity.getResources();
    }

    public final boolean getRetainInstance()
    {
        return mRetainInstance;
    }

    public Object getReturnTransition()
    {
        Object obj;
        if(mReturnTransition == USE_DEFAULT_TRANSITION)
            obj = getEnterTransition();
        else
            obj = mReturnTransition;
        return obj;
    }

    public Object getSharedElementEnterTransition()
    {
        return mSharedElementEnterTransition;
    }

    public Object getSharedElementReturnTransition()
    {
        Object obj;
        if(mSharedElementReturnTransition == USE_DEFAULT_TRANSITION)
            obj = getSharedElementEnterTransition();
        else
            obj = mSharedElementReturnTransition;
        return obj;
    }

    public final String getString(int i)
    {
        return getResources().getString(i);
    }

    public final transient String getString(int i, Object aobj[])
    {
        return getResources().getString(i, aobj);
    }

    public final String getTag()
    {
        return mTag;
    }

    public final Fragment getTargetFragment()
    {
        return mTarget;
    }

    public final int getTargetRequestCode()
    {
        return mTargetRequestCode;
    }

    public final CharSequence getText(int i)
    {
        return getResources().getText(i);
    }

    public boolean getUserVisibleHint()
    {
        return mUserVisibleHint;
    }

    public View getView()
    {
        return mView;
    }

    public final boolean hasOptionsMenu()
    {
        return mHasMenu;
    }

    public final int hashCode()
    {
        return super.hashCode();
    }

    void initState()
    {
        mIndex = -1;
        mWho = null;
        mAdded = false;
        mRemoving = false;
        mResumed = false;
        mFromLayout = false;
        mInLayout = false;
        mRestored = false;
        mBackStackNesting = 0;
        mFragmentManager = null;
        mChildFragmentManager = null;
        mActivity = null;
        mFragmentId = 0;
        mContainerId = 0;
        mTag = null;
        mHidden = false;
        mDetached = false;
        mRetaining = false;
        mLoaderManager = null;
        mLoadersStarted = false;
        mCheckedForLoaderManager = false;
    }

    void instantiateChildFragmentManager()
    {
        mChildFragmentManager = new FragmentManagerImpl();
        mChildFragmentManager.attachActivity(mActivity, new FragmentContainer() {

            public View findViewById(int i)
            {
                if(mView == null)
                    throw new IllegalStateException("Fragment does not have a view");
                else
                    return mView.findViewById(i);
            }

            public boolean hasView()
            {
                boolean flag;
                if(mView != null)
                    flag = true;
                else
                    flag = false;
                return flag;
            }

            final Fragment this$0;

            
            {
                this$0 = Fragment.this;
                super();
            }
        }
, this);
    }

    public final boolean isAdded()
    {
        boolean flag;
        if(mActivity != null && mAdded)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public final boolean isDetached()
    {
        return mDetached;
    }

    public final boolean isHidden()
    {
        return mHidden;
    }

    final boolean isInBackStack()
    {
        boolean flag;
        if(mBackStackNesting > 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public final boolean isInLayout()
    {
        return mInLayout;
    }

    public final boolean isMenuVisible()
    {
        return mMenuVisible;
    }

    public final boolean isRemoving()
    {
        return mRemoving;
    }

    public final boolean isResumed()
    {
        return mResumed;
    }

    public final boolean isVisible()
    {
        boolean flag;
        if(isAdded() && !isHidden() && mView != null && mView.getWindowToken() != null && mView.getVisibility() == 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void onActivityCreated(Bundle bundle)
    {
        mCalled = true;
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
    }

    public void onAttach(Activity activity)
    {
        mCalled = true;
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        mCalled = true;
    }

    public boolean onContextItemSelected(MenuItem menuitem)
    {
        return false;
    }

    public void onCreate(Bundle bundle)
    {
        mCalled = true;
    }

    public Animation onCreateAnimation(int i, boolean flag, int j)
    {
        return null;
    }

    public void onCreateContextMenu(ContextMenu contextmenu, View view, android.view.ContextMenu.ContextMenuInfo contextmenuinfo)
    {
        getActivity().onCreateContextMenu(contextmenu, view, contextmenuinfo);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuinflater)
    {
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return null;
    }

    public void onDestroy()
    {
        mCalled = true;
        if(!mCheckedForLoaderManager)
        {
            mCheckedForLoaderManager = true;
            mLoaderManager = mActivity.getLoaderManager(mWho, mLoadersStarted, false);
        }
        if(mLoaderManager != null)
            mLoaderManager.doDestroy();
    }

    public void onDestroyOptionsMenu()
    {
    }

    public void onDestroyView()
    {
        mCalled = true;
    }

    public void onDetach()
    {
        mCalled = true;
    }

    public void onHiddenChanged(boolean flag)
    {
    }

    public void onInflate(Activity activity, AttributeSet attributeset, Bundle bundle)
    {
        mCalled = true;
    }

    public void onLowMemory()
    {
        mCalled = true;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        return false;
    }

    public void onOptionsMenuClosed(Menu menu)
    {
    }

    public void onPause()
    {
        mCalled = true;
    }

    public void onPrepareOptionsMenu(Menu menu)
    {
    }

    public void onResume()
    {
        mCalled = true;
    }

    public void onSaveInstanceState(Bundle bundle)
    {
    }

    public void onStart()
    {
        mCalled = true;
        if(!mLoadersStarted)
        {
            mLoadersStarted = true;
            if(!mCheckedForLoaderManager)
            {
                mCheckedForLoaderManager = true;
                mLoaderManager = mActivity.getLoaderManager(mWho, mLoadersStarted, false);
            }
            if(mLoaderManager != null)
                mLoaderManager.doStart();
        }
    }

    public void onStop()
    {
        mCalled = true;
    }

    public void onViewCreated(View view, Bundle bundle)
    {
    }

    public void onViewStateRestored(Bundle bundle)
    {
        mCalled = true;
    }

    void performActivityCreated(Bundle bundle)
    {
        if(mChildFragmentManager != null)
            mChildFragmentManager.noteStateNotSaved();
        mCalled = false;
        onActivityCreated(bundle);
        if(!mCalled)
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onActivityCreated()").toString());
        if(mChildFragmentManager != null)
            mChildFragmentManager.dispatchActivityCreated();
    }

    void performConfigurationChanged(Configuration configuration)
    {
        onConfigurationChanged(configuration);
        if(mChildFragmentManager != null)
            mChildFragmentManager.dispatchConfigurationChanged(configuration);
    }

    boolean performContextItemSelected(MenuItem menuitem)
    {
        boolean flag;
        flag = true;
        break MISSING_BLOCK_LABEL_2;
        if(mHidden || !onContextItemSelected(menuitem) && (mChildFragmentManager == null || !mChildFragmentManager.dispatchContextItemSelected(menuitem)))
            flag = false;
        return flag;
    }

    void performCreate(Bundle bundle)
    {
        if(mChildFragmentManager != null)
            mChildFragmentManager.noteStateNotSaved();
        mCalled = false;
        onCreate(bundle);
        if(!mCalled)
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onCreate()").toString());
        if(bundle != null)
        {
            Parcelable parcelable = bundle.getParcelable("android:support:fragments");
            if(parcelable != null)
            {
                if(mChildFragmentManager == null)
                    instantiateChildFragmentManager();
                mChildFragmentManager.restoreAllState(parcelable, null);
                mChildFragmentManager.dispatchCreate();
            }
        }
    }

    boolean performCreateOptionsMenu(Menu menu, MenuInflater menuinflater)
    {
        boolean flag = false;
        if(!mHidden)
        {
            if(mHasMenu && mMenuVisible)
            {
                flag = true;
                onCreateOptionsMenu(menu, menuinflater);
            }
            if(mChildFragmentManager != null)
                flag |= mChildFragmentManager.dispatchCreateOptionsMenu(menu, menuinflater);
        }
        return flag;
    }

    View performCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        if(mChildFragmentManager != null)
            mChildFragmentManager.noteStateNotSaved();
        return onCreateView(layoutinflater, viewgroup, bundle);
    }

    void performDestroy()
    {
        if(mChildFragmentManager != null)
            mChildFragmentManager.dispatchDestroy();
        mCalled = false;
        onDestroy();
        if(!mCalled)
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onDestroy()").toString());
        else
            return;
    }

    void performDestroyView()
    {
        if(mChildFragmentManager != null)
            mChildFragmentManager.dispatchDestroyView();
        mCalled = false;
        onDestroyView();
        if(!mCalled)
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onDestroyView()").toString());
        if(mLoaderManager != null)
            mLoaderManager.doReportNextStart();
    }

    void performLowMemory()
    {
        onLowMemory();
        if(mChildFragmentManager != null)
            mChildFragmentManager.dispatchLowMemory();
    }

    boolean performOptionsItemSelected(MenuItem menuitem)
    {
        boolean flag;
        flag = true;
        break MISSING_BLOCK_LABEL_2;
        if(mHidden || (!mHasMenu || !mMenuVisible || !onOptionsItemSelected(menuitem)) && (mChildFragmentManager == null || !mChildFragmentManager.dispatchOptionsItemSelected(menuitem)))
            flag = false;
        return flag;
    }

    void performOptionsMenuClosed(Menu menu)
    {
        if(!mHidden)
        {
            if(mHasMenu && mMenuVisible)
                onOptionsMenuClosed(menu);
            if(mChildFragmentManager != null)
                mChildFragmentManager.dispatchOptionsMenuClosed(menu);
        }
    }

    void performPause()
    {
        if(mChildFragmentManager != null)
            mChildFragmentManager.dispatchPause();
        mCalled = false;
        onPause();
        if(!mCalled)
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onPause()").toString());
        else
            return;
    }

    boolean performPrepareOptionsMenu(Menu menu)
    {
        boolean flag = false;
        if(!mHidden)
        {
            if(mHasMenu && mMenuVisible)
            {
                flag = true;
                onPrepareOptionsMenu(menu);
            }
            if(mChildFragmentManager != null)
                flag |= mChildFragmentManager.dispatchPrepareOptionsMenu(menu);
        }
        return flag;
    }

    void performReallyStop()
    {
        if(mChildFragmentManager != null)
            mChildFragmentManager.dispatchReallyStop();
        if(mLoadersStarted)
        {
            mLoadersStarted = false;
            if(!mCheckedForLoaderManager)
            {
                mCheckedForLoaderManager = true;
                mLoaderManager = mActivity.getLoaderManager(mWho, mLoadersStarted, false);
            }
            if(mLoaderManager != null)
                if(!mActivity.mRetaining)
                    mLoaderManager.doStop();
                else
                    mLoaderManager.doRetain();
        }
    }

    void performResume()
    {
        if(mChildFragmentManager != null)
        {
            mChildFragmentManager.noteStateNotSaved();
            mChildFragmentManager.execPendingActions();
        }
        mCalled = false;
        onResume();
        if(!mCalled)
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onResume()").toString());
        if(mChildFragmentManager != null)
        {
            mChildFragmentManager.dispatchResume();
            mChildFragmentManager.execPendingActions();
        }
    }

    void performSaveInstanceState(Bundle bundle)
    {
        onSaveInstanceState(bundle);
        if(mChildFragmentManager != null)
        {
            Parcelable parcelable = mChildFragmentManager.saveAllState();
            if(parcelable != null)
                bundle.putParcelable("android:support:fragments", parcelable);
        }
    }

    void performStart()
    {
        if(mChildFragmentManager != null)
        {
            mChildFragmentManager.noteStateNotSaved();
            mChildFragmentManager.execPendingActions();
        }
        mCalled = false;
        onStart();
        if(!mCalled)
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onStart()").toString());
        if(mChildFragmentManager != null)
            mChildFragmentManager.dispatchStart();
        if(mLoaderManager != null)
            mLoaderManager.doReportStart();
    }

    void performStop()
    {
        if(mChildFragmentManager != null)
            mChildFragmentManager.dispatchStop();
        mCalled = false;
        onStop();
        if(!mCalled)
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onStop()").toString());
        else
            return;
    }

    public void registerForContextMenu(View view)
    {
        view.setOnCreateContextMenuListener(this);
    }

    final void restoreViewState(Bundle bundle)
    {
        if(mSavedViewState != null)
        {
            mInnerView.restoreHierarchyState(mSavedViewState);
            mSavedViewState = null;
        }
        mCalled = false;
        onViewStateRestored(bundle);
        if(!mCalled)
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onViewStateRestored()").toString());
        else
            return;
    }

    public void setAllowEnterTransitionOverlap(boolean flag)
    {
        mAllowEnterTransitionOverlap = Boolean.valueOf(flag);
    }

    public void setAllowReturnTransitionOverlap(boolean flag)
    {
        mAllowReturnTransitionOverlap = Boolean.valueOf(flag);
    }

    public void setArguments(Bundle bundle)
    {
        if(mIndex >= 0)
        {
            throw new IllegalStateException("Fragment already active");
        } else
        {
            mArguments = bundle;
            return;
        }
    }

    public void setEnterSharedElementCallback(SharedElementCallback sharedelementcallback)
    {
        mEnterTransitionCallback = sharedelementcallback;
    }

    public void setEnterTransition(Object obj)
    {
        mEnterTransition = obj;
    }

    public void setExitSharedElementCallback(SharedElementCallback sharedelementcallback)
    {
        mExitTransitionCallback = sharedelementcallback;
    }

    public void setExitTransition(Object obj)
    {
        mExitTransition = obj;
    }

    public void setHasOptionsMenu(boolean flag)
    {
        if(mHasMenu != flag)
        {
            mHasMenu = flag;
            if(isAdded() && !isHidden())
                mActivity.supportInvalidateOptionsMenu();
        }
    }

    final void setIndex(int i, Fragment fragment)
    {
        mIndex = i;
        if(fragment != null)
            mWho = (new StringBuilder()).append(fragment.mWho).append(":").append(mIndex).toString();
        else
            mWho = (new StringBuilder()).append("android:fragment:").append(mIndex).toString();
    }

    public void setInitialSavedState(SavedState savedstate)
    {
        if(mIndex >= 0)
            throw new IllegalStateException("Fragment already active");
        Bundle bundle;
        if(savedstate != null && savedstate.mState != null)
            bundle = savedstate.mState;
        else
            bundle = null;
        mSavedFragmentState = bundle;
    }

    public void setMenuVisibility(boolean flag)
    {
        if(mMenuVisible != flag)
        {
            mMenuVisible = flag;
            if(mHasMenu && isAdded() && !isHidden())
                mActivity.supportInvalidateOptionsMenu();
        }
    }

    public void setReenterTransition(Object obj)
    {
        mReenterTransition = obj;
    }

    public void setRetainInstance(boolean flag)
    {
        if(flag && mParentFragment != null)
        {
            throw new IllegalStateException("Can't retain fragements that are nested in other fragments");
        } else
        {
            mRetainInstance = flag;
            return;
        }
    }

    public void setReturnTransition(Object obj)
    {
        mReturnTransition = obj;
    }

    public void setSharedElementEnterTransition(Object obj)
    {
        mSharedElementEnterTransition = obj;
    }

    public void setSharedElementReturnTransition(Object obj)
    {
        mSharedElementReturnTransition = obj;
    }

    public void setTargetFragment(Fragment fragment, int i)
    {
        mTarget = fragment;
        mTargetRequestCode = i;
    }

    public void setUserVisibleHint(boolean flag)
    {
        if(!mUserVisibleHint && flag && mState < 4)
            mFragmentManager.performPendingDeferredStart(this);
        mUserVisibleHint = flag;
        boolean flag1;
        if(!flag)
            flag1 = true;
        else
            flag1 = false;
        mDeferStart = flag1;
    }

    public void startActivity(Intent intent)
    {
        if(mActivity == null)
        {
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(this).append(" not attached to Activity").toString());
        } else
        {
            mActivity.startActivityFromFragment(this, intent, -1);
            return;
        }
    }

    public void startActivityForResult(Intent intent, int i)
    {
        if(mActivity == null)
        {
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(this).append(" not attached to Activity").toString());
        } else
        {
            mActivity.startActivityFromFragment(this, intent, i);
            return;
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        DebugUtils.buildShortClassTag(this, stringbuilder);
        if(mIndex >= 0)
        {
            stringbuilder.append(" #");
            stringbuilder.append(mIndex);
        }
        if(mFragmentId != 0)
        {
            stringbuilder.append(" id=0x");
            stringbuilder.append(Integer.toHexString(mFragmentId));
        }
        if(mTag != null)
        {
            stringbuilder.append(" ");
            stringbuilder.append(mTag);
        }
        stringbuilder.append('}');
        return stringbuilder.toString();
    }

    public void unregisterForContextMenu(View view)
    {
        view.setOnCreateContextMenuListener(null);
    }

    static final int ACTIVITY_CREATED = 2;
    static final int CREATED = 1;
    static final int INITIALIZING = 0;
    static final int RESUMED = 5;
    static final int STARTED = 4;
    static final int STOPPED = 3;
    static final Object USE_DEFAULT_TRANSITION = new Object();
    private static final SimpleArrayMap sClassMap = new SimpleArrayMap();
    FragmentActivity mActivity;
    boolean mAdded;
    Boolean mAllowEnterTransitionOverlap;
    Boolean mAllowReturnTransitionOverlap;
    View mAnimatingAway;
    Bundle mArguments;
    int mBackStackNesting;
    boolean mCalled;
    boolean mCheckedForLoaderManager;
    FragmentManagerImpl mChildFragmentManager;
    ViewGroup mContainer;
    int mContainerId;
    boolean mDeferStart;
    boolean mDetached;
    Object mEnterTransition;
    SharedElementCallback mEnterTransitionCallback;
    Object mExitTransition;
    SharedElementCallback mExitTransitionCallback;
    int mFragmentId;
    FragmentManagerImpl mFragmentManager;
    boolean mFromLayout;
    boolean mHasMenu;
    boolean mHidden;
    boolean mInLayout;
    int mIndex;
    View mInnerView;
    LoaderManagerImpl mLoaderManager;
    boolean mLoadersStarted;
    boolean mMenuVisible;
    int mNextAnim;
    Fragment mParentFragment;
    Object mReenterTransition;
    boolean mRemoving;
    boolean mRestored;
    boolean mResumed;
    boolean mRetainInstance;
    boolean mRetaining;
    Object mReturnTransition;
    Bundle mSavedFragmentState;
    SparseArray mSavedViewState;
    Object mSharedElementEnterTransition;
    Object mSharedElementReturnTransition;
    int mState;
    int mStateAfterAnimating;
    String mTag;
    Fragment mTarget;
    int mTargetIndex;
    int mTargetRequestCode;
    boolean mUserVisibleHint;
    View mView;
    String mWho;

}
