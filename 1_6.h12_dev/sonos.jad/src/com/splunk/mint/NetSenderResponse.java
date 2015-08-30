// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;


public class NetSenderResponse
{

    protected NetSenderResponse(String s, String s1)
    {
        url = s;
        data = s1;
        sentSuccessfully = Boolean.valueOf(false);
    }

    public String getData()
    {
        return data;
    }

    public Exception getException()
    {
        return exception;
    }

    public int getResponseCode()
    {
        return responseCode;
    }

    public Boolean getSentSuccessfully()
    {
        return sentSuccessfully;
    }

    public String getServerResponse()
    {
        return serverResponse;
    }

    public String getUrl()
    {
        return url;
    }

    protected void setData(String s)
    {
        data = s;
    }

    protected void setException(Exception exception1)
    {
        exception = exception1;
    }

    protected void setResponseCode(int i)
    {
        responseCode = i;
    }

    protected void setSentSuccessfully(Boolean boolean1)
    {
        sentSuccessfully = boolean1;
    }

    protected void setServerResponse(String s)
    {
        serverResponse = s;
    }

    public String toString()
    {
        return (new StringBuilder()).append("NetSenderResponse [exception=").append(exception).append(", sendSuccessfully=").append(sentSuccessfully).append(", serverResponse=").append(serverResponse).append(", data=").append(data).append(", url=").append(url).append(", responseCode=").append(responseCode).append("]").toString();
    }

    private String data;
    private Exception exception;
    private int responseCode;
    private Boolean sentSuccessfully;
    private String serverResponse;
    private String url;
}
