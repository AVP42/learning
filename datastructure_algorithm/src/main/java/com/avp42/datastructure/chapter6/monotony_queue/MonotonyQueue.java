package com.avp42.datastructure.chapter6.monotony_queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 单调队列
 * @author: wufc@viomi.com.cn
 * @create: 2021-07-06 19:58
 * @since： v 3.1.0
 */
public class MonotonyQueue {
    public static  void findMaxAndMinWithinWindow(int[] nums, int k){
        Deque<Integer> maxQueue = new LinkedList<>();
        Deque<Integer> minQueue = new LinkedList<>();

        for(int i = 0; i < nums.length; i ++){
            while(!maxQueue.isEmpty() && nums[maxQueue.peekLast()] < nums[i]){
                maxQueue.pollLast();
            }
            maxQueue.offer(i);
            if(i  + 1 < k) continue;
            System.out.print(nums[maxQueue.peek()] + " ");
//            if(i - maxQueue.peek() + 1 > k) maxQueue.pollFirst();
            if( i - maxQueue.peek() == k) maxQueue.pollFirst();
        }

        System.out.println("--------");

        for(int i = 0; i < nums.length; i ++){
            while(!minQueue.isEmpty() && nums[minQueue.peekLast()] > nums[i]){
                minQueue.pollLast();
            }
            minQueue.offer(i);
            if(i + 1 < k) continue;
            System.out.print(nums[minQueue.peek()] + " ");
//            if(i - minQueue.peekFirst() + 1 > k) minQueue.pollFirst();
            if(i - minQueue.peekFirst() == k) {
                // 说明已经超过k个元素了
                minQueue.pollFirst();
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        findMaxAndMinWithinWindow(nums, 3);
    }


}
