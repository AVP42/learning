package com.avp42.datastructure.chapter8_dynamic_program.digit;

import java.util.Arrays;

/**
 * @description: 最简单的数位动态规划  计算比如2357的所有整数个数
 * @author: fuchang.wu@foxmail.com
 * @date: 2021-11-15 22:15
 */
public class CountIntegers {
    /** 给定一个数数，计算小于等于的整数个数*/
    int count(int num){
        int n = 0;
        int[] digits = new int[11];
        int j = 1;
        while(num > 0){
            n ++;
            digits[j++] = num % 10;
            num /= 10;
        }
        System.out.println(Arrays.toString(digits));
        // dp[pos][0] 第pos位未达到上界的个数
        // dp[pos][1] 第pos位达到上界的个数
        // pos 位数，从高到低为n,..,1位；  lim 是否达到上界
        int[][] dp = new int[n+1][2];
        dp[0][0] = 1; dp[0][1] = 1;
        for(int i = 1; i <= n; i++){
            dp[i][0] = 10 * dp[i-1][0];
            dp[i][1] = digits[i] * dp[i-1][0] + dp[i-1][1];
            System.out.println(dp[i][1]);
        }
        return dp[n][1];
    }

    /** 给定一个区间，计算区间内的整数*/
    int countRange(int l, int r){
        return count(r) - count(l-1);
    }


    public static void main(String[] args) {
        CountIntegers obj = new CountIntegers();
//        System.out.println(obj.count(2357));
        System.out.println(obj.count(100));
        System.out.println(obj.countRange(2350, 2357));

    }
}
