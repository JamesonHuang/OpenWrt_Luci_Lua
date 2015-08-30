// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.spi;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.action.ImplicitAction;
import ch.qos.logback.core.joran.event.*;
import java.util.*;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;

// Referenced classes of package ch.qos.logback.core.joran.spi:
//            CAI_WithLocatorSupport, InterpretationContext, EventPlayer, ActionException, 
//            ElementPath, RuleStore

public class Interpreter
{

    public Interpreter(Context context, RuleStore rulestore, ElementPath elementpath)
    {
        skip = null;
        cai = new CAI_WithLocatorSupport(context, this);
        ruleStore = rulestore;
        interpretationContext = new InterpretationContext(context, this);
        elementPath = elementpath;
        actionListStack = new Stack();
        eventPlayer = new EventPlayer(this);
    }

    private void callBodyAction(List list, String s)
    {
        if(list != null)
        {
            Iterator iterator = list.iterator();
            while(iterator.hasNext()) 
            {
                Action action = (Action)iterator.next();
                try
                {
                    action.body(interpretationContext, s);
                }
                catch(ActionException actionexception)
                {
                    cai.addError((new StringBuilder()).append("Exception in end() methd for action [").append(action).append("]").toString(), actionexception);
                }
            }
        }
    }

    private void callEndAction(List list, String s)
    {
        if(list != null)
        {
            Iterator iterator = list.iterator();
            while(iterator.hasNext()) 
            {
                Action action = (Action)iterator.next();
                try
                {
                    action.end(interpretationContext, s);
                }
                catch(ActionException actionexception)
                {
                    cai.addError((new StringBuilder()).append("ActionException in Action for tag [").append(s).append("]").toString(), actionexception);
                }
                catch(RuntimeException runtimeexception)
                {
                    cai.addError((new StringBuilder()).append("RuntimeException in Action for tag [").append(s).append("]").toString(), runtimeexception);
                }
            }
        }
    }

    private void endElement(String s, String s1, String s2)
    {
        List list = (List)actionListStack.pop();
        if(skip == null) goto _L2; else goto _L1
_L1:
        if(skip.equals(elementPath))
            skip = null;
_L4:
        elementPath.pop();
        return;
_L2:
        if(list != EMPTY_LIST)
            callEndAction(list, getTagName(s1, s2));
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void pushEmptyActionList()
    {
        actionListStack.add(EMPTY_LIST);
    }

    private void startElement(String s, String s1, String s2, Attributes attributes)
    {
        String s3 = getTagName(s1, s2);
        elementPath.push(s3);
        if(skip != null)
        {
            pushEmptyActionList();
        } else
        {
            List list = getApplicableActionList(elementPath, attributes);
            if(list != null)
            {
                actionListStack.add(list);
                callBeginAction(list, s3, attributes);
            } else
            {
                pushEmptyActionList();
                String s4 = (new StringBuilder()).append("no applicable action for [").append(s3).append("], current ElementPath  is [").append(elementPath).append("]").toString();
                cai.addError(s4);
            }
        }
    }

    public void addImplicitAction(ImplicitAction implicitaction)
    {
        implicitActions.add(implicitaction);
    }

    void callBeginAction(List list, String s, Attributes attributes)
    {
        if(list != null)
        {
            Iterator iterator = list.iterator();
            while(iterator.hasNext()) 
            {
                Action action = (Action)iterator.next();
                try
                {
                    action.begin(interpretationContext, s, attributes);
                }
                catch(ActionException actionexception)
                {
                    skip = elementPath.duplicate();
                    cai.addError((new StringBuilder()).append("ActionException in Action for tag [").append(s).append("]").toString(), actionexception);
                }
                catch(RuntimeException runtimeexception)
                {
                    skip = elementPath.duplicate();
                    cai.addError((new StringBuilder()).append("RuntimeException in Action for tag [").append(s).append("]").toString(), runtimeexception);
                }
            }
        }
    }

    public void characters(BodyEvent bodyevent)
    {
        setDocumentLocator(bodyevent.locator);
        String s = bodyevent.getText();
        List list = (List)actionListStack.peek();
        if(s != null)
        {
            String s1 = s.trim();
            if(s1.length() > 0)
                callBodyAction(list, s1);
        }
    }

    public void endElement(EndEvent endevent)
    {
        setDocumentLocator(endevent.locator);
        endElement(endevent.namespaceURI, endevent.localName, endevent.qName);
    }

    List getApplicableActionList(ElementPath elementpath, Attributes attributes)
    {
        List list = ruleStore.matchActions(elementpath);
        if(list == null)
            list = lookupImplicitAction(elementpath, attributes, interpretationContext);
        return list;
    }

    public EventPlayer getEventPlayer()
    {
        return eventPlayer;
    }

    public InterpretationContext getExecutionContext()
    {
        return getInterpretationContext();
    }

    public InterpretationContext getInterpretationContext()
    {
        return interpretationContext;
    }

    public Locator getLocator()
    {
        return locator;
    }

    public RuleStore getRuleStore()
    {
        return ruleStore;
    }

    String getTagName(String s, String s1)
    {
        if(s == null || s.length() < 1)
            s = s1;
        return s;
    }

    List lookupImplicitAction(ElementPath elementpath, Attributes attributes, InterpretationContext interpretationcontext)
    {
        int i;
        int j;
        i = implicitActions.size();
        j = 0;
_L3:
        ImplicitAction implicitaction;
        if(j >= i)
            break MISSING_BLOCK_LABEL_77;
        implicitaction = (ImplicitAction)implicitActions.get(j);
        if(!implicitaction.isApplicable(elementpath, attributes, interpretationcontext)) goto _L2; else goto _L1
_L1:
        ArrayList arraylist;
        ArrayList arraylist1 = new ArrayList(1);
        arraylist1.add(implicitaction);
        arraylist = arraylist1;
_L4:
        return arraylist;
_L2:
        j++;
          goto _L3
        arraylist = null;
          goto _L4
    }

    public void setDocumentLocator(Locator locator1)
    {
        locator = locator1;
    }

    public void setInterpretationContextPropertiesMap(Map map)
    {
        interpretationContext.setPropertiesMap(map);
    }

    public void startDocument()
    {
    }

    public void startElement(StartEvent startevent)
    {
        setDocumentLocator(startevent.getLocator());
        startElement(startevent.namespaceURI, startevent.localName, startevent.qName, startevent.attributes);
    }

    private static List EMPTY_LIST = new Vector(0);
    Stack actionListStack;
    private final CAI_WithLocatorSupport cai;
    private ElementPath elementPath;
    EventPlayer eventPlayer;
    private final ArrayList implicitActions = new ArrayList(3);
    private final InterpretationContext interpretationContext;
    Locator locator;
    private final RuleStore ruleStore;
    ElementPath skip;

}
