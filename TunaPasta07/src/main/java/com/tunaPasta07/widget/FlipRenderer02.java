package com.tunaPasta07.widget;

import static javax.microedition.khronos.opengles.GL10.GL_AMBIENT;
import static javax.microedition.khronos.opengles.GL10.GL_COLOR_BUFFER_BIT;
import static javax.microedition.khronos.opengles.GL10.GL_DEPTH_BUFFER_BIT;
import static javax.microedition.khronos.opengles.GL10.GL_DEPTH_TEST;
import static javax.microedition.khronos.opengles.GL10.GL_LEQUAL;
import static javax.microedition.khronos.opengles.GL10.GL_LIGHT0;
import static javax.microedition.khronos.opengles.GL10.GL_LIGHTING;
import static javax.microedition.khronos.opengles.GL10.GL_MODELVIEW;
import static javax.microedition.khronos.opengles.GL10.GL_NICEST;
import static javax.microedition.khronos.opengles.GL10.GL_PERSPECTIVE_CORRECTION_HINT;
import static javax.microedition.khronos.opengles.GL10.GL_POSITION;
import static javax.microedition.khronos.opengles.GL10.GL_PROJECTION;
import static javax.microedition.khronos.opengles.GL10.GL_SMOOTH;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.view.View;

import com.tunaPasta07.entity.FlipCards02;
import com.tunaPasta07.tools.TextureUtils;

public class FlipRenderer02 implements GLSurfaceView.Renderer {

	private FlipViewGroup01 flipViewGroup;

	private FlipCards02 cards;
	
	private boolean created = false;

	public FlipRenderer02(FlipViewGroup01 flipViewGroup) {
		this.flipViewGroup = flipViewGroup;

		cards = new FlipCards02();
		
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glShadeModel(GL_SMOOTH);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL_DEPTH_TEST);
		gl.glDepthFunc(GL_LEQUAL);
		gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		
		created = true;
		
		cards.invalidateTexture();
		flipViewGroup.reloadTexture();
		
	}

	public static float[] light0Position = {0, 0, 100f, 0f};

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);

		gl.glMatrixMode(GL_PROJECTION);
		gl.glLoadIdentity();

		float fovy = 20f;
		float eyeZ = height / 2f / (float) Math.tan(TextureUtils.d2r(fovy / 2));

		GLU.gluPerspective(gl, fovy, (float) width / (float) height, 0.5f, Math.max(1500.0f, eyeZ));

		gl.glMatrixMode(GL_MODELVIEW);
		gl.glLoadIdentity();


		GLU.gluLookAt(gl,
			width / 2.0f, height / 2f, eyeZ,
			width / 2.0f, height / 2.0f, 0.0f,
			0.0f, 1.0f, 0.0f
		);

		gl.glEnable(GL_LIGHTING);
		gl.glEnable(GL_LIGHT0);

		float lightAmbient[] = new float[]{3.5f, 3.5f, 3.5f, 1f};
		gl.glLightfv(GL_LIGHT0, GL_AMBIENT, lightAmbient, 0);

		light0Position = new float[]{0, 0, eyeZ, 0f};
		gl.glLightfv(GL_LIGHT0, GL_POSITION, light0Position, 0);

	}

	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		cards.draw(gl);
	}

	public void updateTexture(View view) {
		if (created) {
			cards.reloadTexture(view);
		}
	}

	public static void checkError(GL10 gl) {
		int error = gl.glGetError();
		if (error != 0) {
			throw new RuntimeException(GLU.gluErrorString(error));
		}
	}
}
