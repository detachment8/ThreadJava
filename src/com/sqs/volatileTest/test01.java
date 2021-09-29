package com.sqs.volatileTest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用原子类自增
 */
public class test01 {

    static class MyThread extends Thread{
    private static AtomicInteger count = new AtomicInteger();

        @Override
        public void run() {
            addCount();
        }

        private void addCount() {
        for (int i =1 ;i<=100;i++){
            count.getAndIncrement();
        }
            System.out.println(Thread.currentThread().getName()+"count = "+count);
        }
    }



    public static void main(String[] args) {


        for (int i =1 ;i<=100;i++){
            new MyThread().start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(MyThread.count.get());
    }
}