package com.talent.allshare.proxy;

import java.util.List;
import java.util.Stack;

import org.cybergarage.util.CommonLog;
import org.cybergarage.util.LogFactory;

import com.talent.allshare.network.Item;

public class ContentManager {

	private static final CommonLog log = LogFactory.createLog();
	
	private static ContentManager mInstance = null;
	
	private Stack<List<Item>> mStack;
	
	public synchronized static ContentManager getInstance(){
		if (mInstance == null){
			mInstance =  new ContentManager();
		}
		
		return mInstance;
	}
	
	private ContentManager()
	{
		mStack = new Stack<List<Item>>();
	}
	
	public void pushListItem(List<Item> dataList)
	{
		if (dataList != null){
	//		log.e("mStack.add data.size = " + dataList.size());
			mStack.add(dataList);
		}
	}
	
	public List<Item> peekListItem()
	{
		if (mStack.empty()){
			return null;
		}
		
		return mStack.peek();
	}
	
	public List<Item> popListItem()
	{
		if (mStack.empty()){
			return null;
		}
		
		return mStack.pop();
	}
	
	public void clear()
	{
		mStack.clear();
	}
	
	
}
