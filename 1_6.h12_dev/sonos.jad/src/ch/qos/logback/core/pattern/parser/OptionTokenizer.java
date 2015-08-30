// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.pattern.parser;

import ch.qos.logback.core.pattern.util.AsIsEscapeUtil;
import ch.qos.logback.core.pattern.util.IEscapeUtil;
import ch.qos.logback.core.spi.ScanException;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package ch.qos.logback.core.pattern.parser:
//            TokenStream, Token

public class OptionTokenizer
{

    OptionTokenizer(TokenStream tokenstream)
    {
        this(tokenstream, ((IEscapeUtil) (new AsIsEscapeUtil())));
    }

    OptionTokenizer(TokenStream tokenstream, IEscapeUtil iescapeutil)
    {
        state = 0;
        tokenStream = tokenstream;
        pattern = tokenstream.pattern;
        patternLength = tokenstream.patternLength;
        escapeUtil = iescapeutil;
    }

    void emitOptionToken(List list, List list1)
    {
        list.add(new Token(1006, list1));
        tokenStream.state = TokenStream.TokenizerState.LITERAL_STATE;
    }

    void escape(String s, StringBuffer stringbuffer)
    {
        if(tokenStream.pointer < patternLength)
        {
            String s1 = pattern;
            TokenStream tokenstream = tokenStream;
            int i = tokenstream.pointer;
            tokenstream.pointer = i + 1;
            char c = s1.charAt(i);
            escapeUtil.escape(s, stringbuffer, c, tokenStream.pointer);
        }
    }

    void tokenize(char c, List list)
        throws ScanException
    {
        StringBuffer stringbuffer;
        ArrayList arraylist;
        stringbuffer = new StringBuffer();
        arraylist = new ArrayList();
_L7:
        if(tokenStream.pointer >= patternLength) goto _L2; else goto _L1
_L1:
        state;
        JVM INSTR tableswitch 0 2: default 60
    //                   0 96
    //                   1 207
    //                   2 298;
           goto _L3 _L4 _L5 _L6
_L3:
        c = pattern.charAt(tokenStream.pointer);
        TokenStream tokenstream = tokenStream;
        tokenstream.pointer = 1 + tokenstream.pointer;
          goto _L7
_L4:
        c;
        JVM INSTR lookupswitch 8: default 172
    //                   9: 60
    //                   10: 60
    //                   13: 60
    //                   32: 60
    //                   34: 186
    //                   39: 186
    //                   44: 60
    //                   125: 199;
           goto _L8 _L3 _L3 _L3 _L3 _L9 _L9 _L3 _L10
_L8:
        stringbuffer.append(c);
        state = 1;
          goto _L3
_L9:
        state = 2;
        quoteChar = c;
          goto _L3
_L10:
        emitOptionToken(list, arraylist);
_L14:
        return;
_L5:
        c;
        JVM INSTR lookupswitch 2: default 236
    //                   44: 245
    //                   125: 273;
           goto _L11 _L12 _L13
_L11:
        stringbuffer.append(c);
          goto _L3
_L12:
        arraylist.add(stringbuffer.toString().trim());
        stringbuffer.setLength(0);
        state = 0;
          goto _L3
_L13:
        arraylist.add(stringbuffer.toString().trim());
        emitOptionToken(list, arraylist);
          goto _L14
_L6:
        if(c == quoteChar)
        {
            arraylist.add(stringbuffer.toString());
            stringbuffer.setLength(0);
            state = 0;
        } else
        if(c == '\\')
            escape(String.valueOf(quoteChar), stringbuffer);
        else
            stringbuffer.append(c);
          goto _L3
_L2:
        if(c == '}')
        {
            if(state == 0)
                emitOptionToken(list, arraylist);
            else
            if(state == 1)
            {
                arraylist.add(stringbuffer.toString().trim());
                emitOptionToken(list, arraylist);
            } else
            {
                throw new ScanException("Unexpected end of pattern string in OptionTokenizer");
            }
        } else
        {
            throw new ScanException("Unexpected end of pattern string in OptionTokenizer");
        }
          goto _L14
    }

    private static final int EXPECTING_STATE = 0;
    private static final int QUOTED_COLLECTING_STATE = 2;
    private static final int RAW_COLLECTING_STATE = 1;
    final IEscapeUtil escapeUtil;
    final String pattern;
    final int patternLength;
    char quoteChar;
    int state;
    final TokenStream tokenStream;
}
