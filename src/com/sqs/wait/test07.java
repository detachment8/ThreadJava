package com.sqs.wait;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * wait条件变化
 */
public class test07 {
    public static void main(String[] args) {
        ThreadAdd t1 = new ThreadAdd();
        ThreadSubstract t2 = new ThreadSubstract();
        ThreadSubstract t3 = new ThreadSubstract();
        t3.setName("Substract-2");
        t1.setName("add-1");
        t2.setName("Substract-1");
        t2.start();t3.start(); t1.start();
    }
    public static void substract() throws InterruptedException {
        synchronized (list){
        while (list.size() == 0 ){
            System.out.println(Thread.currentThread().getName()+" begin wait...");
            list.wait();
            System.out.println(Thread.currentThread().getName()+" end wait...");
        }
            Object data =list.remove(0);
            System.out.println(Thread.currentThread().getName()+"从集合中取了"+data+"后，集合中数据的数量"+list.size());
        }
    }

    public  static void  add(){
            synchronized (list){
                    list.add("data");
                    System.out.println(Thread.currentThread().getName()+"从集合中添加data后，集合中数据的数量"+list.size());
                    list.notifyAll();
            }
        }

        static  class ThreadAdd extends Thread{
            @Override
            public void run() {
                add();
            }
        }

    static  class ThreadSubstract extends Thread{
        @Override
        public void run() {
            try {
                substract();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static List list = new ArrayList();
}

