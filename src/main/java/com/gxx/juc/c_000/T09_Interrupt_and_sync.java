package com.gxx.juc.c_000;

/**
 * @program: threadStudyDemo
 * @description: 关于线程的打断
 * @author: gouxx
 * @create: 2022/07/20 15:12
//Thread.java
public void interrupt()            //t.interrupt() 打断t线程（设置t线程某给标志位f=true，并不是打断线程的运行）
public boolean isInterrupted()     //t.isInterrupted() 查询打断标志位是否被设置（是不是曾经被打断过）
public static boolean interrupted()//Thread.interrupted() 查看“当前”线程是否被打断，如果被打断，恢复标志位
 *
 * interrupt()不能打断正在竞争锁的线程synchronized()
 */
public class T09_Interrupt_and_sync {

    private static Object o =new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
           synchronized (o){
               System.out.println("睡10ms");
               SleepHelper.sleepSeconds(10);
           }
        });
        t1.start();
        SleepHelper.sleepSeconds(1);

        Thread t2 = new Thread(()->{
           synchronized (o){

           }
            System.out.println("t2 END!");
        });
        t2.start();
//        interrupt()不能打断正在竞争锁的线程synchronized()
        t2.interrupt();

    }


}
