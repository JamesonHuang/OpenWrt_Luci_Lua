// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.pattern.parser;

import ch.qos.logback.core.pattern.*;
import ch.qos.logback.core.pattern.util.IEscapeUtil;
import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.ScanException;
import java.util.*;

// Referenced classes of package ch.qos.logback.core.pattern.parser:
//            Token, TokenStream, CompositeNode, Node, 
//            SimpleKeywordNode, FormattingNode, Compiler

public class Parser extends ContextAwareBase
{

    Parser(TokenStream tokenstream)
        throws ScanException
    {
        pointer = 0;
        tokenList = tokenstream.tokenize();
    }

    public Parser(String s)
        throws ScanException
    {
        this(s, ((IEscapeUtil) (new RegularEscapeUtil())));
    }

    public Parser(String s, IEscapeUtil iescapeutil)
        throws ScanException
    {
        pointer = 0;
        try
        {
            tokenList = (new TokenStream(s, iescapeutil)).tokenize();
            return;
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw new ScanException("Failed to initialize Parser", illegalargumentexception);
        }
    }

    FormattingNode C()
        throws ScanException
    {
        Token token;
        token = getCurentToken();
        expectNotNull(token, "a LEFT_PARENTHESIS or KEYWORD");
        token.getType();
        JVM INSTR tableswitch 1004 1005: default 40
    //                   1004 67
    //                   1005 74;
           goto _L1 _L2 _L3
_L1:
        throw new IllegalStateException((new StringBuilder()).append("Unexpected token ").append(token).toString());
_L2:
        FormattingNode formattingnode = SINGLE();
_L5:
        return formattingnode;
_L3:
        advanceTokenPointer();
        formattingnode = COMPOSITE(token.getValue().toString());
        if(true) goto _L5; else goto _L4
_L4:
    }

    FormattingNode COMPOSITE(String s)
        throws ScanException
    {
        CompositeNode compositenode = new CompositeNode(s);
        compositenode.setChildNode(E());
        Token token = getNextToken();
        if(token == null || token.getType() != 41)
        {
            String s1 = (new StringBuilder()).append("Expecting RIGHT_PARENTHESIS token but got ").append(token).toString();
            addError(s1);
            addError("See also http://logback.qos.ch/codes.html#missingRightParenthesis");
            throw new ScanException(s1);
        }
        Token token1 = getCurentToken();
        if(token1 != null && token1.getType() == 1006)
        {
            compositenode.setOptions((List)token1.getValue());
            advanceTokenPointer();
        }
        return compositenode;
    }

    Node E()
        throws ScanException
    {
        Node node = T();
        if(node != null) goto _L2; else goto _L1
_L1:
        node = null;
_L4:
        return node;
_L2:
        Node node1 = Eopt();
        if(node1 != null)
            node.setNext(node1);
        if(true) goto _L4; else goto _L3
_L3:
    }

    Node Eopt()
        throws ScanException
    {
        Node node;
        if(getCurentToken() == null)
            node = null;
        else
            node = E();
        return node;
    }

    FormattingNode SINGLE()
        throws ScanException
    {
        SimpleKeywordNode simplekeywordnode = new SimpleKeywordNode(getNextToken().getValue());
        Token token = getCurentToken();
        if(token != null && token.getType() == 1006)
        {
            simplekeywordnode.setOptions((List)token.getValue());
            advanceTokenPointer();
        }
        return simplekeywordnode;
    }

    Node T()
        throws ScanException
    {
        Token token;
        token = getCurentToken();
        expectNotNull(token, "a LITERAL or '%'");
        token.getType();
        JVM INSTR lookupswitch 2: default 44
    //                   37: 68
    //                   1000: 48;
           goto _L1 _L2 _L3
_L1:
        Object obj = null;
_L5:
        return ((Node) (obj));
_L3:
        advanceTokenPointer();
        obj = new Node(0, token.getValue());
        continue; /* Loop/switch isn't completed */
_L2:
        advanceTokenPointer();
        Token token1 = getCurentToken();
        expectNotNull(token1, "a FORMAT_MODIFIER, SIMPLE_KEYWORD or COMPOUND_KEYWORD");
        if(token1.getType() == 1002)
        {
            FormatInfo formatinfo = FormatInfo.valueOf((String)token1.getValue());
            advanceTokenPointer();
            obj = C();
            ((FormattingNode) (obj)).setFormatInfo(formatinfo);
        } else
        {
            obj = C();
        }
        if(true) goto _L5; else goto _L4
_L4:
    }

    void advanceTokenPointer()
    {
        pointer = 1 + pointer;
    }

    public Converter compile(Node node, Map map)
    {
        Compiler compiler = new Compiler(node, map);
        compiler.setContext(context);
        return compiler.compile();
    }

    void expectNotNull(Token token, String s)
    {
        if(token == null)
            throw new IllegalStateException((new StringBuilder()).append("All tokens consumed but was expecting ").append(s).toString());
        else
            return;
    }

    Token getCurentToken()
    {
        Token token;
        if(pointer < tokenList.size())
            token = (Token)tokenList.get(pointer);
        else
            token = null;
        return token;
    }

    Token getNextToken()
    {
        Token token;
        if(pointer < tokenList.size())
        {
            List list = tokenList;
            int i = pointer;
            pointer = i + 1;
            token = (Token)list.get(i);
        } else
        {
            token = null;
        }
        return token;
    }

    public Node parse()
        throws ScanException
    {
        return E();
    }

    public static final Map DEFAULT_COMPOSITE_CONVERTER_MAP;
    public static final String MISSING_RIGHT_PARENTHESIS = "http://logback.qos.ch/codes.html#missingRightParenthesis";
    public static final String REPLACE_CONVERTER_WORD = "replace";
    int pointer;
    final List tokenList;

    static 
    {
        DEFAULT_COMPOSITE_CONVERTER_MAP = new HashMap();
        DEFAULT_COMPOSITE_CONVERTER_MAP.put(Token.BARE_COMPOSITE_KEYWORD_TOKEN.getValue().toString(), ch/qos/logback/core/pattern/IdentityCompositeConverter.getName());
        DEFAULT_COMPOSITE_CONVERTER_MAP.put("replace", ch/qos/logback/core/pattern/ReplacingCompositeConverter.getName());
    }
}
