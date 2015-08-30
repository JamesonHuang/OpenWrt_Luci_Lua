package com.talent.allshare.player;

import org.cybergarage.util.CommonLog;
import org.cybergarage.util.LogFactory;

import android.content.Context;
import android.media.AudioManager;




public class MusicPlayerEngineImpl extends AbstractPlayEngine{

	
	private final CommonLog log = LogFactory.createLog();
	
	
	public MusicPlayerEngineImpl(Context context) {
		super(context);
	
	}
	
	
	protected boolean prepare(int index)
	{
		mCurPlayIndex = index;
		mMediaPlayer.reset();
		
		String path = mFileList.get(index).getRes();
		try {
			mMediaPlayer.setDataSource(path);
			mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mMediaPlayer.prepareAsync();
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			mPlayState = PlayState.MPS_INVALID;
			sendPlayStateBrocast();
			return false;
		}

		return true;
	}
	
}
