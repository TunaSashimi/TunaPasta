package com.tunaPasta19.widget;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

import com.tunaPasta19.tool.AphidLog;

public class GrabIt {
	private GrabIt() {
	}

	public static Bitmap takeScreenshot(View view) {
		if (view != null && view.getWidth() > 0 && view.getHeight() > 0) {
			Bitmap.Config config = Bitmap.Config.ARGB_8888;
			Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), config);
			Canvas canvas = new Canvas(bitmap);
			view.draw(canvas);
			AphidLog.d("create bitmap %dx%d", view.getWidth(), view.getHeight());

			return bitmap;
		} else {
			return null;
		}
	}
}
