package com.avp42.interview.execise;


import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息

/**
 * 给字符串A和B。A和B长度相等且长度>=1。给整数V>=0。
 * 求A和B的最长连续公共子串，且子串在A中满足：|A[i]-B[i]|的和小于等于V，公式意思为A字串对应同位置的B字串字符之差的绝对值。
 *
 *
 * 用例：
 * xxcdefg
 * cdefghi
 * 5
 * 返回：2 (A中"cd" "de" "ef" "fg" 可以满足要求，|A[2]-B[2]|+|A[3]-B[3]|=4<V )
 *
 *
 * 通过率 25%
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String a = in.nextLine();
            String b = in.nextLine();
            int V = in.nextInt();
            int n = a.length(), m = b.length();
            // dp[i][j] 表示a以第i个字符结尾，b以第j个字符结尾的最长连续公共子串长度
            // sum[i][j] 表示a以第i个字符结尾，b以第j个字符结尾的最长连续公共子串的V之和
            // dp[i][j] = dp[i-1][j-1] + 1 , sum[i][j] = sum[i-1][j-1] + |A[i] -B[i]| a[i] = b[j] && sum[i-1][j-1] + |A[i] - B[i]| <=v
            // dp[i][j] = 0 sum[i][j] = 0
            int[][] dp = new int[n+1][m+1];
            int[][] sum = new int[n+1][m+1];
            int ans = 0;
            for(int i = 1; i<=n; i ++){
                char c1 = a.charAt(i-1);
                for(int j = 1; j<=m; j ++){
                    char c2 = a.charAt(j-1);
                    int cur = sum[i-1][j-1] + Math.abs(c1 - b.charAt(i-1));
                    if(c1 == c2 && cur <= V) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        sum[i][j] = cur;
                        ans = Math.max(ans, dp[i][j]);
                    }
                }
            }
            for(int i = 0; i <=n; i ++){
                System.out.println(Arrays.toString(dp[i]));
            }
            System.out.println(ans);
        }
    }


}