// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.repackage.brut.androlib.res.decoder;

import ch.qos.logback.repackage.brut.androlib.res.xml.ResXmlEncoders;
import ch.qos.logback.repackage.brut.util.ExtDataInput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StringBlock
{

    private StringBlock()
    {
    }

    private String decodeString(int i, int j)
    {
        String s = null;
        try
        {
            CharsetDecoder charsetdecoder;
            if(m_isUTF8)
                charsetdecoder = UTF8_DECODER;
            else
                charsetdecoder = UTF16LE_DECODER;
            s = charsetdecoder.decode(ByteBuffer.wrap(m_strings, i, j)).toString();
        }
        catch(CharacterCodingException charactercodingexception)
        {
            LOGGER.log(Level.WARNING, null, charactercodingexception);
        }
        return s;
    }

    private static final int getShort(byte abyte0[], int i)
    {
        return (0xff & abyte0[i + 1]) << 8 | 0xff & abyte0[i];
    }

    private static final int getShort(int ai[], int i)
    {
        int j = ai[i / 4];
        int k;
        if((i % 4) / 2 == 0)
            k = j & 0xffff;
        else
            k = j >>> 16;
        return k;
    }

    private int[] getStyle(int i)
    {
        int j = 0;
        if(m_styleOffsets != null && m_styles != null && i < m_styleOffsets.length) goto _L2; else goto _L1
_L1:
        int ai[] = null;
_L4:
        return ai;
_L2:
        int k;
        int ai1[];
        k = m_styleOffsets[i] / 4;
        int l = k;
        int i1 = 0;
        do
        {
            if(l >= m_styles.length || m_styles[l] == -1)
            {
                if(i1 != 0 && i1 % 3 == 0)
                    break;
                ai = null;
                continue; /* Loop/switch isn't completed */
            }
            i1++;
            l++;
        } while(true);
        ai1 = new int[i1];
_L5:
label0:
        {
            if(k < m_styles.length && m_styles[k] != -1)
                break label0;
            ai = ai1;
        }
        if(true) goto _L4; else goto _L3
_L3:
        int j1 = j + 1;
        int ai2[] = m_styles;
        int k1 = k + 1;
        ai1[j] = ai2[k];
        j = j1;
        k = k1;
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    private static final int[] getVarint(byte abyte0[], int i)
    {
        byte byte0 = abyte0[i];
        boolean flag;
        int j;
        int ai[];
        if((byte0 & 0x80) != 0)
            flag = true;
        else
            flag = false;
        j = byte0 & 0x7f;
        if(!flag)
        {
            ai = new int[2];
            ai[0] = j;
            ai[1] = 1;
        } else
        {
            ai = new int[2];
            ai[0] = j << 8 | 0xff & abyte0[i + 1];
            ai[1] = 2;
        }
        return ai;
    }

    private void outputStyleTag(String s, StringBuilder stringbuilder, boolean flag)
    {
        int i;
        stringbuilder.append('<');
        if(flag)
            stringbuilder.append('/');
        i = s.indexOf(';');
        if(i != -1) goto _L2; else goto _L1
_L1:
        stringbuilder.append(s);
_L4:
        stringbuilder.append('>');
        return;
_L2:
        stringbuilder.append(s.substring(0, i));
        if(!flag)
        {
            boolean flag1 = true;
            while(flag1) 
            {
                int j = s.indexOf('=', i + 1);
                stringbuilder.append(' ').append(s.substring(i + 1, j)).append("=\"");
                int k = s.indexOf(';', j + 1);
                String s1;
                if(k != -1)
                {
                    s1 = s.substring(j + 1, k);
                } else
                {
                    s1 = s.substring(j + 1);
                    flag1 = false;
                }
                stringbuilder.append(ResXmlEncoders.escapeXmlChars(s1)).append('"');
                i = k;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static StringBlock read(ExtDataInput extdatainput)
        throws IOException
    {
        extdatainput.skipCheckInt(0x1c0001);
        int i = extdatainput.readInt();
        int j = extdatainput.readInt();
        int k = extdatainput.readInt();
        int l = extdatainput.readInt();
        int i1 = extdatainput.readInt();
        int j1 = extdatainput.readInt();
        StringBlock stringblock = new StringBlock();
        boolean flag;
        int k1;
        int l1;
        if((l & 0x100) != 0)
            flag = true;
        else
            flag = false;
        stringblock.m_isUTF8 = flag;
        stringblock.m_stringOffsets = extdatainput.readIntArray(j);
        if(k != 0)
            stringblock.m_styleOffsets = extdatainput.readIntArray(k);
        if(j1 == 0)
            k1 = i;
        else
            k1 = j1;
        l1 = k1 - i1;
        if(l1 % 4 != 0)
            throw new IOException((new StringBuilder()).append("String data size is not multiple of 4 (").append(l1).append(").").toString());
        stringblock.m_strings = new byte[l1];
        extdatainput.readFully(stringblock.m_strings);
        if(j1 != 0)
        {
            int i2 = i - j1;
            if(i2 % 4 != 0)
                throw new IOException((new StringBuilder()).append("Style data size is not multiple of 4 (").append(i2).append(").").toString());
            stringblock.m_styles = extdatainput.readIntArray(i2 / 4);
        }
        return stringblock;
    }

    public int find(String s)
    {
        if(s != null) goto _L2; else goto _L1
_L1:
        int i = -1;
_L9:
        return i;
_L2:
        i = 0;
_L4:
        int j;
        int k;
        if(i == m_stringOffsets.length)
            break MISSING_BLOCK_LABEL_102;
        j = m_stringOffsets[i];
        k = getShort(m_strings, j);
        if(k == s.length())
            break; /* Loop/switch isn't completed */
_L6:
        i++;
        if(true) goto _L4; else goto _L3
_L3:
        int l;
        int i1;
        l = j;
        i1 = 0;
_L7:
        if(i1 == k)
            continue; /* Loop/switch isn't completed */
        l += 2;
        if(s.charAt(i1) == getShort(m_strings, l))
            break MISSING_BLOCK_LABEL_96;
        if(i1 != k) goto _L6; else goto _L5
_L5:
        continue; /* Loop/switch isn't completed */
        i1++;
          goto _L7
        i = -1;
        if(true) goto _L9; else goto _L8
_L8:
    }

    public CharSequence get(int i)
    {
        return getString(i);
    }

    public int getCount()
    {
        int i;
        if(m_stringOffsets != null)
            i = m_stringOffsets.length;
        else
            i = 0;
        return i;
    }

    public String getHTML(int i)
    {
        String s;
        int ai[];
        String s1;
        s = getString(i);
        if(s == null)
        {
            s1 = s;
        } else
        {
label0:
            {
                ai = getStyle(i);
                if(ai != null)
                    break label0;
                s1 = ResXmlEncoders.escapeXmlChars(s);
            }
        }
_L1:
        return s1;
        StringBuilder stringbuilder;
        int ai1[];
        int j;
        int k;
        stringbuilder = new StringBuilder(32 + s.length());
        ai1 = new int[ai.length / 3];
        j = 0;
        k = 0;
_L3:
        int i1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        int l = 0;
        i1 = -1;
        do
        {
            if(l == ai.length)
                break;
            if(ai[l + 1] != -1 && (i1 == -1 || ai[i1 + 1] > ai[l + 1]))
                i1 = l;
            l += 3;
        } while(true);
        int j1;
        int k1;
        if(i1 != -1)
            j1 = ai[i1 + 1];
        else
            j1 = s.length();
        k1 = j - 1;
        l1 = k;
        i2 = k1;
_L2:
label1:
        {
            if(i2 >= 0)
            {
                k2 = ai1[i2];
                l2 = ai[k2 + 2];
                if(l2 < j1)
                    break label1;
            }
            j2 = i2 + 1;
            if(l1 < j1)
            {
                stringbuilder.append(ResXmlEncoders.escapeXmlChars(s.substring(l1, j1)));
                k = j1;
            } else
            {
                k = l1;
            }
            if(i1 != -1)
                break MISSING_BLOCK_LABEL_294;
            s1 = stringbuilder.toString();
        }
          goto _L1
        if(l1 <= l2)
        {
            stringbuilder.append(ResXmlEncoders.escapeXmlChars(s.substring(l1, l2 + 1)));
            l1 = l2 + 1;
        }
        outputStyleTag(getString(ai[k2]), stringbuilder, true);
        i2--;
          goto _L2
        outputStyleTag(getString(ai[i1]), stringbuilder, false);
        ai[i1 + 1] = -1;
        j = j2 + 1;
        ai1[j2] = i1;
          goto _L3
    }

    public String getString(int i)
    {
        String s;
        if(i < 0 || m_stringOffsets == null || i >= m_stringOffsets.length)
        {
            s = null;
        } else
        {
            int j = m_stringOffsets[i];
            int l;
            int i1;
            if(!m_isUTF8)
            {
                i1 = 2 * getShort(m_strings, j);
                l = j + 2;
            } else
            {
                int k = j + getVarint(m_strings, j)[1];
                int ai[] = getVarint(m_strings, k);
                l = k + ai[1];
                i1 = ai[0];
            }
            s = decodeString(l, i1);
        }
        return s;
    }

    private static final int CHUNK_TYPE = 0x1c0001;
    private static final Logger LOGGER = Logger.getLogger(ch/qos/logback/repackage/brut/androlib/res/decoder/StringBlock.getName());
    private static final CharsetDecoder UTF16LE_DECODER = Charset.forName("UTF-16LE").newDecoder();
    private static final CharsetDecoder UTF8_DECODER = Charset.forName("UTF-8").newDecoder();
    private static final int UTF8_FLAG = 256;
    private boolean m_isUTF8;
    private int m_stringOffsets[];
    private byte m_strings[];
    private int m_styleOffsets[];
    private int m_styles[];

}
