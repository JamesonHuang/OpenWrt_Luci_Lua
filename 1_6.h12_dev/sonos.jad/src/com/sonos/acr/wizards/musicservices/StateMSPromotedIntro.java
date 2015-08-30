// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.musicservices;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.*;
import android.widget.TextView;
import com.sonos.acr.downloadmanager.BitmapDownloadManager;
import com.sonos.acr.downloadmanager.DownloadBitmapCacheListener;
import com.sonos.acr.util.AlbumArtSize;
import com.sonos.acr.util.ImageUtils;
import com.sonos.sclib.SCIMusicServiceWizard;

// Referenced classes of package com.sonos.acr.wizards.musicservices:
//            MusicServicesWizardState, MusicServicesWizard

public class StateMSPromotedIntro extends MusicServicesWizardState
    implements DownloadBitmapCacheListener
{

    public StateMSPromotedIntro(MusicServicesWizard musicserviceswizard)
    {
        super(musicserviceswizard, com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.STATE_MUSICSERVICE_PROMOTED_INTRO, 0x7f030033, true, false);
    }

    public void onBitmapDownloadFailed(long l, String s, int i)
    {
    }

    public void onBitmapDownloaded(long l, String s, Bitmap bitmap, int i, boolean flag)
    {
        serviceText.setCompoundDrawablesWithIntrinsicBounds(new BitmapDrawable(serviceText.getContext().getResources(), bitmap), null, null, null);
    }

    public void onClick(View view)
    {
        super.onClick(view);
        if(view.getId() != 0x7f0a0056) goto _L2; else goto _L1
_L1:
        getWizard().setRemovePromotedChoice(false);
        transitionNext();
_L4:
        return;
_L2:
        if(view.getId() == 0x7f0a0057)
        {
            getWizard().setRemovePromotedChoice(true);
            transitionNext();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        String s = getWizard().getServiceName();
        serviceText = (TextView)view.findViewById(0x7f0a00c5);
        serviceText.setText(s);
        int i = serviceText.getContext().getResources().getDimensionPixelSize(0x7f090004);
        serviceText.setCompoundDrawablesWithIntrinsicBounds(new BitmapDrawable(serviceText.getContext().getResources(), ImageUtils.getSvgFromResource(serviceText.getContext().getResources(), 0x7f06002b, i, i)), null, null, null);
        serialNumber = AlbumArtSize.SIZE_BROWSE.getManager().queueDownload(getWizard().getServiceLogoUri(), com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOGO, this, 0);
        return view;
    }

    public void onExit(com.sonos.sclib.SCIWizard.StateTransitionType statetransitiontype)
    {
        super.onExit(statetransitiontype);
        AlbumArtSize.SIZE_BROWSE.getManager().cancelDownload(this, serialNumber);
    }

    private long serialNumber;
    private TextView serviceText;
}
