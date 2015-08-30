// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions;

import android.app.AlertDialog;
import com.sonos.acr.SonosActivity;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.uiactions:
//            UIAction

public class DisplayTextPaneAction extends UIAction
{

    public DisplayTextPaneAction(SonosActivity sonosactivity, String s, String s1, SCIInfoViewTextPaneMetadata sciinfoviewtextpanemetadata)
    {
        super(sonosactivity);
        mTitle = s;
        mText = s1;
        mMetadata = sciinfoviewtextpanemetadata;
    }

    public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
    {
        currentContext.showTextPane(mTitle, mText, mMetadata, sciactioncontext);
        return SCActionCompletionStatus.DONE_WITH_ACTION;
    }

    private AlertDialog mDialog;
    private SCIInfoViewTextPaneMetadata mMetadata;
    private String mText;
    private String mTitle;
}
