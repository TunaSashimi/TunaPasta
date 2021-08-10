package com.tunaPasta16.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.tunaPasta16.R;
import com.tunaPasta16.livedata.User;
import com.tunaPasta16.livedata.UserViewModel;

public class LiveDataTest extends FragmentActivity {
    private TextView mContentTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.livedatatest);

        mContentTv = findViewById(R.id.tv_content);

        //构建ViewModel实例
        UserViewModel userModel = ViewModelProviders.of(this).get(UserViewModel.class);

        //让TextView观察ViewModel中数据的变化,并实时展示
        userModel.userMutableLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                mContentTv.setText(user.toString());
            }
        });

        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击按钮  更新User数据  观察TextView变化
                userModel.doSomething();
            }
        });
    }
}
