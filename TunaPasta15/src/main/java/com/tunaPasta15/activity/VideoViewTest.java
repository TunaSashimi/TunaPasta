package com.tunaPasta15.activity;

import java.io.File;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;
import android.widget.VideoView;

import com.tunaPasta15.R;

public class VideoViewTest extends Activity {
    private Button btn_play, btn_pause, btn_replay, btn_stop;
    private SeekBar seekBar;
    private VideoView videoView;
    private boolean isPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoviewtest);

        seekBar = findViewById(R.id.seekBar);
        videoView = findViewById(R.id.videoView);

        btn_play = findViewById(R.id.btn_play);
        btn_pause = findViewById(R.id.btn_pause);
        btn_replay = findViewById(R.id.btn_replay);
        btn_stop = findViewById(R.id.btn_stop);

        btn_play.setOnClickListener(onClickListener);
        btn_pause.setOnClickListener(onClickListener);
        btn_replay.setOnClickListener(onClickListener);
        btn_stop.setOnClickListener(onClickListener);

        // 为进度条添加进度更改事件
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // 当进度条停止修改的时候触发
                // 取得当前进度条的刻度
                int progress = seekBar.getProgress();
                if (videoView != null && videoView.isPlaying()) {
                    // 设置当前播放的位置
                    videoView.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }
        });
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_play:
                    play(0);
                    break;
                case R.id.btn_pause:
                    pause();
                    break;
                case R.id.btn_replay:
                    replay();
                    break;
                case R.id.btn_stop:
                    stop();
                    break;
                default:
                    break;
            }
        }
    };

    protected void play(int msec) {
        File file = new File(Environment.getExternalStorageDirectory() + "/DHF/demo1118.mp4");
        if (!file.exists()) {
            Toast.makeText(this, "视频文件路径错误", Toast.LENGTH_SHORT).show();
            return;
        }

        videoView.setVideoPath(file.getAbsolutePath());
        videoView.start();

        // 按照初始位置播放
        videoView.seekTo(msec);
        // 设置进度条的最大进度为视频流的最大播放时长
        seekBar.setMax(videoView.getDuration());

        // 开始线程，更新进度条的刻度
        new Thread() {
            @Override
            public void run() {
                try {
                    isPlaying = true;
                    while (isPlaying) {
                        // 如果正在播放，每0.5毫秒更新一次进度条
                        int current = videoView.getCurrentPosition();
                        seekBar.setProgress(current);
                        sleep(500);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        // 播放之后设置播放按钮不可用
        btn_play.setEnabled(false);
        videoView.setOnCompletionListener(new OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // 在播放完毕被回调
                btn_play.setEnabled(true);
            }
        });

        videoView.setOnErrorListener(new OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                // 发生错误重新播放
                play(0);
                isPlaying = false;
                return false;
            }
        });
    }

    /**
     * 重新开始播放
     */
    protected void replay() {
        if (videoView != null && videoView.isPlaying()) {
            videoView.seekTo(0);
            Toast.makeText(this, "重新播放", Toast.LENGTH_SHORT).show();
            btn_pause.setText("暂停");
            return;
        }
        isPlaying = false;
        play(0);

    }

    /**
     * 暂停或继续
     */
    protected void pause() {
        if (btn_pause.getText().toString().trim().equals("继续")) {
            btn_pause.setText("暂停");
            videoView.start();
            Toast.makeText(this, "继续播放", Toast.LENGTH_SHORT).show();
            return;
        }
        if (videoView != null && videoView.isPlaying()) {
            videoView.pause();
            btn_pause.setText("继续");
            Toast.makeText(this, "暂停播放", Toast.LENGTH_SHORT).show();
        }
    }

    /*
     * 停止播放
     */
    protected void stop() {
        if (videoView != null && videoView.isPlaying()) {
            videoView.stopPlayback();
            btn_play.setEnabled(true);
            isPlaying = false;
        }
    }
}
