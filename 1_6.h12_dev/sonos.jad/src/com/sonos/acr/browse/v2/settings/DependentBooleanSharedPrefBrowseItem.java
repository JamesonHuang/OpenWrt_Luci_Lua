// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.settings;

import android.content.SharedPreferences;

// Referenced classes of package com.sonos.acr.browse.v2.settings:
//            BooleanSharedPrefBrowseItem

public class DependentBooleanSharedPrefBrowseItem extends BooleanSharedPrefBrowseItem
{

    public DependentBooleanSharedPrefBrowseItem(String s, String s1, String s2, boolean flag, String as[], String s3)
    {
        super(s, s1, s2, flag);
        isEnabled = true;
        dependentSharedPrefs = as;
        disabledText = s3;
    }

    private boolean getLogicalAndedSharedPref()
    {
        boolean flag = true;
        String as[] = dependentSharedPrefs;
        int i = as.length;
        int j = 0;
        while(j < i) 
        {
            String s = as[j];
            if(flag && sharedPreferences.getBoolean(s, true))
                flag = true;
            else
                flag = false;
            j++;
        }
        return flag;
    }

    private void reevaluateIsEnabled()
    {
        isEnabled = getLogicalAndedSharedPref();
        notifyChanged();
    }

    public boolean canActOn()
    {
        boolean flag;
        if(isEnabled && super.canActOn())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean getPrefValue()
    {
        boolean flag;
        if(super.getPrefValue() && getLogicalAndedSharedPref())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public String getSecondaryTitle()
    {
        String s;
        if(canActOn())
            s = "";
        else
            s = disabledText;
        return s;
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedpreferences, String s)
    {
        super.onSharedPreferenceChanged(sharedpreferences, s);
        String as[] = dependentSharedPrefs;
        int i = as.length;
        for(int j = 0; j < i; j++)
            if(as[j].equals(s))
                reevaluateIsEnabled();

    }

    public void reevaluateState()
    {
        reevaluateIsEnabled();
    }

    String dependentSharedPrefs[];
    String disabledText;
    private boolean isEnabled;
}
