package com.avp42.interview.practise.huawei;

import java.util.Scanner;
public class HJ35 {

    public static void main(String[] args) {
        Scanner in= new Scanner(System.in);
        while(in.hasNext()){
            int n = in.nextInt();
            int base = 1;
            for(int i = 1; i <= n; i ++){
                base += i - 1;
                int a = base;
                System.out.print(a + " ");
                for(int j = 2, delta = i + 1; j <= n - i + 1; j ++){
                    a += delta;
                    System.out.print(a + " ");
                    delta ++;
                }
                System.out.println();
            }
        }
    }
}
