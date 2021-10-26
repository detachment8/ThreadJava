package com.sqs.producerAndComsumer;

public class comsumerT extends Thread{

    private  valueOP obj;

    public comsumerT(valueOP obj){
        this.obj = obj;
    }

    @Override
    public void run() {
        while (true){
            obj.getValue();
        }
    }

}
