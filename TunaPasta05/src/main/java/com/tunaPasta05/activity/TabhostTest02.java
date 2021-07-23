package com.tunaPasta05.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.tunaPasta05.R;

public class TabhostTest02 extends TabActivity {
    private TabHost tabhost;
    private int times;

    //	private ArrayList<TextView> textlist=new ArrayList<TextView>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //	setTheme(R.style.Theme_Tabhost);
        //	自定义TabHostActivity的Theme,不想要头部阴影一定要添加这个android:windowContentOverlay = null
        setContentView(R.layout.tabhosttest02);
        tabhost = getTabHost();

        initTopandDivid();                                                                                        //自定义的头部布局

        addTabItem("首页", R.drawable.icon_home, new Intent(this, RepeatTileTest.class));
        addTabItem("资料", R.drawable.icon_selfinfo, new Intent(this, CheckboxListTest.class));
        addTabItem("信息", R.drawable.icon_meassage, new Intent(this, ButterflyTest.class));
        addTabItem("广场", R.drawable.icon_square, new Intent(this, LoseWeightTest.class));
        addTabItem("更多", R.drawable.icon_more, new Intent(this, RatioListDemo.class));

//		修改的按下去变换图标(图标另找)
//		tabhost.setOnTabChangedListener(new OnTabChangeListener() {
//			@Override
//			public void onTabChanged(String tabId) {
//				textlist.get(0).setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_home1, 0, 0);	//这里不同因为第一个
//				textlist.get(1).setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.tab_zeyi1, 0, 0);
//				textlist.get(2).setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_try1, 0, 0);
//				textlist.get(3).setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_collect1, 0, 0);
//				textlist.get(4).setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_more1, 0, 0);
//				if (tabId.equals("1")) 
//					textlist.get(0).setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_home2, 0, 0);
//				if (tabId.equals("2")) 
//					textlist.get(1).setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_zeyi2, 0, 0);
//				if (tabId.equals("3")) 
//					textlist.get(2).setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_try2, 0, 0);
//				if (tabId.equals("4")) 
//					textlist.get(3).setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_collect2, 0, 0);
//				if (tabId.equals("5")) 
//					textlist.get(4).setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_more2, 0, 0);
//			}
//		});

    }

    //自定义头部布局和标签页分割线
    private void initTopandDivid() {
        View child = getLayoutInflater().inflate(R.layout.example_top, null);
        LinearLayout layout = findViewById(R.id.tab_top);
        layout.addView(child);

        // 得到tabwidget设置分割线,注意这句话不能写在addTabItem后面,不然报错
        getTabWidget().setDividerDrawable(R.drawable.tab_divider);
    }

    private void addTabItem(String title, int icon, Intent intent) {
        TabSpec tabSpec = tabhost.newTabSpec(title);
        View tabItem = getLayoutInflater().inflate(R.layout.tabhosttest02item, null);
        TextView textview = tabItem.findViewById(R.id.tab_item_tv);
        textview.setPadding(3, 3, 3, 3);
        textview.setText(title);    //如果图标上有文字,这里就不用显示了
        if (times % 2 == 0) {
            textview.setBackgroundResource(R.drawable.maintab_toolbar_bgx1);
        } else {
            textview.setBackgroundResource(R.drawable.maintab_toolbar_bgx2);
        }
        times++;
        textview.setCompoundDrawablesWithIntrinsicBounds(0, icon, 0, 0);
        tabSpec.setIndicator(tabItem);
        tabSpec.setContent(intent);
        tabhost.addTab(tabSpec);
    }
}