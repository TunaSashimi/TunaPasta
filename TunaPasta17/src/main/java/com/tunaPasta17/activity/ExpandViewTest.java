package com.tunaPasta17.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tunaPasta17.R;
import com.tunaPasta17.view.ExpandView;

public class ExpandViewTest extends Activity {
    private LinearLayout lin_tv;
    private ImageView img_shrink;
    private ExpandView expandView;
    private TextView textview_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandviewtest);

        lin_tv = findViewById(R.id.lin_tv);
        img_shrink = findViewById(R.id.img_shrink);
        expandView = findViewById(R.id.ex_expandview);
        textview_title = findViewById(R.id.textview_title);

        expandView.setContentView();
        lin_tv.setClickable(true);
        lin_tv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (expandView.isExpand()) {
                    expandView.collapse();
                    textview_title.setText("点击向上展开");
                    img_shrink.setImageDrawable(getResources().getDrawable(R.drawable.icon_arrow_up));
                } else {
                    expandView.expand();
                    textview_title.setText("点击向下收缩");
                    img_shrink.setImageDrawable(getResources().getDrawable(R.drawable.icon_arrow_down));
                }
            }
        });
    }
}
