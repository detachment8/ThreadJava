package com.generic.demo4;

import java.lang.reflect.Method;

public class InfoImpl implements Info<Integer>{

    @Override
    public Integer info(Integer integer) {
        return integer;
    }


    public static void main(String[] args) {
        Class<InfoImpl> infoClass = InfoImpl.class;
        Method[] InfoImplMethods = infoClass.getMethods();
        for (Method method : InfoImplMethods) {
            System.out.println(method.getName() + ":"+method.getReturnType().getSimpleName());
        }

    }
}
