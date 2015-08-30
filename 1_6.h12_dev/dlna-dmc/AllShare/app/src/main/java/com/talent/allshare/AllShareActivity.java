package com.talent.allshare;



import java.util.ArrayList;

import java.util.List;

import org.cybergarage.upnp.Device;

import com.talent.allshare.adapter.DeviceAdapter;
import com.talent.allshare.proxy.AllShareProxy;
import com.talent.allshare.proxy.DeviceBrocastFactory;
import com.talent.allshare.proxy.IDeviceChangeListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;



/**
 * 
 * @author geniuseoe2012
 *  more brilliant，Please pay attention to my CSDN blog --> http://blog.csdn.net/geniuseoe2012
 *  android develop group：200102476
 */
public class AllShareActivity extends Activity implements OnClickListener, IDeviceChangeListener, OnItemClickListener{
    /** Called when the activity is first created. */

	private Button mBtnTest;
	private Button mBtnSearch;
	private Button mBtnReset;
	private Button mBtnExit;
	
	private TextView mTVSelDeV;
	private ListView mDevListView;
	
	private DeviceAdapter mDevAdapter;
	private AllShareProxy mAllShareProxy;
	
	private DeviceBrocastFactory mBrocastFactory;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        initView();
        
        initData();
        
    }

    @Override
	protected void onResume() {
		super.onResume();
		
		updateDeviceList();	
	
	}

	@Override
	protected void onPause() {
		super.onPause();
		
	
	}

	@Override
	protected void onDestroy() {
		
		mBrocastFactory.unRegisterListener();
		
		
		super.onDestroy();
	}

	private void initView(){
		mBtnSearch = (Button) findViewById(R.id.btn_search);
		mBtnReset = (Button) findViewById(R.id.btn_reset);
		mBtnExit = (Button) findViewById(R.id.btn_exit);
		mBtnSearch.setOnClickListener(this);
		mBtnReset.setOnClickListener(this);
		mBtnExit.setOnClickListener(this);	
		mBtnTest = (Button) findViewById(R.id.btn_test);
		mBtnTest.setOnClickListener(this);
		
		mDevListView = (ListView) findViewById(R.id.device_list);
		mDevListView.setOnItemClickListener(this);
    }
	
	private void initData(){
		mAllShareProxy = AllShareProxy.getInstance(this);
		
		mDevAdapter = new DeviceAdapter(this, new ArrayList<Device>());
    	mDevListView.setAdapter(mDevAdapter);
    	
    	mBrocastFactory = new DeviceBrocastFactory(this);
    	mBrocastFactory.registerListener(this);
	}


	private void updateDeviceList(){
		List<Device> list = mAllShareProxy.getDeviceList();
		mDevAdapter.refreshData(list);
	}


	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.btn_search:
				mAllShareProxy.startSearch();
				break;
			case R.id.btn_reset:
				mAllShareProxy.resetSearch();
				break;
			case R.id.btn_exit:
				mAllShareProxy.exitSearch();
				finish();
				break;
			case R.id.btn_test:
				test();
				break;
		}
	}
	
	private void test(){
		String aString = null;
		aString.length();
	}


	@Override
	public void onDeviceChange(boolean isSelDeviceChange) {
		updateDeviceList();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Device device = (Device) parent.getItemAtPosition(position);
		mAllShareProxy.setSelectedDevice(device);
		goContentActivity();
	}
	
	
	private void goContentActivity(){
		Intent intent = new Intent(this, ContentActivity.class);
		startActivity(intent);
	}
}