package com.tunaPasta09.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnTouchModeChangeListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.tunaPasta09.R;
import com.tunaPasta09.tool.NativeRequest;
import com.tunaPasta09.widget.NemesisView;
import com.tunaPasta09.widget.NemesisView.NemesisType;

public class LevelDetailsTest extends Activity {

    // 屏幕宽度
    private int screenWidth;
    //屏幕高度
    private int screenHeight;

    // 进度条长度占屏幕宽度比
    private float screenWidthRatio = 0.72f;

    // 进度条进度
    private float progressBarProcess = 0f;

    //动画和进度条时间
    private int durationMillis = 1500;

    // 根目录的相对布局
    private RelativeLayout leveldetailstest_relative01;

    //遮盖图片
    private ImageView leveldetailstest_imageview_scale;

    // 车辆图片
    private ImageView leveldetailstest_imageview02;
    // 等级特权钻石图片
    private ImageView leveldetailstest_imageview03;
    //升级图片
    private ImageView leveldetailstest_imageview04;

    // 进度条
    private ProgressBar leveldetailstest_progressbar01;

    //动画效果
    private Animation translateAnimation;
    private Animation scaleAnimation;

    // 添加的布局文件宽度
    private int addTriangleImageViewWidth;
    private int addTriangleImageViewHeight;
    private int addTriangleImageViewMarginTop;

    private int addTextViewLayoutWidth;
    private int addTextViewLayoutHeight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.leveldetailstest);

        //按照数据设置进度
        progressBarProcess = 2500 / 10000f;

        // 获取屏幕宽度
        screenWidth = NativeRequest.getScreenWidth(this);
        screenHeight = NativeRequest.getScreenHeight(this);

        // 次外层RelativeLayout高度将修改
        leveldetailstest_relative01 = findViewById(R.id.leveldetailstest_relative01);
        RelativeLayout.LayoutParams relativelayoutParams = (LayoutParams) leveldetailstest_relative01.getLayoutParams();
        relativelayoutParams.height = (int) (screenHeight * 0.8f);

        // 设置progressbar长度为屏幕宽度的screenWidthRatio
        leveldetailstest_progressbar01 = findViewById(R.id.leveldetailstest_progressbar01);
        RelativeLayout.LayoutParams progressbarLayoutparams = (LayoutParams) leveldetailstest_progressbar01.getLayoutParams();
        progressbarLayoutparams.width = (int) (screenWidth * screenWidthRatio);

        //设置遮盖图片长度为屏幕宽度的screenWidthRatio
        leveldetailstest_imageview_scale = findViewById(R.id.leveldetailstest_imageview_scale);
        RelativeLayout.LayoutParams leveldetailstest_imageview_params = (LayoutParams) leveldetailstest_imageview_scale.getLayoutParams();
        leveldetailstest_imageview_params.width = (int) (screenWidth * screenWidthRatio);

        // Imageview车辆重新在代码中设置marginTop,因为要和progressbar做对齐,所以在progressbar下面
        leveldetailstest_imageview02 = findViewById(R.id.leveldetailstest_imageview02);
        RelativeLayout.LayoutParams leveldetailstestParams = (LayoutParams) leveldetailstest_imageview02.getLayoutParams();
        leveldetailstestParams.addRule(RelativeLayout.LEFT_OF, leveldetailstest_progressbar01.getId());
        leveldetailstestParams.setMargins(0, (int) (screenHeight * 0.45f), 0, 0);

        //移动等级特权的左边钻石和如何升级的问号向右边
        leveldetailstest_imageview03 = findViewById(R.id.leveldetailstest_imageview03);
        leveldetailstest_imageview04 = findViewById(R.id.leveldetailstest_imageview04);
        RelativeLayout.LayoutParams diamondImageParams = (LayoutParams) leveldetailstest_imageview03.getLayoutParams();
        RelativeLayout.LayoutParams informationImageParams = (LayoutParams) leveldetailstest_imageview04.getLayoutParams();

        int diamondImageWidth = getResources().getDimensionPixelSize(R.dimen.imageview_diamond_width);
        int diamondTextViewWidth = getResources().getDimensionPixelSize(R.dimen.textview_diamond_width);
        int diamondTextViewMargin = getResources().getDimensionPixelSize(R.dimen.textview_diamond_margin);
        //最外层的margin,所以左边要减去一倍,右边减去两倍
        int rootRelativeMargin = getResources().getDimensionPixelSize(R.dimen.relative_root_margin);

        int diamondLeftMargin = screenWidth / 4 - (diamondImageWidth + diamondTextViewWidth + diamondTextViewMargin) / 4 - rootRelativeMargin;
        int informaritonLeftMargin = screenWidth / 4 - (diamondImageWidth + diamondTextViewWidth + diamondTextViewMargin) / 4 - rootRelativeMargin * 2;

        diamondImageParams.setMargins(diamondLeftMargin, 0, 0, 0);
        informationImageParams.setMargins(informaritonLeftMargin, 0, 0, 0);

        int x = (int) (screenWidth * screenWidthRatio);
        int y = getResources().getDimensionPixelSize(R.dimen.textview_margin);

        //设置拉伸动画效果
        scaleAnimation = new ScaleAnimation(1.0f, 1 - progressBarProcess, 1.0f, 1.0f, x, y / 2);
        scaleAnimation.setDuration(durationMillis);
        scaleAnimation.setInterpolator(new LinearInterpolator());
        scaleAnimation.setFillAfter(true);

        // 设置平移动画效果
        translateAnimation = new TranslateAnimation(0, (int) (screenWidth * screenWidthRatio * progressBarProcess), 0, 0);
        translateAnimation.setDuration(durationMillis);
        translateAnimation.setInterpolator(new LinearInterpolator());
        translateAnimation.setFillAfter(true);
        translateAnimation.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 添加的布局文件中的三角图片

                // 获取三角imagevView文件宽度
                addTriangleImageViewWidth = getResources().getDimensionPixelSize(R.dimen.add_triangle_imageview_width);
                addTriangleImageViewHeight = getResources().getDimensionPixelSize(R.dimen.add_triangle_imageview_height);

                NemesisView nemesisView = new NemesisView(LevelDetailsTest.this, addTriangleImageViewWidth, addTriangleImageViewHeight, NemesisType.TRANGLE_POSITIVE, Color.parseColor("#EFEFF4"));

                addTriangleImageViewMarginTop = getResources().getDimensionPixelSize(R.dimen.add_triangle_imageview_margin_top);

                RelativeLayout.LayoutParams addImageLayoutParams = new RelativeLayout.LayoutParams(addTriangleImageViewWidth, addTriangleImageViewHeight);
                addImageLayoutParams.addRule(RelativeLayout.BELOW, leveldetailstest_progressbar01.getId());
                addImageLayoutParams.addRule(RelativeLayout.ALIGN_LEFT, leveldetailstest_progressbar01.getId());

                addImageLayoutParams.setMargins((int) (screenWidth * screenWidthRatio * progressBarProcess - addTriangleImageViewWidth / 2), addTriangleImageViewMarginTop, 0, 0);

                leveldetailstest_relative01.addView(nemesisView, addImageLayoutParams);

                // 添加的布局文件中的textviewDistance
                TextView textviewDistance = new TextView(LevelDetailsTest.this);
                textviewDistance.setGravity(Gravity.CENTER);
                textviewDistance.setBackgroundResource(R.drawable.leveldetailstest_textview_background_distance);
                textviewDistance.setTextColor(Color.parseColor("#666666"));
                textviewDistance.setText("累计里程:2500公里");
                textviewDistance.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);

                Spannable spanGrade = new SpannableString(textviewDistance.getText());
                spanGrade.setSpan(new ForegroundColorSpan(Color.parseColor("#592D2D")), 5, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textviewDistance.setText(spanGrade);

                addTextViewLayoutWidth = getResources().getDimensionPixelSize(R.dimen.add_textview_width);
                addTextViewLayoutHeight = getResources().getDimensionPixelSize(R.dimen.add_textview_height);

                RelativeLayout.LayoutParams addTextDistanceLayoutParams = new RelativeLayout.LayoutParams(addTextViewLayoutWidth, addTextViewLayoutHeight);
                addTextDistanceLayoutParams.addRule(RelativeLayout.BELOW, leveldetailstest_progressbar01.getId());
                addTextDistanceLayoutParams.addRule(RelativeLayout.ALIGN_LEFT, leveldetailstest_progressbar01.getId());

                // 正常情况下的LeftMargin
                int textViewLeftMargin = (int) (screenWidth * screenWidthRatio * progressBarProcess - addTextViewLayoutWidth / 2 - addTriangleImageViewWidth / 2);

                // 控件位于最左边的LeftMargin(就是对话框最左端和进度条最左端对齐,但是由于下面的对话框是圆角的还是对不上,多加一个"白银卡"的宽度)
                int textViewLeftShortest = -addTriangleImageViewWidth;
                // 控件位于最右边的LeftMargin(就是对话框最右端和进度条最右端对齐,但是由于下面的对话框是圆角的还是对不上,多加一个"白银卡"的宽度)
                int textViewLeftLongtest = (int) (screenWidth * screenWidthRatio * 1.0 - addTextViewLayoutWidth + addTriangleImageViewWidth);

                if (textViewLeftMargin < textViewLeftShortest) {
                    textViewLeftMargin = textViewLeftShortest;
                } else if (textViewLeftMargin > textViewLeftLongtest) {
                    textViewLeftMargin = textViewLeftLongtest;
                }

                addTextDistanceLayoutParams.setMargins(textViewLeftMargin, addTriangleImageViewMarginTop + addTriangleImageViewHeight, 0, 0);
                leveldetailstest_relative01.addView(textviewDistance, addTextDistanceLayoutParams);

                // 添加的布局文件中的textviewGrade
                TextView textviewGrade = new TextView(LevelDetailsTest.this);
                textviewGrade.setGravity(Gravity.CENTER);
                textviewGrade.setBackgroundResource(R.drawable.leveldetailstest_textview_background_grade);
                textviewGrade.setTextColor(Color.parseColor("#666666"));
                textviewGrade.setText("升级还需:7500公里");
                textviewGrade.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);

                Spannable spanGradeFor = new SpannableString(textviewGrade.getText());
                spanGradeFor.setSpan(new ForegroundColorSpan(Color.parseColor("#592D2D")), 5, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textviewGrade.setText(spanGradeFor);

                RelativeLayout.LayoutParams addTextGradeLayoutParams = new RelativeLayout.LayoutParams(addTextViewLayoutWidth, addTextViewLayoutHeight);
                addTextGradeLayoutParams.addRule(RelativeLayout.BELOW, leveldetailstest_progressbar01.getId());
                addTextGradeLayoutParams.addRule(RelativeLayout.ALIGN_LEFT, leveldetailstest_progressbar01.getId());

                addTextGradeLayoutParams.setMargins(textViewLeftMargin, addTriangleImageViewMarginTop + addTriangleImageViewHeight + addTextViewLayoutHeight, 0, 0);

                leveldetailstest_relative01.addView(textviewGrade, addTextGradeLayoutParams);
            }
        });

        ViewTreeObserver viewtreeobserver =  leveldetailstest_imageview02.getViewTreeObserver();
        viewtreeobserver.addOnTouchModeChangeListener(new OnTouchModeChangeListener() {
            @Override
            public void onTouchModeChanged(boolean isInTouchMode) {

                leveldetailstest_imageview_scale.startAnimation(scaleAnimation);

                leveldetailstest_imageview02.startAnimation(translateAnimation);
            }
        });
    }
}