package com.tunaPasta07.entity;

import static javax.microedition.khronos.opengles.GL10.GL_LINEAR;
import static javax.microedition.khronos.opengles.GL10.GL_RGBA;
import static javax.microedition.khronos.opengles.GL10.GL_TEXTURE_2D;
import static javax.microedition.khronos.opengles.GL10.GL_TEXTURE_MAG_FILTER;
import static javax.microedition.khronos.opengles.GL10.GL_TEXTURE_MIN_FILTER;
import static javax.microedition.khronos.opengles.GL10.GL_UNSIGNED_BYTE;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.opengl.GLUtils;

public class Texture02 {
	private int[] id = {0};

	private int width, height;
	private int contentWidth, contentHeight;

	private boolean destroyed = false;

	private Texture02() {
	}

	public static Texture02 createTexture(Bitmap bitmap, GL10 gl) {
		Texture02 t = new Texture02();

		int w = Integer.highestOneBit(bitmap.getWidth() - 1) << 1;
		int h = Integer.highestOneBit(bitmap.getHeight() - 1) << 1;

		t.contentWidth = bitmap.getWidth();
		t.contentHeight = bitmap.getHeight();
		t.width = w;
		t.height = h;

		gl.glGenTextures(1, t.id, 0);
		gl.glBindTexture(GL_TEXTURE_2D, t.id[0]);
		gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		
		gl.glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, w, h, 0, GL_RGBA, GL_UNSIGNED_BYTE, null);
		GLUtils.texSubImage2D(GL_TEXTURE_2D, 0, 0, 0, bitmap);

		return t;
	}

	public void destroy(GL10 gl) {
		if (id[0] != 0)
			gl.glDeleteTextures(1, id, 0);

		id[0] = 0;
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
