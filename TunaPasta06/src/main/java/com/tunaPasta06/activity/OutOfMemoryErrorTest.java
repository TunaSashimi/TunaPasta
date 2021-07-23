package com.tunaPasta06.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.tunaPasta06.R;
import com.tunaPasta06.adapter.OutOfMemoryErrorTestAdapter;

//接下来放心大胆的测试吧吗哈哈 ，这个解决方案 虽然并不是很规范 但是基本能解决 内存溢出的问题。我用了500k左右的图片 测试下没问题 还有我的运行版本是2.2。希望能给被这个问题困扰的朋友们提供些思路。
public class OutOfMemoryErrorTest extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outofmemoryerrortest);
        ArrayList<Integer> testList = new ArrayList<Integer>();
        for (int i = 0; i < 30; i++) {
            testList.add(0);
        }
        OutOfMemoryErrorTestAdapter ta = new OutOfMemoryErrorTestAdapter(this, testList);
        ListView lv = findViewById(R.id.testListView);
        lv.setAdapter(ta);
    }
}