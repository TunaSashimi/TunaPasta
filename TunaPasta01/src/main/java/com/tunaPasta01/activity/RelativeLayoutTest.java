package com.tunaPasta01.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.RelativeLayout;

import com.tunaPasta01.R;

public class RelativeLayoutTest extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relativelayouttest);

        RelativeLayout relativeLayout = new RelativeLayout(getApplicationContext());
        relativeLayout.setGravity(Gravity.FILL);
    }
}