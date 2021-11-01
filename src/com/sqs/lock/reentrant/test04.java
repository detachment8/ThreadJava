package com.sqs.lock.reentrant;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class test04 {

    static class IntLock implements Runnable {
        public static Lock lock1 =
                new ReentrantLock();

        public static Lock lock2 =
                new ReentrantLock();

        int lockNum; //定义 整数变量,决定使用哪个锁

        public IntLock(int lockNum) {
            this.lockNum = lockNum;
        }


        @Override
        public void run() {
            try {
                if (lockNum % 2 == 1) {
//奇数
                    lock1.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "获得锁1,还需要获得锁2");
                    Thread.sleep(new Random().nextInt(500));
                    lock2.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "同时获得了锁1与锁2....");
                } else {
//偶数
                    lock2.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "获得锁2,还需要获得锁1");
                    Thread.sleep(new Random().nextInt(500));
                    lock1.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "同时获得了锁1与锁2....");
                }
            } catch
            (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
                lock2.unlock();
                System.out.println(Thread.currentThread().getName() + "线程退出");
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        IntLock intLock1 = new IntLock(11);
        IntLock intLock2 = new IntLock(22);

        Thread t1 = new Thread(intLock1);
        Thread t2 = new Thread(intLock2);

        t1.start();
        t2.start();

        Thread.sleep(3000);

       // 可以中断任何一个线程来解决死锁，t2线程会放弃对锁1的申请,同时释放锁2， t1线程会完成它的任务
        if (t2.isAlive()){t2.interrupt();}
    }
}
