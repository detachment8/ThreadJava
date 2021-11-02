package com.sqs.lock.condition;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 使用condition实现生产者/消费者设计模式，两个线程交替打印
 */
public class test03 {


    static class  myService {

        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();
        private  boolean flag = true;

        public  void  printOne(){
            try {
                lock.lock();
                while(flag){
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName()+"****************printOne");
                flag = true;
                condition.signal();
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
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName()+"---------------printTwo");
                flag = false;
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

                lock.unlock();
            }
        }


    }


    public static void main(String[] args) {
        myService myService = new myService();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i<100;i++){
                    myService.printOne();
                }
            }
        }).start();

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
