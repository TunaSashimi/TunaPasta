package com.tunaPasta17.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.databinding.ViewDataBinding;

/**
 * @author Tunasashimi
 * @date 2020-01-19 15:23
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description
 */
public class BaseModel<E extends ViewDataBinding> {

    /**
     * 上下文
     */
    protected Context mContext;
    /**
     * mBinding
     */
    protected E mBinding;
    private static final float SCALE_VALUE = 0.5F;
    private LayoutInflater mInflater;

    /**
     * 父类构造器
     *
     * @param binding ViewDataBinding
     */
    public BaseModel(E binding) {
        if (binding != null) {
            this.mBinding = binding;
            this.mContext = binding.getRoot().getContext();
            this.mInflater = LayoutInflater.from(mContext);
        }
        bindViews();
        initData(LoadState.FIRST_LOAD);
    }

    protected void initData(LoadState loadState) {
    }

    protected LayoutInflater getLayoutInflater() {
        return mInflater;
    }

    protected void bindViews() {
    }


    /**
     * 根据资源id寻找字符串
     *
     * @param strRes 字符串id
     * @return 返回字符串
     */
    public String getString(@StringRes int strRes) {
        return mContext.getString(strRes);
    }

    protected int getColor(@ColorRes int colorRes) {
        return ContextCompat.getColor(getContext(), colorRes);
    }

    protected int getColor(Context context, @ColorRes int color) {
        return ContextCompat.getColor(context, color);
    }

    protected Drawable getDrawable(@DrawableRes int drawRes) {
        return ContextCompat.getDrawable(getContext(), drawRes);
    }

    protected Drawable getDrawable(Context context, @DrawableRes int drawRes) {
        return ContextCompat.getDrawable(context, drawRes);
    }

    /**
     * 获取上下文
     *
     * @return 上下文
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * 获取Binding
     *
     * @return ViewDataDinging
     */
    public E getBinding() {
        return mBinding;
    }

    /**
     * 获取屏幕宽度
     *
     * @return 屏幕宽度
     */
    protected int getScreenWidth() {
        return mContext.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    protected int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + SCALE_VALUE);
    }

    /**
     * 获取屏幕高度
     *
     * @return 屏幕高度
     */
    protected int getScreenHeight() {
        return mContext.getResources().getDisplayMetrics().heightPixels;
    }


}
