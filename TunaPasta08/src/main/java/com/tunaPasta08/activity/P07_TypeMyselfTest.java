package com.tunaPasta08.activity;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tunaPasta08.R;
import com.tunaPasta08.entity.Cloth;
import com.tunaPasta08.widget.FingerDrawView;

public class P07_TypeMyselfTest extends Activity implements SurfaceHolder.Callback, Camera.ShutterCallback, Camera.PictureCallback {

    FingerDrawView fingerDrawView;           // 绘画用的自定义view
    float anchorX, anchorY;                     // 当一个手指按下去如果正好在控件内,所按点与控件左上角点的间距~
    double originDistance;                      // 当屏幕上有两只手指之间的距离

    ImageView[] imageViewArrary;                // 衣服加载按钮的ImageView组
    LinearLayout linearLayout;                  // surfaceview(照相机)的上层,放置图片按钮和背景来遮盖影像
    ArrayList<Cloth> clothList;                 // 所有加载的衣服的集合~~
    Button button1, button2;                    // 第一个按钮切换模特与相机,第二个按钮用来拍照~
    int screenWidth, screenHeight;              // 屏幕的宽和高值~
    Camera camera;
    SurfaceView surfaceview;                    // 照相机拍照内容的绘图层
    SurfaceHolder holder;

    ImageView shotimage, delectimage;

    View view;                                  // 全屏截图
    Bitmap bitmap;
    ImageView imageview01, imageview02, imageview03, imageview04, imageview05, imageview06;    //加载衣服按钮的亮和暗~

    Animation rotateAnimation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p07_typemyselftest);

        rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        screenWidth = display.getWidth();
        screenHeight = display.getHeight();

        surfaceview = findViewById(R.id.surfaceview);
        holder = surfaceview.getHolder();            //为了实现照R.id.片预览功能，需要将SurfaceHolder的类型设置为PUSH
        holder.addCallback(this);                    //这样，画图缓存就由Camera类来管理，画图缓存是独立于Surface的
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        linearLayout = findViewById(R.id.linearlayout);
        linearLayout.setBackgroundResource(R.drawable.screenshot);            //首先设置为默认的背景

        shotimage = findViewById(R.id.shotimage);               //所有的image都在这里得到~
        delectimage = findViewById(R.id.delectimage);

        view = this.getWindow().getDecorView();                                            //上面的代码,为获取全屏截图的代码
        view.layout(0, 0, screenWidth, screenHeight);
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(false);        //这行是清除缓存的代码,getDrawingCache的源码，里面可能有缓存,所以一定要设置成false

        fingerDrawView = findViewById(R.id.fingerdrawview);

        imageview01 = findViewById(R.id.imageview01);
        imageview02 = findViewById(R.id.imageview02);
        imageview03 = findViewById(R.id.imageview03);
        imageview04 = findViewById(R.id.imageview04);
        imageview05 = findViewById(R.id.imageview05);
        imageview06 = findViewById(R.id.imageview06);

        clothList = new ArrayList();
        fingerDrawView.clothList = clothList;            //注意先要获取drawview才能绑定drawview.clothList

        imageViewArrary = new ImageView[6];
        for (int i = 1; i <= imageViewArrary.length; i++) {
            int id = getResources().getIdentifier("imageview0" + i, "id", "com.tunaPasta08");//注意下这里的路径~
            imageViewArrary[i - 1] = (ImageView) findViewById(id);
            final int index = i;
            imageViewArrary[i - 1].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //以下为后期添加的代码~~
                    imageview01.setBackgroundResource(R.drawable.b01);
                    imageview02.setBackgroundResource(R.drawable.b02);
                    imageview03.setBackgroundResource(R.drawable.b03);
                    imageview04.setBackgroundResource(R.drawable.b04);
                    imageview05.setBackgroundResource(R.drawable.b05);
                    imageview06.setBackgroundResource(R.drawable.b06);

                    int seat = (int) (screenWidth * 2.0 / 3.0);
                    long timeMiles = System.currentTimeMillis();

                    switch (v.getId()) {
                        case R.id.imageview01:
                            initCloth(R.drawable.dress_hat, seat, seat, 1, imageViewArrary[index - 1], R.drawable.b01d, timeMiles);
                            break;
                        case R.id.imageview02:
                            initCloth(R.drawable.dress_tshirt, seat, seat, 2, imageViewArrary[index - 1], R.drawable.b02d, timeMiles);
                            break;
                        case R.id.imageview03:
                            initCloth(R.drawable.dress_coat, seat, seat, 3, imageViewArrary[index - 1], R.drawable.b03d, timeMiles);
                            break;
                        case R.id.imageview04:
                            initCloth(R.drawable.dress_skirt, seat, seat, 4, imageViewArrary[index - 1], R.drawable.b04d, timeMiles);
                            break;
                        case R.id.imageview05:
                            initCloth(R.drawable.dress_pants, seat, seat, 5, imageViewArrary[index - 1], R.drawable.b05d, timeMiles);
                            break;
                        case R.id.imageview06:
                            initCloth(R.drawable.dress_decoration, seat, seat, 6, imageViewArrary[index - 1], R.drawable.b06d, timeMiles);
                            break;
                    }
                }
            });
        }
        button1 = findViewById(R.id.button1);
        button1.setText("实景");
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("实景".equals(button1.getText().toString())) {
                    linearLayout.getBackground().setAlpha(0);
                    button1.setText("模特");
                } else {
                    linearLayout.getBackground().setAlpha(255);
                    button1.setText("实景");
                }
            }
        });
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("实景".equals(button1.getText().toString())) {
                    Bitmap bitmap = view.getDrawingCache();
                    shotimage.setImageBitmap(bitmap);
                } else {
                    camera.takePicture(P07_TypeMyselfTest.this, P07_TypeMyselfTest.this, P07_TypeMyselfTest.this);
                }
            }
        });
        shotimage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("模特".equals(button1.getText().toString())) {
                    Dialog dialog = new Dialog(P07_TypeMyselfTest.this, R.style.Theme_NoTitleDialog);//后面的一项为设置风格,使对话框无标题栏
                    dialog.setContentView(R.layout.p07_typemyself_item);
                    ImageView imm = dialog.findViewById(R.id.myweatherli);
                    imm.setImageBitmap(bitmap);
                    dialog.show();
                }
            }
        });
    }

    private void initCloth(int picture, int x, int y, int type, ImageView iv, int background, long timeMiles) {
        Cloth cloth = new Cloth(getResources().getDrawable(picture), x, y, type, timeMiles);
        clothList.add(cloth);
        iv.setBackgroundResource(background);
        fingerDrawView.invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (clothList != null && clothList.size() > 0) {                                                    // 如果cloth不为空的话执行以下代码~

            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:                        // 唯一指落下的点正好在图片上~设置随指移动属性
                    label:
                    for (Cloth clo : clothList) {
                        if (event.getX() >= clo.x && event.getX() <= clo.x + clo.width
                            && event.getY() >= clo.y
                            && event.getY() <= clo.y + clo.height) {
                            clo.canMove = true;
                            clo.timeMiles = System.currentTimeMillis();

                            //以下为后期添加的代码~~
                            imageview01.setBackgroundResource(R.drawable.b01);
                            imageview02.setBackgroundResource(R.drawable.b02);
                            imageview03.setBackgroundResource(R.drawable.b03);
                            imageview04.setBackgroundResource(R.drawable.b04);
                            imageview05.setBackgroundResource(R.drawable.b05);
                            imageview06.setBackgroundResource(R.drawable.b06);
                            switch (clo.type) {
                                case 1:
                                    imageview01.setBackgroundResource(R.drawable.b01d);
                                    break;
                                case 2:
                                    imageview02.setBackgroundResource(R.drawable.b02d);
                                    break;
                                case 3:
                                    imageview03.setBackgroundResource(R.drawable.b03d);
                                    break;
                                case 4:
                                    imageview04.setBackgroundResource(R.drawable.b04d);
                                    break;
                                case 5:
                                    imageview05.setBackgroundResource(R.drawable.b05d);
                                    break;
                                case 6:
                                    imageview06.setBackgroundResource(R.drawable.b06d);
                                    break;
                                default:
                                    break;
                            }
                            anchorX = event.getX() - clo.x;                     // ~定义锚点按下点与左上角点的锚点间距~
                            anchorY = event.getY() - clo.y;
                            break label;                                            // 只要有一个list满足条件就终止操作
                        }
                    }
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:    // 第二指落下
                    originDistance = Math.sqrt((event.getX(0) - event.getX(1))
                        * (event.getX(0) - event.getX(1))
                        + (event.getY(0) - event.getY(1))
                        * (event.getY(0) - event.getY(1)));
                    if (originDistance > 10) {                                    // 计算第二指落下点与第一指的距离，如果大于10像素，保留初值间距~设置可调整属性~
                        label:
                        for (Cloth clo : clothList) {
                            if (clo.canMove) {
                                clo.canChange = true;
                                break label;
                            }
                        }
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    for (int i = 0; i < clothList.size(); i++) {
                        if (clothList.get(i).canChange) {
                            double changedistence = (float) Math.sqrt((event.getX(0) - event.getX(1))
                                * (event.getX(0) - event.getX(1))
                                + (event.getY(0) - event.getY(1))
                                * (event.getY(0) - event.getY(1)));
                            if (changedistence > 10) {
                                double difference = changedistence - originDistance;
                                if (difference > 10) {
                                    clothList.get(i).width = (int) (1.05 * clothList.get(i).width);    // 即时修改宽度和高度~
                                    clothList.get(i).height = (int) (clothList.get(i).width * clothList.get(i).proportion);
                                } else if (difference < -10) {
                                    clothList.get(i).width = (int) (0.95 * clothList.get(i).width);
                                    clothList.get(i).height = (int) (clothList.get(i).width * clothList.get(i).proportion);
                                }
                                fingerDrawView.invalidate();                                                        //两个invalidate
                            }
                        } else if (clothList.get(i).canMove) {                                                // 如果是随指移动属性为真~
                            clothList.get(i).x = (int) (event.getX() - anchorX);                    // 即时修改cloth的x和y值
                            clothList.get(i).y = (int) (event.getY() - anchorY);
                            if (clothList.get(i).x + clothList.get(i).width >= screenWidth * 0.95f && clothList.get(i).y <= 150) {
                                clothList.get(i).x = screenWidth;
                                clothList.get(i).y = screenHeight;
                                clothList.remove(i);                                                            //就是这里出的错,所以不能用foreach来迭代~
                                delectimage.startAnimation(rotateAnimation);
                            }
                            if (clothList.size() == 0) {
                                imageview01.setBackgroundResource(R.drawable.b01);
                                imageview02.setBackgroundResource(R.drawable.b02);
                                imageview03.setBackgroundResource(R.drawable.b03);
                                imageview04.setBackgroundResource(R.drawable.b04);
                                imageview05.setBackgroundResource(R.drawable.b05);
                            }
                            fingerDrawView.invalidate();
                        }
                    }
                    break;
                case MotionEvent.ACTION_POINTER_UP:            // 第二指抬起
                    for (Cloth clo : clothList) {
                        clo.canChange = false;
                    }
                    break;
                case MotionEvent.ACTION_UP:                      // 唯一指抬起
                    for (Cloth clo : clothList) {
                        clo.canMove = false;                    // 迭代取消canMove属性
                    }
                    break;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (Integer.parseInt(Build.VERSION.SDK) >= 8) {
            camera.setDisplayOrientation(90);    //
        }
        camera.startPreview();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        camera = Camera.open();                        //当Surface被创建的时候，该方法被调用，可以在这里实例化Camera对象
        try {
            camera.setPreviewDisplay(holder);    //同时可以对Camera进行定制
        } catch (IOException e) {
            e.printStackTrace();
            camera.release();
            camera = null;
        }
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
            fingerDrawView.setDrawingCacheEnabled(true);
            Bitmap bitmapdraw = Bitmap.createBitmap(fingerDrawView.getDrawingCache());//这里只能用左面的方法来获得图像!
            fingerDrawView.setDrawingCacheEnabled(false);

            Bitmap bitmapcamera = BitmapFactory.decodeByteArray(data, 0, data.length);
            Matrix m = new Matrix();
            m.setRotate(90, (float) bitmapcamera.getWidth() / 2, (float) bitmapcamera.getHeight() / 2);                                    //图片旋转~
//			注意这里的代码控制旋转~~!!
            bitmapcamera = Bitmap.createBitmap(bitmapcamera, 0, 0, bitmapcamera.getWidth(), bitmapcamera.getHeight(), m, true);
            bitmapcamera = Bitmap.createScaledBitmap(bitmapcamera, screenWidth, screenHeight, false);                                //图片的缩放为屏幕的大小

            Bitmap bitmaptotal = Bitmap.createBitmap(screenWidth, screenHeight, Config.ARGB_8888);            //创建一个新的和SRC源图长度宽度一样的位图
            Canvas canvas = new Canvas(bitmaptotal);
            canvas.drawBitmap(bitmapcamera, 0, 0, null);    //在0,0坐标开始画入src
            canvas.drawBitmap(bitmapdraw, 0, 0, null);        //在0,0坐标开始画入src
            bitmap = bitmaptotal;
            shotimage.setImageBitmap(bitmaptotal);        //这里开始,应该是newbitmap了
            camera.startPreview();
        }
    }

    @Override
    public void onShutter() {
    }
}
