package com.sqs.lock.reentrant;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class test03 {

    static Lock lock = new ReentrantLock();

    public static void sm1(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"====method1===="+System.currentTimeMillis());
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
           lock.unlock();
        }
    }


    public static void sm2(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"====method2===="+System.currentTimeMillis());
            Thread.sleep(new Random().nextInt(1000));
            System.out.println(Thread.currentThread().getName()+"====method2 sleep===="+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                sm1();
            }
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                sm1();
            }
        };

       new Thread(r1).start();
        new Thread(r1).start();
        new Thread(r1).start();
        new Thread(r1).start();
        new Thread(r1).start();

    }
}
