package com.generic.demo2;

public class test07 {

    public static void main(String[] args) {
        box<Number> box1 = new box<>();
        box1.setFirst(100);
        showBox(box1);

        box<Integer> box2 = new box<>();
        box2.setFirst(100);
        //showBox(box2);
    }

    public static void showBox(box<Number> box){
        Number first = box.getFirst();
        System.out.println(first);
    }
}
