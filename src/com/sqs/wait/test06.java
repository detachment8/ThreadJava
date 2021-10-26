package com.sqs.wait;

public class test06 {

    public static void main(String[] args) {

        final Object lock = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(lock){
                    System.out.println("begin wait");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("end wait");
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(lock){
                    System.out.println("begin notify");
                        lock.notify();
                    System.out.println("end notify");
                }
            }
        });

        //如果调换顺序就无法唤起线程
        t2.start();
        t1.start();


    }
}
