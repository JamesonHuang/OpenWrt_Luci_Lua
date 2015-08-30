package com.talent.allshare.player;

import java.util.ArrayList;
import java.util.List;

import com.talent.allshare.network.Item;

public class MediaManager {

	private static MediaManager mInstance;
	
	private List<Item> mMusicList;
	private List<Item> mVideoList;
	private List<Item> mPictureist;
	
	public synchronized static MediaManager getInstance(){
		if (mInstance == null){
			mInstance = new MediaManager();
		}
		return mInstance;
	}
	
	private MediaManager()
	{
		mMusicList = new ArrayList<Item>();
		mVideoList = new ArrayList<Item>();
		mPictureist = new ArrayList<Item>();
	}
	
	public void setMusicList(List<Item> list){
		if (list != null){
			mMusicList = list;
		}
	
	}	
	public List<Item> getMusicList(){
		return mMusicList;
	}	
	public void clearMusicList(){
		mMusicList = new ArrayList<Item>();
	}
	
	public void setVideoList(List<Item> list){
		if (list != null){
			mVideoList = list;
		}
	
	}	
	public List<Item> getVideoList(){
		return mVideoList;
	}	
	public void clearVideoList(){
		mVideoList = new ArrayList<Item>();
	}
	
	public void setPictureList(List<Item> list){
		if (list != null){
			mPictureist = list;
		}
	
	}	
	public List<Item> getPictureList(){
		return mPictureist;
	}	
	public void clearPictureList(){
		mPictureist = new ArrayList<Item>();
	}
	
}
