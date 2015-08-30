// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net;

import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.Layout;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
import java.net.UnknownHostException;

// Referenced classes of package ch.qos.logback.core.net:
//            SyslogOutputStream

public abstract class SyslogAppenderBase extends AppenderBase
{

    public SyslogAppenderBase()
    {
        port = 514;
        initialized = false;
        lazyInit = false;
    }

    private boolean connect()
    {
        int i;
        sos = new SyslogOutputStream(syslogHost, port);
        i = sos.getSendBufferSize();
        if(maxMessageSize != 0) goto _L2; else goto _L1
_L1:
        maxMessageSize = Math.min(i, 65000);
        addInfo((new StringBuilder()).append("Defaulting maxMessageSize to [").append(maxMessageSize).append("]").toString());
_L4:
        SocketException socketexception;
        boolean flag;
        UnknownHostException unknownhostexception;
        if(sos != null)
            flag = true;
        else
            flag = false;
        return flag;
_L2:
        if(maxMessageSize <= i) goto _L4; else goto _L3
_L3:
        addWarn((new StringBuilder()).append("maxMessageSize of [").append(maxMessageSize).append("] is larger than the system defined datagram size of [").append(i).append("].").toString());
        addWarn("This may result in dropped logs.");
          goto _L4
        unknownhostexception;
        addError("Could not create SyslogWriter", unknownhostexception);
          goto _L4
        socketexception;
        addWarn("Failed to bind to a random datagram socket. Will try to reconnect later.", socketexception);
          goto _L4
    }

    public static int facilityStringToint(String s)
    {
        int i;
        if("KERN".equalsIgnoreCase(s))
            i = 0;
        else
        if("USER".equalsIgnoreCase(s))
            i = 8;
        else
        if("MAIL".equalsIgnoreCase(s))
            i = 16;
        else
        if("DAEMON".equalsIgnoreCase(s))
            i = 24;
        else
        if("AUTH".equalsIgnoreCase(s))
            i = 32;
        else
        if("SYSLOG".equalsIgnoreCase(s))
            i = 40;
        else
        if("LPR".equalsIgnoreCase(s))
            i = 48;
        else
        if("NEWS".equalsIgnoreCase(s))
            i = 56;
        else
        if("UUCP".equalsIgnoreCase(s))
            i = 64;
        else
        if("CRON".equalsIgnoreCase(s))
            i = 72;
        else
        if("AUTHPRIV".equalsIgnoreCase(s))
            i = 80;
        else
        if("FTP".equalsIgnoreCase(s))
            i = 88;
        else
        if("NTP".equalsIgnoreCase(s))
            i = 96;
        else
        if("AUDIT".equalsIgnoreCase(s))
            i = 104;
        else
        if("ALERT".equalsIgnoreCase(s))
            i = 112;
        else
        if("CLOCK".equalsIgnoreCase(s))
            i = 120;
        else
        if("LOCAL0".equalsIgnoreCase(s))
            i = 128;
        else
        if("LOCAL1".equalsIgnoreCase(s))
            i = 136;
        else
        if("LOCAL2".equalsIgnoreCase(s))
            i = 144;
        else
        if("LOCAL3".equalsIgnoreCase(s))
            i = 152;
        else
        if("LOCAL4".equalsIgnoreCase(s))
            i = 160;
        else
        if("LOCAL5".equalsIgnoreCase(s))
            i = 168;
        else
        if("LOCAL6".equalsIgnoreCase(s))
            i = 176;
        else
        if("LOCAL7".equalsIgnoreCase(s))
            i = 184;
        else
            throw new IllegalArgumentException((new StringBuilder()).append(s).append(" is not a valid syslog facility string").toString());
        return i;
    }

    protected void append(Object obj)
    {
        if(isStarted()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(!initialized && lazyInit)
        {
            initialized = true;
            connect();
        }
        if(sos != null)
            try
            {
                String s = layout.doLayout(obj);
                if(s != null)
                {
                    if(s.length() > maxMessageSize)
                        s = s.substring(0, maxMessageSize);
                    sos.write(s.getBytes());
                    sos.flush();
                    postProcess(obj, sos);
                }
            }
            catch(IOException ioexception)
            {
                addError((new StringBuilder()).append("Failed to send diagram to ").append(syslogHost).toString(), ioexception);
            }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public abstract Layout buildLayout();

    public String getFacility()
    {
        return facilityStr;
    }

    public Layout getLayout()
    {
        return layout;
    }

    public boolean getLazy()
    {
        return lazyInit;
    }

    public int getMaxMessageSize()
    {
        return maxMessageSize;
    }

    public int getPort()
    {
        return port;
    }

    public abstract int getSeverityForEvent(Object obj);

    public String getSuffixPattern()
    {
        return suffixPattern;
    }

    public String getSyslogHost()
    {
        return syslogHost;
    }

    protected void postProcess(Object obj, OutputStream outputstream)
    {
    }

    public void setFacility(String s)
    {
        if(s != null)
            s = s.trim();
        facilityStr = s;
    }

    public void setLayout(Layout layout1)
    {
        addWarn("The layout of a SyslogAppender cannot be set directly. See also http://logback.qos.ch/codes.html#syslog_layout");
    }

    public void setLazy(boolean flag)
    {
        lazyInit = flag;
    }

    public void setMaxMessageSize(int i)
    {
        maxMessageSize = i;
    }

    public void setPort(int i)
    {
        port = i;
    }

    public void setSuffixPattern(String s)
    {
        suffixPattern = s;
    }

    public void setSyslogHost(String s)
    {
        syslogHost = s;
    }

    public void start()
    {
        int i = 0;
        if(facilityStr == null)
        {
            addError("The Facility option is mandatory");
            i = 1;
        }
        if(!lazyInit && !connect())
            i++;
        if(layout == null)
            layout = buildLayout();
        if(i == 0)
            super.start();
    }

    public void stop()
    {
        sos.close();
        super.stop();
    }

    static final int MAX_MESSAGE_SIZE_LIMIT = 65000;
    static final String SYSLOG_LAYOUT_URL = "http://logback.qos.ch/codes.html#syslog_layout";
    String facilityStr;
    boolean initialized;
    Layout layout;
    private boolean lazyInit;
    int maxMessageSize;
    int port;
    protected SyslogOutputStream sos;
    protected String suffixPattern;
    String syslogHost;
}
