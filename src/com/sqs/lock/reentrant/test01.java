package com.sqs.lock.reentrant;

public class test01 {

    public synchronized void sm1(){
        System.out.println("sm1");

        //线程执行sm1()方法,默认this作为锁对象，在sm1()方法中调用了sm2()方法,注意当前线程还是持有this锁对象的
        //sm2()同步方法默认的锁对象也是this对象,要执行sm2( )必须先获得this锁对象，当前this对象被当前线程持有,可以再次获得
        //this对象，这就是锁的可重入性
        sm2();
    }

    public synchronized void sm2() {
        System.out.println("sm2");
        sm3();
    }

    public synchronized void sm3() {
        System.out.println("sm3");
    }

    public static void main(String[] args) {
        test01 obj = new test01();


        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.sm1();
            }
        }).start();
    }
}
