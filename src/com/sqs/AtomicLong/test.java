package com.sqs.AtomicLong;

import java.util.Random;

public class test {
    public static void main(String[] args) {
        //通过线程模拟线程
        for (int i =1;i<100;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                indicator.getIndicator().newRequestReceive();
                int num = new Random().nextInt();
                if (num%2 == 0){
                    indicator.getIndicator().requestSuccess();
                }else
                    indicator.getIndicator().requestFailure();
                }
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(indicator.getIndicator().getSuccessCount());
        System.out.println(indicator.getIndicator().getFailureCount());
        System.out.println(indicator.getIndicator().getRequestCount());
    }
}