package com.tunaPasta07.entity;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;

import com.tunaPasta07.tools.AphidLog;
import com.tunaPasta07.tools.GrabIt;
import com.tunaPasta07.widget.FlipRenderer03;

public class FlipCards03 {
	private static final float ACCELERATION = 0.618f;
	private static final float TIP_SPEED = 1f;
	private static final float MOVEMENT_RATE = 1.5f;
	private static final int MAX_TIP_ANGLE = 60;

	private static final int STATE_TIP = 0;
	private static final int STATE_TOUCH = 1;
	private static final int STATE_AUTO_ROTATE = 2;

	private Texture02 frontTexture;
	private Bitmap frontBitmap;

	private Texture02 backTexture;
	private Bitmap backBitmap;

	private Card03 frontTopCard;
	private Card03 frontBottomCard;

	private Card03 backTopCard;
	private Card03 backBottomCard;

	private float angle = 0f;
	private boolean forward = true;
	//	private boolean animating = false;
	private int animatedFrame = 0;
	private int state = STATE_TIP;

	public FlipCards03() {
		frontTopCard = new Card03();
		frontBottomCard = new Card03();

		backTopCard = new Card03();
		backBottomCard = new Card03();

		frontBottomCard.setAxis(Card03.AXIS_TOP);
		backTopCard.setAxis(Card03.AXIS_BOTTOM);
	}

	public void reloadTexture(View frontView, View backView) {
		frontBitmap = GrabIt.takeScreenshot(frontView);
		backBitmap = GrabIt.takeScreenshot(backView);
	}

	public void rotateBy(float delta) {
		angle += delta;
		if (angle > 180)
			angle = 180;
		else if (angle < 0)
			angle = 0;
	}

	public void setState(int state) {
		if (this.state != state) {
			this.state = state;
			animatedFrame = 0;
		}
	}

	public void draw(GL10 gl) {
		applyTexture(gl);

		if (frontTexture == null)
			return;

		switch (state) {
			case STATE_TIP: {
				if (angle >= 180)
					forward = false;
				else if (angle <= 0)
					forward = true;

				rotateBy((forward ? TIP_SPEED : -TIP_SPEED));
				if (angle > 90 && angle <= 180 - MAX_TIP_ANGLE) {
					forward = true;
				} else if (angle < 90 && angle >= MAX_TIP_ANGLE) {
					forward = false;
				}
			}
			break;
			case STATE_TOUCH:
				break;
			case STATE_AUTO_ROTATE: {
				animatedFrame++;
				rotateBy((forward ? ACCELERATION : -ACCELERATION) * animatedFrame);
				if (angle >= 180 || angle <= 0)
					setState(STATE_TIP);
			}
			break;
			default:
				AphidLog.e("Invalid state: " + state);
				break;
		}

		if (angle < 90) {
			frontTopCard.draw(gl);
			backBottomCard.draw(gl);
			frontBottomCard.setAngle(angle);
			frontBottomCard.draw(gl);
		} else {
			frontTopCard.draw(gl);
			backTopCard.setAngle(180 - angle);
			backTopCard.draw(gl);
			backBottomCard.draw(gl);
		}
	}

	private void applyTexture(GL10 gl) {
		if (frontBitmap != null) {
			if (frontTexture != null)
				frontTexture.destroy(gl);

			frontTexture = Texture02.createTexture(frontBitmap, gl);

			frontTopCard.setTexture(frontTexture);
			frontBottomCard.setTexture(frontTexture);

			frontTopCard.setCardVertices(new float[]{
				0f, frontBitmap.getHeight(), 0f,                     //top left
				0f, frontBitmap.getHeight() / 2.0f, 0f,              //bottom left
				frontBitmap.getWidth(), frontBitmap.getHeight() / 2f, 0f, //bottom right
				frontBitmap.getWidth(), frontBitmap.getHeight(), 0f       //top right
			});

			frontTopCard.setTextureCoordinates(new float[]{
				0f, 0f,
				0f, frontBitmap.getHeight() / 2f / (float) frontTexture.getHeight(),
				frontBitmap.getWidth() / (float) frontTexture.getWidth(), frontBitmap.getHeight() / 2f / (float) frontTexture.getHeight(),
				frontBitmap.getWidth() / (float) frontTexture.getWidth(), 0f
			});

			frontBottomCard.setCardVertices(new float[]{
				0f, frontBitmap.getHeight() / 2f, 0f,                //top left
				0f, 0f, 0f,                                      //bottom left
				frontBitmap.getWidth(), 0f, 0f,                      //bottom right
				frontBitmap.getWidth(), frontBitmap.getHeight() / 2f, 0f  //top right
			});

			frontBottomCard.setTextureCoordinates(new float[]{
				0f, frontBitmap.getHeight() / 2f / (float) frontTexture.getHeight(),
				0f, frontBitmap.getHeight() / (float) frontTexture.getHeight(),
				frontBitmap.getWidth() / (float) frontTexture.getWidth(), frontBitmap.getHeight() / (float) frontTexture.getHeight(),
				frontBitmap.getWidth() / (float) frontTexture.getWidth(), frontBitmap.getHeight() / 2f / (float) frontTexture.getHeight()
			});

			FlipRenderer03.checkError(gl);

			frontBitmap.recycle();
			frontBitmap = null;
		}

		if (backBitmap != null) {
			if (backTexture != null)
				backTexture.destroy(gl);

			backTexture = Texture02.createTexture(backBitmap, gl);

			backTopCard.setTexture(backTexture);
			backBottomCard.setTexture(backTexture);

			backTopCard.setCardVertices(new float[]{
				0f, backBitmap.getHeight(), 0f,                     //top left
				0f, backBitmap.getHeight() / 2.0f, 0f,              //bottom left
				backBitmap.getWidth(), backBitmap.getHeight() / 2f, 0f, //bottom right
				backBitmap.getWidth(), backBitmap.getHeight(), 0f       //top right
			});

			backTopCard.setTextureCoordinates(new float[]{
				0f, 0f,
				0f, backBitmap.getHeight() / 2f / (float) backTexture.getHeight(),
				backBitmap.getWidth() / (float) backTexture.getWidth(), backBitmap.getHeight() / 2f / (float) backTexture.getHeight(),
				backBitmap.getWidth() / (float) backTexture.getWidth(), 0f
			});

			backBottomCard.setCardVertices(new float[]{
				0f, backBitmap.getHeight() / 2f, 0f,                //top left
				0f, 0f, 0f,                                      //bottom left
				backBitmap.getWidth(), 0f, 0f,                      //bottom right
				backBitmap.getWidth(), backBitmap.getHeight() / 2f, 0f  //top right
			});

			backBottomCard.setTextureCoordinates(new float[]{
				0f, backBitmap.getHeight() / 2f / (float) backTexture.getHeight(),
				0f, backBitmap.getHeight() / (float) backTexture.getHeight(),
				backBitmap.getWidth() / (float) backTexture.getWidth(), backBitmap.getHeight() / (float) backTexture.getHeight(),
				backBitmap.getWidth() / (float) backTexture.getWidth(), backBitmap.getHeight() / 2f / (float) backTexture.getHeight()
			});

			FlipRenderer03.checkError(gl);

			backBitmap.recycle();
			backBitmap = null;
		}
	}

	public void invalidateTexture() {
		//Texture is vanished when the gl context is gone, no need to delete it explicitly
		frontTexture = null;
		backTexture = null;
	}

	private float lastY = -1;

	public boolean handleTouchEvent(MotionEvent event) {
		if (frontTexture == null)
			return false;

		float delta;

		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				lastY = event.getY();
				setState(STATE_TOUCH);
				return true;
			case MotionEvent.ACTION_MOVE:
				delta = lastY - event.getY();
				rotateBy(180 * delta / frontTexture.getContentHeight() * MOVEMENT_RATE);
				lastY = event.getY();
				return true;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				delta = lastY - event.getY();
				rotateBy(180 * delta / frontTexture.getContentHeight() * MOVEMENT_RATE);
				if (angle < 90)
					forward = false;
				else
					forward = true;
				setState(STATE_AUTO_ROTATE);
				return true;
		}

		return false;
	}
}
