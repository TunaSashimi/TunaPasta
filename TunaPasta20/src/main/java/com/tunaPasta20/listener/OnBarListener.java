package com.tunaPasta20.listener;

import android.graphics.drawable.Drawable;

/**
 * @author Tunasashimi
 * @date 2020-01-17 12:00
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description 标题栏设置
 */
public interface OnBarListener {
    Drawable getDrawableLeft();

    Drawable getDrawableRight();

    String getTextCenter();

    String getTextLeft();

    String getTextRight();
}
