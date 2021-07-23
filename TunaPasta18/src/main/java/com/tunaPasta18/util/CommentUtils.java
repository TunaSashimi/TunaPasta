package com.tunaPasta18.util;

import android.content.Context;

public class CommentUtils {
    public static CommentUtils utils;
    private Context context;

    public static CommentUtils getUtils(Context context) {
        if (utils == null) {
            synchronized (CommentUtils.class) {
                if (utils == null) {
                    // 因为静态utils变量的生命周期等同程序生命周期
                    // 所以当utils持有activity的context时，会使activity始终存在着utils的强引用，导致该activity无法被销毁
                    // 解决办法1.使用context.getApplicationContext(),2.弱引用context
                    utils = new CommentUtils(context);
                }
            }
        }
        return utils;
    }

    private CommentUtils(Context context) {
        this.context = context;
    }
}
