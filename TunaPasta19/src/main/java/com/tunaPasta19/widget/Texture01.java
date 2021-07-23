package com.tunaPasta19.widget;

import static javax.microedition.khronos.opengles.GL10.GL_LINEAR;
import static javax.microedition.khronos.opengles.GL10.GL_RGBA;
import static javax.microedition.khronos.opengles.GL10.GL_TEXTURE_2D;
import static javax.microedition.khronos.opengles.GL10.GL_TEXTURE_MAG_FILTER;
import static javax.microedition.khronos.opengles.GL10.GL_TEXTURE_MIN_FILTER;
import static javax.microedition.khronos.opengles.GL10.GL_UNSIGNED_BYTE;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.opengl.GLUtils;

import com.tunaPasta19.tool.AphidLog;

public class Texture01 {
	private FlipRenderer01 renderer;
	
	private int[] id = {0};

	private int width, height;
	private int contentWidth, contentHeight;

	private boolean destroyed = false;

	private Texture01() {
	}

	public static Texture01 createTexture(Bitmap bitmap, FlipRenderer01 renderer, GL10 gl) {
		Texture01 t = new Texture01();
		t.renderer = renderer;

		int w = Integer.highestOneBit(bitmap.getWidth() - 1) << 1;
		int h = Integer.highestOneBit(bitmap.getHeight() - 1) << 1;

		t.contentWidth = bitmap.getWidth();
		t.contentHeight = bitmap.getHeight();
		t.width = w;
		t.height = h;

		if (AphidLog.ENABLE_DEBUG)
			AphidLog.d("createTexture: %d, %d; POT: %d, %d", bitmap.getWidth(), bitmap.getHeight(), w, h);


		gl.glGenTextures(1, t.id, 0);
		gl.glBindTexture(GL_TEXTURE_2D, t.id[0]);
		gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		
		gl.glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, w, h, 0, GL_RGBA, GL_UNSIGNED_BYTE, null);
		GLUtils.texSubImage2D(GL_TEXTURE_2D, 0, 0, 0, bitmap);

		return t;
	}
	
	public void postDestroy() {
		renderer.postDestroyTexture(this);
	}

	public void destroy(GL10 gl) {
		if (id[0] != 0) {
			gl.glDeleteTextures(1, id, 0);
			if (AphidLog.ENABLE_DEBUG)
				AphidLog.d("Destroy texture: %d", id[0]);
		}

		id[0] = 0;
		destroyed = true;
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public int[] getId() {
		return id;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getContentWidth() {
		return contentWidth;
	}

	public int getContentHeight() {
		return contentHeight;
	}
}
