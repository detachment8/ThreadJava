package com.sqs.producerAndComsumer;

public class test {

    public static void main(String[] args) {
        valueOP valueOP = new valueOP();

        producerT p1 =new producerT(valueOP);p1.setName("p1");
        comsumerT c1 = new comsumerT(valueOP);c1.setName("c1");
        comsumerT c2 = new comsumerT(valueOP);c2.setName("c2");
        producerT p2 =new producerT(valueOP);p2.setName("p2");
        comsumerT c3 = new comsumerT(valueOP);c3.setName("c3");
        comsumerT c4 = new comsumerT(valueOP);c4.setName("c4");
        p1.start(); c1.start();c2.start();  p2.start(); c3.start();c4.start();
    }
}
