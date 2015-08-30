// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.joran.action;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import org.xml.sax.Attributes;

public class ContextNameAction extends Action
{

    public ContextNameAction()
    {
    }

    public void begin(InterpretationContext interpretationcontext, String s, Attributes attributes)
    {
    }

    public void body(InterpretationContext interpretationcontext, String s)
    {
        String s1;
        s1 = interpretationcontext.subst(s);
        addInfo((new StringBuilder()).append("Setting logger context name as [").append(s1).append("]").toString());
        context.setName(s1);
_L1:
        return;
        IllegalStateException illegalstateexception;
        illegalstateexception;
        addError((new StringBuilder()).append("Failed to rename context [").append(context.getName()).append("] as [").append(s1).append("]").toString(), illegalstateexception);
          goto _L1
    }

    public void end(InterpretationContext interpretationcontext, String s)
    {
    }
}
