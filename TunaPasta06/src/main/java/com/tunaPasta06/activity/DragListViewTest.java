package com.tunaPasta06.activity;

import android.app.Activity;
import android.os.Bundle;

import com.tunaPasta06.R;
import com.tunaPasta06.adapter.DragListViewAdapter;
import com.tunaPasta06.widget.TouchInterceptorListView;

public class DragListViewTest extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.draglistviewtest);
        
        TouchInterceptorListView lv = findViewById(R.id.list_view);
        
        String[] strs = getResources().getStringArray(R.array.lv_data);
        DragListViewAdapter draglistviewadapter = new DragListViewAdapter(this, strs);
        
        lv.setAdapter(draglistviewadapter);
    }
}