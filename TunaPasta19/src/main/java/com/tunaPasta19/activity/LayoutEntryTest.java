package com.tunaPasta19.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tunaPasta19.R;

public class LayoutEntryTest extends Activity {

    private Class<?>[] c = {MoveBackGroundTest.class, RelaxLayoutTest.class, FlipLayoutTest.class, WaterFallTest.class,
            SlidingMenuLayoutTest.class, DoubleSlidingLayoutTest.class, DragGridViewTest.class, DialLayoutTest.class,
            Rotate3DLayoutTest.class, ProtrusionLayoutTest.class, MultiTagLayoutTestChain.class, MultiTagLayoutTest.class,
            RollLayoutTest.class, StageLayoutTest.class,
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layoutentrytest);

        ListView lv = findViewById(R.id.listView);
        List<String> list = new ArrayList();

        String[] LayoutEntryTestTitleArray = getResources().getStringArray(R.array.layoutentrytest_titleArray);
        for (int i = 0; i < LayoutEntryTestTitleArray.length; i++) {
            list.add(LayoutEntryTestTitleArray[i]);
        }

        lv.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, list));

        lv.setOnItemClickListener((arg0, arg1, arg2, arg3) -> startActivity(new Intent(LayoutEntryTest.this, c[arg2])));
    }
}
