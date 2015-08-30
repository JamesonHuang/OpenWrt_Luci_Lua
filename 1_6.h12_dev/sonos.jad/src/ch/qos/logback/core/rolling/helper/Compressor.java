// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling.helper;

import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.status.ErrorStatus;
import ch.qos.logback.core.status.WarnStatus;
import ch.qos.logback.core.util.FileUtil;
import java.io.*;
import java.util.zip.*;

// Referenced classes of package ch.qos.logback.core.rolling.helper:
//            CompressionMode

public class Compressor extends ContextAwareBase
{

    public Compressor(CompressionMode compressionmode)
    {
        compressionMode = compressionmode;
    }

    public static String computeFileNameStr_WCS(String s, CompressionMode compressionmode)
    {
        int i = s.length();
        class _cls1
        {

            static final int $SwitchMap$ch$qos$logback$core$rolling$helper$CompressionMode[];

            static 
            {
                $SwitchMap$ch$qos$logback$core$rolling$helper$CompressionMode = new int[CompressionMode.values().length];
                NoSuchFieldError nosuchfielderror2;
                try
                {
                    $SwitchMap$ch$qos$logback$core$rolling$helper$CompressionMode[CompressionMode.GZ.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$ch$qos$logback$core$rolling$helper$CompressionMode[CompressionMode.ZIP.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                $SwitchMap$ch$qos$logback$core$rolling$helper$CompressionMode[CompressionMode.NONE.ordinal()] = 3;
_L2:
                return;
                nosuchfielderror2;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.ch.qos.logback.core.rolling.helper.CompressionMode[compressionmode.ordinal()];
        JVM INSTR tableswitch 1 3: default 40
    //                   1 50
    //                   2 70
    //                   3 68;
           goto _L1 _L2 _L3 _L4
_L1:
        throw new IllegalStateException("Execution should not reach this point");
_L2:
        if(s.endsWith(".gz"))
            s = s.substring(0, i - 3);
_L4:
        return s;
_L3:
        if(s.endsWith(".zip"))
            s = s.substring(0, i - 4);
        if(true) goto _L4; else goto _L5
_L5:
    }

    private void gzCompress(String s, String s1)
    {
        Object obj;
        File file;
        File file1;
        obj = null;
        file = new File(s);
        if(!file.exists())
        {
            addStatus(new WarnStatus((new StringBuilder()).append("The file to compress named [").append(s).append("] does not exist.").toString(), this));
        } else
        {
label0:
            {
                if(!s1.endsWith(".gz"))
                    s1 = (new StringBuilder()).append(s1).append(".gz").toString();
                file1 = new File(s1);
                if(!file1.exists())
                    break label0;
                addWarn((new StringBuilder()).append("The target compressed file named [").append(s1).append("] exist already. Aborting file compression.").toString());
            }
        }
_L2:
        return;
        addInfo((new StringBuilder()).append("GZ compressing [").append(file).append("] as [").append(file1).append("]").toString());
        createMissingTargetDirsIfNecessary(file1);
        Object obj1 = new BufferedInputStream(new FileInputStream(s));
        GZIPOutputStream gzipoutputstream = new GZIPOutputStream(new FileOutputStream(s1));
        byte abyte0[] = new byte[8192];
        do
        {
            int i = ((BufferedInputStream) (obj1)).read(abyte0);
            if(i == -1)
                break;
            gzipoutputstream.write(abyte0, 0, i);
        } while(true);
          goto _L1
        Exception exception1;
        exception1;
        obj = obj1;
_L5:
        addStatus(new ErrorStatus((new StringBuilder()).append("Error occurred while compressing [").append(s).append("] into [").append(s1).append("].").toString(), this, exception1));
        Exception exception;
        IOException ioexception4;
        IOException ioexception5;
        if(obj != null)
            try
            {
                ((BufferedInputStream) (obj)).close();
            }
            catch(IOException ioexception3) { }
        if(gzipoutputstream != null)
            try
            {
                gzipoutputstream.close();
            }
            catch(IOException ioexception2) { }
          goto _L2
_L1:
        ((BufferedInputStream) (obj1)).close();
        gzipoutputstream.close();
        if(!file.delete())
            addStatus(new WarnStatus((new StringBuilder()).append("Could not delete [").append(s).append("].").toString(), this));
        if(false)
            try
            {
                null.close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException ioexception5) { }
        if(false)
            try
            {
                null.close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException ioexception4) { }
          goto _L2
        exception;
        obj1 = null;
_L4:
        if(obj1 != null)
            try
            {
                ((BufferedInputStream) (obj1)).close();
            }
            catch(IOException ioexception1) { }
        if(obj != null)
            try
            {
                ((GZIPOutputStream) (obj)).close();
            }
            catch(IOException ioexception) { }
        throw exception;
        exception;
        continue; /* Loop/switch isn't completed */
        exception;
        obj = gzipoutputstream;
        continue; /* Loop/switch isn't completed */
        exception;
        obj1 = null;
        obj = gzipoutputstream;
        continue; /* Loop/switch isn't completed */
        exception;
        obj1 = obj;
        obj = gzipoutputstream;
        if(true) goto _L4; else goto _L3
_L3:
        exception1;
        gzipoutputstream = null;
          goto _L5
        exception1;
        gzipoutputstream = null;
        obj = obj1;
          goto _L5
        exception1;
          goto _L5
    }

    private void zipCompress(String s, String s1, String s2)
    {
        BufferedInputStream bufferedinputstream;
        File file;
        File file1;
        bufferedinputstream = null;
        file = new File(s);
        if(!file.exists())
            addStatus(new WarnStatus((new StringBuilder()).append("The file to compress named [").append(s).append("] does not exist.").toString(), this));
        else
        if(s2 == null)
        {
            addStatus(new WarnStatus("The innerEntryName parameter cannot be null", this));
        } else
        {
label0:
            {
                if(!s1.endsWith(".zip"))
                    s1 = (new StringBuilder()).append(s1).append(".zip").toString();
                file1 = new File(s1);
                if(!file1.exists())
                    break label0;
                addStatus(new WarnStatus((new StringBuilder()).append("The target compressed file named [").append(s1).append("] exist already.").toString(), this));
            }
        }
_L2:
        return;
        addInfo((new StringBuilder()).append("ZIP compressing [").append(file).append("] as [").append(file1).append("]").toString());
        createMissingTargetDirsIfNecessary(file1);
        BufferedInputStream bufferedinputstream1 = new BufferedInputStream(new FileInputStream(s));
        ZipOutputStream zipoutputstream = new ZipOutputStream(new FileOutputStream(s1));
        zipoutputstream.putNextEntry(computeZipEntry(s2));
        byte abyte0[] = new byte[8192];
        do
        {
            int i = bufferedinputstream1.read(abyte0);
            if(i == -1)
                break;
            zipoutputstream.write(abyte0, 0, i);
        } while(true);
          goto _L1
        Exception exception1;
        exception1;
        bufferedinputstream = bufferedinputstream1;
_L5:
        addStatus(new ErrorStatus((new StringBuilder()).append("Error occurred while compressing [").append(s).append("] into [").append(s1).append("].").toString(), this, exception1));
        Exception exception;
        IOException ioexception4;
        IOException ioexception5;
        if(bufferedinputstream != null)
            try
            {
                bufferedinputstream.close();
            }
            catch(IOException ioexception3) { }
        if(zipoutputstream != null)
            try
            {
                zipoutputstream.close();
            }
            catch(IOException ioexception2) { }
          goto _L2
_L1:
        bufferedinputstream1.close();
        zipoutputstream.close();
        if(!file.delete())
            addStatus(new WarnStatus((new StringBuilder()).append("Could not delete [").append(s).append("].").toString(), this));
        if(false)
            try
            {
                null.close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException ioexception5) { }
        if(false)
            try
            {
                null.close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException ioexception4) { }
          goto _L2
        exception;
        zipoutputstream = null;
_L4:
        if(bufferedinputstream != null)
            try
            {
                bufferedinputstream.close();
            }
            catch(IOException ioexception1) { }
        if(zipoutputstream != null)
            try
            {
                zipoutputstream.close();
            }
            catch(IOException ioexception) { }
        throw exception;
        exception;
        zipoutputstream = null;
        bufferedinputstream = bufferedinputstream1;
        continue; /* Loop/switch isn't completed */
        exception;
        bufferedinputstream = bufferedinputstream1;
        continue; /* Loop/switch isn't completed */
        exception;
        if(true) goto _L4; else goto _L3
_L3:
        exception1;
        zipoutputstream = null;
          goto _L5
        exception1;
        zipoutputstream = null;
        bufferedinputstream = bufferedinputstream1;
          goto _L5
        exception1;
          goto _L5
    }

    public void compress(String s, String s1, String s2)
    {
        _cls1..SwitchMap.ch.qos.logback.core.rolling.helper.CompressionMode[compressionMode.ordinal()];
        JVM INSTR tableswitch 1 3: default 36
    //                   1 37
    //                   2 46
    //                   3 56;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L2:
        gzCompress(s, s1);
        continue; /* Loop/switch isn't completed */
_L3:
        zipCompress(s, s1, s2);
        if(true) goto _L1; else goto _L4
_L4:
        throw new UnsupportedOperationException("compress method called in NONE compression mode");
    }

    ZipEntry computeZipEntry(File file)
    {
        return computeZipEntry(file.getName());
    }

    ZipEntry computeZipEntry(String s)
    {
        return new ZipEntry(computeFileNameStr_WCS(s, compressionMode));
    }

    void createMissingTargetDirsIfNecessary(File file)
    {
        if(FileUtil.isParentDirectoryCreationRequired(file))
            if(!FileUtil.createMissingParentDirectories(file))
                addError((new StringBuilder()).append("Failed to create parent directories for [").append(file.getAbsolutePath()).append("]").toString());
            else
                addInfo((new StringBuilder()).append("Created missing parent directories for [").append(file.getAbsolutePath()).append("]").toString());
    }

    public String toString()
    {
        return getClass().getName();
    }

    static final int BUFFER_SIZE = 8192;
    final CompressionMode compressionMode;
}
