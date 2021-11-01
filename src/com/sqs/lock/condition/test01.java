package com.sqs.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 *
 * conditon等待与通知
 *
 */
public class test01 {
    static  Lock lock = new ReentrantLock();
    static Condition condition =lock.newCondition();
    static class SubThread extends Thread{

        @Override
        public void run() {
           try {
            lock.lock();
               System.out.println("method lock");
               condition.await();
               System.out.println("method await ");

           } catch (InterruptedException e) {
               e.printStackTrace();
           } finally {
               lock.unlock();
               System.out.println("method unlock");
           }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SubThread subThread = new SubThread();
        subThread.start();
        Thread.sleep(3000);
        try {
            lock.lock();
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
