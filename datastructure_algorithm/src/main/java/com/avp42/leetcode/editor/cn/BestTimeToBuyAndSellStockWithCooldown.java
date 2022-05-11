//给定一个整数数组 prices，其中第 prices[i] 表示第 i 天的股票价格 。 
//
// 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）: 
//
// 
// 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。 
// 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1: 
//
// 
//输入: prices = [1,2,3,0,2]
//输出: 3 
//解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出] 
//
// 示例 2: 
//
// 
//输入: prices = [1]
//输出: 0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 5000 
// 0 <= prices[i] <= 1000 
// 
// Related Topics 数组 动态规划


package com.avp42.leetcode.editor.cn;

/**
 * 309 最佳买卖股票时机含冷冻期
 */
public class BestTimeToBuyAndSellStockWithCooldown{
    public static void main(String[] args) {
        Solution solution = new BestTimeToBuyAndSellStockWithCooldown().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProfit(int[] prices) {
        // 限制条件为不能在卖出第二天就买入，站在第i个元素的角度，即买入时，不能是昨天麦出的
        // dp[i][0] 为前i天的最大收益，在第i天完成交易后
        // dp[i][0] = max{dp[i-1][0], dp[i-1][1] + prices[i]}
        // dp[i][1] = max{dp[i-1][1], dp[i-2][0] - prices[i]}
        // 上面dp[i][1]的转换也可以通过下面得到
        // 因为dp[i-1][0]包括了dp[i-2][0] 和dp[i-2][1] + prices, 排除点了dp[i-2][1] + prices就剩下dp[i-2][0]
        int prePreProfit0 = 0, preProfit0 = 0, preProfit1 = -prices[0];
        for(int i = 1;  i < prices.length; i ++){
            int temp = preProfit0;
            preProfit0 = Math.max(preProfit0, preProfit1 + prices[i]);
            preProfit1 = Math.max(preProfit1, prePreProfit0 - prices[i]);
            prePreProfit0 = temp;
        }
        return preProfit0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}