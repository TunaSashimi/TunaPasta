package com.tunaPasta17.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;


public interface IService extends IProvider {
    void sayHello(Context context);
}
