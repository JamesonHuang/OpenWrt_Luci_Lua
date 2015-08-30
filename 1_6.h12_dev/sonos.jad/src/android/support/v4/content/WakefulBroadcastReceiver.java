// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content;

import android.content.*;
import android.os.PowerManager;
import android.util.Log;
import android.util.SparseArray;

public abstract class WakefulBroadcastReceiver extends BroadcastReceiver
{

    public WakefulBroadcastReceiver()
    {
    }

    public static boolean completeWakefulIntent(Intent intent)
    {
        boolean flag;
        int i;
        flag = false;
        i = intent.getIntExtra("android.support.content.wakelockid", 0);
        if(i != 0) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        SparseArray sparsearray = mActiveWakeLocks;
        sparsearray;
        JVM INSTR monitorenter ;
        android.os.PowerManager.WakeLock wakelock = (android.os.PowerManager.WakeLock)mActiveWakeLocks.get(i);
        if(wakelock != null)
        {
            wakelock.release();
            mActiveWakeLocks.remove(i);
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
        Log.w("WakefulBroadcastReceiver", (new StringBuilder()).append("No active wake lock id #").append(i).toString());
        flag = true;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public static ComponentName startWakefulService(Context context, Intent intent)
    {
        SparseArray sparsearray = mActiveWakeLocks;
        sparsearray;
        JVM INSTR monitorenter ;
        int i = mNextId;
        mNextId = 1 + mNextId;
        if(mNextId <= 0)
            mNextId = 1;
        intent.putExtra("android.support.content.wakelockid", i);
        ComponentName componentname = context.startService(intent);
        if(componentname == null)
        {
            componentname = null;
        } else
        {
            android.os.PowerManager.WakeLock wakelock = ((PowerManager)context.getSystemService("power")).newWakeLock(1, (new StringBuilder()).append("wake:").append(componentname.flattenToShortString()).toString());
            wakelock.setReferenceCounted(false);
            wakelock.acquire(60000L);
            mActiveWakeLocks.put(i, wakelock);
        }
        return componentname;
    }

    private static final String EXTRA_WAKE_LOCK_ID = "android.support.content.wakelockid";
    private static final SparseArray mActiveWakeLocks = new SparseArray();
    private static int mNextId = 1;

}
