package com.avp42.interview.od.HJ;

/**
 * 放苹果
 *
 * @author wufc@viomi.com.cn
 * @since 2022/5/11 0011
 */
import java.util.*;

public class HJ61 {

    public static void main(String[] args) {
        // dp[i][j] i个桶放j个苹果
        // dp[i][j] = dp[i-1][j] + dp[i][j-i];
        // dp[i-1][j] 表示有一个盘子为空，dp[i][j-i] 表示所有盘子至少有一个苹果
        // 边界条件
        // 1.只要有0个或者一个苹果，不管盘有多少个，都只有一种方案
        // 2.只要有0个或者一个盘子，不管苹果有多少个，都只有一种方案
        // 与普通的背包问题相比，由于不考虑顺序，所以第二个选择是dp[i][j-i], 而不是dp[i][j-1];
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int m = in.nextInt(), n = in.nextInt();
//            System.out.println(helper(m, n));
            System.out.println(helper2(m, n));
        }
    }

    private static int helper2(int m, int n) {
        int[][] dp = new int[m + 1][n+1];
        for(int i = 0; i<=m; i ++){
            dp[i][1] = 1;
            dp[i][0] = 1;
        }
        for(int j = 0; j<=n;j++){
            dp[1][j] = 1;
            dp[0][j] = 1;
        }

        for(int i = 2; i <= m; i++){
            for(int j = 2; j <= n; j ++){
                dp[i][j] = dp[i][j-1];
                if(i >= j) dp[i][j] += dp[i-j][j];
            }
        }
        return dp[m][n];
    }

    // 递归
    private static int helper(int m, int n) {
        if(m <0 || n <0) return 0;
        if(m == 1 || n == 1) return 1;
        return helper(m,n-1) + helper(m - n, n);
    }


    public static void main2(String[] args) {
        // dp[i][j] i个桶放j个苹果
        // dp[i][j] = dp[i-1][j] + dp[i][j-i];
        // dp[i-1][j] 表示有一个盘子为空，dp[i][j-i] 表示所有盘子至少有一个苹果
        // 边界条件
        // 与普通的背包问题相比，由于不考虑顺序，所以第二个选择是dp[i][j-i], 而不是dp[i][j-1];
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int m = in.nextInt(), n = in.nextInt();
            int[][] dp = new int[n+1][m+1];
            for(int i = 0; i <= n; i ++){
                dp[i][0] = 1;
            }
            for(int j = 0; j <= m; j ++){
                dp[0][j] = 1;
            }
            for(int i = 1; i <= n; i ++){
                dp[i][1] = 1;
            }
            for(int j = 1; j <= m; j ++){
                dp[1][j] = 1;
            }
            for(int i = 1; i <= n; i ++){
                for(int j = 1; j <= m; j ++){
                    dp[i][j] = dp[i-1][j];
                    if(j >= i) dp[i][j] += dp[i][j-i];
                }
            }
            for(int[] arr: dp){
                System.out.println(Arrays.toString(arr));
            }
            System.out.println(dp[n][m]);
        }
    }
}
