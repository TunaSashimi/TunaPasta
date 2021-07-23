package com.tunaPasta09.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tunaPasta09.R;

public class ViewTreeObserverTest extends Activity implements OnClickListener,
        ViewTreeObserver.OnTouchModeChangeListener, // 用于监听 Touch 和非 Touch 模式的转换
        ViewTreeObserver.OnGlobalLayoutListener, // 用于监听布局之类的变化，比如某个空间消失了
        ViewTreeObserver.OnPreDrawListener, // 用于在屏幕上画 View 之前，要做什么额外的工作
        ViewTreeObserver.OnGlobalFocusChangeListener { // 用于监听焦点的变化

    private TextView tv_show;
    private ViewTreeObserver vto;
    private View all;
    private EditText ed1;
    private EditText ed2;
    private TextView tv_display;
    private Button button;
    private boolean btnClicked;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.viewtreeobservertest);

        tv_show = findViewById(R.id.tv_show);

        // 得到整个屏幕对象 ， 因为顶层 layout 的width 和 height 都是MATCH_PARENT
        all = findViewById(R.id.full_screen);

        // 通过getViewTreeObserver获得ViewTreeObserver对象
        vto = all.getViewTreeObserver();

        tv_display = findViewById(R.id.tv_display);

        ed1 = findViewById(R.id.ed_enter1);

        ed2 = findViewById(R.id.ed_enter2);

        button = findViewById(R.id.button);

        button.setOnClickListener(this);

        vto.addOnTouchModeChangeListener(this); // 增加对应的 Listener

        vto.addOnGlobalFocusChangeListener(this); // 增加对应的 Listener

        vto.addOnPreDrawListener(this); // 增加对应的 Listener

        vto.addOnGlobalLayoutListener(this); // 增加对应的 Listener

    }

    // onTouchModeChanged 是接口 ViewTreeObserver.OnTouchModeChangeListener中定义的方法。
    @Override
    public void onTouchModeChanged(boolean isInTouchMode) {
        if (isInTouchMode)
            tv_show.setText("In touch mode");
        else
            tv_show.setText("Not in touch mode");
    }

    // onGlobalLayout 是接口 ViewTreeObserver.OnGlobalLayoutListener中定义的方法。
    // Callback method to be invoked when the global layout state or the
    // visibility of views within the view tree changes

    @Override
    public void onGlobalLayout() {
        if (btnClicked) {
            if (!ed2.isShown())
                ed1.setText(" 第二个 EditText 不见了 ");
            else
                ed1.setText(" 第二个 EditText 出来了 ");
        }
    }

    // onPreDraw 是接口 ViewTreeObserver.OnPreDrawListener
    // 中定义的方法。

    @Override
    public boolean onPreDraw() {

        // 在屏幕上画出 ed1 控件之间 ， 给它增加一个提示 ， 并改变其字体大小

        ed1.setHint(" 在 onPreDraw 方法中增加一个提示信息 ");

        ed1.setTextSize((float) 20.0);

        // return false; // Return true to proceed with the current drawing
        // pass, or false to cancel.

        return true; // 如果此处不返回 true ， 则整个界面不能完整显示。

    }

    // onGlobalFocusChanged 是接口 ViewTreeObserver.OnGlobalFocusChangeListener中定义的方法。焦点发生变化时，会触发这个方法的执行

    @Override
    public void onGlobalFocusChanged(View oldFocus, View newFocus) {
        if (oldFocus != null && newFocus != null) {
            tv_display.setText("Focus /nFROM:/t" + oldFocus.toString() + "/n     TO:/t" + newFocus.toString());
        }
    }

    @Override
    public void onClick(View v) {
        // 改变 ed2 的可见性 ， 会触发 onGlobalLayout 方法的执行
        btnClicked = true;
        if (v.getId() == R.id.button) {
            if (ed2.isShown())
                ed2.setVisibility(View.INVISIBLE);
            else
                ed2.setVisibility(View.VISIBLE);
        }
    }
}