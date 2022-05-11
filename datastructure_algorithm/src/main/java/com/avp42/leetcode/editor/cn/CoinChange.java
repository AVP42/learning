//给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。 
//
// 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。 
//
// 你可以认为每种硬币的数量是无限的。 
//
// 
//
// 示例 1： 
//
// 
//输入：coins = [1, 2, 5], amount = 11
//输出：3 
//解释：11 = 5 + 5 + 1 
//
// 示例 2： 
//
// 
//输入：coins = [2], amount = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：coins = [1], amount = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 12 
// 1 <= coins[i] <= 231 - 1 
// 0 <= amount <= 104 
// 
// Related Topics 广度优先搜索 数组 动态规划


package com.avp42.leetcode.editor.cn;

import java.util.Arrays;

/**
 * 322 零钱兑换
 */
public class CoinChange{
    public static void main(String[] args) {
        Solution solution = new CoinChange().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int coinChange(int[] coins, int amount) {
        // dp[i][j] 表示使用前i种硬币凑j元所需的最少硬币数量
        // 非线性递归方式：dp[i][j] = min{0<=k<=i|dp[i][j-k*coins[i]] + k}  考虑当前第i中硬币用多少，其他硬币用多少，遍历这个数量
        // 线性递归方式：dp[i][j] = min{dp[i-1][j], dp[i][j-coins[i]] + 1} 考虑当前硬币用于不用，只有两种情况
        int[][] dp = new int[coins.length+1][amount+1];
        Arrays.fill(dp[0], 1);
        for(int i = 1; i <= coins.length; i++){
            for(int j = 0; j <= amount; j ++){
                dp[i][j] = Math.min(dp[i-1][j], j < coins[i-1] ? 0 : (dp[i][j-coins[i-1]] + 1));
            }
        }
        return dp[coins.length][amount];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}