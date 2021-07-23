package com.tunaPasta13.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DifferentGetMeasuredWidth extends Activity {

	private getMeasureDWidthTextView mTextViewTest;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mTextViewTest = new getMeasureDWidthTextView(this);

		setContentView(mTextViewTest);  
	}
}


class getMeasureDWidthTextView extends TextView {
	public getMeasureDWidthTextView(Context context) {
		super(context);
		setText("DifferentBetweenGetMeasuredWidthAndGetWidth");
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		
		 measure(0, 0);
		
//		  getWidth(): View在設定好佈局後整個View的寬度。 
		
//		  getMeasuredWidth(): 對View上的內容進行測量後得到的View內容佔據的寬度
		
		
		//控件能占的大小
		Log.i("Tag", "width==>" + getWidth() + ",height==>" + getHeight());
		
		//控件占的实际大小,需要在onLayout()方法或者此View的onDraw()方法裡調用measure(0,0);(measure 參數的值你可以自己定義)，否則你得到的結果和getWidth()得到的結果一樣。
		Log.i("Tag", "MeasuredWidth==>" + getMeasuredWidth() + ",MeasuredHeight==> " + getMeasuredHeight());
		
	}

}
