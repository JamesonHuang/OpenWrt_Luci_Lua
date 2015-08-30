// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.musicservices;

import android.graphics.Bitmap;
import android.view.*;
import com.sonos.acr.downloadmanager.BitmapDownloadManager;
import com.sonos.acr.downloadmanager.DownloadBitmapCacheListener;
import com.sonos.acr.util.AlbumArtSize;
import com.sonos.acr.view.SonosImageView;
import com.sonos.sclib.SCIMusicServiceWizard;

// Referenced classes of package com.sonos.acr.wizards.musicservices:
//            MusicServicesWizardState, MusicServicesWizard

public class StateMSIntro extends MusicServicesWizardState
    implements DownloadBitmapCacheListener
{

    public StateMSIntro(MusicServicesWizard musicserviceswizard)
    {
        super(musicserviceswizard, com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.STATE_MUSICSERVICE_INTRO, 0x7f03002e, true, false);
    }

    public void onBitmapDownloadFailed(long l, String s, int i)
    {
    }

    public void onBitmapDownloaded(long l, String s, Bitmap bitmap, int i, boolean flag)
    {
        if(bitmap != null)
            serviceImage.setImageBitmap(bitmap);
        else
            serviceImage.setImageResource(i);
    }

    public void onClick(View view)
    {
        super.onClick(view);
        if(view.getId() != 0x7f0a0056) goto _L2; else goto _L1
_L1:
        getWizard().setHasAccountChoice(false);
        transitionNext();
_L4:
        return;
_L2:
        if(view.getId() == 0x7f0a0057)
        {
            getWizard().setHasAccountChoice(true);
            transitionNext();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        serviceImage = (SonosImageView)view.findViewById(0x7f0a00c2);
        BitmapDownloadManager bitmapdownloadmanager = AlbumArtSize.SIZE_BROWSE.getManager();
        String s = getWizard().getServiceLogoUri();
        bitmapdownloadmanager.queueDownload(s, com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOGO, this, 0);
        serialNumber = bitmapdownloadmanager.queueDownload(s, com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOGO, this, 0);
        return view;
    }

    public void onExit(com.sonos.sclib.SCIWizard.StateTransitionType statetransitiontype)
    {
        super.onExit(statetransitiontype);
        AlbumArtSize.SIZE_BROWSE.getManager().cancelDownload(this, serialNumber);
    }

    private long serialNumber;
    SonosImageView serviceImage;
}
