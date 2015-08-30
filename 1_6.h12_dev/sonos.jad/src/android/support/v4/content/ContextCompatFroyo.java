// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content;

import android.content.Context;
import java.io.File;

class ContextCompatFroyo
{

    ContextCompatFroyo()
    {
    }

    public static File getExternalCacheDir(Context context)
    {
        return context.getExternalCacheDir();
    }

    public static File getExternalFilesDir(Context context, String s)
    {
        return context.getExternalFilesDir(s);
    }
}
