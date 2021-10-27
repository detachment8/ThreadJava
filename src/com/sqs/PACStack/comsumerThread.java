package com.sqs.PACStack;

/**
 * 生产者线程
 */
public class comsumerThread  extends Thread{

    private MyStack myStack;

    public comsumerThread(MyStack myStack){
        this.myStack =myStack;
    }
    @Override
    public void run() {
        while(true){
            myStack.pop();
        }
    }
}
