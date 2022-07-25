package com.gxx.juc.c_000;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: threadStudyDemo
 * @description: 如果想打断竞争锁的线程，使用ReentrantLock
 * @author: gouxx
 * @create: 2022/07/20 15:39
 */
public class T11_Interrupt_and_lockInterruptibly {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(()-> {
            lock.lock();
            try {
                SleepHelper.sleepSeconds(10);
            } finally {
                lock.unlock();
            }
            System.out.println("t1 end!");
        });

        t1.start();

        SleepHelper.sleepSeconds(1);


        Thread t2 = new Thread(()-> {
            System.out.println("t2 start!");
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println("t2 end!");
        });

        t2.start();

        SleepHelper.sleepSeconds(1);

        t2.interrupt();
    }



}
