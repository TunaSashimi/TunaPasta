package com.tunaPasta17.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

import com.tunaPasta17.R;
import com.tunaPasta17.databinding.DbIncludeBinding;
import com.tunaPasta17.databinding.ViewStubBinding;
import com.tunaPasta17.model.UserInfo;

import androidx.databinding.DataBindingUtil;

public class DBIncludeTest extends Activity {
    private DbIncludeBinding binding;
    private UserInfo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.db_include);
        binding.setHandler(new Handler());
        user = new UserInfo("leavesC", "123456");
        binding.setUserInfo(user);
        binding.viewStub.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub stub, View inflated) {
                //如果在 xml 中没有使用 bind:userInfo="@{userInf}" 对 viewStub 进行数据绑定
                //那么可以在此处进行手动绑定
                ViewStubBinding viewStubBinding = DataBindingUtil.bind(inflated);
                viewStubBinding.setUserInfo(user);
            }
        });
    }

    public class Handler {
        public void onClick(View v) {
            if (!binding.viewStub.isInflated()) {
                binding.viewStub.getViewStub().inflate();
            }
        }
    }
}
