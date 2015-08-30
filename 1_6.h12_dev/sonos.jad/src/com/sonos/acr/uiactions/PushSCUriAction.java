// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions;

import android.os.Handler;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.browse.Browseable;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.search.SearchController;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.SLog;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.uiactions:
//            UIAction

public class PushSCUriAction extends UIAction
{

    public PushSCUriAction(SonosActivity sonosactivity, String s, String s1, boolean flag)
    {
        super(sonosactivity);
        mTitle = s;
        mUri = s1;
        mClearStack = flag;
    }

    public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
    {
        SLog.e(LOG_TAG, (new StringBuilder()).append("PUSH SCURI ACTION CALLED! \n\tTitle: ").append(mTitle).append("\n\tURI: ").append(mUri).append("\n\tClearStack: ").append(mClearStack).toString());
        if(sclib.SCLibMatchesFixedSCUriCategory(mUri, SCFixedSCUriCategory.SC_SCURICATEGORY_Music_Pandora_AddToStation) || sclib.SCLibIsSearchSCUri(mUri))
        {
            if(currentContext.getHousehold() != null)
            {
                SCIBrowseDataSource scibrowsedatasource = LibraryUtils.createBrowseDataSource(mUri, "");
                SCIPandoraResults scipandoraresults = (SCIPandoraResults)LibraryUtils.cast(scibrowsedatasource, com/sonos/sclib/SCIPandoraResults);
                if(scipandoraresults != null)
                {
                    final SCISearchable searchable;
                    if(sclib.SCLibMatchesFixedSCUriCategory(mUri, SCFixedSCUriCategory.SC_SCURICATEGORY_Music_Pandora_AddToStation))
                        searchable = scipandoraresults.getAddToStationSearchable();
                    else
                        searchable = currentContext.getHousehold().lookupSearchableBySCUri(scibrowsedatasource.getSCUri());
                    if(searchable != null)
                        (new Handler()).postDelayed(new Runnable() {

                            public void run()
                            {
                                currentContext.getSearchController().setRestrictedSearchable(searchable);
                                currentContext.showSearch();
                            }

                            final PushSCUriAction this$0;
                            final SCISearchable val$searchable;

            
            {
                this$0 = PushSCUriAction.this;
                searchable = scisearchable;
                super();
            }
                        }
, 250L);
                    else
                        SLog.e(LOG_TAG, "Cannot Push URI!  Add to Station Searchable is null");
                } else
                {
                    currentContext.showSearch();
                }
            }
        } else
        if(currentContext instanceof Browseable)
            ((Browseable)currentContext).pushURI(mUri, mTitle, mClearStack);
        else
            SLog.e(LOG_TAG, (new StringBuilder()).append("Cannot Push URI!  Current context is not browseable: ").append(currentContext.getClass().getName()).toString());
        return SCActionCompletionStatus.DONE_WITH_ACTION;
    }

    private static final String LOG_TAG = com/sonos/acr/uiactions/PushSCUriAction.getSimpleName();
    boolean mClearStack;
    String mTitle;
    String mUri;

}
