package com.sqs.producerAndComsumer;

public class producerT extends Thread{

    private  valueOP obj;

    public producerT(valueOP obj){
        this.obj = obj;
    }

    @Override
    public void run() {
        while (true){
            obj.setValue();
        }
    }

}
