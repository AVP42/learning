package com.avp42.datastructure.chapter5.binary_algorithm;

import java.util.Arrays;

public class BinarySearch3 {

    /**
     * 普通的二分查找
     */
    public static  int binary_search(int[] nums, int l, int r, int target){
        int ans = -1, mid;
        // 这里要等于，不同的不需要
        while(l <= r){
            mid = (r - l) / 2 + l;
            if(nums[mid] == 1){
                // 更新ans
                ans = mid;
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 0000111  另外一种解决方式  不使用哨兵，返回-1就是不存在
     */
    public static  int binary_search_000111(int[] nums, int l, int r){
        int ans = -1, mid;
        // 这里要等于，不同的不需要
        while(l <= r){
            mid = (r - l) / 2 + l;
            if(nums[mid] == 1){
                // 更新ans
                ans = mid;
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 11110000 最后一个1，如果返回初始的ans=-1，说明不存在
     */
    public static  int binary_search_111000(int[] nums, int l, int r){
        int ans = -1, mid;
        while(l <= r){
            mid = (r - l) / 2 + l;
            if(nums[mid] == 1){
                // 更新ans
                ans = mid;
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(1 << -1);
        int[] nums1 = {0,0,0,1,1};// 3
        int[] nums2 = {1,1,1,1,1};// 0
        int[] nums3 = {0,0,0,0,0};// -1
        int[] nums4 = {0,0,0,0,1};// 4

        System.out.println(binary_search_000111(nums1, 0, 4));
        System.out.println(binary_search_000111(nums2, 0, 4));
        System.out.println(binary_search_000111(nums3, 0, 4));
        System.out.println(binary_search_000111(nums4, 0, 4));
        System.out.println();
        int[] nums11 = {1,1,0,0,0};// 1
        int[] nums21 = {1,1,1,1,1};// 4
        int[] nums31 = {0,0,0,0,0};// -1
        int[] nums41 = {1,1,1,1,0};// 3

        System.out.println(binary_search_111000(nums11, 0, 4));
        System.out.println(binary_search_111000(nums21, 0, 4));
        System.out.println(binary_search_111000(nums31, 0, 4));
        System.out.println(binary_search_111000(nums41, 0, 4));

    }
}
