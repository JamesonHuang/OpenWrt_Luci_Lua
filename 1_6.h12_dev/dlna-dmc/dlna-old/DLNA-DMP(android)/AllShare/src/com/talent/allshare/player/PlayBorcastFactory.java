package com.talent.allshare.player;


import com.talent.allshare.network.Item;
import com.talent.allshare.network.ItemFactory;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class PlayBorcastFactory {

	private PlayBrocastReceiver mPlayBrocastReceiver;
	private Context mContext;
	
	public PlayBorcastFactory(Context context){
		mContext = context;	
	}	
	
	public void register(PlayerEngineListener listener){
		if (mPlayBrocastReceiver == null){
			mPlayBrocastReceiver = new PlayBrocastReceiver();
			mPlayBrocastReceiver.setPlayEngineListener(listener);	
			IntentFilter intentFilter1 = new IntentFilter(BROCAST_NAME);		
			mContext.registerReceiver(mPlayBrocastReceiver, intentFilter1);
		}
	}
	
	public void unregister()
	{
		if (mPlayBrocastReceiver != null){
			mContext.unregisterReceiver(mPlayBrocastReceiver);
			mPlayBrocastReceiver = null;
		}
	}
	
	

	public final static String BROCAST_NAME = "com.talent.allshare.musicplay.brocast";	
	public final static String PLAY_MUSIC_STATE = "PLAY_MUSIC_STATE";
	public final static String PLAY_MUSIC_INDEX = "PLAY_MUSIC_INDEX";
	public final static String PLAY_MUSIC_CURPOS = "PLAY_MUSIC_CURPOS";
	
	public static void sendPlayState(Context context, int playState, int playIndex, Item item){
		Intent intent = new Intent(BROCAST_NAME);
		intent.putExtra(PLAY_MUSIC_STATE, playState);
		intent.putExtra(PLAY_MUSIC_INDEX, playIndex);
		ItemFactory.putItemToIntent(item, intent);
		context.sendBroadcast(intent);
	}

	public static void sendPlayState(Context context, int curPos){	
		Intent intent = new Intent(BROCAST_NAME);
		intent.putExtra(PLAY_MUSIC_CURPOS, curPos);
		context.sendBroadcast(intent);

	}
}
