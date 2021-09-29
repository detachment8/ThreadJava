package com.sqs.CAS;


/**
 *
 * CAS实现计数器
 */
public class casTest {

    volatile long value;

    public long getValue() {
        return value;
    }

    private boolean compareAndSwap(long expectedValue, long newValue) {
        synchronized (this) {
            if (value == expectedValue) {
                value = newValue;
                return true;
            } else return false;
        }
    }

    //知道检测到新值和旧值一样才加一
    public long incrementAndGet() {
        long oldValue;
        long newValue;
        do {
            oldValue = value;
            newValue = oldValue + 1;
        } while (!compareAndSwap(oldValue, newValue));
        return newValue;
    }

    public static void main(String[] args) {
        casTest casTest = new casTest();
        for (int i =1;i<1000;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(casTest.incrementAndGet());
                }
            }).start();
        }
    }

}