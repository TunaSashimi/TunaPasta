package com.tunaPasta05.activity;

import java.io.IOException;

import android.app.Activity;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.tunaPasta05.R;

public class CustomPlayerTest extends Activity implements OnGestureListener, OnTouchListener, OnItemClickListener {
    ViewFlipper myFlipper;
    GestureDetector myGesture;
    int fling_distance = 100;
    int fling_velocity = 200;
    ListView myList = null;

    String[] mCursorCols = new String[]{"audio._id AS _id",
            MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.MIME_TYPE, MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ARTIST_ID, MediaStore.Audio.Media.DURATION};

    Cursor mCursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.customplayertest);
        findView();
        setListener();
    }

    public void findView() {
        myFlipper = findViewById(R.id.myFlipper);
        myFlipper.setClickable(true);//允许长按事件，这样才能识别拖动等手势
        myGesture = new GestureDetector(this);//注册一个手势识别的类
        myList = findViewById(R.id.mylist);
        //以下实例一个ListView，定义一个SimpleCursorAdapter并且将其与ListView绑定
        Uri MUSIC_URL = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;//定义取得音乐信息的URI
        mCursor = getContentResolver().query(MUSIC_URL, mCursorCols, null, null, null);//将游标指向数据
        startManagingCursor(mCursor);//托管给系统，用完让系统释放
        ListAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, mCursor, new String[]{
                MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST},
                new int[]{android.R.id.text1, android.R.id.text2});
        myList.setAdapter(adapter);
    }

    public void setListener() {
        myFlipper.setOnTouchListener(this);//向Flipper注册监听事件
        myList.setOnTouchListener(this);
        myList.setOnItemClickListener(this);
    }

    /* 定义从右侧进入的动画效果 */
    public Animation inFromRight() {
        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromRight.setDuration(250);
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }

    /* 定义从右侧退出的动画效果 */
    public Animation outToRight() {
        Animation outToRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outToRight.setDuration(250);
        outToRight.setInterpolator(new AccelerateInterpolator());
        return outToRight;
    }

    /*定义从左侧进入的动画*/
    public Animation inFromLeft() {
        Animation inFromLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromLeft.setDuration(250);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        return inFromLeft;
    }

    /*定义从左侧退出的动画*/
    public Animation outToLeft() {
        Animation outtoLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoLeft.setDuration(250);
        outtoLeft.setInterpolator(new AccelerateInterpolator());
        return outtoLeft;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // 一定要将触屏事件交给手势识别类去处理（自己处理会很麻烦的）
        return myGesture.onTouchEvent(event);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        /*
         * * 用户按下触摸屏、快速移动后松开即触发这个事件 *
         * e1：第1个ACTION_DOWN MotionEvent *
         * e2：最后一个ACTION_MOVE MotionEvent *
         * velocityX：X轴上的移动速度，像素/秒 *
         * velocityY：Y轴上的移动速度，像素/秒 * 触发条件 ： *
         * X轴的坐标位移大于FLING_MIN_DISTANCE，且移动速度大于FLING_MIN_VELOCITY个像素/秒
         */
        if (e1.getX() - e2.getX() > fling_distance &&
                Math.abs(velocityX) > fling_velocity) {//当向左侧滑动时
            //设置进入View时的动画
            myFlipper.setInAnimation(inFromRight());
            //设置退出View的动画
            myFlipper.setOutAnimation(outToLeft());
            //显示要显示的View
            myFlipper.showNext();
        } else if (e2.getX() - e1.getX() > fling_distance &&
                Math.abs(velocityX) > fling_velocity) {//当向右滑动时
            myFlipper.setInAnimation(inFromLeft());
            myFlipper.setOutAnimation(outToRight());
            myFlipper.showNext();
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
        prepareData(position);
    }

    MediaPlayer myPlayer = new MediaPlayer();

    public void prepareData(int position) {
        mCursor.moveToPosition(position);
        int columnIndex = mCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
        String dataSource = mCursor.getString(columnIndex);
        try {
            myPlayer.reset();
            myPlayer.setDataSource(dataSource);
            myPlayer.prepare();
            myPlayer.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy方法中的System.exit(0)会直接退出程序", Toast.LENGTH_LONG).show();
        System.exit(0);//保证完全退出程序
    }
}