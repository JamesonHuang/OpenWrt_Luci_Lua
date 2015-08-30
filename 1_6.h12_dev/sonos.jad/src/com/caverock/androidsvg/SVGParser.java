// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.caverock.androidsvg;

import android.graphics.Matrix;
import android.util.Log;
import java.io.*;
import java.util.*;
import java.util.zip.GZIPInputStream;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.ext.DefaultHandler2;

// Referenced classes of package com.caverock.androidsvg:
//            CSSParser, SVG, PreserveAspectRatio, SVGParseException

public class SVGParser extends DefaultHandler2
{
    protected static class TextScanner
    {

        private int scanForFloat()
        {
            int i;
            if(empty())
            {
                i = position;
            } else
            {
                i = position;
                int j = position;
                int k = input.charAt(position);
                if(k == 45 || k == 43)
                    k = advanceChar();
                if(Character.isDigit(k))
                {
                    i = 1 + position;
                    for(k = advanceChar(); Character.isDigit(k); k = advanceChar())
                        i = 1 + position;

                }
                if(k == 46)
                {
                    i = 1 + position;
                    for(k = advanceChar(); Character.isDigit(k); k = advanceChar())
                        i = 1 + position;

                }
                if(k == 101 || k == 69)
                {
                    int l = advanceChar();
                    if(l == 45 || l == 43)
                        l = advanceChar();
                    if(Character.isDigit(l))
                    {
                        i = 1 + position;
                        for(int i1 = advanceChar(); Character.isDigit(i1); i1 = advanceChar())
                            i = 1 + position;

                    }
                }
                position = j;
            }
            return i;
        }

        private int scanForInteger()
        {
            int i;
            if(empty())
            {
                i = position;
            } else
            {
                i = position;
                int j = position;
                int k = input.charAt(position);
                if(k == 45 || k == 43)
                    k = advanceChar();
                if(Character.isDigit(k))
                {
                    i = 1 + position;
                    for(int l = advanceChar(); Character.isDigit(l); l = advanceChar())
                        i = 1 + position;

                }
                position = j;
            }
            return i;
        }

        protected int advanceChar()
        {
            char c = '\uFFFF';
            if(position != input.length()) goto _L2; else goto _L1
_L1:
            return c;
_L2:
            position = 1 + position;
            if(position < input.length())
                c = input.charAt(position);
            if(true) goto _L1; else goto _L3
_L3:
        }

        public String ahead()
        {
            int i = position;
            for(; !empty() && !isWhitespace(input.charAt(position)); position = 1 + position);
            String s = input.substring(i, position);
            position = i;
            return s;
        }

        public Boolean checkedNextFlag(Object obj)
        {
            Boolean boolean1;
            if(obj == null)
            {
                boolean1 = null;
            } else
            {
                skipCommaWhitespace();
                boolean1 = nextFlag();
            }
            return boolean1;
        }

        public Float checkedNextFloat(Object obj)
        {
            Float float1;
            if(obj == null)
            {
                float1 = null;
            } else
            {
                skipCommaWhitespace();
                float1 = nextFloat();
            }
            return float1;
        }

        public boolean consume(char c)
        {
            boolean flag;
            if(position < input.length() && input.charAt(position) == c)
                flag = true;
            else
                flag = false;
            if(flag)
                position = 1 + position;
            return flag;
        }

        public boolean consume(String s)
        {
            int i = s.length();
            boolean flag;
            if(position <= input.length() - i && input.substring(position, i + position).equals(s))
                flag = true;
            else
                flag = false;
            if(flag)
                position = i + position;
            return flag;
        }

        public boolean empty()
        {
            boolean flag;
            if(position == input.length())
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean hasLetter()
        {
            boolean flag = false;
            if(position != input.length()) goto _L2; else goto _L1
_L1:
            return flag;
_L2:
            char c = input.charAt(position);
            if(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z')
                flag = true;
            if(true) goto _L1; else goto _L3
_L3:
        }

        protected boolean isEOL(int i)
        {
            boolean flag;
            if(i == 10 || i == 13)
                flag = true;
            else
                flag = false;
            return flag;
        }

        protected boolean isWhitespace(int i)
        {
            boolean flag;
            if(i == 32 || i == 10 || i == 13 || i == 9)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public Integer nextChar()
        {
            Integer integer;
            if(position == input.length())
            {
                integer = null;
            } else
            {
                String s = input;
                int i = position;
                position = i + 1;
                integer = Integer.valueOf(s.charAt(i));
            }
            return integer;
        }

        public Boolean nextFlag()
        {
            Boolean boolean1 = null;
            if(position != input.length()) goto _L2; else goto _L1
_L1:
            return boolean1;
_L2:
            char c = input.charAt(position);
            if(c == '0' || c == '1')
            {
                position = 1 + position;
                boolean flag;
                if(c == '1')
                    flag = true;
                else
                    flag = false;
                boolean1 = Boolean.valueOf(flag);
            }
            if(true) goto _L1; else goto _L3
_L3:
        }

        public Float nextFloat()
        {
            int i = scanForFloat();
            Float float1;
            if(i == position)
            {
                float1 = null;
            } else
            {
                float1 = Float.valueOf(Float.parseFloat(input.substring(position, i)));
                position = i;
            }
            return float1;
        }

        public String nextFunction()
        {
            String s = null;
            if(!empty())
            {
                int i = position;
                int j;
                for(j = input.charAt(position); j >= 97 && j <= 122 || j >= 65 && j <= 90; j = advanceChar());
                int k = position;
                for(; isWhitespace(j); j = advanceChar());
                if(j == 40)
                {
                    position = 1 + position;
                    s = input.substring(i, k);
                } else
                {
                    position = i;
                }
            }
            return s;
        }

        public Integer nextInteger()
        {
            int i = scanForInteger();
            Integer integer;
            if(i == position)
            {
                integer = null;
            } else
            {
                integer = Integer.valueOf(Integer.parseInt(input.substring(position, i)));
                position = i;
            }
            return integer;
        }

        public SVG.Length nextLength()
        {
            Float float1 = nextFloat();
            SVG.Length length;
            if(float1 == null)
            {
                length = null;
            } else
            {
                SVG.Unit unit = nextUnit();
                if(unit == null)
                    length = new SVG.Length(float1.floatValue(), SVG.Unit.px);
                else
                    length = new SVG.Length(float1.floatValue(), unit);
            }
            return length;
        }

        public String nextQuotedString()
        {
            String s = null;
            if(!empty()) goto _L2; else goto _L1
_L1:
            return s;
_L2:
            int i = position;
            char c = input.charAt(position);
            if(c == '\'' || c == '"')
            {
                int j;
                for(j = advanceChar(); j != -1 && j != c; j = advanceChar());
                if(j == -1)
                {
                    position = i;
                } else
                {
                    position = 1 + position;
                    s = input.substring(i + 1, -1 + position);
                }
            }
            if(true) goto _L1; else goto _L3
_L3:
        }

        public String nextToken()
        {
            return nextToken(' ');
        }

        public String nextToken(char c)
        {
            String s = null;
            if(!empty()) goto _L2; else goto _L1
_L1:
            return s;
_L2:
            char c1 = input.charAt(position);
            if(!isWhitespace(c1) && c1 != c)
            {
                int i = position;
                for(int j = advanceChar(); j != -1 && j != c && !isWhitespace(j); j = advanceChar());
                s = input.substring(i, position);
            }
            if(true) goto _L1; else goto _L3
_L3:
        }

        public SVG.Unit nextUnit()
        {
            SVG.Unit unit;
            if(empty())
                unit = null;
            else
            if(input.charAt(position) == '%')
            {
                position = 1 + position;
                unit = SVG.Unit.percent;
            } else
            if(position > -2 + input.length())
                unit = null;
            else
                try
                {
                    unit = SVG.Unit.valueOf(input.substring(position, 2 + position).toLowerCase(Locale.US));
                    position = 2 + position;
                }
                catch(IllegalArgumentException illegalargumentexception)
                {
                    unit = null;
                }
            return unit;
        }

        public Float possibleNextFloat()
        {
            int i = position;
            skipCommaWhitespace();
            Float float1 = nextFloat();
            if(float1 == null)
            {
                position = i;
                float1 = null;
            }
            return float1;
        }

        public String restOfText()
        {
            String s;
            if(empty())
            {
                s = null;
            } else
            {
                int i = position;
                position = input.length();
                s = input.substring(i);
            }
            return s;
        }

        public boolean skipCommaWhitespace()
        {
            boolean flag;
            flag = false;
            skipWhitespace();
            break MISSING_BLOCK_LABEL_6;
            if(position != input.length() && input.charAt(position) == ',')
            {
                position = 1 + position;
                skipWhitespace();
                flag = true;
            }
            return flag;
        }

        public void skipWhitespace()
        {
            do
            {
                if(position >= input.length() || !isWhitespace(input.charAt(position)))
                    return;
                position = 1 + position;
            } while(true);
        }

        protected String input;
        protected int position;

        public TextScanner(String s)
        {
            position = 0;
            input = s.trim();
        }
    }

    private static final class SVGAttr extends Enum
    {

        public static SVGAttr fromString(String s)
        {
            if(!s.equals("class")) goto _L2; else goto _L1
_L1:
            SVGAttr svgattr = CLASS;
_L4:
            return svgattr;
_L2:
            if(s.indexOf('_') != -1)
            {
                svgattr = UNSUPPORTED;
                continue; /* Loop/switch isn't completed */
            }
            SVGAttr svgattr1 = valueOf(s.replace('-', '_'));
            svgattr = svgattr1;
            continue; /* Loop/switch isn't completed */
            IllegalArgumentException illegalargumentexception;
            illegalargumentexception;
            svgattr = UNSUPPORTED;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static SVGAttr valueOf(String s)
        {
            return (SVGAttr)Enum.valueOf(com/caverock/androidsvg/SVGParser$SVGAttr, s);
        }

        public static SVGAttr[] values()
        {
            return (SVGAttr[])$VALUES.clone();
        }

        private static final SVGAttr $VALUES[];
        public static final SVGAttr CLASS;
        public static final SVGAttr UNSUPPORTED;
        public static final SVGAttr clip;
        public static final SVGAttr clipPathUnits;
        public static final SVGAttr clip_path;
        public static final SVGAttr clip_rule;
        public static final SVGAttr color;
        public static final SVGAttr cx;
        public static final SVGAttr cy;
        public static final SVGAttr d;
        public static final SVGAttr direction;
        public static final SVGAttr display;
        public static final SVGAttr dx;
        public static final SVGAttr dy;
        public static final SVGAttr fill;
        public static final SVGAttr fill_opacity;
        public static final SVGAttr fill_rule;
        public static final SVGAttr font;
        public static final SVGAttr font_family;
        public static final SVGAttr font_size;
        public static final SVGAttr font_style;
        public static final SVGAttr font_weight;
        public static final SVGAttr fx;
        public static final SVGAttr fy;
        public static final SVGAttr gradientTransform;
        public static final SVGAttr gradientUnits;
        public static final SVGAttr height;
        public static final SVGAttr href;
        public static final SVGAttr id;
        public static final SVGAttr marker;
        public static final SVGAttr markerHeight;
        public static final SVGAttr markerUnits;
        public static final SVGAttr markerWidth;
        public static final SVGAttr marker_end;
        public static final SVGAttr marker_mid;
        public static final SVGAttr marker_start;
        public static final SVGAttr mask;
        public static final SVGAttr maskContentUnits;
        public static final SVGAttr maskUnits;
        public static final SVGAttr media;
        public static final SVGAttr offset;
        public static final SVGAttr opacity;
        public static final SVGAttr orient;
        public static final SVGAttr overflow;
        public static final SVGAttr pathLength;
        public static final SVGAttr patternContentUnits;
        public static final SVGAttr patternTransform;
        public static final SVGAttr patternUnits;
        public static final SVGAttr points;
        public static final SVGAttr preserveAspectRatio;
        public static final SVGAttr r;
        public static final SVGAttr refX;
        public static final SVGAttr refY;
        public static final SVGAttr requiredExtensions;
        public static final SVGAttr requiredFeatures;
        public static final SVGAttr requiredFonts;
        public static final SVGAttr requiredFormats;
        public static final SVGAttr rx;
        public static final SVGAttr ry;
        public static final SVGAttr solid_color;
        public static final SVGAttr solid_opacity;
        public static final SVGAttr spreadMethod;
        public static final SVGAttr startOffset;
        public static final SVGAttr stop_color;
        public static final SVGAttr stop_opacity;
        public static final SVGAttr stroke;
        public static final SVGAttr stroke_dasharray;
        public static final SVGAttr stroke_dashoffset;
        public static final SVGAttr stroke_linecap;
        public static final SVGAttr stroke_linejoin;
        public static final SVGAttr stroke_miterlimit;
        public static final SVGAttr stroke_opacity;
        public static final SVGAttr stroke_width;
        public static final SVGAttr style;
        public static final SVGAttr systemLanguage;
        public static final SVGAttr text_anchor;
        public static final SVGAttr text_decoration;
        public static final SVGAttr transform;
        public static final SVGAttr type;
        public static final SVGAttr vector_effect;
        public static final SVGAttr version;
        public static final SVGAttr viewBox;
        public static final SVGAttr viewport_fill;
        public static final SVGAttr viewport_fill_opacity;
        public static final SVGAttr visibility;
        public static final SVGAttr width;
        public static final SVGAttr x;
        public static final SVGAttr x1;
        public static final SVGAttr x2;
        public static final SVGAttr y;
        public static final SVGAttr y1;
        public static final SVGAttr y2;

        static 
        {
            CLASS = new SVGAttr("CLASS", 0);
            clip = new SVGAttr("clip", 1);
            clip_path = new SVGAttr("clip_path", 2);
            clipPathUnits = new SVGAttr("clipPathUnits", 3);
            clip_rule = new SVGAttr("clip_rule", 4);
            color = new SVGAttr("color", 5);
            cx = new SVGAttr("cx", 6);
            cy = new SVGAttr("cy", 7);
            direction = new SVGAttr("direction", 8);
            dx = new SVGAttr("dx", 9);
            dy = new SVGAttr("dy", 10);
            fx = new SVGAttr("fx", 11);
            fy = new SVGAttr("fy", 12);
            d = new SVGAttr("d", 13);
            display = new SVGAttr("display", 14);
            fill = new SVGAttr("fill", 15);
            fill_rule = new SVGAttr("fill_rule", 16);
            fill_opacity = new SVGAttr("fill_opacity", 17);
            font = new SVGAttr("font", 18);
            font_family = new SVGAttr("font_family", 19);
            font_size = new SVGAttr("font_size", 20);
            font_weight = new SVGAttr("font_weight", 21);
            font_style = new SVGAttr("font_style", 22);
            gradientTransform = new SVGAttr("gradientTransform", 23);
            gradientUnits = new SVGAttr("gradientUnits", 24);
            height = new SVGAttr("height", 25);
            href = new SVGAttr("href", 26);
            id = new SVGAttr("id", 27);
            marker = new SVGAttr("marker", 28);
            marker_start = new SVGAttr("marker_start", 29);
            marker_mid = new SVGAttr("marker_mid", 30);
            marker_end = new SVGAttr("marker_end", 31);
            markerHeight = new SVGAttr("markerHeight", 32);
            markerUnits = new SVGAttr("markerUnits", 33);
            markerWidth = new SVGAttr("markerWidth", 34);
            mask = new SVGAttr("mask", 35);
            maskContentUnits = new SVGAttr("maskContentUnits", 36);
            maskUnits = new SVGAttr("maskUnits", 37);
            media = new SVGAttr("media", 38);
            offset = new SVGAttr("offset", 39);
            opacity = new SVGAttr("opacity", 40);
            orient = new SVGAttr("orient", 41);
            overflow = new SVGAttr("overflow", 42);
            pathLength = new SVGAttr("pathLength", 43);
            patternContentUnits = new SVGAttr("patternContentUnits", 44);
            patternTransform = new SVGAttr("patternTransform", 45);
            patternUnits = new SVGAttr("patternUnits", 46);
            points = new SVGAttr("points", 47);
            preserveAspectRatio = new SVGAttr("preserveAspectRatio", 48);
            r = new SVGAttr("r", 49);
            refX = new SVGAttr("refX", 50);
            refY = new SVGAttr("refY", 51);
            requiredFeatures = new SVGAttr("requiredFeatures", 52);
            requiredExtensions = new SVGAttr("requiredExtensions", 53);
            requiredFormats = new SVGAttr("requiredFormats", 54);
            requiredFonts = new SVGAttr("requiredFonts", 55);
            rx = new SVGAttr("rx", 56);
            ry = new SVGAttr("ry", 57);
            solid_color = new SVGAttr("solid_color", 58);
            solid_opacity = new SVGAttr("solid_opacity", 59);
            spreadMethod = new SVGAttr("spreadMethod", 60);
            startOffset = new SVGAttr("startOffset", 61);
            stop_color = new SVGAttr("stop_color", 62);
            stop_opacity = new SVGAttr("stop_opacity", 63);
            stroke = new SVGAttr("stroke", 64);
            stroke_dasharray = new SVGAttr("stroke_dasharray", 65);
            stroke_dashoffset = new SVGAttr("stroke_dashoffset", 66);
            stroke_linecap = new SVGAttr("stroke_linecap", 67);
            stroke_linejoin = new SVGAttr("stroke_linejoin", 68);
            stroke_miterlimit = new SVGAttr("stroke_miterlimit", 69);
            stroke_opacity = new SVGAttr("stroke_opacity", 70);
            stroke_width = new SVGAttr("stroke_width", 71);
            style = new SVGAttr("style", 72);
            systemLanguage = new SVGAttr("systemLanguage", 73);
            text_anchor = new SVGAttr("text_anchor", 74);
            text_decoration = new SVGAttr("text_decoration", 75);
            transform = new SVGAttr("transform", 76);
            type = new SVGAttr("type", 77);
            vector_effect = new SVGAttr("vector_effect", 78);
            version = new SVGAttr("version", 79);
            viewBox = new SVGAttr("viewBox", 80);
            width = new SVGAttr("width", 81);
            x = new SVGAttr("x", 82);
            y = new SVGAttr("y", 83);
            x1 = new SVGAttr("x1", 84);
            y1 = new SVGAttr("y1", 85);
            x2 = new SVGAttr("x2", 86);
            y2 = new SVGAttr("y2", 87);
            viewport_fill = new SVGAttr("viewport_fill", 88);
            viewport_fill_opacity = new SVGAttr("viewport_fill_opacity", 89);
            visibility = new SVGAttr("visibility", 90);
            UNSUPPORTED = new SVGAttr("UNSUPPORTED", 91);
            SVGAttr asvgattr[] = new SVGAttr[92];
            asvgattr[0] = CLASS;
            asvgattr[1] = clip;
            asvgattr[2] = clip_path;
            asvgattr[3] = clipPathUnits;
            asvgattr[4] = clip_rule;
            asvgattr[5] = color;
            asvgattr[6] = cx;
            asvgattr[7] = cy;
            asvgattr[8] = direction;
            asvgattr[9] = dx;
            asvgattr[10] = dy;
            asvgattr[11] = fx;
            asvgattr[12] = fy;
            asvgattr[13] = d;
            asvgattr[14] = display;
            asvgattr[15] = fill;
            asvgattr[16] = fill_rule;
            asvgattr[17] = fill_opacity;
            asvgattr[18] = font;
            asvgattr[19] = font_family;
            asvgattr[20] = font_size;
            asvgattr[21] = font_weight;
            asvgattr[22] = font_style;
            asvgattr[23] = gradientTransform;
            asvgattr[24] = gradientUnits;
            asvgattr[25] = height;
            asvgattr[26] = href;
            asvgattr[27] = id;
            asvgattr[28] = marker;
            asvgattr[29] = marker_start;
            asvgattr[30] = marker_mid;
            asvgattr[31] = marker_end;
            asvgattr[32] = markerHeight;
            asvgattr[33] = markerUnits;
            asvgattr[34] = markerWidth;
            asvgattr[35] = mask;
            asvgattr[36] = maskContentUnits;
            asvgattr[37] = maskUnits;
            asvgattr[38] = media;
            asvgattr[39] = offset;
            asvgattr[40] = opacity;
            asvgattr[41] = orient;
            asvgattr[42] = overflow;
            asvgattr[43] = pathLength;
            asvgattr[44] = patternContentUnits;
            asvgattr[45] = patternTransform;
            asvgattr[46] = patternUnits;
            asvgattr[47] = points;
            asvgattr[48] = preserveAspectRatio;
            asvgattr[49] = r;
            asvgattr[50] = refX;
            asvgattr[51] = refY;
            asvgattr[52] = requiredFeatures;
            asvgattr[53] = requiredExtensions;
            asvgattr[54] = requiredFormats;
            asvgattr[55] = requiredFonts;
            asvgattr[56] = rx;
            asvgattr[57] = ry;
            asvgattr[58] = solid_color;
            asvgattr[59] = solid_opacity;
            asvgattr[60] = spreadMethod;
            asvgattr[61] = startOffset;
            asvgattr[62] = stop_color;
            asvgattr[63] = stop_opacity;
            asvgattr[64] = stroke;
            asvgattr[65] = stroke_dasharray;
            asvgattr[66] = stroke_dashoffset;
            asvgattr[67] = stroke_linecap;
            asvgattr[68] = stroke_linejoin;
            asvgattr[69] = stroke_miterlimit;
            asvgattr[70] = stroke_opacity;
            asvgattr[71] = stroke_width;
            asvgattr[72] = style;
            asvgattr[73] = systemLanguage;
            asvgattr[74] = text_anchor;
            asvgattr[75] = text_decoration;
            asvgattr[76] = transform;
            asvgattr[77] = type;
            asvgattr[78] = vector_effect;
            asvgattr[79] = version;
            asvgattr[80] = viewBox;
            asvgattr[81] = width;
            asvgattr[82] = x;
            asvgattr[83] = y;
            asvgattr[84] = x1;
            asvgattr[85] = y1;
            asvgattr[86] = x2;
            asvgattr[87] = y2;
            asvgattr[88] = viewport_fill;
            asvgattr[89] = viewport_fill_opacity;
            asvgattr[90] = visibility;
            asvgattr[91] = UNSUPPORTED;
            $VALUES = asvgattr;
        }

        private SVGAttr(String s, int i)
        {
            super(s, i);
        }
    }


    public SVGParser()
    {
        svgDocument = null;
        currentElement = null;
        ignoring = false;
        inMetadataElement = false;
        metadataTag = null;
        metadataElementContents = null;
        inStyleElement = false;
        styleElementContents = null;
        supportedFormats = null;
    }

    private void circle(Attributes attributes)
        throws SAXException
    {
        debug("<circle>", new Object[0]);
        if(currentElement == null)
        {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else
        {
            SVG.Circle circle1 = new SVG.Circle();
            circle1.document = svgDocument;
            circle1.parent = currentElement;
            parseAttributesCore(circle1, attributes);
            parseAttributesStyle(circle1, attributes);
            parseAttributesTransform(circle1, attributes);
            parseAttributesConditional(circle1, attributes);
            parseAttributesCircle(circle1, attributes);
            currentElement.addChild(circle1);
            return;
        }
    }

    private void clipPath(Attributes attributes)
        throws SAXException
    {
        debug("<clipPath>", new Object[0]);
        if(currentElement == null)
        {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else
        {
            SVG.ClipPath clippath = new SVG.ClipPath();
            clippath.document = svgDocument;
            clippath.parent = currentElement;
            parseAttributesCore(clippath, attributes);
            parseAttributesStyle(clippath, attributes);
            parseAttributesTransform(clippath, attributes);
            parseAttributesConditional(clippath, attributes);
            parseAttributesClipPath(clippath, attributes);
            currentElement.addChild(clippath);
            currentElement = clippath;
            return;
        }
    }

    private transient void debug(String s, Object aobj[])
    {
    }

    private void defs(Attributes attributes)
        throws SAXException
    {
        debug("<defs>", new Object[0]);
        if(currentElement == null)
        {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else
        {
            SVG.Defs defs1 = new SVG.Defs();
            defs1.document = svgDocument;
            defs1.parent = currentElement;
            parseAttributesCore(defs1, attributes);
            parseAttributesStyle(defs1, attributes);
            parseAttributesTransform(defs1, attributes);
            currentElement.addChild(defs1);
            currentElement = defs1;
            return;
        }
    }

    private void dumpNode(SVG.SvgObject svgobject, String s)
    {
        Log.d("SVGParser", (new StringBuilder()).append(s).append(svgobject).toString());
        if(svgobject instanceof SVG.SvgConditionalContainer)
        {
            String s1 = (new StringBuilder()).append(s).append("  ").toString();
            for(Iterator iterator = ((SVG.SvgConditionalContainer)svgobject).children.iterator(); iterator.hasNext(); dumpNode((SVG.SvgObject)iterator.next(), s1));
        }
    }

    private void ellipse(Attributes attributes)
        throws SAXException
    {
        debug("<ellipse>", new Object[0]);
        if(currentElement == null)
        {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else
        {
            SVG.Ellipse ellipse1 = new SVG.Ellipse();
            ellipse1.document = svgDocument;
            ellipse1.parent = currentElement;
            parseAttributesCore(ellipse1, attributes);
            parseAttributesStyle(ellipse1, attributes);
            parseAttributesTransform(ellipse1, attributes);
            parseAttributesConditional(ellipse1, attributes);
            parseAttributesEllipse(ellipse1, attributes);
            currentElement.addChild(ellipse1);
            return;
        }
    }

    private void g(Attributes attributes)
        throws SAXException
    {
        debug("<g>", new Object[0]);
        if(currentElement == null)
        {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else
        {
            SVG.Group group = new SVG.Group();
            group.document = svgDocument;
            group.parent = currentElement;
            parseAttributesCore(group, attributes);
            parseAttributesStyle(group, attributes);
            parseAttributesTransform(group, attributes);
            parseAttributesConditional(group, attributes);
            currentElement.addChild(group);
            currentElement = group;
            return;
        }
    }

    private void image(Attributes attributes)
        throws SAXException
    {
        debug("<image>", new Object[0]);
        if(currentElement == null)
        {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else
        {
            SVG.Image image1 = new SVG.Image();
            image1.document = svgDocument;
            image1.parent = currentElement;
            parseAttributesCore(image1, attributes);
            parseAttributesStyle(image1, attributes);
            parseAttributesTransform(image1, attributes);
            parseAttributesConditional(image1, attributes);
            parseAttributesImage(image1, attributes);
            currentElement.addChild(image1);
            currentElement = image1;
            return;
        }
    }

    private void line(Attributes attributes)
        throws SAXException
    {
        debug("<line>", new Object[0]);
        if(currentElement == null)
        {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else
        {
            SVG.Line line1 = new SVG.Line();
            line1.document = svgDocument;
            line1.parent = currentElement;
            parseAttributesCore(line1, attributes);
            parseAttributesStyle(line1, attributes);
            parseAttributesTransform(line1, attributes);
            parseAttributesConditional(line1, attributes);
            parseAttributesLine(line1, attributes);
            currentElement.addChild(line1);
            return;
        }
    }

    private void linearGradient(Attributes attributes)
        throws SAXException
    {
        debug("<linearGradiant>", new Object[0]);
        if(currentElement == null)
        {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else
        {
            SVG.SvgLinearGradient svglineargradient = new SVG.SvgLinearGradient();
            svglineargradient.document = svgDocument;
            svglineargradient.parent = currentElement;
            parseAttributesCore(svglineargradient, attributes);
            parseAttributesStyle(svglineargradient, attributes);
            parseAttributesGradient(svglineargradient, attributes);
            parseAttributesLinearGradient(svglineargradient, attributes);
            currentElement.addChild(svglineargradient);
            currentElement = svglineargradient;
            return;
        }
    }

    private void marker(Attributes attributes)
        throws SAXException
    {
        debug("<marker>", new Object[0]);
        if(currentElement == null)
        {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else
        {
            SVG.Marker marker1 = new SVG.Marker();
            marker1.document = svgDocument;
            marker1.parent = currentElement;
            parseAttributesCore(marker1, attributes);
            parseAttributesStyle(marker1, attributes);
            parseAttributesConditional(marker1, attributes);
            parseAttributesViewBox(marker1, attributes);
            parseAttributesMarker(marker1, attributes);
            currentElement.addChild(marker1);
            currentElement = marker1;
            return;
        }
    }

    private void mask(Attributes attributes)
        throws SAXException
    {
        debug("<mask>", new Object[0]);
        if(currentElement == null)
        {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else
        {
            SVG.Mask mask1 = new SVG.Mask();
            mask1.document = svgDocument;
            mask1.parent = currentElement;
            parseAttributesCore(mask1, attributes);
            parseAttributesStyle(mask1, attributes);
            parseAttributesConditional(mask1, attributes);
            parseAttributesMask(mask1, attributes);
            currentElement.addChild(mask1);
            currentElement = mask1;
            return;
        }
    }

    private void parseAttributesCircle(SVG.Circle circle1, Attributes attributes)
        throws SAXException
    {
        int i = 0;
_L6:
        String s;
        if(i >= attributes.getLength())
            break MISSING_BLOCK_LABEL_128;
        s = attributes.getValue(i).trim();
        class _cls1
        {

            static final int $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[];

            static 
            {
                $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr = new int[SVGAttr.values().length];
                NoSuchFieldError nosuchfielderror87;
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.x.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.y.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.width.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.height.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.version.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.href.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.preserveAspectRatio.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.d.ordinal()] = 8;
                }
                catch(NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.pathLength.ordinal()] = 9;
                }
                catch(NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.rx.ordinal()] = 10;
                }
                catch(NoSuchFieldError nosuchfielderror9) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.ry.ordinal()] = 11;
                }
                catch(NoSuchFieldError nosuchfielderror10) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.cx.ordinal()] = 12;
                }
                catch(NoSuchFieldError nosuchfielderror11) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.cy.ordinal()] = 13;
                }
                catch(NoSuchFieldError nosuchfielderror12) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.r.ordinal()] = 14;
                }
                catch(NoSuchFieldError nosuchfielderror13) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.x1.ordinal()] = 15;
                }
                catch(NoSuchFieldError nosuchfielderror14) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.y1.ordinal()] = 16;
                }
                catch(NoSuchFieldError nosuchfielderror15) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.x2.ordinal()] = 17;
                }
                catch(NoSuchFieldError nosuchfielderror16) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.y2.ordinal()] = 18;
                }
                catch(NoSuchFieldError nosuchfielderror17) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.dx.ordinal()] = 19;
                }
                catch(NoSuchFieldError nosuchfielderror18) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.dy.ordinal()] = 20;
                }
                catch(NoSuchFieldError nosuchfielderror19) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.requiredFeatures.ordinal()] = 21;
                }
                catch(NoSuchFieldError nosuchfielderror20) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.requiredExtensions.ordinal()] = 22;
                }
                catch(NoSuchFieldError nosuchfielderror21) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.systemLanguage.ordinal()] = 23;
                }
                catch(NoSuchFieldError nosuchfielderror22) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.requiredFormats.ordinal()] = 24;
                }
                catch(NoSuchFieldError nosuchfielderror23) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.requiredFonts.ordinal()] = 25;
                }
                catch(NoSuchFieldError nosuchfielderror24) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.refX.ordinal()] = 26;
                }
                catch(NoSuchFieldError nosuchfielderror25) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.refY.ordinal()] = 27;
                }
                catch(NoSuchFieldError nosuchfielderror26) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.markerWidth.ordinal()] = 28;
                }
                catch(NoSuchFieldError nosuchfielderror27) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.markerHeight.ordinal()] = 29;
                }
                catch(NoSuchFieldError nosuchfielderror28) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.markerUnits.ordinal()] = 30;
                }
                catch(NoSuchFieldError nosuchfielderror29) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.orient.ordinal()] = 31;
                }
                catch(NoSuchFieldError nosuchfielderror30) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.gradientUnits.ordinal()] = 32;
                }
                catch(NoSuchFieldError nosuchfielderror31) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.gradientTransform.ordinal()] = 33;
                }
                catch(NoSuchFieldError nosuchfielderror32) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.spreadMethod.ordinal()] = 34;
                }
                catch(NoSuchFieldError nosuchfielderror33) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fx.ordinal()] = 35;
                }
                catch(NoSuchFieldError nosuchfielderror34) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fy.ordinal()] = 36;
                }
                catch(NoSuchFieldError nosuchfielderror35) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.offset.ordinal()] = 37;
                }
                catch(NoSuchFieldError nosuchfielderror36) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.clipPathUnits.ordinal()] = 38;
                }
                catch(NoSuchFieldError nosuchfielderror37) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.startOffset.ordinal()] = 39;
                }
                catch(NoSuchFieldError nosuchfielderror38) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.patternUnits.ordinal()] = 40;
                }
                catch(NoSuchFieldError nosuchfielderror39) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.patternContentUnits.ordinal()] = 41;
                }
                catch(NoSuchFieldError nosuchfielderror40) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.patternTransform.ordinal()] = 42;
                }
                catch(NoSuchFieldError nosuchfielderror41) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.maskUnits.ordinal()] = 43;
                }
                catch(NoSuchFieldError nosuchfielderror42) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.maskContentUnits.ordinal()] = 44;
                }
                catch(NoSuchFieldError nosuchfielderror43) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.style.ordinal()] = 45;
                }
                catch(NoSuchFieldError nosuchfielderror44) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.CLASS.ordinal()] = 46;
                }
                catch(NoSuchFieldError nosuchfielderror45) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fill.ordinal()] = 47;
                }
                catch(NoSuchFieldError nosuchfielderror46) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fill_rule.ordinal()] = 48;
                }
                catch(NoSuchFieldError nosuchfielderror47) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fill_opacity.ordinal()] = 49;
                }
                catch(NoSuchFieldError nosuchfielderror48) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.stroke.ordinal()] = 50;
                }
                catch(NoSuchFieldError nosuchfielderror49) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.stroke_opacity.ordinal()] = 51;
                }
                catch(NoSuchFieldError nosuchfielderror50) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.stroke_width.ordinal()] = 52;
                }
                catch(NoSuchFieldError nosuchfielderror51) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.stroke_linecap.ordinal()] = 53;
                }
                catch(NoSuchFieldError nosuchfielderror52) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.stroke_linejoin.ordinal()] = 54;
                }
                catch(NoSuchFieldError nosuchfielderror53) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.stroke_miterlimit.ordinal()] = 55;
                }
                catch(NoSuchFieldError nosuchfielderror54) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.stroke_dasharray.ordinal()] = 56;
                }
                catch(NoSuchFieldError nosuchfielderror55) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.stroke_dashoffset.ordinal()] = 57;
                }
                catch(NoSuchFieldError nosuchfielderror56) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.opacity.ordinal()] = 58;
                }
                catch(NoSuchFieldError nosuchfielderror57) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.color.ordinal()] = 59;
                }
                catch(NoSuchFieldError nosuchfielderror58) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.font.ordinal()] = 60;
                }
                catch(NoSuchFieldError nosuchfielderror59) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.font_family.ordinal()] = 61;
                }
                catch(NoSuchFieldError nosuchfielderror60) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.font_size.ordinal()] = 62;
                }
                catch(NoSuchFieldError nosuchfielderror61) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.font_weight.ordinal()] = 63;
                }
                catch(NoSuchFieldError nosuchfielderror62) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.font_style.ordinal()] = 64;
                }
                catch(NoSuchFieldError nosuchfielderror63) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.text_decoration.ordinal()] = 65;
                }
                catch(NoSuchFieldError nosuchfielderror64) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.direction.ordinal()] = 66;
                }
                catch(NoSuchFieldError nosuchfielderror65) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.text_anchor.ordinal()] = 67;
                }
                catch(NoSuchFieldError nosuchfielderror66) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.overflow.ordinal()] = 68;
                }
                catch(NoSuchFieldError nosuchfielderror67) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.marker.ordinal()] = 69;
                }
                catch(NoSuchFieldError nosuchfielderror68) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.marker_start.ordinal()] = 70;
                }
                catch(NoSuchFieldError nosuchfielderror69) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.marker_mid.ordinal()] = 71;
                }
                catch(NoSuchFieldError nosuchfielderror70) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.marker_end.ordinal()] = 72;
                }
                catch(NoSuchFieldError nosuchfielderror71) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.display.ordinal()] = 73;
                }
                catch(NoSuchFieldError nosuchfielderror72) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.visibility.ordinal()] = 74;
                }
                catch(NoSuchFieldError nosuchfielderror73) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.stop_color.ordinal()] = 75;
                }
                catch(NoSuchFieldError nosuchfielderror74) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.stop_opacity.ordinal()] = 76;
                }
                catch(NoSuchFieldError nosuchfielderror75) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.clip.ordinal()] = 77;
                }
                catch(NoSuchFieldError nosuchfielderror76) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.clip_path.ordinal()] = 78;
                }
                catch(NoSuchFieldError nosuchfielderror77) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.clip_rule.ordinal()] = 79;
                }
                catch(NoSuchFieldError nosuchfielderror78) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.mask.ordinal()] = 80;
                }
                catch(NoSuchFieldError nosuchfielderror79) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.solid_color.ordinal()] = 81;
                }
                catch(NoSuchFieldError nosuchfielderror80) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.solid_opacity.ordinal()] = 82;
                }
                catch(NoSuchFieldError nosuchfielderror81) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.viewport_fill.ordinal()] = 83;
                }
                catch(NoSuchFieldError nosuchfielderror82) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.viewport_fill_opacity.ordinal()] = 84;
                }
                catch(NoSuchFieldError nosuchfielderror83) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.vector_effect.ordinal()] = 85;
                }
                catch(NoSuchFieldError nosuchfielderror84) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.viewBox.ordinal()] = 86;
                }
                catch(NoSuchFieldError nosuchfielderror85) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.type.ordinal()] = 87;
                }
                catch(NoSuchFieldError nosuchfielderror86) { }
                $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.media.ordinal()] = 88;
_L2:
                return;
                nosuchfielderror87;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.com.caverock.androidsvg.SVGParser.SVGAttr[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
        JVM INSTR tableswitch 12 14: default 68
    //                   12 74
    //                   13 86
    //                   14 98;
           goto _L1 _L2 _L3 _L4
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L7:
        i++;
        if(true) goto _L6; else goto _L5
_L5:
        circle1.cx = parseLength(s);
          goto _L7
_L3:
        circle1.cy = parseLength(s);
          goto _L7
_L4:
        circle1.r = parseLength(s);
        if(!circle1.r.isNegative()) goto _L7; else goto _L8
_L8:
        throw new SAXException("Invalid <circle> element. r cannot be negative");
    }

    private void parseAttributesClipPath(SVG.ClipPath clippath, Attributes attributes)
        throws SAXException
    {
        int i = 0;
_L6:
        if(i >= attributes.getLength()) goto _L2; else goto _L1
_L1:
        String s = attributes.getValue(i).trim();
        _cls1..SwitchMap.com.caverock.androidsvg.SVGParser.SVGAttr[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
        JVM INSTR tableswitch 38 38: default 60
    //                   38 66;
           goto _L3 _L4
_L3:
        i++;
        continue; /* Loop/switch isn't completed */
_L4:
        if("objectBoundingBox".equals(s))
            clippath.clipPathUnitsAreUser = Boolean.valueOf(false);
        else
        if("userSpaceOnUse".equals(s))
            clippath.clipPathUnitsAreUser = Boolean.valueOf(true);
        else
            throw new SAXException("Invalid value for attribute clipPathUnits");
        if(true) goto _L3; else goto _L5
_L5:
        if(true) goto _L6; else goto _L2
_L2:
    }

    private void parseAttributesConditional(SVG.SvgConditional svgconditional, Attributes attributes)
        throws SAXException
    {
        int i = 0;
_L8:
        String s;
        if(i >= attributes.getLength())
            break MISSING_BLOCK_LABEL_182;
        s = attributes.getValue(i).trim();
        _cls1..SwitchMap.com.caverock.androidsvg.SVGParser.SVGAttr[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
        JVM INSTR tableswitch 21 25: default 76
    //                   21 82
    //                   22 96
    //                   23 107
    //                   24 121
    //                   25 135;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L6:
        break MISSING_BLOCK_LABEL_135;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L9:
        i++;
        if(true) goto _L8; else goto _L7
_L7:
        svgconditional.setRequiredFeatures(parseRequiredFeatures(s));
          goto _L9
_L3:
        svgconditional.setRequiredExtensions(s);
          goto _L9
_L4:
        svgconditional.setSystemLanguage(parseSystemLanguage(s));
          goto _L9
_L5:
        svgconditional.setRequiredFormats(parseRequiredFormats(s));
          goto _L9
        List list = parseFontFamily(s);
        HashSet hashset;
        if(list != null)
            hashset = new HashSet(list);
        else
            hashset = new HashSet(0);
        svgconditional.setRequiredFonts(hashset);
          goto _L9
    }

    private void parseAttributesCore(SVG.SvgElementBase svgelementbase, Attributes attributes)
        throws SAXException
    {
        int i = 0;
_L7:
        if(i >= attributes.getLength()) goto _L2; else goto _L1
_L1:
        String s = attributes.getQName(i);
        if(!s.equals("id") && !s.equals("xml:id")) goto _L4; else goto _L3
_L3:
        svgelementbase.id = attributes.getValue(i).trim();
_L2:
        return;
_L4:
        if(!s.equals("xml:space"))
            break; /* Loop/switch isn't completed */
        String s1 = attributes.getValue(i).trim();
        if("default".equals(s1))
            svgelementbase.spacePreserve = Boolean.FALSE;
        else
        if("preserve".equals(s1))
            svgelementbase.spacePreserve = Boolean.TRUE;
        else
            throw new SAXException((new StringBuilder()).append("Invalid value for \"xml:space\" attribute: ").append(s1).toString());
        if(true) goto _L2; else goto _L5
_L5:
        i++;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private void parseAttributesEllipse(SVG.Ellipse ellipse1, Attributes attributes)
        throws SAXException
    {
        int i = 0;
_L7:
        String s;
        if(i >= attributes.getLength())
            break MISSING_BLOCK_LABEL_162;
        s = attributes.getValue(i).trim();
        _cls1..SwitchMap.com.caverock.androidsvg.SVGParser.SVGAttr[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
        JVM INSTR tableswitch 10 13: default 72
    //                   10 102
    //                   11 132
    //                   12 78
    //                   13 90;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        break; /* Loop/switch isn't completed */
_L4:
        break; /* Loop/switch isn't completed */
_L8:
        i++;
        if(true) goto _L7; else goto _L6
_L6:
        ellipse1.cx = parseLength(s);
          goto _L8
_L5:
        ellipse1.cy = parseLength(s);
          goto _L8
_L2:
        ellipse1.rx = parseLength(s);
        if(ellipse1.rx.isNegative())
            throw new SAXException("Invalid <ellipse> element. rx cannot be negative");
          goto _L8
_L3:
        ellipse1.ry = parseLength(s);
        if(!ellipse1.ry.isNegative()) goto _L8; else goto _L9
_L9:
        throw new SAXException("Invalid <ellipse> element. ry cannot be negative");
    }

    private void parseAttributesGradient(SVG.GradientElement gradientelement, Attributes attributes)
        throws SAXException
    {
        int i = 0;
_L7:
        String s;
        if(i >= attributes.getLength())
            break MISSING_BLOCK_LABEL_231;
        s = attributes.getValue(i).trim();
        _cls1..SwitchMap.com.caverock.androidsvg.SVGParser.SVGAttr[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
        JVM INSTR lookupswitch 4: default 84
    //                   6: 207
    //                   32: 90
    //                   33: 145
    //                   34: 158;
           goto _L1 _L2 _L3 _L4 _L5
_L2:
        break MISSING_BLOCK_LABEL_207;
_L1:
        break; /* Loop/switch isn't completed */
_L3:
        break; /* Loop/switch isn't completed */
_L8:
        i++;
        if(true) goto _L7; else goto _L6
_L6:
        if("objectBoundingBox".equals(s))
            gradientelement.gradientUnitsAreUser = Boolean.valueOf(false);
        else
        if("userSpaceOnUse".equals(s))
            gradientelement.gradientUnitsAreUser = Boolean.valueOf(true);
        else
            throw new SAXException("Invalid value for attribute gradientUnits");
          goto _L8
_L4:
        gradientelement.gradientTransform = parseTransformList(s);
          goto _L8
_L5:
        try
        {
            gradientelement.spreadMethod = SVG.GradientSpread.valueOf(s);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw new SAXException((new StringBuilder()).append("Invalid spreadMethod attribute. \"").append(s).append("\" is not a valid value.").toString());
        }
          goto _L8
        if("http://www.w3.org/1999/xlink".equals(attributes.getURI(i)))
            gradientelement.href = s;
          goto _L8
    }

    private void parseAttributesImage(SVG.Image image1, Attributes attributes)
        throws SAXException
    {
        int i = 0;
_L9:
        String s;
        if(i >= attributes.getLength())
            break MISSING_BLOCK_LABEL_207;
        s = attributes.getValue(i).trim();
        _cls1..SwitchMap.com.caverock.androidsvg.SVGParser.SVGAttr[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
        JVM INSTR tableswitch 1 7: default 84
    //                   1 90
    //                   2 102
    //                   3 114
    //                   4 144
    //                   5 84
    //                   6 174
    //                   7 198;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L6 _L7
_L7:
        break MISSING_BLOCK_LABEL_198;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L10:
        i++;
        if(true) goto _L9; else goto _L8
_L8:
        image1.x = parseLength(s);
          goto _L10
_L3:
        image1.y = parseLength(s);
          goto _L10
_L4:
        image1.width = parseLength(s);
        if(image1.width.isNegative())
            throw new SAXException("Invalid <use> element. width cannot be negative");
          goto _L10
_L5:
        image1.height = parseLength(s);
        if(image1.height.isNegative())
            throw new SAXException("Invalid <use> element. height cannot be negative");
          goto _L10
_L6:
        if("http://www.w3.org/1999/xlink".equals(attributes.getURI(i)))
            image1.href = s;
          goto _L10
        parsePreserveAspectRatio(image1, s);
          goto _L10
    }

    private void parseAttributesLine(SVG.Line line1, Attributes attributes)
        throws SAXException
    {
        int i = 0;
_L7:
        String s;
        if(i >= attributes.getLength())
            break MISSING_BLOCK_LABEL_126;
        s = attributes.getValue(i).trim();
        _cls1..SwitchMap.com.caverock.androidsvg.SVGParser.SVGAttr[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
        JVM INSTR tableswitch 15 18: default 72
    //                   15 78
    //                   16 90
    //                   17 102
    //                   18 114;
           goto _L1 _L2 _L3 _L4 _L5
_L5:
        break MISSING_BLOCK_LABEL_114;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L8:
        i++;
        if(true) goto _L7; else goto _L6
_L6:
        line1.x1 = parseLength(s);
          goto _L8
_L3:
        line1.y1 = parseLength(s);
          goto _L8
_L4:
        line1.x2 = parseLength(s);
          goto _L8
        line1.y2 = parseLength(s);
          goto _L8
    }

    private void parseAttributesLinearGradient(SVG.SvgLinearGradient svglineargradient, Attributes attributes)
        throws SAXException
    {
        int i = 0;
_L7:
        String s;
        if(i >= attributes.getLength())
            break MISSING_BLOCK_LABEL_126;
        s = attributes.getValue(i).trim();
        _cls1..SwitchMap.com.caverock.androidsvg.SVGParser.SVGAttr[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
        JVM INSTR tableswitch 15 18: default 72
    //                   15 78
    //                   16 90
    //                   17 102
    //                   18 114;
           goto _L1 _L2 _L3 _L4 _L5
_L5:
        break MISSING_BLOCK_LABEL_114;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L8:
        i++;
        if(true) goto _L7; else goto _L6
_L6:
        svglineargradient.x1 = parseLength(s);
          goto _L8
_L3:
        svglineargradient.y1 = parseLength(s);
          goto _L8
_L4:
        svglineargradient.x2 = parseLength(s);
          goto _L8
        svglineargradient.y2 = parseLength(s);
          goto _L8
    }

    private void parseAttributesMarker(SVG.Marker marker1, Attributes attributes)
        throws SAXException
    {
        int i = 0;
_L9:
        String s;
        if(i >= attributes.getLength())
            break MISSING_BLOCK_LABEL_258;
        s = attributes.getValue(i).trim();
        _cls1..SwitchMap.com.caverock.androidsvg.SVGParser.SVGAttr[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
        JVM INSTR tableswitch 26 31: default 80
    //                   26 86
    //                   27 98
    //                   28 110
    //                   29 140
    //                   30 170
    //                   31 219;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L7:
        break MISSING_BLOCK_LABEL_219;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L10:
        i++;
        if(true) goto _L9; else goto _L8
_L8:
        marker1.refX = parseLength(s);
          goto _L10
_L3:
        marker1.refY = parseLength(s);
          goto _L10
_L4:
        marker1.markerWidth = parseLength(s);
        if(marker1.markerWidth.isNegative())
            throw new SAXException("Invalid <marker> element. markerWidth cannot be negative");
          goto _L10
_L5:
        marker1.markerHeight = parseLength(s);
        if(marker1.markerHeight.isNegative())
            throw new SAXException("Invalid <marker> element. markerHeight cannot be negative");
          goto _L10
_L6:
        if("strokeWidth".equals(s))
            marker1.markerUnitsAreUser = false;
        else
        if("userSpaceOnUse".equals(s))
            marker1.markerUnitsAreUser = true;
        else
            throw new SAXException("Invalid value for attribute markerUnits");
          goto _L10
        if("auto".equals(s))
            marker1.orient = Float.valueOf((0.0F / 0.0F));
        else
            marker1.orient = Float.valueOf(parseFloat(s));
          goto _L10
    }

    private void parseAttributesMask(SVG.Mask mask1, Attributes attributes)
        throws SAXException
    {
        int i = 0;
_L9:
        String s;
        if(i >= attributes.getLength())
            break MISSING_BLOCK_LABEL_300;
        s = attributes.getValue(i).trim();
        _cls1..SwitchMap.com.caverock.androidsvg.SVGParser.SVGAttr[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
        JVM INSTR lookupswitch 6: default 100
    //                   1: 216
    //                   2: 228
    //                   3: 240
    //                   4: 270
    //                   43: 106
    //                   44: 161;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        break; /* Loop/switch isn't completed */
_L6:
        break; /* Loop/switch isn't completed */
_L10:
        i++;
        if(true) goto _L9; else goto _L8
_L8:
        if("objectBoundingBox".equals(s))
            mask1.maskUnitsAreUser = Boolean.valueOf(false);
        else
        if("userSpaceOnUse".equals(s))
            mask1.maskUnitsAreUser = Boolean.valueOf(true);
        else
            throw new SAXException("Invalid value for attribute maskUnits");
          goto _L10
_L7:
        if("objectBoundingBox".equals(s))
            mask1.maskContentUnitsAreUser = Boolean.valueOf(false);
        else
        if("userSpaceOnUse".equals(s))
            mask1.maskContentUnitsAreUser = Boolean.valueOf(true);
        else
            throw new SAXException("Invalid value for attribute maskContentUnits");
          goto _L10
_L2:
        mask1.x = parseLength(s);
          goto _L10
_L3:
        mask1.y = parseLength(s);
          goto _L10
_L4:
        mask1.width = parseLength(s);
        if(mask1.width.isNegative())
            throw new SAXException("Invalid <mask> element. width cannot be negative");
          goto _L10
_L5:
        mask1.height = parseLength(s);
        if(!mask1.height.isNegative()) goto _L10; else goto _L11
_L11:
        throw new SAXException("Invalid <mask> element. height cannot be negative");
    }

    private void parseAttributesPath(SVG.Path path1, Attributes attributes)
        throws SAXException
    {
        int i = 0;
_L6:
        if(i >= attributes.getLength()) goto _L2; else goto _L1
_L1:
        String s = attributes.getValue(i).trim();
        _cls1..SwitchMap.com.caverock.androidsvg.SVGParser.SVGAttr[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
        JVM INSTR tableswitch 8 9: default 64
    //                   8 70
    //                   9 82;
           goto _L3 _L4 _L5
_L3:
        i++;
          goto _L6
_L4:
        path1.d = parsePath(s);
          goto _L3
_L5:
        path1.pathLength = Float.valueOf(parseFloat(s));
        if(path1.pathLength.floatValue() >= 0.0F) goto _L3; else goto _L7
_L7:
        throw new SAXException("Invalid <path> element. pathLength cannot be negative");
_L2:
    }

    private void parseAttributesPattern(SVG.Pattern pattern1, Attributes attributes)
        throws SAXException
    {
        int i = 0;
_L11:
        String s;
        if(i >= attributes.getLength())
            break MISSING_BLOCK_LABEL_353;
        s = attributes.getValue(i).trim();
        _cls1..SwitchMap.com.caverock.androidsvg.SVGParser.SVGAttr[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
        JVM INSTR lookupswitch 8: default 116
    //                   1: 245
    //                   2: 257
    //                   3: 269
    //                   4: 299
    //                   6: 329
    //                   40: 122
    //                   41: 177
    //                   42: 232;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L6:
        break MISSING_BLOCK_LABEL_329;
_L1:
        break; /* Loop/switch isn't completed */
_L7:
        break; /* Loop/switch isn't completed */
_L12:
        i++;
        if(true) goto _L11; else goto _L10
_L10:
        if("objectBoundingBox".equals(s))
            pattern1.patternUnitsAreUser = Boolean.valueOf(false);
        else
        if("userSpaceOnUse".equals(s))
            pattern1.patternUnitsAreUser = Boolean.valueOf(true);
        else
            throw new SAXException("Invalid value for attribute patternUnits");
          goto _L12
_L8:
        if("objectBoundingBox".equals(s))
            pattern1.patternContentUnitsAreUser = Boolean.valueOf(false);
        else
        if("userSpaceOnUse".equals(s))
            pattern1.patternContentUnitsAreUser = Boolean.valueOf(true);
        else
            throw new SAXException("Invalid value for attribute patternContentUnits");
          goto _L12
_L9:
        pattern1.patternTransform = parseTransformList(s);
          goto _L12
_L2:
        pattern1.x = parseLength(s);
          goto _L12
_L3:
        pattern1.y = parseLength(s);
          goto _L12
_L4:
        pattern1.width = parseLength(s);
        if(pattern1.width.isNegative())
            throw new SAXException("Invalid <pattern> element. width cannot be negative");
          goto _L12
_L5:
        pattern1.height = parseLength(s);
        if(pattern1.height.isNegative())
            throw new SAXException("Invalid <pattern> element. height cannot be negative");
          goto _L12
        if("http://www.w3.org/1999/xlink".equals(attributes.getURI(i)))
            pattern1.href = s;
          goto _L12
    }

    private void parseAttributesPolyLine(SVG.PolyLine polyline1, Attributes attributes, String s)
        throws SAXException
    {
        for(int i = 0; i < attributes.getLength(); i++)
        {
            if(SVGAttr.fromString(attributes.getLocalName(i)) != SVGAttr.points)
                continue;
            TextScanner textscanner = new TextScanner(attributes.getValue(i));
            ArrayList arraylist = new ArrayList();
            textscanner.skipWhitespace();
            Float float3;
            for(; !textscanner.empty(); arraylist.add(float3))
            {
                Float float2 = textscanner.nextFloat();
                if(float2 == null)
                    throw new SAXException((new StringBuilder()).append("Invalid <").append(s).append("> points attribute. Non-coordinate content found in list.").toString());
                textscanner.skipCommaWhitespace();
                float3 = textscanner.nextFloat();
                if(float3 == null)
                    throw new SAXException((new StringBuilder()).append("Invalid <").append(s).append("> points attribute. There should be an even number of coordinates.").toString());
                textscanner.skipCommaWhitespace();
                arraylist.add(float2);
            }

            polyline1.points = new float[arraylist.size()];
            int j = 0;
            for(Iterator iterator = arraylist.iterator(); iterator.hasNext();)
            {
                Float float1 = (Float)iterator.next();
                float af[] = polyline1.points;
                int k = j + 1;
                af[j] = float1.floatValue();
                j = k;
            }

        }

    }

    private void parseAttributesRadialGradient(SVG.SvgRadialGradient svgradialgradient, Attributes attributes)
        throws SAXException
    {
        int i = 0;
_L8:
        String s;
        if(i >= attributes.getLength())
            break MISSING_BLOCK_LABEL_176;
        s = attributes.getValue(i).trim();
        _cls1..SwitchMap.com.caverock.androidsvg.SVGParser.SVGAttr[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
        JVM INSTR lookupswitch 5: default 92
    //                   12: 98
    //                   13: 110
    //                   14: 122
    //                   35: 152
    //                   36: 164;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L6:
        break MISSING_BLOCK_LABEL_164;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L9:
        i++;
        if(true) goto _L8; else goto _L7
_L7:
        svgradialgradient.cx = parseLength(s);
          goto _L9
_L3:
        svgradialgradient.cy = parseLength(s);
          goto _L9
_L4:
        svgradialgradient.r = parseLength(s);
        if(svgradialgradient.r.isNegative())
            throw new SAXException("Invalid <radialGradient> element. r cannot be negative");
          goto _L9
_L5:
        svgradialgradient.fx = parseLength(s);
          goto _L9
        svgradialgradient.fy = parseLength(s);
          goto _L9
    }

    private void parseAttributesRect(SVG.Rect rect1, Attributes attributes)
        throws SAXException
    {
        int i = 0;
_L9:
        String s;
        if(i >= attributes.getLength())
            break MISSING_BLOCK_LABEL_250;
        s = attributes.getValue(i).trim();
        _cls1..SwitchMap.com.caverock.androidsvg.SVGParser.SVGAttr[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
        JVM INSTR tableswitch 1 11: default 100
    //                   1 106
    //                   2 118
    //                   3 130
    //                   4 160
    //                   5 100
    //                   6 100
    //                   7 100
    //                   8 100
    //                   9 100
    //                   10 190
    //                   11 220;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L1 _L1 _L1 _L1 _L6 _L7
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L10:
        i++;
        if(true) goto _L9; else goto _L8
_L8:
        rect1.x = parseLength(s);
          goto _L10
_L3:
        rect1.y = parseLength(s);
          goto _L10
_L4:
        rect1.width = parseLength(s);
        if(rect1.width.isNegative())
            throw new SAXException("Invalid <rect> element. width cannot be negative");
          goto _L10
_L5:
        rect1.height = parseLength(s);
        if(rect1.height.isNegative())
            throw new SAXException("Invalid <rect> element. height cannot be negative");
          goto _L10
_L6:
        rect1.rx = parseLength(s);
        if(rect1.rx.isNegative())
            throw new SAXException("Invalid <rect> element. rx cannot be negative");
          goto _L10
_L7:
        rect1.ry = parseLength(s);
        if(!rect1.ry.isNegative()) goto _L10; else goto _L11
_L11:
        throw new SAXException("Invalid <rect> element. ry cannot be negative");
    }

    private void parseAttributesSVG(SVG.Svg svg1, Attributes attributes)
        throws SAXException
    {
        int i = 0;
_L8:
        String s;
        if(i >= attributes.getLength())
            break MISSING_BLOCK_LABEL_175;
        s = attributes.getValue(i).trim();
        _cls1..SwitchMap.com.caverock.androidsvg.SVGParser.SVGAttr[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
        JVM INSTR tableswitch 1 5: default 76
    //                   1 82
    //                   2 94
    //                   3 106
    //                   4 136
    //                   5 166;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L6:
        break MISSING_BLOCK_LABEL_166;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L9:
        i++;
        if(true) goto _L8; else goto _L7
_L7:
        svg1.x = parseLength(s);
          goto _L9
_L3:
        svg1.y = parseLength(s);
          goto _L9
_L4:
        svg1.width = parseLength(s);
        if(svg1.width.isNegative())
            throw new SAXException("Invalid <svg> element. width cannot be negative");
          goto _L9
_L5:
        svg1.height = parseLength(s);
        if(svg1.height.isNegative())
            throw new SAXException("Invalid <svg> element. height cannot be negative");
          goto _L9
        svg1.version = s;
          goto _L9
    }

    private void parseAttributesStop(SVG.Stop stop1, Attributes attributes)
        throws SAXException
    {
        int i = 0;
_L5:
        String s;
        if(i >= attributes.getLength())
            break; /* Loop/switch isn't completed */
        s = attributes.getValue(i).trim();
        _cls1..SwitchMap.com.caverock.androidsvg.SVGParser.SVGAttr[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
        JVM INSTR tableswitch 37 37: default 60
    //                   37 66;
           goto _L1 _L2
_L1:
        i++;
        break; /* Loop/switch isn't completed */
_L2:
        stop1.offset = parseGradiantOffset(s);
        if(true) goto _L1; else goto _L3
_L3:
        if(true) goto _L5; else goto _L4
_L4:
    }

    private void parseAttributesStyle(SVG.SvgElementBase svgelementbase, Attributes attributes)
        throws SAXException
    {
        int i = 0;
_L5:
        if(i >= attributes.getLength()) goto _L2; else goto _L1
_L1:
        String s = attributes.getValue(i).trim();
        if(s.length() != 0) goto _L4; else goto _L3
_L3:
        i++;
          goto _L5
_L4:
        switch(_cls1..SwitchMap.com.caverock.androidsvg.SVGParser.SVGAttr[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()])
        {
        default:
            if(svgelementbase.baseStyle == null)
                svgelementbase.baseStyle = new SVG.Style();
            processStyleProperty(svgelementbase.baseStyle, attributes.getLocalName(i), attributes.getValue(i).trim());
            break;

        case 45: // '-'
            parseStyle(svgelementbase, s);
            break;

        case 46: // '.'
            svgelementbase.classNames = CSSParser.parseClassAttribute(s);
            break;
        }
        if(true) goto _L3; else goto _L2
_L2:
    }

    private void parseAttributesTRef(SVG.TRef tref1, Attributes attributes)
        throws SAXException
    {
        int i = 0;
_L5:
        String s;
        if(i >= attributes.getLength())
            break; /* Loop/switch isn't completed */
        s = attributes.getValue(i).trim();
        _cls1..SwitchMap.com.caverock.androidsvg.SVGParser.SVGAttr[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
        JVM INSTR tableswitch 6 6: default 60
    //                   6 66;
           goto _L1 _L2
_L1:
        i++;
        break; /* Loop/switch isn't completed */
_L2:
        if("http://www.w3.org/1999/xlink".equals(attributes.getURI(i)))
            tref1.href = s;
        if(true) goto _L1; else goto _L3
_L3:
        if(true) goto _L5; else goto _L4
_L4:
    }

    private void parseAttributesTextPath(SVG.TextPath textpath, Attributes attributes)
        throws SAXException
    {
        int i = 0;
_L2:
        String s;
        if(i >= attributes.getLength())
            break MISSING_BLOCK_LABEL_110;
        s = attributes.getValue(i).trim();
        switch(_cls1..SwitchMap.com.caverock.androidsvg.SVGParser.SVGAttr[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()])
        {
        default:
            break;

        case 6: // '\006'
            break; /* Loop/switch isn't completed */

        case 39: // '\''
            break;
        }
        break MISSING_BLOCK_LABEL_98;
_L3:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        if("http://www.w3.org/1999/xlink".equals(attributes.getURI(i)))
            textpath.href = s;
          goto _L3
        textpath.startOffset = parseLength(s);
          goto _L3
    }

    private void parseAttributesTextPosition(SVG.TextPositionedContainer textpositionedcontainer, Attributes attributes)
        throws SAXException
    {
        int i = 0;
_L7:
        String s;
        if(i >= attributes.getLength())
            break MISSING_BLOCK_LABEL_138;
        s = attributes.getValue(i).trim();
        _cls1..SwitchMap.com.caverock.androidsvg.SVGParser.SVGAttr[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
        JVM INSTR lookupswitch 4: default 84
    //                   1: 90
    //                   2: 102
    //                   19: 114
    //                   20: 126;
           goto _L1 _L2 _L3 _L4 _L5
_L5:
        break MISSING_BLOCK_LABEL_126;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L8:
        i++;
        if(true) goto _L7; else goto _L6
_L6:
        textpositionedcontainer.x = parseLengthList(s);
          goto _L8
_L3:
        textpositionedcontainer.y = parseLengthList(s);
          goto _L8
_L4:
        textpositionedcontainer.dx = parseLengthList(s);
          goto _L8
        textpositionedcontainer.dy = parseLengthList(s);
          goto _L8
    }

    private void parseAttributesTransform(SVG.HasTransform hastransform, Attributes attributes)
        throws SAXException
    {
        for(int i = 0; i < attributes.getLength(); i++)
            if(SVGAttr.fromString(attributes.getLocalName(i)) == SVGAttr.transform)
                hastransform.setTransform(parseTransformList(attributes.getValue(i)));

    }

    private void parseAttributesUse(SVG.Use use1, Attributes attributes)
        throws SAXException
    {
        int i = 0;
_L8:
        String s;
        if(i >= attributes.getLength())
            break MISSING_BLOCK_LABEL_194;
        s = attributes.getValue(i).trim();
        _cls1..SwitchMap.com.caverock.androidsvg.SVGParser.SVGAttr[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
        JVM INSTR tableswitch 1 6: default 80
    //                   1 86
    //                   2 98
    //                   3 110
    //                   4 140
    //                   5 80
    //                   6 170;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L6
_L6:
        break MISSING_BLOCK_LABEL_170;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L9:
        i++;
        if(true) goto _L8; else goto _L7
_L7:
        use1.x = parseLength(s);
          goto _L9
_L3:
        use1.y = parseLength(s);
          goto _L9
_L4:
        use1.width = parseLength(s);
        if(use1.width.isNegative())
            throw new SAXException("Invalid <use> element. width cannot be negative");
          goto _L9
_L5:
        use1.height = parseLength(s);
        if(use1.height.isNegative())
            throw new SAXException("Invalid <use> element. height cannot be negative");
          goto _L9
        if("http://www.w3.org/1999/xlink".equals(attributes.getURI(i)))
            use1.href = s;
          goto _L9
    }

    private void parseAttributesViewBox(SVG.SvgViewBoxContainer svgviewboxcontainer, Attributes attributes)
        throws SAXException
    {
        int i = 0;
_L2:
        String s;
        if(i >= attributes.getLength())
            break MISSING_BLOCK_LABEL_95;
        s = attributes.getValue(i).trim();
        switch(_cls1..SwitchMap.com.caverock.androidsvg.SVGParser.SVGAttr[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()])
        {
        default:
            break;

        case 86: // 'V'
            break; /* Loop/switch isn't completed */

        case 7: // '\007'
            break;
        }
        break MISSING_BLOCK_LABEL_86;
_L3:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        svgviewboxcontainer.viewBox = parseViewBox(s);
          goto _L3
        parsePreserveAspectRatio(svgviewboxcontainer, s);
          goto _L3
    }

    private void parseCSSStyleSheet(String s)
        throws SAXException
    {
        CSSParser cssparser = new CSSParser(CSSParser.MediaType.screen);
        svgDocument.addCSSRules(cssparser.parse(s));
    }

    private static SVG.CSSClipRect parseClip(String s)
        throws SAXException
    {
        SVG.CSSClipRect csscliprect;
        if("auto".equals(s))
        {
            csscliprect = null;
        } else
        {
            if(!s.toLowerCase(Locale.US).startsWith("rect("))
                throw new SAXException("Invalid clip attribute shape. Only rect() is supported.");
            TextScanner textscanner = new TextScanner(s.substring(5));
            textscanner.skipWhitespace();
            SVG.Length length = parseLengthOrAuto(textscanner);
            textscanner.skipCommaWhitespace();
            SVG.Length length1 = parseLengthOrAuto(textscanner);
            textscanner.skipCommaWhitespace();
            SVG.Length length2 = parseLengthOrAuto(textscanner);
            textscanner.skipCommaWhitespace();
            SVG.Length length3 = parseLengthOrAuto(textscanner);
            textscanner.skipWhitespace();
            if(!textscanner.consume(')'))
                throw new SAXException((new StringBuilder()).append("Bad rect() clip definition: ").append(s).toString());
            csscliprect = new SVG.CSSClipRect(length, length1, length2, length3);
        }
        return csscliprect;
    }

    private static SVG.Colour parseColour(String s)
        throws SAXException
    {
        if(s.charAt(0) != '#')
            break MISSING_BLOCK_LABEL_184;
        SVG.Colour colour;
        if(s.length() == 7)
        {
            colour = new SVG.Colour(Integer.parseInt(s.substring(1), 16));
            break MISSING_BLOCK_LABEL_316;
        }
        NumberFormatException numberformatexception;
        if(s.length() == 4)
        {
            int l = Integer.parseInt(s.substring(1), 16);
            int i1 = l & 0xf00;
            int j1 = l & 0xf0;
            int k1 = l & 0xf;
            colour = new SVG.Colour(k1 | (i1 << 16 | i1 << 12 | j1 << 8 | j1 << 4 | k1 << 4));
            break MISSING_BLOCK_LABEL_316;
        } else
        {
            try
            {
                throw new SAXException((new StringBuilder()).append("Bad hex colour value: ").append(s).toString());
            }
            // Misplaced declaration of an exception variable
            catch(NumberFormatException numberformatexception)
            {
                throw new SAXException((new StringBuilder()).append("Bad colour value: ").append(s).toString());
            }
        }
        if(s.toLowerCase(Locale.US).startsWith("rgb("))
        {
            TextScanner textscanner = new TextScanner(s.substring(4));
            textscanner.skipWhitespace();
            int i = parseColourComponent(textscanner);
            textscanner.skipCommaWhitespace();
            int j = parseColourComponent(textscanner);
            textscanner.skipCommaWhitespace();
            int k = parseColourComponent(textscanner);
            textscanner.skipWhitespace();
            if(!textscanner.consume(')'))
                throw new SAXException((new StringBuilder()).append("Bad rgb() colour value: ").append(s).toString());
            colour = new SVG.Colour(k | (i << 16 | j << 8));
        } else
        {
            colour = parseColourKeyword(s);
        }
        return colour;
    }

    private static int parseColourComponent(TextScanner textscanner)
        throws SAXException
    {
        float f = textscanner.nextFloat().floatValue();
        if(textscanner.consume('%'))
            f = (256F * f) / 100F;
        int i;
        if(f < 0.0F)
            i = 0;
        else
        if(f > 255F)
            i = 255;
        else
            i = (int)f;
        return i;
    }

    private static SVG.Colour parseColourKeyword(String s)
        throws SAXException
    {
        Integer integer = (Integer)colourKeywords.get(s.toLowerCase(Locale.US));
        if(integer == null)
            throw new SAXException((new StringBuilder()).append("Invalid colour keyword: ").append(s).toString());
        else
            return new SVG.Colour(integer.intValue());
    }

    private static SVG.SvgPaint parseColourSpecifer(String s)
        throws SAXException
    {
        Object obj;
        if(s.equals("none"))
            obj = null;
        else
        if(s.equals("currentColor"))
            obj = SVG.CurrentColor.getInstance();
        else
            obj = parseColour(s);
        return ((SVG.SvgPaint) (obj));
    }

    private static SVG.Style.FillRule parseFillRule(String s)
        throws SAXException
    {
        SVG.Style.FillRule fillrule;
        if("nonzero".equals(s))
            fillrule = SVG.Style.FillRule.NonZero;
        else
        if("evenodd".equals(s))
            fillrule = SVG.Style.FillRule.EvenOdd;
        else
            throw new SAXException((new StringBuilder()).append("Invalid fill-rule property: ").append(s).toString());
        return fillrule;
    }

    private static float parseFloat(String s)
        throws SAXException
    {
        if(s.length() == 0)
            throw new SAXException("Invalid float value (empty string)");
        float f;
        try
        {
            f = Float.parseFloat(s);
        }
        catch(NumberFormatException numberformatexception)
        {
            throw new SAXException((new StringBuilder()).append("Invalid float value: ").append(s).toString(), numberformatexception);
        }
        return f;
    }

    private static void parseFont(SVG.Style style1, String s)
        throws SAXException
    {
        Integer integer;
        SVG.Style.FontStyle fontstyle;
        String s1;
        integer = null;
        fontstyle = null;
        s1 = null;
        if("|caption|icon|menu|message-box|small-caption|status-bar|".indexOf((new StringBuilder()).append('|').append(s).append('|').toString()) == -1) goto _L2; else goto _L1
_L1:
        return;
_L2:
        TextScanner textscanner = new TextScanner(s);
_L7:
        String s2;
        s2 = textscanner.nextToken('/');
        textscanner.skipWhitespace();
        if(s2 == null)
            throw new SAXException("Invalid font style attribute: missing font size and family");
        if(integer == null || fontstyle == null) goto _L4; else goto _L3
_L3:
        SVG.Length length;
        String s3;
        length = parseFontSize(s2);
        if(!textscanner.consume('/'))
            break MISSING_BLOCK_LABEL_221;
        textscanner.skipWhitespace();
        s3 = textscanner.nextToken();
        if(s3 == null)
            throw new SAXException("Invalid font style attribute: missing line-height");
        break; /* Loop/switch isn't completed */
_L4:
        if(s2.equals("normal"))
            continue; /* Loop/switch isn't completed */
        if(integer == null)
        {
            integer = (Integer)fontWeightKeywords.get(s2);
            if(integer != null)
                continue; /* Loop/switch isn't completed */
        }
        if(fontstyle != null)
            continue; /* Loop/switch isn't completed */
        fontstyle = (SVG.Style.FontStyle)fontStyleKeywords.get(s2);
        if(fontstyle != null)
            continue; /* Loop/switch isn't completed */
        if(s1 != null || !s2.equals("small-caps")) goto _L3; else goto _L5
_L5:
        s1 = s2;
        if(true) goto _L7; else goto _L6
_L6:
        parseLength(s3);
        textscanner.skipWhitespace();
        style1.fontFamily = parseFontFamily(textscanner.restOfText());
        style1.fontSize = length;
        int i;
        SVG.Style.FontStyle fontstyle1;
        if(integer == null)
            i = 400;
        else
            i = integer.intValue();
        style1.fontWeight = Integer.valueOf(i);
        if(fontstyle == null)
            fontstyle1 = SVG.Style.FontStyle.Normal;
        else
            fontstyle1 = fontstyle;
        style1.fontStyle = fontstyle1;
        style1.specifiedFlags = 0x1e000L | style1.specifiedFlags;
        if(true) goto _L1; else goto _L8
_L8:
    }

    private static List parseFontFamily(String s)
        throws SAXException
    {
        Object obj;
        TextScanner textscanner;
        obj = null;
        textscanner = new TextScanner(s);
_L3:
        String s1;
        s1 = textscanner.nextQuotedString();
        if(s1 == null)
            s1 = textscanner.nextToken(',');
        if(s1 != null) goto _L2; else goto _L1
_L1:
        return ((List) (obj));
_L2:
        if(obj == null)
            obj = new ArrayList();
        ((List) (obj)).add(s1);
        textscanner.skipCommaWhitespace();
        if(!textscanner.empty()) goto _L3; else goto _L1
    }

    private static SVG.Length parseFontSize(String s)
        throws SAXException
    {
        SVG.Length length = (SVG.Length)fontSizeKeywords.get(s);
        if(length == null)
            length = parseLength(s);
        return length;
    }

    private static SVG.Style.FontStyle parseFontStyle(String s)
        throws SAXException
    {
        SVG.Style.FontStyle fontstyle = (SVG.Style.FontStyle)fontStyleKeywords.get(s);
        if(fontstyle == null)
            throw new SAXException((new StringBuilder()).append("Invalid font-style property: ").append(s).toString());
        else
            return fontstyle;
    }

    private static Integer parseFontWeight(String s)
        throws SAXException
    {
        Integer integer = (Integer)fontWeightKeywords.get(s);
        if(integer == null)
            throw new SAXException((new StringBuilder()).append("Invalid font-weight property: ").append(s).toString());
        else
            return integer;
    }

    private static String parseFunctionalIRI(String s, String s1)
        throws SAXException
    {
        String s2;
        if(s.equals("none"))
        {
            s2 = null;
        } else
        {
            if(!s.startsWith("url(") || !s.endsWith(")"))
                throw new SAXException((new StringBuilder()).append("Bad ").append(s1).append(" attribute. Expected \"none\" or \"url()\" format").toString());
            s2 = s.substring(4, -1 + s.length()).trim();
        }
        return s2;
    }

    private Float parseGradiantOffset(String s)
        throws SAXException
    {
        int i;
        boolean flag;
        if(s.length() == 0)
            throw new SAXException("Invalid offset value in <stop> (empty string)");
        i = s.length();
        flag = false;
        if(s.charAt(-1 + s.length()) == '%')
        {
            i--;
            flag = true;
        }
        float f;
        f = Float.parseFloat(s.substring(0, i));
        if(flag)
            f /= 100F;
          goto _L1
_L2:
        Float float1 = Float.valueOf(f);
        return float1;
_L4:
        if(f > 100F)
            f = 100F;
          goto _L2
        NumberFormatException numberformatexception;
        numberformatexception;
        throw new SAXException((new StringBuilder()).append("Invalid offset value in <stop>: ").append(s).toString(), numberformatexception);
_L1:
        if(f >= 0.0F) goto _L4; else goto _L3
_L3:
        f = 0.0F;
          goto _L2
    }

    protected static SVG.Length parseLength(String s)
        throws SAXException
    {
        if(s.length() == 0)
            throw new SAXException("Invalid length value (empty string)");
        int i = s.length();
        SVG.Unit unit = SVG.Unit.px;
        char c = s.charAt(i - 1);
        SVG.Length length;
        if(c == '%')
        {
            i--;
            unit = SVG.Unit.percent;
        } else
        if(i > 2 && Character.isLetter(c) && Character.isLetter(s.charAt(i - 2)))
        {
            i -= 2;
            String s1 = s.substring(i);
            SVG.Unit unit1;
            try
            {
                unit1 = SVG.Unit.valueOf(s1.toLowerCase(Locale.US));
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                throw new SAXException((new StringBuilder()).append("Invalid length unit specifier: ").append(s).toString());
            }
            unit = unit1;
        }
        try
        {
            length = new SVG.Length(Float.parseFloat(s.substring(0, i)), unit);
        }
        catch(NumberFormatException numberformatexception)
        {
            throw new SAXException((new StringBuilder()).append("Invalid length value: ").append(s).toString(), numberformatexception);
        }
        return length;
    }

    private static List parseLengthList(String s)
        throws SAXException
    {
        if(s.length() == 0)
            throw new SAXException("Invalid length list (empty string)");
        ArrayList arraylist = new ArrayList(1);
        TextScanner textscanner = new TextScanner(s);
        textscanner.skipWhitespace();
        for(; !textscanner.empty(); textscanner.skipCommaWhitespace())
        {
            Float float1 = textscanner.nextFloat();
            if(float1 == null)
                throw new SAXException((new StringBuilder()).append("Invalid length list value: ").append(textscanner.ahead()).toString());
            SVG.Unit unit = textscanner.nextUnit();
            if(unit == null)
                unit = SVG.Unit.px;
            arraylist.add(new SVG.Length(float1.floatValue(), unit));
        }

        return arraylist;
    }

    private static SVG.Length parseLengthOrAuto(TextScanner textscanner)
    {
        SVG.Length length;
        if(textscanner.consume("auto"))
            length = new SVG.Length(0.0F);
        else
            length = textscanner.nextLength();
        return length;
    }

    private static float parseOpacity(String s)
        throws SAXException
    {
        float f = parseFloat(s);
        if(f >= 0.0F) goto _L2; else goto _L1
_L1:
        f = 0.0F;
_L4:
        return f;
_L2:
        if(f > 1.0F)
            f = 1.0F;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static Boolean parseOverflow(String s)
        throws SAXException
    {
        Boolean boolean1;
        if("visible".equals(s) || "auto".equals(s))
            boolean1 = Boolean.TRUE;
        else
        if("hidden".equals(s) || "scroll".equals(s))
            boolean1 = Boolean.FALSE;
        else
            throw new SAXException((new StringBuilder()).append("Invalid toverflow property: ").append(s).toString());
        return boolean1;
    }

    private static SVG.SvgPaint parsePaintSpecifier(String s, String s1)
        throws SAXException
    {
        Object obj;
        if(s.startsWith("url("))
        {
            int i = s.indexOf(")");
            if(i == -1)
                throw new SAXException((new StringBuilder()).append("Bad ").append(s1).append(" attribute. Unterminated url() reference").toString());
            String s2 = s.substring(4, i).trim();
            SVG.SvgPaint svgpaint = null;
            String s3 = s.substring(i + 1).trim();
            if(s3.length() > 0)
                svgpaint = parseColourSpecifer(s3);
            obj = new SVG.PaintReference(s2, svgpaint);
        } else
        {
            obj = parseColourSpecifer(s);
        }
        return ((SVG.SvgPaint) (obj));
    }

    private static SVG.PathDefinition parsePath(String s)
        throws SAXException
    {
        TextScanner textscanner;
        float f;
        float f1;
        float f2;
        float f3;
        float f4;
        float f5;
        SVG.PathDefinition pathdefinition;
        textscanner = new TextScanner(s);
        f = 0.0F;
        f1 = 0.0F;
        f2 = 0.0F;
        f3 = 0.0F;
        f4 = 0.0F;
        f5 = 0.0F;
        pathdefinition = new SVG.PathDefinition();
        if(!textscanner.empty()) goto _L2; else goto _L1
_L1:
        return pathdefinition;
_L2:
        int i = textscanner.nextChar().intValue();
        if(i != 77 && i != 109) goto _L1; else goto _L3
_L3:
        textscanner.skipWhitespace();
        i;
        JVM INSTR lookupswitch 20: default 244
    //                   65: 247
    //                   67: 675
    //                   72: 1141
    //                   76: 550
    //                   77: 363
    //                   81: 1323
    //                   83: 906
    //                   84: 1504
    //                   86: 1232
    //                   90: 1119
    //                   97: 247
    //                   99: 675
    //                   104: 1141
    //                   108: 550
    //                   109: 363
    //                   113: 1323
    //                   115: 906
    //                   116: 1504
    //                   118: 1232
    //                   122: 1119;
           goto _L1 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13
_L4:
        Float float27;
        Float float28;
        Float float29;
        Boolean boolean1;
        Boolean boolean2;
        Float float30;
        Float float31;
        float27 = textscanner.nextFloat();
        float28 = textscanner.checkedNextFloat(float27);
        float29 = textscanner.checkedNextFloat(float28);
        boolean1 = textscanner.checkedNextFlag(float29);
        boolean2 = textscanner.checkedNextFlag(boolean1);
        float30 = textscanner.checkedNextFloat(boolean2);
        float31 = textscanner.checkedNextFloat(float30);
        if(float31 != null && float27.floatValue() >= 0.0F && float28.floatValue() >= 0.0F) goto _L15; else goto _L14
_L14:
        Log.e("SVGParser", (new StringBuilder()).append("Bad path coords for ").append((char)i).append(" path segment").toString());
          goto _L1
_L8:
        Float float25;
        Float float26;
        float25 = textscanner.nextFloat();
        float26 = textscanner.checkedNextFloat(float25);
        if(float26 != null) goto _L17; else goto _L16
_L16:
        Log.e("SVGParser", (new StringBuilder()).append("Bad path coords for ").append((char)i).append(" path segment").toString());
          goto _L1
_L17:
        if(i == 109 && !pathdefinition.isEmpty())
        {
            float25 = Float.valueOf(f + float25.floatValue());
            float26 = Float.valueOf(f1 + float26.floatValue());
        }
        pathdefinition.moveTo(float25.floatValue(), float26.floatValue());
        f4 = float25.floatValue();
        f2 = f4;
        f = f4;
        f5 = float26.floatValue();
        f3 = f5;
        f1 = f5;
        if(i == 109)
            i = 108;
        else
            i = 76;
_L21:
        textscanner.skipCommaWhitespace();
        if(textscanner.empty()) goto _L1; else goto _L18
_L18:
        if(textscanner.hasLetter())
            i = textscanner.nextChar().intValue();
          goto _L3
_L7:
        Float float23;
        Float float24;
        float23 = textscanner.nextFloat();
        float24 = textscanner.checkedNextFloat(float23);
        if(float24 != null) goto _L20; else goto _L19
_L19:
        Log.e("SVGParser", (new StringBuilder()).append("Bad path coords for ").append((char)i).append(" path segment").toString());
          goto _L1
_L20:
        if(i == 108)
        {
            float23 = Float.valueOf(f + float23.floatValue());
            float24 = Float.valueOf(f1 + float24.floatValue());
        }
        pathdefinition.lineTo(float23.floatValue(), float24.floatValue());
        f4 = float23.floatValue();
        f = f4;
        f5 = float24.floatValue();
        f1 = f5;
          goto _L21
_L5:
        Float float17;
        Float float18;
        Float float19;
        Float float20;
        Float float21;
        Float float22;
        float17 = textscanner.nextFloat();
        float18 = textscanner.checkedNextFloat(float17);
        float19 = textscanner.checkedNextFloat(float18);
        float20 = textscanner.checkedNextFloat(float19);
        float21 = textscanner.checkedNextFloat(float20);
        float22 = textscanner.checkedNextFloat(float21);
        if(float22 != null) goto _L23; else goto _L22
_L22:
        Log.e("SVGParser", (new StringBuilder()).append("Bad path coords for ").append((char)i).append(" path segment").toString());
          goto _L1
_L23:
        if(i == 99)
        {
            float21 = Float.valueOf(f + float21.floatValue());
            float22 = Float.valueOf(f1 + float22.floatValue());
            float17 = Float.valueOf(f + float17.floatValue());
            float18 = Float.valueOf(f1 + float18.floatValue());
            float19 = Float.valueOf(f + float19.floatValue());
            float20 = Float.valueOf(f1 + float20.floatValue());
        }
        pathdefinition.cubicTo(float17.floatValue(), float18.floatValue(), float19.floatValue(), float20.floatValue(), float21.floatValue(), float22.floatValue());
        f4 = float19.floatValue();
        f5 = float20.floatValue();
        f = float21.floatValue();
        f1 = float22.floatValue();
          goto _L21
_L10:
        Float float11;
        Float float12;
        Float float13;
        Float float14;
        Float float15;
        Float float16;
        float11 = Float.valueOf(2.0F * f - f4);
        float12 = Float.valueOf(2.0F * f1 - f5);
        float13 = textscanner.nextFloat();
        float14 = textscanner.checkedNextFloat(float13);
        float15 = textscanner.checkedNextFloat(float14);
        float16 = textscanner.checkedNextFloat(float15);
        if(float16 != null) goto _L25; else goto _L24
_L24:
        Log.e("SVGParser", (new StringBuilder()).append("Bad path coords for ").append((char)i).append(" path segment").toString());
          goto _L1
_L25:
        if(i == 115)
        {
            float15 = Float.valueOf(f + float15.floatValue());
            float16 = Float.valueOf(f1 + float16.floatValue());
            float13 = Float.valueOf(f + float13.floatValue());
            float14 = Float.valueOf(f1 + float14.floatValue());
        }
        pathdefinition.cubicTo(float11.floatValue(), float12.floatValue(), float13.floatValue(), float14.floatValue(), float15.floatValue(), float16.floatValue());
        f4 = float13.floatValue();
        f5 = float14.floatValue();
        f = float15.floatValue();
        f1 = float16.floatValue();
          goto _L21
_L13:
        pathdefinition.close();
        f4 = f2;
        f = f2;
        f5 = f3;
        f1 = f3;
          goto _L21
_L6:
        Float float10 = textscanner.nextFloat();
        if(float10 != null) goto _L27; else goto _L26
_L26:
        Log.e("SVGParser", (new StringBuilder()).append("Bad path coords for ").append((char)i).append(" path segment").toString());
          goto _L1
_L27:
        if(i == 104)
            float10 = Float.valueOf(f + float10.floatValue());
        pathdefinition.lineTo(float10.floatValue(), f1);
        f4 = float10.floatValue();
        f = f4;
          goto _L21
_L12:
        Float float9 = textscanner.nextFloat();
        if(float9 != null) goto _L29; else goto _L28
_L28:
        Log.e("SVGParser", (new StringBuilder()).append("Bad path coords for ").append((char)i).append(" path segment").toString());
          goto _L1
_L29:
        if(i == 118)
            float9 = Float.valueOf(f1 + float9.floatValue());
        pathdefinition.lineTo(f, float9.floatValue());
        f5 = float9.floatValue();
        f1 = f5;
          goto _L21
_L9:
        Float float5;
        Float float6;
        Float float7;
        Float float8;
        float5 = textscanner.nextFloat();
        float6 = textscanner.checkedNextFloat(float5);
        float7 = textscanner.checkedNextFloat(float6);
        float8 = textscanner.checkedNextFloat(float7);
        if(float8 != null) goto _L31; else goto _L30
_L30:
        Log.e("SVGParser", (new StringBuilder()).append("Bad path coords for ").append((char)i).append(" path segment").toString());
          goto _L1
_L31:
        if(i == 113)
        {
            float7 = Float.valueOf(f + float7.floatValue());
            float8 = Float.valueOf(f1 + float8.floatValue());
            float5 = Float.valueOf(f + float5.floatValue());
            float6 = Float.valueOf(f1 + float6.floatValue());
        }
        pathdefinition.quadTo(float5.floatValue(), float6.floatValue(), float7.floatValue(), float8.floatValue());
        f4 = float5.floatValue();
        f5 = float6.floatValue();
        f = float7.floatValue();
        f1 = float8.floatValue();
          goto _L21
_L11:
        Float float1;
        Float float2;
        Float float3;
        Float float4;
        float1 = Float.valueOf(2.0F * f - f4);
        float2 = Float.valueOf(2.0F * f1 - f5);
        float3 = textscanner.nextFloat();
        float4 = textscanner.checkedNextFloat(float3);
        if(float4 != null) goto _L33; else goto _L32
_L32:
        Log.e("SVGParser", (new StringBuilder()).append("Bad path coords for ").append((char)i).append(" path segment").toString());
          goto _L1
_L33:
        if(i == 116)
        {
            float3 = Float.valueOf(f + float3.floatValue());
            float4 = Float.valueOf(f1 + float4.floatValue());
        }
        pathdefinition.quadTo(float1.floatValue(), float2.floatValue(), float3.floatValue(), float4.floatValue());
        f4 = float1.floatValue();
        f5 = float2.floatValue();
        f = float3.floatValue();
        f1 = float4.floatValue();
          goto _L21
_L15:
        if(i == 97)
        {
            float30 = Float.valueOf(f + float30.floatValue());
            float31 = Float.valueOf(f1 + float31.floatValue());
        }
        pathdefinition.arcTo(float27.floatValue(), float28.floatValue(), float29.floatValue(), boolean1.booleanValue(), boolean2.booleanValue(), float30.floatValue(), float31.floatValue());
        f4 = float30.floatValue();
        f = f4;
        f5 = float31.floatValue();
        f1 = f5;
          goto _L21
    }

    private static void parsePreserveAspectRatio(SVG.SvgPreserveAspectRatioContainer svgpreserveaspectratiocontainer, String s)
        throws SAXException
    {
        TextScanner textscanner = new TextScanner(s);
        textscanner.skipWhitespace();
        PreserveAspectRatio.Scale scale = null;
        String s1 = textscanner.nextToken();
        if("defer".equals(s1))
        {
            textscanner.skipWhitespace();
            s1 = textscanner.nextToken();
        }
        PreserveAspectRatio.Alignment alignment = (PreserveAspectRatio.Alignment)aspectRatioKeywords.get(s1);
        textscanner.skipWhitespace();
        if(!textscanner.empty())
        {
            String s2 = textscanner.nextToken();
            if(s2.equals("meet"))
                scale = PreserveAspectRatio.Scale.Meet;
            else
            if(s2.equals("slice"))
                scale = PreserveAspectRatio.Scale.Slice;
            else
                throw new SAXException((new StringBuilder()).append("Invalid preserveAspectRatio definition: ").append(s).toString());
        }
        svgpreserveaspectratiocontainer.preserveAspectRatio = new PreserveAspectRatio(alignment, scale);
    }

    private static Set parseRequiredFeatures(String s)
        throws SAXException
    {
        TextScanner textscanner = new TextScanner(s);
        HashSet hashset = new HashSet();
        while(!textscanner.empty()) 
        {
            String s1 = textscanner.nextToken();
            if(s1.startsWith("http://www.w3.org/TR/SVG11/feature#"))
                hashset.add(s1.substring("http://www.w3.org/TR/SVG11/feature#".length()));
            else
                hashset.add("UNSUPPORTED");
            textscanner.skipWhitespace();
        }
        return hashset;
    }

    private static Set parseRequiredFormats(String s)
        throws SAXException
    {
        TextScanner textscanner = new TextScanner(s);
        HashSet hashset = new HashSet();
        for(; !textscanner.empty(); textscanner.skipWhitespace())
            hashset.add(textscanner.nextToken());

        return hashset;
    }

    private static SVG.Length[] parseStrokeDashArray(String s)
        throws SAXException
    {
        SVG.Length alength[];
        TextScanner textscanner;
        alength = null;
        textscanner = new TextScanner(s);
        textscanner.skipWhitespace();
        if(!textscanner.empty()) goto _L2; else goto _L1
_L1:
        return alength;
_L2:
        SVG.Length length = textscanner.nextLength();
        if(length != null)
        {
            if(length.isNegative())
                throw new SAXException((new StringBuilder()).append("Invalid stroke-dasharray. Dash segemnts cannot be negative: ").append(s).toString());
            float f = length.floatValue();
            ArrayList arraylist = new ArrayList();
            arraylist.add(length);
            while(!textscanner.empty()) 
            {
                textscanner.skipCommaWhitespace();
                SVG.Length length1 = textscanner.nextLength();
                if(length1 == null)
                    throw new SAXException((new StringBuilder()).append("Invalid stroke-dasharray. Non-Length content found: ").append(s).toString());
                if(length1.isNegative())
                    throw new SAXException((new StringBuilder()).append("Invalid stroke-dasharray. Dash segemnts cannot be negative: ").append(s).toString());
                arraylist.add(length1);
                f += length1.floatValue();
            }
            if(f != 0.0F)
                alength = (SVG.Length[])arraylist.toArray(new SVG.Length[arraylist.size()]);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    private static SVG.Style.LineCaps parseStrokeLineCap(String s)
        throws SAXException
    {
        SVG.Style.LineCaps linecaps;
        if("butt".equals(s))
            linecaps = SVG.Style.LineCaps.Butt;
        else
        if("round".equals(s))
            linecaps = SVG.Style.LineCaps.Round;
        else
        if("square".equals(s))
            linecaps = SVG.Style.LineCaps.Square;
        else
            throw new SAXException((new StringBuilder()).append("Invalid stroke-linecap property: ").append(s).toString());
        return linecaps;
    }

    private static SVG.Style.LineJoin parseStrokeLineJoin(String s)
        throws SAXException
    {
        SVG.Style.LineJoin linejoin;
        if("miter".equals(s))
            linejoin = SVG.Style.LineJoin.Miter;
        else
        if("round".equals(s))
            linejoin = SVG.Style.LineJoin.Round;
        else
        if("bevel".equals(s))
            linejoin = SVG.Style.LineJoin.Bevel;
        else
            throw new SAXException((new StringBuilder()).append("Invalid stroke-linejoin property: ").append(s).toString());
        return linejoin;
    }

    private static void parseStyle(SVG.SvgElementBase svgelementbase, String s)
        throws SAXException
    {
        TextScanner textscanner = new TextScanner(s.replaceAll("/\\*.*?\\*/", ""));
_L5:
        String s1;
        s1 = textscanner.nextToken(':');
        textscanner.skipWhitespace();
        if(textscanner.consume(':')) goto _L2; else goto _L1
_L1:
        return;
_L2:
        String s2;
        textscanner.skipWhitespace();
        s2 = textscanner.nextToken(';');
        if(s2 == null) goto _L1; else goto _L3
_L3:
        textscanner.skipWhitespace();
        if(textscanner.empty() || textscanner.consume(';'))
        {
            if(svgelementbase.style == null)
                svgelementbase.style = new SVG.Style();
            processStyleProperty(svgelementbase.style, s1, s2);
            textscanner.skipWhitespace();
        }
        if(true) goto _L5; else goto _L4
_L4:
    }

    private static Set parseSystemLanguage(String s)
        throws SAXException
    {
        TextScanner textscanner = new TextScanner(s);
        HashSet hashset = new HashSet();
        for(; !textscanner.empty(); textscanner.skipWhitespace())
        {
            String s1 = textscanner.nextToken();
            int i = s1.indexOf('-');
            if(i != -1)
                s1 = s1.substring(0, i);
            hashset.add((new Locale(s1, "", "")).getLanguage());
        }

        return hashset;
    }

    private static SVG.Style.TextAnchor parseTextAnchor(String s)
        throws SAXException
    {
        SVG.Style.TextAnchor textanchor;
        if("start".equals(s))
            textanchor = SVG.Style.TextAnchor.Start;
        else
        if("middle".equals(s))
            textanchor = SVG.Style.TextAnchor.Middle;
        else
        if("end".equals(s))
            textanchor = SVG.Style.TextAnchor.End;
        else
            throw new SAXException((new StringBuilder()).append("Invalid text-anchor property: ").append(s).toString());
        return textanchor;
    }

    private static SVG.Style.TextDecoration parseTextDecoration(String s)
        throws SAXException
    {
        SVG.Style.TextDecoration textdecoration;
        if("none".equals(s))
            textdecoration = SVG.Style.TextDecoration.None;
        else
        if("underline".equals(s))
            textdecoration = SVG.Style.TextDecoration.Underline;
        else
        if("overline".equals(s))
            textdecoration = SVG.Style.TextDecoration.Overline;
        else
        if("line-through".equals(s))
            textdecoration = SVG.Style.TextDecoration.LineThrough;
        else
        if("blink".equals(s))
            textdecoration = SVG.Style.TextDecoration.Blink;
        else
            throw new SAXException((new StringBuilder()).append("Invalid text-decoration property: ").append(s).toString());
        return textdecoration;
    }

    private static SVG.Style.TextDirection parseTextDirection(String s)
        throws SAXException
    {
        SVG.Style.TextDirection textdirection;
        if("ltr".equals(s))
            textdirection = SVG.Style.TextDirection.LTR;
        else
        if("rtl".equals(s))
            textdirection = SVG.Style.TextDirection.RTL;
        else
            throw new SAXException((new StringBuilder()).append("Invalid direction property: ").append(s).toString());
        return textdirection;
    }

    private Matrix parseTransformList(String s)
        throws SAXException
    {
        Matrix matrix;
        TextScanner textscanner;
        matrix = new Matrix();
        textscanner = new TextScanner(s);
        textscanner.skipWhitespace();
_L6:
        if(textscanner.empty()) goto _L2; else goto _L1
_L1:
        String s1;
        s1 = textscanner.nextFunction();
        if(s1 == null)
            throw new SAXException((new StringBuilder()).append("Bad transform function encountered in transform list: ").append(s).toString());
        if(s1.equals("matrix"))
        {
            textscanner.skipWhitespace();
            Float float10 = textscanner.nextFloat();
            textscanner.skipCommaWhitespace();
            Float float11 = textscanner.nextFloat();
            textscanner.skipCommaWhitespace();
            Float float12 = textscanner.nextFloat();
            textscanner.skipCommaWhitespace();
            Float float13 = textscanner.nextFloat();
            textscanner.skipCommaWhitespace();
            Float float14 = textscanner.nextFloat();
            textscanner.skipCommaWhitespace();
            Float float15 = textscanner.nextFloat();
            textscanner.skipWhitespace();
            if(float15 == null || !textscanner.consume(')'))
                throw new SAXException((new StringBuilder()).append("Invalid transform list: ").append(s).toString());
            Matrix matrix1 = new Matrix();
            float af[] = new float[9];
            af[0] = float10.floatValue();
            af[1] = float12.floatValue();
            af[2] = float14.floatValue();
            af[3] = float11.floatValue();
            af[4] = float13.floatValue();
            af[5] = float15.floatValue();
            af[6] = 0.0F;
            af[7] = 0.0F;
            af[8] = 1.0F;
            matrix1.setValues(af);
            matrix.preConcat(matrix1);
        } else
        if(s1.equals("translate"))
        {
            textscanner.skipWhitespace();
            Float float8 = textscanner.nextFloat();
            Float float9 = textscanner.possibleNextFloat();
            textscanner.skipWhitespace();
            if(float8 == null || !textscanner.consume(')'))
                throw new SAXException((new StringBuilder()).append("Invalid transform list: ").append(s).toString());
            if(float9 == null)
                matrix.preTranslate(float8.floatValue(), 0.0F);
            else
                matrix.preTranslate(float8.floatValue(), float9.floatValue());
        } else
        if(s1.equals("scale"))
        {
            textscanner.skipWhitespace();
            Float float6 = textscanner.nextFloat();
            Float float7 = textscanner.possibleNextFloat();
            textscanner.skipWhitespace();
            if(float6 == null || !textscanner.consume(')'))
                throw new SAXException((new StringBuilder()).append("Invalid transform list: ").append(s).toString());
            if(float7 == null)
                matrix.preScale(float6.floatValue(), float6.floatValue());
            else
                matrix.preScale(float6.floatValue(), float7.floatValue());
        } else
        if(s1.equals("rotate"))
        {
            textscanner.skipWhitespace();
            Float float3 = textscanner.nextFloat();
            Float float4 = textscanner.possibleNextFloat();
            Float float5 = textscanner.possibleNextFloat();
            textscanner.skipWhitespace();
            if(float3 == null || !textscanner.consume(')'))
                throw new SAXException((new StringBuilder()).append("Invalid transform list: ").append(s).toString());
            if(float4 == null)
                matrix.preRotate(float3.floatValue());
            else
            if(float5 != null)
                matrix.preRotate(float3.floatValue(), float4.floatValue(), float5.floatValue());
            else
                throw new SAXException((new StringBuilder()).append("Invalid transform list: ").append(s).toString());
        } else
        if(s1.equals("skewX"))
        {
            textscanner.skipWhitespace();
            Float float2 = textscanner.nextFloat();
            textscanner.skipWhitespace();
            if(float2 == null || !textscanner.consume(')'))
                throw new SAXException((new StringBuilder()).append("Invalid transform list: ").append(s).toString());
            matrix.preSkew((float)Math.tan(Math.toRadians(float2.floatValue())), 0.0F);
        } else
        {
            if(!s1.equals("skewY"))
                continue; /* Loop/switch isn't completed */
            textscanner.skipWhitespace();
            Float float1 = textscanner.nextFloat();
            textscanner.skipWhitespace();
            if(float1 == null || !textscanner.consume(')'))
                throw new SAXException((new StringBuilder()).append("Invalid transform list: ").append(s).toString());
            matrix.preSkew(0.0F, (float)Math.tan(Math.toRadians(float1.floatValue())));
        }
_L4:
        if(!textscanner.empty())
            break MISSING_BLOCK_LABEL_894;
_L2:
        return matrix;
        if(s1 == null) goto _L4; else goto _L3
_L3:
        throw new SAXException((new StringBuilder()).append("Invalid transform list fn: ").append(s1).append(")").toString());
        textscanner.skipCommaWhitespace();
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static SVG.Style.VectorEffect parseVectorEffect(String s)
        throws SAXException
    {
        SVG.Style.VectorEffect vectoreffect;
        if("none".equals(s))
            vectoreffect = SVG.Style.VectorEffect.None;
        else
        if("non-scaling-stroke".equals(s))
            vectoreffect = SVG.Style.VectorEffect.NonScalingStroke;
        else
            throw new SAXException((new StringBuilder()).append("Invalid vector-effect property: ").append(s).toString());
        return vectoreffect;
    }

    private static SVG.Box parseViewBox(String s)
        throws SAXException
    {
        TextScanner textscanner = new TextScanner(s);
        textscanner.skipWhitespace();
        Float float1 = textscanner.nextFloat();
        textscanner.skipCommaWhitespace();
        Float float2 = textscanner.nextFloat();
        textscanner.skipCommaWhitespace();
        Float float3 = textscanner.nextFloat();
        textscanner.skipCommaWhitespace();
        Float float4 = textscanner.nextFloat();
        if(float1 == null || float2 == null || float3 == null || float4 == null)
            throw new SAXException("Invalid viewBox definition - should have four numbers");
        if(float3.floatValue() < 0.0F)
            throw new SAXException("Invalid viewBox. width cannot be negative");
        if(float4.floatValue() < 0.0F)
            throw new SAXException("Invalid viewBox. height cannot be negative");
        else
            return new SVG.Box(float1.floatValue(), float2.floatValue(), float3.floatValue(), float4.floatValue());
    }

    private void path(Attributes attributes)
        throws SAXException
    {
        debug("<path>", new Object[0]);
        if(currentElement == null)
        {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else
        {
            SVG.Path path1 = new SVG.Path();
            path1.document = svgDocument;
            path1.parent = currentElement;
            parseAttributesCore(path1, attributes);
            parseAttributesStyle(path1, attributes);
            parseAttributesTransform(path1, attributes);
            parseAttributesConditional(path1, attributes);
            parseAttributesPath(path1, attributes);
            currentElement.addChild(path1);
            return;
        }
    }

    private void pattern(Attributes attributes)
        throws SAXException
    {
        debug("<pattern>", new Object[0]);
        if(currentElement == null)
        {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else
        {
            SVG.Pattern pattern1 = new SVG.Pattern();
            pattern1.document = svgDocument;
            pattern1.parent = currentElement;
            parseAttributesCore(pattern1, attributes);
            parseAttributesStyle(pattern1, attributes);
            parseAttributesConditional(pattern1, attributes);
            parseAttributesViewBox(pattern1, attributes);
            parseAttributesPattern(pattern1, attributes);
            currentElement.addChild(pattern1);
            currentElement = pattern1;
            return;
        }
    }

    private void polygon(Attributes attributes)
        throws SAXException
    {
        debug("<polygon>", new Object[0]);
        if(currentElement == null)
        {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else
        {
            SVG.Polygon polygon1 = new SVG.Polygon();
            polygon1.document = svgDocument;
            polygon1.parent = currentElement;
            parseAttributesCore(polygon1, attributes);
            parseAttributesStyle(polygon1, attributes);
            parseAttributesTransform(polygon1, attributes);
            parseAttributesConditional(polygon1, attributes);
            parseAttributesPolyLine(polygon1, attributes, "polygon");
            currentElement.addChild(polygon1);
            return;
        }
    }

    private void polyline(Attributes attributes)
        throws SAXException
    {
        debug("<polyline>", new Object[0]);
        if(currentElement == null)
        {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else
        {
            SVG.PolyLine polyline1 = new SVG.PolyLine();
            polyline1.document = svgDocument;
            polyline1.parent = currentElement;
            parseAttributesCore(polyline1, attributes);
            parseAttributesStyle(polyline1, attributes);
            parseAttributesTransform(polyline1, attributes);
            parseAttributesConditional(polyline1, attributes);
            parseAttributesPolyLine(polyline1, attributes, "polyline");
            currentElement.addChild(polyline1);
            return;
        }
    }

    protected static void processStyleProperty(SVG.Style style1, String s, String s1)
        throws SAXException
    {
_L2:
        return;
        if(s1.length() == 0 || s1.equals("inherit")) goto _L2; else goto _L1
_L1:
        switch(_cls1..SwitchMap.com.caverock.androidsvg.SVGParser.SVGAttr[SVGAttr.fromString(s).ordinal()])
        {
        case 47: // '/'
            style1.fill = parsePaintSpecifier(s1, "fill");
            style1.specifiedFlags = 1L | style1.specifiedFlags;
            break;

        case 48: // '0'
            style1.fillRule = parseFillRule(s1);
            style1.specifiedFlags = 2L | style1.specifiedFlags;
            break;

        case 49: // '1'
            style1.fillOpacity = Float.valueOf(parseOpacity(s1));
            style1.specifiedFlags = 4L | style1.specifiedFlags;
            break;

        case 50: // '2'
            style1.stroke = parsePaintSpecifier(s1, "stroke");
            style1.specifiedFlags = 8L | style1.specifiedFlags;
            break;

        case 51: // '3'
            style1.strokeOpacity = Float.valueOf(parseOpacity(s1));
            style1.specifiedFlags = 16L | style1.specifiedFlags;
            break;

        case 52: // '4'
            style1.strokeWidth = parseLength(s1);
            style1.specifiedFlags = 32L | style1.specifiedFlags;
            break;

        case 53: // '5'
            style1.strokeLineCap = parseStrokeLineCap(s1);
            style1.specifiedFlags = 64L | style1.specifiedFlags;
            break;

        case 54: // '6'
            style1.strokeLineJoin = parseStrokeLineJoin(s1);
            style1.specifiedFlags = 128L | style1.specifiedFlags;
            break;

        case 55: // '7'
            style1.strokeMiterLimit = Float.valueOf(parseFloat(s1));
            style1.specifiedFlags = 256L | style1.specifiedFlags;
            break;

        case 56: // '8'
            if("none".equals(s1))
                style1.strokeDashArray = null;
            else
                style1.strokeDashArray = parseStrokeDashArray(s1);
            style1.specifiedFlags = 512L | style1.specifiedFlags;
            break;

        case 57: // '9'
            style1.strokeDashOffset = parseLength(s1);
            style1.specifiedFlags = 1024L | style1.specifiedFlags;
            break;

        case 58: // ':'
            style1.opacity = Float.valueOf(parseOpacity(s1));
            style1.specifiedFlags = 2048L | style1.specifiedFlags;
            break;

        case 59: // ';'
            style1.color = parseColour(s1);
            style1.specifiedFlags = 4096L | style1.specifiedFlags;
            break;

        case 60: // '<'
            parseFont(style1, s1);
            break;

        case 61: // '='
            style1.fontFamily = parseFontFamily(s1);
            style1.specifiedFlags = 8192L | style1.specifiedFlags;
            break;

        case 62: // '>'
            style1.fontSize = parseFontSize(s1);
            style1.specifiedFlags = 16384L | style1.specifiedFlags;
            break;

        case 63: // '?'
            style1.fontWeight = parseFontWeight(s1);
            style1.specifiedFlags = 32768L | style1.specifiedFlags;
            break;

        case 64: // '@'
            style1.fontStyle = parseFontStyle(s1);
            style1.specifiedFlags = 0x10000L | style1.specifiedFlags;
            break;

        case 65: // 'A'
            style1.textDecoration = parseTextDecoration(s1);
            style1.specifiedFlags = 0x20000L | style1.specifiedFlags;
            break;

        case 66: // 'B'
            style1.direction = parseTextDirection(s1);
            style1.specifiedFlags = 0x1000000000L | style1.specifiedFlags;
            break;

        case 67: // 'C'
            style1.textAnchor = parseTextAnchor(s1);
            style1.specifiedFlags = 0x40000L | style1.specifiedFlags;
            break;

        case 68: // 'D'
            style1.overflow = parseOverflow(s1);
            style1.specifiedFlags = 0x80000L | style1.specifiedFlags;
            break;

        case 69: // 'E'
            style1.markerStart = parseFunctionalIRI(s1, s);
            style1.markerMid = style1.markerStart;
            style1.markerEnd = style1.markerStart;
            style1.specifiedFlags = 0xe00000L | style1.specifiedFlags;
            break;

        case 70: // 'F'
            style1.markerStart = parseFunctionalIRI(s1, s);
            style1.specifiedFlags = 0x200000L | style1.specifiedFlags;
            break;

        case 71: // 'G'
            style1.markerMid = parseFunctionalIRI(s1, s);
            style1.specifiedFlags = 0x400000L | style1.specifiedFlags;
            break;

        case 72: // 'H'
            style1.markerEnd = parseFunctionalIRI(s1, s);
            style1.specifiedFlags = 0x800000L | style1.specifiedFlags;
            break;

        case 73: // 'I'
            if(s1.indexOf('|') >= 0 || "|inline|block|list-item|run-in|compact|marker|table|inline-table|table-row-group|table-header-group|table-footer-group|table-row|table-column-group|table-column|table-cell|table-caption|none|".indexOf((new StringBuilder()).append('|').append(s1).append('|').toString()) == -1)
                throw new SAXException((new StringBuilder()).append("Invalid value for \"display\" attribute: ").append(s1).toString());
            boolean flag;
            if(!s1.equals("none"))
                flag = true;
            else
                flag = false;
            style1.display = Boolean.valueOf(flag);
            style1.specifiedFlags = 0x1000000L | style1.specifiedFlags;
            break;

        case 74: // 'J'
            if(s1.indexOf('|') >= 0 || "|visible|hidden|collapse|".indexOf((new StringBuilder()).append('|').append(s1).append('|').toString()) == -1)
                throw new SAXException((new StringBuilder()).append("Invalid value for \"visibility\" attribute: ").append(s1).toString());
            style1.visibility = Boolean.valueOf(s1.equals("visible"));
            style1.specifiedFlags = 0x2000000L | style1.specifiedFlags;
            break;

        case 75: // 'K'
            if(s1.equals("currentColor"))
                style1.stopColor = SVG.CurrentColor.getInstance();
            else
                style1.stopColor = parseColour(s1);
            style1.specifiedFlags = 0x4000000L | style1.specifiedFlags;
            break;

        case 76: // 'L'
            style1.stopOpacity = Float.valueOf(parseOpacity(s1));
            style1.specifiedFlags = 0x8000000L | style1.specifiedFlags;
            break;

        case 77: // 'M'
            style1.clip = parseClip(s1);
            style1.specifiedFlags = 0x100000L | style1.specifiedFlags;
            break;

        case 78: // 'N'
            style1.clipPath = parseFunctionalIRI(s1, s);
            style1.specifiedFlags = 0x10000000L | style1.specifiedFlags;
            break;

        case 79: // 'O'
            style1.clipRule = parseFillRule(s1);
            style1.specifiedFlags = 0x20000000L | style1.specifiedFlags;
            break;

        case 80: // 'P'
            style1.mask = parseFunctionalIRI(s1, s);
            style1.specifiedFlags = 0x40000000L | style1.specifiedFlags;
            break;

        case 81: // 'Q'
            if(s1.equals("currentColor"))
                style1.solidColor = SVG.CurrentColor.getInstance();
            else
                style1.solidColor = parseColour(s1);
            style1.specifiedFlags = 0x80000000L | style1.specifiedFlags;
            break;

        case 82: // 'R'
            style1.solidOpacity = Float.valueOf(parseOpacity(s1));
            style1.specifiedFlags = 0x100000000L | style1.specifiedFlags;
            break;

        case 83: // 'S'
            if(s1.equals("currentColor"))
                style1.viewportFill = SVG.CurrentColor.getInstance();
            else
                style1.viewportFill = parseColour(s1);
            style1.specifiedFlags = 0x200000000L | style1.specifiedFlags;
            break;

        case 84: // 'T'
            style1.viewportFillOpacity = Float.valueOf(parseOpacity(s1));
            style1.specifiedFlags = 0x400000000L | style1.specifiedFlags;
            break;

        case 85: // 'U'
            style1.vectorEffect = parseVectorEffect(s1);
            style1.specifiedFlags = 0x800000000L | style1.specifiedFlags;
            break;
        }
        if(true) goto _L2; else goto _L3
_L3:
    }

    private void radialGradient(Attributes attributes)
        throws SAXException
    {
        debug("<radialGradient>", new Object[0]);
        if(currentElement == null)
        {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else
        {
            SVG.SvgRadialGradient svgradialgradient = new SVG.SvgRadialGradient();
            svgradialgradient.document = svgDocument;
            svgradialgradient.parent = currentElement;
            parseAttributesCore(svgradialgradient, attributes);
            parseAttributesStyle(svgradialgradient, attributes);
            parseAttributesGradient(svgradialgradient, attributes);
            parseAttributesRadialGradient(svgradialgradient, attributes);
            currentElement.addChild(svgradialgradient);
            currentElement = svgradialgradient;
            return;
        }
    }

    private void rect(Attributes attributes)
        throws SAXException
    {
        debug("<rect>", new Object[0]);
        if(currentElement == null)
        {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else
        {
            SVG.Rect rect1 = new SVG.Rect();
            rect1.document = svgDocument;
            rect1.parent = currentElement;
            parseAttributesCore(rect1, attributes);
            parseAttributesStyle(rect1, attributes);
            parseAttributesTransform(rect1, attributes);
            parseAttributesConditional(rect1, attributes);
            parseAttributesRect(rect1, attributes);
            currentElement.addChild(rect1);
            return;
        }
    }

    private void solidColor(Attributes attributes)
        throws SAXException
    {
        debug("<solidColor>", new Object[0]);
        if(currentElement == null)
        {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else
        {
            SVG.SolidColor solidcolor = new SVG.SolidColor();
            solidcolor.document = svgDocument;
            solidcolor.parent = currentElement;
            parseAttributesCore(solidcolor, attributes);
            parseAttributesStyle(solidcolor, attributes);
            currentElement.addChild(solidcolor);
            currentElement = solidcolor;
            return;
        }
    }

    private void stop(Attributes attributes)
        throws SAXException
    {
        debug("<stop>", new Object[0]);
        if(currentElement == null)
            throw new SAXException("Invalid document. Root element must be <svg>");
        if(!(currentElement instanceof SVG.GradientElement))
        {
            throw new SAXException("Invalid document. <stop> elements are only valid inside <linearGradiant> or <radialGradient> elements.");
        } else
        {
            SVG.Stop stop1 = new SVG.Stop();
            stop1.document = svgDocument;
            stop1.parent = currentElement;
            parseAttributesCore(stop1, attributes);
            parseAttributesStyle(stop1, attributes);
            parseAttributesStop(stop1, attributes);
            currentElement.addChild(stop1);
            currentElement = stop1;
            return;
        }
    }

    private void style(Attributes attributes)
        throws SAXException
    {
        boolean flag;
        String s;
        int i;
        debug("<style>", new Object[0]);
        if(currentElement == null)
            throw new SAXException("Invalid document. Root element must be <svg>");
        flag = true;
        s = "all";
        i = 0;
_L2:
        String s1;
        if(i >= attributes.getLength())
            break MISSING_BLOCK_LABEL_128;
        s1 = attributes.getValue(i).trim();
        switch(_cls1..SwitchMap.com.caverock.androidsvg.SVGParser.SVGAttr[SVGAttr.fromString(attributes.getLocalName(i)).ordinal()])
        {
        default:
            break;

        case 87: // 'W'
            break; /* Loop/switch isn't completed */

        case 88: // 'X'
            break;
        }
        break MISSING_BLOCK_LABEL_122;
_L3:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        flag = s1.equals("text/css");
          goto _L3
        s = s1;
          goto _L3
        if(flag && CSSParser.mediaMatches(s, CSSParser.MediaType.screen))
        {
            inStyleElement = true;
        } else
        {
            ignoring = true;
            ignoreDepth = 1;
        }
        return;
    }

    private void svg(Attributes attributes)
        throws SAXException
    {
        debug("<svg>", new Object[0]);
        SVG.Svg svg1 = new SVG.Svg();
        svg1.document = svgDocument;
        svg1.parent = currentElement;
        parseAttributesCore(svg1, attributes);
        parseAttributesStyle(svg1, attributes);
        parseAttributesConditional(svg1, attributes);
        parseAttributesViewBox(svg1, attributes);
        parseAttributesSVG(svg1, attributes);
        if(currentElement == null)
            svgDocument.setRootElement(svg1);
        else
            currentElement.addChild(svg1);
        currentElement = svg1;
    }

    private void symbol(Attributes attributes)
        throws SAXException
    {
        debug("<symbol>", new Object[0]);
        if(currentElement == null)
        {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else
        {
            SVG.Symbol symbol1 = new SVG.Symbol();
            symbol1.document = svgDocument;
            symbol1.parent = currentElement;
            parseAttributesCore(symbol1, attributes);
            parseAttributesStyle(symbol1, attributes);
            parseAttributesConditional(symbol1, attributes);
            parseAttributesViewBox(symbol1, attributes);
            currentElement.addChild(symbol1);
            currentElement = symbol1;
            return;
        }
    }

    private void text(Attributes attributes)
        throws SAXException
    {
        debug("<text>", new Object[0]);
        if(currentElement == null)
        {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else
        {
            SVG.Text text1 = new SVG.Text();
            text1.document = svgDocument;
            text1.parent = currentElement;
            parseAttributesCore(text1, attributes);
            parseAttributesStyle(text1, attributes);
            parseAttributesTransform(text1, attributes);
            parseAttributesConditional(text1, attributes);
            parseAttributesTextPosition(text1, attributes);
            currentElement.addChild(text1);
            currentElement = text1;
            return;
        }
    }

    private void textPath(Attributes attributes)
        throws SAXException
    {
        debug("<textPath>", new Object[0]);
        if(currentElement == null)
            throw new SAXException("Invalid document. Root element must be <svg>");
        SVG.TextPath textpath = new SVG.TextPath();
        textpath.document = svgDocument;
        textpath.parent = currentElement;
        parseAttributesCore(textpath, attributes);
        parseAttributesStyle(textpath, attributes);
        parseAttributesConditional(textpath, attributes);
        parseAttributesTextPath(textpath, attributes);
        currentElement.addChild(textpath);
        currentElement = textpath;
        if(textpath.parent instanceof SVG.TextRoot)
            textpath.setTextRoot((SVG.TextRoot)textpath.parent);
        else
            textpath.setTextRoot(((SVG.TextChild)textpath.parent).getTextRoot());
    }

    private void tref(Attributes attributes)
        throws SAXException
    {
        debug("<tref>", new Object[0]);
        if(currentElement == null)
            throw new SAXException("Invalid document. Root element must be <svg>");
        if(!(currentElement instanceof SVG.TextContainer))
            throw new SAXException("Invalid document. <tref> elements are only valid inside <text> or <tspan> elements.");
        SVG.TRef tref1 = new SVG.TRef();
        tref1.document = svgDocument;
        tref1.parent = currentElement;
        parseAttributesCore(tref1, attributes);
        parseAttributesStyle(tref1, attributes);
        parseAttributesConditional(tref1, attributes);
        parseAttributesTRef(tref1, attributes);
        currentElement.addChild(tref1);
        if(tref1.parent instanceof SVG.TextRoot)
            tref1.setTextRoot((SVG.TextRoot)tref1.parent);
        else
            tref1.setTextRoot(((SVG.TextChild)tref1.parent).getTextRoot());
    }

    private void tspan(Attributes attributes)
        throws SAXException
    {
        debug("<tspan>", new Object[0]);
        if(currentElement == null)
            throw new SAXException("Invalid document. Root element must be <svg>");
        if(!(currentElement instanceof SVG.TextContainer))
            throw new SAXException("Invalid document. <tspan> elements are only valid inside <text> or other <tspan> elements.");
        SVG.TSpan tspan1 = new SVG.TSpan();
        tspan1.document = svgDocument;
        tspan1.parent = currentElement;
        parseAttributesCore(tspan1, attributes);
        parseAttributesStyle(tspan1, attributes);
        parseAttributesConditional(tspan1, attributes);
        parseAttributesTextPosition(tspan1, attributes);
        currentElement.addChild(tspan1);
        currentElement = tspan1;
        if(tspan1.parent instanceof SVG.TextRoot)
            tspan1.setTextRoot((SVG.TextRoot)tspan1.parent);
        else
            tspan1.setTextRoot(((SVG.TextChild)tspan1.parent).getTextRoot());
    }

    private void use(Attributes attributes)
        throws SAXException
    {
        debug("<use>", new Object[0]);
        if(currentElement == null)
        {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else
        {
            SVG.Use use1 = new SVG.Use();
            use1.document = svgDocument;
            use1.parent = currentElement;
            parseAttributesCore(use1, attributes);
            parseAttributesStyle(use1, attributes);
            parseAttributesTransform(use1, attributes);
            parseAttributesConditional(use1, attributes);
            parseAttributesUse(use1, attributes);
            currentElement.addChild(use1);
            currentElement = use1;
            return;
        }
    }

    private void view(Attributes attributes)
        throws SAXException
    {
        debug("<view>", new Object[0]);
        if(currentElement == null)
        {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else
        {
            SVG.View view1 = new SVG.View();
            view1.document = svgDocument;
            view1.parent = currentElement;
            parseAttributesCore(view1, attributes);
            parseAttributesConditional(view1, attributes);
            parseAttributesViewBox(view1, attributes);
            currentElement.addChild(view1);
            currentElement = view1;
            return;
        }
    }

    private void zwitch(Attributes attributes)
        throws SAXException
    {
        debug("<switch>", new Object[0]);
        if(currentElement == null)
        {
            throw new SAXException("Invalid document. Root element must be <svg>");
        } else
        {
            SVG.Switch switch1 = new SVG.Switch();
            switch1.document = svgDocument;
            switch1.parent = currentElement;
            parseAttributesCore(switch1, attributes);
            parseAttributesStyle(switch1, attributes);
            parseAttributesTransform(switch1, attributes);
            parseAttributesConditional(switch1, attributes);
            currentElement.addChild(switch1);
            currentElement = switch1;
            return;
        }
    }

    public void characters(char ac[], int i, int j)
        throws SAXException
    {
        if(!ignoring) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(inMetadataElement)
        {
            if(metadataElementContents == null)
                metadataElementContents = new StringBuilder(j);
            metadataElementContents.append(ac, i, j);
        } else
        if(inStyleElement)
        {
            if(styleElementContents == null)
                styleElementContents = new StringBuilder(j);
            styleElementContents.append(ac, i, j);
        } else
        if(currentElement instanceof SVG.TextContainer)
        {
            SVG.SvgConditionalContainer svgconditionalcontainer = (SVG.SvgConditionalContainer)currentElement;
            int k = svgconditionalcontainer.children.size();
            SVG.SvgObject svgobject;
            if(k == 0)
                svgobject = null;
            else
                svgobject = (SVG.SvgObject)svgconditionalcontainer.children.get(k - 1);
            if(svgobject instanceof SVG.TextSequence)
            {
                StringBuilder stringbuilder = new StringBuilder();
                SVG.TextSequence textsequence = (SVG.TextSequence)svgobject;
                textsequence.text = stringbuilder.append(textsequence.text).append(new String(ac, i, j)).toString();
            } else
            {
                ((SVG.SvgConditionalContainer)currentElement).addChild(new SVG.TextSequence(new String(ac, i, j)));
            }
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void comment(char ac[], int i, int j)
        throws SAXException
    {
        if(!ignoring && inStyleElement)
        {
            if(styleElementContents == null)
                styleElementContents = new StringBuilder(j);
            styleElementContents.append(ac, i, j);
        }
    }

    public void endDocument()
        throws SAXException
    {
        super.endDocument();
    }

    public void endElement(String s, String s1, String s2)
        throws SAXException
    {
        super.endElement(s, s1, s2);
        if(!ignoring) goto _L2; else goto _L1
_L1:
        int i;
        i = -1 + ignoreDepth;
        ignoreDepth = i;
        if(i != 0) goto _L2; else goto _L3
_L3:
        ignoring = false;
_L10:
        return;
_L2:
        if(!"http://www.w3.org/2000/svg".equals(s) && !"".equals(s))
            continue; /* Loop/switch isn't completed */
        if(!s1.equals("title") && !s1.equals("desc")) goto _L5; else goto _L4
_L4:
        inMetadataElement = false;
        if(!metadataTag.equals("title")) goto _L7; else goto _L6
_L6:
        svgDocument.setTitle(metadataElementContents.toString());
_L8:
        metadataElementContents.setLength(0);
        continue; /* Loop/switch isn't completed */
_L7:
        if(metadataTag.equals("desc"))
            svgDocument.setDesc(metadataElementContents.toString());
        if(true) goto _L8; else goto _L5
_L5:
        if(s1.equals("style") && styleElementContents != null)
        {
            inStyleElement = false;
            parseCSSStyleSheet(styleElementContents.toString());
            styleElementContents.setLength(0);
        } else
        if(s1.equals("svg") || s1.equals("defs") || s1.equals("g") || s1.equals("use") || s1.equals("image") || s1.equals("text") || s1.equals("tspan") || s1.equals("switch") || s1.equals("symbol") || s1.equals("marker") || s1.equals("linearGradient") || s1.equals("radialGradient") || s1.equals("stop") || s1.equals("clipPath") || s1.equals("textPath") || s1.equals("pattern") || s1.equals("view") || s1.equals("mask") || s1.equals("solidColor"))
            currentElement = ((SVG.SvgObject)currentElement).parent;
        if(true) goto _L10; else goto _L9
_L9:
    }

    protected SVG parse(InputStream inputstream)
        throws SVGParseException
    {
        Object obj;
        Object obj1;
        SAXParserFactory saxparserfactory;
        Exception exception;
        IOException ioexception2;
        int i;
        if(!inputstream.markSupported())
            obj = new BufferedInputStream(inputstream);
        else
            obj = inputstream;
        ((InputStream) (obj)).mark(3);
        i = ((InputStream) (obj)).read() + (((InputStream) (obj)).read() << 8);
        ((InputStream) (obj)).reset();
        if(i != 35615) goto _L2; else goto _L1
_L1:
        obj1 = new GZIPInputStream(((InputStream) (obj)));
_L4:
        saxparserfactory = SAXParserFactory.newInstance();
        XMLReader xmlreader = saxparserfactory.newSAXParser().getXMLReader();
        xmlreader.setContentHandler(this);
        xmlreader.setProperty("http://xml.org/sax/properties/lexical-handler", this);
        xmlreader.parse(new InputSource(((InputStream) (obj1))));
        IOException ioexception;
        try
        {
            ((InputStream) (obj1)).close();
        }
        catch(IOException ioexception3)
        {
            Log.e("SVGParser", "Exception thrown closing input stream");
        }
        return svgDocument;
        ioexception;
        obj1 = obj;
        continue; /* Loop/switch isn't completed */
        ioexception2;
        throw new SVGParseException("File error", ioexception2);
        exception;
        SAXException saxexception;
        ParserConfigurationException parserconfigurationexception;
        try
        {
            ((InputStream) (obj1)).close();
        }
        catch(IOException ioexception1)
        {
            Log.e("SVGParser", "Exception thrown closing input stream");
        }
        throw exception;
        parserconfigurationexception;
        throw new SVGParseException("XML Parser problem", parserconfigurationexception);
        saxexception;
        throw new SVGParseException((new StringBuilder()).append("SVG parse error: ").append(saxexception.getMessage()).toString(), saxexception);
_L2:
        obj1 = obj;
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void setSupportedFormats(String as[])
    {
        supportedFormats = new HashSet(as.length);
        Collections.addAll(supportedFormats, as);
    }

    public void startDocument()
        throws SAXException
    {
        super.startDocument();
        svgDocument = new SVG();
    }

    public void startElement(String s, String s1, String s2, Attributes attributes)
        throws SAXException
    {
        super.startElement(s, s1, s2, attributes);
        if(!ignoring) goto _L2; else goto _L1
_L1:
        ignoreDepth = 1 + ignoreDepth;
_L4:
        return;
_L2:
        if("http://www.w3.org/2000/svg".equals(s) || "".equals(s))
            if(s1.equals("svg"))
                svg(attributes);
            else
            if(s1.equals("g"))
                g(attributes);
            else
            if(s1.equals("defs"))
                defs(attributes);
            else
            if(s1.equals("use"))
                use(attributes);
            else
            if(s1.equals("path"))
                path(attributes);
            else
            if(s1.equals("rect"))
                rect(attributes);
            else
            if(s1.equals("circle"))
                circle(attributes);
            else
            if(s1.equals("ellipse"))
                ellipse(attributes);
            else
            if(s1.equals("line"))
                line(attributes);
            else
            if(s1.equals("polyline"))
                polyline(attributes);
            else
            if(s1.equals("polygon"))
                polygon(attributes);
            else
            if(s1.equals("text"))
                text(attributes);
            else
            if(s1.equals("tspan"))
                tspan(attributes);
            else
            if(s1.equals("tref"))
                tref(attributes);
            else
            if(s1.equals("switch"))
                zwitch(attributes);
            else
            if(s1.equals("symbol"))
                symbol(attributes);
            else
            if(s1.equals("marker"))
                marker(attributes);
            else
            if(s1.equals("linearGradient"))
                linearGradient(attributes);
            else
            if(s1.equals("radialGradient"))
                radialGradient(attributes);
            else
            if(s1.equals("stop"))
                stop(attributes);
            else
            if(s1.equals("a"))
                g(attributes);
            else
            if(s1.equals("title") || s1.equals("desc"))
            {
                inMetadataElement = true;
                metadataTag = s1;
            } else
            if(s1.equals("clipPath"))
                clipPath(attributes);
            else
            if(s1.equals("textPath"))
                textPath(attributes);
            else
            if(s1.equals("pattern"))
                pattern(attributes);
            else
            if(s1.equals("image"))
                image(attributes);
            else
            if(s1.equals("view"))
                view(attributes);
            else
            if(s1.equals("mask"))
                mask(attributes);
            else
            if(s1.equals("style"))
                style(attributes);
            else
            if(s1.equals("solidColor"))
            {
                solidColor(attributes);
            } else
            {
                ignoring = true;
                ignoreDepth = 1;
            }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final String CURRENTCOLOR = "currentColor";
    private static final String FEATURE_STRING_PREFIX = "http://www.w3.org/TR/SVG11/feature#";
    private static final String NONE = "none";
    private static final String SVG_NAMESPACE = "http://www.w3.org/2000/svg";
    private static final String TAG = "SVGParser";
    private static final String TAG_A = "a";
    private static final String TAG_CIRCLE = "circle";
    private static final String TAG_CLIPPATH = "clipPath";
    private static final String TAG_DEFS = "defs";
    private static final String TAG_DESC = "desc";
    private static final String TAG_ELLIPSE = "ellipse";
    private static final String TAG_G = "g";
    private static final String TAG_IMAGE = "image";
    private static final String TAG_LINE = "line";
    private static final String TAG_LINEARGRADIENT = "linearGradient";
    private static final String TAG_MARKER = "marker";
    private static final String TAG_MASK = "mask";
    private static final String TAG_PATH = "path";
    private static final String TAG_PATTERN = "pattern";
    private static final String TAG_POLYGON = "polygon";
    private static final String TAG_POLYLINE = "polyline";
    private static final String TAG_RADIALGRADIENT = "radialGradient";
    private static final String TAG_RECT = "rect";
    private static final String TAG_SOLIDCOLOR = "solidColor";
    private static final String TAG_STOP = "stop";
    private static final String TAG_STYLE = "style";
    private static final String TAG_SVG = "svg";
    private static final String TAG_SWITCH = "switch";
    private static final String TAG_SYMBOL = "symbol";
    private static final String TAG_TEXT = "text";
    private static final String TAG_TEXTPATH = "textPath";
    private static final String TAG_TITLE = "title";
    private static final String TAG_TREF = "tref";
    private static final String TAG_TSPAN = "tspan";
    private static final String TAG_USE = "use";
    private static final String TAG_VIEW = "view";
    private static final String VALID_DISPLAY_VALUES = "|inline|block|list-item|run-in|compact|marker|table|inline-table|table-row-group|table-header-group|table-footer-group|table-row|table-column-group|table-column|table-cell|table-caption|none|";
    private static final String VALID_VISIBILITY_VALUES = "|visible|hidden|collapse|";
    private static final String XLINK_NAMESPACE = "http://www.w3.org/1999/xlink";
    private static HashMap aspectRatioKeywords;
    private static HashMap colourKeywords;
    private static HashMap fontSizeKeywords;
    private static HashMap fontStyleKeywords;
    private static HashMap fontWeightKeywords;
    protected static HashSet supportedFeatures;
    private SVG.SvgContainer currentElement;
    private int ignoreDepth;
    private boolean ignoring;
    private boolean inMetadataElement;
    private boolean inStyleElement;
    private StringBuilder metadataElementContents;
    private String metadataTag;
    private StringBuilder styleElementContents;
    private HashSet supportedFormats;
    private SVG svgDocument;

    static 
    {
        colourKeywords = new HashMap();
        fontSizeKeywords = new HashMap(9);
        fontWeightKeywords = new HashMap(13);
        fontStyleKeywords = new HashMap(3);
        aspectRatioKeywords = new HashMap();
        supportedFeatures = new HashSet();
        colourKeywords.put("aliceblue", Integer.valueOf(0xf0f8ff));
        colourKeywords.put("antiquewhite", Integer.valueOf(0xfaebd7));
        colourKeywords.put("aqua", Integer.valueOf(65535));
        colourKeywords.put("aquamarine", Integer.valueOf(0x7fffd4));
        colourKeywords.put("azure", Integer.valueOf(0xf0ffff));
        colourKeywords.put("beige", Integer.valueOf(0xf5f5dc));
        colourKeywords.put("bisque", Integer.valueOf(0xffe4c4));
        colourKeywords.put("black", Integer.valueOf(0));
        colourKeywords.put("blanchedalmond", Integer.valueOf(0xffebcd));
        colourKeywords.put("blue", Integer.valueOf(255));
        colourKeywords.put("blueviolet", Integer.valueOf(0x8a2be2));
        colourKeywords.put("brown", Integer.valueOf(0xa52a2a));
        colourKeywords.put("burlywood", Integer.valueOf(0xdeb887));
        colourKeywords.put("cadetblue", Integer.valueOf(0x5f9ea0));
        colourKeywords.put("chartreuse", Integer.valueOf(0x7fff00));
        colourKeywords.put("chocolate", Integer.valueOf(0xd2691e));
        colourKeywords.put("coral", Integer.valueOf(0xff7f50));
        colourKeywords.put("cornflowerblue", Integer.valueOf(0x6495ed));
        colourKeywords.put("cornsilk", Integer.valueOf(0xfff8dc));
        colourKeywords.put("crimson", Integer.valueOf(0xdc143c));
        colourKeywords.put("cyan", Integer.valueOf(65535));
        colourKeywords.put("darkblue", Integer.valueOf(139));
        colourKeywords.put("darkcyan", Integer.valueOf(35723));
        colourKeywords.put("darkgoldenrod", Integer.valueOf(0xb8860b));
        colourKeywords.put("darkgray", Integer.valueOf(0xa9a9a9));
        colourKeywords.put("darkgreen", Integer.valueOf(25600));
        colourKeywords.put("darkgrey", Integer.valueOf(0xa9a9a9));
        colourKeywords.put("darkkhaki", Integer.valueOf(0xbdb76b));
        colourKeywords.put("darkmagenta", Integer.valueOf(0x8b008b));
        colourKeywords.put("darkolivegreen", Integer.valueOf(0x556b2f));
        colourKeywords.put("darkorange", Integer.valueOf(0xff8c00));
        colourKeywords.put("darkorchid", Integer.valueOf(0x9932cc));
        colourKeywords.put("darkred", Integer.valueOf(0x8b0000));
        colourKeywords.put("darksalmon", Integer.valueOf(0xe9967a));
        colourKeywords.put("darkseagreen", Integer.valueOf(0x8fbc8f));
        colourKeywords.put("darkslateblue", Integer.valueOf(0x483d8b));
        colourKeywords.put("darkslategray", Integer.valueOf(0x2f4f4f));
        colourKeywords.put("darkslategrey", Integer.valueOf(0x2f4f4f));
        colourKeywords.put("darkturquoise", Integer.valueOf(52945));
        colourKeywords.put("darkviolet", Integer.valueOf(0x9400d3));
        colourKeywords.put("deeppink", Integer.valueOf(0xff1493));
        colourKeywords.put("deepskyblue", Integer.valueOf(49151));
        colourKeywords.put("dimgray", Integer.valueOf(0x696969));
        colourKeywords.put("dimgrey", Integer.valueOf(0x696969));
        colourKeywords.put("dodgerblue", Integer.valueOf(0x1e90ff));
        colourKeywords.put("firebrick", Integer.valueOf(0xb22222));
        colourKeywords.put("floralwhite", Integer.valueOf(0xfffaf0));
        colourKeywords.put("forestgreen", Integer.valueOf(0x228b22));
        colourKeywords.put("fuchsia", Integer.valueOf(0xff00ff));
        colourKeywords.put("gainsboro", Integer.valueOf(0xdcdcdc));
        colourKeywords.put("ghostwhite", Integer.valueOf(0xf8f8ff));
        colourKeywords.put("gold", Integer.valueOf(0xffd700));
        colourKeywords.put("goldenrod", Integer.valueOf(0xdaa520));
        colourKeywords.put("gray", Integer.valueOf(0x808080));
        colourKeywords.put("green", Integer.valueOf(32768));
        colourKeywords.put("greenyellow", Integer.valueOf(0xadff2f));
        colourKeywords.put("grey", Integer.valueOf(0x808080));
        colourKeywords.put("honeydew", Integer.valueOf(0xf0fff0));
        colourKeywords.put("hotpink", Integer.valueOf(0xff69b4));
        colourKeywords.put("indianred", Integer.valueOf(0xcd5c5c));
        colourKeywords.put("indigo", Integer.valueOf(0x4b0082));
        colourKeywords.put("ivory", Integer.valueOf(0xfffff0));
        colourKeywords.put("khaki", Integer.valueOf(0xf0e68c));
        colourKeywords.put("lavender", Integer.valueOf(0xe6e6fa));
        colourKeywords.put("lavenderblush", Integer.valueOf(0xfff0f5));
        colourKeywords.put("lawngreen", Integer.valueOf(0x7cfc00));
        colourKeywords.put("lemonchiffon", Integer.valueOf(0xfffacd));
        colourKeywords.put("lightblue", Integer.valueOf(0xadd8e6));
        colourKeywords.put("lightcoral", Integer.valueOf(0xf08080));
        colourKeywords.put("lightcyan", Integer.valueOf(0xe0ffff));
        colourKeywords.put("lightgoldenrodyellow", Integer.valueOf(0xfafad2));
        colourKeywords.put("lightgray", Integer.valueOf(0xd3d3d3));
        colourKeywords.put("lightgreen", Integer.valueOf(0x90ee90));
        colourKeywords.put("lightgrey", Integer.valueOf(0xd3d3d3));
        colourKeywords.put("lightpink", Integer.valueOf(0xffb6c1));
        colourKeywords.put("lightsalmon", Integer.valueOf(0xffa07a));
        colourKeywords.put("lightseagreen", Integer.valueOf(0x20b2aa));
        colourKeywords.put("lightskyblue", Integer.valueOf(0x87cefa));
        colourKeywords.put("lightslategray", Integer.valueOf(0x778899));
        colourKeywords.put("lightslategrey", Integer.valueOf(0x778899));
        colourKeywords.put("lightsteelblue", Integer.valueOf(0xb0c4de));
        colourKeywords.put("lightyellow", Integer.valueOf(0xffffe0));
        colourKeywords.put("lime", Integer.valueOf(65280));
        colourKeywords.put("limegreen", Integer.valueOf(0x32cd32));
        colourKeywords.put("linen", Integer.valueOf(0xfaf0e6));
        colourKeywords.put("magenta", Integer.valueOf(0xff00ff));
        colourKeywords.put("maroon", Integer.valueOf(0x800000));
        colourKeywords.put("mediumaquamarine", Integer.valueOf(0x66cdaa));
        colourKeywords.put("mediumblue", Integer.valueOf(205));
        colourKeywords.put("mediumorchid", Integer.valueOf(0xba55d3));
        colourKeywords.put("mediumpurple", Integer.valueOf(0x9370db));
        colourKeywords.put("mediumseagreen", Integer.valueOf(0x3cb371));
        colourKeywords.put("mediumslateblue", Integer.valueOf(0x7b68ee));
        colourKeywords.put("mediumspringgreen", Integer.valueOf(64154));
        colourKeywords.put("mediumturquoise", Integer.valueOf(0x48d1cc));
        colourKeywords.put("mediumvioletred", Integer.valueOf(0xc71585));
        colourKeywords.put("midnightblue", Integer.valueOf(0x191970));
        colourKeywords.put("mintcream", Integer.valueOf(0xf5fffa));
        colourKeywords.put("mistyrose", Integer.valueOf(0xffe4e1));
        colourKeywords.put("moccasin", Integer.valueOf(0xffe4b5));
        colourKeywords.put("navajowhite", Integer.valueOf(0xffdead));
        colourKeywords.put("navy", Integer.valueOf(128));
        colourKeywords.put("oldlace", Integer.valueOf(0xfdf5e6));
        colourKeywords.put("olive", Integer.valueOf(0x808000));
        colourKeywords.put("olivedrab", Integer.valueOf(0x6b8e23));
        colourKeywords.put("orange", Integer.valueOf(0xffa500));
        colourKeywords.put("orangered", Integer.valueOf(0xff4500));
        colourKeywords.put("orchid", Integer.valueOf(0xda70d6));
        colourKeywords.put("palegoldenrod", Integer.valueOf(0xeee8aa));
        colourKeywords.put("palegreen", Integer.valueOf(0x98fb98));
        colourKeywords.put("paleturquoise", Integer.valueOf(0xafeeee));
        colourKeywords.put("palevioletred", Integer.valueOf(0xdb7093));
        colourKeywords.put("papayawhip", Integer.valueOf(0xffefd5));
        colourKeywords.put("peachpuff", Integer.valueOf(0xffdab9));
        colourKeywords.put("peru", Integer.valueOf(0xcd853f));
        colourKeywords.put("pink", Integer.valueOf(0xffc0cb));
        colourKeywords.put("plum", Integer.valueOf(0xdda0dd));
        colourKeywords.put("powderblue", Integer.valueOf(0xb0e0e6));
        colourKeywords.put("purple", Integer.valueOf(0x800080));
        colourKeywords.put("red", Integer.valueOf(0xff0000));
        colourKeywords.put("rosybrown", Integer.valueOf(0xbc8f8f));
        colourKeywords.put("royalblue", Integer.valueOf(0x4169e1));
        colourKeywords.put("saddlebrown", Integer.valueOf(0x8b4513));
        colourKeywords.put("salmon", Integer.valueOf(0xfa8072));
        colourKeywords.put("sandybrown", Integer.valueOf(0xf4a460));
        colourKeywords.put("seagreen", Integer.valueOf(0x2e8b57));
        colourKeywords.put("seashell", Integer.valueOf(0xfff5ee));
        colourKeywords.put("sienna", Integer.valueOf(0xa0522d));
        colourKeywords.put("silver", Integer.valueOf(0xc0c0c0));
        colourKeywords.put("skyblue", Integer.valueOf(0x87ceeb));
        colourKeywords.put("slateblue", Integer.valueOf(0x6a5acd));
        colourKeywords.put("slategray", Integer.valueOf(0x708090));
        colourKeywords.put("slategrey", Integer.valueOf(0x708090));
        colourKeywords.put("snow", Integer.valueOf(0xfffafa));
        colourKeywords.put("springgreen", Integer.valueOf(65407));
        colourKeywords.put("steelblue", Integer.valueOf(0x4682b4));
        colourKeywords.put("tan", Integer.valueOf(0xd2b48c));
        colourKeywords.put("teal", Integer.valueOf(32896));
        colourKeywords.put("thistle", Integer.valueOf(0xd8bfd8));
        colourKeywords.put("tomato", Integer.valueOf(0xff6347));
        colourKeywords.put("turquoise", Integer.valueOf(0x40e0d0));
        colourKeywords.put("violet", Integer.valueOf(0xee82ee));
        colourKeywords.put("wheat", Integer.valueOf(0xf5deb3));
        colourKeywords.put("white", Integer.valueOf(0xffffff));
        colourKeywords.put("whitesmoke", Integer.valueOf(0xf5f5f5));
        colourKeywords.put("yellow", Integer.valueOf(0xffff00));
        colourKeywords.put("yellowgreen", Integer.valueOf(0x9acd32));
        fontSizeKeywords.put("xx-small", new SVG.Length(0.694F, SVG.Unit.pt));
        fontSizeKeywords.put("x-small", new SVG.Length(0.833F, SVG.Unit.pt));
        fontSizeKeywords.put("small", new SVG.Length(10F, SVG.Unit.pt));
        fontSizeKeywords.put("medium", new SVG.Length(12F, SVG.Unit.pt));
        fontSizeKeywords.put("large", new SVG.Length(14.4F, SVG.Unit.pt));
        fontSizeKeywords.put("x-large", new SVG.Length(17.3F, SVG.Unit.pt));
        fontSizeKeywords.put("xx-large", new SVG.Length(20.7F, SVG.Unit.pt));
        fontSizeKeywords.put("smaller", new SVG.Length(83.33F, SVG.Unit.percent));
        fontSizeKeywords.put("larger", new SVG.Length(120F, SVG.Unit.percent));
        fontWeightKeywords.put("normal", Integer.valueOf(400));
        fontWeightKeywords.put("bold", Integer.valueOf(700));
        fontWeightKeywords.put("bolder", Integer.valueOf(1));
        fontWeightKeywords.put("lighter", Integer.valueOf(-1));
        fontWeightKeywords.put("100", Integer.valueOf(100));
        fontWeightKeywords.put("200", Integer.valueOf(200));
        fontWeightKeywords.put("300", Integer.valueOf(300));
        fontWeightKeywords.put("400", Integer.valueOf(400));
        fontWeightKeywords.put("500", Integer.valueOf(500));
        fontWeightKeywords.put("600", Integer.valueOf(600));
        fontWeightKeywords.put("700", Integer.valueOf(700));
        fontWeightKeywords.put("800", Integer.valueOf(800));
        fontWeightKeywords.put("900", Integer.valueOf(900));
        fontStyleKeywords.put("normal", SVG.Style.FontStyle.Normal);
        fontStyleKeywords.put("italic", SVG.Style.FontStyle.Italic);
        fontStyleKeywords.put("oblique", SVG.Style.FontStyle.Oblique);
        aspectRatioKeywords.put("none", PreserveAspectRatio.Alignment.None);
        aspectRatioKeywords.put("xMinYMin", PreserveAspectRatio.Alignment.XMinYMin);
        aspectRatioKeywords.put("xMidYMin", PreserveAspectRatio.Alignment.XMidYMin);
        aspectRatioKeywords.put("xMaxYMin", PreserveAspectRatio.Alignment.XMaxYMin);
        aspectRatioKeywords.put("xMinYMid", PreserveAspectRatio.Alignment.XMinYMid);
        aspectRatioKeywords.put("xMidYMid", PreserveAspectRatio.Alignment.XMidYMid);
        aspectRatioKeywords.put("xMaxYMid", PreserveAspectRatio.Alignment.XMaxYMid);
        aspectRatioKeywords.put("xMinYMax", PreserveAspectRatio.Alignment.XMinYMax);
        aspectRatioKeywords.put("xMidYMax", PreserveAspectRatio.Alignment.XMidYMax);
        aspectRatioKeywords.put("xMaxYMax", PreserveAspectRatio.Alignment.XMaxYMax);
        supportedFeatures.add("Structure");
        supportedFeatures.add("BasicStructure");
        supportedFeatures.add("ConditionalProcessing");
        supportedFeatures.add("Image");
        supportedFeatures.add("Style");
        supportedFeatures.add("ViewportAttribute");
        supportedFeatures.add("Shape");
        supportedFeatures.add("BasicText");
        supportedFeatures.add("PaintAttribute");
        supportedFeatures.add("BasicPaintAttribute");
        supportedFeatures.add("OpacityAttribute");
        supportedFeatures.add("BasicGraphicsAttribute");
        supportedFeatures.add("Marker");
        supportedFeatures.add("Gradient");
        supportedFeatures.add("Pattern");
        supportedFeatures.add("Clip");
        supportedFeatures.add("BasicClip");
        supportedFeatures.add("Mask");
        supportedFeatures.add("View");
    }
}
