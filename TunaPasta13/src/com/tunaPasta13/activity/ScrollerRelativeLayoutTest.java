package com.tunaPasta13.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.tunaPasta13.R;
import com.tunaPasta13.view.ScrollerRelativeLayout;

public class ScrollerRelativeLayoutTest extends Activity {
    private ScrollerRelativeLayout myViewGroup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollerrelativelayouttest);
        myViewGroup = findViewById(R.id.myviewGroup);

    }

    public void scroll(View view) {
        myViewGroup.beginScroll();
    }
}