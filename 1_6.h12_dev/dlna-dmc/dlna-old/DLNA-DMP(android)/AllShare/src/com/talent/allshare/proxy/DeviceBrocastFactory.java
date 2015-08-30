package com.talent.allshare.proxy;

import org.cybergarage.util.CommonLog;
import org.cybergarage.util.LogFactory;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class DeviceBrocastFactory {
	private static final CommonLog log = LogFactory.createLog();
	
	private DeviceChangeBrocastReceiver mReceiver;	
	private Context mContext;
	
	public DeviceBrocastFactory(Context context){
		mContext = context;
	}
	
	public void registerListener(IDeviceChangeListener listener){
		if (mReceiver == null){
			mReceiver = new DeviceChangeBrocastReceiver();
			mContext.registerReceiver(mReceiver, new IntentFilter(ADD_DEVICES));
			mContext.registerReceiver(mReceiver, new IntentFilter(REMOVE_DEVICES));
			mContext.registerReceiver(mReceiver, new IntentFilter(CLEAR_DEVICES));
			mReceiver.setListener(listener);
		}
	}
	
	public void unRegisterListener(){
		if (mReceiver != null){
			mContext.unregisterReceiver(mReceiver);
			mReceiver = null;
		}
	}
	
	
	
	
	
	public static final String ADD_DEVICES = "com.talent.allshare.add_device";
	public static final String REMOVE_DEVICES = "com.talent.allshare.remove_device";
	public static final String REMOVE_EXTRA_FLAG = "com.talent.allshare.remove_extra_flag";
	public static final String CLEAR_DEVICES = "com.talent.allshare.clear_device";
	public static final String SEARCH_DEVICES_FAIL = "com.talent.allshare.search_devices_fail";

	public static  void sendAddBrocast(Context context){
		log.e("sendAddBrocast");
		Intent intent = new Intent(ADD_DEVICES);
		context.sendBroadcast(intent);
	}
	
	public static void sendRemoveBrocast(Context context, boolean isSelected){
		log.e("sendRemoveBrocast isSelected = " + isSelected);
		Intent intent = new Intent(REMOVE_DEVICES);
		intent.putExtra(REMOVE_EXTRA_FLAG, isSelected);
		context.sendBroadcast(intent);
	}
	
	public static void sendClearBrocast(Context context){
		log.e("sendClearBrocast");
		Intent intent = new Intent(CLEAR_DEVICES);
		intent.putExtra(REMOVE_EXTRA_FLAG, true);
		context.sendBroadcast(intent);
	}
	
	public static void sendSearchDeviceFailBrocast(Context context){
		log.e("sendSearchDeviceFailBrocast");
		Intent intent = new Intent(SEARCH_DEVICES_FAIL);
		context.sendBroadcast(intent);
	}
}
