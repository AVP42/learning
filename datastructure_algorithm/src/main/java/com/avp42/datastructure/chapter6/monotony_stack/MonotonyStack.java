package com.avp42.datastructure.chapter6.monotony_stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

/**
 * @description: 使用单调栈解决最近大于的问题
 * @author: wufc@viomi.com.cn
 * @create: 2021-07-18 10:49
 * @since： v 3.1.0
 */
public class MonotonyStack {

    public void findClosestGt(int[] nums){
        Stack<Integer> s = new Stack<>();
        int[] pre = new int[nums.length], next = new int[nums.length];
        for(int i = 0; i < nums.length; i ++){
            // 最近大于，使用递减
            while(!s.isEmpty() && nums[s.peek()] < nums[i]){
                next[s.peek()] = i;
                s.pop();
            }
            // 到这一步，栈顶元素就是左侧第一个大于i元素的元素
            if(!s.isEmpty()) pre[i] = s.peek();
            else pre[i] = -1;
            s.push(i);
        }
        // 最后剩余在栈中的元素，都是没有找到右侧比他们大的元素
        while(!s.isEmpty()) next[s.pop()] = -1;
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(pre));
        System.out.println(Arrays.toString(next));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = Stream.of(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        new MonotonyStack().findClosestGt(nums);
    }



}
