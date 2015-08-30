/**
 * @auth  geniuseoe2012
 * @blog http://blog.csdn.net/geniuseoe2012
 * @date 2013-1-6
 * @description nothing to say
*/
package com.talent.allshare;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.cybergarage.util.CommonLog;
import org.cybergarage.util.LogFactory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


import com.talent.allshare.network.Item;
import com.talent.allshare.network.ItemFactory;
import com.talent.allshare.player.DownLoadHelper;
import com.talent.allshare.player.FileManager;
import com.talent.allshare.player.MediaManager;
import com.talent.allshare.util.CommonUtil;
import com.talent.allshare.util.FileHelper;

public class PicturePlayerActivity extends Activity implements DownLoadHelper.IDownLoadCallback{
	private static final CommonLog log = LogFactory.createLog();
	
	public static final String PLAY_INDEX = "player_index";
	
	private UIManager mUIManager;
	private DelCacheFileManager mDelCacheFileManager;
	
	private int mScreenWidth = 0;
	private int mScreenHeight = 0;
	

	private Item item;
	private List<Item> mItemsList;
	private int mCurIndex = 0;
	
	private DownLoadHelper mDownLoadHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.picture_player_layout);
		
		initView();
		initData(getIntent());
	//	initData();
	}
	
	private void initView(){
		mUIManager = new UIManager();
		mDelCacheFileManager = new DelCacheFileManager();
	}

	
	private void initData(Intent intent){
	
		int index = 0;
		if (intent != null){
			mCurIndex = intent.getIntExtra(PLAY_INDEX, 0);		
			item = ItemFactory.getItemFromIntent(intent);
		}
		mItemsList = MediaManager.getInstance().getPictureList();
		
		mScreenWidth =  CommonUtil.getScreenWidth(this);
		mScreenHeight = CommonUtil.getScreenHeight(this);	
		mDownLoadHelper = new DownLoadHelper();
		mDownLoadHelper.init();
		
		play(mCurIndex);
	}

	@Override
	protected void onDestroy() {
		mDelCacheFileManager.start(FileManager.getSaveIconPath());
		mDownLoadHelper.unInit();
		super.onDestroy();
	}


	private void play(int pos){
		log.e("play pos = " + pos);
		mCurIndex = pos;
		mCurIndex = reviceIndex(mCurIndex);
		String requestUrl = mItemsList.get(mCurIndex).getRes();
		mDownLoadHelper.syncDownLoadFile(requestUrl, FileManager.mkSaveIconPath(requestUrl), this);
		mUIManager.showProgress(true);
	}
	
	private void pre(){
		if (mItemsList.size() == 0){
			return ;
		}
		
		mCurIndex--;
		mCurIndex = reviceIndex(mCurIndex);
	
		String requestUrl = mItemsList.get(mCurIndex).getRes();
		mDownLoadHelper.syncDownLoadFile(requestUrl, FileManager.mkSaveIconPath(requestUrl), this);
		mUIManager.showProgress(true);
	}
	
	private void next(){
		if (mItemsList.size() == 0){
			return ;
		}
		
		mCurIndex++;
		mCurIndex = reviceIndex(mCurIndex);
		
		String requestUrl = mItemsList.get(mCurIndex).getRes();
		mDownLoadHelper.syncDownLoadFile(requestUrl, FileManager.mkSaveIconPath(requestUrl), this);
		mUIManager.showProgress(true);
	}
	
	private int reviceIndex(int index){
		if (index < 0){
			return mItemsList.size() - 1;
		}else if (index >= mItemsList.size()){
			return 0;
		}
		
		return index;
	}

	
	class UIManager implements OnClickListener{
		
		public ImageView mImageView;
		public ImageButton mBtnPre;
		public ImageButton mBtnNext;
		public View mLoadView;
		
		public Bitmap recycleBitmap;
		public boolean mIsScalBitmap = false;
		
		
		public UIManager(){
			initView();
		}
		
		
		private void initView() {
			mImageView = (ImageView) findViewById(R.id.imageview);
			mLoadView = findViewById(R.id.show_load_progress);
		
			
			mBtnPre = (ImageButton) findViewById(R.id.btn_playpre);
			mBtnNext = (ImageButton) findViewById(R.id.btn_playnext);
			mBtnPre.setOnClickListener(this);
			mBtnNext.setOnClickListener(this);
		}
		
		public void setBitmap(Bitmap bitmap){
			if (recycleBitmap != null && !recycleBitmap.isRecycled()) {
				mImageView.setImageBitmap(null);
				recycleBitmap.recycle();
				recycleBitmap = null;
			}
						
			if (mIsScalBitmap) {
				mImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
			} else {
				mImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
			}
						
			recycleBitmap = bitmap;
			mImageView.setImageBitmap(recycleBitmap);
			
	//		log.e("	mImageView.setImageBitmap over...");
		}
		
		public void showProgress(boolean bShow)
		{
			if (bShow){
				mLoadView.setVisibility(View.VISIBLE);
			} else{
				mLoadView.setVisibility(View.GONE);
			}		
		}
		
		public void showLoadFailTip(){
			showToask(R.string.load_image_fail);
		}
		
		public void showParseFailTip(){
			showToask(R.string.parse_image_fail);
		}
		
		private void showToask(int tip) {
			Toast.makeText(PicturePlayerActivity.this, tip, Toast.LENGTH_SHORT).show();
		}


		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_playpre:
				pre();
				break;
			case R.id.btn_playnext:
				next();
				break;
			default:
				break;
			}
			
		}
	}
	
	
	
	class DelCacheFileManager implements Runnable
	{
		private Thread mThread;
		private String mFilePath;
		
		public DelCacheFileManager()
		{
			
		}
		
		@Override
		public void run() {
			
			long time = System.currentTimeMillis();
			log.e("DelCacheFileManager run...");
			try {
				FileHelper.deleteDirectory(mFilePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			long interval = System.currentTimeMillis() - time;
			log.e("DelCacheFileManager del over, cost time = " + interval);
		}
		
		public boolean start(String directory)
		{		
			if (mThread != null)
			{
				if (mThread.isAlive())
				{
					return false;
				}			
			}
			mFilePath = directory;	
			mThread = new Thread(this);			
			mThread.start();	
			
			return true;
		}
		
	}

	@Override
	public void downLoadResult(boolean isSuccess, String savePath) {

		onTransDelLoadResult(isSuccess, savePath);
	}
	
	private void onTransDelLoadResult(final boolean isSuccess,final String savePath){
	
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				mUIManager.showProgress(false);
				
				if (!isSuccess){
					mUIManager.showLoadFailTip();
					return ;
				}
				
				Bitmap bitmap = decodeOptionsFile(savePath);
				if (bitmap == null){
					mUIManager.showParseFailTip();
					return ;
				}
				
				mUIManager.setBitmap(bitmap);
			}
		});
	
	
	}
	
	public Bitmap decodeOptionsFile(String filePath) {
		  try {
		  	File file = new File(filePath);
		      BitmapFactory.Options o = new BitmapFactory.Options();
		      o.inJustDecodeBounds = true;
		      BitmapFactory.decodeStream(new FileInputStream(file),null,o);         
		      int width_tmp=o.outWidth, height_tmp=o.outHeight;
		      int scale = 1;
		      if (width_tmp <= mScreenWidth && height_tmp <= mScreenHeight)
		      {
		      	scale = 1;
		      	mUIManager.mIsScalBitmap = false;
		      }else{
		      	double widthFit = width_tmp * 1.0 / mScreenWidth;
		          double heightFit = height_tmp * 1.0 / mScreenHeight;
		          double fit = widthFit > heightFit ? widthFit : heightFit; 
		          scale = (int) (fit + 0.5);    
		          mUIManager.mIsScalBitmap = true;
		      }
		      Bitmap bitmap = null;
		      if(scale == 1)
		      { 	
		      	bitmap =  BitmapFactory.decodeStream(new FileInputStream(file));
		      }else{
		      	BitmapFactory.Options o2 = new BitmapFactory.Options();
		          o2.inSampleSize = scale;
		          bitmap = BitmapFactory.decodeStream(new FileInputStream(file), null, o2);
		      }
		      
		      return bitmap;
		      
		  } catch (FileNotFoundException e) {
		  	log.e("fileNotFoundException, e: " + e.toString());
		  	  	
		  }
		  return null;
	}
		
}