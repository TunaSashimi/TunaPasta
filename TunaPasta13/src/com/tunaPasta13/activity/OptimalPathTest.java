package com.tunaPasta13.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.tunaPasta13.R;

public class OptimalPathTest extends Activity implements OnTouchListener {

    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();
    DisplayMetrics dm;
    ImageView imgView;
    Bitmap bitmap;
    float minScaleR;// 最小缩放比例
    static final float MAX_SCALE = 4f;// 最大缩放比例
    static final int NONE = 0;// 初始状态
    static final int DRAG = 1;// 拖动
    static final int ZOOM = 2;// 缩放
    int mode = NONE;
    PointF prev = new PointF();
    PointF mid = new PointF();
    float dist = 1f;
    RectF rect;
    int index;

    ImageButton search;
    AutoCompleteTextView starter;
    AutoCompleteTextView ender;
    Spinner choiceTran;
    public String[] nodeName = {"AB宿舍", "CD宿舍", "菜市场/网络中心", "第二三食堂", " DG座教室", "大礼堂", "东门", "第四食堂", "EF宿舍", "E座教室",
            "附中附小", "G座宿舍", "荷花池", "互助学习中心", "教师公寓", "旧行政楼", "科学楼", "路口1", "路口2", "路口3", "路口4", "路口5", "路口6", "路口7",
            "路口8", "路口9", "路口10", "路口11", "路口12", "路口13", "路口14", "理科楼", "留学生宿舍", "溜冰场", "篮球场", "789号楼", "水库",
            "图书馆", "体育馆", "田径场", "网球场", "校门", "学术交流中心", "校训碑", "新行政中心", "校医院", "邮局", "研究生宿舍", "艺术教育中心", "艺术学院", "游泳池",
            "至诚书院"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.optimalpathtest);

        imgView = findViewById(R.id.imageview);// 获取控件
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.optimalpathtest_imageview_roadmap);// 获取图片资源  
        imgView.setImageBitmap(bitmap);// 填充控件  

        //监听搜索键
        search = findViewById(R.id.search);
        search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                View searchPathView = LayoutInflater.from(OptimalPathTest.this).inflate(R.layout.inputdialogitem, null);

                starter = searchPathView.findViewById(R.id.startplace);
                ender = searchPathView.findViewById(R.id.endplace);

                starter.setAdapter(new ArrayAdapter(OptimalPathTest.this, android.R.layout.simple_dropdown_item_1line, nodeName));
                ender.setAdapter(new ArrayAdapter(OptimalPathTest.this, android.R.layout.simple_dropdown_item_1line, nodeName));

                choiceTran = searchPathView.findViewById(R.id.choice);
                choiceTran.setAdapter(new ArrayAdapter(OptimalPathTest.this,
                        android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.choicetran)));


                new AlertDialog.Builder(OptimalPathTest.this).setTitle("请输入：").setView(searchPathView)
                        .setPositiveButton("查找", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (!"".equals(starter.getText().toString()) &&
                                        !"".equals(ender.getText().toString())) {
                                    Intent intent = new Intent(OptimalPathTest.this, OptimalPathResult.class);
                                    intent.putExtra("starter", starter.getText().toString());
                                    intent.putExtra("ender", ender.getText().toString());
                                    intent.putExtra("choice", choiceTran.getSelectedItem().toString());
                                    startActivity(intent);
                                }
                            }
                        }).setNegativeButton("退出", null).show();
            }
        });

        imgView.setOnTouchListener(this);// 设置触屏监听  
        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);// 获取分辨率  

        minZoom();
        center();
        imgView.setImageMatrix(matrix);
    }

    public static Bitmap convertViewToBitmap(View view) {
        view.setDrawingCacheEnabled(true);
        view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();

        return bitmap;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) { // 主点按下
            case MotionEvent.ACTION_DOWN:
                savedMatrix.set(matrix);
                prev.set(event.getX(), event.getY());
                mode = DRAG;
                break; // 副点按下
            case MotionEvent.ACTION_POINTER_DOWN:
                dist = spacing(event); // 如果连续两点距离大于10，则判定为多点模式
                if (spacing(event) > 10f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {
                    matrix.set(savedMatrix);
                    matrix.postTranslate(event.getX() - prev.x, event.getY()
                            - prev.y);
                } else if (mode == ZOOM) {
                    float newDist = spacing(event);
                    if (newDist > 10f) {
                        matrix.set(savedMatrix);
                        float tScale = newDist / dist;
                        matrix.postScale(tScale, tScale, mid.x, mid.y);
                    }
                }
                break;
        }
        imgView.setImageMatrix(matrix);
        CheckView();
        return true;
    }

    /**
     * 限制最大最小缩放比例，自动居中
     */
    private void CheckView() {
        float p[] = new float[9];
        matrix.getValues(p);
        if (mode == ZOOM) {
            if (p[0] < minScaleR) {
                matrix.setScale(minScaleR, minScaleR);
            }
            if (p[0] > MAX_SCALE) {
                matrix.set(savedMatrix);
            }
        }
        center();
    }

    /**
     * 最小缩放比例，最大为100%
     */
    private void minZoom() {
        minScaleR = (float) Math.min(
                (float) dm.widthPixels / (float) bitmap.getWidth() - 0.2,
                (float) dm.heightPixels / (float) bitmap.getHeight() - 0.2);
        if (minScaleR < 1.0) {
            matrix.postScale(minScaleR, minScaleR);
        }
    }

    private void center() {
        center(true, true);
    }

    /**
     * 横向、纵向居中
     */
    protected void center(boolean horizontal, boolean vertical) {
        Matrix m = new Matrix();
        m.set(matrix);
        rect = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        m.mapRect(rect);
        float height = rect.height();
        float width = rect.width();
        float deltaX = 0, deltaY = 0;
        if (vertical) {
            // 图片小于屏幕大小，则居中显示。大于屏幕，上方留空则往上移，下放留空则往下移  
            int screenHeight = dm.heightPixels;
            if (height < screenHeight) {
                deltaY = (screenHeight - height) / 2 - rect.top;
            } else if (rect.top > 0) {
                deltaY = -rect.top;
            } else if (rect.bottom < screenHeight) {
                deltaY = imgView.getHeight() - rect.bottom;
            }
        }
        if (horizontal) {
            int screenWidth = dm.widthPixels;
            if (width < screenWidth) {
                deltaX = (screenWidth - width) / 2 - rect.left;
            } else if (rect.left > 0) {
                deltaX = -rect.left;
            } else if (rect.right < screenWidth) {
                deltaX = screenWidth - rect.right;
            }
        }
        matrix.postTranslate(deltaX, deltaY);
    }

    /**
     * 两点的距离
     */
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * 两点的中点
     */
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}