package com.tunaPasta16.livedata;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    public final MutableLiveData<User> userMutableLiveData = new MutableLiveData();

    public UserViewModel() {
        //模拟从网络加载用户信息
        userMutableLiveData.postValue(new User(1, "name1"));
    }

    //模拟 进行一些数据骚操作
    public void doSomething() {
        User user = userMutableLiveData.getValue();
        if (user != null) {
            user.age = 15;
            user.name = "name15";
            userMutableLiveData.setValue(user);
        }
    }
}


