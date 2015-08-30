// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.LogWriter;
import android.util.Log;
import android.util.SparseArray;
import android.view.*;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            FragmentTransaction, FragmentManagerImpl, FragmentContainer, Fragment, 
//            SharedElementCallback, FragmentTransitionCompat21, FragmentActivity

final class BackStackRecord extends FragmentTransaction
    implements FragmentManager.BackStackEntry, Runnable
{
    public class TransitionState
    {

        public FragmentTransitionCompat21.EpicenterView enteringEpicenterView;
        public ArrayList hiddenFragmentViews;
        public ArrayMap nameOverrides;
        public View nonExistentView;
        final BackStackRecord this$0;

        public TransitionState()
        {
            this$0 = BackStackRecord.this;
            super();
            nameOverrides = new ArrayMap();
            hiddenFragmentViews = new ArrayList();
            enteringEpicenterView = new FragmentTransitionCompat21.EpicenterView();
        }
    }

    static final class Op
    {

        int cmd;
        int enterAnim;
        int exitAnim;
        Fragment fragment;
        Op next;
        int popEnterAnim;
        int popExitAnim;
        Op prev;
        ArrayList removed;

        Op()
        {
        }
    }


    public BackStackRecord(FragmentManagerImpl fragmentmanagerimpl)
    {
        mAllowAddToBackStack = true;
        mIndex = -1;
        mManager = fragmentmanagerimpl;
    }

    private TransitionState beginTransition(SparseArray sparsearray, SparseArray sparsearray1, boolean flag)
    {
        TransitionState transitionstate = new TransitionState();
        transitionstate.nonExistentView = new View(mManager.mActivity);
        boolean flag1 = false;
        for(int i = 0; i < sparsearray.size(); i++)
            if(configureTransitions(sparsearray.keyAt(i), transitionstate, flag, sparsearray, sparsearray1))
                flag1 = true;

        for(int j = 0; j < sparsearray1.size(); j++)
        {
            int k = sparsearray1.keyAt(j);
            if(sparsearray.get(k) == null && configureTransitions(k, transitionstate, flag, sparsearray, sparsearray1))
                flag1 = true;
        }

        if(!flag1)
            transitionstate = null;
        return transitionstate;
    }

    private void calculateFragments(SparseArray sparsearray, SparseArray sparsearray1)
    {
        if(mManager.mContainer.hasView()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Op op = mHead;
_L12:
        if(op == null) goto _L1; else goto _L3
_L3:
        op.cmd;
        JVM INSTR tableswitch 1 7: default 72
    //                   1 80
    //                   2 92
    //                   3 196
    //                   4 207
    //                   5 218
    //                   6 230
    //                   7 241;
           goto _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L4:
        break; /* Loop/switch isn't completed */
_L11:
        break MISSING_BLOCK_LABEL_241;
_L13:
        op = op.next;
          goto _L12
_L5:
        setLastIn(sparsearray1, op.fragment);
          goto _L13
_L6:
        Fragment fragment = op.fragment;
        if(mManager.mAdded != null)
        {
            int i = 0;
            while(i < mManager.mAdded.size()) 
            {
                Fragment fragment1 = (Fragment)mManager.mAdded.get(i);
                if(fragment == null || fragment1.mContainerId == fragment.mContainerId)
                    if(fragment1 == fragment)
                        fragment = null;
                    else
                        setFirstOut(sparsearray, fragment1);
                i++;
            }
        }
        setLastIn(sparsearray1, fragment);
          goto _L13
_L7:
        setFirstOut(sparsearray, op.fragment);
          goto _L13
_L8:
        setFirstOut(sparsearray, op.fragment);
          goto _L13
_L9:
        setLastIn(sparsearray1, op.fragment);
          goto _L13
_L10:
        setFirstOut(sparsearray, op.fragment);
          goto _L13
        setLastIn(sparsearray1, op.fragment);
          goto _L13
    }

    private void callSharedElementEnd(TransitionState transitionstate, Fragment fragment, Fragment fragment1, boolean flag, ArrayMap arraymap)
    {
        SharedElementCallback sharedelementcallback;
        if(flag)
            sharedelementcallback = fragment1.mEnterTransitionCallback;
        else
            sharedelementcallback = fragment.mEnterTransitionCallback;
        if(sharedelementcallback != null)
            sharedelementcallback.onSharedElementEnd(new ArrayList(arraymap.keySet()), new ArrayList(arraymap.values()), null);
    }

    private static Object captureExitingViews(Object obj, Fragment fragment, ArrayList arraylist, ArrayMap arraymap, View view)
    {
        if(obj != null)
            obj = FragmentTransitionCompat21.captureExitingViews(obj, fragment.getView(), arraylist, arraymap, view);
        return obj;
    }

    private boolean configureTransitions(int i, TransitionState transitionstate, boolean flag, SparseArray sparsearray, SparseArray sparsearray1)
    {
        ViewGroup viewgroup = (ViewGroup)mManager.mContainer.findViewById(i);
        boolean flag2;
        if(viewgroup == null)
        {
            flag2 = false;
        } else
        {
            final Fragment inFragment = (Fragment)sparsearray1.get(i);
            Fragment fragment = (Fragment)sparsearray.get(i);
            Object obj = getEnterTransition(inFragment, flag);
            Object obj1 = getSharedElementTransition(inFragment, fragment, flag);
            Object obj2 = getExitTransition(fragment, flag);
            if(obj == null && obj1 == null && obj2 == null)
            {
                flag2 = false;
            } else
            {
                ArrayMap arraymap = null;
                ArrayList arraylist = new ArrayList();
                boolean flag1;
                if(obj1 != null)
                {
                    arraymap = remapSharedElements(transitionstate, fragment, flag);
                    arraylist.add(transitionstate.nonExistentView);
                    arraylist.addAll(arraymap.values());
                    ArrayList arraylist1;
                    View view;
                    Object obj3;
                    FragmentTransitionCompat21.ViewRetriever viewretriever;
                    ArrayList arraylist2;
                    ArrayMap arraymap1;
                    Object obj4;
                    Object obj5;
                    View view1;
                    SharedElementCallback sharedelementcallback;
                    if(flag)
                        sharedelementcallback = fragment.mEnterTransitionCallback;
                    else
                        sharedelementcallback = inFragment.mEnterTransitionCallback;
                    if(sharedelementcallback != null)
                    {
                        ArrayList arraylist3 = new ArrayList(arraymap.keySet());
                        ArrayList arraylist4 = new ArrayList(arraymap.values());
                        sharedelementcallback.onSharedElementStart(arraylist3, arraylist4, null);
                    }
                }
                arraylist1 = new ArrayList();
                view = transitionstate.nonExistentView;
                obj3 = captureExitingViews(obj2, fragment, arraylist1, arraymap, view);
                if(mSharedElementTargetNames != null && arraymap != null)
                {
                    obj5 = mSharedElementTargetNames.get(0);
                    view1 = (View)arraymap.get(obj5);
                    if(view1 != null)
                    {
                        if(obj3 != null)
                            FragmentTransitionCompat21.setEpicenter(obj3, view1);
                        if(obj1 != null)
                            FragmentTransitionCompat21.setEpicenter(obj1, view1);
                    }
                }
                viewretriever = new FragmentTransitionCompat21.ViewRetriever() {

                    public View getView()
                    {
                        return inFragment.getView();
                    }

                    final BackStackRecord this$0;
                    final Fragment val$inFragment;

            
            {
                this$0 = BackStackRecord.this;
                inFragment = fragment;
                super();
            }
                }
;
                if(obj1 != null)
                    prepareSharedElementTransition(transitionstate, viewgroup, obj1, inFragment, fragment, flag, arraylist);
                arraylist2 = new ArrayList();
                arraymap1 = new ArrayMap();
                if(flag)
                    flag1 = inFragment.getAllowReturnTransitionOverlap();
                else
                    flag1 = inFragment.getAllowEnterTransitionOverlap();
                obj4 = FragmentTransitionCompat21.mergeTransitions(obj, obj3, obj1, flag1);
                if(obj4 != null)
                {
                    FragmentTransitionCompat21.addTransitionTargets(obj, obj1, viewgroup, viewretriever, transitionstate.nonExistentView, transitionstate.enteringEpicenterView, transitionstate.nameOverrides, arraylist2, arraymap1, arraylist);
                    excludeHiddenFragmentsAfterEnter(viewgroup, transitionstate, i, obj4);
                    FragmentTransitionCompat21.excludeTarget(obj4, transitionstate.nonExistentView, true);
                    excludeHiddenFragments(transitionstate, i, obj4);
                    FragmentTransitionCompat21.beginDelayedTransition(viewgroup, obj4);
                    FragmentTransitionCompat21.cleanupTransitions(viewgroup, transitionstate.nonExistentView, obj, arraylist2, obj3, arraylist1, obj1, arraylist, obj4, transitionstate.hiddenFragmentViews, arraymap1);
                }
                if(obj4 != null)
                    flag2 = true;
                else
                    flag2 = false;
            }
        }
        return flag2;
    }

    private void doAddOp(int i, Fragment fragment, String s, int j)
    {
        fragment.mFragmentManager = mManager;
        if(s != null)
        {
            if(fragment.mTag != null && !s.equals(fragment.mTag))
                throw new IllegalStateException((new StringBuilder()).append("Can't change tag of fragment ").append(fragment).append(": was ").append(fragment.mTag).append(" now ").append(s).toString());
            fragment.mTag = s;
        }
        if(i != 0)
        {
            if(fragment.mFragmentId != 0 && fragment.mFragmentId != i)
                throw new IllegalStateException((new StringBuilder()).append("Can't change container ID of fragment ").append(fragment).append(": was ").append(fragment.mFragmentId).append(" now ").append(i).toString());
            fragment.mFragmentId = i;
            fragment.mContainerId = i;
        }
        Op op = new Op();
        op.cmd = j;
        op.fragment = fragment;
        addOp(op);
    }

    private void excludeHiddenFragments(TransitionState transitionstate, int i, Object obj)
    {
        if(mManager.mAdded != null)
        {
            int j = 0;
            while(j < mManager.mAdded.size()) 
            {
                Fragment fragment = (Fragment)mManager.mAdded.get(j);
                if(fragment.mView != null && fragment.mContainer != null && fragment.mContainerId == i)
                    if(fragment.mHidden)
                    {
                        if(!transitionstate.hiddenFragmentViews.contains(fragment.mView))
                        {
                            FragmentTransitionCompat21.excludeTarget(obj, fragment.mView, true);
                            transitionstate.hiddenFragmentViews.add(fragment.mView);
                        }
                    } else
                    {
                        FragmentTransitionCompat21.excludeTarget(obj, fragment.mView, false);
                        transitionstate.hiddenFragmentViews.remove(fragment.mView);
                    }
                j++;
            }
        }
    }

    private void excludeHiddenFragmentsAfterEnter(final View sceneRoot, final TransitionState state, final int containerId, final Object transition)
    {
        sceneRoot.getViewTreeObserver().addOnPreDrawListener(new android.view.ViewTreeObserver.OnPreDrawListener() {

            public boolean onPreDraw()
            {
                sceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
                excludeHiddenFragments(state, containerId, transition);
                return true;
            }

            final BackStackRecord this$0;
            final int val$containerId;
            final View val$sceneRoot;
            final TransitionState val$state;
            final Object val$transition;

            
            {
                this$0 = BackStackRecord.this;
                sceneRoot = view;
                state = transitionstate;
                containerId = i;
                transition = obj;
                super();
            }
        }
);
    }

    private static Object getEnterTransition(Fragment fragment, boolean flag)
    {
        Object obj1;
        if(fragment == null)
        {
            obj1 = null;
        } else
        {
            Object obj;
            if(flag)
                obj = fragment.getReenterTransition();
            else
                obj = fragment.getEnterTransition();
            obj1 = FragmentTransitionCompat21.cloneTransition(obj);
        }
        return obj1;
    }

    private static Object getExitTransition(Fragment fragment, boolean flag)
    {
        Object obj1;
        if(fragment == null)
        {
            obj1 = null;
        } else
        {
            Object obj;
            if(flag)
                obj = fragment.getReturnTransition();
            else
                obj = fragment.getExitTransition();
            obj1 = FragmentTransitionCompat21.cloneTransition(obj);
        }
        return obj1;
    }

    private static Object getSharedElementTransition(Fragment fragment, Fragment fragment1, boolean flag)
    {
        Object obj;
        if(fragment == null || fragment1 == null)
        {
            obj = null;
        } else
        {
            Object obj1;
            if(flag)
                obj1 = fragment1.getSharedElementReturnTransition();
            else
                obj1 = fragment.getSharedElementEnterTransition();
            obj = FragmentTransitionCompat21.cloneTransition(obj1);
        }
        return obj;
    }

    private ArrayMap mapEnteringSharedElements(TransitionState transitionstate, Fragment fragment, boolean flag)
    {
        ArrayMap arraymap = new ArrayMap();
        View view = fragment.getView();
        if(view != null && mSharedElementSourceNames != null)
        {
            FragmentTransitionCompat21.findNamedViews(arraymap, view);
            if(flag)
                arraymap = remapNames(mSharedElementSourceNames, mSharedElementTargetNames, arraymap);
            else
                arraymap.retainAll(mSharedElementTargetNames);
        }
        return arraymap;
    }

    private ArrayMap mapSharedElementsIn(TransitionState transitionstate, boolean flag, Fragment fragment)
    {
        ArrayMap arraymap = mapEnteringSharedElements(transitionstate, fragment, flag);
        if(flag)
        {
            if(fragment.mExitTransitionCallback != null)
                fragment.mExitTransitionCallback.onMapSharedElements(mSharedElementTargetNames, arraymap);
            setBackNameOverrides(transitionstate, arraymap, true);
        } else
        {
            if(fragment.mEnterTransitionCallback != null)
                fragment.mEnterTransitionCallback.onMapSharedElements(mSharedElementTargetNames, arraymap);
            setNameOverrides(transitionstate, arraymap, true);
        }
        return arraymap;
    }

    private void prepareSharedElementTransition(final TransitionState state, final View sceneRoot, final Object sharedElementTransition, final Fragment inFragment, final Fragment outFragment, final boolean isBack, final ArrayList sharedElementTargets)
    {
        sceneRoot.getViewTreeObserver().addOnPreDrawListener(new android.view.ViewTreeObserver.OnPreDrawListener() {

            public boolean onPreDraw()
            {
                sceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
                if(sharedElementTransition != null)
                {
                    FragmentTransitionCompat21.removeTargets(sharedElementTransition, sharedElementTargets);
                    sharedElementTargets.clear();
                    ArrayMap arraymap = mapSharedElementsIn(state, isBack, inFragment);
                    sharedElementTargets.add(state.nonExistentView);
                    sharedElementTargets.addAll(arraymap.values());
                    FragmentTransitionCompat21.addTargets(sharedElementTransition, sharedElementTargets);
                    setEpicenterIn(arraymap, state);
                    callSharedElementEnd(state, inFragment, outFragment, isBack, arraymap);
                }
                return true;
            }

            final BackStackRecord this$0;
            final Fragment val$inFragment;
            final boolean val$isBack;
            final Fragment val$outFragment;
            final View val$sceneRoot;
            final ArrayList val$sharedElementTargets;
            final Object val$sharedElementTransition;
            final TransitionState val$state;

            
            {
                this$0 = BackStackRecord.this;
                sceneRoot = view;
                sharedElementTransition = obj;
                sharedElementTargets = arraylist;
                state = transitionstate;
                isBack = flag;
                inFragment = fragment;
                outFragment = fragment1;
                super();
            }
        }
);
    }

    private static ArrayMap remapNames(ArrayList arraylist, ArrayList arraylist1, ArrayMap arraymap)
    {
        if(!arraymap.isEmpty())
        {
            ArrayMap arraymap1 = new ArrayMap();
            int i = arraylist.size();
            for(int j = 0; j < i; j++)
            {
                View view = (View)arraymap.get(arraylist.get(j));
                if(view != null)
                    arraymap1.put(arraylist1.get(j), view);
            }

            arraymap = arraymap1;
        }
        return arraymap;
    }

    private ArrayMap remapSharedElements(TransitionState transitionstate, Fragment fragment, boolean flag)
    {
        ArrayMap arraymap = new ArrayMap();
        if(mSharedElementSourceNames != null)
        {
            FragmentTransitionCompat21.findNamedViews(arraymap, fragment.getView());
            if(flag)
                arraymap.retainAll(mSharedElementTargetNames);
            else
                arraymap = remapNames(mSharedElementSourceNames, mSharedElementTargetNames, arraymap);
        }
        if(flag)
        {
            if(fragment.mEnterTransitionCallback != null)
                fragment.mEnterTransitionCallback.onMapSharedElements(mSharedElementTargetNames, arraymap);
            setBackNameOverrides(transitionstate, arraymap, false);
        } else
        {
            if(fragment.mExitTransitionCallback != null)
                fragment.mExitTransitionCallback.onMapSharedElements(mSharedElementTargetNames, arraymap);
            setNameOverrides(transitionstate, arraymap, false);
        }
        return arraymap;
    }

    private void setBackNameOverrides(TransitionState transitionstate, ArrayMap arraymap, boolean flag)
    {
        int i;
        int j;
        if(mSharedElementTargetNames == null)
            i = 0;
        else
            i = mSharedElementTargetNames.size();
        j = 0;
        while(j < i) 
        {
            String s = (String)mSharedElementSourceNames.get(j);
            View view = (View)arraymap.get((String)mSharedElementTargetNames.get(j));
            if(view != null)
            {
                String s1 = FragmentTransitionCompat21.getTransitionName(view);
                if(flag)
                    setNameOverride(transitionstate.nameOverrides, s, s1);
                else
                    setNameOverride(transitionstate.nameOverrides, s1, s);
            }
            j++;
        }
    }

    private void setEpicenterIn(ArrayMap arraymap, TransitionState transitionstate)
    {
        if(mSharedElementTargetNames != null && !arraymap.isEmpty())
        {
            View view = (View)arraymap.get(mSharedElementTargetNames.get(0));
            if(view != null)
                transitionstate.enteringEpicenterView.epicenter = view;
        }
    }

    private static void setFirstOut(SparseArray sparsearray, Fragment fragment)
    {
        if(fragment != null)
        {
            int i = fragment.mContainerId;
            if(i != 0 && !fragment.isHidden() && fragment.isAdded() && fragment.getView() != null && sparsearray.get(i) == null)
                sparsearray.put(i, fragment);
        }
    }

    private void setLastIn(SparseArray sparsearray, Fragment fragment)
    {
        if(fragment != null)
        {
            int i = fragment.mContainerId;
            if(i != 0)
                sparsearray.put(i, fragment);
        }
    }

    private static void setNameOverride(ArrayMap arraymap, String s, String s1)
    {
        if(s == null || s1 == null || s.equals(s1)) goto _L2; else goto _L1
_L1:
        int i = 0;
_L5:
        if(i >= arraymap.size())
            break MISSING_BLOCK_LABEL_52;
        if(!s.equals(arraymap.valueAt(i))) goto _L4; else goto _L3
_L3:
        arraymap.setValueAt(i, s1);
_L2:
        return;
_L4:
        i++;
          goto _L5
        arraymap.put(s, s1);
          goto _L2
    }

    private void setNameOverrides(TransitionState transitionstate, ArrayMap arraymap, boolean flag)
    {
        int i = arraymap.size();
        int j = 0;
        while(j < i) 
        {
            String s = (String)arraymap.keyAt(j);
            String s1 = FragmentTransitionCompat21.getTransitionName((View)arraymap.valueAt(j));
            if(flag)
                setNameOverride(transitionstate.nameOverrides, s, s1);
            else
                setNameOverride(transitionstate.nameOverrides, s1, s);
            j++;
        }
    }

    private static void setNameOverrides(TransitionState transitionstate, ArrayList arraylist, ArrayList arraylist1)
    {
        if(arraylist != null)
        {
            for(int i = 0; i < arraylist.size(); i++)
            {
                String s = (String)arraylist.get(i);
                String s1 = (String)arraylist1.get(i);
                setNameOverride(transitionstate.nameOverrides, s, s1);
            }

        }
    }

    public FragmentTransaction add(int i, Fragment fragment)
    {
        doAddOp(i, fragment, null, 1);
        return this;
    }

    public FragmentTransaction add(int i, Fragment fragment, String s)
    {
        doAddOp(i, fragment, s, 1);
        return this;
    }

    public FragmentTransaction add(Fragment fragment, String s)
    {
        doAddOp(0, fragment, s, 1);
        return this;
    }

    void addOp(Op op)
    {
        if(mHead == null)
        {
            mTail = op;
            mHead = op;
        } else
        {
            op.prev = mTail;
            mTail.next = op;
            mTail = op;
        }
        op.enterAnim = mEnterAnim;
        op.exitAnim = mExitAnim;
        op.popEnterAnim = mPopEnterAnim;
        op.popExitAnim = mPopExitAnim;
        mNumOp = 1 + mNumOp;
    }

    public FragmentTransaction addSharedElement(View view, String s)
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
        {
            String s1 = FragmentTransitionCompat21.getTransitionName(view);
            if(s1 == null)
                throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
            if(mSharedElementSourceNames == null)
            {
                mSharedElementSourceNames = new ArrayList();
                mSharedElementTargetNames = new ArrayList();
            }
            mSharedElementSourceNames.add(s1);
            mSharedElementTargetNames.add(s);
        }
        return this;
    }

    public FragmentTransaction addToBackStack(String s)
    {
        if(!mAllowAddToBackStack)
        {
            throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
        } else
        {
            mAddToBackStack = true;
            mName = s;
            return this;
        }
    }

    public FragmentTransaction attach(Fragment fragment)
    {
        Op op = new Op();
        op.cmd = 7;
        op.fragment = fragment;
        addOp(op);
        return this;
    }

    void bumpBackStackNesting(int i)
    {
        if(mAddToBackStack)
        {
            if(FragmentManagerImpl.DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("Bump nesting in ").append(this).append(" by ").append(i).toString());
            Op op = mHead;
            while(op != null) 
            {
                if(op.fragment != null)
                {
                    Fragment fragment1 = op.fragment;
                    fragment1.mBackStackNesting = i + fragment1.mBackStackNesting;
                    if(FragmentManagerImpl.DEBUG)
                        Log.v("FragmentManager", (new StringBuilder()).append("Bump nesting of ").append(op.fragment).append(" to ").append(op.fragment.mBackStackNesting).toString());
                }
                if(op.removed != null)
                {
                    for(int j = -1 + op.removed.size(); j >= 0; j--)
                    {
                        Fragment fragment = (Fragment)op.removed.get(j);
                        fragment.mBackStackNesting = i + fragment.mBackStackNesting;
                        if(FragmentManagerImpl.DEBUG)
                            Log.v("FragmentManager", (new StringBuilder()).append("Bump nesting of ").append(fragment).append(" to ").append(fragment.mBackStackNesting).toString());
                    }

                }
                op = op.next;
            }
        }
    }

    public void calculateBackFragments(SparseArray sparsearray, SparseArray sparsearray1)
    {
        if(mManager.mContainer.hasView()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Op op = mHead;
_L12:
        if(op == null) goto _L1; else goto _L3
_L3:
        op.cmd;
        JVM INSTR tableswitch 1 7: default 72
    //                   1 80
    //                   2 91
    //                   3 149
    //                   4 161
    //                   5 173
    //                   6 184
    //                   7 196;
           goto _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L4:
        break; /* Loop/switch isn't completed */
_L11:
        break MISSING_BLOCK_LABEL_196;
_L13:
        op = op.next;
          goto _L12
_L5:
        setFirstOut(sparsearray, op.fragment);
          goto _L13
_L6:
        if(op.removed != null)
        {
            for(int i = -1 + op.removed.size(); i >= 0; i--)
                setLastIn(sparsearray1, (Fragment)op.removed.get(i));

        }
        setFirstOut(sparsearray, op.fragment);
          goto _L13
_L7:
        setLastIn(sparsearray1, op.fragment);
          goto _L13
_L8:
        setLastIn(sparsearray1, op.fragment);
          goto _L13
_L9:
        setFirstOut(sparsearray, op.fragment);
          goto _L13
_L10:
        setLastIn(sparsearray1, op.fragment);
          goto _L13
        setFirstOut(sparsearray, op.fragment);
          goto _L13
    }

    public int commit()
    {
        return commitInternal(false);
    }

    public int commitAllowingStateLoss()
    {
        return commitInternal(true);
    }

    int commitInternal(boolean flag)
    {
        if(mCommitted)
            throw new IllegalStateException("commit already called");
        if(FragmentManagerImpl.DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder()).append("Commit: ").append(this).toString());
            dump("  ", null, new PrintWriter(new LogWriter("FragmentManager")), null);
        }
        mCommitted = true;
        if(mAddToBackStack)
            mIndex = mManager.allocBackStackIndex(this);
        else
            mIndex = -1;
        mManager.enqueueAction(this, flag);
        return mIndex;
    }

    public FragmentTransaction detach(Fragment fragment)
    {
        Op op = new Op();
        op.cmd = 6;
        op.fragment = fragment;
        addOp(op);
        return this;
    }

    public FragmentTransaction disallowAddToBackStack()
    {
        if(mAddToBackStack)
        {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        } else
        {
            mAllowAddToBackStack = false;
            return this;
        }
    }

    public void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        dump(s, printwriter, true);
    }

    public void dump(String s, PrintWriter printwriter, boolean flag)
    {
        String s1;
        Op op;
        int i;
        if(flag)
        {
            printwriter.print(s);
            printwriter.print("mName=");
            printwriter.print(mName);
            printwriter.print(" mIndex=");
            printwriter.print(mIndex);
            printwriter.print(" mCommitted=");
            printwriter.println(mCommitted);
            if(mTransition != 0)
            {
                printwriter.print(s);
                printwriter.print("mTransition=#");
                printwriter.print(Integer.toHexString(mTransition));
                printwriter.print(" mTransitionStyle=#");
                printwriter.println(Integer.toHexString(mTransitionStyle));
            }
            if(mEnterAnim != 0 || mExitAnim != 0)
            {
                printwriter.print(s);
                printwriter.print("mEnterAnim=#");
                printwriter.print(Integer.toHexString(mEnterAnim));
                printwriter.print(" mExitAnim=#");
                printwriter.println(Integer.toHexString(mExitAnim));
            }
            if(mPopEnterAnim != 0 || mPopExitAnim != 0)
            {
                printwriter.print(s);
                printwriter.print("mPopEnterAnim=#");
                printwriter.print(Integer.toHexString(mPopEnterAnim));
                printwriter.print(" mPopExitAnim=#");
                printwriter.println(Integer.toHexString(mPopExitAnim));
            }
            if(mBreadCrumbTitleRes != 0 || mBreadCrumbTitleText != null)
            {
                printwriter.print(s);
                printwriter.print("mBreadCrumbTitleRes=#");
                printwriter.print(Integer.toHexString(mBreadCrumbTitleRes));
                printwriter.print(" mBreadCrumbTitleText=");
                printwriter.println(mBreadCrumbTitleText);
            }
            if(mBreadCrumbShortTitleRes != 0 || mBreadCrumbShortTitleText != null)
            {
                printwriter.print(s);
                printwriter.print("mBreadCrumbShortTitleRes=#");
                printwriter.print(Integer.toHexString(mBreadCrumbShortTitleRes));
                printwriter.print(" mBreadCrumbShortTitleText=");
                printwriter.println(mBreadCrumbShortTitleText);
            }
        }
        if(mHead == null)
            break MISSING_BLOCK_LABEL_817;
        printwriter.print(s);
        printwriter.println("Operations:");
        s1 = (new StringBuilder()).append(s).append("    ").toString();
        op = mHead;
        i = 0;
_L13:
        if(op == null) goto _L2; else goto _L1
_L1:
        op.cmd;
        JVM INSTR tableswitch 0 7: default 424
    //                   0 699
    //                   1 707
    //                   2 715
    //                   3 723
    //                   4 731
    //                   5 739
    //                   6 747
    //                   7 755;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L11:
        break MISSING_BLOCK_LABEL_755;
_L3:
        String s2 = (new StringBuilder()).append("cmd=").append(op.cmd).toString();
_L12:
        printwriter.print(s);
        printwriter.print("  Op #");
        printwriter.print(i);
        printwriter.print(": ");
        printwriter.print(s2);
        printwriter.print(" ");
        printwriter.println(op.fragment);
        if(flag)
        {
            if(op.enterAnim != 0 || op.exitAnim != 0)
            {
                printwriter.print(s);
                printwriter.print("enterAnim=#");
                printwriter.print(Integer.toHexString(op.enterAnim));
                printwriter.print(" exitAnim=#");
                printwriter.println(Integer.toHexString(op.exitAnim));
            }
            if(op.popEnterAnim != 0 || op.popExitAnim != 0)
            {
                printwriter.print(s);
                printwriter.print("popEnterAnim=#");
                printwriter.print(Integer.toHexString(op.popEnterAnim));
                printwriter.print(" popExitAnim=#");
                printwriter.println(Integer.toHexString(op.popExitAnim));
            }
        }
        if(op.removed != null && op.removed.size() > 0)
        {
            int j = 0;
            while(j < op.removed.size()) 
            {
                printwriter.print(s1);
                if(op.removed.size() == 1)
                {
                    printwriter.print("Removed: ");
                } else
                {
                    if(j == 0)
                        printwriter.println("Removed:");
                    printwriter.print(s1);
                    printwriter.print("  #");
                    printwriter.print(j);
                    printwriter.print(": ");
                }
                printwriter.println(op.removed.get(j));
                j++;
            }
        }
        break MISSING_BLOCK_LABEL_804;
_L4:
        s2 = "NULL";
          goto _L12
_L5:
        s2 = "ADD";
          goto _L12
_L6:
        s2 = "REPLACE";
          goto _L12
_L7:
        s2 = "REMOVE";
          goto _L12
_L8:
        s2 = "HIDE";
          goto _L12
_L9:
        s2 = "SHOW";
          goto _L12
_L10:
        s2 = "DETACH";
          goto _L12
        s2 = "ATTACH";
          goto _L12
        op = op.next;
        i++;
          goto _L13
_L2:
    }

    public CharSequence getBreadCrumbShortTitle()
    {
        CharSequence charsequence;
        if(mBreadCrumbShortTitleRes != 0)
            charsequence = mManager.mActivity.getText(mBreadCrumbShortTitleRes);
        else
            charsequence = mBreadCrumbShortTitleText;
        return charsequence;
    }

    public int getBreadCrumbShortTitleRes()
    {
        return mBreadCrumbShortTitleRes;
    }

    public CharSequence getBreadCrumbTitle()
    {
        CharSequence charsequence;
        if(mBreadCrumbTitleRes != 0)
            charsequence = mManager.mActivity.getText(mBreadCrumbTitleRes);
        else
            charsequence = mBreadCrumbTitleText;
        return charsequence;
    }

    public int getBreadCrumbTitleRes()
    {
        return mBreadCrumbTitleRes;
    }

    public int getId()
    {
        return mIndex;
    }

    public String getName()
    {
        return mName;
    }

    public int getTransition()
    {
        return mTransition;
    }

    public int getTransitionStyle()
    {
        return mTransitionStyle;
    }

    public FragmentTransaction hide(Fragment fragment)
    {
        Op op = new Op();
        op.cmd = 4;
        op.fragment = fragment;
        addOp(op);
        return this;
    }

    public boolean isAddToBackStackAllowed()
    {
        return mAllowAddToBackStack;
    }

    public boolean isEmpty()
    {
        boolean flag;
        if(mNumOp == 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public TransitionState popFromBackStack(boolean flag, TransitionState transitionstate, SparseArray sparsearray, SparseArray sparsearray1)
    {
        int i;
        int j;
        Op op;
        int k;
        int l;
        if(FragmentManagerImpl.DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder()).append("popFromBackStack: ").append(this).toString());
            dump("  ", null, new PrintWriter(new LogWriter("FragmentManager")), null);
        }
        if(transitionstate == null)
        {
            if(sparsearray.size() != 0 || sparsearray1.size() != 0)
                transitionstate = beginTransition(sparsearray, sparsearray1, true);
        } else
        if(!flag)
        {
            ArrayList arraylist = mSharedElementTargetNames;
            ArrayList arraylist1 = mSharedElementSourceNames;
            setNameOverrides(transitionstate, arraylist, arraylist1);
        }
        bumpBackStackNesting(-1);
        if(transitionstate != null)
            i = 0;
        else
            i = mTransitionStyle;
        if(transitionstate != null)
            j = 0;
        else
            j = mTransition;
        op = mTail;
_L10:
        if(op == null)
            break MISSING_BLOCK_LABEL_569;
        if(transitionstate != null)
            k = 0;
        else
            k = op.popEnterAnim;
        if(transitionstate != null)
            l = 0;
        else
            l = op.popExitAnim;
        op.cmd;
        JVM INSTR tableswitch 1 7: default 176
    //                   1 273
    //                   2 313
    //                   3 410
    //                   4 437
    //                   5 470
    //                   6 503
    //                   7 536;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L8:
        break MISSING_BLOCK_LABEL_536;
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Unknown cmd: ").append(op.cmd).toString());
_L2:
        Fragment fragment7 = op.fragment;
        fragment7.mNextAnim = l;
        mManager.removeFragment(fragment7, FragmentManagerImpl.reverseTransit(j), i);
_L11:
        op = op.prev;
        if(true) goto _L10; else goto _L9
_L9:
        Fragment fragment5 = op.fragment;
        if(fragment5 != null)
        {
            fragment5.mNextAnim = l;
            mManager.removeFragment(fragment5, FragmentManagerImpl.reverseTransit(j), i);
        }
        if(op.removed != null)
        {
            int i1 = 0;
            while(i1 < op.removed.size()) 
            {
                Fragment fragment6 = (Fragment)op.removed.get(i1);
                fragment6.mNextAnim = k;
                mManager.addFragment(fragment6, false);
                i1++;
            }
        }
          goto _L11
_L4:
        Fragment fragment4 = op.fragment;
        fragment4.mNextAnim = k;
        mManager.addFragment(fragment4, false);
          goto _L11
_L5:
        Fragment fragment3 = op.fragment;
        fragment3.mNextAnim = k;
        mManager.showFragment(fragment3, FragmentManagerImpl.reverseTransit(j), i);
          goto _L11
_L6:
        Fragment fragment2 = op.fragment;
        fragment2.mNextAnim = l;
        mManager.hideFragment(fragment2, FragmentManagerImpl.reverseTransit(j), i);
          goto _L11
_L7:
        Fragment fragment1 = op.fragment;
        fragment1.mNextAnim = k;
        mManager.attachFragment(fragment1, FragmentManagerImpl.reverseTransit(j), i);
          goto _L11
        Fragment fragment = op.fragment;
        fragment.mNextAnim = k;
        mManager.detachFragment(fragment, FragmentManagerImpl.reverseTransit(j), i);
          goto _L11
        if(flag)
        {
            mManager.moveToState(mManager.mCurState, FragmentManagerImpl.reverseTransit(j), i, true);
            transitionstate = null;
        }
        if(mIndex >= 0)
        {
            mManager.freeBackStackIndex(mIndex);
            mIndex = -1;
        }
        return transitionstate;
    }

    public FragmentTransaction remove(Fragment fragment)
    {
        Op op = new Op();
        op.cmd = 3;
        op.fragment = fragment;
        addOp(op);
        return this;
    }

    public FragmentTransaction replace(int i, Fragment fragment)
    {
        return replace(i, fragment, null);
    }

    public FragmentTransaction replace(int i, Fragment fragment, String s)
    {
        if(i == 0)
        {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        } else
        {
            doAddOp(i, fragment, s, 2);
            return this;
        }
    }

    public void run()
    {
        int i;
        int j;
        Op op;
        int k;
        int l;
        if(FragmentManagerImpl.DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("Run: ").append(this).toString());
        if(mAddToBackStack && mIndex < 0)
            throw new IllegalStateException("addToBackStack() called after commit()");
        bumpBackStackNesting(1);
        TransitionState transitionstate = null;
        if(android.os.Build.VERSION.SDK_INT >= 21)
        {
            SparseArray sparsearray = new SparseArray();
            SparseArray sparsearray1 = new SparseArray();
            calculateFragments(sparsearray, sparsearray1);
            transitionstate = beginTransition(sparsearray, sparsearray1, false);
        }
        if(transitionstate != null)
            i = 0;
        else
            i = mTransitionStyle;
        if(transitionstate != null)
            j = 0;
        else
            j = mTransition;
        op = mHead;
_L10:
        if(op == null)
            break MISSING_BLOCK_LABEL_723;
        if(transitionstate != null)
            k = 0;
        else
            k = op.enterAnim;
        if(transitionstate != null)
            l = 0;
        else
            l = op.exitAnim;
        op.cmd;
        JVM INSTR tableswitch 1 7: default 188
    //                   1 258
    //                   2 292
    //                   3 573
    //                   4 603
    //                   5 633
    //                   6 663
    //                   7 693;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L8:
        break MISSING_BLOCK_LABEL_693;
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Unknown cmd: ").append(op.cmd).toString());
_L2:
        Fragment fragment7 = op.fragment;
        fragment7.mNextAnim = k;
        mManager.addFragment(fragment7, false);
_L11:
        op = op.next;
        if(true) goto _L10; else goto _L9
_L9:
        Fragment fragment5 = op.fragment;
        if(mManager.mAdded != null)
        {
            int i1 = 0;
            while(i1 < mManager.mAdded.size()) 
            {
                Fragment fragment6 = (Fragment)mManager.mAdded.get(i1);
                if(FragmentManagerImpl.DEBUG)
                    Log.v("FragmentManager", (new StringBuilder()).append("OP_REPLACE: adding=").append(fragment5).append(" old=").append(fragment6).toString());
                if(fragment5 == null || fragment6.mContainerId == fragment5.mContainerId)
                    if(fragment6 == fragment5)
                    {
                        fragment5 = null;
                        op.fragment = null;
                    } else
                    {
                        if(op.removed == null)
                            op.removed = new ArrayList();
                        op.removed.add(fragment6);
                        fragment6.mNextAnim = l;
                        if(mAddToBackStack)
                        {
                            fragment6.mBackStackNesting = 1 + fragment6.mBackStackNesting;
                            if(FragmentManagerImpl.DEBUG)
                                Log.v("FragmentManager", (new StringBuilder()).append("Bump nesting of ").append(fragment6).append(" to ").append(fragment6.mBackStackNesting).toString());
                        }
                        mManager.removeFragment(fragment6, j, i);
                    }
                i1++;
            }
        }
        if(fragment5 != null)
        {
            fragment5.mNextAnim = k;
            mManager.addFragment(fragment5, false);
        }
          goto _L11
_L4:
        Fragment fragment4 = op.fragment;
        fragment4.mNextAnim = l;
        mManager.removeFragment(fragment4, j, i);
          goto _L11
_L5:
        Fragment fragment3 = op.fragment;
        fragment3.mNextAnim = l;
        mManager.hideFragment(fragment3, j, i);
          goto _L11
_L6:
        Fragment fragment2 = op.fragment;
        fragment2.mNextAnim = k;
        mManager.showFragment(fragment2, j, i);
          goto _L11
_L7:
        Fragment fragment1 = op.fragment;
        fragment1.mNextAnim = l;
        mManager.detachFragment(fragment1, j, i);
          goto _L11
        Fragment fragment = op.fragment;
        fragment.mNextAnim = k;
        mManager.attachFragment(fragment, j, i);
          goto _L11
        mManager.moveToState(mManager.mCurState, j, i, true);
        if(mAddToBackStack)
            mManager.addBackStackState(this);
        return;
    }

    public FragmentTransaction setBreadCrumbShortTitle(int i)
    {
        mBreadCrumbShortTitleRes = i;
        mBreadCrumbShortTitleText = null;
        return this;
    }

    public FragmentTransaction setBreadCrumbShortTitle(CharSequence charsequence)
    {
        mBreadCrumbShortTitleRes = 0;
        mBreadCrumbShortTitleText = charsequence;
        return this;
    }

    public FragmentTransaction setBreadCrumbTitle(int i)
    {
        mBreadCrumbTitleRes = i;
        mBreadCrumbTitleText = null;
        return this;
    }

    public FragmentTransaction setBreadCrumbTitle(CharSequence charsequence)
    {
        mBreadCrumbTitleRes = 0;
        mBreadCrumbTitleText = charsequence;
        return this;
    }

    public FragmentTransaction setCustomAnimations(int i, int j)
    {
        return setCustomAnimations(i, j, 0, 0);
    }

    public FragmentTransaction setCustomAnimations(int i, int j, int k, int l)
    {
        mEnterAnim = i;
        mExitAnim = j;
        mPopEnterAnim = k;
        mPopExitAnim = l;
        return this;
    }

    public FragmentTransaction setTransition(int i)
    {
        mTransition = i;
        return this;
    }

    public FragmentTransaction setTransitionStyle(int i)
    {
        mTransitionStyle = i;
        return this;
    }

    public FragmentTransaction show(Fragment fragment)
    {
        Op op = new Op();
        op.cmd = 5;
        op.fragment = fragment;
        addOp(op);
        return this;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("BackStackEntry{");
        stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
        if(mIndex >= 0)
        {
            stringbuilder.append(" #");
            stringbuilder.append(mIndex);
        }
        if(mName != null)
        {
            stringbuilder.append(" ");
            stringbuilder.append(mName);
        }
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    static final int OP_ADD = 1;
    static final int OP_ATTACH = 7;
    static final int OP_DETACH = 6;
    static final int OP_HIDE = 4;
    static final int OP_NULL = 0;
    static final int OP_REMOVE = 3;
    static final int OP_REPLACE = 2;
    static final int OP_SHOW = 5;
    static final String TAG = "FragmentManager";
    boolean mAddToBackStack;
    boolean mAllowAddToBackStack;
    int mBreadCrumbShortTitleRes;
    CharSequence mBreadCrumbShortTitleText;
    int mBreadCrumbTitleRes;
    CharSequence mBreadCrumbTitleText;
    boolean mCommitted;
    int mEnterAnim;
    int mExitAnim;
    Op mHead;
    int mIndex;
    final FragmentManagerImpl mManager;
    String mName;
    int mNumOp;
    int mPopEnterAnim;
    int mPopExitAnim;
    ArrayList mSharedElementSourceNames;
    ArrayList mSharedElementTargetNames;
    Op mTail;
    int mTransition;
    int mTransitionStyle;




}
