package com.tunaPasta16.livedata;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    public MutableLiveData<User> userMutableLiveData = new MutableLiveData();

    public UserViewModel() {
        //模拟从网络加载用户信息
        userMutableLiveData.postValue(new User(18, "name18"));
    }

    //模拟 进行一些数据骚操作
    public void update() {
        User user = userMutableLiveData.getValue();
        if (user != null) {
            user.age = 25;
            user.name = "name25";
            userMutableLiveData.setValue(user);
        }
    }
}


