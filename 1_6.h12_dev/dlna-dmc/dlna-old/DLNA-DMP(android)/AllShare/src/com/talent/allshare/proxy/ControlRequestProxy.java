package com.talent.allshare.proxy;

import java.util.List;

import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.Argument;
import org.cybergarage.upnp.ArgumentList;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.UPnPStatus;
import org.cybergarage.util.CommonLog;
import org.cybergarage.util.LogFactory;
import org.cybergarage.xml.Node;

import android.content.Context;

import com.talent.allshare.network.Item;
import com.talent.allshare.util.ParseUtil;


public class ControlRequestProxy {

	public static interface ControlRequestCallback
	{
		public void onGetItems(final List<Item> list);
	}
	
	private static final CommonLog log = LogFactory.createLog();
	
	public static  void syncGetDirectory(final Context context, final ControlRequestCallback callback) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {

				List<Item> list = null;
				try {
					list = getDirectory(context);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (callback != null){
					callback.onGetItems(list);
				}
			}
		});
		
		thread.start();
	}
	
	public static void syncGetItems(final Context context, final String id,final ControlRequestCallback callback) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				List<Item> list = null;
				try {
					list = getItems(context, id);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (callback != null){
					callback.onGetItems(list);
				}
			}
		});
		
		thread.start();
		

	}
	
	public static List<Item> getDirectory(Context context) throws Exception {
		
		Device selDevice = AllShareProxy.getInstance(context).getSelectedDevice();
		if (selDevice == null) {
			log.e("no selDevice!!!");
			return null;
		}
		
//		Node selDevNode = selDevice.getDeviceNode();
//		if (selDevNode != null){
//			selDevNode.print();
//		}
		
		org.cybergarage.upnp.Service service = selDevice
		.getService("urn:schemas-upnp-org:service:ContentDirectory:1");
		if (selDevice == null)
		{
			log.e("no service for ContentDirectory!!!");
			return null;
		}
		
//		Node serverNode = service.getServiceNode();
//		if (serverNode != null){
//			serverNode.print();
//		}
	
		Action action = service.getAction("Browse");
		if(action == null)
		{
			log.e("action for Browse is null!!!");
			return null;
		}
		ArgumentList argumentList = action.getArgumentList();
		argumentList.getArgument("ObjectID").setValue(0);
		argumentList.getArgument("BrowseFlag").setValue("BrowseDirectChildren");
		argumentList.getArgument("StartingIndex").setValue("0");
		argumentList.getArgument("RequestedCount").setValue("0");
		argumentList.getArgument("Filter").setValue("*");
		argumentList.getArgument("SortCriteria").setValue("");
		
		ArgumentList actionInputArgList = action.getInputArgumentList();	
		int size = actionInputArgList.size();
		for(int i = 0; i < size; i++){
			Argument argument =  (Argument) (actionInputArgList.get(i));
		//	argument.getArgumentNode().print();
		}

		if (action.postControlAction()) {
			ArgumentList outArgList = action.getOutputArgumentList();
			Argument result = outArgList.getArgument("Result");
		
		//	log.e("result value = \n" + result.getValue());	
			
			
			List<Item> items = ParseUtil.parseResult(result);
			return items;
		} else {
			UPnPStatus err = action.getControlStatus();
			log.e("Error Code = " + err.getCode());
			log.e("Error Desc = " + err.getDescription());
		}
		return null;
	}
	
	public static List<Item> getItems(Context context, String id) throws Exception{
		
		
		Device selDevice = AllShareProxy.getInstance(context).getSelectedDevice();
		if (selDevice == null) {
			log.e("no service for ContentDirectory!!!");
			return null;
		}
		
//		Node selDevNode = selDevice.getDeviceNode();
//		if (selDevNode != null){
//			selDevNode.print();
//		}

		log.e("getItems id = " + id);
		
		
		org.cybergarage.upnp.Service service = selDevice
		.getService("urn:schemas-upnp-org:service:ContentDirectory:1");
		if (selDevice == null)
		{
			log.e("no service for ContentDirectory!!!");
			return null;
		}
		
		Action action = service.getAction("Browse");
		if(action == null)
		{
			log.e("action for Browse is null");
			return null;
		}
	
	//	action.getActionNode().print();
		
		
		ArgumentList argumentList = action.getArgumentList();
		argumentList.getArgument("ObjectID").setValue(id);
		argumentList.getArgument("BrowseFlag").setValue("BrowseDirectChildren");
		argumentList.getArgument("StartingIndex").setValue("0");
		argumentList.getArgument("RequestedCount").setValue("0");
		argumentList.getArgument("Filter").setValue("*");
		argumentList.getArgument("SortCriteria").setValue("");

		if (action.postControlAction()) {
			ArgumentList outArgList = action.getOutputArgumentList();
			Argument result = outArgList.getArgument("Result");
	//		log.e("result value = \n" + result.getValue());	
			
			List<Item> items = ParseUtil.parseResult(result);
			return items;
		} else {
			UPnPStatus err = action.getControlStatus();
			System.out.println("Error Code = " + err.getCode());
			System.out.println("Error Desc = " + err.getDescription());
		}
		return null;
	}
}
