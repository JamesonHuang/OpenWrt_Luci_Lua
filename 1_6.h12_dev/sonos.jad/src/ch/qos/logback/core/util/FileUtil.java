// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.util;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.rolling.RolloverFailure;
import ch.qos.logback.core.spi.ContextAwareBase;
import java.io.*;
import java.net.*;

// Referenced classes of package ch.qos.logback.core.util:
//            OptionHelper

public class FileUtil extends ContextAwareBase
{

    public FileUtil(Context context)
    {
        setContext(context);
    }

    public static boolean createMissingParentDirectories(File file)
    {
        File file1 = file.getParentFile();
        if(file1 == null)
            throw new IllegalStateException((new StringBuilder()).append(file).append(" should not have a null parent").toString());
        if(file1.exists())
            throw new IllegalStateException((new StringBuilder()).append(file).append(" should not have existing parent directory").toString());
        else
            return file1.mkdirs();
    }

    public static URL fileToURL(File file)
    {
        URL url;
        try
        {
            url = file.toURI().toURL();
        }
        catch(MalformedURLException malformedurlexception)
        {
            throw new RuntimeException((new StringBuilder()).append("Unexpected exception on file [").append(file).append("]").toString(), malformedurlexception);
        }
        return url;
    }

    public static boolean isParentDirectoryCreationRequired(File file)
    {
        File file1 = file.getParentFile();
        boolean flag;
        if(file1 != null && !file1.exists())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static String prefixRelativePath(String s, String s1)
    {
        if(s != null && !OptionHelper.isEmpty(s.trim()) && !(new File(s1)).isAbsolute())
            s1 = (new StringBuilder()).append(s).append("/").append(s1).toString();
        return s1;
    }

    public void copy(String s, String s1)
        throws RolloverFailure
    {
        BufferedInputStream bufferedinputstream = null;
        BufferedInputStream bufferedinputstream1;
        BufferedOutputStream bufferedoutputstream;
        Exception exception;
        IOException ioexception2;
        byte abyte0[];
        try
        {
            bufferedinputstream1 = new BufferedInputStream(new FileInputStream(s));
        }
        // Misplaced declaration of an exception variable
        catch(IOException ioexception2)
        {
            bufferedoutputstream = null;
            continue; /* Loop/switch isn't completed */
        }
        try
        {
            bufferedoutputstream = new BufferedOutputStream(new FileOutputStream(s1));
        }
        // Misplaced declaration of an exception variable
        catch(IOException ioexception2)
        {
            bufferedoutputstream = null;
            bufferedinputstream = bufferedinputstream1;
            continue; /* Loop/switch isn't completed */
        }
        abyte0 = new byte[32768];
        do
        {
            int i = bufferedinputstream1.read(abyte0);
            if(i == -1)
                break;
            bufferedoutputstream.write(abyte0, 0, i);
        } while(true);
          goto _L1
        ioexception2;
        bufferedinputstream = bufferedinputstream1;
_L7:
        String s2 = (new StringBuilder()).append("Failed to copy [").append(s).append("] to [").append(s1).append("]").toString();
        addError(s2, ioexception2);
        throw new RolloverFailure(s2);
        exception;
_L5:
        IOException ioexception3;
        IOException ioexception4;
        if(bufferedinputstream != null)
            try
            {
                bufferedinputstream.close();
            }
            catch(IOException ioexception1) { }
        if(bufferedoutputstream != null)
            try
            {
                bufferedoutputstream.close();
            }
            catch(IOException ioexception) { }
        throw exception;
_L1:
        bufferedinputstream1.close();
        bufferedoutputstream.close();
        if(false)
            try
            {
                null.close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException ioexception4) { }
        if(true)
            break MISSING_BLOCK_LABEL_178;
        null.close();
_L3:
        return;
        ioexception3;
        if(true) goto _L3; else goto _L2
_L2:
        exception;
        bufferedoutputstream = null;
        continue; /* Loop/switch isn't completed */
        exception;
        bufferedoutputstream = null;
        bufferedinputstream = bufferedinputstream1;
        continue; /* Loop/switch isn't completed */
        exception;
        bufferedinputstream = bufferedinputstream1;
        if(true) goto _L5; else goto _L4
_L4:
        break MISSING_BLOCK_LABEL_19;
        ioexception2;
        if(true) goto _L7; else goto _L6
_L6:
    }

    static final int BUF_SIZE = 32768;
}
