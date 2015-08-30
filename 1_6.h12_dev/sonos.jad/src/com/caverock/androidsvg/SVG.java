// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.caverock.androidsvg;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.*;
import android.util.Log;
import java.io.*;
import java.util.*;
import org.xml.sax.SAXException;

// Referenced classes of package com.caverock.androidsvg:
//            SVGParseException, SVGParser, SVGAndroidRenderer, SVGExternalFileResolver, 
//            PreserveAspectRatio

public class SVG
{
    protected static class PathDefinition
        implements PathInterface
    {

        public void arcTo(float f, float f1, float f2, boolean flag, boolean flag1, float f3, float f4)
        {
            boolean flag2 = false;
            byte byte0;
            int i;
            int j;
            if(flag)
                byte0 = 2;
            else
                byte0 = 0;
            i = byte0 | 4;
            if(flag1)
                flag2 = true;
            j = i | flag2;
            commands.add(Byte.valueOf((byte)j));
            coords.add(Float.valueOf(f));
            coords.add(Float.valueOf(f1));
            coords.add(Float.valueOf(f2));
            coords.add(Float.valueOf(f3));
            coords.add(Float.valueOf(f4));
        }

        public void close()
        {
            commands.add(Byte.valueOf((byte)8));
        }

        public void cubicTo(float f, float f1, float f2, float f3, float f4, float f5)
        {
            commands.add(Byte.valueOf((byte)2));
            coords.add(Float.valueOf(f));
            coords.add(Float.valueOf(f1));
            coords.add(Float.valueOf(f2));
            coords.add(Float.valueOf(f3));
            coords.add(Float.valueOf(f4));
            coords.add(Float.valueOf(f5));
        }

        public void enumeratePath(PathInterface pathinterface)
        {
            Iterator iterator = coords.iterator();
            Iterator iterator1 = commands.iterator();
            do
            {
                if(!iterator1.hasNext())
                    break;
                byte byte0 = ((Byte)iterator1.next()).byteValue();
                switch(byte0)
                {
                case 4: // '\004'
                case 5: // '\005'
                case 6: // '\006'
                case 7: // '\007'
                default:
                    boolean flag;
                    boolean flag1;
                    if((byte0 & 2) != 0)
                        flag = true;
                    else
                        flag = false;
                    if((byte0 & 1) != 0)
                        flag1 = true;
                    else
                        flag1 = false;
                    pathinterface.arcTo(((Float)iterator.next()).floatValue(), ((Float)iterator.next()).floatValue(), ((Float)iterator.next()).floatValue(), flag, flag1, ((Float)iterator.next()).floatValue(), ((Float)iterator.next()).floatValue());
                    break;

                case 0: // '\0'
                    pathinterface.moveTo(((Float)iterator.next()).floatValue(), ((Float)iterator.next()).floatValue());
                    break;

                case 1: // '\001'
                    pathinterface.lineTo(((Float)iterator.next()).floatValue(), ((Float)iterator.next()).floatValue());
                    break;

                case 2: // '\002'
                    pathinterface.cubicTo(((Float)iterator.next()).floatValue(), ((Float)iterator.next()).floatValue(), ((Float)iterator.next()).floatValue(), ((Float)iterator.next()).floatValue(), ((Float)iterator.next()).floatValue(), ((Float)iterator.next()).floatValue());
                    break;

                case 3: // '\003'
                    pathinterface.quadTo(((Float)iterator.next()).floatValue(), ((Float)iterator.next()).floatValue(), ((Float)iterator.next()).floatValue(), ((Float)iterator.next()).floatValue());
                    break;

                case 8: // '\b'
                    pathinterface.close();
                    break;
                }
            } while(true);
        }

        public boolean isEmpty()
        {
            return commands.isEmpty();
        }

        public void lineTo(float f, float f1)
        {
            commands.add(Byte.valueOf((byte)1));
            coords.add(Float.valueOf(f));
            coords.add(Float.valueOf(f1));
        }

        public void moveTo(float f, float f1)
        {
            commands.add(Byte.valueOf((byte)0));
            coords.add(Float.valueOf(f));
            coords.add(Float.valueOf(f1));
        }

        public void quadTo(float f, float f1, float f2, float f3)
        {
            commands.add(Byte.valueOf((byte)3));
            coords.add(Float.valueOf(f));
            coords.add(Float.valueOf(f1));
            coords.add(Float.valueOf(f2));
            coords.add(Float.valueOf(f3));
        }

        private static final byte ARCTO = 4;
        private static final byte CLOSE = 8;
        private static final byte CUBICTO = 2;
        private static final byte LINETO = 1;
        private static final byte MOVETO = 0;
        private static final byte QUADTO = 3;
        private List commands;
        private List coords;

        public PathDefinition()
        {
            commands = null;
            coords = null;
            commands = new ArrayList();
            coords = new ArrayList();
        }
    }

    protected static interface PathInterface
    {

        public abstract void arcTo(float f, float f1, float f2, boolean flag, boolean flag1, float f3, float f4);

        public abstract void close();

        public abstract void cubicTo(float f, float f1, float f2, float f3, float f4, float f5);

        public abstract void lineTo(float f, float f1);

        public abstract void moveTo(float f, float f1);

        public abstract void quadTo(float f, float f1, float f2, float f3);
    }

    protected static class SolidColor extends SvgElementBase
        implements SvgContainer
    {

        public void addChild(SvgObject svgobject)
            throws SAXException
        {
        }

        public List getChildren()
        {
            return SVG.EMPTY_CHILD_LIST;
        }

        public Length solidColor;
        public Length solidOpacity;

        protected SolidColor()
        {
        }
    }

    protected static class Mask extends SvgConditionalContainer
        implements NotDirectlyRendered
    {

        public Length height;
        public Boolean maskContentUnitsAreUser;
        public Boolean maskUnitsAreUser;
        public Length width;
        public Length x;
        public Length y;

        protected Mask()
        {
        }
    }

    protected static class View extends SvgViewBoxContainer
        implements NotDirectlyRendered
    {

        protected View()
        {
        }
    }

    protected static class Image extends SvgPreserveAspectRatioContainer
        implements HasTransform
    {

        public void setTransform(Matrix matrix)
        {
            transform = matrix;
        }

        public Length height;
        public String href;
        public Matrix transform;
        public Length width;
        public Length x;
        public Length y;

        protected Image()
        {
        }
    }

    protected static class Pattern extends SvgViewBoxContainer
        implements NotDirectlyRendered
    {

        public Length height;
        public String href;
        public Boolean patternContentUnitsAreUser;
        public Matrix patternTransform;
        public Boolean patternUnitsAreUser;
        public Length width;
        public Length x;
        public Length y;

        protected Pattern()
        {
        }
    }

    protected static class ClipPath extends Group
        implements NotDirectlyRendered
    {

        public Boolean clipPathUnitsAreUser;

        protected ClipPath()
        {
        }
    }

    protected static class SvgRadialGradient extends GradientElement
    {

        public Length cx;
        public Length cy;
        public Length fx;
        public Length fy;
        public Length r;

        protected SvgRadialGradient()
        {
        }
    }

    protected static class SvgLinearGradient extends GradientElement
    {

        public Length x1;
        public Length x2;
        public Length y1;
        public Length y2;

        protected SvgLinearGradient()
        {
        }
    }

    protected static class Stop extends SvgElementBase
        implements SvgContainer
    {

        public void addChild(SvgObject svgobject)
            throws SAXException
        {
        }

        public List getChildren()
        {
            return SVG.EMPTY_CHILD_LIST;
        }

        public Float offset;

        protected Stop()
        {
        }
    }

    protected static class GradientElement extends SvgElementBase
        implements SvgContainer
    {

        public void addChild(SvgObject svgobject)
            throws SAXException
        {
            if(svgobject instanceof Stop)
            {
                children.add(svgobject);
                return;
            } else
            {
                throw new SAXException((new StringBuilder()).append("Gradient elements cannot contain ").append(svgobject).append(" elements.").toString());
            }
        }

        public List getChildren()
        {
            return children;
        }

        public List children;
        public Matrix gradientTransform;
        public Boolean gradientUnitsAreUser;
        public String href;
        public GradientSpread spreadMethod;

        protected GradientElement()
        {
            children = new ArrayList();
        }
    }

    protected static class Marker extends SvgViewBoxContainer
        implements NotDirectlyRendered
    {

        public Length markerHeight;
        public boolean markerUnitsAreUser;
        public Length markerWidth;
        public Float orient;
        public Length refX;
        public Length refY;

        protected Marker()
        {
        }
    }

    protected static class Symbol extends SvgViewBoxContainer
        implements NotDirectlyRendered
    {

        protected Symbol()
        {
        }
    }

    protected static class Switch extends Group
    {

        protected Switch()
        {
        }
    }

    protected static class TextPath extends TextContainer
        implements TextChild
    {

        public TextRoot getTextRoot()
        {
            return textRoot;
        }

        public void setTextRoot(TextRoot textroot)
        {
            textRoot = textroot;
        }

        public String href;
        public Length startOffset;
        private TextRoot textRoot;

        protected TextPath()
        {
        }
    }

    protected static class TRef extends TextContainer
        implements TextChild
    {

        public TextRoot getTextRoot()
        {
            return textRoot;
        }

        public void setTextRoot(TextRoot textroot)
        {
            textRoot = textroot;
        }

        public String href;
        private TextRoot textRoot;

        protected TRef()
        {
        }
    }

    protected static class TextSequence extends SvgObject
        implements TextChild
    {

        public TextRoot getTextRoot()
        {
            return textRoot;
        }

        public void setTextRoot(TextRoot textroot)
        {
            textRoot = textroot;
        }

        public String toString()
        {
            return (new StringBuilder()).append(getClass().getSimpleName()).append(" '").append(text).append("'").toString();
        }

        public String text;
        private TextRoot textRoot;

        public TextSequence(String s)
        {
            text = s;
        }
    }

    protected static class TSpan extends TextPositionedContainer
        implements TextChild
    {

        public TextRoot getTextRoot()
        {
            return textRoot;
        }

        public void setTextRoot(TextRoot textroot)
        {
            textRoot = textroot;
        }

        private TextRoot textRoot;

        protected TSpan()
        {
        }
    }

    protected static class Text extends TextPositionedContainer
        implements TextRoot, HasTransform
    {

        public void setTransform(Matrix matrix)
        {
            transform = matrix;
        }

        public Matrix transform;

        protected Text()
        {
        }
    }

    protected static class TextPositionedContainer extends TextContainer
    {

        public List dx;
        public List dy;
        public List x;
        public List y;

        protected TextPositionedContainer()
        {
        }
    }

    protected static class TextContainer extends SvgConditionalContainer
    {

        public void addChild(SvgObject svgobject)
            throws SAXException
        {
            if(svgobject instanceof TextChild)
            {
                children.add(svgobject);
                return;
            } else
            {
                throw new SAXException((new StringBuilder()).append("Text content elements cannot contain ").append(svgobject).append(" elements.").toString());
            }
        }

        protected TextContainer()
        {
        }
    }

    protected static interface TextChild
    {

        public abstract TextRoot getTextRoot();

        public abstract void setTextRoot(TextRoot textroot);
    }

    protected static interface TextRoot
    {
    }

    protected static class Polygon extends PolyLine
    {

        protected Polygon()
        {
        }
    }

    protected static class PolyLine extends GraphicsElement
    {

        public float points[];

        protected PolyLine()
        {
        }
    }

    protected static class Line extends GraphicsElement
    {

        public Length x1;
        public Length x2;
        public Length y1;
        public Length y2;

        protected Line()
        {
        }
    }

    protected static class Ellipse extends GraphicsElement
    {

        public Length cx;
        public Length cy;
        public Length rx;
        public Length ry;

        protected Ellipse()
        {
        }
    }

    protected static class Circle extends GraphicsElement
    {

        public Length cx;
        public Length cy;
        public Length r;

        protected Circle()
        {
        }
    }

    protected static class Rect extends GraphicsElement
    {

        public Length height;
        public Length rx;
        public Length ry;
        public Length width;
        public Length x;
        public Length y;

        protected Rect()
        {
        }
    }

    protected static class Path extends GraphicsElement
    {

        public PathDefinition d;
        public Float pathLength;

        protected Path()
        {
        }
    }

    protected static class Use extends Group
    {

        public Length height;
        public String href;
        public Length width;
        public Length x;
        public Length y;

        protected Use()
        {
        }
    }

    protected static abstract class GraphicsElement extends SvgConditionalElement
        implements HasTransform
    {

        public void setTransform(Matrix matrix)
        {
            transform = matrix;
        }

        public Matrix transform;

        protected GraphicsElement()
        {
        }
    }

    protected static class Defs extends Group
        implements NotDirectlyRendered
    {

        protected Defs()
        {
        }
    }

    protected static interface NotDirectlyRendered
    {
    }

    protected static class Group extends SvgConditionalContainer
        implements HasTransform
    {

        public void setTransform(Matrix matrix)
        {
            transform = matrix;
        }

        public Matrix transform;

        protected Group()
        {
        }
    }

    protected static class Svg extends SvgViewBoxContainer
    {

        public Length height;
        public String version;
        public Length width;
        public Length x;
        public Length y;

        protected Svg()
        {
        }
    }

    protected static class SvgViewBoxContainer extends SvgPreserveAspectRatioContainer
    {

        public Box viewBox;

        protected SvgViewBoxContainer()
        {
        }
    }

    protected static class SvgPreserveAspectRatioContainer extends SvgConditionalContainer
    {

        public PreserveAspectRatio preserveAspectRatio;

        protected SvgPreserveAspectRatioContainer()
        {
            preserveAspectRatio = null;
        }
    }

    protected static interface HasTransform
    {

        public abstract void setTransform(Matrix matrix);
    }

    protected static class SvgConditionalContainer extends SvgElement
        implements SvgContainer, SvgConditional
    {

        public void addChild(SvgObject svgobject)
            throws SAXException
        {
            children.add(svgobject);
        }

        public List getChildren()
        {
            return children;
        }

        public String getRequiredExtensions()
        {
            return requiredExtensions;
        }

        public Set getRequiredFeatures()
        {
            return requiredFeatures;
        }

        public Set getRequiredFonts()
        {
            return requiredFonts;
        }

        public Set getRequiredFormats()
        {
            return requiredFormats;
        }

        public Set getSystemLanguage()
        {
            return null;
        }

        public void setRequiredExtensions(String s)
        {
            requiredExtensions = s;
        }

        public void setRequiredFeatures(Set set)
        {
            requiredFeatures = set;
        }

        public void setRequiredFonts(Set set)
        {
            requiredFonts = set;
        }

        public void setRequiredFormats(Set set)
        {
            requiredFormats = set;
        }

        public void setSystemLanguage(Set set)
        {
            systemLanguage = set;
        }

        public List children;
        public String requiredExtensions;
        public Set requiredFeatures;
        public Set requiredFonts;
        public Set requiredFormats;
        public Set systemLanguage;

        protected SvgConditionalContainer()
        {
            children = new ArrayList();
            requiredFeatures = null;
            requiredExtensions = null;
            systemLanguage = null;
            requiredFormats = null;
            requiredFonts = null;
        }
    }

    protected static interface SvgContainer
    {

        public abstract void addChild(SvgObject svgobject)
            throws SAXException;

        public abstract List getChildren();
    }

    protected static class SvgConditionalElement extends SvgElement
        implements SvgConditional
    {

        public String getRequiredExtensions()
        {
            return requiredExtensions;
        }

        public Set getRequiredFeatures()
        {
            return requiredFeatures;
        }

        public Set getRequiredFonts()
        {
            return requiredFonts;
        }

        public Set getRequiredFormats()
        {
            return requiredFormats;
        }

        public Set getSystemLanguage()
        {
            return systemLanguage;
        }

        public void setRequiredExtensions(String s)
        {
            requiredExtensions = s;
        }

        public void setRequiredFeatures(Set set)
        {
            requiredFeatures = set;
        }

        public void setRequiredFonts(Set set)
        {
            requiredFonts = set;
        }

        public void setRequiredFormats(Set set)
        {
            requiredFormats = set;
        }

        public void setSystemLanguage(Set set)
        {
            systemLanguage = set;
        }

        public String requiredExtensions;
        public Set requiredFeatures;
        public Set requiredFonts;
        public Set requiredFormats;
        public Set systemLanguage;

        protected SvgConditionalElement()
        {
            requiredFeatures = null;
            requiredExtensions = null;
            systemLanguage = null;
            requiredFormats = null;
            requiredFonts = null;
        }
    }

    protected static interface SvgConditional
    {

        public abstract String getRequiredExtensions();

        public abstract Set getRequiredFeatures();

        public abstract Set getRequiredFonts();

        public abstract Set getRequiredFormats();

        public abstract Set getSystemLanguage();

        public abstract void setRequiredExtensions(String s);

        public abstract void setRequiredFeatures(Set set);

        public abstract void setRequiredFonts(Set set);

        public abstract void setRequiredFormats(Set set);

        public abstract void setSystemLanguage(Set set);
    }

    protected static class SvgElement extends SvgElementBase
    {

        public Box boundingBox;

        protected SvgElement()
        {
            boundingBox = null;
        }
    }

    protected static class SvgElementBase extends SvgObject
    {

        public Style baseStyle;
        public List classNames;
        public String id;
        public Boolean spacePreserve;
        public Style style;

        protected SvgElementBase()
        {
            id = null;
            spacePreserve = null;
            baseStyle = null;
            style = null;
            classNames = null;
        }
    }

    protected static class SvgObject
    {

        public String toString()
        {
            return getClass().getSimpleName();
        }

        public SVG document;
        public SvgContainer parent;

        protected SvgObject()
        {
        }
    }

    protected static class CSSClipRect
    {

        public Length bottom;
        public Length left;
        public Length right;
        public Length top;

        public CSSClipRect(Length length, Length length1, Length length2, Length length3)
        {
            top = length;
            right = length1;
            bottom = length2;
            left = length3;
        }
    }

    protected static class Length
        implements Cloneable
    {

        public float floatValue()
        {
            return value;
        }

        public float floatValue(float f)
        {
            class _cls1
            {

                static final int $SwitchMap$com$caverock$androidsvg$SVG$Unit[];

                static 
                {
                    $SwitchMap$com$caverock$androidsvg$SVG$Unit = new int[Unit.values().length];
                    NoSuchFieldError nosuchfielderror8;
                    try
                    {
                        $SwitchMap$com$caverock$androidsvg$SVG$Unit[Unit.px.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror) { }
                    try
                    {
                        $SwitchMap$com$caverock$androidsvg$SVG$Unit[Unit.em.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror1) { }
                    try
                    {
                        $SwitchMap$com$caverock$androidsvg$SVG$Unit[Unit.ex.ordinal()] = 3;
                    }
                    catch(NoSuchFieldError nosuchfielderror2) { }
                    try
                    {
                        $SwitchMap$com$caverock$androidsvg$SVG$Unit[Unit.in.ordinal()] = 4;
                    }
                    catch(NoSuchFieldError nosuchfielderror3) { }
                    try
                    {
                        $SwitchMap$com$caverock$androidsvg$SVG$Unit[Unit.cm.ordinal()] = 5;
                    }
                    catch(NoSuchFieldError nosuchfielderror4) { }
                    try
                    {
                        $SwitchMap$com$caverock$androidsvg$SVG$Unit[Unit.mm.ordinal()] = 6;
                    }
                    catch(NoSuchFieldError nosuchfielderror5) { }
                    try
                    {
                        $SwitchMap$com$caverock$androidsvg$SVG$Unit[Unit.pt.ordinal()] = 7;
                    }
                    catch(NoSuchFieldError nosuchfielderror6) { }
                    try
                    {
                        $SwitchMap$com$caverock$androidsvg$SVG$Unit[Unit.pc.ordinal()] = 8;
                    }
                    catch(NoSuchFieldError nosuchfielderror7) { }
                    $SwitchMap$com$caverock$androidsvg$SVG$Unit[Unit.percent.ordinal()] = 9;
_L2:
                    return;
                    nosuchfielderror8;
                    if(true) goto _L2; else goto _L1
_L1:
                }
            }

            _cls1..SwitchMap.com.caverock.androidsvg.SVG.Unit[unit.ordinal()];
            JVM INSTR tableswitch 1 8: default 56
        //                       1 63
        //                       2 56
        //                       3 56
        //                       4 71
        //                       5 81
        //                       6 94
        //                       7 107
        //                       8 120;
               goto _L1 _L2 _L1 _L1 _L3 _L4 _L5 _L6 _L7
_L1:
            float f1 = value;
_L9:
            return f1;
_L2:
            f1 = value;
            continue; /* Loop/switch isn't completed */
_L3:
            f1 = f * value;
            continue; /* Loop/switch isn't completed */
_L4:
            f1 = (f * value) / 2.54F;
            continue; /* Loop/switch isn't completed */
_L5:
            f1 = (f * value) / 25.4F;
            continue; /* Loop/switch isn't completed */
_L6:
            f1 = (f * value) / 72F;
            continue; /* Loop/switch isn't completed */
_L7:
            f1 = (f * value) / 6F;
            if(true) goto _L9; else goto _L8
_L8:
        }

        public float floatValue(SVGAndroidRenderer svgandroidrenderer)
        {
            float f;
            if(unit == Unit.percent)
            {
                Box box = svgandroidrenderer.getCurrentViewPortInUserUnits();
                if(box == null)
                {
                    f = value;
                } else
                {
                    float f1 = box.width;
                    float f2 = box.height;
                    if(f1 == f2)
                        f = (f1 * value) / 100F;
                    else
                        f = ((float)(Math.sqrt(f1 * f1 + f2 * f2) / 1.4142135623730949D) * value) / 100F;
                }
            } else
            {
                f = floatValueX(svgandroidrenderer);
            }
            return f;
        }

        public float floatValue(SVGAndroidRenderer svgandroidrenderer, float f)
        {
            float f1;
            if(unit == Unit.percent)
                f1 = (f * value) / 100F;
            else
                f1 = floatValueX(svgandroidrenderer);
            return f1;
        }

        public float floatValueX(SVGAndroidRenderer svgandroidrenderer)
        {
            _cls1..SwitchMap.com.caverock.androidsvg.SVG.Unit[unit.ordinal()];
            JVM INSTR tableswitch 1 9: default 60
        //                       1 67
        //                       2 75
        //                       3 88
        //                       4 101
        //                       5 114
        //                       6 130
        //                       7 146
        //                       8 162
        //                       9 178;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
            float f = value;
_L12:
            return f;
_L2:
            f = value;
            continue; /* Loop/switch isn't completed */
_L3:
            f = value * svgandroidrenderer.getCurrentFontSize();
            continue; /* Loop/switch isn't completed */
_L4:
            f = value * svgandroidrenderer.getCurrentFontXHeight();
            continue; /* Loop/switch isn't completed */
_L5:
            f = value * svgandroidrenderer.getDPI();
            continue; /* Loop/switch isn't completed */
_L6:
            f = (value * svgandroidrenderer.getDPI()) / 2.54F;
            continue; /* Loop/switch isn't completed */
_L7:
            f = (value * svgandroidrenderer.getDPI()) / 25.4F;
            continue; /* Loop/switch isn't completed */
_L8:
            f = (value * svgandroidrenderer.getDPI()) / 72F;
            continue; /* Loop/switch isn't completed */
_L9:
            f = (value * svgandroidrenderer.getDPI()) / 6F;
            continue; /* Loop/switch isn't completed */
_L10:
            Box box = svgandroidrenderer.getCurrentViewPortInUserUnits();
            if(box == null)
                f = value;
            else
                f = (value * box.width) / 100F;
            if(true) goto _L12; else goto _L11
_L11:
        }

        public float floatValueY(SVGAndroidRenderer svgandroidrenderer)
        {
            float f;
            if(unit == Unit.percent)
            {
                Box box = svgandroidrenderer.getCurrentViewPortInUserUnits();
                if(box == null)
                    f = value;
                else
                    f = (value * box.height) / 100F;
            } else
            {
                f = floatValueX(svgandroidrenderer);
            }
            return f;
        }

        public boolean isNegative()
        {
            boolean flag;
            if(value < 0.0F)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean isZero()
        {
            boolean flag;
            if(value == 0.0F)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public String toString()
        {
            return (new StringBuilder()).append(String.valueOf(value)).append(unit).toString();
        }

        Unit unit;
        float value;

        public Length(float f)
        {
            value = 0.0F;
            unit = Unit.px;
            value = f;
            unit = Unit.px;
        }

        public Length(float f, Unit unit1)
        {
            value = 0.0F;
            unit = Unit.px;
            value = f;
            unit = unit1;
        }
    }

    protected static class PaintReference extends SvgPaint
    {

        public String toString()
        {
            return (new StringBuilder()).append(href).append(" ").append(fallback).toString();
        }

        public SvgPaint fallback;
        public String href;

        public PaintReference(String s, SvgPaint svgpaint)
        {
            href = s;
            fallback = svgpaint;
        }
    }

    protected static class CurrentColor extends SvgPaint
    {

        public static CurrentColor getInstance()
        {
            return instance;
        }

        private static CurrentColor instance = new CurrentColor();


        private CurrentColor()
        {
        }
    }

    protected static class Colour extends SvgPaint
    {

        public String toString()
        {
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(colour);
            return String.format("#%06x", aobj);
        }

        public static final Colour BLACK = new Colour(0);
        public int colour;


        public Colour(int i)
        {
            colour = i;
        }
    }

    protected static abstract class SvgPaint
        implements Cloneable
    {

        protected SvgPaint()
        {
        }
    }

    protected static class Style
        implements Cloneable
    {
        public static final class VectorEffect extends Enum
        {

            public static VectorEffect valueOf(String s)
            {
                return (VectorEffect)Enum.valueOf(com/caverock/androidsvg/SVG$Style$VectorEffect, s);
            }

            public static VectorEffect[] values()
            {
                return (VectorEffect[])$VALUES.clone();
            }

            private static final VectorEffect $VALUES[];
            public static final VectorEffect NonScalingStroke;
            public static final VectorEffect None;

            static 
            {
                None = new VectorEffect("None", 0);
                NonScalingStroke = new VectorEffect("NonScalingStroke", 1);
                VectorEffect avectoreffect[] = new VectorEffect[2];
                avectoreffect[0] = None;
                avectoreffect[1] = NonScalingStroke;
                $VALUES = avectoreffect;
            }

            private VectorEffect(String s, int i)
            {
                super(s, i);
            }
        }

        public static final class TextDirection extends Enum
        {

            public static TextDirection valueOf(String s)
            {
                return (TextDirection)Enum.valueOf(com/caverock/androidsvg/SVG$Style$TextDirection, s);
            }

            public static TextDirection[] values()
            {
                return (TextDirection[])$VALUES.clone();
            }

            private static final TextDirection $VALUES[];
            public static final TextDirection LTR;
            public static final TextDirection RTL;

            static 
            {
                LTR = new TextDirection("LTR", 0);
                RTL = new TextDirection("RTL", 1);
                TextDirection atextdirection[] = new TextDirection[2];
                atextdirection[0] = LTR;
                atextdirection[1] = RTL;
                $VALUES = atextdirection;
            }

            private TextDirection(String s, int i)
            {
                super(s, i);
            }
        }

        public static final class TextDecoration extends Enum
        {

            public static TextDecoration valueOf(String s)
            {
                return (TextDecoration)Enum.valueOf(com/caverock/androidsvg/SVG$Style$TextDecoration, s);
            }

            public static TextDecoration[] values()
            {
                return (TextDecoration[])$VALUES.clone();
            }

            private static final TextDecoration $VALUES[];
            public static final TextDecoration Blink;
            public static final TextDecoration LineThrough;
            public static final TextDecoration None;
            public static final TextDecoration Overline;
            public static final TextDecoration Underline;

            static 
            {
                None = new TextDecoration("None", 0);
                Underline = new TextDecoration("Underline", 1);
                Overline = new TextDecoration("Overline", 2);
                LineThrough = new TextDecoration("LineThrough", 3);
                Blink = new TextDecoration("Blink", 4);
                TextDecoration atextdecoration[] = new TextDecoration[5];
                atextdecoration[0] = None;
                atextdecoration[1] = Underline;
                atextdecoration[2] = Overline;
                atextdecoration[3] = LineThrough;
                atextdecoration[4] = Blink;
                $VALUES = atextdecoration;
            }

            private TextDecoration(String s, int i)
            {
                super(s, i);
            }
        }

        public static final class TextAnchor extends Enum
        {

            public static TextAnchor valueOf(String s)
            {
                return (TextAnchor)Enum.valueOf(com/caverock/androidsvg/SVG$Style$TextAnchor, s);
            }

            public static TextAnchor[] values()
            {
                return (TextAnchor[])$VALUES.clone();
            }

            private static final TextAnchor $VALUES[];
            public static final TextAnchor End;
            public static final TextAnchor Middle;
            public static final TextAnchor Start;

            static 
            {
                Start = new TextAnchor("Start", 0);
                Middle = new TextAnchor("Middle", 1);
                End = new TextAnchor("End", 2);
                TextAnchor atextanchor[] = new TextAnchor[3];
                atextanchor[0] = Start;
                atextanchor[1] = Middle;
                atextanchor[2] = End;
                $VALUES = atextanchor;
            }

            private TextAnchor(String s, int i)
            {
                super(s, i);
            }
        }

        public static final class FontStyle extends Enum
        {

            public static FontStyle valueOf(String s)
            {
                return (FontStyle)Enum.valueOf(com/caverock/androidsvg/SVG$Style$FontStyle, s);
            }

            public static FontStyle[] values()
            {
                return (FontStyle[])$VALUES.clone();
            }

            private static final FontStyle $VALUES[];
            public static final FontStyle Italic;
            public static final FontStyle Normal;
            public static final FontStyle Oblique;

            static 
            {
                Normal = new FontStyle("Normal", 0);
                Italic = new FontStyle("Italic", 1);
                Oblique = new FontStyle("Oblique", 2);
                FontStyle afontstyle[] = new FontStyle[3];
                afontstyle[0] = Normal;
                afontstyle[1] = Italic;
                afontstyle[2] = Oblique;
                $VALUES = afontstyle;
            }

            private FontStyle(String s, int i)
            {
                super(s, i);
            }
        }

        public static final class LineJoin extends Enum
        {

            public static LineJoin valueOf(String s)
            {
                return (LineJoin)Enum.valueOf(com/caverock/androidsvg/SVG$Style$LineJoin, s);
            }

            public static LineJoin[] values()
            {
                return (LineJoin[])$VALUES.clone();
            }

            private static final LineJoin $VALUES[];
            public static final LineJoin Bevel;
            public static final LineJoin Miter;
            public static final LineJoin Round;

            static 
            {
                Miter = new LineJoin("Miter", 0);
                Round = new LineJoin("Round", 1);
                Bevel = new LineJoin("Bevel", 2);
                LineJoin alinejoin[] = new LineJoin[3];
                alinejoin[0] = Miter;
                alinejoin[1] = Round;
                alinejoin[2] = Bevel;
                $VALUES = alinejoin;
            }

            private LineJoin(String s, int i)
            {
                super(s, i);
            }
        }

        public static final class LineCaps extends Enum
        {

            public static LineCaps valueOf(String s)
            {
                return (LineCaps)Enum.valueOf(com/caverock/androidsvg/SVG$Style$LineCaps, s);
            }

            public static LineCaps[] values()
            {
                return (LineCaps[])$VALUES.clone();
            }

            private static final LineCaps $VALUES[];
            public static final LineCaps Butt;
            public static final LineCaps Round;
            public static final LineCaps Square;

            static 
            {
                Butt = new LineCaps("Butt", 0);
                Round = new LineCaps("Round", 1);
                Square = new LineCaps("Square", 2);
                LineCaps alinecaps[] = new LineCaps[3];
                alinecaps[0] = Butt;
                alinecaps[1] = Round;
                alinecaps[2] = Square;
                $VALUES = alinecaps;
            }

            private LineCaps(String s, int i)
            {
                super(s, i);
            }
        }

        public static final class FillRule extends Enum
        {

            public static FillRule valueOf(String s)
            {
                return (FillRule)Enum.valueOf(com/caverock/androidsvg/SVG$Style$FillRule, s);
            }

            public static FillRule[] values()
            {
                return (FillRule[])$VALUES.clone();
            }

            private static final FillRule $VALUES[];
            public static final FillRule EvenOdd;
            public static final FillRule NonZero;

            static 
            {
                NonZero = new FillRule("NonZero", 0);
                EvenOdd = new FillRule("EvenOdd", 1);
                FillRule afillrule[] = new FillRule[2];
                afillrule[0] = NonZero;
                afillrule[1] = EvenOdd;
                $VALUES = afillrule;
            }

            private FillRule(String s, int i)
            {
                super(s, i);
            }
        }


        public static Style getDefaultStyle()
        {
            Style style = new Style();
            style.specifiedFlags = -1L;
            style.fill = Colour.BLACK;
            style.fillRule = FillRule.NonZero;
            style.fillOpacity = Float.valueOf(1.0F);
            style.stroke = null;
            style.strokeOpacity = Float.valueOf(1.0F);
            style.strokeWidth = new Length(1.0F);
            style.strokeLineCap = LineCaps.Butt;
            style.strokeLineJoin = LineJoin.Miter;
            style.strokeMiterLimit = Float.valueOf(4F);
            style.strokeDashArray = null;
            style.strokeDashOffset = new Length(0.0F);
            style.opacity = Float.valueOf(1.0F);
            style.color = Colour.BLACK;
            style.fontFamily = null;
            style.fontSize = new Length(12F, Unit.pt);
            style.fontWeight = Integer.valueOf(400);
            style.fontStyle = FontStyle.Normal;
            style.textDecoration = TextDecoration.None;
            style.direction = TextDirection.LTR;
            style.textAnchor = TextAnchor.Start;
            style.overflow = Boolean.valueOf(true);
            style.clip = null;
            style.markerStart = null;
            style.markerMid = null;
            style.markerEnd = null;
            style.display = Boolean.TRUE;
            style.visibility = Boolean.TRUE;
            style.stopColor = Colour.BLACK;
            style.stopOpacity = Float.valueOf(1.0F);
            style.clipPath = null;
            style.clipRule = FillRule.NonZero;
            style.mask = null;
            style.solidColor = null;
            style.solidOpacity = Float.valueOf(1.0F);
            style.viewportFill = null;
            style.viewportFillOpacity = Float.valueOf(1.0F);
            style.vectorEffect = VectorEffect.None;
            return style;
        }

        protected Object clone()
        {
            Style style;
            try
            {
                style = (Style)super.clone();
                if(strokeDashArray != null)
                    style.strokeDashArray = (Length[])(Length[])strokeDashArray.clone();
            }
            catch(CloneNotSupportedException clonenotsupportedexception)
            {
                throw new InternalError(clonenotsupportedexception.toString());
            }
            return style;
        }

        public void resetNonInheritingProperties()
        {
            resetNonInheritingProperties(false);
        }

        public void resetNonInheritingProperties(boolean flag)
        {
            display = Boolean.TRUE;
            Boolean boolean1;
            if(flag)
                boolean1 = Boolean.TRUE;
            else
                boolean1 = Boolean.FALSE;
            overflow = boolean1;
            clip = null;
            clipPath = null;
            opacity = Float.valueOf(1.0F);
            stopColor = Colour.BLACK;
            stopOpacity = Float.valueOf(1.0F);
            mask = null;
            solidColor = null;
            solidOpacity = Float.valueOf(1.0F);
            viewportFill = null;
            viewportFillOpacity = Float.valueOf(1.0F);
            vectorEffect = VectorEffect.None;
        }

        public static final int FONT_WEIGHT_BOLD = 700;
        public static final int FONT_WEIGHT_BOLDER = 1;
        public static final int FONT_WEIGHT_LIGHTER = -1;
        public static final int FONT_WEIGHT_NORMAL = 400;
        public CSSClipRect clip;
        public String clipPath;
        public FillRule clipRule;
        public Colour color;
        public TextDirection direction;
        public Boolean display;
        public SvgPaint fill;
        public Float fillOpacity;
        public FillRule fillRule;
        public List fontFamily;
        public Length fontSize;
        public FontStyle fontStyle;
        public Integer fontWeight;
        public String markerEnd;
        public String markerMid;
        public String markerStart;
        public String mask;
        public Float opacity;
        public Boolean overflow;
        public SvgPaint solidColor;
        public Float solidOpacity;
        public long specifiedFlags;
        public SvgPaint stopColor;
        public Float stopOpacity;
        public SvgPaint stroke;
        public Length strokeDashArray[];
        public Length strokeDashOffset;
        public LineCaps strokeLineCap;
        public LineJoin strokeLineJoin;
        public Float strokeMiterLimit;
        public Float strokeOpacity;
        public Length strokeWidth;
        public TextAnchor textAnchor;
        public TextDecoration textDecoration;
        public VectorEffect vectorEffect;
        public SvgPaint viewportFill;
        public Float viewportFillOpacity;
        public Boolean visibility;

        protected Style()
        {
            specifiedFlags = 0L;
        }
    }

    protected static class Box
        implements Cloneable
    {

        public static Box fromLimits(float f, float f1, float f2, float f3)
        {
            return new Box(f, f1, f2 - f, f3 - f1);
        }

        public float maxX()
        {
            return minX + width;
        }

        public float maxY()
        {
            return minY + height;
        }

        public RectF toRectF()
        {
            return new RectF(minX, minY, maxX(), maxY());
        }

        public String toString()
        {
            return (new StringBuilder()).append("[").append(minX).append(" ").append(minY).append(" ").append(width).append(" ").append(height).append("]").toString();
        }

        public void union(Box box)
        {
            if(box.minX < minX)
                minX = box.minX;
            if(box.minY < minY)
                minY = box.minY;
            if(box.maxX() > maxX())
                width = box.maxX() - minX;
            if(box.maxY() > maxY())
                height = box.maxY() - minY;
        }

        public float height;
        public float minX;
        public float minY;
        public float width;

        public Box(float f, float f1, float f2, float f3)
        {
            minX = f;
            minY = f1;
            width = f2;
            height = f3;
        }
    }

    protected static final class GradientSpread extends Enum
    {

        public static GradientSpread valueOf(String s)
        {
            return (GradientSpread)Enum.valueOf(com/caverock/androidsvg/SVG$GradientSpread, s);
        }

        public static GradientSpread[] values()
        {
            return (GradientSpread[])$VALUES.clone();
        }

        private static final GradientSpread $VALUES[];
        public static final GradientSpread pad;
        public static final GradientSpread reflect;
        public static final GradientSpread repeat;

        static 
        {
            pad = new GradientSpread("pad", 0);
            reflect = new GradientSpread("reflect", 1);
            repeat = new GradientSpread("repeat", 2);
            GradientSpread agradientspread[] = new GradientSpread[3];
            agradientspread[0] = pad;
            agradientspread[1] = reflect;
            agradientspread[2] = repeat;
            $VALUES = agradientspread;
        }

        private GradientSpread(String s, int i)
        {
            super(s, i);
        }
    }

    protected static final class Unit extends Enum
    {

        public static Unit valueOf(String s)
        {
            return (Unit)Enum.valueOf(com/caverock/androidsvg/SVG$Unit, s);
        }

        public static Unit[] values()
        {
            return (Unit[])$VALUES.clone();
        }

        private static final Unit $VALUES[];
        public static final Unit cm;
        public static final Unit em;
        public static final Unit ex;
        public static final Unit in;
        public static final Unit mm;
        public static final Unit pc;
        public static final Unit percent;
        public static final Unit pt;
        public static final Unit px;

        static 
        {
            px = new Unit("px", 0);
            em = new Unit("em", 1);
            ex = new Unit("ex", 2);
            in = new Unit("in", 3);
            cm = new Unit("cm", 4);
            mm = new Unit("mm", 5);
            pt = new Unit("pt", 6);
            pc = new Unit("pc", 7);
            percent = new Unit("percent", 8);
            Unit aunit[] = new Unit[9];
            aunit[0] = px;
            aunit[1] = em;
            aunit[2] = ex;
            aunit[3] = in;
            aunit[4] = cm;
            aunit[5] = mm;
            aunit[6] = pt;
            aunit[7] = pc;
            aunit[8] = percent;
            $VALUES = aunit;
        }

        private Unit(String s, int i)
        {
            super(s, i);
        }
    }


    protected SVG()
    {
        rootElement = null;
        title = "";
        desc = "";
        fileResolver = null;
        renderDPI = 96F;
        cssRules = new CSSParser.Ruleset();
    }

    private Box getDocumentDimensions(float f)
    {
        Length length;
        Length length1;
        length = rootElement.width;
        length1 = rootElement.height;
        if(length != null && !length.isZero() && length.unit != Unit.percent && length.unit != Unit.em && length.unit != Unit.ex) goto _L2; else goto _L1
_L1:
        Box box = new Box(-1F, -1F, -1F, -1F);
_L4:
        return box;
_L2:
        float f1;
        float f2;
        f1 = length.floatValue(f);
        if(length1 == null)
            break; /* Loop/switch isn't completed */
        if(length1.isZero() || length1.unit == Unit.percent || length1.unit == Unit.em || length1.unit == Unit.ex)
        {
            box = new Box(-1F, -1F, -1F, -1F);
            continue; /* Loop/switch isn't completed */
        }
        f2 = length1.floatValue(f);
_L5:
        box = new Box(0.0F, 0.0F, f1, f2);
        if(true) goto _L4; else goto _L3
_L3:
        if(rootElement.viewBox != null)
            f2 = (f1 * rootElement.viewBox.height) / rootElement.viewBox.width;
        else
            f2 = f1;
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    private SvgElementBase getElementById(SvgContainer svgcontainer, String s)
    {
        SvgElementBase svgelementbase = (SvgElementBase)svgcontainer;
        if(!s.equals(svgelementbase.id)) goto _L2; else goto _L1
_L1:
        return svgelementbase;
_L2:
        Iterator iterator = svgcontainer.getChildren().iterator();
label0:
        do
        {
            SvgObject svgobject;
            do
            {
                if(!iterator.hasNext())
                    break label0;
                svgobject = (SvgObject)iterator.next();
                if(svgobject instanceof SvgElementBase)
                {
                    SvgElementBase svgelementbase1 = (SvgElementBase)svgobject;
                    if(!s.equals(svgelementbase1.id))
                        continue;
                    svgelementbase = svgelementbase1;
                    continue; /* Loop/switch isn't completed */
                }
                continue label0;
            } while(!(svgobject instanceof SvgContainer));
            SvgElementBase svgelementbase2 = getElementById((SvgContainer)svgobject, s);
            if(svgelementbase2 != null)
            {
                svgelementbase = svgelementbase2;
                continue; /* Loop/switch isn't completed */
            }
        } while(true);
        svgelementbase = null;
        if(true) goto _L1; else goto _L3
_L3:
    }

    private List getElementsByTagName(SvgContainer svgcontainer, Class class1)
    {
        ArrayList arraylist = new ArrayList();
        if(svgcontainer.getClass() == class1)
            arraylist.add((SvgObject)svgcontainer);
        Iterator iterator = svgcontainer.getChildren().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            SvgObject svgobject = (SvgObject)iterator.next();
            if(svgobject.getClass() == class1)
                arraylist.add(svgobject);
            if(svgobject instanceof SvgContainer)
                getElementsByTagName((SvgContainer)svgobject, class1);
        } while(true);
        return arraylist;
    }

    public static SVG getFromAsset(AssetManager assetmanager, String s)
        throws SVGParseException, IOException
    {
        SVGParser svgparser = new SVGParser();
        InputStream inputstream = assetmanager.open(s);
        SVG svg = svgparser.parse(inputstream);
        inputstream.close();
        return svg;
    }

    public static SVG getFromInputStream(InputStream inputstream)
        throws SVGParseException
    {
        return (new SVGParser()).parse(inputstream);
    }

    public static SVG getFromResource(Context context, int i)
        throws SVGParseException
    {
        return getFromResource(context.getResources(), i);
    }

    public static SVG getFromResource(Resources resources, int i)
        throws SVGParseException
    {
        return (new SVGParser()).parse(resources.openRawResource(i));
    }

    public static SVG getFromString(String s)
        throws SVGParseException
    {
        return (new SVGParser()).parse(new ByteArrayInputStream(s.getBytes()));
    }

    public static String getVersion()
    {
        return "1.2.1";
    }

    protected void addCSSRules(CSSParser.Ruleset ruleset)
    {
        cssRules.addAll(ruleset);
    }

    protected List getCSSRules()
    {
        return cssRules.getRules();
    }

    public float getDocumentAspectRatio()
    {
        float f;
        Length length;
        Length length1;
        f = -1F;
        if(rootElement == null)
            throw new IllegalArgumentException("SVG document is empty");
        length = rootElement.width;
        length1 = rootElement.height;
        if(length == null || length1 == null || length.unit == Unit.percent || length1.unit == Unit.percent) goto _L2; else goto _L1
_L1:
        if(!length.isZero() && !length1.isZero())
            f = length.floatValue(renderDPI) / length1.floatValue(renderDPI);
_L4:
        return f;
_L2:
        if(rootElement.viewBox != null && rootElement.viewBox.width != 0.0F && rootElement.viewBox.height != 0.0F)
            f = rootElement.viewBox.width / rootElement.viewBox.height;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getDocumentDescription()
    {
        if(rootElement == null)
            throw new IllegalArgumentException("SVG document is empty");
        else
            return desc;
    }

    public float getDocumentHeight()
    {
        if(rootElement == null)
            throw new IllegalArgumentException("SVG document is empty");
        else
            return getDocumentDimensions(renderDPI).height;
    }

    public PreserveAspectRatio getDocumentPreserveAspectRatio()
    {
        if(rootElement == null)
            throw new IllegalArgumentException("SVG document is empty");
        PreserveAspectRatio preserveaspectratio;
        if(rootElement.preserveAspectRatio == null)
            preserveaspectratio = null;
        else
            preserveaspectratio = rootElement.preserveAspectRatio;
        return preserveaspectratio;
    }

    public String getDocumentSVGVersion()
    {
        if(rootElement == null)
            throw new IllegalArgumentException("SVG document is empty");
        else
            return rootElement.version;
    }

    public String getDocumentTitle()
    {
        if(rootElement == null)
            throw new IllegalArgumentException("SVG document is empty");
        else
            return title;
    }

    public RectF getDocumentViewBox()
    {
        if(rootElement == null)
            throw new IllegalArgumentException("SVG document is empty");
        RectF rectf;
        if(rootElement.viewBox == null)
            rectf = null;
        else
            rectf = rootElement.viewBox.toRectF();
        return rectf;
    }

    public float getDocumentWidth()
    {
        if(rootElement == null)
            throw new IllegalArgumentException("SVG document is empty");
        else
            return getDocumentDimensions(renderDPI).width;
    }

    protected SvgObject getElementById(String s)
    {
        Object obj;
        if(s.equals(rootElement.id))
            obj = rootElement;
        else
            obj = getElementById(((SvgContainer) (rootElement)), s);
        return ((SvgObject) (obj));
    }

    protected List getElementsByTagName(Class class1)
    {
        return getElementsByTagName(((SvgContainer) (rootElement)), class1);
    }

    protected SVGExternalFileResolver getFileResolver()
    {
        return fileResolver;
    }

    public float getRenderDPI()
    {
        return renderDPI;
    }

    protected Svg getRootElement()
    {
        return rootElement;
    }

    public Set getViewList()
    {
        if(rootElement == null)
            throw new IllegalArgumentException("SVG document is empty");
        List list = getElementsByTagName(com/caverock/androidsvg/SVG$View);
        HashSet hashset = new HashSet(list.size());
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            View view = (View)(SvgObject)iterator.next();
            if(view.id != null)
                hashset.add(view.id);
            else
                Log.w("AndroidSVG", "getViewList(): found a <view> without an id attribute");
        }

        return hashset;
    }

    protected boolean hasCSSRules()
    {
        boolean flag;
        if(!cssRules.isEmpty())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void registerExternalFileResolver(SVGExternalFileResolver svgexternalfileresolver)
    {
        fileResolver = svgexternalfileresolver;
    }

    public void renderToCanvas(Canvas canvas)
    {
        renderToCanvas(canvas, null);
    }

    public void renderToCanvas(Canvas canvas, RectF rectf)
    {
        Box box;
        if(rectf != null)
            box = Box.fromLimits(rectf.left, rectf.top, rectf.right, rectf.bottom);
        else
            box = new Box(0.0F, 0.0F, canvas.getWidth(), canvas.getHeight());
        (new SVGAndroidRenderer(canvas, box, renderDPI)).renderDocument(this, null, null, true);
    }

    public Picture renderToPicture()
    {
        Length length = rootElement.width;
        Picture picture;
        if(length != null)
        {
            float f = length.floatValue(renderDPI);
            Box box = rootElement.viewBox;
            float f1;
            if(box != null)
            {
                f1 = (f * box.height) / box.width;
            } else
            {
                Length length1 = rootElement.height;
                if(length1 != null)
                    f1 = length1.floatValue(renderDPI);
                else
                    f1 = f;
            }
            picture = renderToPicture((int)Math.ceil(f), (int)Math.ceil(f1));
        } else
        {
            picture = renderToPicture(128, 128);
        }
        return picture;
    }

    public Picture renderToPicture(int i, int j)
    {
        Picture picture = new Picture();
        (new SVGAndroidRenderer(picture.beginRecording(i, j), new Box(0.0F, 0.0F, i, j), renderDPI)).renderDocument(this, null, null, false);
        picture.endRecording();
        return picture;
    }

    public void renderViewToCanvas(String s, Canvas canvas)
    {
        renderViewToCanvas(s, canvas, null);
    }

    public void renderViewToCanvas(String s, Canvas canvas, RectF rectf)
    {
        SvgObject svgobject;
        svgobject = getElementById(s);
        break MISSING_BLOCK_LABEL_7;
        if(svgobject != null && (svgobject instanceof View))
        {
            View view = (View)svgobject;
            if(view.viewBox == null)
            {
                Log.w("AndroidSVG", "View element is missing a viewBox attribute.");
            } else
            {
                Box box;
                if(rectf != null)
                    box = Box.fromLimits(rectf.left, rectf.top, rectf.right, rectf.bottom);
                else
                    box = new Box(0.0F, 0.0F, canvas.getWidth(), canvas.getHeight());
                (new SVGAndroidRenderer(canvas, box, renderDPI)).renderDocument(this, view.viewBox, view.preserveAspectRatio, true);
            }
        }
        return;
    }

    public Picture renderViewToPicture(String s, int i, int j)
    {
        Picture picture;
        SvgObject svgobject;
        picture = null;
        svgobject = getElementById(s);
        break MISSING_BLOCK_LABEL_10;
        if(svgobject != null && (svgobject instanceof View))
        {
            View view = (View)svgobject;
            if(view.viewBox == null)
            {
                Log.w("AndroidSVG", "View element is missing a viewBox attribute.");
            } else
            {
                picture = new Picture();
                (new SVGAndroidRenderer(picture.beginRecording(i, j), new Box(0.0F, 0.0F, i, j), renderDPI)).renderDocument(this, view.viewBox, view.preserveAspectRatio, false);
                picture.endRecording();
            }
        }
        return picture;
    }

    protected SvgObject resolveIRI(String s)
    {
        SvgObject svgobject;
        svgobject = null;
        break MISSING_BLOCK_LABEL_2;
        if(s != null && s.length() > 1 && s.startsWith("#"))
            svgobject = getElementById(s.substring(1));
        return svgobject;
    }

    protected void setDesc(String s)
    {
        desc = s;
    }

    public void setDocumentHeight(float f)
    {
        if(rootElement == null)
        {
            throw new IllegalArgumentException("SVG document is empty");
        } else
        {
            rootElement.height = new Length(f);
            return;
        }
    }

    public void setDocumentHeight(String s)
        throws SVGParseException
    {
        if(rootElement == null)
            throw new IllegalArgumentException("SVG document is empty");
        try
        {
            rootElement.height = SVGParser.parseLength(s);
            return;
        }
        catch(SAXException saxexception)
        {
            throw new SVGParseException(saxexception.getMessage());
        }
    }

    public void setDocumentPreserveAspectRatio(PreserveAspectRatio preserveaspectratio)
    {
        if(rootElement == null)
        {
            throw new IllegalArgumentException("SVG document is empty");
        } else
        {
            rootElement.preserveAspectRatio = preserveaspectratio;
            return;
        }
    }

    public void setDocumentViewBox(float f, float f1, float f2, float f3)
    {
        if(rootElement == null)
        {
            throw new IllegalArgumentException("SVG document is empty");
        } else
        {
            rootElement.viewBox = new Box(f, f1, f2, f3);
            return;
        }
    }

    public void setDocumentWidth(float f)
    {
        if(rootElement == null)
        {
            throw new IllegalArgumentException("SVG document is empty");
        } else
        {
            rootElement.width = new Length(f);
            return;
        }
    }

    public void setDocumentWidth(String s)
        throws SVGParseException
    {
        if(rootElement == null)
            throw new IllegalArgumentException("SVG document is empty");
        try
        {
            rootElement.width = SVGParser.parseLength(s);
            return;
        }
        catch(SAXException saxexception)
        {
            throw new SVGParseException(saxexception.getMessage());
        }
    }

    public void setRenderDPI(float f)
    {
        renderDPI = f;
    }

    protected void setRootElement(Svg svg)
    {
        rootElement = svg;
    }

    protected void setTitle(String s)
    {
        title = s;
    }

    private static final int DEFAULT_PICTURE_HEIGHT = 128;
    private static final int DEFAULT_PICTURE_WIDTH = 128;
    private static final List EMPTY_CHILD_LIST = new ArrayList(0);
    protected static final long SPECIFIED_ALL = -1L;
    protected static final long SPECIFIED_CLIP = 0x100000L;
    protected static final long SPECIFIED_CLIP_PATH = 0x10000000L;
    protected static final long SPECIFIED_CLIP_RULE = 0x20000000L;
    protected static final long SPECIFIED_COLOR = 4096L;
    protected static final long SPECIFIED_DIRECTION = 0x1000000000L;
    protected static final long SPECIFIED_DISPLAY = 0x1000000L;
    protected static final long SPECIFIED_FILL = 1L;
    protected static final long SPECIFIED_FILL_OPACITY = 4L;
    protected static final long SPECIFIED_FILL_RULE = 2L;
    protected static final long SPECIFIED_FONT_FAMILY = 8192L;
    protected static final long SPECIFIED_FONT_SIZE = 16384L;
    protected static final long SPECIFIED_FONT_STYLE = 0x10000L;
    protected static final long SPECIFIED_FONT_WEIGHT = 32768L;
    protected static final long SPECIFIED_MARKER_END = 0x800000L;
    protected static final long SPECIFIED_MARKER_MID = 0x400000L;
    protected static final long SPECIFIED_MARKER_START = 0x200000L;
    protected static final long SPECIFIED_MASK = 0x40000000L;
    protected static final long SPECIFIED_NON_INHERITING = 0xfdd180800L;
    protected static final long SPECIFIED_OPACITY = 2048L;
    protected static final long SPECIFIED_OVERFLOW = 0x80000L;
    protected static final long SPECIFIED_SOLID_COLOR = 0x80000000L;
    protected static final long SPECIFIED_SOLID_OPACITY = 0x100000000L;
    protected static final long SPECIFIED_STOP_COLOR = 0x4000000L;
    protected static final long SPECIFIED_STOP_OPACITY = 0x8000000L;
    protected static final long SPECIFIED_STROKE = 8L;
    protected static final long SPECIFIED_STROKE_DASHARRAY = 512L;
    protected static final long SPECIFIED_STROKE_DASHOFFSET = 1024L;
    protected static final long SPECIFIED_STROKE_LINECAP = 64L;
    protected static final long SPECIFIED_STROKE_LINEJOIN = 128L;
    protected static final long SPECIFIED_STROKE_MITERLIMIT = 256L;
    protected static final long SPECIFIED_STROKE_OPACITY = 16L;
    protected static final long SPECIFIED_STROKE_WIDTH = 32L;
    protected static final long SPECIFIED_TEXT_ANCHOR = 0x40000L;
    protected static final long SPECIFIED_TEXT_DECORATION = 0x20000L;
    protected static final long SPECIFIED_VECTOR_EFFECT = 0x800000000L;
    protected static final long SPECIFIED_VIEWPORT_FILL = 0x200000000L;
    protected static final long SPECIFIED_VIEWPORT_FILL_OPACITY = 0x400000000L;
    protected static final long SPECIFIED_VISIBILITY = 0x2000000L;
    private static final double SQRT2 = 1.4142135623730949D;
    protected static final String SUPPORTED_SVG_VERSION = "1.2";
    private static final String TAG = "AndroidSVG";
    private static final String VERSION = "1.2.1";
    private CSSParser.Ruleset cssRules;
    private String desc;
    private SVGExternalFileResolver fileResolver;
    private float renderDPI;
    private Svg rootElement;
    private String title;


}
