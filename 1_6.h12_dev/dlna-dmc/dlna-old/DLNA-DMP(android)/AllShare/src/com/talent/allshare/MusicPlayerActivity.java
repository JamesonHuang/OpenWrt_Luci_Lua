/**
 * @auth  geniuseoe2012
 * @blog http://blog.csdn.net/geniuseoe2012
 * @date 2013-1-6
 * @description nothing to say
*/
package com.talent.allshare;

import java.util.ArrayList;
import java.util.List;

import org.cybergarage.util.CommonLog;
import org.cybergarage.util.LogFactory;

import com.talent.allshare.network.Item;
import com.talent.allshare.network.ItemFactory;
import com.talent.allshare.player.MediaManager;
import com.talent.allshare.player.MusicPlayerEngineImpl;
import com.talent.allshare.player.PlayBorcastFactory;
import com.talent.allshare.player.PlayState;
import com.talent.allshare.player.PlayerEngineListener;
import com.talent.allshare.util.TalentTimeUtil;



import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MusicPlayerActivity extends Activity implements OnClickListener{

	private static final CommonLog log = LogFactory.createLog();
	
	public static final String PLAY_INDEX = "player_index";
	
	public ImageButton mBtnPlay;
	public ImageButton mBtnPause;
	public ImageButton mBtnPre;
	public ImageButton mBtnNext;
	
	public SeekBar mSeekBar;
	public TextView mTVCurTime;
	public TextView mTVTotalTime;
	
	
	private TextView mTextViewContent;
	
	
	private PlayBorcastFactory mPlayBrocastMng;	
	private MusicPlayEngineListener mPlayEngineListener;
	private SeekbarChangeListener mSeekbarChangeListener;
	private MusicPlayerEngineImpl mPlayerEngineImpl;
	
	private Item item;
	private List<Item> mItemsList;
	
	private Handler mHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		setContentView(R.layout.music_player_layout);	
		initView();		
		initData(getIntent());
	}

	@Override
	protected void onResume() {
		
		mPlayBrocastMng.register(mPlayEngineListener);	
		super.onResume();
	}

	@Override
	protected void onPause() {
		
		mPlayBrocastMng.unregister();
		
		super.onPause();
	}
	
	@Override
	protected void onDestroy() {
		mPlayerEngineImpl.exit();
		MediaManager.getInstance().clearMusicList();
		isDestory = true;
		super.onDestroy();
	}


	private final static int REFRESH_CURPOS = 0x0001;
	public void initView()
	{
		mTextViewContent = (TextView) findViewById(R.id.tv_content);
		
		mBtnPlay = (ImageButton) findViewById(R.id.btn_play);
		mBtnPause = (ImageButton) findViewById(R.id.btn_pause);
		mBtnPre = (ImageButton) findViewById(R.id.btn_playpre);
		mBtnNext = (ImageButton) findViewById(R.id.btn_playnext);
		mBtnPlay.setOnClickListener(this);
		mBtnPause.setOnClickListener(this);
		mBtnPre.setOnClickListener(this);
		mBtnNext.setOnClickListener(this);
		
		mSeekBar = (SeekBar) findViewById(R.id.playback_seeker);
		mTVCurTime = (TextView) findViewById(R.id.tv_curTime);
		mTVTotalTime = (TextView) findViewById(R.id.tv_totalTime);
	}
	
	private boolean isDestory = false;
	private int mCurIndex = 0;
	public void initData(Intent intent){
		int index = 0;
		if (intent != null){
			mCurIndex = intent.getIntExtra(PLAY_INDEX, 0);		
			item = ItemFactory.getItemFromIntent(intent);
			setContent(item);
		}
		
		mPlayerEngineImpl = new MusicPlayerEngineImpl(this);
		mItemsList = MediaManager.getInstance().getMusicList();
		if (item != null){
			mPlayerEngineImpl.openPlaylist(mItemsList, mCurIndex);	
		}
		
		mPlayBrocastMng = new PlayBorcastFactory(this);
		mPlayEngineListener = new MusicPlayEngineListener();
		
		mSeekbarChangeListener = new SeekbarChangeListener();
		setSeekbarListener(mSeekbarChangeListener);
			
		
		mHandler = new Handler()
		{
			@Override
			public void handleMessage(Message msg) {
				switch(msg.what)
				{
				case REFRESH_CURPOS:				
					onSendCurposBrocast();
					if (!isDestory){
						mHandler.sendEmptyMessageDelayed(REFRESH_CURPOS, 1000);
					}
					break;
				}
			}
			
		};
		
		mHandler.sendEmptyMessageDelayed(REFRESH_CURPOS, 1000);
	}

	
	private void onSendCurposBrocast()
	{
		int playState = mPlayerEngineImpl.getPlayState();
		switch(playState)
		{
			case PlayState.MPS_PLAYING:
			{
				PlayBorcastFactory.sendPlayState(this, mPlayerEngineImpl.getCurPosition());
			}
				break;
		}
	}
	
	private void setContent(Item item){
		if (item != null){
			
			String content = "uri:" + item.getRes() + "\n";
			content += item.getShowString();
			mTextViewContent.setText(content);
		}else{
			mTextViewContent.setText("null");
		}
		
	}
	
	public void play()
	{
		mPlayerEngineImpl.replay();
	}
	
	public void pause()
	{
		mPlayerEngineImpl.pause();
	}
	
	public void playPre()
	{
		mPlayerEngineImpl.prev();
	}
	
	public void playNext()
	{
		mPlayerEngineImpl.next();
	}
	
	public void setPlayInfo(int curTime, Item Info)
	{
		setContent(item);
		item = Info;
		if (item == null)
		{
			return ;
		}
		
	//	log.e("setPlayInfo  -->  data = \n" + musicInfo.getShowString());
		
		String curTimeString = TalentTimeUtil.formateTime(curTime);
		String totalTimeString = TalentTimeUtil.formateTime(item.getDuration());
		String songName = item.getTitle();
//		log.e("curTimeString = " + curTimeString + "\n" + 
//				"totalTimeString = " + totalTimeString + "\n" + 
//				"songName = " + songName);	
		
		setSeekbarProgress(curTime);
		mTVCurTime.setText(curTimeString);
		mTVTotalTime.setText(totalTimeString);
	}
	
	
	public void setSeekbarProgress(int time)
	{
		if (!isSeekbarTouch)
		{
			mSeekBar.setProgress(time);
		
		}
	}

	public void setSeekbarMax(Item item)
	{
		if (item != null){
			int duration = item.getDuration();
			if (duration != 0){
				mSeekBar.setMax(duration);
			}else{
				mSeekBar.setMax(100);
			}
			
			log.e("setSeekbarMax = " + mSeekBar.getMax());
		}
	}
	
	public void showPlay(boolean bShow)
	{
		if (bShow)
		{
			mBtnPlay.setVisibility(View.VISIBLE);
			mBtnPause.setVisibility(View.INVISIBLE);
		}else{
			mBtnPlay.setVisibility(View.INVISIBLE);
			mBtnPause.setVisibility(View.VISIBLE);
		}
	}
	
	public void setSeekbarListener(SeekbarChangeListener listener)
	{
		mSeekBar.setOnSeekBarChangeListener(listener);
	}
	
	
	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.btn_play:
			play();
			break;
		case R.id.btn_pause:
			pause();
			break;
		case R.id.btn_playpre:
			playPre();
			break;
		case R.id.btn_playnext:
			playNext();
			break;
		}
	}
	
	private class MusicPlayEngineListener implements PlayerEngineListener
	{
		@Override
		public void onTrackPause(Item musicInfo) {
			log.e("onTrackPause ");
			setPlayInfo(mPlayerEngineImpl.getCurPosition(), musicInfo);
			showPlay(true);
		}

		@Override
		public void onTrackPlay(Item musicInfo) {
			log.e("onTrackPlay ");
			setSeekbarMax(musicInfo);
			setPlayInfo(mPlayerEngineImpl.getCurPosition(), musicInfo);
			showPlay(false);
		}



		@Override
		public void onTrackStop(Item musicInfo) {
			log.e("onTrackStop ");
			setPlayInfo(0, musicInfo);
			showPlay(true);
		}

		@Override
		public void onTrackStreamError(Item musicInfo) {
			log.e("onTrackStreamError ");
			setPlayInfo(0, musicInfo);
			showPlay(true);
		}

		@Override
		public void onCurPosition(int curPos) {
			setPlayInfo(curPos, item);
		}
	}
	
	private boolean isSeekbarTouch = false;
	private class SeekbarChangeListener implements OnSeekBarChangeListener
	{

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub
	
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			isSeekbarTouch = true;
		
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			isSeekbarTouch = false;
			
			mPlayerEngineImpl.skipTo(seekBar.getProgress());
			log.e("skipTo = " + seekBar.getProgress() + ", max = " + seekBar.getMax());
			
		}
		
	}
}
