package com.sqs.PACStack;

/**
 * 一对多
 */
public class Test02 {


    public static void main(String[] args) {
        MyStack myStack = new MyStack();

        producerThread p = new producerThread(myStack);
        comsumerThread c1 = new comsumerThread(myStack);
        comsumerThread c2 = new comsumerThread(myStack);
        p.setName("p==");
        c1.setName("c1==");
        c2.setName("c2==");
        p.start();
        c1.start();
        c2.start();
    }
}
