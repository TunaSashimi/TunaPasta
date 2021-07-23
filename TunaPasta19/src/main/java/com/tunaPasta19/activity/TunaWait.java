package com.tunaPasta19.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaAnimation;

public class TunaWait extends Activity {

    private ImageView imageDriverSpreadBefore, imageDriverSpreadMiddle,
            imageDriverSpreadAfter, imageDriverHalo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tunawait);

        imageDriverSpreadBefore = findViewById(R.id.imageDriverSpreadBefore);
        imageDriverSpreadMiddle = findViewById(R.id.imageDriverSpreadMiddle);
        imageDriverSpreadAfter = findViewById(R.id.imageDriverSpreadAfter);

        imageDriverHalo = findViewById(R.id.imageDriverHalo);

        setTimerTask();
    }

    private static final int PLAY_SPREADBEFORE_ANIMATION = 0;
    private static final int PLAY_SPREADMIDDLE_ANIMATION = 1;
    private static final int PLAY_SPREADAFTER_ANIMATION = 2;
    private static final int PLAY_HALOAPPEAR_ANIMATION = 3;

    private Timer timerSpreadBefore = new Timer();
    private Timer timerSpreadMiddle = new Timer();
    private Timer timerSpreadAfter = new Timer();
    private Timer timerHaloAppear = new Timer();

    private void setTimerTask() {

        timerSpreadBefore.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(PLAY_SPREADBEFORE_ANIMATION);
            }
        }, 0, 3000);

        timerSpreadMiddle.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(PLAY_SPREADMIDDLE_ANIMATION);
            }
        }, 250, 3000);

        timerSpreadAfter.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(PLAY_SPREADAFTER_ANIMATION);
            }
        }, 500, 3000);

        timerHaloAppear.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(PLAY_HALOAPPEAR_ANIMATION);
            }
        }, 0, 3000);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case PLAY_SPREADBEFORE_ANIMATION:
                    TunaAnimation.playObjectAnimation(imageDriverSpreadBefore,
                            TunaAnimation.tunawait_circleSpreadParaBefore);
                    break;
                case PLAY_SPREADMIDDLE_ANIMATION:
                    TunaAnimation.playObjectAnimation(imageDriverSpreadMiddle,
                            TunaAnimation.tunawait_circleSpreadParaMiddle);
                    break;
                case PLAY_SPREADAFTER_ANIMATION:
                    TunaAnimation.playObjectAnimation(imageDriverSpreadAfter,
                            TunaAnimation.tunawait_circleSpreadParaAfter);
                    break;
                case PLAY_HALOAPPEAR_ANIMATION:
                    TunaAnimation.playObjectAnimation(imageDriverHalo,
                            TunaAnimation.tunawait_haloAppearPara,
                            true,
                            null);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timerSpreadBefore.cancel();
        timerSpreadMiddle.cancel();
        timerSpreadAfter.cancel();
        timerHaloAppear.cancel();
    }

}
