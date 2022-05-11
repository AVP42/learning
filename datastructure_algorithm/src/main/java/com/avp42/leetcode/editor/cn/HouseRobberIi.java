//你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的
//房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,3,2]
//输出：3
//解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,1]
//输出：4
//解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 3： 
//
// 
//输入：nums = [1,2,3]
//输出：3
// 
//
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 1000 
// 
// Related Topics 数组 动态规划 
// 👍 996 👎 0


package com.avp42.leetcode.editor.cn;

/**
 * 213 打家劫舍 II
 */
public class HouseRobberIi{
    public static void main(String[] args) {
        Solution solution = new HouseRobberIi().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int rob(int[] nums) {
        // dp[i][0][0] 0 not steal，i not steal： dp[i][0][0] = max(dp[i-1][0][0] , dp[i-1][0][1]);
        // dp[i][0][1] 0 not steal，i steal： dp[i][0][1] = dp[i-1][0][0] + nums[i] = max(dp[i-2][0][0] , dp[i-2][0][1]) +nums[i];
        // dp[i][1][0] 0 steal，i not steal： dp[i][1][0] = max(dp[i-1][1][0] , dp[i-1][1][1]);
        // dp[i][1][1] 0 steal，i steal： dp[i][1][1] = dp[i-1][1][0] + nums[i];
        // ans = max(dp[i][0][0], dp[i][0][1], dp[i][1][0])
        int n = nums.length;
        if(n == 1) return nums[0];
        int[][][] dp = new int[n+1][2][2];
        // 因为i=1的就是首个元素, dp值都是
        dp[1][1][1] = nums[0];
        for(int i = 2; i <= n; i ++){
            dp[i][0][0] = Math.max(dp[i-1][0][0], dp[i-1][0][1]);
            dp[i][0][1] = dp[i-1][0][0] + nums[i-1];
            dp[i][1][0] = Math.max(dp[i-1][1][0], dp[i-1][1][1]);
            dp[i][1][1] = dp[i-1][1][0] + nums[i-1];
        }
        for (int i = 0; i <= n; i++) {
            System.out.print(dp[i][0][0]);
            System.out.print(" ");
            System.out.print(dp[i][0][1]);
            System.out.print(" ");
            System.out.print(dp[i][1][0]);
            System.out.print(" ");
            System.out.println(dp[i][1][1]);
        }
        return Math.max(Math.max(dp[n][0][0], dp[n][0][1]), dp[n][1][0]);

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}