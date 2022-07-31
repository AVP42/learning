package com.avp42.interview.practise.huawei;

import java.util.Scanner;

public class HJ52 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String text1 = in.nextLine();
            String text2 = in.nextLine();
            int n = text1.length(), m = text2.length();
            int[][] dp = new int[n+1][m + 1];
            for(int i = 1; i <= n; i ++){
                dp[i][0] = i;
            }
            for(int j = 1; j <= m; j ++){
                dp[0][j] = j;
            }
            for(int i = 1;i <= n; i ++){
                char c1 = text1.charAt(i-1);
                for(int j = 1; j <= m; j ++){
                    char c2 = text2.charAt(j-1);
                    if(c1 == c2){
                        dp[i][j] = dp[i-1][j-1];
                    }else{
                        // dp[i-1][j] text1删除第i个元素； dp[i][j-1] text1插入新元素; dp[i-1][j-1] 表示text1替换元素
                        dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                    }
                }
            }
            System.out.println(dp[n][m]);
        }
    }
}
