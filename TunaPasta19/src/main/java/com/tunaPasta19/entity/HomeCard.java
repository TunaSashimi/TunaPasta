package com.tunaPasta19.entity;

import android.widget.TextView;

import com.tunaPasta19.application.DataTrans;
import com.tunaPasta19.tuna.TunaDownload;

public class HomeCard {
	
	private TunaDownload tunaDownlaod;
	private TunaDownload tunaDownlaodImage;
	private TextView textDownload;
	
	public HomeCard(TunaDownload tunaDownlaod, TunaDownload tunaDownlaodImage, TextView textDownload) {
		this.tunaDownlaod = tunaDownlaod;
		this.tunaDownlaodImage = tunaDownlaodImage;
		this.textDownload = textDownload;
	}
	
	public void setHomeCardInformation(String tunaDownloadBitmapSrcURL,String tunaDownloadImageBitmapSrcURL,String text){
		
		tunaDownlaod.init(
				DataTrans.tunaGraphicsMap,
				tunaDownloadBitmapSrcURL, 
				null, 
				null, 
				null
				);
		
		tunaDownlaodImage.init(
				DataTrans.tunaGraphicsMap,
				tunaDownloadImageBitmapSrcURL, 
				null, 
				null, 
				null
				);
		
		textDownload.setText(text);
		
	}
	
}
