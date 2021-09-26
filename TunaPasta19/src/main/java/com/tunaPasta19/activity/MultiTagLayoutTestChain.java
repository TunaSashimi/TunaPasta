package com.tunaPasta19.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.tunaPasta19.R;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MultiTagLayoutTestChain extends Activity {

	private RadioGroup mRadioGroup;
	private RadioButton mRadioButton1;
	private RadioButton mRadioButton2;
	private RadioButton mRadioButton3;
	private RadioButton mRadioButton4;
	private RadioButton mRadioButton5;
	private ImageView mImageView;

	private HorizontalScrollView mHorizontalScrollView;// 上面的水平滚动控件
	private ViewPager viewPager; // 下方的可横向拖动的控件
	private ArrayList<View> viewList;// 用来存放下方滚动的layout(layout_1,layout_2,layout_3)

	private float mCurrentCheckedRadioLeft;// 当前被选中的RadioButton距离左侧的距离

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.multitaglayouttestchain);

		mHorizontalScrollView =  findViewById(R.id.horizontalScrollView);
		mImageView =  findViewById(R.id.img1);

		viewList = new ArrayList<>();
		viewList.add(getLayoutInflater().inflate(
				R.layout.multitaglayouttestchainitem01, null));
		viewList.add(getLayoutInflater().inflate(
				R.layout.multitaglayouttestchainitem02, null));
		viewList.add(getLayoutInflater().inflate(
				R.layout.multitaglayouttestchainitem03, null));
		viewList.add(getLayoutInflater().inflate(
				R.layout.multitaglayouttestchainitem04, null));
		viewList.add(getLayoutInflater().inflate(
				R.layout.multitaglayouttestchainitem05, null));
		viewList.add(getLayoutInflater().inflate(
				R.layout.multitaglayouttestchainitem06, null));

		mRadioGroup =  findViewById(R.id.radioGroup);
		mRadioButton1 =  findViewById(R.id.btn1);
		mRadioButton2 =  findViewById(R.id.btn2);
		mRadioButton3 =  findViewById(R.id.btn3);
		mRadioButton4 =  findViewById(R.id.btn4);
		mRadioButton5 =  findViewById(R.id.btn5);

		mRadioButton1.setChecked(true);
		mCurrentCheckedRadioLeft = getCurrentCheckedRadioLeft();
		// RadioGroup点击CheckedChanged监听
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				AnimationSet _AnimationSet = new AnimationSet(true);
				TranslateAnimation _TranslateAnimation;
				if (checkedId == R.id.btn1) {
					_TranslateAnimation = new TranslateAnimation(
							mCurrentCheckedRadioLeft,
							getResources()
									.getDimension(
											R.dimen.multitaglayouttestchain_radiobutton01),
							0f, 0f);
					_AnimationSet.addAnimation(_TranslateAnimation);
					_AnimationSet.setFillBefore(false);
					_AnimationSet.setFillAfter(true);
					_AnimationSet.setDuration(100);
					mImageView.startAnimation(_AnimationSet);// 开始上面蓝色横条图片的动画切换
					viewPager.setCurrentItem(1);// 让下方ViewPager跟随上面的HorizontalScrollView切换
				} else if (checkedId == R.id.btn2) {
					_TranslateAnimation = new TranslateAnimation(
							mCurrentCheckedRadioLeft,
							getResources()
									.getDimension(
											R.dimen.multitaglayouttestchain_radiobutton02),
							0f, 0f);
					_AnimationSet.addAnimation(_TranslateAnimation);
					_AnimationSet.setFillBefore(false);
					_AnimationSet.setFillAfter(true);
					_AnimationSet.setDuration(100);
					mImageView.startAnimation(_AnimationSet);
					viewPager.setCurrentItem(2);
				} else if (checkedId == R.id.btn3) {
					_TranslateAnimation = new TranslateAnimation(
							mCurrentCheckedRadioLeft,
							getResources()
									.getDimension(
											R.dimen.multitaglayouttestchain_radiobutton03),
							0f, 0f);
					_AnimationSet.addAnimation(_TranslateAnimation);
					_AnimationSet.setFillBefore(false);
					_AnimationSet.setFillAfter(true);
					_AnimationSet.setDuration(100);
					mImageView.startAnimation(_AnimationSet);
					viewPager.setCurrentItem(3);
				} else if (checkedId == R.id.btn4) {
					_TranslateAnimation = new TranslateAnimation(
							mCurrentCheckedRadioLeft,
							getResources()
									.getDimension(
											R.dimen.multitaglayouttestchain_radiobutton04),
							0f, 0f);
					_AnimationSet.addAnimation(_TranslateAnimation);
					_AnimationSet.setFillBefore(false);
					_AnimationSet.setFillAfter(true);
					_AnimationSet.setDuration(100);
					mImageView.startAnimation(_AnimationSet);
					viewPager.setCurrentItem(4);
				} else if (checkedId == R.id.btn5) {
					_TranslateAnimation = new TranslateAnimation(
							mCurrentCheckedRadioLeft,
							getResources()
									.getDimension(
											R.dimen.multitaglayouttestchain_radiobutton05),
							0f, 0f);
					_AnimationSet.addAnimation(_TranslateAnimation);
					_AnimationSet.setFillBefore(false);
					_AnimationSet.setFillAfter(true);
					_AnimationSet.setDuration(100);
					mImageView.startAnimation(_AnimationSet);
					viewPager.setCurrentItem(5);
				}
				mCurrentCheckedRadioLeft = getCurrentCheckedRadioLeft();
				mHorizontalScrollView
						.smoothScrollTo(
								(int) mCurrentCheckedRadioLeft
										- (int) getResources()
												.getDimension(
														R.dimen.multitaglayouttestchain_radiobutton02),
								0);
			}
		});

		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(new PagerAdapter() { // /设置ViewPager的适配器
					public void destroyItem(View v, int position, Object obj) {
						((ViewPager) v).removeView(viewList.get(position));
					}

					public void finishUpdate(View arg0) {
					}

					public int getCount() {
						return viewList.size();
					}

					public Object instantiateItem(View v, int position) {
						((ViewPager) v).addView(viewList.get(position));
						return viewList.get(position);
					}

					public boolean isViewFromObject(View arg0, Object arg1) {
						return arg0 == arg1;
					}

					public void restoreState(Parcelable arg0, ClassLoader arg1) {
					}

					public Parcelable saveState() {
						return null;
					}

					public void startUpdate(View arg0) {
					}
				});
		// ViewPager的PageChangeListener(页面改变的监听器)
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			// 滑动ViewPager的时候,让上方的HorizontalScrollView自动切换
			public void onPageSelected(int position) {
				if (position == 0) {
					viewPager.setCurrentItem(1);
				} else if (position == 1) {
					mRadioButton1.performClick();
				} else if (position == 2) {
					mRadioButton2.performClick();
				} else if (position == 3) {
					mRadioButton3.performClick();
				} else if (position == 4) {
					mRadioButton4.performClick();
				} else if (position == 5) {
					mRadioButton5.performClick();
				} else if (position == 6) {
					viewPager.setCurrentItem(5);
				}
			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			public void onPageScrollStateChanged(int arg0) {
			}
		});
		viewPager.setCurrentItem(1);

	}

	// 获得当前被选中的RadioButton距离左侧的距离
	private float getCurrentCheckedRadioLeft() {
		if (mRadioButton1.isChecked()) {
			return getResources().getDimension(
					R.dimen.multitaglayouttestchain_radiobutton01);
		} else if (mRadioButton2.isChecked()) {
			return getResources().getDimension(
					R.dimen.multitaglayouttestchain_radiobutton02);
		} else if (mRadioButton3.isChecked()) {
			return getResources().getDimension(
					R.dimen.multitaglayouttestchain_radiobutton03);
		} else if (mRadioButton4.isChecked()) {
			return getResources().getDimension(
					R.dimen.multitaglayouttestchain_radiobutton04);
		} else if (mRadioButton5.isChecked()) {
			return getResources().getDimension(
					R.dimen.multitaglayouttestchain_radiobutton05);
		}
		return 0f;
	}
}