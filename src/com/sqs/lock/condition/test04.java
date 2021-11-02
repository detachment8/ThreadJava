package com.sqs.lock.condition;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 使用condition实现生产者/消费者设计模式，多个线程交替打印
 */
public class test04 {


    static class  myService {

        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();
        private  boolean flag = true;

        public  void  printOne(){
            try {
                lock.lock();
                while(flag){
                    System.out.println(Thread.currentThread().getName()+"waiting ********");
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName()+"****************printOne");
                flag = true;
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

                lock.unlock();
            }
        }


        public  void  printTwo(){
            try {
                lock.lock();
                while(!flag){
                    System.out.println(Thread.currentThread().getName()+"waiting ----");
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName()+"---------------printTwo");
                flag = false;
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

                lock.unlock();
            }
        }


    }


    public static void main(String[] args) {
        myService myService = new myService();

        for (int j = 0;j<5;j++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0;i<100;i++){
                        myService.printOne();
                    }
                }
            }).start();
        }

        for (int j = 0;j<5;j++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0;i<100;i++){
                        myService.printTwo();
                    }
                }
            }).start();
        }
    }
}
