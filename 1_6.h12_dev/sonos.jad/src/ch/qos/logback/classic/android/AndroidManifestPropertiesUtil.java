// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.android;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.android.CommonPathUtil;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.status.StatusManager;
import ch.qos.logback.core.status.WarnStatus;
import ch.qos.logback.core.util.Loader;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

// Referenced classes of package ch.qos.logback.classic.android:
//            ASaxEventRecorder

public class AndroidManifestPropertiesUtil
{

    public AndroidManifestPropertiesUtil()
    {
    }

    public static void setAndroidProperties(Context context)
        throws JoranException
    {
        ASaxEventRecorder asaxeventrecorder;
        StatusManager statusmanager;
        InputStream inputstream;
        asaxeventrecorder = new ASaxEventRecorder();
        String as[] = new String[1];
        as[0] = "-";
        asaxeventrecorder.setFilter(as);
        asaxeventrecorder.setAttributeWatch("manifest");
        statusmanager = context.getStatusManager();
        inputstream = Loader.getClassLoaderOfObject(context).getResourceAsStream("AndroidManifest.xml");
        if(inputstream != null) goto _L2; else goto _L1
_L1:
        statusmanager.add(new WarnStatus("Could not find AndroidManifest.xml", context));
_L6:
        return;
_L2:
        asaxeventrecorder.recordEvents(inputstream);
        Exception exception;
        Map map;
        Iterator iterator;
        String s;
        String s1;
        try
        {
            inputstream.close();
        }
        catch(IOException ioexception1) { }
        context.putProperty("EXT_DIR", CommonPathUtil.getMountedExternalStorageDirectoryPath());
        map = asaxeventrecorder.getAttributeWatchValues();
        iterator = map.keySet().iterator();
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        s1 = (String)iterator.next();
        if(s1.equals("android:versionName"))
            context.putProperty("VERSION_NAME", (String)map.get(s1));
        else
        if(s1.equals("android:versionCode"))
            context.putProperty("VERSION_CODE", (String)map.get(s1));
        else
        if(s1.equals("package"))
            context.putProperty("PACKAGE_NAME", (String)map.get(s1));
        continue; /* Loop/switch isn't completed */
        exception;
        try
        {
            inputstream.close();
        }
        catch(IOException ioexception) { }
        throw exception;
        if(true) goto _L4; else goto _L3
_L4:
        break MISSING_BLOCK_LABEL_111;
_L3:
        s = (String)map.get("package");
        if(s != null && s.length() > 0)
            context.putProperty("DATA_DIR", CommonPathUtil.getFilesDirectoryPath(s));
        else
            statusmanager.add(new WarnStatus("Package name not found. Some properties cannot be set.", context));
        if(true) goto _L6; else goto _L5
_L5:
    }
}
