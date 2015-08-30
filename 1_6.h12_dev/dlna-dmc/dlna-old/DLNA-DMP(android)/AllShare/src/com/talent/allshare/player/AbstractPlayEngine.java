package com.talent.allshare.player;

import java.util.ArrayList;
import java.util.List;

import org.cybergarage.util.CommonLog;
import org.cybergarage.util.LogFactory;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;

import com.talent.allshare.network.Item;
import com.talent.allshare.network.ItemFactory;

public abstract class AbstractPlayEngine implements IPlayEngine, OnCompletionListener, OnErrorListener, OnPreparedListener{
	
	private static final CommonLog log = LogFactory.createLog();
	
	protected MediaPlayer   mMediaPlayer;					
	protected List<Item> 	mFileList;				
	protected int 			mCurPlayIndex;							
	protected int 			mPlayState;         								
	protected Context 		mContext;
	
	protected  void defaultParam()
	{
		mMediaPlayer = new MediaPlayer();		
		mMediaPlayer.setOnCompletionListener(this);	
		mMediaPlayer.setOnErrorListener(this);	
		mMediaPlayer.setOnPreparedListener(this);
		mFileList = new ArrayList<Item>();	
		mCurPlayIndex = -1;
		mPlayState = PlayState.MPS_NOFILE;
	}
	
	public AbstractPlayEngine(Context context){
	
		mContext = context;
		defaultParam();	
	}
	
	@Override
	public void exit() {
		mMediaPlayer.reset();
		mMediaPlayer.release();
		mFileList = new ArrayList<Item>();
		mCurPlayIndex = -1;
		mPlayState = PlayState.MPS_NOFILE;
	}

	@Override
	public void play() {
		replay();
	}

	@Override
	public void play(int position) {
		if (mPlayState == PlayState.MPS_NOFILE)
		{
			return ;
		}
		
		if (mCurPlayIndex == position)
		{
			if (mMediaPlayer.isPlaying() == false)
			{
				mMediaPlayer.start();
				mPlayState = PlayState.MPS_PLAYING;
				sendPlayStateBrocast();
			}
			
			return ;
		}
		
		isStop = false;
		mCurPlayIndex = position;
		if (prepare(mCurPlayIndex) == false)
		{
			return ;
		}
		
	}
	
	@Override
	public void replay() {
		if (mPlayState == PlayState.MPS_NOFILE || mPlayState == PlayState.MPS_INVALID)
		{
			return ;
		}
	
		mMediaPlayer.start();
		isStop = false;
		mPlayState = PlayState.MPS_PLAYING;
		sendPlayStateBrocast();
	}

	@Override
	public void pause() {
		if (mPlayState != PlayState.MPS_PLAYING)
		{
			return ;
		}
		
		mMediaPlayer.pause();
		mPlayState = PlayState.MPS_PAUSE;
		sendPlayStateBrocast();
	}


	protected boolean isStop = false;
	@Override
	public void stop() {
		if (mPlayState != PlayState.MPS_PLAYING && mPlayState != PlayState.MPS_PAUSE)
		{
			return ;
		}	
		isStop = true;
		prepare(mCurPlayIndex);
	}
	
	@Override
	public void prev() {
		if (mPlayState == PlayState.MPS_NOFILE)
		{
			return ;
		}
		
		mCurPlayIndex--;
		mCurPlayIndex = reviceIndex(mCurPlayIndex);
		
		isStop = false;
		if (prepare(mCurPlayIndex) == false)
		{
			return ;
		}
		
	}

	@Override
	public void next() {
		if (mPlayState == PlayState.MPS_NOFILE)
		{
			return ;
		}
		mCurPlayIndex++;
		mCurPlayIndex = reviceIndex(mCurPlayIndex);
		
		isStop = false;
		if (prepare(mCurPlayIndex) == false)
		{
			return ;
		}	
	}
	
	@Override
	public void skipTo(int time) {
		if (mPlayState == PlayState.MPS_NOFILE || mPlayState == PlayState.MPS_INVALID)
		{
			return ;
		}
		int time2 = reviceSeekValue(time);
		mMediaPlayer.seekTo(time2);
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		log.e("onPrepared isStop = " + isStop);
		mPlayState = PlayState.MPS_PREPARE;
		
		if (!isStop){
			replay();
		}
	}
	

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {	
		
		return false;
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		if (mCurPlayIndex != mFileList.size() - 1)
		{
			next();
		}else{
			prepare(mCurPlayIndex);
		}
	}
	

	public List<Item> getPlaylist() {
		// TODO Auto-generated method stub
		return mFileList;
	}


	public boolean isPlaying() {
		// TODO Auto-generated method stub
		return mPlayState == PlayState.MPS_PLAYING;
	}

	public void openPlaylist(List<Item> list,  int index) {
		// TODO Auto-generated method stub
		if (list == null)
		{
			mFileList.clear();
			mPlayState = PlayState.MPS_NOFILE;
			mCurPlayIndex = -1;
			return ;
		}

		mFileList = list;	
		if (mFileList.size() == 0)
		{
			mPlayState = PlayState.MPS_NOFILE;
			mCurPlayIndex = -1;
			return ;
		}

	
		index = reviceIndex(index);
		mCurPlayIndex = index;
		log.e("openPlaylist size = " + mFileList.size() + ", curPlay = "  + mCurPlayIndex);	
		
		isStop = false;
		switch(mPlayState)
		{
		case PlayState.MPS_NOFILE:
			prepare(index);
			break;
		case PlayState.MPS_INVALID:
			prepare(index);
			break;
		case PlayState.MPS_PREPARE:
			prepare(index);
			break;
		case PlayState.MPS_PLAYING:
			break;
		case PlayState.MPS_PAUSE:
			break;
		default:
			break;
		}
	}
		
	public int getCurPosition()
	{
		if (mPlayState == PlayState.MPS_PLAYING || mPlayState == PlayState.MPS_PAUSE)
		{
			return mMediaPlayer.getCurrentPosition();
		}
			
		return 0;
	}
	
	public int getPlayState()
	{
		return mPlayState;
	}
	
	protected abstract boolean prepare(int index);

	public void sendPlayStateBrocast()
	{
		if (mContext != null){
			try {
				log.e("sendPlayStateBrocast mPlayState = " + mPlayState + ", mCurPlayIndex = " + mCurPlayIndex);
				PlayBorcastFactory.sendPlayState(mContext, mPlayState, mCurPlayIndex, mFileList.get(mCurPlayIndex));
			} catch (Exception e) {
				e.printStackTrace();
				log.e("sendPlayStateBrocast catch Exception!!!");
			}
		
		}else{
			log.e("sendPlayStateBrocast fail!!! mContext = null");
		}
	}
		
		

		
	private int reviceSeekValue(int value)
	{
		if (value < 0)
		{
			value = 0;
		}
		
		if (value > mMediaPlayer.getDuration())
		{
			value = mMediaPlayer.getDuration();
		}
		
		return value;
	}
		
	private int reviceIndex(int index)
	{
		if (index < 0)
		{
			index = mFileList.size() - 1;
		}
		
		if (index >= mFileList.size())
		{
			index = 0;
		}
		
		return index;
	}
		
}
