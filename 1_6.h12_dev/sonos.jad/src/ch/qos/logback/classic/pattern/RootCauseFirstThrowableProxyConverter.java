// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.CoreConstants;

// Referenced classes of package ch.qos.logback.classic.pattern:
//            ExtendedThrowableProxyConverter

public class RootCauseFirstThrowableProxyConverter extends ExtendedThrowableProxyConverter
{

    public RootCauseFirstThrowableProxyConverter()
    {
    }

    protected void recursiveAppendRootCauseFirst(StringBuilder stringbuilder, String s, int i, IThrowableProxy ithrowableproxy)
    {
        if(ithrowableproxy.getCause() != null)
        {
            recursiveAppendRootCauseFirst(stringbuilder, s, i, ithrowableproxy.getCause());
            s = null;
        }
        ThrowableProxyUtil.indent(stringbuilder, i - 1);
        if(s != null)
            stringbuilder.append(s);
        ThrowableProxyUtil.subjoinFirstLineRootCauseFirst(stringbuilder, ithrowableproxy);
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        subjoinSTEPArray(stringbuilder, i, ithrowableproxy);
        IThrowableProxy aithrowableproxy[] = ithrowableproxy.getSuppressed();
        if(aithrowableproxy != null)
        {
            int j = aithrowableproxy.length;
            for(int k = 0; k < j; k++)
            {
                IThrowableProxy ithrowableproxy1 = aithrowableproxy[k];
                recursiveAppendRootCauseFirst(stringbuilder, "Suppressed: ", i + 1, ithrowableproxy1);
            }

        }
    }

    protected String throwableProxyToString(IThrowableProxy ithrowableproxy)
    {
        StringBuilder stringbuilder = new StringBuilder(2048);
        recursiveAppendRootCauseFirst(stringbuilder, null, 1, ithrowableproxy);
        return stringbuilder.toString();
    }
}
