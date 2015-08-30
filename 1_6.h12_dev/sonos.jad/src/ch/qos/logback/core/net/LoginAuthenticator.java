// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class LoginAuthenticator extends Authenticator
{

    LoginAuthenticator(String s, String s1)
    {
        username = s;
        password = s1;
    }

    public PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(username, password);
    }

    String password;
    String username;
}
