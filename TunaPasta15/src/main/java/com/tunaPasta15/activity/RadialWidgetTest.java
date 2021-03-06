package com.tunaPasta15.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tunaPasta15.R;

public class RadialWidgetTest extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, getResources()
                .getStringArray(R.array.main_menu)));

        ListView listView = getListView();
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(RadialWidgetTest.this,
                                AltRadialMenuActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(RadialWidgetTest.this,
                                RadialMenuActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(RadialWidgetTest.this, RadialProgressActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(RadialWidgetTest.this, SemiCircularRadialMenuActivity.class));
                        break;
                }
            }
        });
    }
}
