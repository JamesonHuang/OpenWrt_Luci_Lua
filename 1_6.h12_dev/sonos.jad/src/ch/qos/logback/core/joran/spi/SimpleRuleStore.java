// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.spi;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.util.OptionHelper;
import java.util.*;

// Referenced classes of package ch.qos.logback.core.joran.spi:
//            RuleStore, ElementSelector, ElementPath

public class SimpleRuleStore extends ContextAwareBase
    implements RuleStore
{

    public SimpleRuleStore(Context context)
    {
        rules = new HashMap();
        setContext(context);
    }

    private boolean isKleeneStar(String s)
    {
        return KLEENE_STAR.equals(s);
    }

    private boolean isSuffixPattern(ElementSelector elementselector)
    {
        boolean flag = true;
        if(elementselector.size() <= flag || !elementselector.get(0).equals(KLEENE_STAR))
            flag = false;
        return flag;
    }

    public void addRule(ElementSelector elementselector, Action action)
    {
        action.setContext(context);
        Object obj = (List)rules.get(elementselector);
        if(obj == null)
        {
            obj = new ArrayList();
            rules.put(elementselector, obj);
        }
        ((List) (obj)).add(action);
    }

    public void addRule(ElementSelector elementselector, String s)
    {
        Action action;
        try
        {
            action = (Action)OptionHelper.instantiateByClassName(s, ch/qos/logback/core/joran/action/Action, context);
        }
        catch(Exception exception)
        {
            addError((new StringBuilder()).append("Could not instantiate class [").append(s).append("]").toString(), exception);
            action = null;
        }
        if(action != null)
            addRule(elementselector, action);
    }

    List fullPathMatch(ElementPath elementpath)
    {
        Iterator iterator = rules.keySet().iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        ElementSelector elementselector = (ElementSelector)iterator.next();
        if(!elementselector.fullPathMatch(elementpath)) goto _L4; else goto _L3
_L3:
        List list = (List)rules.get(elementselector);
_L6:
        return list;
_L2:
        list = null;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public List matchActions(ElementPath elementpath)
    {
        List list = fullPathMatch(elementpath);
        if(list == null) goto _L2; else goto _L1
_L1:
        return list;
_L2:
        list = suffixMatch(elementpath);
        if(list == null)
        {
            list = prefixMatch(elementpath);
            if(list == null)
            {
                list = middleMatch(elementpath);
                if(list == null)
                    list = null;
            }
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    List middleMatch(ElementPath elementpath)
    {
        Iterator iterator;
        ElementSelector elementselector;
        int i;
        iterator = rules.keySet().iterator();
        elementselector = null;
        i = 0;
_L2:
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        ElementSelector elementselector1 = (ElementSelector)iterator.next();
        String s = elementselector1.peekLast();
        List list;
        String s1;
        int j;
        List list1;
        ElementSelector elementselector2;
        int k;
        if(elementselector1.size() > 1)
            s1 = elementselector1.get(0);
        else
            s1 = null;
        if(!isKleeneStar(s) || !isKleeneStar(s1))
            break MISSING_BLOCK_LABEL_199;
        list1 = elementselector1.getCopyOfPartList();
        if(list1.size() > 2)
        {
            list1.remove(0);
            list1.remove(-1 + list1.size());
        }
        elementselector2 = new ElementSelector(list1);
        if(elementselector2.isContainedIn(elementpath))
            k = elementselector2.size();
        else
            k = 0;
        if(k <= i)
            break MISSING_BLOCK_LABEL_199;
        j = k;
_L3:
        i = j;
        elementselector = elementselector1;
        if(true) goto _L2; else goto _L1
_L1:
        if(elementselector != null)
            list = (List)rules.get(elementselector);
        else
            list = null;
        return list;
        elementselector1 = elementselector;
        j = i;
          goto _L3
    }

    List prefixMatch(ElementPath elementpath)
    {
        int i;
        Iterator iterator;
        ElementSelector elementselector;
        i = 0;
        iterator = rules.keySet().iterator();
        elementselector = null;
_L2:
        ElementSelector elementselector1;
        int j;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        elementselector1 = (ElementSelector)iterator.next();
        if(!isKleeneStar(elementselector1.peekLast()))
            break MISSING_BLOCK_LABEL_119;
        int k = elementselector1.getPrefixMatchLength(elementpath);
        if(k != -1 + elementselector1.size() || k <= i)
            break MISSING_BLOCK_LABEL_119;
        j = k;
_L3:
        i = j;
        elementselector = elementselector1;
        if(true) goto _L2; else goto _L1
_L1:
        List list;
        if(elementselector != null)
            list = (List)rules.get(elementselector);
        else
            list = null;
        return list;
        elementselector1 = elementselector;
        j = i;
          goto _L3
    }

    List suffixMatch(ElementPath elementpath)
    {
        int i;
        Iterator iterator;
        ElementSelector elementselector;
        i = 0;
        iterator = rules.keySet().iterator();
        elementselector = null;
_L2:
        ElementSelector elementselector1;
        int j;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        elementselector1 = (ElementSelector)iterator.next();
        if(!isSuffixPattern(elementselector1))
            break MISSING_BLOCK_LABEL_103;
        int k = elementselector1.getTailMatchLength(elementpath);
        if(k <= i)
            break MISSING_BLOCK_LABEL_103;
        j = k;
_L3:
        i = j;
        elementselector = elementselector1;
        if(true) goto _L2; else goto _L1
_L1:
        List list;
        if(elementselector != null)
            list = (List)rules.get(elementselector);
        else
            list = null;
        return list;
        elementselector1 = elementselector;
        j = i;
          goto _L3
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("SimpleRuleStore ( ").append("rules = ").append(rules).append("  ").append(" )");
        return stringbuilder.toString();
    }

    static String KLEENE_STAR = "*";
    HashMap rules;

}
