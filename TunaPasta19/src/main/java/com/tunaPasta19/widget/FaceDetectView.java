package com.tunaPasta19.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.media.FaceDetector.Face;
import android.util.AttributeSet;
import android.view.View;

public class FaceDetectView extends View {
	private int imageWidth, imageHeight;
	private int facenumber = 5;
	private FaceDetector facedetector;
	private FaceDetector.Face[] myFace;
	private int numberOfFaceDetected;
	private float eyesDistance;
	private Bitmap myBitmap;

	private PointF midPoint = new PointF();
	private Paint facePaint = new Paint();
	private Paint eyePaint = new Paint();
	private Paint paint = new Paint();

	public FaceDetectView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FaceDetectView(Context context) {
		super(context);
	}

	public void set(Bitmap bitmap) {
		myBitmap = bitmap;
		imageWidth = myBitmap.getWidth();
		imageHeight = myBitmap.getHeight();
		myFace = new FaceDetector.Face[facenumber];
		facedetector = new FaceDetector(imageWidth, imageHeight, facenumber);
		numberOfFaceDetected = facedetector.findFaces(myBitmap, myFace);
	}

	protected void onDraw(Canvas canvas) {
		if (myBitmap == null) {
			return;
		}
		canvas.drawBitmap(myBitmap, 0, 0, null);

		facePaint.setColor(0x80ff0000);
		facePaint.setStyle(Paint.Style.STROKE);
		facePaint.setStrokeWidth(3);
		eyePaint.setColor(Color.YELLOW);
		eyePaint.setStyle(Paint.Style.STROKE);
		eyePaint.setStrokeWidth(3);

		for (int i = 0; i < numberOfFaceDetected; i++) {
			Face face = myFace[i];
			face.getMidPoint(midPoint);// 两眼的中点距离
			eyesDistance = face.eyesDistance();// 两眼之间的距离

			canvas.drawRect((int) (midPoint.x - eyesDistance),(int) (midPoint.y - eyesDistance),(int) (midPoint.x + eyesDistance),(int) (midPoint.y + eyesDistance), facePaint);

			canvas.drawCircle((int) (midPoint.x - eyesDistance / 2),midPoint.y, 10.0f, eyePaint);
			canvas.drawCircle((int) (midPoint.x + eyesDistance / 2),midPoint.y, 10.0f, eyePaint);
		}

		paint.setColor(Color.YELLOW);
		paint.setTextSize(25);
		canvas.drawText("共发现了"+numberOfFaceDetected+"张人脸", 100, 100, paint);
	}
}
