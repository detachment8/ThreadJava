package com.sqs.ThreadException;

public class test01 {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName()+"这里的线程有异常："+e.getMessage());
            }
        });

        Thread t1 =new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"开始运行");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    //捕获异常
                    e.printStackTrace();
                }
                System.out.println(12/0);
            }
        });
        t1.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String txt = null;
                System.out.println(txt.length());
            }
        }).start();
        /*
        .在实际开发中,这种设计异常处理的方式还是比较常用的,尤其是异常执行的方法如果线程产生了异常，JVM会 调用dispatchUncaughtException()方法，
        在该方法中调用了getUncaughtExcept ionHandlerg(). uncaughtException(this, e);
        如果当前线程设置了UncaughtExcept ionHandler回调接口就直接调用它自己的uncaughtException方法，
        如果没有设置则调用当前线程所在线程组UncaughtExceptionHandler回调接口的uncaughtException方法，如果线程组也没有设置回调接口，
        则直接把异常的栈信息定向到System. err中
         */
    }
}
