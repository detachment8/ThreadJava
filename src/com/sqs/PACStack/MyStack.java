package com.sqs.PACStack;

import java.util.ArrayList;
import java.util.List;

public class MyStack {
    private List list = new ArrayList();
    private static final int MAX = 1;//集合最大的容量

    //定义入栈方法

    public synchronized void push() {

        while (list.size() >= this.MAX) {
            System.out.println(Thread.currentThread().getName() + "begin wait.....push");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String data = "data----" + Math.random();
        System.out.println(Thread.currentThread().getName() + "add date the data is :" + data);
        list.add(data);
        this.notifyAll();
    }

    public synchronized void pop() {
        while (list.size() == 0) {
            try {
                System.out.println(Thread.currentThread().getName() + "begin wait.....pop");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName() + "pop date the data is :" + list.remove(0));
        this.notifyAll();
    }
}
