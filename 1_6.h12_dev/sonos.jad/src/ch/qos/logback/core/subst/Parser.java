// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.subst;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.spi.ScanException;
import java.util.List;

// Referenced classes of package ch.qos.logback.core.subst:
//            Node, Token

public class Parser
{

    public Parser(List list)
    {
        pointer = 0;
        tokenList = list;
    }

    private Node C()
        throws ScanException
    {
        Node node = E();
        if(isDefaultToken(peekAtCurentToken()))
        {
            advanceTokenPointer();
            node.append(makeNewLiteralNode(":-"));
            node.append(E());
        }
        return node;
    }

    private Node E()
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
            node.append(node1);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private Node Eopt()
        throws ScanException
    {
        Node node;
        if(peekAtCurentToken() == null)
            node = null;
        else
            node = E();
        return node;
    }

    private Node T()
        throws ScanException
    {
        Node node;
        Token token;
        node = null;
        token = peekAtCurentToken();
        if(token != null) goto _L2; else goto _L1
_L1:
        return node;
_L2:
        class _cls1
        {

            static final int $SwitchMap$ch$qos$logback$core$subst$Token$Type[];

            static 
            {
                $SwitchMap$ch$qos$logback$core$subst$Token$Type = new int[Token.Type.values().length];
                NoSuchFieldError nosuchfielderror2;
                try
                {
                    $SwitchMap$ch$qos$logback$core$subst$Token$Type[Token.Type.LITERAL.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$ch$qos$logback$core$subst$Token$Type[Token.Type.CURLY_LEFT.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                $SwitchMap$ch$qos$logback$core$subst$Token$Type[Token.Type.START.ordinal()] = 3;
_L2:
                return;
                nosuchfielderror2;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        switch(_cls1..SwitchMap.ch.qos.logback.core.subst.Token.Type[token.type.ordinal()])
        {
        case 1: // '\001'
            advanceTokenPointer();
            node = makeNewLiteralNode(token.payload);
            break;

        case 2: // '\002'
            advanceTokenPointer();
            Node node1 = C();
            expectCurlyRight(peekAtCurentToken());
            advanceTokenPointer();
            node = makeNewLiteralNode(CoreConstants.LEFT_ACCOLADE);
            node.append(node1);
            node.append(makeNewLiteralNode(CoreConstants.RIGHT_ACCOLADE));
            break;

        case 3: // '\003'
            advanceTokenPointer();
            node = V();
            expectCurlyRight(peekAtCurentToken());
            advanceTokenPointer();
            break;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    private Node V()
        throws ScanException
    {
        Node node = E();
        Node node1 = new Node(Node.Type.VARIABLE, node);
        if(isDefaultToken(peekAtCurentToken()))
        {
            advanceTokenPointer();
            node1.defaultPart = E();
        }
        return node1;
    }

    private boolean isDefaultToken(Token token)
    {
        boolean flag;
        if(token != null && token.type == Token.Type.DEFAULT)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private Node makeNewLiteralNode(String s)
    {
        return new Node(Node.Type.LITERAL, s);
    }

    void advanceTokenPointer()
    {
        pointer = 1 + pointer;
    }

    void expectCurlyRight(Token token)
        throws ScanException
    {
        expectNotNull(token, "}");
        if(token.type != Token.Type.CURLY_RIGHT)
            throw new ScanException("Expecting }");
        else
            return;
    }

    void expectNotNull(Token token, String s)
    {
        if(token == null)
            throw new IllegalArgumentException((new StringBuilder()).append("All tokens consumed but was expecting \"").append(s).append("\"").toString());
        else
            return;
    }

    public Node parse()
        throws ScanException
    {
        return E();
    }

    Token peekAtCurentToken()
    {
        Token token;
        if(pointer < tokenList.size())
            token = (Token)tokenList.get(pointer);
        else
            token = null;
        return token;
    }

    int pointer;
    final List tokenList;
}
