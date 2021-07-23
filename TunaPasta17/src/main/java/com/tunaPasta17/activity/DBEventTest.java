package com.tunaPasta17.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Toast;

import com.tunaPasta17.R;
import com.tunaPasta17.databinding.DbEventBinding;
import com.tunaPasta17.model.UserInfo;

import androidx.databinding.DataBindingUtil;

public class DBEventTest extends Activity {
    private DbEventBinding binding;
    private UserInfo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.db_event);
        user = new UserInfo("leavesC", "12345");
        binding.setUserInfo(user);
        binding.setUserPresenter(new UserPresenter());
    }

    public class UserPresenter {
        public void onUserNameClick(UserInfo user) {
            Toast.makeText(DBEventTest.this, "用户名：" + user.getName(), Toast.LENGTH_SHORT).show();
        }

        public void afterTextChanged(Editable s) {
            user.setName(s.toString());
            binding.setUserInfo(user);
        }

        public void afterUserPasswordChanged(Editable s) {
            user.setPassword(s.toString());
            binding.setUserInfo(user);
        }
    }
}
