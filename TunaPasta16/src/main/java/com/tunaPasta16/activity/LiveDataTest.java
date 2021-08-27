package com.tunaPasta16.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private Button btn_test;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.livedatatest);

        mContentTv = findViewById(R.id.tv_content);
        btn_test = findViewById(R.id.btn_test);

        //构建userViewModel实例
        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        //让TextView观察ViewModel中数据的变化,并实时展示
        userViewModel.userMutableLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                mContentTv.setText(user.toString());
            }
        });

        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击按钮  更新User数据  观察TextView变化
                userViewModel.update();
            }
        });
    }
}
