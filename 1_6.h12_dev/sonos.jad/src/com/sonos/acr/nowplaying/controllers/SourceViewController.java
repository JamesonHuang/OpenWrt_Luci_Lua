// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.nowplaying.controllers;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.sonos.acr.sclib.wrappers.NowPlaying;
import com.sonos.acr.util.StringUtils;
import com.sonos.acr.util.WeakHashSet;
import com.sonos.acr.view.MarqueeView;
import com.sonos.acr.view.RemoteImageView;
import java.util.Iterator;

public class SourceViewController
    implements com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayingListener
{
    public static final class MetaDataType extends Enum
    {

        public static MetaDataType valueOf(String s)
        {
            return (MetaDataType)Enum.valueOf(com/sonos/acr/nowplaying/controllers/SourceViewController$MetaDataType, s);
        }

        public static MetaDataType[] values()
        {
            return (MetaDataType[])$VALUES.clone();
        }

        private static final MetaDataType $VALUES[];
        public static final MetaDataType albumart;
        public static final MetaDataType albumartsmall;
        public static final MetaDataType hasmusic;
        public static final MetaDataType logo;
        public static final MetaDataType proplabel;
        public static final MetaDataType proptext;

        static 
        {
            albumart = new MetaDataType("albumart", 0);
            albumartsmall = new MetaDataType("albumartsmall", 1);
            logo = new MetaDataType("logo", 2);
            hasmusic = new MetaDataType("hasmusic", 3);
            proptext = new MetaDataType("proptext", 4);
            proplabel = new MetaDataType("proplabel", 5);
            MetaDataType ametadatatype[] = new MetaDataType[6];
            ametadatatype[0] = albumart;
            ametadatatype[1] = albumartsmall;
            ametadatatype[2] = logo;
            ametadatatype[3] = hasmusic;
            ametadatatype[4] = proptext;
            ametadatatype[5] = proplabel;
            $VALUES = ametadatatype;
        }

        private MetaDataType(String s, int i)
        {
            super(s, i);
        }
    }


    public SourceViewController(Context context)
    {
    }

    private void updateView(View view, NowPlaying nowplaying)
    {
        String as[];
        MetaDataType metadatatype;
        as = view.getTag().toString().split(":");
        metadatatype = MetaDataType.valueOf(as[1]);
        class _cls1
        {

            static final int $SwitchMap$com$sonos$acr$nowplaying$controllers$SourceViewController$MetaDataType[];

            static 
            {
                $SwitchMap$com$sonos$acr$nowplaying$controllers$SourceViewController$MetaDataType = new int[MetaDataType.values().length];
                NoSuchFieldError nosuchfielderror5;
                try
                {
                    $SwitchMap$com$sonos$acr$nowplaying$controllers$SourceViewController$MetaDataType[MetaDataType.albumart.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$acr$nowplaying$controllers$SourceViewController$MetaDataType[MetaDataType.albumartsmall.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$sonos$acr$nowplaying$controllers$SourceViewController$MetaDataType[MetaDataType.hasmusic.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$sonos$acr$nowplaying$controllers$SourceViewController$MetaDataType[MetaDataType.logo.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$sonos$acr$nowplaying$controllers$SourceViewController$MetaDataType[MetaDataType.proptext.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                $SwitchMap$com$sonos$acr$nowplaying$controllers$SourceViewController$MetaDataType[MetaDataType.proplabel.ordinal()] = 6;
_L2:
                return;
                nosuchfielderror5;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.com.sonos.acr.nowplaying.controllers.SourceViewController.MetaDataType[metadatatype.ordinal()];
        JVM INSTR tableswitch 1 6: default 68
    //                   1 69
    //                   2 87
    //                   3 105
    //                   4 131
    //                   5 177
    //                   6 177;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L6
_L1:
        return;
_L2:
        if(view instanceof RemoteImageView)
            ((RemoteImageView)view).setImageFromNowPlaying(nowplaying);
        continue; /* Loop/switch isn't completed */
_L3:
        if(view instanceof RemoteImageView)
            ((RemoteImageView)view).setSmallImageFromNowPlaying(nowplaying);
        continue; /* Loop/switch isn't completed */
_L4:
        int j;
        if(nowplaying.hasMusic())
            j = 0;
        else
            j = 8;
        view.setVisibility(j);
        continue; /* Loop/switch isn't completed */
_L5:
        int i = nowplaying.getPartnerLogoResourceId();
        if(view instanceof ImageView)
            ((ImageView)view).setImageResource(i);
        byte byte2;
        if(i == 0)
            byte2 = 8;
        else
            byte2 = 0;
        view.setVisibility(byte2);
        continue; /* Loop/switch isn't completed */
_L6:
        boolean flag = view instanceof TextView;
        boolean flag1 = view instanceof MarqueeView;
        if(!flag && !flag1)
            continue; /* Loop/switch isn't completed */
        if(metadatatype == MetaDataType.proptext)
        {
            if(as[2].startsWith("twoline_one"))
            {
                String as2[] = nowplaying.getTripleLineMetaData();
                if(as2 != null)
                {
                    byte byte1;
                    if(flag)
                        ((TextView)view).setText(as2[0]);
                    else
                        ((MarqueeView)view).setText(as2[0]);
                    if(StringUtils.isEmptyOrNull(as2[0]))
                        byte1 = 8;
                    else
                        byte1 = 0;
                    view.setVisibility(byte1);
                }
                continue; /* Loop/switch isn't completed */
            }
            if(as[2].startsWith("twoline_two"))
            {
                String as1[] = nowplaying.getTripleLineMetaData();
                String s1 = "";
                byte byte0;
                if(as1 != null)
                    if(StringUtils.isEmptyOrNull(as1[1]))
                        s1 = as1[2];
                    else
                    if(StringUtils.isEmptyOrNull(as1[2]))
                    {
                        s1 = as1[1];
                    } else
                    {
                        Object aobj[] = new Object[2];
                        aobj[0] = as1[1];
                        aobj[1] = as1[2];
                        s1 = String.format("%s \u2013 %s", aobj);
                    }
                if(flag)
                    ((TextView)view).setText(s1);
                else
                    ((MarqueeView)view).setText(s1);
                if(StringUtils.isEmptyOrNull(s1))
                    byte0 = 8;
                else
                    byte0 = 0;
                view.setVisibility(byte0);
                continue; /* Loop/switch isn't completed */
            }
        }
        if(!as[2].startsWith("twoline"))
        {
            com.sonos.acr.sclib.wrappers.NowPlaying.NowPlayingMetaData nowplayingmetadata = nowplaying.getMetaData(as[2]);
            String s;
            if(metadatatype == MetaDataType.proplabel)
                s = nowplayingmetadata.getLabel();
            else
                s = nowplayingmetadata.getText();
            if(flag)
                ((TextView)view).setText(s);
            else
                ((MarqueeView)view).setText(s);
        }
        if(true) goto _L1; else goto _L7
_L7:
    }

    void ignoreView(View view)
    {
        if(!metadataViews.remove(view));
    }

    void observeView(View view)
    {
        if(metadataViews.add(view) && lastNowPlaying != null)
            updateView(view, lastNowPlaying);
    }

    public void onNowPlayingEvent(NowPlaying nowplaying, com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent nowplayevent)
    {
        lastNowPlaying = nowplaying;
        Iterator iterator = metadataViews.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            View view = (View)iterator.next();
            if(view != null)
                updateView(view, nowplaying);
        } while(true);
    }

    protected static final int DELAY_MILLIS = 1000;
    public static final String NPSRC_OBSERVER = "npsrc";
    NowPlaying lastNowPlaying;
    private final WeakHashSet metadataViews = new WeakHashSet();
}
