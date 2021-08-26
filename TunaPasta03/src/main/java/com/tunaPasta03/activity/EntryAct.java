package com.tunaPasta03.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tunaPasta03.R;

public class EntryAct extends Activity {
    private String[] s = {"DressUpGameTest", "DialogMenuTest", "DataBaseTest", "ContactDatabaseTest"
            , "AnimationTest", "FrameAnimationTest", "AnimationListenerTest", "BroadcastReceiverTest"};
    private Class<?>[] c = {DressUpGameTest.class, DialogMenuTest.class, DataBaseTest.class, ContactDatabaseTest.class
            ,AnimationTest.class, FrameAnimationTest.class, AnimationListenerTest.class, BroadcastReceiverTest.class};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置标题栏样式,要在contentview之前
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.entryact);
        ListView lv = findViewById(R.id.listview);
        List<String> list = new ArrayList();
        for (int i = 0; i < s.length; i++) {
            list.add(s[i]);
        }
        lv.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, list));
        lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                startActivity(new Intent(EntryAct.this, c[arg2]));
            }
        });
    }
}