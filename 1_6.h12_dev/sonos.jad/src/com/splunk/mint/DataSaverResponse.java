// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;


public class DataSaverResponse
{

    protected DataSaverResponse(String s, String s1)
    {
        data = s;
        filepath = s1;
    }

    public String getData()
    {
        return data;
    }

    public Exception getException()
    {
        return exception;
    }

    public String getFilepath()
    {
        return filepath;
    }

    public Boolean getSavedSuccessfully()
    {
        return savedSuccessfully;
    }

    protected void setException(Exception exception1)
    {
        exception = exception1;
    }

    protected void setSavedSuccessfully(Boolean boolean1)
    {
        savedSuccessfully = boolean1;
    }

    public String toString()
    {
        return (new StringBuilder()).append("DataSaverResponse [data=").append(data).append(", filepath=").append(filepath).append(", exception=").append(exception).append(", savedSuccessfully=").append(savedSuccessfully).append("]").toString();
    }

    private String data;
    private Exception exception;
    private String filepath;
    private Boolean savedSuccessfully;
}
