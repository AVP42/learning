package com.avp42.datastructure.chapter6.exercise;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author wufc@viomi.com.cn
 * @since 2022/4/4 0004
 */
public class MonotonyQueue {

    public void maxInBlock(int[] arr, int k){
        // 极大值，使用单调递减
        Deque<Integer> q = new LinkedList<>();
        for(int i = 0; i <arr.length; i ++){
            // 单调递减，如果peekLast还要小，则剔除队列，否则不构成单调递减
            while(!q.isEmpty() && arr[q.peekLast()] < arr[i]) q.pollLast();
            q.offer(i);
            if(i - q.peekFirst() == k) q.pollFirst();
            if(i + 1 < k) continue;
            System.out.println("当前极大值为：" + arr[q.peekFirst()]);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        new MonotonyQueue().maxInBlock(nums, 3);
    }
}
