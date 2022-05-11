//给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。 
//
// 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。 
//
// 返回容器可以储存的最大水量。 
//
// 说明：你不能倾斜容器。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：[1,8,6,2,5,4,8,3,7]
//输出：49 
//解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。 
//
// 示例 2： 
//
// 
//输入：height = [1,1]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 2 <= n <= 105 
// 0 <= height[i] <= 104 
// 
// Related Topics 贪心 数组 双指针


package com.avp42.leetcode.editor.cn;

/**
 * 11 盛最多水的容器
 */
public class ContainerWithMostWater{
    public static void main(String[] args) {
        Solution solution = new ContainerWithMostWater().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxArea(int[] height) {
        return maxArea0(height, 0, height.length - 1);
    }

    int maxArea0(int[] height, int l, int r){
        int ans = 0;
        while(l < r){
            ans = Math.max(ans, (r -l)* Math.min(height[l], height[r]));
            if(height[l] < height[r]){
                l ++;
            }else if(height[r] < height[l]){
                r --;
            }else{
                l++;
                r--;

            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}