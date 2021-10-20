package com.avp42.basic.swap;

import java.util.Arrays;

/**
 * @description:
 * @author: fuchang.wu@foxmail.com
 * @date: 2021-08-25 22:40
 */
public class Swap {


    public void swap1(int[] nums, int i, int j){
       int temp = nums[i];
       nums[j] = nums[i];
       nums[i] = temp;
    }

    public void swap2(int[] nums, int i, int j){
        // 如果i == j 则i位置一定为0。改变了nums[i] 实际上也改变了nums[j]
        // 不需要考虑溢出
        if(i != j){
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }

    public void swap3(int[] nums, int i, int j){
        // 如果i == j 则i位置一定为0。改变了nums[i] 实际上也改变了nums[j]
        // 不用担心溢出问题，依然是可以计算的。在二进制运算中，负数以补码的形式存在，符号位是可以参与运算的。
        // 不过对于乘法或者除法就会有溢出的问题了。
        if(i != j){
            nums[i] = nums[i] + nums[j];
            nums[j] = nums[i] - nums[j];
            nums[i] = nums[i] - nums[j];
        }
    }

    public void swap4(int[] nums, int i, int j){
        // 如果i == j 则i位置一定为0；改变了nums[i] 实际上也改变了nums[j]
        // 不需要考虑溢出
        if(i != j){
            nums[i] = nums[i] - nums[j];
            nums[j] = nums[i] + nums[j];
            nums[i] = nums[j] - nums[i];
        }
    }

    public static void main(String[] args) {
        Swap swap = new Swap();
        int[] nums1 = {1, 2, 3};
        swap.swap1(nums1, 1,1);
        System.out.println("swap1: " + Arrays.toString(nums1));

        int[] nums2 = {1, 2, 3};
        swap.swap2(nums2, 1,1);
        System.out.println("swap2: " + Arrays.toString(nums2));

        int[] nums3 = {1, 2, 3};
        swap.swap3(nums3, 1,1);
        System.out.println("swap3: " +Arrays.toString(nums3));

        int[] nums4 = {1, 2, 3};
        swap.swap4(nums4, 1,1);
        System.out.println("swap4: " +Arrays.toString(nums4));
    }
}
