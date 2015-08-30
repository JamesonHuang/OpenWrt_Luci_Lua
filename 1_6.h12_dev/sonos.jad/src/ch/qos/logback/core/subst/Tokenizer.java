// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.subst;

import ch.qos.logback.core.spi.ScanException;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package ch.qos.logback.core.subst:
//            Token

public class Tokenizer
{
    static final class TokenizerState extends Enum
    {

        public static TokenizerState valueOf(String s)
        {
            return (TokenizerState)Enum.valueOf(ch/qos/logback/core/subst/Tokenizer$TokenizerState, s);
        }

        public static TokenizerState[] values()
        {
            return (TokenizerState[])$VALUES.clone();
        }

        private static final TokenizerState $VALUES[];
        public static final TokenizerState DEFAULT_VAL_STATE;
        public static final TokenizerState LITERAL_STATE;
        public static final TokenizerState START_STATE;

        static 
        {
            LITERAL_STATE = new TokenizerState("LITERAL_STATE", 0);
            START_STATE = new TokenizerState("START_STATE", 1);
            DEFAULT_VAL_STATE = new TokenizerState("DEFAULT_VAL_STATE", 2);
            TokenizerState atokenizerstate[] = new TokenizerState[3];
            atokenizerstate[0] = LITERAL_STATE;
            atokenizerstate[1] = START_STATE;
            atokenizerstate[2] = DEFAULT_VAL_STATE;
            $VALUES = atokenizerstate;
        }

        private TokenizerState(String s, int i)
        {
            super(s, i);
        }
    }


    public Tokenizer(String s)
    {
        state = TokenizerState.LITERAL_STATE;
        pointer = 0;
        pattern = s;
        patternLength = s.length();
    }

    private void addLiteralToken(List list, StringBuilder stringbuilder)
    {
        if(stringbuilder.length() != 0)
            list.add(new Token(Token.Type.LITERAL, stringbuilder.toString()));
    }

    private void handleDefaultValueState(char c, List list, StringBuilder stringbuilder)
    {
        c;
        JVM INSTR lookupswitch 2: default 28
    //                   36: 67
    //                   45: 47;
           goto _L1 _L2 _L3
_L1:
        stringbuilder.append(':').append(c);
        state = TokenizerState.LITERAL_STATE;
_L5:
        return;
_L3:
        list.add(Token.DEFAULT_SEP_TOKEN);
        state = TokenizerState.LITERAL_STATE;
        continue; /* Loop/switch isn't completed */
_L2:
        stringbuilder.append(':');
        addLiteralToken(list, stringbuilder);
        stringbuilder.setLength(0);
        state = TokenizerState.START_STATE;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private void handleLiteralState(char c, List list, StringBuilder stringbuilder)
    {
        if(c == '$')
        {
            addLiteralToken(list, stringbuilder);
            stringbuilder.setLength(0);
            state = TokenizerState.START_STATE;
        } else
        if(c == ':')
        {
            addLiteralToken(list, stringbuilder);
            stringbuilder.setLength(0);
            state = TokenizerState.DEFAULT_VAL_STATE;
        } else
        if(c == '{')
        {
            addLiteralToken(list, stringbuilder);
            list.add(Token.CURLY_LEFT_TOKEN);
            stringbuilder.setLength(0);
        } else
        if(c == '}')
        {
            addLiteralToken(list, stringbuilder);
            list.add(Token.CURLY_RIGHT_TOKEN);
            stringbuilder.setLength(0);
        } else
        {
            stringbuilder.append(c);
        }
    }

    private void handleStartState(char c, List list, StringBuilder stringbuilder)
    {
        if(c == '{')
            list.add(Token.START_TOKEN);
        else
            stringbuilder.append('$').append(c);
        state = TokenizerState.LITERAL_STATE;
    }

    List tokenize()
        throws ScanException
    {
        ArrayList arraylist;
        StringBuilder stringbuilder;
        arraylist = new ArrayList();
        stringbuilder = new StringBuilder();
_L5:
        class _cls1
        {

            static final int $SwitchMap$ch$qos$logback$core$subst$Tokenizer$TokenizerState[];

            static 
            {
                $SwitchMap$ch$qos$logback$core$subst$Tokenizer$TokenizerState = new int[TokenizerState.values().length];
                NoSuchFieldError nosuchfielderror2;
                try
                {
                    $SwitchMap$ch$qos$logback$core$subst$Tokenizer$TokenizerState[TokenizerState.LITERAL_STATE.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$ch$qos$logback$core$subst$Tokenizer$TokenizerState[TokenizerState.START_STATE.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                $SwitchMap$ch$qos$logback$core$subst$Tokenizer$TokenizerState[TokenizerState.DEFAULT_VAL_STATE.ordinal()] = 3;
_L2:
                return;
                nosuchfielderror2;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        if(pointer < patternLength)
        {
            char c = pattern.charAt(pointer);
            pointer = 1 + pointer;
            switch(_cls1..SwitchMap.ch.qos.logback.core.subst.Tokenizer.TokenizerState[state.ordinal()])
            {
            case 1: // '\001'
                handleLiteralState(c, arraylist, stringbuilder);
                break;

            case 2: // '\002'
                handleStartState(c, arraylist, stringbuilder);
                break;

            case 3: // '\003'
                handleDefaultValueState(c, arraylist, stringbuilder);
                break;
            }
            continue; /* Loop/switch isn't completed */
        }
        _cls1..SwitchMap.ch.qos.logback.core.subst.Tokenizer.TokenizerState[state.ordinal()];
        JVM INSTR tableswitch 1 2: default 156
    //                   1 158
    //                   2 167;
           goto _L1 _L2 _L3
_L1:
        return arraylist;
_L2:
        addLiteralToken(arraylist, stringbuilder);
          goto _L1
_L3:
        throw new ScanException("Unexpected end of pattern string");
        if(true) goto _L5; else goto _L4
_L4:
    }

    final String pattern;
    final int patternLength;
    int pointer;
    TokenizerState state;
}
