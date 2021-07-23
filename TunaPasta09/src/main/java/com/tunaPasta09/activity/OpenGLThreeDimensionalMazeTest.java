package com.tunaPasta09.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLUtils;
import android.os.Bundle;
import android.view.KeyEvent;

import com.tunaPasta09.R;

public class OpenGLThreeDimensionalMazeTest extends Activity {
	ThreeDimensionalMazeRenderer render = new ThreeDimensionalMazeRenderer();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ThreeDimensionalMazeImage.load(this.getResources());
		new GLFile(this.getResources());
		GLSurfaceView glView = new GLSurfaceView(this);

		glView.setRenderer(render);
		setContentView(glView);
	}

	public boolean onKeyUp(int keyCode, KeyEvent event) {
		render.onKeyUp(keyCode, event);
		return false;
	}
}

class ThreeDimensionalMazeImage {
	public static Bitmap mBitmap;

	public static void load(Resources resources) {
		mBitmap = BitmapFactory.decodeResource(resources, R.drawable.threepyramidandcuberotation);
	}
}

class GLFile {
	public static Resources resources;

	public GLFile(Resources resources) {
		GLFile.resources = resources;
	}

	public static InputStream getFile(String name) {
		AssetManager am = GLFile.resources.getAssets();
		try {
			return am.open(name);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}

class ThreeDimensionalMazeRenderer implements Renderer {
	SECTOR sector1 = new SECTOR();
	int[] texture = new int[3];

	public final static float piover180 = 0.0174532925f;
	float heading;
	float xpos;
	float zpos;

	float yrot; // Y Rotation
	float walkbias = 0;
	float walkbiasangle = 0;
	float lookupdown = 0.0f;
	float z = 0.0f;

	// 读取资源数据
	public void SetupWorld() {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				GLFile.getFile("threedimensionalmazedata/threedimensionalmazeworld.txt")));

		TRIANGLE triangle = new TRIANGLE();
		int vertexIndex = 0;

		try {
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.trim().length() <= 0 || line.startsWith("/")) {
					continue;
				}
				String part[] = line.trim().split("\\s+");
				float x = Float.valueOf(part[0]);
				float y = Float.valueOf(part[1]);
				float z = Float.valueOf(part[2]);
				float u = Float.valueOf(part[3]);
				float v = Float.valueOf(part[4]);
				VERTEX vertex = new VERTEX(x, y, z, u, v);
				triangle.vertex[vertexIndex] = vertex;

				vertexIndex++;
				if (vertexIndex == 3) {
					vertexIndex = 0;
					sector1.triangle.add(triangle);
					triangle = new TRIANGLE();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void LoadGLTextures(GL10 gl) {
		IntBuffer textureBuffer = IntBuffer.allocate(3);

		gl.glGenTextures(3, textureBuffer);

		texture[0] = textureBuffer.get();
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture[0]);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				GL10.GL_NEAREST);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				GL10.GL_NEAREST);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, ThreeDimensionalMazeImage.mBitmap, 0);

		texture[1] = textureBuffer.get(2);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture[1]);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				GL10.GL_LINEAR);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				GL10.GL_LINEAR);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, ThreeDimensionalMazeImage.mBitmap, 0);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT); // Clear
																			// The
																			// Screen
																			// And
																			// The
																			// Depth
																			// Buffer
		gl.glLoadIdentity(); // Reset The View

		float xtrans = -xpos;
		float ztrans = -zpos;
		float ytrans = -walkbias - 0.25f;
		float sceneroty = 360.0f - yrot;

		//FloatBuffer vertexPointer = FloatBuffer.wrap(new float[9]);
		FloatBuffer vertexPointer = BufferUtil.fBuffer(new float[9]);
		
		//FloatBuffer texCoordPointer = FloatBuffer.wrap(new float[6]);
		FloatBuffer texCoordPointer = BufferUtil.fBuffer(new float[6]);
		
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexPointer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texCoordPointer);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

		gl.glLoadIdentity();

		gl.glRotatef(lookupdown, 1.0f, 0.0f, 0.0f);
		gl.glRotatef(sceneroty, 0.0f, 1.0f, 0.0f);

		gl.glTranslatef(xtrans, ytrans, ztrans);

		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture[1]);

		for (TRIANGLE triangle : sector1.triangle) {
			vertexPointer.clear();
			texCoordPointer.clear();
			gl.glNormal3f(0.0f, 0.0f, 1.0f);
			for (int i = 0; i < 3; i++) {
				VERTEX vt = triangle.vertex[i];
				vertexPointer.put(vt.x);
				vertexPointer.put(vt.y);
				vertexPointer.put(vt.z);
				texCoordPointer.put(vt.u);
				texCoordPointer.put(vt.v);
			}
			gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 4);
		}

		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		float ratio = (float) width / height;
		// 设置OpenGL场景的大小
		gl.glViewport(0, 0, width, height);
		// 设置投影矩阵
		gl.glMatrixMode(GL10.GL_PROJECTION);
		// 重置投影矩阵
		gl.glLoadIdentity();
		// 设置视口的大小
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
		// 选择模型观察矩阵
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		// 重置模型观察矩阵
		gl.glLoadIdentity();
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		LoadGLTextures(gl);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glClearDepthf(1.0f);
		gl.glDepthFunc(GL10.GL_LESS);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);

		SetupWorld();
	}

	public boolean onKeyUp(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_LEFT:
			yrot -= 1.5f;
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			yrot += 1.5f;
			break;
		case KeyEvent.KEYCODE_DPAD_UP:
			// 沿游戏者所在的X平面移动
			xpos -= (float) Math.sin(heading * piover180) * 0.05f;
			// 沿游戏者所在的Z平面移动
			zpos -= (float) Math.cos(heading * piover180) * 0.05f;
			if (walkbiasangle >= 359.0f)// 如果walkbiasangle大于359度
			{
				walkbiasangle = 0.0f;// 将 walkbiasangle 设为0
			} else {
				walkbiasangle += 10;// 如果 walkbiasangle < 359 ，则增加 10
			}
			// 使游戏者产生跳跃感
			walkbias = (float) Math.sin(walkbiasangle * piover180) / 20.0f;
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			// 沿游戏者所在的X平面移动
			xpos += (float) Math.sin(heading * piover180) * 0.05f;
			// 沿游戏者所在的Z平面移动
			zpos += (float) Math.cos(heading * piover180) * 0.05f;
			// 如果walkbiasangle小于1度
			if (walkbiasangle <= 1.0f) {
				walkbiasangle = 359.0f;// 使 walkbiasangle 等于 359
			} else {
				walkbiasangle -= 10;// 如果 walkbiasangle > 1 减去 10
			}
			// 使游戏者产生跳跃感
			walkbias = (float) Math.sin(walkbiasangle * piover180) / 20.0f;
			break;
		}
		return false;
	}
}

// VERTEX顶点结构
class VERTEX {
	float x, y, z;// 3D 坐标
	float u, v;// 纹理坐标

	public VERTEX(float x, float y, float z, float u, float v) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.u = u;
		this.v = v;
	}
}

// TRIANGLE三角形结构
class TRIANGLE {
	// VERTEX矢量数组，大小为3
	VERTEX[] vertex = new VERTEX[3];
}

// SECTOR区段结构
class SECTOR {
	// Sector中的三角形个数
	int numtriangles;
	// 三角行的list
	List<TRIANGLE> triangle = new ArrayList();
}