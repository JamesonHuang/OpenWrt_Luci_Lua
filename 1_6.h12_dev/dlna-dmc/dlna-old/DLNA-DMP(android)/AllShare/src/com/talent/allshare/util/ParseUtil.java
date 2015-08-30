package com.talent.allshare.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.cybergarage.upnp.Argument;
import org.cybergarage.util.CommonLog;
import org.cybergarage.util.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.R.integer;

import com.talent.allshare.network.Item;



public class ParseUtil {
	private static final CommonLog log = LogFactory.createLog();
		public static List<Item> parseResult(Argument result) {
		
		List<Item> list = new ArrayList<Item>();
		
		DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		try {
			documentBuilder = dfactory.newDocumentBuilder();
			InputStream is = new ByteArrayInputStream(result.getValue()
					.getBytes("UTF-8"));

			Document doc = documentBuilder.parse(is);

			NodeList containers = doc.getElementsByTagName("container");
			for (int j = 0; j < containers.getLength(); ++j) {
				Node container = containers.item(j);
				
				String childCountString = "";
				Node childcountNodes =  container.getAttributes().getNamedItem("childCount");
				if (childcountNodes != null){
					childCountString = childcountNodes.getNodeValue();
				}
				
				String title = null;
				String objectClass = null;
				int id = 0;
				String stringid ="";
				String date = "";
				NodeList childNodes = container.getChildNodes();
				for (int l = 0; l < childNodes.getLength(); ++l) {
					Node childNode = childNodes.item(l);
					
					if (childNode.getNodeName().equals("dc:title")) {
						title = childNode.getFirstChild().getNodeValue();
						//id = Integer.parseInt(container.getAttributes()
							//	.getNamedItem("id").getNodeValue());
						stringid = container.getAttributes().getNamedItem("id").getNodeValue();
					}
					else if (childNode.getNodeName().equals("upnp:class")) {
						objectClass = childNode.getFirstChild().getNodeValue();
					} if (childNode.getNodeName().equals("dc:date")) {
						Node dateNode = childNode.getFirstChild();
						if (dateNode != null){
							date = dateNode.getNodeValue();
						}				
					}
					
					
				}
				//Item i = new Item(id, title, null, null, objectClass);
				Item i = new Item(stringid, title, null, null, objectClass);
				i.setchildCount(childCountString);
				i.setDate(formatTimeString(date));
				list.add(i);
			}

			NodeList items = doc.getElementsByTagName("item");
			for (int j = 0; j < items.getLength(); ++j) {
				Node item = items.item(j);
				//int id = 0;
				String stringid;
				String title = null;
				String artist = null;
				String album = null;
				String objectClass = null;
				String res = null;
				String duration = null;
				String albumUri = null;
				String size = null;
				String date = null;
				
				boolean resRead = false;
				//id = Integer.parseInt(item.getAttributes().getNamedItem("id").getNodeValue());
                 stringid = item.getAttributes().getNamedItem("id").getNodeValue();
				NodeList childNodes = item.getChildNodes();
				for (int l = 0; l < childNodes.getLength(); ++l) {
					Node childNode = childNodes.item(l);

					if (childNode.getNodeName().equals("dc:title")) {
						title = childNode.getFirstChild().getNodeValue();

					}else if (childNode.getNodeName().equals("dc:date")) {
						Node dateNode = childNode.getFirstChild();
						if (dateNode != null){
							date = dateNode.getNodeValue();
						}				
					} else if (childNode.getNodeName().equals("upnp:artist")) {
						artist = childNode.getFirstChild().getNodeValue();
					} else if (childNode.getNodeName().equals("upnp:album")) {
						album = childNode.getFirstChild().getNodeValue();
					} else if (childNode.getNodeName().equals("upnp:class")) {
						objectClass = childNode.getFirstChild().getNodeValue();
					} else if (childNode.getNodeName().equals("res")) {
						
						if (!resRead){
							res = childNode.getFirstChild().getNodeValue();
						    if (childNode.getAttributes().getNamedItem("duration")!=null) {
							    duration = childNode.getAttributes().getNamedItem("duration").getNodeValue();
						     }
						    
							Node SizeNode = childNode.getAttributes().getNamedItem("size");
							
							if(SizeNode!=null)
							{
								size = SizeNode.getNodeValue();	      				    
							}
							resRead = true;
						}
					    
						
					}else if (childNode.getNodeName().equals("upnp:albumArtURI") || childNode.getNodeName().equals("upnp:icon")){
						albumUri = childNode.getFirstChild().getNodeValue();
					}

				}
				//Item i = new Item(id, title, artist, album, objectClass);
				Item i = new Item(stringid, title, artist, album, objectClass);
				i.setRes(res);
				i.setDuration(formatDurationString(duration));
				i.setAlbumUri(albumUri);
				i.setSize(formatSizeString(size));
				i.setDate(formatTimeString(date));
				
//				if ("object.item.audioItem.musicTrack".equals(objectClass) ||
//						"object.item.videoItem".equals(objectClass) ||
//						"object.item.imageItem.photo".equals(objectClass)) {
//					i.setRes(res);
//					i.setDuration(duration);
//				}
				list.add(i);
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}		

		

		public static int formatSizeString(String sizeString){
			int size = 0;
			if (sizeString == null || sizeString.length() < 1){
				return size;
			}
			try {
				size =Integer.parseInt(sizeString);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return size;
		}

		// 2000-2-3
		public static  long formatTimeString(String timeString){
			long time = 0;
			if (timeString == null || timeString.length() < 1){
				return time;
			}
			try {
				log.e("formatTimeString time = " + timeString);
				String []array = timeString.split("-");
				int year = Integer.valueOf(array[0]);
				int month = Integer.valueOf(array[1]);
				int day = Integer.valueOf(array[2]);
				
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.YEAR, year);
				calendar.set(Calendar.MONTH, month);
				calendar.set(Calendar.DAY_OF_MONTH, day);
				
				time = calendar.getTimeInMillis();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return time;
		}

		public static int formatDurationString(String durationString){
			int duration = 0;
			if (durationString == null || durationString.length() == 0){
				return duration;
			}
			
			double a = 3.2;
			int b = (int) a;
			try {
				String sArray[] = durationString.split(":");
				double hour = Double.valueOf(sArray[0]);
				double minute = Double.valueOf(sArray[1]);
				double second = Double.valueOf(sArray[2]);		
				
				return (int) (((hour * 60 + minute) * 60 + second) * 1000);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return duration;
		}
		
}

