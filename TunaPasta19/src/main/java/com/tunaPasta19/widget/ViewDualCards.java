package com.tunaPasta19.widget;

import java.lang.ref.WeakReference;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.view.View;

public class ViewDualCards {
	private int index = -1;
	private WeakReference<View> viewRef;
	private Texture01 texture;

	private Bitmap screenshot;
	private boolean dirty = true;

	private Card01 topCard = new Card01();
	private Card01 bottomCard = new Card01();

	public int getIndex() {
		return index;
	}

	public View getView() {
		return viewRef != null ? viewRef.get() : null;
	}

	public boolean setView(int index, View view) {
		UIUtils.assertInMainThread();
		this.index = index;
		if (getView() == view && (screenshot != null || TextureUtils.isValidTexture01(texture)))
			return false;
		viewRef = null;
		if (texture != null) {
			texture.postDestroy();
			texture = null;
		}
		if (view != null) {
			viewRef = new WeakReference<View>(view);
			UIUtils.recycleBitmap(screenshot);
			screenshot = GrabIt.takeScreenshot(view);
		} else {
			UIUtils.recycleBitmap(screenshot);
		}
		return true;
	}

	public Texture01 getTexture() {
		return texture;
	}

	public Bitmap getScreenshot() {
		return screenshot;
	}

	public boolean isDirty() {
		return dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	public Card01 getTopCard() {
		return topCard;
	}

	public Card01 getBottomCard() {
		return bottomCard;
	}

	public void buildTexture(FlipRenderer01 renderer, GL10 gl) {
		if (screenshot != null) {
			if (texture != null)
				texture.destroy(gl);
			texture = Texture01.createTexture(screenshot, renderer, gl);
			UIUtils.recycleBitmap(screenshot);
			screenshot = null;

			topCard.setTexture(texture);
			bottomCard.setTexture(texture);

			final float viewHeight = texture.getContentHeight();
			final float viewWidth = texture.getContentWidth();
			final float textureHeight = texture.getHeight();
			final float textureWidth = texture.getWidth();

			topCard.setCardVertices(new float[]{
				0f, viewHeight, 0f,                     //top left
				0f, viewHeight / 2.0f, 0f,              //bottom left
				viewWidth, viewHeight / 2f, 0f, //bottom right
				viewWidth, viewHeight, 0f       //top right
			});

			topCard.setTextureCoordinates(new float[]{
				0f, 0f,
				0f, viewHeight / 2f / textureHeight,
				viewWidth / textureWidth, viewHeight / 2f / textureHeight,
				viewWidth / textureWidth, 0f
			});

			bottomCard.setCardVertices(new float[]{
				0f, viewHeight / 2f, 0f,                //top left
				0f, 0f, 0f,                                      //bottom left
				viewWidth, 0f, 0f,                      //bottom right
				viewWidth, viewHeight / 2f, 0f  //top right
			});

			bottomCard.setTextureCoordinates(new float[]{
				0f, viewHeight / 2f / textureHeight,
				0f, viewHeight / textureHeight,
				viewWidth / textureWidth, viewHeight / textureHeight,
				viewWidth / textureWidth, viewHeight / 2f / textureHeight
			});

			FlipRenderer01.checkError(gl);
		}
	}

	public void abandonTexture() {
		texture = null;
	}
}
