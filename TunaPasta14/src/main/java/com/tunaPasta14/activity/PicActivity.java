package com.tunaPasta14.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.TextView;

import com.tunaPasta14.R;
import com.tunaPasta14.myinterface.ImageChangeListener;
import com.tunaPasta14.pic.GalleryAdapter;
import com.tunaPasta14.pic.MyGallery;

/**
 * 来源于：http://www.eoeandroid.com/forum.php?mod=viewthread&tid=226786
 *
 * @author wissea
 */

public class PicActivity extends Activity implements OnTouchListener {
    /**
     * Called when the activity is first created.
     */
    //屏幕的宽度
    public static int screenWidth;
    //屏幕的高度
    public static int screenHeight;
    private MyGallery gallery;
    private boolean isScale = false;  //是否缩放
    private float beforeLenght = 0.0f;     //两触点距离
    private float afterLenght = 0.0f;
    private float currentScale = 1.0f;
    private TextView current_page;
    private GalleryAdapter galleryAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置窗体无标题 全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layoutpic);
        current_page = (TextView) findViewById(R.id.pages);


        gallery = (MyGallery) findViewById(R.id.mygallery);
        gallery.setVerticalFadingEdgeEnabled(false);
        gallery.setHorizontalFadingEdgeEnabled(false);//);// 设置view在水平滚动时，水平边不淡出。
        galleryAdapter = new GalleryAdapter(this);
        galleryAdapter.setOnPageChangListener(new ImageChangeListener() {

            @Override
            public void onChange(int i) {
                current_page.setText(i + "/" + galleryAdapter.getCount());
            }
        });

        current_page.setText("1/" + galleryAdapter.getCount());
        gallery.setAdapter(galleryAdapter);

        //获取屏幕的大小
        screenWidth = getWindow().getWindowManager().getDefaultDisplay().getWidth();
        screenHeight = getWindow().getWindowManager().getDefaultDisplay().getHeight();
    }

    private class GalleryChangeListener implements OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            currentScale = 1.0f;
            isScale = false;
            beforeLenght = 0.0f;
            afterLenght = 0.0f;
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    /**
     * 计算两点间的距离
     */
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_POINTER_DOWN:
                beforeLenght = spacing(event);
                if (beforeLenght > 5f) {
                    isScale = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                /*处理拖动*/
                if (isScale) {
                    afterLenght = spacing(event);
                    if (afterLenght < 5f)
                        break;
                    float gapLenght = afterLenght - beforeLenght;
                    if (gapLenght == 0) {
                        break;
                    } else if (Math.abs(gapLenght) > 5f) {
                        float scaleRate = gapLenght / 854;
                        Animation myAnimation_Scale = new ScaleAnimation(currentScale, currentScale + scaleRate, currentScale, currentScale + scaleRate, Animation.RELATIVE_TO_SELF, 0.5f,
                                Animation.RELATIVE_TO_SELF, 0.5f);
                        myAnimation_Scale.setDuration(100);
                        myAnimation_Scale.setFillAfter(true);
                        myAnimation_Scale.setFillEnabled(true);
                        currentScale = currentScale + scaleRate;
                        gallery.getSelectedView().setLayoutParams(new Gallery.LayoutParams((int) (480 * (currentScale)), (int) (854 * (currentScale))));
                        beforeLenght = afterLenght;
                    }
                    return true;
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                isScale = false;
                break;
        }

        return false;
    }
}