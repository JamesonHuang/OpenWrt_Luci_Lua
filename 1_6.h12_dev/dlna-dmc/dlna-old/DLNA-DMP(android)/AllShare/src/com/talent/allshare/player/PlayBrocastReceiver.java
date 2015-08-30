package com.talent.allshare.player;


import org.cybergarage.util.CommonLog;
import org.cybergarage.util.LogFactory;

import com.talent.allshare.network.Item;
import com.talent.allshare.network.ItemFactory;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class  PlayBrocastReceiver extends BroadcastReceiver
{
	private static final CommonLog log = LogFactory.createLog();
	private PlayerEngineListener mPlayerEngineListener;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		  String action = intent.getAction(); 
		  if (action.equals(PlayBorcastFactory.BROCAST_NAME))
		  {
			  TransPlayStateEvent(intent);
		  }
		
	}
	
	public void setPlayEngineListener(PlayerEngineListener listener)
	{
		mPlayerEngineListener = listener;
	}
	
	
	public void TransPlayStateEvent(Intent intent)
	{
		if (mPlayerEngineListener == null){
			return ;
		}
		
		int playState = intent.getIntExtra(PlayBorcastFactory.PLAY_MUSIC_STATE, -2);
		int playIndex = intent.getIntExtra(PlayBorcastFactory.PLAY_MUSIC_INDEX, -1);
		int playCurPos = intent.getIntExtra(PlayBorcastFactory.PLAY_MUSIC_CURPOS, -1);
		
		Item item = ItemFactory.getItemFromIntent(intent);
		

		if (playState == -2){
			if (playCurPos != -1){
				mPlayerEngineListener.onCurPosition(playCurPos);
			}
			return ;
		}
		
		log.e("playState = " + playState + ", TransPlayStateEvent --> data = \n" + item.getShowString());
		
		switch (playState) {
		case PlayState.MPS_INVALID:
			mPlayerEngineListener.onTrackStreamError(item);
			break;
		case PlayState.MPS_PREPARE:
			mPlayerEngineListener.onTrackStop(item);
			break;
		case PlayState.MPS_PLAYING:
			mPlayerEngineListener.onTrackPlay(item);
			break;
		case PlayState.MPS_PAUSE:
			mPlayerEngineListener.onTrackPause(item);
			break;
		default:
			break;
		}
		
		
	}
	
	
}