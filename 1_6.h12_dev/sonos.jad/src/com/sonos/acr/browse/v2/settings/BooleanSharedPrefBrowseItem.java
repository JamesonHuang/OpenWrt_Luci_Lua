// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.settings;

import android.content.SharedPreferences;
import com.sonos.acr.sclib.SCLibManager;
import com.sonos.acr.uiactions.ToggleBooleanPreferenceAction;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.sclib.*;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.browse.v2.settings:
//            BaseBrowseItem

public class BooleanSharedPrefBrowseItem extends BaseBrowseItem
    implements android.content.SharedPreferences.OnSharedPreferenceChangeListener
{

    public BooleanSharedPrefBrowseItem(String s, String s1, String s2, boolean flag)
    {
        super(s, s1);
        sharedPrefKey = s2;
        defaultValue = flag;
        sharedPreferences = LibraryUtils.getSharedPreferences();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        actions = LibraryUtils.getSCLibManager().getLibrary().createCustomUIActionEnumerator(com/sonos/acr/uiactions/ToggleBooleanPreferenceAction.getSimpleName(), s2);
    }

    /**
     * @deprecated Method dispose is deprecated
     */

    public void dispose()
    {
        this;
        JVM INSTR monitorenter ;
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
        super.dispose();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public SCIEnumerator getActions()
    {
        return actions;
    }

    public boolean getPrefValue()
    {
        return sharedPreferences.getBoolean(sharedPrefKey, defaultValue);
    }

    protected void notifyChanged()
    {
        for(Iterator iterator = eventSinks.iterator(); iterator.hasNext(); ((SCIEventSink)iterator.next()).dispatchEvent(this, sclib.SCIBROWSEITEM_ONITEMCHANGED_EVENT));
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedpreferences, String s)
    {
        if(sharedPrefKey.equals(s))
            notifyChanged();
    }

    public SCIObj queryInterface(String s)
    {
        if(!s.equals("SCISETTINGS_CUSTOM_BROWSE_ITEM_INTERFACE"))
            this = super.queryInterface(s);
        return this;
    }

    public void reevaluateState()
    {
    }

    public static final String CUSTOM_BROWSE_ITEM_INTERFACE = "SCISETTINGS_CUSTOM_BROWSE_ITEM_INTERFACE";
    private SCIEnumerator actions;
    private final boolean defaultValue;
    private final String sharedPrefKey;
    protected SharedPreferences sharedPreferences;
}
