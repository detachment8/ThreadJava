package com.sqs.casTest;

public class casTest {
    public static void main(String[] args) {
        casCount casCounter = new casCount();
        for ( int i =0;i<100;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"  "+casCounter.incrementAndGet());
                }
            }).start();
        }
    }

}
class casCount{
    volatile private long value;
    public long getValue(){
        return value;
    }


    private  boolean compareAndSwap(long exceptedValue,long newValue){
        synchronized (this){
            if (value ==exceptedValue){
                value = newValue;
                return true;
            }else {
                return false;
            }
        }}

    public  long incrementAndGet(){
        long oldValue;
        long newValue;
        do {
            oldValue = value;
            newValue = oldValue+1;
        }while (!compareAndSwap(oldValue,newValue));
        System.out.println(value);
        return value;
    }


}