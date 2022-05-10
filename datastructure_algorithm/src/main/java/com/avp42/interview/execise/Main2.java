package com.avp42.interview.execise;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.Collectors;


// 注意类名必须为 Main, 不要有任何 package xxx 信息

/**
 * 给一个数字，求该数字是否可以分解为2个质数相乘。可以的话按升序返回2个质数，空格分开。如果不行返回"-1 -1"
 * 用例 15->[3 5]
 * 用例 27->[-1 -1]
 *
 * 通过率35%
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int num = in.nextInt();
            // 埃氏筛 一个数为质数，则它的倍数都是合数，可以从i*i开始，避免重复计算
            int[] primes = new int[num];
            Arrays.fill(primes, 1);
            for(int i = 2;i < primes.length; i++){
                if(primes[i] == 1){
                    for(int j = i; i * j < num;  j++){
                        primes[i*j] = 0;
                    }
                }
            }
            System.out.println(Arrays.toString(primes));
            boolean flag = true;
            for(int i = 2;i <= num / 2; i++){
                if(primes[i] == 0) continue;
                if(num % i != 0) continue;
                if(primes[num / i] == 0) continue;
                System.out.println(Arrays.toString(new int[]{i, num/i}));
                flag = false;
                break;
            }
            if(flag){
                System.out.println(Arrays.toString(new int[]{-1, -1}));
            }
        }
    }


}