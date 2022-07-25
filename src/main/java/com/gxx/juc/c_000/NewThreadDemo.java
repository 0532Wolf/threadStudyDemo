package com.gxx.juc.c_000;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @program: threadStudyDemo
 * @description: 创建线程的方式汇总
 * @author: gouxx
 * @create: 2022/07/20 17:21
 */
public class NewThreadDemo {

    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("Hello MyThread");
        }
    }

    static class MyRun implements Runnable {

        @Override
        public void run() {
            System.out.println("Hello MyRun");
        }
    }

    static class MyCall implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("Hello MyCall");
            return "success";
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRun()).start();
        new Thread(()->{
            System.out.println("hello lamba");
        }).start();
        Thread t = new Thread(new FutureTask<String>(new MyCall()));
        t.start();

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(()->{
            System.out.println("hello ThreadPool");
        });
        service.shutdown();
    }





}
