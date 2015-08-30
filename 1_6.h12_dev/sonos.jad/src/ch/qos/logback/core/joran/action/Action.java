// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.joran.spi.*;
import ch.qos.logback.core.spi.ContextAwareBase;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;

public abstract class Action extends ContextAwareBase
{

    public Action()
    {
    }

    public abstract void begin(InterpretationContext interpretationcontext, String s, Attributes attributes)
        throws ActionException;

    public void body(InterpretationContext interpretationcontext, String s)
        throws ActionException
    {
    }

    public abstract void end(InterpretationContext interpretationcontext, String s)
        throws ActionException;

    protected int getColumnNumber(InterpretationContext interpretationcontext)
    {
        Locator locator = interpretationcontext.getJoranInterpreter().getLocator();
        int i;
        if(locator != null)
            i = locator.getColumnNumber();
        else
            i = -1;
        return i;
    }

    protected String getLineColStr(InterpretationContext interpretationcontext)
    {
        return (new StringBuilder()).append("line: ").append(getLineNumber(interpretationcontext)).append(", column: ").append(getColumnNumber(interpretationcontext)).toString();
    }

    protected int getLineNumber(InterpretationContext interpretationcontext)
    {
        Locator locator = interpretationcontext.getJoranInterpreter().getLocator();
        int i;
        if(locator != null)
            i = locator.getLineNumber();
        else
            i = -1;
        return i;
    }

    public String toString()
    {
        return getClass().getName();
    }

    public static final String ACTION_CLASS_ATTRIBUTE = "actionClass";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String FILE_ATTRIBUTE = "file";
    public static final String KEY_ATTRIBUTE = "key";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String PATTERN_ATTRIBUTE = "pattern";
    public static final String SCOPE_ATTRIBUTE = "scope";
    public static final String VALUE_ATTRIBUTE = "value";
}
