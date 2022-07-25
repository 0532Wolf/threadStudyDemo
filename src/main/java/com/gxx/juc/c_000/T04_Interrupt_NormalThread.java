package com.gxx.juc.c_000;

/**
 * @program: threadStudyDemo
 * @description: interrupt 设定标志位优雅结束线程
 * @author: gouxx
 * @create: 2022/07/22 16:43
 */
public class T04_Interrupt_NormalThread {

    public static void main(String[] args) {

        Thread t = new Thread(()->{
            int i = 0;
            while (!Thread.interrupted()){
                i++;
            }
            System.out.println("t end "+ i);
        });
        t.start();
        SleepHelper.sleepSeconds(1);
        t.interrupt();
    }




}
