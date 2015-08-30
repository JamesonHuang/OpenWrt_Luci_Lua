// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.caverock.androidsvg;

import android.util.Log;
import java.util.*;
import org.xml.sax.SAXException;

// Referenced classes of package com.caverock.androidsvg:
//            SVGParser

public class CSSParser
{
    private static class CSSTextScanner extends SVGParser.TextScanner
    {

        private String nextAttribValue()
        {
            if(!empty()) goto _L2; else goto _L1
_L1:
            String s = null;
_L4:
            return s;
_L2:
            s = nextQuotedString();
            if(s == null)
                s = nextIdentifier();
            if(true) goto _L4; else goto _L3
_L3:
        }

        private int scanForIdentifier()
        {
            int j;
            if(empty())
            {
                j = position;
            } else
            {
                int i = position;
                j = position;
                int k = input.charAt(position);
                if(k == 45)
                    k = advanceChar();
                if(k >= 65 && k <= 90 || k >= 97 && k <= 122 || k == 95)
                {
                    for(int l = advanceChar(); l >= 65 && l <= 90 || l >= 97 && l <= 122 || l >= 48 && l <= 57 || l == 45 || l == 95; l = advanceChar());
                    j = position;
                }
                position = i;
            }
            return j;
        }

        public String nextIdentifier()
        {
            int i = scanForIdentifier();
            String s;
            if(i == position)
            {
                s = null;
            } else
            {
                s = input.substring(position, i);
                position = i;
            }
            return s;
        }

        public String nextPropertyValue()
        {
            String s = null;
            if(!empty())
            {
                int i = position;
                int j = position;
                for(int k = input.charAt(position); k != '\uFFFF' && k != ';' && k != '}' && k != '!' && !isEOL(k); k = advanceChar())
                    if(!isWhitespace(k))
                        j = 1 + position;

                if(position > i)
                    s = input.substring(i, j);
                else
                    position = i;
            }
            return s;
        }

        public boolean nextSimpleSelector(Selector selector)
            throws SAXException
        {
            boolean flag = false;
            if(!empty()) goto _L2; else goto _L1
_L1:
            return flag;
_L2:
            int i;
            Combinator combinator;
            SimpleSelector simpleselector;
            i = position;
            combinator = null;
            simpleselector = null;
            if(!selector.isEmpty())
                if(consume('>'))
                {
                    combinator = Combinator.CHILD;
                    skipWhitespace();
                } else
                if(consume('+'))
                {
                    combinator = Combinator.FOLLOWS;
                    skipWhitespace();
                }
            if(consume('*'))
            {
                simpleselector = new SimpleSelector(combinator, null);
            } else
            {
                String s4 = nextIdentifier();
                if(s4 != null)
                {
                    simpleselector = new SimpleSelector(combinator, s4);
                    selector.addedElement();
                }
            }
_L5:
            for(; !empty(); selector.addedAttributeOrPseudo())
            {
                if(!consume('.'))
                    break MISSING_BLOCK_LABEL_193;
                if(simpleselector == null)
                    simpleselector = new SimpleSelector(combinator, null);
                String s3 = nextIdentifier();
                if(s3 == null)
                    throw new SAXException("Invalid \".class\" selector in <style> element");
                simpleselector.addAttrib("class", AttribOp.EQUALS, s3);
            }

              goto _L3
            if(consume('#'))
            {
                if(simpleselector == null)
                    simpleselector = new SimpleSelector(combinator, null);
                String s2 = nextIdentifier();
                if(s2 == null)
                    throw new SAXException("Invalid \"#id\" selector in <style> element");
                simpleselector.addAttrib("id", AttribOp.EQUALS, s2);
                selector.addedIdAttribute();
            }
            if(simpleselector != null) goto _L4; else goto _L3
_L3:
            int j;
            String s;
            String s1;
            AttribOp attribop;
            if(simpleselector != null)
            {
                selector.add(simpleselector);
                flag = true;
            } else
            {
                position = i;
            }
              goto _L1
_L4:
label0:
            {
                if(!consume('['))
                    break label0;
                skipWhitespace();
                s = nextIdentifier();
                s1 = null;
                if(s == null)
                    throw new SAXException("Invalid attribute selector in <style> element");
                skipWhitespace();
                attribop = null;
                if(consume('='))
                    attribop = AttribOp.EQUALS;
                else
                if(consume("~="))
                    attribop = AttribOp.INCLUDES;
                else
                if(consume("|="))
                    attribop = AttribOp.DASHMATCH;
                if(attribop != null)
                {
                    skipWhitespace();
                    s1 = nextAttribValue();
                    if(s1 == null)
                        throw new SAXException("Invalid attribute selector in <style> element");
                    skipWhitespace();
                }
                if(!consume(']'))
                    throw new SAXException("Invalid attribute selector in <style> element");
                if(attribop == null)
                    attribop = AttribOp.EXISTS;
                simpleselector.addAttrib(s, attribop, s1);
                selector.addedAttributeOrPseudo();
            }
              goto _L5
            if(consume(':'))
            {
                j = position;
                if(nextIdentifier() != null)
                {
label1:
                    {
                        if(!consume('('))
                            break label1;
                        skipWhitespace();
                        if(nextIdentifier() == null)
                            break label1;
                        skipWhitespace();
                        if(consume(')'))
                            break label1;
                        position = j - 1;
                    }
                }
            }
              goto _L3
            simpleselector.addPseudo(input.substring(j, position));
            selector.addedAttributeOrPseudo();
              goto _L3
        }

        public CSSTextScanner(String s)
        {
            super(s.replaceAll("(?s)/\\*.*?\\*/", ""));
        }
    }

    public static class Selector
    {

        public void add(SimpleSelector simpleselector)
        {
            if(selector == null)
                selector = new ArrayList();
            selector.add(simpleselector);
        }

        public void addedAttributeOrPseudo()
        {
            specificity = 100 + specificity;
        }

        public void addedElement()
        {
            specificity = 1 + specificity;
        }

        public void addedIdAttribute()
        {
            specificity = 10000 + specificity;
        }

        public SimpleSelector get(int i)
        {
            return (SimpleSelector)selector.get(i);
        }

        public boolean isEmpty()
        {
            boolean flag;
            if(selector == null)
                flag = true;
            else
                flag = selector.isEmpty();
            return flag;
        }

        public int size()
        {
            int i;
            if(selector == null)
                i = 0;
            else
                i = selector.size();
            return i;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            for(Iterator iterator = selector.iterator(); iterator.hasNext(); stringbuilder.append((SimpleSelector)iterator.next()).append(' '));
            return stringbuilder.append('(').append(specificity).append(')').toString();
        }

        public List selector;
        public int specificity;

        public Selector()
        {
            selector = null;
            specificity = 0;
        }
    }

    public static class Rule
    {

        public String toString()
        {
            return (new StringBuilder()).append(selector).append(" {}").toString();
        }

        public Selector selector;
        public SVG.Style style;

        public Rule(Selector selector1, SVG.Style style1)
        {
            selector = null;
            style = null;
            selector = selector1;
            style = style1;
        }
    }

    public static class Ruleset
    {

        public void add(Rule rule)
        {
            int i;
            if(rules == null)
                rules = new ArrayList();
            i = 0;
_L3:
            if(i >= rules.size())
                break MISSING_BLOCK_LABEL_80;
            if(((Rule)rules.get(i)).selector.specificity <= rule.selector.specificity) goto _L2; else goto _L1
_L1:
            rules.add(i, rule);
_L4:
            return;
_L2:
            i++;
              goto _L3
            rules.add(rule);
              goto _L4
        }

        public void addAll(Ruleset ruleset)
        {
            if(ruleset.rules != null)
            {
                if(rules == null)
                    rules = new ArrayList(ruleset.rules.size());
                Iterator iterator = ruleset.rules.iterator();
                while(iterator.hasNext()) 
                {
                    Rule rule = (Rule)iterator.next();
                    rules.add(rule);
                }
            }
        }

        public List getRules()
        {
            return rules;
        }

        public boolean isEmpty()
        {
            boolean flag;
            if(rules == null || rules.isEmpty())
                flag = true;
            else
                flag = false;
            return flag;
        }

        public String toString()
        {
            String s;
            if(rules == null)
            {
                s = "";
            } else
            {
                StringBuilder stringbuilder = new StringBuilder();
                for(Iterator iterator = rules.iterator(); iterator.hasNext(); stringbuilder.append(((Rule)iterator.next()).toString()).append('\n'));
                s = stringbuilder.toString();
            }
            return s;
        }

        private List rules;

        public Ruleset()
        {
            rules = null;
        }
    }

    private static class SimpleSelector
    {

        public void addAttrib(String s, AttribOp attribop, String s1)
        {
            if(attribs == null)
                attribs = new ArrayList();
            attribs.add(new Attrib(s, attribop, s1));
        }

        public void addPseudo(String s)
        {
            if(pseudos == null)
                pseudos = new ArrayList();
            pseudos.add(s);
        }

        public String toString()
        {
            StringBuilder stringbuilder;
            Attrib attrib;
            stringbuilder = new StringBuilder();
            class _cls1
            {

                static final int $SwitchMap$com$caverock$androidsvg$CSSParser$AttribOp[];

                static 
                {
                    $SwitchMap$com$caverock$androidsvg$CSSParser$AttribOp = new int[AttribOp.values().length];
                    NoSuchFieldError nosuchfielderror2;
                    try
                    {
                        $SwitchMap$com$caverock$androidsvg$CSSParser$AttribOp[AttribOp.EQUALS.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror) { }
                    try
                    {
                        $SwitchMap$com$caverock$androidsvg$CSSParser$AttribOp[AttribOp.INCLUDES.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror1) { }
                    $SwitchMap$com$caverock$androidsvg$CSSParser$AttribOp[AttribOp.DASHMATCH.ordinal()] = 3;
_L2:
                    return;
                    nosuchfielderror2;
                    if(true) goto _L2; else goto _L1
_L1:
                }
            }

            String s;
            Iterator iterator1;
            if(combinator == Combinator.CHILD)
                stringbuilder.append("> ");
            else
            if(combinator == Combinator.FOLLOWS)
                stringbuilder.append("+ ");
            if(tag == null)
                s = "*";
            else
                s = tag;
            stringbuilder.append(s);
            if(attribs == null)
                break MISSING_BLOCK_LABEL_228;
            iterator1 = attribs.iterator();
            if(!iterator1.hasNext())
                break MISSING_BLOCK_LABEL_228;
            attrib = (Attrib)iterator1.next();
            stringbuilder.append('[').append(attrib.name);
            switch(_cls1..SwitchMap.com.caverock.androidsvg.CSSParser.AttribOp[attrib.operation.ordinal()])
            {
            default:
                break;

            case 1: // '\001'
                break; /* Loop/switch isn't completed */

            case 2: // '\002'
                stringbuilder.append("~=").append(attrib.value);
                continue; /* Loop/switch isn't completed */

            case 3: // '\003'
                break;
            }
            break MISSING_BLOCK_LABEL_210;
_L4:
            stringbuilder.append(']');
            if(true) goto _L2; else goto _L1
_L2:
            break MISSING_BLOCK_LABEL_59;
_L1:
            stringbuilder.append('=').append(attrib.value);
            continue; /* Loop/switch isn't completed */
            stringbuilder.append("|=").append(attrib.value);
            if(true) goto _L4; else goto _L3
_L3:
            if(pseudos != null)
            {
                String s1;
                for(Iterator iterator = pseudos.iterator(); iterator.hasNext(); stringbuilder.append(':').append(s1))
                    s1 = (String)iterator.next();

            }
            return stringbuilder.toString();
        }

        public List attribs;
        public Combinator combinator;
        public List pseudos;
        public String tag;

        public SimpleSelector(Combinator combinator1, String s)
        {
            combinator = null;
            tag = null;
            attribs = null;
            pseudos = null;
            if(combinator1 == null)
                combinator1 = Combinator.DESCENDANT;
            combinator = combinator1;
            tag = s;
        }
    }

    public static class Attrib
    {

        public String name;
        public AttribOp operation;
        public String value;

        public Attrib(String s, AttribOp attribop, String s1)
        {
            name = null;
            value = null;
            name = s;
            operation = attribop;
            value = s1;
        }
    }

    private static final class AttribOp extends Enum
    {

        public static AttribOp valueOf(String s)
        {
            return (AttribOp)Enum.valueOf(com/caverock/androidsvg/CSSParser$AttribOp, s);
        }

        public static AttribOp[] values()
        {
            return (AttribOp[])$VALUES.clone();
        }

        private static final AttribOp $VALUES[];
        public static final AttribOp DASHMATCH;
        public static final AttribOp EQUALS;
        public static final AttribOp EXISTS;
        public static final AttribOp INCLUDES;

        static 
        {
            EXISTS = new AttribOp("EXISTS", 0);
            EQUALS = new AttribOp("EQUALS", 1);
            INCLUDES = new AttribOp("INCLUDES", 2);
            DASHMATCH = new AttribOp("DASHMATCH", 3);
            AttribOp aattribop[] = new AttribOp[4];
            aattribop[0] = EXISTS;
            aattribop[1] = EQUALS;
            aattribop[2] = INCLUDES;
            aattribop[3] = DASHMATCH;
            $VALUES = aattribop;
        }

        private AttribOp(String s, int i)
        {
            super(s, i);
        }
    }

    private static final class Combinator extends Enum
    {

        public static Combinator valueOf(String s)
        {
            return (Combinator)Enum.valueOf(com/caverock/androidsvg/CSSParser$Combinator, s);
        }

        public static Combinator[] values()
        {
            return (Combinator[])$VALUES.clone();
        }

        private static final Combinator $VALUES[];
        public static final Combinator CHILD;
        public static final Combinator DESCENDANT;
        public static final Combinator FOLLOWS;

        static 
        {
            DESCENDANT = new Combinator("DESCENDANT", 0);
            CHILD = new Combinator("CHILD", 1);
            FOLLOWS = new Combinator("FOLLOWS", 2);
            Combinator acombinator[] = new Combinator[3];
            acombinator[0] = DESCENDANT;
            acombinator[1] = CHILD;
            acombinator[2] = FOLLOWS;
            $VALUES = acombinator;
        }

        private Combinator(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class MediaType extends Enum
    {

        public static MediaType valueOf(String s)
        {
            return (MediaType)Enum.valueOf(com/caverock/androidsvg/CSSParser$MediaType, s);
        }

        public static MediaType[] values()
        {
            return (MediaType[])$VALUES.clone();
        }

        private static final MediaType $VALUES[];
        public static final MediaType all;
        public static final MediaType aural;
        public static final MediaType braille;
        public static final MediaType embossed;
        public static final MediaType handheld;
        public static final MediaType print;
        public static final MediaType projection;
        public static final MediaType screen;
        public static final MediaType tty;
        public static final MediaType tv;

        static 
        {
            all = new MediaType("all", 0);
            aural = new MediaType("aural", 1);
            braille = new MediaType("braille", 2);
            embossed = new MediaType("embossed", 3);
            handheld = new MediaType("handheld", 4);
            print = new MediaType("print", 5);
            projection = new MediaType("projection", 6);
            screen = new MediaType("screen", 7);
            tty = new MediaType("tty", 8);
            tv = new MediaType("tv", 9);
            MediaType amediatype[] = new MediaType[10];
            amediatype[0] = all;
            amediatype[1] = aural;
            amediatype[2] = braille;
            amediatype[3] = embossed;
            amediatype[4] = handheld;
            amediatype[5] = print;
            amediatype[6] = projection;
            amediatype[7] = screen;
            amediatype[8] = tty;
            amediatype[9] = tv;
            $VALUES = amediatype;
        }

        private MediaType(String s, int i)
        {
            super(s, i);
        }
    }


    public CSSParser(MediaType mediatype)
    {
        rendererMediaType = null;
        inMediaRule = false;
        rendererMediaType = mediatype;
    }

    private static int getChildPosition(List list, int i, SVG.SvgElementBase svgelementbase)
    {
        if(i >= 0) goto _L2; else goto _L1
_L1:
        int j = -1;
_L4:
        return j;
_L2:
        if(list.get(i) != svgelementbase.parent)
        {
            j = -1;
            continue; /* Loop/switch isn't completed */
        }
        j = 0;
        for(Iterator iterator = svgelementbase.parent.getChildren().iterator(); iterator.hasNext();)
        {
            if((SVG.SvgObject)iterator.next() == svgelementbase)
                continue; /* Loop/switch isn't completed */
            j++;
        }

        j = -1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static boolean mediaMatches(String s, MediaType mediatype)
        throws SAXException
    {
        CSSTextScanner csstextscanner = new CSSTextScanner(s);
        csstextscanner.skipWhitespace();
        List list = parseMediaList(csstextscanner);
        if(!csstextscanner.empty())
            throw new SAXException("Invalid @media type list");
        else
            return mediaMatches(list, mediatype);
    }

    private static boolean mediaMatches(List list, MediaType mediatype)
    {
        Iterator iterator = list.iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        MediaType mediatype1 = (MediaType)iterator.next();
        if(mediatype1 != MediaType.all && mediatype1 != mediatype) goto _L4; else goto _L3
_L3:
        boolean flag = true;
_L6:
        return flag;
_L2:
        flag = false;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void parseAtRule(Ruleset ruleset, CSSTextScanner csstextscanner)
        throws SAXException
    {
        String s = csstextscanner.nextIdentifier();
        csstextscanner.skipWhitespace();
        if(s == null)
            throw new SAXException("Invalid '@' rule in <style> element");
        if(!inMediaRule && s.equals("media"))
        {
            List list = parseMediaList(csstextscanner);
            if(!csstextscanner.consume('{'))
                throw new SAXException("Invalid @media rule: missing rule set");
            csstextscanner.skipWhitespace();
            if(mediaMatches(list, rendererMediaType))
            {
                inMediaRule = true;
                ruleset.addAll(parseRuleset(csstextscanner));
                inMediaRule = false;
            } else
            {
                parseRuleset(csstextscanner);
            }
            if(!csstextscanner.consume('}'))
                throw new SAXException("Invalid @media rule: expected '}' at end of rule set");
        } else
        {
            Object aobj[] = new Object[1];
            aobj[0] = s;
            warn("Ignoring @%s rule", aobj);
            skipAtRule(csstextscanner);
        }
        csstextscanner.skipWhitespace();
    }

    protected static List parseClassAttribute(String s)
        throws SAXException
    {
        CSSTextScanner csstextscanner = new CSSTextScanner(s);
        ArrayList arraylist = null;
        for(; !csstextscanner.empty(); csstextscanner.skipWhitespace())
        {
            String s1 = csstextscanner.nextIdentifier();
            if(s1 == null)
                throw new SAXException((new StringBuilder()).append("Invalid value for \"class\" attribute: ").append(s).toString());
            if(arraylist == null)
                arraylist = new ArrayList();
            arraylist.add(s1);
        }

        return arraylist;
    }

    private SVG.Style parseDeclarations(CSSTextScanner csstextscanner)
        throws SAXException
    {
        SVG.Style style = new SVG.Style();
_L4:
        String s;
        s = csstextscanner.nextIdentifier();
        csstextscanner.skipWhitespace();
        if(csstextscanner.consume(':')) goto _L2; else goto _L1
_L1:
        throw new SAXException("Malformed rule set in <style> element");
_L2:
        String s1;
        csstextscanner.skipWhitespace();
        s1 = csstextscanner.nextPropertyValue();
        if(s1 == null) goto _L1; else goto _L3
_L3:
        csstextscanner.skipWhitespace();
        if(csstextscanner.consume('!'))
        {
            csstextscanner.skipWhitespace();
            if(!csstextscanner.consume("important"))
                throw new SAXException("Malformed rule set in <style> element: found unexpected '!'");
            csstextscanner.skipWhitespace();
        }
        csstextscanner.consume(';');
        SVGParser.processStyleProperty(style, s, s1);
        csstextscanner.skipWhitespace();
        if(csstextscanner.consume('}'))
            return style;
        if(!csstextscanner.empty()) goto _L4; else goto _L1
    }

    private static List parseMediaList(CSSTextScanner csstextscanner)
        throws SAXException
    {
        ArrayList arraylist = new ArrayList();
        do
        {
            if(csstextscanner.empty())
                break;
            String s = csstextscanner.nextToken(',');
            try
            {
                arraylist.add(MediaType.valueOf(s));
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                throw new SAXException("Invalid @media type list");
            }
        } while(csstextscanner.skipCommaWhitespace());
        return arraylist;
    }

    private boolean parseRule(Ruleset ruleset, CSSTextScanner csstextscanner)
        throws SAXException
    {
        List list = parseSelectorGroup(csstextscanner);
        boolean flag;
        if(list != null && !list.isEmpty())
        {
            if(!csstextscanner.consume('{'))
                throw new SAXException("Malformed rule block in <style> element: missing '{'");
            csstextscanner.skipWhitespace();
            SVG.Style style = parseDeclarations(csstextscanner);
            csstextscanner.skipWhitespace();
            for(Iterator iterator = list.iterator(); iterator.hasNext(); ruleset.add(new Rule((Selector)iterator.next(), style)));
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    private Ruleset parseRuleset(CSSTextScanner csstextscanner)
        throws SAXException
    {
        Ruleset ruleset = new Ruleset();
label0:
        do
            do
            {
                do
                    if(csstextscanner.empty())
                        break label0;
                while(csstextscanner.consume("<!--") || csstextscanner.consume("-->"));
                if(!csstextscanner.consume('@'))
                    continue label0;
                parseAtRule(ruleset, csstextscanner);
            } while(true);
        while(parseRule(ruleset, csstextscanner));
        return ruleset;
    }

    private List parseSelectorGroup(CSSTextScanner csstextscanner)
        throws SAXException
    {
        if(!csstextscanner.empty()) goto _L2; else goto _L1
_L1:
        Object obj = null;
_L4:
        return ((List) (obj));
_L2:
        obj = new ArrayList(1);
        Selector selector = new Selector();
        do
        {
            if(csstextscanner.empty() || !csstextscanner.nextSimpleSelector(selector))
                break;
            if(csstextscanner.skipCommaWhitespace())
            {
                ((ArrayList) (obj)).add(selector);
                selector = new Selector();
            }
        } while(true);
        if(!selector.isEmpty())
            ((ArrayList) (obj)).add(selector);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static boolean ruleMatch(Selector selector, int i, List list, int j, SVG.SvgElementBase svgelementbase)
    {
        boolean flag;
        SimpleSelector simpleselector;
        flag = false;
        simpleselector = selector.get(i);
        if(selectorMatch(simpleselector, list, j, svgelementbase)) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(simpleselector.combinator == Combinator.DESCENDANT)
        {
            if(i == 0)
            {
                flag = true;
                continue; /* Loop/switch isn't completed */
            }
            do
            {
                if(j < 0)
                    continue; /* Loop/switch isn't completed */
                if(!ruleMatchOnAncestors(selector, i - 1, list, j))
                {
                    j--;
                } else
                {
                    flag = true;
                    continue; /* Loop/switch isn't completed */
                }
            } while(true);
        }
        if(simpleselector.combinator == Combinator.CHILD)
        {
            flag = ruleMatchOnAncestors(selector, i - 1, list, j);
        } else
        {
            int k = getChildPosition(list, j, svgelementbase);
            if(k > 0)
            {
                SVG.SvgElementBase svgelementbase1 = (SVG.SvgElementBase)svgelementbase.parent.getChildren().get(k - 1);
                flag = ruleMatch(selector, i - 1, list, j, svgelementbase1);
            }
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected static boolean ruleMatch(Selector selector, SVG.SvgElementBase svgelementbase)
    {
        ArrayList arraylist = new ArrayList();
        for(SVG.SvgContainer svgcontainer = svgelementbase.parent; svgcontainer != null; svgcontainer = ((SVG.SvgObject)svgcontainer).parent)
            arraylist.add(0, svgcontainer);

        int i = -1 + arraylist.size();
        boolean flag;
        if(selector.size() == 1)
            flag = selectorMatch(selector.get(0), arraylist, i, svgelementbase);
        else
            flag = ruleMatch(selector, -1 + selector.size(), ((List) (arraylist)), i, svgelementbase);
        return flag;
    }

    private static boolean ruleMatchOnAncestors(Selector selector, int i, List list, int j)
    {
        boolean flag;
        SimpleSelector simpleselector;
        SVG.SvgElementBase svgelementbase;
        flag = false;
        simpleselector = selector.get(i);
        svgelementbase = (SVG.SvgElementBase)list.get(j);
        if(selectorMatch(simpleselector, list, j, svgelementbase)) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(simpleselector.combinator == Combinator.DESCENDANT)
        {
            if(i == 0)
            {
                flag = true;
                continue; /* Loop/switch isn't completed */
            }
            int l;
            do
            {
                if(j <= 0)
                    continue; /* Loop/switch isn't completed */
                l = i - 1;
                j--;
            } while(!ruleMatchOnAncestors(selector, l, list, j));
            flag = true;
        } else
        if(simpleselector.combinator == Combinator.CHILD)
        {
            flag = ruleMatchOnAncestors(selector, i - 1, list, j - 1);
        } else
        {
            int k = getChildPosition(list, j, svgelementbase);
            if(k > 0)
            {
                SVG.SvgElementBase svgelementbase1 = (SVG.SvgElementBase)svgelementbase.parent.getChildren().get(k - 1);
                flag = ruleMatch(selector, i - 1, list, j, svgelementbase1);
            }
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    private static boolean selectorMatch(SimpleSelector simpleselector, List list, int i, SVG.SvgElementBase svgelementbase)
    {
        boolean flag = false;
        if(simpleselector.tag != null && (simpleselector.tag.equalsIgnoreCase("G") ? !(svgelementbase instanceof SVG.Group) : !simpleselector.tag.equals(svgelementbase.getClass().getSimpleName().toLowerCase(Locale.US)))) goto _L2; else goto _L1
_L2:
        return flag;
_L1:
label0:
        {
            if(simpleselector.attribs == null)
                break label0;
            Iterator iterator1 = simpleselector.attribs.iterator();
            Attrib attrib;
            do
            {
                if(!iterator1.hasNext())
                    break label0;
                attrib = (Attrib)iterator1.next();
            } while(attrib.name != "id" ? attrib.name == "class" && svgelementbase.classNames != null && svgelementbase.classNames.contains(attrib.value) : attrib.value.equals(svgelementbase.id));
            continue; /* Loop/switch isn't completed */
        }
label1:
        {
            if(simpleselector.pseudos == null)
                break label1;
            Iterator iterator = simpleselector.pseudos.iterator();
            do
                if(!iterator.hasNext())
                    break label1;
            while(((String)iterator.next()).equals("first-child") && getChildPosition(list, i, svgelementbase) == 0);
            continue; /* Loop/switch isn't completed */
        }
        flag = true;
        if(true) goto _L2; else goto _L3
_L3:
    }

    private void skipAtRule(CSSTextScanner csstextscanner)
    {
        int i = 0;
_L4:
        if(csstextscanner.empty()) goto _L2; else goto _L1
_L1:
        int j = csstextscanner.nextChar().intValue();
        if(j != 59 || i != 0) goto _L3; else goto _L2
_L2:
        return;
_L3:
        if(j != 123)
            continue; /* Loop/switch isn't completed */
        i++;
        break; /* Loop/switch isn't completed */
        if(j != 125 || i <= 0 || --i != 0) goto _L4; else goto _L2
    }

    private static transient void warn(String s, Object aobj[])
    {
        Log.w("AndroidSVG CSSParser", String.format(s, aobj));
    }

    public Ruleset parse(String s)
        throws SAXException
    {
        CSSTextScanner csstextscanner = new CSSTextScanner(s);
        csstextscanner.skipWhitespace();
        return parseRuleset(csstextscanner);
    }

    private static final String CLASS = "class";
    private static final String ID = "id";
    private static final String TAG = "AndroidSVG CSSParser";
    private boolean inMediaRule;
    private MediaType rendererMediaType;
}
