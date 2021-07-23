package com.tunaPasta07.entity;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.view.View;

import com.tunaPasta07.tools.GrabIt;
import com.tunaPasta07.widget.FlipRenderer02;

public class FlipCards02 {
	private Texture02 texture;
	private Bitmap bitmap;

	private Card02 topCard;
	private Card02 bottomCard;

	public FlipCards02() {
		topCard = new Card02();
		bottomCard = new Card02();

		bottomCard.setAnimating(true);
	}

	public void reloadTexture(View view) {
		bitmap = GrabIt.takeScreenshot(view);
	}

	public void draw(GL10 gl) {
		applyTexture(gl);

		if (texture == null)
			return;

		topCard.draw(gl);
		bottomCard.draw(gl);
	}

	private void applyTexture(GL10 gl) {
		if (bitmap != null) {
			if (texture != null)
				texture.destroy(gl);

			texture = Texture02.createTexture(bitmap, gl);

			topCard.setTexture(texture);
			bottomCard.setTexture(texture);

			topCard.setCardVertices(new float[] { 0f, bitmap.getHeight(), 0f, // top
																				// left
					0f, bitmap.getHeight() / 2.0f, 0f, // bottom left
					bitmap.getWidth(), bitmap.getHeight() / 2f, 0f, // bottom
																	// right
					bitmap.getWidth(), bitmap.getHeight(), 0f // top right
			});

			topCard.setTextureCoordinates(new float[] { 0f, 0f, 0,
					bitmap.getHeight() / 2f / (float) texture.getHeight(),
					bitmap.getWidth() / (float) texture.getWidth(),
					bitmap.getHeight() / 2f / (float) texture.getHeight(),
					bitmap.getWidth() / (float) texture.getWidth(), 0f });

			bottomCard.setCardVertices(new float[] { 0f,
					bitmap.getHeight() / 2f, 0f, // top left
					0f, 0, 0f, // bottom left
					bitmap.getWidth(), 0f, 0f, // bottom right
					bitmap.getWidth(), bitmap.getHeight() / 2f, 0f // top right
					});

			bottomCard.setTextureCoordinates(new float[] { 0f,
					bitmap.getHeight() / 2f / (float) texture.getHeight(), 0,
					bitmap.getHeight() / (float) texture.getHeight(),
					bitmap.getWidth() / (float) texture.getWidth(),
					bitmap.getHeight() / (float) texture.getHeight(),
					bitmap.getWidth() / (float) texture.getWidth(),
					bitmap.getHeight() / 2f / (float) texture.getHeight() });

			FlipRenderer02.checkError(gl);

			bitmap.recycle();
			bitmap = null;

		}
	}

	public void invalidateTexture() {
		texture = null;
	}
}
