// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling;

import ch.qos.logback.core.util.FileSize;
import ch.qos.logback.core.util.InvocationGate;
import java.io.File;

// Referenced classes of package ch.qos.logback.core.rolling:
//            TriggeringPolicyBase

public class SizeBasedTriggeringPolicy extends TriggeringPolicyBase
{

    public SizeBasedTriggeringPolicy()
    {
        maxFileSizeAsString = Long.toString(0xa00000L);
        invocationGate = new InvocationGate();
    }

    public SizeBasedTriggeringPolicy(String s)
    {
        maxFileSizeAsString = Long.toString(0xa00000L);
        invocationGate = new InvocationGate();
        setMaxFileSize(s);
    }

    public String getMaxFileSize()
    {
        return maxFileSizeAsString;
    }

    public boolean isTriggeringEvent(File file, Object obj)
    {
        boolean flag = false;
        if(!invocationGate.skipFurtherWork()) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        long l = System.currentTimeMillis();
        invocationGate.updateMaskIfNecessary(l);
        if(file.length() >= maxFileSize.getSize())
            flag = true;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void setMaxFileSize(String s)
    {
        maxFileSizeAsString = s;
        maxFileSize = FileSize.valueOf(s);
    }

    long toFileSize(String s)
    {
        long l = 0xa00000L;
        if(s != null) goto _L2; else goto _L1
_L1:
        return l;
_L2:
        String s1;
        long l1;
        int i;
        s1 = s.trim().toUpperCase();
        l1 = 1L;
        i = s1.indexOf("KB");
        if(i == -1) goto _L4; else goto _L3
_L3:
        l1 = 1024L;
        s1 = s1.substring(0, i);
_L6:
        if(s1 == null)
            continue; /* Loop/switch isn't completed */
        long l2 = Long.valueOf(s1).longValue();
        l = l2 * l1;
        continue; /* Loop/switch isn't completed */
_L4:
        int j = s1.indexOf("MB");
        if(j != -1)
        {
            l1 = 0x100000L;
            s1 = s1.substring(0, j);
        } else
        {
            int k = s1.indexOf("GB");
            if(k != -1)
            {
                l1 = 0x40000000L;
                s1 = s1.substring(0, k);
            }
        }
        if(true) goto _L6; else goto _L5
_L5:
        NumberFormatException numberformatexception;
        numberformatexception;
        addError((new StringBuilder()).append("[").append(s1).append("] is not in proper int format. Please refer to ").append("http://logback.qos.ch/codes.html#sbtp_size_format").toString());
        addError((new StringBuilder()).append("[").append(s).append("] not in expected format.").toString(), numberformatexception);
        if(true) goto _L1; else goto _L7
_L7:
    }

    public static final long DEFAULT_MAX_FILE_SIZE = 0xa00000L;
    public static final String SEE_SIZE_FORMAT = "http://logback.qos.ch/codes.html#sbtp_size_format";
    private InvocationGate invocationGate;
    FileSize maxFileSize;
    String maxFileSizeAsString;
}
