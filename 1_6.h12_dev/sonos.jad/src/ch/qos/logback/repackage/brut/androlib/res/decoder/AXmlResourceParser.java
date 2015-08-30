// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.repackage.brut.androlib.res.decoder;

import android.content.res.XmlResourceParser;
import android.util.TypedValue;
import ch.qos.logback.repackage.brut.androlib.AndrolibException;
import ch.qos.logback.repackage.brut.androlib.res.xml.ResXmlEncoders;
import ch.qos.logback.repackage.brut.util.ExtDataInput;
import ch.qos.logback.repackage.com.mindprod.ledatastream.LEDataInputStream;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package ch.qos.logback.repackage.brut.androlib.res.decoder:
//            ResAttrDecoder, StringBlock

public class AXmlResourceParser
    implements XmlResourceParser
{
    private static final class NamespaceStack
    {

        private void ensureDataCapacity(int i)
        {
            int j = m_data.length - m_dataLength;
            if(j <= i)
            {
                int ai[] = new int[2 * (j + m_data.length)];
                System.arraycopy(m_data, 0, ai, 0, m_dataLength);
                m_data = ai;
            }
        }

        private final int find(int i, boolean flag)
        {
            int j = -1;
            if(m_dataLength != 0) goto _L2; else goto _L1
_L1:
            return j;
_L2:
            int k;
            int l;
            k = -1 + m_dataLength;
            l = m_depth;
_L7:
            if(l == 0) goto _L1; else goto _L3
_L3:
            int i1;
            i1 = m_data[k];
            k -= 2;
_L6:
            if(i1 == 0) goto _L5; else goto _L4
_L4:
            if(flag)
            {
                if(m_data[k] != i)
                    break MISSING_BLOCK_LABEL_101;
                j = m_data[k + 1];
            } else
            {
                if(m_data[k + 1] != i)
                    break MISSING_BLOCK_LABEL_101;
                j = m_data[k];
            }
              goto _L1
            k -= 2;
            i1--;
              goto _L6
_L5:
            l--;
              goto _L7
        }

        private final int get(int i, boolean flag)
        {
            int j = -1;
            if(m_dataLength != 0 && i >= 0) goto _L2; else goto _L1
_L1:
            return j;
_L2:
            int k = 0;
            int l = m_depth;
            do
            {
                if(l == 0)
                    continue; /* Loop/switch isn't completed */
                int i1 = m_data[k];
                if(i < i1)
                    break;
                i -= i1;
                k += 2 + i1 * 2;
                l--;
            } while(true);
            int j1 = k + (1 + i * 2);
            if(!flag)
                j1++;
            j = m_data[j1];
            if(true) goto _L1; else goto _L3
_L3:
        }

        public final void decreaseDepth()
        {
            if(m_dataLength != 0) goto _L2; else goto _L1
_L1:
            return;
_L2:
            int i = -1 + m_dataLength;
            int j = m_data[i];
            if(i - 1 - j * 2 != 0)
            {
                m_dataLength = m_dataLength - (2 + j * 2);
                m_count = m_count - j;
                m_depth = -1 + m_depth;
            }
            if(true) goto _L1; else goto _L3
_L3:
        }

        public final int findPrefix(int i)
        {
            return find(i, false);
        }

        public final int findUri(int i)
        {
            return find(i, true);
        }

        public final int getAccumulatedCount(int i)
        {
            int j = 0;
            if(m_dataLength != 0 && i >= 0)
            {
                if(i > m_depth)
                    i = m_depth;
                int k = 0;
                while(i != 0) 
                {
                    int l = m_data[k];
                    int i1 = j + l;
                    int j1 = k + (2 + l * 2);
                    i--;
                    k = j1;
                    j = i1;
                }
            }
            return j;
        }

        public final int getCurrentCount()
        {
            int j;
            if(m_dataLength == 0)
            {
                j = 0;
            } else
            {
                int i = -1 + m_dataLength;
                j = m_data[i];
            }
            return j;
        }

        public final int getDepth()
        {
            return m_depth;
        }

        public final int getPrefix(int i)
        {
            return get(i, true);
        }

        public final int getTotalCount()
        {
            return m_count;
        }

        public final int getUri(int i)
        {
            return get(i, false);
        }

        public final void increaseDepth()
        {
            ensureDataCapacity(2);
            int i = m_dataLength;
            m_data[i] = 0;
            m_data[i + 1] = 0;
            m_dataLength = 2 + m_dataLength;
            m_depth = 1 + m_depth;
        }

        public final boolean pop()
        {
            boolean flag = false;
            if(m_dataLength != 0) goto _L2; else goto _L1
_L1:
            return flag;
_L2:
            int i = -1 + m_dataLength;
            int j = m_data[i];
            if(j != 0)
            {
                int k = j - 1;
                int l = i - 2;
                m_data[l] = k;
                int i1 = l - (1 + k * 2);
                m_data[i1] = k;
                m_dataLength = -2 + m_dataLength;
                m_count = -1 + m_count;
                flag = true;
            }
            if(true) goto _L1; else goto _L3
_L3:
        }

        public final boolean pop(int i, int j)
        {
            boolean flag = false;
            if(m_dataLength != 0) goto _L2; else goto _L1
_L1:
            return flag;
_L2:
            int k = -1 + m_dataLength;
            int l = m_data[k];
            int i1 = k - 2;
            int j1 = 0;
            do
            {
                if(j1 == l)
                    continue; /* Loop/switch isn't completed */
                if(m_data[i1] == i && m_data[i1 + 1] == j)
                    break;
                j1++;
                i1 -= 2;
            } while(true);
            int k1 = l - 1;
            if(j1 == 0)
            {
                m_data[i1] = k1;
                int i2 = i1 - (1 + k1 * 2);
                m_data[i2] = k1;
            } else
            {
                m_data[k] = k1;
                int l1 = k - (3 + k1 * 2);
                m_data[l1] = k1;
                System.arraycopy(m_data, i1 + 2, m_data, i1, m_dataLength - i1);
            }
            m_dataLength = -2 + m_dataLength;
            m_count = -1 + m_count;
            flag = true;
            if(true) goto _L1; else goto _L3
_L3:
        }

        public final void push(int i, int j)
        {
            if(m_depth == 0)
                increaseDepth();
            ensureDataCapacity(2);
            int k = -1 + m_dataLength;
            int l = m_data[k];
            m_data[k - 1 - l * 2] = l + 1;
            m_data[k] = i;
            m_data[k + 1] = j;
            m_data[k + 2] = l + 1;
            m_dataLength = 2 + m_dataLength;
            m_count = 1 + m_count;
        }

        public final void reset()
        {
            m_dataLength = 0;
            m_count = 0;
            m_depth = 0;
        }

        private int m_count;
        private int m_data[];
        private int m_dataLength;
        private int m_depth;

        public NamespaceStack()
        {
            m_data = new int[32];
        }
    }


    public AXmlResourceParser()
    {
        mAttrDecoder = new ResAttrDecoder();
        m_operational = false;
        m_namespaces = new NamespaceStack();
        resetEventInfo();
    }

    public AXmlResourceParser(InputStream inputstream)
    {
        this();
        open(inputstream);
    }

    private final void doNext()
        throws IOException
    {
        int i;
        i = 3;
        if(m_strings == null)
        {
            m_reader.skipCheckInt(0x80003);
            m_reader.skipInt();
            m_strings = StringBlock.read(m_reader);
            m_namespaces.increaseDepth();
            m_operational = true;
        }
        if(m_event != 1) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int j = m_event;
        resetEventInfo();
        int k;
        do
        {
            int l;
            do
            {
                if(m_decreaseDepth)
                {
                    m_decreaseDepth = false;
                    m_namespaces.decreaseDepth();
                }
                if(j == i && m_namespaces.getDepth() == 1 && m_namespaces.getCurrentCount() == 0)
                {
                    m_event = 1;
                    continue; /* Loop/switch isn't completed */
                }
                if(j == 0)
                    k = 0x100102;
                else
                    k = m_reader.readInt();
                if(k == 0x80180)
                {
                    int i2 = m_reader.readInt();
                    if(i2 < 8 || i2 % 4 != 0)
                        throw new IOException((new StringBuilder()).append("Invalid resource ids size (").append(i2).append(").").toString());
                    m_resourceIDs = m_reader.readIntArray(-2 + i2 / 4);
                    continue;
                }
                if(k < 0x100100 || k > 0x100104)
                    throw new IOException((new StringBuilder()).append("Invalid chunk type (").append(k).append(").").toString());
                if(k == 0x100102 && j == -1)
                {
                    m_event = 0;
                    continue; /* Loop/switch isn't completed */
                }
                m_reader.skipInt();
                l = m_reader.readInt();
                m_reader.skipInt();
                if(k != 0x100100 && k != 0x100101)
                    break;
                if(k == 0x100100)
                {
                    int i1 = m_reader.readInt();
                    int j1 = m_reader.readInt();
                    m_namespaces.push(i1, j1);
                } else
                {
                    m_reader.skipInt();
                    m_reader.skipInt();
                    m_namespaces.pop();
                }
            } while(true);
            m_lineNumber = l;
            if(k == 0x100102)
            {
                m_namespaceUri = m_reader.readInt();
                m_name = m_reader.readInt();
                m_reader.skipInt();
                int k1 = m_reader.readInt();
                m_idAttribute = -1 + (k1 >>> 16);
                int l1 = k1 & 0xffff;
                m_classAttribute = m_reader.readInt();
                m_styleAttribute = -1 + (m_classAttribute >>> 16);
                m_classAttribute = -1 + (0xffff & m_classAttribute);
                for(m_attributes = m_reader.readIntArray(l1 * 5); i < m_attributes.length; i += 5)
                    m_attributes[i] = m_attributes[i] >>> 24;

                m_namespaces.increaseDepth();
                m_event = 2;
            } else
            {
                if(k != 0x100103)
                    continue;
                m_namespaceUri = m_reader.readInt();
                m_name = m_reader.readInt();
                m_event = i;
                m_decreaseDepth = true;
            }
            continue; /* Loop/switch isn't completed */
        } while(k != 0x100104);
        m_name = m_reader.readInt();
        m_reader.skipInt();
        m_reader.skipInt();
        m_event = 4;
        if(true) goto _L1; else goto _L3
_L3:
    }

    private final int findAttribute(String s, String s1)
    {
        int i = -1;
        if(m_strings != null && s1 != null) goto _L2; else goto _L1
_L1:
        return i;
_L2:
        int l;
        int j = m_strings.find(s1);
        if(j == i)
            continue; /* Loop/switch isn't completed */
        int k;
        if(s != null)
            k = m_strings.find(s);
        else
            k = i;
        l = 0;
_L4:
        if(l != m_attributes.length)
        {
label0:
            {
                if(j != m_attributes[l + 1] || k != i && k != m_attributes[l + 0])
                    break label0;
                i = l / 5;
            }
        }
        if(true) goto _L1; else goto _L3
_L3:
        l++;
          goto _L4
        if(true) goto _L1; else goto _L5
_L5:
    }

    private final int getAttributeOffset(int i)
    {
        if(m_event != 2)
            throw new IndexOutOfBoundsException("Current event is not START_TAG.");
        int j = i * 5;
        if(j >= m_attributes.length)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("Invalid attribute index (").append(i).append(").").toString());
        else
            return j;
    }

    private final void resetEventInfo()
    {
        m_event = -1;
        m_lineNumber = -1;
        m_name = -1;
        m_namespaceUri = -1;
        m_attributes = null;
        m_idAttribute = -1;
        m_classAttribute = -1;
        m_styleAttribute = -1;
    }

    private void setFirstError(AndrolibException androlibexception)
    {
        if(mFirstError == null)
            mFirstError = androlibexception;
    }

    public void close()
    {
        if(m_operational)
        {
            m_operational = false;
            m_reader = null;
            m_strings = null;
            m_resourceIDs = null;
            m_namespaces.reset();
            resetEventInfo();
        }
    }

    public void defineEntityReplacementText(String s, String s1)
        throws XmlPullParserException
    {
        throw new XmlPullParserException("Method is not supported.");
    }

    public ResAttrDecoder getAttrDecoder()
    {
        return mAttrDecoder;
    }

    public boolean getAttributeBooleanValue(int i, boolean flag)
    {
        boolean flag1 = true;
        int j;
        if(flag)
            j = ((flag1) ? 1 : 0);
        else
            j = 0;
        if(getAttributeIntValue(i, j) == 0)
            flag1 = false;
        return flag1;
    }

    public boolean getAttributeBooleanValue(String s, String s1, boolean flag)
    {
        int i = findAttribute(s, s1);
        if(i != -1)
            flag = getAttributeBooleanValue(i, flag);
        return flag;
    }

    public int getAttributeCount()
    {
        int i;
        if(m_event != 2)
            i = -1;
        else
            i = m_attributes.length / 5;
        return i;
    }

    public float getAttributeFloatValue(int i, float f)
    {
        int j = getAttributeOffset(i);
        if(m_attributes[j + 3] == 4)
            f = Float.intBitsToFloat(m_attributes[j + 4]);
        return f;
    }

    public float getAttributeFloatValue(String s, String s1, float f)
    {
        int i = findAttribute(s, s1);
        if(i != -1)
            f = getAttributeFloatValue(i, f);
        return f;
    }

    public int getAttributeIntValue(int i, int j)
    {
        int k = getAttributeOffset(i);
        int l = m_attributes[k + 3];
        if(l >= 16 && l <= 31)
            j = m_attributes[k + 4];
        return j;
    }

    public int getAttributeIntValue(String s, String s1, int i)
    {
        int j = findAttribute(s, s1);
        if(j != -1)
            i = getAttributeIntValue(j, i);
        return i;
    }

    public int getAttributeListValue(int i, String as[], int j)
    {
        return 0;
    }

    public int getAttributeListValue(String s, String s1, String as[], int i)
    {
        return 0;
    }

    public String getAttributeName(int i)
    {
        int j = getAttributeOffset(i);
        int k = m_attributes[j + 1];
        String s;
        if(k == -1)
            s = "";
        else
            s = m_strings.getString(k);
        return s;
    }

    public int getAttributeNameResource(int i)
    {
        int j = getAttributeOffset(i);
        int k = m_attributes[j + 1];
        int l;
        if(m_resourceIDs == null || k < 0 || k >= m_resourceIDs.length)
            l = 0;
        else
            l = m_resourceIDs[k];
        return l;
    }

    public String getAttributeNamespace(int i)
    {
        int j = getAttributeOffset(i);
        int k = m_attributes[j + 0];
        String s;
        if(k == -1)
            s = "";
        else
            s = m_strings.getString(k);
        return s;
    }

    public String getAttributePrefix(int i)
    {
        int j = getAttributeOffset(i);
        int k = m_attributes[j + 0];
        int l = m_namespaces.findPrefix(k);
        String s;
        if(l == -1)
            s = "";
        else
            s = m_strings.getString(l);
        return s;
    }

    public int getAttributeResourceValue(int i, int j)
    {
        int k = getAttributeOffset(i);
        if(m_attributes[k + 3] == 1)
            j = m_attributes[k + 4];
        return j;
    }

    public int getAttributeResourceValue(String s, String s1, int i)
    {
        int j = findAttribute(s, s1);
        if(j != -1)
            i = getAttributeResourceValue(j, i);
        return i;
    }

    public String getAttributeType(int i)
    {
        return "CDATA";
    }

    public int getAttributeUnsignedIntValue(int i, int j)
    {
        return getAttributeIntValue(i, j);
    }

    public int getAttributeUnsignedIntValue(String s, String s1, int i)
    {
        int j = findAttribute(s, s1);
        if(j != -1)
            i = getAttributeUnsignedIntValue(j, i);
        return i;
    }

    public String getAttributeValue(int i)
    {
        int k;
        int l;
        int i1;
        int j = getAttributeOffset(i);
        k = m_attributes[j + 3];
        l = m_attributes[j + 4];
        i1 = m_attributes[j + 2];
        if(mAttrDecoder == null)
            break MISSING_BLOCK_LABEL_167;
        ResAttrDecoder resattrdecoder = mAttrDecoder;
        if(i1 != -1) goto _L2; else goto _L1
_L1:
        String s2 = null;
_L3:
        String s;
        s = resattrdecoder.decode(k, l, s2, getAttributeNameResource(i));
        break MISSING_BLOCK_LABEL_175;
_L2:
        String s1 = ResXmlEncoders.escapeXmlChars(m_strings.getString(i1));
        s2 = s1;
          goto _L3
        AndrolibException androlibexception;
        androlibexception;
        setFirstError(androlibexception);
        Logger logger = LOGGER;
        Level level = Level.WARNING;
        Object aobj[] = new Object[3];
        aobj[0] = getAttributePrefix(i);
        aobj[1] = getAttributeName(i);
        aobj[2] = Integer.valueOf(l);
        logger.log(level, String.format("Could not decode attr value, using undecoded value instead: ns=%s, name=%s, value=0x%08x", aobj), androlibexception);
        s = TypedValue.coerceToString(k, l);
        return s;
    }

    public String getAttributeValue(String s, String s1)
    {
        int i = findAttribute(s, s1);
        String s2;
        if(i == -1)
            s2 = null;
        else
            s2 = getAttributeValue(i);
        return s2;
    }

    public int getAttributeValueData(int i)
    {
        int j = getAttributeOffset(i);
        return m_attributes[j + 4];
    }

    public int getAttributeValueType(int i)
    {
        int j = getAttributeOffset(i);
        return m_attributes[j + 3];
    }

    public String getClassAttribute()
    {
        String s;
        if(m_classAttribute == -1)
        {
            s = null;
        } else
        {
            int i = getAttributeOffset(m_classAttribute);
            int j = m_attributes[i + 2];
            s = m_strings.getString(j);
        }
        return s;
    }

    public int getColumnNumber()
    {
        return -1;
    }

    public int getDepth()
    {
        return -1 + m_namespaces.getDepth();
    }

    public int getEventType()
        throws XmlPullParserException
    {
        return m_event;
    }

    public boolean getFeature(String s)
    {
        return false;
    }

    public AndrolibException getFirstError()
    {
        return mFirstError;
    }

    public String getIdAttribute()
    {
        String s;
        if(m_idAttribute == -1)
        {
            s = null;
        } else
        {
            int i = getAttributeOffset(m_idAttribute);
            int j = m_attributes[i + 2];
            s = m_strings.getString(j);
        }
        return s;
    }

    public int getIdAttributeResourceValue(int i)
    {
        if(m_idAttribute != -1) goto _L2; else goto _L1
_L1:
        return i;
_L2:
        int j = getAttributeOffset(m_idAttribute);
        if(m_attributes[j + 3] == 1)
            i = m_attributes[j + 4];
        if(true) goto _L1; else goto _L3
_L3:
    }

    public String getInputEncoding()
    {
        return null;
    }

    public int getLineNumber()
    {
        return m_lineNumber;
    }

    public String getName()
    {
        String s;
        if(m_name == -1 || m_event != 2 && m_event != 3)
            s = null;
        else
            s = m_strings.getString(m_name);
        return s;
    }

    public String getNamespace()
    {
        return m_strings.getString(m_namespaceUri);
    }

    public String getNamespace(String s)
    {
        throw new RuntimeException("Method is not supported.");
    }

    public int getNamespaceCount(int i)
        throws XmlPullParserException
    {
        return m_namespaces.getAccumulatedCount(i);
    }

    public String getNamespacePrefix(int i)
        throws XmlPullParserException
    {
        int j = m_namespaces.getPrefix(i);
        return m_strings.getString(j);
    }

    public String getNamespaceUri(int i)
        throws XmlPullParserException
    {
        int j = m_namespaces.getUri(i);
        return m_strings.getString(j);
    }

    public String getPositionDescription()
    {
        return (new StringBuilder()).append("XML line #").append(getLineNumber()).toString();
    }

    public String getPrefix()
    {
        int i = m_namespaces.findPrefix(m_namespaceUri);
        return m_strings.getString(i);
    }

    public Object getProperty(String s)
    {
        return null;
    }

    final StringBlock getStrings()
    {
        return m_strings;
    }

    public int getStyleAttribute()
    {
        int j;
        if(m_styleAttribute == -1)
        {
            j = 0;
        } else
        {
            int i = getAttributeOffset(m_styleAttribute);
            j = m_attributes[i + 4];
        }
        return j;
    }

    public String getText()
    {
        String s;
        if(m_name == -1 || m_event != 4)
            s = null;
        else
            s = m_strings.getString(m_name);
        return s;
    }

    public char[] getTextCharacters(int ai[])
    {
        String s = getText();
        char ac[];
        if(s == null)
        {
            ac = null;
        } else
        {
            ai[0] = 0;
            ai[1] = s.length();
            ac = new char[s.length()];
            s.getChars(0, s.length(), ac, 0);
        }
        return ac;
    }

    public boolean isAttributeDefault(int i)
    {
        return false;
    }

    public boolean isEmptyElementTag()
        throws XmlPullParserException
    {
        return false;
    }

    public boolean isWhitespace()
        throws XmlPullParserException
    {
        return false;
    }

    public int next()
        throws XmlPullParserException, IOException
    {
        if(m_reader == null)
            throw new XmlPullParserException("Parser is not opened.", this, null);
        int i;
        try
        {
            doNext();
            i = m_event;
        }
        catch(IOException ioexception)
        {
            close();
            throw ioexception;
        }
        return i;
    }

    public int nextTag()
        throws XmlPullParserException, IOException
    {
        int i = next();
        if(i == 4 && isWhitespace())
            i = next();
        if(i != 2 && i != 3)
            throw new XmlPullParserException("Expected start or end tag.", this, null);
        else
            return i;
    }

    public String nextText()
        throws XmlPullParserException, IOException
    {
label0:
        {
            if(getEventType() != 2)
                throw new XmlPullParserException("Parser must be on START_TAG to read next text.", this, null);
            int i = next();
            String s;
            if(i == 4)
            {
                s = getText();
                if(next() != 3)
                    throw new XmlPullParserException("Event TEXT must be immediately followed by END_TAG.", this, null);
            } else
            {
                if(i != 3)
                    break label0;
                s = "";
            }
            return s;
        }
        throw new XmlPullParserException("Parser must be on START_TAG or TEXT to read text.", this, null);
    }

    public int nextToken()
        throws XmlPullParserException, IOException
    {
        return next();
    }

    public void open(InputStream inputstream)
    {
        close();
        if(inputstream != null)
            m_reader = new ExtDataInput(new LEDataInputStream(inputstream));
    }

    public void require(int i, String s, String s1)
        throws XmlPullParserException, IOException
    {
        if(i != getEventType() || s != null && !s.equals(getNamespace()) || s1 != null && !s1.equals(getName()))
            throw new XmlPullParserException((new StringBuilder()).append(TYPES[i]).append(" is expected.").toString(), this, null);
        else
            return;
    }

    public void setAttrDecoder(ResAttrDecoder resattrdecoder)
    {
        mAttrDecoder = resattrdecoder;
    }

    public void setFeature(String s, boolean flag)
        throws XmlPullParserException
    {
        throw new XmlPullParserException("Method is not supported.");
    }

    public void setInput(InputStream inputstream, String s)
        throws XmlPullParserException
    {
        open(inputstream);
    }

    public void setInput(Reader reader)
        throws XmlPullParserException
    {
        throw new XmlPullParserException("Method is not supported.");
    }

    public void setProperty(String s, Object obj)
        throws XmlPullParserException
    {
        throw new XmlPullParserException("Method is not supported.");
    }

    private static final int ATTRIBUTE_IX_NAME = 1;
    private static final int ATTRIBUTE_IX_NAMESPACE_URI = 0;
    private static final int ATTRIBUTE_IX_VALUE_DATA = 4;
    private static final int ATTRIBUTE_IX_VALUE_STRING = 2;
    private static final int ATTRIBUTE_IX_VALUE_TYPE = 3;
    private static final int ATTRIBUTE_LENGHT = 5;
    private static final int CHUNK_AXML_FILE = 0x80003;
    private static final int CHUNK_RESOURCEIDS = 0x80180;
    private static final int CHUNK_XML_END_NAMESPACE = 0x100101;
    private static final int CHUNK_XML_END_TAG = 0x100103;
    private static final int CHUNK_XML_FIRST = 0x100100;
    private static final int CHUNK_XML_LAST = 0x100104;
    private static final int CHUNK_XML_START_NAMESPACE = 0x100100;
    private static final int CHUNK_XML_START_TAG = 0x100102;
    private static final int CHUNK_XML_TEXT = 0x100104;
    private static final String E_NOT_SUPPORTED = "Method is not supported.";
    private static final Logger LOGGER = Logger.getLogger(ch/qos/logback/repackage/brut/androlib/res/decoder/AXmlResourceParser.getName());
    private ResAttrDecoder mAttrDecoder;
    private AndrolibException mFirstError;
    private int m_attributes[];
    private int m_classAttribute;
    private boolean m_decreaseDepth;
    private int m_event;
    private int m_idAttribute;
    private int m_lineNumber;
    private int m_name;
    private int m_namespaceUri;
    private NamespaceStack m_namespaces;
    private boolean m_operational;
    private ExtDataInput m_reader;
    private int m_resourceIDs[];
    private StringBlock m_strings;
    private int m_styleAttribute;

}
