// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.SLog;
import com.sonos.sclib.SCILibrary;
import java.util.HashSet;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr:
//            SonosActivity

public class SonosFragment extends Fragment
{
    public static interface FragmentStateListener
    {

        public abstract void onActiveChanged(SonosFragment sonosfragment, boolean flag);

        public abstract void onAnimationEnd(SonosFragment sonosfragment);

        public abstract void onAnimationStart(SonosFragment sonosfragment);
    }

    protected class FragmentAnimationListener
        implements android.view.animation.Animation.AnimationListener
    {

        public void onAnimationEnd(Animation animation)
        {
            SLog.i(LOG_TAG, (new StringBuilder()).append("onAnimationEnd called: ").append(getStateString()).toString());
            animationListener = null;
            isAnimating = false;
            if(isHidden() || isRemoving())
                setActive(false);
            for(Iterator iterator = listeners.iterator(); iterator.hasNext(); ((FragmentStateListener)iterator.next()).onAnimationEnd(SonosFragment.this));
            SonosFragment.this.onAnimationEnd(animation);
        }

        public void onAnimationRepeat(Animation animation)
        {
        }

        public void onAnimationStart(Animation animation)
        {
            SLog.i(LOG_TAG, "onAnimationStart called");
            isAnimating = true;
            for(Iterator iterator = listeners.iterator(); iterator.hasNext(); ((FragmentStateListener)iterator.next()).onAnimationStart(SonosFragment.this));
            SonosFragment.this.onAnimationStart(animation);
        }

        private boolean isAnimating;
        final SonosFragment this$0;


        public FragmentAnimationListener()
        {
            this$0 = SonosFragment.this;
            super();
            isAnimating = false;
        }
    }


    public SonosFragment()
    {
        LOG_TAG = (new StringBuilder()).append(com/sonos/acr/SonosFragment.getSimpleName()).append(":").append(getClass().getSimpleName()).append(":").append(hashCode()).toString();
        fragmentActive = false;
        listeners = new HashSet();
        themedAttributeId = 0;
    }

    public SonosFragment(int i)
    {
        LOG_TAG = (new StringBuilder()).append(com/sonos/acr/SonosFragment.getSimpleName()).append(":").append(getClass().getSimpleName()).append(":").append(hashCode()).toString();
        fragmentActive = false;
        listeners = new HashSet();
        themedAttributeId = i;
    }

    private String getStateString()
    {
        return (new StringBuilder()).append("isAdded: ").append(isAdded()).append(" ").append("isInLayout: ").append(isInLayout()).append(" ").append("isVisible: ").append(isVisible()).append(" ").append("isResumed: ").append(isResumed()).append(" ").append("isHidden: ").append(isHidden()).append(" ").append("isRemoving: ").append(isRemoving()).append(" ").append("isDetached: ").append(isDetached()).append(" ").append("isActive: ").append(isActive()).append(" ").append("isTransitioining: ").append(isTransitioning()).append(" ").append("listeners: ").append(listeners.size()).toString();
    }

    private void setActive(boolean flag)
    {
        if(fragmentActive != flag)
        {
            fragmentActive = flag;
            onActiveChanged(fragmentActive);
            Iterator iterator = listeners.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                FragmentStateListener fragmentstatelistener = (FragmentStateListener)iterator.next();
                if(fragmentstatelistener != null)
                    fragmentstatelistener.onActiveChanged(this, flag);
            } while(true);
        }
    }

    public void addListener(FragmentStateListener fragmentstatelistener)
    {
        SLog.e(LOG_TAG, "adding Fragment stateListener! ");
        listeners.add(fragmentstatelistener);
    }

    public Household getHousehold()
    {
        return LibraryUtils.getHousehold();
    }

    public SCILibrary getLibrary()
    {
        return getSonosActivity().getLibrary();
    }

    public SonosActivity getSonosActivity()
    {
        return (SonosActivity)getActivity();
    }

    public Context getThemedContext()
    {
        return themedContext;
    }

    public CharSequence getTitle()
    {
        return "Unknown";
    }

    public boolean isActive()
    {
        return fragmentActive;
    }

    public boolean isTransitioning()
    {
        boolean flag;
        if(animationListener != null && animationListener.isAnimating)
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected void onActiveChanged(boolean flag)
    {
        Log.i(LOG_TAG, (new StringBuilder()).append("onActiveStateChanged: ").append(flag).toString());
        if(flag)
            onSubscribeEventSinks();
        else
            onUnsubscribeEventSinks();
    }

    protected void onAnimationEnd(Animation animation)
    {
    }

    protected void onAnimationStart(Animation animation)
    {
    }

    public boolean onBackPressed()
    {
        return false;
    }

    public Animation onCreateAnimation(int i, boolean flag, int j)
    {
        SLog.i(LOG_TAG, (new StringBuilder()).append("").append(getTitle()).append(" onCreateAnimation called: ").append(i).append(" enter: ").append(flag).append(" nextAnim: ").append(j).toString());
        Object obj;
        if(disableAnimations)
        {
            obj = new Animation() {

                final SonosFragment this$0;

            
            {
                this$0 = SonosFragment.this;
                super();
            }
            }
;
            ((Animation) (obj)).setDuration(0L);
        } else
        if(j != 0)
        {
            animationListener = new FragmentAnimationListener();
            Animation animation = AnimationUtils.loadAnimation(getActivity(), j);
            animation.setAnimationListener(animationListener);
            obj = animation;
        } else
        {
            obj = super.onCreateAnimation(i, flag, j);
        }
        return ((Animation) (obj));
    }

    public View onCreateThemedView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return null;
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        Context context = layoutinflater.getContext();
        Fragment fragment = getParentFragment();
        if(themedAttributeId == 0)
            if(fragment instanceof SonosFragment)
                themedAttributeId = ((SonosFragment)fragment).themedAttributeId;
            else
                try
                {
                    FragmentActivity fragmentactivity = getActivity();
                    if(fragmentactivity != null)
                        themedAttributeId = fragmentactivity.getPackageManager().getActivityInfo(fragmentactivity.getComponentName(), 0).theme;
                }
                catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
                {
                    namenotfoundexception.printStackTrace();
                }
        if(themedAttributeId != 0)
        {
            themedContext = new ContextThemeWrapper(context, resolveThemeStyle(context, themedAttributeId));
            layoutinflater = LayoutInflater.from(themedContext);
        } else
        {
            themedContext = context;
        }
        return onCreateThemedView(layoutinflater, viewgroup, bundle);
    }

    public void onDestroy()
    {
        super.onDestroy();
        SLog.i(LOG_TAG, "onDestroy called");
        setActive(false);
    }

    public void onDestroyView()
    {
        super.onDestroyView();
        SLog.i(LOG_TAG, "onDestroyView called");
    }

    public void onHiddenChanged(boolean flag)
    {
        super.onHiddenChanged(flag);
        SLog.i(LOG_TAG, (new StringBuilder()).append("onHiddenChanged: ").append(flag).toString());
        if(isResumed())
            if(flag)
            {
                if(animationListener == null)
                    setActive(false);
            } else
            {
                setActive(true);
            }
    }

    public void onPause()
    {
        super.onPause();
        SLog.i(LOG_TAG, "onPause called");
    }

    public void onResume()
    {
        super.onResume();
        SLog.i(LOG_TAG, "OnResume called");
        if(!isHidden())
            setActive(true);
    }

    public void onStart()
    {
        super.onStart();
        SLog.i(LOG_TAG, "onStart called");
        if(!isHidden())
            setActive(true);
    }

    public void onStop()
    {
        super.onStop();
        if(!isRemoving())
            setActive(false);
        SLog.i(LOG_TAG, "onStop called");
    }

    public void onSubscribeEventSinks()
    {
    }

    public void onUnsubscribeEventSinks()
    {
    }

    public void removeListener(FragmentStateListener fragmentstatelistener)
    {
        listeners.remove(fragmentstatelistener);
    }

    public int resolveThemeStyle(Context context, int i)
    {
        TypedValue typedvalue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedvalue, true);
        return typedvalue.resourceId;
    }

    public void setThemedAttributeId(int i)
    {
        themedAttributeId = i;
    }

    public static boolean disableAnimations = false;
    protected final String LOG_TAG;
    private FragmentAnimationListener animationListener;
    boolean fragmentActive;
    protected HashSet listeners;
    private int themedAttributeId;
    protected Context themedContext;




/*
    static FragmentAnimationListener access$202(SonosFragment sonosfragment, FragmentAnimationListener fragmentanimationlistener)
    {
        sonosfragment.animationListener = fragmentanimationlistener;
        return fragmentanimationlistener;
    }

*/

}
