package com.gxx.juc.c_000;

/**
 * @program: threadStudyDemo
 * @description: volatile  线程可见性
 * @author: gouxx
 * @create: 2022/07/22 15:38
 * volatile 关键字，使一个变量在多个线程间可见
 * A B线程都用到一个变量，java默认是A线程中保留一份copy，这样如果B线程修改了该变量，则A线程未必知道
 * 使用volatile关键字，会让所有线程都会读到变量的修改值
 *
 * 在下面的代码中，running是存在于堆内存的t对象中
 * 当线程t1开始运行的时候，会把running值从内存中读到t1线程的工作区，在运行过程中直接使用这个copy，并不会每次都去
 * 读取堆内存，这样，当主线程修改running的值之后，t1线程感知不到，所以不会停止运行
 *
 * 使用volatile，将会强制所有线程都去堆内存中读取running的值
 *
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 */
public class T01_HelloVolatile {

    private static volatile boolean running = true;

    private static void m(){
        System.out.println("m start");
        int i = 1;
        while (running){
            i++;
//            SleepHelper.sleepSeconds(1);
            System.out.println(i);
        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        new Thread(T01_HelloVolatile::m,"t1").start();
        SleepHelper.sleepSeconds(1);
        running = false;
    }








}
