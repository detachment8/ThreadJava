package com.sqs.wait;

public class test01 {

    public static void main(String[] args) {
        try {
        String test ="woshisqs";
        String test2 ="123";
        System.out.println("同步前的代码！！！");
        synchronized (test){
            System.out.println("同步开始！！！");
                test2.wait();//需要唤醒，不然会一直等待
            System.out.println("wait后的代码!!!");
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MAIN!!!");
    }
}
