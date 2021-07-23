package com.tunaPasta17.model;

/**
 * @author Tunasashimi
 * @date 2020-01-19 15:25
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description
 */
public enum LoadState {
    /**
     * 刷新加载
     */
    REFRESH_LOAD(0),
    /**
     * 加载更多
     */
    LOAD_MORE(1),
    /**
     * 首次加载
     */
    FIRST_LOAD(2),
    /**
     * 用户手动触发请求
     */
    CLICK_LOAD(3),
    /**
     * 加载更多结束
     */
    LOAD_MORE_COMPLETE(4),
    /**
     * 没有更多数据
     */
    LOAD_MORE_END(5),
    /**
     * 取消上拉加载
     */
    CANCEL_LOAD_MORE(7),
    /**
     * 开启上拉加载
     */
    OPEN_LOAD_MORE(8),
    /**
     * 登陆状态改变刷新
     */
    LOGIN_STATE_CHANGE(9);
    private int mState;

    LoadState(int state) {
        this.mState = state;
    }

    /**
     * 返回加载状态
     *
     * @return 加载状态
     */
    public int getState() {
        return mState;
    }
}
