package com.sqs.wait;

import java.util.ArrayList;
import java.util.List;

public class test03 {


    public static void main(String[] args) throws InterruptedException {

        List<String> list = new ArrayList<>();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1开始执行"+System.currentTimeMillis());
                synchronized (list){
                    if (list.size()!=5)
                    try {
                        list.wait();
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
                System.out.println("t2开始执行"+System.currentTimeMillis());
                synchronized (list){
                    for(int i =0;i<10;i++){
                        System.out.println("t2 thread add number "+i+" data");
                        list.add("data:"+i);
                        if (list.size()==5){
                            list.notify();//并不会立即释放锁对象，必须等所有同步代码块执行完后才释放
                            System.out.println("send notify");
                            //让t1执行
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }


                    }
                    System.out.println("t2结束唤醒");

                }
            }
        });
        t1.start();
        Thread.sleep(100);
        t2.start();
    }
}
