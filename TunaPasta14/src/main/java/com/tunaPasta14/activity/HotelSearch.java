package com.tunaPasta14.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.tunaPasta14.R;

public class HotelSearch extends Activity implements OnClickListener {

    private TextView title;
    private LinearLayout layout;

    private ListView listView;
    private MyAdapter adapter;
    private LayoutInflater inflater;

    // 底部栏的事件
    private LinearLayout tabLayout1, tabLayout2, tabLayout3, tabLayout4;

    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotelsearch);

        tabLayout1 = findViewById(R.id.linear);
        tabLayout2 = findViewById(R.id.linear1);
        tabLayout3 = findViewById(R.id.linear2);
        tabLayout4 = findViewById(R.id.linear3);

        tabLayout1.setOnClickListener(this);
        tabLayout2.setOnClickListener(this);
        tabLayout3.setOnClickListener(this);
        tabLayout4.setOnClickListener(this);

        title = (TextView) findViewById(R.id.title_text);
        title.setText("酒店搜索");
        layout = (LinearLayout) findViewById(R.id.title_back);
        layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list = new ArrayList();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        adapter = new MyAdapter();
        listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int arg2,
                                    long arg3) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), HotelDetail.class);
                startActivity(intent);
                // 想做成点击后变颜色，没搞成功，下次回来该
                // if (Integer.parseInt(view.findViewById(R.id.flag).getTag()
                // .toString()) == 1){
                // ((RelativeLayout) view.findViewById(R.id.listview_item))
                // .setBackgroundColor(getResources().getColor(
                // R.color.inselected));
                // view.findViewById(R.id.flag).setTag("2");
                // }
            }
        });

    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int arg0) {
            return list.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(int arg0, View view, ViewGroup arg2) {
            view = inflater.inflate(R.layout.hotelsearch_item, null);

            return view;
        }

    }

/*	
	if (hasFocus) {
		LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		View music_popunwindwow = mLayoutInflater.inflate(
				R.layout.hotel_filter, null);
		PopupWindow mPopupWindow = new PopupWindow(music_popunwindwow,
				LayoutParams.MATCH_PARENT, 150, true);
		mPopupWindow.setFocusable(true);
		mPopupWindow.setOutsideTouchable(true);
		// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
		mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		int height = windowManager.getDefaultDisplay().getHeight();
		mPopupWindow
				.showAtLocation(
						v,
						Gravity.NO_GRAVITY,
						tabLayout1.getWidth() / 2
								- mPopupWindow.getWidth() / 2,
						height - mPopupWindow.getHeight()
								- tabLayout1.getHeight() + 4);
		mPopupWindow.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				tabLayout3.clearFocus();
			}
		});
	}*/

    @Override
    public void onClick(View v) {

        int tag = 0;

        switch (v.getId()) {
            case R.id.linear:
                tag = 0;
                break;
            case R.id.linear1:
                tag = 1;
                break;
            case R.id.linear2:
                tag = 2;
                break;
            case R.id.linear3:
                tag = 3;
                break;
            default:
                break;
        }

        //这一部分有个Bug没还没有解决
		/*Intent intent		=new Intent();
		intent.setClass(getApplicationContext(), HotelTab.class);
		intent.putExtra("tag", tag);
		startActivity(intent);*/
    }

}
