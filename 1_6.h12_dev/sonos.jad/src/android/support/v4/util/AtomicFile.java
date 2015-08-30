// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.util;

import android.util.Log;
import java.io.*;

public class AtomicFile
{

    public AtomicFile(File file)
    {
        mBaseName = file;
        mBackupName = new File((new StringBuilder()).append(file.getPath()).append(".bak").toString());
    }

    static boolean sync(FileOutputStream fileoutputstream)
    {
        if(fileoutputstream == null)
            break MISSING_BLOCK_LABEL_11;
        fileoutputstream.getFD().sync();
        boolean flag = true;
_L2:
        return flag;
        IOException ioexception;
        ioexception;
        flag = false;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void delete()
    {
        mBaseName.delete();
        mBackupName.delete();
    }

    public void failWrite(FileOutputStream fileoutputstream)
    {
        if(fileoutputstream == null)
            break MISSING_BLOCK_LABEL_33;
        sync(fileoutputstream);
        fileoutputstream.close();
        mBaseName.delete();
        mBackupName.renameTo(mBaseName);
_L1:
        return;
        IOException ioexception;
        ioexception;
        Log.w("AtomicFile", "failWrite: Got exception:", ioexception);
          goto _L1
    }

    public void finishWrite(FileOutputStream fileoutputstream)
    {
        if(fileoutputstream == null)
            break MISSING_BLOCK_LABEL_21;
        sync(fileoutputstream);
        fileoutputstream.close();
        mBackupName.delete();
_L1:
        return;
        IOException ioexception;
        ioexception;
        Log.w("AtomicFile", "finishWrite: Got exception:", ioexception);
          goto _L1
    }

    public File getBaseFile()
    {
        return mBaseName;
    }

    public FileInputStream openRead()
        throws FileNotFoundException
    {
        if(mBackupName.exists())
        {
            mBaseName.delete();
            mBackupName.renameTo(mBaseName);
        }
        return new FileInputStream(mBaseName);
    }

    public byte[] readFully()
        throws IOException
    {
        FileInputStream fileinputstream;
        int i;
        fileinputstream = openRead();
        i = 0;
        byte abyte0[] = new byte[fileinputstream.available()];
_L2:
        int j = fileinputstream.read(abyte0, i, abyte0.length - i);
        if(j <= 0)
        {
            fileinputstream.close();
            return abyte0;
        }
        i += j;
        int k = fileinputstream.available();
        if(k <= abyte0.length - i) goto _L2; else goto _L1
_L1:
        byte abyte1[];
        abyte1 = new byte[i + k];
        System.arraycopy(abyte0, 0, abyte1, 0, i);
        abyte0 = abyte1;
          goto _L2
        Exception exception;
        exception;
        fileinputstream.close();
        throw exception;
    }

    public FileOutputStream startWrite()
        throws IOException
    {
        FileOutputStream fileoutputstream;
        if(mBaseName.exists())
            if(!mBackupName.exists())
            {
                if(!mBaseName.renameTo(mBackupName))
                    Log.w("AtomicFile", (new StringBuilder()).append("Couldn't rename file ").append(mBaseName).append(" to backup file ").append(mBackupName).toString());
            } else
            {
                mBaseName.delete();
            }
        try
        {
            fileoutputstream = new FileOutputStream(mBaseName);
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            if(!mBaseName.getParentFile().mkdir())
                throw new IOException((new StringBuilder()).append("Couldn't create directory ").append(mBaseName).toString());
            try
            {
                fileoutputstream = new FileOutputStream(mBaseName);
            }
            catch(FileNotFoundException filenotfoundexception1)
            {
                throw new IOException((new StringBuilder()).append("Couldn't create ").append(mBaseName).toString());
            }
        }
        return fileoutputstream;
    }

    private final File mBackupName;
    private final File mBaseName;
}
