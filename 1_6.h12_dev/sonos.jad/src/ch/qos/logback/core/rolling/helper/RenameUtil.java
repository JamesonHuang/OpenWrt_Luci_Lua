// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling.helper;

import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.RolloverFailure;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.util.EnvUtil;
import ch.qos.logback.core.util.FileUtil;
import java.io.File;

// Referenced classes of package ch.qos.logback.core.rolling.helper:
//            FileStoreUtil

public class RenameUtil extends ContextAwareBase
{

    public RenameUtil()
    {
    }

    boolean areOnDifferentVolumes(File file, File file1)
        throws RolloverFailure
    {
        boolean flag = false;
        if(EnvUtil.isJDK7OrHigher()) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        File file2 = file1.getParentFile();
        boolean flag1 = FileStoreUtil.areOnSameFileStore(file, file2);
        if(!flag1)
            flag = true;
        continue; /* Loop/switch isn't completed */
        RolloverFailure rolloverfailure;
        rolloverfailure;
        addWarn("Error while checking file store equality", rolloverfailure);
        if(true) goto _L1; else goto _L3
_L3:
    }

    void createMissingTargetDirsIfNecessary(File file)
        throws RolloverFailure
    {
        if(FileUtil.isParentDirectoryCreationRequired(file) && !FileUtil.createMissingParentDirectories(file))
            throw new RolloverFailure((new StringBuilder()).append("Failed to create parent directories for [").append(file.getAbsolutePath()).append("]").toString());
        else
            return;
    }

    public void rename(String s, String s1)
        throws RolloverFailure
    {
        if(!s.equals(s1)) goto _L2; else goto _L1
_L1:
        addWarn((new StringBuilder()).append("Source and target files are the same [").append(s).append("]. Skipping.").toString());
_L4:
        return;
_L2:
        File file = new File(s);
        if(!file.exists())
            break; /* Loop/switch isn't completed */
        File file1 = new File(s1);
        createMissingTargetDirsIfNecessary(file1);
        addInfo((new StringBuilder()).append("Renaming file [").append(file).append("] to [").append(file1).append("]").toString());
        if(!file.renameTo(file1))
        {
            addWarn((new StringBuilder()).append("Failed to rename file [").append(file).append("] as [").append(file1).append("].").toString());
            if(areOnDifferentVolumes(file, file1))
            {
                addWarn((new StringBuilder()).append("Detected different file systems for source [").append(s).append("] and target [").append(s1).append("]. Attempting rename by copying.").toString());
                renameByCopying(s, s1);
            } else
            {
                addWarn((new StringBuilder()).append("Please consider leaving the [file] option of ").append(ch/qos/logback/core/rolling/RollingFileAppender.getSimpleName()).append(" empty.").toString());
                addWarn((new StringBuilder()).append("See also ").append(RENAMING_ERROR_URL).toString());
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
        throw new RolloverFailure((new StringBuilder()).append("File [").append(s).append("] does not exist.").toString());
    }

    public void renameByCopying(String s, String s1)
        throws RolloverFailure
    {
        (new FileUtil(getContext())).copy(s, s1);
        if(!(new File(s)).delete())
            addWarn((new StringBuilder()).append("Could not delete ").append(s).toString());
    }

    public String toString()
    {
        return "c.q.l.co.rolling.helper.RenameUtil";
    }

    static String RENAMING_ERROR_URL = "http://logback.qos.ch/codes.html#renamingError";

}
