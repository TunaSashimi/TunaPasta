package com.tunaPasta08.tools;

import java.io.IOException;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import android.graphics.Bitmap;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class XMLDecode {
	private org.dom4j.Element root;
	private java.util.ArrayList<String> list;
	private String s;
	public XMLDecode(String s){
		this.s=s;
	}
	public Element connectServer() throws Exception {
		list = new java.util.ArrayList();
		String url = "http://www.google.com/ig/api?weather="+s+"&hl=zh-cn";
		HttpClient client = new DefaultHttpClient();
		HttpUriRequest request = new HttpGet(url);	//	若使用本来的:HttpGet request = new HttpGet(url) 会连接不上!!!!
		HttpResponse resp = client.execute(request);
		java.io.InputStreamReader is = new java.io.InputStreamReader(resp.getEntity().getContent(),"gbk");
		SAXReader reader = new SAXReader();
		Document document = reader.read(is);
		root=document.getRootElement();
		return root;
	}
	
	public List<String> receiveDate() {
		for (java.util.Iterator<?> i = root.elementIterator(); i.hasNext();) {
			org.dom4j.Element weather = (org.dom4j.Element) i.next();
			for (java.util.Iterator<?> it = weather.elementIterator(); it.hasNext();) {
				org.dom4j.Element current = (org.dom4j.Element) it.next();
				for (java.util.Iterator<?> ite = current.elementIterator(); ite.hasNext();) {
					org.dom4j.Element data = (org.dom4j.Element) ite.next();
					list.add(data.getName());
					for (java.util.Iterator<?> iter = data.attributeIterator(); iter.hasNext();) {
						org.dom4j.Attribute attribute = (org.dom4j.Attribute) iter.next();
						list.add(attribute.getValue());
					}
				}
			}
		}
		if(list.size()>=15)
		list.set(15,list.get(15).length()==1? "  "+list.get(15):list.get(15));	//保证对齐属性(晴~下雨)~
		return list;
	}
	public Bitmap loadingPicture() throws ClientProtocolException, IOException {
		String s="http://www.google.com"+list.get(23);
		HttpGet request = new HttpGet(s);
		HttpResponse resp = new DefaultHttpClient().execute(request);
		return android.graphics.BitmapFactory.decodeStream(resp.getEntity().getContent());
	}
}
