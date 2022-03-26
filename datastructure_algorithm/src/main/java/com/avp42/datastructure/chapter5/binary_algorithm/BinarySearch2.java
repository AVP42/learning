package com.avp42.datastructure.chapter5.binary_algorithm;

public class BinarySearch2 {

    /**
     * 使用0000111这种方式来解决1111000的问题
     * 判断取反，然后ans-1是所要的答案，当ans=-1时，是不存在
     * @param nums
     * @param l
     * @param r
     * @return
     */
    public static  int binary_search_111000_trans(int[] nums, int l, int r){
        int mid;
        while(l < r){
            mid = (r - l) / 2 + l;
            if(nums[mid] == 0){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        return l - 1;
    }

    public static void main(String[] args) {
        System.out.println(1 << -1);
        int[] nums1 = {1,1,0,0,0};//1
        int[] nums2 = {1,1,1,1,1};//4
        int[] nums3 = {0,0,0,0,0};//-1
        int[] nums4 = {1,0,0,0,0};//0

        System.out.println(binary_search_111000_trans(nums1, 0, 5));
        System.out.println(binary_search_111000_trans(nums2, 0, 5));
        System.out.println(binary_search_111000_trans(nums3, 0, 5));
        System.out.println(binary_search_111000_trans(nums4, 0, 5));

    }
}
