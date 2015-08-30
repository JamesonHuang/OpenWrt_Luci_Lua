// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.graphics.Rect;
import android.text.Layout;
import android.widget.TextView;
import java.io.StringWriter;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

// Referenced classes of package com.sonos.acr.util:
//            SLog, I18nTextViewWrapper

class TextViewXMLLogger
{

    TextViewXMLLogger()
    {
        doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        automation = doc.createElement("automation");
        truncation = doc.createElement("truncation");
        automation.appendChild(truncation);
        doc.appendChild(automation);
_L1:
        return;
        Exception exception;
        exception;
        SLog.e(LOG_TAG, "Exception when trying to initialize log info:", exception);
        exception.printStackTrace();
          goto _L1
    }

    public String getXmlData()
    {
        String s = null;
        String s1;
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty("omit-xml-declaration", "no");
        transformer.setOutputProperty("indent", "yes");
        StringWriter stringwriter = new StringWriter();
        StreamResult streamresult = new StreamResult(stringwriter);
        transformer.transform(new DOMSource(doc), streamresult);
        s1 = stringwriter.toString();
        s = s1;
_L2:
        return s;
        Exception exception;
        exception;
        SLog.e(LOG_TAG, "Exception when trying to get the xml data:", exception);
        if(true) goto _L2; else goto _L1
_L1:
    }

    /**
     * @deprecated Method log is deprecated
     */

    void log(I18nTextViewWrapper i18ntextviewwrapper)
    {
        this;
        JVM INSTR monitorenter ;
        String s;
        Layout layout;
        s = String.valueOf(i18ntextviewwrapper.view.getText());
        layout = i18ntextviewwrapper.view.getLayout();
        if(s == null || i18ntextviewwrapper.textFits) goto _L2; else goto _L1
_L1:
        Element element;
        String s1;
        element = doc.createElement("error");
        element.setAttribute("reason", "clipped");
        element.setAttribute("time", (new StringBuilder()).append("").append((new Date()).toLocaleString()).toString());
        element.setAttribute("activity", (new StringBuilder()).append("").append(i18ntextviewwrapper.activityName).toString());
        element.setAttribute("resourceId", (new StringBuilder()).append("").append(i18ntextviewwrapper.resId).toString());
        if(layout == null)
            break MISSING_BLOCK_LABEL_485;
        s1 = (new StringBuilder()).append("(").append(layout.getWidth()).append(",").append(layout.getHeight()).append(")").toString();
_L3:
        element.setAttribute("layoutSize", s1);
        element.setAttribute("textPaintSize", (new StringBuilder()).append("(").append(i18ntextviewwrapper.textRect.width()).append(",").append(i18ntextviewwrapper.textRect.height()).append(")").toString());
        element.setAttribute("textViewSize", (new StringBuilder()).append("(").append(i18ntextviewwrapper.view.getWidth()).append(",").append(i18ntextviewwrapper.view.getHeight()).append(")").toString());
        element.setAttribute("textViewVisibleSize", (new StringBuilder()).append("(").append(i18ntextviewwrapper.visibleRect.width()).append(",").append(i18ntextviewwrapper.visibleRect.height()).append(")").toString());
        element.setAttribute("type", (new StringBuilder()).append("").append(i18ntextviewwrapper.typeName).toString());
        element.appendChild(doc.createCDATASection(s));
        Element element1 = doc.createElement("context");
        element1.appendChild(doc.createCDATASection(i18ntextviewwrapper.buildHeiarchyString()));
        element.appendChild(element1);
        truncation.appendChild(element);
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        s1 = "(null)";
          goto _L3
        Exception exception;
        exception;
        throw exception;
    }

    private static final String LOG_TAG = com/sonos/acr/util/TextViewXMLLogger.getSimpleName();
    private Element automation;
    private Document doc;
    private Element truncation;

}
