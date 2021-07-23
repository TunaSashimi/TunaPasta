package com.tunaPasta05.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.tunaPasta05.R;
import com.tunaPasta05.widget.InnerScrollView;

public class ScrollViewTest extends Activity {

    private HorizontalScrollView scrollBar;

    private ScrollView scroll01;
    private InnerScrollView innerscrollview01;

    private ImageView arrowl, arrowr;

    //注意,相关图片只能放在drawable-mdpi中!不然会失真!!
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.scollviewtest);

        scrollBar = findViewById(R.id.scroll_bar);

        scroll01 = findViewById(R.id.scroll01);

        innerscrollview01 = findViewById(R.id.innerscrollview01);
        innerscrollview01.parentScrollView = scroll01;


        arrowl = findViewById(R.id.arrowl);
        arrowr = findViewById(R.id.arrowr);

        scrollBar.fling(1000);
        scrollBar.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                LinearLayout linear = (LinearLayout) scrollBar.getChildAt(0);
                int radioWidth = linear.getChildAt(0).getWidth();
                if (scrollBar.getScrollX() < 6) {
                    arrowl.setVisibility(View.GONE);
                } else if (scrollBar.getScrollX() + scrollBar.getWidth() > radioWidth - 6) {
                    arrowr.setVisibility(View.GONE);
                } else {
                    arrowr.setVisibility(View.VISIBLE);
                    arrowl.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });
    }
}