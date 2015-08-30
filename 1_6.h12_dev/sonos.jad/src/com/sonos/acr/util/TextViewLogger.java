// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.sonos.acr.view.SonosTextView;
import com.sonos.sclib.SCLibTruncatedStringsCallback;

// Referenced classes of package com.sonos.acr.util:
//            I18nTextViewWrapper, TextViewXMLLogger, DbgProp, ViewUtils, 
//            TextViewPngWrapper, SLog

public class TextViewLogger extends SCLibTruncatedStringsCallback
{

    private TextViewLogger()
    {
        clearTruncatedStrings();
    }

    public static TextViewLogger getInstance()
    {
        if(ourInstance == null)
            ourInstance = new TextViewLogger();
        return ourInstance;
    }

    public static android.view.LayoutInflater.Factory getLayoutInflater(Activity activity)
    {
        return new android.view.LayoutInflater.Factory() {

            public View onCreateView(String s, Context context, AttributeSet attributeset)
            {
                LayoutInflater layoutinflater = LayoutInflater.from(context);
                String as[];
                int i;
                int j;
                as = TextViewLogger.sClassPrefixList;
                i = as.length;
                j = 0;
_L3:
                String s1;
                if(j >= i)
                    break MISSING_BLOCK_LABEL_196;
                s1 = as[j];
                View view = layoutinflater.createView(s, s1, attributeset);
                if(view == null) goto _L2; else goto _L1
_L1:
                if(view instanceof TextView)
                {
                    TextView textview = (TextView)view;
                    if(!(view instanceof SonosTextView))
                        ViewUtils.fixTypeFace(textview);
                    if(logOverflow || highlightOverflow)
                        new I18nTextViewWrapper(textview, attributeset.getIdAttribute(), activityName, logOverflow, highlightOverflow);
                    if(createTextViewPngWrapper)
                        new TextViewPngWrapper(textview);
                }
_L4:
                return view;
                ClassNotFoundException classnotfoundexception;
                classnotfoundexception;
_L2:
                j++;
                  goto _L3
                Exception exception;
                exception;
                SLog.v("TextViewLogger", (new StringBuilder()).append("Exception inflating a view! [").append(s).append("] ").append(exception).append(" Cause: ").append(exception.getCause()).toString());
                view = null;
                  goto _L4
            }

            boolean createTextViewPngWrapper;
            boolean highlightOverflow;
            boolean logOverflow;
            final String val$activityName;

            
            {
                activityName = s;
                super();
                highlightOverflow = DbgProp.get("textOverflowHighlighting");
                logOverflow = DbgProp.get("textOverflowLogging");
                createTextViewPngWrapper = DbgProp.get("dumpTextView");
            }
        }
;
    }

    public void clearTruncatedStrings()
    {
        xmlLogger = new TextViewXMLLogger();
    }

    public String getTruncatedStrings()
    {
        return xmlLogger.getXmlData();
    }

    public void log(I18nTextViewWrapper i18ntextviewwrapper)
    {
        xmlLogger.log(i18ntextviewwrapper);
    }

    private static final String LOG_TAG = "TextViewLogger";
    private static TextViewLogger ourInstance;
    private static final String sClassPrefixList[];
    private static long timeStamp = System.currentTimeMillis();
    TextViewXMLLogger xmlLogger;

    static 
    {
        String as[] = new String[3];
        as[0] = null;
        as[1] = "android.widget.";
        as[2] = "android.webkit.";
        sClassPrefixList = as;
    }

}
