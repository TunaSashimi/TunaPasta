package com.tunaPasta01.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import com.tunaPasta01.R;

public class ProgressSeekBarTest extends Activity {
    private ProgressBar progress_bar_02, progress_bar_03, progress_bar_04, progress_bar_05, progress_bar_06, progress_bar_07;
    private RatingBar ratingBar;
    private SeekBar seek_bar_01, seek_bar_02;
    private Button btn_01, btn_02;

    private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.progressseekbartest);

        progress_bar_02 = findViewById(R.id.progress_bar_02);
        progress_bar_03 = findViewById(R.id.progress_bar_03);
        progress_bar_04 = findViewById(R.id.progress_bar_04);
        progress_bar_05 = findViewById(R.id.progress_bar_05);
        progress_bar_06 = findViewById(R.id.progress_bar_06);
        progress_bar_07 = findViewById(R.id.progress_bar_07);

        ratingBar = findViewById(R.id.rating_bar_01);

        seek_bar_01 = findViewById(R.id.seek_bar_01);
        seek_bar_02 = findViewById(R.id.seek_bar_02);

        btn_01 = findViewById(R.id.button_01);
        btn_02 = findViewById(R.id.button_02);

        chronometer = findViewById(R.id.chronometer);
        //setFormat设置用于显示的格式化字符串。
        //格式化字符串:如果指定，计时器将根据这个字符串来显示，替换字符串中第一个“%s”为当前"MM:SS"或 "H:MM:SS"格式的时间显示。  
        chronometer.setFormat("计时：%s");

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    if (i == 100) {
                        i = 0;
                    }
                    try {
                        progress_bar_02.setProgress(i);
                        progress_bar_02.setSecondaryProgress(i + 20);

                        progress_bar_03.setProgress(i);
                        progress_bar_03.setSecondaryProgress(i + 20);

                        progress_bar_04.setProgress(i);

                        progress_bar_05.setProgress(100 - i);
                        progress_bar_05.setSecondaryProgress(100 - i + 20);

                        progress_bar_06.setProgress(100 - i);
                        progress_bar_06.setSecondaryProgress(100 - i + 20);

                        progress_bar_07.setProgress(i);
                        Thread.sleep(200);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        btn_01.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                int p1 = progress_bar_02.getProgress();
                int p2 = ratingBar.getProgress();
                int p3 = seek_bar_01.getProgress();
                String str = "ProgressBar:" + p1 + "\n";
                str += "RatingBar:" + p2 + "\n";
                str += "SeekBar:" + p3;
                android.widget.Toast.makeText(ProgressSeekBarTest.this, str, Toast.LENGTH_LONG).show();
            }
        });
        btn_02.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                progress_bar_02.setProgress(20);
                progress_bar_02.setSecondaryProgress(40);
                ratingBar.setProgress(60);
                seek_bar_01.setProgress(80);
            }
        });

        seek_bar_01.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_bar_02.setProgress(progress);
                ProgressSeekBarTest.this.setTitle("当前进度值：" + progress);
                ratingBar.setProgress(progress / 10);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//				System.out.println("onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//				System.out.println("onStopTrackingTouch");
            }
        });

        seek_bar_02.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                System.out.println(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                System.out.println("onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                System.out.println("onStopTrackingTouch");
            }
        });
    }

    //三个在xml中定义的点击事件

    /**
     * 开始
     */
    public void onStart(View view) {
        chronometer.start();
    }

    /**
     * 停止
     */
    public void onStop(View view) {
        chronometer.stop();
    }

    /**
     * 重置
     */
    public void onReset(View view) {
        //setBase 设置基准时间  
        //设置参数base为SystemClock.elapsedRealtime()即表示从当前时间开始重新计时）。  
        chronometer.setBase(SystemClock.elapsedRealtime());
    }
}

