// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.graphics.Rect;
import android.text.*;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.*;

// Referenced classes of package com.sonos.acr.util:
//            TextViewLogger, DebugBackground, DebugColorDrawable, DebugBackgroundDrawable

class I18nTextViewWrapper
    implements TextWatcher, android.view.ViewTreeObserver.OnPreDrawListener
{

    public I18nTextViewWrapper(TextView textview, String s, String s1, boolean flag, boolean flag1)
    {
        viewPartiallyHidden = false;
        textFits = false;
        lastLogState = 0;
        logOverflow = flag;
        highlightOverflow = flag1;
        view = textview;
        resId = s;
        activityName = s1;
        typeName = getClassName(textview);
        int i = idCounter;
        idCounter = i + 1;
        uniqueID = i;
        textview.addTextChangedListener(this);
        refresh();
    }

    private int getActualViewHeight()
    {
        return view.getHeight() - view.getCompoundPaddingTop() - view.getCompoundPaddingBottom();
    }

    private int getActualViewWidth()
    {
        return view.getWidth() - view.getCompoundPaddingRight() - view.getCompoundPaddingLeft();
    }

    public static String getClassName(Object obj)
    {
        String s = obj.getClass().getName();
        return s.substring(1 + s.lastIndexOf('.'));
    }

    private String getLogMessage()
    {
        String s = String.valueOf(view.getText());
        Layout layout = view.getLayout();
        StringBuilder stringbuilder = (new StringBuilder()).append(String.valueOf(uniqueID)).append('\t').append(activityName).append('\t').append(textFits).append('\t').append(viewPartiallyHidden).append('\t');
        String s1;
        StringBuilder stringbuilder1;
        String s2;
        if(s != null)
            s1 = (new StringBuilder()).append("\"").append(s.replace("\n", "\\n")).append("\"").toString();
        else
            s1 = "\"\"";
        stringbuilder1 = stringbuilder.append(s1).append('\t').append(typeName).append('\t').append("(").append(textRect.width()).append(",").append(textRect.height()).append(")").append('\t').append("(").append(view.getWidth()).append(",").append(view.getHeight()).append(")").append('\t').append("(").append(visibleRect.width()).append(",").append(visibleRect.height()).append(")").append('\t');
        if(layout != null)
            s2 = (new StringBuilder()).append("(").append(layout.getWidth()).append(",").append(layout.getHeight()).append(")").toString();
        else
            s2 = "(null)";
        return stringbuilder1.append(s2).append('\t').append(buildHeiarchyString()).append('\t').append(resId).toString();
    }

    public static String getLogMessageColumns()
    {
        return "UniqueID\tActivity\tTextFitsInView\tViewPartiallyHidden\tViewContents\tTypeName\tTextPaintSize\tTextViewSize\tTextViewVisibleSize\tTextLayoutSize\tHeiarchyPathToRoot\tResourceIdentifier";
    }

    private boolean hasListViewParent()
    {
        ViewParent viewparent = view.getParent();
_L3:
        if(viewparent == null)
            break MISSING_BLOCK_LABEL_40;
        if(!(viewparent instanceof ListView) && !(viewparent instanceof ScrollView)) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        viewparent = viewparent.getParent();
          goto _L3
        flag = false;
          goto _L4
    }

    private void refresh()
    {
        registerForPredraw();
    }

    private void registerForPredraw()
    {
        ViewTreeObserver viewtreeobserver = view.getViewTreeObserver();
        if(viewtreeobserver.isAlive())
        {
            viewtreeobserver.removeOnPreDrawListener(this);
            viewtreeobserver.addOnPreDrawListener(this);
        }
    }

    public void afterTextChanged(Editable editable)
    {
    }

    public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    String buildHeiarchyString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        for(ViewParent viewparent = view.getParent(); viewparent != null; viewparent = viewparent.getParent())
        {
            stringbuffer.append("==>");
            stringbuffer.append(getClassName(viewparent));
        }

        return stringbuffer.toString();
    }

    public void calculateValues()
    {
        boolean flag = true;
        TextPaint textpaint = view.getPaint();
        String s = String.valueOf(view.getText());
        textpaint.getTextBounds(s, 0, s.length(), textRect);
        view.getLocalVisibleRect(visibleRect);
        Layout layout = view.getLayout();
        boolean flag1;
        if(visibleRect.width() < view.getWidth() || !hasListViewParent() && visibleRect.height() < view.getHeight())
            flag1 = flag;
        else
            flag1 = false;
        viewPartiallyHidden = flag1;
        if(viewPartiallyHidden)
            textFits = false;
        else
        if(view.getLineCount() > flag && layout != null)
        {
            if(layout.getHeight() > getActualViewHeight() || layout.getWidth() > getActualViewWidth())
                flag = false;
            textFits = flag;
        } else
        if(layout == null || String.valueOf(view.getText()).equals(String.valueOf(layout.getText())))
        {
            if(textRect.height() > getActualViewHeight() || textRect.width() > getActualViewWidth())
                flag = false;
            textFits = flag;
        } else
        {
            textFits = false;
        }
    }

    public boolean onPreDraw()
    {
        int i;
        android.graphics.drawable.Drawable drawable;
        i = 2;
        calculateValues();
        int j = lastLogState;
        int k;
        if(textFits)
            k = i;
        else
            k = 1;
        if(j == k || view.getVisibility() != 0 || view.getWidth() <= 0) goto _L2; else goto _L1
_L1:
        if(logOverflow)
            TextViewLogger.getInstance().log(this);
        if(!highlightOverflow) goto _L4; else goto _L3
_L3:
        drawable = view.getBackground();
        if(textFits || (drawable instanceof DebugBackground)) goto _L6; else goto _L5
_L5:
        if(drawable == null)
            view.setBackgroundDrawable(new DebugColorDrawable(0xffff0000));
        else
            view.setBackgroundDrawable(new DebugBackgroundDrawable(0xffff0000, drawable));
_L8:
        view.setDrawingCacheEnabled(false);
_L4:
        if(!textFits)
            i = 1;
        lastLogState = i;
_L2:
        return true;
_L6:
        if(textFits && (drawable instanceof DebugBackground))
            if(drawable instanceof DebugBackgroundDrawable)
                view.setBackgroundDrawable(((DebugBackgroundDrawable)drawable).getOldDrawable());
            else
                view.setBackgroundDrawable(null);
        if(true) goto _L8; else goto _L7
_L7:
    }

    public void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        refresh();
    }

    static final int DEBUGVIEW_INVALID = 1;
    static final int DEBUGVIEW_UNKNOWN = 0;
    static final int DEBUGVIEW_VALID = 2;
    private static final char DEL = 9;
    static int idCounter = 0;
    final String activityName;
    final boolean highlightOverflow;
    int lastLogState;
    final boolean logOverflow;
    final String resId;
    boolean textFits;
    final Rect textRect = new Rect();
    final String typeName;
    final int uniqueID;
    final TextView view;
    boolean viewPartiallyHidden;
    final Rect visibleRect = new Rect();

}
