package com.tunaPasta19.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;

import com.tunaPasta19.R;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author Tunasashimi
 * @date 11/3/15 20:43
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class CustomThreadTest extends Activity {
    private Button button01;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.customthreadtest);

        button01 =  findViewById(R.id.button01);

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 5; i++) {
                    SystemClock.sleep(1000);
                    subscriber.onNext(i + 1);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        button01.setText("Observer==>" + integer);
                    }
                });
    }

}
