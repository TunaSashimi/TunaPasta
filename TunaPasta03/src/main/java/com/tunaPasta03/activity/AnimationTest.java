package com.tunaPasta03.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.tunaPasta03.R;

public class AnimationTest extends Activity {
    private ImageView imageView00, imageView01, imageView02;
    private Button btn01, btn02, btn03, btn04, btn05, btn06, btn07;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.animatiotest);

        imageView00 = findViewById(R.id.imageView00);
        imageView01 = findViewById(R.id.imageView01);
        imageView02 = findViewById(R.id.imageView02);

        btn01 = findViewById(R.id.anim_btn01);
        btn02 = findViewById(R.id.anim_btn02);
        btn03 = findViewById(R.id.anim_btn03);
        btn04 = findViewById(R.id.anim_btn04);
        btn05 = findViewById(R.id.anim_btn05);
        btn06 = findViewById(R.id.anim_btn06);
        btn07 = findViewById(R.id.anim_btn07);

        btn01.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//				//可以通过XML资源文件定义的动画
//				Animation anim=AnimationUtils.loadAnimation(AnimationTest.this, R.anim.alpha);
//				image.startAnimation(anim);
//				//使用Java代码定义动画:透明度的渐变,单位是float型,从0f到1.0f,1代表完全不透明~

                AnimationSet animset = new AnimationSet(true);

                Animation alphaAnimation = new AlphaAnimation(1.0f, 0.1f);
                alphaAnimation.setDuration(2000);
                alphaAnimation.setFillAfter(true);
                alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation paramAnimation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation paramAnimation) {
                        imageView00.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation paramAnimation) {
                    }
                });
                Animation scaleAnimation = new ScaleAnimation(1.0f, 1.0f, 1.0f, 0.0f);
                scaleAnimation.setDuration(2000);
                //停留在动画最后的状态
                scaleAnimation.setFillAfter(true);
                animset.addAnimation(alphaAnimation);
                animset.addAnimation(scaleAnimation);
                imageView00.startAnimation(animset);
                //
            }
        });

        btn02.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用Java代码定义动画	x轴从0.0到1,y轴从0.0到2~
//				Animation anim2=new ScaleAnimation(0.0f,1.0f,0.0f,2.0f);
//				Animation anim2=new ScaleAnimation(1.0f,0.0f,1.0f,1.0f);

                int x = getResources().getDimensionPixelSize(R.dimen.imageview_width);
                int y = getResources().getDimensionPixelSize(R.dimen.imageview_height);

                Animation anim2 = new ScaleAnimation(1.0f, 0.0f, 1.0f, 1.0f, x, y / 2);
                anim2.setDuration(5000);
                //停留在动画最后的状态
                anim2.setFillAfter(true);
                imageView00.startAnimation(anim2);
            }
        });

        btn03.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用Java代码定义动画	x轴从0到400,y轴不动~
                Animation anim2 = new TranslateAnimation(0, 400, 0, 0);
                anim2.setDuration(5000);
                //设置响应开始的时间
                anim2.setStartOffset(1000);
//				动画默认在获得应用中有相同的速度。“Interpolator”类来改变它。这个类能控制动画的速度。
//				比如，AccelerateInterpolator、DecelerateInterpolator、LinearInterpolator
//				、BounceInterpolator、OvershootInterpolator和CycleInterpolator。
//				例如一旦动画快结束时，BounceInterpolator给出一个跳跃的效果。


//				DecelerateInterpolator 在动画开始与介绍的地方速率改变比较慢，在中间的时候加速 
//				AccelerateInterpolator 在动画开始的地方速率改变比较慢，然后开始加速 
//				CycleInterpolator 动画循环播放特定的次数，速率改变沿着正弦曲线 
//				DecelerateInterpolator 在动画开始的地方速率改变比较慢，然后开始减速 
//				LinearInterpolator 在动画的以均匀的速率改变 

                anim2.setInterpolator(new BounceInterpolator());
                imageView00.startAnimation(anim2);
            }
        });

        btn04.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用Java代码定义动画
                //第一个参数代表开始的角度,90代表从3点的位置开始旋转,400代表旋转到多少度
//				Animation anim2=new RotateAnimation(0,400,100,100);
                //第一个参数代表开始的角度,90代表从3点的位置开始旋转,400代表旋转到多少度,圆心为1个父控件的宽,0.5个子空间的高
                Animation anim2 = new RotateAnimation(90, 400, Animation.RELATIVE_TO_PARENT, 1f, Animation.RELATIVE_TO_SELF, 0.5f);
                anim2.setDuration(5000);
                //表示重复一次
                anim2.setRepeatCount(1);
                anim2.setInterpolator(new BounceInterpolator());
                imageView00.startAnimation(anim2);
            }
        });

        btn05.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过XML资源文件定义动画组合
                Animation anim = AnimationUtils.loadAnimation(AnimationTest.this, R.anim.group);
                imageView00.startAnimation(anim);
            }
        });

        btn06.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过Java代码定义动画组合
                AnimationSet animset = new AnimationSet(true);
                Animation anim1 = new AlphaAnimation(0.1f, 1.0f);
                anim1.setDuration(5000);
                Animation anim2 = new RotateAnimation(0, 400, 100, 100);
                anim2.setDuration(5000);
                animset.addAnimation(anim1);
                animset.addAnimation(anim2);
                imageView00.startAnimation(animset);
            }
        });

        btn07.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View paramView) {

                Animation translate_show = AnimationUtils.loadAnimation(AnimationTest.this, R.anim.translate_show);
                translate_show.setFillAfter(true);
                translate_show.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        imageView02.setVisibility(View.VISIBLE);
                        imageView01.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                Animation translate_dismiss = AnimationUtils.loadAnimation(AnimationTest.this, R.anim.translate_dismiss);
                translate_dismiss.setFillAfter(true);
                translate_dismiss.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        imageView02.setVisibility(View.GONE);
                        imageView01.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });

                //
                if (imageView02.getVisibility() == View.GONE) {
                    imageView01.setAnimation(translate_show);
                    translate_show.start();
                } else {
                    imageView01.setAnimation(translate_dismiss);
                    translate_dismiss.start();
                }
            }
        });
    }
}
