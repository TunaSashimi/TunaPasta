package com.tunaPasta02.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.tunaPasta02.R;
import com.tunaPasta02.widget.CircleProgressView;

public class CircleProgressViewTest extends Activity implements OnClickListener {

	private Button mBtnAddMain; // 增加进度值
	private Button mBtnAddSub; // 减少进度值
	private ImageButton mImageBtn; // 清除进度值

	private CircleProgressView mCircleProgressBar1;
	private CircleProgressView mCircleProgressBar2;
	private CircleProgressView mCircleProgressBar3;

	private int progress = 0;
	private int subProgress = 0;

	private Button mBtnStartButton; // 开启动画
	private Button mBtnSTopButton; // 结束动画
	private CircleProgressView mCircleProgressBar4;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.circleprogressviewtest);

		initView();
	}

	public void initView() {
		mBtnAddMain =  findViewById(R.id.buttonAddMainPro);
		mBtnAddSub =  findViewById(R.id.buttonAddSubPro);
		mImageBtn =  findViewById(R.id.buttonImage);

		mBtnStartButton =  findViewById(R.id.buttonStart);
		mBtnSTopButton =  findViewById(R.id.buttonStop);

		mBtnAddMain.setOnClickListener(this);
		mBtnAddSub.setOnClickListener(this);
		mImageBtn.setOnClickListener(this);

		mBtnStartButton.setOnClickListener(this);
		mBtnSTopButton.setOnClickListener(this);

		mCircleProgressBar1 =  findViewById(R.id.roundBar1);
		mCircleProgressBar2 =  findViewById(R.id.roundBar2);
		mCircleProgressBar3 =  findViewById(R.id.roundBar3);
		mCircleProgressBar4 =  findViewById(R.id.roundBar4);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.buttonAddMainPro:
			add();
			break;
		case R.id.buttonAddSubPro:
			Sub();
			break;
		case R.id.buttonImage:
			clear();
			break;
		case R.id.buttonStart:
			start();
			break;
		case R.id.buttonStop:
			stop();
			break;
		}
	}

	public void add() {
		progress += 5;
		if (progress > 100) {
			progress = 0;
		}

		mCircleProgressBar1.setMainProgress(progress);
		mCircleProgressBar2.setMainProgress(progress);
		mCircleProgressBar3.setMainProgress(progress);

	}

	public void Sub() {
		subProgress += 5;
		if (subProgress > 100) {
			subProgress = 0;
		}

		mCircleProgressBar1.setSubProgress(subProgress);
		mCircleProgressBar2.setSubProgress(subProgress);
		mCircleProgressBar3.setSubProgress(subProgress);

	}

	public void clear() {
		progress = 0;
		subProgress = 0;

		mCircleProgressBar1.setMainProgress(0);
		mCircleProgressBar2.setMainProgress(0);
		mCircleProgressBar3.setMainProgress(0);

		mCircleProgressBar1.setSubProgress(0);
		mCircleProgressBar2.setSubProgress(0);
		mCircleProgressBar3.setSubProgress(0);

	}

	public void start() {
		mCircleProgressBar4.startCartoom(10);
	}

	public void stop() {
		mCircleProgressBar4.stopCartoom();
	}
}