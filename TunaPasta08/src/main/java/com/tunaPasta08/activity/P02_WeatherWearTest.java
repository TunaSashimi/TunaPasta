package com.tunaPasta08.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tunaPasta08.R;
import com.tunaPasta08.tools.WeatherCal;
import com.tunaPasta08.tools.XMLDecode;

public class P02_WeatherWearTest extends Activity {
    private LinearLayout lv;
    private EditText e;
    private Button b;
    private ProgressDialog pd;//这里的dialog为加载，进度条~
    private AlertDialog dialog;//这里的dialog为提示
    private TextView tv1, tv2;
    private ImageView iv1, iv2;
    private ArrayList<String> list;
    private MediaPlayer player;
    private Handler handler;
    private static boolean flag, sole;
    private static int record;
    XMLDecode decode;

    @Override        //旋转屏的问题
    public void onConfigurationChanged(android.content.res.Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p02_weatherweartest);
        lv = findViewById(R.id.weather_linear);
        iv1 = findViewById(R.id.weiv01);
        iv2 = findViewById(R.id.weiv02);
        tv1 = findViewById(R.id.wetv01);
        tv2 = findViewById(R.id.wetv02);
        e = findViewById(R.id.weet01);
        b = findViewById(R.id.webu01);
        //设定自定义对话框~考虑放出去
        View layout = getLayoutInflater().inflate(R.layout.p02_weatherweartestitem, null);
        dialog = new android.app.AlertDialog.Builder(this).create();
        dialog.setTitle(" 输入拼音查询 & 长按屏暂停播放");
        LinearLayout linear = layout.findViewById(R.id.myweatherlinear);
        linear.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                dialog.dismiss();
                if (!sole) {
                    Toast toast = Toast.makeText(getApplicationContext(), "点击Manu显示帮助选项", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    sole = true;
                }
            }
        });
        dialog.setView(layout);
        //以上为dialog的构造
        if (!flag) {
            dialog.show();
            flag = true;
        }
        //音频的构造
        player = MediaPlayer.create(this, R.raw.handinhand);
        player.setLooping(true);
        player.start();
        handler = new Handler() {
            @Override
            public void handleMessage(android.os.Message msg) {
                switch (msg.what) {
                    case 1:
                        pd.setProgress(20);
                        pd.setMessage("正在接受数据...");
                        receiveDate();
                        break;
                    case 2:
                        pd.setProgress(40);
                        pd.setMessage("正在加载组件...");
                        try {
                            loadingPicture();
                        } catch (Exception e) {
                            handler.sendEmptyMessage(6);
                        }
                        break;
                    case 3:
                        pd.setProgress(80);
                        pd.setMessage("初始化布局...");
                        loadComponents();
                        break;
                    case 4:
                        pd.setProgress(100);
                        pd.setMessage("加载完成");
                        distributionComplete();
                        break;
                    case 5:
                        Toast toast = Toast.makeText(getApplicationContext(), "网络站点错误,请重试~", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        ImageView add = new ImageView(getApplicationContext());
                        add.setImageResource(R.drawable.weathererr);
                        ((LinearLayout) toast.getView()).addView(add, 180, 240);
                        toast.show();
                        pd.dismiss();
                        break;
                    case 6:
                        Toast toastm = Toast.makeText(getApplicationContext(), " 城市名称输入错误,请重试~", Toast.LENGTH_LONG);
                        toastm.setGravity(Gravity.CENTER, 0, 0);
                        ImageView addm = new ImageView(getApplicationContext());
                        addm.setImageResource(R.drawable.weathererr);
                        ((LinearLayout) toastm.getView()).addView(addm, 180, 240);
                        toastm.show();
                        pd.dismiss();
                        break;
                }
            }
        };
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pd = new ProgressDialog(P02_WeatherWearTest.this);
                pd.setProgressStyle(android.app.ProgressDialog.STYLE_HORIZONTAL);
                pd.setTitle("程序运行,请稍后...");
                pd.setMessage("正在连接服务器...");
                pd.show();
                new Thread() {
                    public void run() {
                        try {
                            decode = new XMLDecode(e.getText().toString().trim());
                            decode.connectServer();
                            handler.sendEmptyMessage(1);
                        } catch (Exception e) {
                            handler.sendEmptyMessage(5);
                        }
                    }
                }.start();
            }
        });
        lv.setOnLongClickListener(new View.OnLongClickListener() {
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

    //		下载数据存入List~
    private void receiveDate() {
        list = (ArrayList<String>) decode.receiveDate();
        handler.sendEmptyMessage(2);
    }

    //		加载图片~
    private void loadingPicture() throws Exception {
        iv1.setImageBitmap(decode.loadingPicture());
        handler.sendEmptyMessage(3);
    }

    // 	加载当天天气~
    private void loadComponents() {
        tv1.setText(new WeatherCal(list).calToday());
        handler.sendEmptyMessage(4);
    }

    //		初始化布局
    private void distributionComplete() {
        tv2.setText(new WeatherCal(list).distributionComplete());
        iv2.setImageResource(new WeatherCal(list).getWeatherImage());//通过这个方法,返回合适的图片
        pd.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        menu.add(0, 0, 0, "显示帮助");
        menu.add(0, 1, 1, "始终显示");
        menu.add(0, 2, 2, "关闭显示");
        menu.add(0, 0, 3, "取消操作");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                dialog.show();
                break;
            case 1:
                record = 1;
                break;
            case 2:
                record = 0;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        switch (record) {
            case 0:
                flag = true;
                break;
            case 1:
                flag = false;
                break;
        }
        player.stop();
        super.finish();
    }
}