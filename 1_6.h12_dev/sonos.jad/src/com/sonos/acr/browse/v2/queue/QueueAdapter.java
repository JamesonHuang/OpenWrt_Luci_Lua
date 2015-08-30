// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.queue;

import android.content.Context;
import com.sonos.acr.browse.v2.BrowseDataSourceEventSink;
import com.sonos.acr.browse.v2.adapters.BrowseDataSourceAdapter;
import com.sonos.sclib.SCIBrowseDataSource;
import com.sonos.sclib.SCIPowerscrollDataSource;

// Referenced classes of package com.sonos.acr.browse.v2.queue:
//            QueueDataSourceEventSink

public class QueueAdapter extends BrowseDataSourceAdapter
{
    private class QueueEventSink extends QueueDataSourceEventSink
    {

        public void onBrowseChanged(SCIBrowseDataSource scibrowsedatasource)
        {
            QueueAdapter.this.onBrowseChanged(scibrowsedatasource);
        }

        public void onBrowseInvalidation(SCIBrowseDataSource scibrowsedatasource)
        {
        }

        public void onPowerScrollInfo(SCIBrowseDataSource scibrowsedatasource, SCIPowerscrollDataSource scipowerscrolldatasource)
        {
        }

        protected void onQueueCurrentItemChanged(SCIBrowseDataSource scibrowsedatasource)
        {
            onCurrentItemChanged(scibrowsedatasource);
        }

        protected void onQueueInUseChanged(SCIBrowseDataSource scibrowsedatasource)
        {
            QueueAdapter.this.onQueueInUseChanged(scibrowsedatasource);
        }

        final QueueAdapter this$0;

        private QueueEventSink()
        {
            this$0 = QueueAdapter.this;
            QueueDataSourceEventSink();
        }

    }


    public QueueAdapter(Context context)
    {
        super(context);
    }

    protected BrowseDataSourceEventSink createEventSink()
    {
        return new QueueEventSink();
    }

    protected void onCurrentItemChanged(SCIBrowseDataSource scibrowsedatasource)
    {
    }

    protected void onQueueInUseChanged(SCIBrowseDataSource scibrowsedatasource)
    {
    }

}
