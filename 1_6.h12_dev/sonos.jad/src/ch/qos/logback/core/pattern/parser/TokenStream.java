// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.pattern.parser;

import ch.qos.logback.core.pattern.util.*;
import ch.qos.logback.core.spi.ScanException;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package ch.qos.logback.core.pattern.parser:
//            Token, OptionTokenizer

class TokenStream
{
    static final class TokenizerState extends Enum
    {

        public static TokenizerState valueOf(String s)
        {
            return (TokenizerState)Enum.valueOf(ch/qos/logback/core/pattern/parser/TokenStream$TokenizerState, s);
        }

        public static TokenizerState[] values()
        {
            return (TokenizerState[])$VALUES.clone();
        }

        private static final TokenizerState $VALUES[];
        public static final TokenizerState FORMAT_MODIFIER_STATE;
        public static final TokenizerState KEYWORD_STATE;
        public static final TokenizerState LITERAL_STATE;
        public static final TokenizerState OPTION_STATE;
        public static final TokenizerState RIGHT_PARENTHESIS_STATE;

        static 
        {
            LITERAL_STATE = new TokenizerState("LITERAL_STATE", 0);
            FORMAT_MODIFIER_STATE = new TokenizerState("FORMAT_MODIFIER_STATE", 1);
            KEYWORD_STATE = new TokenizerState("KEYWORD_STATE", 2);
            OPTION_STATE = new TokenizerState("OPTION_STATE", 3);
            RIGHT_PARENTHESIS_STATE = new TokenizerState("RIGHT_PARENTHESIS_STATE", 4);
            TokenizerState atokenizerstate[] = new TokenizerState[5];
            atokenizerstate[0] = LITERAL_STATE;
            atokenizerstate[1] = FORMAT_MODIFIER_STATE;
            atokenizerstate[2] = KEYWORD_STATE;
            atokenizerstate[3] = OPTION_STATE;
            atokenizerstate[4] = RIGHT_PARENTHESIS_STATE;
            $VALUES = atokenizerstate;
        }

        private TokenizerState(String s, int i)
        {
            super(s, i);
        }
    }


    TokenStream(String s)
    {
        this(s, ((IEscapeUtil) (new RegularEscapeUtil())));
    }

    TokenStream(String s, IEscapeUtil iescapeutil)
    {
        optionEscapeUtil = new RestrictedEscapeUtil();
        state = TokenizerState.LITERAL_STATE;
        pointer = 0;
        if(s == null || s.length() == 0)
        {
            throw new IllegalArgumentException("null or empty pattern string not allowed");
        } else
        {
            pattern = s;
            patternLength = s.length();
            escapeUtil = iescapeutil;
            return;
        }
    }

    private void addValuedToken(int i, StringBuffer stringbuffer, List list)
    {
        if(stringbuffer.length() > 0)
        {
            list.add(new Token(i, stringbuffer.toString()));
            stringbuffer.setLength(0);
        }
    }

    private void handleFormatModifierState(char c, List list, StringBuffer stringbuffer)
    {
        if(c == '(')
        {
            addValuedToken(1002, stringbuffer, list);
            list.add(Token.BARE_COMPOSITE_KEYWORD_TOKEN);
            state = TokenizerState.LITERAL_STATE;
        } else
        if(Character.isJavaIdentifierStart(c))
        {
            addValuedToken(1002, stringbuffer, list);
            state = TokenizerState.KEYWORD_STATE;
            stringbuffer.append(c);
        } else
        {
            stringbuffer.append(c);
        }
    }

    private void handleKeywordState(char c, List list, StringBuffer stringbuffer)
    {
        if(Character.isJavaIdentifierPart(c))
            stringbuffer.append(c);
        else
        if(c == '{')
        {
            addValuedToken(1004, stringbuffer, list);
            state = TokenizerState.OPTION_STATE;
        } else
        if(c == '(')
        {
            addValuedToken(1005, stringbuffer, list);
            state = TokenizerState.LITERAL_STATE;
        } else
        if(c == '%')
        {
            addValuedToken(1004, stringbuffer, list);
            list.add(Token.PERCENT_TOKEN);
            state = TokenizerState.FORMAT_MODIFIER_STATE;
        } else
        if(c == ')')
        {
            addValuedToken(1004, stringbuffer, list);
            state = TokenizerState.RIGHT_PARENTHESIS_STATE;
        } else
        {
            addValuedToken(1004, stringbuffer, list);
            if(c == '\\')
            {
                if(pointer < patternLength)
                {
                    String s = pattern;
                    int i = pointer;
                    pointer = i + 1;
                    char c1 = s.charAt(i);
                    escapeUtil.escape("%()", stringbuffer, c1, pointer);
                }
            } else
            {
                stringbuffer.append(c);
            }
            state = TokenizerState.LITERAL_STATE;
        }
    }

    private void handleLiteralState(char c, List list, StringBuffer stringbuffer)
    {
        c;
        JVM INSTR lookupswitch 3: default 36
    //                   37: 53
    //                   41: 82
    //                   92: 43;
           goto _L1 _L2 _L3 _L4
_L1:
        stringbuffer.append(c);
_L6:
        return;
_L4:
        escape("%()", stringbuffer);
        continue; /* Loop/switch isn't completed */
_L2:
        addValuedToken(1000, stringbuffer, list);
        list.add(Token.PERCENT_TOKEN);
        state = TokenizerState.FORMAT_MODIFIER_STATE;
        continue; /* Loop/switch isn't completed */
_L3:
        addValuedToken(1000, stringbuffer, list);
        state = TokenizerState.RIGHT_PARENTHESIS_STATE;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void handleRightParenthesisState(char c, List list, StringBuffer stringbuffer)
    {
        list.add(Token.RIGHT_PARENTHESIS_TOKEN);
        c;
        JVM INSTR lookupswitch 3: default 44
    //                   41: 57
    //                   92: 68
    //                   123: 58;
           goto _L1 _L2 _L3 _L4
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        stringbuffer.append(c);
        state = TokenizerState.LITERAL_STATE;
_L6:
        return;
_L4:
        state = TokenizerState.OPTION_STATE;
        continue; /* Loop/switch isn't completed */
_L3:
        escape("%{}", stringbuffer);
        state = TokenizerState.LITERAL_STATE;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void processOption(char c, List list, StringBuffer stringbuffer)
        throws ScanException
    {
        (new OptionTokenizer(this)).tokenize(c, list);
    }

    void escape(String s, StringBuffer stringbuffer)
    {
        if(pointer < patternLength)
        {
            String s1 = pattern;
            int i = pointer;
            pointer = i + 1;
            char c = s1.charAt(i);
            escapeUtil.escape(s, stringbuffer, c, pointer);
        }
    }

    void optionEscape(String s, StringBuffer stringbuffer)
    {
        if(pointer < patternLength)
        {
            String s1 = pattern;
            int i = pointer;
            pointer = i + 1;
            char c = s1.charAt(i);
            optionEscapeUtil.escape(s, stringbuffer, c, pointer);
        }
    }

    List tokenize()
        throws ScanException
    {
        ArrayList arraylist;
        StringBuffer stringbuffer;
        arraylist = new ArrayList();
        stringbuffer = new StringBuffer();
_L7:
        class _cls1
        {

            static final int $SwitchMap$ch$qos$logback$core$pattern$parser$TokenStream$TokenizerState[];

            static 
            {
                $SwitchMap$ch$qos$logback$core$pattern$parser$TokenStream$TokenizerState = new int[TokenizerState.values().length];
                NoSuchFieldError nosuchfielderror4;
                try
                {
                    $SwitchMap$ch$qos$logback$core$pattern$parser$TokenStream$TokenizerState[TokenizerState.LITERAL_STATE.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$ch$qos$logback$core$pattern$parser$TokenStream$TokenizerState[TokenizerState.FORMAT_MODIFIER_STATE.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$ch$qos$logback$core$pattern$parser$TokenStream$TokenizerState[TokenizerState.OPTION_STATE.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$ch$qos$logback$core$pattern$parser$TokenStream$TokenizerState[TokenizerState.KEYWORD_STATE.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                $SwitchMap$ch$qos$logback$core$pattern$parser$TokenStream$TokenizerState[TokenizerState.RIGHT_PARENTHESIS_STATE.ordinal()] = 5;
_L2:
                return;
                nosuchfielderror4;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        if(pointer < patternLength)
        {
            char c = pattern.charAt(pointer);
            pointer = 1 + pointer;
            switch(_cls1..SwitchMap.ch.qos.logback.core.pattern.parser.TokenStream.TokenizerState[state.ordinal()])
            {
            case 1: // '\001'
                handleLiteralState(c, arraylist, stringbuffer);
                break;

            case 2: // '\002'
                handleFormatModifierState(c, arraylist, stringbuffer);
                break;

            case 3: // '\003'
                processOption(c, arraylist, stringbuffer);
                break;

            case 4: // '\004'
                handleKeywordState(c, arraylist, stringbuffer);
                break;

            case 5: // '\005'
                handleRightParenthesisState(c, arraylist, stringbuffer);
                break;
            }
            continue; /* Loop/switch isn't completed */
        }
        _cls1..SwitchMap.ch.qos.logback.core.pattern.parser.TokenStream.TokenizerState[state.ordinal()];
        JVM INSTR tableswitch 1 5: default 200
    //                   1 202
    //                   2 251
    //                   3 251
    //                   4 214
    //                   5 238;
           goto _L1 _L2 _L3 _L3 _L4 _L5
_L1:
        return arraylist;
_L2:
        addValuedToken(1000, stringbuffer, arraylist);
        continue; /* Loop/switch isn't completed */
_L4:
        arraylist.add(new Token(1004, stringbuffer.toString()));
        continue; /* Loop/switch isn't completed */
_L5:
        arraylist.add(Token.RIGHT_PARENTHESIS_TOKEN);
        if(true) goto _L1; else goto _L3
_L3:
        throw new ScanException("Unexpected end of pattern string");
        if(true) goto _L7; else goto _L6
_L6:
    }

    final IEscapeUtil escapeUtil;
    final IEscapeUtil optionEscapeUtil;
    final String pattern;
    final int patternLength;
    int pointer;
    TokenizerState state;
}
