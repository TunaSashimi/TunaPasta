package com.tunaPasta18.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tunasashimi
 * @date 2018/11/13 16:55
 * @Copyright 2018 TunaSashimi. All rights reserved.
 * @Description
 */
public class Student {
    public String name;
    public List courses = new ArrayList<>();

    public Student(String name) {
        this.name = name;
        courses.add(new Course("语文"));
    }
}
