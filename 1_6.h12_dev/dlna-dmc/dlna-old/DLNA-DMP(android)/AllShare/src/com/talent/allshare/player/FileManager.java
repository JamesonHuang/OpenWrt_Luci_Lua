package com.talent.allshare.player;

import com.talent.allshare.util.CommonUtil;


public class FileManager {

	public static String getSaveIconPath() {
		return CommonUtil.getRootFilePath() + "com.geniuseoe2012/allshare/icons/";
	}
	
	public static String mkSaveIconPath(String uri) {
		return getSaveIconPath() + getFormatUri(uri);
	}
	

	public static String getFormatUri(String uri)
	{
		uri  = uri.replace("/", "_");
		uri  = uri.replace(":", "");	
		uri  = uri.replace("?", "_");
		uri  = uri.replace("%", "_");	
		
		int length = uri.length();
		if (length > 150)
		{
			uri = uri.substring(length - 150);
		}
		
		
		return uri;
	}
	
}
