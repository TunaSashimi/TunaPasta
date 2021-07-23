package com.tunaPasta08.entity;

import android.graphics.drawable.Drawable;

//做出自己的一个类,以后准备分别启动~~~~~~
public class Cloth {

    public Drawable drawable;
    public int x, y, width, height;           //左上角点的x,y,w表示宽度,h表示高度
    public boolean canChange, canMove;        //可以移动和改变大小的布尔标识值
    public double proportion;                 //高除以宽,为内在的比值,定下来就不变

    public int type;                          //用来区别类型~~
    public long timeMiles;

    public Cloth(Drawable drawable, int x, int y, int type, long timeMiles) {
        this.drawable = drawable;
        width = drawable.getMinimumWidth();
        height = drawable.getMinimumHeight();
        proportion = height * 1.0 / width;                //必须先乘以1.0使得height/width结果为double数值
        this.x = x;
        this.y = y;
        this.type = type;
        this.timeMiles = timeMiles;
    }
}
