package com.avp42.datastructure.chapter6.exercise;

import java.util.*;

/**
 * @author wufc@viomi.com.cn
 * @since 2022/4/4 0004
 */
class Solution {
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        int k = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < k) return true;
            while (!stack.isEmpty() && stack.peek() < nums[i]) {
                k = Math.max(stack.pop(), k);
            }
            stack.push(nums[i]);
        }
        return false;
    }
}