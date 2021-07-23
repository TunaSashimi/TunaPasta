package com.tunaPasta18.activity;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Tunasashimi
 * @date 11/3/15 20:43
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class RXJava2Test extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //concat
        final String[] aStrings = {"A1", "A2", "A3", "A4"};
        final String[] bStrings = {"B1", "B2", "B3"};

        final Observable<String> aObservable = Observable.fromArray(aStrings);
        final Observable<String> bObservable = Observable.fromArray(bStrings);

        Observable.concat(aObservable, bObservable)//使用concat操作符将两个被观察者合并
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("concat==>" + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete==>");
                    }
                });

        //map
        Observable.create(new ObservableOnSubscribe<Integer>() { // 第一步：初始化Observable
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onNext(5);
                emitter.onComplete();
                emitter.onNext(6);
            }
        }).subscribeOn(Schedulers.io())
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) {
                        return integer + 1;
                    }
                }).observeOn(Schedulers.newThread())
                .subscribe(new Observer<Integer>() {
                    private Disposable mDisposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        if (integer == 3) {
                            mDisposable.dispose();
                        }
                        System.out.println("map==>" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError==>");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete==>");
                    }
                });

        //
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) {
                List<String> list = new ArrayList();
                for (int i = 0; i < 3; i++) {
                    list.add("integer is " + integer + ", i is " + i);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("flatMap==>" + s + "\n");
            }
        });

        //
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {
                emitter.onNext("er");
                emitter.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                               @Override
                               public void accept(String s) {

                               }
                           }
                );
    }
}