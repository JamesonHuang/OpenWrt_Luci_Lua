// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import java.io.File;

// Referenced classes of package android.support.v4.content:
//            ContextCompatApi21, ContextCompatKitKat, ContextCompatFroyo, ContextCompatHoneycomb, 
//            ContextCompatJellybean

public class ContextCompat
{

    public ContextCompat()
    {
    }

    private static transient File buildPath(File file, String as[])
    {
        int i = as.length;
        int j = 0;
        File file1 = file;
        while(j < i) 
        {
            String s = as[j];
            File file2;
            if(file1 == null)
                file2 = new File(s);
            else
            if(s != null)
                file2 = new File(file1, s);
            else
                file2 = file1;
            j++;
            file1 = file2;
        }
        return file1;
    }

    /**
     * @deprecated Method createFilesDir is deprecated
     */

    private static File createFilesDir(File file)
    {
        android/support/v4/content/ContextCompat;
        JVM INSTR monitorenter ;
        if(file.exists() || file.mkdirs()) goto _L2; else goto _L1
_L1:
        boolean flag = file.exists();
        if(!flag) goto _L3; else goto _L2
_L2:
        android/support/v4/content/ContextCompat;
        JVM INSTR monitorexit ;
        return file;
_L3:
        Log.w("ContextCompat", (new StringBuilder()).append("Unable to create files subdir ").append(file.getPath()).toString());
        file = null;
        if(true) goto _L2; else goto _L4
_L4:
        Exception exception;
        exception;
        throw exception;
    }

    public static final Drawable getDrawable(Context context, int i)
    {
        Drawable drawable;
        if(android.os.Build.VERSION.SDK_INT >= 21)
            drawable = ContextCompatApi21.getDrawable(context, i);
        else
            drawable = context.getResources().getDrawable(i);
        return drawable;
    }

    public static File[] getExternalCacheDirs(Context context)
    {
        int i = android.os.Build.VERSION.SDK_INT;
        File afile[];
        if(i >= 19)
        {
            afile = ContextCompatKitKat.getExternalCacheDirs(context);
        } else
        {
            File file1;
            if(i >= 8)
            {
                file1 = ContextCompatFroyo.getExternalCacheDir(context);
            } else
            {
                File file = Environment.getExternalStorageDirectory();
                String as[] = new String[4];
                as[0] = "Android";
                as[1] = "data";
                as[2] = context.getPackageName();
                as[3] = "cache";
                file1 = buildPath(file, as);
            }
            afile = new File[1];
            afile[0] = file1;
        }
        return afile;
    }

    public static File[] getExternalFilesDirs(Context context, String s)
    {
        int i = android.os.Build.VERSION.SDK_INT;
        File afile[];
        if(i >= 19)
        {
            afile = ContextCompatKitKat.getExternalFilesDirs(context, s);
        } else
        {
            File file1;
            if(i >= 8)
            {
                file1 = ContextCompatFroyo.getExternalFilesDir(context, s);
            } else
            {
                File file = Environment.getExternalStorageDirectory();
                String as[] = new String[5];
                as[0] = "Android";
                as[1] = "data";
                as[2] = context.getPackageName();
                as[3] = "files";
                as[4] = s;
                file1 = buildPath(file, as);
            }
            afile = new File[1];
            afile[0] = file1;
        }
        return afile;
    }

    public static File[] getObbDirs(Context context)
    {
        int i = android.os.Build.VERSION.SDK_INT;
        File afile[];
        if(i >= 19)
        {
            afile = ContextCompatKitKat.getObbDirs(context);
        } else
        {
            File file1;
            if(i >= 11)
            {
                file1 = ContextCompatHoneycomb.getObbDir(context);
            } else
            {
                File file = Environment.getExternalStorageDirectory();
                String as[] = new String[3];
                as[0] = "Android";
                as[1] = "obb";
                as[2] = context.getPackageName();
                file1 = buildPath(file, as);
            }
            afile = new File[1];
            afile[0] = file1;
        }
        return afile;
    }

    public static boolean startActivities(Context context, Intent aintent[])
    {
        return startActivities(context, aintent, null);
    }

    public static boolean startActivities(Context context, Intent aintent[], Bundle bundle)
    {
        boolean flag = true;
        int i = android.os.Build.VERSION.SDK_INT;
        if(i >= 16)
            ContextCompatJellybean.startActivities(context, aintent, bundle);
        else
        if(i >= 11)
            ContextCompatHoneycomb.startActivities(context, aintent);
        else
            flag = false;
        return flag;
    }

    public final File getCodeCacheDir(Context context)
    {
        File file;
        if(android.os.Build.VERSION.SDK_INT >= 21)
            file = ContextCompatApi21.getCodeCacheDir(context);
        else
            file = createFilesDir(new File(context.getApplicationInfo().dataDir, "code_cache"));
        return file;
    }

    public final File getNoBackupFilesDir(Context context)
    {
        File file;
        if(android.os.Build.VERSION.SDK_INT >= 21)
            file = ContextCompatApi21.getNoBackupFilesDir(context);
        else
            file = createFilesDir(new File(context.getApplicationInfo().dataDir, "no_backup"));
        return file;
    }

    private static final String DIR_ANDROID = "Android";
    private static final String DIR_CACHE = "cache";
    private static final String DIR_DATA = "data";
    private static final String DIR_FILES = "files";
    private static final String DIR_OBB = "obb";
    private static final String TAG = "ContextCompat";
}
