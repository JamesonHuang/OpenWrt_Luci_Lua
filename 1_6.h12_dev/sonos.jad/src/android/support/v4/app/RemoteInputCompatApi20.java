// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.RemoteInput;
import android.content.Intent;
import android.os.Bundle;

class RemoteInputCompatApi20
{

    RemoteInputCompatApi20()
    {
    }

    static void addResultsToIntent(RemoteInputCompatBase.RemoteInput aremoteinput[], Intent intent, Bundle bundle)
    {
        RemoteInput.addResultsToIntent(fromCompat(aremoteinput), intent, bundle);
    }

    static RemoteInput[] fromCompat(RemoteInputCompatBase.RemoteInput aremoteinput[])
    {
        RemoteInput aremoteinput1[];
        if(aremoteinput == null)
        {
            aremoteinput1 = null;
        } else
        {
            aremoteinput1 = new RemoteInput[aremoteinput.length];
            int i = 0;
            while(i < aremoteinput.length) 
            {
                RemoteInputCompatBase.RemoteInput remoteinput = aremoteinput[i];
                aremoteinput1[i] = (new android.app.RemoteInput.Builder(remoteinput.getResultKey())).setLabel(remoteinput.getLabel()).setChoices(remoteinput.getChoices()).setAllowFreeFormInput(remoteinput.getAllowFreeFormInput()).addExtras(remoteinput.getExtras()).build();
                i++;
            }
        }
        return aremoteinput1;
    }

    static Bundle getResultsFromIntent(Intent intent)
    {
        return RemoteInput.getResultsFromIntent(intent);
    }

    static RemoteInputCompatBase.RemoteInput[] toCompat(RemoteInput aremoteinput[], RemoteInputCompatBase.RemoteInput.Factory factory)
    {
        RemoteInputCompatBase.RemoteInput aremoteinput1[];
        if(aremoteinput == null)
        {
            aremoteinput1 = null;
        } else
        {
            aremoteinput1 = factory.newArray(aremoteinput.length);
            int i = 0;
            while(i < aremoteinput.length) 
            {
                RemoteInput remoteinput = aremoteinput[i];
                aremoteinput1[i] = factory.build(remoteinput.getResultKey(), remoteinput.getLabel(), remoteinput.getChoices(), remoteinput.getAllowFreeFormInput(), remoteinput.getExtras());
                i++;
            }
        }
        return aremoteinput1;
    }
}
