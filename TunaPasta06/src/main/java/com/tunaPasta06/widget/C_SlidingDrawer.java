package com.tunaPasta06.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SlidingDrawer;

/**
 * 自定义抽屉
 * 
 * @author Administrator
 * 
 */
public class C_SlidingDrawer extends SlidingDrawer {
	/**
	 * 用XML中设置的属性来创建一个新的SlidingDrawe
	 * 
	 * @param context
	 *            上下文
	 * @param attrs
	 *            XML中定义的属性
	 */
	public C_SlidingDrawer(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * 用XML中设置的属性来创建一个新的SlidingDrawe
	 * 
	 * @param context
	 *            上下文
	 * @param attrs
	 *            XML中定义的属性
	 * @param defStyle
	 *            要应用到这个组件上的样式
	 */
	public C_SlidingDrawer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void dispatchDraw(Canvas paramCanvas) {
		float f1 = 0.0f;
		// 获取柄视图
		View localView1 = getHandle();
		// 获取内容试图
		View localView2 = getContent();
		// 获取画图的时间
		long drawingTime = getDrawingTime();
		// 绘制柄视图子元素
		drawChild(paramCanvas, localView1, drawingTime);
		// 绘制完成就锁定画布
		paramCanvas.save();
		// 获取这个视图相对于整个布局的底部位置
		int m_Bottom = getBottom();
		// 获取内容试图的高度 ">>1"好像表示：向右移动1个单位吧 具体的不太记得了
		int c_height = localView2.getHeight() >> 1;
		// 获取柄视图相对于整个布局的底部位置
		int v_Bottom = localView1.getBottom();
		// 得到显示部分歌词的底部位置
		int total_Bottom = m_Bottom - v_Bottom;
		// 得到相对于整个布局的底部位置到显示部分歌词的底部位置
		c_height -= total_Bottom;
		c_height += 2;
		// 获取柄视图相对于整个布局的右部位置
		int h_right = localView1.getRight();
		// 设置显示区域，即设置裁剪区
		paramCanvas.clipRect(0, c_height, h_right, m_Bottom);
		// 抽屉是否在滚动或滑动 如果在滚动或滑动，返回true，否则返回false
		if (isMoving()) {
			int h_Bottom = localView1.getBottom();

			float f2 = (h_Bottom += 2) >> 1;
			// 移动原点
			paramCanvas.translate(f1, f2);
		}
		while (true) {
			float f3 = c_height;
			// 移动原点
			paramCanvas.translate(f1, f3);
			// 绘制内容视图子元素
			drawChild(paramCanvas, localView2, drawingTime);
			// 绘制完成就解除锁定画布
			paramCanvas.restore();

			return;
		}
	}
}
