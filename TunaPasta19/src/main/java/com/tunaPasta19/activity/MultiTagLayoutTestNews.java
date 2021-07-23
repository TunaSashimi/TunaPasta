package com.tunaPasta19.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.tunaPasta19.R;
import com.tunaPasta19.tool.MoveBg;
import com.tunaPasta19.tuna.TunaView;

public class MultiTagLayoutTestNews extends Activity {
    RelativeLayout layout;
    TextView tv_front;

    TextView tv_bar_news;
    TextView tv_bar_sport;
    TextView tv_bar_play;
    TextView tv_bar_finance;
    TextView tv_bar_science;
    TextView tv_bar_more;

    int avg_width = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.multitaglayouttestnews);

        initViews();
    }

    private void initViews() {
        layout = findViewById(R.id.layout_title_bar);

        tv_bar_news = findViewById(R.id.tv_title_bar_news);
        tv_bar_sport = findViewById(R.id.tv_title_bar_sport);
        tv_bar_play = findViewById(R.id.tv_title_bar_play);
        tv_bar_finance = findViewById(R.id.tv_title_bar_finance);
        tv_bar_science = findViewById(R.id.tv_title_bar_science);
        tv_bar_more = findViewById(R.id.tv_title_bar_more);

        tv_bar_news.setOnClickListener(onClickListener);
        tv_bar_sport.setOnClickListener(onClickListener);
        tv_bar_play.setOnClickListener(onClickListener);
        tv_bar_finance.setOnClickListener(onClickListener);
        tv_bar_science.setOnClickListener(onClickListener);
        tv_bar_more.setOnClickListener(onClickListener);

        tv_front = new TextView(this);
        tv_front.setBackgroundResource(R.drawable.tabnewsactivity_testview_slidebar);
        tv_front.setTextColor(Color.WHITE);
        tv_front.setText("头条");
        tv_front.setGravity(Gravity.CENTER);

        int width = TunaView.getScreenWidth(this) / 6;
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(
                width, LayoutParams.WRAP_CONTENT);

        param.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        layout.addView(tv_front, param);
    }

    private OnClickListener onClickListener = new OnClickListener() {
        int startX;

        @Override
        public void onClick(View v) {
            avg_width = findViewById(R.id.layout).getWidth();
            switch (v.getId()) {
                case R.id.tv_title_bar_news:
                    MoveBg.moveFrontBg(tv_front, startX, 0, 0, 0);
                    startX = 0;
                    tv_front.setText(R.string.title_news_category_tops);
                    break;
                case R.id.tv_title_bar_sport:
                    MoveBg.moveFrontBg(tv_front, startX, avg_width, 0, 0);
                    startX = avg_width;
                    tv_front.setText(R.string.title_news_category_sport);
                    break;
                case R.id.tv_title_bar_play:
                    MoveBg.moveFrontBg(tv_front, startX, avg_width * 2, 0, 0);
                    startX = avg_width * 2;
                    tv_front.setText(R.string.title_news_category_play);
                    break;
                case R.id.tv_title_bar_finance:
                    MoveBg.moveFrontBg(tv_front, startX, avg_width * 3, 0, 0);
                    startX = avg_width * 3;
                    tv_front.setText(R.string.title_news_category_finance);
                    break;
                case R.id.tv_title_bar_science:
                    MoveBg.moveFrontBg(tv_front, startX, avg_width * 4, 0, 0);
                    startX = avg_width * 4;
                    tv_front.setText(R.string.title_news_category_science);
                    break;
                case R.id.tv_title_bar_more:
                    MoveBg.moveFrontBg(tv_front, startX, avg_width * 5, 0, 0);
                    startX = avg_width * 5;
                    tv_front.setText(R.string.title_news_category_more);
                    break;

                default:
                    break;
            }

        }
    };

}
