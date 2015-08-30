// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.subst;

import ch.qos.logback.core.spi.PropertyContainer;
import ch.qos.logback.core.spi.ScanException;
import ch.qos.logback.core.util.OptionHelper;
import java.util.Iterator;
import java.util.Stack;

// Referenced classes of package ch.qos.logback.core.subst:
//            Node, Parser, Tokenizer

public class NodeToStringTransformer
{

    public NodeToStringTransformer(Node node1, PropertyContainer propertycontainer)
    {
        this(node1, propertycontainer, null);
    }

    public NodeToStringTransformer(Node node1, PropertyContainer propertycontainer, PropertyContainer propertycontainer1)
    {
        node = node1;
        propertyContainer0 = propertycontainer;
        propertyContainer1 = propertycontainer1;
    }

    private void compileNode(Node node1, StringBuilder stringbuilder, Stack stack)
        throws ScanException
    {
_L2:
        if(node1 == null)
            break MISSING_BLOCK_LABEL_63;
        class _cls1
        {

            static final int $SwitchMap$ch$qos$logback$core$subst$Node$Type[];

            static 
            {
                $SwitchMap$ch$qos$logback$core$subst$Node$Type = new int[Node.Type.values().length];
                NoSuchFieldError nosuchfielderror1;
                try
                {
                    $SwitchMap$ch$qos$logback$core$subst$Node$Type[Node.Type.LITERAL.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                $SwitchMap$ch$qos$logback$core$subst$Node$Type[Node.Type.VARIABLE.ordinal()] = 2;
_L2:
                return;
                nosuchfielderror1;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        switch(_cls1..SwitchMap.ch.qos.logback.core.subst.Node.Type[node1.type.ordinal()])
        {
        default:
            break;

        case 1: // '\001'
            break; /* Loop/switch isn't completed */

        case 2: // '\002'
            break;
        }
        break MISSING_BLOCK_LABEL_53;
_L3:
        node1 = node1.next;
        if(true) goto _L2; else goto _L1
_L1:
        handleLiteral(node1, stringbuilder);
          goto _L3
        handleVariable(node1, stringbuilder, stack);
          goto _L3
    }

    private String constructRecursionErrorMessage(Stack stack)
    {
        StringBuilder stringbuilder = new StringBuilder("Circular variable reference detected while parsing input [");
        Iterator iterator = stack.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Node node1 = (Node)iterator.next();
            stringbuilder.append("${").append(variableNodeValue(node1)).append("}");
            if(stack.lastElement() != node1)
                stringbuilder.append(" --> ");
        } while(true);
        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    private boolean equalNodes(Node node1, Node node2)
    {
        boolean flag;
        flag = false;
        break MISSING_BLOCK_LABEL_2;
        if((node1.type == null || node1.type.equals(node2.type)) && (node1.payload == null || node1.payload.equals(node2.payload)) && (node1.defaultPart == null || node1.defaultPart.equals(node2.defaultPart)))
            flag = true;
        return flag;
    }

    private void handleLiteral(Node node1, StringBuilder stringbuilder)
    {
        stringbuilder.append((String)node1.payload);
    }

    private void handleVariable(Node node1, StringBuilder stringbuilder, Stack stack)
        throws ScanException
    {
        if(haveVisitedNodeAlready(node1, stack))
        {
            stack.push(node1);
            throw new IllegalArgumentException(constructRecursionErrorMessage(stack));
        }
        stack.push(node1);
        StringBuilder stringbuilder1 = new StringBuilder();
        compileNode((Node)node1.payload, stringbuilder1, stack);
        String s = stringbuilder1.toString();
        String s1 = lookupKey(s);
        if(s1 != null)
        {
            compileNode(tokenizeAndParseString(s1), stringbuilder, stack);
            stack.pop();
        } else
        if(node1.defaultPart == null)
        {
            stringbuilder.append((new StringBuilder()).append(s).append("_IS_UNDEFINED").toString());
            stack.pop();
        } else
        {
            Node node2 = (Node)node1.defaultPart;
            StringBuilder stringbuilder2 = new StringBuilder();
            compileNode(node2, stringbuilder2, stack);
            stack.pop();
            stringbuilder.append(stringbuilder2.toString());
        }
    }

    private boolean haveVisitedNodeAlready(Node node1, Stack stack)
    {
        Iterator iterator = stack.iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        if(!equalNodes(node1, (Node)iterator.next())) goto _L4; else goto _L3
_L3:
        boolean flag = true;
_L6:
        return flag;
_L2:
        flag = false;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private String lookupKey(String s)
    {
        String s1 = propertyContainer0.getProperty(s);
        if(s1 == null) goto _L2; else goto _L1
_L1:
        return s1;
_L2:
        if(propertyContainer1 != null)
        {
            s1 = propertyContainer1.getProperty(s);
            if(s1 != null)
                continue; /* Loop/switch isn't completed */
        }
        s1 = OptionHelper.getSystemProperty(s, null);
        if(s1 == null)
        {
            s1 = OptionHelper.getEnv(s);
            if(s1 == null)
                s1 = null;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public static String substituteVariable(String s, PropertyContainer propertycontainer, PropertyContainer propertycontainer1)
        throws ScanException
    {
        return (new NodeToStringTransformer(tokenizeAndParseString(s), propertycontainer, propertycontainer1)).transform();
    }

    private static Node tokenizeAndParseString(String s)
        throws ScanException
    {
        return (new Parser((new Tokenizer(s)).tokenize())).parse();
    }

    private String variableNodeValue(Node node1)
    {
        return (String)((Node)node1.payload).payload;
    }

    public String transform()
        throws ScanException
    {
        StringBuilder stringbuilder = new StringBuilder();
        compileNode(node, stringbuilder, new Stack());
        return stringbuilder.toString();
    }

    final Node node;
    final PropertyContainer propertyContainer0;
    final PropertyContainer propertyContainer1;
}
