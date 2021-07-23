package com.tunaPasta06.activity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.tunaPasta06.R;
import com.tunaPasta06.widget.PressNavigationBar;

public class TopNavigationBarTest extends Activity {

    private RadioGroup genderGroup;
    private RadioButton radiobutton_taxi, radiobutton_vip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.topnavigationbartest);

        PressNavigationBar pressNavigationBar = findViewById(R.id.navigationbartest_ui_PressNavigationBar);

        /* 动态部署数据 */
        // String[] text = { "最热", "最新", "猜你喜欢" };
        // int[] textSize = { 14, 14, 14 };// 单位sp
        // int[] textColor = { Color.WHITE, Color.WHITE, Color.WHITE };
        // int[] image = { R.drawable.message_left_button_normal,
        // R.drawable.message_middle_button_normal,
        // R.drawable.message_right_button_normal };// 未被选择样式（图片）
        // int[] imageSelected = { R.drawable.message_left_button_pressed,
        // R.drawable.message_middle_button_pressed,
        // R.drawable.message_right_button_pressed };// 被选择样式（图片）

        String[] text = {"最热", "最新"};
        int[] textSize = {14, 14};// 单位sp

        int[] textColor = {Color.WHITE, Color.WHITE};
        int[] textColorSelected = {Color.YELLOW, Color.YELLOW};

        int[] image = {R.drawable.message_left_button_normal,
                R.drawable.message_right_button_normal,};// 未被选择样式（图片）
        int[] imageSelected = {R.drawable.message_left_button_pressed,
                R.drawable.message_right_button_pressed,};// 被选择样式（图片）

        List<Map<String, Object>> pressBarList = new LinkedList();
        for (int i = 0; i < image.length; i++) {
            Map<String, Object> map = new HashMap();
            map.put("text", text[i]);
            map.put("textSize", textSize[i]);
            map.put("textColor", textColor[i]);
            map.put("textColorSelected", textColorSelected[i]);
            map.put("image", image[i]);
            map.put("imageSelected", imageSelected[i]);
            pressBarList.add(map);
        }

        /* "按下效果导航栏"添加子组件 */
        pressNavigationBar.addChild(pressBarList);

        /* "按下效果导航栏"添加监视 */
        pressNavigationBar
                .setPressNavigationBarListener(new PressNavigationBar.PressNavigationBarListener() {
                    /**
                     * @params position 被选位置
                     * @params view 为导航栏
                     * @params event 移动事件
                     */
                    @Override
                    public void onNavigationBarClick(int position, View view,
                                                     MotionEvent event) {
                        if (1 == position) {

                        }
                        // switch (event.getAction()) {
                        // case MotionEvent.ACTION_DOWN:// 按下去时
                        // break;
                        // case MotionEvent.ACTION_MOVE:// 移动中
                        // break;
                        // case MotionEvent.ACTION_UP:// 抬手时
                        // break;
                        // }
                    }
                });

        radiobutton_taxi = findViewById(R.id.radiobutton_taxi);
        radiobutton_vip = findViewById(R.id.radiobutton_vip);

        genderGroup = findViewById(R.id.genderGroup);
        genderGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radiobutton_taxi) {

                    radiobutton_taxi.setTextColor(Color.WHITE);
                    radiobutton_vip.setTextColor(Color.rgb(89, 45, 45));
                } else {

                    radiobutton_taxi.setTextColor(Color.rgb(89, 45, 45));
                    radiobutton_vip.setTextColor(Color.WHITE);
                }
            }
        });
    }
}