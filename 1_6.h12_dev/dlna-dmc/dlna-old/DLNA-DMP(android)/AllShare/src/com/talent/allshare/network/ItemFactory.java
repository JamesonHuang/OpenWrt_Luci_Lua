package com.talent.allshare.network;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;

public class ItemFactory {

	public  static void putItemToIntent(Item item, Intent intent){
		intent.putExtra("title", item.getTitle());
		intent.putExtra("artist", item.getArtist());
		intent.putExtra("album", item.getAlbum());
		intent.putExtra("stringid", item.getStringid());
		intent.putExtra("objectClass", item.getObjectClass());
		intent.putExtra("res", item.getRes());
		intent.putExtra("duration", item.getDuration());

	}
	
	public static Item getItemFromIntent( Intent intent){
		Item item = new Item();
		item.setTitle(intent.getStringExtra("title"));
		item.setArtist(intent.getStringExtra("artist"));
		item.setAlbum(intent.getStringExtra("album"));
		item.setStringid(intent.getStringExtra("stringid"));
		item.setObjectClass(intent.getStringExtra("objectClass"));
		item.setRes(intent.getStringExtra("res"));
		item.setDuration(intent.getIntExtra("duration", 0));
		return item;
	}
	
}
