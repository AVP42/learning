//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
//。 
//
// 返回 滑动窗口中的最大值 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], k = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 105 
// -104 <= nums[i] <= 104 
// 1 <= k <= nums.length 
// 
// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列）


package com.avp42.leetcode.editor.cn;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * 239 滑动窗口最大值
 */
public class SlidingWindowMaximum{
    public static void main(String[] args) {
        Solution solution = new SlidingWindowMaximum().new Solution();
        int[] ret = solution.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        System.out.println(Arrays.toString(ret));
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length < k) return new int[]{};
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int cnt = 0;
        int[] ans = new int[nums.length - k + 1];
        for(int i = 0, j = 0; i <nums.length; i ++){
            if(cnt < k){
                cnt += 1;
            }else if(cnt == k){
                ans[j++] = map.lastKey();
                Integer count = map.get(nums[i-k]);
                if(--count  == 0){
                    map.remove(nums[i-k]);
                }else{
                    map.put(nums[i - k], count);
                }
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        ans[nums.length - k] = map.lastKey();
        return ans;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}