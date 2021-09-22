package com.sqs.volatileTest;

public class p1  {
    public static void main(String[] args) {
        for (int i =0;i<100;i++){
          new MyThread().start();
        }
    }


    static class MyThread extends Thread{
          public  static int count;

        public  synchronized static void addCount(){
                for (int i =0;i<1000;i++){
                    count++;
                }
            System.out.println(Thread.currentThread().getName()+" count = "+count);
        }

        @Override
        public void run() {
            addCount();
        }
    }


}