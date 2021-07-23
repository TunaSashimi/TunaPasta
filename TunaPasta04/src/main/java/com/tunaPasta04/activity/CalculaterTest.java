package com.tunaPasta04.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextSwitcher;
import android.widget.ViewSwitcher.ViewFactory;

import com.tunaPasta04.R;
import com.tunaPasta04.tool.CalculaterTool;
import com.tunaPasta04.tool.ConvertTool;

//android:digits 设置允许输入哪些字符,非常有用的方法~阻止用户从键盘输入~
public class CalculaterTest extends Activity {
    private Button[] btnNums = new Button[10];
    private Button btnClear, btnCut, btnSum, btnDivide, btnMultiply, btnPoint, btnEqual;
    private TextSwitcher ts;
    private RadioButton rabub, rabuo, rabuh;
    private OnClickListener listener = new OnClickListener() {        // 设定数字键的OnClick方法
        @Override
        public void onClick(View v) {
            Button btn = (Button) v;
            EditText text = (EditText) ts.getCurrentView();            //ts.setCurrentText相当于text.setText
            ts.setCurrentText(text.getText().toString() + btn.getText().toString());
            text.setSelection(text.length());                                    // 将光标调整到最后一行
        }
    };

    private OnClickListener listenerSp = new OnClickListener() {    // 设定+*/运算键的OnClick方法
        @Override
        public void onClick(View v) {
            Button btn = (Button) v;
            EditText text = (EditText) ts.getCurrentView();
            String s = "" + text.getText().toString();
            if (s.matches(".*[\\+\\-\\×\\÷]\\-"))        //匹配已经输入过运算符字符串~
                s = s.substring(0, s.length() - 2);      //如果尾部有连续的*-号之类的,直接减掉两个值~
            if (s.matches(".*[\\+\\-×÷]"))               //匹配已经输入过运算符字符串~
                s = s.substring(0, s.length() - 1);      //把尾部的运算符去掉~
            ts.setCurrentText(s + btn.getText().toString());
            text.setSelection(s.length());                // 将光标调整到最后一行
        }
    };

    @Override        //旋转屏
    public void onConfigurationChanged(android.content.res.Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculatetest);
        ts = findViewById(R.id.calc_ts);
        ts.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.in_anim));    //淡出的动画~
        ts.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.out_anim));
        ts.setFactory(new ViewFactory() {
            @Override
            public View makeView() {
                EditText text = new EditText(CalculaterTest.this);
                text.setSingleLine();                                    //不设置的话,是一个会变长的计算器,效果可观~
                //设置给出的Edittext在父控件TextSwitcher中的布局,填充满父控件
                text.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)); //设置宽、高
                text.setGravity(Gravity.RIGHT + Gravity.BOTTOM);
                text.setTextSize(55);
                return text;
            }
        });

        String packageName = getPackageName();

        for (int i = 0; i <= 9; i++) {
            int id = getResources().getIdentifier("calc_btn" + i, "id", packageName);
            // 把每一个数字button的id捆绑在数组button的元素上并设置数字键监听器listener
            btnNums[i] = findViewById(id);
            btnNums[i].setOnClickListener(listener);
        }
        btnCut = findViewById(R.id.calc_btnCut);
        btnSum = findViewById(R.id.calc_btnSum);
        btnDivide = findViewById(R.id.calc_btnDivide);
        btnMultiply = findViewById(R.id.calc_btnMultiply);
        btnPoint = findViewById(R.id.calc_btnPoint);
        btnEqual = findViewById(R.id.calc_btnEqual);
        btnClear = findViewById(R.id.calc_btnClear);

        //二进制与八进制两个单选框拿到:
        rabub = findViewById(R.id.calc_rabuB);
        rabuo = findViewById(R.id.calc_rabuO);
        rabuh = findViewById(R.id.calc_rabuH);

        //设置单选框的监听器~
        rabub.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    EditText text = (EditText) ts.getCurrentView();
                    String content = text.getText().toString();
                    ts.setText(ConvertTool.getBinary(content));
                    text.setSelection(text.length());
                    rabub.setChecked(false);
                }
            }
        });

        rabuo.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    EditText text = (EditText) ts.getCurrentView();
                    String content = text.getText().toString();
                    ts.setText(ConvertTool.getOctal(content));
                    text.setSelection(text.length());
                    rabuo.setChecked(false);
                }
            }
        });

        rabuh.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    EditText text = (EditText) ts.getCurrentView();
                    String content = text.getText().toString();
                    ts.setText(ConvertTool.getHex(content));
                    text.setSelection(text.length());
                    rabuh.setChecked(false);
                }
            }
        });

        // 加减乘除,设置运算键的监听器listener
        btnSum.setOnClickListener(listenerSp);
        btnDivide.setOnClickListener(listenerSp);
        btnMultiply.setOnClickListener(listenerSp);

        //	设置减法的监听器~
        btnCut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                EditText text = (EditText) ts.getCurrentView();
                String s = "" + text.getText().toString();
                if (s.matches(".*\\-\\-"))                            //匹配已经输入过运算符字符串~
                    s = s.substring(0, s.length() - 1);                //把尾部的运算符去掉~
                ts.setCurrentText(s + btn.getText().toString());
                text.setSelection(s.length());                        // 将光标调整到最后一行
            }
        });

        // 按点号的时候发生的事件~
        btnPoint.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                EditText text = (EditText) ts.getCurrentView();
                String s = "" + text.getText().toString();
                if (s.equals("") || s.endsWith("+") || s.endsWith("-") || s.endsWith("×") || s.endsWith("÷")) {
                    ts.setCurrentText(s + "0" + btn.getText().toString());
                } else {
                    String[] ss = s.split("[\\+\\-×÷]");        //按加减乘除来拆分看看`
                    if (!ss[ss.length - 1].contains("."))
                        ts.setCurrentText(s + btn.getText().toString());
                }
                text.setSelection(s.length());            // 将光标调整到最后一行
            }
        });

        // 按清除号的时候发生的事件~
        btnClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text = (EditText) ts.getCurrentView();
                text.setText("");
            }
        });

        // 按等号的时候发生的事件~
        btnEqual.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text = (EditText) ts.getCurrentView();
                String exp = text.getText().toString();
                ts.setText(new CalculaterTool(exp).CalResults());
                text.setSelection(text.length());            // 将光标调整到最后一行
            }
        });
    }
}