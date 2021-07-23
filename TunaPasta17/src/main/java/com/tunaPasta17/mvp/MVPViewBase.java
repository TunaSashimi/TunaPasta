package com.tunaPasta17.mvp;

import android.content.Context;

public interface MVPViewBase {
    /**
     * 显示正在加载view
     */
    void showLoading();
    /**
     * 关闭正在加载view
     */
    void hideLoading();
    /**
     * 显示提示
     * @param msg
     */
    void showToast(String msg);
    /**
     * 显示请求错误提示
     */
    void showErr();
    /**
     * 获取上下文
     * @return 上下文
     */
    Context getContext();
}