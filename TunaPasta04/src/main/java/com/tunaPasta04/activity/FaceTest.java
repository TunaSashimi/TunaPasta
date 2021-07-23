package com.tunaPasta04.activity;

import java.io.IOException;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.tunaPasta04.R;
import com.tunaPasta04.tool.NativeTool;
import com.tunaPasta04.widget.FaceDetectView;

public class FaceTest extends Activity implements SurfaceHolder.Callback, Camera.ShutterCallback, Camera.PictureCallback {
    private Camera camera;
    private SurfaceView surfaceView;
    private SurfaceHolder holder;
    private Bitmap bitmapcamera;
    private int screenHeight; // 屏幕高度
    private int cameraCount; // 摄像头数量,2为有前置摄像头的手机,1为普通手机,按类型处理图片选择角度
    private LinearLayout linearlayout;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    camera.takePicture(null, null, FaceTest.this);
//				surfaceView.setDrawingCacheEnabled(true);
//				surfaceView.setDrawingCacheEnabled(false);
//				bitmapcamera=surfaceView.getDrawingCache();

//				Bitmap bitmap = Bitmap.createBitmap(screenHeight / 2, (int) (screenHeight / 2.0 / 0.75), Bitmap.Config.RGB_565);
//				Canvas canvas = new Canvas(bitmap);
//				surfaceView.draw(canvas);
//				NativeRequest.savePicture(bitmap, "/image01.jpg");
//				
//				startActivity(new Intent(MyFaceAct.this, Result.class));
//				finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facetest);

        screenHeight = NativeTool.getScreenHeight(this);

        surfaceView = findViewById(R.id.surfaceview);
        linearlayout = findViewById(R.id.layout01);

        holder = surfaceView.getHolder(); // 为了实现照片预览功能，需要将SurfaceHolder的类型设置为PUSH

        holder.addCallback(this); // 这样，画图缓存就由Camera类来管理，画图缓存是独立于Surface的
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        cameraCount = Camera.getNumberOfCameras(); // 获取摄像头数量
        camera = Camera.open(cameraCount - 1);                // 当Surface被创建的时候，该方法被调用，可以在这里实例化Camera对象
        try {
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
            camera.release();
            camera = null;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        if (Integer.parseInt(Build.VERSION.SDK) >= 8) {
            camera.setDisplayOrientation(90); // 旋转90度!
        }
        camera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.setPreviewCallback(null); //这个必须在前，不然退出出错
        camera.stopPreview(); // 需要释放资源~~
        camera.release();
        camera = null;
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        if (data != null) {
            BitmapFactory.Options bitmapfactoryoptions = new BitmapFactory.Options();
            bitmapfactoryoptions.inPreferredConfig = Bitmap.Config.RGB_565; // 输入图片必须为bitmapRBG565格式
            bitmapcamera = BitmapFactory.decodeByteArray(data, 0, data.length, bitmapfactoryoptions);
            Matrix matrix = new Matrix();
            if (cameraCount == 1) { // 按照摄像头的数量算旋转的角度
                matrix.setRotate(90, (float) bitmapcamera.getWidth() / 2, (float) bitmapcamera.getHeight() / 2); // 图片旋转~
            } else {
                matrix.setRotate(270, (float) bitmapcamera.getWidth() / 2, (float) bitmapcamera.getHeight() / 2); // 图片旋转~
            }

            // 照相机的宽高比为0.75;
            bitmapcamera = Bitmap.createBitmap(bitmapcamera, 0, 0, bitmapcamera.getWidth(), bitmapcamera.getHeight(), matrix, true);
            bitmapcamera = Bitmap.createScaledBitmap(bitmapcamera, screenHeight / 2, (int) (screenHeight / 2.0 / 0.75), false); // 这样造出来的facedecte控件大小为屏幕大小的1/2

            if (linearlayout.getVisibility() == View.GONE) {
                final Dialog dialog = new Dialog(this, R.style.Theme_NoTitleDialog);// 后面的一项为设置风格,使对话框无标题栏
                dialog.setContentView(R.layout.facetestitem);

                // 动态设置facedetectview大小
                FaceDetectView facedecte = (FaceDetectView) dialog.findViewById(R.id.myface);
                LayoutParams para = facedecte.getLayoutParams();
                para.width = screenHeight / 2;
                para.height = (int) (screenHeight / 2.0 / 0.75);
                facedecte.setLayoutParams(para);

                facedecte.set(bitmapcamera);
                facedecte.invalidate();

                (dialog.findViewById(R.id.btn_save)).setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        NativeTool.savePicture(bitmapcamera, "/image00.jpg");
                        dialog.dismiss();
                        linearlayout.setVisibility(View.VISIBLE);

                        new Thread() {
                            public void run() {
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                handler.sendEmptyMessage(0);
                            }
                        }.start();
                    }
                });
                (dialog.findViewById(R.id.btn_cancel)).setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                camera.startPreview();
            } else {
                NativeTool.savePicture(bitmapcamera, "/image01.jpg");
                startActivity(new Intent(this, Result.class));
                finish();
            }
        }
    }

    public void onShutter() {
    }

    public void onViewClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.image02:
                camera.takePicture(null, null, this);// 第一个为拍照声音,第二个为数据,第三个位jpej格式
                break;
        }
    }
}
