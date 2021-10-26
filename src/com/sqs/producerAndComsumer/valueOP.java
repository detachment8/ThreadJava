package com.sqs.producerAndComsumer;

public class valueOP {
    private String value="";

    public void setValue(){
        synchronized (this){
            while (!value.equalsIgnoreCase("")){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String value =System.currentTimeMillis()+"-"+System.nanoTime();
            System.out.println(Thread.currentThread().getName()+"-set设置的时间是"+value);
            this.value= value;
            this.notify();
        }

    }

    public String getValue() {
        synchronized (this){
            while (value.equalsIgnoreCase("")){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"-get的值是："+value);
            this.value ="";
            this.notifyAll();
        }
        return value;
    }


}
