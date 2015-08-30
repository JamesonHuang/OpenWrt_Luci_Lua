// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.spi;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAwareImpl;
import org.xml.sax.Locator;

// Referenced classes of package ch.qos.logback.core.joran.spi:
//            Interpreter

class CAI_WithLocatorSupport extends ContextAwareImpl
{

    CAI_WithLocatorSupport(Context context, Interpreter interpreter)
    {
        super(context, interpreter);
    }

    protected Object getOrigin()
    {
        Locator locator = ((Interpreter)super.getOrigin()).locator;
        String s;
        if(locator != null)
            s = (new StringBuilder()).append(ch/qos/logback/core/joran/spi/Interpreter.getName()).append("@").append(locator.getLineNumber()).append(":").append(locator.getColumnNumber()).toString();
        else
            s = (new StringBuilder()).append(ch/qos/logback/core/joran/spi/Interpreter.getName()).append("@NA:NA").toString();
        return s;
    }
}
