// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.media;

import android.content.*;
import android.content.pm.*;
import android.os.Handler;
import java.util.*;

// Referenced classes of package android.support.v7.media:
//            RegisteredMediaRouteProvider, MediaRouteProvider

final class RegisteredMediaRouteProviderWatcher
{
    public static interface Callback
    {

        public abstract void addProvider(MediaRouteProvider mediarouteprovider);

        public abstract void removeProvider(MediaRouteProvider mediarouteprovider);
    }


    public RegisteredMediaRouteProviderWatcher(Context context, Callback callback)
    {
        mContext = context;
        mCallback = callback;
        mPackageManager = context.getPackageManager();
    }

    private int findProvider(String s, String s1)
    {
        int i;
        int j;
        i = mProviders.size();
        j = 0;
_L3:
        if(j >= i)
            break MISSING_BLOCK_LABEL_46;
        if(!((RegisteredMediaRouteProvider)mProviders.get(j)).hasComponentName(s, s1)) goto _L2; else goto _L1
_L1:
        return j;
_L2:
        j++;
          goto _L3
        j = -1;
          goto _L1
    }

    private void scanPackages()
    {
        if(mRunning) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i = 0;
        Intent intent = new Intent("android.media.MediaRouteProviderService");
        Iterator iterator = mPackageManager.queryIntentServices(intent, 0).iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ServiceInfo serviceinfo = ((ResolveInfo)iterator.next()).serviceInfo;
            if(serviceinfo != null)
            {
                int k = findProvider(serviceinfo.packageName, serviceinfo.name);
                if(k < 0)
                {
                    RegisteredMediaRouteProvider registeredmediarouteprovider1 = new RegisteredMediaRouteProvider(mContext, new ComponentName(serviceinfo.packageName, serviceinfo.name));
                    registeredmediarouteprovider1.start();
                    ArrayList arraylist = mProviders;
                    int l = i + 1;
                    arraylist.add(i, registeredmediarouteprovider1);
                    mCallback.addProvider(registeredmediarouteprovider1);
                    i = l;
                } else
                if(k >= i)
                {
                    RegisteredMediaRouteProvider registeredmediarouteprovider2 = (RegisteredMediaRouteProvider)mProviders.get(k);
                    registeredmediarouteprovider2.start();
                    registeredmediarouteprovider2.rebindIfDisconnected();
                    ArrayList arraylist1 = mProviders;
                    int i1 = i + 1;
                    Collections.swap(arraylist1, k, i);
                    i = i1;
                }
            }
        } while(true);
        if(i < mProviders.size())
        {
            int j = -1 + mProviders.size();
            while(j >= i) 
            {
                RegisteredMediaRouteProvider registeredmediarouteprovider = (RegisteredMediaRouteProvider)mProviders.get(j);
                mCallback.removeProvider(registeredmediarouteprovider);
                mProviders.remove(registeredmediarouteprovider);
                registeredmediarouteprovider.stop();
                j--;
            }
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void start()
    {
        if(!mRunning)
        {
            mRunning = true;
            IntentFilter intentfilter = new IntentFilter();
            intentfilter.addAction("android.intent.action.PACKAGE_ADDED");
            intentfilter.addAction("android.intent.action.PACKAGE_REMOVED");
            intentfilter.addAction("android.intent.action.PACKAGE_CHANGED");
            intentfilter.addAction("android.intent.action.PACKAGE_REPLACED");
            intentfilter.addAction("android.intent.action.PACKAGE_RESTARTED");
            intentfilter.addDataScheme("package");
            mContext.registerReceiver(mScanPackagesReceiver, intentfilter, null, mHandler);
            mHandler.post(mScanPackagesRunnable);
        }
    }

    public void stop()
    {
        if(mRunning)
        {
            mRunning = false;
            mContext.unregisterReceiver(mScanPackagesReceiver);
            mHandler.removeCallbacks(mScanPackagesRunnable);
            for(int i = -1 + mProviders.size(); i >= 0; i--)
                ((RegisteredMediaRouteProvider)mProviders.get(i)).stop();

        }
    }

    private final Callback mCallback;
    private final Context mContext;
    private final Handler mHandler = new Handler();
    private final PackageManager mPackageManager;
    private final ArrayList mProviders = new ArrayList();
    private boolean mRunning;
    private final BroadcastReceiver mScanPackagesReceiver = new BroadcastReceiver() {

        public void onReceive(Context context1, Intent intent)
        {
            scanPackages();
        }

        final RegisteredMediaRouteProviderWatcher this$0;

            
            {
                this$0 = RegisteredMediaRouteProviderWatcher.this;
                super();
            }
    }
;
    private final Runnable mScanPackagesRunnable = new Runnable() {

        public void run()
        {
            scanPackages();
        }

        final RegisteredMediaRouteProviderWatcher this$0;

            
            {
                this$0 = RegisteredMediaRouteProviderWatcher.this;
                super();
            }
    }
;

}
