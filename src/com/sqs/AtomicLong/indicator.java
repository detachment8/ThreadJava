package com.sqs.AtomicLong;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 使用原子变量类定义一个计数器
 * 该计数器,在整个程序中都能使用,并且所有的地方都使用这一个计数器,这个计数器可以设计为单例
 */
public class indicator {

    private indicator(){}

    private static final  indicator instance1 =new indicator();

    public  static  indicator getIndicator() {
        return instance1;
    }

    private final AtomicLong requestCount = new AtomicLong(0);

    private final AtomicLong successCount = new AtomicLong(0);

    private final AtomicLong failureCount = new AtomicLong(0);

    public void  newRequestReceive(){
        requestCount.incrementAndGet();
    }

    public void  requestSuccess(){
        successCount.incrementAndGet();
    }

    public void  requestFailure(){
        failureCount.incrementAndGet();
    }

    public long getRequestCount() {
        return requestCount.get();
    }

    public long getSuccessCount() {
        return successCount.get();
    }

    public long getFailureCount() {
        return failureCount.get();
    }

}