// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.pattern.parser;

import ch.qos.logback.core.pattern.*;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.status.ErrorStatus;
import ch.qos.logback.core.util.OptionHelper;
import java.util.Map;

// Referenced classes of package ch.qos.logback.core.pattern.parser:
//            Node, CompositeNode, SimpleKeywordNode

class Compiler extends ContextAwareBase
{

    Compiler(Node node, Map map)
    {
        top = node;
        converterMap = map;
    }

    private void addToList(Converter converter)
    {
        if(head == null)
        {
            tail = converter;
            head = converter;
        } else
        {
            tail.setNext(converter);
            tail = converter;
        }
    }

    Converter compile()
    {
        Node node;
        tail = null;
        head = null;
        node = top;
_L6:
        if(node == null)
            break MISSING_BLOCK_LABEL_359;
        node.type;
        JVM INSTR tableswitch 0 2: default 48
    //                   0 56
    //                   1 236
    //                   2 77;
           goto _L1 _L2 _L3 _L4
_L3:
        break MISSING_BLOCK_LABEL_236;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L7:
        node = node.next;
        if(true) goto _L6; else goto _L5
_L5:
        addToList(new LiteralConverter((String)node.getValue()));
          goto _L7
_L4:
        CompositeNode compositenode = (CompositeNode)node;
        CompositeConverter compositeconverter = createCompositeConverter(compositenode);
        if(compositeconverter == null)
        {
            addError((new StringBuilder()).append("Failed to create converter for [%").append(compositenode.getValue()).append("] keyword").toString());
            addToList(new LiteralConverter((new StringBuilder()).append("%PARSER_ERROR[").append(compositenode.getValue()).append("]").toString()));
        } else
        {
            compositeconverter.setFormattingInfo(compositenode.getFormatInfo());
            compositeconverter.setOptionList(compositenode.getOptions());
            Compiler compiler = new Compiler(compositenode.getChildNode(), converterMap);
            compiler.setContext(context);
            compositeconverter.setChildConverter(compiler.compile());
            addToList(compositeconverter);
        }
          goto _L7
        SimpleKeywordNode simplekeywordnode = (SimpleKeywordNode)node;
        DynamicConverter dynamicconverter = createConverter(simplekeywordnode);
        if(dynamicconverter != null)
        {
            dynamicconverter.setFormattingInfo(simplekeywordnode.getFormatInfo());
            dynamicconverter.setOptionList(simplekeywordnode.getOptions());
            addToList(dynamicconverter);
        } else
        {
            LiteralConverter literalconverter = new LiteralConverter((new StringBuilder()).append("%PARSER_ERROR[").append(simplekeywordnode.getValue()).append("]").toString());
            addStatus(new ErrorStatus((new StringBuilder()).append("[").append(simplekeywordnode.getValue()).append("] is not a valid conversion word").toString(), this));
            addToList(literalconverter);
        }
          goto _L7
        return head;
    }

    CompositeConverter createCompositeConverter(CompositeNode compositenode)
    {
        String s = (String)compositenode.getValue();
        String s1 = (String)converterMap.get(s);
        CompositeConverter compositeconverter;
        if(s1 != null)
        {
            try
            {
                compositeconverter = (CompositeConverter)OptionHelper.instantiateByClassName(s1, ch/qos/logback/core/pattern/CompositeConverter, context);
            }
            catch(Exception exception)
            {
                addError((new StringBuilder()).append("Failed to instantiate converter class [").append(s1).append("] as a composite converter for keyword [").append(s).append("]").toString(), exception);
                compositeconverter = null;
            }
        } else
        {
            addError((new StringBuilder()).append("There is no conversion class registered for composite conversion word [").append(s).append("]").toString());
            compositeconverter = null;
        }
        return compositeconverter;
    }

    DynamicConverter createConverter(SimpleKeywordNode simplekeywordnode)
    {
        String s = (String)simplekeywordnode.getValue();
        String s1 = (String)converterMap.get(s);
        DynamicConverter dynamicconverter;
        if(s1 != null)
        {
            try
            {
                dynamicconverter = (DynamicConverter)OptionHelper.instantiateByClassName(s1, ch/qos/logback/core/pattern/DynamicConverter, context);
            }
            catch(Exception exception)
            {
                addError((new StringBuilder()).append("Failed to instantiate converter class [").append(s1).append("] for keyword [").append(s).append("]").toString(), exception);
                dynamicconverter = null;
            }
        } else
        {
            addError((new StringBuilder()).append("There is no conversion class registered for conversion word [").append(s).append("]").toString());
            dynamicconverter = null;
        }
        return dynamicconverter;
    }

    final Map converterMap;
    Converter head;
    Converter tail;
    final Node top;
}
