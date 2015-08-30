// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.pattern;

import ch.qos.logback.core.pattern.*;

// Referenced classes of package ch.qos.logback.classic.pattern:
//            ThrowableHandlingConverter, ExtendedThrowableProxyConverter

public class EnsureExceptionHandling
    implements PostCompileProcessor
{

    public EnsureExceptionHandling()
    {
    }

    public boolean chainHandlesThrowable(Converter converter)
    {
_L3:
        if(converter == null)
            break MISSING_BLOCK_LABEL_23;
        if(!(converter instanceof ThrowableHandlingConverter)) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        converter = converter.getNext();
          goto _L3
        flag = false;
          goto _L4
    }

    public void process(Converter converter)
    {
        if(converter == null)
            throw new IllegalArgumentException("cannot process empty chain");
        if(!chainHandlesThrowable(converter))
            ConverterUtil.findTail(converter).setNext(new ExtendedThrowableProxyConverter());
    }
}
