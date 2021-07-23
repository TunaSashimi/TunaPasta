package com.tunaPasta06.activity;
import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ViewFlipper;

import com.tunaPasta06.R;
import com.tunaPasta06.widget.ImageTextButton;
public class ActivityGroup03 extends ActivityGroup implements OnGestureListener,OnTouchListener {
	// 声明ViewFlipper对象
	private ViewFlipper m_ViewFlipper;
	// 声明GestureDetector对象
	private GestureDetector m_GestureDetector;
	// 声明LocalActivityManager对象
	private LocalActivityManager m_ActivityManager;
	private static int FLING_MIN_DISTANCE = 100;
	private static int FLING_MIN_VELOCITY = 200;
	// 定义自定义图片加文字按钮ImageTextButton对象
	private ImageTextButton now_playing;
	private ImageTextButton native_music;
	private ImageTextButton network_music;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置内容试图
		setContentView(R.layout.activitygroup03);
		// 构建ImageTextButton对象
		now_playing =  findViewById(R.id.now_playing);
		native_music =  findViewById(R.id.native_music);
		network_music =  findViewById(R.id.network_music);
		// 构建ViewFlipper对象
		m_ViewFlipper =  findViewById(R.id.fliper);
		// 获取Activity消息
		m_ActivityManager = getLocalActivityManager();
		// 注册一个用于手势识别的类
		m_GestureDetector = new GestureDetector(this);
		// 添加视图，指定每个视图对应的Activity
		m_ViewFlipper.addView((m_ActivityManager.startActivity("", new Intent(this, SlidingDrawerTest.class)).getDecorView()), 0);
		m_ViewFlipper.addView((m_ActivityManager.startActivity("", new Intent(this, Activity07.class)).getDecorView()), 1);
		m_ViewFlipper.addView((m_ActivityManager.startActivity("", new Intent(this, Activity09.class)).getDecorView()), 2);
		// 给ViewFlipper设置一个listener
		m_ViewFlipper.setOnTouchListener(this);
		// 默认为正在播放页面并设置图标
		now_playing.setIcon(R.drawable.frame_player_press);
		// 设置相应元素索引显示的子视图
		m_ViewFlipper.setDisplayedChild(0);
		// 允许长按住ViewFlipper,这样才能识别拖动等手势
		m_ViewFlipper.setLongClickable(true);
		// 监听
		BindListener();
	}

	/**
	 * 监听本Activity中所要监听的对象
	 */
	public void BindListener() {
		// 监听正在播放按钮事件
		now_playing.setOnClickListener(new ImageTextButton.OnClickListener() {
			public void onClick(View v) {
				// 点击之后改变图片
				now_playing.setIcon(R.drawable.frame_player_press);
				native_music.setIcon(R.drawable.frame_local_normal);
				network_music.setIcon(R.drawable.frame_internet_normal);
				// //设置View进入屏幕时候使用的动画
				// m_ViewFlipper.setInAnimation(inFromRightAnimation());
				// //设置View退出屏幕时候使用的动画
				// m_ViewFlipper.setOutAnimation(outToLeftAnimation());
				// 设置相应元素索引显示的子视图
				m_ViewFlipper.setDisplayedChild(0);
			}
		});
		// 监听本地歌曲按钮事件
		native_music.setOnClickListener(new ImageTextButton.OnClickListener() {
			public void onClick(View v) {
				// 点击之后改变图片
				now_playing.setIcon(R.drawable.frame_player_normal);
				native_music.setIcon(R.drawable.frame_local_press);
				network_music.setIcon(R.drawable.frame_internet_normal);
				// 设置View进入屏幕时候使用的动画
				// m_ViewFlipper.setInAnimation(inFromRightAnimation());
				// //设置View退出屏幕时候使用的动画
				// m_ViewFlipper.setOutAnimation(outToLeftAnimation());
				// 设置相应元素索引显示的子视图
				m_ViewFlipper.setDisplayedChild(1);
			}
		});
		// 监听网络歌曲按钮事件
		network_music.setOnClickListener(new ImageTextButton.OnClickListener() {
			public void onClick(View v) {
				// 点击之后改变图片
				now_playing.setIcon(R.drawable.frame_player_normal);
				native_music.setIcon(R.drawable.frame_local_normal);
				network_music.setIcon(R.drawable.frame_internet_press);
				// //设置View进入屏幕时候使用的动画
				// m_ViewFlipper.setInAnimation(inFromRightAnimation());
				// //设置View退出屏幕时候使用的动画
				// m_ViewFlipper.setOutAnimation(outToLeftAnimation());
				// 设置相应元素索引显示的子视图
				m_ViewFlipper.setDisplayedChild(2);
			}
		});
	}

	/**
	 * 定义从右侧进入的动画效果
	 * 
	 * @return
	 */
	public Animation inFromRightAnimation() {
		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromRight.setDuration(500);
		inFromRight.setInterpolator(new AccelerateInterpolator());
		return inFromRight;
	}

	/**
	 * 定义从左侧退出的动画效果
	 * @return
	 */
	public Animation outToLeftAnimation() {
		Animation outtoLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoLeft.setDuration(500);
		outtoLeft.setInterpolator(new AccelerateInterpolator());
		return outtoLeft;
	}

	/**
	 * 定义从左侧进入的动画效果
	 * @return
	 */
	public Animation inFromLeftAnimation() {
		Animation inFromLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromLeft.setDuration(500);
		inFromLeft.setInterpolator(new AccelerateInterpolator());
		return inFromLeft;
	}

	/**
	 * 定义从右侧退出时的动画效果
	 * @return
	 */
	public Animation outToRightAnimation() {
		Animation outtoRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoRight.setDuration(500);
		outtoRight.setInterpolator(new AccelerateInterpolator());
		return outtoRight;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// 当像左侧滑动的时候
		if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
				&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
			// 设置View进入屏幕时候使用的动画
			m_ViewFlipper.setInAnimation(inFromRightAnimation());
			// 设置View退出屏幕时候使用的动画
			m_ViewFlipper.setOutAnimation(outToLeftAnimation());
			// 下一个页面
			m_ViewFlipper.showNext();
			// 获取相应元素索引显示的子视图

			int child = m_ViewFlipper.getDisplayedChild();
			switch (child) {
			case 0:
				now_playing.setIcon(R.drawable.frame_player_press);
				native_music.setIcon(R.drawable.frame_local_normal);
				network_music.setIcon(R.drawable.frame_internet_normal);
				break;
			case 1:
				now_playing.setIcon(R.drawable.frame_player_normal);
				native_music.setIcon(R.drawable.frame_local_press);
				network_music.setIcon(R.drawable.frame_internet_normal);
				break;
			case 2:
				now_playing.setIcon(R.drawable.frame_player_normal);
				native_music.setIcon(R.drawable.frame_local_normal);
				network_music.setIcon(R.drawable.frame_internet_press);
				break;
			case 3:
				now_playing.setIcon(R.drawable.frame_player_normal);
				native_music.setIcon(R.drawable.frame_local_press);
				network_music.setIcon(R.drawable.frame_internet_normal);
				break;
			}
		}
		// 当像右侧滑动的时候
		else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
				&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
			// 设置View进入屏幕时候使用的动画
			m_ViewFlipper.setInAnimation(inFromLeftAnimation());
			// 设置View退出屏幕时候使用的动画
			m_ViewFlipper.setOutAnimation(outToRightAnimation());
			// 上一个页面
			m_ViewFlipper.showPrevious();
			// 获取相应元素索引显示的子视图
			int child = m_ViewFlipper.getDisplayedChild();
			switch (child) {
			case 0:
				now_playing.setIcon(R.drawable.frame_player_press);
				native_music.setIcon(R.drawable.frame_local_normal);
				network_music.setIcon(R.drawable.frame_internet_normal);
				break;
			case 1:
				now_playing.setIcon(R.drawable.frame_player_normal);
				native_music.setIcon(R.drawable.frame_local_press);
				network_music.setIcon(R.drawable.frame_internet_normal);
				break;
			case 2:
				now_playing.setIcon(R.drawable.frame_player_normal);
				native_music.setIcon(R.drawable.frame_local_normal);
				network_music.setIcon(R.drawable.frame_internet_press);
				break;
			case 3:
				now_playing.setIcon(R.drawable.frame_player_normal);
				native_music.setIcon(R.drawable.frame_local_press);
				network_music.setIcon(R.drawable.frame_internet_normal);
				break;
			}
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,float distanceY) {
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
	public boolean onTouch(View v, MotionEvent event) {
		// 一定要将触屏事件交给手势识别类去处理(自己处理会很麻烦的)
		return m_GestureDetector.onTouchEvent(event);
	}}
