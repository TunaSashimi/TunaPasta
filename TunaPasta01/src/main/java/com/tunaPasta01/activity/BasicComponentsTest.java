package com.tunaPasta01.activity;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.tunaPasta01.R;

public class BasicComponentsTest extends Activity {
    private TextView text01;
    private Button btn01;
    private EditText edit01, edit02;
    private CheckBox check01;
    private RadioButton radio01, radio02, radio03;
    private DatePicker date01;
    private TimePicker time01;
    private ToggleButton toogle01;
    private ScrollView scroll;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         String.format()用法

         1、转换符
         %s: 字符串类型，如："ljq"
         %b: 布尔类型，如：true
         %d: 整数类型(十进制)，如：99
         %f: 浮点类型，如：99.99
         %%: 百分比类型，如：％
         %n: 换行符
         */

        /**
         * 2、常见日期时间格式化
         * c: 包括全部日期和时间信息 星期六 十月 27 14:21:20 CST 2007
         * F: "年-月-日"格式，如：2007-10-27
         * D: "月/日/年"格式，如：10/27/07
         * r: "HH:MM:SS PM"格式(12时制)，如：02:25:51 下午
         * T: "HH:MM:SS"格式(24时制)，如：14:28:16
         * R: "HH:MM"格式(24时制)，如：14:28
         */

        /**
         * 3、格式化日期字符串
         * b或者h: 月份简称，如
         * 中：十月
         * 英：Oct
         * <p/>
         * B: 月份全称，如
         * 中：十月
         * 英：October
         * <p/>
         * a: 星期的简称，如
         * 中：星期六
         * 英：Sat
         * <p/>
         * A: 星期的全称，如：
         * 中：星期六
         * 英：Saturday
         * <p/>
         * C: 年的前两位数字(不足两位前面补0)，如：20
         * y: 年的后两位数字(不足两位前面补0)，如：07
         * Y: 4位数字的年份(不足4位前面补0)，如：2007
         * j: 一年中的天数(即年的第几天)，如：300
         * m: 两位数字的月份(不足两位前面补0)，如：10
         * d: 两位数字的日(不足两位前面补0)，如：27
         * e: 月份的日(前面不补0)，如：5
         */

        /**
         * 4、格式化时间字符串
         * H: 2位数字24时制的小时(不足2位前面补0)，如：15
         * I: 2位数字12时制的小时(不足2位前面补0)，如：03
         * k: 2位数字24时制的小时(前面不补0)，如：15
         * l: 2位数字12时制的小时(前面不补0)，如：3
         * M: 2位数字的分钟(不足2位前面补0)，如：03
         * S: 2位数字的秒(不足2位前面补0)，如：09
         * L: 3位数字的毫秒(不足3位前面补0)，如：015
         * N: 9位数字的毫秒数(不足9位前面补0)，如：562000000
         * <p/>
         * p: 小写字母的上午或下午标记，如：
         * 中：下午
         * 英：pm
         * <p/>
         * z: 相对于GMT的RFC822时区的偏移量，如：+0800
         * Z: 时区缩写字符串，如：CST
         * s: 1970-1-1 00:00:00 到现在所经过的秒数，如：1193468128
         * Q: 1970-1-1 00:00:00 到现在所经过的毫秒数，如：1193468128984
         */

        setContentView(R.layout.basiccomponenttest);

        text01 = findViewById(R.id.main_text01);
        String str = getResources().getString(R.string.text);
        text01.setText(String.format(str, new Date(), 50, "TunaSashimi团队"));

        btn01 = findViewById(R.id.main_btn01);

        edit01 = findViewById(R.id.main_edit01);

        edit01.setHint("修改提示");

        edit02 = findViewById(R.id.main_edit02);
        check01 = findViewById(R.id.main_check01);

        radio01 = findViewById(R.id.main_radio01);
        radio02 = findViewById(R.id.main_radio02);
        radio03 = findViewById(R.id.main_radio03);

        date01 = findViewById(R.id.main_date01);
        time01 = findViewById(R.id.main_time01);

        toogle01 = findViewById(R.id.main_toogle01);

        scroll = findViewById(R.id.scroll);

//      text01.setTextSize(TypedValue.COMPLEX_UNIT_PX,22);//22像素
//      text01.setTextSize(TypedValue.COMPLEX_UNIT_SP,22);//22SP
//      text01.setTextSize(TypedValue.COMPLEX_UNIT_dp,22);//22dp

//      注意getResources().getDimensionPixelSize(R.dimen.my_text_size)返回的是像素数值
//      main_text01.setTextSize(getResources().getDimensionPixelSize(R.dimen.my_text_size));发生错误，
//      因为setTextSize默认是SP单位，我传进去却是像素的数值，结果字体变异常大了。应该如下:
        text01.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.my_text_size));

        check01.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (check01.isChecked()) {
                    edit02.setVisibility(View.VISIBLE);
                } else {
                    edit02.setVisibility(View.GONE);
                }
            }
        });

        toogle01.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(BasicComponentsTest.this, "scrollview的位置为" + scroll.getScrollY() + "scrollview距离底部的位置为" + scroll.getPaddingBottom(), Toast.LENGTH_SHORT).show();
            }
        });
        btn01.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("EditText01:" + edit01.getText().toString() + "\n");
                stringBuffer.append("CheckBox:" + check01.isChecked() + "\n");

                if (check01.isChecked())
                    stringBuffer.append("EditText02:" + edit02.getText().toString() + "\n");

                stringBuffer.append("RadioButton:");

                if (radio01.isChecked())
                    stringBuffer.append(radio01.getText());

                if (radio02.isChecked())
                    stringBuffer.append(radio02.getText());

                if (radio03.isChecked())
                    stringBuffer.append(radio03.getText());

                stringBuffer.append("\n");
                stringBuffer.append("DatePicker:" + date01.getYear() + "-" + (date01.getMonth() + 1) + "-" + date01.getDayOfMonth() + "\n");
                stringBuffer.append("TimePicker:" + time01.getCurrentHour() + ":" + time01.getCurrentMinute() + "\n");
                stringBuffer.append("ToggleButton:" + toogle01.isChecked());

                Intent it = new Intent(BasicComponentsTest.this, BasicComponentsTestResult.class);
                it.putExtra("values", stringBuffer.toString());
                startActivity(it);
            }
        });

        //
        TextView textPasta = findViewById(R.id.tv_pasta);

        textPasta.setText(Html.fromHtml("<a href=\"tunapasta03:\">打开 TunaPasta03</a><br/>"));
        textPasta.setMovementMethod(LinkMovementMethod.getInstance());

        ViewServer.get(this).addWindow(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ViewServer.get(this).removeWindow(this);
    }
}