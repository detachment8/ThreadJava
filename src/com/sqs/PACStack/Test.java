package com.sqs.PACStack;

/**
 * 一对一
 */
public class Test {

    public static void main(String[] args) {
        MyStack myStack = new MyStack();

        producerThread p = new producerThread(myStack);
        comsumerThread c = new comsumerThread(myStack);
        p.setName("p==");  c.setName("c==");
        p.start();c.start();
    }
}
