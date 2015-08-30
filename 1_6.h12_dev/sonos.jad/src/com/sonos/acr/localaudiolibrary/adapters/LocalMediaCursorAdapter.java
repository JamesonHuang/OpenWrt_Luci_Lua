// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.localaudiolibrary.adapters;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import com.sonos.acr.application.SonosApplication;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.localaudiolibrary.adapters:
//            LocalMediaCursor

public abstract class LocalMediaCursorAdapter extends SCILocalMediaCollectionSwigBase
{

    public LocalMediaCursorAdapter(Context context1)
    {
        this(context1, null, null, null);
    }

    protected LocalMediaCursorAdapter(Context context1, String s, String s1)
    {
        this(context1, s, s1, null);
    }

    protected LocalMediaCursorAdapter(Context context1, String s, String s1, String s2)
    {
        context = context1;
        containerType = s;
        containerId = s1;
        searchTerm = s2;
    }

    private void destroyCursor()
    {
        if(mediaCursor != null)
        {
            try
            {
                mediaCursor.unregisterContentObserver(observer);
            }
            catch(IllegalStateException illegalstateexception) { }
            mediaCursor.close();
            mediaCursor = null;
        }
    }

    private void initialize()
    {
        if(mediaCursor == null)
            mediaCursor = createMediaCursor(context.getContentResolver());
    }

    protected abstract LocalMediaCursor createMediaCursor(ContentResolver contentresolver);

    public com.sonos.sclib.SCILocalMediaCollection.AllNodeType getAllNodeType()
    {
        return com.sonos.sclib.SCILocalMediaCollection.AllNodeType.LMBI_ALL_NODE_NONE;
    }

    public long getCount()
    {
        initialize();
        long l;
        if(mediaCursor != null)
            l = mediaCursor.getCount();
        else
            l = 0L;
        return l;
    }

    public SCILocalMusicBrowseItemInfo getItemAt(long l)
    {
        initialize();
        SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo;
        if(mediaCursor != null)
        {
            mediaCursor.moveToPosition((int)l);
            scilocalmusicbrowseiteminfo = mediaCursor.getCurrentItem();
        } else
        {
            scilocalmusicbrowseiteminfo = null;
        }
        return scilocalmusicbrowseiteminfo;
    }

    public String getPowerscrollCSV()
    {
        return "";
    }

    public com.sonos.sclib.SCIBrowseListPresentationMap.SCListPresentationType getPresentationType()
    {
        return com.sonos.sclib.SCIBrowseListPresentationMap.SCListPresentationType.PRESENTATION_LIST;
    }

    public void onContentChanged()
    {
        destroyCursor();
        if(dataListener != null)
            dataListener.onMediaCollectionChanged();
    }

    public void registerMediaCollectionListener(SCILocalMediaCollectionListener scilocalmediacollectionlistener)
    {
        dataListener = scilocalmediacollectionlistener;
        if(dataListener != null)
        {
            if(mediaCursor == null)
                initialize();
            if(mediaCursor != null)
                mediaCursor.registerContentObserver(observer);
        } else
        {
            destroyCursor();
        }
    }

    public boolean showTrackNumber()
    {
        return showTrackNumber;
    }

    protected String containerId;
    protected String containerType;
    protected Context context;
    SCILocalMediaCollectionListener dataListener;
    protected LocalMediaCursor mediaCursor;
    private ContentObserver observer = new ContentObserver(SonosApplication.getInstance().getHandler()) {

        public void onChange(boolean flag)
        {
            onContentChanged();
        }

        final LocalMediaCursorAdapter this$0;

            
            {
                this$0 = LocalMediaCursorAdapter.this;
                super(handler);
            }
    }
;
    protected String searchTerm;
    protected boolean showTrackNumber;
}
