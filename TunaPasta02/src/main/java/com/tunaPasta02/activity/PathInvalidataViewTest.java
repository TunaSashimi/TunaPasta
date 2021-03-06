package com.tunaPasta02.activity;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.os.Bundle;
import android.view.View;
public class PathInvalidataViewTest extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new PathInvalidataView(this));
	}

//path可以预先在view上将n个点连成一条路径~
	class PathInvalidataView extends View {
		float phase;
		PathEffect[] effects = new PathEffect[7];
		int[] colors;
		private Paint paint;
		Path path;
		public PathInvalidataView(Context context) {
			super(context);
			paint = new Paint();
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeWidth(4);
			// 创建、并初始化Path
			path = new Path();
			path.moveTo(0, 0);
			for (int i = 1; i <= 15; i++) {
				// 生成15个点，随机生成它们的Y座标。并将它们连成一条Path
				path.lineTo(i * 20, (float) Math.random() * 60);
			}
			// 初始化7个颜色
			colors = new int[] { Color.BLACK, Color.BLUE, Color.CYAN,Color.GREEN, Color.MAGENTA, Color.RED, Color.YELLOW };
		}

		@Override
		protected void onDraw(Canvas canvas) {
			// 将背景填充成白色
			canvas.drawColor(Color.WHITE);
			// -----------下面开始初始化7中路径效果----------
			
			// 不使用路径效果。
			effects[0] = null;
			// 使用CornerPathEffect路径效果
			effects[1] = new CornerPathEffect(10);//将线段与线段直接的夹角转成圆角,构造方法的参数表示圆角的半径
			// 初始化DiscretePathEffect
			effects[2] = new DiscretePathEffect(3.0f, 5.0f);//与DashPathEffect相似，但是添加了随机性。当绘制它的时候，需要指定每一段的长度和与原始路径的偏离度。
			// 初始化DashPathEffect
			effects[3] = new DashPathEffect(new float[] {20, 10, 5, 10 },phase);//用户绘制虚线路径.长线段的长度,长线段与短线段的距离,短线段的长度,短线段与长线段的距离
			// 初始化PathDashPathEffect
			
			Path p = new Path();
			p.addRect(0, 0, 8, 8, Path.Direction.CCW);
			effects[4] = new PathDashPathEffect(p, 12, phase,PathDashPathEffect.Style.ROTATE);
			// 初始化PathDashPathEffect
			effects[5] = new ComposePathEffect(effects[2], effects[4]);//将两种效果组合起来应用，先使用第一种效果，然后在这种效果的基础上应用第二种效果。
			effects[6] = new SumPathEffect(effects[4], effects[3]);//顺序地在一条路径中添加两种效果，这样每一种效果都可以应用到原始路径中，而且两种结果可以结合起来。
			// 将画布移动到8、8处开始绘制
			canvas.translate(8, 8);
			// 依次使用7中不同路径效果、7种不同的颜色来绘制路径
			for (int i = 0; i < effects.length; i++) {
				paint.setPathEffect(effects[i]);
				paint.setColor(colors[i]);
				canvas.drawPath(path, paint);
				canvas.translate(0, 60);
			}
			// 改变phase值，形成动画效果
			phase += 2;
			 // 重绘  
			invalidate();
		}
	}
}