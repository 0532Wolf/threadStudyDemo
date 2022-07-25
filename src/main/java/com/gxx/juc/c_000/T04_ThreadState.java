package com.gxx.juc.c_000;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @program: threadStudyDemo
 * @description: 线程状态
 * @author: gouxx
 * @create: 2022/07/20 10:25
 * java的6中线程状态
    NEW  线程刚刚创建，还没有启动
    RUNNABLE  可运行状态，由线程调度器可以安排执行
        包括READY和RUNNING两种细分状态
    WAITING  等待被唤醒
    TIMEDWAITING 隔一段时间后自动唤醒
    BLOCKED  被阻塞，正在等待锁
    TERMINATED 线程结束
 *
 */
public class T04_ThreadState {

    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("2: "+this.getState());
            for (int i = 0; i < 10; i++) {

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(i + "- ");
            }
            System.out.println();
        }

        public static void main(String[] args) throws Exception {
            Thread t1 = new MyThread();
            System.out.println("1: " + t1.getState());
            t1.start();
            t1.join();
            System.out.println("3: " + t1.getState());

            Thread t2 = new Thread(() -> {
                try {
                    LockSupport.park();
                    System.out.println("t2 go on!");
                    TimeUnit.SECONDS.sleep(5);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            t2.start();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("4: " + t2.getState());

            LockSupport.unpark(t2);
            TimeUnit.SECONDS.sleep(1);
            System.out.println("5: " + t2.getState());

            final Object o = new Object();
            Thread t3 = new Thread(()->{
                synchronized (o) {
                    System.out.println("t3 得到了锁 o");
                }
            });

            new Thread(()-> {
                synchronized (o) {
                    SleepHelper.sleepSeconds(5);
                }
            }).start();

            SleepHelper.sleepSeconds(1);

            t3.start();
            SleepHelper.sleepSeconds(1);
            System.out.println("6: " + t3.getState());

        }

    }




}
