package com.tunaPasta05.activity;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tunaPasta05.R;

public class PopupWindowTest extends Activity {
    //PopupWindow属于不阻塞的对话框，AlertDialog则是阻塞的。
    private TextView tv_showText;
    private Button bt_popupWindow1;
    private Button bt_popupWindow2;
    private Button bt_popupWindow3;
    private Button bt_popupWindow4;
    private PopupWindow popupWindow;
    private int screenWidth, popWindowWidth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupwindowtest);
        initView();
    }

    /* * 初始化控件和响应事件
     */
    private void initView() {
        bt_popupWindow1 = findViewById(R.id.bt_PopupWindow1);
        bt_popupWindow2 = findViewById(R.id.bt_PopupWindow2);
        bt_popupWindow3 = findViewById(R.id.bt_PopupWindow3);
        bt_popupWindow4 = findViewById(R.id.bt_PopupWindow4);
        tv_showText = findViewById(R.id.tv_showText);

        bt_popupWindow1.setOnClickListener(new ClickEvent());
        bt_popupWindow2.setOnClickListener(new ClickEvent());
        bt_popupWindow3.setOnClickListener(new ClickEvent());
        bt_popupWindow4.setOnClickListener(new ClickEvent());
    }

    /* 按钮点击事件处理
     */
    private class ClickEvent implements OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_PopupWindow1:
                    getPopupWindow();
                    //以自己为Anchor，不偏移
                    popupWindow.showAsDropDown(v);
                    break;
                case R.id.bt_PopupWindow2:
                    getPopupWindow();
                    //以自己为Anchor，偏移(screenWidth-popWindowWidth)/ --按钮正下方
                    popupWindow.showAsDropDown(v, (screenWidth - popWindowWidth) / 2, 0);
                    break;
                case R.id.bt_PopupWindow3:
                    //以屏幕中心为参照，不偏移
                    getPopupWindow();
                    popupWindow.showAtLocation(findViewById(R.id.layout), Gravity.CENTER, 0, 0);
                    break;
                case R.id.bt_PopupWindow4:
                    //以屏幕左下角为参照，偏移(screenWidth-popWindowWidth)/  --屏幕下方中央
                    getPopupWindow();
                    popupWindow.showAtLocation(findViewById(R.id.layout), Gravity.BOTTOM, 0, 0);
                    break;
                default:
                    break;
            }
        }
    }

    /* 创建PopupWindow,只执行一次
     */
    protected void initPopuptWindow() {
        //获取自定义布局文件dialog.xml的视图
        View popupWindow_view = getLayoutInflater().inflate(R.layout.popupwindowitem, null, false);
        //创建PopupWindow实例
        popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //dialog.xml视图里面的控件
        Button bt_ok = popupWindow_view.findViewById(R.id.bt_ok);
        Button bt_cancle = popupWindow_view.findViewById(R.id.bt_cancle);
        final EditText edit_text = popupWindow_view.findViewById(R.id.edit_text);
        bt_ok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_showText.setText(edit_text.getText()); //在文本框显示内容
                popupWindow.dismiss();                  //对话框消失
            }
        });

        bt_cancle.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        //获取屏幕和对话框各自高宽
        screenWidth = this.getWindowManager().getDefaultDisplay().getWidth();
        popWindowWidth = popupWindow.getWidth();

        //以下两句代码保证在点击popupWindow外部的地方也能让popupWindow消失
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

    }

    /* 获取PopupWindow实例
     */
    private void getPopupWindow() {
        if (null != popupWindow) {
            popupWindow.dismiss();
            return;
        } else {
            initPopuptWindow();
        }
    }
}
