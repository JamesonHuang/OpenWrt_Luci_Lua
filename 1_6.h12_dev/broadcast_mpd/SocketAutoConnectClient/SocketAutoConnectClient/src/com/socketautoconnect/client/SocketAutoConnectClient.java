package com.socketautoconnect.client;

import android.app.Activity;
import android.os.Bundle;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket; 
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

public class SocketAutoConnectClient extends Activity implements Runnable 
{ 
	private MulticastSocket multicastSocket=null;  
	private static int BROADCAST_PORT=9898;  
    private static String BROADCAST_IP="224.0.0.1";    
    InetAddress inetAddress=null;   
    Thread thread=null;
    TextView ipInfo;
    private static String ip;
     Handler  myHandler=new Handler() 
     {
 		@Override 
 		public void handleMessage(Message msg)
 		{
 			super.handleMessage(msg);
 			if(msg.what==1)
 			{ 
 	             ipInfo.append(msg.obj.toString());
 			}
 		}
     };
    /** Called when the activity is first created. */
    @Override  
    public void onCreate(Bundle savedInstanceState) 
    { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.main);
        ipInfo=(TextView) findViewById(R.id.ip_info);
        thread=new Thread(this); 
        try    
        {     
        	 multicastSocket = new MulticastSocket(BROADCAST_PORT);  
             inetAddress=InetAddress.getByName(BROADCAST_IP); 
             multicastSocket.joinGroup(inetAddress);  
             thread.start();

        } catch (Exception e1)   
        {     
            e1.printStackTrace();  
        } 
        
    }         
  
	@Override 
	public void run( )    
	{   
		// TODO Auto-generated method stub
		Message msg=new Message();
		msg.what=1;
		byte buf[] = new byte[1024];  
        DatagramPacket dp =null;    
        dp=new DatagramPacket(buf,buf.length,inetAddress,BROADCAST_PORT); 
		 
	        while (true)     
	        {      
	            try                 
	            {             
	                multicastSocket.receive(dp);
	                Thread.sleep(3000);
	                ip=new String(buf, 0, dp.getLength());
	                msg.obj=ip;     
	                myHandler.sendMessage(msg);
	                System.out.println("检测到服务端IP : "+ip);    
	              //  Toast.makeText(this, new String(buf, 0, dp.getLength()), Toast.LENGTH_SHORT);
	            } catch (Exception e)    
	            {      
	                e.printStackTrace();          
	            }    
	        }    
	} 

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		multicastSocket.close();     
		System.out.println("UDP Client程序退出,关掉socket,停止广播");
		finish();    
	}
	
	
	
	
}