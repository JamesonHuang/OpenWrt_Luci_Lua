package com.talent.allshare.proxy;

import org.cybergarage.util.CommonLog;
import org.cybergarage.util.LogFactory;

import com.talent.allshare.service.DlnaService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DeviceChangeBrocastReceiver extends BroadcastReceiver{

	private static final CommonLog log = LogFactory.createLog();
	
	private IDeviceChangeListener mListener;
	
	public void setListener(IDeviceChangeListener listener){
		mListener  = listener;
	}
	
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
	
		
		if (DeviceBrocastFactory.ADD_DEVICES.equalsIgnoreCase(action) || 
				DeviceBrocastFactory.REMOVE_DEVICES.equalsIgnoreCase(action) || 
				DeviceBrocastFactory.CLEAR_DEVICES.equalsIgnoreCase(action)){			
			boolean isSelDeviceChange = intent.getBooleanExtra(DeviceBrocastFactory.REMOVE_EXTRA_FLAG, false);
			if (mListener != null){
				mListener.onDeviceChange(isSelDeviceChange);
			}
		}

	}
}
