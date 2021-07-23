package com.tunaPasta17.mvp;

public interface MVPViewSub extends MVPViewBase {
    /**
     * 当数据请求成功后，调用此接口显示数据
     * @param data 数据源
     */
    void showData(String data);
}