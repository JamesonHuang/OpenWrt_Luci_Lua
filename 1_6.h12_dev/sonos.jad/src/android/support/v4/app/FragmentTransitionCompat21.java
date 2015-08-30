// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.graphics.Rect;
import android.transition.*;
import android.view.*;
import java.util.*;

class FragmentTransitionCompat21
{
    public static class EpicenterView
    {

        public View epicenter;

        public EpicenterView()
        {
        }
    }

    public static interface ViewRetriever
    {

        public abstract View getView();
    }


    FragmentTransitionCompat21()
    {
    }

    public static void addTargets(Object obj, ArrayList arraylist)
    {
        Transition transition = (Transition)obj;
        if(transition instanceof TransitionSet)
        {
            TransitionSet transitionset = (TransitionSet)transition;
            int k = transitionset.getTransitionCount();
            for(int l = 0; l < k; l++)
                addTargets(transitionset.getTransitionAt(l), arraylist);

        } else
        if(!hasSimpleTarget(transition) && isNullOrEmpty(transition.getTargets()))
        {
            int i = arraylist.size();
            for(int j = 0; j < i; j++)
                transition.addTarget((View)arraylist.get(j));

        }
    }

    public static void addTransitionTargets(Object obj, Object obj1, final View container, final ViewRetriever inFragment, final View nonExistentView, EpicenterView epicenterview, final Map nameOverrides, final ArrayList enteringViews, 
            final Map renamedViews, ArrayList arraylist)
    {
        if(obj != null || obj1 != null)
        {
            final Transition enterTransition = (Transition)obj;
            if(enterTransition != null)
                enterTransition.addTarget(nonExistentView);
            if(obj1 != null)
                addTargets((Transition)obj1, arraylist);
            if(inFragment != null)
                container.getViewTreeObserver().addOnPreDrawListener(new android.view.ViewTreeObserver.OnPreDrawListener() {

                    public boolean onPreDraw()
                    {
                        container.getViewTreeObserver().removeOnPreDrawListener(this);
                        View view = inFragment.getView();
                        if(view != null)
                        {
                            if(!nameOverrides.isEmpty())
                            {
                                FragmentTransitionCompat21.findNamedViews(renamedViews, view);
                                renamedViews.keySet().retainAll(nameOverrides.values());
                                Iterator iterator = nameOverrides.entrySet().iterator();
                                do
                                {
                                    if(!iterator.hasNext())
                                        break;
                                    java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                                    String s = (String)entry.getValue();
                                    View view1 = (View)renamedViews.get(s);
                                    if(view1 != null)
                                        view1.setTransitionName((String)entry.getKey());
                                } while(true);
                            }
                            if(enterTransition != null)
                            {
                                FragmentTransitionCompat21.captureTransitioningViews(enteringViews, view);
                                enteringViews.removeAll(renamedViews.values());
                                enteringViews.add(nonExistentView);
                                enterTransition.removeTarget(nonExistentView);
                                FragmentTransitionCompat21.addTargets(enterTransition, enteringViews);
                            }
                        }
                        return true;
                    }

                    final View val$container;
                    final Transition val$enterTransition;
                    final ArrayList val$enteringViews;
                    final ViewRetriever val$inFragment;
                    final Map val$nameOverrides;
                    final View val$nonExistentView;
                    final Map val$renamedViews;

            
            {
                container = view;
                inFragment = viewretriever;
                nameOverrides = map;
                renamedViews = map1;
                enterTransition = transition;
                enteringViews = arraylist;
                nonExistentView = view1;
                super();
            }
                }
);
            setSharedElementEpicenter(enterTransition, epicenterview);
        }
    }

    public static void beginDelayedTransition(ViewGroup viewgroup, Object obj)
    {
        TransitionManager.beginDelayedTransition(viewgroup, (Transition)obj);
    }

    public static Object captureExitingViews(Object obj, View view, ArrayList arraylist, Map map, View view1)
    {
        if(obj != null)
        {
            captureTransitioningViews(arraylist, view);
            if(map != null)
                arraylist.removeAll(map.values());
            if(arraylist.isEmpty())
            {
                obj = null;
            } else
            {
                arraylist.add(view1);
                addTargets((Transition)obj, arraylist);
            }
        }
        return obj;
    }

    private static void captureTransitioningViews(ArrayList arraylist, View view)
    {
        if(view.getVisibility() == 0)
            if(view instanceof ViewGroup)
            {
                ViewGroup viewgroup = (ViewGroup)view;
                if(viewgroup.isTransitionGroup())
                {
                    arraylist.add(viewgroup);
                } else
                {
                    int i = viewgroup.getChildCount();
                    int j = 0;
                    while(j < i) 
                    {
                        captureTransitioningViews(arraylist, viewgroup.getChildAt(j));
                        j++;
                    }
                }
            } else
            {
                arraylist.add(view);
            }
    }

    public static void cleanupTransitions(final View sceneRoot, final View nonExistentView, Object obj, final ArrayList enteringViews, Object obj1, final ArrayList exitingViews, Object obj2, final ArrayList sharedElementTargets, 
            Object obj3, final ArrayList hiddenViews, final Map renamedViews)
    {
        final Transition enterTransition = (Transition)obj;
        final Transition exitTransition = (Transition)obj1;
        final Transition sharedElementTransition = (Transition)obj2;
        final Transition overallTransition = (Transition)obj3;
        if(overallTransition != null)
            sceneRoot.getViewTreeObserver().addOnPreDrawListener(new android.view.ViewTreeObserver.OnPreDrawListener() {

                public boolean onPreDraw()
                {
                    sceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
                    if(enterTransition != null)
                    {
                        enterTransition.removeTarget(nonExistentView);
                        FragmentTransitionCompat21.removeTargets(enterTransition, enteringViews);
                    }
                    if(exitTransition != null)
                        FragmentTransitionCompat21.removeTargets(exitTransition, exitingViews);
                    if(sharedElementTransition != null)
                        FragmentTransitionCompat21.removeTargets(sharedElementTransition, sharedElementTargets);
                    java.util.Map.Entry entry;
                    for(Iterator iterator = renamedViews.entrySet().iterator(); iterator.hasNext(); ((View)entry.getValue()).setTransitionName((String)entry.getKey()))
                        entry = (java.util.Map.Entry)iterator.next();

                    int i = hiddenViews.size();
                    for(int j = 0; j < i; j++)
                        overallTransition.excludeTarget((View)hiddenViews.get(j), false);

                    overallTransition.excludeTarget(nonExistentView, false);
                    return true;
                }

                final Transition val$enterTransition;
                final ArrayList val$enteringViews;
                final Transition val$exitTransition;
                final ArrayList val$exitingViews;
                final ArrayList val$hiddenViews;
                final View val$nonExistentView;
                final Transition val$overallTransition;
                final Map val$renamedViews;
                final View val$sceneRoot;
                final ArrayList val$sharedElementTargets;
                final Transition val$sharedElementTransition;

            
            {
                sceneRoot = view;
                enterTransition = transition;
                nonExistentView = view1;
                enteringViews = arraylist;
                exitTransition = transition1;
                exitingViews = arraylist1;
                sharedElementTransition = transition2;
                sharedElementTargets = arraylist2;
                renamedViews = map;
                hiddenViews = arraylist3;
                overallTransition = transition3;
                super();
            }
            }
);
    }

    public static Object cloneTransition(Object obj)
    {
        if(obj != null)
            obj = ((Transition)obj).clone();
        return obj;
    }

    public static void excludeTarget(Object obj, View view, boolean flag)
    {
        ((Transition)obj).excludeTarget(view, flag);
    }

    public static void findNamedViews(Map map, View view)
    {
        if(view.getVisibility() == 0)
        {
            String s = view.getTransitionName();
            if(s != null)
                map.put(s, view);
            if(view instanceof ViewGroup)
            {
                ViewGroup viewgroup = (ViewGroup)view;
                int i = viewgroup.getChildCount();
                for(int j = 0; j < i; j++)
                    findNamedViews(map, viewgroup.getChildAt(j));

            }
        }
    }

    private static Rect getBoundsOnScreen(View view)
    {
        Rect rect = new Rect();
        int ai[] = new int[2];
        view.getLocationOnScreen(ai);
        rect.set(ai[0], ai[1], ai[0] + view.getWidth(), ai[1] + view.getHeight());
        return rect;
    }

    public static String getTransitionName(View view)
    {
        return view.getTransitionName();
    }

    private static boolean hasSimpleTarget(Transition transition)
    {
        boolean flag;
        if(!isNullOrEmpty(transition.getTargetIds()) || !isNullOrEmpty(transition.getTargetNames()) || !isNullOrEmpty(transition.getTargetTypes()))
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean isNullOrEmpty(List list)
    {
        boolean flag;
        if(list == null || list.isEmpty())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static Object mergeTransitions(Object obj, Object obj1, Object obj2, boolean flag)
    {
        boolean flag1;
        Transition transition;
        Transition transition1;
        Transition transition2;
        flag1 = true;
        transition = (Transition)obj;
        transition1 = (Transition)obj1;
        transition2 = (Transition)obj2;
        if(transition != null && transition1 != null)
            flag1 = flag;
        if(!flag1) goto _L2; else goto _L1
_L1:
        Object obj3;
        TransitionSet transitionset = new TransitionSet();
        if(transition != null)
            transitionset.addTransition(transition);
        if(transition1 != null)
            transitionset.addTransition(transition1);
        if(transition2 != null)
            transitionset.addTransition(transition2);
        obj3 = transitionset;
_L5:
        return obj3;
_L2:
        Object obj4 = null;
        if(transition1 == null || transition == null) goto _L4; else goto _L3
_L3:
        obj4 = (new TransitionSet()).addTransition(transition1).addTransition(transition).setOrdering(1);
_L6:
        if(transition2 != null)
        {
            TransitionSet transitionset1 = new TransitionSet();
            if(obj4 != null)
                transitionset1.addTransition(((Transition) (obj4)));
            transitionset1.addTransition(transition2);
            obj3 = transitionset1;
        } else
        {
            obj3 = obj4;
        }
        if(true) goto _L5; else goto _L4
_L4:
        if(transition1 != null)
            obj4 = transition1;
        else
        if(transition != null)
            obj4 = transition;
          goto _L6
    }

    public static void removeTargets(Object obj, ArrayList arraylist)
    {
        Transition transition = (Transition)obj;
        if(transition instanceof TransitionSet)
        {
            TransitionSet transitionset = (TransitionSet)transition;
            int j = transitionset.getTransitionCount();
            for(int k = 0; k < j; k++)
                removeTargets(transitionset.getTransitionAt(k), arraylist);

        } else
        if(!hasSimpleTarget(transition))
        {
            List list = transition.getTargets();
            if(list != null && list.size() == arraylist.size() && list.containsAll(arraylist))
            {
                for(int i = -1 + arraylist.size(); i >= 0; i--)
                    transition.removeTarget((View)arraylist.get(i));

            }
        }
    }

    public static void setEpicenter(Object obj, View view)
    {
        ((Transition)obj).setEpicenterCallback(new android.transition.Transition.EpicenterCallback() {

            public Rect onGetEpicenter(Transition transition)
            {
                return epicenter;
            }

            final Rect val$epicenter;

            
            {
                epicenter = rect;
                super();
            }
        }
);
    }

    private static void setSharedElementEpicenter(Transition transition, final EpicenterView epicenterView)
    {
        if(transition != null)
            transition.setEpicenterCallback(new android.transition.Transition.EpicenterCallback() {

                public Rect onGetEpicenter(Transition transition1)
                {
                    if(mEpicenter == null && epicenterView.epicenter != null)
                        mEpicenter = FragmentTransitionCompat21.getBoundsOnScreen(epicenterView.epicenter);
                    return mEpicenter;
                }

                private Rect mEpicenter;
                final EpicenterView val$epicenterView;

            
            {
                epicenterView = epicenterview;
                super();
            }
            }
);
    }


}
