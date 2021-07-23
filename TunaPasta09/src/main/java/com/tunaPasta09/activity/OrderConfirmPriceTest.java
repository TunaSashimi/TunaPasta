package com.tunaPasta09.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tunaPasta09.R;
import com.tunaPasta09.tool.NativeRequest;
import com.tunaPasta09.widget.MoveBg;
import com.tunaPasta09.widget.NemesisView;
import com.tunaPasta09.widget.NemesisView.NemesisType;

public class OrderConfirmPriceTest extends Activity {

    FrameLayout frameLayout;
    ImageView tv_bar_news, tv_bar_sport, tv_bar_play, tv_bar_finance;
    View iv_front;// 需要移动的View
    int layoutWidth;// 每个标签的宽度
    int imageWidth;// 移动箭头的宽度
    int imageStrokeWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.orderconfirmpricetest);

        initViews();
    }

    private void initViews() {

        frameLayout = findViewById(R.id.frame01);

        // 这里的iv_front使用的是默认的布局,所以要获取到屏幕的宽度除以4(上方有4个按钮)
        layoutWidth = NativeRequest.getScreenWidth(this) / 4;// 80

        tv_bar_news = findViewById(R.id.tv_title_bar_news);
        tv_bar_sport = findViewById(R.id.tv_title_bar_sport);
        tv_bar_play = findViewById(R.id.tv_title_bar_play);
        tv_bar_finance = findViewById(R.id.tv_title_bar_finance);

        tv_bar_news.setOnClickListener(onClickListener);
        tv_bar_sport.setOnClickListener(onClickListener);
        tv_bar_play.setOnClickListener(onClickListener);
        tv_bar_finance.setOnClickListener(onClickListener);

        imageWidth = layoutWidth / 4;

        imageStrokeWidth = (int) getResources().getDimension(
                R.dimen.add_triangle_imageview_strokewidth);

        iv_front = new NemesisView(OrderConfirmPriceTest.this, imageWidth,
                imageWidth - 20, NemesisType.TRANGLE_REVERSE,
                Color.parseColor("#ffffff"), imageStrokeWidth,
                Color.parseColor("#80999999"), true);

        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(
                imageWidth, imageWidth);

        param.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);// 居中对齐
        frameLayout.addView(iv_front, param);
        MoveBg.moveFrontBg(iv_front, 0, layoutWidth / 2 - imageWidth / 2
                + layoutWidth * 0, 0, 0);

    }

    private OnClickListener onClickListener = new OnClickListener() {
        int startX;// 移动的起始位置

        @Override
        public void onClick(View v) {

            setBackgrounds();

            switch (v.getId()) {
                case R.id.tv_title_bar_news:

                    tv_bar_news
                            .setImageResource(R.drawable.icn_car_type_1_selected);
                    MoveBg.moveFrontBg(iv_front, startX, layoutWidth / 2
                            - imageWidth / 2 + layoutWidth * 0, 0, 0);
                    startX = layoutWidth / 2 - imageWidth / 2 + layoutWidth * 0;

                    break;
                case R.id.tv_title_bar_sport:

                    tv_bar_sport
                            .setImageResource(R.drawable.icn_car_type_1_selected);
                    MoveBg.moveFrontBg(iv_front, startX, layoutWidth / 2
                            - imageWidth / 2 + layoutWidth * 1, 0, 0);
                    startX = layoutWidth / 2 - imageWidth / 2 + layoutWidth * 1;

                    break;
                case R.id.tv_title_bar_play:

                    tv_bar_play
                            .setImageResource(R.drawable.icn_car_type_1_selected);
                    MoveBg.moveFrontBg(iv_front, startX, layoutWidth / 2
                            - imageWidth / 2 + layoutWidth * 2, 0, 0);
                    startX = layoutWidth / 2 - imageWidth / 2 + layoutWidth * 2;

                    break;
                case R.id.tv_title_bar_finance:

                    tv_bar_finance
                            .setImageResource(R.drawable.icn_car_type_1_selected);
                    MoveBg.moveFrontBg(iv_front, startX, layoutWidth / 2
                            - imageWidth / 2 + layoutWidth * 3, 0, 0);
                    startX = layoutWidth / 2 - imageWidth / 2 + layoutWidth * 3;

                    break;
                default:
                    break;
            }
        }
    };

    private void setBackgrounds() {
        tv_bar_news.setImageResource(R.drawable.icn_car_type_1);
        tv_bar_sport.setImageResource(R.drawable.icn_car_type_2);
        tv_bar_play.setImageResource(R.drawable.icn_car_type_3);
        tv_bar_finance.setImageResource(R.drawable.icn_car_type_4);
    }
}
