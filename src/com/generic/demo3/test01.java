package com.generic.demo3;

import java.util.ArrayList;

public class test01  {

    public static void main(String[] args) {

    }

    public static void showAnimal(ArrayList<? super animal> list) {
        list.add(new blueCat());
        list.add(new cat());

    }

    public static void showAnimal2(ArrayList<? extends blueCat> list) {

    }
}
