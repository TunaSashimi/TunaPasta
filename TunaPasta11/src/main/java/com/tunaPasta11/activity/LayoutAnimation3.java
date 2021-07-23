package com.tunaPasta11.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.tunaPasta11.R;

public class LayoutAnimation3 extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_animation_3);
        setListAdapter(new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, mStrings));
    }

    private String[] mStrings = {
        "Bordeaux",
        "Lyon",
        "Marseille",
        "Nancy",
        "Paris",
        "Toulouse",
        "Strasbourg"
    };
}
