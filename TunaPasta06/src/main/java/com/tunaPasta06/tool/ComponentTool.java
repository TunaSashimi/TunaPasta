package com.tunaPasta06.tool;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.View;

import com.tunaPasta06.widget.ViewSizeAndPosition;

public class ComponentTool {
	
	public static Point getViewCenterPoint(View v) {
		int mL = v.getLeft();
		int mT = v.getTop();
		int mR = v.getRight();
		int mB = v.getBottom();
		int cX = mL + (mR - mL) / 2;
		int cY = mT + (mB - mT) / 2;
		Point cP = new Point(cX, cY);
		return cP;
	}

	public static ViewSizeAndPosition getViewSizeAndPosition(View view) {
		int width = view.getRight() - view.getLeft();
		int height = view.getBottom() - view.getTop();
		ViewSizeAndPosition vsp = new ViewSizeAndPosition();
		vsp.setWidth(width);
		vsp.setHeight(height);
		vsp.setLeft(view.getLeft());
		vsp.setTop(view.getTop());
		vsp.setRight(view.getRight());
		vsp.setBottom(view.getBottom());
		return vsp;
	}

	public static ViewSizeAndPosition getScreenSizeAndPosition(Activity activity) {
		int screenWidth = activity.getWindowManager().getDefaultDisplay()
				.getWidth();
		int screenHeight = activity.getWindowManager().getDefaultDisplay()
				.getHeight();
		ViewSizeAndPosition vs = new ViewSizeAndPosition();
		vs.setLeft(0);
		vs.setTop(0);
		vs.setRight(screenWidth);
		vs.setBottom(screenHeight);
		vs.setWidth(screenWidth);
		vs.setHeight(screenHeight);
		return vs;
	}

	public static int dpToPx(Context context, float dpValue) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5F);
	}

	public static int pxTodp(Context context, float pxValue) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5F);
	}
}