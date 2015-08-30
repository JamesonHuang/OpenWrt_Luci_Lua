// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.browse.v2.adapters.BrowseDataSourceAdapter;
import com.sonos.acr.browse.v2.adapters.IDataSourceAdapter;
import com.sonos.acr.browse.v2.view.BrowseItemCell;
import com.sonos.acr.sclib.SCLibManager;
import com.sonos.acr.uiactions.*;
import com.sonos.acr.util.*;
import com.sonos.sclib.*;
import java.util.ArrayList;

// Referenced classes of package com.sonos.acr.browse.v2.settings:
//            SettingsBrowsePageFragment, BooleanSharedPrefBrowseItem, DependentBooleanSharedPrefBrowseItem, BaseBrowseItem

public class AdvancedSettingsBrowsePageFragment extends SettingsBrowsePageFragment
{
    public class AdvancedSettingsBrowseDataSourceAdapter extends BrowseDataSourceAdapter
    {

        public int getCount()
        {
            return browseItems.size();
        }

        public SCIBrowseItem getItem(int i)
        {
            return (SCIBrowseItem)browseItems.get(i);
        }

        public volatile Object getItem(int i)
        {
            return getItem(i);
        }

        ArrayList browseItems;
        final AdvancedSettingsBrowsePageFragment this$0;

        public AdvancedSettingsBrowseDataSourceAdapter(Context context, SCIBrowseDataSource scibrowsedatasource)
        {
            this$0 = AdvancedSettingsBrowsePageFragment.this;
            super(context);
            browseItems = new ArrayList();
            setBrowseDataSource(scibrowsedatasource);
            Resources resources = SonosApplication.getInstance().getResources();
            int i = (int)dataSource.getNumItems();
            for(int j = 0; j < i; j++)
                browseItems.add(dataSource.getItemAtIndex(j));

            int k;
            int l;
            final String final_s;
            if(i >= 5)
                k = 5;
            else
                k = i;
            browseItems.add(k, new BooleanSharedPrefBrowseItem(resources.getString(0x7f0c0093), "x-sonos-scuri://settings/advanced/notifications", "NOTIFICATIONS_ENABLED", true));
            l = k + 1;
            if(android.os.Build.VERSION.SDK_INT >= 14)
            {
                final String final_s = resources.getString(0x7f0c0058);
                String as[] = new String[1];
                as[0] = "NOTIFICATIONS_ENABLED";
                DependentBooleanSharedPrefBrowseItem dependentbooleansharedprefbrowseitem = new DependentBooleanSharedPrefBrowseItem("x-sonos-scuri://settings/advanced/lockscreen", "LOCKSCREEN_ENABLED", true, as, SonosApplication.getInstance().getResources().getString(0x7f0c0059), AdvancedSettingsBrowsePageFragment.this) {

                    public void onSharedPreferenceChanged(SharedPreferences sharedpreferences, String s1)
                    {
                        super.onSharedPreferenceChanged(sharedpreferences, s1);
                        if("LOCKSCREEN_ENABLED".equals(s1))
                            if(sharedpreferences.getBoolean("LOCKSCREEN_ENABLED", true))
                                sclib.getAppReportingInstance().interaction(com.sonos.sclib.SCIAppReporting.SCInteractionID.ACTIVATED, com.sonos.sclib.SCIAppReporting.SCViewID.SETTINGS, com.sonos.sclib.SCIAppReporting.SCViewComponentID.VC_LOCKSCREEN);
                            else
                                sclib.getAppReportingInstance().interaction(com.sonos.sclib.SCIAppReporting.SCInteractionID.DEACTIVATED, com.sonos.sclib.SCIAppReporting.SCViewID.SETTINGS, com.sonos.sclib.SCIAppReporting.SCViewComponentID.VC_LOCKSCREEN);
                    }

                    final AdvancedSettingsBrowseDataSourceAdapter this$1;
                    final AdvancedSettingsBrowsePageFragment val$this$0;

                
                {
                    this$1 = AdvancedSettingsBrowseDataSourceAdapter.this;
                    this$0 = advancedsettingsbrowsepagefragment;
                    super(final_s, s1, s2, flag, as, s3);
                }
                }
;
                dependentbooleansharedprefbrowseitem.reevaluateState();
                browseItems.add(l, dependentbooleansharedprefbrowseitem);
                int i1 = l + 1;
                String s = resources.getString(0x7f0c0056);
                String as1[] = new String[2];
                as1[0] = "LOCKSCREEN_ENABLED";
                as1[1] = "NOTIFICATIONS_ENABLED";
                DependentBooleanSharedPrefBrowseItem dependentbooleansharedprefbrowseitem1 = new DependentBooleanSharedPrefBrowseItem(s, "x-sonos-scuri://settings/advanced/externalcontrols", "EXTERNAL_CONTROLS", false, as1, SonosApplication.getInstance().getResources().getString(0x7f0c0057));
                dependentbooleansharedprefbrowseitem1.reevaluateState();
                browseItems.add(i1, dependentbooleansharedprefbrowseitem1);
            }
            final_s = DbgProp.get("crashOnSettings", "");
            if(StringUtils.isNotEmptyOrNull(final_s))
                browseItems.add(0, new BaseBrowseItem(final_s, AdvancedSettingsBrowsePageFragment.this) {

                    public SCIEnumerator getActions()
                    {
                        return LibraryUtils.getSCLibManager().getLibrary().createCustomUIActionEnumerator(com/sonos/acr/uiactions/CrashAction.getSimpleName(), getTitle());
                    }

                    final AdvancedSettingsBrowseDataSourceAdapter this$1;
                    final AdvancedSettingsBrowsePageFragment val$this$0;

                
                {
                    this$1 = AdvancedSettingsBrowseDataSourceAdapter.this;
                    this$0 = advancedsettingsbrowsepagefragment;
                    super(final_s, s1);
                }
                }
);
            if(DbgProp.getInstance().get("crashNativeOnSettings", false))
                browseItems.add(0, new BaseBrowseItem("Native Crash", AdvancedSettingsBrowsePageFragment.this) {

                    public SCIEnumerator getActions()
                    {
                        return LibraryUtils.getSCLibManager().getLibrary().createCustomUIActionEnumerator(com/sonos/acr/uiactions/CrashNativeAction.getSimpleName(), getTitle());
                    }

                    final AdvancedSettingsBrowseDataSourceAdapter this$1;
                    final AdvancedSettingsBrowsePageFragment val$this$0;

                
                {
                    this$1 = AdvancedSettingsBrowseDataSourceAdapter.this;
                    this$0 = advancedsettingsbrowsepagefragment;
                    super(final_s, s1);
                }
                }
);
            if(SonosApplication.isDebuggable())
            {
                final String final_s = resources.getString(0x7f0c0043);
                browseItems.add(0, new BaseBrowseItem(final_s, AdvancedSettingsBrowsePageFragment.this) {

                    public SCIEnumerator getActions()
                    {
                        return LibraryUtils.getSCLibManager().getLibrary().createCustomUIActionEnumerator(com/sonos/acr/uiactions/DebugWizardAction.getSimpleName(), getTitle());
                    }

                    final AdvancedSettingsBrowseDataSourceAdapter this$1;
                    final AdvancedSettingsBrowsePageFragment val$this$0;

                
                {
                    this$1 = AdvancedSettingsBrowseDataSourceAdapter.this;
                    this$0 = advancedsettingsbrowsepagefragment;
                    super(final_s, s1);
                }
                }
);
            }
            if(DbgProp.getInstance().get("debugSVG", false))
                browseItems.add(0, new BaseBrowseItem("Debug SVGs", AdvancedSettingsBrowsePageFragment.this) {

                    public SCIEnumerator getActions()
                    {
                        return LibraryUtils.getSCLibManager().getLibrary().createCustomUIActionEnumerator(com/sonos/acr/uiactions/DebugSvgAction.getSimpleName(), getTitle());
                    }

                    final AdvancedSettingsBrowseDataSourceAdapter this$1;
                    final AdvancedSettingsBrowsePageFragment val$this$0;

                
                {
                    this$1 = AdvancedSettingsBrowseDataSourceAdapter.this;
                    this$0 = advancedsettingsbrowsepagefragment;
                    super(final_s, s1);
                }
                }
);
        }
    }


    public AdvancedSettingsBrowsePageFragment(SCIBrowseDataSource scibrowsedatasource)
    {
        super(scibrowsedatasource);
    }

    protected IDataSourceAdapter createDataSourceAdapter(SCIBrowseDataSource scibrowsedatasource, Context context)
    {
        return new AdvancedSettingsBrowseDataSourceAdapter(getThemedContext(), scibrowsedatasource);
    }

    public void updateCellView(BrowseItemCell browseitemcell, int i)
    {
    }

    public static final String SETTINGS_ADVANCED_EXTERNAL_CONTROLS_SCURI = "x-sonos-scuri://settings/advanced/externalcontrols";
    public static final String SETTINGS_ADVANCED_LOCKSCREEN_SCURI = "x-sonos-scuri://settings/advanced/lockscreen";
    public static final String SETTINGS_ADVANCED_NOTIFICATIONS_SCURI = "x-sonos-scuri://settings/advanced/notifications";
    public static final String SETTINGS_ADVANCED_TABLET_LAYOUT_SCURI = "x-sonos-scuri://settings/advanced/tabletlayout";
}
