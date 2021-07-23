package com.tunaPasta07.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.tunaPasta07.R;

//注意所有界面都能适用的诀窍是
//android:scaleType="fitCenter"
//setImageResource而不是setBackgroundResource
public class CustomtabTest extends TabActivity {
    private TabHost tabhost;
    private List<ImageView> imagelist = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customtabtest);

        tabhost = getTabHost();

        addTabItem("首页", R.drawable.tab_home2, new Intent(this, CustomTabItem.class));
        addTabItem("查找", R.drawable.tab_zeyi1, new Intent(this, CustomTabItem.class));
        addTabItem("秀品牌", R.drawable.tab_try1, new Intent(this, CustomTabItem.class));
        addTabItem("我的", R.drawable.tab_collect1, new Intent(this, CustomTabItem.class));
        addTabItem("其它", R.drawable.tab_more1, new Intent(this, CustomTabItem.class));

        tabhost.setOnTabChangedListener(new OnTabChangeListener() {
            public void onTabChanged(String tabId) {

                imagelist.get(0).setImageResource(R.drawable.tab_home1);
                imagelist.get(1).setImageResource(R.drawable.tab_zeyi1);
                imagelist.get(2).setImageResource(R.drawable.tab_try1);
                imagelist.get(3).setImageResource(R.drawable.tab_collect1);
                imagelist.get(4).setImageResource(R.drawable.tab_more1);

                if (tabId.equals("首页"))
                    imagelist.get(0).setImageResource(R.drawable.tab_home2);
                if (tabId.equals("查找"))
                    imagelist.get(1).setImageResource(R.drawable.tab_zeyi2);
                if (tabId.equals("秀品牌"))
                    imagelist.get(2).setImageResource(R.drawable.tab_try1);
                if (tabId.equals("我的"))
                    imagelist.get(3).setImageResource(R.drawable.tab_collect2);
                if (tabId.equals("其它"))
                    imagelist.get(4).setImageResource(R.drawable.tab_more2);
            }
        });
    }

    private void addTabItem(String title, int myicon, Intent intent) {

        TabSpec tabSpec = tabhost.newTabSpec(title);
        View tabItem = getLayoutInflater().inflate(R.layout.customtabtestitem, null);

        TextView textview = tabItem.findViewById(R.id.tab_item_tv);
        textview.setPadding(4, 4, 4, 4);
        textview.setText(title);

        ImageView imageview = tabItem.findViewById(R.id.tab_item_iv);

        imageview.setImageResource(myicon);
        imagelist.add(imageview);

        tabSpec.setIndicator(tabItem);
        tabSpec.setContent(intent);
        tabhost.addTab(tabSpec);
    }

    //考虑做横向或者纵向的

//	@Override
//	public void onConfigurationChanged(Configuration newConfig) {
//		super.onConfigurationChanged(newConfig);
//	}
}