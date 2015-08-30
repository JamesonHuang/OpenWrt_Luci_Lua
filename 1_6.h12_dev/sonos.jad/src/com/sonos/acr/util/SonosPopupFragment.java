// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.*;
import android.view.*;
import android.view.animation.*;
import android.widget.FrameLayout;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.view.SonosPopupWindow;
import java.io.FileDescriptor;
import java.io.PrintWriter;

// Referenced classes of package com.sonos.acr.util:
//            ViewUtils

public class SonosPopupFragment extends Fragment
    implements com.sonos.acr.view.SonosPopupWindow.OnDismissListener
{
    private class SonosPopupFrame extends FrameLayout
    {

        public boolean dispatchKeyEvent(KeyEvent keyevent)
        {
            if(keyevent.getKeyCode() != 4) goto _L2; else goto _L1
_L1:
            if(getKeyDispatcherState() != null) goto _L4; else goto _L3
_L3:
            boolean flag = super.dispatchKeyEvent(keyevent);
_L6:
            return flag;
_L4:
            if(keyevent.getAction() == 0 && keyevent.getRepeatCount() == 0)
            {
                android.view.KeyEvent.DispatcherState dispatcherstate1 = getKeyDispatcherState();
                if(dispatcherstate1 != null)
                    dispatcherstate1.startTracking(keyevent, this);
                flag = true;
                continue; /* Loop/switch isn't completed */
            }
            if(keyevent.getAction() == 1)
            {
                android.view.KeyEvent.DispatcherState dispatcherstate = getKeyDispatcherState();
                if(dispatcherstate != null && dispatcherstate.isTracking(keyevent) && !keyevent.isCanceled())
                {
                    if(!onBackPressed())
                        dismiss();
                    flag = true;
                    continue; /* Loop/switch isn't completed */
                }
            }
            flag = super.dispatchKeyEvent(keyevent);
            continue; /* Loop/switch isn't completed */
_L2:
            if((getActivity() instanceof SonosActivity) && keyevent.getKeyCode() == 25 || keyevent.getKeyCode() == 24)
            {
                if(keyevent.getAction() == 1 && ((SonosActivity)getActivity()).handleKeyUp(keyevent.getKeyCode(), keyevent))
                {
                    flag = true;
                    continue; /* Loop/switch isn't completed */
                }
                if(keyevent.getAction() == 0 && ((SonosActivity)getActivity()).handleKeyDown(keyevent.getKeyCode(), keyevent))
                {
                    flag = true;
                    continue; /* Loop/switch isn't completed */
                }
            }
            flag = super.dispatchKeyEvent(keyevent);
            if(true) goto _L6; else goto _L5
_L5:
        }

        public boolean onKeyDown(int i, KeyEvent keyevent)
        {
            boolean flag;
            if((getActivity() instanceof SonosActivity) && ((SonosActivity)getActivity()).handleKeyDown(i, keyevent) || super.onKeyDown(i, keyevent))
                flag = true;
            else
                flag = false;
            return flag;
        }

        protected void onMeasure(int i, int j)
        {
            int k = maxWidth;
            if(k < 0)
                k = ViewUtils.getDisplaySize(getActivity()).x;
            super.onMeasure(android.view.View.MeasureSpec.makeMeasureSpec(k, 0x80000000), j);
        }

        protected void onSizeChanged(int i, int j, int k, int l)
        {
            super.onSizeChanged(i, j, k, l);
            update(i, j);
        }

        public boolean onTouchEvent(MotionEvent motionevent)
        {
            boolean flag = true;
            int i = (int)motionevent.getX();
            int j = (int)motionevent.getY();
            if(motionevent.getAction() == 0 && (i < 0 || i >= getWidth() || j < 0 || j >= getHeight()))
                dismiss();
            else
            if(motionevent.getAction() == 4)
                dismiss();
            else
                flag = super.onTouchEvent(motionevent);
            return flag;
        }

        private int maxWidth;
        final SonosPopupFragment this$0;

        public SonosPopupFrame(Context context)
        {
            this$0 = SonosPopupFragment.this;
            super(context);
            maxWidth = context.getResources().getDimensionPixelSize(0x7f090051);
        }
    }

    private class PopupResizeAnimation extends Animation
    {

        protected void applyTransformation(float f, Transformation transformation)
        {
            if(mPopupWindow != null && mPopupWindow.isShowing())
            {
                int i = startWidth + (int)(f * (float)(endWidth - startWidth));
                int j = startHeight + (int)(f * (float)(endHeight - startHeight));
                Point point = getOffsetRelativeToAnchor(i, j);
                mPopupWindow.update(anchor, point.x, point.y, i, j);
            }
        }

        private int endHeight;
        private int endWidth;
        private int startHeight;
        private int startWidth;
        final SonosPopupFragment this$0;

        public PopupResizeAnimation(int i, int j, int k, int l)
        {
            this$0 = SonosPopupFragment.this;
            super();
            startWidth = i;
            startHeight = j;
            endWidth = k;
            endHeight = l;
        }
    }


    public SonosPopupFragment()
    {
        mTheme = 0;
        mShowsPopup = true;
        mBackStackId = -1;
        isFirstUpdate = true;
        centerInsideAnchor = false;
        anchorCenterX = 0;
        anchorCenterY = 0;
        isVisible = false;
        animateResize = false;
    }

    private Point getOffsetRelativeToAnchor(int i, int j)
    {
        int ai[] = new int[2];
        anchor.getLocationInWindow(ai);
        Rect rect = new Rect();
        anchor.getWindowVisibleDisplayFrame(rect);
        int k = rect.right - rect.left;
        int l = rect.bottom - rect.top;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        if(centerInsideAnchor)
        {
            if(anchorCenterX == 0 && anchorCenterY == 0 && i != 0 && j != 0)
            {
                anchorCenterX = anchor.getWidth() / 2;
                anchorCenterY = -anchor.getHeight() / 2;
                if(i + (ai[0] + anchorCenterX) > k)
                    anchorCenterX = anchorCenterX - i;
                if(j + (ai[1] + anchorCenterY) > l)
                    anchorCenterY = anchorCenterY - j;
            }
            i1 = anchorCenterX;
            j1 = anchorCenterY;
        } else
        {
            Point point = resolveGravity(i, j);
            i1 = point.x;
            j1 = point.y;
        }
        k1 = i1 + (i + ai[0]);
        if(k1 > k)
            i1 -= k1 - k;
        l1 = i1 + ai[0];
        if(l1 < 0)
            i1 -= l1;
        i2 = j1 + ai[1];
        if(i2 < 0)
            j1 -= i2;
        j2 = j + (j1 + (ai[1] + anchor.getHeight()));
        if(j2 > l)
            j1 -= j2 - l;
        return new Point(i1, j1);
    }

    private Point resolveGravity(int i, int j)
    {
        int k;
        int l;
        k = anchor.getWidth();
        l = anchor.getHeight();
        7 & gravity;
        JVM INSTR tableswitch 1 5: default 60
    //                   1 125
    //                   2 60
    //                   3 119
    //                   4 60
    //                   5 135;
           goto _L1 _L2 _L1 _L3 _L1 _L4
_L1:
        int i1 = 0;
_L9:
        0x70 & gravity;
        JVM INSTR lookupswitch 3: default 104
    //                   16: 149
    //                   48: 141
    //                   80: 164;
           goto _L5 _L6 _L7 _L8
_L5:
        int j1 = 0;
_L10:
        return new Point(i1, j1);
_L3:
        i1 = 0;
          goto _L9
_L2:
        i1 = (k - i) / 2;
          goto _L9
_L4:
        i1 = k;
          goto _L9
_L7:
        j1 = -l;
          goto _L10
_L6:
        j1 = -l - (j - l) / 2;
          goto _L10
_L8:
        j1 = 0;
          goto _L10
    }

    public void dismiss()
    {
        dismissInternal(false);
    }

    public void dismissAllowingStateLoss()
    {
        dismissInternal(true);
    }

    void dismissInternal(boolean flag)
    {
        if(!mDismissed)
        {
            mDismissed = true;
            mShownByMe = false;
            if(mPopupWindow != null)
            {
                mPopupWindow.dismiss();
                mPopupWindow = null;
            }
            mViewDestroyed = true;
            if(mBackStackId >= 0)
            {
                getFragmentManager().popBackStack(mBackStackId, 1);
                mBackStackId = -1;
            } else
            {
                FragmentTransaction fragmenttransaction = getFragmentManager().beginTransaction();
                fragmenttransaction.remove(this);
                if(flag)
                    fragmenttransaction.commitAllowingStateLoss();
                else
                    fragmenttransaction.commit();
            }
        }
    }

    public void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        super.dump(s, filedescriptor, printwriter, as);
        printwriter.print(s);
        printwriter.println("PopupFragment:");
        printwriter.print(" mTheme=0x");
        printwriter.println(Integer.toHexString(mTheme));
        printwriter.print(" mShowsPopup=");
        printwriter.print(mShowsPopup);
        printwriter.print(" mBackStackId=");
        printwriter.println(mBackStackId);
        printwriter.print(s);
        printwriter.print("  mPopupWindow=");
        printwriter.println(mPopupWindow);
        printwriter.print(s);
        printwriter.print("  mViewDestroyed=");
        printwriter.print(mViewDestroyed);
        printwriter.print(" mDismissed=");
        printwriter.print(mDismissed);
        printwriter.print(" mShownByMe=");
        printwriter.println(mShownByMe);
    }

    public LayoutInflater getLayoutInflater(Bundle bundle)
    {
        LayoutInflater layoutinflater;
        if(!mShowsPopup)
        {
            layoutinflater = super.getLayoutInflater(bundle);
        } else
        {
            mPopupWindow = onCreatePopupWindow();
            layoutinflater = (LayoutInflater)getActivity().getSystemService("layout_inflater");
        }
        return layoutinflater;
    }

    public SonosPopupWindow getPopupWindow()
    {
        return mPopupWindow;
    }

    public boolean getShowsDialog()
    {
        return mShowsPopup;
    }

    public int getTheme()
    {
        return mTheme;
    }

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        if(mShowsPopup) goto _L2; else goto _L1
_L1:
        return;
_L2:
        View view = getView();
        if(view != null)
        {
            if(view.getParent() != null)
                throw new IllegalStateException("PopupWindow can not be attached to a container view");
            android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
            byte byte0 = -1;
            if(layoutparams != null && layoutparams.height == -2)
                byte0 = -2;
            SonosPopupFrame sonospopupframe = new SonosPopupFrame(getActivity());
            sonospopupframe.addView(view, new android.widget.FrameLayout.LayoutParams(-1, byte0));
            mPopupWindow.setWidth(-2);
            mPopupWindow.setHeight(-2);
            mPopupWindow.setWindowLayoutMode(-2, -2);
            mPopupWindow.setOnDismissListener(this);
            mPopupWindow.setAnimationStyle(0x7f0d00a7);
            mPopupWindow.setContentView(sonospopupframe);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        if(!mShownByMe)
            mDismissed = false;
    }

    public boolean onBackPressed()
    {
        return false;
    }

    public void onCancel(DialogInterface dialoginterface)
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        mShowsPopup = true;
        if(bundle != null)
        {
            mTheme = bundle.getInt("android:theme", 0);
            mShowsPopup = bundle.getBoolean("android:showsDialog", mShowsPopup);
            mBackStackId = bundle.getInt("android:backStackId", -1);
        }
    }

    public SonosPopupWindow onCreatePopupWindow()
    {
        SonosPopupWindow sonospopupwindow = new SonosPopupWindow(getActivity());
        sonospopupwindow.setFocusable(true);
        sonospopupwindow.setOutsideTouchable(true);
        sonospopupwindow.setBackgroundDrawable(null);
        sonospopupwindow.setClippingEnabled(false);
        return sonospopupwindow;
    }

    public void onDestroyView()
    {
        super.onDestroyView();
        if(mPopupWindow != null)
        {
            mViewDestroyed = true;
            mPopupWindow.dismiss();
            mPopupWindow = null;
        }
    }

    public void onDetach()
    {
        super.onDetach();
        if(!mShownByMe && !mDismissed)
            mDismissed = true;
    }

    public void onDismiss()
    {
        onPopupDismissed();
    }

    public void onDismiss(DialogInterface dialoginterface)
    {
        if(!mViewDestroyed)
            dismissInternal(true);
    }

    public void onPopupDismissed()
    {
        isVisible = false;
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        if(mTheme != 0)
            bundle.putInt("android:theme", mTheme);
        if(!mShowsPopup)
            bundle.putBoolean("android:showsDialog", mShowsPopup);
        if(mBackStackId != -1)
            bundle.putInt("android:backStackId", mBackStackId);
    }

    public void onStart()
    {
        super.onStart();
        if(mPopupWindow != null && isVisible)
        {
            mViewDestroyed = false;
            Point point = getOffsetRelativeToAnchor(0, 0);
            mPopupWindow.showAsDropDown(anchor, point.x, point.y);
        }
    }

    public void setAnimateResize(boolean flag)
    {
        animateResize = flag;
    }

    public void setCenterInsideAnchor(boolean flag)
    {
        centerInsideAnchor = flag;
    }

    public void setGravity(int i)
    {
        gravity = i;
    }

    public void setShowsDialog(boolean flag)
    {
        mShowsPopup = flag;
    }

    public int show(FragmentTransaction fragmenttransaction, String s, View view)
    {
        mDismissed = false;
        mShownByMe = true;
        anchor = view;
        fragmenttransaction.add(this, s);
        mViewDestroyed = false;
        mBackStackId = fragmenttransaction.commit();
        isVisible = true;
        return mBackStackId;
    }

    public void show(FragmentManager fragmentmanager, String s, View view)
    {
        mDismissed = false;
        mShownByMe = true;
        anchor = view;
        FragmentTransaction fragmenttransaction = fragmentmanager.beginTransaction();
        fragmenttransaction.add(this, s);
        fragmenttransaction.commit();
        isVisible = true;
    }

    public void update(int i, int j)
    {
        if(mPopupWindow != null && i != 0 && j != 0)
            if(animateResize && mPopupWindow.getWidth() > 0 && mPopupWindow.getHeight() > 0)
            {
                final PopupResizeAnimation animation = new PopupResizeAnimation(mPopupWindow.getWidth(), mPopupWindow.getHeight(), i, j);
                animation.setInterpolator(new AccelerateDecelerateInterpolator());
                animation.setDuration(200L);
                SonosApplication.getInstance().getHandler().post(new Runnable() {

                    public void run()
                    {
                        if(getView() != null)
                            getView().startAnimation(animation);
                    }

                    final SonosPopupFragment this$0;
                    final PopupResizeAnimation val$animation;

            
            {
                this$0 = SonosPopupFragment.this;
                animation = popupresizeanimation;
                super();
            }
                }
);
            } else
            {
                Point point = getOffsetRelativeToAnchor(i, j);
                mPopupWindow.update(anchor, point.x, point.y, i, j);
            }
    }

    private static final String SAVED_BACK_STACK_ID = "android:backStackId";
    private static final String SAVED_CANCELABLE = "android:cancelable";
    private static final String SAVED_DIALOG_STATE_TAG = "android:savedDialogState";
    private static final String SAVED_SHOWS_DIALOG = "android:showsDialog";
    private static final String SAVED_STYLE = "android:style";
    private static final String SAVED_THEME = "android:theme";
    protected View anchor;
    protected int anchorCenterX;
    protected int anchorCenterY;
    protected boolean animateResize;
    protected boolean centerInsideAnchor;
    protected int gravity;
    boolean isFirstUpdate;
    protected boolean isVisible;
    int mBackStackId;
    protected boolean mDismissed;
    SonosPopupWindow mPopupWindow;
    boolean mShownByMe;
    boolean mShowsPopup;
    int mTheme;
    boolean mViewDestroyed;

}
