// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content;

import android.content.*;
import android.os.*;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;

public class LocalBroadcastManager
{
    private static class BroadcastRecord
    {

        final Intent intent;
        final ArrayList receivers;

        BroadcastRecord(Intent intent1, ArrayList arraylist)
        {
            intent = intent1;
            receivers = arraylist;
        }
    }

    private static class ReceiverRecord
    {

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder(128);
            stringbuilder.append("Receiver{");
            stringbuilder.append(receiver);
            stringbuilder.append(" filter=");
            stringbuilder.append(filter);
            stringbuilder.append("}");
            return stringbuilder.toString();
        }

        boolean broadcasting;
        final IntentFilter filter;
        final BroadcastReceiver receiver;

        ReceiverRecord(IntentFilter intentfilter, BroadcastReceiver broadcastreceiver)
        {
            filter = intentfilter;
            receiver = broadcastreceiver;
        }
    }


    private LocalBroadcastManager(Context context)
    {
        mAppContext = context;
        mHandler = new Handler(context.getMainLooper()) {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 1 1: default 24
            //                           1 30;
                   goto _L1 _L2
_L1:
                super.handleMessage(message);
_L4:
                return;
_L2:
                executePendingBroadcasts();
                if(true) goto _L4; else goto _L3
_L3:
            }

            final LocalBroadcastManager this$0;

            
            {
                this$0 = LocalBroadcastManager.this;
                super(looper);
            }
        }
;
    }

    private void executePendingBroadcasts()
    {
_L4:
        BroadcastRecord abroadcastrecord[];
        int j;
        synchronized(mReceivers)
        {
            int i = mPendingBroadcasts.size();
            if(i <= 0)
                return;
            abroadcastrecord = new BroadcastRecord[i];
            mPendingBroadcasts.toArray(abroadcastrecord);
            mPendingBroadcasts.clear();
        }
        j = 0;
_L2:
        if(j < abroadcastrecord.length)
        {
            BroadcastRecord broadcastrecord = abroadcastrecord[j];
            for(int k = 0; k < broadcastrecord.receivers.size(); k++)
                ((ReceiverRecord)broadcastrecord.receivers.get(k)).receiver.onReceive(mAppContext, broadcastrecord.intent);

            break MISSING_BLOCK_LABEL_120;
        }
        continue; /* Loop/switch isn't completed */
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static LocalBroadcastManager getInstance(Context context)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mInstance == null)
            mInstance = new LocalBroadcastManager(context.getApplicationContext());
        LocalBroadcastManager localbroadcastmanager = mInstance;
        return localbroadcastmanager;
    }

    public void registerReceiver(BroadcastReceiver broadcastreceiver, IntentFilter intentfilter)
    {
        HashMap hashmap = mReceivers;
        hashmap;
        JVM INSTR monitorenter ;
        ReceiverRecord receiverrecord = new ReceiverRecord(intentfilter, broadcastreceiver);
        ArrayList arraylist = (ArrayList)mReceivers.get(broadcastreceiver);
        if(arraylist == null)
        {
            arraylist = new ArrayList(1);
            mReceivers.put(broadcastreceiver, arraylist);
        }
        arraylist.add(intentfilter);
        for(int i = 0; i < intentfilter.countActions(); i++)
        {
            String s = intentfilter.getAction(i);
            ArrayList arraylist1 = (ArrayList)mActions.get(s);
            if(arraylist1 == null)
            {
                arraylist1 = new ArrayList(1);
                mActions.put(s, arraylist1);
            }
            arraylist1.add(receiverrecord);
        }

        return;
    }

    public boolean sendBroadcast(Intent intent)
    {
        HashMap hashmap = mReceivers;
        hashmap;
        JVM INSTR monitorenter ;
        boolean flag1;
        ArrayList arraylist1;
        int j;
        String s3;
        String s = intent.getAction();
        String s1 = intent.resolveTypeIfNeeded(mAppContext.getContentResolver());
        android.net.Uri uri = intent.getData();
        String s2 = intent.getScheme();
        java.util.Set set = intent.getCategories();
        boolean flag;
        ArrayList arraylist;
        int i;
        if((8 & intent.getFlags()) != 0)
            flag = true;
        else
            flag = false;
        if(flag)
            Log.v("LocalBroadcastManager", (new StringBuilder()).append("Resolving type ").append(s1).append(" scheme ").append(s2).append(" of intent ").append(intent).toString());
        arraylist = (ArrayList)mActions.get(intent.getAction());
        if(arraylist != null)
        {
            Exception exception;
            ReceiverRecord receiverrecord;
            int k;
            if(flag)
                Log.v("LocalBroadcastManager", (new StringBuilder()).append("Action list: ").append(arraylist).toString());
            arraylist1 = null;
            i = 0;
            continue; /* Loop/switch isn't completed */
        }
          goto _L1
_L20:
        if(i >= arraylist.size()) goto _L3; else goto _L2
_L2:
        receiverrecord = (ReceiverRecord)arraylist.get(i);
        if(flag)
            Log.v("LocalBroadcastManager", (new StringBuilder()).append("Matching against filter ").append(receiverrecord.filter).toString());
        if(!receiverrecord.broadcasting) goto _L5; else goto _L4
_L4:
        if(flag)
            Log.v("LocalBroadcastManager", "  Filter's target already added");
          goto _L6
_L5:
        k = receiverrecord.filter.match(s, s1, s2, uri, set, "LocalBroadcastManager");
        if(k < 0) goto _L8; else goto _L7
_L7:
        if(flag)
            Log.v("LocalBroadcastManager", (new StringBuilder()).append("  Filter matched!  match=0x").append(Integer.toHexString(k)).toString());
        if(arraylist1 == null)
            arraylist1 = new ArrayList();
        arraylist1.add(receiverrecord);
        receiverrecord.broadcasting = true;
          goto _L6
        exception;
        throw exception;
_L8:
        if(!flag) goto _L6; else goto _L9
_L9:
        k;
        JVM INSTR tableswitch -4 -1: default 376
    //                   -4 521
    //                   -3 514
    //                   -2 528
    //                   -1 535;
           goto _L10 _L11 _L12 _L13 _L14
_L10:
        s3 = "unknown reason";
_L16:
        Log.v("LocalBroadcastManager", (new StringBuilder()).append("  Filter did not match: ").append(s3).toString());
          goto _L6
_L18:
        for(; j < arraylist1.size(); j++)
            ((ReceiverRecord)arraylist1.get(j)).broadcasting = false;

        mPendingBroadcasts.add(new BroadcastRecord(intent, arraylist1));
        if(!mHandler.hasMessages(1))
            mHandler.sendEmptyMessage(1);
        flag1 = true;
        hashmap;
        JVM INSTR monitorexit ;
          goto _L15
_L1:
        hashmap;
        JVM INSTR monitorexit ;
        flag1 = false;
          goto _L15
_L6:
        i++;
        continue; /* Loop/switch isn't completed */
_L12:
        s3 = "action";
          goto _L16
_L11:
        s3 = "category";
          goto _L16
_L13:
        s3 = "data";
          goto _L16
_L14:
        s3 = "type";
          goto _L16
_L3:
        if(arraylist1 == null) goto _L1; else goto _L17
_L17:
        j = 0;
          goto _L18
_L15:
        return flag1;
        if(true) goto _L20; else goto _L19
_L19:
    }

    public void sendBroadcastSync(Intent intent)
    {
        if(sendBroadcast(intent))
            executePendingBroadcasts();
    }

    public void unregisterReceiver(BroadcastReceiver broadcastreceiver)
    {
        HashMap hashmap = mReceivers;
        hashmap;
        JVM INSTR monitorenter ;
        ArrayList arraylist = (ArrayList)mReceivers.remove(broadcastreceiver);
        if(arraylist != null) goto _L2; else goto _L1
_L10:
        int i;
        if(i >= arraylist.size()) goto _L1; else goto _L3
_L3:
        IntentFilter intentfilter;
        int j;
        intentfilter = (IntentFilter)arraylist.get(i);
        j = 0;
_L8:
        if(j >= intentfilter.countActions()) goto _L5; else goto _L4
_L4:
        String s;
        ArrayList arraylist1;
        int k;
        s = intentfilter.getAction(j);
        arraylist1 = (ArrayList)mActions.get(s);
        if(arraylist1 == null)
            continue; /* Loop/switch isn't completed */
        k = 0;
_L7:
        if(k < arraylist1.size())
        {
            if(((ReceiverRecord)arraylist1.get(k)).receiver == broadcastreceiver)
            {
                arraylist1.remove(k);
                k--;
            }
        } else
        {
            if(arraylist1.size() <= 0)
                mActions.remove(s);
            continue; /* Loop/switch isn't completed */
        }
        k++;
        continue; /* Loop/switch isn't completed */
_L1:
        return;
_L2:
        i = 0;
        continue; /* Loop/switch isn't completed */
        if(true) goto _L7; else goto _L6
_L6:
        j++;
          goto _L8
_L5:
        i++;
        if(true) goto _L10; else goto _L9
_L9:
    }

    private static final boolean DEBUG = false;
    static final int MSG_EXEC_PENDING_BROADCASTS = 1;
    private static final String TAG = "LocalBroadcastManager";
    private static LocalBroadcastManager mInstance;
    private static final Object mLock = new Object();
    private final HashMap mActions = new HashMap();
    private final Context mAppContext;
    private final Handler mHandler;
    private final ArrayList mPendingBroadcasts = new ArrayList();
    private final HashMap mReceivers = new HashMap();


}
