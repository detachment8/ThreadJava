package com.sqs.ThreadPool;

import java.util.concurrent.*;

/**
 * 异常处理
 *
 */
public class test03 {
    private static class myPool extends ThreadPoolExecutor{

        public myPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        //定义方法，对执行的任务进行包装，接收两个参数，第一 个参数接收要执行的任务，第-个参数是一 个Exception异常
        public Runnable wrap(Runnable task , Exception exception){
            return new Runnable() {
                @Override
                public void run() {
                    try{
                        task.run();
                    }catch ( Exception e){
                        exception.printStackTrace();
                        throw e;
                    }

                }
            };
        }

        //重写submit
        @Override
        public Future<?> submit(Runnable task) {
            return super.submit(wrap(task,new Exception("客户跟踪异常")));
        }
    }
    private static class myTask implements Runnable{

        private int x;
        private  int y;
        myTask(int x,int y){
            this.x=x;this.y=y;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"计算"+x+"/"+y+"="+(x/y));
        }
    }
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor;
       // poolExecutor = new ThreadPoolExecutor(0,Integer.MAX_VALUE,0,TimeUnit.SECONDS,new SynchronousQueue<>());
        poolExecutor =new myPool(0,Integer.MAX_VALUE,0,TimeUnit.SECONDS,new SynchronousQueue<>());
        for (int i =0;i<5;i++){
            poolExecutor.submit(new myTask(10,i));
           // poolExecutor.execute(new myTask(10,i));
            /*
            运行程序，只有四条计算结果，我们实际上向线程池提交了5个计算任务，分析结果发现当i==0时，提交的任务会产生算术异常,线
            程池把该异常给吃掉了，导致我们对该 异常一无所知
            解决方法:
            -是把submit( )提交方法改为execute();
            二是对线程池进行扩展，对submit()方法进行包装
            */
        }

    }
}
