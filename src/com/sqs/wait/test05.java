package com.sqs.wait;

/**
 * notifyALL();
 */
public class test05 {
    public static void main(String[] args) throws InterruptedException {
    Object lock = new Object();
    SubThread t1 = new SubThread(lock);
    t1.setName("t1");
        SubThread t2 = new SubThread(lock);
        t2.setName("t2");
        SubThread t3 = new SubThread(lock);
        t3.setName("t3");
        t1.start();t2.start();t3.start();
        Thread.sleep(200);
        synchronized(lock){
            lock.notify();
            lock.notifyAll();
        }
    }
   static class SubThread extends Thread{
    private Object lock;
    public SubThread(Object lock){
        this.lock= lock;
    }

       @Override
       public void run() {
           synchronized(lock){

               try {
                   System.out.println(Thread.currentThread().getName()+"/////begin wait");
                   lock.wait();
                   System.out.println(Thread.currentThread().getName()+"/////end wait");
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

           }
       }
   }
}
