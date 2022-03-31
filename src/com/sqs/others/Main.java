package com.sqs.others;

import java.util.Scanner;

public class Main {

    public int[] solution(String temp){
        int r[] = new int[4];
        int size = temp.length();
        int counts[][] = new int[size][size];

        //i为起始位置
        for (int i =0;i<size;i++){
            //j为字符串长度
            for (int j =1;j<=size-i && j!=size;j++)
                counts[i][j] = getStringOneCount(temp.substring(i,i+j));
        }

        for (int i =size-1;i>=1;i--){
            //找寻长度相等且含有1的个数相等的字符串
            for (int j =0;j<size-i;j++){
                for (int k =j+1;k<=size-i;k++){
                    if (counts[j][i] == counts[k][i]){
                        r[0] =j+1;r[1] =j+i;
                        r[2] =k+1;r[3] =k+i;
                        return  r;
                    }
                }
            }
        }
        return r;
    }

    public int getStringOneCount(String temp){
        int r = 0;
        for (int i =0;i<temp.length();i++)
            if (temp.charAt(i)=='1')
                r++;
        return r;
    }

    public static void main(String[] args) {
        Main mainclass = new Main();
        Scanner sc= new Scanner(System.in);
        String t = sc.next();
        //System.out.println(t.substring(3,3+2));
        int[] solution = mainclass.solution(t);
        for (int i:
             solution) {
            System.out.println(i);

        }
    }
}
