package com.tunaPasta17.mvp;

public class MVPPresenterBase<V extends MVPViewBase> {
    /**
     * 绑定的view
     */
    private V baseView;

    /**
     * 绑定view，一般在初始化中调用该方法
     */

    public void attachView(V BaseView) {
        this.baseView = BaseView;
    }

    /**
     * 断开view，一般在onDestroy中调用
     */

    public void detachView() {
        this.baseView = null;
    }

    /**
     * 是否与View建立连接
     * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
     */
    public boolean isViewAttached() {
        return baseView != null;
    }

    /**
     * 获取连接的view
     */
    public V getView() {
        return baseView;
    }
}