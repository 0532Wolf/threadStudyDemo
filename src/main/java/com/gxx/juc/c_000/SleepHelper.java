package com.gxx.juc.c_000;

/**
 * @program: threadStudyDemo
 * @description:
 * @author: gouxx
 * @create: 2022/07/20 10:59
 */
public class SleepHelper {

    public static void sleepSeconds(int n )  {
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
