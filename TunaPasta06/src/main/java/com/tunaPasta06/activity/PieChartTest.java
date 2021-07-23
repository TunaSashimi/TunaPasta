package com.tunaPasta06.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;

import com.tunaPasta06.widget.PieView;

public class PieChartTest extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//		这里用到了一个方法，
//		android.graphics.Canvas.drawArc(RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint)
//		android.graphics.RectF.RectF(float left, float top, float right, float bottom)
//		Paint是用来设置颜色，风格，
//		paint.setStyle(Style.STROKE);设置为空心
//		paint.setStyle(Style.FILL);设置为实心。
//		paint.setStrokeWidth(1);设置边框像素。
//		这里需要注意一下所有颜色的角度加起来必须为360度，否则画出来的圆会有瑕疵。

        int[] colors = new int[]{Color.WHITE, 0xadadad, Color.BLUE, Color.GREEN};
        int[] shade_colors = new int[]{Color.rgb(173, 173, 173), Color.rgb(255, 255, 255), Color.rgb(3, 23, 163), Color.rgb(15, 165, 0)};

        int i = (int) (89.00 / 100 * 360);
        int j = 360 - i;
        int[] percent = new int[]{j, i};

        System.out.println(percent[0]);
        System.out.println(percent[1]);

        PieView pieView = new PieView(this, colors, shade_colors, percent);

        setContentView(pieView, new LayoutParams(200, 200));
    }
}
