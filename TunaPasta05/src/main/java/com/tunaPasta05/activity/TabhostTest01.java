package com.tunaPasta05.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;
import android.widget.TextView;

import com.tunaPasta05.R;

public class TabhostTest01 extends TabActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        final TabHost tabs = getTabHost();
        final TabWidget tabWidget = tabs.getTabWidget();

        tabWidget.setDividerDrawable(null);

        // Android默认的tabwidget是置顶的
        // 所以这里把host的布局文件中最上面的标签先删掉再加入,就置底了
        LinearLayout layout = (LinearLayout) tabs.getChildAt(0);
        View view = layout.getChildAt(0);
        layout.removeViewAt(0); // 同时设置标签的背景
        view.setBackgroundResource(R.drawable.bar_top);

        layout.addView(view);
        tabs.addTab(tabs.newTabSpec("first tab").setIndicator("信息").setContent(new Intent(this, DoubleClickTest.class)));
        tabs.addTab(tabs.newTabSpec("second tab").setIndicator("收藏").setContent(new Intent(this, TextViewPaintFlagTest.class)));
        tabs.addTab(tabs.newTabSpec("third tab").setIndicator("下载").setContent(new Intent(this, DownLoadTest.class)));

        int width = getResources().getDimensionPixelSize(R.dimen.tabWidth);
        int height = getResources().getDimensionPixelSize(R.dimen.tabHeight);

        for (int i = 0; i < tabWidget.getChildCount(); i++) {
            /**
             * 设置高度、宽度，不过宽度由于设置为MATCH_PARENT，在此对它没效果
             */
            tabWidget.getChildAt(i).getLayoutParams().width = width;
            tabWidget.getChildAt(i).getLayoutParams().height = height;
            /**
             * 设置tab中标题文字的颜色，不然默认为黑色
             */
            final TextView tv = tabWidget.getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(this.getResources().getColorStateList(android.R.color.white));

            // 初始化的时候的背景
            View vvv = tabWidget.getChildAt(i);
            if (tabs.getCurrentTab() == i) {
                vvv.setBackgroundDrawable(getResources().getDrawable(R.drawable.no));
            } else {
                vvv.setBackgroundDrawable(getResources().getDrawable(R.drawable.navbgfocus));
            }
        }
        /**
         * 当点击tab选项卡的时候，更改当前的背景
         */
        tabs.setOnTabChangedListener(new OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                System.out.println(tabId);
                for (int i = 0; i < tabWidget.getChildCount(); i++) {
                    View vvv = tabWidget.getChildAt(i);
                    if (tabs.getCurrentTab() == i) {
                        vvv.setBackgroundDrawable(getResources().getDrawable(R.drawable.no));
                    } else {
                        vvv.setBackgroundDrawable(getResources().getDrawable(R.drawable.navbgfocus));
                    }
                }
            }
        });
    }
}
