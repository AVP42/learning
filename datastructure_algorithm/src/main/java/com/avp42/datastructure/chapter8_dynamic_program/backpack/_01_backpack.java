package com.avp42.datastructure.chapter8_dynamic_program.backpack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @description: 01背包问题
 * @author: fuchang.wu@foxmail.com
 * @date: 2021-09-11 17:49
 */
public class _01_backpack {


    public static int solution1(int weightLimit, int[] w, int[] v){
        // dp[i][j] 表示前i个物品，重量不超过j 的最大价值
        // dp[i][j] = max{dp[i-1][j], dp[i-1][j-w[i]] + v[i]}
        int n = w.length;
        int[][] dp = new int[n + 1][weightLimit + 1];
        for(int i = 1; i <= n; i ++){
            for(int j = 1; j <= weightLimit; j ++){
                dp[i][j] = dp[i-1][j];
                if(j - w[i-1] >= 0){
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-w[i-1]] + v[i-1]);
                }
            }
        }
        return dp[n][weightLimit];
    }

    public static int solution2(int wl, int[] w, int[] v){
        int n = w.length;
        int[][] dp = new int[2][wl + 1];
        for(int i = 1; i <= n; i ++){
            int ind = i%2, preInd = (i -1) % 2;
            for(int j = 1; j <= wl; j ++){
                dp[ind][j] = dp[preInd][j];
                if(j - w[i-1]>=0){
                    dp[ind][j] = Math.max(dp[ind][j], dp[preInd][j - w[i-1]] + v[i-1]);
                }
            }
        }
        return dp[n%2][wl];
    }



    public static void main(String[] args) {

        int weightLimit = 15;
        int[] w = {4,3,12,9};
        int[] v = {10,7,12,8};

        System.out.println(">>>>>solution1:" + solution1(weightLimit, w, v));
        System.out.println(">>>>>solution1:" + solution2(weightLimit, w, v));

        Scanner scanner = new Scanner(System.in);
        int wl = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] dp = new int[2][wl + 1];
        int i = 1;
        while(i <= n ){
            System.out.println(i);
            int ind = i%2, preInd = (i -1) % 2;
            int weight= scanner.nextInt();
            int value = scanner.nextInt();
            for(int j = 1; j <= wl; j ++){
                dp[ind][j] = dp[preInd][j];
                if(j - weight >= 0){
                    dp[ind][j] = Math.max(dp[ind][j], dp[preInd][j-weight] + value);
                }
            }
            i ++;
        }
        scanner.close();
        System.out.println(">>>>>solution3:" + dp[n%2][wl]);
    }

}
