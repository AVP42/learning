//你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
//被小偷闯入，系统会自动报警。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 
//输入：[1,2,3,1]
//输出：4
//解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 2： 
//
// 
//输入：[2,7,9,3,1]
//输出：12
//解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 400 
// 
// Related Topics 数组 动态规划


package com.avp42.leetcode.editor.cn;

/**
 * 198 打家劫舍
 */
public class HouseRobber{
    public static void main(String[] args) {
        Solution solution = new HouseRobber().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int rob(int[] nums) {
        // dp[i][0] 前i家得到的最多金额，第i不偷,
        // dp[i][0] = max{dp[i-1][0], dp[i-1][1]}
        // dp[i][1] = dp[i-1][0] + nums[i];
        // 尝试降维：定义f(n) 为前n家得到的最多金额, f(n) = ans = max{dp[i][0], dp[i][1]}
        // f(n) = max{dp[i-1][0], dp[i-1][1], dp[i-1][0] + nums[i]}
        //      = max{f(n-1), dp[i-1][0] + nums[i]}
        //      = max{f(n-1), max{dp[i-2][0], dp[i-2][1]} + nums[i]}
        //      = max{f(n-1), f(n-2) +nums[i]}
        // 可以理解为第i个房间不偷，此时相当于前i-1的情况，第i个房间偷，相当于i-2的情况 + nums[i]
        // 类似爬楼梯
        int gold0 = 0, gold1 = nums[0];
        for(int i = 1; i < nums.length; i ++){
            int temp = gold0;
            gold0 = Math.max(gold0, gold1);
            gold1 = temp + nums[i];
        }
        return Math.max(gold0, gold1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}