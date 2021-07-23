package com.tunaPasta17.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

/**
 * @author TunaSashimi
 * @date 2020-04-11 23:43
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description
 */

public class BindingSeekBar extends AppCompatSeekBar {
    private InverseBindingListener inverseBindingListener;

    public InverseBindingListener getInverseBindingListener() {
        return inverseBindingListener;
    }

    public void setInverseBindingListener(InverseBindingListener inverseBindingListener) {
        this.inverseBindingListener = inverseBindingListener;
    }

    public BindingSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                System.out.println("==>3333");
                //触发反向数据的传递
                if (inverseBindingListener != null) {
//                    System.out.println("==>5555");
                    inverseBindingListener.onChange();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    //第一个参数是需要设置属性的view，第二个参数是需要设置的属性值。通过比较注释 @BindingAdapter的方法中的新旧值来打破可能的无限循环非常重要。
    @BindingAdapter(value = "progress")
    public static void setProgress(BindingSeekBar bindingSeekBar, int progress) {
//        System.out.println("==>1111");
        if (getProgress(bindingSeekBar) != progress) {
            bindingSeekBar.setProgress(progress);
        }
    }

    // 用InverseBindingAdapter定义getter函数。
    // attribute是xml中的属性名，event是设置属性监听的属性名，类型是InverseBindingListener。
    @InverseBindingAdapter(attribute = "progress", event = "progressChange")
    public static int getProgress(BindingSeekBar seekBar) {
//        System.out.println("==>2222");
        return seekBar.getProgress();
    }

    @BindingAdapter(value = {"progressChange"})
    public static void setProgressChange(BindingSeekBar bindingSeekBar, InverseBindingListener inverseBindingListener) {
//        System.out.println("==>4444");
        if (inverseBindingListener != null) {
            bindingSeekBar.setInverseBindingListener(inverseBindingListener);
        }
    }
}