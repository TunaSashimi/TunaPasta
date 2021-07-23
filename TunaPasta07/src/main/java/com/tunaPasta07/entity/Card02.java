package com.tunaPasta07.entity;

import static javax.microedition.khronos.opengles.GL10.GL_BACK;
import static javax.microedition.khronos.opengles.GL10.GL_BLEND;
import static javax.microedition.khronos.opengles.GL10.GL_CCW;
import static javax.microedition.khronos.opengles.GL10.GL_CLAMP_TO_EDGE;
import static javax.microedition.khronos.opengles.GL10.GL_CULL_FACE;
import static javax.microedition.khronos.opengles.GL10.GL_DEPTH_TEST;
import static javax.microedition.khronos.opengles.GL10.GL_FLOAT;
import static javax.microedition.khronos.opengles.GL10.GL_LIGHTING;
import static javax.microedition.khronos.opengles.GL10.GL_ONE_MINUS_SRC_ALPHA;
import static javax.microedition.khronos.opengles.GL10.GL_SRC_ALPHA;
import static javax.microedition.khronos.opengles.GL10.GL_TEXTURE_2D;
import static javax.microedition.khronos.opengles.GL10.GL_TEXTURE_COORD_ARRAY;
import static javax.microedition.khronos.opengles.GL10.GL_TEXTURE_WRAP_S;
import static javax.microedition.khronos.opengles.GL10.GL_TEXTURE_WRAP_T;
import static javax.microedition.khronos.opengles.GL10.GL_TRIANGLES;
import static javax.microedition.khronos.opengles.GL10.GL_UNSIGNED_SHORT;
import static javax.microedition.khronos.opengles.GL10.GL_VERTEX_ARRAY;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import com.tunaPasta07.tools.TextureUtils;
import com.tunaPasta07.widget.FlipRenderer01;

public class Card02 {
	private static final int MAX_ANGLE = 75;
	private static final float SPEED = 1.5f;

	private float cardVertices[];

	private short[] indices = { 0, 1, 2, 0, 2, 3 };

	private FloatBuffer vertexBuffer;

	private ShortBuffer indexBuffer;

	private float textureCoordinates[];

	private FloatBuffer textureBuffer;

	private Texture02 texture;

	private float angle = 0f;

	private boolean animating = false;

	private boolean forward = true;

	private boolean dirty = false;

	public Texture02 getTexture() {
		return texture;
	}

	public void setTexture(Texture02 texture) {
		this.texture = texture;
	}

	public void setCardVertices(float[] cardVertices) {
		this.cardVertices = cardVertices;
		this.dirty = true;
	}

	public void setTextureCoordinates(float[] textureCoordinates) {
		this.textureCoordinates = textureCoordinates;
		this.dirty = true;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public void setAnimating(boolean animating) {
		this.animating = animating;
	}

	public void draw(GL10 gl) {
		if (dirty)
			updateVertices();

		if (cardVertices == null)
			return;

		gl.glFrontFace(GL_CCW);

		gl.glEnable(GL_CULL_FACE);
		gl.glCullFace(GL_BACK);

		gl.glEnableClientState(GL_VERTEX_ARRAY);

		gl.glEnable(GL_BLEND);
		gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		gl.glColor4f(1f, 1.0f, 1f, 1.0f);

		if (TextureUtils.isValidTexture02(texture)) {
			gl.glEnable(GL_TEXTURE_2D);
			gl.glEnableClientState(GL_TEXTURE_COORD_ARRAY);
			gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S,
					GL_CLAMP_TO_EDGE);
			gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T,
					GL_CLAMP_TO_EDGE);
			gl.glTexCoordPointer(2, GL_FLOAT, 0, textureBuffer);
			gl.glBindTexture(GL_TEXTURE_2D, texture.getId()[0]);
		}

		FlipRenderer01.checkError(gl);

		gl.glPushMatrix();

		if (angle > 0) {
			gl.glTranslatef(0, cardVertices[1], 0f);
			gl.glRotatef(-angle, 1f, 0f, 0f);
			gl.glTranslatef(0, -cardVertices[1], 0f);
		}

		if (animating) {
			if (angle >= MAX_ANGLE)
				forward = false;
			if (angle <= 1)
				forward = true;

			if (forward)
				angle += SPEED;
			else
				angle -= SPEED;
		}

		gl.glVertexPointer(3, GL_FLOAT, 0, vertexBuffer);
		gl.glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_SHORT,
				indexBuffer);

		FlipRenderer01.checkError(gl);

		gl.glPopMatrix();

		if (TextureUtils.isValidTexture02(texture)) {
			gl.glDisableClientState(GL_TEXTURE_COORD_ARRAY);
			gl.glDisable(GL_TEXTURE_2D);
		}

		if (angle > 0) {
			gl.glDisable(GL_LIGHTING);
			gl.glDisable(GL_DEPTH_TEST);
			float w = cardVertices[9] - cardVertices[0];
			float h = (float) ((cardVertices[1] - cardVertices[4])
								* (1f - Math.cos(TextureUtils.d2r(angle))));
			float z = (float) ((cardVertices[1] - cardVertices[4])
								* Math.sin(TextureUtils.d2r(angle)));
			float[] shadowVertices = new float[] { cardVertices[0],
					h + cardVertices[4], z, cardVertices[3], cardVertices[4],
					0f, w, cardVertices[7], 0f, w, h + cardVertices[4], z };

			float alpha = 1f * (90f - angle) / 90f;

			gl.glColor4f(0f, 0.0f, 0f, alpha);
			gl.glVertexPointer(3, GL_FLOAT, 0,
					TextureUtils.toFloatBuffer(shadowVertices));
			gl.glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_SHORT,
					indexBuffer);
			gl.glEnable(GL_DEPTH_TEST);
			gl.glEnable(GL_LIGHTING);
		}

		FlipRenderer01.checkError(gl);

		gl.glDisable(GL_BLEND);
		gl.glDisableClientState(GL_VERTEX_ARRAY);
		gl.glDisable(GL_CULL_FACE);
	}

	private void updateVertices() {
		vertexBuffer = TextureUtils.toFloatBuffer(cardVertices);
		indexBuffer = TextureUtils.toShortBuffer(indices);
		textureBuffer = TextureUtils.toFloatBuffer(textureCoordinates);
	}
}
