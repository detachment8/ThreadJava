package com.sqs.others;

public class testOne extends TestTwo{
    public testOne(){
        System.out.println("子类构造方法");
    }
    {
        System.out.println("子类代码块");
    }
    static {
        System.out.println("子类静态代码块");
    }

    public static void main(String[] args) {
        new testOne();
    }

}
class  TestTwo{
    public TestTwo(){
        System.out.println("父类构造方法");
    }
    {
        System.out.println("父类代码块");
    }
    static {
        System.out.println("父类静态代码块");
    }
    public static void find(){
        System.out.println("静态方法");
    }


    public static void main(String[] args) {
        System.out.println(new testOne());
    }
}
