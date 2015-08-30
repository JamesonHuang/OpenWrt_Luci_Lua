// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.content.Context;
import android.content.res.*;
import android.os.*;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.LogWriter;
import android.support.v4.view.ViewCompat;
import android.util.*;
import android.view.*;
import android.view.animation.*;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.*;

// Referenced classes of package android.support.v4.app:
//            FragmentManager, FragmentActivity, Fragment, BackStackRecord, 
//            LoaderManagerImpl, SuperNotCalledException, FragmentContainer, NoSaveStateFrameLayout, 
//            FragmentManagerState, FragmentState, BackStackState, FragmentTransaction

final class FragmentManagerImpl extends FragmentManager
    implements android.view.LayoutInflater.Factory
{
    static class FragmentTag
    {

        public static final int Fragment[];
        public static final int Fragment_id = 1;
        public static final int Fragment_name = 0;
        public static final int Fragment_tag = 2;

        static 
        {
            int ai[] = new int[3];
            ai[0] = 0x1010003;
            ai[1] = 0x10100d0;
            ai[2] = 0x10100d1;
            Fragment = ai;
        }

        FragmentTag()
        {
        }
    }


    FragmentManagerImpl()
    {
        mCurState = 0;
        mStateBundle = null;
        mStateArray = null;
        mExecCommit = new Runnable() {

            public void run()
            {
                execPendingActions();
            }

            final FragmentManagerImpl this$0;

            
            {
                this$0 = FragmentManagerImpl.this;
                super();
            }
        }
;
    }

    private void checkStateLoss()
    {
        if(mStateSaved)
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        if(mNoTransactionsBecause != null)
            throw new IllegalStateException((new StringBuilder()).append("Can not perform this action inside of ").append(mNoTransactionsBecause).toString());
        else
            return;
    }

    static Animation makeFadeAnimation(Context context, float f, float f1)
    {
        AlphaAnimation alphaanimation = new AlphaAnimation(f, f1);
        alphaanimation.setInterpolator(DECELERATE_CUBIC);
        alphaanimation.setDuration(220L);
        return alphaanimation;
    }

    static Animation makeOpenCloseAnimation(Context context, float f, float f1, float f2, float f3)
    {
        AnimationSet animationset = new AnimationSet(false);
        ScaleAnimation scaleanimation = new ScaleAnimation(f, f1, f, f1, 1, 0.5F, 1, 0.5F);
        scaleanimation.setInterpolator(DECELERATE_QUINT);
        scaleanimation.setDuration(220L);
        animationset.addAnimation(scaleanimation);
        AlphaAnimation alphaanimation = new AlphaAnimation(f2, f3);
        alphaanimation.setInterpolator(DECELERATE_CUBIC);
        alphaanimation.setDuration(220L);
        animationset.addAnimation(alphaanimation);
        return animationset;
    }

    public static int reverseTransit(int i)
    {
        int j = 0;
        i;
        JVM INSTR lookupswitch 3: default 36
    //                   4097: 38
    //                   4099: 52
    //                   8194: 45;
           goto _L1 _L2 _L3 _L4
_L1:
        return j;
_L2:
        j = 8194;
        continue; /* Loop/switch isn't completed */
_L4:
        j = 4097;
        continue; /* Loop/switch isn't completed */
_L3:
        j = 4099;
        if(true) goto _L1; else goto _L5
_L5:
    }

    private void throwException(RuntimeException runtimeexception)
    {
        Log.e("FragmentManager", runtimeexception.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printwriter = new PrintWriter(new LogWriter("FragmentManager"));
        if(mActivity != null)
            try
            {
                mActivity.dump("  ", null, printwriter, new String[0]);
            }
            catch(Exception exception1)
            {
                Log.e("FragmentManager", "Failed dumping state", exception1);
            }
        else
            try
            {
                dump("  ", null, printwriter, new String[0]);
            }
            catch(Exception exception)
            {
                Log.e("FragmentManager", "Failed dumping state", exception);
            }
        throw runtimeexception;
    }

    public static int transitToStyleIndex(int i, boolean flag)
    {
        byte byte0 = -1;
        i;
        JVM INSTR lookupswitch 3: default 40
    //                   4097: 42
    //                   4099: 70
    //                   8194: 56;
           goto _L1 _L2 _L3 _L4
_L1:
        return byte0;
_L2:
        if(flag)
            byte0 = 1;
        else
            byte0 = 2;
        continue; /* Loop/switch isn't completed */
_L4:
        if(flag)
            byte0 = 3;
        else
            byte0 = 4;
        continue; /* Loop/switch isn't completed */
_L3:
        if(flag)
            byte0 = 5;
        else
            byte0 = 6;
        if(true) goto _L1; else goto _L5
_L5:
    }

    void addBackStackState(BackStackRecord backstackrecord)
    {
        if(mBackStack == null)
            mBackStack = new ArrayList();
        mBackStack.add(backstackrecord);
        reportBackStackChanged();
    }

    public void addFragment(Fragment fragment, boolean flag)
    {
        if(mAdded == null)
            mAdded = new ArrayList();
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("add: ").append(fragment).toString());
        makeActive(fragment);
        if(!fragment.mDetached)
        {
            if(mAdded.contains(fragment))
                throw new IllegalStateException((new StringBuilder()).append("Fragment already added: ").append(fragment).toString());
            mAdded.add(fragment);
            fragment.mAdded = true;
            fragment.mRemoving = false;
            if(fragment.mHasMenu && fragment.mMenuVisible)
                mNeedMenuInvalidate = true;
            if(flag)
                moveToState(fragment);
        }
    }

    public void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener onbackstackchangedlistener)
    {
        if(mBackStackChangeListeners == null)
            mBackStackChangeListeners = new ArrayList();
        mBackStackChangeListeners.add(onbackstackchangedlistener);
    }

    public int allocBackStackIndex(BackStackRecord backstackrecord)
    {
        this;
        JVM INSTR monitorenter ;
        int j;
        if(mAvailBackStackIndices == null || mAvailBackStackIndices.size() <= 0)
        {
            if(mBackStackIndices == null)
                mBackStackIndices = new ArrayList();
            int i = mBackStackIndices.size();
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("Setting back stack index ").append(i).append(" to ").append(backstackrecord).toString());
            mBackStackIndices.add(backstackrecord);
            j = i;
        } else
        {
            int k = ((Integer)mAvailBackStackIndices.remove(-1 + mAvailBackStackIndices.size())).intValue();
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("Adding back stack index ").append(k).append(" with ").append(backstackrecord).toString());
            mBackStackIndices.set(k, backstackrecord);
            j = k;
        }
        return j;
    }

    public void attachActivity(FragmentActivity fragmentactivity, FragmentContainer fragmentcontainer, Fragment fragment)
    {
        if(mActivity != null)
        {
            throw new IllegalStateException("Already attached");
        } else
        {
            mActivity = fragmentactivity;
            mContainer = fragmentcontainer;
            mParent = fragment;
            return;
        }
    }

    public void attachFragment(Fragment fragment, int i, int j)
    {
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("attach: ").append(fragment).toString());
        if(fragment.mDetached)
        {
            fragment.mDetached = false;
            if(!fragment.mAdded)
            {
                if(mAdded == null)
                    mAdded = new ArrayList();
                if(mAdded.contains(fragment))
                    throw new IllegalStateException((new StringBuilder()).append("Fragment already added: ").append(fragment).toString());
                if(DEBUG)
                    Log.v("FragmentManager", (new StringBuilder()).append("add from attach: ").append(fragment).toString());
                mAdded.add(fragment);
                fragment.mAdded = true;
                if(fragment.mHasMenu && fragment.mMenuVisible)
                    mNeedMenuInvalidate = true;
                moveToState(fragment, mCurState, i, j, false);
            }
        }
    }

    public FragmentTransaction beginTransaction()
    {
        return new BackStackRecord(this);
    }

    public void detachFragment(Fragment fragment, int i, int j)
    {
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("detach: ").append(fragment).toString());
        if(!fragment.mDetached)
        {
            fragment.mDetached = true;
            if(fragment.mAdded)
            {
                if(mAdded != null)
                {
                    if(DEBUG)
                        Log.v("FragmentManager", (new StringBuilder()).append("remove from detach: ").append(fragment).toString());
                    mAdded.remove(fragment);
                }
                if(fragment.mHasMenu && fragment.mMenuVisible)
                    mNeedMenuInvalidate = true;
                fragment.mAdded = false;
                moveToState(fragment, 1, i, j, false);
            }
        }
    }

    public void dispatchActivityCreated()
    {
        mStateSaved = false;
        moveToState(2, false);
    }

    public void dispatchConfigurationChanged(Configuration configuration)
    {
        if(mAdded != null)
        {
            for(int i = 0; i < mAdded.size(); i++)
            {
                Fragment fragment = (Fragment)mAdded.get(i);
                if(fragment != null)
                    fragment.performConfigurationChanged(configuration);
            }

        }
    }

    public boolean dispatchContextItemSelected(MenuItem menuitem)
    {
        int i;
        if(mAdded == null)
            break MISSING_BLOCK_LABEL_57;
        i = 0;
_L3:
        Fragment fragment;
        if(i >= mAdded.size())
            break MISSING_BLOCK_LABEL_57;
        fragment = (Fragment)mAdded.get(i);
        if(fragment == null || !fragment.performContextItemSelected(menuitem)) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        i++;
          goto _L3
        flag = false;
          goto _L4
    }

    public void dispatchCreate()
    {
        mStateSaved = false;
        moveToState(1, false);
    }

    public boolean dispatchCreateOptionsMenu(Menu menu, MenuInflater menuinflater)
    {
        boolean flag = false;
        ArrayList arraylist = null;
        if(mAdded != null)
        {
            for(int j = 0; j < mAdded.size(); j++)
            {
                Fragment fragment1 = (Fragment)mAdded.get(j);
                if(fragment1 == null || !fragment1.performCreateOptionsMenu(menu, menuinflater))
                    continue;
                flag = true;
                if(arraylist == null)
                    arraylist = new ArrayList();
                arraylist.add(fragment1);
            }

        }
        if(mCreatedMenus != null)
        {
            for(int i = 0; i < mCreatedMenus.size(); i++)
            {
                Fragment fragment = (Fragment)mCreatedMenus.get(i);
                if(arraylist == null || !arraylist.contains(fragment))
                    fragment.onDestroyOptionsMenu();
            }

        }
        mCreatedMenus = arraylist;
        return flag;
    }

    public void dispatchDestroy()
    {
        mDestroyed = true;
        execPendingActions();
        moveToState(0, false);
        mActivity = null;
        mContainer = null;
        mParent = null;
    }

    public void dispatchDestroyView()
    {
        moveToState(1, false);
    }

    public void dispatchLowMemory()
    {
        if(mAdded != null)
        {
            for(int i = 0; i < mAdded.size(); i++)
            {
                Fragment fragment = (Fragment)mAdded.get(i);
                if(fragment != null)
                    fragment.performLowMemory();
            }

        }
    }

    public boolean dispatchOptionsItemSelected(MenuItem menuitem)
    {
        int i;
        if(mAdded == null)
            break MISSING_BLOCK_LABEL_57;
        i = 0;
_L3:
        Fragment fragment;
        if(i >= mAdded.size())
            break MISSING_BLOCK_LABEL_57;
        fragment = (Fragment)mAdded.get(i);
        if(fragment == null || !fragment.performOptionsItemSelected(menuitem)) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        i++;
          goto _L3
        flag = false;
          goto _L4
    }

    public void dispatchOptionsMenuClosed(Menu menu)
    {
        if(mAdded != null)
        {
            for(int i = 0; i < mAdded.size(); i++)
            {
                Fragment fragment = (Fragment)mAdded.get(i);
                if(fragment != null)
                    fragment.performOptionsMenuClosed(menu);
            }

        }
    }

    public void dispatchPause()
    {
        moveToState(4, false);
    }

    public boolean dispatchPrepareOptionsMenu(Menu menu)
    {
        boolean flag = false;
        if(mAdded != null)
        {
            for(int i = 0; i < mAdded.size(); i++)
            {
                Fragment fragment = (Fragment)mAdded.get(i);
                if(fragment != null && fragment.performPrepareOptionsMenu(menu))
                    flag = true;
            }

        }
        return flag;
    }

    public void dispatchReallyStop()
    {
        moveToState(2, false);
    }

    public void dispatchResume()
    {
        mStateSaved = false;
        moveToState(5, false);
    }

    public void dispatchStart()
    {
        mStateSaved = false;
        moveToState(4, false);
    }

    public void dispatchStop()
    {
        mStateSaved = true;
        moveToState(3, false);
    }

    public void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        String s1 = (new StringBuilder()).append(s).append("    ").toString();
        if(mActive != null)
        {
            int k2 = mActive.size();
            if(k2 > 0)
            {
                printwriter.print(s);
                printwriter.print("Active Fragments in ");
                printwriter.print(Integer.toHexString(System.identityHashCode(this)));
                printwriter.println(":");
                for(int l2 = 0; l2 < k2; l2++)
                {
                    Fragment fragment2 = (Fragment)mActive.get(l2);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(l2);
                    printwriter.print(": ");
                    printwriter.println(fragment2);
                    if(fragment2 != null)
                        fragment2.dump(s1, filedescriptor, printwriter, as);
                }

            }
        }
        if(mAdded != null)
        {
            int i2 = mAdded.size();
            if(i2 > 0)
            {
                printwriter.print(s);
                printwriter.println("Added Fragments:");
                for(int j2 = 0; j2 < i2; j2++)
                {
                    Fragment fragment1 = (Fragment)mAdded.get(j2);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(j2);
                    printwriter.print(": ");
                    printwriter.println(fragment1.toString());
                }

            }
        }
        if(mCreatedMenus != null)
        {
            int k1 = mCreatedMenus.size();
            if(k1 > 0)
            {
                printwriter.print(s);
                printwriter.println("Fragments Created Menus:");
                for(int l1 = 0; l1 < k1; l1++)
                {
                    Fragment fragment = (Fragment)mCreatedMenus.get(l1);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(l1);
                    printwriter.print(": ");
                    printwriter.println(fragment.toString());
                }

            }
        }
        if(mBackStack != null)
        {
            int i1 = mBackStack.size();
            if(i1 > 0)
            {
                printwriter.print(s);
                printwriter.println("Back Stack:");
                for(int j1 = 0; j1 < i1; j1++)
                {
                    BackStackRecord backstackrecord1 = (BackStackRecord)mBackStack.get(j1);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(j1);
                    printwriter.print(": ");
                    printwriter.println(backstackrecord1.toString());
                    backstackrecord1.dump(s1, filedescriptor, printwriter, as);
                }

            }
        }
        this;
        JVM INSTR monitorenter ;
        if(mBackStackIndices != null)
        {
            int k = mBackStackIndices.size();
            if(k > 0)
            {
                printwriter.print(s);
                printwriter.println("Back Stack Indices:");
                for(int l = 0; l < k; l++)
                {
                    BackStackRecord backstackrecord = (BackStackRecord)mBackStackIndices.get(l);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(l);
                    printwriter.print(": ");
                    printwriter.println(backstackrecord);
                }

            }
        }
        if(mAvailBackStackIndices != null && mAvailBackStackIndices.size() > 0)
        {
            printwriter.print(s);
            printwriter.print("mAvailBackStackIndices: ");
            printwriter.println(Arrays.toString(mAvailBackStackIndices.toArray()));
        }
        this;
        JVM INSTR monitorexit ;
        if(mPendingActions != null)
        {
            int i = mPendingActions.size();
            if(i > 0)
            {
                printwriter.print(s);
                printwriter.println("Pending Actions:");
                for(int j = 0; j < i; j++)
                {
                    Runnable runnable = (Runnable)mPendingActions.get(j);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(j);
                    printwriter.print(": ");
                    printwriter.println(runnable);
                }

            }
        }
        break MISSING_BLOCK_LABEL_694;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        printwriter.print(s);
        printwriter.println("FragmentManager misc state:");
        printwriter.print(s);
        printwriter.print("  mActivity=");
        printwriter.println(mActivity);
        printwriter.print(s);
        printwriter.print("  mContainer=");
        printwriter.println(mContainer);
        if(mParent != null)
        {
            printwriter.print(s);
            printwriter.print("  mParent=");
            printwriter.println(mParent);
        }
        printwriter.print(s);
        printwriter.print("  mCurState=");
        printwriter.print(mCurState);
        printwriter.print(" mStateSaved=");
        printwriter.print(mStateSaved);
        printwriter.print(" mDestroyed=");
        printwriter.println(mDestroyed);
        if(mNeedMenuInvalidate)
        {
            printwriter.print(s);
            printwriter.print("  mNeedMenuInvalidate=");
            printwriter.println(mNeedMenuInvalidate);
        }
        if(mNoTransactionsBecause != null)
        {
            printwriter.print(s);
            printwriter.print("  mNoTransactionsBecause=");
            printwriter.println(mNoTransactionsBecause);
        }
        if(mAvailIndices != null && mAvailIndices.size() > 0)
        {
            printwriter.print(s);
            printwriter.print("  mAvailIndices: ");
            printwriter.println(Arrays.toString(mAvailIndices.toArray()));
        }
        return;
    }

    public void enqueueAction(Runnable runnable, boolean flag)
    {
        if(!flag)
            checkStateLoss();
        this;
        JVM INSTR monitorenter ;
        if(mDestroyed || mActivity == null)
            throw new IllegalStateException("Activity has been destroyed");
        break MISSING_BLOCK_LABEL_40;
        Exception exception;
        exception;
        throw exception;
        if(mPendingActions == null)
            mPendingActions = new ArrayList();
        mPendingActions.add(runnable);
        if(mPendingActions.size() == 1)
        {
            mActivity.mHandler.removeCallbacks(mExecCommit);
            mActivity.mHandler.post(mExecCommit);
        }
        this;
        JVM INSTR monitorexit ;
    }

    public boolean execPendingActions()
    {
        if(mExecutingActions)
            throw new IllegalStateException("Recursive entry to executePendingTransactions");
        if(Looper.myLooper() != mActivity.mHandler.getLooper())
            throw new IllegalStateException("Must be called from main thread of process");
        boolean flag = false;
_L2:
        this;
        JVM INSTR monitorenter ;
        if(mPendingActions != null && mPendingActions.size() != 0)
            break MISSING_BLOCK_LABEL_136;
        this;
        JVM INSTR monitorexit ;
        boolean flag1;
        if(!mHavePendingDeferredStart)
            break MISSING_BLOCK_LABEL_275;
        flag1 = false;
        for(int i = 0; i < mActive.size(); i++)
        {
            Fragment fragment = (Fragment)mActive.get(i);
            if(fragment != null && fragment.mLoaderManager != null)
                flag1 |= fragment.mLoaderManager.hasRunningLoaders();
        }

        break; /* Loop/switch isn't completed */
        int j;
        j = mPendingActions.size();
        if(mTmpActions == null || mTmpActions.length < j)
            mTmpActions = new Runnable[j];
        mPendingActions.toArray(mTmpActions);
        mPendingActions.clear();
        mActivity.mHandler.removeCallbacks(mExecCommit);
        this;
        JVM INSTR monitorexit ;
        mExecutingActions = true;
        for(int k = 0; k < j; k++)
        {
            mTmpActions[k].run();
            mTmpActions[k] = null;
        }

        break MISSING_BLOCK_LABEL_252;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        mExecutingActions = false;
        flag = true;
        if(true) goto _L2; else goto _L1
_L1:
        if(!flag1)
        {
            mHavePendingDeferredStart = false;
            startPendingDeferredFragments();
        }
        return flag;
    }

    public boolean executePendingTransactions()
    {
        return execPendingActions();
    }

    public Fragment findFragmentById(int i)
    {
        if(mAdded == null) goto _L2; else goto _L1
_L1:
        int k = -1 + mAdded.size();
_L6:
        if(k < 0) goto _L2; else goto _L3
_L3:
        Fragment fragment = (Fragment)mAdded.get(k);
        if(fragment == null || fragment.mFragmentId != i) goto _L5; else goto _L4
_L4:
        return fragment;
_L5:
        k--;
          goto _L6
_L2:
        int j;
        if(mActive == null)
            break MISSING_BLOCK_LABEL_109;
        j = -1 + mActive.size();
_L8:
        if(j < 0)
            break MISSING_BLOCK_LABEL_109;
        fragment = (Fragment)mActive.get(j);
        if(fragment != null && fragment.mFragmentId == i) goto _L4; else goto _L7
_L7:
        j--;
          goto _L8
        fragment = null;
          goto _L4
    }

    public Fragment findFragmentByTag(String s)
    {
        if(mAdded == null || s == null) goto _L2; else goto _L1
_L1:
        int j = -1 + mAdded.size();
_L6:
        if(j < 0) goto _L2; else goto _L3
_L3:
        Fragment fragment = (Fragment)mAdded.get(j);
        if(fragment == null || !s.equals(fragment.mTag)) goto _L5; else goto _L4
_L4:
        return fragment;
_L5:
        j--;
          goto _L6
_L2:
        int i;
        if(mActive == null || s == null)
            break MISSING_BLOCK_LABEL_123;
        i = -1 + mActive.size();
_L8:
        if(i < 0)
            break MISSING_BLOCK_LABEL_123;
        fragment = (Fragment)mActive.get(i);
        if(fragment != null && s.equals(fragment.mTag)) goto _L4; else goto _L7
_L7:
        i--;
          goto _L8
        fragment = null;
          goto _L4
    }

    public Fragment findFragmentByWho(String s)
    {
        int i;
        if(mActive == null || s == null)
            break MISSING_BLOCK_LABEL_63;
        i = -1 + mActive.size();
_L3:
        if(i < 0) goto _L2; else goto _L1
_L1:
        Fragment fragment;
        Fragment fragment1 = (Fragment)mActive.get(i);
        if(fragment1 == null)
            continue; /* Loop/switch isn't completed */
        fragment = fragment1.findFragmentByWho(s);
        if(fragment == null)
            continue; /* Loop/switch isn't completed */
_L4:
        return fragment;
        i--;
          goto _L3
_L2:
        fragment = null;
          goto _L4
    }

    public void freeBackStackIndex(int i)
    {
        this;
        JVM INSTR monitorenter ;
        mBackStackIndices.set(i, null);
        if(mAvailBackStackIndices == null)
            mAvailBackStackIndices = new ArrayList();
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("Freeing back stack index ").append(i).toString());
        mAvailBackStackIndices.add(Integer.valueOf(i));
        return;
    }

    public FragmentManager.BackStackEntry getBackStackEntryAt(int i)
    {
        return (FragmentManager.BackStackEntry)mBackStack.get(i);
    }

    public int getBackStackEntryCount()
    {
        int i;
        if(mBackStack != null)
            i = mBackStack.size();
        else
            i = 0;
        return i;
    }

    public Fragment getFragment(Bundle bundle, String s)
    {
        int i = bundle.getInt(s, -1);
        if(i != -1) goto _L2; else goto _L1
_L1:
        Fragment fragment = null;
_L4:
        return fragment;
_L2:
        if(i >= mActive.size())
            throwException(new IllegalStateException((new StringBuilder()).append("Fragment no longer exists for key ").append(s).append(": index ").append(i).toString()));
        fragment = (Fragment)mActive.get(i);
        if(fragment == null)
            throwException(new IllegalStateException((new StringBuilder()).append("Fragment no longer exists for key ").append(s).append(": index ").append(i).toString()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public List getFragments()
    {
        return mActive;
    }

    android.view.LayoutInflater.Factory getLayoutInflaterFactory()
    {
        return this;
    }

    public void hideFragment(Fragment fragment, int i, int j)
    {
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("hide: ").append(fragment).toString());
        if(!fragment.mHidden)
        {
            fragment.mHidden = true;
            if(fragment.mView != null)
            {
                Animation animation = loadAnimation(fragment, i, false, j);
                if(animation != null)
                    fragment.mView.startAnimation(animation);
                fragment.mView.setVisibility(8);
            }
            if(fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible)
                mNeedMenuInvalidate = true;
            fragment.onHiddenChanged(true);
        }
    }

    public boolean isDestroyed()
    {
        return mDestroyed;
    }

    Animation loadAnimation(Fragment fragment, int i, boolean flag, int j)
    {
        Animation animation = fragment.onCreateAnimation(i, flag, fragment.mNextAnim);
        if(animation == null) goto _L2; else goto _L1
_L1:
        return animation;
_L2:
        int k;
        if(fragment.mNextAnim != 0)
        {
            Animation animation1 = AnimationUtils.loadAnimation(mActivity, fragment.mNextAnim);
            if(animation1 != null)
            {
                animation = animation1;
                continue; /* Loop/switch isn't completed */
            }
        }
        if(i == 0)
        {
            animation = null;
            continue; /* Loop/switch isn't completed */
        }
        k = transitToStyleIndex(i, flag);
        if(k >= 0)
            break; /* Loop/switch isn't completed */
        animation = null;
        if(true) goto _L1; else goto _L3
_L3:
        switch(k)
        {
        default:
            if(j == 0 && mActivity.getWindow() != null)
                j = mActivity.getWindow().getAttributes().windowAnimations;
            if(j == 0)
                animation = null;
            else
                animation = null;
            break;

        case 1: // '\001'
            animation = makeOpenCloseAnimation(mActivity, 1.125F, 1.0F, 0.0F, 1.0F);
            break;

        case 2: // '\002'
            animation = makeOpenCloseAnimation(mActivity, 1.0F, 0.975F, 1.0F, 0.0F);
            break;

        case 3: // '\003'
            animation = makeOpenCloseAnimation(mActivity, 0.975F, 1.0F, 0.0F, 1.0F);
            break;

        case 4: // '\004'
            animation = makeOpenCloseAnimation(mActivity, 1.0F, 1.075F, 1.0F, 0.0F);
            break;

        case 5: // '\005'
            animation = makeFadeAnimation(mActivity, 0.0F, 1.0F);
            break;

        case 6: // '\006'
            animation = makeFadeAnimation(mActivity, 1.0F, 0.0F);
            break;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    void makeActive(Fragment fragment)
    {
        if(fragment.mIndex < 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(mAvailIndices == null || mAvailIndices.size() <= 0)
        {
            if(mActive == null)
                mActive = new ArrayList();
            fragment.setIndex(mActive.size(), mParent);
            mActive.add(fragment);
        } else
        {
            fragment.setIndex(((Integer)mAvailIndices.remove(-1 + mAvailIndices.size())).intValue(), mParent);
            mActive.set(fragment.mIndex, fragment);
        }
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("Allocated fragment index ").append(fragment).toString());
        if(true) goto _L1; else goto _L3
_L3:
    }

    void makeInactive(Fragment fragment)
    {
        if(fragment.mIndex >= 0)
        {
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("Freeing fragment index ").append(fragment).toString());
            mActive.set(fragment.mIndex, null);
            if(mAvailIndices == null)
                mAvailIndices = new ArrayList();
            mAvailIndices.add(Integer.valueOf(fragment.mIndex));
            mActivity.invalidateSupportFragment(fragment.mWho);
            fragment.initState();
        }
    }

    void moveToState(int i, int j, int k, boolean flag)
    {
        if(mActivity == null && i != 0)
            throw new IllegalStateException("No activity");
        if(flag || mCurState != i) goto _L2; else goto _L1
_L1:
        return;
_L2:
        mCurState = i;
        if(mActive != null)
        {
            boolean flag1 = false;
            for(int l = 0; l < mActive.size(); l++)
            {
                Fragment fragment = (Fragment)mActive.get(l);
                if(fragment == null)
                    continue;
                moveToState(fragment, i, j, k, false);
                if(fragment.mLoaderManager != null)
                    flag1 |= fragment.mLoaderManager.hasRunningLoaders();
            }

            if(!flag1)
                startPendingDeferredFragments();
            if(mNeedMenuInvalidate && mActivity != null && mCurState == 5)
            {
                mActivity.supportInvalidateOptionsMenu();
                mNeedMenuInvalidate = false;
            }
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    void moveToState(int i, boolean flag)
    {
        moveToState(i, 0, 0, flag);
    }

    void moveToState(Fragment fragment)
    {
        moveToState(fragment, mCurState, 0, 0, false);
    }

    void moveToState(final Fragment fragment, int i, int j, int k, boolean flag)
    {
        if((!fragment.mAdded || fragment.mDetached) && i > 1)
            i = 1;
        if(fragment.mRemoving && i > fragment.mState)
            i = fragment.mState;
        if(fragment.mDeferStart && fragment.mState < 4 && i > 3)
            i = 3;
        if(fragment.mState >= i) goto _L2; else goto _L1
_L1:
        if(!fragment.mFromLayout || fragment.mInLayout) goto _L4; else goto _L3
_L3:
        return;
_L4:
        if(fragment.mAnimatingAway != null)
        {
            fragment.mAnimatingAway = null;
            moveToState(fragment, fragment.mStateAfterAnimating, 0, 0, true);
        }
        fragment.mState;
        JVM INSTR tableswitch 0 4: default 148
    //                   0 156
    //                   1 516
    //                   2 821
    //                   3 821
    //                   4 862;
           goto _L5 _L6 _L7 _L8 _L8 _L9
_L5:
        fragment.mState = i;
          goto _L3
_L6:
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("moveto CREATED: ").append(fragment).toString());
        if(fragment.mSavedFragmentState != null)
        {
            fragment.mSavedFragmentState.setClassLoader(mActivity.getClassLoader());
            fragment.mSavedViewState = fragment.mSavedFragmentState.getSparseParcelableArray("android:view_state");
            fragment.mTarget = getFragment(fragment.mSavedFragmentState, "android:target_state");
            if(fragment.mTarget != null)
                fragment.mTargetRequestCode = fragment.mSavedFragmentState.getInt("android:target_req_state", 0);
            fragment.mUserVisibleHint = fragment.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
            if(!fragment.mUserVisibleHint)
            {
                fragment.mDeferStart = true;
                if(i > 3)
                    i = 3;
            }
        }
        fragment.mActivity = mActivity;
        fragment.mParentFragment = mParent;
        FragmentManagerImpl fragmentmanagerimpl;
        if(mParent != null)
            fragmentmanagerimpl = mParent.mChildFragmentManager;
        else
            fragmentmanagerimpl = mActivity.mFragments;
        fragment.mFragmentManager = fragmentmanagerimpl;
        fragment.mCalled = false;
        fragment.onAttach(mActivity);
        if(!fragment.mCalled)
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(fragment).append(" did not call through to super.onAttach()").toString());
        if(fragment.mParentFragment == null)
            mActivity.onAttachFragment(fragment);
        if(!fragment.mRetaining)
            fragment.performCreate(fragment.mSavedFragmentState);
        fragment.mRetaining = false;
        if(fragment.mFromLayout)
        {
            fragment.mView = fragment.performCreateView(fragment.getLayoutInflater(fragment.mSavedFragmentState), null, fragment.mSavedFragmentState);
            if(fragment.mView != null)
            {
                fragment.mInnerView = fragment.mView;
                ViewGroup viewgroup;
                Animation animation1;
                if(android.os.Build.VERSION.SDK_INT >= 11)
                    ViewCompat.setSaveFromParentEnabled(fragment.mView, false);
                else
                    fragment.mView = NoSaveStateFrameLayout.wrap(fragment.mView);
                if(fragment.mHidden)
                    fragment.mView.setVisibility(8);
                fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
            } else
            {
                fragment.mInnerView = null;
            }
        }
_L7:
        if(i > 1)
        {
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("moveto ACTIVITY_CREATED: ").append(fragment).toString());
            if(!fragment.mFromLayout)
            {
                viewgroup = null;
                if(fragment.mContainerId != 0)
                {
                    viewgroup = (ViewGroup)mContainer.findViewById(fragment.mContainerId);
                    if(viewgroup == null && !fragment.mRestored)
                        throwException(new IllegalArgumentException((new StringBuilder()).append("No view found for id 0x").append(Integer.toHexString(fragment.mContainerId)).append(" (").append(fragment.getResources().getResourceName(fragment.mContainerId)).append(") for fragment ").append(fragment).toString()));
                }
                fragment.mContainer = viewgroup;
                fragment.mView = fragment.performCreateView(fragment.getLayoutInflater(fragment.mSavedFragmentState), viewgroup, fragment.mSavedFragmentState);
                if(fragment.mView != null)
                {
                    fragment.mInnerView = fragment.mView;
                    if(android.os.Build.VERSION.SDK_INT >= 11)
                        ViewCompat.setSaveFromParentEnabled(fragment.mView, false);
                    else
                        fragment.mView = NoSaveStateFrameLayout.wrap(fragment.mView);
                    if(viewgroup != null)
                    {
                        animation1 = loadAnimation(fragment, j, true, k);
                        if(animation1 != null)
                            fragment.mView.startAnimation(animation1);
                        viewgroup.addView(fragment.mView);
                    }
                    if(fragment.mHidden)
                        fragment.mView.setVisibility(8);
                    fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
                } else
                {
                    fragment.mInnerView = null;
                }
            }
            fragment.performActivityCreated(fragment.mSavedFragmentState);
            if(fragment.mView != null)
                fragment.restoreViewState(fragment.mSavedFragmentState);
            fragment.mSavedFragmentState = null;
        }
_L8:
        if(i > 3)
        {
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("moveto STARTED: ").append(fragment).toString());
            fragment.performStart();
        }
_L9:
        if(i <= 4) goto _L5; else goto _L10
_L10:
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("moveto RESUMED: ").append(fragment).toString());
        fragment.mResumed = true;
        fragment.performResume();
        fragment.mSavedFragmentState = null;
        fragment.mSavedViewState = null;
          goto _L5
_L2:
        if(fragment.mState <= i) goto _L5; else goto _L11
_L11:
        fragment.mState;
        JVM INSTR tableswitch 1 5: default 1012
    //                   1 1015
    //                   2 1195
    //                   3 1154
    //                   4 1113
    //                   5 1067;
           goto _L5 _L12 _L13 _L14 _L15 _L16
_L12:
        if(i < 1)
        {
            if(mDestroyed && fragment.mAnimatingAway != null)
            {
                View view = fragment.mAnimatingAway;
                fragment.mAnimatingAway = null;
                view.clearAnimation();
            }
            Animation animation;
            if(fragment.mAnimatingAway != null)
            {
                fragment.mStateAfterAnimating = i;
                i = 1;
            } else
            {
                if(DEBUG)
                    Log.v("FragmentManager", (new StringBuilder()).append("movefrom CREATED: ").append(fragment).toString());
                if(!fragment.mRetaining)
                    fragment.performDestroy();
                fragment.mCalled = false;
                fragment.onDetach();
                if(!fragment.mCalled)
                    throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(fragment).append(" did not call through to super.onDetach()").toString());
                if(!flag)
                    if(!fragment.mRetaining)
                    {
                        makeInactive(fragment);
                    } else
                    {
                        fragment.mActivity = null;
                        fragment.mParentFragment = null;
                        fragment.mFragmentManager = null;
                        fragment.mChildFragmentManager = null;
                    }
            }
        }
          goto _L5
_L16:
        if(i < 5)
        {
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("movefrom RESUMED: ").append(fragment).toString());
            fragment.performPause();
            fragment.mResumed = false;
        }
_L15:
        if(i < 4)
        {
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("movefrom STARTED: ").append(fragment).toString());
            fragment.performStop();
        }
_L14:
        if(i < 3)
        {
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("movefrom STOPPED: ").append(fragment).toString());
            fragment.performReallyStop();
        }
_L13:
        if(i >= 2) goto _L12; else goto _L17
_L17:
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("movefrom ACTIVITY_CREATED: ").append(fragment).toString());
        if(fragment.mView != null && !mActivity.isFinishing() && fragment.mSavedViewState == null)
            saveFragmentViewState(fragment);
        fragment.performDestroyView();
        if(fragment.mView != null && fragment.mContainer != null)
        {
            animation = null;
            if(mCurState > 0 && !mDestroyed)
                animation = loadAnimation(fragment, j, false, k);
            if(animation != null)
            {
                fragment.mAnimatingAway = fragment.mView;
                fragment.mStateAfterAnimating = i;
                animation.setAnimationListener(new android.view.animation.Animation.AnimationListener() {

                    public void onAnimationEnd(Animation animation2)
                    {
                        if(fragment.mAnimatingAway != null)
                        {
                            fragment.mAnimatingAway = null;
                            moveToState(fragment, fragment.mStateAfterAnimating, 0, 0, false);
                        }
                    }

                    public void onAnimationRepeat(Animation animation2)
                    {
                    }

                    public void onAnimationStart(Animation animation2)
                    {
                    }

                    final FragmentManagerImpl this$0;
                    final Fragment val$fragment;

            
            {
                this$0 = FragmentManagerImpl.this;
                fragment = fragment1;
                super();
            }
                }
);
                fragment.mView.startAnimation(animation);
            }
            fragment.mContainer.removeView(fragment.mView);
        }
        fragment.mContainer = null;
        fragment.mView = null;
        fragment.mInnerView = null;
          goto _L12
    }

    public void noteStateNotSaved()
    {
        mStateSaved = false;
    }

    public View onCreateView(String s, Context context, AttributeSet attributeset)
    {
        View view = null;
        if("fragment".equals(s)) goto _L2; else goto _L1
_L1:
        return view;
_L2:
        String s1 = attributeset.getAttributeValue(null, "class");
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, FragmentTag.Fragment);
        if(s1 == null)
            s1 = typedarray.getString(0);
        int i = typedarray.getResourceId(1, -1);
        String s2 = typedarray.getString(2);
        typedarray.recycle();
        if(!Fragment.isSupportFragmentClass(mActivity, s1))
            continue; /* Loop/switch isn't completed */
        int j;
        if(false)
            j = null.getId();
        else
            j = 0;
        if(j == -1 && i == -1 && s2 == null)
            throw new IllegalArgumentException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Must specify unique android:id, android:tag, or have a parent with an id for ").append(s1).toString());
        Fragment fragment;
        if(i != -1)
            fragment = findFragmentById(i);
        else
            fragment = null;
        if(fragment == null && s2 != null)
            fragment = findFragmentByTag(s2);
        if(fragment == null && j != -1)
            fragment = findFragmentById(j);
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("onCreateView: id=0x").append(Integer.toHexString(i)).append(" fname=").append(s1).append(" existing=").append(fragment).toString());
        if(fragment == null)
        {
            fragment = Fragment.instantiate(context, s1);
            fragment.mFromLayout = true;
            int k;
            if(i != 0)
                k = i;
            else
                k = j;
            fragment.mFragmentId = k;
            fragment.mContainerId = j;
            fragment.mTag = s2;
            fragment.mInLayout = true;
            fragment.mFragmentManager = this;
            fragment.onInflate(mActivity, attributeset, fragment.mSavedFragmentState);
            addFragment(fragment, true);
        } else
        {
            if(fragment.mInLayout)
                throw new IllegalArgumentException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Duplicate id 0x").append(Integer.toHexString(i)).append(", tag ").append(s2).append(", or parent id 0x").append(Integer.toHexString(j)).append(" with another fragment for ").append(s1).toString());
            fragment.mInLayout = true;
            if(!fragment.mRetaining)
                fragment.onInflate(mActivity, attributeset, fragment.mSavedFragmentState);
        }
        if(mCurState < 1 && fragment.mFromLayout)
            moveToState(fragment, 1, 0, 0, false);
        else
            moveToState(fragment);
        if(fragment.mView == null)
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(s1).append(" did not create a view.").toString());
        if(i != 0)
            fragment.mView.setId(i);
        if(fragment.mView.getTag() == null)
            fragment.mView.setTag(s2);
        view = fragment.mView;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void performPendingDeferredStart(Fragment fragment)
    {
        if(fragment.mDeferStart)
            if(mExecutingActions)
            {
                mHavePendingDeferredStart = true;
            } else
            {
                fragment.mDeferStart = false;
                moveToState(fragment, mCurState, 0, 0, false);
            }
    }

    public void popBackStack()
    {
        enqueueAction(new Runnable() {

            public void run()
            {
                popBackStackState(mActivity.mHandler, null, -1, 0);
            }

            final FragmentManagerImpl this$0;

            
            {
                this$0 = FragmentManagerImpl.this;
                super();
            }
        }
, false);
    }

    public void popBackStack(final int id, final int flags)
    {
        if(id < 0)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Bad id: ").append(id).toString());
        } else
        {
            enqueueAction(new Runnable() {

                public void run()
                {
                    popBackStackState(mActivity.mHandler, null, id, flags);
                }

                final FragmentManagerImpl this$0;
                final int val$flags;
                final int val$id;

            
            {
                this$0 = FragmentManagerImpl.this;
                id = i;
                flags = j;
                super();
            }
            }
, false);
            return;
        }
    }

    public void popBackStack(final String name, final int flags)
    {
        enqueueAction(new Runnable() {

            public void run()
            {
                popBackStackState(mActivity.mHandler, name, -1, flags);
            }

            final FragmentManagerImpl this$0;
            final int val$flags;
            final String val$name;

            
            {
                this$0 = FragmentManagerImpl.this;
                name = s;
                flags = i;
                super();
            }
        }
, false);
    }

    public boolean popBackStackImmediate()
    {
        checkStateLoss();
        executePendingTransactions();
        return popBackStackState(mActivity.mHandler, null, -1, 0);
    }

    public boolean popBackStackImmediate(int i, int j)
    {
        checkStateLoss();
        executePendingTransactions();
        if(i < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad id: ").append(i).toString());
        else
            return popBackStackState(mActivity.mHandler, null, i, j);
    }

    public boolean popBackStackImmediate(String s, int i)
    {
        checkStateLoss();
        executePendingTransactions();
        return popBackStackState(mActivity.mHandler, s, -1, i);
    }

    boolean popBackStackState(Handler handler, String s, int i, int j)
    {
        if(mBackStack != null) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L5:
        return flag;
_L2:
        if(s != null || i >= 0 || (j & 1) != 0) goto _L4; else goto _L3
_L3:
        int l1;
label0:
        {
            l1 = -1 + mBackStack.size();
            if(l1 >= 0)
                break label0;
            flag = false;
        }
          goto _L5
        BackStackRecord backstackrecord3 = (BackStackRecord)mBackStack.remove(l1);
        SparseArray sparsearray2 = new SparseArray();
        SparseArray sparsearray3 = new SparseArray();
        backstackrecord3.calculateBackFragments(sparsearray2, sparsearray3);
        backstackrecord3.popFromBackStack(true, null, sparsearray2, sparsearray3);
        reportBackStackChanged();
_L10:
        flag = true;
          goto _L5
_L4:
        int k;
        k = -1;
        if(s == null && i < 0)
            break MISSING_BLOCK_LABEL_267;
        k = -1 + mBackStack.size();
_L9:
        BackStackRecord backstackrecord2;
label1:
        {
            if(k < 0)
                break label1;
            backstackrecord2 = (BackStackRecord)mBackStack.get(k);
        }
          goto _L6
_L8:
        if(k >= 0)
            break MISSING_BLOCK_LABEL_203;
        flag = false;
          goto _L5
_L6:
        if(s != null && s.equals(backstackrecord2.getName()) || i >= 0 && i == backstackrecord2.mIndex) goto _L8; else goto _L7
_L7:
        k--;
          goto _L9
        if((j & 1) != 0)
        {
            k--;
            do
            {
                if(k < 0)
                    break;
                BackStackRecord backstackrecord1 = (BackStackRecord)mBackStack.get(k);
                if((s == null || !s.equals(backstackrecord1.getName())) && (i < 0 || i != backstackrecord1.mIndex))
                    break;
                k--;
            } while(true);
        }
label2:
        {
            if(k != -1 + mBackStack.size())
                break label2;
            flag = false;
        }
          goto _L5
        ArrayList arraylist = new ArrayList();
        for(int l = -1 + mBackStack.size(); l > k; l--)
            arraylist.add(mBackStack.remove(l));

        int i1 = -1 + arraylist.size();
        SparseArray sparsearray = new SparseArray();
        SparseArray sparsearray1 = new SparseArray();
        for(int j1 = 0; j1 <= i1; j1++)
            ((BackStackRecord)arraylist.get(j1)).calculateBackFragments(sparsearray, sparsearray1);

        BackStackRecord.TransitionState transitionstate = null;
        int k1 = 0;
        while(k1 <= i1) 
        {
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("Popping back stack state: ").append(arraylist.get(k1)).toString());
            BackStackRecord backstackrecord = (BackStackRecord)arraylist.get(k1);
            boolean flag1;
            if(k1 == i1)
                flag1 = true;
            else
                flag1 = false;
            transitionstate = backstackrecord.popFromBackStack(flag1, transitionstate, sparsearray, sparsearray1);
            k1++;
        }
        reportBackStackChanged();
          goto _L10
    }

    public void putFragment(Bundle bundle, String s, Fragment fragment)
    {
        if(fragment.mIndex < 0)
            throwException(new IllegalStateException((new StringBuilder()).append("Fragment ").append(fragment).append(" is not currently in the FragmentManager").toString()));
        bundle.putInt(s, fragment.mIndex);
    }

    public void removeFragment(Fragment fragment, int i, int j)
    {
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("remove: ").append(fragment).append(" nesting=").append(fragment.mBackStackNesting).toString());
        boolean flag;
        if(!fragment.isInBackStack())
            flag = true;
        else
            flag = false;
        if(!fragment.mDetached || flag)
        {
            if(mAdded != null)
                mAdded.remove(fragment);
            if(fragment.mHasMenu && fragment.mMenuVisible)
                mNeedMenuInvalidate = true;
            fragment.mAdded = false;
            fragment.mRemoving = true;
            int k;
            if(flag)
                k = 0;
            else
                k = 1;
            moveToState(fragment, k, i, j, false);
        }
    }

    public void removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener onbackstackchangedlistener)
    {
        if(mBackStackChangeListeners != null)
            mBackStackChangeListeners.remove(onbackstackchangedlistener);
    }

    void reportBackStackChanged()
    {
        if(mBackStackChangeListeners != null)
        {
            for(int i = 0; i < mBackStackChangeListeners.size(); i++)
                ((FragmentManager.OnBackStackChangedListener)mBackStackChangeListeners.get(i)).onBackStackChanged();

        }
    }

    void restoreAllState(Parcelable parcelable, ArrayList arraylist)
    {
        if(parcelable != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        FragmentManagerState fragmentmanagerstate = (FragmentManagerState)parcelable;
        if(fragmentmanagerstate.mActive != null)
        {
            if(arraylist != null)
            {
                for(int i1 = 0; i1 < arraylist.size(); i1++)
                {
                    Fragment fragment3 = (Fragment)arraylist.get(i1);
                    if(DEBUG)
                        Log.v("FragmentManager", (new StringBuilder()).append("restoreAllState: re-attaching retained ").append(fragment3).toString());
                    FragmentState fragmentstate1 = fragmentmanagerstate.mActive[fragment3.mIndex];
                    fragmentstate1.mInstance = fragment3;
                    fragment3.mSavedViewState = null;
                    fragment3.mBackStackNesting = 0;
                    fragment3.mInLayout = false;
                    fragment3.mAdded = false;
                    fragment3.mTarget = null;
                    if(fragmentstate1.mSavedFragmentState != null)
                    {
                        fragmentstate1.mSavedFragmentState.setClassLoader(mActivity.getClassLoader());
                        fragment3.mSavedViewState = fragmentstate1.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                        fragment3.mSavedFragmentState = fragmentstate1.mSavedFragmentState;
                    }
                }

            }
            mActive = new ArrayList(fragmentmanagerstate.mActive.length);
            if(mAvailIndices != null)
                mAvailIndices.clear();
            int i = 0;
            while(i < fragmentmanagerstate.mActive.length) 
            {
                FragmentState fragmentstate = fragmentmanagerstate.mActive[i];
                if(fragmentstate != null)
                {
                    Fragment fragment2 = fragmentstate.instantiate(mActivity, mParent);
                    if(DEBUG)
                        Log.v("FragmentManager", (new StringBuilder()).append("restoreAllState: active #").append(i).append(": ").append(fragment2).toString());
                    mActive.add(fragment2);
                    fragmentstate.mInstance = null;
                } else
                {
                    mActive.add(null);
                    if(mAvailIndices == null)
                        mAvailIndices = new ArrayList();
                    if(DEBUG)
                        Log.v("FragmentManager", (new StringBuilder()).append("restoreAllState: avail #").append(i).toString());
                    mAvailIndices.add(Integer.valueOf(i));
                }
                i++;
            }
            if(arraylist != null)
            {
                int l = 0;
                while(l < arraylist.size()) 
                {
                    Fragment fragment1 = (Fragment)arraylist.get(l);
                    if(fragment1.mTargetIndex >= 0)
                        if(fragment1.mTargetIndex < mActive.size())
                        {
                            fragment1.mTarget = (Fragment)mActive.get(fragment1.mTargetIndex);
                        } else
                        {
                            Log.w("FragmentManager", (new StringBuilder()).append("Re-attaching retained fragment ").append(fragment1).append(" target no longer exists: ").append(fragment1.mTargetIndex).toString());
                            fragment1.mTarget = null;
                        }
                    l++;
                }
            }
            if(fragmentmanagerstate.mAdded != null)
            {
                mAdded = new ArrayList(fragmentmanagerstate.mAdded.length);
                for(int k = 0; k < fragmentmanagerstate.mAdded.length; k++)
                {
                    Fragment fragment = (Fragment)mActive.get(fragmentmanagerstate.mAdded[k]);
                    if(fragment == null)
                        throwException(new IllegalStateException((new StringBuilder()).append("No instantiated fragment for index #").append(fragmentmanagerstate.mAdded[k]).toString()));
                    fragment.mAdded = true;
                    if(DEBUG)
                        Log.v("FragmentManager", (new StringBuilder()).append("restoreAllState: added #").append(k).append(": ").append(fragment).toString());
                    if(mAdded.contains(fragment))
                        throw new IllegalStateException("Already added!");
                    mAdded.add(fragment);
                }

            } else
            {
                mAdded = null;
            }
            if(fragmentmanagerstate.mBackStack != null)
            {
                mBackStack = new ArrayList(fragmentmanagerstate.mBackStack.length);
                int j = 0;
                while(j < fragmentmanagerstate.mBackStack.length) 
                {
                    BackStackRecord backstackrecord = fragmentmanagerstate.mBackStack[j].instantiate(this);
                    if(DEBUG)
                    {
                        Log.v("FragmentManager", (new StringBuilder()).append("restoreAllState: back stack #").append(j).append(" (index ").append(backstackrecord.mIndex).append("): ").append(backstackrecord).toString());
                        backstackrecord.dump("  ", new PrintWriter(new LogWriter("FragmentManager")), false);
                    }
                    mBackStack.add(backstackrecord);
                    if(backstackrecord.mIndex >= 0)
                        setBackStackIndex(backstackrecord.mIndex, backstackrecord);
                    j++;
                }
            } else
            {
                mBackStack = null;
            }
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    ArrayList retainNonConfig()
    {
        ArrayList arraylist = null;
        if(mActive != null)
        {
            int i = 0;
            while(i < mActive.size()) 
            {
                Fragment fragment = (Fragment)mActive.get(i);
                if(fragment == null || !fragment.mRetainInstance)
                    continue;
                if(arraylist == null)
                    arraylist = new ArrayList();
                arraylist.add(fragment);
                fragment.mRetaining = true;
                int j;
                if(fragment.mTarget != null)
                    j = fragment.mTarget.mIndex;
                else
                    j = -1;
                fragment.mTargetIndex = j;
                if(DEBUG)
                    Log.v("FragmentManager", (new StringBuilder()).append("retainNonConfig: keeping retained ").append(fragment).toString());
                i++;
            }
        }
        return arraylist;
    }

    Parcelable saveAllState()
    {
        Object obj;
        obj = null;
        execPendingActions();
        if(HONEYCOMB)
            mStateSaved = true;
        if(mActive != null && mActive.size() > 0) goto _L2; else goto _L1
_L1:
        return ((Parcelable) (obj));
_L2:
        int i = mActive.size();
        FragmentState afragmentstate[] = new FragmentState[i];
        boolean flag = false;
        int j = 0;
        while(j < i) 
        {
            Fragment fragment = (Fragment)mActive.get(j);
            if(fragment == null)
                continue;
            if(fragment.mIndex < 0)
                throwException(new IllegalStateException((new StringBuilder()).append("Failure saving state: active ").append(fragment).append(" has cleared index: ").append(fragment.mIndex).toString()));
            flag = true;
            FragmentState fragmentstate = new FragmentState(fragment);
            afragmentstate[j] = fragmentstate;
            if(fragment.mState > 0 && fragmentstate.mSavedFragmentState == null)
            {
                fragmentstate.mSavedFragmentState = saveFragmentBasicState(fragment);
                if(fragment.mTarget != null)
                {
                    if(fragment.mTarget.mIndex < 0)
                        throwException(new IllegalStateException((new StringBuilder()).append("Failure saving state: ").append(fragment).append(" has target not in fragment manager: ").append(fragment.mTarget).toString()));
                    if(fragmentstate.mSavedFragmentState == null)
                        fragmentstate.mSavedFragmentState = new Bundle();
                    putFragment(fragmentstate.mSavedFragmentState, "android:target_state", fragment.mTarget);
                    if(fragment.mTargetRequestCode != 0)
                        fragmentstate.mSavedFragmentState.putInt("android:target_req_state", fragment.mTargetRequestCode);
                }
            } else
            {
                fragmentstate.mSavedFragmentState = fragment.mSavedFragmentState;
            }
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("Saved state of ").append(fragment).append(": ").append(fragmentstate.mSavedFragmentState).toString());
            j++;
        }
        if(!flag)
        {
            if(DEBUG)
                Log.v("FragmentManager", "saveAllState: no fragments!");
        } else
        {
            int ai[] = null;
            BackStackState abackstackstate[] = null;
            if(mAdded != null)
            {
                int i1 = mAdded.size();
                if(i1 > 0)
                {
                    ai = new int[i1];
                    for(int j1 = 0; j1 < i1; j1++)
                    {
                        ai[j1] = ((Fragment)mAdded.get(j1)).mIndex;
                        if(ai[j1] < 0)
                            throwException(new IllegalStateException((new StringBuilder()).append("Failure saving state: active ").append(mAdded.get(j1)).append(" has cleared index: ").append(ai[j1]).toString()));
                        if(DEBUG)
                            Log.v("FragmentManager", (new StringBuilder()).append("saveAllState: adding fragment #").append(j1).append(": ").append(mAdded.get(j1)).toString());
                    }

                }
            }
            if(mBackStack != null)
            {
                int k = mBackStack.size();
                if(k > 0)
                {
                    abackstackstate = new BackStackState[k];
                    for(int l = 0; l < k; l++)
                    {
                        abackstackstate[l] = new BackStackState(this, (BackStackRecord)mBackStack.get(l));
                        if(DEBUG)
                            Log.v("FragmentManager", (new StringBuilder()).append("saveAllState: adding back stack #").append(l).append(": ").append(mBackStack.get(l)).toString());
                    }

                }
            }
            obj = new FragmentManagerState();
            obj.mActive = afragmentstate;
            obj.mAdded = ai;
            obj.mBackStack = abackstackstate;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    Bundle saveFragmentBasicState(Fragment fragment)
    {
        Bundle bundle = null;
        if(mStateBundle == null)
            mStateBundle = new Bundle();
        fragment.performSaveInstanceState(mStateBundle);
        if(!mStateBundle.isEmpty())
        {
            bundle = mStateBundle;
            mStateBundle = null;
        }
        if(fragment.mView != null)
            saveFragmentViewState(fragment);
        if(fragment.mSavedViewState != null)
        {
            if(bundle == null)
                bundle = new Bundle();
            bundle.putSparseParcelableArray("android:view_state", fragment.mSavedViewState);
        }
        if(!fragment.mUserVisibleHint)
        {
            if(bundle == null)
                bundle = new Bundle();
            bundle.putBoolean("android:user_visible_hint", fragment.mUserVisibleHint);
        }
        return bundle;
    }

    public Fragment.SavedState saveFragmentInstanceState(Fragment fragment)
    {
        Fragment.SavedState savedstate = null;
        if(fragment.mIndex < 0)
            throwException(new IllegalStateException((new StringBuilder()).append("Fragment ").append(fragment).append(" is not currently in the FragmentManager").toString()));
        if(fragment.mState > 0)
        {
            Bundle bundle = saveFragmentBasicState(fragment);
            if(bundle != null)
                savedstate = new Fragment.SavedState(bundle);
        }
        return savedstate;
    }

    void saveFragmentViewState(Fragment fragment)
    {
        if(fragment.mInnerView != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(mStateArray == null)
            mStateArray = new SparseArray();
        else
            mStateArray.clear();
        fragment.mInnerView.saveHierarchyState(mStateArray);
        if(mStateArray.size() > 0)
        {
            fragment.mSavedViewState = mStateArray;
            mStateArray = null;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void setBackStackIndex(int i, BackStackRecord backstackrecord)
    {
        this;
        JVM INSTR monitorenter ;
        if(mBackStackIndices == null)
            mBackStackIndices = new ArrayList();
        int j = mBackStackIndices.size();
        if(i < j)
        {
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("Setting back stack index ").append(i).append(" to ").append(backstackrecord).toString());
            mBackStackIndices.set(i, backstackrecord);
        } else
        {
            for(; j < i; j++)
            {
                mBackStackIndices.add(null);
                if(mAvailBackStackIndices == null)
                    mAvailBackStackIndices = new ArrayList();
                if(DEBUG)
                    Log.v("FragmentManager", (new StringBuilder()).append("Adding available back stack index ").append(j).toString());
                mAvailBackStackIndices.add(Integer.valueOf(j));
            }

            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("Adding back stack index ").append(i).append(" with ").append(backstackrecord).toString());
            mBackStackIndices.add(backstackrecord);
        }
        return;
    }

    public void showFragment(Fragment fragment, int i, int j)
    {
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("show: ").append(fragment).toString());
        if(fragment.mHidden)
        {
            fragment.mHidden = false;
            if(fragment.mView != null)
            {
                Animation animation = loadAnimation(fragment, i, true, j);
                if(animation != null)
                    fragment.mView.startAnimation(animation);
                fragment.mView.setVisibility(0);
            }
            if(fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible)
                mNeedMenuInvalidate = true;
            fragment.onHiddenChanged(false);
        }
    }

    void startPendingDeferredFragments()
    {
        if(mActive != null)
        {
            int i = 0;
            while(i < mActive.size()) 
            {
                Fragment fragment = (Fragment)mActive.get(i);
                if(fragment != null)
                    performPendingDeferredStart(fragment);
                i++;
            }
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("FragmentManager{");
        stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringbuilder.append(" in ");
        if(mParent != null)
            DebugUtils.buildShortClassTag(mParent, stringbuilder);
        else
            DebugUtils.buildShortClassTag(mActivity, stringbuilder);
        stringbuilder.append("}}");
        return stringbuilder.toString();
    }

    static final Interpolator ACCELERATE_CUBIC = new AccelerateInterpolator(1.5F);
    static final Interpolator ACCELERATE_QUINT = new AccelerateInterpolator(2.5F);
    static final int ANIM_DUR = 220;
    public static final int ANIM_STYLE_CLOSE_ENTER = 3;
    public static final int ANIM_STYLE_CLOSE_EXIT = 4;
    public static final int ANIM_STYLE_FADE_ENTER = 5;
    public static final int ANIM_STYLE_FADE_EXIT = 6;
    public static final int ANIM_STYLE_OPEN_ENTER = 1;
    public static final int ANIM_STYLE_OPEN_EXIT = 2;
    static boolean DEBUG = false;
    static final Interpolator DECELERATE_CUBIC = new DecelerateInterpolator(1.5F);
    static final Interpolator DECELERATE_QUINT = new DecelerateInterpolator(2.5F);
    static final boolean HONEYCOMB = false;
    static final String TAG = "FragmentManager";
    static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
    static final String TARGET_STATE_TAG = "android:target_state";
    static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
    static final String VIEW_STATE_TAG = "android:view_state";
    ArrayList mActive;
    FragmentActivity mActivity;
    ArrayList mAdded;
    ArrayList mAvailBackStackIndices;
    ArrayList mAvailIndices;
    ArrayList mBackStack;
    ArrayList mBackStackChangeListeners;
    ArrayList mBackStackIndices;
    FragmentContainer mContainer;
    ArrayList mCreatedMenus;
    int mCurState;
    boolean mDestroyed;
    Runnable mExecCommit;
    boolean mExecutingActions;
    boolean mHavePendingDeferredStart;
    boolean mNeedMenuInvalidate;
    String mNoTransactionsBecause;
    Fragment mParent;
    ArrayList mPendingActions;
    SparseArray mStateArray;
    Bundle mStateBundle;
    boolean mStateSaved;
    Runnable mTmpActions[];

    static 
    {
        boolean flag = false;
        DEBUG = false;
        if(android.os.Build.VERSION.SDK_INT >= 11)
            flag = true;
        HONEYCOMB = flag;
    }
}
