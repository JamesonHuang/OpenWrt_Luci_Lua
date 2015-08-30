package com.talent.allshare.network;

import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.util.CommonLog;
import org.cybergarage.util.LogFactory;

import com.talent.allshare.util.CommonUtil;

import android.content.Context;
import android.os.Process;



public class ControlCenterRunnable implements Runnable{

	private static final CommonLog log = LogFactory.createLog();
	
	private static final int REFRESH_DEVICES_INTERVAL = 30 * 1000; 
	
	public static interface ISearchDeviceListener{
		public void onSearchComplete(boolean searchSuccess);
	}
	
	private ControlPoint mCP = null;
	private Context mContext = null;
	private boolean mStartComplete = false;
	private boolean mIsExit = false;
	private ISearchDeviceListener mSearchDeviceListener;
	
	public ControlCenterRunnable(Context context, ControlPoint controlPoint){
		mContext = context;
		mCP = controlPoint; 
	}
	
	public void  setCompleteFlag(boolean flag){
		mStartComplete = flag;
	}
	
	public void setSearchListener(ISearchDeviceListener listener){
		mSearchDeviceListener = listener;
	}
	
	public void notifyThread(){
		synchronized (this) {
			notifyAll();
		}
	}
	
	public void reset(){
		setCompleteFlag(false);
		notifyThread();
	}
	
	public void exit(){
		mIsExit = true;
		notifyThread();
	}
	
	@Override
	public void run() {
		log.e("ControlCenterRunnable run...");		
		Process.setThreadPriority(Process.THREAD_PRIORITY_LOWEST);	
		
		while(true)
		{
			synchronized(this)
			{
				if (mIsExit){
					break;
				}
				
				refreshDevices();
				try
				{
					wait(REFRESH_DEVICES_INTERVAL);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}	
				
				if (mIsExit){
					break;
				}
			}
		}
		
		log.e("ControlCenterRunnable over...");		
	}

	private void refreshDevices(){
		log.e("refreshDevices...");
		if (!CommonUtil.checkNetState(mContext)){
			return ;
		}

		try {
			if (mStartComplete){
				boolean searchRet = mCP.search();
				log.e("mCP.search() ret = "  + searchRet);
				if (mSearchDeviceListener != null){
					mSearchDeviceListener.onSearchComplete(searchRet);
				}
			}else{
				boolean startRet = mCP.start();
				log.e("mCP.start() ret = "  + startRet);
				if (startRet){
					mStartComplete = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
