package com.talent.allshare.player;

import org.cybergarage.util.CommonLog;
import org.cybergarage.util.LogFactory;

import android.content.Context;
import android.media.AudioManager;
import android.view.SurfaceHolder;


public class VideoPlayerEngineImpl extends AbstractPlayEngine{

	private final CommonLog log = LogFactory.createLog();
	
	private SurfaceHolder mHolder = null;  
	
	
	public VideoPlayerEngineImpl(Context context,  SurfaceHolder holder) {
		super(context);
	
		setHolder(holder);
	}
	
	public void setHolder(SurfaceHolder holder){
		mHolder = holder;
	}
	
	
	protected boolean prepare(int index)
	{
		log.e("prepare index = " + index);

		mCurPlayIndex = index;
		mMediaPlayer.reset();
		
	
		String path = mFileList.get(index).getRes();
		try {
			mMediaPlayer.setDataSource(path);
			mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);  
			if (mHolder != null){
				mMediaPlayer.setDisplay(mHolder);
			}
			mMediaPlayer.prepareAsync();
			log.e("mMediaPlayer.prepareAsync	path = " + path);
		
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
