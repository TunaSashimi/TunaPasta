/*
 * Copyright (c) 2010-2011, The MiCode Open Source Community (www.micode.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tunaPasta09.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 自定义一个View继承ImageView，增加一个通用的旋转图片资源的方法
 * 
 * @author way
 * 
 */
public class CompassView extends ImageView {
	private float mDirection;// 方向旋转浮点数
	private Drawable compass;// 图片资源
	
	//三个构造器
	public CompassView(Context context) {
		super(context);
		mDirection = 0.0f;// 默认不旋转
		compass = null;
	}

	public CompassView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mDirection = 0.0f;
		compass = null;
	}

	public CompassView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mDirection = 0.0f;
		compass = null;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (compass == null) {
			compass = getDrawable();// 获取当前view的图片资源
			compass.setBounds(0, 0, getWidth(), getHeight());// 图片资源在view的位置，此处相当于充满view
		}

		canvas.save();
		canvas.rotate(mDirection, getWidth() / 2, getHeight() / 2);// 绕图片中心点旋转，
		compass.draw(canvas);// 把旋转后的图片画在view上，即保持旋转后的样子
		canvas.restore();// 保存一下
	}

	/**
	 * 自定义更新方向的方法
	 * 
	 * @param direction
	 *            传入的方向
	 */
	public void updateDirection(float direction) {
		mDirection = direction;
		invalidate();// 重新刷新一下，更新方向
	}

}
