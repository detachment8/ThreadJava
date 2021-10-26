package com.sqs.wait;

/**
 * 通过notify唤醒线程
 */
public class test02 {

    public static void main(String[] args) throws InterruptedException {

        String lock= "sqs";

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    System.out.println("t1开始等待"+System.currentTimeMillis());
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t1结束等待");
                }
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    System.out.println("t2开始唤醒"+System.currentTimeMillis());
                        lock.notify();
                    System.out.println("t2结束唤醒");
                }
            }
        });
        t1.start();
        t1.sleep(3000);
        t2.start();
    }
}
