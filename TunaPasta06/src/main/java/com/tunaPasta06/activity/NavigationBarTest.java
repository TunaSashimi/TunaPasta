package com.tunaPasta06.activity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tunaPasta06.R;
import com.tunaPasta06.adapter.RollNavigationBarAdapter;
import com.tunaPasta06.widget.RollNavigationBar;

public class NavigationBarTest extends Activity {

    private String[] title = {"关注", "热门", "分类", "喜欢", "我"};
    private int[] photo = {R.drawable.nav_menu_home, R.drawable.nav_menu_hot,
        R.drawable.nav_menu_category, R.drawable.nav_menu_like,
        R.drawable.nav_menu_me};
    private int[] photoSelected = {R.drawable.nav_menu_home_selected,
        R.drawable.nav_menu_hot_selected,
        R.drawable.nav_menu_category_active,
        R.drawable.nav_menu_like_active, R.drawable.nav_menu_me_selected};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigationbartest);
        RollNavigationBar rnb = findViewById(R.id.navigationbartest_ui_RollNavigationBar);
        /* 定制动态数据 */
        List<Map<String, Object>> list = new LinkedList();
        for (int i = 0; i < title.length; i++) {
            Map<String, Object> map = new HashMap();
            map.put("title", title[i]);
            map.put("photo", photo[i]);
            map.put("photoSelected", photoSelected[i]);
            list.add(map);
        }
        /* 设置滑动条的滑动时间，时间范围在0.1~1s，不在范围则默认0.1s */
        rnb.setSelecterMoveContinueTime(0.1f);// 可以不设置，默认0.1s
        /* 设置滑动条样式（图片） */
        rnb.setSelecterDrawableSource(R.drawable.nav_menu_bg);// 必须
        /* 设置导航栏的被选位置 */
        rnb.setSelectedChildPosition(0);// 可以不设置

        /* 导航栏扩展 */
        final MyNavigationBarAdapter adapter = new MyNavigationBarAdapter(this, list);
        rnb.setAdapter(adapter);// 必须
        rnb.setNavigationBarListener(new RollNavigationBar.NavigationBarListener() {
            /**
             * position 被选位置
             * view 为导航栏
             * event 移动事件
             */
            @Override
            public void onNavigationBarClick(int position, View view,
                                             MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:// 按下去时

                        break;
                    case MotionEvent.ACTION_MOVE://移动中

                        break;
                    case MotionEvent.ACTION_UP:// 抬手时

                        break;
                }
            }
        });
    }

    /**
     * 导航栏扩展
     *
     * @author w.song
     * @version 1.0.1
     * @date 2012-4-22
     */
    class MyNavigationBarAdapter extends RollNavigationBarAdapter {
        private List<Map<String, Object>> list;
        private LayoutInflater mInflater;

        public MyNavigationBarAdapter(Activity activity,
                                      List<Map<String, Object>> list) {
            mInflater = LayoutInflater.from(activity);
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        /**
         * 获取每个组件
         *
         * @param position    组件的位置
         * @param contextView 组件
         * @param parent      上层组件
         */
        @Override
        public View getView(int position, View contextView, ViewGroup parent) {
            mInflater.inflate(R.layout.navigationbartestitem, (ViewGroup) contextView);
            RollNavigationBar rollNavigationBar = (RollNavigationBar) parent;
            /* 获取组件 */
            ImageView imageView = contextView
                .findViewById(R.id.image_view);
            TextView titleView = contextView
                .findViewById(R.id.title_view);

            /* 获取参数 */
            String title = "" + list.get(position).get("title");
            int photo = (Integer) list.get(position).get("photo");
            int photoSelected = (Integer) list.get(position).get("photoSelected");

            /* 组件设置参数 */
            // 区分选择与被选择图片
            if (position == rollNavigationBar.getSelectedChildPosition()) {//被选择
                imageView.setBackgroundResource(photoSelected);
            } else {//没被选择
                imageView.setBackgroundResource(photo);
            }
            titleView.setText(title);

            return contextView;
        }
    }
}
