// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.caverock.androidsvg;

import android.graphics.*;
import android.util.Base64;
import android.util.Log;
import java.util.*;

// Referenced classes of package com.caverock.androidsvg:
//            SVG, PreserveAspectRatio, SVGExternalFileResolver, SVGParser, 
//            CSSParser

public class SVGAndroidRenderer
{
    private class PlainTextToPath extends TextProcessor
    {

        public boolean doTextContainer(SVG.TextContainer textcontainer)
        {
            boolean flag = false;
            if(textcontainer instanceof SVG.TextPath)
                SVGAndroidRenderer.warn("Using <textPath> elements in a clip path is not supported.", new Object[0]);
            else
                flag = true;
            return flag;
        }

        public void processText(String s)
        {
            if(visible())
            {
                Path path = new Path();
                state.fillPaint.getTextPath(s, 0, s.length(), x, y, path);
                textAsPath.addPath(path);
            }
            x = x + state.fillPaint.measureText(s);
        }

        public Path textAsPath;
        final SVGAndroidRenderer this$0;
        public float x;
        public float y;

        public PlainTextToPath(float f, float f1, Path path)
        {
            this$0 = SVGAndroidRenderer.this;
            super();
            x = f;
            y = f1;
            textAsPath = path;
        }
    }

    private class MarkerPositionCalculator
        implements SVG.PathInterface
    {

        public void arcTo(float f, float f1, float f2, boolean flag, boolean flag1, float f3, float f4)
        {
            startArc = true;
            normalCubic = false;
            SVGAndroidRenderer.arcTo(lastPos.x, lastPos.y, f, f1, f2, flag, flag1, f3, f4, this);
            normalCubic = true;
            closepathReAdjustPending = false;
        }

        public void close()
        {
            markers.add(lastPos);
            lineTo(startX, startY);
            closepathReAdjustPending = true;
        }

        public void cubicTo(float f, float f1, float f2, float f3, float f4, float f5)
        {
            if(normalCubic || startArc)
            {
                lastPos.add(f, f1);
                markers.add(lastPos);
                startArc = false;
            }
            lastPos = new MarkerVector(f4, f5, f4 - f2, f5 - f3);
            closepathReAdjustPending = false;
        }

        public List getMarkers()
        {
            return markers;
        }

        public void lineTo(float f, float f1)
        {
            lastPos.add(f, f1);
            markers.add(lastPos);
            lastPos = new MarkerVector(f, f1, f - lastPos.x, f1 - lastPos.y);
            closepathReAdjustPending = false;
        }

        public void moveTo(float f, float f1)
        {
            if(closepathReAdjustPending)
            {
                lastPos.add((MarkerVector)markers.get(subpathStartIndex));
                markers.set(subpathStartIndex, lastPos);
                closepathReAdjustPending = false;
            }
            if(lastPos != null)
                markers.add(lastPos);
            startX = f;
            startY = f1;
            lastPos = new MarkerVector(f, f1, 0.0F, 0.0F);
            subpathStartIndex = markers.size();
        }

        public void quadTo(float f, float f1, float f2, float f3)
        {
            lastPos.add(f, f1);
            markers.add(lastPos);
            lastPos = new MarkerVector(f2, f3, f2 - f, f3 - f1);
            closepathReAdjustPending = false;
        }

        private boolean closepathReAdjustPending;
        private MarkerVector lastPos;
        private List markers;
        private boolean normalCubic;
        private boolean startArc;
        private float startX;
        private float startY;
        private int subpathStartIndex;
        final SVGAndroidRenderer this$0;

        public MarkerPositionCalculator(SVG.PathDefinition pathdefinition)
        {
            this$0 = SVGAndroidRenderer.this;
            super();
            markers = new ArrayList();
            lastPos = null;
            startArc = false;
            normalCubic = true;
            subpathStartIndex = -1;
            pathdefinition.enumeratePath(this);
            if(closepathReAdjustPending)
            {
                lastPos.add((MarkerVector)markers.get(subpathStartIndex));
                markers.set(subpathStartIndex, lastPos);
                closepathReAdjustPending = false;
            }
            if(lastPos != null)
                markers.add(lastPos);
        }
    }

    private class MarkerVector
    {

        public void add(float f, float f1)
        {
            float f2 = f - x;
            float f3 = f1 - y;
            double d = Math.sqrt(f2 * f2 + f3 * f3);
            if(d != 0.0D)
            {
                dx = dx + (float)((double)f2 / d);
                dy = dy + (float)((double)f3 / d);
            }
        }

        public void add(MarkerVector markervector)
        {
            dx = dx + markervector.dx;
            dy = dy + markervector.dy;
        }

        public String toString()
        {
            return (new StringBuilder()).append("(").append(x).append(",").append(y).append(" ").append(dx).append(",").append(dy).append(")").toString();
        }

        public float dx;
        public float dy;
        final SVGAndroidRenderer this$0;
        public float x;
        public float y;

        public MarkerVector(float f, float f1, float f2, float f3)
        {
            this$0 = SVGAndroidRenderer.this;
            super();
            dx = 0.0F;
            dy = 0.0F;
            x = f;
            y = f1;
            double d = Math.sqrt(f2 * f2 + f3 * f3);
            if(d != 0.0D)
            {
                dx = (float)((double)f2 / d);
                dy = (float)((double)f3 / d);
            }
        }
    }

    private class PathConverter
        implements SVG.PathInterface
    {

        public void arcTo(float f, float f1, float f2, boolean flag, boolean flag1, float f3, float f4)
        {
            SVGAndroidRenderer.arcTo(lastX, lastY, f, f1, f2, flag, flag1, f3, f4, this);
            lastX = f3;
            lastY = f4;
        }

        public void close()
        {
            path.close();
        }

        public void cubicTo(float f, float f1, float f2, float f3, float f4, float f5)
        {
            path.cubicTo(f, f1, f2, f3, f4, f5);
            lastX = f4;
            lastY = f5;
        }

        public Path getPath()
        {
            return path;
        }

        public void lineTo(float f, float f1)
        {
            path.lineTo(f, f1);
            lastX = f;
            lastY = f1;
        }

        public void moveTo(float f, float f1)
        {
            path.moveTo(f, f1);
            lastX = f;
            lastY = f1;
        }

        public void quadTo(float f, float f1, float f2, float f3)
        {
            path.quadTo(f, f1, f2, f3);
            lastX = f2;
            lastY = f3;
        }

        float lastX;
        float lastY;
        Path path;
        final SVGAndroidRenderer this$0;

        public PathConverter(SVG.PathDefinition pathdefinition)
        {
            this$0 = SVGAndroidRenderer.this;
            super();
            path = new Path();
            pathdefinition.enumeratePath(this);
        }
    }

    private class TextBoundsCalculator extends TextProcessor
    {

        public boolean doTextContainer(SVG.TextContainer textcontainer)
        {
            boolean flag = false;
            if(textcontainer instanceof SVG.TextPath)
            {
                SVG.TextPath textpath = (SVG.TextPath)textcontainer;
                SVG.SvgObject svgobject = textcontainer.document.resolveIRI(textpath.href);
                if(svgobject == null)
                {
                    Object aobj[] = new Object[1];
                    aobj[flag] = textpath.href;
                    SVGAndroidRenderer.error("TextPath path reference '%s' not found", aobj);
                } else
                {
                    SVG.Path path = (SVG.Path)svgobject;
                    Path path1 = (new PathConverter(path.d)).getPath();
                    if(path.transform != null)
                        path1.transform(path.transform);
                    RectF rectf = new RectF();
                    path1.computeBounds(rectf, true);
                    bbox.union(rectf);
                }
            } else
            {
                flag = true;
            }
            return flag;
        }

        public void processText(String s)
        {
            if(visible())
            {
                Rect rect = new Rect();
                state.fillPaint.getTextBounds(s, 0, s.length(), rect);
                RectF rectf = new RectF(rect);
                rectf.offset(x, y);
                bbox.union(rectf);
            }
            x = x + state.fillPaint.measureText(s);
        }

        RectF bbox;
        final SVGAndroidRenderer this$0;
        float x;
        float y;

        public TextBoundsCalculator(float f, float f1)
        {
            this$0 = SVGAndroidRenderer.this;
            super();
            bbox = new RectF();
            x = f;
            y = f1;
        }
    }

    private class TextWidthCalculator extends TextProcessor
    {

        public void processText(String s)
        {
            x = x + state.fillPaint.measureText(s);
        }

        final SVGAndroidRenderer this$0;
        public float x;

        private TextWidthCalculator()
        {
            this$0 = SVGAndroidRenderer.this;
            super();
            x = 0.0F;
        }

    }

    private class PathTextDrawer extends PlainTextDrawer
    {

        public void processText(String s)
        {
            if(visible())
            {
                if(state.hasFill)
                    canvas.drawTextOnPath(s, path, x, y, state.fillPaint);
                if(state.hasStroke)
                    canvas.drawTextOnPath(s, path, x, y, state.strokePaint);
            }
            x = x + state.fillPaint.measureText(s);
        }

        private Path path;
        final SVGAndroidRenderer this$0;

        public PathTextDrawer(Path path1, float f, float f1)
        {
            this$0 = SVGAndroidRenderer.this;
            super(f, f1);
            path = path1;
        }
    }

    private abstract class TextProcessor
    {

        public boolean doTextContainer(SVG.TextContainer textcontainer)
        {
            return true;
        }

        public abstract void processText(String s);

        final SVGAndroidRenderer this$0;

        private TextProcessor()
        {
            this$0 = SVGAndroidRenderer.this;
            super();
        }

    }

    private class PlainTextDrawer extends TextProcessor
    {

        public void processText(String s)
        {
            SVGAndroidRenderer.debug("TextSequence render", new Object[0]);
            if(visible())
            {
                if(state.hasFill)
                    canvas.drawText(s, x, y, state.fillPaint);
                if(state.hasStroke)
                    canvas.drawText(s, x, y, state.strokePaint);
            }
            x = x + state.fillPaint.measureText(s);
        }

        final SVGAndroidRenderer this$0;
        public float x;
        public float y;

        public PlainTextDrawer(float f, float f1)
        {
            this$0 = SVGAndroidRenderer.this;
            super();
            x = f;
            y = f1;
        }
    }

    private class RendererState
        implements Cloneable
    {

        protected Object clone()
        {
            RendererState rendererstate;
            try
            {
                rendererstate = (RendererState)super.clone();
                rendererstate.style = (SVG.Style)style.clone();
                rendererstate.fillPaint = new Paint(fillPaint);
                rendererstate.strokePaint = new Paint(strokePaint);
            }
            catch(CloneNotSupportedException clonenotsupportedexception)
            {
                throw new InternalError(clonenotsupportedexception.toString());
            }
            return rendererstate;
        }

        public boolean directRendering;
        public Paint fillPaint;
        public boolean hasFill;
        public boolean hasStroke;
        public boolean spacePreserve;
        public Paint strokePaint;
        public SVG.Style style;
        final SVGAndroidRenderer this$0;
        public SVG.Box viewBox;
        public SVG.Box viewPort;

        public RendererState()
        {
            this$0 = SVGAndroidRenderer.this;
            super();
            fillPaint = new Paint();
            fillPaint.setFlags(385);
            fillPaint.setStyle(android.graphics.Paint.Style.FILL);
            fillPaint.setTypeface(Typeface.DEFAULT);
            strokePaint = new Paint();
            strokePaint.setFlags(385);
            strokePaint.setStyle(android.graphics.Paint.Style.STROKE);
            strokePaint.setTypeface(Typeface.DEFAULT);
            style = SVG.Style.getDefaultStyle();
        }
    }


    protected SVGAndroidRenderer(Canvas canvas1, SVG.Box box, float f)
    {
        canvas = canvas1;
        dpi = f;
        canvasViewPort = box;
    }

    private void addObjectToClip(SVG.GraphicsElement graphicselement, Path path, Matrix matrix)
    {
        updateStyleForElement(state, graphicselement);
        break MISSING_BLOCK_LABEL_9;
_L2:
        Path path1;
        do
            return;
        while(!display() || !visible());
        if(graphicselement.transform != null)
            matrix.preConcat(graphicselement.transform);
        if(!(graphicselement instanceof SVG.Rect))
            break; /* Loop/switch isn't completed */
        path1 = makePathAndBoundingBox((SVG.Rect)graphicselement);
_L3:
        checkForClipPath(graphicselement);
        path.setFillType(path1.getFillType());
        path.addPath(path1, matrix);
        if(true) goto _L2; else goto _L1
_L1:
        if(graphicselement instanceof SVG.Circle)
        {
            path1 = makePathAndBoundingBox((SVG.Circle)graphicselement);
        } else
        {
            if(!(graphicselement instanceof SVG.Ellipse))
                continue; /* Loop/switch isn't completed */
            path1 = makePathAndBoundingBox((SVG.Ellipse)graphicselement);
        }
          goto _L3
        if(!(graphicselement instanceof SVG.PolyLine)) goto _L2; else goto _L4
_L4:
        path1 = makePathAndBoundingBox((SVG.PolyLine)graphicselement);
          goto _L3
    }

    private void addObjectToClip(SVG.Path path, Path path1, Matrix matrix)
    {
        updateStyleForElement(state, path);
        break MISSING_BLOCK_LABEL_9;
        if(display() && visible())
        {
            if(path.transform != null)
                matrix.preConcat(path.transform);
            Path path2 = (new PathConverter(path.d)).getPath();
            if(path.boundingBox == null)
                path.boundingBox = calculatePathBounds(path2);
            checkForClipPath(path);
            path1.setFillType(getClipRuleFromState());
            path1.addPath(path2, matrix);
        }
        return;
    }

    private void addObjectToClip(SVG.SvgObject svgobject, boolean flag, Path path, Matrix matrix)
    {
        if(display()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        clipStatePush();
        if(!(svgobject instanceof SVG.Use))
            break; /* Loop/switch isn't completed */
        if(flag)
            addObjectToClip((SVG.Use)svgobject, path, matrix);
        else
            error("<use> elements inside a <clipPath> cannot reference another <use>", new Object[0]);
_L4:
        clipStatePop();
        if(true) goto _L1; else goto _L3
_L3:
        if(svgobject instanceof SVG.Path)
            addObjectToClip((SVG.Path)svgobject, path, matrix);
        else
        if(svgobject instanceof SVG.Text)
            addObjectToClip((SVG.Text)svgobject, path, matrix);
        else
        if(svgobject instanceof SVG.GraphicsElement)
        {
            addObjectToClip((SVG.GraphicsElement)svgobject, path, matrix);
        } else
        {
            Object aobj[] = new Object[1];
            aobj[0] = svgobject.getClass().getSimpleName();
            error("Invalid %s element found in clipPath definition", aobj);
        }
          goto _L4
        if(true) goto _L1; else goto _L5
_L5:
    }

    private void addObjectToClip(SVG.Text text, Path path, Matrix matrix)
    {
        updateStyleForElement(state, text);
        if(display())
        {
            if(text.transform != null)
                matrix.preConcat(text.transform);
            float f;
            float f1;
            float f2;
            float f3;
            if(text.x == null || text.x.size() == 0)
                f = 0.0F;
            else
                f = ((SVG.Length)text.x.get(0)).floatValueX(this);
            if(text.y == null || text.y.size() == 0)
                f1 = 0.0F;
            else
                f1 = ((SVG.Length)text.y.get(0)).floatValueY(this);
            if(text.dx == null || text.dx.size() == 0)
                f2 = 0.0F;
            else
                f2 = ((SVG.Length)text.dx.get(0)).floatValueX(this);
            if(text.dy == null || text.dy.size() == 0)
                f3 = 0.0F;
            else
                f3 = ((SVG.Length)text.dy.get(0)).floatValueY(this);
            if(state.style.textAnchor != SVG.Style.TextAnchor.Start)
            {
                float f4 = calculateTextWidth(text);
                TextBoundsCalculator textboundscalculator;
                Path path1;
                if(state.style.textAnchor == SVG.Style.TextAnchor.Middle)
                    f -= f4 / 2.0F;
                else
                    f -= f4;
            }
            if(text.boundingBox == null)
            {
                textboundscalculator = new TextBoundsCalculator(f, f1);
                enumerateTextSpans(text, textboundscalculator);
                text.boundingBox = new SVG.Box(textboundscalculator.bbox.left, textboundscalculator.bbox.top, textboundscalculator.bbox.width(), textboundscalculator.bbox.height());
            }
            checkForClipPath(text);
            path1 = new Path();
            enumerateTextSpans(text, new PlainTextToPath(f + f2, f1 + f3, path1));
            path.setFillType(getClipRuleFromState());
            path.addPath(path1, matrix);
        }
    }

    private void addObjectToClip(SVG.Use use, Path path, Matrix matrix)
    {
        updateStyleForElement(state, use);
        break MISSING_BLOCK_LABEL_9;
        if(display() && visible())
        {
            if(use.transform != null)
                matrix.preConcat(use.transform);
            SVG.SvgObject svgobject = use.document.resolveIRI(use.href);
            if(svgobject == null)
            {
                Object aobj[] = new Object[1];
                aobj[0] = use.href;
                error("Use reference '%s' not found", aobj);
            } else
            {
                checkForClipPath(use);
                addObjectToClip(svgobject, false, path, matrix);
            }
        }
        return;
    }

    private static void arcTo(float f, float f1, float f2, float f3, float f4, boolean flag, boolean flag1, float f5, 
            float f6, SVG.PathInterface pathinterface)
    {
        if(f != f5 || f1 != f6) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(f2 != 0.0F && f3 != 0.0F)
            break; /* Loop/switch isn't completed */
        pathinterface.lineTo(f5, f6);
        if(true) goto _L1; else goto _L3
_L3:
        float f7 = Math.abs(f2);
        float f8 = Math.abs(f3);
        float f9 = (float)Math.toRadians((double)f4 % 360D);
        double d = Math.cos(f9);
        double d1 = Math.sin(f9);
        double d2 = (double)(f - f5) / 2D;
        double d3 = (double)(f1 - f6) / 2D;
        double d4 = d * d2 + d1 * d3;
        double d5 = d2 * -d1 + d * d3;
        double d6 = f7 * f7;
        double d7 = f8 * f8;
        double d8 = d4 * d4;
        double d9 = d5 * d5;
        double d10 = d8 / d6 + d9 / d7;
        if(d10 > 1.0D)
        {
            f7 *= (float)Math.sqrt(d10);
            f8 *= (float)Math.sqrt(d10);
            d6 = f7 * f7;
            d7 = f8 * f8;
        }
        double d11;
        double d12;
        double d13;
        double d14;
        double d15;
        double d16;
        double d17;
        double d18;
        double d19;
        double d20;
        double d21;
        double d22;
        double d23;
        double d24;
        double d25;
        double d26;
        double d27;
        double d28;
        double d29;
        double d30;
        double d31;
        float af[];
        Matrix matrix;
        int i;
        int j;
        if(flag == flag1)
            d11 = -1D;
        else
            d11 = 1.0D;
        d12 = (d6 * d7 - d6 * d9 - d7 * d8) / (d6 * d9 + d7 * d8);
        if(d12 < 0.0D)
            d12 = 0.0D;
        d13 = d11 * Math.sqrt(d12);
        d14 = d13 * ((d5 * (double)f7) / (double)f8);
        d15 = d13 * -((d4 * (double)f8) / (double)f7);
        d16 = (double)(f + f5) / 2D;
        d17 = (double)(f1 + f6) / 2D;
        d18 = d16 + (d * d14 - d1 * d15);
        d19 = d17 + (d1 * d14 + d * d15);
        d20 = (d4 - d14) / (double)f7;
        d21 = (d5 - d15) / (double)f8;
        d22 = (-d4 - d14) / (double)f7;
        d23 = (-d5 - d15) / (double)f8;
        d24 = Math.sqrt(d20 * d20 + d21 * d21);
        if(d21 < 0.0D)
            d25 = -1D;
        else
            d25 = 1.0D;
        d26 = Math.toDegrees(d25 * Math.acos(d20 / d24));
        d27 = Math.sqrt((d20 * d20 + d21 * d21) * (d22 * d22 + d23 * d23));
        d28 = d20 * d22 + d21 * d23;
        if(d20 * d23 - d21 * d22 < 0.0D)
            d29 = -1D;
        else
            d29 = 1.0D;
        d30 = Math.toDegrees(d29 * Math.acos(d28 / d27));
        if(!flag1 && d30 > 0.0D)
            d30 -= 360D;
        else
        if(flag1 && d30 < 0.0D)
            d30 += 360D;
        d31 = d30 % 360D;
        af = arcToBeziers(d26 % 360D, d31);
        matrix = new Matrix();
        matrix.postScale(f7, f8);
        matrix.postRotate(f4);
        matrix.postTranslate((float)d18, (float)d19);
        matrix.mapPoints(af);
        af[-2 + af.length] = f5;
        af[-1 + af.length] = f6;
        i = 0;
        j = af.length;
        if(i < j)
        {
            pathinterface.cubicTo(af[i], af[i + 1], af[i + 2], af[i + 3], af[i + 4], af[i + 5]);
            i += 6;
            break MISSING_BLOCK_LABEL_647;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    private static float[] arcToBeziers(double d, double d1)
    {
        int i = (int)Math.ceil(Math.abs(d1) / 90D);
        double d2 = Math.toRadians(d);
        float f = (float)(Math.toRadians(d1) / (double)i);
        double d3 = (1.3333333333333333D * Math.sin((double)f / 2D)) / (1.0D + Math.cos((double)f / 2D));
        float af[] = new float[i * 6];
        int j = 0;
        int k = 0;
        for(; j < i; j++)
        {
            double d4 = d2 + (double)(f * (float)j);
            double d5 = Math.cos(d4);
            double d6 = Math.sin(d4);
            int l = k + 1;
            af[k] = (float)(d5 - d3 * d6);
            int i1 = l + 1;
            af[l] = (float)(d6 + d3 * d5);
            double d7 = d4 + (double)f;
            double d8 = Math.cos(d7);
            double d9 = Math.sin(d7);
            int j1 = i1 + 1;
            af[i1] = (float)(d8 + d3 * d9);
            int k1 = j1 + 1;
            af[j1] = (float)(d9 - d3 * d8);
            int l1 = k1 + 1;
            af[k1] = (float)d8;
            k = l1 + 1;
            af[l1] = (float)d9;
        }

        return af;
    }

    private List calculateMarkerPositions(SVG.Line line)
    {
        float f;
        float f1;
        float f2;
        float f3;
        ArrayList arraylist;
        if(line.x1 != null)
            f = line.x1.floatValueX(this);
        else
            f = 0.0F;
        if(line.y1 != null)
            f1 = line.y1.floatValueY(this);
        else
            f1 = 0.0F;
        if(line.x2 != null)
            f2 = line.x2.floatValueX(this);
        else
            f2 = 0.0F;
        if(line.y2 != null)
            f3 = line.y2.floatValueY(this);
        else
            f3 = 0.0F;
        arraylist = new ArrayList(2);
        arraylist.add(new MarkerVector(f, f1, f2 - f, f3 - f1));
        arraylist.add(new MarkerVector(f2, f3, f2 - f, f3 - f1));
        return arraylist;
    }

    private List calculateMarkerPositions(SVG.PolyLine polyline)
    {
        int i = polyline.points.length;
        if(i >= 2) goto _L2; else goto _L1
_L1:
        Object obj = null;
_L4:
        return ((List) (obj));
_L2:
        obj = new ArrayList();
        MarkerVector markervector = new MarkerVector(polyline.points[0], polyline.points[1], 0.0F, 0.0F);
        float f = 0.0F;
        float f1 = 0.0F;
        for(int j = 2; j < i; j += 2)
        {
            f = polyline.points[j];
            f1 = polyline.points[j + 1];
            markervector.add(f, f1);
            ((List) (obj)).add(markervector);
            markervector = new MarkerVector(f, f1, f - markervector.x, f1 - markervector.y);
        }

        if(polyline instanceof SVG.Polygon)
        {
            if(f != polyline.points[0] && f1 != polyline.points[1])
            {
                float f2 = polyline.points[0];
                float f3 = polyline.points[1];
                markervector.add(f2, f3);
                ((List) (obj)).add(markervector);
                MarkerVector markervector1 = new MarkerVector(f2, f3, f2 - markervector.x, f3 - markervector.y);
                markervector1.add((MarkerVector)((List) (obj)).get(0));
                ((List) (obj)).add(markervector1);
                ((List) (obj)).set(0, markervector1);
            }
        } else
        {
            ((List) (obj)).add(markervector);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private SVG.Box calculatePathBounds(Path path)
    {
        RectF rectf = new RectF();
        path.computeBounds(rectf, true);
        return new SVG.Box(rectf.left, rectf.top, rectf.width(), rectf.height());
    }

    private float calculateTextWidth(SVG.TextContainer textcontainer)
    {
        TextWidthCalculator textwidthcalculator = new TextWidthCalculator();
        enumerateTextSpans(textcontainer, textwidthcalculator);
        return textwidthcalculator.x;
    }

    private Matrix calculateViewBoxTransform(SVG.Box box, SVG.Box box1, PreserveAspectRatio preserveaspectratio)
    {
        Matrix matrix;
        float f;
        float f1;
        float f2;
        float f3;
        matrix = new Matrix();
        if(preserveaspectratio != null && preserveaspectratio.getAlignment() != null)
        {
label0:
            {
                f = box.width / box1.width;
                f1 = box.height / box1.height;
                f2 = -box1.minX;
                f3 = -box1.minY;
                if(!preserveaspectratio.equals(PreserveAspectRatio.STRETCH))
                    break label0;
                matrix.preTranslate(box.minX, box.minY);
                matrix.preScale(f, f1);
                matrix.preTranslate(f2, f3);
            }
        }
_L7:
        return matrix;
        float f5;
        float f6;
        class _cls1
        {

            static final int $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment[];
            static final int $SwitchMap$com$caverock$androidsvg$SVG$Style$FillRule[];
            static final int $SwitchMap$com$caverock$androidsvg$SVG$Style$LineCaps[];
            static final int $SwitchMap$com$caverock$androidsvg$SVG$Style$LineJoin[];

            static 
            {
                $SwitchMap$com$caverock$androidsvg$SVG$Style$FillRule = new int[SVG.Style.FillRule.values().length];
                NoSuchFieldError nosuchfielderror15;
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVG$Style$FillRule[SVG.Style.FillRule.EvenOdd.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVG$Style$FillRule[SVG.Style.FillRule.NonZero.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                $SwitchMap$com$caverock$androidsvg$SVG$Style$LineJoin = new int[SVG.Style.LineJoin.values().length];
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVG$Style$LineJoin[SVG.Style.LineJoin.Miter.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVG$Style$LineJoin[SVG.Style.LineJoin.Round.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVG$Style$LineJoin[SVG.Style.LineJoin.Bevel.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                $SwitchMap$com$caverock$androidsvg$SVG$Style$LineCaps = new int[SVG.Style.LineCaps.values().length];
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVG$Style$LineCaps[SVG.Style.LineCaps.Butt.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVG$Style$LineCaps[SVG.Style.LineCaps.Round.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$SVG$Style$LineCaps[SVG.Style.LineCaps.Square.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror7) { }
                $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment = new int[PreserveAspectRatio.Alignment.values().length];
                try
                {
                    $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment[PreserveAspectRatio.Alignment.XMidYMin.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment[PreserveAspectRatio.Alignment.XMidYMid.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror9) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment[PreserveAspectRatio.Alignment.XMidYMax.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror10) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment[PreserveAspectRatio.Alignment.XMaxYMin.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror11) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment[PreserveAspectRatio.Alignment.XMaxYMid.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror12) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment[PreserveAspectRatio.Alignment.XMaxYMax.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror13) { }
                try
                {
                    $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment[PreserveAspectRatio.Alignment.XMinYMid.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror14) { }
                $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment[PreserveAspectRatio.Alignment.XMinYMax.ordinal()] = 8;
_L2:
                return;
                nosuchfielderror15;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        float f4;
        if(preserveaspectratio.getScale() == PreserveAspectRatio.Scale.Slice)
            f4 = Math.max(f, f1);
        else
            f4 = Math.min(f, f1);
        f5 = box.width / f4;
        f6 = box.height / f4;
        _cls1..SwitchMap.com.caverock.androidsvg.PreserveAspectRatio.Alignment[preserveaspectratio.getAlignment().ordinal()];
        JVM INSTR tableswitch 1 6: default 192
    //                   1 293
    //                   2 293
    //                   3 293
    //                   4 310
    //                   5 310
    //                   6 310;
           goto _L1 _L2 _L2 _L2 _L3 _L3 _L3
_L1:
        _cls1..SwitchMap.com.caverock.androidsvg.PreserveAspectRatio.Alignment[preserveaspectratio.getAlignment().ordinal()];
        JVM INSTR tableswitch 2 8: default 244
    //                   2 325
    //                   3 342
    //                   4 244
    //                   5 325
    //                   6 342
    //                   7 325
    //                   8 342;
           goto _L4 _L5 _L6 _L4 _L5 _L6 _L5 _L6
_L4:
        matrix.preTranslate(box.minX, box.minY);
        matrix.preScale(f4, f4);
        matrix.preTranslate(f2, f3);
          goto _L7
_L2:
        f2 -= (box1.width - f5) / 2.0F;
          goto _L1
_L3:
        f2 -= box1.width - f5;
          goto _L1
_L5:
        f3 -= (box1.height - f6) / 2.0F;
          goto _L4
_L6:
        f3 -= box1.height - f6;
          goto _L4
    }

    private void checkForClipPath(SVG.SvgElement svgelement)
    {
        checkForClipPath(svgelement, svgelement.boundingBox);
    }

    private void checkForClipPath(SVG.SvgElement svgelement, SVG.Box box)
    {
        if(state.style.clipPath != null)
        {
            SVG.SvgObject svgobject = svgelement.document.resolveIRI(state.style.clipPath);
            if(svgobject == null)
            {
                Object aobj1[] = new Object[1];
                aobj1[0] = state.style.clipPath;
                error("ClipPath reference '%s' not found", aobj1);
            } else
            {
                SVG.ClipPath clippath = (SVG.ClipPath)svgobject;
                if(clippath.children.isEmpty())
                {
                    canvas.clipRect(0, 0, 0, 0);
                } else
                {
                    boolean flag;
                    if(clippath.clipPathUnitsAreUser == null || clippath.clipPathUnitsAreUser.booleanValue())
                        flag = true;
                    else
                        flag = false;
                    if((svgelement instanceof SVG.Group) && !flag)
                    {
                        Object aobj[] = new Object[1];
                        aobj[0] = svgelement.getClass().getSimpleName();
                        warn("<clipPath clipPathUnits=\"objectBoundingBox\"> is not supported when referenced from container elements (like %s)", aobj);
                    } else
                    {
                        clipStatePush();
                        if(!flag)
                        {
                            Matrix matrix = new Matrix();
                            matrix.preTranslate(box.minX, box.minY);
                            matrix.preScale(box.width, box.height);
                            canvas.concat(matrix);
                        }
                        if(clippath.transform != null)
                            canvas.concat(clippath.transform);
                        state = findInheritFromAncestorState(clippath);
                        checkForClipPath(((SVG.SvgElement) (clippath)));
                        Path path = new Path();
                        for(Iterator iterator = clippath.children.iterator(); iterator.hasNext(); addObjectToClip((SVG.SvgObject)iterator.next(), true, path, new Matrix()));
                        canvas.clipPath(path);
                        clipStatePop();
                    }
                }
            }
        }
    }

    private void checkForGradiantsAndPatterns(SVG.SvgElement svgelement)
    {
        if(state.style.fill instanceof SVG.PaintReference)
            decodePaintReference(true, svgelement.boundingBox, (SVG.PaintReference)state.style.fill);
        if(state.style.stroke instanceof SVG.PaintReference)
            decodePaintReference(false, svgelement.boundingBox, (SVG.PaintReference)state.style.stroke);
    }

    private Bitmap checkForImageDataURL(String s)
    {
        Bitmap bitmap;
        bitmap = null;
        break MISSING_BLOCK_LABEL_2;
        while(true) 
        {
            do
                return bitmap;
            while(!s.startsWith("data:") || s.length() < 14);
            int i = s.indexOf(',');
            if(i != -1 && i >= 12 && ";base64".equals(s.substring(i - 7, i)))
            {
                byte abyte0[] = Base64.decode(s.substring(i + 1), 0);
                bitmap = BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length);
            }
        }
    }

    private Typeface checkGenericFont(String s, Integer integer, SVG.Style.FontStyle fontstyle)
    {
        Typeface typeface;
        byte byte0;
        typeface = null;
        boolean flag;
        if(fontstyle == SVG.Style.FontStyle.Italic)
            flag = true;
        else
            flag = false;
        if(integer.intValue() > 500)
        {
            if(flag)
                byte0 = 3;
            else
                byte0 = 1;
        } else
        if(flag)
            byte0 = 2;
        else
            byte0 = 0;
        if(!s.equals("serif")) goto _L2; else goto _L1
_L1:
        typeface = Typeface.create(Typeface.SERIF, byte0);
_L4:
        return typeface;
_L2:
        if(s.equals("sans-serif"))
            typeface = Typeface.create(Typeface.SANS_SERIF, byte0);
        else
        if(s.equals("monospace"))
            typeface = Typeface.create(Typeface.MONOSPACE, byte0);
        else
        if(s.equals("cursive"))
            typeface = Typeface.create(Typeface.SANS_SERIF, byte0);
        else
        if(s.equals("fantasy"))
            typeface = Typeface.create(Typeface.SANS_SERIF, byte0);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void checkXMLSpaceAttribute(SVG.SvgObject svgobject)
    {
        if(svgobject instanceof SVG.SvgElementBase) goto _L2; else goto _L1
_L1:
        return;
_L2:
        SVG.SvgElementBase svgelementbase = (SVG.SvgElementBase)svgobject;
        if(svgelementbase.spacePreserve != null)
            state.spacePreserve = svgelementbase.spacePreserve.booleanValue();
        if(true) goto _L1; else goto _L3
_L3:
    }

    private int clamp255(float f)
    {
        int i = (int)(256F * f);
        if(i >= 0) goto _L2; else goto _L1
_L1:
        i = 0;
_L4:
        return i;
_L2:
        if(i > 255)
            i = 255;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void clipStatePop()
    {
        canvas.restore();
        state = (RendererState)stateStack.pop();
    }

    private void clipStatePush()
    {
        canvas.save(1);
        stateStack.push(state);
        state = (RendererState)state.clone();
    }

    private static transient void debug(String s, Object aobj[])
    {
    }

    private void decodePaintReference(boolean flag, SVG.Box box, SVG.PaintReference paintreference)
    {
        SVG.SvgObject svgobject = document.resolveIRI(paintreference.href);
        if(svgobject != null) goto _L2; else goto _L1
_L1:
        Object aobj[] = new Object[2];
        String s;
        if(flag)
            s = "Fill";
        else
            s = "Stroke";
        aobj[0] = s;
        aobj[1] = paintreference.href;
        error("%s reference '%s' not found", aobj);
        if(paintreference.fallback != null)
            setPaintColour(state, flag, paintreference.fallback);
        else
        if(flag)
            state.hasFill = false;
        else
            state.hasStroke = false;
_L4:
        return;
_L2:
        if(svgobject instanceof SVG.SvgLinearGradient)
            makeLinearGradiant(flag, box, (SVG.SvgLinearGradient)svgobject);
        if(svgobject instanceof SVG.SvgRadialGradient)
            makeRadialGradiant(flag, box, (SVG.SvgRadialGradient)svgobject);
        if(svgobject instanceof SVG.SolidColor)
            setSolidColor(flag, (SVG.SolidColor)svgobject);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private boolean display()
    {
        boolean flag;
        if(state.style.display != null)
            flag = state.style.display.booleanValue();
        else
            flag = true;
        return flag;
    }

    private void doFilledPath(SVG.SvgElement svgelement, Path path)
    {
        if(!(state.style.fill instanceof SVG.PaintReference)) goto _L2; else goto _L1
_L1:
        SVG.SvgObject svgobject = document.resolveIRI(((SVG.PaintReference)state.style.fill).href);
        if(!(svgobject instanceof SVG.Pattern)) goto _L2; else goto _L3
_L3:
        fillWithPattern(svgelement, path, (SVG.Pattern)svgobject);
_L5:
        return;
_L2:
        canvas.drawPath(path, state.fillPaint);
        if(true) goto _L5; else goto _L4
_L4:
    }

    private void doStroke(Path path)
    {
        if(state.style.vectorEffect == SVG.Style.VectorEffect.NonScalingStroke)
        {
            Matrix matrix = canvas.getMatrix();
            Path path1 = new Path();
            path.transform(matrix, path1);
            canvas.setMatrix(new Matrix());
            Shader shader = state.strokePaint.getShader();
            Matrix matrix1 = new Matrix();
            if(shader != null)
            {
                shader.getLocalMatrix(matrix1);
                Matrix matrix2 = new Matrix(matrix1);
                matrix2.postConcat(matrix);
                shader.setLocalMatrix(matrix2);
            }
            canvas.drawPath(path1, state.strokePaint);
            canvas.setMatrix(matrix);
            if(shader != null)
                shader.setLocalMatrix(matrix1);
        } else
        {
            canvas.drawPath(path, state.strokePaint);
        }
    }

    private void duplicateCanvas()
    {
        try
        {
            Bitmap bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
            bitmapStack.push(bitmap);
            Canvas canvas1 = new Canvas(bitmap);
            canvas1.setMatrix(canvas.getMatrix());
            canvas = canvas1;
            return;
        }
        catch(OutOfMemoryError outofmemoryerror)
        {
            error("Not enough memory to create temporary bitmaps for mask processing", new Object[0]);
            throw outofmemoryerror;
        }
    }

    private void enumerateTextSpans(SVG.TextContainer textcontainer, TextProcessor textprocessor)
    {
        if(display())
        {
            Iterator iterator = textcontainer.children.iterator();
            boolean flag = true;
            while(iterator.hasNext()) 
            {
                SVG.SvgObject svgobject = (SVG.SvgObject)iterator.next();
                if(svgobject instanceof SVG.TextSequence)
                {
                    String s = ((SVG.TextSequence)svgobject).text;
                    boolean flag1;
                    if(!iterator.hasNext())
                        flag1 = true;
                    else
                        flag1 = false;
                    textprocessor.processText(textXMLSpaceTransform(s, flag, flag1));
                } else
                {
                    processTextChild(svgobject, textprocessor);
                }
                flag = false;
            }
        }
    }

    private static transient void error(String s, Object aobj[])
    {
        Log.e("SVGAndroidRenderer", String.format(s, aobj));
    }

    private void extractRawText(SVG.TextContainer textcontainer, StringBuilder stringbuilder)
    {
        Iterator iterator = textcontainer.children.iterator();
        boolean flag = true;
        while(iterator.hasNext()) 
        {
            SVG.SvgObject svgobject = (SVG.SvgObject)iterator.next();
            if(svgobject instanceof SVG.TextContainer)
                extractRawText((SVG.TextContainer)svgobject, stringbuilder);
            else
            if(svgobject instanceof SVG.TextSequence)
            {
                String s = ((SVG.TextSequence)svgobject).text;
                boolean flag1;
                if(!iterator.hasNext())
                    flag1 = true;
                else
                    flag1 = false;
                stringbuilder.append(textXMLSpaceTransform(s, flag, flag1));
            }
            flag = false;
        }
    }

    private void fillInChainedGradientFields(SVG.GradientElement gradientelement, String s)
    {
        SVG.SvgObject svgobject = gradientelement.document.resolveIRI(s);
        if(svgobject != null) goto _L2; else goto _L1
_L1:
        Object aobj1[] = new Object[1];
        aobj1[0] = s;
        warn("Gradient reference '%s' not found", aobj1);
_L4:
        return;
_L2:
        SVG.GradientElement gradientelement1;
        if(!(svgobject instanceof SVG.GradientElement))
        {
            error("Gradient href attributes must point to other gradient elements", new Object[0]);
            continue; /* Loop/switch isn't completed */
        }
        if(svgobject == gradientelement)
        {
            Object aobj[] = new Object[1];
            aobj[0] = s;
            error("Circular reference in gradient href attribute '%s'", aobj);
            continue; /* Loop/switch isn't completed */
        }
        gradientelement1 = (SVG.GradientElement)svgobject;
        if(gradientelement.gradientUnitsAreUser == null)
            gradientelement.gradientUnitsAreUser = gradientelement1.gradientUnitsAreUser;
        if(gradientelement.gradientTransform == null)
            gradientelement.gradientTransform = gradientelement1.gradientTransform;
        if(gradientelement.spreadMethod == null)
            gradientelement.spreadMethod = gradientelement1.spreadMethod;
        if(gradientelement.children.isEmpty())
            gradientelement.children = gradientelement1.children;
        if(!(gradientelement instanceof SVG.SvgLinearGradient))
            break; /* Loop/switch isn't completed */
        fillInChainedGradientFields((SVG.SvgLinearGradient)gradientelement, (SVG.SvgLinearGradient)svgobject);
_L5:
        if(gradientelement1.href != null)
            fillInChainedGradientFields(gradientelement, gradientelement1.href);
        if(true) goto _L4; else goto _L3
_L3:
        try
        {
            fillInChainedGradientFields((SVG.SvgRadialGradient)gradientelement, (SVG.SvgRadialGradient)svgobject);
        }
        catch(ClassCastException classcastexception) { }
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    private void fillInChainedGradientFields(SVG.SvgLinearGradient svglineargradient, SVG.SvgLinearGradient svglineargradient1)
    {
        if(svglineargradient.x1 == null)
            svglineargradient.x1 = svglineargradient1.x1;
        if(svglineargradient.y1 == null)
            svglineargradient.y1 = svglineargradient1.y1;
        if(svglineargradient.x2 == null)
            svglineargradient.x2 = svglineargradient1.x2;
        if(svglineargradient.y2 == null)
            svglineargradient.y2 = svglineargradient1.y2;
    }

    private void fillInChainedGradientFields(SVG.SvgRadialGradient svgradialgradient, SVG.SvgRadialGradient svgradialgradient1)
    {
        if(svgradialgradient.cx == null)
            svgradialgradient.cx = svgradialgradient1.cx;
        if(svgradialgradient.cy == null)
            svgradialgradient.cy = svgradialgradient1.cy;
        if(svgradialgradient.r == null)
            svgradialgradient.r = svgradialgradient1.r;
        if(svgradialgradient.fx == null)
            svgradialgradient.fx = svgradialgradient1.fx;
        if(svgradialgradient.fy == null)
            svgradialgradient.fy = svgradialgradient1.fy;
    }

    private void fillInChainedPatternFields(SVG.Pattern pattern, String s)
    {
        SVG.SvgObject svgobject = pattern.document.resolveIRI(s);
        if(svgobject != null) goto _L2; else goto _L1
_L1:
        Object aobj1[] = new Object[1];
        aobj1[0] = s;
        warn("Pattern reference '%s' not found", aobj1);
_L4:
        return;
_L2:
        if(!(svgobject instanceof SVG.Pattern))
            error("Pattern href attributes must point to other pattern elements", new Object[0]);
        else
        if(svgobject == pattern)
        {
            Object aobj[] = new Object[1];
            aobj[0] = s;
            error("Circular reference in pattern href attribute '%s'", aobj);
        } else
        {
            SVG.Pattern pattern1 = (SVG.Pattern)svgobject;
            if(pattern.patternUnitsAreUser == null)
                pattern.patternUnitsAreUser = pattern1.patternUnitsAreUser;
            if(pattern.patternContentUnitsAreUser == null)
                pattern.patternContentUnitsAreUser = pattern1.patternContentUnitsAreUser;
            if(pattern.patternTransform == null)
                pattern.patternTransform = pattern1.patternTransform;
            if(pattern.x == null)
                pattern.x = pattern1.x;
            if(pattern.y == null)
                pattern.y = pattern1.y;
            if(pattern.width == null)
                pattern.width = pattern1.width;
            if(pattern.height == null)
                pattern.height = pattern1.height;
            if(pattern.children.isEmpty())
                pattern.children = pattern1.children;
            if(pattern.viewBox == null)
                pattern.viewBox = pattern1.viewBox;
            if(pattern.preserveAspectRatio == null)
                pattern.preserveAspectRatio = pattern1.preserveAspectRatio;
            if(pattern1.href != null)
                fillInChainedPatternFields(pattern, pattern1.href);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void fillWithPattern(SVG.SvgElement svgelement, Path path, SVG.Pattern pattern)
    {
        float f4;
        float f5;
        float f6;
        float f7;
        boolean flag;
        if(pattern.patternUnitsAreUser != null && pattern.patternUnitsAreUser.booleanValue())
            flag = true;
        else
            flag = false;
        if(pattern.href != null)
            fillInChainedPatternFields(pattern, pattern.href);
        if(flag)
        {
            if(pattern.x != null)
                f4 = pattern.x.floatValueX(this);
            else
                f4 = 0.0F;
            if(pattern.y != null)
                f5 = pattern.y.floatValueY(this);
            else
                f5 = 0.0F;
            if(pattern.width != null)
                f6 = pattern.width.floatValueX(this);
            else
                f6 = 0.0F;
            if(pattern.height != null)
                f7 = pattern.height.floatValueY(this);
            else
                f7 = 0.0F;
        } else
        {
            float f;
            float f1;
            float f2;
            float f3;
            if(pattern.x != null)
                f = pattern.x.floatValue(this, 1.0F);
            else
                f = 0.0F;
            if(pattern.y != null)
                f1 = pattern.y.floatValue(this, 1.0F);
            else
                f1 = 0.0F;
            if(pattern.width != null)
                f2 = pattern.width.floatValue(this, 1.0F);
            else
                f2 = 0.0F;
            if(pattern.height != null)
                f3 = pattern.height.floatValue(this, 1.0F);
            else
                f3 = 0.0F;
            f4 = svgelement.boundingBox.minX + f * svgelement.boundingBox.width;
            f5 = svgelement.boundingBox.minY + f1 * svgelement.boundingBox.height;
            f6 = f2 * svgelement.boundingBox.width;
            f7 = f3 * svgelement.boundingBox.height;
        }
        if(f6 != 0.0F && f7 != 0.0F) goto _L2; else goto _L1
_L1:
        return;
_L2:
        PreserveAspectRatio preserveaspectratio;
        float f8;
        float f10;
        float f11;
        SVG.Box box1;
        float f12;
        RendererState rendererstate;
        SVG.Box box;
        if(pattern.preserveAspectRatio != null)
            preserveaspectratio = pattern.preserveAspectRatio;
        else
            preserveaspectratio = PreserveAspectRatio.LETTERBOX;
        statePush();
        canvas.clipPath(path);
        rendererstate = new RendererState();
        updateStyle(rendererstate, SVG.Style.getDefaultStyle());
        rendererstate.style.overflow = Boolean.valueOf(false);
        state = findInheritFromAncestorState(pattern, rendererstate);
        box = svgelement.boundingBox;
        if(pattern.patternTransform != null)
        {
            canvas.concat(pattern.patternTransform);
            Matrix matrix = new Matrix();
            if(pattern.patternTransform.invert(matrix))
            {
                float af[] = new float[8];
                af[0] = svgelement.boundingBox.minX;
                af[1] = svgelement.boundingBox.minY;
                af[2] = svgelement.boundingBox.maxX();
                af[3] = svgelement.boundingBox.minY;
                af[4] = svgelement.boundingBox.maxX();
                af[5] = svgelement.boundingBox.maxY();
                af[6] = svgelement.boundingBox.minX;
                af[7] = svgelement.boundingBox.maxY();
                matrix.mapPoints(af);
                RectF rectf = new RectF(af[0], af[1], af[0], af[1]);
                for(int i = 2; i <= 6; i += 2)
                {
                    if(af[i] < rectf.left)
                        rectf.left = af[i];
                    if(af[i] > rectf.right)
                        rectf.right = af[i];
                    if(af[i + 1] < rectf.top)
                        rectf.top = af[i + 1];
                    if(af[i + 1] > rectf.bottom)
                        rectf.bottom = af[i + 1];
                }

                float f14 = rectf.left;
                float f15 = rectf.top;
                float f16 = rectf.right - rectf.left;
                float f17 = rectf.bottom - rectf.top;
                box = new SVG.Box(f14, f15, f16, f17);
            }
        }
        f8 = f4 + f6 * (float)Math.floor((box.minX - f4) / f6);
        float f9 = f5 + f7 * (float)Math.floor((box.minY - f5) / f7);
        f10 = box.maxX();
        f11 = box.maxY();
        box1 = new SVG.Box(0.0F, 0.0F, f6, f7);
        f12 = f9;
_L11:
        float f13;
        if(f12 >= f11)
            break; /* Loop/switch isn't completed */
        f13 = f8;
_L9:
        if(f13 >= f10) goto _L4; else goto _L3
_L3:
        box1.minX = f13;
        box1.minY = f12;
        statePush();
        if(!state.style.overflow.booleanValue())
            setClipRect(box1.minX, box1.minY, box1.width, box1.height);
        if(pattern.viewBox == null) goto _L6; else goto _L5
_L5:
        canvas.concat(calculateViewBoxTransform(box1, pattern.viewBox, preserveaspectratio));
_L8:
        boolean flag2;
        flag2 = pushLayer();
        for(Iterator iterator = pattern.children.iterator(); iterator.hasNext(); render((SVG.SvgObject)iterator.next()));
        break; /* Loop/switch isn't completed */
_L6:
        boolean flag1;
        if(pattern.patternContentUnitsAreUser == null || pattern.patternContentUnitsAreUser.booleanValue())
            flag1 = true;
        else
            flag1 = false;
        canvas.translate(f13, f12);
        if(!flag1)
            canvas.scale(svgelement.boundingBox.width, svgelement.boundingBox.height);
        if(true) goto _L8; else goto _L7
_L7:
        if(flag2)
            popLayer(pattern);
        statePop();
        f13 += f6;
          goto _L9
_L4:
        f12 += f7;
        if(true) goto _L11; else goto _L10
_L10:
        statePop();
        if(true) goto _L1; else goto _L12
_L12:
    }

    private RendererState findInheritFromAncestorState(SVG.SvgObject svgobject)
    {
        RendererState rendererstate = new RendererState();
        updateStyle(rendererstate, SVG.Style.getDefaultStyle());
        return findInheritFromAncestorState(svgobject, rendererstate);
    }

    private RendererState findInheritFromAncestorState(SVG.SvgObject svgobject, RendererState rendererstate)
    {
        ArrayList arraylist = new ArrayList();
        do
        {
            if(svgobject instanceof SVG.SvgElementBase)
                arraylist.add(0, (SVG.SvgElementBase)svgobject);
            if(svgobject.parent == null)
            {
                for(Iterator iterator = arraylist.iterator(); iterator.hasNext(); updateStyleForElement(rendererstate, (SVG.SvgElementBase)iterator.next()));
                break;
            }
            svgobject = (SVG.SvgObject)svgobject.parent;
        } while(true);
        rendererstate.viewBox = document.getRootElement().viewBox;
        if(rendererstate.viewBox == null)
            rendererstate.viewBox = canvasViewPort;
        rendererstate.viewPort = canvasViewPort;
        rendererstate.directRendering = state.directRendering;
        return rendererstate;
    }

    private SVG.Style.TextAnchor getAnchorPosition()
    {
        SVG.Style.TextAnchor textanchor;
        if(state.style.direction == SVG.Style.TextDirection.LTR || state.style.textAnchor == SVG.Style.TextAnchor.Middle)
            textanchor = state.style.textAnchor;
        else
        if(state.style.textAnchor == SVG.Style.TextAnchor.Start)
            textanchor = SVG.Style.TextAnchor.End;
        else
            textanchor = SVG.Style.TextAnchor.Start;
        return textanchor;
    }

    private android.graphics.Path.FillType getClipRuleFromState()
    {
        if(state.style.clipRule != null) goto _L2; else goto _L1
_L1:
        android.graphics.Path.FillType filltype = android.graphics.Path.FillType.WINDING;
_L4:
        return filltype;
_L2:
        switch(_cls1..SwitchMap.com.caverock.androidsvg.SVG.Style.FillRule[state.style.clipRule.ordinal()])
        {
        default:
            filltype = android.graphics.Path.FillType.WINDING;
            break;

        case 1: // '\001'
            filltype = android.graphics.Path.FillType.EVEN_ODD;
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private android.graphics.Path.FillType getFillTypeFromState()
    {
        if(state.style.fillRule != null) goto _L2; else goto _L1
_L1:
        android.graphics.Path.FillType filltype = android.graphics.Path.FillType.WINDING;
_L4:
        return filltype;
_L2:
        switch(_cls1..SwitchMap.com.caverock.androidsvg.SVG.Style.FillRule[state.style.fillRule.ordinal()])
        {
        default:
            filltype = android.graphics.Path.FillType.WINDING;
            break;

        case 1: // '\001'
            filltype = android.graphics.Path.FillType.EVEN_ODD;
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static transient void info(String s, Object aobj[])
    {
        Log.i("SVGAndroidRenderer", String.format(s, aobj));
    }

    private boolean isSpecified(SVG.Style style, long l)
    {
        boolean flag;
        if((l & style.specifiedFlags) != 0L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void makeLinearGradiant(boolean flag, SVG.Box box, SVG.SvgLinearGradient svglineargradient)
    {
        Paint paint;
        float f;
        float f1;
        float f2;
        float f3;
        Matrix matrix;
        int i;
        if(svglineargradient.href != null)
            fillInChainedGradientFields(svglineargradient, svglineargradient.href);
        boolean flag1;
        if(svglineargradient.gradientUnitsAreUser != null && svglineargradient.gradientUnitsAreUser.booleanValue())
            flag1 = true;
        else
            flag1 = false;
        if(flag)
            paint = state.fillPaint;
        else
            paint = state.strokePaint;
        if(flag1)
        {
            SVG.Box box1 = getCurrentViewPortInUserUnits();
            if(svglineargradient.x1 != null)
                f = svglineargradient.x1.floatValueX(this);
            else
                f = 0.0F;
            if(svglineargradient.y1 != null)
                f1 = svglineargradient.y1.floatValueY(this);
            else
                f1 = 0.0F;
            if(svglineargradient.x2 != null)
                f2 = svglineargradient.x2.floatValueX(this);
            else
                f2 = box1.width;
            if(svglineargradient.y2 != null)
                f3 = svglineargradient.y2.floatValueY(this);
            else
                f3 = 0.0F;
        } else
        {
            if(svglineargradient.x1 != null)
                f = svglineargradient.x1.floatValue(this, 1.0F);
            else
                f = 0.0F;
            if(svglineargradient.y1 != null)
                f1 = svglineargradient.y1.floatValue(this, 1.0F);
            else
                f1 = 0.0F;
            if(svglineargradient.x2 != null)
                f2 = svglineargradient.x2.floatValue(this, 1.0F);
            else
                f2 = 1.0F;
            if(svglineargradient.y2 != null)
                f3 = svglineargradient.y2.floatValue(this, 1.0F);
            else
                f3 = 0.0F;
        }
        statePush();
        state = findInheritFromAncestorState(svglineargradient);
        matrix = new Matrix();
        if(!flag1)
        {
            matrix.preTranslate(box.minX, box.minY);
            matrix.preScale(box.width, box.height);
        }
        if(svglineargradient.gradientTransform != null)
            matrix.preConcat(svglineargradient.gradientTransform);
        i = svglineargradient.children.size();
        if(i != 0) goto _L2; else goto _L1
_L1:
        statePop();
        if(flag)
            state.hasFill = false;
        else
            state.hasStroke = false;
_L4:
        return;
_L2:
        int ai[];
        float af[];
        android.graphics.Shader.TileMode tilemode;
        ai = new int[i];
        af = new float[i];
        int j = 0;
        float f4 = -1F;
        Iterator iterator = svglineargradient.children.iterator();
        while(iterator.hasNext()) 
        {
            SVG.Stop stop = (SVG.Stop)(SVG.SvgObject)iterator.next();
            SVG.Colour colour;
            if(j == 0 || stop.offset.floatValue() >= f4)
            {
                af[j] = stop.offset.floatValue();
                f4 = stop.offset.floatValue();
            } else
            {
                af[j] = f4;
            }
            statePush();
            updateStyleForElement(state, stop);
            colour = (SVG.Colour)state.style.stopColor;
            if(colour == null)
                colour = SVG.Colour.BLACK;
            ai[j] = clamp255(state.style.stopOpacity.floatValue()) << 24 | colour.colour;
            j++;
            statePop();
        }
        if(f == f2 && f1 == f3 || i == 1)
        {
            statePop();
            int k = ai[i - 1];
            paint.setColor(k);
            continue; /* Loop/switch isn't completed */
        }
        tilemode = android.graphics.Shader.TileMode.CLAMP;
        if(svglineargradient.spreadMethod != null)
        {
            if(svglineargradient.spreadMethod != SVG.GradientSpread.reflect)
                break; /* Loop/switch isn't completed */
            tilemode = android.graphics.Shader.TileMode.MIRROR;
        }
_L6:
        statePop();
        LinearGradient lineargradient = new LinearGradient(f, f1, f2, f3, ai, af, tilemode);
        lineargradient.setLocalMatrix(matrix);
        paint.setShader(lineargradient);
        if(true) goto _L4; else goto _L3
_L3:
        if(svglineargradient.spreadMethod != SVG.GradientSpread.repeat) goto _L6; else goto _L5
_L5:
        tilemode = android.graphics.Shader.TileMode.REPEAT;
          goto _L6
    }

    private Path makePathAndBoundingBox(SVG.Circle circle)
    {
        float f;
        float f1;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        Path path;
        if(circle.cx != null)
            f = circle.cx.floatValueX(this);
        else
            f = 0.0F;
        if(circle.cy != null)
            f1 = circle.cy.floatValueY(this);
        else
            f1 = 0.0F;
        f2 = circle.r.floatValue(this);
        f3 = f - f2;
        f4 = f1 - f2;
        f5 = f + f2;
        f6 = f1 + f2;
        if(circle.boundingBox == null)
            circle.boundingBox = new SVG.Box(f3, f4, 2.0F * f2, 2.0F * f2);
        f7 = f2 * 0.5522848F;
        path = new Path();
        path.moveTo(f, f4);
        path.cubicTo(f + f7, f4, f5, f1 - f7, f5, f1);
        path.cubicTo(f5, f1 + f7, f + f7, f6, f, f6);
        path.cubicTo(f - f7, f6, f3, f1 + f7, f3, f1);
        path.cubicTo(f3, f1 - f7, f - f7, f4, f, f4);
        path.close();
        return path;
    }

    private Path makePathAndBoundingBox(SVG.Ellipse ellipse)
    {
        float f;
        float f1;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        Path path;
        if(ellipse.cx != null)
            f = ellipse.cx.floatValueX(this);
        else
            f = 0.0F;
        if(ellipse.cy != null)
            f1 = ellipse.cy.floatValueY(this);
        else
            f1 = 0.0F;
        f2 = ellipse.rx.floatValueX(this);
        f3 = ellipse.ry.floatValueY(this);
        f4 = f - f2;
        f5 = f1 - f3;
        f6 = f + f2;
        f7 = f1 + f3;
        if(ellipse.boundingBox == null)
            ellipse.boundingBox = new SVG.Box(f4, f5, 2.0F * f2, 2.0F * f3);
        f8 = f2 * 0.5522848F;
        f9 = f3 * 0.5522848F;
        path = new Path();
        path.moveTo(f, f5);
        path.cubicTo(f + f8, f5, f6, f1 - f9, f6, f1);
        path.cubicTo(f6, f1 + f9, f + f8, f7, f, f7);
        path.cubicTo(f - f8, f7, f4, f1 + f9, f4, f1);
        path.cubicTo(f4, f1 - f9, f - f8, f5, f, f5);
        path.close();
        return path;
    }

    private Path makePathAndBoundingBox(SVG.Line line)
    {
        float f;
        float f1;
        float f2;
        float f3;
        Path path;
        if(line.x1 == null)
            f = 0.0F;
        else
            f = line.x1.floatValueX(this);
        if(line.y1 == null)
            f1 = 0.0F;
        else
            f1 = line.y1.floatValueY(this);
        if(line.x2 == null)
            f2 = 0.0F;
        else
            f2 = line.x2.floatValueX(this);
        if(line.y2 == null)
            f3 = 0.0F;
        else
            f3 = line.y2.floatValueY(this);
        if(line.boundingBox == null)
            line.boundingBox = new SVG.Box(Math.min(f, f1), Math.min(f1, f3), Math.abs(f2 - f), Math.abs(f3 - f1));
        path = new Path();
        path.moveTo(f, f1);
        path.lineTo(f2, f3);
        return path;
    }

    private Path makePathAndBoundingBox(SVG.PolyLine polyline)
    {
        Path path = new Path();
        path.moveTo(polyline.points[0], polyline.points[1]);
        for(int i = 2; i < polyline.points.length; i += 2)
            path.lineTo(polyline.points[i], polyline.points[i + 1]);

        if(polyline instanceof SVG.Polygon)
            path.close();
        if(polyline.boundingBox == null)
            polyline.boundingBox = calculatePathBounds(path);
        path.setFillType(getClipRuleFromState());
        return path;
    }

    private Path makePathAndBoundingBox(SVG.Rect rect)
    {
        float f;
        float f1;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        Path path;
        if(rect.rx == null && rect.ry == null)
        {
            f = 0.0F;
            f1 = 0.0F;
        } else
        if(rect.rx == null)
        {
            f1 = rect.ry.floatValueY(this);
            f = f1;
        } else
        if(rect.ry == null)
        {
            f1 = rect.rx.floatValueX(this);
            f = f1;
        } else
        {
            f = rect.rx.floatValueX(this);
            f1 = rect.ry.floatValueY(this);
        }
        f2 = rect.width.floatValueX(this) / 2.0F;
        f3 = Math.min(f, f2);
        f4 = rect.height.floatValueY(this) / 2.0F;
        f5 = Math.min(f1, f4);
        if(rect.x != null)
            f6 = rect.x.floatValueX(this);
        else
            f6 = 0.0F;
        if(rect.y != null)
            f7 = rect.y.floatValueY(this);
        else
            f7 = 0.0F;
        f8 = rect.width.floatValueX(this);
        f9 = rect.height.floatValueY(this);
        if(rect.boundingBox == null)
            rect.boundingBox = new SVG.Box(f6, f7, f8, f9);
        f10 = f6 + f8;
        f11 = f7 + f9;
        path = new Path();
        if(f3 == 0.0F || f5 == 0.0F)
        {
            path.moveTo(f6, f7);
            path.lineTo(f10, f7);
            path.lineTo(f10, f11);
            path.lineTo(f6, f11);
            path.lineTo(f6, f7);
        } else
        {
            float f12 = f3 * 0.5522848F;
            float f13 = f5 * 0.5522848F;
            path.moveTo(f6, f7 + f5);
            path.cubicTo(f6, (f7 + f5) - f13, (f6 + f3) - f12, f7, f6 + f3, f7);
            path.lineTo(f10 - f3, f7);
            path.cubicTo(f12 + (f10 - f3), f7, f10, (f7 + f5) - f13, f10, f7 + f5);
            path.lineTo(f10, f11 - f5);
            path.cubicTo(f10, f13 + (f11 - f5), f12 + (f10 - f3), f11, f10 - f3, f11);
            path.lineTo(f6 + f3, f11);
            float f14 = (f6 + f3) - f12;
            float f15 = f13 + (f11 - f5);
            float f16 = f11 - f5;
            path.cubicTo(f14, f11, f6, f15, f6, f16);
            path.lineTo(f6, f7 + f5);
        }
        path.close();
        return path;
    }

    private void makeRadialGradiant(boolean flag, SVG.Box box, SVG.SvgRadialGradient svgradialgradient)
    {
        Paint paint;
        float f;
        float f1;
        float f2;
        Matrix matrix;
        int i;
        if(svgradialgradient.href != null)
            fillInChainedGradientFields(svgradialgradient, svgradialgradient.href);
        boolean flag1;
        if(svgradialgradient.gradientUnitsAreUser != null && svgradialgradient.gradientUnitsAreUser.booleanValue())
            flag1 = true;
        else
            flag1 = false;
        if(flag)
            paint = state.fillPaint;
        else
            paint = state.strokePaint;
        if(flag1)
        {
            SVG.Length length = new SVG.Length(50F, SVG.Unit.percent);
            if(svgradialgradient.cx != null)
                f = svgradialgradient.cx.floatValueX(this);
            else
                f = length.floatValueX(this);
            if(svgradialgradient.cy != null)
                f1 = svgradialgradient.cy.floatValueY(this);
            else
                f1 = length.floatValueY(this);
            if(svgradialgradient.r != null)
                f2 = svgradialgradient.r.floatValue(this);
            else
                f2 = length.floatValue(this);
        } else
        {
            if(svgradialgradient.cx != null)
                f = svgradialgradient.cx.floatValue(this, 1.0F);
            else
                f = 0.5F;
            if(svgradialgradient.cy != null)
                f1 = svgradialgradient.cy.floatValue(this, 1.0F);
            else
                f1 = 0.5F;
            if(svgradialgradient.r != null)
                f2 = svgradialgradient.r.floatValue(this, 1.0F);
            else
                f2 = 0.5F;
        }
        statePush();
        state = findInheritFromAncestorState(svgradialgradient);
        matrix = new Matrix();
        if(!flag1)
        {
            matrix.preTranslate(box.minX, box.minY);
            matrix.preScale(box.width, box.height);
        }
        if(svgradialgradient.gradientTransform != null)
            matrix.preConcat(svgradialgradient.gradientTransform);
        i = svgradialgradient.children.size();
        if(i != 0) goto _L2; else goto _L1
_L1:
        statePop();
        if(flag)
            state.hasFill = false;
        else
            state.hasStroke = false;
_L4:
        return;
_L2:
        int ai[];
        float af[];
        android.graphics.Shader.TileMode tilemode;
        ai = new int[i];
        af = new float[i];
        int j = 0;
        float f3 = -1F;
        Iterator iterator = svgradialgradient.children.iterator();
        while(iterator.hasNext()) 
        {
            SVG.Stop stop = (SVG.Stop)(SVG.SvgObject)iterator.next();
            SVG.Colour colour;
            if(j == 0 || stop.offset.floatValue() >= f3)
            {
                af[j] = stop.offset.floatValue();
                f3 = stop.offset.floatValue();
            } else
            {
                af[j] = f3;
            }
            statePush();
            updateStyleForElement(state, stop);
            colour = (SVG.Colour)state.style.stopColor;
            if(colour == null)
                colour = SVG.Colour.BLACK;
            ai[j] = clamp255(state.style.stopOpacity.floatValue()) << 24 | colour.colour;
            j++;
            statePop();
        }
        if(f2 == 0.0F || i == 1)
        {
            statePop();
            int k = ai[i - 1];
            paint.setColor(k);
            continue; /* Loop/switch isn't completed */
        }
        tilemode = android.graphics.Shader.TileMode.CLAMP;
        if(svgradialgradient.spreadMethod != null)
        {
            if(svgradialgradient.spreadMethod != SVG.GradientSpread.reflect)
                break; /* Loop/switch isn't completed */
            tilemode = android.graphics.Shader.TileMode.MIRROR;
        }
_L6:
        statePop();
        RadialGradient radialgradient = new RadialGradient(f, f1, f2, ai, af, tilemode);
        radialgradient.setLocalMatrix(matrix);
        paint.setShader(radialgradient);
        if(true) goto _L4; else goto _L3
_L3:
        if(svgradialgradient.spreadMethod != SVG.GradientSpread.repeat) goto _L6; else goto _L5
_L5:
        tilemode = android.graphics.Shader.TileMode.REPEAT;
          goto _L6
    }

    private void parentPop()
    {
        parentStack.pop();
        matrixStack.pop();
    }

    private void parentPush(SVG.SvgContainer svgcontainer)
    {
        parentStack.push(svgcontainer);
        matrixStack.push(canvas.getMatrix());
    }

    private void popLayer(SVG.SvgElement svgelement)
    {
        if(state.style.mask != null && state.directRendering)
        {
            SVG.SvgObject svgobject = document.resolveIRI(state.style.mask);
            duplicateCanvas();
            renderMask((SVG.Mask)svgobject, svgelement);
            Bitmap bitmap = processMaskBitmaps();
            canvas = (Canvas)canvasStack.pop();
            canvas.save();
            canvas.setMatrix(new Matrix());
            canvas.drawBitmap(bitmap, 0.0F, 0.0F, state.fillPaint);
            bitmap.recycle();
            canvas.restore();
        }
        statePop();
    }

    private Bitmap processMaskBitmaps()
    {
        Bitmap bitmap = (Bitmap)bitmapStack.pop();
        Bitmap bitmap1 = (Bitmap)bitmapStack.pop();
        int i = bitmap.getWidth();
        int j = bitmap.getHeight();
        int ai[] = new int[i];
        int ai1[] = new int[i];
        for(int k = 0; k < j; k++)
        {
            bitmap.getPixels(ai, 0, i, 0, k, i, 1);
            bitmap1.getPixels(ai1, 0, i, 0, k, i, 1);
            int l = 0;
            while(l < i) 
            {
                int i1 = ai[l];
                int j1 = i1 & 0xff;
                int k1 = 0xff & i1 >> 8;
                int l1 = 0xff & i1 >> 16;
                int i2 = 0xff & i1 >> 24;
                if(i2 == 0)
                {
                    ai1[l] = 0;
                } else
                {
                    int j2 = (i2 * (l1 * 6963 + k1 * 23442 + j1 * 2362)) / 0x7f8000;
                    int k2 = ai1[l];
                    int l2 = (j2 * (0xff & k2 >> 24)) / 255;
                    ai1[l] = 0xffffff & k2 | l2 << 24;
                }
                l++;
            }
            bitmap1.setPixels(ai1, 0, i, 0, k, i, 1);
        }

        bitmap.recycle();
        return bitmap1;
    }

    private void processTextChild(SVG.SvgObject svgobject, TextProcessor textprocessor)
    {
        if(textprocessor.doTextContainer((SVG.TextContainer)svgobject)) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(svgobject instanceof SVG.TextPath)
        {
            statePush();
            renderTextPath((SVG.TextPath)svgobject);
            statePop();
        } else
        if(svgobject instanceof SVG.TSpan)
        {
            debug("TSpan render", new Object[0]);
            statePush();
            SVG.TSpan tspan = (SVG.TSpan)svgobject;
            updateStyleForElement(state, tspan);
            if(display())
            {
                float f = 0.0F;
                float f1 = 0.0F;
                float f2 = 0.0F;
                float f3 = 0.0F;
                if(textprocessor instanceof PlainTextDrawer)
                {
                    boolean flag;
                    if(tspan.x == null || tspan.x.size() == 0)
                        f = ((PlainTextDrawer)textprocessor).x;
                    else
                        f = ((SVG.Length)tspan.x.get(0)).floatValueX(this);
                    if(tspan.y == null || tspan.y.size() == 0)
                        f1 = ((PlainTextDrawer)textprocessor).y;
                    else
                        f1 = ((SVG.Length)tspan.y.get(0)).floatValueY(this);
                    if(tspan.dx == null || tspan.dx.size() == 0)
                        f2 = 0.0F;
                    else
                        f2 = ((SVG.Length)tspan.dx.get(0)).floatValueX(this);
                    if(tspan.dy == null || tspan.dy.size() == 0)
                        f3 = 0.0F;
                    else
                        f3 = ((SVG.Length)tspan.dy.get(0)).floatValueY(this);
                }
                checkForGradiantsAndPatterns((SVG.SvgElement)tspan.getTextRoot());
                if(textprocessor instanceof PlainTextDrawer)
                {
                    ((PlainTextDrawer)textprocessor).x = f + f2;
                    ((PlainTextDrawer)textprocessor).y = f1 + f3;
                }
                flag = pushLayer();
                enumerateTextSpans(tspan, textprocessor);
                if(flag)
                    popLayer(tspan);
            }
            statePop();
        } else
        if(svgobject instanceof SVG.TRef)
        {
            statePush();
            SVG.TRef tref = (SVG.TRef)svgobject;
            updateStyleForElement(state, tref);
            if(display())
            {
                checkForGradiantsAndPatterns((SVG.SvgElement)tref.getTextRoot());
                SVG.SvgObject svgobject1 = svgobject.document.resolveIRI(tref.href);
                if(svgobject1 != null && (svgobject1 instanceof SVG.TextContainer))
                {
                    StringBuilder stringbuilder = new StringBuilder();
                    extractRawText((SVG.TextContainer)svgobject1, stringbuilder);
                    if(stringbuilder.length() > 0)
                        textprocessor.processText(stringbuilder.toString());
                } else
                {
                    Object aobj[] = new Object[1];
                    aobj[0] = tref.href;
                    error("Tref reference '%s' not found", aobj);
                }
            }
            statePop();
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    private boolean pushLayer()
    {
        if(requiresCompositing()) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        canvas.saveLayerAlpha(null, clamp255(state.style.opacity.floatValue()), 4);
        stateStack.push(state);
        state = (RendererState)state.clone();
        if(state.style.mask != null && state.directRendering)
        {
            SVG.SvgObject svgobject = document.resolveIRI(state.style.mask);
            if(svgobject == null || !(svgobject instanceof SVG.Mask))
            {
                Object aobj[] = new Object[1];
                aobj[0] = state.style.mask;
                error("Mask reference '%s' not found", aobj);
                state.style.mask = null;
                flag = true;
                continue; /* Loop/switch isn't completed */
            }
            canvasStack.push(canvas);
            duplicateCanvas();
        }
        flag = true;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void render(SVG.Circle circle)
    {
        debug("Circle render", new Object[0]);
        if(circle.r != null && !circle.r.isZero()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        updateStyleForElement(state, circle);
        if(display() && visible())
        {
            if(circle.transform != null)
                canvas.concat(circle.transform);
            Path path = makePathAndBoundingBox(circle);
            updateParentBoundingBox(circle);
            checkForGradiantsAndPatterns(circle);
            checkForClipPath(circle);
            boolean flag = pushLayer();
            if(state.hasFill)
                doFilledPath(circle, path);
            if(state.hasStroke)
                doStroke(path);
            if(flag)
                popLayer(circle);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    private void render(SVG.Ellipse ellipse)
    {
        debug("Ellipse render", new Object[0]);
        if(ellipse.rx != null && ellipse.ry != null && !ellipse.rx.isZero() && !ellipse.ry.isZero()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        updateStyleForElement(state, ellipse);
        if(display() && visible())
        {
            if(ellipse.transform != null)
                canvas.concat(ellipse.transform);
            Path path = makePathAndBoundingBox(ellipse);
            updateParentBoundingBox(ellipse);
            checkForGradiantsAndPatterns(ellipse);
            checkForClipPath(ellipse);
            boolean flag = pushLayer();
            if(state.hasFill)
                doFilledPath(ellipse, path);
            if(state.hasStroke)
                doStroke(path);
            if(flag)
                popLayer(ellipse);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    private void render(SVG.Group group)
    {
        debug("Group render", new Object[0]);
        updateStyleForElement(state, group);
        if(display())
        {
            if(group.transform != null)
                canvas.concat(group.transform);
            checkForClipPath(group);
            boolean flag = pushLayer();
            renderChildren(group, true);
            if(flag)
                popLayer(group);
            updateParentBoundingBox(group);
        }
    }

    private void render(SVG.Image image)
    {
        debug("Image render", new Object[0]);
        break MISSING_BLOCK_LABEL_10;
        while(true) 
        {
            do
                return;
            while(image.width == null || image.width.isZero() || image.height == null || image.height.isZero() || image.href == null);
            PreserveAspectRatio preserveaspectratio;
            Bitmap bitmap;
            if(image.preserveAspectRatio != null)
                preserveaspectratio = image.preserveAspectRatio;
            else
                preserveaspectratio = PreserveAspectRatio.LETTERBOX;
            bitmap = checkForImageDataURL(image.href);
            if(bitmap == null)
            {
                SVGExternalFileResolver svgexternalfileresolver = document.getFileResolver();
                if(svgexternalfileresolver == null)
                    continue;
                bitmap = svgexternalfileresolver.resolveImage(image.href);
            }
            if(bitmap == null)
            {
                Object aobj[] = new Object[1];
                aobj[0] = image.href;
                error("Could not locate image '%s'", aobj);
            } else
            {
                updateStyleForElement(state, image);
                if(display() && visible())
                {
                    if(image.transform != null)
                        canvas.concat(image.transform);
                    float f;
                    float f1;
                    float f2;
                    float f3;
                    boolean flag;
                    if(image.x != null)
                        f = image.x.floatValueX(this);
                    else
                        f = 0.0F;
                    if(image.y != null)
                        f1 = image.y.floatValueY(this);
                    else
                        f1 = 0.0F;
                    f2 = image.width.floatValueX(this);
                    f3 = image.height.floatValueX(this);
                    state.viewPort = new SVG.Box(f, f1, f2, f3);
                    if(!state.style.overflow.booleanValue())
                        setClipRect(state.viewPort.minX, state.viewPort.minY, state.viewPort.width, state.viewPort.height);
                    image.boundingBox = new SVG.Box(0.0F, 0.0F, bitmap.getWidth(), bitmap.getHeight());
                    canvas.concat(calculateViewBoxTransform(state.viewPort, image.boundingBox, preserveaspectratio));
                    updateParentBoundingBox(image);
                    checkForClipPath(image);
                    flag = pushLayer();
                    viewportFill();
                    canvas.drawBitmap(bitmap, 0.0F, 0.0F, state.fillPaint);
                    if(flag)
                        popLayer(image);
                }
            }
        }
    }

    private void render(SVG.Line line)
    {
        debug("Line render", new Object[0]);
        updateStyleForElement(state, line);
        break MISSING_BLOCK_LABEL_19;
        while(true) 
        {
            do
                return;
            while(!display() || !visible() || !state.hasStroke);
            if(line.transform != null)
                canvas.concat(line.transform);
            Path path = makePathAndBoundingBox(line);
            updateParentBoundingBox(line);
            checkForGradiantsAndPatterns(line);
            checkForClipPath(line);
            boolean flag = pushLayer();
            doStroke(path);
            renderMarkers(line);
            if(flag)
                popLayer(line);
        }
    }

    private void render(SVG.Path path)
    {
        debug("Path render", new Object[0]);
        updateStyleForElement(state, path);
        break MISSING_BLOCK_LABEL_19;
        while(true) 
        {
            do
                return;
            while(!display() || !visible() || !state.hasStroke && !state.hasFill);
            if(path.transform != null)
                canvas.concat(path.transform);
            Path path1 = (new PathConverter(path.d)).getPath();
            if(path.boundingBox == null)
                path.boundingBox = calculatePathBounds(path1);
            updateParentBoundingBox(path);
            checkForGradiantsAndPatterns(path);
            checkForClipPath(path);
            boolean flag = pushLayer();
            if(state.hasFill)
            {
                path1.setFillType(getFillTypeFromState());
                doFilledPath(path, path1);
            }
            if(state.hasStroke)
                doStroke(path1);
            renderMarkers(path);
            if(flag)
                popLayer(path);
        }
    }

    private void render(SVG.PolyLine polyline)
    {
        debug("PolyLine render", new Object[0]);
        updateStyleForElement(state, polyline);
        break MISSING_BLOCK_LABEL_19;
        while(true) 
        {
            do
                return;
            while(!display() || !visible() || !state.hasStroke && !state.hasFill);
            if(polyline.transform != null)
                canvas.concat(polyline.transform);
            if(polyline.points.length >= 2)
            {
                Path path = makePathAndBoundingBox(polyline);
                updateParentBoundingBox(polyline);
                checkForGradiantsAndPatterns(polyline);
                checkForClipPath(polyline);
                boolean flag = pushLayer();
                if(state.hasFill)
                    doFilledPath(polyline, path);
                if(state.hasStroke)
                    doStroke(path);
                renderMarkers(polyline);
                if(flag)
                    popLayer(polyline);
            }
        }
    }

    private void render(SVG.Polygon polygon)
    {
        debug("Polygon render", new Object[0]);
        updateStyleForElement(state, polygon);
        break MISSING_BLOCK_LABEL_19;
        while(true) 
        {
            do
                return;
            while(!display() || !visible() || !state.hasStroke && !state.hasFill);
            if(polygon.transform != null)
                canvas.concat(polygon.transform);
            if(polygon.points.length >= 2)
            {
                Path path = makePathAndBoundingBox(polygon);
                updateParentBoundingBox(polygon);
                checkForGradiantsAndPatterns(polygon);
                checkForClipPath(polygon);
                boolean flag = pushLayer();
                if(state.hasFill)
                    doFilledPath(polygon, path);
                if(state.hasStroke)
                    doStroke(path);
                renderMarkers(polygon);
                if(flag)
                    popLayer(polygon);
            }
        }
    }

    private void render(SVG.Rect rect)
    {
        debug("Rect render", new Object[0]);
        if(rect.width != null && rect.height != null && !rect.width.isZero() && !rect.height.isZero()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        updateStyleForElement(state, rect);
        if(display() && visible())
        {
            if(rect.transform != null)
                canvas.concat(rect.transform);
            Path path = makePathAndBoundingBox(rect);
            updateParentBoundingBox(rect);
            checkForGradiantsAndPatterns(rect);
            checkForClipPath(rect);
            boolean flag = pushLayer();
            if(state.hasFill)
                doFilledPath(rect, path);
            if(state.hasStroke)
                doStroke(path);
            if(flag)
                popLayer(rect);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    private void render(SVG.Svg svg)
    {
        render(svg, svg.width, svg.height);
    }

    private void render(SVG.Svg svg, SVG.Length length, SVG.Length length1)
    {
        render(svg, length, length1, svg.viewBox, svg.preserveAspectRatio);
    }

    private void render(SVG.Svg svg, SVG.Length length, SVG.Length length1, SVG.Box box, PreserveAspectRatio preserveaspectratio)
    {
        debug("Svg render", new Object[0]);
        if((length == null || !length.isZero()) && (length1 == null || !length1.isZero())) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(preserveaspectratio == null)
            if(svg.preserveAspectRatio != null)
                preserveaspectratio = svg.preserveAspectRatio;
            else
                preserveaspectratio = PreserveAspectRatio.LETTERBOX;
        updateStyleForElement(state, svg);
        if(display())
        {
            float f = 0.0F;
            float f1 = 0.0F;
            SVG.Box box1;
            float f2;
            float f3;
            if(svg.parent != null)
            {
                boolean flag;
                if(svg.x != null)
                    f = svg.x.floatValueX(this);
                else
                    f = 0.0F;
                if(svg.y != null)
                    f1 = svg.y.floatValueY(this);
                else
                    f1 = 0.0F;
            }
            box1 = getCurrentViewPortInUserUnits();
            if(length != null)
                f2 = length.floatValueX(this);
            else
                f2 = box1.width;
            if(length1 != null)
                f3 = length1.floatValueY(this);
            else
                f3 = box1.height;
            state.viewPort = new SVG.Box(f, f1, f2, f3);
            if(!state.style.overflow.booleanValue())
                setClipRect(state.viewPort.minX, state.viewPort.minY, state.viewPort.width, state.viewPort.height);
            checkForClipPath(svg, state.viewPort);
            if(box != null)
            {
                canvas.concat(calculateViewBoxTransform(state.viewPort, box, preserveaspectratio));
                state.viewBox = svg.viewBox;
            } else
            {
                canvas.translate(f, f1);
            }
            flag = pushLayer();
            viewportFill();
            renderChildren(svg, true);
            if(flag)
                popLayer(svg);
            updateParentBoundingBox(svg);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    private void render(SVG.SvgObject svgobject)
    {
        if(!(svgobject instanceof SVG.NotDirectlyRendered)) goto _L2; else goto _L1
_L1:
        return;
_L2:
        statePush();
        checkXMLSpaceAttribute(svgobject);
        if(!(svgobject instanceof SVG.Svg))
            break; /* Loop/switch isn't completed */
        render((SVG.Svg)svgobject);
_L4:
        statePop();
        if(true) goto _L1; else goto _L3
_L3:
        if(svgobject instanceof SVG.Use)
            render((SVG.Use)svgobject);
        else
        if(svgobject instanceof SVG.Switch)
            render((SVG.Switch)svgobject);
        else
        if(svgobject instanceof SVG.Group)
            render((SVG.Group)svgobject);
        else
        if(svgobject instanceof SVG.Image)
            render((SVG.Image)svgobject);
        else
        if(svgobject instanceof SVG.Path)
            render((SVG.Path)svgobject);
        else
        if(svgobject instanceof SVG.Rect)
            render((SVG.Rect)svgobject);
        else
        if(svgobject instanceof SVG.Circle)
            render((SVG.Circle)svgobject);
        else
        if(svgobject instanceof SVG.Ellipse)
            render((SVG.Ellipse)svgobject);
        else
        if(svgobject instanceof SVG.Line)
            render((SVG.Line)svgobject);
        else
        if(svgobject instanceof SVG.Polygon)
            render((SVG.Polygon)svgobject);
        else
        if(svgobject instanceof SVG.PolyLine)
            render((SVG.PolyLine)svgobject);
        else
        if(svgobject instanceof SVG.Text)
            render((SVG.Text)svgobject);
          goto _L4
        if(true) goto _L1; else goto _L5
_L5:
    }

    private void render(SVG.Switch switch1)
    {
        debug("Switch render", new Object[0]);
        updateStyleForElement(state, switch1);
        if(display())
        {
            if(switch1.transform != null)
                canvas.concat(switch1.transform);
            checkForClipPath(switch1);
            boolean flag = pushLayer();
            renderSwitchChild(switch1);
            if(flag)
                popLayer(switch1);
            updateParentBoundingBox(switch1);
        }
    }

    private void render(SVG.Symbol symbol, SVG.Length length, SVG.Length length1)
    {
        debug("Symbol render", new Object[0]);
        if((length == null || !length.isZero()) && (length1 == null || !length1.isZero()))
        {
            PreserveAspectRatio preserveaspectratio;
            float f;
            float f1;
            boolean flag;
            if(symbol.preserveAspectRatio != null)
                preserveaspectratio = symbol.preserveAspectRatio;
            else
                preserveaspectratio = PreserveAspectRatio.LETTERBOX;
            updateStyleForElement(state, symbol);
            if(length != null)
                f = length.floatValueX(this);
            else
                f = state.viewPort.width;
            if(length1 != null)
                f1 = length1.floatValueX(this);
            else
                f1 = state.viewPort.height;
            state.viewPort = new SVG.Box(0.0F, 0.0F, f, f1);
            if(!state.style.overflow.booleanValue())
                setClipRect(state.viewPort.minX, state.viewPort.minY, state.viewPort.width, state.viewPort.height);
            if(symbol.viewBox != null)
            {
                canvas.concat(calculateViewBoxTransform(state.viewPort, symbol.viewBox, preserveaspectratio));
                state.viewBox = symbol.viewBox;
            }
            flag = pushLayer();
            renderChildren(symbol, true);
            if(flag)
                popLayer(symbol);
            updateParentBoundingBox(symbol);
        }
    }

    private void render(SVG.Text text)
    {
        debug("Text render", new Object[0]);
        updateStyleForElement(state, text);
        if(display()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(text.transform != null)
            canvas.concat(text.transform);
        float f;
        float f1;
        float f2;
        float f3;
        SVG.Style.TextAnchor textanchor;
        if(text.x == null || text.x.size() == 0)
            f = 0.0F;
        else
            f = ((SVG.Length)text.x.get(0)).floatValueX(this);
        if(text.y == null || text.y.size() == 0)
            f1 = 0.0F;
        else
            f1 = ((SVG.Length)text.y.get(0)).floatValueY(this);
        if(text.dx == null || text.dx.size() == 0)
            f2 = 0.0F;
        else
            f2 = ((SVG.Length)text.dx.get(0)).floatValueX(this);
        if(text.dy == null || text.dy.size() == 0)
            f3 = 0.0F;
        else
            f3 = ((SVG.Length)text.dy.get(0)).floatValueY(this);
        textanchor = getAnchorPosition();
        if(textanchor != SVG.Style.TextAnchor.Start)
        {
            float f4 = calculateTextWidth(text);
            TextBoundsCalculator textboundscalculator;
            boolean flag;
            if(textanchor == SVG.Style.TextAnchor.Middle)
                f -= f4 / 2.0F;
            else
                f -= f4;
        }
        if(text.boundingBox == null)
        {
            textboundscalculator = new TextBoundsCalculator(f, f1);
            enumerateTextSpans(text, textboundscalculator);
            text.boundingBox = new SVG.Box(textboundscalculator.bbox.left, textboundscalculator.bbox.top, textboundscalculator.bbox.width(), textboundscalculator.bbox.height());
        }
        updateParentBoundingBox(text);
        checkForGradiantsAndPatterns(text);
        checkForClipPath(text);
        flag = pushLayer();
        enumerateTextSpans(text, new PlainTextDrawer(f + f2, f1 + f3));
        if(flag)
            popLayer(text);
        if(true) goto _L1; else goto _L3
_L3:
    }

    private void render(SVG.Use use)
    {
        debug("Use render", new Object[0]);
        if((use.width == null || !use.width.isZero()) && (use.height == null || !use.height.isZero())) goto _L2; else goto _L1
_L1:
        return;
_L2:
        SVG.SvgObject svgobject;
        updateStyleForElement(state, use);
        if(!display())
            continue; /* Loop/switch isn't completed */
        svgobject = use.document.resolveIRI(use.href);
        if(svgobject == null)
        {
            Object aobj[] = new Object[1];
            aobj[0] = use.href;
            error("Use reference '%s' not found", aobj);
            continue; /* Loop/switch isn't completed */
        }
        if(use.transform != null)
            canvas.concat(use.transform);
        Matrix matrix = new Matrix();
        float f;
        float f1;
        boolean flag;
        SVG.Svg svg;
        SVG.Length length2;
        SVG.Length length3;
        if(use.x != null)
            f = use.x.floatValueX(this);
        else
            f = 0.0F;
        if(use.y != null)
            f1 = use.y.floatValueY(this);
        else
            f1 = 0.0F;
        matrix.preTranslate(f, f1);
        canvas.concat(matrix);
        checkForClipPath(use);
        flag = pushLayer();
        parentPush(use);
        if(!(svgobject instanceof SVG.Svg))
            break; /* Loop/switch isn't completed */
        statePush();
        svg = (SVG.Svg)svgobject;
        if(use.width != null)
            length2 = use.width;
        else
            length2 = svg.width;
        if(use.height != null)
            length3 = use.height;
        else
            length3 = svg.height;
        render(svg, length2, length3);
        statePop();
_L4:
        parentPop();
        if(flag)
            popLayer(use);
        updateParentBoundingBox(use);
        if(true) goto _L1; else goto _L3
_L3:
        if(svgobject instanceof SVG.Symbol)
        {
            SVG.Length length;
            SVG.Length length1;
            if(use.width != null)
                length = use.width;
            else
                length = new SVG.Length(100F, SVG.Unit.percent);
            if(use.height != null)
                length1 = use.height;
            else
                length1 = new SVG.Length(100F, SVG.Unit.percent);
            statePush();
            render((SVG.Symbol)svgobject, length, length1);
            statePop();
        } else
        {
            render(svgobject);
        }
          goto _L4
        if(true) goto _L1; else goto _L5
_L5:
    }

    private void renderChildren(SVG.SvgContainer svgcontainer, boolean flag)
    {
        if(flag)
            parentPush(svgcontainer);
        for(Iterator iterator = svgcontainer.getChildren().iterator(); iterator.hasNext(); render((SVG.SvgObject)iterator.next()));
        if(flag)
            parentPop();
    }

    private void renderMarker(SVG.Marker marker, MarkerVector markervector)
    {
        Matrix matrix;
        float f2;
        float f3;
        float f4;
        float f5;
        float f8;
        float f9;
        float f10;
        float f11;
        float f = 0.0F;
        statePush();
        float f1;
        boolean flag;
        if(marker.orient != null)
            if(Float.isNaN(marker.orient.floatValue()))
            {
                if(markervector.dx != 0.0F || markervector.dy != 0.0F)
                    f = (float)Math.toDegrees(Math.atan2(markervector.dy, markervector.dx));
            } else
            {
                f = marker.orient.floatValue();
            }
        if(marker.markerUnitsAreUser)
            f1 = 1.0F;
        else
            f1 = state.style.strokeWidth.floatValue(dpi);
        state = findInheritFromAncestorState(marker);
        matrix = new Matrix();
        matrix.preTranslate(markervector.x, markervector.y);
        matrix.preRotate(f);
        matrix.preScale(f1, f1);
        if(marker.refX != null)
            f2 = marker.refX.floatValueX(this);
        else
            f2 = 0.0F;
        if(marker.refY != null)
            f3 = marker.refY.floatValueY(this);
        else
            f3 = 0.0F;
        if(marker.markerWidth != null)
            f4 = marker.markerWidth.floatValueX(this);
        else
            f4 = 3F;
        if(marker.markerHeight != null)
            f5 = marker.markerHeight.floatValueY(this);
        else
            f5 = 3F;
        if(marker.viewBox == null) goto _L2; else goto _L1
_L1:
        float f6 = f4 / marker.viewBox.width;
        float f7 = f5 / marker.viewBox.height;
        PreserveAspectRatio preserveaspectratio;
        if(marker.preserveAspectRatio != null)
            preserveaspectratio = marker.preserveAspectRatio;
        else
            preserveaspectratio = PreserveAspectRatio.LETTERBOX;
        if(!preserveaspectratio.equals(PreserveAspectRatio.STRETCH))
        {
            float f12;
            if(preserveaspectratio.getScale() == PreserveAspectRatio.Scale.Slice)
                f12 = Math.max(f6, f7);
            else
                f12 = Math.min(f6, f7);
            f7 = f12;
            f6 = f12;
        }
        matrix.preTranslate(f6 * -f2, f7 * -f3);
        canvas.concat(matrix);
        f8 = f6 * marker.viewBox.width;
        f9 = f7 * marker.viewBox.height;
        f10 = 0.0F;
        f11 = 0.0F;
        _cls1..SwitchMap.com.caverock.androidsvg.PreserveAspectRatio.Alignment[preserveaspectratio.getAlignment().ordinal()];
        JVM INSTR tableswitch 1 6: default 380
    //                   1 596
    //                   2 596
    //                   3 596
    //                   4 610
    //                   5 610
    //                   6 610;
           goto _L3 _L4 _L4 _L4 _L5 _L5 _L5
_L3:
        _cls1..SwitchMap.com.caverock.androidsvg.PreserveAspectRatio.Alignment[preserveaspectratio.getAlignment().ordinal()];
        JVM INSTR tableswitch 2 8: default 436
    //                   2 622
    //                   3 636
    //                   4 436
    //                   5 622
    //                   6 636
    //                   7 622
    //                   8 636;
           goto _L6 _L7 _L8 _L6 _L7 _L8 _L7 _L8
_L6:
        if(!state.style.overflow.booleanValue())
            setClipRect(f10, f11, f4, f5);
        matrix.reset();
        matrix.preScale(f6, f7);
        canvas.concat(matrix);
_L9:
        flag = pushLayer();
        renderChildren(marker, false);
        if(flag)
            popLayer(marker);
        statePop();
        return;
_L4:
        f10 = 0.0F - (f4 - f8) / 2.0F;
          goto _L3
_L5:
        f10 = 0.0F - (f4 - f8);
          goto _L3
_L7:
        f11 = 0.0F - (f5 - f9) / 2.0F;
          goto _L6
_L8:
        f11 = 0.0F - (f5 - f9);
          goto _L6
_L2:
        matrix.preTranslate(-f2, -f3);
        canvas.concat(matrix);
        if(!state.style.overflow.booleanValue())
            setClipRect(0.0F, 0.0F, f4, f5);
          goto _L9
    }

    private void renderMarkers(SVG.GraphicsElement graphicselement)
    {
        if(state.style.markerStart != null || state.style.markerMid != null || state.style.markerEnd != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        SVG.Marker marker = null;
        SVG.Marker marker1 = null;
        SVG.Marker marker2 = null;
        List list;
        if(state.style.markerStart != null)
        {
            SVG.SvgObject svgobject2 = graphicselement.document.resolveIRI(state.style.markerStart);
            SVG.Style style;
            SVG.Style style1;
            int j;
            SVG.SvgObject svgobject;
            SVG.SvgObject svgobject1;
            if(svgobject2 != null)
            {
                marker = (SVG.Marker)svgobject2;
            } else
            {
                Object aobj2[] = new Object[1];
                aobj2[0] = state.style.markerStart;
                error("Marker reference '%s' not found", aobj2);
            }
        }
        if(state.style.markerMid != null)
        {
            svgobject1 = graphicselement.document.resolveIRI(state.style.markerMid);
            if(svgobject1 != null)
            {
                marker1 = (SVG.Marker)svgobject1;
            } else
            {
                Object aobj1[] = new Object[1];
                aobj1[0] = state.style.markerMid;
                error("Marker reference '%s' not found", aobj1);
            }
        }
        if(state.style.markerEnd != null)
        {
            svgobject = graphicselement.document.resolveIRI(state.style.markerEnd);
            if(svgobject != null)
            {
                marker2 = (SVG.Marker)svgobject;
            } else
            {
                Object aobj[] = new Object[1];
                aobj[0] = state.style.markerEnd;
                error("Marker reference '%s' not found", aobj);
            }
        }
        if(graphicselement instanceof SVG.Path)
            list = (new MarkerPositionCalculator(((SVG.Path)graphicselement).d)).getMarkers();
        else
        if(graphicselement instanceof SVG.Line)
            list = calculateMarkerPositions((SVG.Line)graphicselement);
        else
            list = calculateMarkerPositions((SVG.PolyLine)graphicselement);
        if(list != null)
        {
            int i = list.size();
            if(i != 0)
            {
                style = state.style;
                style1 = state.style;
                state.style.markerEnd = null;
                style1.markerMid = null;
                style.markerStart = null;
                if(marker != null)
                    renderMarker(marker, (MarkerVector)list.get(0));
                if(marker1 != null)
                    for(j = 1; j < i - 1; j++)
                        renderMarker(marker1, (MarkerVector)list.get(j));

                if(marker2 != null)
                    renderMarker(marker2, (MarkerVector)list.get(i - 1));
            }
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    private void renderMask(SVG.Mask mask, SVG.SvgElement svgelement)
    {
        debug("Mask render", new Object[0]);
        boolean flag;
        float f4;
        float f5;
        if(mask.maskUnitsAreUser != null && mask.maskUnitsAreUser.booleanValue())
            flag = true;
        else
            flag = false;
        if(flag)
        {
            if(mask.width != null)
                f4 = mask.width.floatValueX(this);
            else
                f4 = svgelement.boundingBox.width;
            if(mask.height != null)
                f5 = mask.height.floatValueY(this);
            else
                f5 = svgelement.boundingBox.height;
            if(mask.x != null)
                mask.x.floatValueX(this);
            else
            {
                float _tmp = (float)((double)svgelement.boundingBox.minX - 0.10000000000000001D * (double)svgelement.boundingBox.width);
            }
            if(mask.y != null)
                mask.y.floatValueY(this);
            else
            {
                float _tmp1 = (float)((double)svgelement.boundingBox.minY - 0.10000000000000001D * (double)svgelement.boundingBox.height);
            }
        } else
        {
            float f;
            float f1;
            float f2;
            float f3;
            if(mask.x != null)
                f = mask.x.floatValue(this, 1.0F);
            else
                f = -0.1F;
            if(mask.y != null)
                f1 = mask.y.floatValue(this, 1.0F);
            else
                f1 = -0.1F;
            if(mask.width != null)
                f2 = mask.width.floatValue(this, 1.0F);
            else
                f2 = 1.2F;
            if(mask.height != null)
                f3 = mask.height.floatValue(this, 1.0F);
            else
                f3 = 1.2F;
            float _tmp2 = svgelement.boundingBox.minX + f * svgelement.boundingBox.width;
            float _tmp3 = svgelement.boundingBox.minY + f1 * svgelement.boundingBox.height;
            f4 = f2 * svgelement.boundingBox.width;
            f5 = f3 * svgelement.boundingBox.height;
        }
        if(f4 != 0.0F && f5 != 0.0F)
        {
            statePush();
            state = findInheritFromAncestorState(mask);
            state.style.opacity = Float.valueOf(1.0F);
            boolean flag1;
            if(mask.maskContentUnitsAreUser == null || mask.maskContentUnitsAreUser.booleanValue())
                flag1 = true;
            else
                flag1 = false;
            if(!flag1)
            {
                canvas.translate(svgelement.boundingBox.minX, svgelement.boundingBox.minY);
                canvas.scale(svgelement.boundingBox.width, svgelement.boundingBox.height);
            }
            renderChildren(mask, false);
            statePop();
        }
    }

    private void renderSwitchChild(SVG.Switch switch1)
    {
        String s;
        SVGExternalFileResolver svgexternalfileresolver;
        Iterator iterator;
        s = Locale.getDefault().getLanguage();
        svgexternalfileresolver = document.getFileResolver();
        iterator = switch1.getChildren().iterator();
_L4:
        SVG.SvgObject svgobject;
        SVG.SvgConditional svgconditional;
        Set set2;
        Set set1;
        do
        {
            Set set;
            do
            {
                do
                {
                    do
                    {
                        if(!iterator.hasNext())
                            break MISSING_BLOCK_LABEL_309;
                        svgobject = (SVG.SvgObject)iterator.next();
                    } while(!(svgobject instanceof SVG.SvgConditional));
                    svgconditional = (SVG.SvgConditional)svgobject;
                } while(svgconditional.getRequiredExtensions() != null);
                set = svgconditional.getSystemLanguage();
            } while(set != null && (set.isEmpty() || !set.contains(s)));
            set1 = svgconditional.getRequiredFeatures();
        } while(set1 != null && (set1.isEmpty() || !SVGParser.supportedFeatures.containsAll(set1)));
        set2 = svgconditional.getRequiredFormats();
        if(set2 == null) goto _L2; else goto _L1
_L1:
        if(set2.isEmpty() || svgexternalfileresolver == null) goto _L4; else goto _L3
_L3:
        Iterator iterator2 = set2.iterator();
_L6:
        if(!iterator2.hasNext()) goto _L2; else goto _L5
_L5:
        if(svgexternalfileresolver.isFormatSupported((String)iterator2.next())) goto _L6; else goto _L4
_L2:
        Set set3 = svgconditional.getRequiredFonts();
        if(set3 == null) goto _L8; else goto _L7
_L7:
        if(set3.isEmpty() || svgexternalfileresolver == null) goto _L4; else goto _L9
_L9:
        Iterator iterator1 = set3.iterator();
_L11:
        if(!iterator1.hasNext()) goto _L8; else goto _L10
_L10:
        if(svgexternalfileresolver.resolveFont((String)iterator1.next(), state.style.fontWeight.intValue(), String.valueOf(state.style.fontStyle)) != null) goto _L11; else goto _L4
_L8:
        render(svgobject);
    }

    private void renderTextPath(SVG.TextPath textpath)
    {
        debug("TextPath render", new Object[0]);
        updateStyleForElement(state, textpath);
        break MISSING_BLOCK_LABEL_19;
        while(true) 
        {
            do
                return;
            while(!display() || !visible());
            SVG.SvgObject svgobject = textpath.document.resolveIRI(textpath.href);
            if(svgobject == null)
            {
                Object aobj[] = new Object[1];
                aobj[0] = textpath.href;
                error("TextPath reference '%s' not found", aobj);
            } else
            {
                SVG.Path path = (SVG.Path)svgobject;
                Path path1 = (new PathConverter(path.d)).getPath();
                if(path.transform != null)
                    path1.transform(path.transform);
                PathMeasure pathmeasure = new PathMeasure(path1, false);
                float f;
                SVG.Style.TextAnchor textanchor;
                if(textpath.startOffset != null)
                    f = textpath.startOffset.floatValue(this, pathmeasure.getLength());
                else
                    f = 0.0F;
                textanchor = getAnchorPosition();
                if(textanchor != SVG.Style.TextAnchor.Start)
                {
                    float f1 = calculateTextWidth(textpath);
                    boolean flag;
                    if(textanchor == SVG.Style.TextAnchor.Middle)
                        f -= f1 / 2.0F;
                    else
                        f -= f1;
                }
                checkForGradiantsAndPatterns((SVG.SvgElement)textpath.getTextRoot());
                flag = pushLayer();
                enumerateTextSpans(textpath, new PathTextDrawer(path1, f, 0.0F));
                if(flag)
                    popLayer(textpath);
            }
        }
    }

    private boolean requiresCompositing()
    {
        boolean flag = false;
        if(state.style.mask != null && !state.directRendering)
            warn("Masks are not supported when using getPicture()", new Object[0]);
        if(state.style.opacity.floatValue() < 1.0F || state.style.mask != null && state.directRendering)
            flag = true;
        return flag;
    }

    private void resetState()
    {
        state = new RendererState();
        stateStack = new Stack();
        updateStyle(state, SVG.Style.getDefaultStyle());
        state.viewPort = canvasViewPort;
        state.spacePreserve = false;
        state.directRendering = directRenderingMode;
        stateStack.push((RendererState)state.clone());
        canvasStack = new Stack();
        bitmapStack = new Stack();
        matrixStack = new Stack();
        parentStack = new Stack();
    }

    private void setClipRect(float f, float f1, float f2, float f3)
    {
        float f4 = f;
        float f5 = f1;
        float f6 = f + f2;
        float f7 = f1 + f3;
        if(state.style.clip != null)
        {
            f4 += state.style.clip.left.floatValueX(this);
            f5 += state.style.clip.top.floatValueY(this);
            f6 -= state.style.clip.right.floatValueX(this);
            f7 -= state.style.clip.bottom.floatValueY(this);
        }
        canvas.clipRect(f4, f5, f6, f7);
    }

    private void setPaintColour(RendererState rendererstate, boolean flag, SVG.SvgPaint svgpaint)
    {
        int i;
        Float float1;
        float f;
        if(flag)
            float1 = rendererstate.style.fillOpacity;
        else
            float1 = rendererstate.style.strokeOpacity;
        f = float1.floatValue();
        if(!(svgpaint instanceof SVG.Colour)) goto _L2; else goto _L1
_L1:
        i = ((SVG.Colour)svgpaint).colour;
_L5:
        int j = i | clamp255(f) << 24;
        if(flag)
            rendererstate.fillPaint.setColor(j);
        else
            rendererstate.strokePaint.setColor(j);
_L4:
        return;
_L2:
        if(!(svgpaint instanceof SVG.CurrentColor)) goto _L4; else goto _L3
_L3:
        i = rendererstate.style.color.colour;
          goto _L5
    }

    private void setSolidColor(boolean flag, SVG.SolidColor solidcolor)
    {
        boolean flag1 = true;
        if(!flag) goto _L2; else goto _L1
_L1:
        if(isSpecified(solidcolor.baseStyle, 0x80000000L))
        {
            state.style.fill = solidcolor.baseStyle.solidColor;
            RendererState rendererstate1 = state;
            if(solidcolor.baseStyle.solidColor == null)
                flag1 = false;
            rendererstate1.hasFill = flag1;
        }
        if(isSpecified(solidcolor.baseStyle, 0x100000000L))
            state.style.fillOpacity = solidcolor.baseStyle.solidOpacity;
        if(isSpecified(solidcolor.baseStyle, 0x180000000L))
            setPaintColour(state, flag, state.style.fill);
_L4:
        return;
_L2:
        if(isSpecified(solidcolor.baseStyle, 0x80000000L))
        {
            state.style.stroke = solidcolor.baseStyle.solidColor;
            RendererState rendererstate = state;
            if(solidcolor.baseStyle.solidColor == null)
                flag1 = false;
            rendererstate.hasStroke = flag1;
        }
        if(isSpecified(solidcolor.baseStyle, 0x100000000L))
            state.style.strokeOpacity = solidcolor.baseStyle.solidOpacity;
        if(isSpecified(solidcolor.baseStyle, 0x180000000L))
            setPaintColour(state, flag, state.style.stroke);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void statePop()
    {
        canvas.restore();
        state = (RendererState)stateStack.pop();
    }

    private void statePush()
    {
        canvas.save();
        stateStack.push(state);
        state = (RendererState)state.clone();
    }

    private String textXMLSpaceTransform(String s, boolean flag, boolean flag1)
    {
        String s2;
        if(state.spacePreserve)
        {
            s2 = s.replaceAll("[\\n\\t]", " ");
        } else
        {
            String s1 = s.replaceAll("\\n", "").replaceAll("\\t", " ");
            if(flag)
                s1 = s1.replaceAll("^\\s+", "");
            if(flag1)
                s1 = s1.replaceAll("\\s+$", "");
            s2 = s1.replaceAll("\\s{2,}", " ");
        }
        return s2;
    }

    private void updateParentBoundingBox(SVG.SvgElement svgelement)
    {
_L2:
        return;
        if(svgelement.parent == null || svgelement.boundingBox == null) goto _L2; else goto _L1
_L1:
        Matrix matrix = new Matrix();
        if(((Matrix)matrixStack.peek()).invert(matrix))
        {
            float af[] = new float[8];
            af[0] = svgelement.boundingBox.minX;
            af[1] = svgelement.boundingBox.minY;
            af[2] = svgelement.boundingBox.maxX();
            af[3] = svgelement.boundingBox.minY;
            af[4] = svgelement.boundingBox.maxX();
            af[5] = svgelement.boundingBox.maxY();
            af[6] = svgelement.boundingBox.minX;
            af[7] = svgelement.boundingBox.maxY();
            matrix.preConcat(canvas.getMatrix());
            matrix.mapPoints(af);
            RectF rectf = new RectF(af[0], af[1], af[0], af[1]);
            for(int i = 2; i <= 6; i += 2)
            {
                if(af[i] < rectf.left)
                    rectf.left = af[i];
                if(af[i] > rectf.right)
                    rectf.right = af[i];
                if(af[i + 1] < rectf.top)
                    rectf.top = af[i + 1];
                if(af[i + 1] > rectf.bottom)
                    rectf.bottom = af[i + 1];
            }

            SVG.SvgElement svgelement1 = (SVG.SvgElement)parentStack.peek();
            if(svgelement1.boundingBox == null)
                svgelement1.boundingBox = SVG.Box.fromLimits(rectf.left, rectf.top, rectf.right, rectf.bottom);
            else
                svgelement1.boundingBox.union(SVG.Box.fromLimits(rectf.left, rectf.top, rectf.right, rectf.bottom));
        }
        if(true) goto _L2; else goto _L3
_L3:
    }

    private void updateStyle(RendererState rendererstate, SVG.Style style)
    {
        if(isSpecified(style, 4096L))
            rendererstate.style.color = style.color;
        if(isSpecified(style, 2048L))
            rendererstate.style.opacity = style.opacity;
        if(isSpecified(style, 1L))
        {
            rendererstate.style.fill = style.fill;
            Paint paint;
            Paint paint1;
            Paint paint2;
            Paint paint3;
            Typeface typeface;
            SVGExternalFileResolver svgexternalfileresolver;
            Iterator iterator;
            String s;
            SVG.Style style2;
            float f;
            boolean flag5;
            if(style.fill != null)
                flag5 = true;
            else
                flag5 = false;
            rendererstate.hasFill = flag5;
        }
        if(isSpecified(style, 4L))
            rendererstate.style.fillOpacity = style.fillOpacity;
        if(isSpecified(style, 6149L))
            setPaintColour(rendererstate, true, rendererstate.style.fill);
        if(isSpecified(style, 2L))
            rendererstate.style.fillRule = style.fillRule;
        if(isSpecified(style, 8L))
        {
            rendererstate.style.stroke = style.stroke;
            boolean flag4;
            if(style.stroke != null)
                flag4 = true;
            else
                flag4 = false;
            rendererstate.hasStroke = flag4;
        }
        if(isSpecified(style, 16L))
            rendererstate.style.strokeOpacity = style.strokeOpacity;
        if(isSpecified(style, 6168L))
            setPaintColour(rendererstate, false, rendererstate.style.stroke);
        if(isSpecified(style, 0x800000000L))
            rendererstate.style.vectorEffect = style.vectorEffect;
        if(isSpecified(style, 32L))
        {
            rendererstate.style.strokeWidth = style.strokeWidth;
            rendererstate.strokePaint.setStrokeWidth(rendererstate.style.strokeWidth.floatValue(this));
        }
        if(!isSpecified(style, 64L)) goto _L2; else goto _L1
_L1:
        rendererstate.style.strokeLineCap = style.strokeLineCap;
        _cls1..SwitchMap.com.caverock.androidsvg.SVG.Style.LineCaps[style.strokeLineCap.ordinal()];
        JVM INSTR tableswitch 1 3: default 352
    //                   1 1377
    //                   2 1390
    //                   3 1403;
           goto _L2 _L3 _L4 _L5
_L2:
        if(!isSpecified(style, 128L)) goto _L7; else goto _L6
_L6:
        rendererstate.style.strokeLineJoin = style.strokeLineJoin;
        _cls1..SwitchMap.com.caverock.androidsvg.SVG.Style.LineJoin[style.strokeLineJoin.ordinal()];
        JVM INSTR tableswitch 1 3: default 412
    //                   1 1416
    //                   2 1429
    //                   3 1442;
           goto _L8 _L9 _L10 _L11
_L8:
        break; /* Loop/switch isn't completed */
_L11:
        break MISSING_BLOCK_LABEL_1442;
_L7:
        if(isSpecified(style, 256L))
        {
            rendererstate.style.strokeMiterLimit = style.strokeMiterLimit;
            rendererstate.strokePaint.setStrokeMiter(style.strokeMiterLimit.floatValue());
        }
        if(isSpecified(style, 512L))
            rendererstate.style.strokeDashArray = style.strokeDashArray;
        if(isSpecified(style, 1024L))
            rendererstate.style.strokeDashOffset = style.strokeDashOffset;
        if(isSpecified(style, 1536L))
            if(rendererstate.style.strokeDashArray == null)
            {
                rendererstate.strokePaint.setPathEffect(null);
            } else
            {
                float f1 = 0.0F;
                int i = rendererstate.style.strokeDashArray.length;
                int j;
                float af[];
                if(i % 2 == 0)
                    j = i;
                else
                    j = i * 2;
                af = new float[j];
                for(int k = 0; k < j; k++)
                {
                    af[k] = rendererstate.style.strokeDashArray[k % i].floatValue(this);
                    f1 += af[k];
                }

                if(f1 == 0.0F)
                {
                    rendererstate.strokePaint.setPathEffect(null);
                } else
                {
                    float f2 = rendererstate.style.strokeDashOffset.floatValue(this);
                    if(f2 < 0.0F)
                        f2 = f1 + f2 % f1;
                    rendererstate.strokePaint.setPathEffect(new DashPathEffect(af, f2));
                }
            }
        if(isSpecified(style, 16384L))
        {
            f = getCurrentFontSize();
            rendererstate.style.fontSize = style.fontSize;
            rendererstate.fillPaint.setTextSize(style.fontSize.floatValue(this, f));
            rendererstate.strokePaint.setTextSize(style.fontSize.floatValue(this, f));
        }
        if(isSpecified(style, 8192L))
            rendererstate.style.fontFamily = style.fontFamily;
        if(isSpecified(style, 32768L))
            if(style.fontWeight.intValue() == -1 && rendererstate.style.fontWeight.intValue() > 100)
            {
                style2 = rendererstate.style;
                style2.fontWeight = Integer.valueOf(-100 + style2.fontWeight.intValue());
            } else
            if(style.fontWeight.intValue() == 1 && rendererstate.style.fontWeight.intValue() < 900)
            {
                SVG.Style style1 = rendererstate.style;
                style1.fontWeight = Integer.valueOf(100 + style1.fontWeight.intValue());
            } else
            {
                rendererstate.style.fontWeight = style.fontWeight;
            }
        if(isSpecified(style, 0x10000L))
            rendererstate.style.fontStyle = style.fontStyle;
        if(isSpecified(style, 0x1a000L))
        {
            typeface = null;
            if(rendererstate.style.fontFamily != null && document != null)
            {
                svgexternalfileresolver = document.getFileResolver();
                iterator = rendererstate.style.fontFamily.iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    s = (String)iterator.next();
                    typeface = checkGenericFont(s, rendererstate.style.fontWeight, rendererstate.style.fontStyle);
                    if(typeface == null && svgexternalfileresolver != null)
                        typeface = svgexternalfileresolver.resolveFont(s, rendererstate.style.fontWeight.intValue(), String.valueOf(rendererstate.style.fontStyle));
                } while(typeface == null);
            }
            if(typeface == null)
                typeface = checkGenericFont("sans-serif", rendererstate.style.fontWeight, rendererstate.style.fontStyle);
            rendererstate.fillPaint.setTypeface(typeface);
            rendererstate.strokePaint.setTypeface(typeface);
        }
        if(isSpecified(style, 0x20000L))
        {
            rendererstate.style.textDecoration = style.textDecoration;
            paint = rendererstate.fillPaint;
            boolean flag;
            boolean flag1;
            if(style.textDecoration == SVG.Style.TextDecoration.LineThrough)
                flag = true;
            else
                flag = false;
            paint.setStrikeThruText(flag);
            paint1 = rendererstate.fillPaint;
            if(style.textDecoration == SVG.Style.TextDecoration.Underline)
                flag1 = true;
            else
                flag1 = false;
            paint1.setUnderlineText(flag1);
            if(android.os.Build.VERSION.SDK_INT >= 17)
            {
                paint2 = rendererstate.strokePaint;
                boolean flag2;
                boolean flag3;
                if(style.textDecoration == SVG.Style.TextDecoration.LineThrough)
                    flag2 = true;
                else
                    flag2 = false;
                paint2.setStrikeThruText(flag2);
                paint3 = rendererstate.strokePaint;
                if(style.textDecoration == SVG.Style.TextDecoration.Underline)
                    flag3 = true;
                else
                    flag3 = false;
                paint3.setUnderlineText(flag3);
            }
        }
        if(isSpecified(style, 0x1000000000L))
            rendererstate.style.direction = style.direction;
        if(isSpecified(style, 0x40000L))
            rendererstate.style.textAnchor = style.textAnchor;
        if(isSpecified(style, 0x80000L))
            rendererstate.style.overflow = style.overflow;
        if(isSpecified(style, 0x200000L))
            rendererstate.style.markerStart = style.markerStart;
        if(isSpecified(style, 0x400000L))
            rendererstate.style.markerMid = style.markerMid;
        if(isSpecified(style, 0x800000L))
            rendererstate.style.markerEnd = style.markerEnd;
        if(isSpecified(style, 0x1000000L))
            rendererstate.style.display = style.display;
        if(isSpecified(style, 0x2000000L))
            rendererstate.style.visibility = style.visibility;
        if(isSpecified(style, 0x100000L))
            rendererstate.style.clip = style.clip;
        if(isSpecified(style, 0x10000000L))
            rendererstate.style.clipPath = style.clipPath;
        if(isSpecified(style, 0x20000000L))
            rendererstate.style.clipRule = style.clipRule;
        if(isSpecified(style, 0x40000000L))
            rendererstate.style.mask = style.mask;
        if(isSpecified(style, 0x4000000L))
            rendererstate.style.stopColor = style.stopColor;
        if(isSpecified(style, 0x8000000L))
            rendererstate.style.stopOpacity = style.stopOpacity;
        if(isSpecified(style, 0x200000000L))
            rendererstate.style.viewportFill = style.viewportFill;
        if(isSpecified(style, 0x400000000L))
            rendererstate.style.viewportFillOpacity = style.viewportFillOpacity;
        return;
_L3:
        rendererstate.strokePaint.setStrokeCap(android.graphics.Paint.Cap.BUTT);
          goto _L2
_L4:
        rendererstate.strokePaint.setStrokeCap(android.graphics.Paint.Cap.ROUND);
          goto _L2
_L5:
        rendererstate.strokePaint.setStrokeCap(android.graphics.Paint.Cap.SQUARE);
          goto _L2
_L9:
        rendererstate.strokePaint.setStrokeJoin(android.graphics.Paint.Join.MITER);
          goto _L7
_L10:
        rendererstate.strokePaint.setStrokeJoin(android.graphics.Paint.Join.ROUND);
          goto _L7
        rendererstate.strokePaint.setStrokeJoin(android.graphics.Paint.Join.BEVEL);
          goto _L7
    }

    private void updateStyleForElement(RendererState rendererstate, SVG.SvgElementBase svgelementbase)
    {
        boolean flag;
        if(svgelementbase.parent == null)
            flag = true;
        else
            flag = false;
        rendererstate.style.resetNonInheritingProperties(flag);
        if(svgelementbase.baseStyle != null)
            updateStyle(rendererstate, svgelementbase.baseStyle);
        if(document.hasCSSRules())
        {
            Iterator iterator = document.getCSSRules().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                CSSParser.Rule rule = (CSSParser.Rule)iterator.next();
                if(CSSParser.ruleMatch(rule.selector, svgelementbase))
                    updateStyle(rendererstate, rule.style);
            } while(true);
        }
        if(svgelementbase.style != null)
            updateStyle(rendererstate, svgelementbase.style);
    }

    private void viewportFill()
    {
        if(!(state.style.viewportFill instanceof SVG.Colour)) goto _L2; else goto _L1
_L1:
        int i = ((SVG.Colour)state.style.viewportFill).colour;
_L6:
        if(state.style.viewportFillOpacity != null)
            i |= clamp255(state.style.viewportFillOpacity.floatValue()) << 24;
        canvas.drawColor(i);
_L4:
        return;
_L2:
        if(!(state.style.viewportFill instanceof SVG.CurrentColor)) goto _L4; else goto _L3
_L3:
        i = state.style.color.colour;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private boolean visible()
    {
        boolean flag;
        if(state.style.visibility != null)
            flag = state.style.visibility.booleanValue();
        else
            flag = true;
        return flag;
    }

    private static transient void warn(String s, Object aobj[])
    {
        Log.w("SVGAndroidRenderer", String.format(s, aobj));
    }

    protected float getCurrentFontSize()
    {
        return state.fillPaint.getTextSize();
    }

    protected float getCurrentFontXHeight()
    {
        return state.fillPaint.getTextSize() / 2.0F;
    }

    protected SVG.Box getCurrentViewPortInUserUnits()
    {
        SVG.Box box;
        if(state.viewBox != null)
            box = state.viewBox;
        else
            box = state.viewPort;
        return box;
    }

    protected float getDPI()
    {
        return dpi;
    }

    protected void renderDocument(SVG svg, SVG.Box box, PreserveAspectRatio preserveaspectratio, boolean flag)
    {
        document = svg;
        directRenderingMode = flag;
        SVG.Svg svg1 = svg.getRootElement();
        if(svg1 == null)
        {
            warn("Nothing to render. Document is empty.", new Object[0]);
        } else
        {
            resetState();
            checkXMLSpaceAttribute(svg1);
            SVG.Length length = svg1.width;
            SVG.Length length1 = svg1.height;
            SVG.Box box1;
            PreserveAspectRatio preserveaspectratio1;
            if(box != null)
                box1 = box;
            else
                box1 = svg1.viewBox;
            if(preserveaspectratio != null)
                preserveaspectratio1 = preserveaspectratio;
            else
                preserveaspectratio1 = svg1.preserveAspectRatio;
            render(svg1, length, length1, box1, preserveaspectratio1);
        }
    }

    private static final float BEZIER_ARC_FACTOR = 0.5522848F;
    private static final String DEFAULT_FONT_FAMILY = "sans-serif";
    private static final int LUMINANCE_FACTOR_SHIFT = 15;
    private static final int LUMINANCE_TO_ALPHA_BLUE = 2362;
    private static final int LUMINANCE_TO_ALPHA_GREEN = 23442;
    private static final int LUMINANCE_TO_ALPHA_RED = 6963;
    private static final String TAG = "SVGAndroidRenderer";
    private Stack bitmapStack;
    private Canvas canvas;
    private Stack canvasStack;
    private SVG.Box canvasViewPort;
    private boolean directRenderingMode;
    private SVG document;
    private float dpi;
    private Stack matrixStack;
    private Stack parentStack;
    private RendererState state;
    private Stack stateStack;







}
