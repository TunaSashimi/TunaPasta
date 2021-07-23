package com.tunaPasta06.activity;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.tunaPasta06.R;

public class MeasureSizeTest extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.measuresizetest);

		final ImageView imageView =  findViewById(R.id.image01);
		final TextView text01 =  findViewById(R.id.text01);

		// 说明等onCreate方法执行完了,我们定义的控件才会被度量(measure),
		// 所以我们在onCreate方法里面通过view.getHeight()获取控件的高度或者宽度肯定是0,
		// 因为它自己还没有被度量,也就是说他自己都不知道自己有多高,而你这时候去获取它的尺寸,肯定是不行的.

//		// ------------------------------------------------方法一(根据输出语句,调用了三次onMeasure)
//		int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
//		int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
//		imageView.measure(w, h);
//		int height = imageView.getMeasuredHeight();
//		int width = imageView.getMeasuredWidth();
//		text01.append("\n" + height + "," + width);

		
//		//-----------------------------------------------方法二(根据输出语句,调用了两次onMeasure),比较合适
	    ViewTreeObserver vto2 = imageView.getViewTreeObserver();    
	    vto2.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {  
	        @Override    
	        public void onGlobalLayout() {  
	            imageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);    
	            text01.append("该图片的长度和宽度分别为\n\n"+imageView.getHeight()+","+imageView.getWidth());  
	        }    
	    });    
//		System.out.println("执行完毕.." + System.currentTimeMillis());
	}

}
