package com.avp42.interview.practise.huawei;

/**
 * HJ61 放苹果
 */

import java.util.*;
import java.util.stream.Collectors;

public class HJ61 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int m = in.nextInt(), n = in.nextInt();
            List<Integer> buffer = new ArrayList<>();
            Set<String> memo = new HashSet<>();
            dfs(m + 1, n - 1, 0, 0, buffer, memo);
            System.out.println(memo.size());
        }
    }

    // buffer 如果使用Stringbuilder的话，在回溯deleteCharAt的时候，对于大于10的两位整数，就会只删除最后一位数。
    private static void dfs(int len, int remain, int position, int prePosition, List<Integer> buffer, Set<String> memo) {
        if(remain == 0){
            buffer.add(len - 1 - prePosition);
            memo.add(convert(buffer));
            buffer.remove(buffer.size() - 1);
            return;
        }
        if(position >= len) return;
        for(int i = position; i < len; i++){
            // 选择
            buffer.add(i - prePosition);
            dfs(len, remain - 1, i, i, buffer, memo);
            buffer.remove(buffer.size() - 1);

        }
    }

    private static String convert(List<Integer> buffer) {
        List<Integer> temp = new ArrayList<>(buffer);
        Collections.sort(temp);
        return temp.stream().map(x -> x + "").collect(Collectors.joining());
    }


    public static void main2(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int m = in.nextInt(), n = in.nextInt();
            // dp[i][j] 用j个苹果放到i个盘子中的方法个数
            // dp[i][j] = dp[i-1][j] + dp[i][j-1];
            int[][] dp = new int[n+1][m+1];
            dp[0][0] = 1;
            for(int i = 1; i <= n; i ++){
                dp[i][0]  = 1;
            }

            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= m; j ++){
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
            System.out.println(dp[n][m]);
        }
    }
}
