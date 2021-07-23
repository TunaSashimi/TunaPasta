package com.tunaPasta19.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.tunaPasta19.R;

public class MainEntryTest extends TabActivity {
    private int[] in = {R.drawable.rotate3dlayouttest_button_imagebutton_sleep, R.drawable.rotate3dlayouttest_button_imagebutton_with, R.drawable.rotate3dlayouttest_button_imagebutton_place,
        R.drawable.rotate3dlayouttest_button_imagebutton_thought, R.drawable.rotate3dlayouttest_button_imagebutton_camera, R.drawable.rotate3dlayouttest_button_imagebutton_music,
        R.drawable.rotate3dlayouttest_button_imagebutton_music,};

    private Class<?>[] c = {LoginEntryTest.class, LinkEntryTest.class, LayoutEntryTest.class,
        ReportEntryTest.class, FunctionEntryTest.class, WidgetEntryTest.class,
        BusinessEntryTest.class,
    };

    private TabHost tabhost;
    private int times;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mainentrytest);
        tabhost = getTabHost();

        String[] MainEntryTestTitleArray = getResources().getStringArray(R.array.mainentrytest_titleArray);
        for (int i = 0; i < MainEntryTestTitleArray.length; i++) {
            addTabItem(MainEntryTestTitleArray[i], in[i], new Intent(this, c[i]));
        }
    }

    private void addTabItem(String title, int icon, Intent intent) {
        TabSpec tabSpec = tabhost.newTabSpec(title);
        View tabItem = getLayoutInflater().inflate(R.layout.mainentrytestitem, null);
        TextView textview = tabItem.findViewById(R.id.tab_item_tv);
        textview.setPadding(3, 3, 3, 3);
        textview.setText(title);
        if (times % 2 == 0) {
            textview.setBackgroundResource(R.drawable.maintab_toolbar_bgx1);
        } else {
            textview.setBackgroundResource(R.drawable.maintab_toolbar_bgx2);
        }
        times++;
        textview.setCompoundDrawablesWithIntrinsicBounds(0, icon, 0, 0);
        tabSpec.setIndicator(tabItem);
        tabSpec.setContent(intent);
        tabhost.addTab(tabSpec);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }
}
