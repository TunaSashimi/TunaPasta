package com.tunaPasta09.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;

import com.tunaPasta09.R;

public class ViewStubTest extends Activity {

    private View msgView;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewstubtest);

        this.findViewById(R.id.button01).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        if (flag) {
                            showMsgView();
                        } else {
                            closeMsgView();
                        }
                        flag = !flag;
                    }
                });
    }

    private void showMsgView() {
        if (msgView != null) {
            msgView.setVisibility(View.VISIBLE);
            return;
        }
        ViewStub stub = findViewById(R.id.msg_layout);
        msgView = stub.inflate();
    }

    private void closeMsgView() {
        if (msgView != null) {
            msgView.setVisibility(View.GONE);
        }

        ViewServer.get(this).addWindow(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ViewServer.get(this).removeWindow(this);
    }
}
