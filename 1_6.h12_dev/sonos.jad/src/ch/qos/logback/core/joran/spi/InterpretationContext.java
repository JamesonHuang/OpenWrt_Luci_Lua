// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.spi;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.event.InPlayListener;
import ch.qos.logback.core.joran.event.SaxEvent;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.PropertyContainer;
import ch.qos.logback.core.util.OptionHelper;
import java.util.*;
import org.xml.sax.Locator;

// Referenced classes of package ch.qos.logback.core.joran.spi:
//            DefaultNestedComponentRegistry, Interpreter

public class InterpretationContext extends ContextAwareBase
    implements PropertyContainer
{

    public InterpretationContext(Context context, Interpreter interpreter)
    {
        defaultNestedComponentRegistry = new DefaultNestedComponentRegistry();
        this.context = context;
        joranInterpreter = interpreter;
        objectStack = new Stack();
        objectMap = new HashMap(5);
        propertiesMap = new HashMap(5);
    }

    public void addInPlayListener(InPlayListener inplaylistener)
    {
        if(listenerList.contains(inplaylistener))
            addWarn((new StringBuilder()).append("InPlayListener ").append(inplaylistener).append(" has been already registered").toString());
        else
            listenerList.add(inplaylistener);
    }

    public void addSubstitutionProperties(Properties properties)
    {
        if(properties != null)
        {
            Iterator iterator = properties.keySet().iterator();
            while(iterator.hasNext()) 
            {
                String s = (String)iterator.next();
                addSubstitutionProperty(s, properties.getProperty(s));
            }
        }
    }

    public void addSubstitutionProperty(String s, String s1)
    {
        if(s != null && s1 != null)
        {
            String s2 = s1.trim();
            propertiesMap.put(s, s2);
        }
    }

    void fireInPlay(SaxEvent saxevent)
    {
        for(Iterator iterator = listenerList.iterator(); iterator.hasNext(); ((InPlayListener)iterator.next()).inPlay(saxevent));
    }

    public Map getCopyOfPropertyMap()
    {
        return new HashMap(propertiesMap);
    }

    public DefaultNestedComponentRegistry getDefaultNestedComponentRegistry()
    {
        return defaultNestedComponentRegistry;
    }

    public Interpreter getJoranInterpreter()
    {
        return joranInterpreter;
    }

    public Locator getLocator()
    {
        return joranInterpreter.getLocator();
    }

    public Object getObject(int i)
    {
        return objectStack.get(i);
    }

    public Map getObjectMap()
    {
        return objectMap;
    }

    public Stack getObjectStack()
    {
        return objectStack;
    }

    public String getProperty(String s)
    {
        String s1 = (String)propertiesMap.get(s);
        if(s1 == null)
            s1 = context.getProperty(s);
        return s1;
    }

    public boolean isEmpty()
    {
        return objectStack.isEmpty();
    }

    public boolean isListenerListEmpty()
    {
        return listenerList.isEmpty();
    }

    public Object peekObject()
    {
        return objectStack.peek();
    }

    public Object popObject()
    {
        return objectStack.pop();
    }

    public void pushObject(Object obj)
    {
        objectStack.push(obj);
    }

    public boolean removeInPlayListener(InPlayListener inplaylistener)
    {
        return listenerList.remove(inplaylistener);
    }

    void setPropertiesMap(Map map)
    {
        propertiesMap = map;
    }

    public String subst(String s)
    {
        String s1;
        if(s == null)
            s1 = null;
        else
            s1 = OptionHelper.substVars(s, this, context);
        return s1;
    }

    String updateLocationInfo(String s)
    {
        Locator locator = joranInterpreter.getLocator();
        if(locator != null)
            s = (new StringBuilder()).append(s).append(locator.getLineNumber()).append(":").append(locator.getColumnNumber()).toString();
        return s;
    }

    DefaultNestedComponentRegistry defaultNestedComponentRegistry;
    Interpreter joranInterpreter;
    final List listenerList = new ArrayList();
    Map objectMap;
    Stack objectStack;
    Map propertiesMap;
}
