package com.avp42.datastructure.chapter5.binary_algorithm;

/**
 * @description: 二分查找
 * @author: wufc@viomi.com.cn
 * @create: 2021-06-13 16:45
 * @since： v 3.1.0
 */
public class BinarySearch {
    // 精确查找
    public int binary_search(int[] nums, int l, int r, int k){
        int mid;
        while(l <= r){
            // 存在溢出风险
//            mid = (l + r) >> 1;
            mid = l + (r - l) >> 1;
            if(nums[mid] < k){
                l = mid + 1;
            }else if(nums[mid] > k){
                r = mid - 1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    // 小区间使用顺序查找，避免边界条件的处理
    public int binary_search_v2(int[] nums, int l, int r, int k){
        int mid;
        while( r - l > 3){
            mid = l + (r - l) >> 1;
            if(nums[mid] < k){
                l = mid + 1;
            }else if(nums[mid] > k){
                r = mid - 1;
            }else{
                return mid;
            }
        }
        for(int i = l; i <= r; i ++){
            if(nums[i] == k) return i;
        }
        return -1;
    }

    // 泛型查找 比如查找大于等于k的第一个元素
    public int binary_search_01(int[] nums, int l, int r, int k){
        int mid;
        while(l < r){
            // 注意右移符号的优先级比 加号，减号的优先级还要低
            mid = l + ((r - l) >> 1);
            if(nums[mid] >= k) r = mid;
            else l = mid + 1;
        }
        // 最后l == r
        // 但是也有可能返回最后一个元素的位置，这个元素不一定是大于等于k的元素
        return nums[l] >= k ? l : nums.length;
    }

    public int binary_search_01_v2(int[] nums, int l, int r, int k){
        int mid;
        while(r - l > 3){
            // 注意右移符号的优先级比 加号，减号的优先级还要低
            mid = l + ((r - l) >> 1);
            if(nums[mid] >= k) r = mid;
            else l = mid + 1;
        }
        for(int i = l; i <= r; i ++){
            if(nums[i] >= k) return i;
        }
        // 到这一步说明肯定找不到i了，所以返回哨兵位置
        return nums.length;
    }


    /** 所以对于000111类型找第一个1ra，mid 靠左，使用哨兵， 判断mid元素，结果为l处，当l==nums.length说明不存在；不使用哨兵，判断mid元素, 则需要额外判定l==r时的元素*/
    /** 所以对于111000类型找最后一个1，mid 靠右，使用哨兵，判断mid - 1元素，结果为l - 1处, 当l==-1时，说明不存在； 不使用哨兵，判断mid元素, 则需要额外判定l==r时的元素*/

    // 00001111类型  mid靠左
    //            使用哨兵    不使用哨兵(需要判断边界)
    // 11111返回     0          0
    // 00000返回     5          4
    // 00001返回     4          4
    public int binary_search_0011_1(int[] nums, int l, int r){
        int mid;
        while(l < r){
            mid = (r - l) / 2 + l;
            if(nums[mid] == 1){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        return l;
    }

    // 00001111类型  mid靠左
    //            使用哨兵
    // 11111返回     0
    // 00000返回     越界
    // 00001返回     4
    public int binary_search_0011_2(int[] nums, int l, int r){
        int mid;
        while(l < r){
            mid = (r - l) / 2 + l;
            if(nums[mid + 1] == 1){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        return l;
    }

    // 111000类型  mid 靠右
    //               使用哨兵(判断mid)       不使用哨兵(需要判断边界)
    // 11111返回        出现越界异常            4
    // 00000返回           0                  0
    // 10000返回           0                  0
    public int binary_search_1100_1(int[] nums, int l, int r){
        int mid;
        while(l < r){
            mid = (r - l + 1) / 2 + l;
            if(nums[mid] == 1){
                l = mid;
            }else{
                r = mid - 1;
            }
        }
        return l;
    }

    // 111000类型   通过增加哨兵节点(判断mid - 1)，这样既不会出界，也能返回正确的值。
    // 11111返回        5
    // 00000返回        0
    // 10000返回        1
    public int binary_search_1100_2(int[] nums, int l, int r){
        int mid;
        while(l<r){
            mid = ((r - l + 1) >> 1) + l;
            if(nums[mid - 1] == 1){
                l = mid;
            }else{
                r = mid - 1;
            }
        }
        return l;
    }



    public static void main(String[] args) {
        System.out.println("=====0011型， 不使用哨兵==========");
        int[] nums1 = new int[]{1,1,1,1,1}; // 0
        int[] nums2 = new int[]{0,0,0,0,0}; // 4
        int[] nums3 = new int[]{0,0,0,0,1}; // 4
        System.out.println(new BinarySearch().binary_search_0011_1(nums1, 0, nums1.length - 1));
        System.out.println(new BinarySearch().binary_search_0011_1(nums2, 0, nums2.length - 1));
        System.out.println(new BinarySearch().binary_search_0011_1(nums3, 0, nums3.length - 1));

        System.out.println("=====0011型， 使用哨兵==========");
        int[] nums11 = new int[]{1,1,1,1,1}; // 0
        int[] nums22 = new int[]{0,0,0,0,0}; // 5
        int[] nums33 = new int[]{0,0,0,0,1}; // 4
        System.out.println(new BinarySearch().binary_search_0011_1(nums11, 0, nums11.length));
        System.out.println(new BinarySearch().binary_search_0011_1(nums22, 0, nums22.length));
        System.out.println(new BinarySearch().binary_search_0011_1(nums33, 0, nums33.length));

        System.out.println("=====0011型， 使用哨兵==========");
        int[] nums111 = new int[]{1,1,1,1,1}; // 0
//        int[] nums222 = new int[]{0,0,0,0,0}; // 越界
//        int[] nums333 = new int[]{0,0,0,0,1}; // 越界
        System.out.println(new BinarySearch().binary_search_0011_2(nums111, 0, nums111.length));
//        System.out.println(new BinarySearch().binary_search_0011_2(nums222, 0, nums222.length));
//        System.out.println(new BinarySearch().binary_search_0011_2(nums333, 0, nums333.length));

        System.out.println("=====1100型， 不使用哨兵==========");
        int[] nums44 = new int[]{1,1,1,1,1};  // 4
        int[] nums55 = new int[]{0,0,0,0,0};  // 0
        int[] nums66 = new int[]{1,0,0,0,0};  // 0
        System.out.println(new BinarySearch().binary_search_1100_1(nums44, 0, nums44.length - 1));
        System.out.println(new BinarySearch().binary_search_1100_1(nums55, 0, nums55.length - 1));
        System.out.println(new BinarySearch().binary_search_1100_1(nums66, 0, nums66.length - 1));

        System.out.println("=====1100型， 使用哨兵==========");
//        int[] nums4 = new int[]{1,1,1,1,1}; // 越界
        int[] nums5 = new int[]{0,0,0,0,0};   // 0
        int[] nums6 = new int[]{1,0,0,0,0};   // 0
//        System.out.println(new BinarySearch().binary_search_1100_1(nums4, 0, nums4.length));
        System.out.println(new BinarySearch().binary_search_1100_1(nums5, 0, nums5.length));
        System.out.println(new BinarySearch().binary_search_1100_1(nums6, 0, nums6.length));

        System.out.println("=====1100型， 使用哨兵并判断的是mid - 1元素==========");
        int[] nums7 = new int[]{1,1,1,1,1}; // 5
        int[] nums8 = new int[]{0,0,0,0,0}; // 0
        int[] nums9 = new int[]{1,0,0,0,0}; // 1
        System.out.println(new BinarySearch().binary_search_1100_2(nums7, 0, nums7.length));
        System.out.println(new BinarySearch().binary_search_1100_2(nums8, 0, nums8.length));
        System.out.println(new BinarySearch().binary_search_1100_2(nums9, 0, nums9.length));

    }


}
