package com.tunaPasta09.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnTouchModeChangeListener;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.tunaPasta09.R;
import com.tunaPasta09.widget.Rotate3D;

public class ImageRotateTest extends Activity {

    private ImageView imageview01;
    private boolean isChange = false;// isChange用于区别当前图片的状态，为false第一段动画未播放完,为true表示第一段动画播放完了

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.imagerotatetest);

        imageview01 = findViewById(R.id.imageview01);//image01是问号,image02是番茄

        imageview01.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isChange) {
                    applyRotation01(0, 0, -90);//第一段动画左旋90度
                } else {
                    applyRotation01(0, 0, 90); //第一段动画右旋90度
                }
            }
        });

        // 装载动画效果
        ViewTreeObserver viewtreeobserver = imageview01.getViewTreeObserver();
        viewtreeobserver.addOnTouchModeChangeListener(new OnTouchModeChangeListener() {
            @Override
            public void onTouchModeChanged(boolean isInTouchMode) {
                applyRotation01(0, 0, -90);//第一段动画左旋90度
            }
        });
    }

    /*
     * 应用变换的方法，里面将会使用之前写好的Rotate3d类
     */
    private void applyRotation01(int position, float start, float end) {
        // 计算中心点
        final float centerX = getResources().getDimension(R.dimen.activity_image_width) / 2.0f;
        final float centerY = getResources().getDimension(R.dimen.activity_image_height) / 2.0f;

        final Rotate3D rotation = new Rotate3D(start, end, centerX, centerY, 310.0f, true);
        rotation.setDuration(2000); // 可设置翻转的时间，以ms为单位
        rotation.setFillAfter(true);
        // 设置监听
        rotation.setAnimationListener(new FirstAnimationListener01());
        imageview01.startAnimation(rotation); // 开始翻转前90度
    }

    private void applyRotation02(int position, float start, float end) {
        final float centerX = getResources().getDimension(R.dimen.activity_image_width) / 2.0f;
        final float centerY = getResources().getDimension(R.dimen.activity_image_height) / 2.0f;

        final Rotate3D rotation = new Rotate3D(start, end, centerX, centerY, 310.0f, true);
        rotation.setDuration(1000); // 可设置翻转的时间，以ms为单位
        rotation.setFillAfter(true);
        rotation.setAnimationListener(new SecondAnimationListener01());
        imageview01.startAnimation(rotation); // 开始翻转前90度
    }

    /*
     * 这个类用于监听第一段动画前90度翻转完成
     */
    private final class FirstAnimationListener01 implements Animation.AnimationListener {
        private FirstAnimationListener01() {
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
//     前90度翻转完成后，根据图片的状态翻转第二张图片的90度
            if (!isChange) {
                imageview01.setImageResource(R.drawable.imageview_background02);
                imageview01.post(new SwapViewsRunnable01(0));
            } else {
                imageview01.setImageResource(R.drawable.imageview_background01);
                imageview01.post(new SwapViewsRunnable01(1));
            }
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    /*
     * 这个类用于监听第一段动画后90度翻转完成
     */
    private final class FirstAnimationListener02 implements Animation.AnimationListener {
        private FirstAnimationListener02() {
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            applyRotation02(0, 0, -90);//第一段动画左旋90度
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    /*
     * 这个类用于监听第二段动画前90度翻转完成
     */
    private final class SecondAnimationListener01 implements Animation.AnimationListener {
        private SecondAnimationListener01() {
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {

            if (!isChange) {
                imageview01.setImageResource(R.drawable.imageview_background02);
                imageview01.post(new SwapViewsRunnable02(0));
            } else {
                imageview01.setImageResource(R.drawable.imageview_background01);
                imageview01.post(new SwapViewsRunnable02(1));
            }

        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    /**
     * 这个类用于翻转第一个动画剩下的90度
     */
    private final class SwapViewsRunnable01 implements Runnable {
        private final int mdirection;

        // 我把API的例子改了，这里用一个方向变量来指明剩下的90度应该怎么翻转。
        public SwapViewsRunnable01(int direction) {
            mdirection = direction;
        }

        public void run() {
            final float centerX = imageview01.getWidth() / 2.0f;
            final float centerY = imageview01.getHeight() / 2.0f;
            Rotate3D rotation;
            if (mdirection == 0) {
                rotation = new Rotate3D(90, 0, centerX, centerY, 310.0f, false);
                isChange = true;// 待翻转完成后，修改图片状态
            } else {
                rotation = new Rotate3D(-90, 0, centerX, centerY, 310.0f, false);
                isChange = false;
            }
            rotation.setDuration(1000);
            rotation.setFillAfter(true);
            rotation.setAnimationListener(new FirstAnimationListener02());
            rotation.setInterpolator(new DecelerateInterpolator());
            imageview01.startAnimation(rotation); // 开始翻转余下的90度
        }
    }

    /**
     * 这个类用于翻转第一个动画剩下的90度
     */
    private final class SwapViewsRunnable02 implements Runnable {
        private final int mdirection;

        // 我把API的例子改了，这里用一个方向变量来指明剩下的90度应该怎么翻转。
        public SwapViewsRunnable02(int direction) {
            mdirection = direction;
        }

        public void run() {
            final float centerX = imageview01.getWidth() / 2.0f;
            final float centerY = imageview01.getHeight() / 2.0f;
            Rotate3D rotation;
            if (mdirection == 0) {
                rotation = new Rotate3D(90, 0, centerX, centerY, 310.0f, false);
                isChange = true;// 待翻转完成后，修改图片状态
            } else {
                rotation = new Rotate3D(-90, 0, centerX, centerY, 310.0f, false);
                isChange = false;
            }
            rotation.setDuration(1000);
            rotation.setFillAfter(true);
            rotation.setInterpolator(new DecelerateInterpolator());
            imageview01.startAnimation(rotation); // 开始翻转余下的90度
        }
    }
}
