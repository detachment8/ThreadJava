package com.sqs.hook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class test01 {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("jvm exit,start this thread!");
                getLockFile().toFile().delete();
            }
        });

//2)程序运行时，检查lock文件是否存在，如果lock文件存在，则抛出异常
        if (getLockFile().toFile().exists()) {
            throw new RuntimeException("程序已启动");
        } else {
//文件不存在,说明程序是第一次 启动，创建lock文件
            try {
                getLockFile().toFile().createNewFile();
                System.out.println("程序在启动时创建了lock文件");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//模拟程序运行
        for (int i = 0; i < 100; i++) {
            System.out.println("============");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
    private static Path getLockFile() {
        return Paths.get("", "tmp.lcok");
    }

}
