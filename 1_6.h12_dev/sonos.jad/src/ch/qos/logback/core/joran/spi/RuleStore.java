// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.spi;

import ch.qos.logback.core.joran.action.Action;
import java.util.List;

// Referenced classes of package ch.qos.logback.core.joran.spi:
//            ElementSelector, ElementPath

public interface RuleStore
{

    public abstract void addRule(ElementSelector elementselector, Action action);

    public abstract void addRule(ElementSelector elementselector, String s)
        throws ClassNotFoundException;

    public abstract List matchActions(ElementPath elementpath);
}
