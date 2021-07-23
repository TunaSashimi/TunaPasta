package com.tunaPasta15.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.tunaPasta15.R;
import com.tunaPasta15.widget.FlakeView;

public class Droidflakes extends Activity {

    FlakeView flakeView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.droidflakes);
        LinearLayout container = findViewById(R.id.container);
        flakeView = new FlakeView(this);
        container.addView(flakeView);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));

        Button more = findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flakeView.addFlakes(flakeView.getNumFlakes());
            }
        });
        Button less = findViewById(R.id.less);
        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flakeView.subtractFlakes(flakeView.getNumFlakes() / 2);
            }
        });
        if (Integer.parseInt(Build.VERSION.SDK) >= Build.VERSION_CODES.HONEYCOMB) {
            HoneycombHelper.setup(this);
        }
    }

    private static final class HoneycombHelper {
        static void setup(final Droidflakes activity) {
            CheckBox accelerated = activity.findViewById(R.id.accelerated);
            accelerated.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {
                    activity.flakeView.setLayerType(
                            isChecked ? View.LAYER_TYPE_NONE
                                    : View.LAYER_TYPE_SOFTWARE, null);
                }
            });
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        flakeView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        flakeView.resume();
    }

}
