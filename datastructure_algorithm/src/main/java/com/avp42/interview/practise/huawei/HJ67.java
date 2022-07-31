package com.avp42.interview.practise.huawei;

/**
 *  HJ67 24点游戏算法
 */
import java.util.*;
public class HJ67 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            // 需要使用double，题目没说明除法可以直接取整
            double[] nums = new double[4];
            for (int i = 0; i < 4; i++) {
                nums[i] = in.nextInt();
            }
            boolean[] used = new boolean[4];
            System.out.println(helper(nums, 0, 0, used));
        }
    }

    private static boolean helper(double[] nums, int cnt,  double ret, boolean[] used) {
        System.out.println(ret);
        // 只要能凑够就行，不一定要全部使用数字
        if(ret == 24) return true;
        if(cnt == nums.length) return false;
        for(int i = 0;i < nums.length; i++){
            if(used[i]) continue;
            used[i] = true;
            if(helper(nums, cnt + 1,ret + nums[i], used)) return true;
            if(helper(nums, cnt + 1,ret - nums[i], used)) return true;
            if(helper(nums, cnt + 1,ret * nums[i], used)) return true;
            if(helper(nums, cnt + 1,ret / nums[i], used)) return true;
            used[i] = false;
        }
        return false;
    }
}
