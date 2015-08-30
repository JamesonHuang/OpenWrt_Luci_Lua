// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net;

import ch.qos.logback.core.*;
import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.boolex.EventEvaluator;
import ch.qos.logback.core.helpers.CyclicBuffer;
import ch.qos.logback.core.pattern.PatternLayoutBase;
import ch.qos.logback.core.sift.DefaultDiscriminator;
import ch.qos.logback.core.sift.Discriminator;
import ch.qos.logback.core.spi.CyclicBufferTracker;
import ch.qos.logback.core.util.ContentTypeUtil;
import ch.qos.logback.core.util.OptionHelper;
import java.util.*;
import java.util.concurrent.ExecutorService;
import javax.mail.*;
import javax.mail.internet.*;

// Referenced classes of package ch.qos.logback.core.net:
//            LoginAuthenticator

public abstract class SMTPAppenderBase extends AppenderBase
{
    class SenderRunnable
        implements Runnable
    {

        public void run()
        {
            sendBuffer(cyclicBuffer, e);
        }

        final CyclicBuffer cyclicBuffer;
        final Object e;
        final SMTPAppenderBase this$0;

        SenderRunnable(CyclicBuffer cyclicbuffer, Object obj)
        {
            this$0 = SMTPAppenderBase.this;
            super();
            cyclicBuffer = cyclicbuffer;
            e = obj;
        }
    }


    public SMTPAppenderBase()
    {
        lastTrackerStatusPrint = 0L;
        delayBetweenStatusMessages = 0x493e0;
        toPatternLayoutList = new ArrayList();
        subjectStr = null;
        smtpPort = 25;
        starttls = false;
        ssl = false;
        asynchronousSending = true;
        charsetEncoding = "UTF-8";
        discriminator = new DefaultDiscriminator();
        errorCount = 0;
    }

    private Session buildSessionFromProperties()
    {
        Properties properties;
        LoginAuthenticator loginauthenticator;
        properties = new Properties(OptionHelper.getSystemProperties());
        if(smtpHost != null)
            properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", Integer.toString(smtpPort));
        if(localhost != null)
            properties.put("mail.smtp.localhost", localhost);
        loginauthenticator = null;
        if(username != null)
        {
            loginauthenticator = new LoginAuthenticator(username, password);
            properties.put("mail.smtp.auth", "true");
        }
        if(!isSTARTTLS() || !isSSL()) goto _L2; else goto _L1
_L1:
        addError("Both SSL and StartTLS cannot be enabled simultaneously");
_L4:
        return Session.getInstance(properties, loginauthenticator);
_L2:
        if(isSTARTTLS())
            properties.put("mail.smtp.starttls.enable", "true");
        if(isSSL())
        {
            properties.put("mail.smtp.socketFactory.port", Integer.toString(smtpPort));
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.put("mail.smtp.socketFactory.fallback", "true");
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private List parseAddress(Object obj)
    {
        int i;
        ArrayList arraylist;
        int j;
        i = toPatternLayoutList.size();
        arraylist = new ArrayList();
        j = 0;
_L6:
        if(j >= i) goto _L2; else goto _L1
_L1:
        String s = ((PatternLayoutBase)toPatternLayoutList.get(j)).doLayout(obj);
        if(s != null && s.length() != 0)
            arraylist.addAll(Arrays.asList(InternetAddress.parse(s, true)));
          goto _L3
        AddressException addressexception;
        addressexception;
        ArrayList arraylist1;
        addError((new StringBuilder()).append("Could not parse email address for [").append(toPatternLayoutList.get(j)).append("] for event [").append(obj).append("]").toString(), addressexception);
        arraylist1 = arraylist;
_L4:
        return arraylist1;
_L2:
        arraylist1 = arraylist;
        if(true) goto _L4; else goto _L3
_L3:
        j++;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void addTo(String s)
    {
        if(s == null || s.length() == 0)
        {
            throw new IllegalArgumentException("Null or empty <to> property");
        } else
        {
            PatternLayoutBase patternlayoutbase = makeNewToPatternLayout(s.trim());
            patternlayoutbase.setContext(context);
            patternlayoutbase.start();
            toPatternLayoutList.add(patternlayoutbase);
            return;
        }
    }

    protected void append(Object obj)
    {
        if(checkEntryConditions()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        String s = discriminator.getDiscriminatingValue(obj);
        long l = System.currentTimeMillis();
        CyclicBuffer cyclicbuffer = (CyclicBuffer)cbTracker.getOrCreate(s, l);
        subAppend(cyclicbuffer, obj);
        CyclicBuffer cyclicbuffer1;
        try
        {
            if(eventEvaluator.evaluate(obj))
            {
                cyclicbuffer1 = new CyclicBuffer(cyclicbuffer);
                cyclicbuffer.clear();
                if(!asynchronousSending)
                    break; /* Loop/switch isn't completed */
                SenderRunnable senderrunnable = new SenderRunnable(cyclicbuffer1, obj);
                context.getExecutorService().execute(senderrunnable);
            }
        }
        catch(EvaluationException evaluationexception)
        {
            errorCount = 1 + errorCount;
            if(errorCount < 4)
                addError("SMTPAppender's EventEvaluator threw an Exception-", evaluationexception);
        }
        if(eventMarksEndOfLife(obj))
            cbTracker.endOfLife(s);
        cbTracker.removeStaleComponents(l);
        if(lastTrackerStatusPrint + (long)delayBetweenStatusMessages < l)
        {
            addInfo((new StringBuilder()).append("SMTPAppender [").append(name).append("] is tracking [").append(cbTracker.getComponentCount()).append("] buffers").toString());
            lastTrackerStatusPrint = l;
            if(delayBetweenStatusMessages < 0x493e0000)
                delayBetweenStatusMessages = 4 * delayBetweenStatusMessages;
        }
        if(true) goto _L1; else goto _L3
_L3:
        sendBuffer(cyclicbuffer1, obj);
        break MISSING_BLOCK_LABEL_109;
    }

    public boolean checkEntryConditions()
    {
        boolean flag = false;
        if(!started)
            addError((new StringBuilder()).append("Attempting to append to a non-started appender: ").append(getName()).toString());
        else
        if(mimeMsg == null)
            addError("Message object not configured.");
        else
        if(eventEvaluator == null)
            addError((new StringBuilder()).append("No EventEvaluator is set for appender [").append(name).append("].").toString());
        else
        if(layout == null)
            addError((new StringBuilder()).append("No layout set for appender named [").append(name).append("]. For more information, please visit http://logback.qos.ch/codes.html#smtp_no_layout").toString());
        else
            flag = true;
        return flag;
    }

    protected abstract boolean eventMarksEndOfLife(Object obj);

    protected abstract void fillBuffer(CyclicBuffer cyclicbuffer, StringBuffer stringbuffer);

    InternetAddress getAddress(String s)
    {
        InternetAddress internetaddress;
        try
        {
            internetaddress = new InternetAddress(s);
        }
        catch(AddressException addressexception)
        {
            addError((new StringBuilder()).append("Could not parse address [").append(s).append("].").toString(), addressexception);
            internetaddress = null;
        }
        return internetaddress;
    }

    public String getCharsetEncoding()
    {
        return charsetEncoding;
    }

    public CyclicBufferTracker getCyclicBufferTracker()
    {
        return cbTracker;
    }

    public Discriminator getDiscriminator()
    {
        return discriminator;
    }

    public String getFrom()
    {
        return from;
    }

    public Layout getLayout()
    {
        return layout;
    }

    public String getLocalhost()
    {
        return localhost;
    }

    public Message getMessage()
    {
        return mimeMsg;
    }

    public String getPassword()
    {
        return password;
    }

    public String getSMTPHost()
    {
        return getSmtpHost();
    }

    public int getSMTPPort()
    {
        return getSmtpPort();
    }

    public String getSmtpHost()
    {
        return smtpHost;
    }

    public int getSmtpPort()
    {
        return smtpPort;
    }

    public String getSubject()
    {
        return subjectStr;
    }

    public List getToAsListOfString()
    {
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = toPatternLayoutList.iterator(); iterator.hasNext(); arraylist.add(((PatternLayoutBase)iterator.next()).getPattern()));
        return arraylist;
    }

    public List getToList()
    {
        return toPatternLayoutList;
    }

    public String getUsername()
    {
        return username;
    }

    public boolean isAsynchronousSending()
    {
        return asynchronousSending;
    }

    public boolean isSSL()
    {
        return ssl;
    }

    public boolean isSTARTTLS()
    {
        return starttls;
    }

    protected abstract PatternLayoutBase makeNewToPatternLayout(String s);

    protected abstract Layout makeSubjectLayout(String s);

    protected void sendBuffer(CyclicBuffer cyclicbuffer, Object obj)
    {
        MimeBodyPart mimebodypart;
        StringBuffer stringbuffer;
        String s4;
        mimebodypart = new MimeBodyPart();
        stringbuffer = new StringBuffer();
        String s = layout.getFileHeader();
        if(s != null)
            stringbuffer.append(s);
        String s1 = layout.getPresentationHeader();
        if(s1 != null)
            stringbuffer.append(s1);
        fillBuffer(cyclicbuffer, stringbuffer);
        String s2 = layout.getPresentationFooter();
        if(s2 != null)
            stringbuffer.append(s2);
        String s3 = layout.getFileFooter();
        if(s3 != null)
            stringbuffer.append(s3);
        s4 = "Undefined subject";
        if(subjectLayout == null) goto _L2; else goto _L1
_L1:
        int i;
        s4 = subjectLayout.doLayout(obj);
        if(s4 == null)
            break MISSING_BLOCK_LABEL_409;
        i = s4.indexOf('\n');
_L11:
        if(i <= -1) goto _L2; else goto _L3
_L3:
        String s5 = s4.substring(0, i);
_L10:
        List list;
        mimeMsg.setSubject(s5, charsetEncoding);
        list = parseAddress(obj);
        if(!list.isEmpty()) goto _L5; else goto _L4
_L4:
        addInfo("Empty destination address. Aborting email transmission");
          goto _L6
_L5:
        InternetAddress ainternetaddress[];
        String s6;
        ainternetaddress = (InternetAddress[])list.toArray(EMPTY_IA_ARRAY);
        mimeMsg.setRecipients(javax.mail.Message.RecipientType.TO, ainternetaddress);
        s6 = layout.getContentType();
        if(!ContentTypeUtil.isTextual(s6)) goto _L8; else goto _L7
_L7:
        mimebodypart.setText(stringbuffer.toString(), charsetEncoding, ContentTypeUtil.getSubType(s6));
_L9:
        MimeMultipart mimemultipart = new MimeMultipart();
        mimemultipart.addBodyPart(mimebodypart);
        mimeMsg.setContent(mimemultipart);
        mimeMsg.setSentDate(new Date());
        addInfo((new StringBuilder()).append("About to send out SMTP message \"").append(s5).append("\" to ").append(Arrays.toString(ainternetaddress)).toString());
        Transport.send(mimeMsg);
          goto _L6
        Exception exception;
        exception;
        addError("Error occurred while sending e-mail notification.", exception);
          goto _L6
_L8:
        mimebodypart.setContent(stringbuffer.toString(), layout.getContentType());
          goto _L9
_L2:
        s5 = s4;
          goto _L10
_L6:
        return;
        i = -1;
          goto _L11
    }

    public void setAsynchronousSending(boolean flag)
    {
        asynchronousSending = flag;
    }

    public void setCharsetEncoding(String s)
    {
        charsetEncoding = s;
    }

    public void setCyclicBufferTracker(CyclicBufferTracker cyclicbuffertracker)
    {
        cbTracker = cyclicbuffertracker;
    }

    public void setDiscriminator(Discriminator discriminator1)
    {
        discriminator = discriminator1;
    }

    public void setEvaluator(EventEvaluator eventevaluator)
    {
        eventEvaluator = eventevaluator;
    }

    public void setFrom(String s)
    {
        from = s;
    }

    public void setLayout(Layout layout1)
    {
        layout = layout1;
    }

    public void setLocalhost(String s)
    {
        localhost = s;
    }

    public void setMessage(MimeMessage mimemessage)
    {
        mimeMsg = mimemessage;
    }

    public void setPassword(String s)
    {
        password = s;
    }

    public void setSMTPHost(String s)
    {
        setSmtpHost(s);
    }

    public void setSMTPPort(int i)
    {
        setSmtpPort(i);
    }

    public void setSSL(boolean flag)
    {
        ssl = flag;
    }

    public void setSTARTTLS(boolean flag)
    {
        starttls = flag;
    }

    public void setSmtpHost(String s)
    {
        smtpHost = s;
    }

    public void setSmtpPort(int i)
    {
        smtpPort = i;
    }

    public void setSubject(String s)
    {
        subjectStr = s;
    }

    public void setUsername(String s)
    {
        username = s;
    }

    public void start()
    {
        Session session;
        if(cbTracker == null)
            cbTracker = new CyclicBufferTracker();
        session = buildSessionFromProperties();
        if(session != null) goto _L2; else goto _L1
_L1:
        addError("Failed to obtain javax.mail.Session. Cannot start.");
_L3:
        return;
_L2:
        mimeMsg = new MimeMessage(session);
        if(from == null)
            break MISSING_BLOCK_LABEL_101;
        mimeMsg.setFrom(getAddress(from));
_L4:
        subjectLayout = makeSubjectLayout(subjectStr);
        started = true;
          goto _L3
        MessagingException messagingexception;
        messagingexception;
        addError("Could not activate SMTPAppender options.", messagingexception);
          goto _L3
        mimeMsg.setFrom();
          goto _L4
    }

    /**
     * @deprecated Method stop is deprecated
     */

    public void stop()
    {
        this;
        JVM INSTR monitorenter ;
        started = false;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected abstract void subAppend(CyclicBuffer cyclicbuffer, Object obj);

    static InternetAddress EMPTY_IA_ARRAY[] = new InternetAddress[0];
    static final int MAX_DELAY_BETWEEN_STATUS_MESSAGES = 0x493e0000;
    boolean asynchronousSending;
    protected CyclicBufferTracker cbTracker;
    private String charsetEncoding;
    int delayBetweenStatusMessages;
    protected Discriminator discriminator;
    private int errorCount;
    protected EventEvaluator eventEvaluator;
    private String from;
    long lastTrackerStatusPrint;
    protected Layout layout;
    String localhost;
    protected MimeMessage mimeMsg;
    String password;
    private String smtpHost;
    private int smtpPort;
    private boolean ssl;
    private boolean starttls;
    protected Layout subjectLayout;
    private String subjectStr;
    private List toPatternLayoutList;
    String username;

}
