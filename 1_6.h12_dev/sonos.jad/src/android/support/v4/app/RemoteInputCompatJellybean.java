// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.content.*;
import android.os.Bundle;

class RemoteInputCompatJellybean
{

    RemoteInputCompatJellybean()
    {
    }

    static void addResultsToIntent(RemoteInputCompatBase.RemoteInput aremoteinput[], Intent intent, Bundle bundle)
    {
        Bundle bundle1 = new Bundle();
        int i = aremoteinput.length;
        for(int j = 0; j < i; j++)
        {
            RemoteInputCompatBase.RemoteInput remoteinput = aremoteinput[j];
            Object obj = bundle.get(remoteinput.getResultKey());
            if(obj instanceof CharSequence)
                bundle1.putCharSequence(remoteinput.getResultKey(), (CharSequence)obj);
        }

        Intent intent1 = new Intent();
        intent1.putExtra("android.remoteinput.resultsData", bundle1);
        intent.setClipData(ClipData.newIntent("android.remoteinput.results", intent1));
    }

    static RemoteInputCompatBase.RemoteInput fromBundle(Bundle bundle, RemoteInputCompatBase.RemoteInput.Factory factory)
    {
        return factory.build(bundle.getString("resultKey"), bundle.getCharSequence("label"), bundle.getCharSequenceArray("choices"), bundle.getBoolean("allowFreeFormInput"), bundle.getBundle("extras"));
    }

    static RemoteInputCompatBase.RemoteInput[] fromBundleArray(Bundle abundle[], RemoteInputCompatBase.RemoteInput.Factory factory)
    {
        RemoteInputCompatBase.RemoteInput aremoteinput[];
        if(abundle == null)
        {
            aremoteinput = null;
        } else
        {
            aremoteinput = factory.newArray(abundle.length);
            int i = 0;
            while(i < abundle.length) 
            {
                aremoteinput[i] = fromBundle(abundle[i], factory);
                i++;
            }
        }
        return aremoteinput;
    }

    static Bundle getResultsFromIntent(Intent intent)
    {
        Bundle bundle;
        ClipData clipdata;
        bundle = null;
        clipdata = intent.getClipData();
        if(clipdata != null) goto _L2; else goto _L1
_L1:
        return bundle;
_L2:
        ClipDescription clipdescription = clipdata.getDescription();
        if(clipdescription.hasMimeType("text/vnd.android.intent") && clipdescription.getLabel().equals("android.remoteinput.results"))
            bundle = (Bundle)clipdata.getItemAt(0).getIntent().getExtras().getParcelable("android.remoteinput.resultsData");
        if(true) goto _L1; else goto _L3
_L3:
    }

    static Bundle toBundle(RemoteInputCompatBase.RemoteInput remoteinput)
    {
        Bundle bundle = new Bundle();
        bundle.putString("resultKey", remoteinput.getResultKey());
        bundle.putCharSequence("label", remoteinput.getLabel());
        bundle.putCharSequenceArray("choices", remoteinput.getChoices());
        bundle.putBoolean("allowFreeFormInput", remoteinput.getAllowFreeFormInput());
        bundle.putBundle("extras", remoteinput.getExtras());
        return bundle;
    }

    static Bundle[] toBundleArray(RemoteInputCompatBase.RemoteInput aremoteinput[])
    {
        Bundle abundle[];
        if(aremoteinput == null)
        {
            abundle = null;
        } else
        {
            abundle = new Bundle[aremoteinput.length];
            int i = 0;
            while(i < aremoteinput.length) 
            {
                abundle[i] = toBundle(aremoteinput[i]);
                i++;
            }
        }
        return abundle;
    }

    public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
    private static final String KEY_ALLOW_FREE_FORM_INPUT = "allowFreeFormInput";
    private static final String KEY_CHOICES = "choices";
    private static final String KEY_EXTRAS = "extras";
    private static final String KEY_LABEL = "label";
    private static final String KEY_RESULT_KEY = "resultKey";
    public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";
}
