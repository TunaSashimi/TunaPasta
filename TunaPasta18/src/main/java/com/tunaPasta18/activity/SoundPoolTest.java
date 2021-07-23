package com.tunaPasta18.activity;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tunaPasta18.R;

/**
 * @author Tunasashimi
 * @date 11/3/15 20:43
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class SoundPoolTest extends Activity {

    private SoundPool mSoundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.soundpooltest);

        mSoundPool = new SoundPool(1, AudioManager.STREAM_SYSTEM, 1);
        mSoundPool.load(this, R.raw.noface, 1);


        Button button = findViewById(R.id.btn_play);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSoundPool.play(1, 1, 1, 0, 0, 1);
            }
        });
    }
}
