package com.tunaPasta17.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tunaPasta17.R;
import com.tunaPasta17.mvp.MVPActivityBase;
import com.tunaPasta17.mvp.MVPPresenterSub;
import com.tunaPasta17.mvp.MVPViewSub;

public class MVPTest extends MVPActivityBase implements MVPViewSub {
    TextView text;
    MVPPresenterSub presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvptest);
        text = findViewById(R.id.text);
        //初始化Presenter
        presenter = new MVPPresenterSub();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 断开View引用
        presenter.detachView();
    }

    @Override
    public void showData(String data) {
        text.setText(data);
    }


    // button 点击事件调用方法
    public void getData(View view) {
        presenter.getData("normal");
    }

    // button 点击事件调用方法
    public void getDataForFailure(View view) {
        presenter.getData("failure");
    }

    // button 点击事件调用方法
    public void getDataForError(View view) {
        presenter.getData("error");
    }

}
