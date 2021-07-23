package com.tunaPasta01.activity;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tunaPasta01.R;

//先在res/anim中创建list_anim_layout.xml文件和list_anim.xml文件,
//凡是要用到的地方只要在属性中配上android:layoutAnimation="@anim/list_anim_layout"即可~
public class ToastTest extends Activity {
    Handler handler = new Handler();

    protected void onCreate(Bundle bu) {
        super.onCreate(bu);
        setContentView(R.layout.toasttest);

        Button button01 = findViewById(R.id.button01);
        Button button02 = findViewById(R.id.button02);
        Button button03 = findViewById(R.id.button03);
        Button button04 = findViewById(R.id.button04);
        Button button05 = findViewById(R.id.button05);
        Button button06 = findViewById(R.id.button06);
        Button button07 = findViewById(R.id.button07);

        button01.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ToastTest.this, "ActivityContext", Toast.LENGTH_SHORT).show();
            }
        });

        button02.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // this.getApplicationContext（）取的是这个应用程序的Context，Activity.this取的是这个Activity的Context，这两者的生命周期是不同
                // 的，前者的生命周期是整个应用，后者的生命周期只是它所在的Activity。
                Toast.makeText(getApplicationContext(), "ApplicationContext", Toast.LENGTH_SHORT).show();
            }
        });

        button03.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 兼容所有机型
                Toast t = new Toast(getApplication());
                t.setDuration(Toast.LENGTH_LONG);
                t.setGravity(Gravity.FILL_HORIZONTAL, 0, getActionBarHeight());
                t.setView(createContentView("全屏风格"));
                t.show();
            }
        });

        button04.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "自定义位置", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        button05.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "带图片TOAST", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                ImageView add = new ImageView(getApplicationContext());
                add.setImageResource(R.drawable.bear);
                LinearLayout toastView = (LinearLayout) toast.getView();
                // 设置LinearLayout的布局取向
                toastView.setOrientation(LinearLayout.VERTICAL); // 给touast布局设置纵向~
                // 给toastView添加View布局
                toastView.addView(add, 180, 240);
                toast.show();
            }
        });

        button06.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // android.view.LayoutInflater inflater = getLayoutInflater();
                // 本来要用返回的inflaer来得到layout的,这里直接省略~
                View layout = getLayoutInflater().inflate(R.layout.toasttestcustomitem, (ViewGroup) findViewById(R.id.llToast));
                ImageView image = layout.findViewById(R.id.tvImageToast);
                image.setImageResource(R.drawable.tunasashimi);
                TextView title = layout.findViewById(R.id.tvTitleToast);
                title.setText("Tuna");
                TextView text = layout.findViewById(R.id.tvTextToast);
                text.setText("完全自定义Toast");
                text.setTextColor(Color.YELLOW);
                Toast toast = new Toast(getApplicationContext());
                // 学习用法~
                toast.setGravity(Gravity.RIGHT | Gravity.TOP, 12, 40);
                toast.setDuration(Toast.LENGTH_LONG);

                toast.setView(layout);
                toast.show();
            }
        });

        button07.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        showToast();
                    }
                }).start();
            }
        });
    }


    public void showToast() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "其他线程！", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "我来自其他线程！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private TextView createContentView(String msg) {
        TextView tv = new TextView(this);
        //下面这句不能少, 否则将没有任何东西显示
        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tv.setText(msg);
        tv.setGravity(Gravity.CENTER);
        int padding = toPx(12);
        tv.setPadding(padding, padding, padding, padding);
        tv.setBackgroundColor(Color.parseColor("#88000000"));
        tv.setTextColor(Color.WHITE);
        return tv;
    }

    //获取ActionBar的高度
    private int getActionBarHeight() {
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelOffset(tv.data, getResources().getDisplayMetrics());
        } else {
            //获取不到系统属性, 设置一个默认高度
            return toPx(48);
        }
    }

    public static int toPx(float dpValue) {
        Resources resources = Resources.getSystem();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, resources.getDisplayMetrics());
        return (int) px;
    }
}
