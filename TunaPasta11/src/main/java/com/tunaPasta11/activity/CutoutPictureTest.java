package com.tunaPasta11.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.view.View;

import com.tunaPasta11.R;

public class CutoutPictureTest extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(new CutoutPicture(this));
	}

}

class CutoutPicture extends View {
	public CutoutPicture(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.basemap);

		Paint paint = new Paint();
		paint.setTextSize(48);
		paint.setColor(Color.RED);
		paint.setAntiAlias(true);

		Bitmap targetBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Config.ARGB_8888);

		// 产生一个同样大小的画布
		Canvas tmpCanvas = new Canvas(targetBitmap);

		// 首先绘制下层图片(在设置setXfermode之前)
		tmpCanvas.drawCircle(360, 360, 360, paint);
		tmpCanvas.drawRect(300, 840, 420, 900, paint);
		tmpCanvas.drawText("圆形", 360, 800, paint);
		
		//指纹曲线
		Path path=new Path();
		
		// 左下的图形圆心
		float bezierOvalX = 110;
		float bezierOvalY = 820;
		
		//左下的图形宽度的一半,高度的一半
		float deviationOvalX = 4;
		float deviationOvalY = 20;
		//宽度的一半,高度的一般的变动量
		float deviationOvalXOffset = 4;
		float deviationOvalYOffset = 4;
		
		//左下的图形拉伸距离的宽度,高度
		float controlOvalX = 4;
		float controlOvalY = 16;
		//拉伸距离的宽度,高度的变动量
		float controlOvalXOffset = 3;
		float controlOvalYOffset = 4;
		
		int cyclesNum=5;
		
		for (int i = 0; i < cyclesNum; i++) {
			if (i == 0) {
				//中间段曲线
				path.moveTo(bezierOvalX, bezierOvalY + 15);
				path.lineTo(bezierOvalX + 2, bezierOvalY + 2);
			}
			path.quadTo(bezierOvalX, bezierOvalY - deviationOvalY - controlOvalY, 
					bezierOvalX + deviationOvalX, bezierOvalY - deviationOvalY);// 右上
			
			path.quadTo(bezierOvalX + deviationOvalX + controlOvalX, bezierOvalY, 
					bezierOvalX + deviationOvalX, bezierOvalY + deviationOvalY);// 右下
			
			path.quadTo(bezierOvalX, bezierOvalY + deviationOvalY + controlOvalY, 
					bezierOvalX - deviationOvalX, bezierOvalY + deviationOvalY);// 左下
			
			path.quadTo(bezierOvalX - deviationOvalX - controlOvalX, bezierOvalY, 
					bezierOvalX - deviationOvalX, bezierOvalY - deviationOvalY);// 左上

			deviationOvalX += deviationOvalXOffset;
			deviationOvalY += deviationOvalYOffset;

			controlOvalX += controlOvalXOffset;
			controlOvalY += controlOvalYOffset;
		}
		
		paint.setColor(Color.RED);
		tmpCanvas.drawPath(path, paint);
		//指纹曲线

		// 使用SRC_IN
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));

		// 在设置玩SRC_IN之后绘制上层图片
		tmpCanvas.drawBitmap(mBitmap, 0, 0, paint);

		canvas.drawBitmap(targetBitmap, 0, 0, null);
	}
}