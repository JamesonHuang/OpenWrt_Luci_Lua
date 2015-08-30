// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions;

import android.content.SharedPreferences;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.sclib.SCActionCompletionStatus;
import com.sonos.sclib.SCIActionContext;

// Referenced classes of package com.sonos.acr.uiactions:
//            UIAction

public class ToggleBooleanPreferenceAction extends UIAction
{

    public ToggleBooleanPreferenceAction(SonosActivity sonosactivity, String s)
    {
        super(sonosactivity);
        booleanPreferenceName = s;
    }

    public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
    {
        boolean flag = false;
        boolean flag1 = prefs.getBoolean(booleanPreferenceName, false);
        android.content.SharedPreferences.Editor editor = prefs.edit();
        String s = booleanPreferenceName;
        if(!flag1)
            flag = true;
        editor.putBoolean(s, flag).commit();
        return SCActionCompletionStatus.DONE_WITH_ACTION;
    }

    final String booleanPreferenceName;
    final SharedPreferences prefs = LibraryUtils.getSharedPreferences();
}
