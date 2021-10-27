package com.sqs.PACStack;

/**
 * 生产者线程
 */
public class producerThread  extends Thread{

    private MyStack myStack;

    public producerThread(MyStack myStack){
        this.myStack =myStack;
    }
    @Override
    public void run() {
        while(true){
        myStack.push();
        }
    }
}
