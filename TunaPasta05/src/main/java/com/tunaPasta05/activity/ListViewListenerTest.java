package com.tunaPasta05.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.tunaPasta05.R;
import com.tunaPasta05.adapter.ListViewListenerAdapter;

public class ListViewListenerTest extends Activity {
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listlistenertest);

        listview = findViewById(R.id.listview);

        int[] i = new int[88];
        for (int j = 0; j < i.length; j++) {
            i[j] = j;
        }
        listview.setAdapter(new ListViewListenerAdapter(this, i, listview));
    }
}
