package com.socketautoconnect.server;

import java.net.DatagramPacket; 
import java.net.InetAddress; 
import java.net.MulticastSocket;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle; 
import android.util.Log;
import android.view.View;    
import android.view.View.OnClickListener; 
import android.widget.Button;
import android.widget.TextView;
import java.lang.*;
   

public class SocketAutoConnectServer extends Activity implements Runnable
{
	private static String ip; //服务端ip   
	private static int BROADCAST_PORT=9898;
	private static String BROADCAST_IP="224.0.0.1";   
	InetAddress inetAddress=null; 
	Thread t=null;   
	/*发送广播端的socket*/  
    MulticastSocket multicastSocket=null;   
    /*发送广播的按钮*/   
    private Button sendUDPBrocast; 
	private volatile boolean isRuning= true;
	TextView ipInfo;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ipInfo=(TextView) findViewById(R.id.ip_info);
        sendUDPBrocast=(Button) findViewById(R.id.sendUDPBrocast);
        sendUDPBrocast.setOnClickListener(new SendUDPBrocastListener());   
        //Wifi状态判断  
        WifiManager wifiManager=(WifiManager) getSystemService (Context.WIFI_SERVICE); 
        if(wifiManager.isWifiEnabled())
        {
        	WifiInfo wifiInfo=wifiManager.getConnectionInfo();
        	ip=getIpString(wifiInfo.getIpAddress());
        	ipInfo.append(ip);
        	System.out.println("服务端的wifi IP:"+ip);   
        }
        try 
        {
        	inetAddress=InetAddress.getByName(BROADCAST_IP);
        	multicastSocket=new MulticastSocket(BROADCAST_PORT);
        	multicastSocket.setTimeToLive(1);
        	multicastSocket.joinGroup(inetAddress);
        	
        }catch(Exception e)
        {
        	e.printStackTrace();
        	
        }
         t=new Thread(this); 
         t.start(); 
       
    }
    
  //将获取到的int型ip转成string类型
    private String getIpString(int i)
	{  
		return (i & 0xFF) + "." +((i >> 8) & 0xFF) + "."
	           +((i >> 16) & 0xFF) + "." +(i >> 24 & 0xFF);
	} 
    
    
    class SendUDPBrocastListener implements OnClickListener
    {  
        
		@Override
		public void onClick(View v)
		{
			if(isRuning) 
			{
				isRuning=false;
				sendUDPBrocast.setText("发送广播");
				System.out.println("现在停止广播..");
				 
			}else
			{  
				isRuning=true;
				sendUDPBrocast.setText("停止广播");
				System.out.println("现在发送广播..");
			}
		}  
    }
     
	@Override
	public void run()  
	{ 

    	//发送的数据包，局网内的所有地址都可以收到该数据包  
        DatagramPacket dataPacket = null;          
        //将本机的IP（这里可以写动态获取的IP）地址放到数据包里，其实server端接收到数据包后也能获取到发包方的IP的  
        byte[] data =ip.getBytes();   
        dataPacket = new DatagramPacket(data, data.length, inetAddress,BROADCAST_PORT);  
		while(true)  
		{
			if(isRuning) 
			{
				try  
		        {  
		           multicastSocket.send(dataPacket); 
		           Thread.sleep(3000);  
		           System.out.println("再次发送ip地址广播:.....");  
		        } catch (Exception e)              
		        {    
		            e.printStackTrace();     
		        } 
			}
		} 
	}
	
 
	@Override
	protected void onDestroy()  
	{
		super.onDestroy(); 
		isRuning=false;
		multicastSocket.close();     
		System.out.println("UDP Server程序退出,关掉socket,停止广播");
		finish(); 
	}  

}