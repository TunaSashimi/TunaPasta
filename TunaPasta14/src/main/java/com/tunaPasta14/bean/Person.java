package com.tunaPasta14.bean;

/**
 * @author Tunasashimi
 * @date 2019-06-30 23:50
 * @Copyright 2019 TunaSashimi. All rights reserved.
 * @Description
 */

public class Person {
    public  static final int TYPE_ONE = 1;
    public  static final int TYPE_TWO = 2;
    public  static final int TYPE_THREE = 3;

    public int type;
    public int avaterColor;
    public int contentColor;
    public String name;
    public String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getContentColor() {
        return contentColor;
    }

    public void setContentColor(int contentColor) {
        this.contentColor = contentColor;
    }

    public int getAvaterColor() {
        return avaterColor;
    }

    public void setAvaterColor(int avaterColor) {
        this.avaterColor = avaterColor;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static int getTypeThree() {
        return TYPE_THREE;
    }

    public static int getTypeTwo() {
        return TYPE_TWO;
    }
    public static int getTypeOne() {
        return TYPE_ONE;
    }
}