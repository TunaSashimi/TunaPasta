package com.tunaPasta19.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaRow;

public class TunaRowTest extends Activity {
    private final static int TUNAROW_PLAY = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TUNAROW_PLAY:
                    tunaRow.play();
                    break;
                default:
                    break;
            }
        }
    };
    private TunaRow tunaRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tunarowtest);

        tunaRow = findViewById(R.id.tunaRow);
        handler.sendEmptyMessageDelayed(TUNAROW_PLAY, 1000);
    }
}
