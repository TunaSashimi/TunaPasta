package com.tunaPasta01.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.tunaPasta01.R;

public class EntryAct extends Activity {
    private Button[] btns;
    private String[] btnStrs = {"BasicComponentsTest", "TableLayoutTest", "RelativeLayoutTest", "SpinnerTest",
            "ListViewTest", "ToastTest", "ProgressSeekBarTest", "RadioGroupTest",
            "FileAccessTest", "HandlerTest",};
    private Class<?>[] cc = {BasicComponentsTest.class, TableLayoutTest.class, RelativeLayoutTest.class, SpinnerTest.class,
            ListViewTest.class, ToastTest.class, ProgressSeekBarTest.class, RadioGroupTest.class,
            FileAccessTest.class, HandlerTest.class,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 隐去标题（应用的名字) 此设定必须要写在setContentView之前，否则会有异常!
        // 对于设置全屏，第一点是设置隐去状态栏部分，包括电池等图标，第二点把设置应用的名字也隐去不显示。
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐去电池等图标和一切修饰部分（状态栏部分）
        requestWindowFeature(Window.FEATURE_NO_TITLE);        // 隐去标题栏（程序的名字）
//		requestWindowFeature(featrueId),它的功能是启用窗体的扩展特性。参数是Window类中定义的常量。
//		.FEATURE_NO_TITLE：无标题选择~
        setContentView(R.layout.entryact);

        btns = new Button[btnStrs.length];

        for (int i = 0; i < btns.length; i++) {
            int id = getResources().getIdentifier("entryact_btn0" + i, "id", getPackageName());
            btns[i] = findViewById(id);
            btns[i].setText(btnStrs[i]);
            btns[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < btns.length; i++)
                        if (btns[i].equals(v)) {
                            Intent intent = new Intent(EntryAct.this, cc[i]);
                            startActivity(intent);
                        }
                }
            });
        }
        //HierarchyView视图检测工具
        ViewServer.get(this).addWindow(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewServer.get(this).removeWindow(this);
    }
}
