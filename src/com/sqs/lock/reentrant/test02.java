package com.sqs.lock.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class test02 {

    static Lock lock = new ReentrantLock();

    public static void sm() {
        lock.lock();
        for (int i=1;i<100;i++){
            System.out.println(Thread.currentThread().getName()+"-----"+i);
        }
        lock.unlock();
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                sm();
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
