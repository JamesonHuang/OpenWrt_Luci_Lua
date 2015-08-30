package com.talent.allshare.proxy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.DeviceList;
import org.cybergarage.util.CommonLog;
import org.cybergarage.util.LogFactory;

import android.content.Context;
import android.content.Intent;

import com.talent.allshare.service.DlnaService;


public class AllShareProxy {

	private static final CommonLog log = LogFactory.createLog();

	
	private static  AllShareProxy instance;
	private Context mContext;
	
	private AllShareProxy(Context context) {
		mContext = context;
	}

	public static synchronized AllShareProxy getInstance(Context context) {
		if (instance == null){
			instance  = new AllShareProxy(context);
		}
		return instance;
	}

	
	public void startSearch(){
	
		mContext.startService(new Intent(DlnaService.SEARCH_DEVICES));
	}
	
	public void resetSearch(){

		mContext.startService(new Intent(DlnaService.RESET_SEARCH_DEVICES));
		clear();
	}
	
	public void exitSearch(){

		mContext.stopService(new Intent(mContext, DlnaService.class));
		clear();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	private List<Device> mDeviceList = new ArrayList<Device>();
	private Device mSelectedDevice;
	
	public synchronized List<Device> getDeviceList() {
		return mDeviceList;
	}
	
	/**
	 * @return the selectedDevice
	 */
	public Device getSelectedDevice() {
		return mSelectedDevice;
	}

	/**
	 * @param selectedDevice
	 *            the selectedDevice to set
	 */
	public void setSelectedDevice(Device selectedDevice) {
		log.e("setSelectedDevice = " + mSelectedDevice);
		mSelectedDevice = selectedDevice;
	}
	
	public synchronized void  addDevice(Device d) {
	//	log.i("DeviceDataCenter addDevice = " + d.toString() + "\n"  + d.getDeviceNode().toString());
		log.i("DeviceDataCenter addDevice = " + d.getFriendlyName());
		mDeviceList.add(d);
		log.i("addDevice devices.size = " + mDeviceList.size());
		DeviceBrocastFactory.sendAddBrocast(mContext);
	}
	
	public synchronized void removeDevice(Device d)
	{	
	//	log.i("DeviceDataCenter removeDevice = " + d.toString() + "\n"  + d.getDeviceNode().toString());
		log.i("DeviceDataCenter removeDevice = " + d.getFriendlyName());
		int size = mDeviceList.size();
		for(int i = 0; i < size; i++)
		{
			String udnString = mDeviceList.get(i).getUDN();
			if (d.getUDN().equalsIgnoreCase(udnString)){
				Device device = mDeviceList.remove(i);
				
				boolean ret = false;
				if (mSelectedDevice != null){
					ret = mSelectedDevice.getUDN().equalsIgnoreCase(device.getUDN());
				}
				if (ret){
					setSelectedDevice(null);
				}
				DeviceBrocastFactory.sendRemoveBrocast(mContext, ret);
				break;
			}
		}
		log.i("removeDevice devices.size = " + mDeviceList.size());
	}
	
	public synchronized void  clear()
	{
		mDeviceList = new ArrayList<Device>();
		mSelectedDevice = null;
		DeviceBrocastFactory.sendClearBrocast(mContext);
	}
	
	
	

	

}
