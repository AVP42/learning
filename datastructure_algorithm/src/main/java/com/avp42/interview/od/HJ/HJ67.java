package com.avp42.interview.od.HJ;

/**
 * HJ67 24点游戏算法
 *
 * @author wufc@viomi.com.cn
 * @since 2022/5/11 0011
 */

import java.util.*;
public class HJ67 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int[] nums = new int[4];
            for (int i = 0; i < 4; i++) {
                nums[i] = in.nextInt();
            }
            System.out.println(helper(nums, 0, ret, ));
        }
    }
}
