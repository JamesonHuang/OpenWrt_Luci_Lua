// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.content.Context;
import android.os.Environment;
import java.io.*;
import java.nio.channels.FileChannel;

// Referenced classes of package com.sonos.acr.util:
//            SLog

public class FileUtils
{

    public FileUtils()
    {
    }

    public static void copyDir(File file, File file1)
        throws IOException
    {
        if(!file.equals(file1))
        {
            String s = file.getParent();
            if(!file1.exists() && !file1.mkdirs())
                throw new IOException("Unable to create directory");
            File afile[] = file.listFiles();
            int i = afile.length;
            int j = 0;
            while(j < i) 
            {
                File file2 = afile[j];
                File file3 = new File(file2.getAbsolutePath().replace(s, file1.getParent()));
                if(file2.isDirectory())
                    copyDir(file2, file3);
                else
                    copyFile(file2, file3);
                j++;
            }
        }
    }

    public static void copyFile(File file, File file1)
        throws IOException
    {
        FileChannel filechannel;
        FileChannel filechannel1;
        filechannel = (new FileInputStream(file)).getChannel();
        filechannel1 = (new FileOutputStream(file1)).getChannel();
        filechannel.transferTo(0L, filechannel.size(), filechannel1);
        if(filechannel != null)
            filechannel.close();
        if(filechannel1 != null)
            filechannel1.close();
        return;
        Exception exception;
        exception;
        if(filechannel != null)
            filechannel.close();
        if(filechannel1 != null)
            filechannel1.close();
        throw exception;
    }

    public static boolean deleteDir(File file)
    {
        File afile[];
        int i;
        int j;
        if(!file.isDirectory())
            break MISSING_BLOCK_LABEL_44;
        afile = file.listFiles();
        i = afile.length;
        j = 0;
_L3:
        if(j >= i)
            break MISSING_BLOCK_LABEL_44;
        if(deleteDir(afile[j])) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        j++;
          goto _L3
        flag = file.delete();
          goto _L4
    }

    public static File getNewExternalStorageDir(Context context)
    {
        return context.getExternalFilesDir(null);
    }

    public static File getNewPhoneStorageDir(Context context)
    {
        return context.getFilesDir();
    }

    public static File getOldExternalStorageDir(Context context)
    {
        return new File(Environment.getExternalStorageDirectory(), (new StringBuilder()).append("Android/data/").append(context.getPackageName()).toString());
    }

    public static File getOldPhoneStorageDir(Context context)
    {
        return new File(Environment.getDataDirectory(), (new StringBuilder()).append("data/").append(context.getPackageName()).toString());
    }

    public static byte[] readBytes(InputStream inputstream)
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        byte abyte0[] = new byte[1024];
        try
        {
            do
            {
                int i = inputstream.read(abyte0, 0, 1024);
                if(i < 0)
                    break;
                bytearrayoutputstream.write(abyte0, 0, i);
            } while(true);
        }
        catch(IOException ioexception)
        {
            SLog.e(LOG_TAG, "Error reading File", ioexception);
            ioexception.printStackTrace();
            bytearrayoutputstream = null;
        }
        byte abyte1[];
        try
        {
            inputstream.close();
        }
        catch(IOException ioexception1)
        {
            SLog.e(LOG_TAG, "Error closing File", ioexception1);
            ioexception1.printStackTrace();
        }
        if(bytearrayoutputstream == null)
            abyte1 = null;
        else
            abyte1 = bytearrayoutputstream.toByteArray();
        return abyte1;
    }

    public static final String LOG_TAG = com/sonos/acr/util/FileUtils.getSimpleName();

}
