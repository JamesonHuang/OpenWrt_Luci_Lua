// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling;

import ch.qos.logback.core.rolling.helper.CompressionMode;
import ch.qos.logback.core.rolling.helper.Compressor;
import ch.qos.logback.core.rolling.helper.FileFilterUtil;
import ch.qos.logback.core.rolling.helper.FileNamePattern;
import ch.qos.logback.core.rolling.helper.RenameUtil;
import java.io.File;
import java.util.Date;

// Referenced classes of package ch.qos.logback.core.rolling:
//            RollingPolicyBase, RolloverFailure

public class FixedWindowRollingPolicy extends RollingPolicyBase
{

    public FixedWindowRollingPolicy()
    {
        util = new RenameUtil();
        minIndex = 1;
        maxIndex = 7;
    }

    private String transformFileNamePatternFromInt2Date(String s)
    {
        return FileFilterUtil.afterLastSlash(FileFilterUtil.slashify(s)).replace("%i", "%d{yyyy-MM-dd_HHmm}");
    }

    public String getActiveFileName()
    {
        return getParentsRawFileProperty();
    }

    public int getMaxIndex()
    {
        return maxIndex;
    }

    protected int getMaxWindowSize()
    {
        return MAX_WINDOW_SIZE;
    }

    public int getMinIndex()
    {
        return minIndex;
    }

    public void rollover()
        throws RolloverFailure
    {
        if(maxIndex < 0) goto _L2; else goto _L1
_L1:
        File file = new File(fileNamePattern.convertInt(maxIndex));
        if(file.exists())
            file.delete();
        int i = -1 + maxIndex;
        while(i >= minIndex) 
        {
            String s = fileNamePattern.convertInt(i);
            if((new File(s)).exists())
                util.rename(s, fileNamePattern.convertInt(i + 1));
            else
                addInfo((new StringBuilder()).append("Skipping roll-over for inexistent file ").append(s).toString());
            i--;
        }
        class _cls1
        {

            static final int $SwitchMap$ch$qos$logback$core$rolling$helper$CompressionMode[];

            static 
            {
                $SwitchMap$ch$qos$logback$core$rolling$helper$CompressionMode = new int[CompressionMode.values().length];
                NoSuchFieldError nosuchfielderror2;
                try
                {
                    $SwitchMap$ch$qos$logback$core$rolling$helper$CompressionMode[CompressionMode.NONE.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$ch$qos$logback$core$rolling$helper$CompressionMode[CompressionMode.GZ.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                $SwitchMap$ch$qos$logback$core$rolling$helper$CompressionMode[CompressionMode.ZIP.ordinal()] = 3;
_L2:
                return;
                nosuchfielderror2;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.ch.qos.logback.core.rolling.helper.CompressionMode[compressionMode.ordinal()];
        JVM INSTR tableswitch 1 3: default 164
    //                   1 165
    //                   2 190
    //                   3 216;
           goto _L2 _L3 _L4 _L5
_L2:
        return;
_L3:
        util.rename(getActiveFileName(), fileNamePattern.convertInt(minIndex));
        continue; /* Loop/switch isn't completed */
_L4:
        compressor.compress(getActiveFileName(), fileNamePattern.convertInt(minIndex), null);
        continue; /* Loop/switch isn't completed */
_L5:
        compressor.compress(getActiveFileName(), fileNamePattern.convertInt(minIndex), zipEntryFileNamePattern.convert(new Date()));
        if(true) goto _L2; else goto _L6
_L6:
    }

    public void setMaxIndex(int i)
    {
        maxIndex = i;
    }

    public void setMinIndex(int i)
    {
        minIndex = i;
    }

    public void start()
    {
        util.setContext(context);
        if(fileNamePatternStr != null)
        {
            fileNamePattern = new FileNamePattern(fileNamePatternStr, context);
            determineCompressionMode();
            if(isParentPrudent())
            {
                addError("Prudent mode is not supported with FixedWindowRollingPolicy.");
                addError("See also http://logback.qos.ch/codes.html#tbr_fnp_prudent_unsupported");
                throw new IllegalStateException("Prudent mode is not supported.");
            }
        } else
        {
            addError("The \"FileNamePattern\" property must be set before using FixedWindowRollingPolicy. ");
            addError("See also http://logback.qos.ch/codes.html#tbr_fnp_not_set");
            throw new IllegalStateException("The \"FileNamePattern\" property must be set before using FixedWindowRollingPolicy. See also http://logback.qos.ch/codes.html#tbr_fnp_not_set");
        }
        if(getParentsRawFileProperty() == null)
        {
            addError("The File name property must be set before using this rolling policy.");
            addError("Please refer to http://logback.qos.ch/codes.html#fwrp_parentFileName_not_set");
            throw new IllegalStateException("The \"File\" option must be set.");
        }
        if(maxIndex < minIndex)
        {
            addWarn((new StringBuilder()).append("MaxIndex (").append(maxIndex).append(") cannot be smaller than MinIndex (").append(minIndex).append(").").toString());
            addWarn("Setting maxIndex to equal minIndex.");
            maxIndex = minIndex;
        }
        int i = getMaxWindowSize();
        if(maxIndex - minIndex > i)
        {
            addWarn("Large window sizes are not allowed.");
            maxIndex = i + minIndex;
            addWarn((new StringBuilder()).append("MaxIndex reduced to ").append(maxIndex).toString());
        }
        if(fileNamePattern.getIntegerTokenConverter() == null)
            throw new IllegalStateException((new StringBuilder()).append("FileNamePattern [").append(fileNamePattern.getPattern()).append("] does not contain a valid IntegerToken").toString());
        if(compressionMode == CompressionMode.ZIP)
            zipEntryFileNamePattern = new FileNamePattern(transformFileNamePatternFromInt2Date(fileNamePatternStr), context);
        compressor = new Compressor(compressionMode);
        compressor.setContext(context);
        super.start();
    }

    static final String FNP_NOT_SET = "The \"FileNamePattern\" property must be set before using FixedWindowRollingPolicy. ";
    private static int MAX_WINDOW_SIZE = 0;
    static final String PRUDENT_MODE_UNSUPPORTED = "See also http://logback.qos.ch/codes.html#tbr_fnp_prudent_unsupported";
    static final String SEE_PARENT_FN_NOT_SET = "Please refer to http://logback.qos.ch/codes.html#fwrp_parentFileName_not_set";
    public static final String ZIP_ENTRY_DATE_PATTERN = "yyyy-MM-dd_HHmm";
    Compressor compressor;
    int maxIndex;
    int minIndex;
    RenameUtil util;

    static 
    {
        MAX_WINDOW_SIZE = 20;
    }
}
