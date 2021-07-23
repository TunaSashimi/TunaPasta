package com.tunaPasta06.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.TextView;

import com.tunaPasta06.R;

public class GetPhoneSpecificationsTest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.getphonespecificationstest);

        TextView text002 = findViewById(R.id.text002);
        final TextView text003 = findViewById(R.id.text003);
        final View view01 = findViewById(R.id.view01);

        Button button01 = findViewById(R.id.button01);

        String str = "";
        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        float density = dm.density;
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;

        str += "屏幕分辨率为:" + dm.widthPixels + " * " + dm.heightPixels + "\n";
        str += "绝对宽度:" + screenWidth + "pixels\n";
        str += "绝对高度:" + screenHeight + "pixels\n";
        str += "逻辑密度:" + density + "\n";
        str += "X 维 :" + xdpi + "像素每英尺\n";
        str += "Y 维 :" + ydpi + "像素每英尺\n";

        text002.setText(str);

        ViewTreeObserver vto2 = view01.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view01.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                text003.append("控件的宽度和高度分别为\n" + view01.getWidth() + "," + view01.getHeight());
            }
        });

        button01.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                view01.setBackgroundResource(R.drawable.btn_back);
                text003.append("控件新长度和宽度分别为\n" + view01.getHeight() + "," + view01.getWidth());
            }
        });
    }
}
