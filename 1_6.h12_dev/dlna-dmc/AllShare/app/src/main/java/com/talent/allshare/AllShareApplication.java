package com.talent.allshare;

import com.talent.allshare.proxy.AllShareProxy;

import android.app.Application;

public class AllShareApplication extends Application{

	private AllShareProxy mAllShareProxy;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		mAllShareProxy = AllShareProxy.getInstance(this);
	}
	
	
	
}
