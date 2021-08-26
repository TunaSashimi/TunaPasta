package com.tunaPasta02.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.tunaPasta02.R;

public class EntryAct extends Activity implements OnGestureListener, OnTouchListener {
    private String[] s = {"TextSwitcherTest", "AutoCompleteTextViewTest", "ListActivityTest", "GetImageMethodTest"
            , "ScaleTypeTest", "EditTextTest", "SharedPreferencesTest", "MarqueeTextViewTest"
            , "SurfaceViewText", "LearnGameViewTest", "MineTest", "MatrixViewTest"
            , "CanvasTest", "MoveBackTest", "WarpViewTest", "DrawOnPathTest"
            , "PathInvalidataTest", "XMLShapeTest", "WebViewTest", "TelephoneTest"
            , "ReadContactTest", "ServiceTest", "SendbroadcastTest", "PalmistryTest"
            , "CircleProgressViewTest", "TalkUITest", "ChartViewTest", "GlowPadTest"};

    private Class<?>[] c = {TextSwitcherTest.class, AutoCompleteTextViewTest.class, ListActivityTest.class, GetImageMethodTest.class
            , ScaleTypeTest.class, EditTextTest.class, SharedPreferencesTest.class, MarqueeTextViewTest.class
            , SurfaceViewTest.class, LearnGameViewTest.class, MineTest.class, MatrixViewTest.class
            , CanvasTest.class, MoveBackTest.class, WarpViewTest.class, DrawOnPathTest.class
            , PathInvalidataViewTest.class, XMLShapeTest.class, WebViewTest.class, TelephoneTest.class
            , ReadContactTest.class, ServiceTest.class, SendbroadcastTest.class, PalmistryTest.class
            , CircleProgressViewTest.class, TalkUITest.class, ChartViewTest.class, GlowPadViewTest.class};

    private GestureDetector myGestureDetector; // 定义一个GestureDetector(手势识别类)对象的引用
    private LinearLayout linear;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.entryact); // 语句排列顺序~
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title); // 自定义title布局标题栏~
        ListView lv = findViewById(R.id.listView);
        List<String> list = new ArrayList();
        for (int i = 0; i < s.length; i++) {
            list.add(s[i]);
        }
        lv.setAdapter(new ArrayAdapter(this, R.layout.entryactitem, list));
        lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                startActivity(new Intent(EntryAct.this, c[arg2]));
            }
        });

        // 实例化GestureDetector对象。
        myGestureDetector = new GestureDetector(this);
        linear = findViewById(R.id.rootlinear);
        // 让linear能够处理长按操作。
        linear.setLongClickable(true);
        // 对linear设置触屏事件监听器。
        linear.setOnTouchListener(this);

        Toast.makeText(this, "通过向右滑动的手势启动最后一个activity", Toast.LENGTH_LONG).show();
    }

    /*
     * 实现OnTouchListener接口中的onTouch()方法，当View上发生触屏时间时调用，传如一个View和一个运动事件event，
     * 我们将这个event传给OnGestureListener接口的onTouchEvent()方法处理，这样我们的OnFling()就能工作了
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return myGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        // 参数e1是按下事件，e2是放开事件，剩下两个是滑动的速度分量，这里用不到
        // 按下时的横坐标大于放开时的横坐标，从右向左滑动
        if (e2.getX() > e1.getX()) {
            startActivity(new Intent(EntryAct.this, c[c.length - 1]));
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }
}