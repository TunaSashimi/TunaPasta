package com.tunaPasta13.view;

import java.util.HashMap;
import java.util.LinkedHashMap;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

public class PageTransformerTest02ViewPager extends ViewPager {
    private float mTrans;
    private float mScale;
    /**
     * 最大的缩小比例
     */
    private static final float SCALE_MAX = 0.5f;
    /**
     * 保存position与对于的View
     */
    private HashMap<Integer, View> mChildrenViews = new LinkedHashMap<Integer, View>();
    /**
     * 滑动时左边的元素
     */
    private View mLeft;
    /**
     * 滑动时右边的元素
     */
    private View mRight;

    public PageTransformerTest02ViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

//		System.out.println("position=" +position+", positionOffset = "+positionOffset+" ,positionOffsetPixels =  "+ positionOffsetPixels+" , currentPos = " + getCurrentItem());

        // 滑动特别小的距离时，我们认为没有动，可有可无的判断
        float effectOffset = isSmall(positionOffset) ? 0 : positionOffset;

        // 获取左边的View
        mLeft = findViewFromObject(position);
        // 获取右边的View
        mRight = findViewFromObject(position + 1);

        // 添加切换动画效果
        animateStack(mLeft, mRight, effectOffset, positionOffsetPixels);

        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
    }

    public void setObjectForPosition(View view, int position) {
        mChildrenViews.put(position, view);
    }

    /**
     * 通过过位置获得对应的View
     *
     * @param position
     * @return
     */
    public View findViewFromObject(int position) {
        return mChildrenViews.get(position);
    }

    private boolean isSmall(float positionOffset) {
        return Math.abs(positionOffset) < 0.0001;
    }

    protected void animateStack(View left, View right, float effectOffset, int positionOffsetPixels) {
        if (right != null) {
            /**
             * 缩小比例 如果手指从右到左的滑动（切换到后一个）：0.0~1.0，即从一半到最大
             * 如果手指从左到右的滑动（切换到前一个）：1.0~0，即从最大到一半
             */
//			System.out.println("effectOffset====>"+effectOffset);
            mScale = (1 - SCALE_MAX) * effectOffset + SCALE_MAX;
            /**
             * x偏移量： 如果手指从右到左的滑动（切换到后一个）：0-720 如果手指从左到右的滑动（切换到前一个）：720-0
             */
            mTrans = -getWidth() - getPageMargin() + positionOffsetPixels;
            right.setScaleX(mScale);
            right.setScaleY(mScale);
            right.setTranslationX(mTrans);
        }
        //Change the view's z order in the tree, so it's on top of other sibling views
        if (left != null) {
            left.bringToFront();
        }
    }
}
