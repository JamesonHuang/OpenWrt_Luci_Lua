// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content;

import android.content.ComponentName;
import android.content.Intent;

// Referenced classes of package android.support.v4.content:
//            IntentCompatIcsMr1, IntentCompatHoneycomb

public class IntentCompat
{
    static class IntentCompatImplIcsMr1 extends IntentCompatImplHC
    {

        public Intent makeMainSelectorActivity(String s, String s1)
        {
            return IntentCompatIcsMr1.makeMainSelectorActivity(s, s1);
        }

        IntentCompatImplIcsMr1()
        {
        }
    }

    static class IntentCompatImplHC extends IntentCompatImplBase
    {

        public Intent makeMainActivity(ComponentName componentname)
        {
            return IntentCompatHoneycomb.makeMainActivity(componentname);
        }

        public Intent makeRestartActivityTask(ComponentName componentname)
        {
            return IntentCompatHoneycomb.makeRestartActivityTask(componentname);
        }

        IntentCompatImplHC()
        {
        }
    }

    static class IntentCompatImplBase
        implements IntentCompatImpl
    {

        public Intent makeMainActivity(ComponentName componentname)
        {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setComponent(componentname);
            intent.addCategory("android.intent.category.LAUNCHER");
            return intent;
        }

        public Intent makeMainSelectorActivity(String s, String s1)
        {
            Intent intent = new Intent(s);
            intent.addCategory(s1);
            return intent;
        }

        public Intent makeRestartActivityTask(ComponentName componentname)
        {
            Intent intent = makeMainActivity(componentname);
            intent.addFlags(0x10008000);
            return intent;
        }

        IntentCompatImplBase()
        {
        }
    }

    static interface IntentCompatImpl
    {

        public abstract Intent makeMainActivity(ComponentName componentname);

        public abstract Intent makeMainSelectorActivity(String s, String s1);

        public abstract Intent makeRestartActivityTask(ComponentName componentname);
    }


    private IntentCompat()
    {
    }

    public static Intent makeMainActivity(ComponentName componentname)
    {
        return IMPL.makeMainActivity(componentname);
    }

    public static Intent makeMainSelectorActivity(String s, String s1)
    {
        return IMPL.makeMainSelectorActivity(s, s1);
    }

    public static Intent makeRestartActivityTask(ComponentName componentname)
    {
        return IMPL.makeRestartActivityTask(componentname);
    }

    public static final String ACTION_EXTERNAL_APPLICATIONS_AVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE";
    public static final String ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE";
    public static final String EXTRA_CHANGED_PACKAGE_LIST = "android.intent.extra.changed_package_list";
    public static final String EXTRA_CHANGED_UID_LIST = "android.intent.extra.changed_uid_list";
    public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";
    public static final int FLAG_ACTIVITY_CLEAR_TASK = 32768;
    public static final int FLAG_ACTIVITY_TASK_ON_HOME = 16384;
    private static final IntentCompatImpl IMPL;

    static 
    {
        int i = android.os.Build.VERSION.SDK_INT;
        if(i >= 15)
            IMPL = new IntentCompatImplIcsMr1();
        else
        if(i >= 11)
            IMPL = new IntentCompatImplHC();
        else
            IMPL = new IntentCompatImplBase();
    }
}
