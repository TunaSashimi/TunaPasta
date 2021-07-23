package com.tunaPasta04.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.tunaPasta04.R;
import com.tunaPasta04.application.PhotoTrans;

public class CustomCamera extends Activity implements SurfaceHolder.Callback, Camera.ShutterCallback, Camera.PictureCallback {
    private Camera camera;
    private SurfaceView surfaceView;
    private SurfaceHolder holder;
    private Bitmap bitmapcamera;
    private File CompletePathFile;
    private ProgressDialog progressDialog;
    private Handler handler;
    private double density;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customcamera);
        surfaceView = findViewById(R.id.surfaceview);
        holder = surfaceView.getHolder();    //为了实现照片预览功能，需要将SurfaceHolder的类型设置为PUSH
        holder.addCallback(this);                //这样，画图缓存就由Camera类来管理，画图缓存是独立于Surface的
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        getParameters();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                progressDialog.dismiss();
            }
        };
    }

    /**
     * 获取系统的屏幕像素密度~保存在density中
     */
    private void getParameters() {
        WindowManager windowManager = getWindowManager();
        DisplayMetrics metric = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metric);
        density = metric.density;
    }

    public void onViewClick(View arg0) {
        camera.takePicture(this, this, this);//第一个为拍照声音,第二个为数据,第三个位jpej格式
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        camera = Camera.open();                // 当Surface被创建的时候，该方法被调用，可以在这里实例化Camera对象
        try {
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {            //同时可以对Camera进行定制
            e.printStackTrace();
            camera.release();
            camera = null;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (Integer.parseInt(Build.VERSION.SDK) >= 8) {
            camera.setDisplayOrientation(90);    //这句话的代码非常重要!旋转90度!!
        }
        camera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();                            //需要释放资源~~
        camera.release();
        camera = null;
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        if (data != null) {
            bitmapcamera = BitmapFactory.decodeByteArray(data, 0, data.length);
            Matrix matrix = new Matrix();
            matrix.setRotate(90, (float) bitmapcamera.getWidth() / 2, (float) bitmapcamera.getHeight() / 2);                                    //图片旋转~
            bitmapcamera = Bitmap.createBitmap(bitmapcamera, 0, 0, bitmapcamera.getWidth(), bitmapcamera.getHeight(), matrix, true);
//			这里是弹出窗口栏
            final Dialog dialog = new Dialog(this, R.style.Theme_NoTitleDialog);//后面的一项为设置风格,使对话框无标题栏
            dialog.setContentView(R.layout.customcameraitem);
//			设置拍照图片			
            ((ImageView) dialog.findViewById(R.id.myweatherli)).setImageBitmap(bitmapcamera);

            Button btn_save = dialog.findViewById(R.id.btn_save);
            btn_save.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    progressDialog = new ProgressDialog(CustomCamera.this);
                    progressDialog.setMessage("请稍后~~");
                    progressDialog.show();

                    new Thread() {
                        @Override
                        public void run() {
                            saveMyBitmap(bitmapcamera);
                            handler.sendEmptyMessage(0);
                            startActivityForResult(getMyIntent(), 0);
                        }
                    }.start();
                }
            });
            Button btn_cancel = dialog.findViewById(R.id.btn_cancel);
            btn_cancel.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
            camera.startPreview();
        }
    }

    @Override
    public void onShutter() {
    }

    public void saveMyBitmap(Bitmap savebitmap) {
        File picDir = new File(Environment.getExternalStorageDirectory(), "js");// 根据 parent 抽象路径名和 child路径名字符串创建一个新File 实例。
        picDir.mkdirs(); // 创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
        CompletePathFile = new File(picDir, "CustomCamera.jpg");
        try {
            FileOutputStream fileoutput = new FileOutputStream(CompletePathFile);
            savebitmap.compress(Bitmap.CompressFormat.PNG, 100, fileoutput);
            fileoutput.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Intent getMyIntent() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(Uri.fromFile(CompletePathFile), "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 3);
        intent.putExtra("aspectY", 4);
        intent.putExtra("outputX", (int) (180 * density));
        intent.putExtra("outputY", (int) (240 * density));
        intent.putExtra("return-data", true);
        return intent;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;
        Bundle extras = data.getExtras();
        ((PhotoTrans) getApplication()).setBitmap((Bitmap) extras.get("data"));
        startActivity(new Intent(this, GetResult.class));
    }
}
