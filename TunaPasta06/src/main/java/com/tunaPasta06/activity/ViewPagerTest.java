package com.tunaPasta06.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.tunaPasta06.R;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * 第一次进入三张图片 只要修改第pic数组，添加或者修改资源即可替换图片
 */
public class ViewPagerTest extends Activity {
    private ViewPager viewPager;
    private int pic[] = {R.drawable.lunzhuan1, R.drawable.lunzhuan2, R.drawable.lunzhuan3, R.drawable.lunzhuan4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.viewpagertest);

        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public Object instantiateItem(View arg0, int arg1) {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                imageView.setImageResource(pic[arg1]);
                imageView.setScaleType(ScaleType.CENTER_CROP);
                if (arg1 == (pic.length - 1)) {
                    imageView.setOnTouchListener(new OnTouchListener() {
                        int startX;
                        private int endX;

                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            switch (event.getAction()) {
                                case MotionEvent.ACTION_DOWN:
                                    startX = (int) event.getRawX();
                                    break;
                                case MotionEvent.ACTION_MOVE:
                                    endX = (int) event.getRawX();
                                    int distance = endX - startX;
                                    if (distance < -10) {
                                        finish();
                                    }
                                    break;
                            }
                            return true;
                        }
                    });
                }
                ((ViewPager) arg0).addView(imageView);
                return imageView;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void restoreState(Parcelable arg0, ClassLoader arg1) {
            }

            @Override
            public Parcelable saveState() {
                return null;
            }

            @Override
            public void destroyItem(View arg0, int arg1, Object arg2) {
                ((ViewPager) arg0).removeView((View) arg2);
                arg2 = null;
            }

            @Override
            public void finishUpdate(View arg0) {
            }

            @Override
            public int getCount() {
                return pic.length;
            }
            // @Override
            // public int getItemPosition(Object object) {
            // return POSITION_NONE;
            // }

            // @Override
            // public float getPageWidth(int position){
            // return 1.0f;
            // }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 在非第一页与最后一页时，滑动到下一页，position为当前页位置；滑动到上一页：position为当前页-1
                // positionOffset 滑动到下一页，[0,1)区间上变化；滑动到上一页：(1,0]区间上变化
                // positionOffsetPixels这个和positionOffset很像：滑动到下一页，[0,宽度)区间上变化；滑动到上一页：(宽度,0]区间上变化
                // System.out.println("position==>"+position);
                // System.out.println("positionOffset==>"+positionOffset);
                // System.out.println("positionOffsetPixels==>"+positionOffsetPixels);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

}
