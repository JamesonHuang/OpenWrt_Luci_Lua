/**
 * @auth  geniuseoe2012
 * @blog http://blog.csdn.net/geniuseoe2012
 * @date 2013-1-6
 * @description nothing to say
*/
package com.talent.allshare.service;

import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.device.DeviceChangeListener;
import org.cybergarage.upnp.device.SearchResponseListener;
import org.cybergarage.upnp.ssdp.SSDPPacket;
import org.cybergarage.util.CommonLog;
import org.cybergarage.util.LogFactory;

import com.talent.allshare.network.ControlCenterRunnable;
import com.talent.allshare.network.UpnpUtil;
import com.talent.allshare.proxy.AllShareProxy;
import com.talent.allshare.proxy.DeviceBrocastFactory;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DlnaService extends Service implements DeviceChangeListener, ControlCenterRunnable.ISearchDeviceListener{

	private static final CommonLog log = LogFactory.createLog();
	
	public static final String SEARCH_DEVICES = "com.talent.allshare.search_device";
	public static final String RESET_SEARCH_DEVICES = "com.talent.allshare.reset_search_device";
	
	
	private  ControlPoint mControlPoint;
	private  Thread mSearchDeviceThread = null;
	private  ControlCenterRunnable mControlCenterRunnable = null;
	private  AllShareProxy mAllShareProxy;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		log.e("DlnaService onCreate");
		init();
	}
	
	
	

	@Override
	public void onStart(Intent intent, int startId) {
		log.e("DlnaService onStart");
		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		if (intent != null && intent.getAction() != null){
			String action = intent.getAction();
			if (DlnaService.SEARCH_DEVICES.equals(action)) {
				search();
			}else if (DlnaService.RESET_SEARCH_DEVICES.equals(action)){
				reset();
			}
		}else{
			log.e("intent = " + intent);
		}

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		log.e("DlnaService onDestroy");
		unInit();
		super.onDestroy();
	}
	
	
	private void init(){
		mControlPoint = new ControlPoint();
		mControlPoint.addDeviceChangeListener(this);
		mControlPoint.addSearchResponseListener(new SearchResponseListener() {		
			public void deviceSearchResponseReceived(SSDPPacket ssdpPacket) {
			}
		});
	
		mControlCenterRunnable = new ControlCenterRunnable(this, mControlPoint);
		mControlCenterRunnable.setSearchListener(this);
		
		mAllShareProxy = AllShareProxy.getInstance(this);
		
	}
	
	private void unInit(){
		mControlCenterRunnable.setSearchListener(null);
		mControlCenterRunnable.exit();
		mControlPoint.stop();
	}

	private void search(){
		
		if (mSearchDeviceThread == null){
			mSearchDeviceThread = new Thread(mControlCenterRunnable);
			mSearchDeviceThread.start();
		}else{
			mControlCenterRunnable.notifyThread();
		}
	}
	
	private void reset(){
		mControlCenterRunnable.reset();
	}
	
	
	@Override
	public void deviceAdded(Device dev) {
		if (UpnpUtil.isValidDevice(dev)){
			mAllShareProxy.addDevice(dev);
		}	
	}

	@Override
	public void deviceRemoved(Device dev) {
		if (UpnpUtil.isValidDevice(dev)){
			mAllShareProxy.removeDevice(dev);
		}		
	}

	@Override
	public void onSearchComplete(boolean searchSuccess) {

		if (!searchSuccess){
			DeviceBrocastFactory.sendSearchDeviceFailBrocast(this);
		}
	}
	
	

}
