package com.tunaPasta18;

/**
 * @author TunaSashimi
 * @date 2021/7/26 20:17
 * @Copyright 2021 TunaSashimi. All rights reserved.
 * @Description
 */
public class InjectTest {
    public static int i;

    public InjectTest() {
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread.sleep(2000);
        long endTime = System.currentTimeMillis();
        System.out.println("execute time=" + (endTime - startTime) + "ms");
    }

    void method() {

    }
}
