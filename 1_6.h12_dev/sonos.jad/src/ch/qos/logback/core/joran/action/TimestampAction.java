// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.spi.ActionException;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.util.CachingDateFormatter;
import ch.qos.logback.core.util.OptionHelper;
import org.xml.sax.Attributes;

// Referenced classes of package ch.qos.logback.core.joran.action:
//            Action, ActionUtil

public class TimestampAction extends Action
{

    public TimestampAction()
    {
        inError = false;
    }

    public void begin(InterpretationContext interpretationcontext, String s, Attributes attributes)
        throws ActionException
    {
        String s1 = attributes.getValue("key");
        if(OptionHelper.isEmpty(s1))
        {
            addError("Attribute named [key] cannot be empty");
            inError = true;
        }
        String s2 = attributes.getValue(DATE_PATTERN_ATTRIBUTE);
        if(OptionHelper.isEmpty(s2))
        {
            addError((new StringBuilder()).append("Attribute named [").append(DATE_PATTERN_ATTRIBUTE).append("] cannot be empty").toString());
            inError = true;
        }
        String s3 = attributes.getValue(TIME_REFERENCE_ATTRIBUTE);
        long l;
        if(CONTEXT_BIRTH.equalsIgnoreCase(s3))
        {
            addInfo("Using context birth as time reference.");
            l = context.getBirthTime();
        } else
        {
            l = System.currentTimeMillis();
            addInfo("Using current interpretation time, i.e. now, as time reference.");
        }
        if(!inError)
        {
            ActionUtil.Scope scope = ActionUtil.stringToScope(attributes.getValue("scope"));
            String s4 = (new CachingDateFormatter(s2)).format(l);
            addInfo((new StringBuilder()).append("Adding property to the context with key=\"").append(s1).append("\" and value=\"").append(s4).append("\" to the ").append(scope).append(" scope").toString());
            ActionUtil.setProperty(interpretationcontext, s1, s4, scope);
        }
    }

    public void end(InterpretationContext interpretationcontext, String s)
        throws ActionException
    {
    }

    static String CONTEXT_BIRTH = "contextBirth";
    static String DATE_PATTERN_ATTRIBUTE = "datePattern";
    static String TIME_REFERENCE_ATTRIBUTE = "timeReference";
    boolean inError;

}
