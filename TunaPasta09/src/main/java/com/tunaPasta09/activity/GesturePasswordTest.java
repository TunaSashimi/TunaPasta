package com.tunaPasta09.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.tunaPasta09.R;
import com.tunaPasta09.widget.LocusPassWordView;
import com.tunaPasta09.widget.LocusPassWordView.OnCompleteListener;

public class GesturePasswordTest extends Activity {
    private LocusPassWordView lpwv;
    private Toast toast;

    private void showToast(CharSequence message) {
        if (null == toast) {
            toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }

        toast.show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.gesturepasswordtest);

        //九宫格解锁在Android中应用的很广泛，也是Android特有的一种解锁方式，
        //其实实现起来也并不是很复杂，下面我就根据系统源码LockPatternView，
        //移植出来的一个更加简单小巧九宫格解锁的例子，和大家一起学习一下。

        lpwv = (LocusPassWordView) this.findViewById(R.id.mLocusPassWordView);
        lpwv.setOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(String mPassword) {
                // 如果密码正确,则进入主页面。
                if (lpwv.verifyPassword(mPassword)) {
                    showToast("登陆成功！");
                    Intent intent = new Intent(GesturePasswordTest.this, UnlockSuccessTest.class);
                    // 打开新的Activity
                    startActivity(intent);
                    finish();
                } else {
                    showToast("密码输入错误,请重新输入");
                    lpwv.clearPassword();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        // 如果密码为空,则进入设置密码的界面
        View noSetPassword = this.findViewById(R.id.tvNoSetPassword);
        TextView toastTv = findViewById(R.id.login_toast);
        if (lpwv.isPasswordEmpty()) {
            lpwv.setVisibility(View.GONE);
            noSetPassword.setVisibility(View.VISIBLE);
            toastTv.setText("请先绘制手势密码");
            noSetPassword.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(GesturePasswordTest.this, SetPasswordTest.class);
                    // 打开新的Activity
                    startActivity(intent);
                    finish();
                }

            });
        } else {
            toastTv.setText("请输入手势密码");
            lpwv.setVisibility(View.VISIBLE);
            noSetPassword.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
