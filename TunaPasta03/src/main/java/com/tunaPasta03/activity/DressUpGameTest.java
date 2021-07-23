package com.tunaPasta03.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tunaPasta03.R;

public class DressUpGameTest extends Activity {
    private boolean flag;
    private LinearLayout lv;
    private TextView tv1, tv2, tv3;
    private MediaPlayer player;
    private int a = 6;
    private int[] mess = {R.drawable.mess01, R.drawable.mess02, R.drawable.mess03, R.drawable.mess04,
            R.drawable.mess05, R.drawable.mess06, R.drawable.mess07, R.drawable.mess08, R.drawable.mess09};
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv2.setText("" + msg.getData().getInt("value"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dressupgametest);

        tv1 = findViewById(R.id.messtv01);
        tv2 = findViewById(R.id.messtv02);
        tv3 = findViewById(R.id.messtv03);
        lv = findViewById(R.id.messll);
        player = MediaPlayer.create(this, R.raw.baby);
        player.setLooping(true);
        player.start();

        //设定自定义对话框
        View layout = getLayoutInflater().inflate(R.layout.mymessagehandler, null);
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle(" 点击设置数字 & 长按屏暂停播放");
        LinearLayout linear = (LinearLayout) layout.findViewById(R.id.mymessagelinear);
        linear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                change();
            }
        });
        dialog.setView(layout);
        dialog.show();

        lv.setOnClickListener(new OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      change();
                                  }
                              }
        );
        lv.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (player.isPlaying()) {
                    player.pause();
                } else {
                    player.start();
                }
                return false;
            }
        });
    }

    @Override
    public void finish() {
        player.stop();
        super.finish();
    }

    public void change() {
        if (flag == false) {
            a = (tv1.getText().toString().equals("目标" + tv2.getText().toString())) ? a + 1 : a - 1;
            if (a == 0 || a == 10) {
                Toast toast = Toast.makeText(getApplicationContext(), a == 0 ? "YOU FAIL" : "YOU WIN", android.widget.Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                ImageView add = new ImageView(getApplicationContext());
                add.setImageResource(a == 0 ? R.drawable.fail : R.drawable.win);
                ((LinearLayout) toast.getView()).addView(add, 180, 200);
                toast.show();
                if (a == 10)
                    startActivity(new Intent(DressUpGameTest.this, DressUpGameResult.class));
                DressUpGameTest.this.finish();
            } else {
                tv3.setText("Lv" + a);
                lv.setBackgroundResource(mess[a - 1]);
                tv1.setText("目标" + (int) (Math.random() * 10));
            }
        }
        flag = !flag;
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; flag; i = i == 9 ? 0 : ++i) {
                    Bundle bun = new Bundle();
                    bun.putInt("value", i);
                    Message mesg = new Message();
                    mesg.setData(bun);
                    handler.sendMessage(mesg);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }.start();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}