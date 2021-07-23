package com.tunaPasta13.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.tunaPasta13.R;

public class OptimalPathResult extends Activity {

    private ImageButton lookfor, clear, showPic1, showPic2, showPic3;
    private LinearLayout llMapContent;
    private RoadMapView stuMap;
    private String startName, endName, choice;
    AutoCompleteTextView starter;
    AutoCompleteTextView ender;
    Spinner choiceTran;
    TextView showPath;
    public String[] nodeName = {"AB宿舍", "CD宿舍", "菜市场/网络中心", "第二三食堂", " DG座教室", "大礼堂", "东门", "第四食堂", "EF宿舍", "E座教室",
            "附中附小", "G座宿舍", "荷花池", "互助学习中心", "教师公寓", "旧行政楼", "科学楼", "路口1", "路口2", "路口3", "路口4", "路口5", "路口6", "路口7",
            "路口8", "路口9", "路口10", "路口11", "路口12", "路口13", "路口14", "理科楼", "留学生宿舍", "溜冰场", "篮球场", "789号楼", "水库",
            "图书馆", "体育馆", "田径场", "网球场", "校门", "学术交流中心", "校训碑", "新行政中心", "校医院", "邮局", "研究生宿舍", "艺术教育中心", "艺术学院", "游泳池",
            "至诚书院"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.optimalpathresult);

        Intent intent = getIntent();
        startName = intent.getStringExtra("starter");
        endName = intent.getStringExtra("ender");
        choice = intent.getStringExtra("choice");

        init();

        DisplayMetrics dm = new DisplayMetrics();
        //获取窗口属性
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        operate();
    }

    private void init() {
        lookfor = findViewById(R.id.lookfor);
        showPic1 = findViewById(R.id.showpic1);
        showPic2 = findViewById(R.id.showpic2);
        showPic3 = findViewById(R.id.showpic3);
        showPath = findViewById(R.id.showpath);
        llMapContent = findViewById(R.id.ll_map_content);
        clear = findViewById(R.id.clear);
        clear.setOnClickListener(new ClearOnClickListener());
        lookfor.setOnClickListener(new LookForOnClickListener());
        showPic1.setOnClickListener(new ShowPicOnClickListener());
        showPic2.setOnClickListener(new ShowPicOnClickListener());
        showPic3.setOnClickListener(new ShowPicOnClickListener());
    }

    // 查询的主要方法
    private void operate() {
        OptimalPathData dijkstra = new OptimalPathData(choice);
        Node start = dijkstra.init(startName);
        dijkstra.computePath(start);
        String strPath = dijkstra.printPathInfo(endName);
        showPath.setText("路径：\n" + strPath);
        stuMap = new RoadMapView(this, choice);
        stuMap.setPath(strPath);
        stuMap.setBackgroundResource(R.drawable.optimalpathtest_imageview_roadmap);
        // 将原先的图片remove掉，添加已画路线的图片
        llMapContent.removeAllViews();
        llMapContent.addView(stuMap);
    }

    class ShowPicOnClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            LayoutInflater layout = LayoutInflater.from(OptimalPathResult.this);
            View myView = layout.inflate(R.layout.showpicture, null);
            ImageView iv = (ImageView) myView.findViewById(R.id.picture);
            switch (v.getId()) {
                case R.id.showpic1:
                    iv.setBackgroundResource(R.drawable.optimalpathresult_imageview_stadium);
                    break;
                case R.id.showpic2:
                    iv.setBackgroundResource(R.drawable.optimalpathresult_imageview_library);
                    break;
                case R.id.showpic3:
                    iv.setBackgroundResource(R.drawable.optimalpathresult_imageview_auditorium);
                    break;
                default:
                    break;
            }
            new AlertDialog.Builder(OptimalPathResult.this).setView(myView).show();
        }

    }

    class ClearOnClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(OptimalPathResult.this, OptimalPathTest.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }

    }

    class LookForOnClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            LayoutInflater layout = LayoutInflater.from(OptimalPathResult.this);

            View myView = layout.inflate(R.layout.inputdialogitem, null);

            starter = myView.findViewById(R.id.startplace);
            ender = myView.findViewById(R.id.endplace);
            choiceTran = myView.findViewById(R.id.choice);
            starter.setAdapter(new ArrayAdapter(OptimalPathResult.this, android.R.layout.simple_dropdown_item_1line, nodeName));
            ender.setAdapter(new ArrayAdapter(OptimalPathResult.this, android.R.layout.simple_dropdown_item_1line, nodeName));
            choiceTran.setAdapter(new ArrayAdapter(OptimalPathResult.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.choicetran)));

            new AlertDialog.Builder(OptimalPathResult.this)
                    .setTitle("请输入：")
                    .setView(myView)
                    .setPositiveButton("查找", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startName = starter.getText().toString();
                            endName = ender.getText().toString();
                            choice = choiceTran.getSelectedItem().toString();
                            operate();
                        }
                    }).setNegativeButton("退出", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).create().show();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
