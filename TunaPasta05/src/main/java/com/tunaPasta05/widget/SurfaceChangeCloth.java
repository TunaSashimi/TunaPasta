package com.tunaPasta05.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class SurfaceChangeCloth extends SurfaceView implements SurfaceHolder.Callback {
	private boolean flag,flagdress;
	private int screenWidth, screenHeight;
	private int source,face,underwear,dress,dress2;
	private double ratio;
	Context context;
	
	public void setFlagdress(boolean flagdress) {
		this.flagdress = flagdress;
	}
	
	public boolean isFlagdress() {
		return flagdress;
	}
	public SurfaceChangeCloth(Context context, AttributeSet attrs) {
		
		super(context, attrs);
		
		this.context=context;
		
		getHolder().addCallback(this);
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		flag = true;
		// 创建surfaceview的时候得到当前设备并获得当前设备的宽度和高度~
		WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		Display mDisplay = wm.getDefaultDisplay();
		screenWidth = mDisplay.getWidth();
		screenHeight = mDisplay.getHeight();
		System.out.println("获取当前设备的宽度为" + screenWidth);
		System.out.println("获取当前设备的高度为" + screenHeight);
		//source三段身体图片源， 中间模特长度约为源图长度的0.67
		source = (int) (screenHeight * 0.80/0.67 );
		face = (int) (source*109/814);
		underwear = (int) (source*174/814);
		dress=(int)(source*226/814);
		dress2=(int)(source*222/814);
		ratio=screenHeight * 0.80/0.67/814;
		
//		System.out.println("获取图片源结合体长度为" + source);
//		System.out.println("获取图脸型长度为" + face);
//		System.out.println("获取图内衣长度为" + underwear);
//		System.out.println("设备图与源图比率" + ratio);
	}
	
	public void draw() {
		if (flag) {
			// 得到画布并设置抗锯齿效果
			Canvas canvas = getHolder().lockCanvas();
			canvas.setDrawFilter(new PaintFlagsDrawFilter(0,Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
//			canvas.drawColor(Color.BLACK);
			
		    // 绘制身体
			compoDrawing(canvas, "f0b3",source,109,31);
     		// 绘制脸型
			compoDrawing(canvas, "f0f3",face,213,34);
			// 绘制内衣
			compoDrawing(canvas, "f0u3",underwear,185,112);
			
			//绘制连衣裙
			if(flagdress){
			dressDrawing(canvas,"2011/04/20/e4f7f370-1f19-4373-bcf1-f99d8f3429fb_19171_25711_f.png",dress,182,112);
			}else{
				//黑色
			dressDrawing(canvas,"2011/04/20/c1e525dc-c915-4ecf-bc26-59f44339c3ab_19172_25712_f.png",dress2,182,112);
				//dressDrawing(canvas,"2011/04/20/20a6bed9-e68b-4ff4-8d13-f9ad51b33081_19170_25710_f.png",(int)(source*232/814),182,111);
			}
			getHolder().unlockCanvasAndPost(canvas);
		}
	}
	
	private void compoDrawing(Canvas canvas, String type,int size,int offsetX,int offsetY){
		// 通过url地址的type确定类型，通过size确定大小，size由屏幕大小确定，图片长宽比都固定，高度就能确定，宽度先统一设置为0
		String site = "http://www.l2-fashion.com/hmdrprod?+source=url[file:/dressingroom/models/1/2/"+type+".png]&scale=width[0],height["
				+ size + "]&sink=format[png]";
		HttpGet request = new HttpGet(site);
		HttpResponse resp;
		try {
			resp = new DefaultHttpClient().execute(request);
			Bitmap bitmap = BitmapFactory.decodeStream(resp.getEntity().getContent());
			//按比列截取源图中的中间模特部分矩形，注意三目的时候要处理两次~
			canvas.drawBitmap(bitmap, new Rect(0, 0,bitmap.getWidth(), "f0b3".equals(type)?(int)(bitmap.getHeight()*0.67):bitmap.getHeight()), new Rect( (int)(offsetX*ratio), (int)(offsetY*ratio),bitmap.getWidth()+(int)(offsetX*ratio), "f0b3".equals(type)?(int)(bitmap.getHeight()*0.67)+ (int)(offsetY*ratio):bitmap.getHeight()+ (int)(offsetY*ratio)), null);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	//绘制连衣裙
	private void dressDrawing(Canvas canvas,String type,int size,int offsetX,int offsetY){
		String site = "http://www.l2-fashion.com/hmdrprod?+source=url[file:/dressingroom/items/"+type+"]&scale=width[104],height["+size+"]&sink=format[png]";
		HttpGet request = new HttpGet(site);
		HttpResponse resp;
			try {
				
				System.out.println(type+"设备图与源图比率" + offsetX*ratio);
				System.out.println(type+"设备图与源图比率" + offsetY*ratio);
				
				resp = new DefaultHttpClient().execute(request);
				Bitmap bitmap = BitmapFactory.decodeStream(resp.getEntity().getContent());
				//按比列截取源图中的中间模特部分矩形，注意三目的时候要处理两次~
				
				canvas.drawBitmap(bitmap, new Rect(0, 0,bitmap.getWidth(), bitmap.getHeight()), new Rect( (int)(offsetX*ratio), (int)(offsetY*ratio),bitmap.getWidth()+(int)(offsetX*ratio), bitmap.getHeight()+ (int)(offsetY*ratio)), null);
				
			} catch (final Exception e) {
				((Activity) context).runOnUiThread(new Runnable() {    
					public void run(){    
						Toast.makeText(context, e+"报错", Toast.LENGTH_LONG).show();
						((Activity) context).finish();
	            }    
	        }); 
			}finally{
				
				
				
			}
		
	}
}
