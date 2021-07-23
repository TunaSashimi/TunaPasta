package com.tunaPasta19.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaAnimation;

public class TunaLoad extends Activity {
    private FrameLayout frameStayFirst, frameStayFinal, frameClipFalse, frameClipTrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tunaload);

        frameStayFirst = findViewById(R.id.frameStayFirst);
        frameStayFinal = findViewById(R.id.frameStayFinal);
        frameClipFalse = findViewById(R.id.frameClipFalse);
        frameClipTrue = findViewById(R.id.frameClipTrue);

        TunaAnimation.playObjectAnimation(frameStayFinal, TunaAnimation.tunaload_loadLayoutAlphaPara);

        TunaAnimation.playObjectAnimation(frameClipFalse, TunaAnimation.tunaload_loadLayoutScalePara);

        TunaAnimation.playObjectAnimation(frameClipTrue, TunaAnimation.tunaload_loadLayoutScalePara);

        frameStayFirst.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                TunaAnimation.playToCenterAnimation(TunaLoad.this, frameStayFirst, false, TunaAnimation.tunaload_toCenterPara, null);
            }
        });

        frameStayFinal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                TunaAnimation.playToCenterAnimation(TunaLoad.this, frameStayFinal, true, TunaAnimation.tunaload_toCenterAlphaPara, false, null);
            }
        });

        frameClipFalse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                TunaAnimation.playObjectAnimation(frameClipFalse, TunaAnimation.tunaload_framePara);
            }
        });

        frameClipTrue.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                TunaAnimation.playObjectAnimation(frameClipTrue, TunaAnimation.tunaload_framePara);
            }
        });
    }
}
